package sistema_biblioteca;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class UserTableModel extends AbstractTableModel {

	private List<Usuario> usuarios;
	private String[] columnas = { "Nombre", "Apellido", "Mail", "Estado" };

	public UserTableModel(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public int getRowCount() {
		return usuarios.size();
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public String getColumnName(int column) {
		return columnas[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Usuario usuario = usuarios.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return usuario.getNombre();
		case 1:
			return usuario.getApellido();
		case 2:
			return usuario.getMail();
		case 3:
			return usuario.getEstado();

		default:
			return null;
		}
	}

}