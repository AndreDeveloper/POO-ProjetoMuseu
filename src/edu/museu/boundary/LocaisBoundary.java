package edu.museu.boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import edu.museu.control.ComponenteFormater;
import edu.museu.control.ImagemFormater;
import edu.museu.control.LocalControl;
import edu.museu.control.Observer;
import edu.museu.entity.Exposicao;
import edu.museu.entity.Local;
import edu.museu.entity.Obra;
import edu.museu.entity.Visitante;

public class LocaisBoundary implements ActionListener, Observer {
	private JLabel lblNome = new JLabel("Nome");
	private JLabel lblEmail = new JLabel("Email");
	private JLabel lblTelefone = new JLabel("Telefone");
	private JLabel lblResponsavel = new JLabel("Responsável");
	private JLabel lblLocal = new JLabel("Local");
	private JLabel lblEndereco = new JLabel("Endereço");
	private JLabel lblCep = new JLabel("CEP");
	private JLabel lblLogradouro = new JLabel("Logradouro");
	private JLabel lblNumero = new JLabel("Numero");
	private JLabel lblComplemento = new JLabel("Complemento");
	private JLabel lblBairro = new JLabel("Bairro");
	private JLabel lblCidade = new JLabel("Cidade");
	private JLabel lblUF = new JLabel("UF");
	private JLabel lblStatus = new JLabel("Status");
	private JLabel lblLogo = new JLabel("");

	private JTextField txtNome = new JTextField(40);
	private JTextField txtEmail = new JTextField(40);
	private JFormattedTextField txtTelefone = new JFormattedTextField();
	private JFormattedTextField txtCep = new JFormattedTextField();
	private JTextField txtLogradouro = new JTextField(40);
	private JTextField txtResponsavel = new JTextField(17);
	private JTextField txtNumero = new JTextField(5);
	private JTextField txtComplemento = new JTextField(21);
	private JTextField txtBairro = new JTextField(12);
	private JTextField txtCidade = new JTextField(10);

