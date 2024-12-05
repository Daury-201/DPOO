package logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;

    private String idEvento;
    private String nombre;
    private String tipo;
    private String tipoEvento;
    private Date fechaInicio;
    private Date fechaFin;
    private ArrayList<TrabajoCientifico> trabajosEvento;
	private ArrayList<Comision>comisionesEvento;
    private ArrayList<Recurso> recursos; 

    public Evento(String idEvento, String nombre, String tipo, Date fechaInicio, Date fechaFin) {
        this.idEvento = idEvento;
        this.nombre = nombre;
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.trabajosEvento = new ArrayList<>();
		this.comisionesEvento = new ArrayList<>();
        this.recursos = new ArrayList<>(); 
    }

    

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getTipoEvento() {
		return tipoEvento;
	}



	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}


    public ArrayList<Recurso> getRecursos() {
        return recursos;
    }

    public void setRecursos(ArrayList<Recurso> recursos) {
        this.recursos = recursos;
    }

    public void agregarRecurso(Recurso recurso) {
        if (!recursos.contains(recurso)) {
            recursos.add(recurso);
        }
    }
    
    public void eliminarRecurso(Recurso recurso) {
        recursos.remove(recurso);
    }



	public ArrayList<TrabajoCientifico> getTrabajosEvento() {
		return trabajosEvento;
	}



	public void setTrabajosEvento(ArrayList<TrabajoCientifico> trabajosEvento) {
		this.trabajosEvento = trabajosEvento;
	}



	public ArrayList<Comision> getComisionesEvento() {
		return comisionesEvento;
	}



	public void setComisionesEvento(ArrayList<Comision> comisionesEvento) {
		this.comisionesEvento = comisionesEvento;
	}
}
