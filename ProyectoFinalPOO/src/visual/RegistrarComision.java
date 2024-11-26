package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import logico.Comision;
import logico.GestionEvento;
import logico.Jurado;

public class RegistrarComision extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable tableJurados;
    private JTable tableSeleccionados;
    private DefaultTableModel modeloJurados;
    private DefaultTableModel modeloSeleccionados;
    private ArrayList<Jurado> juradosSeleccionados = new ArrayList<>();
    private Comision comisionSeleccionada;
    private JTextField txtIdComision;
    private JTextField txtNombreComision;
    private JTextField txtArea;

    public RegistrarComision(Comision comisionSeleccionada) {
        this.comisionSeleccionada = comisionSeleccionada;
        setBounds(100, 100, 700, 500);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        // Campos para ID, Nombre y Área
        JLabel lblIdComision = new JLabel("ID Comisión:");
        lblIdComision.setBounds(10, 10, 100, 20);
        contentPanel.add(lblIdComision);

        txtIdComision = new JTextField();
        txtIdComision.setBounds(120, 10, 200, 20);
        contentPanel.add(txtIdComision);

        JLabel lblNombreComision = new JLabel("Nombre:");
        lblNombreComision.setBounds(10, 40, 100, 20);
        contentPanel.add(lblNombreComision);

        txtNombreComision = new JTextField();
        txtNombreComision.setBounds(120, 40, 200, 20);
        contentPanel.add(txtNombreComision);

        JLabel lblArea = new JLabel("Área:");
        lblArea.setBounds(10, 70, 100, 20);
        contentPanel.add(lblArea);

        txtArea = new JTextField();
        txtArea.setBounds(120, 70, 200, 20);
        contentPanel.add(txtArea);

        // Tabla para jurados disponibles
        JScrollPane scrollPaneJurados = new JScrollPane();
        scrollPaneJurados.setBounds(10, 100, 300, 250);
        contentPanel.add(scrollPaneJurados);

        tableJurados = new JTable();
        modeloJurados = new DefaultTableModel();
        String[] headersJurados = { "ID", "Nombre", "Apellido", "Teléfono" };
        modeloJurados.setColumnIdentifiers(headersJurados);
        tableJurados.setModel(modeloJurados);
        scrollPaneJurados.setViewportView(tableJurados);

        // Botón para agregar jurados
        JButton btnAgregar = new JButton("Agregar ->");
        btnAgregar.setBounds(320, 200, 120, 30);
        btnAgregar.addActionListener(e -> agregarJuradoSeleccionado());
        contentPanel.add(btnAgregar);

        // Tabla para jurados seleccionados
        JScrollPane scrollPaneSeleccionados = new JScrollPane();
        scrollPaneSeleccionados.setBounds(450, 100, 300, 250);
        contentPanel.add(scrollPaneSeleccionados);

        tableSeleccionados = new JTable();
        modeloSeleccionados = new DefaultTableModel();
        String[] headersSeleccionados = { "ID", "Nombre", "Apellido", "Teléfono" };
        modeloSeleccionados.setColumnIdentifiers(headersSeleccionados);
        tableSeleccionados.setModel(modeloSeleccionados);
        scrollPaneSeleccionados.setViewportView(tableSeleccionados);

        // Botones Guardar y Cancelar
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarComision());
        buttonPane.add(btnGuardar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        buttonPane.add(btnCancelar);

        cargarJurados();
        cargarDatosComision();
    }

    private void cargarJurados() {
        modeloJurados.setRowCount(0); // Limpia la tabla
        for (Jurado jurado : GestionEvento.getInstance().getJurados()) {
            Object[] row = { jurado.getId(), jurado.getNombre(), jurado.getApellido(), jurado.getTelefono() };
            modeloJurados.addRow(row);
        }
    }

    private void cargarDatosComision() {
        if (comisionSeleccionada != null) {
            txtIdComision.setText(comisionSeleccionada.getIdComision());
            txtIdComision.setEditable(false);
            txtNombreComision.setText(comisionSeleccionada.getNombreComision());
            txtArea.setText(comisionSeleccionada.getArea());

            // Cargar jurados asignados previamente
            for (Jurado jurado : comisionSeleccionada.getJuradoComision()) {
                juradosSeleccionados.add(jurado);
                Object[] row = { jurado.getId(), jurado.getNombre(), jurado.getApellido(), jurado.getTelefono() };
                modeloSeleccionados.addRow(row);
            }
        }
    }

    private void agregarJuradoSeleccionado() {
        int selectedRow = tableJurados.getSelectedRow();
        if (selectedRow >= 0) {
            String idJurado = (String) modeloJurados.getValueAt(selectedRow, 0);
            Jurado jurado = GestionEvento.getInstance().buscarJuradoPorId(idJurado);
            if (!juradosSeleccionados.contains(jurado)) {
                juradosSeleccionados.add(jurado);
                Object[] row = { jurado.getId(), jurado.getNombre(), jurado.getApellido(), jurado.getTelefono() };
                modeloSeleccionados.addRow(row); // Añadir a la tabla de seleccionados
            } else {
                JOptionPane.showMessageDialog(this, "El jurado ya está seleccionado.", "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un jurado para agregar.", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void guardarComision() {
        String id = txtIdComision.getText();
        String nombre = txtNombreComision.getText();
        String area = txtArea.getText();

        if (comisionSeleccionada == null) {
            Comision nuevaComision = new Comision(id, nombre, area);
            nuevaComision.setJuradoComision(juradosSeleccionados);
            GestionEvento.getInstance().agregarComision(nuevaComision);
        } else {
            comisionSeleccionada.setNombreComision(nombre);
            comisionSeleccionada.setArea(area);
            comisionSeleccionada.setJuradoComision(juradosSeleccionados);
        }

        JOptionPane.showMessageDialog(this, "Comisión guardada exitosamente.", "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}
