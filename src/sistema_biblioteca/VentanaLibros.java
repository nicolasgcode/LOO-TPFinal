package sistema_biblioteca;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

public class VentanaLibros extends JFrame implements ActionListener {

	private Usuario usuario;

	private CardLayout cardLayout = new CardLayout();
	private JTable table;

	private JPanel mainPanel = new JPanel(cardLayout);

	private JPanel dataPanel = new JPanel();

	private JPanel cargaLibroPanel = new JPanel();

	private JPanel cargaBtnPanel = new JPanel();

	private JTextField ISBNTxf = new JTextField();
	private JTextField tituloTxf = new JTextField();
	private JTextField autorTxf = new JTextField();
	private JTextField edicionTxf = new JTextField();
	private JTextField prestamoTxf = new JTextField();

	public VentanaLibros(Usuario usuario, VentanaUsuario ventana) {
		this.usuario = usuario;
		setSize(400, 300);
		setTitle("Panel de libros");
		setLocationRelativeTo(ventana);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		if (usuario instanceof UsuarioBasico) {

			showPanelLibros();

		} else {

			showPanelCargaLibro();

		}

	}

	public void showPanelLibros() {

		cargaBtnPanel.setLayout(new GridLayout(2, 2, 10, 10));

		List<Libro> librosDisponibles = new ArrayList<Libro>();

		if (usuario instanceof UsuarioBasico usuario) {

			librosDisponibles = usuario.consultarLibrosDisp();

		}

		TableModel tableModel = new LibroTableModel(librosDisponibles);
		table = new JTable(tableModel);

		JScrollPane scrollPane = new JScrollPane(table);

		JPanel librosPanel = new JPanel(new BorderLayout());
		librosPanel.add(scrollPane, BorderLayout.CENTER);

		Factory.newLabel(cargaBtnPanel, "ISBN a alquilar");
		cargaBtnPanel.add(prestamoTxf);
		cargaBtnPanel.add(Factory.newButton(cargaBtnPanel, "Realizar prestamo", "realizarprestamo", this));
		cargaBtnPanel.add(Factory.newButton(cargaBtnPanel, "Salir", "salir", this));

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
		if (e.getActionCommand().equals("salir")) {

			VentanaUsuario ventanaUsuario = new VentanaUsuario(usuario, null);
			this.setVisible(false);
			ventanaUsuario.setVisible(true);

		} else if (e.getActionCommand().equals("cargar")) {

			if (usuario instanceof Bibliotecario bibliotecario) {

				boolean exito = bibliotecario.cargarLibro(ISBNTxf.getText(), tituloTxf.getText(), autorTxf.getText(),
						edicionTxf.getText(), this);

				if (exito) {

					limpiarCarga();

				}

			} else {
				JOptionPane.showMessageDialog(this, "No tienes permiso para cargar un libro");
			}

		} else if (e.getActionCommand().equals("realizarprestamo")) {

			if (usuario instanceof UsuarioBasico usuario) {

				usuario.realizarPrestamo(prestamoTxf.getText(), usuario, this);

			}

		}

	}

	public void limpiarCarga() {
		ISBNTxf.setText("");
		tituloTxf.setText("");
		autorTxf.setText("");
		edicionTxf.setText("");
	}

}
