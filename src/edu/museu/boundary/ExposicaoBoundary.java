package edu.museu.boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.toedter.calendar.JDateChooser;

import edu.museu.control.AuxiliarPesquisaControl;
import edu.museu.control.ComponenteFormater;
import edu.museu.control.ExposicaoControl;
import edu.museu.control.ObrasToExposicao;
import edu.museu.control.Observer;
import edu.museu.entity.Exposicao;
import edu.museu.entity.Local;
import edu.museu.entity.Obra;
import edu.museu.entity.Visitante;

public class ExposicaoBoundary implements ActionListener, Observer{
	private JLabel lblnome = new JLabel("Nome");
	private JLabel lblDataInicio = new JLabel("Data de Inicio");
	private JLabel lblDatafim = new JLabel("Data de Termino");
	private JLabel lblIngresso = new JLabel("Valor do Ingresso");
	private JLabel lblObrasDisponiveis = new JLabel("Obras Disponiveis");
	private JLabel lblExposicao = new JLabel("Exposição");
	
	private JTextField txtNome = new JTextField(60);
	private JTextField txtIngresso = new JTextField(20);
	
	private JDateChooser dcDataInicio = new JDateChooser();
	private JDateChooser dcDataFim = new JDateChooser();
	
	private JButton btnSalvar = new JButton("Salvar");
	private JButton btnAlterar = new JButton("Alterar");
	private JButton btnExcluir = new JButton("Excluir");
	private JButton btnVoltar = new JButton("Voltar");
	private JButton btnPesquisar = new JButton("");
	private JButton btnAdiciona = new JButton(">>");
	private JButton btnRemove = new JButton("<<");
	
	private JTable tabelaObras = new JTable();
	private JTable tabelaExposicao = new JTable();
	
	private JPanel painelPrincipal = new JPanel(new BorderLayout());
	
	private ObrasToExposicao toExposicao = new ObrasToExposicao();
	private ExposicaoControl control = new ExposicaoControl();
	
	public JPanel getPainelPrincipal() {
		return painelPrincipal;
	}

	public void setPainelPrincipal(JPanel painelPrincipal) {
		this.painelPrincipal = painelPrincipal;
	}
	

	public ExposicaoBoundary() {
		create();
	}

