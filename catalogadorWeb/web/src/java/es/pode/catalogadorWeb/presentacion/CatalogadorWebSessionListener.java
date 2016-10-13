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
