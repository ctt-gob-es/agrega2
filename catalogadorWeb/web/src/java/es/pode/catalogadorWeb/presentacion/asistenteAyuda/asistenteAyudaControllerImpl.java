/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.catalogadorWeb.presentacion.asistenteAyuda;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.catalogadorWeb.presentacion.asistenteAyuda.asistenteAyudaController
 */
public class asistenteAyudaControllerImpl extends asistenteAyudaController
{

	private static Logger logger = Logger.getLogger(asistenteAyudaControllerImpl.class);
    /**
     * @see es.pode.catalogadorWeb.presentacion.asistenteAyuda.asistenteAyudaController#activarAsistente(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.asistenteAyuda.ActivarAsistenteForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void activarAsistente(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.asistenteAyuda.ActivarAsistenteForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        //si está activado se desactiva; si está desactivado se activa
      	if (this.getCatalogadorAvSession(request).isAsistente()){
      		//está activo y lo vams a desactivar
      		this.getCatalogadorAvSession(request).setAsistente(false);
      	} else {
      		//está desactivado y lo vams a activar
      		this.getCatalogadorAvSession(request).setAsistente(true);
      	}
      	this.getCatalogadorAvSession(request).setMensajeAsistente("");
      	//formamos la url de vuelta
    
//      String redirect = "http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+ "/catalogadorWeb" + mapping.findForward("catalogador.avanzado").getPath();
      	String url =  "http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio() + "/" + request.getSession().getServletContext().getInitParameter("url_cat_avanzado");
      	response.sendRedirect(url);
     }


}