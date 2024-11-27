package logico;

import java.util.ArrayList;

public class Comision {

    private String idComision;
    private String nombreComision;
    private String area;
    private ArrayList<Jurado> juradoComision;
    private ArrayList<TrabajoCientifico> trabajosEvaluar;

    public Comision(String idComision, String nombreComision, String area) {
        this.idComision = idComision;
        this.nombreComision = nombreComision;
        this.area = area;
        this.juradoComision = new ArrayList<>();
        this.trabajosEvaluar = new ArrayList<>();
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

    public void agregarJurado(Jurado jurado) {
        juradoComision.add(jurado);
    }

    public void removerJurado(Jurado jurado) {
        juradoComision.remove(jurado);
    }

    public void agregarTrabajo(TrabajoCientifico trabajo) {
        trabajosEvaluar.add(trabajo);
    }

    public void removerTrabajo(TrabajoCientifico trabajo) {
        trabajosEvaluar.remove(trabajo);
    }
}
