/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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
