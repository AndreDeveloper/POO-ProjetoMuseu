package edu.museu.control;

import java.util.List;

import javax.swing.event.TableModelListener;

import edu.museu.entity.Exposicao;
import edu.museu.infrastructure.ExposicaoDAO;

public class PesquisaExposicaoControl implements PesquisaControl{
	private static List<Exposicao> lista = new ExposicaoDAO().selectAll();
	
	public PesquisaExposicaoControl(){
		lista = new ExposicaoDAO().selectAll();
	}
	
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
		case 3: return String.class;
		}
		return null;
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch(columnIndex){
		case 0: return "Nome";
		case 1: return "Data Inicio";
		case 2: return "Data Termino";
		case 3: return "Preço";
		}
		return null;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Exposicao exposicao = lista.get(rowIndex);
		switch(columnIndex){
		case 0: return exposicao.getNome();
		case 1: return exposicao.getDataInicio();
		case 2: return exposicao.getDataFim();
		case 3: return exposicao.getValor();
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
		lista = new ExposicaoDAO().selectAll();
		return lista;
	}

	@Override
	public List preencheLista(String value) {
		lista = new ExposicaoDAO().selectByName(value);
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
