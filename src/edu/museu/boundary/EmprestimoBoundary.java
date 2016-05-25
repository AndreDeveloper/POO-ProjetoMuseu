package edu.museu.boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.toedter.calendar.JDateChooser;

import edu.museu.control.ComponenteFormater;
import edu.museu.control.EmprestimoControl;
import edu.museu.entity.Emprestimo;


public class EmprestimoBoundary implements ActionListener, ListSelectionListener, MouseListener{
	private JLabel lblObra = new JLabel("Obra");
	private JLabel lblNomeObra = new JLabel("Nome da obra");
	private JLabel lblDestinatario = new JLabel("Destinatário");
	private JLabel lblDataSaida = new JLabel("Data de saida");
	private JLabel lblDevolucao = new JLabel("Previsão de devolução");
	private JLabel lbltitulo = new JLabel("Relação de Obras Emprestadas");
	
	private JTextField txtObra = new JTextField(3);
	private JTextField txtNomeObra = new JTextField(35);
	private JTextField txtDestinatario = new JTextField(3);
	private JTextField txtNomeDestinatario = new JTextField(35);
	private JDateChooser dcSaida = new JDateChooser();
	private JDateChooser dcDevolucao = new JDateChooser();
	
	private JButton btnSalvar = new JButton("Emprestar");
	private JButton btnAlterar = new JButton("Alterar");
	private JButton btnExcluir = new JButton("Excluir");
	private JButton btnDevolver = new JButton("Devolver");
	private JButton btnVoltar = new JButton("Voltar");
	private JButton btnPesquisarObra = new JButton();
	private JButton btnPesquisarLocal = new JButton();
	
	private JTable tabela = new JTable();
	private JScrollPane scrollPane = new JScrollPane();
	private Emprestimo emprestimo = new Emprestimo();
	
	private EmprestimoControl control = new EmprestimoControl();

	private JPanel painelPrincipal = new JPanel(new GridLayout(1, 2));

	public EmprestimoBoundary() {
		super();
		create();
	}

	public JPanel getPainelPrincipal() {
		return painelPrincipal;
	}

	public void setPainelPrincipal(JPanel painelPrincipal) {
		this.painelPrincipal = painelPrincipal;
	}

