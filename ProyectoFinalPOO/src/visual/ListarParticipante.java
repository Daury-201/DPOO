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
import logico.Participante;
import logico.Persona;

import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarParticipante extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private static DefaultTableModel modelo;
    private static Object[] row;
    private JScrollPane scrollPane;
    private static JTable table;
    private JButton btnEliminar;
    private JButton btnModificar;
    private Participante participanteSeleccionado = null;

    public ListarParticipante() {
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
                    String idParticipante = (String) table.getValueAt(index, 0);
                    participanteSeleccionado = (Participante) GestionEvento.getInstance().buscarParticipantePorId(idParticipante);
                }
            }
        });

        modelo = new DefaultTableModel();
        String[] headers = { "ID", "Nombre", "Apellido", "Teléfono", "Dirección", "Email", "Rol" };
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
                if (participanteSeleccionado != null) {
                    RegistrarParticipante modificar = new RegistrarParticipante(participanteSeleccionado);
                    modificar.setModal(true);
                    modificar.setVisible(true);
                    loadParticipantes();
                }
            }
        });
        buttonPane.add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (participanteSeleccionado != null) {
                    int option = JOptionPane.showConfirmDialog(null,
                            "¿Está seguro de eliminar al participante con ID: " + participanteSeleccionado.getId() + "?",
                            "Confirmación", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        GestionEvento.getInstance().getMisPersonas().remove(participanteSeleccionado);
                        btnEliminar.setEnabled(false);
                        btnModificar.setEnabled(false);
                        loadParticipantes();
                        
                        GestionEvento.getInstance().guardarDatos("gestionEvento.dat");
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

        loadParticipantes();
    }

    public static void loadParticipantes() {
        modelo.setRowCount(0); 
        row = new Object[table.getColumnCount()]; 
        for (Persona persona : GestionEvento.getInstance().getMisPersonas()) {
            if (persona instanceof Participante) {
                Participante participante = (Participante) persona;
                row[0] = participante.getId();
                row[1] = participante.getNombre();
                row[2] = participante.getApellido();
                row[3] = participante.getTelefono();
                row[4] = participante.getDireccion();
                row[5] = participante.getEmail();
                row[6] = participante.getRol();
                modelo.addRow(row);
            }
        }
    }
}
