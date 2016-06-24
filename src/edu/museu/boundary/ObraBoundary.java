package edu.museu.boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import edu.museu.control.ComponenteFormater;
import edu.museu.control.ImagemFormater;
import edu.museu.control.ObraControl;
import edu.museu.control.Observer;
import edu.museu.entity.Exposicao;
import edu.museu.entity.Local;
import edu.museu.entity.Obra;
import edu.museu.entity.Visitante;

public class ObraBoundary implements ActionListener, Observer {
	private JLabel lblImagemObra = new JLabel();
	private JLabel lblNomeObra = new JLabel("Obra");
	private JLabel lblNomeAutor = new JLabel("Autor");
	private JLabel lblBiografia = new JLabel("Biografia");
	private JLabel lblDataObra = new JLabel("Data da Obra");
	private JLabel lblTipo = new JLabel("Tipo");
	private JLabel lblCategoria = new JLabel("Categoria");
	private JLabel lblLocalizacao = new JLabel("Localizacao");
	private JCheckBox disponibilidade = new JCheckBox("Disponivel para emprestimo?");

	private JTextField txtObra = new JTextField(40);
	private JTextField txtAutor = new JTextField(40);
	private JTextArea txtBiografia = new JTextArea();
	private JDateChooser dcDataObra = new JDateChooser();
	private JComboBox<String> cbTipo = new JComboBox<String>();
	private JComboBox<String> cbCategoria = new JComboBox<String>();
	private JComboBox<String> cbLocalizacao = new JComboBox<String>();

	private JButton btnSalvar = new JButton("Salvar");
	private JButton btnAlterar = new JButton("Alterar");
	private JButton btnExcluir = new JButton("Excluir");
	private JButton btnCarregaImagem = new JButton("Carregar Imagem");
	private JButton btnPesquisaObra = new JButton();
	private JButton btnVoltar = new JButton("Voltar");
	private JPanel painelPrincipal = new JPanel(new GridLayout(1, 2, 0, 0));

	private ObraControl control = new ObraControl();
	private Obra obra = new Obra();

	public JPanel getPainelPrincipal() {
		return painelPrincipal;
	}

	public void setPainelPrincipal(JPanel painelPrincipal) {
		this.painelPrincipal = painelPrincipal;
	}

