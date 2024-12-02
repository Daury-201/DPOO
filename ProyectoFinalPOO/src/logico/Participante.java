package logico;

import java.util.ArrayList;

public class Participante extends Persona {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
    private String rol;
    private ArrayList<TrabajoCientifico> trabajos;


    public Participante(String id, String nombre, String apellido, String telefono, String direccion, String email, String rol) {
        super(id, nombre, apellido, telefono, direccion);
        this.email = email;
        this.rol = rol;
        this.trabajos = new ArrayList<>(); 
    }

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public ArrayList<TrabajoCientifico> getTrabajos() {
        return trabajos;
    }

    public void setTrabajos(ArrayList<TrabajoCientifico> trabajos) {
        this.trabajos = trabajos;
    }

    public void agregarTrabajo(TrabajoCientifico trabajo) {
        if (trabajos == null) {
            trabajos = new ArrayList<>();
        }
        if (!trabajos.contains(trabajo)) {
            trabajos.add(trabajo);
        }
    }
    @Override
    public String toString() {
        return getNombre() + " " + getApellido(); 
    }
}
