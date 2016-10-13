package es.pode.buscar.negocio.buscar.comparators;

import java.io.Serializable;
import java.util.Comparator;

import es.pode.indexador.negocio.servicios.busqueda.TaxonVO30;

public class TaxonNombreComparator implements Comparator, Serializable {
	
	public int compare(Object o1, Object o2) {
		return ((TaxonVO30) o1).getNombre().compareTo(((TaxonVO30) o2).getNombre());
	}

}
