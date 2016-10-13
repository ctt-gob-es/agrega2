/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.administracion.presentacion.adminusuarios.activarUsuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.UsuarioVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.administracion.presentacion.adminusuarios.activarUsuario.ActivarUsuarioController
 */
public class ActivarUsuarioControllerImpl extends ActivarUsuarioController
{


	private static Logger log = Logger.getLogger(ActivarUsuarioControllerImpl.class);




    /**
     * @see es.pode.administracion.presentacion.adminusuarios.activarUsuario.ActivarUsuarioController#activarUsuario(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.adminusuarios.activarUsuario.ActivarUsuarioForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void activarUsuario(ActionMapping mapping, es.pode.administracion.presentacion.adminusuarios.activarUsuario.ActivarUsuarioForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
    	// el idioma será el que tenga en el atributo idioma del usuario
    	// que esta en sesion.
    	try {
    	   
    		if(log.isDebugEnabled())log.debug("Obtenemos el usuario que se desea activar");
    	    Long id = Long.valueOf(request.getParameter("id"));
    	    String login = LdapUserDetailsUtils.getLogin();
    	    this.getSrvAdminUsuariosService().activarUsuario(id, login);
    	    form.setResultado("activarUsuario.OK");
    	} catch (Exception e) {
    	    log.error("Se ha producido un Error al activar el usuario: " + e);
    	    form.setResultado("activarUsuario.FALLO");
    	}
     }

	public void getUsuario(ActionMapping mapping, GetUsuarioForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	// el idioma será el que tenga en el atributo idioma del usuario
    	// que esta en sesion.
    	try {
    	   
    	    Long id = Long.valueOf(request.getParameter("id"));
    	    UsuarioVO usuarioVO = this.getSrvAdminUsuariosService().descripcionUsuario(id);
    	    if(log.isDebugEnabled())log.debug("Obtenemos el usuario que se desea activar");
    	    form.setUsuario(usuarioVO.getUsuario());
    	} catch (Exception e) {
    	    log.error("Error: " + e);
    	    throw new ValidatorException("{errors.getUsuarioInactivo}");
    	}
	}
	
  










}