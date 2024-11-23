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
}
