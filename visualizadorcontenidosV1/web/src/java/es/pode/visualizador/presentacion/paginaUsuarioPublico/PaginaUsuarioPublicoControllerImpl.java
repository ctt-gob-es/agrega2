/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.visualizador.presentacion.paginaUsuarioPublico;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.adminusuarios.negocio.servicios.FavoritoVO;
import es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO;
import es.pode.adminusuarios.negocio.servicios.UsuarioPublicoVO;
import es.pode.adminusuarios.negocio.servicios.UsuarioVO;
import es.pode.publicacion.negocio.servicios.OdePublicadoVO;
import es.pode.publicacion.negocio.servicios.TransicionVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.url.ImagenODE;
import es.pode.soporte.url.ImagenesAgrega;
import es.pode.soporte.url.ODEPublico;
import es.pode.soporte.url.PerfilPublico;





/**
 * @see es.pode.visualizador.presentacion.paginaUsuarioPublico.PaginaUsuarioPublicoController
 */
public class PaginaUsuarioPublicoControllerImpl extends PaginaUsuarioPublicoController
{



	private static Logger logger = Logger.getLogger(PaginaUsuarioPublicoControllerImpl.class);
	private java.util.Properties pSpringProperties = null;

    /**
     * @see es.pode.visualizador.presentacion.paginaUsuarioPublico.PaginaUsuarioPublicoController#obtenerTodosDatosUsuarioPublico(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.paginaUsuarioPublico.ObtenerTodosDatosUsuarioPublicoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerTodosDatosUsuarioPublico(ActionMapping mapping, es.pode.visualizador.presentacion.paginaUsuarioPublico.ObtenerTodosDatosUsuarioPublicoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
	    	String usuario = form.getUsuario();
	    	
	//    	String usuarioRequest = (String)request.getSession().getAttribute("usuario").toString();
	    	logger.info("El usuario que obtenemos es "+usuario);
	    	String actionFavorito=form.getActionFavorito();
	    	String actionManual=form.getActionManual();
	    	String actionAuto=form.getActionAuto();
	    	String actionGrupos=form.getActionGrupos();
	    	
	    	form.setUrlUsuario(PerfilPublico.urlUsuarioPublico(usuario));
	    	logger.info("el action que recibimos es "+actionFavorito+" o "+actionManual+"o "+actionAuto+"o "+actionGrupos);
	    	Collection verMas=new ArrayList();
	       UsuarioPublicoVO usuarioPublico=this.getSrvPerfilPublico().obtenerUsuarioPublico(usuario);
	       Collection<Boolean> mostrar=new ArrayList();
	       if(usuarioPublico.getFoto().equals(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_USUARIO)){
	    	   form.setImagenUsuario(ImagenesAgrega.urlImagenDefectoUsuario(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_USUARIO)));
	       	}else{
	       	 form.setImagenUsuario(AgregaPropertiesImpl.getInstance().getUrlNodo()+AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.IMAGENES_USUARIO_PUBLICO_URL)+"/"+usuarioPublico.getFoto());
	       	}
	       
	       
		      
		       if(usuarioPublico.getCentroEducativo()!= null && !usuarioPublico.getCentroEducativo().equals("")){
		    	   form.setCentro(usuarioPublico.getCentroEducativo());
		       }else{
		    	   form.setCentro(null);
		       }
		       form.setNombre(usuarioPublico.getNombreUsuario());
		       if(usuarioPublico.getDescripcion()!=null && !usuarioPublico.getDescripcion().equals("")){
		    	   form.setDescripcion(usuarioPublico.getDescripcion());
		       }else{
		    	   form.setDescripcion(null);
		       }
		       FavoritoVO[] favoritosLimpios=null;
		       if(usuarioPublico.getMostrarFavoritos().equals(Boolean.TRUE)){
		    	   mostrar.add(Boolean.TRUE);
			       if(actionFavorito!=null && !actionFavorito.equals("")){
			    	   
			    	   favoritosLimpios =usuarioPublico.getFavoritoUsuario();
			    	   verMas.add(Boolean.FALSE); 
			       }else{
			    	   FavoritoVO[] favoritosSelec = usuarioPublico.getFavoritoUsuario();
			    	   if(favoritosSelec!=null && favoritosSelec.length>0){
					       if(favoritosSelec.length >new Long(getPropertyValue("cantidad_ver_publicos"))){
					    	   logger.info("Mas favoritos que los que pintamos ");
					    	   verMas.add(Boolean.TRUE); 
					    	   favoritosLimpios=new FavoritoVO[new Integer(getPropertyValue("cantidad_ver_publicos"))];
					    	   for(int i=0;i<favoritosLimpios.length;i++){
					    		   favoritosLimpios[i]=favoritosSelec[i];
					    	   }
					       }else{
					    	   verMas.add(Boolean.FALSE); 
					    	   favoritosLimpios=new FavoritoVO[favoritosSelec.length];
					    	   favoritosLimpios=favoritosSelec;
					       }
			    	   }else{
			    		   verMas.add(Boolean.FALSE); 
			    		   favoritosLimpios=new FavoritoVO[0];
			    	   }
			       }
		       }else{
		    	   verMas.add(Boolean.FALSE);
		    	   mostrar.add(Boolean.FALSE);
		       }
		       GrupoPublicoVO[] gruposLimpios=null;
		       if(usuarioPublico.getMostrarGrupos().equals(Boolean.TRUE)) {
		    	   mostrar.add(Boolean.TRUE);
			       if(actionGrupos!=null && !actionGrupos.equals("")){
			    	   gruposLimpios=usuarioPublico.getGrupoPublico();
			    	   verMas.add(Boolean.FALSE); 
			       }else{
				       GrupoPublicoVO[] gruposSelec = usuarioPublico.getGrupoPublico();
				       if(gruposSelec!=null && gruposSelec.length>0){
					       if(gruposSelec.length>new Long(getPropertyValue("cantidad_ver_publicos"))){
					    	   logger.info("Mas grupos que los que pintamos");
					    	   verMas.add(Boolean.TRUE);
					    	   gruposLimpios=new GrupoPublicoVO[new Integer(getPropertyValue("cantidad_ver_publicos"))];
					    	   for(int i=0;i<gruposLimpios.length;i++){
					    		   gruposLimpios[i]=gruposSelec[i];
					    	   }
					    	   
					       }else{
					    	   verMas.add(Boolean.FALSE);
					    	   gruposLimpios=new GrupoPublicoVO[gruposSelec.length];
					    	   gruposLimpios=gruposSelec;
					       }
				       }else{
				    	   verMas.add(Boolean.FALSE);
				    	   gruposLimpios=new GrupoPublicoVO[0];
				       }
			       }
		       }else{
		    	   verMas.add(Boolean.FALSE);
		    	   mostrar.add(Boolean.FALSE);
		       }
		       
		       OdePublicadoVO[] publicadosLimpios=null;
		       TransicionVO[] autonomosLimpios=null;
		       if(usuarioPublico.getMostrarObjetos().equals(Boolean.TRUE)) {
		    	   mostrar.add(Boolean.TRUE);
			       if(actionManual!=null && !actionManual.equals("")){
			    	   publicadosLimpios= this.getSrvPublicacionService().obtenODEsPublicadoUsuario(usuario);
			    	   verMas.add(Boolean.FALSE); 
			       }else{
			    	   OdePublicadoVO[] odesPublicados = this.getSrvPublicacionService().obtenODEsPublicadoUsuario(usuario);
				       if(odesPublicados!=null && odesPublicados.length>0){
					       if(odesPublicados.length>new Long(getPropertyValue("cantidad_ver_publicos"))){
					    	   logger.info("Mas publicados que los que pintamos");
					    	   verMas.add(Boolean.TRUE);
					    	   publicadosLimpios=new OdePublicadoVO[new Integer(getPropertyValue("cantidad_ver_publicos"))];
					    	   for(int i=0;i<publicadosLimpios.length;i++){
					    		   publicadosLimpios[i]=odesPublicados[i];
					    	   }
					    	   
					       }else{
					    	   verMas.add(Boolean.FALSE);
					    	   publicadosLimpios=new OdePublicadoVO[odesPublicados.length];
					    	   publicadosLimpios=odesPublicados;
					       }
				       }else{
				    	   verMas.add(Boolean.FALSE);
				    	   publicadosLimpios=new OdePublicadoVO[0];
				       }
			       }
		       
		       
		      
			       if(actionAuto!=null && !actionAuto.equals("")){
			    	   autonomosLimpios= this.getSrvPublicacionService().obtenODEsPublicadosAutonomoPorUsuario(usuario);
			    	   verMas.add(Boolean.FALSE); 
			       }else{
				       TransicionVO[] autonomosPublicados = this.getSrvPublicacionService().obtenODEsPublicadosAutonomoPorUsuario(usuario);
				       if(autonomosPublicados!=null && autonomosPublicados.length>0){
					       if(autonomosPublicados.length>new Long(getPropertyValue("cantidad_ver_publicos"))){
					    	   logger.info("Mas publicados que los que pintamos");
					    	   verMas.add(Boolean.TRUE);
					    	   autonomosLimpios=new TransicionVO[new Integer(getPropertyValue("cantidad_ver_publicos"))];
					    	   for(int i=0;i<autonomosLimpios.length;i++){
					    		   autonomosLimpios[i]=autonomosPublicados[i];
					    	   }
					    	   
					       }else{
					    	   verMas.add(Boolean.FALSE);
					    	   autonomosLimpios=new TransicionVO[autonomosPublicados.length];
					    	   autonomosLimpios=autonomosPublicados;
					       }
				       }else{
				    	   verMas.add(Boolean.FALSE);
				    	   autonomosLimpios=new TransicionVO[0];
				       }
			       }
		       }else{
		    	   verMas.add(Boolean.FALSE);
		    	   verMas.add(Boolean.FALSE);
		    	   mostrar.add(Boolean.FALSE);
		       }
		       
		       GrupoPublicoConURLs[] gruposConURLs = insertarUrls(gruposLimpios);
		       FavoritoConURLs[] favoritosConUrl = insertarUrl(favoritosLimpios);
		       form.setOdesFavoritosAsArray(favoritosConUrl);
		       form.setGruposPublicosAsArray(gruposConURLs);
		       OdePublicadoVOConUrls[] publicadosUrl = insertarUrls(publicadosLimpios);
		       form.setPublicadosAsArray(publicadosUrl);
		       String idioma=((Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
		       AutonomoConURLs[] publicadosAutonomaURL = insertarUrlsAutonomos(autonomosLimpios,idioma);
		       form.setPublicadosAutonomaAsArray(publicadosAutonomaURL);
		       form.setVerMas(verMas);
		       form.setActionAuto(null);
		       form.setActionFavorito(null);
		       form.setActionGrupos(null);
		       form.setActionManual(null);
		       form.setMostrar(mostrar);
		       String imagenEncodada=PerfilPublico.encodarTexto(form.getImagenUsuario());
		       String urlFeed=PerfilPublico.encodarTexto(AgregaPropertiesImpl.getInstance().getUrlNodo()+"/widgets/usuario/"+usuario+"/"+this.getSrvPropiedadService().get(AgregaProperties.NUMERO_ODES_EN_FLASH));
		       String codigoEmbeb="<object classid='clsid:d27cdb6e-ae6d-11cf-96b8-444553540000' codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0' width='280' height='656' id='scrollerAgrega_vert' align='middle'>" +
		       		" <param name='allowScriptAccess' value='sameDomain' />" +
		       		"<param name='allowFullScreen' value='false' />" +
		       		"<param name='FlashVars' value='imagenUsr="+imagenEncodada+"&nombreUsr="+form.getNombre()+"&urlFeed="+urlFeed+"'>" +
		       				"  <param name='movie' value='"+AgregaPropertiesImpl.getInstance().getUrlNodo()+"/static/flash/scrollerAgrega_widget/scrollerAgrega_vert.swf' />" +
		       						"<param name='quality' value='high' /><param name='bgcolor' value='#333333' />   " +
		       						"<embed src='"+AgregaPropertiesImpl.getInstance().getUrlNodo()+"/static/flash/scrollerAgrega_widget/scrollerAgrega_vert.swf' quality='high' bgcolor='#333333' width='280' height='656' name='scrollerAgrega_vert' align='middle' allowScriptAccess='sameDomain' allowFullScreen='false' type='application/x-shockwave-flash' pluginspage='http://www.adobe.com/go/getflashplayer' FlashVars = 'imagenUsr="+imagenEncodada+"&nombreUsr="+form.getNombre()+"&urlFeed="+urlFeed+"' />" +
		       								"</object>";
		       form.setCodigoEmbeb(codigoEmbeb);
	       
    }

    private String getPropertyValue(String sKey) throws IOException {
    	try{
    		InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/spring_visualizadorcontenidos.properties");
			if (this.pSpringProperties == null) {
				pSpringProperties = new java.util.Properties();
				pSpringProperties.load(fIsSpringProperties);
			}
			fIsSpringProperties.close();
			logger.debug("PaginaUsuarioPublico - getPropertyValue: Propiedad recuperada: " + sKey + " : "+ pSpringProperties.getProperty(sKey));
//				 devolvemos la propiedad
            
        }catch (Exception e){
 		logger.error("PaginaUsuarioPublico - Error recuperando propiedad de spring_visualizadorcontenidos.properties=",e);
        }
		
		return pSpringProperties.getProperty(sKey);
	}
    private FavoritoConURLs[] insertarUrl(FavoritoVO[] favoritos){
    	FavoritoConURLs[] favoritosURL=null;
    	try{
    		Collection<FavoritoConURLs> collections=new ArrayList();
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
    		favoritosURL=collections.toArray(new FavoritoConURLs[0]);
    		
    	}catch(Exception e){
    		logger.error("Error al insertar la url del ode");
    	}
    	return favoritosURL;
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

	 private OdePublicadoVOConUrls[] insertarUrls(OdePublicadoVO[] publicados){
		 OdePublicadoVOConUrls[] publicadoURL=null;
		 
	    	try{
	    		Collection<OdePublicadoVOConUrls> collections=new ArrayList();
	    		for(int i=0;i<publicados.length;i++){
	    			OdePublicadoVOConUrls publicadosCon=new OdePublicadoVOConUrls();
	    			String identificador=publicados[i].getIdODE();
	    			String idioma=publicados[i].getIdioma();
	    			String urlFicha=ODEPublico.urlFichaODEPublicado(identificador, idioma);
	    			String urlPrevisualizar=ODEPublico.urlPrevisualizaODEPublicado(identificador, idioma);
	    			String urlImagen=ImagenODE.urlImagenODE(identificador);
	    			publicadosCon.setUrlFicha(urlFicha);
	    			publicadosCon.setUrlPrevisualizar(urlPrevisualizar);
	    			publicadosCon.setUrlImagen(urlImagen);
	    			publicadosCon.setIdODE(identificador);
	    			publicadosCon.setTitulo(publicados[i].getTitulo());
	    			publicadosCon.setIdioma(idioma);
	    			collections.add(publicadosCon);
	    		}
	    		publicadoURL=collections.toArray(new OdePublicadoVOConUrls[0]);
	    		
	    	}catch(Exception e){
	    		logger.error("Error al insertar la url del ode");
	    	}
	    	return publicadoURL;
	    }
	 
	 public class OdePublicadoVOConUrls {
	        
		 	private java.lang.String titulo;
		 	
		 	private java.lang.String idODE;

		 	private java.lang.String idioma;
		 	
			private java.lang.String urlImagen;
	        
	        private java.lang.String urlFicha;
	        
	        private java.lang.String urlPrevisualizar;
	        
	        public java.lang.String getTitulo() {
				return titulo;
			}


			public void setTitulo(java.lang.String titulo) {
				this.titulo = titulo;
			}


			public java.lang.String getIdODE() {
				return idODE;
			}


			public void setIdODE(java.lang.String idODE) {
				this.idODE = idODE;
			}



			public java.lang.String getUrlImagen() {
				return urlImagen;
			}


			public void setUrlImagen(java.lang.String urlImagen) {
				this.urlImagen = urlImagen;
			}


			public java.lang.String getUrlFicha() {
				return urlFicha;
			}


			public void setUrlFicha(java.lang.String urlFicha) {
				this.urlFicha = urlFicha;
			}


			public java.lang.String getUrlPrevisualizar() {
				return urlPrevisualizar;
			}


			public void setUrlPrevisualizar(java.lang.String urlPrevisualizar) {
				this.urlPrevisualizar = urlPrevisualizar;
			}
			
			public java.lang.String getIdioma() {
				return idioma;
			}


			public void setIdioma(java.lang.String idioma) {
				this.idioma = idioma;
			}


			
	
	
	        public OdePublicadoVOConUrls() {
	        }
	 }
	 
	 private AutonomoConURLs[] insertarUrlsAutonomos(TransicionVO[] autonomos,String idioma){
		 AutonomoConURLs[] publicadoURL=null;
		
	    	try{
	    		Collection<AutonomoConURLs> collections=new ArrayList();
	    		for(int i=0;i<autonomos.length;i++){
	    			AutonomoConURLs publicadosCon=new AutonomoConURLs();
	    			String identificador=autonomos[i].getIdODE();
	    			String urlPrevisualizar=ODEPublico.urlPrevisualizaODEPublicadoAutonomo(identificador, idioma);
	    			publicadosCon.setUrlPrevisualizar(urlPrevisualizar);
	    			publicadosCon.setIdODE(identificador);
	    			publicadosCon.setTitulo(autonomos[i].getTitulo());
	    			publicadosCon.setIdUsuario(autonomos[i].getIdUsuario());
	    			publicadosCon.setUrlImagen(ImagenesAgrega.urlImagenDefectoAutopublicado());
	    			collections.add(publicadosCon);
	    		}
	    		publicadoURL=collections.toArray(new AutonomoConURLs[0]);
	    		
	    	}catch(Exception e){
	    		logger.error("Error al insertar la url del ode");
	    	}
	    	return publicadoURL;
	    }
	 
	 public class AutonomoConURLs {
	        
		 	private java.lang.String titulo;
		 	
		 	private java.lang.String idODE;

		 	private java.lang.String idUsuario;
		 	
		 	private java.lang.String urlImagen;
		 	
	        
	        public java.lang.String getUrlImagen() {
				return urlImagen;
			}


			public void setUrlImagen(java.lang.String urlImagen) {
				this.urlImagen = urlImagen;
			}


			private java.lang.String urlPrevisualizar;
	
	
	        public AutonomoConURLs() {
	        }


			public java.lang.String getTitulo() {
				return titulo;
			}


			public void setTitulo(java.lang.String titulo) {
				this.titulo = titulo;
			}


			public java.lang.String getIdODE() {
				return idODE;
			}


			public void setIdODE(java.lang.String idODE) {
				this.idODE = idODE;
			}


			public java.lang.String getIdUsuario() {
				return idUsuario;
			}


			public void setIdUsuario(java.lang.String idUsuario) {
				this.idUsuario = idUsuario;
			}


			public java.lang.String getUrlPrevisualizar() {
				return urlPrevisualizar;
			}


			public void setUrlPrevisualizar(java.lang.String urlPrevisualizar) {
				this.urlPrevisualizar = urlPrevisualizar;
			}
	 }
	 private GrupoPublicoConURLs[] insertarUrls(GrupoPublicoVO[] grupos){
		 GrupoPublicoConURLs[] gruposURL=null;
		
	    	try{
	    		Collection<GrupoPublicoConURLs> collections=new ArrayList();
	    		for(int i=0;i<grupos.length;i++){
	    			GrupoPublicoConURLs grupoCon=new GrupoPublicoConURLs();
	    			grupoCon.setId(grupos[i].getId());
	    			String imagenGrupo=grupos[i].getImagenGrupo();
	    		       if(imagenGrupo.equals("ImagenDefectoGrupo1"))
	    		    	   grupoCon.setImagenGrupo(ImagenesAgrega.urlImagenDefectoGrupo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_1)));
	    	    	   else if(imagenGrupo.equals("ImagenDefectoGrupo2"))
	    	    		   grupoCon.setImagenGrupo(ImagenesAgrega.urlImagenDefectoGrupo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_2)));
	    	    	   else if(imagenGrupo.equals("ImagenDefectoGrupo3"))
	    	    		   grupoCon.setImagenGrupo(ImagenesAgrega.urlImagenDefectoGrupo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_3)));
	    	    	   else if(imagenGrupo.equals("ImagenDefectoGrupo4"))
	    	    		   grupoCon.setImagenGrupo(ImagenesAgrega.urlImagenDefectoGrupo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_4)));
	    	    	   else
	    	    		   grupoCon.setImagenGrupo(ImagenesAgrega.urlImagenDefectoGrupo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_5)));
		    		grupoCon.setUrlGrupo(PerfilPublico.urlGrupoPublico(grupos[i].getNombre()));
		    		grupoCon.setAdministrador(grupos[i].getAdministrador());
		    		if(grupos[i].getDescripcion()!=null && !grupos[i].getDescripcion().equals("")){
		    			grupoCon.setDescripcion(grupos[i].getDescripcion());
		    		}else{
		    			grupoCon.setDescripcion(null);
		    		}
		    		grupoCon.setNombre(grupos[i].getNombre());
	    			
	    			collections.add(grupoCon);
	    		}
	    		gruposURL=collections.toArray(new GrupoPublicoConURLs[0]);
	    		
	    	}catch(Exception e){
	    		logger.error("Error al insertar la url del ode");
	    	}
	    	return gruposURL;
	    }
	 public class GrupoPublicoConURLs {
		    /* Nombre de la foto del usuario */
		    private java.lang.String imagenGrupo;

