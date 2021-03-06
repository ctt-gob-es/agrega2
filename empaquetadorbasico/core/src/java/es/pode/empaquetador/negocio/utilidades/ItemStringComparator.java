/**
 * 
 */
package es.pode.empaquetador.negocio.utilidades;

import java.io.Serializable;
import java.util.Comparator;

import es.pode.parseadorXML.castor.Item;

/**
 * @author dgonzalezd
 *
 */
public class ItemStringComparator implements Comparator, Serializable {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object o1, Object o2) {
		return ((Item) o1).getIdentifier().compareTo((String) o2);
	}

}
