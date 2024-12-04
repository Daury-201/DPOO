package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import logico.GestionEvento;
import logico.Participante;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class RegistrarParticipante extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtDireccion;
    private JTextField txtEmail;
    private JTextField txtRolOtro;
    private JComboBox<String> comboRol;
    private Participante participanteSeleccionado;

    public RegistrarParticipante(Participante participanteSeleccionado) {
        this.participanteSeleccionado = participanteSeleccionado;

        if (participanteSeleccionado == null) {
            setTitle("Registrar Participante");
        } else {
            setTitle("Modificar Participante");
        }
        setBounds(100, 100, 500, 322);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(null);

        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(12, 20, 100, 20);
        contentPanel.add(lblId);

        txtId = new JTextField();
        txtId.setBounds(120, 20, 300, 20);
        contentPanel.add(txtId);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(12, 50, 100, 20);
        contentPanel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(120, 50, 300, 20);
        contentPanel.add(txtNombre);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(12, 80, 100, 20);
        contentPanel.add(lblApellido);

        txtApellido = new JTextField();
        txtApellido.setBounds(120, 80, 300, 20);
        contentPanel.add(txtApellido);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(12, 110, 100, 20);
        contentPanel.add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(120, 110, 300, 20);
        contentPanel.add(txtTelefono);

        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setBounds(12, 140, 100, 20);
        contentPanel.add(lblDireccion);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(120, 140, 300, 20);
        contentPanel.add(txtDireccion);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(12, 170, 100, 20);
        contentPanel.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(120, 170, 300, 20);
        contentPanel.add(txtEmail);

        JLabel lblRol = new JLabel("Rol:");
        lblRol.setBounds(12, 200, 100, 20);
        contentPanel.add(lblRol);

        comboRol = new JComboBox<>();
        comboRol.setModel(new DefaultComboBoxModel(new String[] {"Autor", "Otro"}));
        comboRol.setBounds(120, 200, 150, 20);
        contentPanel.add(comboRol);

        txtRolOtro = new JTextField();
        txtRolOtro.setBounds(280, 200, 140, 20);
        txtRolOtro.setVisible(false);
        contentPanel.add(txtRolOtro);

        comboRol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboRol.getSelectedItem().equals("Otro")) {
                    txtRolOtro.setVisible(true);
                } else {
                    txtRolOtro.setVisible(false);
                }
            }
        });

        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarParticipante();
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

        cargarDatosParticipante();
    }

    private void cargarDatosParticipante() {
        if (participanteSeleccionado != null) {
            txtId.setText(participanteSeleccionado.getId());
            txtId.setEditable(false);
            txtNombre.setText(participanteSeleccionado.getNombre());
            txtApellido.setText(participanteSeleccionado.getApellido());
            txtTelefono.setText(participanteSeleccionado.getTelefono());
            txtDireccion.setText(participanteSeleccionado.getDireccion());
            txtEmail.setText(participanteSeleccionado.getEmail());
            comboRol.setSelectedItem(participanteSeleccionado.getRol());
            if (comboRol.getSelectedItem().equals("Otro")) {
                txtRolOtro.setText(participanteSeleccionado.getRol());
                txtRolOtro.setVisible(true);
            }
        }
    }

    private void guardarParticipante() {
        String id = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String email = txtEmail.getText().trim();
        String rol = (comboRol.getSelectedItem().equals("Otro")) ? txtRolOtro.getText().trim() : comboRol.getSelectedItem().toString();

        if (id.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || direccion.isEmpty() || email.isEmpty() || rol.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (participanteSeleccionado == null) {
            if (GestionEvento.getInstance().buscarParticipantePorId(id) != null) {
                JOptionPane.showMessageDialog(this, "Ya existe un participante con este ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Participante nuevoParticipante = new Participante(id, nombre, apellido, telefono, direccion, email, rol);
            GestionEvento.getInstance().agregarPersona(nuevoParticipante);
            JOptionPane.showMessageDialog(this, "Participante registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            participanteSeleccionado.setNombre(nombre);
            participanteSeleccionado.setApellido(apellido);
            participanteSeleccionado.setTelefono(telefono);
            participanteSeleccionado.setDireccion(direccion);
            participanteSeleccionado.setEmail(email);
            participanteSeleccionado.setRol(rol);
            JOptionPane.showMessageDialog(this, "Participante modificado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
        dispose();
    }
}
