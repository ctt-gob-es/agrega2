/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.gestorFlujo.presentacion.objetosPublicados.odeGrupos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO;
import es.pode.adminusuarios.negocio.servicios.OdeGrupoVO;
import es.pode.gestorFlujo.presentacion.ObjetosPersonalesSession;
import es.pode.publicacion.negocio.servicios.OdePublicadoVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;


/**
 * @see es.pode.gestorFlujo.presentacion.objetosPublicados.odeGrupos.OdeGruposController
 */
public class OdeGruposControllerImpl extends OdeGruposController
{


	private Logger logger = Logger.getLogger(OdeGruposController.class);



    /** 
     * Este método carga la lista de grupos a los que el usuario está asociado.
     * @see es.pode.gestorFlujo.presentacion.objetosPublicados.odeGrupos.OdeGruposController#cargaGruposUsuario(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPublicados.odeGrupos.CargaGruposUsuarioForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cargaGruposUsuario(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosPublicados.odeGrupos.CargaGruposUsuarioForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {	
    	try{
    		GrupoPublicoVO[] grupoPublico = this.getSrvPerfilPublico().listarTodosGrupoDeUsuario(LdapUserDetailsUtils.getUsuario());
    		if(grupoPublico !=null && grupoPublico.length>0){
    			logger.debug("Se han cargado ["+ grupoPublico.length+ "] grupos públicos del usuario["+LdapUserDetailsUtils.getUsuario()+"]");
    			GrupoUsuarioCheckVO[] grupoCheck = obtenerGrupoCheck(grupoPublico, form.getIdODE());
    			form.setListaGruposAsArray(grupoCheck);
	    		// añadimos el id del ode que se esta moviendo a grupos
	    		ObjetosPersonalesSession sesion = this.getObjetosPersonalesSession(request);
	        	sesion.setIdODE(form.getIdODE());
    		}
    	} catch (Exception ex) {
    		logger.error("Error inesperado cargando los grupos publicos del usuario: " + LdapUserDetailsUtils.getUsuario(), ex);
    		throw new ValidatorException("{gestorFlujo.error.inesperado}");
    	}
    }

    /*Este método asigna un ode a los grupos que hayamos checkeado previamente*/
    public final void pasarAGrupos(ActionMapping mapping, PasarAGruposForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
    {
    	ObjetosPersonalesSession sesion = this.getObjetosPersonalesSession(request);
    	String idODE = sesion.getIdODE();
    	logger.info("Envio a una lista de grupos el ode con identificador: " + idODE);
    	OdeGrupoVO odeGrupo = new OdeGrupoVO();
		odeGrupo.setId_mec(idODE);
		String[] nombreGrupos = form.getNombreRowSelectionAsArray();
		if(logger.isDebugEnabled())logger.debug("Se han seleccionado "+((nombreGrupos!=null)?nombreGrupos.length:0) + "grupos");
		if(nombreGrupos == null || nombreGrupos.length == 0){
			logger.error("No se ha seleccionado ningún grupo para asignar el ode: " + idODE);
			form.setExitoFracaso(false);
			form.setTexto("gestorFlujo.mostrarGrupos.noGrupos");
		}else{
			try {
				OdePublicadoVO odePublicado = this.getSrvPublicacionService().obtenODEPublicado(idODE);
				odeGrupo.setTitulo(odePublicado.getTitulo());
				odeGrupo.setIdioma(odePublicado.getIdioma());
				this.getSrvPerfilPublico().asociarOdeAGrupo(odeGrupo, nombreGrupos);
//				StringBuffer sb = new StringBuffer();
//				for (int i = 0; i < nombreGrupos.length; i++) {
//					sb.append("\n");
//					sb.append(nombreGrupos[i]);
//				}
				form.setExitoFracaso(true);
				form.setTexto("gestorFlujo.envioGrupo.exito");
			}catch (Exception e) {
				logger.error("Ha habido un problema al asociar el ode con identificador: " + idODE +"a los grupos");
				form.setExitoFracaso(false);
				form.setTexto("gestorFlujo.envioGrupo.fracaso");
			}
		}
    }   

    /* Este metodo comprueba si el usuario tiene perfil público, para permitirle el acceso a los grupos públicos  */
    public final  Boolean comprobarUsuarioPublico(ActionMapping mapping, ComprobarUsuarioPublicoForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws java.lang.Exception{
    	logger.info("El usuario: [" + LdapUserDetailsUtils.getUsuario()+ "] es un usuario publico? [" + LdapUserDetailsUtils.tienePerfilPublico()+ "]");
    	return (LdapUserDetailsUtils.tienePerfilPublico());
    }
    
    
    
    
    /*
     * Este metodo recibe los grupos que pertenecen a un usuario y comprueba si el ODE que le pasa esta ya en el grupo. Devuelve
     * un array de VO's donde se indica el grupo y si el ODE pertenece o no a ese grupo.
     * */
	private GrupoUsuarioCheckVO[] obtenerGrupoCheck(GrupoPublicoVO[] grupo, String idODE)throws Exception {
		GrupoUsuarioCheckVO[] resultado = new GrupoUsuarioCheckVO[grupo.length];
		for (int i = 0; i < grupo.length; i++) {
			logger.info("Voy a ver si el ODE con identificador "+ idODE+" esta en el grupo: " + grupo[i].getNombre());
			resultado[i]=new GrupoUsuarioCheckVO();
			resultado[i].setAdministrador(grupo[i].getAdministrador());
			resultado[i].setDescripcion(grupo[i].getDescripcion());
			resultado[i].setFechaCreacion(grupo[i].getFechaCreacion());
			resultado[i].setFechaModificacion(grupo[i].getFechaModificacion());
			resultado[i].setImagenGrupo(grupo[i].getImagenGrupo());
			resultado[i].setNombre(grupo[i].getNombre());
			resultado[i].setId(grupo[i].getId());
			try {
				Boolean existe = this.getSrvPerfilPublico().existeOdeEnGrupo(idODE, grupo[i].getNombre());
				resultado[i].setCheck(existe);
				logger.info("El ODE con identificador "+ idODE +" "+ existe + "en el grupo: " + grupo[i].getNombre());
			} catch (Exception e) {
				logger.error("Error inesperado al mirar si existe el ODE["+ idODE+"] en el grupo["+grupo[i].getNombre()+"]" , e);
	    		throw new ValidatorException("{gestorFlujo.error.inesperado}");
			}
		}
		
		return resultado;
    }


	

}