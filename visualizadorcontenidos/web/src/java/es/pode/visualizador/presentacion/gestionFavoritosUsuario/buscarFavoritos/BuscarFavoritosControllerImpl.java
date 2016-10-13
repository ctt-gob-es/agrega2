/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.visualizador.presentacion.gestionFavoritosUsuario.buscarFavoritos;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.FavoritoAnadidioVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.url.ImagenODE;
import es.pode.soporte.url.ODEPublico;



/**
 * @see es.pode.visualizador.presentacion.gestionFavoritosUsuario.buscarFavoritos.BuscarFavoritosController
 */
public class BuscarFavoritosControllerImpl extends BuscarFavoritosController
{


	private static Logger logger = Logger.getLogger(BuscarFavoritosControllerImpl.class);



    /**
     * @see es.pode.visualizador.presentacion.gestionFavoritosUsuario.buscarFavoritos.BuscarFavoritosController#listarFavoritos(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionFavoritosUsuario.buscarFavoritos.ListarFavoritosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void listarFavoritos(ActionMapping mapping, es.pode.visualizador.presentacion.gestionFavoritosUsuario.buscarFavoritos.ListarFavoritosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String titulo=form.getTextoBusqueda();
    	logger.info("Vamos a buscar favortios de titulo "+titulo);
    	try{
    		Collection<FavoritoAnadidoConURLs> collections=new ArrayList();
    		String usuario=LdapUserDetailsUtils.getUsuario();
    		
    		 FavoritoAnadidioVO[] favoritosBusqueda = this.getSrvPerfilPublico().buscarFavoritosPorTitulo(titulo, usuario);
    		 
    		 if(favoritosBusqueda!=null && favoritosBusqueda.length>0){
       		  
      		   for(int i=0;i<favoritosBusqueda.length;i++){
  	    			FavoritoAnadidoConURLs favoritoCon=new FavoritoAnadidoConURLs();
  	       			String identificador=favoritosBusqueda[i].getId_mec();
  	       			String urlFicha=ODEPublico.urlFichaODEPublicado(identificador, favoritosBusqueda[i].getIdioma());
  	       			String urlPrevisualizar=ODEPublico.urlPrevisualizaODEPublicado(identificador, favoritosBusqueda[i].getIdioma());
  	       			String urlImagen=ImagenODE.urlImagenODE(identificador);
  	       			favoritoCon.setUrlImagen(urlImagen);
  	       			favoritoCon.setUrlFicha(urlFicha);
  	       			favoritoCon.setUrlPrevisualizar(urlPrevisualizar);
  	       			favoritoCon.setId_mec(identificador);
  	       			favoritoCon.setTitulo(favoritosBusqueda[i].getTitulo());
  	       			favoritoCon.setIdioma( favoritosBusqueda[i].getIdioma());
  	       			favoritoCon.setEsFavorito( favoritosBusqueda[i].getEsFavorito());
  	       			collections.add(favoritoCon);
      		   }
      	   }
      	  form.setFavoritosEncontrados(collections);
    		 

    	}catch(Exception e){
    		logger.error("Error: "+e);
    		throw new ValidatorException("{grupos.publicos.buscar.error}");
    	}
     }







    /**
     * @see es.pode.visualizador.presentacion.gestionFavoritosUsuario.buscarFavoritos.BuscarFavoritosController#obtenerTexto(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionFavoritosUsuario.buscarFavoritos.ObtenerTextoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerTexto(ActionMapping mapping, es.pode.visualizador.presentacion.gestionFavoritosUsuario.buscarFavoritos.ObtenerTextoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String texto=form.getTextoBusqueda();
    	if(texto==null || texto.equals("") || texto.length()==0){
    		logger.error("Hay que introducir algún criterio de búsqueda");
    		throw new ValidatorException("{grupos.error.grupo.vacio}");
    	}
     }



    public class FavoritoAnadidoConURLs {
        
	 	private java.lang.String id_mec;
	 	
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

		public Boolean getEsFavorito() {
			return esFavorito;
		}

		public void setEsFavorito(Boolean esFavorito) {
			this.esFavorito = esFavorito;
		}

		private java.lang.String idioma;

		private java.lang.String titulo;

        private java.lang.String urlFicha;
        
        private java.lang.String urlImagen;
        
        private java.lang.String urlPrevisualizar;
        
        private Boolean esFavorito;
        
        public FavoritoAnadidoConURLs() {
        }
    }





}