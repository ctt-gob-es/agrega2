// license-header java merge-point
package es.pode.gestorFlujo.presentacion.objetosPendientes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.GrantedAuthority;
import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.SrvAdminUsuariosService;
import es.pode.publicacion.negocio.servicios.SrvPublicacionService;
import es.pode.publicacion.negocio.servicios.TransicionVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.gestorFlujo.presentacion.objetosPendientes.ObjetosPendientesController
 */
public class ObjetosPendientesControllerImpl extends ObjetosPendientesController
{
	public final static String OBJETOS_PENDIENTES = "objetosPendientes";
	private Logger logger = Logger.getLogger(ObjetosPendientesController.class);


    /**
	 * @see es.pode.gestorFlujo.presentacion.objetosPendientes.ObjetosPendientesController#cargarODESPendientes(org.apache.struts.action.ActionMapping,
	 *      es.pode.gestorFlujo.presentacion.objetosPendientes.CargarODESPendientesForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public final void cargarODESPendientes(ActionMapping mapping,
			es.pode.gestorFlujo.presentacion.objetosPendientes.CargarODESPendientesForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
//		if(logger.isDebugEnabled())
//		logger.debug("Cargando objetos Pendientes");
//		SrvPublicacionService publi = this.getSrvPublicacionService();
//		try {
//			form.setListaODESAsArray(publi.obtenODEsPropuestos());
//		} catch (Exception ex) {
//			logger.error("Imposible obtener los odes pendientes", ex);
//			throw new ValidatorException("{gestorFlujo.error.inesperado}");
//		}
//		form.setIdUsuario(LdapUserDetailsUtils.getUsuario());
//
//		// Como estamos en administración damos por sentado que esta autenticado
//		form.setTipoUsuario(tipo_usuario(LdapUserDetailsUtils.getRoles()));
//
//		form.setEsDespublicado(new Boolean(false));
//		logger.debug("cargarODESPendientes-El objeto es despublicado?"+form.getEsDespublicado().booleanValue());
//		logger.info("Objetos Pendientes cargados correctamente");
		
		
		
		
		
		if(logger.isDebugEnabled())
	    	logger.debug("Cargando objetos Pendientes de Publicacion");
			SrvPublicacionService publi = this.getSrvPublicacionService();
			SrvAdminUsuariosService admin=this.getSrvAdminUsuariosService();
			try {
				TransicionVO[] odes =null;
				String[] todosUsuariosGrupos=admin.obtenerListaUsuariosGrupoTrabajo(LdapUserDetailsUtils.getUsuario());
//				
//				String[] todosUsuariosGrupos=new String[] {LdapUserDetailsUtils.getUsuario()};
				if(todosUsuariosGrupos!=null && todosUsuariosGrupos.length>0){
					logger.info("Obtenidos lista de usuarios de los grupos pertenecientes de usuario:["+LdapUserDetailsUtils.getUsuario()+"Numero de usuarios:["+todosUsuariosGrupos.length);
					 odes = publi.obtenODEsPropuestosPorUsuarios(todosUsuariosGrupos);
					logger.info("Obtenidos odes de esos usuarios, numero de odes pendientes de publicacion ["+odes.length);
				}else{
					logger.info("Obtenidos lista de todos los ODES, pues el usuario:["+LdapUserDetailsUtils.getUsuario()+" es parte de todos los grupos");
					odes=publi.obtenODEsPropuestos();
					logger.info("Obtenidos odes de todos los usuarios, numero de odes pendientes de publicacion["+odes.length);
				}
				form.setListaODESAsArray(odes);
			} catch (Exception ex) {
				logger.error("Imposible obtener los odes pendientes de publicacion", ex);
				throw new ValidatorException("{gestorFlujo.error.inesperado}");
			}
			form.setIdUsuario(LdapUserDetailsUtils.getUsuario());
			// Como estamos en administración damos por sentado que esta autenticado
			form.setTipoUsuario(tipo_usuario(LdapUserDetailsUtils.getRoles()));
			form.setRetorno(OBJETOS_PENDIENTES);
			form.setEsDespublicado(false);
			logger.debug("cargarODESPendientes-El objeto es despublicado?"+form.getEsDespublicado().booleanValue());
			logger.info("Objetos Pendientes de Publicacion cargados correctamente");

	}


    
     
    /**
	 * Devuelve los permisos que tiene el usuario en forma de código numérico,
	 * aunque para manejarlo mejor le damos el tipo String: 0 - ninguno 1 -
	 * catalogador 2 - publicador 3 - ambos
	 */
	private String tipo_usuario(GrantedAuthority[] tipoUsuarios) {
		int resultado = 0;
		String role_publi = "ROLE_PUBLICADOR";
		for (int i = 0; i < tipoUsuarios.length; i++) {
			if(logger.isDebugEnabled())
			logger.debug("tipo user = " + tipoUsuarios[i].getAuthority());
			if (tipoUsuarios[i].getAuthority().equalsIgnoreCase("ROLE_CATALOGADOR")) {
				if (resultado == 0) {
					resultado = 1;
				}
				// si no es 0 es que ya somos publicador
				else {
					resultado = 3;
				}
			}
			if (tipoUsuarios[i].getAuthority().equalsIgnoreCase(role_publi)) {
				if (resultado == 0) {
					resultado = 2;
				}
				// si no es 0 es que ya somos catalogador
				else {
					resultado = 3;
				}
			}
		}
		if(logger.isDebugEnabled())
		logger.debug("tipo usuario = " + resultado);
		return String.valueOf(resultado);

	}



}