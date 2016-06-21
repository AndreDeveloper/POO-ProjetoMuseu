package edu.museu.boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.ImagingOpException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.museu.control.ComponenteFormater;
import edu.museu.control.ImagemFormater;
import edu.museu.control.ObraControl;
import edu.museu.control.TelaInicialControl;
import edu.museu.infrastructure.ObraDAO;

public class TelaInicial extends Thread{
	private JLabel lblLogo = new JLabel();
	private JPanel painelPrincipal = new JPanel(new BorderLayout());
	private JPanel painelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JPanel painelSul = new JPanel(new GridLayout(2, 1, 10,10));
	private TelaInicialControl control = new TelaInicialControl();
	
	public TelaInicial() {
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
		
		lblLogo.setIcon(new ImageIcon
				(TelaInicial.class.getResource("/edu/museu/resource/inicio.png")));
		lblLogo.setHorizontalAlignment(JLabel.CENTER);
		lblLogo.setVerticalAlignment(JLabel.CENTER);
		
		ComponenteFormater.formataJlable(lblLogo, Color.GRAY, 22);
		ComponenteFormater.formataJPanel(painelPrincipal);
		ComponenteFormater.formataJPanel(painelNorte);
		ComponenteFormater.formataJPanel(painelSul);
		
		painelNorte.add(lblLogo);
		painelPrincipal.add(painelNorte, BorderLayout.NORTH);
		painelPrincipal.add(painelSul, BorderLayout.CENTER);
	}
	
	@Override
	public void run() {
		while(true){
			acao();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void acao(){
		List<ImageIcon> lista = control.getImagens();
		JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.CENTER,10,0));
		JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.CENTER,10,0));
		int x = 0;
		for(ImageIcon icon: lista){
			if (x < 5){
				linha1.add(new JLabel(icon));
				ComponenteFormater.formataJPanel(linha1);
			}else{
				linha2.add(new JLabel(icon));				
				ComponenteFormater.formataJPanel(linha2);
			}
			x++;
		}
		painelSul.removeAll();
		painelSul.add(linha1);
		painelSul.add(linha2);
		painelSul.invalidate();
		painelSul.revalidate();
		painelPrincipal.invalidate();
		painelPrincipal.revalidate();
	}
	
}
