/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * 
 */
package es.pode.catalogadorWeb.presentacion;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;


/**
 * @author aschicon
 * 
 */
public class CatalogadorWebSessionListener implements HttpSessionListener {

	private static Logger logger = Logger
			.getLogger(CatalogadorWebSessionListener.class);


	/**
	 * Realiza las operaciones necesarias en el inicio de una session.
	 */
	public void sessionCreated(HttpSessionEvent arg0) {
		logger.info("Sesion iniciada en módulo CatalogadorWeb. ");
		
	}

	/**
	 * <p>
	 * Es invocado cuando una sesion se destruye.
	 * </p>
	 */
	public void sessionDestroyed(HttpSessionEvent arg0) {
		logger.info("Sesion destruida en CatalogadorWeb. ");
	}

	

}
