package edu.museu.boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.ListDataListener;
import javax.swing.text.MaskFormatter;

import edu.museu.control.ComponenteFormater;
import edu.museu.control.ImagemFormater;
import edu.museu.control.Observer;
import edu.museu.control.VisitanteControl;
import edu.museu.entity.Exposicao;
import edu.museu.entity.Local;
import edu.museu.entity.Obra;
import edu.museu.entity.Visitante;

public class VisitanteBoundary implements ActionListener, Observer, KeyListener {
	private JLabel lblCPF = new JLabel("CPF");
	private JLabel lblNacionalidade = new JLabel("Nacionalidade:");
	private JLabel lblIdade = new JLabel("Idade");
	private JLabel lblSexo = new JLabel("Sexo:");
	private JLabel lblInstrucao = new JLabel("Grau de Instrução");
	private JLabel lblTransporte = new JLabel("Meio de Transporte");
	private JLabel lblData = new JLabel();

	private JLabel lblLogo = new JLabel();

	private JFormattedTextField txtCPF = new JFormattedTextField();
	private JTextField txtIdade = new JTextField(5);

	private JButton btnSalvar = new JButton("Salvar");
	private JButton btnAlterar = new JButton("Alterar");
	private JButton btnExcluir = new JButton("Excluir");
	private JButton btnVoltar = new JButton("Voltar");
	private JButton btnPesquisar = new JButton();
	private JComboBox<String> cbInstrucao = new JComboBox<String>();
	private JComboBox<String> cbTransporte = new JComboBox<String>();

	private JRadioButton rbBrasileiro = new JRadioButton("Brasileiro      ");
	private JRadioButton rbOutros = new JRadioButton("Outros");
	private JRadioButton rbFeminino = new JRadioButton("Feminino       ");
	private JRadioButton rbMasculino = new JRadioButton("Masculino");

	private ButtonGroup gnasc = new ButtonGroup();
	private ButtonGroup gSexo = new ButtonGroup();

	private JPanel painelPrincipal = new JPanel(new GridLayout(1, 2, 10, 10));

	private Visitante visitante = new Visitante();
	private VisitanteControl controle = new VisitanteControl();

	public JPanel getPainelPrincipal() {
		return painelPrincipal;
	}

	public void setPainelPrincipal(JPanel painelPrincipal) {
		this.painelPrincipal = painelPrincipal;
	}