	private JComboBox<String> cbUF = new JComboBox<String>(
			new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB",
					"PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" });

	private JRadioButton rbAtivado = new JRadioButton("Ativado");
	private JRadioButton rbDesativado = new JRadioButton("Desativado");
	private ButtonGroup bg = new ButtonGroup();

	private JButton btnSalvar = new JButton("Salvar");
	private JButton btnAlterar = new JButton("Alterar");
	private JButton btnExcluir = new JButton("Excluir");
	private JButton btnVoltar = new JButton("Voltar");
	private JButton btnPesquisar = new JButton("");

	private JPanel painelPrincipal = new JPanel(new GridLayout(1, 2));

	private Local local = new Local();
	private LocalControl control = new LocalControl();

	public LocaisBoundary() {
		super();
		create();
	}

	public JPanel getPainelPrincipal() {
		return painelPrincipal;
	}

	public void setPainelPrincipal(JPanel painelPrincipal) {
		this.painelPrincipal = painelPrincipal;
	}

	private void create() {
		JPanel painelLeft = new JPanel(new BorderLayout());
		JPanel painelRight = new JPanel(new BorderLayout());

		JPanel painelCima = new JPanel(new GridLayout(4, 1, 0, 0));
		JPanel painelBaixo = new JPanel(new GridLayout(7, 1, 0, 0));

		JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
		JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		JPanel linha3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		JPanel linha4 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		JPanel linha5 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
		JPanel linha6 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		JPanel linha7 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		JPanel linha8 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		JPanel linha9 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		JPanel linha10 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
		JPanel linha11 = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 0));

		bg.add(rbAtivado);
		bg.add(rbDesativado);

		linha1.add(lblLocal);

		linha2.add(lblNome);
		linha2.add(txtNome);
		linha2.add(btnPesquisar);

		linha3.add(lblEmail);
		linha3.add(txtEmail);

		linha4.add(lblTelefone);
		linha4.add(txtTelefone);
		linha4.add(lblResponsavel);
		linha4.add(txtResponsavel);

		linha5.add(lblEndereco);

		linha6.add(lblCep);
		linha6.add(txtCep);

		linha7.add(lblLogradouro);
		linha7.add(txtLogradouro);

		linha8.add(lblNumero);
		linha8.add(txtNumero);
		linha8.add(lblComplemento);
		linha8.add(txtComplemento);

		linha9.add(lblBairro);
		linha9.add(txtBairro);
		linha9.add(lblCidade);
		linha9.add(txtCidade);
		linha9.add(lblUF);
		linha9.add(cbUF);

		linha10.add(lblStatus);

		linha11.add(rbAtivado);
		linha11.add(rbDesativado);

		painelCima.add(linha1);
		painelCima.add(linha2);
		painelCima.add(linha3);
		painelCima.add(linha4);

		painelBaixo.add(linha5);
		painelBaixo.add(linha6);
		painelBaixo.add(linha7);
		painelBaixo.add(linha8);
		painelBaixo.add(linha9);
		painelBaixo.add(linha10);
		painelBaixo.add(linha11);

		JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
		painelBotoes.add(btnSalvar);
		painelBotoes.add(btnAlterar);
		painelBotoes.add(btnExcluir);
		painelBotoes.add(btnVoltar);

		ComponenteFormater.formataJlable(lblBairro, Color.DARK_GRAY, 22);
		ComponenteFormater.formataJlable(lblCep, Color.DARK_GRAY, 22);
		ComponenteFormater.formataJlable(lblCidade, Color.DARK_GRAY, 22);
		ComponenteFormater.formataJlable(lblComplemento, Color.DARK_GRAY, 22);
		ComponenteFormater.formataJlable(lblEmail, Color.DARK_GRAY, 22);
		ComponenteFormater.formataJlable(lblEndereco, Color.blue, 24);
		ComponenteFormater.formataJlable(lblLocal, Color.blue, 24);
		ComponenteFormater.formataJlable(lblLogradouro, Color.DARK_GRAY, 22);
		ComponenteFormater.formataJlable(lblNome, Color.DARK_GRAY, 22);
		ComponenteFormater.formataJlable(lblNumero, Color.DARK_GRAY, 22);
		ComponenteFormater.formataJlable(lblResponsavel, Color.DARK_GRAY, 22);
		ComponenteFormater.formataJlable(lblStatus, Color.BLUE, 22);
		ComponenteFormater.formataJlable(lblTelefone, Color.DARK_GRAY, 22);
		ComponenteFormater.formataJlable(lblUF, Color.DARK_GRAY, 22);
		ComponenteFormater.formataJlable(lblLogo, Color.DARK_GRAY, 22);

		ComponenteFormater.formataJComboBox(cbUF, Color.DARK_GRAY, 18);

		ComponenteFormater.formataJRadioButton(rbAtivado, Color.DARK_GRAY, 22);
		ComponenteFormater.formataJRadioButton(rbDesativado, Color.DARK_GRAY, 22);

		ComponenteFormater.formataJButton(btnAlterar);
		ComponenteFormater.formataJButton(btnExcluir);
		ComponenteFormater.formataJButton(btnPesquisar);
		ComponenteFormater.formataJButton(btnSalvar);
		ComponenteFormater.formataJButton(btnVoltar);

		JPanel painelCentro = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 30));
		painelCentro.add(painelCima);
		painelCentro.add(painelBaixo);
		painelCentro.setPreferredSize(new Dimension(200, 200));

		ComponenteFormater.formataJPanel(painelBotoes);
		ComponenteFormater.formataJPanel(painelBaixo);
		ComponenteFormater.formataJPanel(painelCima);
		ComponenteFormater.formataJPanel(painelLeft);
		ComponenteFormater.formataJPanel(painelRight);
		ComponenteFormater.formataJPanel(painelCentro);
		ComponenteFormater.formataJPanel(painelPrincipal);
		ComponenteFormater.formataJPanel(linha1);
		ComponenteFormater.formataJPanel(linha2);
		ComponenteFormater.formataJPanel(linha3);
		ComponenteFormater.formataJPanel(linha4);
		ComponenteFormater.formataJPanel(linha5);
		ComponenteFormater.formataJPanel(linha6);
		ComponenteFormater.formataJPanel(linha7);
		ComponenteFormater.formataJPanel(linha8);
		ComponenteFormater.formataJPanel(linha9);
		ComponenteFormater.formataJPanel(linha10);
		ComponenteFormater.formataJPanel(linha11);

		lblBairro.setPreferredSize(lblLogradouro.getPreferredSize());
		lblCep.setPreferredSize(lblLogradouro.getPreferredSize());
		lblEmail.setPreferredSize(lblLogradouro.getPreferredSize());
		lblLogradouro.setPreferredSize(lblLogradouro.getPreferredSize());
		lblNome.setPreferredSize(lblLogradouro.getPreferredSize());
		lblNumero.setPreferredSize(lblLogradouro.getPreferredSize());
		lblTelefone.setPreferredSize(lblLogradouro.getPreferredSize());
		lblStatus.setHorizontalAlignment(JLabel.CENTER);

		byte[] bs = ImagemFormater
				.imagemParaByte(new ImageIcon(VisitanteBoundary.class.getResource("/edu/museu/resource/locais.png")));
		lblLogo.setIcon(ImagemFormater.bytesParaImagem(bs));
		btnPesquisar.setIcon(new ImageIcon(VisitanteBoundary.class.getResource("/edu/museu/resource/lupa_16.png")));
		btnSalvar.setIcon(new ImageIcon(VisitanteBoundary.class.getResource("/edu/museu/resource/save.png")));
		btnAlterar.setIcon(new ImageIcon(VisitanteBoundary.class.getResource("/edu/museu/resource/edit.png")));
		btnExcluir.setIcon(new ImageIcon(VisitanteBoundary.class.getResource("/edu/museu/resource/delete.png")));
		btnVoltar.setIcon(new ImageIcon(VisitanteBoundary.class.getResource("/edu/museu/resource/back.png")));

		try {
			MaskFormatter maskCep = new MaskFormatter("#####-###");
			maskCep.setPlaceholderCharacter('_');
			maskCep.install(txtCep);

			MaskFormatter maskTelefone = new MaskFormatter("(##) ####-####");
			maskTelefone.setPlaceholderCharacter('_');
			maskTelefone.install(txtTelefone);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		painelLeft.add(lblLogo);

		btnAlterar.addActionListener(this);
		btnExcluir.addActionListener(this);
		btnPesquisar.addActionListener(this);
		btnSalvar.addActionListener(this);
		btnVoltar.addActionListener(this);

		painelRight.add(painelCentro, BorderLayout.CENTER);
		painelRight.add(painelBotoes, BorderLayout.SOUTH);

		painelPrincipal.add(painelLeft);
		painelPrincipal.add(painelRight);

		telaDefault();
	}

	private Local formToLocal() {
		if (validaCampos()) {
			Local local = this.local;
			local.setBairro(txtBairro.getText());
			local.setCep(txtCep.getText());
			local.setCidade(txtCidade.getText());
			local.setComplemento(txtComplemento.getText());
			local.setEmail(txtEmail.getText());
			local.setLogradouro(txtLogradouro.getText());
			local.setNomeLocal(txtNome.getText());
			local.setNumero(txtNumero.getText());
			local.setResponsavel(txtResponsavel.getText());
			local.setTelefone(txtTelefone.getText());
			local.setUf(cbUF.getSelectedItem().toString());
			local.setAtivo(getDisponivel());

			return local;
		}
		return null;
	}

	private void localToForm(Local local) {
		this.local = local;
		txtBairro.setText(local.getBairro());
		txtCep.setText(local.getCep());
		txtCidade.setText(local.getCidade());
		txtComplemento.setText(local.getComplemento());
		txtEmail.setText(local.getEmail());
		txtLogradouro.setText(local.getLogradouro());
		txtNome.setText(local.getNomeLocal());
		txtNumero.setText(local.getNumero());
		txtResponsavel.setText(local.getResponsavel());
		txtTelefone.setText(local.getTelefone());
		cbUF.setSelectedItem(local.getUf());
		setAtivo(local.isAtivo());

	}

	private void setAtivo(boolean b) {
		if (b) {
			rbAtivado.setSelected(true);
		} else {
			rbDesativado.setSelected(true);
		}
	}

	private boolean getDisponivel() {
		if (rbAtivado.isSelected())
			return true;
		if (rbDesativado.isSelected())
			return false;
		return false;
	}

	private void telaDefault() {
		btnSalvar.setEnabled(true);
		btnExcluir.setEnabled(false);
		btnVoltar.setEnabled(false);
		btnAlterar.setEnabled(false);
		limpaCampos();
	}

	private void limpaCampos() {
		txtBairro.setText("");
		txtCep.setText("");
		txtCidade.setText("");
		txtComplemento.setText("");
		txtEmail.setText("");
		txtLogradouro.setText("");
		txtNome.setText("");
		txtNumero.setText("");
		txtResponsavel.setText("");
		txtTelefone.setText("");
		cbUF.setSelectedIndex(0);
		rbAtivado.setSelected(true);

	}

	private void telaAlterar() {
		btnSalvar.setEnabled(false);
		btnExcluir.setEnabled(true);
		btnVoltar.setEnabled(true);
		btnAlterar.setEnabled(true);
	}

	private boolean validaCampos() {
		if (txtBairro.getText().length() <= 0 || txtCidade.getText().length() <= 0
				|| txtComplemento.getText().length() <= 0 || txtEmail.getText().length() <= 0
				|| txtLogradouro.getText().length() <= 0 || txtNome.getText().length() <= 0
				|| txtNumero.getText().length() <= 0 || txtResponsavel.getText().length() <= 0
				|| txtCep.getText().contains("_") || txtTelefone.getText().contains("_")
				|| cbUF.getSelectedIndex() <= 0) {
			JOptionPane.showMessageDialog(painelPrincipal, "Preencha corretamente todos os campos",
					"erro de preenchimento", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnSalvar && validaCampos()) {
			control.salvar(formToLocal());
			telaDefault();
		} else if (e.getSource() == btnPesquisar) {
			FormPesquisa pesquisa = new PesquisaLocais();
			pesquisa.addObserver(this);
			pesquisa.show();
			telaAlterar();
		} else if (e.getSource() == btnAlterar && validaCampos()) {
			control.Alterar(formToLocal());
			telaAlterar();
		} else if (e.getSource() == btnExcluir) {
			int resposta = JOptionPane.showConfirmDialog(painelPrincipal,
					"Você está prestes a excluir um registro!\nTem certeza que deseja continuar?", "Confirmação",
					JOptionPane.YES_NO_OPTION);
			if (resposta == JOptionPane.YES_OPTION) {
				control.excluir(formToLocal());
				telaDefault();
			}
		} else if (e.getSource() == btnVoltar) {
			telaDefault();
		}

	}

	@Override
	public void update(Object o) {
		this.local = (Local) o;
		localToForm(local);
	}
}
