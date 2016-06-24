package edu.museu.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

import edu.museu.entity.Obra;
import edu.museu.infrastructure.ObraDAO;

public class TelaInicialControl {
	private ObraDAO dao = new ObraDAO();
	private List<ImageIcon> listaImagens = dao.selectAllImages();

	public List<ImageIcon> getImagens() throws java.lang.ClassCastException {
		List<ImageIcon> lista = new ArrayList<ImageIcon>();

		Random random = new Random();
		for (int x = 0; x < 10; x++) {
			int i = random.nextInt(listaImagens.size());
			while (lista.contains(listaImagens.get(i))) {
				i = random.nextInt(listaImagens.size());
			}
			lista.add(listaImagens.get(i));
		}
		return lista;

	}
}
