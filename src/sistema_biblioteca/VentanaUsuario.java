package sistema_biblioteca;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaUsuario extends JFrame implements ActionListener {

	CardLayout cardLayout = new CardLayout();

	private Usuario usuario;

	JPanel mainPanel = new JPanel(cardLayout);
	JPanel panelTitulo = new JPanel();
	JPanel panelSalir = new JPanel();
	JPanel panelUsuario = new JPanel();
	JPanel menuUsuario = new JPanel();
	JPanel panelBibliotecario = new JPanel();
	JPanel menuBibliotecario = new JPanel();
	private VentanaLogin ventana;

	public VentanaUsuario(Usuario usuario, VentanaLogin ventana) {
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
		setSize(400, 300);
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
		Factory.newButton(menuUsuario, "Consultar y alquilar libros", "getlibros", this);

		Factory.newButton(panelSalir, "Salir", "salir", this);

		panelUsuario.add(Factory.newPanel(), BorderLayout.EAST);
		panelUsuario.add(Factory.newPanel(), BorderLayout.WEST);
		panelUsuario.add(menuUsuario, BorderLayout.CENTER);

		panelBibliotecario.setLayout(new BorderLayout(10, 50));
		menuBibliotecario.setLayout(new GridLayout(2, 1, 10, 10));
		Factory.newButton(menuBibliotecario, "Prestamos realizados", "getprestamos", this);
		Factory.newButton(menuBibliotecario, "Cargar Libro", "createlibro", this);
		Factory.newButton(menuBibliotecario, "Modificar Libro", "updatelibro", this);

		panelBibliotecario.add(Factory.newPanel(), BorderLayout.EAST);
		panelBibliotecario.add(Factory.newPanel(), BorderLayout.WEST);

		panelBibliotecario.add(menuBibliotecario, BorderLayout.CENTER);

		mainPanel.add(panelUsuario, "panelusuario");
		mainPanel.add(panelBibliotecario, "panelbibliotecario");

		getContentPane().add(mainPanel);

		if (usuario instanceof UsuarioBasico) {
			Factory.newLabel(panelTitulo, "Bienvenido " + usuario.getNombre());
			panelUsuario.add(panelTitulo, BorderLayout.NORTH);
			panelUsuario.add(panelSalir, BorderLayout.SOUTH);

			cardLayout.show(mainPanel, "panelusuario");

		} else {
			Factory.newLabel(panelTitulo, "Bienvenido " + usuario.getNombre() + " " + "[BIBLIOTECARIO]");
			panelBibliotecario.add(panelTitulo, BorderLayout.NORTH);
			panelBibliotecario.add(panelSalir, BorderLayout.SOUTH);
			cardLayout.show(mainPanel, "panelbibliotecario");
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("salir")) {

			this.setVisible(false);
			VentanaLogin ventanaLogin = new VentanaLogin();
			ventanaLogin.setVisible(true);

		} else if (e.getActionCommand().equals("getlibros") || e.getActionCommand().equals("createlibro")
				|| e.getActionCommand().equals("updatelibro")) {

			VentanaLibros ventanaLibros = new VentanaLibros(usuario, e.getActionCommand(), this);
			this.setVisible(false);
			ventanaLibros.setVisible(true);
		} else if (e.getActionCommand().equals("getprestamos")) {
			VentanaPrestamos ventanaPrestamos = new VentanaPrestamos(usuario, this);
			this.setVisible(false);
			ventanaPrestamos.setVisible(true);

		}

	}

}
