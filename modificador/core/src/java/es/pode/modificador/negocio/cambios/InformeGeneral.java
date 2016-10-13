/**
 * 
 */
package es.pode.modificador.negocio.cambios;

import org.apache.log4j.Appender;

/**
 * @author dgonzalezd
 *
 */
public interface InformeGeneral {
	/**
	 * 
	 * @param odes ODEs a los que debe referenciar el informe general
	 * @param informe Appender que se incluira en el Logger de cada clase para
	 *            escribir mensajes al fichero solicitado. Si es null, se
	 *            escribira a los appender por defecto.
	 * @param path Ruta donde se generará el informe general
	 * @return éxito o no de la generación
	 */
	public boolean generar(ODE[] odes, Appender informe, String path);
}
