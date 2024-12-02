package logico;

import java.io.Serializable;

public class Usuario implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String nombre;
    private String contrase�a;
    private String rol;

    
    public Usuario(String nombre, String contrase�a, String rol) {
        this.nombre = nombre;
        this.contrase�a = contrase�a;
        this.rol = rol;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrase�a() {
        return contrase�a;
    }

    public void setContrase�a(String contrase�a) {
        this.contrase�a = contrase�a;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }


}
