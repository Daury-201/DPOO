package logico;

public class TrabajoCientifico {

	private String id;
	private String nombre;
	private String area;
	private Participante autor;
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
}
