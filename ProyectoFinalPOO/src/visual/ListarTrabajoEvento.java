package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import logico.Evento;
import logico.Jurado;
import logico.Comision;
import logico.TrabajoCientifico;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.util.List;

public class ListarTrabajoEvento extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel modelo;
    private Evento evento;

    public ListarTrabajoEvento(Evento evento) {
        this.evento = evento;
        setBounds(100, 100, 700, 400);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        modelo = new DefaultTableModel();
        String[] headers = { "ID Trabajo", "Nombre", "Área", "Comisión Evaluadora", "Estado" };
        modelo.setColumnIdentifiers(headers);
        table.setModel(modelo);
        scrollPane.setViewportView(table);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        buttonPane.add(btnCerrar);

        cargarTrabajos();
    }

    private void cargarTrabajos() {
        modelo.setRowCount(0);
        
        for (Comision comision : evento.getComisionesEvento()) {
            for (TrabajoCientifico trabajo : comision.getTrabajosEvaluar()) {
                StringBuilder jurados = new StringBuilder();
                for (Jurado jurado : comision.getJuradoComision()) {
                    jurados.append(jurado.getNombre()).append(" ").append(jurado.getApellido()).append("; ");
                }
               
                Object[] row = {
                    trabajo.getId(),
                    trabajo.getNombre(),
                    trabajo.getArea(),
                    jurados.toString(),  
                    trabajo.getEstado()   
                };
                
                modelo.addRow(row);
            }
        }
    }
}
