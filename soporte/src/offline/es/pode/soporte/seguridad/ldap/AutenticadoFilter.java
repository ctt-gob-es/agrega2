package es.pode.soporte.seguridad.ldap;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.acegisecurity.AuthenticationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

/** 
 * Clase filtro para marcar al usuario que ha sido auntenticado en algún módulo 
 * y para actualizar el perfil del usuario en la sesión activa.
 * 
 * @author jsimon
 */

public class AutenticadoFilter implements Filter {
	private static Logger logger = Logger.getLogger(AutenticadoFilter.class);
	
	public AutenticadoFilter() {
	}

	public void init(FilterConfig filterconfig) throws ServletException {
	}

	public void destroy() {
	}

	/**
	 * Filtro vacio que sustituye para la versión off-line de las herramientas.
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, AuthenticationException {
		Logger.getLogger(AutenticadoFilter.class).info("AutenticadoFilter: no hace nada.");
		chain.doFilter(request, response);
	}

}