package sistema_biblioteca;

import javax.swing.JOptionPane;

public class Bibliotecario extends Usuario {

	public Bibliotecario(String nombre, String apellido, String mail, String psw, String estado) {
		super(nombre, apellido, mail, psw, estado);

	}

	public boolean cargarLibro(String ISBN, String titulo, String autor, String edicion, VentanaLibros ventanaLibros) {

		boolean exists = Validator.checkExiste(ISBN, titulo, autor, edicion, ventanaLibros);
		boolean exito = false;

		if (!exists) {

			Libro nuevoLibro = new Libro(ISBN, titulo, autor, edicion, "Disponible");

			try {

				RepositorioLibros.getLibros().add(nuevoLibro);
				exito = true;
				JOptionPane.showMessageDialog(ventanaLibros, "Libro cargado con éxito");

			} catch (Exception e) {
				JOptionPane.showMessageDialog(ventanaLibros, "Error al cargar el libro" + e.getMessage());
			}

		}

		return exito;

	}

	public void gestionarLibro() {

	}

//	public void gestionarUsuario() {
//
//	}

}
