package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import logico.GestionEvento;
import logico.Recurso;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class RegistrarRecurso extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtIdRecurso;
    private JTextField txtDescripcion;
    private JComboBox<String> comboEstado;
    private JComboBox<String> comboTipo;
    private JSpinner spinner; 
    private Recurso recursoSeleccionado;

    public RegistrarRecurso(Recurso recursoSeleccionado) {
        this.recursoSeleccionado = recursoSeleccionado;

        if (recursoSeleccionado == null) {
            setTitle("Registrar Recurso");
        } else {
            setTitle("Modificar Recurso");
        }
        setBounds(100, 100, 500, 322);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(null);

        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblIdRecurso = new JLabel("ID Recurso:");
        lblIdRecurso.setBounds(12, 20, 100, 20);
        contentPanel.add(lblIdRecurso);

        txtIdRecurso = new JTextField();
        txtIdRecurso.setBounds(120, 20, 200, 23);
        contentPanel.add(txtIdRecurso);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(12, 60, 100, 20);
        contentPanel.add(lblEstado);

        comboEstado = new JComboBox<>();
        comboEstado.setBounds(120, 60, 200, 23);
        comboEstado.setModel(new DefaultComboBoxModel<>(new String[] { "Disponible", "En uso", "No disponible" }));
        contentPanel.add(comboEstado);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(12, 100, 100, 20);
        contentPanel.add(lblTipo);

        comboTipo = new JComboBox<>();
        comboTipo.setBounds(120, 100, 200, 23);
        comboTipo.setModel(new DefaultComboBoxModel<>(new String[] { "Material", "Equipo", "Personal", "Otro" }));
        contentPanel.add(comboTipo);

        JLabel lblCantidadDisponible = new JLabel("Cantidad Disponible:");
        lblCantidadDisponible.setBounds(12, 140, 120, 20);
        contentPanel.add(lblCantidadDisponible);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(12, 180, 100, 20);
        contentPanel.add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(120, 180, 300, 23);
        contentPanel.add(txtDescripcion);
        
       
        spinner = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1)); 
        spinner.setBounds(130, 140, 58, 20);
        contentPanel.add(spinner);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarRecurso();
            }
        });
        buttonPane.add(btnGuardar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPane.add(btnCancelar);

        cargarDatosRecurso();
    }

    private void cargarDatosRecurso() {
        if (recursoSeleccionado != null) {
            txtIdRecurso.setText(recursoSeleccionado.getIdRecurso());
            txtIdRecurso.setEditable(false);
            comboEstado.setSelectedItem(recursoSeleccionado.getEstado());
            comboTipo.setSelectedItem(recursoSeleccionado.getTipo());
            spinner.setValue(recursoSeleccionado.getCantidadDisponible()); 
            txtDescripcion.setText(recursoSeleccionado.getDescripcion());
        }
    }

    private void guardarRecurso() {
        String idRecurso = txtIdRecurso.getText().trim();
        String estado = (String) comboEstado.getSelectedItem();
        String tipo = (String) comboTipo.getSelectedItem();
        int cantidadDisponible = (int) spinner.getValue();
        String descripcion = txtDescripcion.getText().trim();

        if (idRecurso.isEmpty() || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (cantidadDisponible < 0) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (recursoSeleccionado == null) {
            if (GestionEvento.getInstance().buscarRecursoPorId(idRecurso) != null) {
                JOptionPane.showMessageDialog(this, "Ya existe un recurso con este ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Recurso nuevoRecurso = new Recurso(idRecurso, estado, tipo, cantidadDisponible, descripcion);
            GestionEvento.getInstance().getMisRecursos().add(nuevoRecurso);
            JOptionPane.showMessageDialog(this, "Recurso registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            recursoSeleccionado.setEstado(estado);
            recursoSeleccionado.setTipo(tipo);
            recursoSeleccionado.setCantidadDisponible(cantidadDisponible);
            recursoSeleccionado.setDescripcion(descripcion);
            JOptionPane.showMessageDialog(this, "Recurso modificado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }

        try {
            GestionEvento.getInstance().guardarDatos("gestionEvento.dat");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        dispose();
    }
}
