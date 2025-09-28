package sistema_biblioteca;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Validator {

	public static boolean validInput(String mail, String psw, VentanaLogin ventanaLogin) {

		boolean valid = true;

		Pattern mailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
		Pattern pswPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
		Matcher mailMatcher = mailPattern.matcher(mail);
		Matcher pswMatcher = pswPattern.matcher(psw.trim());

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

		boolean valid = validInput(mail, pswString, ventanaLogin);

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

}
