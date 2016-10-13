/**
 * 
 */
package es.pode.gestorFlujo.presentacion.utilidades;

import java.io.Serializable;
import java.util.Comparator;

import es.pode.publicacion.negocio.servicios.TransicionVO;

/**
 * @author dgonzalezd
 *
 */
public class TransicionVOComparator implements Comparator<TransicionVO>, Serializable {

	@Override
	public int compare(TransicionVO o1, TransicionVO o2) {
		//Invierto el orden de comparación para mantener orden de versión anterior
		return Long.valueOf(o2.getFecha().getTimeInMillis()).compareTo(o1.getFecha().getTimeInMillis());
	}

}
