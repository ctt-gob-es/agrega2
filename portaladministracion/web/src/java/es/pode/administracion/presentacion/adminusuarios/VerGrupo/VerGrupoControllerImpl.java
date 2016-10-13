// license-header java merge-point
package es.pode.administracion.presentacion.adminusuarios.VerGrupo;


import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.GrupoVO;
import es.pode.adminusuarios.negocio.servicios.RolVO;


/**
 * @see es.pode.administracion.presentacion.adminusuarios.VerGrupo.VerGrupoController
 */
public class VerGrupoControllerImpl extends VerGrupoController {

	private static Logger log = Logger.getLogger(VerGrupoControllerImpl.class);

	

	/**
	 * @see es.pode.administracion.presentacion.adminusuarios.VerGrupo.VerGrupoController#recuperarDescripcion(org.apache.struts.action.ActionMapping,
	 *      es.pode.administracion.presentacion.adminusuarios.VerGrupo.RecuperarDescripcionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public final void recuperarDescripcion(
			ActionMapping mapping,
			es.pode.administracion.presentacion.adminusuarios.VerGrupo.RecuperarDescripcionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		try {
			
			Long id = Long.valueOf(request.getParameter("id"));
			GrupoVO grupo = this.getSrvAdminUsuariosService().descripcionGrupo(
					id);
			form.setDescripcion(grupo.getDescripcion());
			form.setId(id);
			Set set = new HashSet();
			RolVO[] grupos = this.getSrvAdminUsuariosService()
					.descripcionGrupo(id).getRols();
			for (int i = 0; i < grupos.length; i++) {
				set.add(grupos[i]);
			}
			if(log.isDebugEnabled())log.debug("Tamanio de la lista de roles del grupo seleccionado "
					+ set.size());
			form.setRoles(set);
			
		} catch (Exception e) {
			log.error("Error: " + e);
			throw new ValidatorException("{verGrupo.error}");
		}
	}

}