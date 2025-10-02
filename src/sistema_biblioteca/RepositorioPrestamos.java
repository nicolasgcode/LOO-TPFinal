package sistema_biblioteca;

import java.util.ArrayList;
import java.util.List;

public class RepositorioPrestamos {

	private static List<Prestamo> prestamos = new ArrayList<>();

	public static List<Prestamo> getPrestamos() {
		return prestamos;
	}

	public static List<Prestamo> getPrestamosEnCurso() {

		List<Prestamo> prestamos = getPrestamos();

		List<Prestamo> prestamosEnCurso = new ArrayList<Prestamo>();

		for (Prestamo prestamo : prestamos) {

			if (prestamo.getEstado().equals("En curso")) {

				prestamosEnCurso.add(prestamo);

			}

		}

		return prestamosEnCurso;
	}

	public static Prestamo getPrestamoDelUsuario(Usuario usuario) {

		for (Prestamo prestamo : prestamos) {

			if (prestamo.getUsuario().equals(usuario) && prestamo.getEstado().equals("En curso")) {

				return prestamo;

			}

		}
		return null;
	}
}
