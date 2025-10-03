package sistema_biblioteca;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RepositorioUsuarios {

	private static List<Usuario> users = new ArrayList<>();

	static {
		users.add(new UsuarioBasico("Nicolás", "García", "contactnicolasgarcia@gmail.com", "usubas123", "Activo"));
		users.add(new UsuarioBasico("Lourdes", "Alí", "lali@gmail.com", "usubas321", "Activo"));
		users.add(new Bibliotecario("Nora", "Rodriguez", "noeli@gmail.com", "usubib456", "Activo"));
	}

	public static List<Usuario> getUsuarios() {
		return users;
	}

	public static Usuario getUser(String mail, char[] psw, JFrame ventana) {

		String pswString = new String(psw);

		boolean valid = Validator.validateLoginInput(mail, pswString, ventana);

		if (valid) {

			for (Usuario usuario : users) {

				if (usuario.getMail().equals(mail) && usuario.getPsw().equals(pswString)) {

					return usuario;

				}

			}

			JOptionPane.showMessageDialog(ventana, "Usuario no encontrado", "Error", JOptionPane.ERROR_MESSAGE);

		}

		return null;

	}

	public static List<Usuario> getActivos() {

		List<Usuario> usuariosActivos = new ArrayList<Usuario>();

		for (Usuario usuario : getUsuarios()) {

			if (usuario.getEstado().equalsIgnoreCase("Activo")) {

				usuariosActivos.add(usuario);

			}

		}

		return usuariosActivos;

	}

	public static List<Usuario> getDesactivados() {

		List<Usuario> usuariosDesactivados = new ArrayList<Usuario>();

		for (Usuario usuario : getUsuarios()) {

			if (usuario.getEstado().equalsIgnoreCase("Desactivado")) {

				usuariosDesactivados.add(usuario);

			}

		}

		return usuariosDesactivados;

	}

	public static Usuario getUserByMail(String mail) {

		for (Usuario usuario : users) {

			if (usuario.getMail().equals(mail)) {

				return usuario;

			}

		}

		return null;
	}

}
