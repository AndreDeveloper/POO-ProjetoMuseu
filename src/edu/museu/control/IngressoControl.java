package edu.museu.control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import edu.museu.entity.Exposicao;
import edu.museu.entity.Ingresso;
import edu.museu.infrastructure.ExposicaoDAO;
import edu.museu.infrastructure.IngressoDAO;

public class IngressoControl implements TableModel{
	private List<Exposicao> listaExposicao = new ArrayList<Exposicao>();
	public IngressoControl() {
		super();
		atualizaLista();
	}

	public List<Exposicao> getListaExposicao() {
		return listaExposicao;
	}

	public void setListaExposicao(List<Exposicao> listaExposicao) {
		this.listaExposicao = listaExposicao;
	}
	
	public void atualizaLista(){
		listaExposicao = new ExposicaoDAO().selectAllToBuy();
	}
	
	public void salvar(Ingresso ingresso){
		IngressoDAO dao = new IngressoDAO();
		dao.insert(ingresso);
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex){
		case 0: return String.class;
		case 1: return String.class;
		case 2: return String.class;
		case 3: return String.class;
		}
		return null;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch(columnIndex){
		case 0: return "Nome";
		case 1: return "Inicio";
		case 2: return "Termino";
		case 3: return "Valor";
		}
		return null;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listaExposicao.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Exposicao exposicao = listaExposicao.get(rowIndex);
		switch (columnIndex){
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
}
