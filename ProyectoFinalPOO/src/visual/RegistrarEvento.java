package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Evento;
import logico.GestionEvento;
import logico.Participante;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class RegistrarEvento extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtFechaF;
	private JTextField txtFechaI;
	private static  Evento eventoSeleccionado;
	private JComboBox<Object> comboTipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarEvento dialog = new RegistrarEvento(eventoSeleccionado);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarEvento(Evento eventoSeleccionado) {
		RegistrarEvento.eventoSeleccionado = eventoSeleccionado;

        if (eventoSeleccionado == null) {
            setTitle("Registrar Evento");
        } else {
            setTitle("Modificar Evento");
        }
        
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JLabel idlabel = new JLabel("IdEvento:");
				idlabel.setBounds(10, 29, 65, 14);
				panel.add(idlabel);
			}
			{
				JLabel labeldateFin = new JLabel("Fecha de Fin:");
				labeldateFin.setBounds(233, 160, 85, 14);
				panel.add(labeldateFin);
			}
			{
				JLabel labeldateInicio = new JLabel("Fecha de Inicio:");
				labeldateInicio.setBounds(10, 160, 95, 14);
				panel.add(labeldateInicio);
			}
			{
				JLabel labeltipo = new JLabel("Tipo:");
				labeltipo.setBounds(233, 90, 65, 14);
				panel.add(labeltipo);
			}
			
			JLabel labelnombre = new JLabel("Nombre del Evento:");
			labelnombre.setBounds(10, 90, 125, 14);
			panel.add(labelnombre);
			
			txtId = new JTextField();
			txtId.setBounds(70, 25, 125, 23);
			panel.add(txtId);
			txtId.setColumns(10);
			
			txtNombre = new JTextField();
			txtNombre.setBounds(130, 86, 85, 23);
			panel.add(txtNombre);
			txtNombre.setColumns(10);
			
			txtFechaF = new JTextField();
			txtFechaF.setColumns(15);
			txtFechaF.setBounds(329, 156, 85, 23);
			panel.add(txtFechaF);
			
			txtFechaI = new JTextField();
			txtFechaI.setBounds(130, 156, 85, 23);
			panel.add(txtFechaI);
			txtFechaI.setColumns(15);
			{
				comboTipo = new JComboBox<Object>();
				comboTipo.setModel(new DefaultComboBoxModel<Object>(new String[] {"Academico", "Artistico", "Deportivo", "Open Day"}));
				comboTipo.setBounds(328, 86, 86, 23);
				panel.add(comboTipo);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Guardar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						guardarEvento();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
			private void cargarDatosEvento() {
				if(eventoSeleccionado != null) {
					txtId.setText(eventoSeleccionado.getIdEvento());
					txtNombre.setText(eventoSeleccionado.getNombre());
					comboTipo.setSelectedItem(eventoSeleccionado.getTipo());
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			        if (eventoSeleccionado.getFechaInicio() != null) {
			            txtFechaI.setText(sdf.format(eventoSeleccionado.getFechaInicio()));
			        }
			        if (eventoSeleccionado.getFechaFin() != null) {
			            txtFechaF.setText(sdf.format(eventoSeleccionado.getFechaFin()));
			        }
					
				}
		}
			
			private void guardarEvento() {
			    String id = txtId.getText().trim();
			    String nombre = txtNombre.getText().trim();
			    String tipo = comboTipo.getSelectedItem().toString();
			    String fechaInicioStr = txtFechaI.getText().trim();
			    String fechaFinStr = txtFechaF.getText().trim();


			    if (id.isEmpty() || nombre.isEmpty() || tipo.isEmpty() || fechaInicioStr.isEmpty() || fechaFinStr.isEmpty()) {
			        JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
			        return;
			    }

			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			    sdf.setLenient(false);
			    Date fechaInicio, fechaFin;

			    try {
			        fechaInicio = sdf.parse(fechaInicioStr);
			        fechaFin = sdf.parse(fechaFinStr);

			        if (fechaInicio.after(fechaFin)) {
			            JOptionPane.showMessageDialog(this, "La Fecha de Inicio no puede ser posterior a la Fecha de Fin.", "Error", JOptionPane.ERROR_MESSAGE);
			            return;
			        }
			    } catch (ParseException e) {
			        JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
			        return;
			    }

			    if (eventoSeleccionado == null) {
			       
			        if (GestionEvento.getInstance().buscarEventoPorId(id) != null) {
			            JOptionPane.showMessageDialog(this, "Ya existe un evento con este ID.", "Error", JOptionPane.ERROR_MESSAGE);
			            return;
			        }

			       
			        Evento nuevoEvento = new Evento(id, nombre, tipo, fechaInicio, fechaFin);
			        GestionEvento.getInstance().agregarEvento(nuevoEvento);
			        GestionEvento.getInstance().guardarDatos("gestionEvento.dat");
			        JOptionPane.showMessageDialog(this, "Evento registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			    } else {
			   
			        eventoSeleccionado.setNombre(nombre);
			        eventoSeleccionado.setTipo(tipo);
			        eventoSeleccionado.setFechaInicio(fechaInicio);
			        eventoSeleccionado.setFechaFin(fechaFin);
			        GestionEvento.getInstance().guardarDatos("gestionEvento.dat");
			        JOptionPane.showMessageDialog(this, "Evento modificado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			    }
                  
			  
			    dispose();
			}

			}


