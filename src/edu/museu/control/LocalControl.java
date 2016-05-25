package edu.museu.control;

import javax.swing.JOptionPane;

import edu.museu.entity.Local;
import edu.museu.infrastructure.LocalDAO;

public class LocalControl {
	public void salvar(Local local){
		LocalDAO dao = new LocalDAO();
		if(dao.insert(local)<1){
			JOptionPane.showMessageDialog(null, "erro ao inserir local!", "erro", JOptionPane.ERROR_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(null, "Local inserido com sucesso!", "erro", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public void Alterar(Local local){
		LocalDAO dao = new LocalDAO();
		if(dao.update(local)<1){
			JOptionPane.showMessageDialog(null, "erro ao atualizar local!", "erro", JOptionPane.ERROR_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(null, "Local atualizado com sucesso!", "erro", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public void excluir(Local local){
		LocalDAO dao = new LocalDAO();
		if(dao.delete(local.getId())<1){
			JOptionPane.showMessageDialog(null, "erro ao excluir local!", "erro", JOptionPane.ERROR_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(null, "Local excluido com sucesso!", "erro", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
