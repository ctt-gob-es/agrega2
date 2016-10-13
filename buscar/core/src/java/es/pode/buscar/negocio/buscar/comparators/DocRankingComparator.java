package es.pode.buscar.negocio.buscar.comparators;

import java.io.Serializable;
import java.util.Comparator;

import es.pode.indexador.negocio.servicios.busqueda.DocVO30;


public class DocRankingComparator implements Comparator<DocVO30>, Serializable {
	
	public int compare(DocVO30 o1, DocVO30 o2) {
		if(o2.getNivelAgregacion() < o1.getNivelAgregacion()) return -1;
		if(o2.getNivelAgregacion() > o1.getNivelAgregacion()) return 1;
		if(o2.getValoracion() < o1.getValoracion()) return -1;
		if(o2.getValoracion() > o1.getValoracion()) return 1;
		return (o2.getRanking() < o1.getRanking() ? -1 : (o2.getRanking().equals(o1.getRanking()) ? 0 : 1));
	}

}
