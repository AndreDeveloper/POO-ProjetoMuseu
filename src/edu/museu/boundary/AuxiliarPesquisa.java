package edu.museu.boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
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
import javax.swing.table.DefaultTableModel;

import edu.museu.control.AuxiliarPesquisaControl;
import edu.museu.control.Observer;
import edu.museu.control.Subject;
import edu.museu.entity.Local;
import edu.museu.entity.Obra;
import edu.museu.entity.Visitante;

public class AuxiliarPesquisa implements MouseListener, KeyListener, Subject{
	private JLabel lblParam = new JLabel();
	private JTextField txtParam = new JTextField();
	private JTable tabela = new JTable();
	private JPanel painelPrincipal = new JPanel(new BorderLayout());
	private JScrollPane scrollPane = new JScrollPane();
	private JPanel painelParam = new JPanel(new GridLayout(1, 1, 20, 20));
	private JDialog tela = new JDialog();
	private AuxiliarPesquisaControl controller = new AuxiliarPesquisaControl();
	private JTextField txtCampo;
	private JTextField txtCampoid = new JTextField();
	private String param;
	private List<Observer> lista = new ArrayList<Observer>();
	private List<Obra> listaObra;
	private List<Visitante> listaVisitante;
	private List<Local>listaLocais;
	
	
	
	public AuxiliarPesquisa(JTextField txtCampo, String param) {
		super();
		this.txtCampo = txtCampo;
		this.param = param;
	}
	public AuxiliarPesquisa(JTextField txtCampoid, JTextField txtCampo,  String param) {
		super();
		this.txtCampo = txtCampo;
		this.param = param;
		this.txtCampoid = txtCampoid;
		
	}
	public void show(){
		tela.setModal(true);
		tela.toFront();
		lblParam.setText(param);
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
	   	
		txtParam.setToolTipText("digite aqui para pesquisar um autor");    	
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
		
		listaObra = controller.preencheTabela(param);
		listaVisitante = controller.preencheTabela(param);
		listaLocais = controller.preencheTabela(param);
		tabela.setModel(controller);
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
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int linha = tabela.getSelectedRow();
		switch (param){
		case "Autor":
		case "Nome":
			long id =  (Long) tabela.getValueAt(linha, 0);
			String nome = (String) tabela.getValueAt(linha, 1);
			txtCampo.setText(nome);
			txtCampoid.setText("" + id);
			for (Obra o: listaObra){
				if (o.getId()== id){
					notificar(o);
				}
			}
		break;
		case "Visitante":
			String cpf =  (String) tabela.getValueAt(linha, 0);
			txtCampo.setText(cpf);
			for (Visitante v: listaVisitante){
				if (v.getCpf().equals(cpf)){
					notificar(v);
				}
			}
		break;
		case "Local":
			long id1 =  (Long) tabela.getValueAt(linha, 0);
			String Nome =  (String) tabela.getValueAt(linha, 1);
			txtCampoid.setText("" + id1);
			txtCampo.setText(Nome);
			for (Local local: listaLocais){
				if (local.getNomeLocal().equals(Nome)){
					notificar(local);
				}
			}
			break;
			
		}
		tela.dispose();
	}



	@Override
	public void mouseEntered(MouseEvent arg0) {
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
		controller.preencheTabela(param,txtParam.getText());
		tabela.invalidate();
		tabela.revalidate();
	}



	@Override
	public void addObserver(Observer o) {
		lista.add(o);		
	}



	@Override
	public void removeObserver(Observer o) {
		lista.remove(o);
	}
	@Override
	public void notificar(String noticia) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void notificar(Obra obra) {
		for(Observer o: lista){
			o.update(obra);
		}
	}

	@Override
	public void notificar(Visitante visitante) {
		for(Observer o: lista){
			o.update(visitante);
		}
	}

	@Override
	public void notificar(Local local) {
		for(Observer o: lista){
			o.update(local);
		}
		
	}
}
