// license-header java merge-point
package es.pode.administracion.presentacion.adminusuarios.modificarGrupo;


import java.util.ArrayList;
import java.util.Arrays;
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
 * @see es.pode.administracion.presentacion.adminusuarios.modificarGrupo.ModificarGruposController
 */
public class ModificarGruposControllerImpl extends ModificarGruposController {

    private static Logger log = Logger.getLogger(ModificarGruposControllerImpl.class);

    /**
     * @see es.pode.administracion.presentacion.adminusuarios.modificarGrupo.ModificarGruposController#cargarDescripcion(org.apache.struts.action.ActionMapping,
     *      es.pode.administracion.presentacion.adminusuarios.modificarGrupo.CargarDescripcionForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public final void cargarDescripcion(ActionMapping mapping,
	    es.pode.administracion.presentacion.adminusuarios.modificarGrupo.CargarDescripcionForm form,
	    HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    
		try {
		   
		    Long id = Long.valueOf(request.getParameter("id"));
		    GrupoVO grupo = this.getSrvAdminUsuariosService().descripcionGrupo(id);
		    if(log.isDebugEnabled())log.debug("Obtengo el grupoVO seleccionado");
		    form.setDescripcion(grupo.getDescripcion());
		    form.setId(grupo.getId());
		} catch (Exception e) {
		    log.error("Error: " + e);
		    throw new ValidatorException("{modificarGrupo.error}");
		}
    }

    /**
     * @see es.pode.administracion.presentacion.adminusuarios.modificarGrupo.ModificarGruposController#recuperarRoles(org.apache.struts.action.ActionMapping,
     *      es.pode.administracion.presentacion.adminusuarios.modificarGrupo.RecuperarRolesForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public final void recuperarRoles(ActionMapping mapping,
	    es.pode.administracion.presentacion.adminusuarios.modificarGrupo.RecuperarRolesForm form,
	    HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	
		try {
		    String descripcion = request.getParameter("descripcion");
		    if(log.isDebugEnabled())log.debug("El valor de id es " + descripcion);
		    
		    //VALIDACION DE CARACTERES INCORRECTOS
		    Pattern mask = Pattern.compile("[^\\�\\?\\�\\!\\>\\#\\&\\<\\@\\$\\/\\\'\\\"]+");
		    Matcher matcher = null;
		    
		    matcher = mask.matcher(descripcion);
		    if (!matcher.matches()) {
		    	if(log.isDebugEnabled())log.debug("nombre caracter ilegal");
		    	throw new ValidatorException("{errors.altagrupo.descripcion.caracterIlegal}");
		    }
		    
		    Long id = Long.valueOf(request.getParameter("id"));
		    if(log.isDebugEnabled())log.debug("Chequeo si la descripcion ya esta asociada a otro grupo");
		    GrupoVO grupoVO = this.getSrvAdminUsuariosService().descripcionGrupo(id);
		    if ((this.getSrvAdminUsuariosService().existeDescripcion(descripcion, id)).booleanValue()) {
		    	if(log.isDebugEnabled())log.debug("Ya existe un grupo en el sistema con esa descripcion");
			throw new ValidatorException("{errors.modificaciongrupo.descripcionExistente}");
		    }
		    if (descripcion.length() > 0) {
	
			grupoVO.setDescripcion(descripcion);
			this.getModificarGrupoBSession(request).setGrupoVO(grupoVO);
			if(log.isDebugEnabled())log.debug("guardo el grupoVO en sesion");
		    } else {
		    	if(log.isDebugEnabled())log.debug("La descripcion introducida es nula");
			throw new ValidatorException("{errors.modificaciongrupo.descripcion}");
		    }
		    try {
			RolVO[] rolVO = this.getSrvAdminUsuariosService().listarRoles();
			RolVOCheck[] rolVOCheck = null;
			rolVOCheck = obtenerRolCheck(rolVO, grupoVO);
			// form.setRolesAsArray(rolVO);
			form.setRolesAsArray(rolVOCheck);
	
		    } catch (Exception e) {
			log.error("Error: " + e);
			throw new ValidatorException("{modificarGrupo.errorRoles}");
		    }
		} catch (ValidatorException validator) {
		    throw validator;
		} catch (Exception e) {
		    log.error("Error: " + e);
		    throw new ValidatorException("{modificarGrupo.errorRoles}");
		}

    }

    /**
     * @see es.pode.administracion.presentacion.adminusuarios.modificarGrupo.ModificarGruposController#modificarGrupo(org.apache.struts.action.ActionMapping,
     *      es.pode.administracion.presentacion.adminusuarios.modificarGrupo.ModificarGrupoForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public final void modificarGrupo(ActionMapping mapping,
	    es.pode.administracion.presentacion.adminusuarios.modificarGrupo.ModificarGrupoForm form,
	    HttpServletRequest request, HttpServletResponse response) throws Exception {


    	try {
    		GrupoVO grupoVO = (this.getModificarGrupoBSession(request)).getGrupoVO();
    		if(log.isDebugEnabled())log.debug("La nueva descripcion es " + grupoVO.getDescripcion());
    		String[] ids = form.getIdRowSelectionAsArray();
    		SrvAdminUsuariosService srvAdminUsuariosService = this.getSrvAdminUsuariosService();
    		if (ids == null) {
    			if(log.isDebugEnabled())log.debug("No se ha seleccionado ningun permiso");
    			throw new ValidatorException("{errors.modificaciongrupo.roles}");
    		}
    		if ((esGrupoAdmin(grupoVO)).booleanValue() && (!((administradorSelected(ids)).booleanValue()))) {
    			if(log.isDebugEnabled()){log.debug("El grupo no ha seleccionado el rol administrador se comprobara si existe otro grupo administrador");
    			log.debug("srvAdminUsuariosService.obtenerGrupoAdministrador() "
    					+ Arrays.toString(srvAdminUsuariosService.obtenerGrupoAdministrador()));
    			log.debug("srvAdminUsuariosService.obtenerGrupoAdministrador().length "
    					+ srvAdminUsuariosService.obtenerGrupoAdministrador().length);
    			}
    			if (srvAdminUsuariosService.obtenerGrupoAdministrador().length == 1) {
    				if(log.isDebugEnabled())log.debug("No se permite modificar los roles es el unico grupo administrador");
    				throw new ValidatorException("{errors.modificaciongrupo.unicoAdmin}");
    			}
    			if(log.isDebugEnabled())log.debug("Tenemos mas de un grupo administrador");
    			//Vamos a comprobar que otros grupos administradores tengan al menos un usuario
    			if(!(existenUsuariosGruposAdmin(grupoVO)))
    			{
    				if(log.isDebugEnabled())log.debug("El resto de grupos administradores no tienen usuarios asociados no dejo modificar");
    				throw new ValidatorException("{errors.modificaciongrupo.unicoAdmin}");
    			}
    			RolVO[] rolVO = new RolVO[ids.length];
    			Long idL = null;
    			for (int i = 0; i < ids.length; i++) {
    				idL = Long.parseLong(ids[i]);

    				RolVO rolG = srvAdminUsuariosService.getRol(idL);
    				// rolG.setId(idL);
    				rolVO[i] = rolG;

    			}
    			grupoVO.setRols(rolVO);
    		} else {
    			RolVO[] rolVO = new RolVO[ids.length];

    			Long idL = null;
    			for (int i = 0; i < ids.length; i++) {
    				idL = Long.parseLong(ids[i]);

    				RolVO rolG = srvAdminUsuariosService.getRol(idL);
    				// rolG.setId(idL);
    				rolVO[i] = rolG;

    			}
    			grupoVO.setRols(rolVO);
    		}

    		Long idModificado = grupoVO.getId();
    		form.setIdModificado(idModificado);

    		if(log.isDebugEnabled())log.debug("Elimino de la sesion la descripcion del grupo");
    		this.removeModificarGrupoBSession(request);
    		srvAdminUsuariosService.modificarGrupo(grupoVO);
    		form.setResultado("grupos.modificar.OK");
    		// /Elimino de la sesion la descripcion del grupo
    	} catch (ValidatorException ve) {
    		log.error("se produce una excepcion " + ve);
    		form.setResultado("grupos.modificar.FALLO");
    		throw ve;
    	} catch (Exception e) {

    		log.error("Error: " + e);
    		form.setResultado("grupos.modificar.FALLO");
    		//throw new ValidatorException("{modificarGrupo.errorRoles}");
    	}
    }


    private Boolean esGrupoAdmin(GrupoVO grupo) {
    	
		Boolean resultado = false;
		RolVO[] roles = grupo.getRols();
		for (int i = 0; i < roles.length; i++) {
		    if (roles[i].getDescripcion().equalsIgnoreCase("administrador")) {
		    	resultado = true;
		    }
		}
		return resultado;
    }

    private Boolean administradorSelected(String[] ids) {
    	
		Boolean resultado = false;
		try {
		    Long idL = null;
		    for (int i = 0; i < ids.length; i++) {
		    	idL = Long.parseLong(ids[i]);
	
		    	RolVO rolG = this.getSrvAdminUsuariosService().getRol(idL);
		    	if (rolG.getDescripcion().equalsIgnoreCase("administrador")) {
		    		resultado = true;
		    	}
		    }
		} catch (Exception e) {
		    log.error("Se ha producido una excepcion " + e);
		}
		return resultado;
    }
    
    private Boolean existenUsuariosGruposAdmin(GrupoVO grupoVO) throws Exception{
	Boolean resultado = false;
		try
		{
			Long[] idAdmin = this.getSrvAdminUsuariosService().obtenerGrupoAdministrador();
			String descripcion = grupoVO.getDescripcion();
			for(int i=0;i<idAdmin.length;i++)
			{
			   GrupoVO grupoAdmin = this.getSrvAdminUsuariosService().descripcionGrupo(idAdmin[i]);
			   if((grupoAdmin.getDescripcion()).equalsIgnoreCase(descripcion))
			   {
				   if(log.isDebugEnabled())log.debug("El grupo administrador es el que queremos modificar");  
			   }else
			   {
			     if(this.getSrvAdminUsuariosService().obtenerUsuariosGrupo(idAdmin[i]).booleanValue()){
			    	 resultado = true;
			     }
			   }
			}
		}catch(Exception e)
		{
		  log.error("Se produce la siguiente excepcion "+e);  
		}
		return resultado;
    }

    private RolVOCheck[] obtenerRolCheck(RolVO[] rolVO, GrupoVO grupoVO) {
    	
		RolVOCheck[] resultado = null;
		resultado = new RolVOCheck[rolVO.length];
		RolVO[] rolesGrupo = grupoVO.getRols();
		ArrayList<Long> vRol = new ArrayList<Long>();
	
		for (int j = 0; j < rolesGrupo.length; j++) {
		    vRol.add(rolesGrupo[j].getId());
		}
	
		for (int i = 0; i < rolVO.length; i++) {
		    RolVOCheck rol = new RolVOCheck();
		    rol.setId(rolVO[i].getId());
		    rol.setDescripcion(rolVO[i].getDescripcion());
		    if (vRol.contains(rolVO[i].getId())) {
			rol.setChecked(true);
		    } else {
			rol.setChecked(false);
		    }
		    resultado[i] = rol;
		}
	
		return resultado;
    }

    // Nueva clase para obtener los roles que tiene asociados ese grupo
    public static class RolVOCheck {
	private Long id;

	private String descripcion;

	private Boolean checked;

	public RolVOCheck() {
	}

	public Long getId() {
	    return id;
	}

	public void setId(Long id) {
	    this.id = id;
	}

	public String getDescripcion() {
	    return descripcion;
	}

	public void setDescripcion(String descripcion) {
	    this.descripcion = descripcion;
	}

	public void setChecked(Boolean checked) {
	    this.checked = checked;
	}

	public Boolean getChecked() {
	    return checked;
	}
    }

}