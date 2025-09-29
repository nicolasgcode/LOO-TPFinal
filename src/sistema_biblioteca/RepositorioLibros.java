package sistema_biblioteca;

import java.util.ArrayList;
import java.util.List;

public class RepositorioLibros {

	private static List<Libro> libros = new ArrayList<>();

	static {

		libros.add(new Libro("0000000000001", "El Quijote", "Miguel de Cervantes", "1", "Disponible"));
		libros.add(new Libro("0000000000002", "Cien Años de Soledad", "Gabriel García Márquez", "1", "Prestado"));
		libros.add(new Libro("0000000000003", "Don Juan Tenorio", "José Zorrilla", "2", "Reservado"));
		libros.add(new Libro("0000000000004", "La Sombra del Viento", "Carlos Ruiz Zafón", "1", "Disponible"));
		libros.add(new Libro("0000000000005", "El Principito", "Antoine de Saint-Exupéry", "3", "No disponible"));
		libros.add(new Libro("0000000000006", "1984", "George Orwell", "1", "Disponible"));
		libros.add(new Libro("0000000000007", "Crimen y Castigo", "Fiódor Dostoyevski", "2", "Prestado"));
		libros.add(new Libro("0000000000008", "Hamlet", "William Shakespeare", "1", "Disponible"));
		libros.add(new Libro("0000000000009", "Orgullo y Prejuicio", "Jane Austen", "1", "Reservado"));
		libros.add(new Libro("0000000000010", "Moby Dick", "Herman Melville", "1", "Disponible"));

	}

	public static List<Libro> getLibros() {
		return libros;
	}

	public static Libro getLibroByISBN(String ISBN) {
		for (Libro libro : libros) {

			if (libro.getISBN().equals(ISBN)) {

				return libro;

			}

		}
		return null;
	}

}
