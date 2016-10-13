/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * 
 */
package es.pode.modificador.presentacion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.tiles.TilesRequestProcessor;

import es.pode.modificador.presentacion.configurar.ConfigurarModificacionSession;
import es.pode.modificador.presentacion.informes.InformeSession;

/**
 * @author dgonzalezd
 *
 */
public class PEProcessor extends TilesRequestProcessor {

	private static Logger logger = Logger.getLogger(PEProcessor.class);
	
	/* (non-Javadoc)
	 * @see org.apache.struts.action.RequestProcessor#processPreprocess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected boolean processPreprocess(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		ConfigurarModificacionSession modSession= (ConfigurarModificacionSession)session.getAttribute(ConfigurarModificacionSession.SESSION_KEY);
		InformeSession infSession= (InformeSession)session.getAttribute(InformeSession.SESSION_KEY);
		if(logger.isDebugEnabled()) {
			logger.debug("Checkeando session caducada. Existe session ConfigurarModificacion? " + (modSession!=null));
			logger.debug("Checkeando session caducada. Existe session Informe? " + (infSession!=null));
			if(request!=null && request.getSession()!=null) logger.debug("Checkeando session caducada. Existe atributo offline? " + (request.getSession().getAttribute("offline")));
		}
		boolean result = true;
		String path = request.getServletPath();
		
		//
		//  Caso 1: offline en sesion es null y estamos en CU que no es Inicio Mod o Sesion caducada -> Redirect a sesion caducada
		
		if(request==null || request.getSession()==null || request.getSession().getAttribute("offline")==null){
			if(!(path.equals("/ModificacionesPendientes/ModificacionesPendientes.do") || request.getServletPath().startsWith("/SesionCaducada"))){
				/*
				 * Para poder pintar correctamente el layout del mensaje de
				 * error introduzco un atributo en la sesion indicando si la
				 * versión en on-line o off-line. Uso un nombre de atributo
				 * diferente para evitar que PEPRocessor considere que la sesion
				 * no ha caducado en caso de navegar para atras o pinchar en
				 * otro link
				 */
				request.getSession().setAttribute("scOffline", DecisorOffline.esOffline());
				result = redirect(request, response);
			}
		}
	
		return result;
	}

	private boolean redirect(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result;
		logger.debug("No se ha encontrado la session");
		try {
			String redirect = request.getContextPath() + "/SesionCaducada/SesionCaducada.do";
			if(logger.isDebugEnabled()) logger.debug("Redirigiendo a " + redirect);
			result = false;
			response.sendRedirect(redirect);
		} catch (Exception e) {
			logger.error("Error en el redirect de sesion caducada",e);
			result = false;
		}
		return result;
	}

}

