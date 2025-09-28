package sistema_biblioteca;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class VentanaLibros extends JFrame implements ActionListener {

	public VentanaLibros() {
		setSize(400, 300);
		setTitle("Panel de libros");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public void showPanelLibros() {
	}

	public void showPanelCargaLibro() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
