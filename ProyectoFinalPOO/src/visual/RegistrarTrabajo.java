package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import logico.GestionEvento;
import logico.Participante;
import logico.TrabajoCientifico;

public class RegistrarTrabajo extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtArea;
    private JComboBox<Participante> comboAutor;
    private JCheckBox chkCoautores; 
    private JList<Participante> listCoautores; 
    private DefaultListModel<Participante> coautorListModel;
    private TrabajoCientifico trabajoSeleccionado;

    public RegistrarTrabajo(TrabajoCientifico trabajoSeleccionado) {
        this.trabajoSeleccionado = trabajoSeleccionado;

        if (trabajoSeleccionado == null) {
            setTitle("Registrar Trabajo Científico");
        } else {
            setTitle("Modificar Trabajo Científico");
        }
        setBounds(100, 100, 600, 500);
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

        JLabel lblArea = new JLabel("Área:");
        lblArea.setBounds(12, 80, 100, 20);
        contentPanel.add(lblArea);

        txtArea = new JTextField();
        txtArea.setBounds(120, 80, 300, 20);
        contentPanel.add(txtArea);

        JLabel lblAutor = new JLabel("Autor:");
        lblAutor.setBounds(12, 110, 100, 20);
        contentPanel.add(lblAutor);

        comboAutor = new JComboBox<>();
        comboAutor.setBounds(120, 110, 300, 20);
        contentPanel.add(comboAutor);

        chkCoautores = new JCheckBox("¿Agregar Coautores?");
        chkCoautores.setBounds(12, 140, 200, 20);
        contentPanel.add(chkCoautores);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(120, 170, 300, 120);
        contentPanel.add(scrollPane);

        coautorListModel = new DefaultListModel<>();
        listCoautores = new JList<>(coautorListModel);
        scrollPane.setViewportView(listCoautores);

        
        scrollPane.setVisible(false);

        chkCoautores.addActionListener(e -> scrollPane.setVisible(chkCoautores.isSelected()));

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarTrabajo());
        buttonPane.add(btnGuardar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        buttonPane.add(btnCancelar);

        cargarAutores();
        cargarDatosTrabajo();
    }

    private void cargarAutores() {
        comboAutor.removeAllItems();
        for (Participante participante : GestionEvento.getInstance().getParticipantes()) {
            if (participante.getRol().equalsIgnoreCase("Autor")) {
                comboAutor.addItem(participante);
            }
        }

        coautorListModel.clear();
        for (Participante participante : GestionEvento.getInstance().getParticipantes()) {
            if (!participante.getRol().equalsIgnoreCase("Autor")) {
                coautorListModel.addElement(participante);
            }
        }
    }

    private void cargarDatosTrabajo() {
        if (trabajoSeleccionado != null) {
            txtId.setText(trabajoSeleccionado.getId());
            txtId.setEditable(false);
            txtNombre.setText(trabajoSeleccionado.getNombre());
            txtArea.setText(trabajoSeleccionado.getArea());
            comboAutor.setSelectedItem(trabajoSeleccionado.getAutor());
        }
    }

    private void guardarTrabajo() {
        String id = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();
        String area = txtArea.getText().trim();
        Participante autor = (Participante) comboAutor.getSelectedItem();

        
        if (id.isEmpty() || nombre.isEmpty() || area.isEmpty() || autor == null) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ArrayList<Participante> coautoresSeleccionados = new ArrayList<>();
        
        if (chkCoautores.isSelected()) {
            coautoresSeleccionados.addAll(listCoautores.getSelectedValuesList());
        }

        if (trabajoSeleccionado == null) { 
            
            if (GestionEvento.getInstance().buscarTrabajoPorId(id) != null) {
                JOptionPane.showMessageDialog(this, "Ya existe un trabajo con este ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            
            TrabajoCientifico nuevoTrabajo = new TrabajoCientifico(id, nombre, area, autor);
            nuevoTrabajo.setEstado("En revisión"); 
            
            for (Participante coautor : coautoresSeleccionados) {
                nuevoTrabajo.agregarCoautor(coautor);
            }

            
            autor.agregarTrabajo(nuevoTrabajo);
            GestionEvento.getInstance().agregarTrabajo(nuevoTrabajo);

            JOptionPane.showMessageDialog(this, "Trabajo científico registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else { 
           
            trabajoSeleccionado.setNombre(nombre);
            trabajoSeleccionado.setArea(area);
            trabajoSeleccionado.setAutor(autor);

            
            trabajoSeleccionado.getCoautores().clear();
            trabajoSeleccionado.getCoautores().addAll(coautoresSeleccionados);

            JOptionPane.showMessageDialog(this, "Trabajo científico modificado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }

        dispose();
    }
}