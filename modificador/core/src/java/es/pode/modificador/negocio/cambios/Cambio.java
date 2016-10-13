package es.pode.modificador.negocio.cambios;



import org.apache.log4j.Appender;

import es.pode.parseadorXML.castor.Manifest;

public interface Cambio {

	/**
	 * @param manifest
	 * @param informe
	 *            Appender que se incluira en el Logger de cada clase para
	 *            escribir mensajes al fichero solicitado. Si es null, se
	 *            escribira a los appender por defecto.
	 * @param path
	 * @return éxito o no de laejecución
	 */
	public boolean ejecutar(Manifest manifest, Appender informe, String path);

}