	public VisitanteBoundary() {

		JPanel painelRight = new JPanel(new BorderLayout());
		JPanel painelLeft = new JPanel(new BorderLayout());

		JPanel painelCentro = new JPanel(new GridLayout(6, 1, 5, 5));
		JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel linha3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel linha4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel linha5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel linha6 = new JPanel(new FlowLayout(FlowLayout.LEFT));

		linha1.add(lblCPF);
		JPanel auxLinha1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		auxLinha1.add(txtCPF);
		auxLinha1.add(btnPesquisar);
		linha1.add(auxLinha1);

		linha2.add(lblNacionalidade);
		linha2.add(rbBrasileiro);
		linha2.add(rbOutros);

		linha3.add(lblIdade);
		linha3.add(txtIdade);

		linha4.add(lblSexo);
		linha4.add(rbFeminino);
		linha4.add(rbMasculino);

		linha5.add(lblInstrucao);
		linha5.add(cbInstrucao);

		linha6.add(lblTransporte);
		linha6.add(cbTransporte);

		painelCentro.add(linha1);
		painelCentro.add(linha2);
		painelCentro.add(linha3);
		painelCentro.add(linha4);
		painelCentro.add(linha5);
		painelCentro.add(linha6);

		JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
		painelBotoes.add(btnSalvar);
		painelBotoes.add(btnAlterar);
		painelBotoes.add(btnExcluir);
		painelBotoes.add(btnVoltar);

		ImageIcon icon = new ImageIcon(VisitanteBoundary.class.getResource("/edu/museu/resource/visitante.png"));
		byte[] bs = ImagemFormater.imagemParaByte(icon);
		lblLogo.setIcon(ImagemFormater.bytesParaImagem(bs));

		gSexo.add(rbFeminino);
		gSexo.add(rbMasculino);

		gnasc.add(rbBrasileiro);
		gnasc.add(rbOutros);

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		lblData.setText("" + formato.format(new Date()));

		String[] grauInstrucao = { "<<Selecione>>", "Analfabeto", "Alfabetizado", "Fundamental incompleto",
				"Fundamental completo", "Médio incompleto", "Médio completo", "Superior incompleto",
				"Superior Completo" };
		String[] meioTransporte = { "<<Selecione>>", "Coletivo privado", "Coletivo público", "Próprio" };

		btnPesquisar.setIcon(new ImageIcon(VisitanteBoundary.class.getResource("/edu/museu/resource/lupa_16.png")));
		btnSalvar.setIcon(new ImageIcon(VisitanteBoundary.class.getResource("/edu/museu/resource/save.png")));
		btnAlterar.setIcon(new ImageIcon(VisitanteBoundary.class.getResource("/edu/museu/resource/edit.png")));
		btnExcluir.setIcon(new ImageIcon(VisitanteBoundary.class.getResource("/edu/museu/resource/delete.png")));
		btnVoltar.setIcon(new ImageIcon(VisitanteBoundary.class.getResource("/edu/museu/resource/back.png")));

		MaskFormatter maskCpf;
		try {
			maskCpf = new MaskFormatter("###.###.###-##");

			maskCpf.setPlaceholderCharacter('_');
			maskCpf.install(txtCPF);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		cbInstrucao.setModel(new DefaultComboBoxModel<>(grauInstrucao));
		cbTransporte.setModel(new DefaultComboBoxModel<>(meioTransporte));

		ComponenteFormater.formataJlable(lblCPF, Color.DARK_GRAY, 20);
		ComponenteFormater.formataJlable(lblData, Color.DARK_GRAY, 20);
		ComponenteFormater.formataJlable(lblIdade, Color.DARK_GRAY, 20);
		ComponenteFormater.formataJlable(lblInstrucao, Color.DARK_GRAY, 20);
		ComponenteFormater.formataJlable(lblNacionalidade, Color.DARK_GRAY, 20);
		ComponenteFormater.formataJlable(lblSexo, Color.DARK_GRAY, 20);
		ComponenteFormater.formataJlable(lblTransporte, Color.DARK_GRAY, 20);
		lblData.setHorizontalAlignment(JLabel.CENTER);

		lblCPF.setPreferredSize(lblTransporte.getPreferredSize());
		lblIdade.setPreferredSize(lblTransporte.getPreferredSize());
		lblInstrucao.setPreferredSize(lblTransporte.getPreferredSize());
		lblNacionalidade.setPreferredSize(lblTransporte.getPreferredSize());
		lblSexo.setPreferredSize(lblTransporte.getPreferredSize());
		lblTransporte.setPreferredSize(lblTransporte.getPreferredSize());
		txtCPF.setPreferredSize(cbInstrucao.getPreferredSize());

		btnAlterar.addActionListener(this);
		btnExcluir.addActionListener(this);
		btnPesquisar.addActionListener(this);
		btnSalvar.addActionListener(this);
		btnVoltar.addActionListener(this);

		txtIdade.addKeyListener(this);

		ComponenteFormater.formataJButton(btnAlterar);
		ComponenteFormater.formataJButton(btnExcluir);
		ComponenteFormater.formataJButton(btnPesquisar);
		ComponenteFormater.formataJButton(btnSalvar);
		ComponenteFormater.formataJButton(btnVoltar);

		ComponenteFormater.formataJPanel(painelBotoes);
		ComponenteFormater.formataJPanel(painelCentro);
		ComponenteFormater.formataJPanel(painelLeft);
		ComponenteFormater.formataJPanel(painelRight);
		ComponenteFormater.formataJPanel(painelPrincipal);
		ComponenteFormater.formataJPanel(auxLinha1);
		ComponenteFormater.formataJPanel(linha1);
		ComponenteFormater.formataJPanel(linha2);
		ComponenteFormater.formataJPanel(linha3);
		ComponenteFormater.formataJPanel(linha4);
		ComponenteFormater.formataJPanel(linha5);
		ComponenteFormater.formataJPanel(linha6);

		ComponenteFormater.formataJRadioButton(rbBrasileiro, Color.RED, 20);
		ComponenteFormater.formataJRadioButton(rbFeminino, Color.RED, 20);
		ComponenteFormater.formataJRadioButton(rbMasculino, Color.RED, 20);
		ComponenteFormater.formataJRadioButton(rbOutros, Color.RED, 20);

		ComponenteFormater.formataJComboBox(cbInstrucao, Color.black, 20);
		ComponenteFormater.formataJComboBox(cbTransporte, Color.black, 20);

		txtIdade.setPreferredSize(new Dimension(cbInstrucao.getWidth(), 30));

		painelRight.add(painelCentro, BorderLayout.CENTER);
		painelRight.add(painelBotoes, BorderLayout.SOUTH);

		painelLeft.add(lblLogo, BorderLayout.CENTER);
		painelLeft.add(lblData, BorderLayout.SOUTH);

		painelPrincipal.add(painelLeft);
		painelPrincipal.add(painelRight);

		telaDefault();
	}

	public Visitante formToVisitante() {
		if (validaCampos()) {
			visitante.setCpf(txtCPF.getText());
			visitante.setId(Long.parseLong(txtIdade.getText()));
			visitante.setGrauInstrucao(cbInstrucao.getSelectedItem().toString());
			visitante.setMeioTransporte(cbTransporte.getSelectedItem().toString());
			visitante.setNacionalidade(getNacionalidade());
			visitante.setSexo(getsexo());
			return visitante;
		}
		return null;
	}

	public void visitanteToForm(Visitante visitante) {
		this.visitante = visitante;
		txtCPF.setText(visitante.getCpf());
		txtIdade.setText("" + visitante.getIdade());
		cbInstrucao.setSelectedItem(visitante.getGrauInstrucao());
		cbTransporte.setSelectedItem(visitante.getMeioTransporte());
		setNacionalidade();
		setSexo();
	}

	private String getNacionalidade() {
		if (rbBrasileiro.isSelected())
			return "Brasileiro";
		if (rbOutros.isSelected())
			return "Outros";
		return "";
	}

	private String getsexo() {
		if (rbFeminino.isSelected())
			return "Feminino";
		if (rbMasculino.isSelected())
			return "Masculino";
		return "";
	}

	private void setNacionalidade() {
		if (visitante.getNacionalidade().equals("Brasileiro")) {
			rbBrasileiro.setSelected(true);
		} else if (visitante.getNacionalidade().equals("Outros")) {
			rbOutros.setSelected(true);
		}
	}

	private void setSexo() {
		if (visitante.getSexo().equals("Feminino")) {
			rbFeminino.setSelected(true);
		} else if (visitante.getSexo().equals("Masculino")) {
			rbMasculino.setSelected(true);
		}
	}

	private boolean validaCampos() {
		if (txtCPF.getText().contains("_") || txtIdade.getText().length() == 0
				|| cbInstrucao.getSelectedItem().toString().equals("<<Selecione>>")
				|| cbTransporte.getSelectedItem().toString().equals("<<Selecione>>")) {
			JOptionPane.showMessageDialog(painelPrincipal, "Preencha corretamente todos os campos!",
					"preenchimento incorreto", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private void limpaCampos() {
		txtCPF.setText("");
		txtIdade.setText("");
		rbBrasileiro.setSelected(true);
		rbOutros.setSelected(false);
		rbMasculino.setSelected(true);
		rbFeminino.setSelected(false);
		cbInstrucao.setSelectedIndex(0);
		cbTransporte.setSelectedIndex(0);
	}

	private void telaDefault() {
		btnSalvar.setEnabled(true);
		btnExcluir.setEnabled(false);
		btnVoltar.setEnabled(false);
		btnAlterar.setEnabled(false);
		limpaCampos();
	}

	private void telaAlterar() {
		btnSalvar.setEnabled(false);
		btnExcluir.setEnabled(true);
		btnVoltar.setEnabled(true);
		btnAlterar.setEnabled(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnSalvar) {
			if (controle.salvar(formToVisitante()) < 1) {
				JOptionPane.showMessageDialog(painelPrincipal, "erro ao inserir registro", "erro",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(painelPrincipal, "Cadastro inserido com sucesso", "Cadastro",
						JOptionPane.INFORMATION_MESSAGE);
				telaDefault();
			}
		} else if (arg0.getSource() == btnPesquisar) {
			FormPesquisa a = new PesquisaVisitante();
			a.addObserver(this);
			a.show();
			telaAlterar();
		} else if (arg0.getSource() == btnAlterar) {
			if (controle.alterar(formToVisitante()) < 1) {
				JOptionPane.showMessageDialog(painelPrincipal, "erro ao inserir registro", "erro",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(painelPrincipal, "Cadastro Alterado com sucesso", "Cadastro",
						JOptionPane.INFORMATION_MESSAGE);
				telaAlterar();
			}
		} else if (arg0.getSource() == btnExcluir) {
			if (controle.excluir(formToVisitante()) < 1) {
				JOptionPane.showMessageDialog(painelPrincipal, "erro ao inserir registro", "erro",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(painelPrincipal, "Cadastro Excluido com sucesso", "Cadastro",
						JOptionPane.INFORMATION_MESSAGE);
				telaDefault();
			}
		} else if (arg0.getSource() == btnVoltar) {
			telaDefault();
		}
	}

	@Override
	public void update(Object v) {
		Visitante visitante = (Visitante) v;
		visitanteToForm(visitante);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtIdade) {
			String caracteres = "0987654321";
			if (!caracteres.contains(e.getKeyChar() + "") || txtIdade.getText().length() > 2) {
				e.consume();
			}
		}

	}
}