	public ObraBoundary() {
		JPanel painelPrincipalLeft = new JPanel(new BorderLayout());
		JPanel painelPrincipalRight = new JPanel(new BorderLayout());
		JPanel painelCampos = new JPanel(new GridLayout(6, 1));
		JPanel painelRightAux = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
		JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));

		painelBotoes.add(btnSalvar);
		painelBotoes.add(btnAlterar);
		painelBotoes.add(btnExcluir);
		painelBotoes.add(btnVoltar);

		painelPrincipalRight.setSize(200, 400);
		painelPrincipalRight.setPreferredSize(painelPrincipalRight.getSize());

		JScrollPane painelBiografia = new JScrollPane();
		painelBiografia.setPreferredSize(new Dimension(600, 100));
		painelBiografia.setViewportView(txtBiografia);
		lblBiografia.setHorizontalAlignment(JLabel.CENTER);

		JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 2));
		JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 2));
		JPanel linha3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 2));
		JPanel linha4 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 2));
		JPanel linha5 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 2));
		JPanel linha6 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 2));

		linha1.add(lblNomeObra);
		linha1.add(txtObra);
		linha1.add(btnPesquisaObra);

		linha2.add(lblNomeAutor);
		linha2.add(txtAutor);

		linha3.add(lblDataObra);
		linha3.add(dcDataObra);
		linha3.add(disponibilidade);

		linha4.add(lblTipo);
		linha4.add(cbTipo);

		linha5.add(lblCategoria);
		linha5.add(cbCategoria);

		linha6.add(lblLocalizacao);
		linha6.add(cbLocalizacao);

		painelCampos.add(linha1);
		painelCampos.add(linha2);
		painelCampos.add(linha3);
		painelCampos.add(linha4);
		painelCampos.add(linha5);
		painelCampos.add(linha6);

		painelPrincipalLeft.add(lblImagemObra, BorderLayout.CENTER);
		painelPrincipalLeft.add(btnCarregaImagem, BorderLayout.SOUTH);

		JPanel painelAuxBiografia = new JPanel(new BorderLayout());
		painelAuxBiografia.add(lblBiografia, BorderLayout.NORTH);
		painelAuxBiografia.add(painelBiografia, BorderLayout.CENTER);

		painelRightAux.add(painelCampos);
		painelRightAux.add(painelAuxBiografia);

		btnCarregaImagem.addActionListener(this);
		btnAlterar.addActionListener(this);
		btnExcluir.addActionListener(this);
		btnPesquisaObra.addActionListener(this);
		btnSalvar.addActionListener(this);
		btnVoltar.addActionListener(this);

		btnPesquisaObra.setIcon(new ImageIcon(ObraBoundary.class.getResource("/edu/museu/resource/lupa_16.png")));
		btnSalvar.setIcon(new ImageIcon(ObraBoundary.class.getResource("/edu/museu/resource/save.png")));
		btnAlterar.setIcon(new ImageIcon(ObraBoundary.class.getResource("/edu/museu/resource/edit.png")));
		btnExcluir.setIcon(new ImageIcon(ObraBoundary.class.getResource("/edu/museu/resource/delete.png")));
		btnVoltar.setIcon(new ImageIcon(ObraBoundary.class.getResource("/edu/museu/resource/back.png")));
		btnCarregaImagem.setIcon(new ImageIcon(ObraBoundary.class.getResource("/edu/museu/resource/open.png")));

		cbTipo.setModel(new DefaultComboBoxModel(
				new String[] { "Selecione", "Pintura", "Escultura", "Fotografia", "Livro", "Outro" }));
		cbCategoria.setModel(new DefaultComboBoxModel(
				new String[] { "Selecione", "Moderna", "Barroca", "Cubista", "Realista", "Outro" }));
		cbLocalizacao.setModel(new DefaultComboBoxModel(
				new String[] { "Selecione", "Salao Principal", "Primeiro Andar", "Subterr\u00E2neo", "Outro" }));

		ComponenteFormater.formataJButton(btnAlterar);
		ComponenteFormater.formataJButton(btnCarregaImagem);
		ComponenteFormater.formataJButton(btnExcluir);
		ComponenteFormater.formataJButton(btnPesquisaObra);
		ComponenteFormater.formataJButton(btnSalvar);
		ComponenteFormater.formataJButton(btnVoltar);

		int tam = 20;
		Color cor = Color.DARK_GRAY;
		ComponenteFormater.formataJlable(lblBiografia, cor, tam);
		ComponenteFormater.formataJlable(lblCategoria, cor, tam);
		ComponenteFormater.formataJlable(lblDataObra, cor, tam);
		ComponenteFormater.formataJlable(lblImagemObra, cor, tam);
		ComponenteFormater.formataJlable(lblLocalizacao, cor, tam);
		ComponenteFormater.formataJlable(lblNomeAutor, cor, tam);
		ComponenteFormater.formataJlable(lblNomeObra, cor, tam);
		ComponenteFormater.formataJlable(lblTipo, cor, tam);

		ComponenteFormater.formataJComboBox(cbCategoria, Color.black, tam);
		ComponenteFormater.formataJComboBox(cbLocalizacao, Color.black, tam);
		ComponenteFormater.formataJComboBox(cbTipo, Color.black, tam);

		ComponenteFormater.formataJCheckBox(disponibilidade, cor, tam);

		ComponenteFormater.formataJTextArea(txtBiografia, Color.black, tam);

		ComponenteFormater.formataJPanel(painelAuxBiografia);
		ComponenteFormater.formataJPanel(painelBotoes);
		ComponenteFormater.formataJPanel(painelCampos);
		ComponenteFormater.formataJPanel(painelPrincipalLeft);
		ComponenteFormater.formataJPanel(painelPrincipalRight);
		ComponenteFormater.formataJPanel(painelRightAux);
		ComponenteFormater.formataJPanel(painelPrincipal);
		ComponenteFormater.formataJPanel(linha1);
		ComponenteFormater.formataJPanel(linha2);
		ComponenteFormater.formataJPanel(linha3);
		ComponenteFormater.formataJPanel(linha4);
		ComponenteFormater.formataJPanel(linha5);
		ComponenteFormater.formataJPanel(linha6);

		lblCategoria.setPreferredSize(lblDataObra.getPreferredSize());
		lblLocalizacao.setPreferredSize(lblDataObra.getPreferredSize());
		lblNomeAutor.setPreferredSize(lblDataObra.getPreferredSize());
		lblNomeObra.setPreferredSize(lblDataObra.getPreferredSize());
		lblTipo.setPreferredSize(lblDataObra.getPreferredSize());

		lblBiografia.setHorizontalAlignment(JLabel.CENTER);

		dcDataObra.setPreferredSize(cbLocalizacao.getPreferredSize());
		cbTipo.setPreferredSize(cbLocalizacao.getPreferredSize());
		cbCategoria.setPreferredSize(cbLocalizacao.getPreferredSize());

		painelPrincipalRight.add(painelRightAux, BorderLayout.CENTER);
		painelPrincipalRight.add(painelBotoes, BorderLayout.SOUTH);

		painelPrincipal.add(painelPrincipalLeft);
		painelPrincipal.add(painelPrincipalRight);
		telaDefault();
	}

	public Obra formToObra() {
		obra.setBiografia(txtBiografia.getText());
		obra.setCategoriaObra(cbCategoria.getSelectedItem().toString());
		obra.setDataObra(dcDataObra.getDate());
		obra.setDisponivel(disponibilidade.isSelected());
		obra.setImagem((ImageIcon) lblImagemObra.getIcon());
		obra.setLocalizacaoObra(cbLocalizacao.getSelectedItem().toString());
		obra.setNomeAutor(txtAutor.getText());
		obra.setNomeObra(txtObra.getText());
		obra.setTipoObra(cbTipo.getSelectedItem().toString());
		return obra;
	}

	public void telaDefault() {
		btnSalvar.setEnabled(true);
		btnAlterar.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnVoltar.setEnabled(false);
		limapaCampo();
	}

	private void limapaCampo() {
		txtAutor.setText("");
		txtBiografia.setText("");
		txtObra.setText("");
		cbCategoria.setSelectedItem("Selecione");
		cbLocalizacao.setSelectedItem("Selecione");
		cbTipo.setSelectedItem("Selecione");
		disponibilidade.setSelected(true);
		dcDataObra.setDate(null);
		lblImagemObra.setIcon(null);
	}

	public void telaAlterar() {
		btnSalvar.setEnabled(false);
		btnAlterar.setEnabled(true);
		btnExcluir.setEnabled(true);
		btnVoltar.setEnabled(true);
	}

	public boolean validaCampos() {
		if (txtAutor.getText().length() == 0 || txtBiografia.getText().length() == 0 || txtObra.getText().length() == 0
				|| cbCategoria.getSelectedItem().toString().equals("Selecione")
				|| cbLocalizacao.getSelectedItem().toString().equals("Selecione")
				|| cbTipo.getSelectedItem().toString().equals("Selecione") || dcDataObra.getDate() == null
				|| lblImagemObra.getIcon() == null) {
			JOptionPane.showMessageDialog(painelPrincipal, "Preencha corretamente todos os campos",
					"Validação de campos", JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCarregaImagem) {
			control.carregaImagem(lblImagemObra);
		} else if (e.getSource() == btnSalvar && validaCampos()) {
			control.salvar(formToObra());
			telaDefault();
		} else if (e.getSource() == btnAlterar && validaCampos()) {
			control.alterar(formToObra());
			telaAlterar();
		} else if (e.getSource() == btnExcluir) {
			int resposta = JOptionPane.showConfirmDialog(painelPrincipal,
					"Você esta prestes a excluir um registro!\nTem certeza que deseja continuar", "Confirmação",
					JOptionPane.YES_NO_OPTION);
			if (resposta == JOptionPane.YES_OPTION) {
				control.deletar(formToObra());
				telaDefault();
			}
		} else if (e.getSource() == btnVoltar) {
			telaDefault();
		} else if (e.getSource() == btnPesquisaObra) {
			FormPesquisa pesquisaObra = new PesquisaObra();
			pesquisaObra.addObserver(this);
			pesquisaObra.show();
			telaAlterar();
		}

	}

	@Override
	public void update(Object o) {
		this.obra = (Obra) o;
		obra.setImagem(control.getImage(obra.getId()));
		obraToForm(obra);
	}

	private void obraToForm(Obra obra) {
		txtAutor.setText(obra.getNomeAutor());
		txtBiografia.setText(obra.getBiografia());
		txtObra.setText(obra.getNomeObra());
		cbCategoria.setSelectedItem(obra.getCategoriaObra());
		cbLocalizacao.setSelectedItem(obra.getLocalizacaoObra());
		cbTipo.setSelectedItem(obra.getTipoObra());
		dcDataObra.setDate(obra.getDataObra());
		disponibilidade.setSelected(obra.isDisponivel());
		lblImagemObra.setIcon(obra.getImagem());
	}
}
