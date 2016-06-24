package edu.museu.boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import edu.museu.control.PesquisaControl;
import edu.museu.control.PesquisaExposicaoControl;
import edu.museu.control.PesquisaObraControl;
import edu.museu.entity.Exposicao;
import edu.museu.entity.Local;
import edu.museu.entity.Obra;
import edu.museu.entity.Visitante;
import edu.museu.infrastructure.ExposicaoDAO;

public class ExposicaoBoundary implements ActionListener, Observer {
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

	private PesquisaControl toExposicao = new PesquisaObraControl();
	private ExposicaoControl control = new ExposicaoControl();
	private Exposicao exposicao = new Exposicao();

	public JPanel getPainelPrincipal() {
		return painelPrincipal;
	}

	public void setPainelPrincipal(JPanel painelPrincipal) {
		this.painelPrincipal = painelPrincipal;
	}

	public ExposicaoBoundary() {
		create();
		telaDefault();
	}

	private void create() {
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

		// formatando paineis
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

		// formatando jlable
		ComponenteFormater.formataJlable(lblDataInicio, Color.DARK_GRAY, 22);
		ComponenteFormater.formataJlable(lblDatafim, Color.DARK_GRAY, 22);
		ComponenteFormater.formataJlable(lblExposicao, Color.GREEN, 22);
		lblExposicao.setHorizontalAlignment(JLabel.CENTER);
		ComponenteFormater.formataJlable(lblIngresso, Color.DARK_GRAY, 22);
		ComponenteFormater.formataJlable(lblnome, Color.DARK_GRAY, 22);
		ComponenteFormater.formataJlable(lblObrasDisponiveis, Color.GREEN, 22);
		lblObrasDisponiveis.setHorizontalAlignment(JLabel.CENTER);

		// formatando botoes
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

		btnPesquisar.setIcon(new ImageIcon(VisitanteBoundary.class.getResource("/edu/museu/resource/lupa_16.png")));
		btnSalvar.setIcon(new ImageIcon(VisitanteBoundary.class.getResource("/edu/museu/resource/save.png")));
		btnAlterar.setIcon(new ImageIcon(VisitanteBoundary.class.getResource("/edu/museu/resource/edit.png")));
		btnExcluir.setIcon(new ImageIcon(VisitanteBoundary.class.getResource("/edu/museu/resource/delete.png")));
		btnVoltar.setIcon(new ImageIcon(VisitanteBoundary.class.getResource("/edu/museu/resource/back.png")));

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

		painelPrincipal.add(painelDados, BorderLayout.NORTH);
		painelPrincipal.add(painelCentro, BorderLayout.CENTER);
		painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

	}

	public Exposicao formToExposicao() {
		if (validaCampos()) {
			exposicao.setValor(Double.parseDouble(txtIngresso.getText()));
			exposicao.setNome(txtNome.getText());
			exposicao.setDataInicio(dcDataInicio.getDate());
			exposicao.setDataFim(dcDataFim.getDate());

			return exposicao;
		}
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdiciona && tabelaObras.getSelectedRow() > -1) {
			control.add((Obra) toExposicao.getLista().get(tabelaObras.getSelectedRow()));
			tabelaExposicao.invalidate();
			tabelaExposicao.revalidate();
		} else if (e.getSource() == btnRemove && tabelaExposicao.getSelectedRow() > -1) {
			control.remove(control.getListaExposicao().get(tabelaExposicao.getSelectedRow()));
			tabelaExposicao.clearSelection();
			tabelaExposicao.invalidate();
			tabelaExposicao.revalidate();
		} else if (e.getSource() == btnSalvar) {
			if (validaCampos()) {
				control.salvar(formToExposicao(), control.getListaExposicao());
				telaDefault();
			}
		} else if (e.getSource() == btnPesquisar) {
			FormPesquisa pesquisa = new PesquisaExposicao();
			pesquisa.addObserver(this);
			pesquisa.show();
			telaAlterar();
		} else if (e.getSource() == btnAlterar) {
			if (validaCampos()) {
				try {
					control.alterar(formToExposicao(), control.getListaExposicao());
					telaAlterar();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == btnExcluir) {
			int resposta = JOptionPane.showConfirmDialog(painelPrincipal,
					"voce esta prestes a excluir um registro!\nTem certeza que deseja continuar?", "Confirmação",
					JOptionPane.YES_NO_OPTION);
			if (resposta == JOptionPane.YES_OPTION) {
				control.deletar(formToExposicao());
				telaDefault();
			}
		} else if (e.getSource() == btnVoltar) {
			telaDefault();
		}
	}

	public void limpaCampos() {
		txtIngresso.setText("");
		txtNome.setText("");
		dcDataFim.setDate(null);
		dcDataInicio.setDate(null);
		List<Obra> lista = new ArrayList<Obra>();
		control.setListaExposicao(lista);
		tabelaExposicao.invalidate();
		tabelaExposicao.revalidate();
	}

	public boolean validaCampos() {
		if (txtIngresso.getText().length() <= 0 || txtNome.getText().length() <= 0 || dcDataFim.getDate() == null
				|| dcDataInicio.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Preencha corretamente todos os campos", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (dcDataFim.getDate().getTime() < dcDataInicio.getDate().getTime()) {
			JOptionPane.showMessageDialog(null, "A data de termino não pode ser menos que a data de inicio", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (dcDataInicio.getDate().getTime() < new Date().getTime()) {
			JOptionPane.showMessageDialog(null, "A data de inicio da exposição não pode ser menor que a data atual",
					"Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		try {
			Double.parseDouble(txtIngresso.getText());
		} catch (java.lang.NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Insira apenas valores validos no campo ingresso!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			txtIngresso.setText("");
			txtIngresso.requestFocus();
			return false;
		}
		return true;
	}

	public void telaDefault() {
		btnSalvar.setEnabled(true);
		btnAlterar.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnVoltar.setEnabled(false);
		limpaCampos();
	}

	public void telaAlterar() {
		btnSalvar.setEnabled(false);
		btnAlterar.setEnabled(true);
		btnExcluir.setEnabled(true);
		btnVoltar.setEnabled(true);

	}

	@Override
	public void update(Object exp) {
		this.exposicao = (Exposicao) exp;
		txtNome.setText(exposicao.getNome());
		txtIngresso.setText("" + exposicao.getValor());
		dcDataInicio.setDate(exposicao.getDataInicio());
		dcDataFim.setDate(exposicao.getDataFim());

		control.pesquisaObras(exposicao.getId());
		tabelaExposicao.invalidate();
		tabelaExposicao.revalidate();
	}
}
