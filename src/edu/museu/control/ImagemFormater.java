package edu.museu.control;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ImagemFormater {

	public static byte[] imagemParaByte(String path) 
	{
		ByteArrayOutputStream bytesImg = new ByteArrayOutputStream();  
        BufferedImage imagemBuffer;
        byte[] bytes = null;
        try {
			imagemBuffer = ImageIO.read(new File(path));
			ImageIO.write((BufferedImage)imagemBuffer, "png", bytesImg);//seta a imagem para bytesImg  
	        bytesImg.flush();//limpa a variável  
	        bytes = bytesImg.toByteArray();//Converte ByteArrayOutputStream para byte[]   
	        bytesImg.close();//
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "erro: " + e.getMessage());
			return null;
		}
        
		return bytes;
	}
	
	public static byte[] imagemParaByte(ImageIcon imageIcon) 
	{
		ByteArrayOutputStream bytesImg = new ByteArrayOutputStream();  
        byte[] bytes = null;
        try {
			ImageIO.write(toBufferedImage(imageIcon.getImage()), "png", bytesImg);//seta a imagem para bytesImg  
	        bytesImg.flush();//limpa a variável  
	        bytes = bytesImg.toByteArray();//Converte ByteArrayOutputStream para byte[]   
	        bytesImg.close();//
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "erro: " + e.getMessage());
			return null;
		}
        
		return bytes;
	}

	public static ImageIcon bytesParaImagem(byte[] bytes){
		BufferedImage img;
		BufferedImage aux;
		try {
			img = ImageIO.read(new ByteArrayInputStream(bytes));
			aux = new BufferedImage(550, 550, img.getType());//cria um buffer auxiliar com o tamanho desejado  
	        Graphics2D g = aux.createGraphics();//pega a classe graphics do aux para edicao  
	        AffineTransform at = AffineTransform.getScaleInstance((double) 550 / img.getWidth(), (double) 550 / img.getHeight());//cria a transformacao  
	        g.drawRenderedImage(img, at);//pinta e transforma a imagem real no auxiliar 
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "erro: " + e.getMessage());
			return null;
		}
		 
        return new ImageIcon(aux);
	}
	public static ImageIcon bytesParaImagem(byte[] bytes, int x, int y) throws java.lang.ClassCastException {
		BufferedImage img;
		BufferedImage aux;
		try {
			img = ImageIO.read(new ByteArrayInputStream(bytes));
			aux = new BufferedImage(x, y, img.getType());//cria um buffer auxiliar com o tamanho desejado  
			Graphics2D g = aux.createGraphics();//pega a classe graphics do aux para edicao  
			AffineTransform at = AffineTransform.getScaleInstance((double) x / img.getWidth(), (double) y / img.getHeight());//cria a transformacao  
			g.drawRenderedImage(img, at);//pinta e transforma a imagem real no auxiliar 
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "erro: " + e.getMessage());
			return null;
		}
		return new ImageIcon(aux);
	}
	
	public static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
}
