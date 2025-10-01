package sistema_biblioteca;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RepositorioLibros {

	private static List<Libro> libros = new ArrayList<>();

	static {

		libros.add(new Libro("0000000000001", "El Quijote", "Miguel de Cervantes", "1", "Disponible"));
		libros.add(new Libro("0000000000002", "Cien Años de Soledad", "Gabriel Garcia Marquez", "1", "Prestado"));
		libros.add(new Libro("0000000000003", "Don Juan Tenorio", "Jose Zorrilla", "2", "Reservado"));
		libros.add(new Libro("0000000000004", "La Sombra del Viento", "Carlos Ruiz Zafon", "1", "Disponible"));
		libros.add(new Libro("0000000000005", "El Principito", "Antoine de Saint Exupery", "3", "No disponible"));
		libros.add(new Libro("0000000000006", "1984", "George Orwell", "1", "Disponible"));
		libros.add(new Libro("0000000000007", "Crimen y Castigo", "Fiodor Dostoyevski", "2", "Prestado"));
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

	public static boolean checkExisteLibro(String ISBN, String titulo, String autor, String edicion, JFrame ventana) {

		boolean exists = false;

		for (Libro libro : libros) {

			if (libro.getISBN().equals(ISBN)) {
				JOptionPane.showMessageDialog(ventana, "El ISBN ingresado ya existe", "Error",
						JOptionPane.ERROR_MESSAGE);

				exists = true;

			} else if (libro.getTitulo().equalsIgnoreCase(titulo) && libro.getAutor().equalsIgnoreCase(autor)) {

				JOptionPane.showMessageDialog(ventana, "El título ingresado ya existe para el actor: " + autor, "Error",
						JOptionPane.ERROR_MESSAGE);

				exists = true;

			}

		}

		return exists;

	}

}
