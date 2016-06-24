package edu.museu.boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.museu.control.ComponenteFormater;
import edu.museu.control.IngressoControl;
import edu.museu.entity.Exposicao;
import edu.museu.entity.Ingresso;

public class IngressoBoundary implements ActionListener, ListSelectionListener{
	private JLabel lblTicket = new JLabel();
	private JLabel lblMuseu = new JLabel();
	private JLabel valorIngresso = new JLabel();
	private JLabel desconto = new JLabel();
	private JLabel valorAPagar = new JLabel();
	private JLabel exposicao = new JLabel("<<Selecione a Exposição>>");
	private JLabel lblValorIngresso = new JLabel("Valor do Ingresso: R$ ");
	private JLabel lblDesconto = new JLabel("Desconto: R$ ");
	private JLabel lblValorAPagar = new JLabel("Valor a pagar: R$");
	private JTable tabela = new JTable();
	private JPanel painelPrincipal = new JPanel(new GridLayout(1, 2));
	private JRadioButton opcao1 = new JRadioButton("Estudante/Professor (50% de desconte)", false);
	private JRadioButton opcao2 = new JRadioButton("Menor de 5 anos/ Maior de 60 anos(Gratuito)", false);
	private JRadioButton opcao3 = new JRadioButton("Ingresso Normal(Sem desconto)", true);
	private ButtonGroup group = new ButtonGroup(); 
	private JButton btnVender = new JButton("Vender Ingresso");
	private JButton btnMenos = new JButton("-   ");
	private JButton btnMais = new JButton("   +");
	private JLabel qtdade = new JLabel();
	
	IngressoControl control = new IngressoControl();
	
	
	public JPanel getPainelPrincipal() {
		return painelPrincipal;
	}
	public void setPainelPrincipal(JPanel painelPrincipal) {
		this.painelPrincipal = painelPrincipal;
	}
	public IngressoBoundary() {
		super();
		create();
	}
	
	public void atualiza(){
		control.atualizaLista();
		tabela.invalidate();
		tabela.revalidate();
	}
	
