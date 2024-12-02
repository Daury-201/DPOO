package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import logico.Participante;
import logico.TrabajoCientifico;

public class RegistrarComision extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable tableJurados;
    private JTable tableSeleccionados;
    private JTable tableTrabajos;
    private JTable tableTrabajosSeleccionados;
    private DefaultTableModel modeloJurados;
    private DefaultTableModel modeloSeleccionados;
    private DefaultTableModel modeloTrabajos;
    private DefaultTableModel modeloTrabajosSeleccionados;
    private ArrayList<Jurado> juradosSeleccionados = new ArrayList<>();
    private ArrayList<TrabajoCientifico> trabajosSeleccionados = new ArrayList<>();
    private Comision comisionSeleccionada;
    private JTextField txtIdComision;
    private JTextField txtNombreComision;
    private JComboBox<String> comboArea; 

    public RegistrarComision(Comision comisionSeleccionada) {
        this.comisionSeleccionada = comisionSeleccionada;
        setBounds(100, 100, 905, 650);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        
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

        comboArea = new JComboBox<>();
        comboArea.setBounds(120, 70, 200, 20);
        contentPanel.add(comboArea);

        
        JScrollPane scrollPaneJurados = new JScrollPane();
        scrollPaneJurados.setBounds(10, 100, 300, 200);
        contentPanel.add(scrollPaneJurados);

        tableJurados = new JTable();
        modeloJurados = new DefaultTableModel();
        String[] headersJurados = { "ID", "Nombre", "Apellido", "Teléfono" };
        modeloJurados.setColumnIdentifiers(headersJurados);
        tableJurados.setModel(modeloJurados);
        scrollPaneJurados.setViewportView(tableJurados);

        
        JButton btnAgregarJurado = new JButton("Agregar Jurado ->");
        btnAgregarJurado.setBounds(320, 170, 150, 30);
        btnAgregarJurado.addActionListener(e -> agregarJuradoSeleccionado());
        contentPanel.add(btnAgregarJurado);

        
        JButton btnEliminarJurado = new JButton("Eliminar Jurado <-");
        btnEliminarJurado.setBounds(320, 210, 150, 30);
        btnEliminarJurado.addActionListener(e -> eliminarJuradoSeleccionado());
        contentPanel.add(btnEliminarJurado);

        
        JScrollPane scrollPaneSeleccionados = new JScrollPane();
        scrollPaneSeleccionados.setBounds(500, 100, 300, 200);
        contentPanel.add(scrollPaneSeleccionados);

        tableSeleccionados = new JTable();
        modeloSeleccionados = new DefaultTableModel();
        String[] headersSeleccionados = { "ID", "Nombre", "Apellido", "Teléfono" };
        modeloSeleccionados.setColumnIdentifiers(headersSeleccionados);
        tableSeleccionados.setModel(modeloSeleccionados);
        scrollPaneSeleccionados.setViewportView(tableSeleccionados);

        
        JLabel lblTrabajos = new JLabel("Trabajos Científicos:");
        lblTrabajos.setBounds(10, 310, 150, 20);
        contentPanel.add(lblTrabajos);

        JScrollPane scrollPaneTrabajos = new JScrollPane();
        scrollPaneTrabajos.setBounds(10, 340, 300, 200);
        contentPanel.add(scrollPaneTrabajos);

        tableTrabajos = new JTable();
        modeloTrabajos = new DefaultTableModel();
        String[] headersTrabajos = { "ID", "Nombre", "Área" };
        modeloTrabajos.setColumnIdentifiers(headersTrabajos);
        tableTrabajos.setModel(modeloTrabajos);
        scrollPaneTrabajos.setViewportView(tableTrabajos);

        
        JButton btnAgregarTrabajo = new JButton("Agregar Trabajo ->");
        btnAgregarTrabajo.setBounds(320, 410, 150, 30);
        btnAgregarTrabajo.addActionListener(e -> agregarTrabajoSeleccionado());
        contentPanel.add(btnAgregarTrabajo);

        
        JButton btnEliminarTrabajo = new JButton("Eliminar Trabajo <-");
        btnEliminarTrabajo.setBounds(320, 450, 150, 30);
        btnEliminarTrabajo.addActionListener(e -> eliminarTrabajoSeleccionado());
        contentPanel.add(btnEliminarTrabajo);

        
        JScrollPane scrollPaneTrabajosSeleccionados = new JScrollPane();
        scrollPaneTrabajosSeleccionados.setBounds(500, 340, 300, 200);
        contentPanel.add(scrollPaneTrabajosSeleccionados);

        tableTrabajosSeleccionados = new JTable();
        modeloTrabajosSeleccionados = new DefaultTableModel();
        String[] headersTrabajosSeleccionados = { "ID", "Nombre", "Área" };
        modeloTrabajosSeleccionados.setColumnIdentifiers(headersTrabajosSeleccionados);
        tableTrabajosSeleccionados.setModel(modeloTrabajosSeleccionados);
        scrollPaneTrabajosSeleccionados.setViewportView(tableTrabajosSeleccionados);

        
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
        cargarAreas();
        cargarTrabajos();
        cargarDatosComision();
    }

    private void cargarJurados() {
        modeloJurados.setRowCount(0);
        for (Jurado jurado : GestionEvento.getInstance().getJurados()) {
            Object[] row = { jurado.getId(), jurado.getNombre(), jurado.getApellido(), jurado.getTelefono() };
            modeloJurados.addRow(row);
        }
    }
    
    private void cargarAreas() {
        comboArea.removeAllItems();  
        ArrayList<String> areas = new ArrayList<>();
        
 
        for (TrabajoCientifico trabajo : GestionEvento.getInstance().getMisTrabajos()) {
            String area = trabajo.getArea();
            if (!areas.contains(area)) { 
                areas.add(area);
            }
        }
        for (String area : areas) {
            comboArea.addItem(area);
        }
    }

    private void cargarTrabajos() {
        modeloTrabajos.setRowCount(0);
        for (TrabajoCientifico trabajo : GestionEvento.getInstance().getMisTrabajos()) {
            Object[] row = { trabajo.getId(), trabajo.getNombre(), trabajo.getArea() };
            modeloTrabajos.addRow(row);
        }
    }

    private void cargarDatosComision() {
        if (comisionSeleccionada != null) {
            txtIdComision.setText(comisionSeleccionada.getIdComision());
            txtIdComision.setEditable(false);
            txtNombreComision.setText(comisionSeleccionada.getNombreComision());
            String areaSeleccionada = comisionSeleccionada.getArea();
            comboArea.setSelectedItem(areaSeleccionada);

            for (Jurado jurado : comisionSeleccionada.getJuradoComision()) {
                juradosSeleccionados.add(jurado);
                Object[] row = { jurado.getId(), jurado.getNombre(), jurado.getApellido(), jurado.getTelefono() };
                modeloSeleccionados.addRow(row);
            }

            for (TrabajoCientifico trabajo : comisionSeleccionada.getTrabajosEvaluar()) {
                trabajosSeleccionados.add(trabajo);
                Object[] row = { trabajo.getId(), trabajo.getNombre(), trabajo.getArea() };
                modeloTrabajosSeleccionados.addRow(row);
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
                modeloSeleccionados.addRow(row);
                modeloJurados.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "El jurado ya está seleccionado.", "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un jurado para agregar.", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void eliminarJuradoSeleccionado() {
        int selectedRow = tableSeleccionados.getSelectedRow();
        if (selectedRow >= 0) {
            String idJurado = (String) modeloSeleccionados.getValueAt(selectedRow, 0);
            Jurado jurado = GestionEvento.getInstance().buscarJuradoPorId(idJurado);
            juradosSeleccionados.remove(jurado);
            Object[] row = { jurado.getId(), jurado.getNombre(), jurado.getApellido(), jurado.getTelefono() };
            modeloJurados.addRow(row);
            modeloSeleccionados.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un jurado para eliminar.", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void agregarTrabajoSeleccionado() {
        int selectedRow = tableTrabajos.getSelectedRow();
        if (selectedRow >= 0) {
            String idTrabajo = (String) modeloTrabajos.getValueAt(selectedRow, 0);
            TrabajoCientifico trabajo = GestionEvento.getInstance().buscarTrabajoPorId(idTrabajo);
            if (!trabajosSeleccionados.contains(trabajo)) {
                trabajosSeleccionados.add(trabajo);
                Object[] row = { trabajo.getId(), trabajo.getNombre(), trabajo.getArea() };
                modeloTrabajosSeleccionados.addRow(row);
                modeloTrabajos.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "El trabajo ya está seleccionado.", "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un trabajo para agregar.", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void eliminarTrabajoSeleccionado() {
        int selectedRow = tableTrabajosSeleccionados.getSelectedRow();
        if (selectedRow >= 0) {
            String idTrabajo = (String) modeloTrabajosSeleccionados.getValueAt(selectedRow, 0);
            TrabajoCientifico trabajo = GestionEvento.getInstance().buscarTrabajoPorId(idTrabajo);
            trabajosSeleccionados.remove(trabajo);
            Object[] row = { trabajo.getId(), trabajo.getNombre(), trabajo.getArea() };
            modeloTrabajos.addRow(row);
            modeloTrabajosSeleccionados.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un trabajo para eliminar.", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void guardarComision() {
        String id = txtIdComision.getText().trim();
        String nombre = txtNombreComision.getText().trim();
        String area = (String) comboArea.getSelectedItem();
    
        
        if (id.isEmpty() || nombre.isEmpty() || area.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        if (juradosSeleccionados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe asignar al menos un jurado a la comisión.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        if (trabajosSeleccionados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe asignar al menos un trabajo científico a la comisión.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (comisionSeleccionada == null) {
            
            Comision nuevaComision = new Comision(id, nombre, area);
            nuevaComision.setJuradoComision(juradosSeleccionados);
            nuevaComision.setTrabajosEvaluar(trabajosSeleccionados);
            GestionEvento.getInstance().agregarComision(nuevaComision);
            JOptionPane.showMessageDialog(this, "Comisión guardada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            
            comisionSeleccionada.setNombreComision(nombre);
            comisionSeleccionada.setArea(area);
            comisionSeleccionada.setJuradoComision(juradosSeleccionados);
            comisionSeleccionada.setTrabajosEvaluar(trabajosSeleccionados);
            JOptionPane.showMessageDialog(this, "Comisión actualizada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }

        dispose();
    }
}