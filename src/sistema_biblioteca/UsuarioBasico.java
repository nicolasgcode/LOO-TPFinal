package sistema_biblioteca;

import java.util.ArrayList;
import java.util.List;

public class UsuarioBasico extends Usuario {

	public UsuarioBasico(String nombre, String apellido, String mail, String psw, String estado) {
		super(nombre, apellido, mail, psw, estado);

	}

	public List<Libro> consultarLibrosDisp() {

		List<Libro> repositorioLibros = RepositorioLibros.getLibros();

		List<Libro> librosDisponibles = new ArrayList<Libro>();

		for (Libro libro : repositorioLibros) {

			if (libro.getEstado().equalsIgnoreCase("disponible")) {

				librosDisponibles.add(libro);

			}

		}

		return librosDisponibles;

	}

	public void realizarPrestamo(String ISBN) {

		Libro miLibro = RepositorioLibros.getLibroByISBN(ISBN);

	}

}
