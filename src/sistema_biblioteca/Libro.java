package sistema_biblioteca;

public class Libro {

	private String ISBN;
	private String titulo;
	private String autor;
	private String edicion;
	private String estado;

	public Libro(String iSBN, String titulo, String autor, String edicion, String estado) {
		super();
		ISBN = iSBN;
		this.titulo = titulo;
		this.autor = autor;
		this.edicion = edicion;
		this.estado = estado;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEdicion() {
		return edicion;
	}

	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
