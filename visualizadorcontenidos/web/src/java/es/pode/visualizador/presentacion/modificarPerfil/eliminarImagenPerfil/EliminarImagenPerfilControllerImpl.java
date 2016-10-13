/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.visualizador.presentacion.modificarPerfil.eliminarImagenPerfil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.visualizador.presentacion.modificarPerfil.eliminarImagenPerfil.EliminarImagenPerfilController
 */
public class EliminarImagenPerfilControllerImpl extends EliminarImagenPerfilController
{




	private static Logger logger = Logger.getLogger(EliminarImagenPerfilControllerImpl.class);

    /**
     * @see es.pode.visualizador.presentacion.modificarPerfil.eliminarImagenPerfil.EliminarImagenPerfilController#eliminarImagen(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.modificarPerfil.eliminarImagenPerfil.EliminarImagenForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarImagen(ActionMapping mapping, es.pode.visualizador.presentacion.modificarPerfil.eliminarImagenPerfil.EliminarImagenForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
    	 String usuario=LdapUserDetailsUtils.getUsuario();
         Boolean vuelta = this.getSrvPerfilPublico().cambiarImagenPorElDefecto(usuario);
         
         	if(vuelta.equals(Boolean.TRUE)){
 				form.setResultado("OK");
 			}
 			else{
 				form.setResultado("FALLO");
 			}

    	}catch(Exception e){
    		logger.error("Error al eliminar la imagen del usuario");
    	}
     }









}