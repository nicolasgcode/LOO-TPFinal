package sistema_biblioteca;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Bibliotecario extends Usuario {

	public Bibliotecario(String nombre, String apellido, String mail, String psw, String estado) {
		super(nombre, apellido, mail, psw, estado);

	}

	public boolean cargarLibro(String ISBN, String titulo, String autor, String edicion, JFrame ventana) {

		boolean valid = Validator.validateLibroInput(ISBN, titulo, autor, edicion, ventana);

		boolean exito = false;

		if (valid) {
			boolean exists = RepositorioLibros.checkExisteLibro(ISBN, titulo, autor, edicion, ventana);

			if (!exists) {

				if (RepositorioLibros.getLibros().size() < 20) {

					Libro nuevoLibro = new Libro(ISBN, titulo, autor, edicion, "Disponible");

					try {

						RepositorioLibros.getLibros().add(nuevoLibro);
						exito = true;
						JOptionPane.showMessageDialog(ventana, "Libro cargado con éxito", "Operación",
								JOptionPane.INFORMATION_MESSAGE);

					} catch (Exception e) {
						JOptionPane.showMessageDialog(ventana, "Error al cargar el libro", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(ventana, "Se ha alcanzado la cantidad máxima de libros", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}

		return exito;

	}

	public boolean gestionarLibro(String ISBN, EstadoLibro estado, JFrame ventana) {

		boolean exito = false;

		Libro libro = RepositorioLibros.getLibroByISBN(ISBN);

		if (libro != null) {
			if (!libro.getEstado().equals(EstadoLibro.PRESTADO)) {
				if (!libro.getEstado().equals(estado)) {
					libro.setEstado(estado);
					JOptionPane.showMessageDialog(ventana, "Operación realizada con éxito", "Operación",
							JOptionPane.INFORMATION_MESSAGE);
					exito = true;

				} else {
					JOptionPane.showMessageDialog(ventana, "El libro ya está en ese estado", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			} else {
				JOptionPane.showMessageDialog(ventana, "El libro se encuentra prestado", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		} else {

			JOptionPane.showMessageDialog(ventana, "Libro no encontrado", "Error", JOptionPane.ERROR_MESSAGE);

		}

		return exito;

	}

	public boolean finalizarPrestamo(String mail, String estado, JFrame ventana) {

		boolean exito = false;

		if (mail != "") {

			Usuario usuario = RepositorioUsuarios.getUserByMail(mail);

			if (usuario != null) {

				Prestamo prestamo = RepositorioPrestamos.getPrestamoDelUsuario(usuario);

				if (prestamo != null) {

					Libro libro = prestamo.getLibro();
					prestamo.setEstado(estado);
					libro.setEstado(EstadoLibro.DISPONIBLE);

					JOptionPane.showMessageDialog(ventana, "Prestamo finalizado con éxito", "Operación",
							JOptionPane.INFORMATION_MESSAGE);

					exito = true;

				} else {
					JOptionPane.showMessageDialog(ventana,
							"No se ha encontrado un prestamo en curso para dicho usuario", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			} else {
				JOptionPane.showMessageDialog(ventana, "Usuario con mail: " + mail + "no encontrado", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		}

		return exito;

	}

	public boolean gestionarUsuario(String mail, JFrame ventana) {

		Usuario usuario = RepositorioUsuarios.getUserByMail(mail);
		boolean exito = false;

		if (usuario != null) {

			if (usuario.getEstado().equalsIgnoreCase("Activo")) {

				exito = true;

				usuario.setEstado("Desactivado");
				JOptionPane.showMessageDialog(ventana, "Usuario desactivado con éxito", "Operación",
						JOptionPane.INFORMATION_MESSAGE);

			} else if (usuario.getEstado().equalsIgnoreCase("Desactivado")) {
				exito = true;
				usuario.setEstado("Activo");
				JOptionPane.showMessageDialog(ventana, "Usuario activado con éxito", "Operación",
						JOptionPane.INFORMATION_MESSAGE);

			}

		} else {
			JOptionPane.showMessageDialog(ventana, "Usuario no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
		}

		return exito;

	}

}
