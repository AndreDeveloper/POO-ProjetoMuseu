package edu.museu.control;

import edu.museu.entity.Visitante;
import edu.museu.infrastructure.VisitanteDAO;

public class VisitanteControl {
	
	public long salvar(Visitante visitante) {
		VisitanteDAO dao = new VisitanteDAO();
		return dao.insert(visitante);
	}
	public long alterar(Visitante visitante) {
		VisitanteDAO dao = new VisitanteDAO();
		return dao.update(visitante);
	}
	public long excluir(Visitante visitante) {
		VisitanteDAO dao = new VisitanteDAO();
		return dao.deleteByCpf(visitante.getCpf());
	}
}
