package edu.museu.control;

import edu.museu.entity.Local;
import edu.museu.entity.Obra;
import edu.museu.entity.Visitante;

public interface Subject {
	void addObserver(Observer o);
	void removeObserver(Observer o);
	void notificar(String noticia);
	void notificar(Obra obra);
	void notificar(Visitante visitante);
	void notificar(Local local);
}
