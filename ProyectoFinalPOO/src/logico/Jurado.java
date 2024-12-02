package logico;

import java.util.ArrayList;


public class Jurado extends Persona {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Comision> comisiones;

    public Jurado(String id, String nombre, String apellido, String telefono, String direccion) {
        super(id, nombre, apellido, telefono, direccion);
        this.comisiones = new ArrayList<>();
    }

    public ArrayList<Comision> getComisiones() {
        return comisiones;
    }

    public void agregarComision(Comision comision) {
        comisiones.add(comision);
    }

    public void removerComision(Comision comision) {
        comisiones.remove(comision);
    }
}
