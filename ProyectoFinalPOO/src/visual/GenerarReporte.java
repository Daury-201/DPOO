package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.GestionEvento;
import logico.Evento;
import logico.Comision;
import logico.Recurso;
import logico.TrabajoCientifico;

public class GenerarReporte extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable tableReportes;
    private DefaultTableModel modelo;
    private JComboBox<String> comboReportes;

    public GenerarReporte() {
        setTitle("Generar Reporte");
        setBounds(100, 100, 600, 400);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        JPanel panelSuperior = new JPanel();
        contentPanel.add(panelSuperior, BorderLayout.NORTH);

        comboReportes = new JComboBox<>(new String[] { "Eventos", "Comisiones", "Recursos", "Trabajos Científicos" });
        panelSuperior.add(comboReportes);

        JButton btnCargar = new JButton("Cargar Reporte");
        btnCargar.addActionListener(e -> cargarReporte());
        panelSuperior.add(btnCargar);

        JScrollPane scrollPane = new JScrollPane();
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        tableReportes = new JTable();
        modelo = new DefaultTableModel();
        tableReportes.setModel(modelo);
        scrollPane.setViewportView(tableReportes);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        buttonPane.add(btnCancelar);
    }

    private void cargarReporte() {
        String seleccion = (String) comboReportes.getSelectedItem();
        modelo.setRowCount(0);

        switch (seleccion) {
            case "Eventos":
                cargarEventos();
                break;
            case "Comisiones":
                cargarComisiones();
                break;
            case "Recursos":
                cargarRecursos();
                break;
            case "Trabajos Científicos":
                cargarTrabajosCientificos();
                break;
            default:
                break;
        }
    }

    private void cargarEventos() {
        modelo.setColumnIdentifiers(new String[] { "ID", "Nombre", "Tipo", "Fecha Inicio", "Fecha Fin" });
        for (Evento evento : GestionEvento.getInstance().getMisEventos()) {
            modelo.addRow(new Object[] { evento.getIdEvento(), evento.getNombre(), evento.getTipo(),
                    evento.getFechaInicio(), evento.getFechaFin() });
        }
    }

    private void cargarComisiones() {
        modelo.setColumnIdentifiers(new String[] { "ID", "Nombre", "Área", "Jurados", "Trabajos" });
        for (Comision comision : GestionEvento.getInstance().getMisComisiones()) {
            modelo.addRow(new Object[] { comision.getIdComision(), comision.getNombreComision(), comision.getArea(),
                    comision.getJuradoComision().size(), comision.getTrabajosEvaluar().size() });
        }
    }

    private void cargarRecursos() {
        modelo.setColumnIdentifiers(new String[] { "ID", "Descripción", "Tipo", "Cantidad Disponible", "Estado" });
        for (Recurso recurso : GestionEvento.getInstance().getMisRecursos()) {
            modelo.addRow(new Object[] { recurso.getIdRecurso(), recurso.getDescripcion(), recurso.getTipo(),
                    recurso.getCantidadDisponible(), recurso.getEstado() });
        }
    }

    private void cargarTrabajosCientificos() {
        modelo.setColumnIdentifiers(new String[] { "ID", "Nombre", "Área", "Autor", "Estado" });
        for (TrabajoCientifico trabajo : GestionEvento.getInstance().getMisTrabajos()) {
            modelo.addRow(new Object[] { trabajo.getId(), trabajo.getNombre(), trabajo.getArea(),
                    trabajo.getAutor().getNombre(), trabajo.getEstado() });
        }
    }
}
