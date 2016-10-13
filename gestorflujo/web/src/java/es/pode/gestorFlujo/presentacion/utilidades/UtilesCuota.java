/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.gestorFlujo.presentacion.utilidades;

import org.apache.log4j.Logger;

import es.pode.soporte.cuota.Cuota;

public class UtilesCuota {
	private static Logger logger = Logger.getLogger(UtilesCuota.class);
	
	
	
	public static String parseaCuota (Long tamanio){
		if(logger.isDebugEnabled()) 	logger.debug("taminoOdes[i]"+tamanio);
		double div = (double)tamanio/(1024*1024);
		String pattern="###.##";
		java.text.DecimalFormat myFormatter = new java.text.DecimalFormat(pattern);
		String output = myFormatter.format(div);
		return output;
	}
	
	public static long suma (Long[] tamanioOdesPersonales, Long[] tamanioOdesPubliAutonomos){
		
		long suma = 0;
		long sumaPersonales = Cuota.totalTamainoOdes(tamanioOdesPersonales);
		long sumaAutonomos = Cuota.totalTamainoOdes(tamanioOdesPubliAutonomos);
		suma = sumaPersonales + sumaAutonomos;
		return suma;
	}

}
