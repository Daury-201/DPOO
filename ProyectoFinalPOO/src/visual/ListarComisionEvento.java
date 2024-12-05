package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import logico.Evento;
import logico.Comision;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.util.List;

public class ListarComisionEvento extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel modelo;
    private Evento evento;

    public ListarComisionEvento(Evento evento) {
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
        String[] headers = { "ID Comisión", "Nombre", "Área", "Jurados", "Trabajos Evaluar" };
        modelo.setColumnIdentifiers(headers);
        table.setModel(modelo);
        scrollPane.setViewportView(table);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        buttonPane.add(btnCerrar);

        cargarComisiones();
    }

    private void cargarComisiones() {
        modelo.setRowCount(0);
        List<Comision> comisiones = evento.getComisionesEvento();
        for (Comision comision : comisiones) {
            Object[] row = {
                comision.getIdComision(),
                comision.getNombreComision(),
                comision.getArea(),
                comision.getJuradoComision().size(),
                comision.getTrabajosEvaluar().size()
            };
            modelo.addRow(row);
        }
    }
}
