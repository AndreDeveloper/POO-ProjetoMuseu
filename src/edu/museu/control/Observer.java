package edu.museu.control;

import edu.museu.entity.Local;
import edu.museu.entity.Obra;
import edu.museu.entity.Visitante;

public interface Observer {
	
	void update(String noticia);
	void update(Obra obra);
	void update(Visitante visitante);
	void update(Local local);

}
