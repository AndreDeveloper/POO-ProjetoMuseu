package edu.museu.control;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.museu.entity.Obra;
import edu.museu.infrastructure.ObraDAO;

public class ObraControl {
	public void carregaImagem(JLabel imagem){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "png","PNG", "jpeg", "JPEG");
		fileChooser.setFileFilter(filter);
		int resposta = fileChooser.showOpenDialog(imagem);
		
		if (resposta == JFileChooser.APPROVE_OPTION){
			java.io.File file = fileChooser.getSelectedFile();
			String Path = file.getAbsolutePath();
			//formLivro.setLivroPath(Path);
			imagem.setText("");
			ImageIcon image = new ImageIcon(Path);
			byte[] b = ImagemFormater.imagemParaByte(image);
			image = ImagemFormater.bytesParaImagem(b);
			imagem.setIcon(
					image
					);
			imagem.repaint();
			imagem.invalidate();
			imagem.revalidate();
		}
	}
	public void salvar(Obra obra){
		ObraDAO dao = new ObraDAO();
		if (dao.insert(obra) < 1){
			JOptionPane.showMessageDialog(null, "Erro ao inserir cadastro!", null, JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);			
		}
	}
	public void alterar(Obra obra){
		ObraDAO dao = new ObraDAO();
		if (dao.update(obra) < 1){
			JOptionPane.showMessageDialog(null, "Erro ao alterar cadastro!", null, JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);			
		}
	}
	public void deletar(Obra obra){
		ObraDAO dao = new ObraDAO();
		if (dao.delete(obra.getId()) < 1){
			JOptionPane.showMessageDialog(null, "Erro ao excluir cadastro!", null, JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Cadastro excluido com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);			
		}
	}
	public ImageIcon getImage(Long id){
		ObraDAO dao = new ObraDAO();
		return dao.selectImageById(id);
	}
}
