package sistema_biblioteca;

public enum EstadoLibro {

	DISPONIBLE("Disponible"), PRESTADO("Prestado"), RESERVADO("Reservado"), NO_DISPONIBLE("No disponible");

	private final String descripcion;

	EstadoLibro(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public String toString() {
		return descripcion;
	}

}
