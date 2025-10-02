package sistema_biblioteca;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class PrestamoTableModel extends AbstractTableModel {

	private List<Prestamo> prestamos;
	private String[] columnas = { "Usuario", "Mail", "Libro", "Fecha Inicio", "Fecha Fin", "Estado" };

	public PrestamoTableModel(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

	@Override
	public int getRowCount() {
		return prestamos.size();
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
		Prestamo prestamo = prestamos.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return prestamo.getUsuario().getNombre().concat(" " + prestamo.getUsuario().getApellido());
		case 1:
			return prestamo.getUsuario().getMail();
		case 2:
			return prestamo.getLibro().getTitulo();
		case 3:
			return prestamo.getFechaInicio();
		case 4:
			return prestamo.getFechaFin();
		case 5:
			return prestamo.getEstado();

		default:
			return null;
		}
	}

}
