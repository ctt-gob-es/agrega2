package es.pode.catalogadorWeb.presentacion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.tiles.TilesRequestProcessor;

import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

public class PEProcessor extends TilesRequestProcessor {

	private static Logger logger = Logger.getLogger(PEProcessor.class);
	
	/* (non-Javadoc)
	 * @see org.apache.struts.action.RequestProcessor#processPreprocess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected boolean processPreprocess(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();	
		CatalogadorAvSession catalogadorAvSession = (CatalogadorAvSession)session.getAttribute("catalogadorAvSession");
		CatalogadorBSession catalogadorBSession =  (CatalogadorBSession)session.getAttribute("catalogadorBSession");
		boolean result = true;
		if ((!(request.getServletPath().endsWith("/InicioCatalogador.do")) ) && 
		   (!(request.getServletPath().endsWith("/VerMetadatos.do"))) && !((request.getServletPath().startsWith("/InicioPortal")))) 
		{
			if ( ((request.getServletPath().contains("/VerMetadatos/")) && (catalogadorAvSession==null)) || 
				 ((catalogadorAvSession==null) && ("avanzado".equals(LdapUserDetailsUtils.getCatalogador()))) || 
				 ((catalogadorBSession==null) &&  ("basico".equals(LdapUserDetailsUtils.getCatalogador()))  && 
				   !( (request.getServletPath().contains("/VerMetadatos/")) || (request.getServletPath().contains("/ExportarLomes/")))) )   
			{ //tenemos q ir a sendRedirect
				
					try {
						String redirect = request.getContextPath() + "/InicioPortal/ErrorJspAceptar.do";
						//URLs tipo host/agrega: No es necesario concatenar el subdominio: getContextPath ya tiene el subdominio incluido.
						redirect="http://"+LdapUserDetailsUtils.getHost()+(redirect.startsWith("/")?"":"/")+redirect;
						if(logger.isDebugEnabled()) logger.debug("Redirigiendo a " + redirect);
						result = false;
						response.sendRedirect(redirect);
					} catch (Exception e) {
						logger.error("Error en el redirect de sesion caducada",e);
						result = false;
					}
			}
		}
		return result;
	}
}
