package sistema_biblioteca;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

public class VentanaLibros extends JFrame implements ActionListener {

	private Usuario usuario;
	private ActionHandler handler = new ActionHandler();

	private CardLayout cardLayout = new CardLayout();
	private JTable table;

	private JPanel mainPanel = new JPanel(cardLayout);

	private JPanel dataPanel = new JPanel();

	private JPanel cargaLibroPanel = new JPanel();

	private JPanel cargaBtnPanel = new JPanel();

	JComboBox<EstadoLibro> comboEstado = new JComboBox<>();

	private JTextField ISBNTxf = new JTextField();
	private JTextField tituloTxf = new JTextField();
	private JTextField autorTxf = new JTextField();
	private JTextField edicionTxf = new JTextField();
	List<Libro> libros = new ArrayList<Libro>();

	public VentanaLibros(Usuario usuario, String command, JFrame ventana) {
		this.usuario = usuario;
		setTitle("Panel de libros");
		if (command.equals("get_libros") || command.equals("update_libro")) {

			setSize(520, 300);

			showPanelLibros();

		} else if (command.equals("create_libro")) {

			setSize(400, 300);

			showPanelCargaLibro();

		}
		setLocationRelativeTo(ventana);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public void showPanelLibros() {

		cargaBtnPanel.setLayout(new FlowLayout());

		if (usuario instanceof UsuarioBasico usuario) {

			libros = usuario.consultarLibrosDisp();

			cargaBtnPanel.add(Factory.newButton(cargaBtnPanel, "Realizar prestamo", "realizar_prestamo", this));
			cargaBtnPanel.add(Factory.newButton(cargaBtnPanel, "Salir", "salir", this));

		} else if (usuario instanceof Bibliotecario) {

			libros = RepositorioLibros.getLibros();
			Factory.newLabel(cargaBtnPanel, "Estado");
			comboEstado.addItem(EstadoLibro.DISPONIBLE);
			comboEstado.addItem(EstadoLibro.NO_DISPONIBLE);
			cargaBtnPanel.add(comboEstado);
			cargaBtnPanel.add(Factory.newButton(cargaBtnPanel, "Modificar libro", "modificar_libro", this));
			cargaBtnPanel.add(Factory.newButton(cargaBtnPanel, "Salir", "salir", this));

		}

		TableModel tableModel = new LibroTableModel(libros);
		table = new JTable(tableModel) {
			@Override
			public String getToolTipText(java.awt.event.MouseEvent e) {
				java.awt.Point p = e.getPoint();
				int rowIndex = rowAtPoint(p);
				int colIndex = columnAtPoint(p);

				if (colIndex == 1 && rowIndex >= 0) {
					Object value = getValueAt(rowIndex, colIndex);
					return value != null ? value.toString() : null;
				}
				return null;
			}
		};

		JScrollPane scrollPane = new JScrollPane(table);

		JPanel librosPanel = new JPanel(new BorderLayout());
		librosPanel.add(scrollPane, BorderLayout.CENTER);

		librosPanel.add(cargaBtnPanel, BorderLayout.SOUTH);

		mainPanel.add(librosPanel, "librospanel");

		cardLayout.show(mainPanel, "librospanel");

		this.getContentPane().add(mainPanel);

	}

	public void showPanelCargaLibro() {

		dataPanel.setLayout(new BorderLayout(10, 50));

		cargaLibroPanel.setLayout(new GridLayout(5, 2));

		cargaBtnPanel.setLayout(new FlowLayout());

		Factory.newLabel(cargaLibroPanel, "ISBN");
		cargaLibroPanel.add(ISBNTxf);
		Factory.newLabel(cargaLibroPanel, "Título");
		cargaLibroPanel.add(tituloTxf);
		Factory.newLabel(cargaLibroPanel, "Autor");
		cargaLibroPanel.add(autorTxf);
		Factory.newLabel(cargaLibroPanel, "Edición");
		cargaLibroPanel.add(edicionTxf);

		dataPanel.add(cargaLibroPanel, BorderLayout.CENTER);

		cargaBtnPanel.add(Factory.newButton(cargaBtnPanel, "Cargar", "cargar", this));
		cargaBtnPanel.add(Factory.newButton(cargaBtnPanel, "Limpiar", "limpiar", this));
		cargaBtnPanel.add(Factory.newButton(cargaBtnPanel, "Salir", "salir", this));

		dataPanel.add(cargaBtnPanel, BorderLayout.SOUTH);

		dataPanel.add(Factory.newPanel(), BorderLayout.NORTH);
		dataPanel.add(Factory.newPanel(), BorderLayout.EAST);
		dataPanel.add(Factory.newPanel(), BorderLayout.WEST);

		mainPanel.add(dataPanel, "cargalibropanel");
		cardLayout.show(mainPanel, "cargalibropanel");

		this.getContentPane().add(mainPanel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();

		switch (command) {
		case "realizar_prestamo":
			handler.RealizarPrestamo(usuario, handler.getSelectedISBN(table, this), table, this);

			break;

		case "cargar":
			handler.CargarLibro(usuario, ISBNTxf.getText(), tituloTxf.getText(), autorTxf.getText(),
					edicionTxf.getText(), this);
			break;

		case "modificar_libro":
			handler.ModificarLibro(usuario, handler.getSelectedISBN(table, this),
					(EstadoLibro) comboEstado.getSelectedItem(), table, this);
			break;

		case "limpiar":
			limpiarCarga();
			break;

		case "salir":
			handler.Salir(this, usuario);
			break;

		default:
			break;
		}

	}

	public void limpiarCarga() {
		ISBNTxf.setText("");
		tituloTxf.setText("");
		autorTxf.setText("");
		edicionTxf.setText("");
	}

}
