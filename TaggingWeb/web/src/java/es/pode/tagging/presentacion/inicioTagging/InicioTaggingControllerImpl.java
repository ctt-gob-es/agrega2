/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.tagging.presentacion.inicioTagging;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.tagging.presentacion.TaggingSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @see es.pode.tagging.presentacion.inicioTagging.InicioTaggingController
 */
public class InicioTaggingControllerImpl extends InicioTaggingController
{


	protected static Logger logger = Logger.getLogger(InicioTaggingControllerImpl.class); 



    /**
     * @see es.pode.tagging.presentacion.inicioTagging.InicioTaggingController#estaAutenticado(org.apache.struts.action.ActionMapping, es.pode.tagging.presentacion.inicioTagging.EstaAutenticadoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final boolean estaAutenticado(ActionMapping mapping, es.pode.tagging.presentacion.inicioTagging.EstaAutenticadoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        
    	String usuario = this.getTaggingSession(request).getUsuario();
    	
		if(usuario !=null)
			return true;
		else
			return false;
    }




    /**
     * @see es.pode.tagging.presentacion.inicioTagging.InicioTaggingController#crearSession(org.apache.struts.action.ActionMapping, es.pode.tagging.presentacion.inicioTagging.CrearSessionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void crearSession(ActionMapping mapping, es.pode.tagging.presentacion.inicioTagging.CrearSessionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        	
    	String usuario = null;
		try{
			usuario = LdapUserDetailsUtils.getUsuario();
			logger.debug("USUARIO CONOCIDO: " + usuario);
		}
		catch(Exception ad){
			logger.debug("USUARIO ANONIMO");
		}
		
    	TaggingSession taggingSession = this.getTaggingSession(request);
    	taggingSession.setUsuario(usuario);
    }









}