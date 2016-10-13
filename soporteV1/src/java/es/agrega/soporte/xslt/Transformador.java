package es.agrega.soporte.xslt;

import javax.xml.transform.Source;

/**
 * Interfaz que ofrece los m�todos necesarios para la gesti�n de transformaciones
 * de ficheros xml por medio de plantillas xslt.
 * 
 * @author pllasso
 */
public interface Transformador {
	
	/**
	 * m�todo que permite realizar la transformaci�n utilizando xslt.
	 * 
	 * @param rutaFicheroXSLT ruta de la plantilla xslt
	 * @param rutaFicheroOrigen ruta del fichero xml origen
	 * @param rutaFicheroDestino ruta del fichero xml destino
	 * @return boolean bandera indicando el resultado de la transformaci�n
	 * @throws Exception
	 */
	public boolean transformar(String rutaFicheroXSLT, String rutaFicheroOrigen, String rutaFicheroDestino) throws Exception;
	
	/**
	 * m�todo que permite realizar la transformaci�n utilizando xslt.
	 * 
	 * @param rutaFicheroXSLT ruta de la plantilla xslt
	 * @param xmlSource fichero xml Origen
	 * @param rutaFicheroDestino ruta del fichero xml destino
	 * @return boolean bandera indicando el resultado de la transformaci�n
	 * @throws Exception
	 */
	public boolean transformar(String rutaFicheroXSLT, Source xmlSource, String rutaFicheroDestino) throws Exception;

}
