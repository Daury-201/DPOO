package logico;

import java.util.ArrayList;

public class TrabajoCientifico {

    private String id;
    private String nombre;
    private String area;
    private Participante autor;
    private ArrayList<Participante> coautores; 
    private String estado; 

    public TrabajoCientifico(String id, String nombre, String area, Participante autor) {
        this.id = id;
        this.nombre = nombre;
        this.area = area;
        this.autor = autor;
        this.coautores = new ArrayList<>();
        this.estado = "En revisión"; 
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Participante getAutor() {
        return autor;
    }

    public void setAutor(Participante autor) {
        this.autor = autor;
    }

    public ArrayList<Participante> getCoautores() {
        return coautores;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    public void agregarCoautor(Participante coautor) {
        coautores.add(coautor);
    }

    public void eliminarCoautor(Participante coautor) {
        coautores.remove(coautor);
    }
}
