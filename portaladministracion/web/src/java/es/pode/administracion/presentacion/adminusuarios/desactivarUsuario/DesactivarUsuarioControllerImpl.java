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
    	
    	// el idioma será el que tenga en el atributo idioma del usuario
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
    	
    	// el idioma será el que tenga en el atributo idioma del usuario
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