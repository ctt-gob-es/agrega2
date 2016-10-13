/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.visualizador.presentacion.gestionFavoritosUsuario.desasociarFavorito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.ResultadoOperacionVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.visualizador.presentacion.gestionFavoritosUsuario.desasociarFavorito.DesasociarFavoritoController
 */
public class DesasociarFavoritoControllerImpl extends DesasociarFavoritoController
{


	private static Logger logger = Logger.getLogger(DesasociarFavoritoControllerImpl.class);



    /**
     * @see es.pode.visualizador.presentacion.gestionFavoritosUsuario.desasociarFavorito.DesasociarFavoritoController#obtenerFavorito(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionFavoritosUsuario.desasociarFavorito.ObtenerFavoritoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerFavorito(ActionMapping mapping, es.pode.visualizador.presentacion.gestionFavoritosUsuario.desasociarFavorito.ObtenerFavoritoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {

    	try{
    	String id_mec=form.getId_mec();
    	String titulo=form.getTitulo();
    	logger.info("El identificador que hemos recibido es "+id_mec+" y el titulo "+titulo);
    	}catch(Exception e){
    		logger.error("Error al obtener el ode favorito");
    	}
     }







    /**
     * @see es.pode.visualizador.presentacion.gestionFavoritosUsuario.desasociarFavorito.DesasociarFavoritoController#desasociarFavorito(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionFavoritosUsuario.desasociarFavorito.DesasociarFavoritoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void desasociarFavorito(ActionMapping mapping, es.pode.visualizador.presentacion.gestionFavoritosUsuario.desasociarFavorito.DesasociarFavoritoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
	    	String usuario=LdapUserDetailsUtils.getUsuario();
	    	String id_mec=form.getId_mec();
	    	String titulo=form.getTitulo();
	    	String[] ids=new String[1];
	    	ids[0]=id_mec;
	    	String[] titulos=new String[1];
	    	titulos[0]=titulo;
	        ResultadoOperacionVO[] vuelta = this.getSrvPerfilPublico().eliminarFavoritoDeUsuario(ids, usuario, titulos);
	        if(vuelta!=null && vuelta.length>0){
	        	ResultadoOperacionVO resultado=vuelta[0];
	        	Boolean texto=resultado.getResultado();
	        	if(texto.equals(Boolean.TRUE)){
					form.setResultado("OK");
				}
				else{
					form.setResultado("FALLO");
				}
	        }
    	}catch(Exception e){
    		logger.error("Error al desasociar el ode favorito");
    	}
     }









}