	public void create(){
		JPanel painelLeft = new JPanel(new BorderLayout());
		JPanel painelRigh = new JPanel(new BorderLayout());
		JPanel painelCentro = new JPanel(new GridLayout(5, 1));
		JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER,25,15));
		
		JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel linha3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel linha4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		linha1.add(lblObra);
		linha1.add(txtObra);
		linha1.add(txtNomeObra);
		linha1.add(btnPesquisarObra);
		
		
		linha2.add(lblDestinatario);
		linha2.add(txtDestinatario);
		linha2.add(txtNomeDestinatario);
		linha2.add(btnPesquisarLocal);
		
		linha3.add(lblDataSaida);
		linha3.add(dcSaida);
		linha3.add(lblDevolucao);
		linha3.add(dcDevolucao);
		
		painelCentro.add(linha1);
		painelCentro.add(linha2);
		painelCentro.add(linha3);
		painelCentro.add(linha4);
		
		painelBotoes.add(btnSalvar);
		painelBotoes.add(btnAlterar);
		painelBotoes.add(btnExcluir);
		painelBotoes.add(btnDevolver);
		painelBotoes.add(btnVoltar);
		
		scrollPane.setViewportView(tabela);
		tabela.getSelectionModel().addListSelectionListener(this);
		
		ComponenteFormater.formataJlable(lblDataSaida, Color.DARK_GRAY, 20);
		ComponenteFormater.formataJlable(lblDestinatario, Color.DARK_GRAY, 20);
		ComponenteFormater.formataJlable(lblDevolucao, Color.DARK_GRAY, 20);
		ComponenteFormater.formataJlable(lblNomeObra, Color.DARK_GRAY, 20);
		ComponenteFormater.formataJlable(lblObra, Color.DARK_GRAY, 20);
		ComponenteFormater.formataJlable(lbltitulo, Color.BLUE, 26);
		lbltitulo.setHorizontalAlignment(JLabel.CENTER);
		
		ComponenteFormater.formataJButton(btnAlterar);
		ComponenteFormater.formataJButton(btnDevolver);
		ComponenteFormater.formataJButton(btnExcluir);
		ComponenteFormater.formataJButton(btnPesquisarLocal);
		ComponenteFormater.formataJButton(btnPesquisarObra);
		ComponenteFormater.formataJButton(btnSalvar);
		ComponenteFormater.formataJButton(btnVoltar);
		
		ComponenteFormater.formataJPanel(painelBotoes);
		ComponenteFormater.formataJPanel(painelCentro);
		ComponenteFormater.formataJPanel(painelLeft);
		ComponenteFormater.formataJPanel(painelRigh);
		ComponenteFormater.formataJPanel(painelPrincipal);
		ComponenteFormater.formataJPanel(linha1);
		ComponenteFormater.formataJPanel(linha2);
		ComponenteFormater.formataJPanel(linha3);
		ComponenteFormater.formataJPanel(linha4);
		
		ComponenteFormater.formataJScrollPane(scrollPane);
		ComponenteFormater.formataJTable(tabela);
		tabela.setModel(control);
		
		lblDataSaida.setPreferredSize(lblNomeObra.getPreferredSize());
		lblDestinatario.setPreferredSize(lblNomeObra.getPreferredSize());
		lblObra.setPreferredSize(lblNomeObra.getPreferredSize());
		dcDevolucao.setPreferredSize(lblNomeObra.getPreferredSize());
		dcSaida.setPreferredSize(lblNomeObra.getPreferredSize());
		
		btnSalvar.setIcon(
				new ImageIcon(VisitanteBoundary.class.getResource("/edu/museu/resource/save.png"))
				);
		btnAlterar.setIcon(
				new ImageIcon(VisitanteBoundary.class.getResource("/edu/museu/resource/edit.png"))
				);
		btnExcluir.setIcon(
				new ImageIcon(VisitanteBoundary.class.getResource("/edu/museu/resource/delete.png"))
				);
		btnVoltar.setIcon(
				new ImageIcon(VisitanteBoundary.class.getResource("/edu/museu/resource/back.png"))
				);
		btnPesquisarLocal.setIcon(
				new ImageIcon(VisitanteBoundary.class.getResource("/edu/museu/resource/lupa_16.png"))
				);
		btnPesquisarObra.setIcon(
				new ImageIcon(VisitanteBoundary.class.getResource("/edu/museu/resource/lupa_16.png"))
				);
		
		btnAlterar.addActionListener(this);
		btnDevolver.addActionListener(this);
		btnExcluir.addActionListener(this);
		btnPesquisarLocal.addActionListener(this);
		btnPesquisarObra.addActionListener(this);
		btnSalvar.addActionListener(this);
		btnVoltar.addActionListener(this);
		
		painelLeft.add(painelCentro, BorderLayout.CENTER);
		painelLeft.add(painelBotoes, BorderLayout.SOUTH);
		
		painelRigh.add(lbltitulo,BorderLayout.NORTH);
		painelRigh.add(scrollPane,BorderLayout.CENTER);
		
		painelPrincipal.add(painelLeft);
		painelPrincipal.add(painelRigh);
		
		telaDefault();
		txtDestinatario.setEditable(false);
		txtNomeDestinatario.setEditable(false);
		txtNomeObra.setEditable(false);
		txtObra.setEditable(false);
		txtDestinatario.setCursor(new Cursor(Cursor.HAND_CURSOR));
		txtNomeDestinatario.setCursor(new Cursor(Cursor.HAND_CURSOR));
		txtNomeObra.setCursor(new Cursor(Cursor.HAND_CURSOR));
		txtObra.setCursor(new Cursor(Cursor.HAND_CURSOR));
		txtDestinatario.addMouseListener(this);
		txtNomeDestinatario.addMouseListener(this);
		txtNomeObra.addMouseListener(this);
		txtObra.addMouseListener(this);
	}
	
	private Emprestimo formToEmprestimo(){
		if(validaCampos()){
			Emprestimo emprestimo = this.emprestimo;
			
			emprestimo.setObra_id(Long.parseLong(txtObra.getText()));
			emprestimo.setNomedaObra(txtNomeObra.getText());
			emprestimo.setLocal_emprestimo_id(Long.parseLong(txtDestinatario.getText()));
			emprestimo.setLocatario(txtNomeDestinatario.getText());
			emprestimo.setDataSaida(dcSaida.getDate());
			emprestimo.setPrevisaoDevolucao(dcDevolucao.getDate());
			emprestimo.setDevolvido("Emprestado");
			emprestimo.setData(new Date());
			return emprestimo;
		}
		return null;
	}
	
	public void EmprestimoToForm(Emprestimo emprestimo){
		this.emprestimo = emprestimo;
		txtDestinatario.setText("" + emprestimo.getLocal_emprestimo_id());
		txtNomeDestinatario.setText(emprestimo.getLocatario());
		txtObra.setText("" + emprestimo.getObra_id());
		txtNomeObra.setText(emprestimo.getNomedaObra());
		dcSaida.setDate(emprestimo.getDataSaida());
		dcDevolucao.setDate(emprestimo.getPrevisaoDevolucao());
	}
	private void updateTable(){
		tabela.invalidate();
		tabela.revalidate();
	}
	
	private void telaDefault(){
		btnSalvar.setEnabled(true);
		btnDevolver.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnAlterar.setEnabled(false);
		btnVoltar.setEnabled(false);
		limparCampos();
	}
	private void telaAlterar(){
		btnSalvar.setEnabled(false);
		btnDevolver.setEnabled(true);
		btnExcluir.setEnabled(true);
		btnAlterar.setEnabled(true);
		btnVoltar.setEnabled(true);		
	}
	private void limparCampos(){
		txtDestinatario.setText("");
		txtNomeDestinatario.setText("");
		txtNomeObra.setText("");
		txtObra.setText("");
		dcDevolucao.setDate(null);
		dcSaida.setDate(null);
	}
	
	private boolean validaCampos(){
		if(
				txtDestinatario.getText().length() <= 0 ||
				txtNomeDestinatario.getText().length() <= 0 ||
				txtNomeObra.getText().length() <= 0 ||
				txtObra.getText().length() <= 0 ||
				dcDevolucao.getDate() == null ||
				dcSaida.getDate() == null 
				){
			JOptionPane.showMessageDialog(painelPrincipal, "preencha corretamente todos os campos!", "erro de preenchimento", JOptionPane.ERROR_MESSAGE);
			return false;
		}else if(dcDevolucao.getDate().getTime() < dcSaida.getDate().getTime()){
			JOptionPane.showMessageDialog(painelPrincipal, "a data prevista para devolução não pode ser menor que a data de saida", "erro de preenchimento", JOptionPane.ERROR_MESSAGE);
			return false;			
		}
		return true;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnPesquisarObra){
			AuxiliarPesquisa a  = new AuxiliarPesquisa(txtObra, txtNomeObra, "Autor");
			a.show();
		}else if(e.getSource() == btnPesquisarLocal){
			AuxiliarPesquisa a  = new AuxiliarPesquisa(txtDestinatario,txtNomeDestinatario, "Local");
			a.show();
		}else if(e.getSource() == btnSalvar && validaCampos()){
			try {
				control.salvar(formToEmprestimo());
				updateTable();
				telaDefault();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}else if(e.getSource() == btnExcluir){
			try {
				control.excluir(formToEmprestimo());
				updateTable();
				telaDefault();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}else if(e.getSource() == btnAlterar && validaCampos()){
			try {
				control.alterar(formToEmprestimo());
				updateTable();
				telaAlterar();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}else if(e.getSource() == btnDevolver){
			try {
				control.devolver(formToEmprestimo());
				updateTable();
				telaDefault();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}else if(e.getSource() == btnVoltar){
			telaDefault();
		}
		
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		int linha = tabela.getSelectedRow();
		Emprestimo emprestimo = control.getListaEmprestimos().get(linha);
		EmprestimoToForm(emprestimo);
		telaAlterar();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource() == txtObra || arg0.getSource() == txtNomeObra){
			AuxiliarPesquisa a  = new AuxiliarPesquisa(txtObra, txtNomeObra, "Autor");
			a.show();
		}else if(arg0.getSource() == txtDestinatario || arg0.getSource() == txtNomeDestinatario){
			AuxiliarPesquisa a  = new AuxiliarPesquisa(txtDestinatario,txtNomeDestinatario, "Local");
			a.show();
		}
		
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
}
