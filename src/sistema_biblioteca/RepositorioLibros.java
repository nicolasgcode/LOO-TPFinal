package sistema_biblioteca;

import java.util.ArrayList;
import java.util.List;

public class RepositorioLibros {

	private static List<Libro> libros = new ArrayList<>();

	static {

		libros.add(new Libro("0000000000001", "El Quijote", "Miguel de Cervantes", "1ª", "Disponible"));
		libros.add(new Libro("0000000000002", "Crimen y Castigo", "Fiódor Dostoyevski", "2ª", "Prestado"));
		libros.add(new Libro("1114458349022", "1984", "George Orwell", "3ª", "Disponible"));
		libros.add(new Libro("978-0-7432-7356-5", "El Código Da Vinci", "Dan Brown", "1ª", "Disponible"));
		libros.add(new Libro("978-0-06-112008-4", "Matar a un ruiseñor", "Harper Lee", "4ª", "Prestado"));
		libros.add(new Libro("978-0-307-27778-0", "Los Pilares de la Tierra", "Ken Follett", "2ª", "Disponible"));
		libros.add(new Libro("978-0-452-28425-8", "Fahrenheit 451", "Ray Bradbury", "3ª", "Disponible"));
		libros.add(new Libro("978-0-375-70368-2", "El Alquimista", "Paulo Coelho", "1ª", "Prestado"));
		libros.add(new Libro("978-0-7432-7357-2", "Ángeles y Demonios", "Dan Brown", "2ª", "Disponible"));
		libros.add(
				new Libro("978-84-376-0494-7", "Cien años de soledad", "Gabriel García Márquez", "5ª", "Disponible"));
		libros.add(new Libro("978-0-452-28425-8", "Fahrenheit 451", "Ray Bradbury", "3ª", "Disponible"));

	}

	public static List<Libro> getLibros() {
		return libros;
	}

}
