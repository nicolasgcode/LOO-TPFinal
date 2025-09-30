package sistema_biblioteca;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class Bibliotecario extends Usuario {

	public Bibliotecario(String nombre, String apellido, String mail, String psw, String estado) {
		super(nombre, apellido, mail, psw, estado);

	}

	public boolean cargarLibro(String ISBN, String titulo, String autor, String edicion, VentanaLibros ventanaLibros) {

		boolean valid = Validator.validateLibroInput(ISBN, titulo, autor, edicion, ventanaLibros);

		boolean exito = false;

		if (valid) {
			boolean exists = RepositorioLibros.checkExisteLibro(ISBN, titulo, autor, edicion, ventanaLibros);

			if (!exists) {

				if (RepositorioLibros.getLibros().size() < 20) {

					Libro nuevoLibro = new Libro(ISBN, titulo, autor, edicion, "Disponible");

					try {

						RepositorioLibros.getLibros().add(nuevoLibro);
						exito = true;
						JOptionPane.showMessageDialog(ventanaLibros, "Libro cargado con éxito");

					} catch (Exception e) {
						JOptionPane.showMessageDialog(ventanaLibros, "Error al cargar el libro" + e.getMessage());
					}

				} else {
					JOptionPane.showMessageDialog(ventanaLibros, "Se ha alcanzado la cantidad máxima de libros");
				}
			}
		}

		return exito;

	}

	public boolean gestionarLibro(String ISBN, EstadoLibro estado, JTable table) {

		boolean success = false;

		Libro libro = RepositorioLibros.getLibroByISBN(ISBN);

		System.out.println(ISBN);

		if (libro != null) {

			libro.setEstado(estado);
			JOptionPane.showMessageDialog(null, "Operación realizada con éxito");
			success = true;

		} else {

			JOptionPane.showMessageDialog(null, "Libro no encontrado");

		}

		return success;

	}

//	public void gestionarUsuario() {
//
//	}

}
