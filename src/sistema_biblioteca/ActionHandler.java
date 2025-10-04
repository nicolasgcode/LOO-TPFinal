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

	public void CargarLibro(Usuario usuario, String ISBN, String titulo, String autor, String edicion, JFrame ventana) {
		if (usuario instanceof Bibliotecario bibliotecario) {

			boolean exito = bibliotecario.cargarLibro(ISBN, titulo, autor, edicion, ventana);

			if (exito) {

				((VentanaLibros) ventana).limpiarCarga();

			}

		} else {
			JOptionPane.showMessageDialog(ventana, "No tienes permiso para cargar un libro", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void RealizarPrestamo(Usuario usuario, String ISBNPrestamo, JTable table, JFrame ventana) {
		if (usuario instanceof UsuarioBasico user) {

			boolean exito = user.realizarPrestamo(ISBNPrestamo, usuario, ventana);

			if (exito) {

				table.setModel(new LibroTableModel(user.consultarLibrosDisp()));

			}

		}

	}

	public boolean filtrarPrestamos(JTable table) {

		boolean exito = false;

		if (!RepositorioPrestamos.getPrestamosEnCurso().isEmpty()) {

			table.setModel(new PrestamoTableModel(RepositorioPrestamos.getPrestamosEnCurso()));
			exito = true;

		}

		return exito;

	}

	public void finalizarPrestamo(Usuario usuario, String mail, String estado, JTable table, JFrame ventana) {
		if (usuario instanceof Bibliotecario bibliotecario) {

			if (mail != "") {

				boolean success = bibliotecario.finalizarPrestamo(mail, estado, ventana);

				if (success) {

					filtrarPrestamos(table);

				}
			}

		}
	}

	public void ModificarLibro(Usuario usuario, String ISBN, EstadoLibro estado, JTable table, JFrame ventana) {
		if (usuario instanceof Bibliotecario bibliotecario) {

			if (ISBN != "") {

				boolean success = bibliotecario.gestionarLibro(ISBN, estado, ventana);

				if (success) {

					((LibroTableModel) table.getModel()).fireTableDataChanged();

				}
			}
		}

	}

	public void GestionarUsuario(Usuario usuario, String mail, JTable table, JFrame ventana) {

		if (usuario instanceof Bibliotecario bibliotecario) {

			if (mail != "") {

				boolean success = bibliotecario.gestionarUsuario(mail, ventana);

				if (success) {

					((UserTableModel) table.getModel()).fireTableDataChanged();

				}
			}
		}
	}

	public void Login(String mail, char[] psw, JFrame ventana) {

		Usuario usuario = RepositorioUsuarios.getUser(mail, psw, ventana);

		if (usuario != null) {
			VentanaUsuario ventanaUsuario = new VentanaUsuario(usuario, ventana);

			ventana.setVisible(false);

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

	public void CreateVentanaUsuarios(JFrame ventana, Usuario usuario) {

		VentanaUsuarios ventanaUsuarios = new VentanaUsuarios(usuario, ventana);
		ventana.setVisible(false);
		ventanaUsuarios.setVisible(true);
	}

	public String getSelectedISBN(JTable table, JFrame ventana) {

		String selectedISBN = "";

		try {

			selectedISBN = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();

		} catch (IndexOutOfBoundsException e) {

			JOptionPane.showMessageDialog(ventana, "No ha seleccionado ningún libro", "Error",
					JOptionPane.ERROR_MESSAGE);

		}

		return selectedISBN;

	}

	public String getSelectedPrestamo(JTable table, JFrame ventana) {

		String selectedMail = "";

		try {

			selectedMail = table.getModel().getValueAt(table.getSelectedRow(), 1).toString();
			System.out.println(selectedMail);

		} catch (IndexOutOfBoundsException e) {

			JOptionPane.showMessageDialog(ventana, "No ha seleccionado ningún préstamo", "Error",
					JOptionPane.ERROR_MESSAGE);

		}

		return selectedMail;

	}

	public String getSelectedUser(JTable table, JFrame ventana) {

		String selectedMail = "";

		try {

			selectedMail = table.getModel().getValueAt(table.getSelectedRow(), 2).toString();
			System.out.println(selectedMail);

		} catch (IndexOutOfBoundsException e) {

			JOptionPane.showMessageDialog(ventana, "No ha seleccionado ningún usuario", "Error",
					JOptionPane.ERROR_MESSAGE);

		}

		return selectedMail;

	}

}
