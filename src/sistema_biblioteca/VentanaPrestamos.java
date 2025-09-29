package sistema_biblioteca;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class VentanaPrestamos extends JFrame implements ActionListener {

	private JTable table;
	private JPanel prestamosPanel = new JPanel();
	private JPanel btnPanel = new JPanel();
	private Usuario usuario;

	public VentanaPrestamos(Usuario usuario, VentanaUsuario ventana) {

		this.usuario = usuario;

		setSize(400, 300);
		setTitle("Panel de prestamos");
		setLocationRelativeTo(ventana);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		showPanelPrestamos();

	}

	public void showPanelPrestamos() {

		List<Prestamo> prestamos = RepositorioPrestamos.getPrestamos();

		TableModel tableModel = new PrestamoTableModel(prestamos);
		table = new JTable(tableModel);

		JScrollPane scrollPane = new JScrollPane(table);

		prestamosPanel.setLayout(new BorderLayout(10, 50));
		btnPanel.setLayout(new FlowLayout());
		prestamosPanel.add(scrollPane, BorderLayout.CENTER);
		btnPanel.add(Factory.newButton(btnPanel, "Salir", "salir", this));
		prestamosPanel.add(btnPanel, BorderLayout.SOUTH);

		this.getContentPane().add(prestamosPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("salir")) {

			VentanaUsuario ventanaUsuario = new VentanaUsuario(usuario);
			this.setVisible(false);
			ventanaUsuario.setVisible(true);

		}

	}

}
