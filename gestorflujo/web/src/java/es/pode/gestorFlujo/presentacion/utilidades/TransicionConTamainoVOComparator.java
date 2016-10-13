/**
 * 
 */
package es.pode.gestorFlujo.presentacion.utilidades;

import java.io.Serializable;
import java.util.Comparator;

import es.pode.gestorFlujo.presentacion.objetosPersonales.TransicionConTamainoVO;

/**
 * @author dgonzalezd
 *
 */
public class TransicionConTamainoVOComparator implements Comparator<TransicionConTamainoVO>, Serializable {

	@Override
	public int compare(TransicionConTamainoVO o1, TransicionConTamainoVO o2) {
		//Invierto el orden de comparación para mantener orden de versión anterior
		return Long.valueOf(o2.getFecha().getTimeInMillis()).compareTo(o1.getFecha().getTimeInMillis());
	}

}
