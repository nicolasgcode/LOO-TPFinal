package sistema_biblioteca;

public enum EstadoPrestamo {

	EN_CURSO("En curso"), FINALIZADO("Finalizado");

	private final String descripcion;

	EstadoPrestamo(String descripcion) {
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
