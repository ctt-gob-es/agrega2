// license-header java merge-point
package es.pode.administracion.presentacion.adminusuarios.verUsuarioInactivo;


import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.GrupoVO;
import es.pode.adminusuarios.negocio.servicios.UsuarioVO;



/**
 * @see es.pode.administracion.presentacion.adminusuarios.verUsuarioInactivo.VerUsuarioInactivoController
 */
public class VerUsuarioInactivoControllerImpl extends VerUsuarioInactivoController
{


	private static Logger log = Logger
	.getLogger(VerUsuarioInactivoControllerImpl.class);




    /**
     * @see es.pode.administracion.presentacion.adminusuarios.verUsuarioInactivo.VerUsuarioInactivoController#recuperarUsuario(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.adminusuarios.verUsuarioInactivo.RecuperarUsuarioForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void recuperarUsuario(ActionMapping mapping, es.pode.administracion.presentacion.adminusuarios.verUsuarioInactivo.RecuperarUsuarioForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
	
		try {
			
			Long id = Long.valueOf(request.getParameter("id"));
			log("Recupero los datos del usuario cuyo id es " + id);
			UsuarioVO usuario = this.getSrvAdminUsuariosService()
					.descripcionUsuario(id);
			form.setNombre(usuario.getNombre());
			form.setApellido1(usuario.getApellido1());
			form.setApellido2(usuario.getApellido2());
			form.setUsuario(usuario.getUsuario());
			form.setIdioma(usuario.getIdioma());
			form.setTipoEmpaquetador(usuario.getTipoEmpaquetador());
			form.setEmail(usuario.getEmail());
			form.setNIF(usuario.getNIF());
			form.setIdiomaBusqueda(usuario.getIdiomaBusqueda());
			form.setId(id);
			Set set = new HashSet();
			GrupoVO[] grupos = this.getSrvAdminUsuariosService()
					.descripcionUsuario(id).getGrupos();
			for (int i = 0; i < grupos.length; i++) {
				set.add(grupos[i]);
			}
			log("Tamanio de la lista de grupos del usuario seleccionado "
					+ set.size());
			form.setGrupos(set);
		} catch (Exception e) {
			log.error("[VERUSUARIOINACTIVO]Error: " + e);
			throw new ValidatorException("{verUsuario.error}");
		}
     }



	private void log(Object obj) {
		
		if (log.isDebugEnabled())
			log.debug(obj);
	}

}