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

	public boolean realizarPrestamo(String ISBN, Usuario usuario, VentanaLibros ventana) {

		boolean exito = false;

		Libro miLibro = RepositorioLibros.getLibroByISBN(ISBN);

		Prestamo prestamo = null;

		if (usuario.getPrestamos().isEmpty()) {

			if (miLibro != null) {

				if (miLibro.getEstado().equals(EstadoLibro.DISPONIBLE)) {

					prestamo = new Prestamo(usuario, miLibro);

					try {

						RepositorioPrestamos.getPrestamos().add(prestamo);

						usuario.getPrestamos().add(prestamo);

						miLibro.setEstado(EstadoLibro.PRESTADO);

						exito = true;

						JOptionPane.showMessageDialog(ventana, "Prestamo realizado con éxito");

					} catch (Exception e) {
						JOptionPane.showMessageDialog(ventana,
								"Ha ocurrido un error al realizar el prestamo" + e.getMessage());
					}

				} else {

					JOptionPane.showMessageDialog(ventana, "El libro seleccionado no se encuentra disponible");

				}
			} else {
				JOptionPane.showMessageDialog(ventana, "Libro no encontrado");
			}

		} else {
			JOptionPane.showMessageDialog(ventana, "Usted ya se encuentra con un prestamo en curso");

		}

		return exito;

	}

}
