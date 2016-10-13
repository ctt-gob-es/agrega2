/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.gestorFlujo.presentacion.albumes.modificarAlbum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.publicacion.negocio.servicios.ResultadoOperacionAlbumVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.gestorFlujo.presentacion.albumes.modificarAlbum.ModificarAlbumController
 */
public class ModificarAlbumControllerImpl extends ModificarAlbumController
{
	
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(ModificarAlbumControllerImpl.class);

	private final String OBJETOS_PERSONALES = "objetosPersonales";
	private final String ODES_POR_ALBUM = "odesPorAlbum";
  
	public final static String SIN_ERRORES = "0.0";
	/**
     * @see es.pode.gestorFlujo.presentacion.albumes.modificarAlbum.ModificarAlbumController#modificarAlbum(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.albumes.modificarAlbum.ModificarAlbumForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final ResultadoOperacionAlbumVO modificarAlbum(ActionMapping mapping, es.pode.gestorFlujo.presentacion.albumes.modificarAlbum.ModificarAlbumForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	if(logger.isDebugEnabled()) logger.debug("Voy a modificar el album con identificador: ["+ form.getIdAlbum()+ "] titulo: ["+ form.getTitulo() + "] y usuario ["+ form.getIdUsuario()+"]");
    	ResultadoOperacionAlbumVO resultadoAlbum = new ResultadoOperacionAlbumVO();
    	if(form.getTitulo() != null && form.getTitulo() != "" && form.getTitulo().length()>0){
	    	try{
	    		if(logger.isDebugEnabled()) logger.debug("Recojo el nuevo titulo: ["+form.getTitulo()+"] para ahora llamar al servicio.");
	    		resultadoAlbum = this.getSrvAlbumService().modificaAlbum(form.getIdAlbum(), form.getTitulo(), form.getTitulo());
	    		
	    		if(SIN_ERRORES.equals(resultadoAlbum.getIdResultado())){
					logger.info("El usuario ["+form.getIdUsuario()+"] esta modificando el album [" + form.getTitulo()+ "] .");
					form.setExitoFracaso(true);
					form.setTexto(resultadoAlbum.getDescripcion());
				}else{
					// Si la modificación del álbum no ha ido bien, entendemos que ha habido un error
					logger.error("Error modificando el álbum [" + resultadoAlbum.getAlbum().getId() + "] de titulo: [" + resultadoAlbum.getAlbum().getTitulo()+ "].");
					form.setExitoFracaso(false);
					form.setTexto(resultadoAlbum.getDescripcion());
				}
	    	}catch(Exception e){
	    		logger.error("Error modificando el album de titulo: ["+form.getTitulo() + "]");
	    		form.setExitoFracaso(false);
	    		if(!(resultadoAlbum.getDescripcion().equals(SIN_ERRORES))){
	    			form.setTexto(resultadoAlbum.getDescripcion());
	    		}
    	}
    	}else{
    		logger.warn(" El usuario [" + LdapUserDetailsUtils.getUsuario() + "] no ha introducido el titulo para crear el nuevo album");
			throw new ValidatorException("{gestorFlujo.album.tituloObligatorio}");
		}
    	return resultadoAlbum;
    }


	
	public String comprobarOrigen(ActionMapping mapping, es.pode.gestorFlujo.presentacion.albumes.modificarAlbum.ComprobarOrigenForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String retorno="";
		logger.debug("El origen es ["+form.getRetorno()+"]");
		if(form.getRetorno()!= null && form.getRetorno().equals(this.OBJETOS_PERSONALES)){
			retorno = "objetosPersonales";
		}
		if(form.getRetorno()!= null && form.getRetorno().equals(this.ODES_POR_ALBUM)){
			retorno = "odesPorAlbum";
		}
		logger.debug("Modificamos el album, y volvemos a: ["+ retorno + "]");
		return retorno;
	}

}
