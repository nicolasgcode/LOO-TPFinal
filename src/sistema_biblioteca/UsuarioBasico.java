package sistema_biblioteca;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class UsuarioBasico extends Usuario {

	public UsuarioBasico(String nombre, String apellido, String mail, String psw, String estado) {
		super(nombre, apellido, mail, psw, estado);

	}

	public List<Libro> consultarLibrosDisp() {

		List<Libro> repositorioLibros = RepositorioLibros.getLibros();

		List<Libro> librosDisponibles = new ArrayList<Libro>();

		for (Libro libro : repositorioLibros) {

			if (libro.getEstado().equals(EstadoLibro.DISPONIBLE)) {

				librosDisponibles.add(libro);

			}

		}

		return librosDisponibles;

	}

	public void realizarPrestamo(String ISBN, Usuario usuario, VentanaLibros ventana) {

		Libro miLibro = RepositorioLibros.getLibroByISBN(ISBN);
		Prestamo prestamo = null;

		if (miLibro != null) {

			prestamo = new Prestamo(usuario, miLibro);

			try {

				RepositorioPrestamos.getPrestamos().add(prestamo);

				usuario.getPrestamos().add(prestamo);

				JOptionPane.showMessageDialog(ventana, "Prestamo realizado con éxito");

			} catch (Exception e) {
				JOptionPane.showMessageDialog(ventana, "Ha ocurrido un error al realizar el prestamo" + e.getMessage());
			}

		} else {
			JOptionPane.showMessageDialog(ventana, "Libro no encontrado");
		}

	}

}
