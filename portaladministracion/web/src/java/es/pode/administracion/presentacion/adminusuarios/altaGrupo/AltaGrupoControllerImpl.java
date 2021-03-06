// license-header java merge-point
package es.pode.administracion.presentacion.adminusuarios.altaGrupo;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.GrupoVO;
import es.pode.adminusuarios.negocio.servicios.RolVO;
import es.pode.adminusuarios.negocio.servicios.SrvAdminUsuariosService;

/**
 * @see es.pode.administracion.presentacion.adminusuarios.altaGrupo.AltaGrupoController
 */
public class AltaGrupoControllerImpl extends AltaGrupoController {

    private static Logger log = Logger.getLogger(AltaGrupoControllerImpl.class);

    /**
         * @see es.pode.adminusuarios.presentacion.altaGrupo.AltaGrupoController#altaGrupo(org.apache.struts.action.ActionMapping,
         *      es.pode.adminusuarios.presentacion.altaGrupo.AltaGrupoForm,
         *      javax.servlet.http.HttpServletRequest,
         *      javax.servlet.http.HttpServletResponse)
         * 
         */
    public final void altaGrupo(ActionMapping mapping, AltaGrupoForm form, HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	try {
	    GrupoVO grupoVO = new GrupoVO();
	    AltaGrupoBSession altaGrupoBSession = this.getAltaGrupoBSession(request);
	    grupoVO.setDescripcion(altaGrupoBSession.getDescripcion());
	    String[] ids = form.getIdRowSelectionAsArray();
	    if ((ids == null) || (ids.length == 0)) {

	    	if(log.isDebugEnabled())log.debug("No se ha seleccionado ningun permiso");
		throw new ValidatorException("{errors.altagrupo.roles}");
	    }

	    SrvAdminUsuariosService srvAdminUsuariosService = this.getSrvAdminUsuariosService();
	    RolVO[] rolVO = new RolVO[ids.length];

	    Long idL = null;
	    for (int i = 0; i < ids.length; i++) {
		idL = new Long(ids[i]);
		RolVO rolG = srvAdminUsuariosService.getRol(idL);

		// rolG.setId(idL);
		rolVO[i] = rolG;

	    }
	    grupoVO.setRols(rolVO);
	    if(log.isDebugEnabled())log.debug("Elimino de la sesion la descripcion del grupo");
	    this.removeAltaGrupoBSession(request);
	    Long idCreado = srvAdminUsuariosService.altaGrupo(grupoVO);
	    form.setIdModificado(idCreado);
	    // /Elimino de la sesion la descripcion del grupo
	} catch (ValidatorException e) {
	    throw e;
	} catch (Exception e) {
	    log.error("Error: " + e);
	    throw new ValidatorException("{errors.altagrupo}");
	}

    }

    /**
         * @see es.pode.adminusuarios.presentacion.altaGrupo.AltaGrupoController#recuperarRoles(org.apache.struts.action.ActionMapping,
         *      es.pode.adminusuarios.presentacion.altaGrupo.RecuperarRolesForm,
         *      javax.servlet.http.HttpServletRequest,
         *      javax.servlet.http.HttpServletResponse) Obtiene los roles que
         *      estan dados de alta en el sistema
         */
    public final void recuperarRoles(ActionMapping mapping, RecuperarRolesForm form, HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
    	
   
	String descripcion = form.getDescripcion();
	Pattern mask = Pattern.compile("[^\\�\\?\\�\\!\\>\\#\\&\\<\\@\\$\\/\\\'\\\"]+");
    Matcher matcher = null;
	
	if ((descripcion == null) || (descripcion.length() == 0)) {
		if(log.isDebugEnabled())log.debug("La descripcion introducida es nula");
	    throw new ValidatorException("{errors.altagrupo.descripcion}");
		
	}
	matcher = mask.matcher(descripcion);
	if (!matcher.matches()) {
		if(log.isDebugEnabled())log.debug("nombre caracter ilegal");
		throw new ValidatorException("{errors.altagrupo.descripcion.caracterIlegal}");
	}
	
	if ((this.getSrvAdminUsuariosService().existeDescripcion(descripcion, Long.valueOf("-1"))).booleanValue()) {
	if(log.isDebugEnabled())log.debug("ya existe la descripcion");
	throw new ValidatorException("{errors.altagrupo.descripcionExistente}");
	}
	this.getAltaGrupoBSession(request).setDescripcion(descripcion);
	if(log.isDebugEnabled())log.debug("Guardamos en sesion la descripcion del grupo");
	RolVO[] rolVO = this.getSrvAdminUsuariosService().listarRoles();
	form.setRolesAsArray(rolVO);
    }

   

}