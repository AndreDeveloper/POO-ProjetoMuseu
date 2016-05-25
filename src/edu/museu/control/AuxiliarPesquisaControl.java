package edu.museu.control;

import java.sql.Date;
import java.util.List;

import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import edu.museu.entity.Local;
import edu.museu.entity.Obra;
import edu.museu.entity.Visitante;
import edu.museu.infrastructure.LocalDAO;
import edu.museu.infrastructure.ObraDAO;
import edu.museu.infrastructure.VisitanteDAO;

public class AuxiliarPesquisaControl implements TableModel{
	private List<Obra> listaObras;
	private List<Visitante> listaVisitante;
	private List<Local> listaLocais;
	private String param = "";
	private ObraDAO obraDAO = new ObraDAO();
	private VisitanteDAO visitanteDAO = new VisitanteDAO();
	private LocalDAO localDAO = new LocalDAO();
	
	public List preencheTabela(String param){
		this.param = param;
		if(param.equals("Autor") || param.equals("Nome")){
			listaObras = obraDAO.selectAll();
			return listaObras;
		} 
		else if (param.equals("Visitante") ){
			listaVisitante = visitanteDAO.selectAll();
			return listaVisitante;
		}
		else if (param.equals("Local") ){
			listaLocais = localDAO.selectAll();
			return listaLocais;
		}
		return null;
	}
	
	public List preencheTabela(String param, String value){
		this.param = param;
		if(param.equals("Autor")){
			listaObras = obraDAO.selectByAutor(value);
			return listaObras;
		} else if (param.equals("Nome") ){
			listaObras = obraDAO.selectByName(value);
			return listaObras;
		}  else if (param.equals("Visitante") ){
			listaVisitante = visitanteDAO.selectByCpf(value);
			return listaVisitante; 
		}  else if (param.equals("Local") ){
			listaLocais = localDAO.selectByName(value);
			return listaLocais;
		} 
		return null;
	}
	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Class<?> getColumnClass(int arg0) {
		switch (arg0){
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
		if(param.equals("Local")) return 4;
		return 2;
	}
	@Override
	public String getColumnName(int arg0) {
		String [] nome = {"",""};
		if(param.equals("Autor") || param.equals("Nome")){
			String[] aux = {"Id","Nome"};
			nome = aux;
		}else if(param.equals("Visitante")){
			String[] aux = {"CPF","Nacionalidade"};
			nome = aux;
		}
		else if(param.equals("Local")){
			String[] aux = {"id","Nome","Responsavel", "Telefone"};
			nome = aux;
	}
		return nome[arg0];
	}
	@Override
	public int getRowCount() {
		if(param.equals("Autor") || param.equals("Nome")){
			return listaObras.size();
		}else if(param.equals("Visitante")){
			return listaVisitante.size();
		}
		else if(param.equals("Local")){
			return listaLocais.size();
		}
		return 0;
	}
	@Override
	public Object getValueAt(int arg0, int arg1) {
		switch(arg1){
		case 0: 
			if(param.equals("Autor") || param.equals("Nome")){
				Obra obra = listaObras.get(arg0);
				return obra.getId();
			}else if(param.equals("Visitante")){
				Visitante visitante = listaVisitante.get(arg0);
				return visitante.getCpf();
			}
			else if(param.equals("Local")){
				Local local = listaLocais.get(arg0);
				return local.getId();
			}
			break;
		case 1: 
			if(param.equals("Autor") || param.equals("Nome")){
				Obra obra = listaObras.get(arg0);
				return obra.getNomeObra();
			}else if(param.equals("Visitante")){
				Visitante visitante = listaVisitante.get(arg0);
				return visitante.getNacionalidade();
			}else if(param.equals("Local")){
				Local local = listaLocais.get(arg0);
				return local.getNomeLocal();
			}
			break;
		case 2: 
			if(param.equals("Local")){
				Local local = listaLocais.get(arg0);
				return local.getResponsavel();
			}
			break;		
		case 3: 
			if(param.equals("Local")){
				Local local = listaLocais.get(arg0);
				return local.getTelefone();
			}
			break;
	}
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
}