	private void create(){
		JPanel painelLeft = new JPanel(new BorderLayout());
		JPanel painelRight = new JPanel(new BorderLayout());
		JPanel painelNorte = new JPanel(new FlowLayout());
		JPanel painelValores = new JPanel(new GridLayout(8, 1));
		JScrollPane scrollPane = new JScrollPane();
		JPanel pScroll = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lblinter1 = new JLabel("Opção de desconto:");
		JLabel lblinter2 = new JLabel("Quantidade: ");
		JLabel lblinter3 = new JLabel("Selecione a exposição");

		lblTicket.setIcon(
				new ImageIcon(IngressoBoundary.class.getResource("/edu/museu/resource/ticket.png"))
				);
		lblMuseu.setIcon(
				new ImageIcon(IngressoBoundary.class.getResource("/edu/museu/resource/logoMuseu.png"))
				);
		btnVender.setIcon(
				new ImageIcon(IngressoBoundary.class.getResource("/edu/museu/resource/cash.png"))
				);
		
		painelNorte.add(lblMuseu);
		painelNorte.add(lblTicket);
		
		tabela.setModel(control);
		scrollPane.setViewportView(tabela);
		scrollPane.setPreferredSize(new Dimension(600, 350));
		pScroll.add(scrollPane);
		painelLeft.setPreferredSize(new Dimension(300, 300));

		JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel linha3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel linha4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel linha5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel linha6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel linha7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel linha8 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		linha1.add(lblValorIngresso);
		linha1.add(valorIngresso);
		
		linha2.add(lblDesconto);
		linha2.add(desconto);
		
		linha3.add(lblValorAPagar);
		linha3.add(valorAPagar);
		
		linha4.add(lblinter1);
		linha5.add(opcao1);
		linha6.add(opcao2);
		linha7.add(opcao3);		
		
		linha8.add(lblinter2);
		linha8.add(btnMenos);
		linha8.add(qtdade);
		linha8.add(btnMais);
		
		
		painelValores.add(linha1);
		painelValores.add(linha2);
		painelValores.add(linha8);
		painelValores.add(linha3);
		painelValores.add(linha4);
		painelValores.add(linha5);
		painelValores.add(linha6);
		painelValores.add(linha7);
		
		group.add(opcao1);
		group.add(opcao2);
		group.add(opcao3);
		
		ComponenteFormater.formataJPanel(painelLeft);
		ComponenteFormater.formataJPanel(painelNorte);
		ComponenteFormater.formataJPanel(painelRight);
		ComponenteFormater.formataJPanel(painelValores);
		ComponenteFormater.formataJPanel(painelPrincipal);
		ComponenteFormater.formataJPanel(linha1);
		ComponenteFormater.formataJPanel(linha2);
		ComponenteFormater.formataJPanel(linha3);
		ComponenteFormater.formataJPanel(linha4);
		ComponenteFormater.formataJPanel(linha5);
		ComponenteFormater.formataJPanel(linha6);
		ComponenteFormater.formataJPanel(linha7);
		ComponenteFormater.formataJPanel(linha8);
		ComponenteFormater.formataJPanel(pScroll);
        
		ComponenteFormater.formataJlable(lblDesconto, Color.black, 22);
		ComponenteFormater.formataJlable(lblinter1, Color.black, 22);
		ComponenteFormater.formataJlable(lblinter2, Color.black, 22);
		ComponenteFormater.formataJlable(lblinter3, Color.black, 26);
		ComponenteFormater.formataJlable(lblMuseu, Color.black, 22);
		ComponenteFormater.formataJlable(lblTicket, Color.black, 22);
		ComponenteFormater.formataJlable(lblValorIngresso, Color.black, 22);
		ComponenteFormater.formataJlable(lblValorAPagar, Color.black, 26);
		
		ComponenteFormater.formataJlable(exposicao, Color.GREEN, 28);
		ComponenteFormater.formataJlable(valorAPagar, Color.BLUE, 32);
		ComponenteFormater.formataJlable(valorIngresso, Color.BLUE, 24);
		ComponenteFormater.formataJlable(desconto, Color.BLUE, 24);
		ComponenteFormater.formataJlable(qtdade, Color.BLACK, 22);
		
		ComponenteFormater.formataJButton(btnMais);
		ComponenteFormater.formataJButton(btnMenos);
		ComponenteFormater.formataJButton(btnVender);
		
		ComponenteFormater.formataJRadioButton(opcao1, Color.GREEN, 20);
		ComponenteFormater.formataJRadioButton(opcao2, Color.GREEN, 20);
		ComponenteFormater.formataJRadioButton(opcao3, Color.DARK_GRAY, 20);
		
		exposicao.setHorizontalAlignment(JLabel.CENTER);
		exposicao.setVerticalAlignment(JLabel.CENTER);
		lblinter1.setHorizontalAlignment(JLabel.CENTER);
		lblinter1.setVerticalAlignment(JLabel.CENTER);
		lblinter3.setHorizontalAlignment(JLabel.CENTER);
		lblinter3.setVerticalAlignment(JLabel.CENTER);
		
		btnMais.setFont(new Font("Arial", Font.BOLD, 36));
		btnMenos.setFont(new Font("Arial", Font.BOLD, 36));
		
		qtdade.setText("1");
		
		painelLeft.add(painelNorte, BorderLayout.NORTH);
		painelLeft.add(pScroll, BorderLayout.CENTER);
		painelLeft.add(lblinter3, BorderLayout.SOUTH);

		painelRight.add(exposicao,BorderLayout.NORTH);
		painelRight.add(painelValores,BorderLayout.CENTER);
		painelRight.add(btnVender,BorderLayout.SOUTH);
		
		painelPrincipal.add(painelLeft);
		painelPrincipal.add(painelRight);
		
		tabela.getSelectionModel().addListSelectionListener(this);
		opcao1.addActionListener(this);
		opcao2.addActionListener(this);
		opcao3.addActionListener(this);
		btnMais.addActionListener(this);
		btnMenos.addActionListener(this);
		btnVender.addActionListener(this);
	}
	private void calcDesc50(){
		double valor = calcValorAPagar();
		valor /= 2;
		desconto.setText("" + valor);
		valorAPagar.setText("" + valor);
	}
	private void gratuidade(){
		desconto.setText("" + calcValorAPagar());
		valorAPagar.setText("GRATUITO");
	}
	private void ingressoNormal(){
		desconto.setText("SEM DESCONTO");
		valorAPagar.setText("" + calcValorAPagar());
	}
	public double calcValorAPagar(){
		double valor = Double.parseDouble(valorIngresso.getText());
		double qdade = Double.parseDouble(qtdade.getText());
		return qdade * valor;
	}
	public Ingresso formToIngresso(){
		Ingresso ingresso = new Ingresso();
		ingresso.setNome(exposicao.getText());
		if(desconto.getText().equals("SEM DESCONTO")){
			ingresso.setDesconto(0);
		}else{
			ingresso.setDesconto(Double.parseDouble(desconto.getText()));
		}
		ingresso.setGratuito(opcao2.isSelected());
		ingresso.setMeiaEntrada(opcao1.isSelected());
		ingresso.setQtdade(Integer.parseInt(qtdade.getText()));
		ingresso.setValor(Double.parseDouble(valorIngresso.getText()));
		if (valorAPagar.getText().equals("GRATUITO")){
			ingresso.setValorPagar(0);
		}else{
			ingresso.setValorPagar(Double.parseDouble(valorAPagar.getText()));
		}
		return ingresso;
	}

	private void acao() {
		if (opcao1.isSelected())calcDesc50();
		else if (opcao2.isSelected()) gratuidade();
		else if (opcao3.isSelected()) ingressoNormal();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == opcao1){
			calcDesc50();
		} else if (e.getSource() == opcao2){
			gratuidade();
		} else if (e.getSource() == opcao3){
			ingressoNormal();
		} else if (e.getSource() == btnMais){
			int valor = Integer.parseInt(qtdade.getText());
			valor++;
			qtdade.setText("" + valor);
			acao();
		} else if (e.getSource() == btnMenos){
			int valor = Integer.parseInt(qtdade.getText());
			if (valor > 1){
				valor --;
				qtdade.setText("" + valor);				
			}
			acao();
		} else if (e.getSource() == btnVender){
			if (tabela.getSelectedRow() >= 0){
			control.salvar(formToIngresso());
			Confirmacao confirmacao = new Confirmacao(formToIngresso());
			
			}else{
				JOptionPane.showMessageDialog(painelPrincipal,
						"Escolha uma exposição!", "erro", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		int linha = tabela.getSelectedRow();
		Exposicao exposicao = control.getListaExposicao().get(linha);
		this.exposicao.setText(exposicao.getNome());
		this.valorIngresso.setText("" + exposicao.getValor());
		acao();
	}
}
