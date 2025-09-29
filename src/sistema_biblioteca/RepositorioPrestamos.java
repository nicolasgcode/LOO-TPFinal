package sistema_biblioteca;

import java.util.ArrayList;
import java.util.List;

public class RepositorioPrestamos {

	private static List<Prestamo> prestamos = new ArrayList<>();

	public static List<Prestamo> getPrestamos() {
		return prestamos;
	}

}
