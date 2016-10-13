/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.administracion.presentacion.adminusuarios.desactivarUsuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.UsuarioVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.administracion.presentacion.adminusuarios.desactivarUsuario.DesactivarUsuarioController
 */
public class DesactivarUsuarioControllerImpl extends DesactivarUsuarioController
{



	private static Logger log = Logger.getLogger(DesactivarUsuarioControllerImpl.class);



    /**
     * @see es.pode.administracion.presentacion.adminusuarios.desactivarUsuario.DesactivarUsuarioController#desactivarUsuario(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.adminusuarios.desactivarUsuario.DesactivarUsuarioForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void desactivarUsuario(ActionMapping mapping, es.pode.administracion.presentacion.adminusuarios.desactivarUsuario.DesactivarUsuarioForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
    	// el idioma ser� el que tenga en el atributo idioma del usuario
    	// que esta en sesion.
    	try {
    	    log("Obtenemos el usuario que se desea desactivar");
    	    Long id = Long.valueOf(request.getParameter("id"));
    	    String login = LdapUserDetailsUtils.getLogin();
    	    this.getSrvAdminUsuariosService().desactivarUsuario(id, login);
    	    form.setResultado("desactivarUsuario.OK");
    	} catch (Exception e) {
    	    log.error("Se ha producido un Error al desactivar el usuario: " + e);
    	    form.setResultado("desactivarUsuario.FALLO");
    	}
     }




    private void log(Object obj) {
    	
    	if (log.isDebugEnabled())
    	    log.debug(obj);
        }



    /**
     * @see es.pode.administracion.presentacion.adminusuarios.desactivarUsuario.DesactivarUsuarioController#getUsuario(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.adminusuarios.desactivarUsuario.GetUsuarioForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void getUsuario(ActionMapping mapping, es.pode.administracion.presentacion.adminusuarios.desactivarUsuario.GetUsuarioForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
    	// el idioma ser� el que tenga en el atributo idioma del usuario
    	// que esta en sesion.
    	try {
     	   
    	    Long id = Long.valueOf(request.getParameter("id"));
    	    UsuarioVO usuarioVO = this.getSrvAdminUsuariosService().descripcionUsuario(id);
    	    log("Obtenemos el usuario que se desea desactivar");
    	    form.setUsuario(usuarioVO.getUsuario());
    	} catch (Exception e) {
    	    log.error("Error: " + e);
    	    throw new ValidatorException("{errors.getUsuarioInactivo}");
    	}
     }









}