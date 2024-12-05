package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import logico.Comision;
import logico.Evento;
import logico.GestionEvento;
import logico.Recurso;
import logico.TrabajoCientifico;

public class RegistrarEvento extends JDialog implements Serializable {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtFechaF;
    private JTextField txtFechaI;
    private static Evento eventoSeleccionado;
    private JComboBox<String> comboTipo;
    private JComboBox<String> comboTipoEvento; 
    private JComboBox<Recurso> comboRecurso;
    private JSpinner spinnerCantidad;
    private ArrayList<Recurso> recursosSeleccionados;

    public RegistrarEvento(Evento eventoSeleccionado) {
    	setResizable(false);
        RegistrarEvento.eventoSeleccionado = eventoSeleccionado;
        recursosSeleccionados = new ArrayList<>();

        if (eventoSeleccionado == null) {
            setTitle("Registrar Evento");
        } else {
            setTitle("Modificar Evento");
        }

        setBounds(100, 100, 500, 400);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(null);

        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblIdEvento = new JLabel("ID Evento:");
        lblIdEvento.setBounds(10, 19, 100, 20);
        contentPanel.add(lblIdEvento);

        txtId = new JTextField();
        txtId.setBounds(120, 17, 200, 23);
        contentPanel.add(txtId);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 58, 100, 20);
        contentPanel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(120, 57, 200, 23);
        contentPanel.add(txtNombre);

        JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
        lblFechaInicio.setBounds(10, 97, 100, 20);
        contentPanel.add(lblFechaInicio);

        txtFechaI = new JTextField();
        txtFechaI.setBounds(120, 97, 200, 23);
        contentPanel.add(txtFechaI);

        JLabel lblFechaFin = new JLabel("Fecha Fin:");
        lblFechaFin.setBounds(10, 136, 100, 20);
        contentPanel.add(lblFechaFin);

        txtFechaF = new JTextField();
        txtFechaF.setBounds(120, 137, 200, 23);
        contentPanel.add(txtFechaF);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(10, 175, 100, 20);
        contentPanel.add(lblTipo);

        comboTipo = new JComboBox<>();
        comboTipo.setModel(new DefaultComboBoxModel<>(new String[] { "Académico", "Artístico", "Deportivo", "Open Day" }));
        comboTipo.setBounds(120, 177, 200, 23);
        contentPanel.add(comboTipo);

        JLabel lblTipoEvento = new JLabel("Tipo de Evento:");
        lblTipoEvento.setBounds(10, 214, 150, 20);
        contentPanel.add(lblTipoEvento);

        comboTipoEvento = new JComboBox<>();
        comboTipoEvento.setModel(new DefaultComboBoxModel<>(new String[] { "Panel", "Ponencia", "Póster", "Mesa Redonda" }));
        comboTipoEvento.setBounds(120, 217, 200, 23);
        contentPanel.add(comboTipoEvento);


        JLabel lblRecurso = new JLabel("Recurso:");
        lblRecurso.setBounds(10, 253, 150, 20);
        contentPanel.add(lblRecurso);

        comboRecurso = new JComboBox<>();
        comboRecurso.setBounds(120, 257, 200, 23);
        cargarRecursos();
        contentPanel.add(comboRecurso);

        JLabel lblCantidad = new JLabel("Cantidad a Usar:");
        lblCantidad.setBounds(10, 292, 150, 20);
        contentPanel.add(lblCantidad);

        spinnerCantidad = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        spinnerCantidad.setBounds(120, 297, 100, 23);
        contentPanel.add(spinnerCantidad);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarEvento());
        buttonPane.add(btnGuardar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        buttonPane.add(btnCancelar);

        cargarDatosEvento();
    }


    private void cargarRecursos() {
        comboRecurso.removeAllItems();
        for (Recurso recurso : GestionEvento.getInstance().getMisRecursos()) {
            if (recurso.getEstado().equalsIgnoreCase("Disponible")) {
                comboRecurso.addItem(recurso);
            }
        }
    }

    private void cargarDatosEvento() {
        if (eventoSeleccionado != null) {
            txtId.setText(eventoSeleccionado.getIdEvento());
            txtId.setEditable(false);
            txtNombre.setText(eventoSeleccionado.getNombre());
            txtFechaI.setText(new SimpleDateFormat("yyyy-MM-dd").format(eventoSeleccionado.getFechaInicio()));
            txtFechaF.setText(new SimpleDateFormat("yyyy-MM-dd").format(eventoSeleccionado.getFechaFin()));
            comboTipo.setSelectedItem(eventoSeleccionado.getTipo());
            comboTipoEvento.setSelectedItem(eventoSeleccionado.getTipoEvento());
        }
    }

    private void guardarEvento() {
        String id = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();
        String tipo = comboTipo.getSelectedItem().toString();
        String tipoEvento = comboTipoEvento.getSelectedItem().toString();
        Recurso recursoSeleccionado = (Recurso) comboRecurso.getSelectedItem();
        int cantidadRecurso = (int) spinnerCantidad.getValue();
        String fechaInicioStr = txtFechaI.getText().trim();
        String fechaFinStr = txtFechaF.getText().trim();

        if (id.isEmpty() || nombre.isEmpty() || fechaInicioStr.isEmpty() || fechaFinStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaInicio, fechaFin;

        try {
            fechaInicio = sdf.parse(fechaInicioStr);
            fechaFin = sdf.parse(fechaFinStr);

            if (fechaInicio.after(fechaFin)) {
                JOptionPane.showMessageDialog(this, "La fecha de inicio no puede ser posterior a la fecha de fin.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use yyyy-MM-dd.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (recursoSeleccionado != null && cantidadRecurso > 0) {
            if (cantidadRecurso > recursoSeleccionado.getCantidadDisponible()) {
                JOptionPane.showMessageDialog(this, "No hay suficiente cantidad disponible para el recurso seleccionado.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            recursoSeleccionado.setCantidadDisponible(recursoSeleccionado.getCantidadDisponible() - cantidadRecurso);
            recursosSeleccionados.add(recursoSeleccionado);
        }

        if (eventoSeleccionado == null) {
            Evento nuevoEvento = new Evento(id, nombre, tipo, fechaInicio, fechaFin);
            nuevoEvento.setTipoEvento(tipoEvento);
            nuevoEvento.setRecursos(recursosSeleccionados);
            GestionEvento.getInstance().agregarEvento(nuevoEvento);
            JOptionPane.showMessageDialog(this, "Evento registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            eventoSeleccionado.setNombre(nombre);
            eventoSeleccionado.setTipo(tipo);
            eventoSeleccionado.setFechaInicio(fechaInicio);
            eventoSeleccionado.setFechaFin(fechaFin);
            eventoSeleccionado.setTipoEvento(tipoEvento);
            eventoSeleccionado.setRecursos(recursosSeleccionados);

            JOptionPane.showMessageDialog(this, "Evento modificado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }

        GestionEvento.getInstance().guardarDatos("gestionEvento.dat");
        dispose();
    }
}
