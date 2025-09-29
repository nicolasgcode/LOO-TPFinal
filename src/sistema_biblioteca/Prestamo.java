package sistema_biblioteca;

import java.time.LocalDate;

public class Prestamo {

	private Usuario usuario;
	private Libro libro;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;

	public Prestamo(Usuario usuario, Libro libro) {
		this.usuario = usuario;
		this.libro = libro;
		this.fechaInicio = LocalDate.now();
		this.fechaFin = LocalDate.now().plusDays(7);
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

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

}
