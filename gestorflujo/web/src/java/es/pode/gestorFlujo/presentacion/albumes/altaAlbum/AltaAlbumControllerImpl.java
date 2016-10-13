/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.gestorFlujo.presentacion.albumes.altaAlbum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.gestorFlujo.presentacion.ObjetosPersonalesSession;
import es.pode.gestorFlujo.presentacion.albumes.listarAlbum.ListarAlbumControllerImpl;
import es.pode.gestorFlujo.presentacion.objetosPersonales.ObjetosPersonalesControllerImpl;
import es.pode.publicacion.negocio.servicios.ResultadoOperacionAlbumVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.gestorFlujo.presentacion.albumes.altaAlbum.AltaAlbumController
 */
public class AltaAlbumControllerImpl extends AltaAlbumController
{
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(AltaAlbumController.class);
	public final static String SIN_ERRORES = "0.0";
	public final static String TITULO_VACIO = "12.4";
	

    /**
     * @see es.pode.gestorFlujo.presentacion.albumes.altaAlbum.AltaAlbumController#crearAlbum(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.albumes.altaAlbum.CrearAlbumForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final es.pode.publicacion.negocio.servicios.ResultadoOperacionAlbumVO crearAlbum(ActionMapping mapping, es.pode.gestorFlujo.presentacion.albumes.altaAlbum.CrearAlbumForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	if(logger.isDebugEnabled()) logger.debug("Voy a crear el album de titulo ["+ form.getTitulo() +"]");
    	ResultadoOperacionAlbumVO resultadoAlbum = new ResultadoOperacionAlbumVO();
    	if(form.getTitulo() != null && form.getTitulo() != "" && form.getTitulo().length()>0){
    		try{
	    		String usuario = LdapUserDetailsUtils.getUsuario();
	    		if(logger.isDebugEnabled()) logger.debug("Estoy creando el album de titulo: ["+form.getTitulo()+"] y para el usuario: ["+ usuario +"]");    		
	    		
	    		resultadoAlbum = this.getSrvAlbumService().altaAlbum(form.getTitulo(), form.getTitulo(), usuario);
	    		
	    		if(SIN_ERRORES.equals(resultadoAlbum.getIdResultado())){
					logger.info("El usuario ["+form.getIdUsuario()+"] esta creando el album [" + form.getTitulo()+ "] con descripcion [" + form.getDescripcion() + "].");
					form.setExitoFracaso(true);
					form.setTexto(resultadoAlbum.getDescripcion());
				}else{
					// Si la creación del álbum no ha ido bien, entendemos que ha habido un error
					logger.error("Error creando el álbum [" + resultadoAlbum.getAlbum().getId() + "] de titulo: [" + resultadoAlbum.getAlbum().getTitulo()+ "].");
					form.setExitoFracaso(false);
					form.setTexto(resultadoAlbum.getDescripcion());
				}
			}catch(Exception e){
	    		logger.error("Error creando el album de titulo: ["+form.getTitulo() + "]");
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


    
    /**
     * @see es.pode.gestorFlujo.presentacion.albumes.altaAlbum.AltaAlbumController#comprobarOrigen(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.albumes.altaAlbum.ComprobarOrigenForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String comprobarOrigen(ActionMapping mapping, es.pode.gestorFlujo.presentacion.albumes.altaAlbum.ComprobarOrigenForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		
		ObjetosPersonalesSession sesion = this.getObjetosPersonalesSession(request);
		Long idAlbum = sesion.getIdAlbum();
		String retorno = sesion.getRetorno();
		
		
		logger.debug("El origen es ["+ retorno +"], y el idAlbum ["+ idAlbum +"]");
		if(retorno != null && retorno.equals(ObjetosPersonalesControllerImpl.OBJETOS_PERSONALES)){
			retorno = ObjetosPersonalesControllerImpl.OBJETOS_PERSONALES;
		}
		if(retorno!= null && retorno.equals(ListarAlbumControllerImpl.ODES_POR_ALBUM)){
			retorno = ListarAlbumControllerImpl.ODES_POR_ALBUM;
		}
		logger.debug("Creamos el album, y volvemos a: ["+ retorno + "]");
		return retorno;
	}
   }
