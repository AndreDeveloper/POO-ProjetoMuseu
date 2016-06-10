package edu.museu.control;

import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.table.TableModel;

public interface PesquisaControler extends TableModel{
	public List preencheLista();
	public List preencheLista(String value);
	public List getLista();
	public void setLista(List lista);
}
