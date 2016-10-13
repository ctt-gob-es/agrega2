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
    	
    	// el idioma ser� el que tenga en el atributo idioma del usuario
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
    	
    	// el idioma ser� el que tenga en el atributo idioma del usuario
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