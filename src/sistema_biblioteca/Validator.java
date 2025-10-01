package sistema_biblioteca;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Validator {

	public static boolean validateLoginInput(String mail, String psw, JFrame ventana) {

		boolean valid = true;

		Pattern mailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
		Pattern pswPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
		Matcher mailMatcher = mailPattern.matcher(mail);
		Matcher pswMatcher = pswPattern.matcher(psw);

		if (!mailMatcher.matches()) {
			JOptionPane.showMessageDialog(ventana, "Mail inválido");
			valid = false;
		} else if (!pswMatcher.matches()) {
			JOptionPane.showMessageDialog(ventana, "Contrasenia inválida");
			valid = false;

		}

		return valid;

	}

	public static boolean validateLibroInput(String ISBN, String titulo, String autor, String edicion, JFrame ventana) {

		boolean valid = true;

		Pattern isbnPattern = Pattern.compile("^\\d{13}$");
		Pattern tituloPattern = Pattern.compile("[A-ZÁÉÍÓÚÑ][a-zA-ZÁÉÍÓÚáéíóúñÑ0-9\\s,%!¡¿?()\\[\\]{}+*/-]{1,50}");
		Pattern autorPattern = Pattern.compile(
				"^([a-zA-ZÁÉÍÓÚáéíóúÑñ]{2,}\\s[a-zA-ZÁÉÍÓÚáéíóúÑñ]{1,}'?-?[a-zA-ZÁÉÍÓÚáéíóúÑñ]{2,}\\s?([a-zA-ZÁÉÍÓÚáéíóúÑñ]{1,})?)");
		Pattern edicionPattern = Pattern.compile("^\\d{1,4}$");

		Matcher isbnMatcher = isbnPattern.matcher(ISBN);
		Matcher tituloMatcher = tituloPattern.matcher(titulo);
		Matcher autorMatcher = autorPattern.matcher(autor);
		Matcher edicionMatcher = edicionPattern.matcher(edicion);

		if (!isbnMatcher.matches()) {
			JOptionPane.showMessageDialog(ventana, "ISBN inválido");
			valid = false;
		} else if (!tituloMatcher.matches()) {
			JOptionPane.showMessageDialog(ventana, "Título inválido");
			valid = false;

		} else if (!autorMatcher.matches()) {
			JOptionPane.showMessageDialog(ventana, "Autor inválido");
			valid = false;
		} else if (!edicionMatcher.matches()) {
			JOptionPane.showMessageDialog(ventana, "Edición inválida");
			valid = false;
		}

		return valid;
	}

}