		    private java.lang.String urlGrupo;

		    private java.lang.Long id;

		    /* Descripcion del usuario publico */
		    private java.lang.String descripcion;

		    private java.lang.String administrador;
		    
		    public java.lang.String getImagenGrupo() {
				return imagenGrupo;
			}


			public void setImagenGrupo(java.lang.String imagenGrupo) {
				this.imagenGrupo = imagenGrupo;
			}


			public java.lang.String getUrlGrupo() {
				return urlGrupo;
			}


			public void setUrlGrupo(java.lang.String urlGrupo) {
				this.urlGrupo = urlGrupo;
			}


			public java.lang.Long getId() {
				return id;
			}


			public void setId(java.lang.Long id) {
				this.id = id;
			}


			public java.lang.String getDescripcion() {
				return descripcion;
			}


			public void setDescripcion(java.lang.String descripcion) {
				this.descripcion = descripcion;
			}


			public java.lang.String getAdministrador() {
				return administrador;
			}


			public void setAdministrador(java.lang.String administrador) {
				this.administrador = administrador;
			}


			public java.lang.String getNombre() {
				return nombre;
			}


			public void setNombre(java.lang.String nombre) {
				this.nombre = nombre;
			}


			private java.lang.String nombre;
		    
		   
		    public GrupoPublicoConURLs() {
		    }
	 }

	public String estaActivoUsuario(ActionMapping mapping,
			EstaActivoUsuarioForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String vuelta="Activo";
		String usuario = form.getUsuario();
		String actionAuto=form.getActionAuto();
		String actionManual=form.getActionManual();
		String actionFavorito=form.getActionFavorito();
		String actionGrupos=form.getActionGrupos();
		 
		
		if(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PERFIL_PUBLICO).equals("true")){
			UsuarioVO usuarioBase = this.getSrvAdminUsuariosService().obtenerDatosUsuario(usuario);
			if(usuarioBase!=null && usuarioBase.getUsuario()!=null && usuarioBase.getUsuarioPublico()!=null){
				UsuarioPublicoVO usuarioPublico=this.getSrvPerfilPublico().obtenerUsuarioPublico(usuario);
			 if(usuarioPublico !=null && usuarioPublico.getUsuario()!=null ){
				 
				 if(usuarioPublico.getActivo()==Boolean.TRUE && usuarioBase.getFechaDesactivacion()==null){
					 vuelta="Activo";
				 }else{
					 form.setDesactivadoCodigo("desactivado");
			    	   vuelta="UsuarioDesactivado";
				 }
			 }else{
	    	   //TODO Saltar exception NotFoundException
	    	   logger.error("Ese usuario no existe");
	    	   form.setDesactivadoCodigo("noExiste");
	    	   vuelta="UsuarioDesactivado";
	       }
			}else{
				logger.error("Ese usuario no existe");
		    	   form.setDesactivadoCodigo("noExiste");
		    	   vuelta="UsuarioDesactivado";
			}
		}else{
			logger.error("Esta comunidad no tiene perfil publico activado");
			vuelta="PerfilDesactivado";
	 	   
		}
		return vuelta;
	}


}