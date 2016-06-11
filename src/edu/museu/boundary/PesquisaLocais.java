package edu.museu.boundary;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.table.TableModel;

import edu.museu.control.PesquisaControl;
import edu.museu.control.PesquisaLocaisControl;

public class PesquisaLocais extends FormPesquisa{
	private PesquisaControl control = new PesquisaLocaisControl();
	@Override
	public void mouseClicked(MouseEvent arg0) {
		int linha = tabela.getSelectedRow();
		notificar(control.getLista().get(linha));
		tela.dispose();		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
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
		return "Local";
	}

}
