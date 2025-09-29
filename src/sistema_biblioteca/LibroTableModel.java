package sistema_biblioteca;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class LibroTableModel extends AbstractTableModel {

	private List<Libro> libros;
	private String[] columnas = { "ISBN", "Título", "Autor", "Edición", "Estado" };

	public LibroTableModel(List<Libro> libros) {
		this.libros = libros;
	}

	@Override
	public int getRowCount() {
		return libros.size();
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
		Libro libro = libros.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return libro.getISBN();
		case 1:
			return libro.getTitulo();
		case 2:
			return libro.getAutor();
		case 3:
			return libro.getEdicion();
		case 4:
			return libro.getEstado();
		default:
			return null;
		}
	}

}
