package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal frame = new Principal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Principal() {
        setTitle("Sistema de Gestión de Eventos Científicos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 782, 499);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        
        JMenu menuParticipantes = new JMenu("Participantes");
        menuBar.add(menuParticipantes);

        JMenuItem itemRegistrarParticipante = new JMenuItem("Registrar");
        itemRegistrarParticipante.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegistrarParticipante registrar = new RegistrarParticipante(null); 
                registrar.setModal(true);
                registrar.setLocationRelativeTo(null);
                registrar.setVisible(true);
            }
        });
        menuParticipantes.add(itemRegistrarParticipante);

        JMenuItem itemListadoParticipantes = new JMenuItem("Listado");
        itemListadoParticipantes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                ListarParticipantes listar = new ListarParticipantes();
                listar.setModal(true);
                listar.setLocationRelativeTo(null);
                listar.setVisible(true);
            }
        });
        menuParticipantes.add(itemListadoParticipantes);

        
        JMenu menuTrabajos = new JMenu("Trabajos Científicos");
        menuBar.add(menuTrabajos);

        JMenuItem itemRegistrarTrabajo = new JMenuItem("Registrar");
        itemRegistrarTrabajo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegistrarTrabajo registrar = new RegistrarTrabajo(null); 
                registrar.setModal(true);
                registrar.setLocationRelativeTo(null);
                registrar.setVisible(true);
            }
        });
        menuTrabajos.add(itemRegistrarTrabajo);

        JMenuItem itemListadoTrabajos = new JMenuItem("Listado");
        itemListadoTrabajos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                ListarTrabajos listar = new ListarTrabajos();
                listar.setModal(true);
                listar.setLocationRelativeTo(null);
                listar.setVisible(true);
            }
        });
        menuTrabajos.add(itemListadoTrabajos);

        
        JMenu menuJurados = new JMenu("Jurados");
        menuBar.add(menuJurados);

        JMenuItem itemRegistrarJurado = new JMenuItem("Registrar");
        itemRegistrarJurado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegistrarJurado registrar = new RegistrarJurado(null); 
                registrar.setModal(true);
                registrar.setLocationRelativeTo(null);
                registrar.setVisible(true);
            }
        });
        menuJurados.add(itemRegistrarJurado);


        JMenuItem itemListadoJurados = new JMenuItem("Listado");
        itemListadoJurados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                ListarJurados listar = new ListarJurados();
                listar.setModal(true);
                listar.setLocationRelativeTo(null);
                listar.setVisible(true);
            }
        });
        menuJurados.add(itemListadoJurados);

       
        JMenu menuComisiones = new JMenu("Comisiones");
        menuBar.add(menuComisiones);

        JMenuItem itemRegistrarComision = new JMenuItem("Registrar");
        itemRegistrarComision.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegistrarComision registrar = new RegistrarComision(null); 
                registrar.setModal(true);
                registrar.setLocationRelativeTo(null);
                registrar.setVisible(true);
            }
        });
        menuComisiones.add(itemRegistrarComision);

        JMenuItem itemListadoComisiones = new JMenuItem("Listado");
        itemListadoComisiones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
                ListarComisiones listar = new ListarComisiones();
                listar.setModal(true);
                listar.setLocationRelativeTo(null);
                listar.setVisible(true);
            }
        });
        menuComisiones.add(itemListadoComisiones);

        
        JMenu menuRecursos = new JMenu("Recursos");
        menuBar.add(menuRecursos);

        JMenuItem itemGestionarRecursos = new JMenuItem("Gestionar");
        itemGestionarRecursos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                GestionarRecursos gestionar = new GestionarRecursos();
                gestionar.setModal(true);
                gestionar.setLocationRelativeTo(null);
                gestionar.setVisible(true);
            }
        });
        menuRecursos.add(itemGestionarRecursos);

        
        JMenu menuPlanificacion = new JMenu("Planificación");
        menuBar.add(menuPlanificacion);

        JMenuItem itemPlanificarEvento = new JMenuItem("Planificar Evento");
        itemPlanificarEvento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                PlanificarEvento planificar = new PlanificarEvento();
                planificar.setModal(true);
                planificar.setLocationRelativeTo(null);
                planificar.setVisible(true);
            }
        });
        menuPlanificacion.add(itemPlanificarEvento);

      
        JMenu menuReportes = new JMenu("Reportes y Consultas");
        menuBar.add(menuReportes);

        JMenuItem itemGenerarReporte = new JMenuItem("Generar Reporte");
        itemGenerarReporte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              
                GenerarReporte generar = new GenerarReporte();
                generar.setModal(true);
                generar.setLocationRelativeTo(null);
                generar.setVisible(true);
            }
        });
        menuReportes.add(itemGenerarReporte);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
    }
}
