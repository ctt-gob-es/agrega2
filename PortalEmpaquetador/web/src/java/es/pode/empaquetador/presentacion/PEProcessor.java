package es.pode.empaquetador.presentacion;

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
		EmpaquetadorSession empSession = (EmpaquetadorSession)session.getAttribute(EmpaquetadorSession.SESSION_KEY);
		if(logger.isDebugEnabled()) 
			logger.debug("Checkeando session caducada. Existe empSession? " + (empSession!=null));
		logger.debug("Checkeando session caducada. Existe empSession? " + (empSession!=null));
		boolean result = true;
		if(!(request.getServletPath().endsWith("/InicioEmpaquetador.do") 
				|| request.getServletPath().startsWith("/SesionCaducada"))) {
			if(empSession==null /*|| empSession.getOde()==null*/) {
				logger.info("No se ha encontrado el ODE en la session");
				logger.info("ASC - PEPROCESSOR DENTRO DE RUTA CHEQUEADA");
				try {
					String redirect = request.getContextPath() + "/SesionCaducada/SesionCaducada.do";
					//URLs tipo host/agrega: getContextPath ya incluye subdominios, no es necesario concatenarlo.
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
