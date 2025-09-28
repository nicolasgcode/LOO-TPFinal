package sistema_biblioteca;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Factory {

	public static void newLabel(JPanel panel, String text) {
		JLabel label = new JLabel();
		label.setText(text);
		panel.add(label);
	}

	public static JButton newButton(JPanel panel, String text, String command, ActionListener listener) {

		JButton btn = new JButton();
		btn.setText(text);
		btn.setActionCommand(command);

		btn.setEnabled(true);
		panel.add(btn);

		btn.addActionListener(listener);

		return btn;

	}

	public static JPanel newPanel() {
		JPanel panel = new JPanel();

		return panel;
	}

}
