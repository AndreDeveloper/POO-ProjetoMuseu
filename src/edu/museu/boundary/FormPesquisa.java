package edu.museu.boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

import edu.museu.control.Observer;
import edu.museu.control.Subject;

public abstract class FormPesquisa implements MouseListener, KeyListener, Subject {
	private JLabel lblParam = new JLabel();
	protected JTextField txtParam = new JTextField();
	protected JTable tabela = new JTable();
	private JPanel painelPrincipal = new JPanel(new BorderLayout());
	private JScrollPane scrollPane = new JScrollPane();
	private JPanel painelParam = new JPanel(new GridLayout(1, 1, 20, 20));
	protected JDialog tela = new JDialog();
	private List<Observer> listaObserver = new ArrayList<Observer>();

	
	public void show(){
		tela.setModal(true);
		tela.toFront();
		lblParam.setText(getTableName());
		painelParam.add(lblParam);
		painelParam.add(txtParam);
		
		tela.setForeground(Color.BLACK);
	   	tela.setBackground(Color.WHITE);
	   	tela.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		painelParam.setForeground(Color.BLACK);
	   	painelParam.setBackground(Color.WHITE);
	   	painelParam.setFont(new Font("Tahoma", Font.BOLD, 14));
	   	painelParam.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
		painelPrincipal.setForeground(Color.BLACK);
	   	painelPrincipal.setBackground(Color.WHITE);
	   	painelPrincipal.setFont(new Font("Tahoma", Font.BOLD, 14));
	   	painelPrincipal.setBorder(BorderFactory.createEmptyBorder());	   	
	   	
		scrollPane.setForeground(Color.BLACK);
	   	scrollPane.setBackground(Color.WHITE);
	   	scrollPane.setFont(new Font("Tahoma", Font.BOLD, 14));
	   	scrollPane.setBorder(BorderFactory.createEmptyBorder());
	   	
		txtParam.setToolTipText("digite aqui para pesquisar");    	
	   	txtParam.setForeground(Color.BLACK);
	   	txtParam.setBackground(Color.WHITE);
	   	txtParam.setFont(new Font("Tahoma", Font.BOLD, 14));
	   	txtParam.setCursor(new Cursor(Cursor.HAND_CURSOR));
	   	txtParam.addKeyListener(this);
	   	    	
	   	lblParam.setForeground(Color.BLACK);
	   	lblParam.setBackground(Color.WHITE);
	   	lblParam.setFont(new Font("Tahoma", Font.BOLD, 14));
	   	lblParam.setCursor(new Cursor(Cursor.HAND_CURSOR));
	   	lblParam.setBorder(BorderFactory.createEmptyBorder());
	   	lblParam.setHorizontalAlignment(JLabel.RIGHT);
		
	   	tabela.setForeground(Color.BLACK);
	   	tabela.setBackground(Color.WHITE);
	   	tabela.setFont(new Font("Tahoma", Font.BOLD, 14));
	   	tabela.setCursor(new Cursor(Cursor.HAND_CURSOR));
	   	tabela.setBorder(BorderFactory.createEmptyBorder());
	   	tabela.setGridColor(Color.white);
	   	
	   	scrollPane.setViewportView(tabela);
		
		painelPrincipal.add(painelParam, BorderLayout.NORTH);
		painelPrincipal.add(scrollPane, BorderLayout.CENTER);
		
		tabela.addMouseListener(this);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tabela.setModel(this.getTableModel());
		tabela.invalidate();
		tabela.revalidate();
		
		tela.setContentPane(painelPrincipal);
		tela.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		tela.setSize(800, 400);
		tela.setLocationRelativeTo(null);
		tela.setTitle("Pesquisa rapida");
		tela.setResizable(false);
		tela.setVisible(true);
	}
	@Override
	public void addObserver(Observer o) {
		listaObserver.add(o);
		
	}
	@Override
	public void removeObserver(Observer o) {
		listaObserver.remove(o);
		
	}
	@Override
	public void notificar(Object object) {
		for(Observer o: listaObserver){
			o.update(object);
		}
		
	}
	protected abstract TableModel getTableModel();
	protected abstract String getTableName();
}
