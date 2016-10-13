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
