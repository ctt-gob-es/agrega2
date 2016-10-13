// license-header java merge-point
package es.pode.visualizador.presentacion.solicitarBajaUsuario;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;
import es.pode.adminusuarios.negocio.servicios.UsuarioVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @see es.pode.administracion.presentacion.adminusuarios.solicitarBajaUsuario.SolicitarBajaUsuarioController
 */
public class SolicitarBajaUsuarioControllerImpl extends SolicitarBajaUsuarioController {

	private static Logger log = Logger.getLogger(SolicitarBajaUsuarioControllerImpl.class);

		
	/**
	 * @see es.pode.administracion.presentacion.adminusuarios.solicitarBajaUsuario.SolicitarBajaUsuarioController#enviarCorreoBaja(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.adminusuarios.solicitarBajaUsuario.EnviarCorreoBajaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public final void enviarCorreoBaja(ActionMapping mapping,
			es.pode.visualizador.presentacion.solicitarBajaUsuario.EnviarCorreoBajaForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	   	String usuario = LdapUserDetailsUtils.getUsuario();
		UsuarioVO usuarioSesion = this.getSrvAdminUsuariosService().obtenerDatosUsuario(usuario);
		String resultado = this.getSrvAdminUsuariosService().enviarCorreoBaja(usuarioSesion);
		log("El resultado del envio es " + resultado);
		form.setResultadoEnvio(resultado);
	}

	private void log(Object obj) {
		if (log.isDebugEnabled())
			log.debug(obj);
	}

}