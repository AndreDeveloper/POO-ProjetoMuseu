package edu.museu.control;

import java.util.List;

import javax.swing.event.TableModelListener;

import edu.museu.entity.Local;
import edu.museu.entity.Obra;
import edu.museu.infrastructure.LocalDAO;

public class PesquisaLocaisControl implements PesquisaControl{
	private List<Local> lista = new LocalDAO().selectAll();
	
	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex){
		case 0: return String.class;
		case 1: return String.class;
		case 2: return String.class;
		}
		return null;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch(columnIndex){
		case 0: return "Nome";
		case 1: return "Responsavel";
		case 2: return "Telefone";		
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
		Local local = lista.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return local.getNomeLocal();
		case 1:
			return local.getResponsavel();
		case 2:
			return local.getTelefone();
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

	@Override
	public List preencheLista() {
		this.lista = new LocalDAO().selectAll();
		return lista;
	}

	@Override
	public List preencheLista(String value) {
		this.lista = new LocalDAO().selectByName(value);
		return lista;
	}

	@Override
	public List getLista() {
		return lista;
	}

	@Override
	public void setLista(List lista) {
		this.lista = lista;
		
	}

}
