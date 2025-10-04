package sistema_biblioteca;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
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

	public boolean realizarPrestamo(String ISBN, Usuario usuario, JFrame ventana) {

		boolean exito = false;

		Libro miLibro = RepositorioLibros.getLibroByISBN(ISBN);

		Prestamo prestamo = null;

		boolean tieneEnCurso = RepositorioPrestamos.tienePrestamoEnCurso(usuario);

		if (!tieneEnCurso) {

			if (miLibro != null) {

				if (miLibro.getEstado().equals(EstadoLibro.DISPONIBLE)) {

					prestamo = new Prestamo(usuario, miLibro);

					try {

						RepositorioPrestamos.getPrestamos().add(prestamo);

						usuario.getPrestamos().add(prestamo);

						miLibro.setEstado(EstadoLibro.PRESTADO);

						exito = true;

						JOptionPane.showMessageDialog(ventana, "Prestamo realizado con éxito", "Operación",
								JOptionPane.INFORMATION_MESSAGE);

					} catch (Exception e) {
						JOptionPane.showMessageDialog(ventana, "Error al realizar el prestamo", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				} else {

					JOptionPane.showMessageDialog(ventana, "El libro seleccionado no se encuentra disponible", "Error",
							JOptionPane.ERROR_MESSAGE);

				}
			} else {
				JOptionPane.showMessageDialog(ventana, "Libro no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
			}

		} else {
			JOptionPane.showMessageDialog(ventana, "Usted ya se encuentra con un prestamo en curso", "Error",
					JOptionPane.ERROR_MESSAGE);

		}

		return exito;

	}

}
