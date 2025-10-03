package sistema_biblioteca;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class VentanaUsuarios extends JFrame implements ActionListener {

	ActionHandler handler = new ActionHandler();
	private JPanel headerPanel = new JPanel();
	private JPanel modBtnPanel = new JPanel();
	JComboBox<String> estadoCombo = new JComboBox<String>();
	private JPanel botonesContainer = new JPanel(new CardLayout());
	private JTable table = new JTable();
	private CardLayout botonesLayout = (CardLayout) botonesContainer.getLayout();
	Usuario usuario;

	public VentanaUsuarios(Usuario usuario, JFrame ventana) {
		this.usuario = usuario;
		setSize(400, 300);
		setTitle("Panel de usuarios");
		setLocationRelativeTo(ventana);

		showUsersPanel();

	}

	public void showUsersPanel() {

		TableModel tableModel = new UserTableModel(RepositorioUsuarios.getUsuarios());

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

		headerPanel.setLayout(new FlowLayout());
		Factory.newLabel(headerPanel, "Filtrar por:");
		estadoCombo.addItem("Todos");
		estadoCombo.addItem("Activo");

		headerPanel.add(estadoCombo);

		Factory.newButton(headerPanel, "Filtrar", "filtrar", this);

		JScrollPane scrollPane = new JScrollPane(table);
		JPanel panelUsuarios = new JPanel(new BorderLayout());
		JPanel btnPanel = new JPanel(new FlowLayout());

		btnPanel.add(Factory.newButton(btnPanel, "Salir", "salir", this));
		modBtnPanel.add(Factory.newButton(modBtnPanel, "Finalizar", "finalizarprestamo", this));
		modBtnPanel.add(Factory.newButton(modBtnPanel, "Salir", "salir", this));

		botonesContainer.add(btnPanel, "btnpanel");
		botonesContainer.add(modBtnPanel, "modbtnpanel");

		Factory.newButton(btnPanel, "Salir", "salir", this);
		panelUsuarios.add(headerPanel, BorderLayout.NORTH);
		panelUsuarios.add(scrollPane, BorderLayout.CENTER);
		panelUsuarios.add(botonesContainer, BorderLayout.SOUTH);

		this.getContentPane().add(panelUsuarios);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("salir")) {

			handler.Salir(this, usuario);

		} else if (e.getActionCommand().equals("filtrar")) {

			boolean exito = handler.filtrarUsuarios(table);

			if (estadoCombo.getSelectedItem().equals("En curso")) {
				if (exito) {

					System.out.println("hi");

					botonesLayout.show(botonesContainer, "modbtnpanel");

				} else {
					JOptionPane.showMessageDialog(this, "No hay usuarios activos");
				}

			} else {
				table.setModel(new UserTableModel(usuarios));
				botonesLayout.show(botonesContainer, "btnpanel");
			}

		}

	}

}
