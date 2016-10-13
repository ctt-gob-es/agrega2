/**
 * 
 */
package es.pode.gestorFlujo.presentacion.utilidades;

import java.io.Serializable;
import java.util.Comparator;

import es.pode.gestorFlujo.presentacion.objetosPublicados.TransicionUrlVO;

/**
 * @author dgonzalezd
 *
 */
public class TransicionUrlVOComparator implements Comparator<TransicionUrlVO>, Serializable {

	@Override
	public int compare(TransicionUrlVO o1, TransicionUrlVO o2) {
		//Invierto el orden de comparación para mantener orden de versión anterior
		return Long.valueOf(o2.getFecha().getTimeInMillis()).compareTo(o1.getFecha().getTimeInMillis());
	}
	

}
