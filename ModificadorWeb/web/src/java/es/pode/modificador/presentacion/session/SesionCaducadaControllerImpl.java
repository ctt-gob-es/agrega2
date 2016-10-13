/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.modificador.presentacion.session;

import java.util.ResourceBundle;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.modificador.presentacion.DecisorOffline;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.modificador.presentacion.session.SesionCaducadaController
 */
public class SesionCaducadaControllerImpl extends SesionCaducadaController
{

	private static Logger logger = Logger.getLogger(SesionCaducadaControllerImpl.class);

    /**
     * @see es.pode.modificador.presentacion.session.SesionCaducadaController#redirect(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.session.RedirectForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void redirect(ActionMapping mapping, es.pode.modificador.presentacion.session.RedirectForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	//Borramos las cookies
    	Cookie c = new Cookie("JSESSIONID", null);
		c.setPath("/");
		if(logger.isDebugEnabled())logger.debug("despues de fijar el path " + c.getPath());
		c.setMaxAge(0);
		response.addCookie(c);
		if(logger.isDebugEnabled())logger.debug("despues de añadir la cookie a la response");
		
		Cookie cookieAutenticated = new Cookie("AUTENTICADO", null);
		cookieAutenticated.setPath("/");
		if(logger.isDebugEnabled())logger.debug("despues de fijar el path " + cookieAutenticated.getPath());
		cookieAutenticated.setMaxAge(0);
		response.addCookie(cookieAutenticated);
		if(logger.isDebugEnabled())logger.debug("despues de añadir la cookie autenticado a la response");
		if(logger.isDebugEnabled())logger.debug("ASC- Probando Cookies ");
		
		Cookie cookieOpenId = new Cookie("OPENID", null);
		cookieOpenId.setPath("/");
		if(logger.isDebugEnabled())logger.debug("despues de fijar el path " + cookieOpenId.getPath());
		cookieOpenId.setMaxAge(0);
		response.addCookie(cookieOpenId);
		request.getSession().removeAttribute("scOffline");
		boolean estamosOffline=false;
		estamosOffline= DecisorOffline.esOffline();
		if (!estamosOffline) {
		//Para Borrar las Cookies del Cas, se ha hecho una modificacion en él generando un controller y jsp auxiliar
		//desde aqui, tenemos que llamar al nuevo controller del cas, para ello debemos recuperar del fichero authbackend.properties
		//su url, es en el unico fichero en que aparece; http://cas.desarrollo.agrega.indra.es/cas + /logoutAux
		//este controller ira a la jsp que redirige a su vez al portaladministracion
    	ResourceBundle authback=ResourceBundle.getBundle("authbackend");
		String cas_url= authback.getString("cas.http.url");
    	response.sendRedirect(cas_url + "/logoutAux"); //http://cas.desarrollo.agrega.indra.es/cas
		}else {
			String urlOffline= request.getSession().getServletContext().getInitParameter("url_Offline");
		    response.sendRedirect("http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+"/" + urlOffline);	
		}
    	//URL OFFLINE /herramientaoffline o  /herramientaoffline/InicialCU/InicialCU.do 
    }









}