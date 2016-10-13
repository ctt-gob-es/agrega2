/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.administracion.presentacion.adminusuarios.verUsuario;


import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.GrupoTrabajoVO;
import es.pode.adminusuarios.negocio.servicios.GrupoVO;
import es.pode.adminusuarios.negocio.servicios.UsuarioVO;


/**
 * @see es.pode.administracion.presentacion.adminusuarios.verUsuario.VerUsuarioController
 */
public class VerUsuarioControllerImpl extends VerUsuarioController {

	private static Logger log = Logger
			.getLogger(VerUsuarioControllerImpl.class);

	/**
	 * @see es.pode.administracion.presentacion.adminusuarios.verUsuario.VerUsuarioController#recuperarUsuario(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.adminusuarios.verUsuario.RecuperarUsuarioForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public final void recuperarUsuario(
			ActionMapping mapping,
			es.pode.administracion.presentacion.adminusuarios.verUsuario.RecuperarUsuarioForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		 
		try {
			
			Long id = Long.valueOf(request.getParameter("id"));
			if(log.isDebugEnabled())log.debug("Recupero los datos del usuario cuyo id es " + id);
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
			form.setTipoCatalogador(usuario.getTipoCatalogador());
			form.setTipoVisualizador(usuario.getTipoVisualizador());
			form.setOpenIdUrl(usuario.getOpenIdUrl());
			form.setRecibirCorreo(usuario.getRecibirCorreoPublicacion());
			//convertimos los bytes de cuota a mbytes para mostrarlos por pantalla
			
			long cuota = 0L;
			if(!(usuario.getCuota() == null))
			{
				cuota = (usuario.getCuota()/1048576);
			}
			
			form.setCuota(Long.valueOf(cuota).toString());
			
			
			form.setId(id);
			
			//recogemos los grupos para mostrarlos
			Set set = new HashSet();
			GrupoVO[] grupos = this.getSrvAdminUsuariosService().descripcionUsuario(id).getGrupos();
			for (int i = 0; i < grupos.length; i++) {
				set.add(grupos[i]);
			}
			if(log.isDebugEnabled())log.debug("Tamanio de la lista de grupos del usuario seleccionado "
					+ set.size());
			form.setGrupos(set);
			
			//recogemos los grupos de trabajo para mostrarlos
			Set setGruposTrabajo = new HashSet();
			GrupoTrabajoVO[] gruposTrabajo = this.getSrvAdminUsuariosService().descripcionUsuario(id).getGrupoTrabajo();
			for (int i = 0; i < gruposTrabajo.length; i++) {
				setGruposTrabajo.add(gruposTrabajo[i]);
			}
			if(log.isDebugEnabled())log.debug("Tamanio de la lista de grupos de trabajo del usuario seleccionado " + setGruposTrabajo.size());
			form.setGruposTrabajo(setGruposTrabajo);
			
		} catch (Exception e) {
			log.error("Error: " + e);
			throw new ValidatorException("{verUsuario.error}");
		}
	}


}