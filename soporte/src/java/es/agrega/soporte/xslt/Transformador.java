package es.agrega.soporte.xslt;

import javax.xml.transform.Source;

/**
 * Interfaz que ofrece los métodos necesarios para la gestión de transformaciones
 * de ficheros xml por medio de plantillas xslt.
 * 
 * @author pllasso
 */
public interface Transformador {
	
	/**
	 * método que permite realizar la transformación utilizando xslt.
	 * 
	 * @param rutaFicheroXSLT ruta de la plantilla xslt
	 * @param rutaFicheroOrigen ruta del fichero xml origen
	 * @param rutaFicheroDestino ruta del fichero xml destino
	 * @return boolean bandera indicando el resultado de la transformación
	 * @throws Exception
	 */
	public boolean transformar(String rutaFicheroXSLT, String rutaFicheroOrigen, String rutaFicheroDestino) throws Exception;
	
	/**
	 * método que permite realizar la transformación utilizando xslt.
	 * 
	 * @param rutaFicheroXSLT ruta de la plantilla xslt
	 * @param xmlSource fichero xml Origen
	 * @param rutaFicheroDestino ruta del fichero xml destino
	 * @return boolean bandera indicando el resultado de la transformación
	 * @throws Exception
	 */
	public boolean transformar(String rutaFicheroXSLT, Source xmlSource, String rutaFicheroDestino) throws Exception;

}
