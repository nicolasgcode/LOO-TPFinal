package sistema_biblioteca;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Prestamo {

	private Usuario usuario;
	private Libro libro;
	private String fechaInicio;
	private String fechaFin;

	public Prestamo(Usuario usuario, Libro libro) {
		this.usuario = usuario;
		this.libro = libro;
		this.fechaInicio = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.fechaFin = LocalDate.now().plusDays(7).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
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

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

}
