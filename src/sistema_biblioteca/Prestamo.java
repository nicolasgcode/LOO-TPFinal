package sistema_biblioteca;

public class Prestamo {

	Usuario usuario;
	Libro libro;
	String fechaInicio;
	int diasPrestamo;

	public Prestamo(Usuario usuario, Libro libro, String fechaInicio, int diasPrestamo) {
		this.usuario = usuario;
		this.libro = libro;
		this.fechaInicio = fechaInicio;
		this.diasPrestamo = diasPrestamo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public int getDiasPrestamo() {
		return diasPrestamo;
	}

	public void setDiasPrestamo(int diasPrestamo) {
		this.diasPrestamo = diasPrestamo;
	}

}
