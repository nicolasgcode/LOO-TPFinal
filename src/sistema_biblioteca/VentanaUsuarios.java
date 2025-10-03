package sistema_biblioteca;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class VentanaUsuarios extends JFrame implements ActionListener {

	ActionHandler handler = new ActionHandler();
	JComboBox<String> estadoCombo = new JComboBox<String>();
	private JTable table = new JTable();
	Usuario usuario;
	private List<Usuario> usuarios = RepositorioUsuarios.getUsuarios();

	public VentanaUsuarios(Usuario usuario, JFrame ventana) {
		this.usuario = usuario;
		setSize(400, 300);
		setTitle("Panel de usuarios");
		setLocationRelativeTo(ventana);

		showUsersPanel();

	}

	public void showUsersPanel() {

		TableModel tableModel = new UserTableModel(usuarios);

		JTable table = new JTable(tableModel) {
			@Override
			public String getToolTipText(java.awt.event.MouseEvent e) {
				java.awt.Point p = e.getPoint();
				int rowIndex = rowAtPoint(p);
				int colIndex = columnAtPoint(p);

				if (colIndex == 2 && rowIndex >= 0) {
					Object value = getValueAt(rowIndex, colIndex);
					return value != null ? value.toString() : null;
				}
				return null;
			}
		};

		this.table = table;

		JScrollPane scrollPane = new JScrollPane(table);
		JPanel panelUsuarios = new JPanel(new BorderLayout());
		JPanel btnPanel = new JPanel(new FlowLayout());

		btnPanel.add(Factory.newButton(btnPanel, "Activar/Desactivar", "gestionarusuario", this));

		btnPanel.add(Factory.newButton(btnPanel, "Salir", "salir", this));

		panelUsuarios.add(scrollPane, BorderLayout.CENTER);
		panelUsuarios.add(btnPanel, BorderLayout.SOUTH);

		this.getContentPane().add(panelUsuarios);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("salir")) {

			handler.Salir(this, usuario);

		} else if (e.getActionCommand().equals("gestionarusuario")) {

			handler.GestionarUsuario(usuario, handler.getSelectedUser(table, this), table, this);

		}

	}

}
