package logico;

import java.io.Serializable;

public class Recurso implements Serializable {

    private static final long serialVersionUID = 1L;
    private String idRecurso;
    private String estado;
    private String tipo;
    private int cantidadDisponible;
    private String descripcion;

    public Recurso(String idRecurso, String estado, String tipo, int cantidadDisponible, String descripcion) {
        super();
        this.idRecurso = idRecurso;
        this.estado = estado;
        this.tipo = tipo;
        this.cantidadDisponible = cantidadDisponible;
        this.descripcion = descripcion;
    }

    public String getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(String idRecurso) {
        this.idRecurso = idRecurso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        
        return idRecurso + " - " + descripcion + " (" + cantidadDisponible + " disponibles)";
    }
}
