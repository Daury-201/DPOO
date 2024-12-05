package logico;


import java.io.*;
import java.util.ArrayList;
import java.io.Serializable;

public class GestionEvento implements Serializable {

    private static final long serialVersionUID = 1L;

    private ArrayList<Persona> misPersonas;
    private ArrayList<Evento> misEventos;
    private ArrayList<Comision> misComisiones;
    private ArrayList<TrabajoCientifico> misTrabajos;
    private ArrayList<Recurso> misRecursos;
    private ArrayList<Usuario> usuarios; 

    public static int codEvento = 1;
    public static int codComision = 1;

    private static GestionEvento gestion = null;

    
    private GestionEvento() {
        misPersonas = new ArrayList<>();
        misEventos = new ArrayList<>();
        misComisiones = new ArrayList<>();
        misTrabajos = new ArrayList<>();
        usuarios = new ArrayList<>();
        misRecursos = new ArrayList<>();
        cargarUsuariosPorDefecto();
    }

   
    public static GestionEvento getInstance() {
        if (gestion == null) {
            gestion = new GestionEvento();
        }
        return gestion;
    }

    
    public void guardarDatos(String archivo) {
        try (FileOutputStream fos = new FileOutputStream(archivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this); 
            System.out.println("Datos guardados correctamente en " + archivo);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    
    public static void cargarDatos(String archivo) {
        try (FileInputStream fis = new FileInputStream(archivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gestion = (GestionEvento) ois.readObject(); 
            System.out.println("Datos cargados correctamente desde " + archivo);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Error al cargar los datos: " + e.getMessage());
        }
    }

    
    public ArrayList<Persona> getMisPersonas() {
        return misPersonas;
    }

    public void setMisPersonas(ArrayList<Persona> misPersonas) {
        this.misPersonas = misPersonas;
    }

    public ArrayList<Evento> getMisEventos() {
        return misEventos;
    }

    public void setMisEventos(ArrayList<Evento> misEventos) {
        this.misEventos = misEventos;
    }

    public ArrayList<Comision> getMisComisiones() {
        return misComisiones;
    }

    public void setMisComisiones(ArrayList<Comision> misComisiones) {
        this.misComisiones = misComisiones;
    }

    public ArrayList<TrabajoCientifico> getMisTrabajos() {
        return misTrabajos;
    }

    public void setMisTrabajos(ArrayList<TrabajoCientifico> misTrabajos) {
        this.misTrabajos = misTrabajos;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
    
    public ArrayList<Recurso> getMisRecursos() {
        return misRecursos;
    }


	public void setMisRecursos(ArrayList<Recurso> misRecursos) {
		this.misRecursos = misRecursos;
	}

    public void agregarUsuario(Usuario usuario) {
        if (buscarUsuarioPorNombre(usuario.getNombre()) == null) {
            usuarios.add(usuario);
        }
    }

    public Usuario buscarUsuarioPorNombre(String nombre) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equalsIgnoreCase(nombre)) {
                return usuario;
            }
        }
        return null;
    }

    private void cargarUsuariosPorDefecto() {
        usuarios.add(new Usuario("admin", "1234", "Administrador"));
        usuarios.add(new Usuario("autor", "password", "Autor"));
        usuarios.add(new Usuario("lector", "1234", "Lector"));
    }

   
    public Comision buscarComisionPorId(String id) {
        for (Comision comision : misComisiones) {
            if (comision.getIdComision().equalsIgnoreCase(id)) {
                return comision;
            }
        }
        return null;
    }

    public Jurado buscarJuradoPorId(String id) {
        for (Persona persona : misPersonas) {
            if (persona instanceof Jurado && persona.getId().equalsIgnoreCase(id)) {
                return (Jurado) persona;
            }
        }
        return null;
    }

    public Participante buscarParticipantePorId(String id) {
        for (Persona persona : misPersonas) {
            if (persona instanceof Participante && persona.getId().equalsIgnoreCase(id)) {
                return (Participante) persona;
            }
        }
        return null;
    }

    public TrabajoCientifico buscarTrabajoPorId(String id) {
        for (TrabajoCientifico trabajo : misTrabajos) {
            if (trabajo.getId().equalsIgnoreCase(id)) {
                return trabajo;
            }
        }
        return null;
    }

    
    public void agregarPersona(Persona persona) {
        if (!misPersonas.contains(persona)) {
            misPersonas.add(persona);
        }
    }

    public void agregarComision(Comision comision) {
        if (!misComisiones.contains(comision)) {
            misComisiones.add(comision);
        }
    }

    public void eliminarComision(Comision comision) {
        misComisiones.remove(comision);
    }

    public ArrayList<Jurado> getJurados() {
        ArrayList<Jurado> jurados = new ArrayList<>();
        for (Persona persona : misPersonas) {
            if (persona instanceof Jurado) {
                jurados.add((Jurado) persona);
            }
        }
        return jurados;
    }

    public ArrayList<Participante> getParticipantes() {
        ArrayList<Participante> participantes = new ArrayList<>();
        for (Persona persona : misPersonas) {
            if (persona instanceof Participante) {
                participantes.add((Participante) persona);
            }
        }
        return participantes;
    }

    public void eliminarJurado(Jurado jurado) {
        misPersonas.remove(jurado);
    }

    public void agregarTrabajo(TrabajoCientifico trabajo) {
        if (!misTrabajos.contains(trabajo)) {
            misTrabajos.add(trabajo);
        }
    }

    public void eliminarTrabajo(TrabajoCientifico trabajo) {
        misTrabajos.remove(trabajo);
    }
    
    public Evento buscarEventoPorId(String id) {
        for (Evento evento : misEventos) {
            if (evento.getIdEvento().equalsIgnoreCase(id)) {
                return evento;
            }
        }
        return null;
    }
    
    public void agregarEvento(Evento evento) {
        if (!misEventos.contains(evento)) {
            misEventos.add(evento);
        }
    }
    
    public Recurso buscarRecursoPorId(String idRecurso) {
        for (Recurso recurso : getMisRecursos()) {
            if (recurso.getIdRecurso().equalsIgnoreCase(idRecurso)) {
                return recurso;
            }
        }
        return null;
    }
    
    public void agregarRecurso(Recurso recurso) {
        if (!misRecursos.contains(recurso)) {
            misRecursos.add(recurso);
        }
    }
    

    
    public void insertarEvento(Evento s1) {
        misEventos.add(s1);
       codEvento++;
    }

  
}


