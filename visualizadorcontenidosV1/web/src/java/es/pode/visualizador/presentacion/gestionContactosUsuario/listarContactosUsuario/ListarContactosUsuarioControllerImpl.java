/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.visualizador.presentacion.gestionContactosUsuario.listarContactosUsuario;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.adminusuarios.negocio.servicios.ContactoVO;
import es.pode.adminusuarios.negocio.servicios.UsuarioPublicoVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.url.ImagenesAgrega;



/**
 * @see es.pode.visualizador.presentacion.gestionContactosUsuario.listarContactosUsuario.ListarContactosUsuarioController
 */
public class ListarContactosUsuarioControllerImpl extends ListarContactosUsuarioController
{


	private static Logger logger = Logger.getLogger(ListarContactosUsuarioControllerImpl.class);



    /**
     * @see es.pode.visualizador.presentacion.gestionContactosUsuario.listarContactosUsuario.ListarContactosUsuarioController#obtenerContactosUsuario(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionContactosUsuario.listarContactosUsuario.ObtenerContactosUsuarioForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerContactosUsuario(ActionMapping mapping, es.pode.visualizador.presentacion.gestionContactosUsuario.listarContactosUsuario.ObtenerContactosUsuarioForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String usuarioPublico=LdapUserDetailsUtils.getUsuario();
    	logger.info("Vamos a obtener los contactos para el usuario "+usuarioPublico);
    	Collection<ContactoConImagen> contactosConImagen=new ArrayList();
       try{
    	   ContactoVO[] contactos = this.getSrvPerfilPublico().listarContactosDeAgenda(usuarioPublico);
    	   if(contactos!=null && contactos.length>0){
    		   
    		   for(int i=0;i<contactos.length;i++){
	    		   ContactoConImagen contacto=new ContactoConImagen();
	    		   contacto.setId(contactos[i].getId());
	    		   contacto.setUsuarioContacto(contactos[i].getUsuarioContacto());
	    		   UsuarioPublicoVO usuario = this.getSrvPerfilPublico().obtenerUsuarioPublico(contactos[i].getUsuarioContacto());
	    		   if(usuario.getFoto().equals(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_USUARIO)){
	    			   contacto.setUrlImagenContacto(ImagenesAgrega.urlImagenDefectoUsuario(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_USUARIO)));
	  		       	}else{
	  		       		contacto.setUrlImagenContacto(AgregaPropertiesImpl.getInstance().getUrlNodo()+AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.IMAGENES_USUARIO_PUBLICO_URL)+"/"+usuario.getFoto());
	  		       	}
	    		   contacto.setDescripcion(usuario.getDescripcion());
	    		   contactosConImagen.add(contacto);
    		   }
    	   }
    	  form.setContactos(contactosConImagen);
    	   form.setTextoBusqueda(null);
       }catch(Exception e){
    	   logger.error("Error al obtener los contactos del usuario "+usuarioPublico);
       }
     }


public class ContactoConImagen {
        
	 	private java.lang.String usuarioContacto;
        
        private Long id;
        
        private String urlImagenContacto;
        
        private String descripcion;

		public String getDescripcion() {
			return descripcion;
		}



		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}



		public java.lang.String getUsuarioContacto() {
			return usuarioContacto;
		}



		public void setUsuarioContacto(java.lang.String usuarioContacto) {
			this.usuarioContacto = usuarioContacto;
		}



		public Long getId() {
			return id;
		}



		public void setId(Long id) {
			this.id = id;
		}



		public String getUrlImagenContacto() {
			return urlImagenContacto;
		}



		public void setUrlImagenContacto(String urlImagenContacto) {
			this.urlImagenContacto = urlImagenContacto;
		}



		public ContactoConImagen() {
        }

		
    	
    }



public void obtenerTextoUsuario(ActionMapping mapping,
		ObtenerTextoUsuarioForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	String texto=form.getTextoBusqueda();
	if(texto==null || texto.equals("") || texto.length()==0){
		logger.error("Hay que introducir algún criterio de búsqueda");
		throw new ValidatorException("{grupos.error.grupo.vacio}");
	}
	
}






}