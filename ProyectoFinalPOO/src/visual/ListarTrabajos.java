package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.GestionEvento;
import logico.TrabajoCientifico;

import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarTrabajos extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private static DefaultTableModel modelo;
    private static Object[] row;
    private JScrollPane scrollPane;
    private static JTable table;
    private JButton btnEliminar;
    private JButton btnModificarEstado;
    private JButton btnModificarDatos; 
    private TrabajoCientifico trabajoSeleccionado = null;

    public ListarTrabajos() {
        setBounds(100, 100, 900, 500); 
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

       
        JPanel panel = new JPanel();
        contentPanel.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));

        scrollPane = new JScrollPane();
        panel.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = table.getSelectedRow();
                if (index >= 0) {
                    btnEliminar.setEnabled(true);
                    btnModificarEstado.setEnabled(true);
                    btnModificarDatos.setEnabled(true); 
                    String idTrabajo = (String) table.getValueAt(index, 0);
                    trabajoSeleccionado = GestionEvento.getInstance().buscarTrabajoPorId(idTrabajo);
                }
            }
        });

        modelo = new DefaultTableModel();
        String[] headers = { "ID", "Nombre", "Área", "Autor", "Estado" }; 
        modelo.setColumnIdentifiers(headers);
        table.setModel(modelo);
        scrollPane.setViewportView(table);

        
        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        btnModificarEstado = new JButton("Modificar Estado");
        btnModificarEstado.setEnabled(false);
        btnModificarEstado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (trabajoSeleccionado != null) {
                    modificarEstado();
                    loadTrabajos();
                }
            }
        });
        buttonPane.add(btnModificarEstado);

        
        btnModificarDatos = new JButton("Modificar Datos");
        btnModificarDatos.setEnabled(false);
        btnModificarDatos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (trabajoSeleccionado != null) {
                    RegistrarTrabajo modificar = new RegistrarTrabajo(trabajoSeleccionado);
                    modificar.setModal(true);
                    modificar.setVisible(true);
                    loadTrabajos();
                }
            }
        });
        buttonPane.add(btnModificarDatos);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (trabajoSeleccionado != null) {
                    int option = JOptionPane.showConfirmDialog(null,
                            "¿Está seguro de eliminar el trabajo con ID: " + trabajoSeleccionado.getId() + "?",
                            "Confirmación", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        GestionEvento.getInstance().getMisTrabajos().remove(trabajoSeleccionado);
                        btnEliminar.setEnabled(false);
                        btnModificarEstado.setEnabled(false);
                        btnModificarDatos.setEnabled(false); 
                        loadTrabajos();
                    }
                }
            }
        });
        buttonPane.add(btnEliminar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPane.add(btnCancelar);

        loadTrabajos();
    }

    private void modificarEstado() {
        String[] opciones = { "En revisión", "Aceptado", "Rechazado" };
        String nuevoEstado = (String) JOptionPane.showInputDialog(this, 
                "Seleccione el nuevo estado del trabajo:",
                "Modificar Estado",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                trabajoSeleccionado.getEstado());

        if (nuevoEstado != null && !nuevoEstado.equals(trabajoSeleccionado.getEstado())) {
            trabajoSeleccionado.setEstado(nuevoEstado);
            JOptionPane.showMessageDialog(this, "Estado modificado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void loadTrabajos() {
        modelo.setRowCount(0); 
        row = new Object[table.getColumnCount()]; 
        for (TrabajoCientifico trabajo : GestionEvento.getInstance().getMisTrabajos()) {
            row[0] = trabajo.getId(); 
            row[1] = trabajo.getNombre(); 
            row[2] = trabajo.getArea(); 
            row[3] = trabajo.getAutor().getNombre() + " " + trabajo.getAutor().getApellido(); 
            row[4] = trabajo.getEstado(); 
            modelo.addRow(row);
        }
    }
}
