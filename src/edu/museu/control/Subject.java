package edu.museu.control;

import edu.museu.entity.Exposicao;
import edu.museu.entity.Local;
import edu.museu.entity.Obra;
import edu.museu.entity.Visitante;

public interface Subject {
	void addObserver(Observer o);
	void removeObserver(Observer o);
	void notificar(Object object);
}
