package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.GestionEvento;
import logico.Recurso;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionarRecurso extends JDialog {

	 private final JPanel contentPanel = new JPanel();
	    private static DefaultTableModel modelo;
	    private static Object[] row;
	    private JScrollPane scrollPane;
	    private static JTable table;
	    private JButton btnEliminar;
	    private JButton btnModificar;
	    private Recurso recursoSeleccionado = null;

	    public GestionarRecurso() {
	        setBounds(100, 100, 725, 455);
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
	                    String idRecurso = (String) table.getValueAt(index, 0);
	                    recursoSeleccionado = (Recurso) GestionEvento.getInstance().buscarRecursoPorId(idRecurso);
	                }
	            }
	        });

	        modelo = new DefaultTableModel();
	        String[] headers = { "ID", "estado", "Tipo", "Cantidad Disponible", "descripcion"};
	        modelo.setColumnIdentifiers(headers);
	        table.setModel(modelo);
	        scrollPane.setViewportView(table);

	       
	        JPanel buttonPane = new JPanel();
	        buttonPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
	        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
	        getContentPane().add(buttonPane, BorderLayout.SOUTH);

	        btnModificar = new JButton("Modificar");
	        btnModificar.setEnabled(false);
	        btnModificar.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if (recursoSeleccionado != null) {
	                    RegistrarRecurso modificar = new RegistrarRecurso(recursoSeleccionado);
	                    modificar.setModal(true);
	                    modificar.setVisible(true);
	                    loadRecursos();
	                }
	            }
	        });
	        buttonPane.add(btnModificar);

	        btnEliminar = new JButton("Eliminar");
	        btnEliminar.setEnabled(false);
	        btnEliminar.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if (recursoSeleccionado != null) {
	                    int option = JOptionPane.showConfirmDialog(null,
	                            "¿Está seguro de eliminar al recurso con ID: " + recursoSeleccionado.getIdRecurso() + "?",
	                            "Confirmación", JOptionPane.OK_CANCEL_OPTION);
	                    if (option == JOptionPane.OK_OPTION) {
	                        GestionEvento.getInstance().getMisRecursos().remove(recursoSeleccionado);
	                        btnEliminar.setEnabled(false);
	                        btnModificar.setEnabled(false);
	                        loadRecursos();
	                        
	                        GestionEvento.getInstance().guardarDatos("gestionEvento.dat");
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

	        loadRecursos();
	    }
	    public static void loadRecursos() {
	        modelo.setRowCount(0); 
	        row = new Object[table.getColumnCount()]; 
	        for (Recurso recurso : GestionEvento.getInstance().getMisRecursos()) {
	                row[0] = recurso.getIdRecurso();
	                row[1] = recurso.getEstado();
	                row[2] = recurso.getTipo();
	                row[3] = recurso.getCantidadDisponible();
	                row[4] = recurso.getDescripcion();
	                modelo.addRow(row);
	            }
	        }
	    }
