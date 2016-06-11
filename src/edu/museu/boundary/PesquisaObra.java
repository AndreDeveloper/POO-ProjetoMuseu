package edu.museu.boundary;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import edu.museu.control.Observer;
import edu.museu.control.PesquisaControl;
import edu.museu.control.PesquisaObraControl;
import edu.museu.entity.Exposicao;
import edu.museu.entity.Local;
import edu.museu.entity.Obra;
import edu.museu.entity.Visitante;

public class PesquisaObra extends FormPesquisa{
	private PesquisaControl control = new PesquisaObraControl();
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int linha = super.tabela.getSelectedRow();
		notificar(control.getLista().get(linha));
		tela.dispose();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		control.preencheLista(txtParam.getText());
		tabela.invalidate();
		tabela.revalidate();
	}

	@Override
	protected TableModel getTableModel() {
		return control;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "Obra";
	}
}
