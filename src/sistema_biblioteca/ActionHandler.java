package sistema_biblioteca;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class ActionHandler {

	public void Salir(JFrame ventana, Usuario usuario) {
		VentanaUsuario ventanaUsuario = new VentanaUsuario(usuario, null);
		ventana.setVisible(false);
		ventanaUsuario.setVisible(true);

	}

	public void CargarLibro(Usuario usuario, String ISBN, String titulo, String autor, String edicion,
			VentanaLibros ventanaLibros) {
		if (usuario instanceof Bibliotecario bibliotecario) {

			boolean exito = bibliotecario.cargarLibro(ISBN, titulo, autor, edicion, ventanaLibros);

			if (exito) {

				ventanaLibros.limpiarCarga();

			}

		} else {
			JOptionPane.showMessageDialog(ventanaLibros, "No tienes permiso para cargar un libro");
		}
	}

	public void RealizarPrestamo(Usuario usuario, String ISBNPrestamo, JTable table, VentanaLibros ventanaLibros) {
		if (usuario instanceof UsuarioBasico user) {

			boolean exito = user.realizarPrestamo(ISBNPrestamo, usuario, ventanaLibros);

			if (exito) {

				table.setModel(new LibroTableModel(user.consultarLibrosDisp()));

			}

		}

	}

	public void ModificarLibro(Usuario usuario, String ISBN, EstadoLibro estado, JTable table,
			VentanaLibros ventanaLibro) {
		if (usuario instanceof Bibliotecario bibliotecario) {

			if (ISBN != "") {

				boolean success = bibliotecario.gestionarLibro(ISBN, estado, table);

				if (success) {

					((LibroTableModel) table.getModel()).fireTableDataChanged();

				} else {
					JOptionPane.showMessageDialog(ventanaLibro, "Error al cambiar estado del libro");
				}

			} else {
				JOptionPane.showMessageDialog(ventanaLibro, "Por favor seleccione un libro");
			}

		}
	}

	public void Login(String mail, char[] psw, VentanaLogin ventanaLogin) {

		Usuario usuario = RepositorioUsuarios.getUser(mail, psw, ventanaLogin);

		if (usuario != null) {
			VentanaUsuario ventanaUsuario = new VentanaUsuario(usuario, ventanaLogin);

			ventanaLogin.setVisible(false);

			ventanaUsuario.setVisible(true);

		}
	}

	public void CreateVentanaLogin(JFrame ventana) {

		ventana.setVisible(false);
		VentanaLogin ventanaLogin = new VentanaLogin();
		ventanaLogin.setVisible(true);
	}

	public void CreateVentanaLibros(JFrame ventana, Usuario usuario, String command) {

		VentanaLibros ventanaLibros = new VentanaLibros(usuario, command, ventana);
		ventana.setVisible(false);
		ventanaLibros.setVisible(true);
	}

	public void CreateVentanaPrestamos(JFrame ventana, Usuario usuario) {

		VentanaPrestamos ventanaPrestamos = new VentanaPrestamos(usuario, ventana);
		ventana.setVisible(false);
		ventanaPrestamos.setVisible(true);
	}

	public String getSelectedISBN(JTable table) {

		String selectedISBN = "";

		try {

			selectedISBN = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();

		} catch (IndexOutOfBoundsException e) {

			JOptionPane.showMessageDialog(null, "No ha seleccionado ningún libro");

		}

		return selectedISBN;

	}

}
