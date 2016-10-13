/**
 * 
 */
package es.pode.gestorFlujo.presentacion.utilidades;

import java.io.Serializable;
import java.util.Comparator;

import es.pode.gestorFlujo.presentacion.objetosPublicadosAutonomos.TransicionAutopublicadosVO;

/**
 * @author dgonzalezd
 *
 */
public class TransicionAutopublicadosVOComparator implements Comparator<TransicionAutopublicadosVO>, Serializable {

	@Override
	public int compare(TransicionAutopublicadosVO o1,
			TransicionAutopublicadosVO o2) {
		//Invierto el orden de comparaci�n para mantener orden de versi�n anterior
		return Long.valueOf(o2.getFecha().getTimeInMillis()).compareTo(o1.getFecha().getTimeInMillis());
	}

}
