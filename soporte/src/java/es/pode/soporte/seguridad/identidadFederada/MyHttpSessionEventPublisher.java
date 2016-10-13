/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.soporte.seguridad.identidadFederada;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.ui.session.HttpSessionCreatedEvent;
import org.acegisecurity.ui.session.HttpSessionDestroyedEvent;
import org.acegisecurity.ui.session.HttpSessionEventPublisher;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;


public class MyHttpSessionEventPublisher extends HttpSessionEventPublisher implements ServletContextListener,
HttpSessionAttributeListener, HttpSessionListener {
	private static Logger logger = Logger.getLogger(MyHttpSessionEventPublisher.class);
//    private UserContext userContext;
    private ApplicationContext appContext;
    private ServletContext servletContext = null;
    private static final String ACEGI_SECURITY_CONTEXT = "ACEGI_SECURITY_CONTEXT";
    
    
    public void contextInitialized(ServletContextEvent event) {
    	appContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());

    	if (appContext == null) {
    		logger.debug("Contexto de la aplicacion appContext nulo. Hasta que se use por vez primera se retrasara su inicializacion.");
    	}
    	servletContext = event.getServletContext();
    	}

    	ApplicationContext getContext() {
    	if (appContext == null) {
    	appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
    	}

    	return appContext;
    	}
    
    public void sessionDestroyed(HttpSessionEvent event) {
      // logger.debug("sessionDestroyed");
       HttpSessionDestroyedEvent e = new HttpSessionDestroyedEvent(event.getSession());
       if(null == servletContext) {
       servletContext = event.getSession().getServletContext();
       }
      
              
    }
    
	public void sessionCreated(HttpSessionEvent event)
	{
		HttpSessionCreatedEvent e = new HttpSessionCreatedEvent(event.getSession());
		if(null == servletContext) {
		servletContext = event.getSession().getServletContext();
		}
		
		//logger.debug("Publishing event: " + e);
		Enumeration enumeracion = e.getSession().getAttributeNames();
		/*
		while (enumeracion.hasMoreElements())
		{
			logger.debug("Atributo de la sesion "+enumeracion.nextElement());
		}
		*/
		 Object contextoSesion = e.getSession().getAttribute("ACEGI_SECURITY_CONTEXT");
		// logger.debug("ContextoSesion "+contextoSesion);
		getContext().publishEvent(e);
		}
	
	 /**
     * Record the fact that a servlet context attribute was added.
     *
     * @param event The session attribute event
     */
    public void attributeAdded(HttpSessionBindingEvent event) {

	  //logger.debug("attributeAdded('" + event.getSession().getId() + "', '" +
	 // event.getName() + "', '" + event.getValue() + "')");
	//Si el atribute es del tipo ACEGI_SECURITY_CONTEXT tengo que anyadir en la hashmap de las sesiones la nueva sesion
	  if(((event.getName()).equalsIgnoreCase(ACEGI_SECURITY_CONTEXT)) && ((AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.IDENTIDAD_FEDERADA))).equalsIgnoreCase("true"))
	  {
	    	 SecurityContext securityContext = (SecurityContext)event.getValue();
	      //   logger.debug("securityContext " + securityContext);
	         String usuario = securityContext.getAuthentication().getName();
	         logger.debug("usuario del que se va a anyadir su sesion <"+securityContext.getAuthentication().getName() + ">");
	         Boolean resultado = WrapperSrvIdentidadFederada.addSesionFederada(usuario);
	         logger.debug("resultado tras anyadir la sesion del usuario <"+resultado+">"); 
	  }
    }


    /**
     * Record the fact that a servlet context attribute was removed.
     *
     * @param event The session attribute event
     */
    public void attributeRemoved(HttpSessionBindingEvent event) {

    	// logger.debug("attributeRemoved('" + event.getSession().getId() + "', '" +
    	// event.getName() + "', '" + event.getValue() + "')");
    	 //Elimino del registro de sesiones la sesion del usuario que se acaba de invalidar
    	 if(((event.getName()).equalsIgnoreCase(ACEGI_SECURITY_CONTEXT)) && ((AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.IDENTIDAD_FEDERADA))).equalsIgnoreCase("true"))
    	 {
			 /*
	    	 SecurityContext securityContext = (SecurityContext)event.getValue();
	        // logger.debug("securityContext " + securityContext);
	    	 if(!(securityContext.getAuthentication() == null))
	    	 {
	         String usuario = securityContext.getAuthentication().getName();
	         logger.debug("usuario del que se va a eliminar su sesion "+securityContext.getAuthentication().getName());
	         Boolean resultado = WrapperSrvIdentidadFederada.eliminarSesionFederada(usuario);
	         logger.debug("resultado de la eliminacion de la sesion del usuario {"+resultado+"}");
	    	 }
			 */
    	 }

    }


    /**
     * Record the fact that a servlet context attribute was replaced.
     *
     * @param event The session attribute event
     */
    public void attributeReplaced(HttpSessionBindingEvent event) {
    	// logger.debug("attributeReplaced('" + event.getSession().getId() + "', '" +
    //  event.getName() + "', '" + event.getValue() + "')");

    }

	public void contextDestroyed(ServletContextEvent arg0)
	{

	}
}
