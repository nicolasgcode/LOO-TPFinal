package sistema_biblioteca;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaLogin extends JFrame implements ActionListener {

	private JPanel mainPanel = new JPanel();
	private JPanel northDivPanel = new JPanel();
	private JPanel loginPanel = new JPanel();
	private JPanel btnPanel = new JPanel();
	private JPanel eastDivPanel = new JPanel();
	private JPanel westDivPanel = new JPanel();

	private JTextField usernameTxf = new JTextField();
	private JTextField passwordTxf = new JTextField();

	public VentanaLogin() {
		setSize(400, 300);
		setTitle("Sistema de gestión para biblioteca");
		setLocationRelativeTo(null);
		createLoginView();
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public void createLoginView() {

		mainPanel.setLayout(new BorderLayout(10, 50));

		loginPanel.setLayout(new GridLayout(3, 2, 10, 10));

		newLabel(loginPanel, "Usuario");
		loginPanel.add(usernameTxf);
		newLabel(loginPanel, "Contrasenia");
		loginPanel.add(passwordTxf);

		JButton loginBtn = newButton(btnPanel, "Iniciar sesión", "login");
		btnPanel.add(loginBtn);

		mainPanel.add(northDivPanel, BorderLayout.NORTH);
		mainPanel.add(eastDivPanel, BorderLayout.EAST);
		mainPanel.add(westDivPanel, BorderLayout.WEST);

		mainPanel.add(loginPanel, BorderLayout.CENTER);

		mainPanel.add(btnPanel, BorderLayout.SOUTH);

		this.getContentPane().add(mainPanel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("login")) {

			VentanaUsuario ventanaUsuario = new VentanaUsuario();

			ventanaUsuario.setVisible(true);

		}

	}

	private void newLabel(JPanel panel, String text) {
		JLabel label = new JLabel();
		label.setText(text);
		panel.add(label);
	}

	private JButton newButton(JPanel panel, String text, String command) {

		JButton btn = new JButton();
		btn.setText(text);
		btn.setActionCommand(command);

		btn.setEnabled(true);
		panel.add(btn);

		btn.addActionListener(this);

		return btn;

	}

}
