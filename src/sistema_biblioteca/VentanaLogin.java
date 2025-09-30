package sistema_biblioteca;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VentanaLogin extends JFrame implements ActionListener {

	private JPanel mainPanel = new JPanel();
	private JPanel loginPanel = new JPanel();
	private JPanel btnPanel = new JPanel();

	private JTextField mailTxf = new JTextField();
	private JPasswordField pswTxf = new JPasswordField();

	public VentanaLogin() {
		setSize(400, 300);
		setTitle("Sistema de gestión para biblioteca");
		setLocationRelativeTo(null);
		createLoginPanel();
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public void createLoginPanel() {

		mainPanel.setLayout(new BorderLayout(60, 50));

		loginPanel.setLayout(new GridLayout(3, 2, 10, 10));

		Factory.newLabel(loginPanel, "Mail");
		loginPanel.add(mailTxf);
		Factory.newLabel(loginPanel, "Contraseña");
		loginPanel.add(pswTxf);

		JButton loginBtn = Factory.newButton(btnPanel, "Iniciar sesión", "login", this);
		btnPanel.add(loginBtn);

		mainPanel.add(Factory.newPanel(), BorderLayout.NORTH);
		mainPanel.add(Factory.newPanel(), BorderLayout.EAST);
		mainPanel.add(Factory.newPanel(), BorderLayout.WEST);

		mainPanel.add(loginPanel, BorderLayout.CENTER);

		mainPanel.add(btnPanel, BorderLayout.SOUTH);

		this.getContentPane().add(mainPanel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("login")) {

			Usuario usuario = RepositorioUsuarios.getUser(mailTxf.getText(), pswTxf.getPassword(), this);

			if (usuario != null) {
				VentanaUsuario ventanaUsuario = new VentanaUsuario(usuario, this);

				this.setVisible(false);

				ventanaUsuario.setVisible(true);

			}

		}

	}

}
