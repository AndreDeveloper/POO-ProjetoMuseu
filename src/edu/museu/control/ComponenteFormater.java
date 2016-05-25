package edu.museu.control;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class ComponenteFormater {
	public static void formataJlable(JLabel jLabel, Color cor, int fonte){
		jLabel.setForeground(cor);
		jLabel.setBackground(Color.WHITE);
		jLabel.setFont(new Font("Palatino Linotype", Font.BOLD, fonte));
		jLabel.setBorder(BorderFactory.createEmptyBorder());
		jLabel.setVerticalAlignment(JLabel.CENTER);
		jLabel.setHorizontalAlignment(JLabel.LEFT);
	}
	public static void formataJRadioButton(JRadioButton rb, Color cor, int fonte){
		rb.setForeground(cor);
		rb.setBackground(Color.WHITE);
		rb.setFont(new Font("Palatino Linotype", Font.BOLD, fonte));
		rb.setBorder(BorderFactory.createEmptyBorder());
		rb.setVerticalAlignment(JLabel.CENTER);
		rb.setHorizontalAlignment(JLabel.LEFT);
	}
	public static void formataJTextArea(JTextArea txt, Color cor, int fonte){
		txt.setForeground(cor);
		txt.setBackground(Color.WHITE);
		txt.setFont(new Font("Palatino Linotype", Font.BOLD, fonte));
		txt.setBorder(BorderFactory.createEmptyBorder());
	    txt.setLineWrap(true);
	    txt.setLineWrap(true);
	    txt.setBorder(BorderFactory.createEtchedBorder());
	}
	public static void formataJComboBox(JComboBox<String> combo, Color cor, int fonte){
		combo.setForeground(cor);
		combo.setBackground(Color.WHITE);
		combo.setFont(new Font("Palatino Linotype", Font.BOLD, fonte));
		combo.setBorder(BorderFactory.createEmptyBorder());
	}
	public static void formataJCheckBox(JCheckBox check, Color cor, int fonte){
		check.setForeground(cor);
		check.setBackground(Color.WHITE);
		check.setFont(new Font("Palatino Linotype", Font.BOLD, fonte));
		check.setBorder(BorderFactory.createEmptyBorder());
		check.setVerticalAlignment(JLabel.CENTER);
		check.setHorizontalAlignment(JLabel.LEFT);
	}
	public static void formataJButtonToMenu(JButton button){
		button.setForeground(Color.white);
		button.setBackground(Color.DARK_GRAY);
		button.setFont(new Font("Papyrus", Font.HANGING_BASELINE, 36));
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setVerticalAlignment(JLabel.CENTER);
		button.setHorizontalAlignment(JLabel.CENTER);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	public static void formataJPanelToMenu(JPanel panel){
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.DARK_GRAY);
		panel.setFont(new Font("Palatino Linotype", Font.BOLD, 22));
		panel.setBorder(BorderFactory.createEmptyBorder());
	}
	public static void formataJButton(JButton button){
		button.setForeground(Color.BLUE);
		button.setBackground(Color.WHITE);
		button.setFont(new Font("Palatino Linotype", Font.HANGING_BASELINE, 24));
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setVerticalAlignment(JLabel.CENTER);
		button.setHorizontalAlignment(JLabel.CENTER);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	public static void formataJTable(JTable table){
		table.setForeground(Color.BLUE);
		table.setBackground(Color.WHITE);
		table.setFont(new Font("Palatino Linotype", Font.HANGING_BASELINE, 16));
		table.setBorder(BorderFactory.createEmptyBorder());
		table.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	public static void formataJPanel(JPanel panel){
		panel.setForeground(Color.BLUE);
		panel.setBackground(Color.WHITE);
		panel.setFont(new Font("Palatino Linotype", Font.BOLD, 22));
		panel.setBorder(BorderFactory.createEmptyBorder());
	}
	public static void formataJScrollPane(JScrollPane panel){
		panel.setForeground(Color.BLUE);
		panel.setBackground(Color.WHITE);
		panel.setFont(new Font("Palatino Linotype", Font.BOLD, 22));
		panel.setBorder(BorderFactory.createEmptyBorder());
	}
	
	
	
	
	
	
	
}
