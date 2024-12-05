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
import logico.Evento;
import logico.Persona;

import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarEvento extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private static DefaultTableModel modelo;
    private static Object[] row;
    private JScrollPane scrollPane;
    private static JTable table;
    private JButton btnEliminar;
    private JButton btnModificar;
    private Evento eventoSeleccionado = null;
    private JButton btnNewButton;
    private JButton btnNewButton_1;

    public ListarEvento() {
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
                    String idEvento = (String) table.getValueAt(index, 0);
                   eventoSeleccionado = (Evento) GestionEvento.getInstance().buscarEventoPorId(idEvento);
                }
            }
        });

        modelo = new DefaultTableModel();
        String[] headers = { "ID", "Nombre", "Tipo", "Fecha Inicio", "Fecha Fin" };
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
                if (eventoSeleccionado != null) {
                    RegistrarEvento modificar = new RegistrarEvento(eventoSeleccionado);
                    modificar.setModal(true);
                    modificar.setVisible(true);
                    loadEvento();
                }
            }
        });
        
        btnNewButton = new JButton(" Comisiones");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (eventoSeleccionado != null) {
                    ListarComisionEvento listarComisiones = new ListarComisionEvento(eventoSeleccionado);
                    listarComisiones.setModal(true);
                    listarComisiones.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un evento primero.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        buttonPane.add(btnNewButton);
        
        btnNewButton_1 = new JButton("Trabajos");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (eventoSeleccionado != null) {
                    ListarTrabajoEvento listarTrabajos = new ListarTrabajoEvento(eventoSeleccionado);
                    listarTrabajos.setModal(true);
                    listarTrabajos.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un evento primero.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        buttonPane.add(btnNewButton_1);
        buttonPane.add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (eventoSeleccionado!= null) {
                    int option = JOptionPane.showConfirmDialog(null,
                            "¿Está seguro de eliminar al evento con ID: " + eventoSeleccionado.getIdEvento() + "?",
                            "Confirmación", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        GestionEvento.getInstance().getMisEventos().remove(eventoSeleccionado);
                        btnEliminar.setEnabled(false);
                        btnModificar.setEnabled(false);
                        loadEvento();
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

        loadEvento();
    }

    public static void loadEvento() {
        modelo.setRowCount(0); 
        row = new Object[table.getColumnCount()]; 
        for (Evento evento : GestionEvento.getInstance().getMisEventos()) {
                row[0] = evento.getIdEvento();
                row[1] = evento.getNombre();
                row[2] = evento.getTipo();
                row[3] = evento.getFechaInicio();
                row[4] = evento.getFechaFin();
                modelo.addRow(row);
            }
        }
}
