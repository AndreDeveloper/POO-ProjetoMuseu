package edu.museu.control;

import java.util.List;

import javax.swing.event.TableModelListener;

import edu.museu.entity.Visitante;
import edu.museu.infrastructure.VisitanteDAO;

public class PesquisaVisitanteControl implements PesquisaControler {
	private List<Visitante> lista = new VisitanteDAO().selectAll();
	
	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub		
	}

	@Override
	public Class<?> getColumnClass(int coluna) {
		// TODO Auto-generated method stub
		switch(coluna){
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
	public String getColumnName(int coluna) {
		switch(coluna){
		case 0: return "CPF";
		case 1: return "Idade";
		case 2: return "Sexo";
		case 3: return "Nacionalidade";
		}
		return null;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return lista.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		Visitante v = lista.get(linha);
		switch(coluna){
		case 0: return v.getCpf();
		case 1: return v.getIdade();
		case 2: return v.getSexo();
		case 3: return v.getNacionalidade();
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List preencheLista() {
		lista = new VisitanteDAO().selectAll();
		return lista;
	}

	@Override
	public List preencheLista(String value) {
		lista = new VisitanteDAO().selectByCpf(value);
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
