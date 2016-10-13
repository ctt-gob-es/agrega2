/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.visualizador.presentacion.gestionGruposPublicos.eliminarGruposSolicitados;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.visualizador.presentacion.gestionGruposPublicos.eliminarGruposSolicitados.EliminarGruposSolicitadosController
 */
public class EliminarGruposSolicitadosControllerImpl extends EliminarGruposSolicitadosController
{





	private static Logger logger = Logger.getLogger(EliminarGruposSolicitadosControllerImpl.class);

    /**
     * @see es.pode.visualizador.presentacion.gestionUsuarioPublico.eliminarGruposSolicitados.EliminarGruposSolicitadosController#eliminarSolicitud(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionUsuarioPublico.eliminarGruposSolicitados.EliminarSolicitudForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarSolicitud(ActionMapping mapping, es.pode.visualizador.presentacion.gestionGruposPublicos.eliminarGruposSolicitados.EliminarSolicitudForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
    		form.setResultado("FALLO"); 
	    	 Long id=form.getId();
	         Long[] identificadores=new Long[1];
	         identificadores[0]=id;
	         String nombre=form.getNombre();
	         String usuario=LdapUserDetailsUtils.getUsuario();
	         Boolean[] vueltas = this.getSrvPerfilPublico().eliminarSolicitud(identificadores, usuario);
	         if(vueltas!=null && vueltas.length>0){
	        	 Boolean vuelta=vueltas[0];
	        	 if(vuelta.equals(Boolean.TRUE)){
	        		 form.setResultado("OK"); 
	        	 }
	         }
	         
    	}catch(Exception e){
    		logger.error("Error en la eliminación de la solicitud "+form.getId());
    		form.setResultado("FALLO");
    		
    	}
      
     }







    /**
     * @see es.pode.visualizador.presentacion.gestionUsuarioPublico.eliminarGruposSolicitados.EliminarGruposSolicitadosController#obtenerSolicitud(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionUsuarioPublico.eliminarGruposSolicitados.ObtenerSolicitudForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerSolicitud(ActionMapping mapping, es.pode.visualizador.presentacion.gestionGruposPublicos.eliminarGruposSolicitados.ObtenerSolicitudForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
    	 Long id=form.getId();
         String nombre=form.getNombre();
         logger.info("La solicitud obtenida es el de identificador "+id+" y es para el itinerario de aprendizaje "+nombre);
    	} catch(Exception e){
        	 logger.error("Error al obtener la solicitud");
         }
     }








}