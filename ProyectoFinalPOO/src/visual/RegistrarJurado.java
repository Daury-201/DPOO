package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import logico.GestionEvento;
import logico.Jurado;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class RegistrarJurado extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtDireccion;
    private Jurado juradoSeleccionado;

    
    public RegistrarJurado(Jurado juradoSeleccionado) {
        this.juradoSeleccionado = juradoSeleccionado;

      
        if (juradoSeleccionado == null) {
            setTitle("Registrar Jurado");
        } else {
            setTitle("Modificar Jurado");
        }
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(null);

        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        
        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(12, 20, 70, 15);
        contentPanel.add(lblId);

        txtId = new JTextField();
        txtId.setBounds(120, 20, 300, 20);
        contentPanel.add(txtId);
        txtId.setColumns(10);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(12, 50, 70, 15);
        contentPanel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(120, 50, 300, 20);
        contentPanel.add(txtNombre);
        txtNombre.setColumns(10);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(12, 80, 70, 15);
        contentPanel.add(lblApellido);

        txtApellido = new JTextField();
        txtApellido.setBounds(120, 80, 300, 20);
        contentPanel.add(txtApellido);
        txtApellido.setColumns(10);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(12, 110, 70, 15);
        contentPanel.add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(120, 110, 300, 20);
        contentPanel.add(txtTelefono);
        txtTelefono.setColumns(10);

        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setBounds(12, 140, 70, 15);
        contentPanel.add(lblDireccion);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(120, 140, 300, 20);
        contentPanel.add(txtDireccion);
        txtDireccion.setColumns(10);

        
        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarJurado();
            }
        });
        btnGuardar.setActionCommand("OK");
        buttonPane.add(btnGuardar);
        getRootPane().setDefaultButton(btnGuardar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCancelar.setActionCommand("Cancel");
        buttonPane.add(btnCancelar);

        
        cargarDatosJurado();
    }

    
    private void cargarDatosJurado() {
        if (juradoSeleccionado != null) {
            txtId.setText(juradoSeleccionado.getId());
            txtId.setEditable(false); 
            txtNombre.setText(juradoSeleccionado.getNombre());
            txtApellido.setText(juradoSeleccionado.getApellido());
            txtTelefono.setText(juradoSeleccionado.getTelefono());
            txtDireccion.setText(juradoSeleccionado.getDireccion());
        }
    }

    private void guardarJurado() {
        String id = txtId.getText().trim(); 
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String direccion = txtDireccion.getText().trim();

      
        if (id.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || direccion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }

       
        if (juradoSeleccionado == null) {
            
            if (GestionEvento.getInstance().buscarJuradoPorId(id) != null) {
                JOptionPane.showMessageDialog(this, "Ya existe un jurado con este ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return; 
            }

            
            Jurado nuevoJurado = new Jurado(id, nombre, apellido, telefono, direccion);
            GestionEvento.getInstance().agregarPersona(nuevoJurado);
            JOptionPane.showMessageDialog(this, "Jurado registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            
            juradoSeleccionado.setNombre(nombre);
            juradoSeleccionado.setApellido(apellido);
            juradoSeleccionado.setTelefono(telefono);
            juradoSeleccionado.setDireccion(direccion);
            JOptionPane.showMessageDialog(this, "Jurado modificado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
        dispose();
    }

    
}
