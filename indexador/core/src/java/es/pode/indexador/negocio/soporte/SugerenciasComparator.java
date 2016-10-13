package es.pode.indexador.negocio.soporte;

import java.io.Serializable;
import java.util.Comparator;

import es.pode.indexador.negocio.servicios.busqueda.SugerenciasVO;

public class SugerenciasComparator implements Comparator, Serializable{

	public int compare(Object o1, Object o2) {
		if(((SugerenciasVO)o1).getNumOdes() > ((SugerenciasVO)o2).getNumOdes())
			return -1;
		else
			return 1;
	}

}
