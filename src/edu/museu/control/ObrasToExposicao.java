package edu.museu.control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import edu.museu.entity.Obra;
import edu.museu.infrastructure.ObraDAO;

public class ObrasToExposicao implements TableModel {
	private List<Obra> lista = new ArrayList<Obra>();

	public List<Obra> getLista() {
		return lista;
	}

	public void setLista(List<Obra> lista) {
		this.lista = lista;
	}

	public ObrasToExposicao() {
		lista = new ObraDAO().selectAll();
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return String.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		case 4:
			return String.class;
		}
		return null;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		switch (columnIndex) {
		case 0:
			return "Id";
		case 1:
			return "Obra";
		case 2:
			return "Autor";
		case 3:
			return "Tipo";
		case 4:
			return "Categoria";
		}
		return null;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return lista.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Obra obra = lista.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return obra.getId();
		case 1:
			return obra.getNomeObra();
		case 2:
			return obra.getNomeAutor();
		case 3:
			return obra.getTipoObra();
		case 4:
			return obra.getCategoriaObra();
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub

	}

}
