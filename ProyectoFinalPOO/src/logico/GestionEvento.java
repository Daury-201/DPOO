package logico;

import java.util.ArrayList;

public class GestionEvento {

	private ArrayList<Persona>misPersonas;
	private ArrayList<Evento>misEventos;
	private ArrayList<Comision>misComisiones;
	private ArrayList<TrabajoCientifico> misTrabajos;
	
	public static int codEvento = 1;
	public static int codComision = 1;
	
	private static GestionEvento gestion = null;
	
	private GestionEvento() {
		misPersonas = new ArrayList<>();
		misEventos = new ArrayList<>();
		misComisiones = new ArrayList<>();
		misTrabajos = new ArrayList<>();
	}
	
	public static GestionEvento getInstance() {
		if(gestion == null) {
			gestion = new GestionEvento();
		}
		return gestion;
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
public ArrayList<Jurado> getJurados() {
    ArrayList<Jurado> jurados = new ArrayList<>();
    for (Persona persona : misPersonas) {
        if (persona instanceof Jurado) {
            jurados.add((Jurado) persona);
        }
    }
    return jurados;
}
public void eliminarJurado(Jurado jurado) {
    misPersonas.remove(jurado);
}

}
