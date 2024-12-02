package logico;

import java.io.Serializable;

public class Usuario implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String nombre;
    private String contraseña;
    private String rol;

    
    public Usuario(String nombre, String contraseña, String rol) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }


}
