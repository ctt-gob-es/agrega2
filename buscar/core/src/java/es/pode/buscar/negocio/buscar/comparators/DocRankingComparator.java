/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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
