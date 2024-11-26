package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.GestionEvento;
import logico.Jurado;
import logico.Persona;

import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ListarJurados extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private static DefaultTableModel modelo;
    private static Object[] row;
    private JScrollPane scrollPane;
    private static JTable table;
    private JButton btnEliminar;
    private JButton btnModificar;
    private Jurado juradoSeleccionado = null;

    public ListarJurados() {
        setBounds(100, 100, 725, 455);
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
                    btnModificar.setEnabled(true);
                    String idJurado = (String) table.getValueAt(index, 0);
                    juradoSeleccionado = (Jurado) GestionEvento.getInstance().buscarJuradoPorId(idJurado);
                }
            }
        });

        modelo = new DefaultTableModel();
        String[] headers = { "ID", "Nombre", "Apellido", "Teléfono", "Dirección" };
        modelo.setColumnIdentifiers(headers);
        table.setModel(modelo);
        scrollPane.setViewportView(table);

        
        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        btnModificar = new JButton("Modificar");
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (juradoSeleccionado != null) {
                    RegistrarJurado modificar = new RegistrarJurado(juradoSeleccionado);
                    modificar.setModal(true);
                    modificar.setVisible(true);
                    loadJurados();
                }
            }
        });
        buttonPane.add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (juradoSeleccionado != null) {
                    int option = JOptionPane.showConfirmDialog(null,
                            "¿Está seguro de eliminar al jurado con ID: " + juradoSeleccionado.getId() + "?",
                            "Confirmación", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        GestionEvento.getInstance().getMisPersonas().remove(juradoSeleccionado);
                        btnEliminar.setEnabled(false);
                        btnModificar.setEnabled(false);
                        loadJurados();
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

        loadJurados();
    }

    public static void loadJurados() {
        modelo.setRowCount(0); 
        row = new Object[table.getColumnCount()]; 
        for (Persona persona : GestionEvento.getInstance().getMisPersonas()) { 
            if (persona instanceof Jurado) { 
                Jurado jurado = (Jurado) persona; 
                row[0] = jurado.getId(); 
                row[1] = jurado.getNombre(); 
                row[2] = jurado.getApellido(); 
                row[3] = jurado.getTelefono(); 
                row[4] = jurado.getDireccion(); 
                modelo.addRow(row); 
            }
        }
    }

}
