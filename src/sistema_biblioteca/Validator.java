package sistema_biblioteca;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Validator {

	public static boolean validateLoginInput(String mail, String psw, VentanaLogin ventanaLogin) {

		boolean valid = true;

		Pattern mailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
		Pattern pswPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
		Matcher mailMatcher = mailPattern.matcher(mail);
		Matcher pswMatcher = pswPattern.matcher(psw);

		if (!mailMatcher.matches()) {
			JOptionPane.showMessageDialog(ventanaLogin, "Mail inválido");
			valid = false;
		} else if (!pswMatcher.matches()) {
			JOptionPane.showMessageDialog(ventanaLogin, "Contrasenia inválida");
			valid = false;

		}

		return valid;

	}

	public static Usuario userExists(String mail, char[] psw, VentanaLogin ventanaLogin) {

		String pswString = new String(psw);

		List<Usuario> users = RepositorioUsuarios.getUsuarios();

		boolean valid = validateLoginInput(mail, pswString, ventanaLogin);

		if (valid) {

			for (Usuario usuario : users) {

				if (usuario.getMail().equals(mail) && usuario.getPsw().equals(pswString)) {

					return usuario;

				}

			}

			JOptionPane.showMessageDialog(ventanaLogin, "Usuario no encontrado");

		}

		return null;

	}

	public static boolean validateLibroInput(String ISBN, String titulo, String autor, String edicion,
			VentanaLibros ventanaLibros) {

		boolean valid = true;

		Pattern isbnPattern = Pattern.compile("^\\d{13}$");
		Pattern tituloPattern = Pattern.compile("^[\\\\p{L}0-9 ,.:;!?()'\\\"-]{1,100}$");
		Pattern autorPattern = Pattern.compile("^[\\p{L} '-]{2,60}$");
		Pattern edicionPattern = Pattern.compile("^\\d{1,4}$");

		Matcher isbnMatcher = isbnPattern.matcher(ISBN);
		Matcher tituloMatcher = tituloPattern.matcher(titulo);
		Matcher autorMatcher = autorPattern.matcher(autor);
		Matcher edicionMatcher = edicionPattern.matcher(edicion);

		if (!isbnMatcher.matches()) {
			JOptionPane.showMessageDialog(ventanaLibros, "ISBN inválido");
			valid = false;
		} else if (!tituloMatcher.matches()) {
			JOptionPane.showMessageDialog(ventanaLibros, "Título inválido");
			valid = false;

		} else if (!autorMatcher.matches()) {
			JOptionPane.showMessageDialog(ventanaLibros, "Autor inválido");
			valid = false;
		} else if (!edicionMatcher.matches()) {
			JOptionPane.showMessageDialog(ventanaLibros, "Edición inválida");
			valid = false;
		}

		return valid;
	}

	public static boolean checkExiste(String ISBN, String titulo, String autor, String edicion,
			VentanaLibros ventanaLibros) {

		boolean valid = validateLibroInput(ISBN, titulo, autor, edicion, ventanaLibros);
		boolean exists = false;

		if (valid) {

			List<Libro> libros = RepositorioLibros.getLibros();

			for (Libro libro : libros) {

				if (libro.getISBN().equals(ISBN)) {
					JOptionPane.showMessageDialog(ventanaLibros, "El ISBN ingresado ya existe");

					exists = true;

				} else if (libro.getTitulo().equalsIgnoreCase(titulo)) {

					JOptionPane.showMessageDialog(ventanaLibros, "El título ingresado ya existe");

					exists = true;

				}

			}

		}
		return exists;

	}

}
