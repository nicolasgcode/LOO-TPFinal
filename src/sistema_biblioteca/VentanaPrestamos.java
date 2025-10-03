package sistema_biblioteca;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class VentanaPrestamos extends JFrame implements ActionListener {

	private JTable table;
	CardLayout cardLayout = new CardLayout();
	private JPanel prestamosPanel = new JPanel(cardLayout);
	private JPanel btnPanel = new JPanel();
	private JPanel headerPanel = new JPanel();
	private JPanel modBtnPanel = new JPanel();
	private JComboBox<String> estadoCombo = new JComboBox<>();
	private JPanel botonesContainer = new JPanel(new CardLayout());
	private CardLayout botonesLayout = (CardLayout) botonesContainer.getLayout();
	private Usuario usuario;
	List<Prestamo> prestamos = new ArrayList<Prestamo>();

	private ActionHandler handler = new ActionHandler();

	public VentanaPrestamos(Usuario usuario, JFrame ventana) {

		this.usuario = usuario;

		setSize(400, 300);

		setLocationRelativeTo(ventana);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		showPanelPrestamos();

	}

	public void showPanelPrestamos() {

		if (usuario instanceof Bibliotecario) {
			setTitle("Panel de prestamos realizados");
			prestamos = RepositorioPrestamos.getPrestamos();

		} else {
			setTitle("Panel mis prestamos");
			prestamos = usuario.getPrestamos();
			headerPanel.setVisible(false);
		}

		TableModel tableModel = new PrestamoTableModel(prestamos);
		table = new JTable(tableModel);

		headerPanel.setLayout(new FlowLayout());
		Factory.newLabel(headerPanel, "Filtrar por:");
		estadoCombo.addItem("Todos");
		estadoCombo.addItem("En curso");

		headerPanel.add(estadoCombo);

		Factory.newButton(headerPanel, "Filtrar", "filtrar", this);

		JScrollPane scrollPane = new JScrollPane(table);

		prestamosPanel.setLayout(new BorderLayout(10, 50));
		btnPanel.setLayout(new FlowLayout());
		prestamosPanel.add(scrollPane, BorderLayout.CENTER);
		btnPanel.add(Factory.newButton(btnPanel, "Salir", "salir", this));
		modBtnPanel.add(Factory.newButton(modBtnPanel, "Finalizar", "finalizarprestamo", this));
		modBtnPanel.add(Factory.newButton(modBtnPanel, "Salir", "salir", this));

		botonesContainer.add(btnPanel, "btnpanel");
		botonesContainer.add(modBtnPanel, "modbtnpanel");

		prestamosPanel.add(botonesContainer, BorderLayout.SOUTH);
		prestamosPanel.add(headerPanel, BorderLayout.NORTH);

		this.getContentPane().add(prestamosPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("salir")) {

			handler.Salir(this, usuario);

		} else if (e.getActionCommand().equals("filtrar")) {
			handler.filtrarPrestamos(table);

			if (estadoCombo.getSelectedItem().equals("En curso")) {
				botonesLayout.show(botonesContainer, "modbtnpanel");
			} else {
				table.setModel(new PrestamoTableModel(prestamos));
				botonesLayout.show(botonesContainer, "btnpanel");
			}
		} else if (e.getActionCommand().equals("finalizarprestamo")) {

			handler.finalizarPrestamo(usuario, handler.getSelectedMail(table, this), "Finalizado", table, this);

		}

	}

}
