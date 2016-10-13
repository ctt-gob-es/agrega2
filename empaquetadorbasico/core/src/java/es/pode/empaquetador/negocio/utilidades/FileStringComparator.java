/**
 * 
 */
package es.pode.empaquetador.negocio.utilidades;

import java.io.Serializable;
import java.util.Comparator;

import es.pode.parseadorXML.castor.File;

/**
 * @author dgonzalezd
 *
 */
public class FileStringComparator implements Comparator, Serializable {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object o1, Object o2) {
		return ((File) o1).getHref().compareTo((String) o2);
	}

}
