package edu.museu.boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import edu.museu.control.ComponenteFormater;

public class TelaPrincipalBoundary implements ActionListener {
	private JFrame tela = new JFrame("Smart Museu");
	private JLabel lblLogo = new JLabel();
	private JButton btnVisitantes = new JButton("Visitantes");
	private JButton btnLocais = new JButton("Locais");
	private JButton btnObra = new JButton("Obras");
	private JButton btnEmprestimo = new JButton("Emprestimos");
	private JButton btnVenda = new JButton("Ingressos");
	private JButton btnExposicao = new JButton("Exposições");
	private JPanel painelPrincipal = new JPanel(new BorderLayout());
	private JPanel painelMenu = new JPanel();
	private JPanel painelCentro = new JPanel();

	private ObraBoundary obraForm = new ObraBoundary();
	private VisitanteBoundary visitanteForm = new VisitanteBoundary();
	private LocaisBoundary locaisForm = new LocaisBoundary();
	private EmprestimoBoundary emprestimoForm = new EmprestimoBoundary();
	private ExposicaoBoundary exposicaoForm = new ExposicaoBoundary();

	public TelaPrincipalBoundary() {

		FlowLayout layoutMenu = new FlowLayout(FlowLayout.LEFT, 0, 0);
		JPanel menuBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
		painelMenu.setLayout(layoutMenu);
		painelMenu.add(lblLogo);
		menuBotoes.add(btnVisitantes);
		menuBotoes.add(btnExposicao);
		menuBotoes.add(btnObra);
		menuBotoes.add(btnLocais);
		menuBotoes.add(btnEmprestimo);
		menuBotoes.add(btnVenda);
		painelMenu.add(menuBotoes);

		lblLogo.setIcon(new ImageIcon(TelaPrincipalBoundary.class.getResource("/edu/museu/resource/logo.png")));
		ComponenteFormater.formataJlable(lblLogo, Color.white, 22);

		ComponenteFormater.formataJButtonToMenu(btnEmprestimo);
		ComponenteFormater.formataJButtonToMenu(btnExposicao);
		ComponenteFormater.formataJButtonToMenu(btnLocais);
		ComponenteFormater.formataJButtonToMenu(btnObra);
		ComponenteFormater.formataJButtonToMenu(btnVenda);
		ComponenteFormater.formataJButtonToMenu(btnVisitantes);

		btnEmprestimo.addActionListener(this);
		btnExposicao.addActionListener(this);
		btnLocais.addActionListener(this);
		btnObra.addActionListener(this);
		btnVenda.addActionListener(this);
		btnVisitantes.addActionListener(this);

		ComponenteFormater.formataJPanel(painelCentro);
		ComponenteFormater.formataJPanelToMenu(painelMenu);
		ComponenteFormater.formataJPanelToMenu(menuBotoes);
		ComponenteFormater.formataJPanel(painelPrincipal);

		painelPrincipal.add(painelMenu, BorderLayout.NORTH);
		painelPrincipal.add(painelCentro, BorderLayout.CENTER);

		tela.setForeground(Color.blue);
		tela.setBackground(Color.WHITE);
		tela.setContentPane(painelPrincipal);
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tela.setExtendedState(JFrame.MAXIMIZED_BOTH);
		tela.setSize(1100, 700);
		tela.setVisible(true);
	}

	public void selectTela(JPanel jpanel) {
		painelPrincipal.removeAll();
		painelPrincipal.add(painelMenu, BorderLayout.NORTH);

		JScrollPane scrollpane = new JScrollPane();
		scrollpane.setViewportView(jpanel);
		painelPrincipal.add(scrollpane, BorderLayout.CENTER);

		tela.repaint();
		tela.invalidate();
		tela.revalidate();
		scrollpane.invalidate();
		scrollpane.revalidate();

	}

	public static void main(String[] args) {
		new TelaPrincipalBoundary();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnObra) {
			obraForm.getPainelPrincipal().invalidate();
			obraForm.getPainelPrincipal().revalidate();
			selectTela(obraForm.getPainelPrincipal());
		} else if (arg0.getSource() == btnVisitantes) {
			visitanteForm.getPainelPrincipal().invalidate();
			visitanteForm.getPainelPrincipal().revalidate();
			selectTela(visitanteForm.getPainelPrincipal());
		} else if (arg0.getSource() == btnLocais) {
			locaisForm.getPainelPrincipal().invalidate();
			locaisForm.getPainelPrincipal().revalidate();
			selectTela(locaisForm.getPainelPrincipal());
		} else if (arg0.getSource() == btnEmprestimo) {
			emprestimoForm.getPainelPrincipal().invalidate();
			emprestimoForm.getPainelPrincipal().revalidate();
			selectTela(emprestimoForm.getPainelPrincipal());
		} else if (arg0.getSource() == btnExposicao) {
			exposicaoForm.getPainelPrincipal().invalidate();
			exposicaoForm.getPainelPrincipal().revalidate();
			selectTela(exposicaoForm.getPainelPrincipal());
		}

	}

}
