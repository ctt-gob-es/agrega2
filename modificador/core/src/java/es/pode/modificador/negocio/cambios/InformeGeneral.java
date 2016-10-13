/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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
	 * @param path Ruta donde se generar� el informe general
	 * @return �xito o no de la generaci�n
	 */
	public boolean generar(ODE[] odes, Appender informe, String path);
}
