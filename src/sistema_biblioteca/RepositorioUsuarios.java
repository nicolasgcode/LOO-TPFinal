package sistema_biblioteca;

import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuarios {

	private static List<Usuario> users = new ArrayList<>();

	static {
		users.add(new UsuarioBasico("Nicolás", "García", "contactnicolasgarcia@gmail.com", "usubas123", "activo"));
		users.add(new UsuarioBasico("Lourdes", "Alí", "lali@gmail.com", "usubas321", "activo"));
		users.add(new Bibliotecario("Nora", "Rodriguez", "noeli@gmail.com", "usubib456", "activo"));
	}

	public static List<Usuario> getUsuarios() {
		return users;
	}

}
