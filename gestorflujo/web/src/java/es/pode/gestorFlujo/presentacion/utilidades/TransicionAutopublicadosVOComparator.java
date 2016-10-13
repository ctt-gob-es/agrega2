/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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
		//Invierto el orden de comparación para mantener orden de versión anterior
		return Long.valueOf(o2.getFecha().getTimeInMillis()).compareTo(o1.getFecha().getTimeInMillis());
	}

}
