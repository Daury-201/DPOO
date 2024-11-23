package logico;

import java.util.ArrayList;

public  class Comision {

	private String idComision;
	private String nombreComision;
	private String area;
	private ArrayList<Jurado> juradoComision;
	private ArrayList<TrabajoCientifico> trabajosEvaluar;
	
	public Comision(String idComision, String nombreComision, String area) {
		super();
		this.idComision = idComision;
		this.nombreComision = nombreComision;
		this.area = area;
	}
	
	public String getIdComision() {
		return idComision;
	}
	public void setIdComision(String idComision) {
		this.idComision = idComision;
	}
	public String getNombreComision() {
		return nombreComision;
	}
	public void setNombreComision(String nombreComision) {
		this.nombreComision = nombreComision;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	public ArrayList<Jurado> getJuradoComision() {
		return juradoComision;
	}
	public void setJuradoComision(ArrayList<Jurado> juradoComision) {
		this.juradoComision = juradoComision;
	}
	
	public ArrayList<TrabajoCientifico> getTrabajosEvaluar() {
		return trabajosEvaluar;
	}
	public void setTrabajosEvaluar(ArrayList<TrabajoCientifico> trabajosEvaluar) {
		this.trabajosEvaluar = trabajosEvaluar;
	}
}
