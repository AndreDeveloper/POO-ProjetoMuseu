package edu.museu.boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import edu.museu.control.ComponenteFormater;
import edu.museu.entity.Ingresso;

public class Confirmacao extends SwingWorker{
	private JDialog tela = new JDialog();
	private JPanel painelPrincipal = new JPanel(new GridLayout(1, 2));
	private JPanel painelRight = new JPanel(new BorderLayout());
	private JPanel painelSouth = new JPanel(new GridLayout(1, 1));	
	private JLabel lblLogo = new JLabel();
	private JLabel lblExpo = new JLabel("Exposição");
	private JLabel lblValor = new JLabel("Valor");
	private JLabel lblQtdade = new JLabel("Quantidade");
	private JLabel expo = new JLabel();
	private JLabel valor = new JLabel();
	private JLabel qtdade = new JLabel();
	private JProgressBar barra = new JProgressBar();
	private Ingresso ingresso = new Ingresso();
	
	public Confirmacao(Ingresso ingresso) {
		this.ingresso = ingresso;
		expo.setText(ingresso.getNome());
		valor.setText("" + ingresso.getValorPagar());
		qtdade.setText("" + ingresso.getQtdade());
		this.execute();
		create();
	}
	
	public void create(){
		lblLogo.setIcon(new ImageIcon
				(TelaInicial.class.getResource("/edu/museu/resource/ingresso.png")));
		
		JPanel painelInfo = new JPanel(new GridLayout(3, 1));
		JPanel linha1 = new JPanel(new BorderLayout());
		JPanel linha2 = new JPanel(new BorderLayout());
		JPanel linha3 = new JPanel(new BorderLayout());
		
		linha1.add(lblExpo, BorderLayout.NORTH);
		linha1.add(expo, BorderLayout.CENTER);

		linha2.add(valor, BorderLayout.CENTER);
		linha2.add(lblValor, BorderLayout.NORTH);
		
		linha3.add(lblQtdade, BorderLayout.NORTH);
		linha3.add(qtdade, BorderLayout.CENTER);
		
		painelInfo.add(linha1);
		painelInfo.add(linha2);
		painelInfo.add(linha3);
		
		painelSouth.add(barra);
		
		
		ComponenteFormater.formataJPanel(painelInfo);
		ComponenteFormater.formataJPanel(painelPrincipal);
		ComponenteFormater.formataJPanel(painelRight);
		ComponenteFormater.formataJPanel(painelSouth);
		ComponenteFormater.formataJPanel(linha1);
		ComponenteFormater.formataJPanel(linha2);
		ComponenteFormater.formataJPanel(linha3);

		ComponenteFormater.formataJlable(lblExpo, Color.BLACK, 16);
		ComponenteFormater.formataJlable(lblValor, Color.BLACK, 16);
		ComponenteFormater.formataJlable(lblQtdade, Color.BLACK, 16);
		ComponenteFormater.formataJlable(qtdade, Color.BLUE, 35);
		ComponenteFormater.formataJlable(valor,  Color.BLUE, 35);
		ComponenteFormater.formataJlable(expo,   Color.BLUE, 35);
		
		lblExpo.setHorizontalAlignment(JLabel.CENTER);
		lblQtdade.setHorizontalAlignment(JLabel.CENTER);
		lblValor.setHorizontalAlignment(JLabel.CENTER);
		valor.setHorizontalAlignment(JLabel.CENTER);
		expo.setHorizontalAlignment(JLabel.CENTER);
		qtdade.setHorizontalAlignment(JLabel.CENTER);
		
		barra.setStringPainted(true);
        barra.setValue(0);
        barra.setSize(new Dimension(180, 30));

		painelRight.add(painelInfo, BorderLayout.CENTER);
		painelRight.add(painelSouth, BorderLayout.SOUTH);
		
		painelPrincipal.add(lblLogo);
		painelPrincipal.add(painelRight);
		
		tela.setModal(true);
		tela.setTitle("Imprimindo Ingresso");
		tela.setContentPane(painelPrincipal);
		tela.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		tela.setResizable(false);
		tela.pack();
		tela.setLocationRelativeTo(null);
		tela.setVisible(true);
		
		
	}
	
	public void carrega() throws InterruptedException{
		for (int i = 1; i <= 100; i++) {
            try {
                barra.setValue(i);
                barra.setString("imprimindo " + i + "%");
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
		tela.dispose();
		
	}

	@Override
	protected Object doInBackground() throws Exception {
		try {
			carrega();
		} catch (InterruptedException f) {
			// TODO Auto-generated catch block
			f.printStackTrace();
		}
		return null;
	}

}
