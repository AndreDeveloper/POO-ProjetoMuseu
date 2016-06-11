package edu.museu.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import edu.museu.entity.Exposicao;
import edu.museu.entity.ObraExposicaoAlteracao;
import edu.museu.entity.ObraExposicao;
import edu.museu.entity.Obra;
import edu.museu.infrastructure.ExposicaoDAO;
import edu.museu.infrastructure.ObraExposicaoDAO;
import edu.museu.infrastructure.ObraDAO;

public class ExposicaoControl implements TableModel {
	private List<Obra> listaExposicao = new ArrayList<Obra>();

	public void salvar(Exposicao exposicao, List<Obra> lista) {
		ExposicaoDAO dao = new ExposicaoDAO();
		ObraExposicaoDAO intDAO = new ObraExposicaoDAO();
		List<ObraExposicao> listaIntObra = new ArrayList<ObraExposicao>();
		long idGerado;
		long qtdadeObras;
		try {
			idGerado = dao.insert(exposicao);
			for (Obra o : lista) {
				ObraExposicao e = new ObraExposicao();
				e.setExposicao_id(idGerado);
				e.setObra_id(o.getId());
				listaIntObra.add(e);
			}
			qtdadeObras = intDAO.insert(listaIntObra);
			JOptionPane.showMessageDialog(null, "Exposição cadastrada com sucesso!", "Aviso",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void alterar(Exposicao exposicao, List<Obra> lista) throws SQLException {
		List<ObraExposicao> listaBanco = new ObraExposicaoDAO().selectById(exposicao.getId());
		List<ObraExposicaoAlteracao> listaAlteracao = new ArrayList<ObraExposicaoAlteracao>();
		List<ObraExposicao> listaTabela = new ArrayList<ObraExposicao>();
		ObraExposicaoDAO intDAO = new ObraExposicaoDAO();
		ExposicaoDAO dao = new ExposicaoDAO();
		for (Obra obra : lista) {
			ObraExposicao entity = new ObraExposicao();
			entity.setExposicao_id(exposicao.getId());
			entity.setObra_id(obra.getId());
			listaTabela.add(entity);
		}

		for (ObraExposicao i : listaTabela) {
			boolean thereIs = false;
			for (ObraExposicao x : listaBanco) {
				if (i.getExposicao_id() == x.getExposicao_id() && i.getObra_id() == x.getObra_id()) {
					thereIs = true;
				}
			}
			if (!thereIs) {
				ObraExposicaoAlteracao entity = new ObraExposicaoAlteracao();
				entity.setExposicao_id(exposicao.getId());
				entity.setObra_id(i.getObra_id());
				entity.setAcao(true);
				listaAlteracao.add(entity);
			}
		}

		for (ObraExposicao i : listaBanco) {
			boolean thereIs = false;
			for (ObraExposicao y : listaTabela) {
				if (i.getExposicao_id() == y.getExposicao_id() && i.getObra_id() == y.getObra_id()) {
					thereIs = true;
				}
			}
			if (!thereIs) {
				ObraExposicaoAlteracao entity = new ObraExposicaoAlteracao();
				entity.setExposicao_id(exposicao.getId());
				entity.setObra_id(i.getObra_id());
				entity.setAcao(false);
				listaAlteracao.add(entity);
			}
		}

		dao.update(exposicao);

		for (ObraExposicaoAlteracao entity : listaAlteracao) {
			if (entity.isAcao()) {
				intDAO.insertByRow(entity);
			} else {
				intDAO.deleteByRow(entity.getExposicao_id(), entity.getObra_id());
			}
		}
		JOptionPane.showMessageDialog(null, "Exposição alterada com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
	}
	public void deletar(Exposicao exposicao){
		new ObraExposicaoDAO().delete(exposicao.getId());
		new ExposicaoDAO().delete(exposicao.getId());
		JOptionPane.showMessageDialog(null, "Exposição Excluida com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
	}
	public void pesquisaObras(long id) {
		ObraExposicaoDAO dao = new ObraExposicaoDAO();
		List<ObraExposicao> lista = dao.selectById(id);
		List<Obra> listaObra = new ArrayList<Obra>();
		ObraDAO obraDAO = new ObraDAO();
		for (ObraExposicao ie : lista) {
			listaObra.add(obraDAO.selectById(ie.getObra_id()));
		}
		this.setListaExposicao(listaObra);
	}

	public List<Obra> getListaExposicao() {
		return listaExposicao;
	}

	public void setListaExposicao(List<Obra> listaExposicao) {
		this.listaExposicao = listaExposicao;
	}

	public void add(Obra obra) {
		boolean valida = true;
		for (Obra o : listaExposicao) {
			if (o.getId() == obra.getId()) {
				valida = false;
			}
		}
		if (valida) {
			listaExposicao.add(obra);
		} else {
			JOptionPane.showMessageDialog(null, "essa obra já foi adicionada!", "erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void remove(Obra obra) {
		boolean valida = false;
		for (Obra o : listaExposicao) {
			if (o.getId() == obra.getId()) {
				valida = true;
			}
		}
		if (valida) {
			listaExposicao.remove(obra);
		}
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
		return listaExposicao.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Obra obra = listaExposicao.get(rowIndex);
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