	private void create(){
		JPanel painelDados = new JPanel(new GridLayout(3, 1));
		JPanel painelCentro = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 5));
		JPanel painelBotoes2 = new JPanel(new GridLayout(2, 1));
		JPanel painelTabelaObras = new JPanel(new BorderLayout());
		JPanel painelTabelaExpo = new JPanel(new BorderLayout());
		JScrollPane panObras = new JScrollPane();
		JScrollPane panExpo = new JScrollPane();
		
		JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 2));
		JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 2));
		JPanel linha3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 2));
		
		linha1.add(lblnome);
		linha1.add(txtNome);
		linha1.add(btnPesquisar);
		
		linha2.add(lblDataInicio);
		linha2.add(dcDataInicio);
		linha2.add(lblDatafim);
		linha2.add(dcDataFim);
		
		linha3.add(lblIngresso);
		linha3.add(txtIngresso);
		
		panObras.setPreferredSize(new Dimension(550, 300));
		panObras.setViewportView(tabelaObras);
		panExpo.setPreferredSize(new Dimension(550, 300));
		panExpo.setViewportView(tabelaExposicao);
		painelTabelaExpo.add(panExpo, BorderLayout.CENTER);
		painelTabelaExpo.add(lblExposicao, BorderLayout.NORTH);
		
		painelTabelaObras.add(panObras, BorderLayout.CENTER);
		painelTabelaObras.add(lblObrasDisponiveis, BorderLayout.NORTH);
		
		JPanel linha21 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 2));
		JPanel linha22 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 2));
		
		linha21.add(btnAdiciona);
		linha22.add(btnRemove);
		painelBotoes2.add(linha21);
		painelBotoes2.add(linha22);
		
		painelBotoes.add(btnSalvar);
		painelBotoes.add(btnAlterar);
		painelBotoes.add(btnExcluir);
		painelBotoes.add(btnVoltar);
		
		painelCentro.add(painelTabelaObras);
		painelCentro.add(painelBotoes2);
		painelCentro.add(painelTabelaExpo);
		
		tabelaObras.setModel(toExposicao);
		tabelaExposicao.setModel(control);
		tabelaObras.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaExposicao.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//formatando paineis
		ComponenteFormater.formataJPanel(painelDados);
		ComponenteFormater.formataJPanel(painelPrincipal);
		ComponenteFormater.formataJPanel(linha1);
		ComponenteFormater.formataJPanel(linha21);
		ComponenteFormater.formataJPanel(linha2);
		ComponenteFormater.formataJPanel(linha22);
		ComponenteFormater.formataJPanel(linha3);
		ComponenteFormater.formataJPanel(painelBotoes2);
		ComponenteFormater.formataJPanel(painelCentro);
		ComponenteFormater.formataJPanel(painelTabelaExpo);
		ComponenteFormater.formataJPanel(painelTabelaObras);
		ComponenteFormater.formataJPanel(painelBotoes);
		
		//formatando jlable
		ComponenteFormater.formataJlable(lblDataInicio, Color.DARK_GRAY, 22);
		ComponenteFormater.formataJlable(lblDatafim, Color.DARK_GRAY, 22);
		ComponenteFormater.formataJlable(lblExposicao, Color.GREEN, 22);
		lblExposicao.setHorizontalAlignment(JLabel.CENTER);
		ComponenteFormater.formataJlable(lblIngresso, Color.DARK_GRAY, 22);
		ComponenteFormater.formataJlable(lblnome, Color.DARK_GRAY, 22);
		ComponenteFormater.formataJlable(lblObrasDisponiveis, Color.GREEN, 22);
		lblObrasDisponiveis.setHorizontalAlignment(JLabel.CENTER);
		
		//formatando botoes
		ComponenteFormater.formataJButton(btnAdiciona);
		ComponenteFormater.formataJButton(btnAlterar);
		ComponenteFormater.formataJButton(btnExcluir);
		ComponenteFormater.formataJButton(btnPesquisar);
		ComponenteFormater.formataJButton(btnRemove);
		ComponenteFormater.formataJButton(btnSalvar);
		ComponenteFormater.formataJButton(btnVoltar);
		
		// formatndo scrollpane
		ComponenteFormater.formataJScrollPane(panExpo);
		ComponenteFormater.formataJScrollPane(panObras);
		
		btnPesquisar.setIcon(
				new ImageIcon(VisitanteBoundary.class.getResource("/edu/museu/resource/lupa_16.png"))
				);
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
		
		btnAdiciona.addActionListener(this);
		btnAlterar.addActionListener(this);
		btnExcluir.addActionListener(this);
		btnPesquisar.addActionListener(this);
		btnRemove.addActionListener(this);
		btnSalvar.addActionListener(this);
		btnVoltar.addActionListener(this);
		
		lblnome.setPreferredSize(lblIngresso.getPreferredSize());
		lblDataInicio.setPreferredSize(lblIngresso.getPreferredSize());
		dcDataFim.setPreferredSize(txtIngresso.getPreferredSize());
		dcDataInicio.setPreferredSize(txtIngresso.getPreferredSize());
	
		painelDados.add(linha1);
		painelDados.add(linha2);
		painelDados.add(linha3);
		
		painelPrincipal.add(painelDados,BorderLayout.NORTH);
		painelPrincipal.add(painelCentro,BorderLayout.CENTER);
		painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

	}
	public Exposicao formToExposicao(){
		Exposicao exposicao = new Exposicao();
		exposicao.setValor(Double.parseDouble(txtIngresso.getText()));
		exposicao.setNome(txtNome.getText());
		exposicao.setDataInicio(dcDataInicio.getDate());
		exposicao.setDataFim(dcDataFim.getDate());
		
		return exposicao;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAdiciona && tabelaObras.getSelectedRow() > 0){
			control.add(toExposicao.getLista().get(tabelaObras.getSelectedRow()));
			tabelaExposicao.invalidate();
			tabelaExposicao.revalidate();
		}else if(e.getSource() == btnRemove && tabelaExposicao.getSelectedRow() > -1){
			control.remove(control.getListaExposicao().get(tabelaExposicao.getSelectedRow()));
			tabelaExposicao.clearSelection();
			tabelaExposicao.invalidate();
			tabelaExposicao.revalidate();
		}else if(e.getSource() == btnSalvar){
			control.salvar(formToExposicao(), control.getListaExposicao());
		}else if(e.getSource() == btnPesquisar){
			AuxiliarPesquisa pesquisa = new AuxiliarPesquisa(txtNome, "Exposicao");
			pesquisa.addObserver(this);
			pesquisa.show();
		}
	}

	@Override
	public void update(String noticia) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Obra obra) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Visitante visitante) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Local local) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Exposicao exposicao) {
		txtIngresso.setText("" + exposicao.getValor());
		dcDataInicio.setDate(exposicao.getDataInicio());
		dcDataFim.setDate(exposicao.getDataFim());
		
		control.pesquisaObras(exposicao.getId());
		tabelaExposicao.invalidate();
		tabelaExposicao.revalidate();
	}
}
