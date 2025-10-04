package sistema_biblioteca;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

public class VentanaUsuario extends JFrame implements ActionListener {

	CardLayout cardLayout = new CardLayout();

	private Usuario usuario;

	JPanel mainPanel = new JPanel(cardLayout);
	JPanel panelTitulo = new JPanel();
	JPanel panelSalir = new JPanel();
	JPanel panelUsuario = new JPanel();
	JPanel panelUsuarios = new JPanel(new BorderLayout());
	JPanel menuUsuario = new JPanel();
	JPanel panelBibliotecario = new JPanel();
	JPanel menuBibliotecario = new JPanel();

	JButton btnAlquilar = Factory.newButton(menuUsuario, "Consultar y alquilar libros", "get_libros", this);

	JTable table = new JTable();
	private JFrame ventana = new JFrame();

	private ActionHandler handler = new ActionHandler();

	public VentanaUsuario(Usuario usuario, JFrame ventana) {
		this.ventana = ventana;
		this.usuario = usuario;
		setSize(400, 300);
		setTitle("Panel de usuario");
		setLocationRelativeTo(ventana);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		showUserPanel(usuario);

	}

	public VentanaUsuario(Usuario usuario) {
		this.usuario = usuario;

		setTitle("Panel de usuario");
		setLocationRelativeTo(ventana);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		showUserPanel(usuario);

	}

	public void showUserPanel(Usuario usuario) {

		panelTitulo.setLayout(new FlowLayout());
		panelSalir.setLayout(new FlowLayout());

		panelUsuario.setLayout(new BorderLayout(10, 50));
		menuUsuario.setLayout(new GridLayout(2, 1, 10, 10));

		if (usuario instanceof UsuarioBasico) {

		}

		Factory.newButton(menuUsuario, "Mis prestamos", "mis_prestamos", this);

		Factory.newButton(panelSalir, "Salir", "salir", this);

		panelUsuario.add(Factory.newPanel(), BorderLayout.EAST);
		panelUsuario.add(Factory.newPanel(), BorderLayout.WEST);
		panelUsuario.add(menuUsuario, BorderLayout.CENTER);

		panelBibliotecario.setLayout(new BorderLayout(10, 50));
		menuBibliotecario.setLayout(new GridLayout(4, 1, 10, 10));
		Factory.newButton(menuBibliotecario, "Prestamos realizados", "get_prestamos", this);
		Factory.newButton(menuBibliotecario, "Cargar Libro", "create_libro", this);
		Factory.newButton(menuBibliotecario, "Modificar Libro", "update_libro", this);
		Factory.newButton(menuBibliotecario, "Gestionar usuarios", "gestion_usuarios", this);

		panelBibliotecario.add(Factory.newPanel(), BorderLayout.EAST);
		panelBibliotecario.add(Factory.newPanel(), BorderLayout.WEST);

		panelBibliotecario.add(menuBibliotecario, BorderLayout.CENTER);

		mainPanel.add(panelUsuario, "panelusuario");
		mainPanel.add(panelBibliotecario, "panelbibliotecario");

		getContentPane().add(mainPanel);

		if (usuario instanceof UsuarioBasico) {

			if (usuario.getEstado().equalsIgnoreCase("Activo")) {

				btnAlquilar.setEnabled(true);
				Factory.newLabel(panelTitulo, "Bienvenido " + usuario.getNombre());

			} else if (usuario.getEstado().equalsIgnoreCase("Desactivado")) {

				btnAlquilar.setEnabled(false);
				Factory.newLabel(panelTitulo, "Bienvenido " + usuario.getNombre() + " " + "[Desactivado]");

			}
			setSize(400, 300);

			panelUsuario.add(panelTitulo, BorderLayout.NORTH);
			panelUsuario.add(panelSalir, BorderLayout.SOUTH);

			cardLayout.show(mainPanel, "panelusuario");

		} else {
			setSize(400, 350);
			Factory.newLabel(panelTitulo, "Bienvenido " + usuario.getNombre() + " " + "[BIBLIOTECARIO]");
			panelBibliotecario.add(panelTitulo, BorderLayout.NORTH);
			panelBibliotecario.add(panelSalir, BorderLayout.SOUTH);
			cardLayout.show(mainPanel, "panelbibliotecario");
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();

		switch (command) {
		case "get_libros":
		case "create_libro":
		case "update_libro":

			handler.CreateVentanaLibros(this, usuario, command);

			break;
		case "get_prestamos":
		case "mis_prestamos":

			handler.CreateVentanaPrestamos(this, usuario);

			break;

		case "gestion_usuarios":
			handler.CreateVentanaUsuarios(this, usuario);

			break;
		case "salir":

			handler.CreateVentanaLogin(this);

			break;

		default:
			break;
		}

	}

}
