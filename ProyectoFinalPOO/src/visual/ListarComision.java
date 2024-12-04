package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import logico.Comision;
import logico.GestionEvento;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarComision extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private static DefaultTableModel modelo;
    private static Object[] row;
    private JScrollPane scrollPane;
    private static JTable table;
    private JButton btnEliminar;
    private JButton btnModificar;
    private Comision comisionSeleccionada = null;

    public ListarComision() {
        
        setBounds(100, 100, 639, 477); 
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
                    String idComision = (String) table.getValueAt(index, 0);
                    comisionSeleccionada = GestionEvento.getInstance().buscarComisionPorId(idComision);
                }
            }
        });

        modelo = new DefaultTableModel();
        String[] headers = { "ID", "Nombre", "Área", "Jurados", "Trabajos Evaluar" };
        modelo.setColumnIdentifiers(headers);
        table.setModel(modelo);

        
        scrollPane.setPreferredSize(new Dimension(950, 300));
        scrollPane.setViewportView(table);

       
        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        btnModificar = new JButton("Modificar");
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (comisionSeleccionada != null) {
                    RegistrarComision modificar = new RegistrarComision(comisionSeleccionada);
                    modificar.setModal(true);
                    modificar.setVisible(true);
                    loadComisiones();
                }
            }
        });
        buttonPane.add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (comisionSeleccionada != null) {
                    int option = JOptionPane.showConfirmDialog(null,
                            "¿Está seguro de eliminar la comisión con ID: " + comisionSeleccionada.getIdComision() + "?",
                            "Confirmación", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        GestionEvento.getInstance().getMisComisiones().remove(comisionSeleccionada);
                        btnEliminar.setEnabled(false);
                        btnModificar.setEnabled(false);
                        loadComisiones();
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

        loadComisiones();
    }

    public static void loadComisiones() {
        modelo.setRowCount(0); 
        row = new Object[table.getColumnCount()]; 
        for (Comision comision : GestionEvento.getInstance().getMisComisiones()) {
            row[0] = comision.getIdComision();
            row[1] = comision.getNombreComision();
            row[2] = comision.getArea();
            row[3] = comision.getJuradoComision().size(); 
            row[4] = comision.getTrabajosEvaluar().size(); 
            modelo.addRow(row);
        }
    }
}
