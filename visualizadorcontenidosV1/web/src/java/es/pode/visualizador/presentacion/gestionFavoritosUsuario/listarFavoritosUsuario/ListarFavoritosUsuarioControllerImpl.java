/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.visualizador.presentacion.gestionFavoritosUsuario.listarFavoritosUsuario;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.FavoritoVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.url.ImagenODE;
import es.pode.soporte.url.ODEPublico;
import es.pode.visualizador.presentacion.gestionContactosUsuario.listarContactosUsuario.ListarContactosUsuarioControllerImpl.ContactoConImagen;



/**
 * @see es.pode.visualizador.presentacion.gestionFavoritosUsuario.listarFavoritosUsuario.ListarFavoritosUsuarioController
 */
public class ListarFavoritosUsuarioControllerImpl extends ListarFavoritosUsuarioController
{



	private static Logger logger = Logger.getLogger(ListarFavoritosUsuarioControllerImpl.class);


    /**
     * @see es.pode.visualizador.presentacion.gestionFavoritosUsuario.listarFavoritosUsuario.ListarFavoritosUsuarioController#obtenerFavoritosUsuario(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionFavoritosUsuario.listarFavoritosUsuario.ObtenerFavoritosUsuarioForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerFavoritosUsuario(ActionMapping mapping, es.pode.visualizador.presentacion.gestionFavoritosUsuario.listarFavoritosUsuario.ObtenerFavoritosUsuarioForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String usuarioPublico=LdapUserDetailsUtils.getUsuario();
    	logger.info("Vamos a obtener los favoritos para el usuario "+usuarioPublico);
    	Collection<ContactoConImagen> contactosConImagen=new ArrayList();
       try{
    	   Collection<FavoritoConURLs> collections=new ArrayList();
    	   FavoritoVO[] favoritos = this.getSrvPerfilPublico().listarFavoritosUsuario(usuarioPublico);
    	   if(favoritos!=null && favoritos.length>0){
    		  
    		   for(int i=0;i<favoritos.length;i++){
	    			FavoritoConURLs favoritoCon=new FavoritoConURLs();
	       			String identificador=favoritos[i].getId_mec();
	       			String urlFicha=ODEPublico.urlFichaODEPublicado(identificador, favoritos[i].getIdioma());
	       			String urlPrevisualizar=ODEPublico.urlPrevisualizaODEPublicado(identificador, favoritos[i].getIdioma());
	       			String urlImagen=ImagenODE.urlImagenODE(identificador);
	       			favoritoCon.setUrlImagen(urlImagen);
	       			favoritoCon.setUrlFicha(urlFicha);
	       			favoritoCon.setUrlPrevisualizar(urlPrevisualizar);
	       			favoritoCon.setId_mec(identificador);
	       			favoritoCon.setTitulo(favoritos[i].getTitulo());
	       			favoritoCon.setIdioma( favoritos[i].getIdioma());
	       			collections.add(favoritoCon);
    		   }
    	   }
    	  form.setFavoritos(collections);
    	   
       }catch(Exception e){
    	   logger.error("Error al obtener los favoritos del usuario "+usuarioPublico);
       }
     }
    

	 public class FavoritoConURLs {
	        
		 	private java.lang.String id_mec;
		 	
		 	private java.lang.String idioma;

			private java.lang.String titulo;
	
	        private java.lang.String urlFicha;
	        
	        private java.lang.String urlImagen;
	        
	        private java.lang.String urlPrevisualizar;
	
	
	        public FavoritoConURLs() {
	        }
	
			public java.lang.String getUrlFicha() {
				return urlFicha;
			}

			public void setUrlFicha(java.lang.String urlFicha) {
				this.urlFicha = urlFicha;
			}

			public java.lang.String getUrlImagen() {
				return urlImagen;
			}

			public void setUrlImagen(java.lang.String urlImagen) {
				this.urlImagen = urlImagen;
			}

			public java.lang.String getUrlPrevisualizar() {
				return urlPrevisualizar;
			}

			public void setUrlPrevisualizar(java.lang.String urlPrevisualizar) {
				this.urlPrevisualizar = urlPrevisualizar;
			}

			public java.lang.String getId_mec() {
				return id_mec;
			}
	
	
			public void setId_mec(java.lang.String id_mec) {
				this.id_mec = id_mec;
			}
	
		   public java.lang.String getIdioma() {
				return idioma;
			}

			public void setIdioma(java.lang.String idioma) {
				this.idioma = idioma;
			}
	
			public java.lang.String getTitulo() {
				return titulo;
			}
	
	
			public void setTitulo(java.lang.String titulo) {
				this.titulo = titulo;
			}
	
	
	
	    	
	    }


	public void obtenerTexto(ActionMapping mapping, ObtenerTextoForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String texto=form.getTextoBusqueda();
		if(texto==null || texto.equals("") || texto.length()==0){
			logger.error("Hay que introducir algún criterio de búsqueda");
			throw new ValidatorException("{grupos.error.grupo.vacio}");
		}
		
	}









}