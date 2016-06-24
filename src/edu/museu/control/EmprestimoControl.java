package edu.museu.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import edu.museu.entity.Emprestimo;
import edu.museu.entity.Obra;
import edu.museu.infrastructure.EmprestimoDAO;
import edu.museu.infrastructure.ObraDAO;

public class EmprestimoControl implements TableModel{
	private List<Emprestimo> listaEmprestimos = new ArrayList<Emprestimo>();
	private EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
	
	
	public List<Emprestimo> getListaEmprestimos() {
		return listaEmprestimos;
	}

	public void setListaEmprestimos(List<Emprestimo> listaEmprestimos) {
		this.listaEmprestimos = listaEmprestimos;
	}

	public EmprestimoControl() {
		super();
		listaEmprestimos = emprestimoDAO.selectAll();
	}
	
	public void salvar(Emprestimo emprestimo) throws SQLException{
		ObraDAO obraDAO = new ObraDAO();
		Obra obra = new Obra();
		obra.setId(emprestimo.getObra_id());
		obra.setDisponivel(false);
		if(emprestimoDAO.insert(emprestimo) > 0){
			obraDAO.updateDisponibilidade(obra);
			JOptionPane.showMessageDialog(null, "Emprestimo realizado com sucesso!", "Emprestado", JOptionPane.INFORMATION_MESSAGE);
			listaEmprestimos = emprestimoDAO.selectAll();
		}else{
			JOptionPane.showMessageDialog(null, "Erro ao realizar emprestimo!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void excluir(Emprestimo emprestimo) throws SQLException{
		if(emprestimoDAO.delete(emprestimo.getEmprestimo_id()) > 0){
			JOptionPane.showMessageDialog(null, "Emprestimo excluido com sucesso!", "Emprestado", JOptionPane.INFORMATION_MESSAGE);
			listaEmprestimos = emprestimoDAO.selectAll();
		}else{
			JOptionPane.showMessageDialog(null, "Erro ao excluir emprestimo!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void alterar(Emprestimo emprestimo) throws SQLException{
		if(emprestimoDAO.update(emprestimo) > 0){
			JOptionPane.showMessageDialog(null, "Emprestimo alterado com sucesso!", "Emprestado", JOptionPane.INFORMATION_MESSAGE);
			listaEmprestimos = emprestimoDAO.selectAll();
		}else{
			JOptionPane.showMessageDialog(null, "Erro ao alterar emprestimo!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void devolver(Emprestimo emprestimo) throws SQLException{
		ObraDAO obraDAO = new ObraDAO();
		Obra obra = new Obra();
		obra.setId(emprestimo.getObra_id());
		obra.setDisponivel(true);
		if(emprestimoDAO.updateDevolvido(emprestimo) > 0){
			obraDAO.updateDisponibilidade(obra);
			JOptionPane.showMessageDialog(null, "Obra devolvida!", "Emprestado", JOptionPane.INFORMATION_MESSAGE);
			listaEmprestimos = emprestimoDAO.selectAll();
		}else{
			JOptionPane.showMessageDialog(null, "Erro ao devolver obra!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0: return Long.class;
		case 1: return String.class;	
		case 2: return String.class;	
		case 3: return String.class;	
		case 4: return String.class;	
		case 5: return String.class;	
		}
		return null;
	}

	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0: return "ID";
		case 1: return "Nome da obra";	
		case 2: return "Locatario";	
		case 3: return "Data saida";	
		case 4: return "Previsão devolução";	
		case 5: return "Status";		
		}
		return null;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listaEmprestimos.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Emprestimo emprestimo = listaEmprestimos.get(rowIndex);
		switch (columnIndex) {
		case 0: return emprestimo.getEmprestimo_id();
		case 1: return emprestimo.getNomedaObra();	
		case 2: return emprestimo.getLocatario();	
		case 3: return emprestimo.getDataSaida().toString();	
		case 4: return emprestimo.getDataSaida().toString();	
		case 5: return emprestimo.getDevolvido();		
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
