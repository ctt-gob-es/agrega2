package es.agrega.soporte.filter;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.soporte.constantes.ConstantesAgrega;

public class HelpFilter implements Filter {
	private static Logger logger = Logger.getLogger(HelpFilter.class);
	public static final String HELP_URL_KEY = "HELP_URL";
	protected FilterConfig fc = null;
	
	public void destroy() {
	    this.fc = null;

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException 
	{
		String sUrl = null;
		// Chequeo si la request es HttpServeltRequest y si lo es obtengo la URI
		if (request != null && request instanceof HttpServletRequest) 
		{
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			sUrl = httpRequest.getRequestURI();
			if (logger.isDebugEnabled())
				logger.debug("La URL de ayuda es = " + sUrl);
			if (request != null && ((HttpServletRequest)request).getSession()!=null && ((Locale)((HttpServletRequest)request).getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE))!=null && ((Locale)((HttpServletRequest)request).getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage()!=null){
				if(((Locale)((HttpServletRequest)request).getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage().equals("es"))
					sUrl = fc.getInitParameter("HELP_URL")+sUrl;
				else
					sUrl = fc.getInitParameter("HELP_URL").replaceFirst("/wiki", "/wiki-"+((Locale)((HttpServletRequest)request).getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage())+sUrl;
			}else
				sUrl = fc.getInitParameter("HELP_URL")+sUrl;
			
			/*
			 * Debemos concatener el subdominio
			 * 
			 */
			sUrl = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SUBDOMINIO) + sUrl;
			
			if (logger.isDebugEnabled())
				logger.debug("La URL de ayuda final es = " + sUrl);
		}
		//Tengo que coger la URL de los parámetros del filtro y sumarle la URI
		// e introducirla en un atributo
		request.setAttribute(HelpFilter.HELP_URL_KEY, sUrl);
		/// pass the request/response on
	    chain.doFilter(request, response);

	}

	public void init(FilterConfig filterConfig) throws ServletException {
	    this.fc = filterConfig;

	}

}
