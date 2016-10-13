/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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
