// license-header java merge-point
package es.pode.visualizador.presentacion.paginaGrupoPublico;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO;
import es.pode.adminusuarios.negocio.servicios.OdeGrupoVO;
import es.pode.adminusuarios.negocio.servicios.UsuarioPublicoVO;
import es.pode.soporte.url.ImagenODE;
import es.pode.soporte.url.ImagenesAgrega;
import es.pode.soporte.url.ODEPublico;
import es.pode.soporte.url.PerfilPublico;



/**
 * @see es.pode.visualizador.presentacion.paginaGrupoPublico.PaginaGrupoPublicoController
 */
public class PaginaGrupoPublicoControllerImpl extends PaginaGrupoPublicoController
{


	private static Logger logger = Logger.getLogger(PaginaGrupoPublicoControllerImpl.class);
	private java.util.Properties pSpringProperties = null;



    /**
     * @see es.pode.visualizador.presentacion.paginaGrupoPublico.PaginaGrupoPublicoController#obtenerTodosDatosGrupoPublico(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.paginaGrupoPublico.ObtenerTodosDatosGrupoPublicoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerTodosDatosGrupoPublico(ActionMapping mapping, es.pode.visualizador.presentacion.paginaGrupoPublico.ObtenerTodosDatosGrupoPublicoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
    	
	    	String nombre = form.getNombre();
	    	
	    	form.setUrlGrupo(PerfilPublico.urlGrupoPublico(nombre));
	//    	String usuarioRequest = (String)request.getSession().getAttribute("usuario").toString();
	    	logger.info("El grupo que obtenemos es "+nombre);
	    	String actionOde=form.getActionOde();
	    	String actionUsuario=form.getActionUsuario();
	    	logger.info("el action que recibimos es "+actionOde+" o "+actionUsuario);
	    	Collection verMas=new ArrayList();
	       GrupoPublicoVO grupoPublico=this.getSrvPerfilPublico().obtenerGrupoPublico(nombre);
	       String imagenGrupo=grupoPublico.getImagenGrupo();

	       if(imagenGrupo.equals("ImagenDefectoGrupo1"))
	    	   form.setImagenGrupo(ImagenesAgrega.urlImagenDefectoGrupo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_1)));
    	   else if(imagenGrupo.equals("ImagenDefectoGrupo2"))
    		   form.setImagenGrupo(ImagenesAgrega.urlImagenDefectoGrupo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_2)));
    	   else if(imagenGrupo.equals("ImagenDefectoGrupo3"))
    		   form.setImagenGrupo(ImagenesAgrega.urlImagenDefectoGrupo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_3)));
    	   else if(imagenGrupo.equals("ImagenDefectoGrupo4"))
    		   form.setImagenGrupo(ImagenesAgrega.urlImagenDefectoGrupo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_4)));
    	   else
    		   form.setImagenGrupo(ImagenesAgrega.urlImagenDefectoGrupo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_5)));
		      
		       form.setNombre(grupoPublico.getNombre());
		       form.setAdministrador(grupoPublico.getAdministrador());
		       if(grupoPublico.getDescripcion()!=null && !grupoPublico.getDescripcion().equals("")){
		    	   form.setDescripcion(grupoPublico.getDescripcion());
		       }else{
		    	   form.setDescripcion(null);
		       }
		       OdeGrupoVO[] odesLimpios=null;
		       if(actionOde!=null && !actionOde.equals("")){
		    	   odesLimpios = grupoPublico.getOdeGrupo();
		    	   verMas.add(Boolean.FALSE); 
		       }else{
			       OdeGrupoVO[] odes = grupoPublico.getOdeGrupo();
			       if(odes!=null && odes.length>0){
				       if(odes.length >new Long(getPropertyValue("cantidad_ver_publicos"))){
				    	   logger.info("Mas odes que los que pintamos ");
				    	   verMas.add(Boolean.TRUE); 
				    	   odesLimpios=new OdeGrupoVO[new Integer(getPropertyValue("cantidad_ver_publicos"))];
				    	   for(int i=0;i<odesLimpios.length;i++){
				    		   odesLimpios[i]=odes[i];
				    	   }
				       }else{
				    	   verMas.add(Boolean.FALSE); 
				    	   odesLimpios=new OdeGrupoVO[odes.length];
				    	   odesLimpios=odes;
				       }
			       }else{
			    	   verMas.add(Boolean.FALSE); 
			    	   odesLimpios=new OdeGrupoVO[0]; 
			       }
		       }
		       UsuarioPublicoVO[] usuariosLimpios=null;// Les vamos a insertar las URLs cortas
		       if(actionUsuario!=null && !actionUsuario.equals("")){
		    	   usuariosLimpios=  this.getSrvPerfilPublico().listarUsuariosDeGrupo(nombre);
	//	    	  grupoPublico.getUsuarioPublico();
		    	  verMas.add(Boolean.FALSE); 
		       }else{
		    	   
		    	   UsuarioPublicoVO[] usuarios = this.getSrvPerfilPublico().listarUsuariosDeGrupo(nombre);
		    	   if(usuarios!=null && usuarios.length>0){
			    	   if(usuarios.length >new Long(getPropertyValue("cantidad_ver_publicos"))){
			    		   logger.info("Mas usuarios que los que pintamos ");
				    	   verMas.add(Boolean.TRUE);
				    	   usuariosLimpios=new UsuarioPublicoVO[new Integer(getPropertyValue("cantidad_ver_publicos"))];
				    	   for(int i=0;i<usuariosLimpios.length;i++){
				    		   usuariosLimpios[i]=usuarios[i];
				    	   }
			    	   }else{
			    		   verMas.add(Boolean.FALSE); 
				    	   usuariosLimpios=new UsuarioPublicoVO[usuarios.length];
				    	   usuariosLimpios=usuarios;
			    	   }
		    	   }else{
		    		   verMas.add(Boolean.FALSE); 
			    	   usuariosLimpios=new UsuarioPublicoVO[0]; 
		    	   }
		       }
		       OdeConURLs[] odesConURL = insertarUrl(odesLimpios);
		       form.setOdesAsArray(odesConURL);
		       UsuarioPublicoConURLs[] usuariosConURL = insertarUrl(usuariosLimpios);
		      
		       form.setUsuariosAsArray(usuariosConURL);
		       form.setVerMas(verMas);
		       form.setActionOde(null);
		       form.setActionUsuario(null);
		       String imagenEncodada=PerfilPublico.encodarTexto(form.getImagenGrupo());
		       String urlFeed=PerfilPublico.encodarTexto(AgregaPropertiesImpl.getInstance().getUrlNodo()+"/widgets/grupo/"+nombre+"/"+this.getSrvPropiedadService().getValorPropiedad(AgregaProperties.NUMERO_ODES_EN_FLASH));
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

    private OdeConURLs[] insertarUrl(OdeGrupoVO[] odes){
    	OdeConURLs[] odesURL=null;
    	try{
    		Collection<OdeConURLs> collections=new ArrayList();
    		for(int i=0;i<odes.length;i++){
    			OdeConURLs odeCon=new OdeConURLs();
    			String identificador=odes[i].getId_mec();
    			String urlFicha=ODEPublico.urlFichaODEPublicado(identificador, odes[i].getIdioma());
    			String urlPrevisualizar=ODEPublico.urlPrevisualizaODEPublicado(identificador, odes[i].getIdioma());
    			String urlImagen=ImagenODE.urlImagenODE(identificador);
    			odeCon.setUrlImagen(urlImagen);
    			odeCon.setUrlFicha(urlFicha);
    			odeCon.setUrlPrevisualizar(urlPrevisualizar);
    			odeCon.setId_mec(identificador);
    			odeCon.setTitulo(odes[i].getTitulo());
    			odeCon.setIdioma( odes[i].getIdioma());
    			collections.add(odeCon);
    		}
    		odesURL=collections.toArray(new OdeConURLs[0]);
    		
    	}catch(Exception e){
    		logger.error("Error al insertar la url del ode");
    	}
    	return odesURL;
    }

	 public class OdeConURLs {
	        
		 	private java.lang.String id_mec;
		 	
		 	private java.lang.String idioma;

			private java.lang.String titulo;
	
			 private java.lang.String urlFicha;
		        
		    private java.lang.String urlImagen;
		        
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


			private java.lang.String urlPrevisualizar;
	
	
	        public OdeConURLs() {
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
	 
	 private UsuarioPublicoConURLs[] insertarUrl(UsuarioPublicoVO[] usuarios){
		 UsuarioPublicoConURLs[] usuariosURL=null;
	    	try{
	    		Collection<UsuarioPublicoConURLs> collections=new ArrayList();
	    		for(int i=0;i<usuarios.length;i++){
	    			UsuarioPublicoConURLs usuarioCon=new UsuarioPublicoConURLs();
	    			String imagenUsuario=usuarios[i].getFoto();
	    			 if(imagenUsuario.equals(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_USUARIO)){
	    				 usuarioCon.setFoto(ImagenesAgrega.urlImagenDefectoUsuario(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_USUARIO)));
	  		       	}else{
	  		       		usuarioCon.setFoto(AgregaPropertiesImpl.getInstance().getUrlNodo()+AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.IMAGENES_USUARIO_PUBLICO_URL)+"/"+imagenUsuario);
	  		       	}
	    			 if(usuarios[i].getCentroEducativo()!= null && !usuarios[i].getCentroEducativo().equals("")){
	    		    	   usuarioCon.setCentroEducativo(usuarios[i].getCentroEducativo());
	    		       }else{
	    		    	   usuarioCon.setCentroEducativo(null);
	    		       }
	    		       if(usuarios[i].getDescripcion()!=null && !usuarios[i].getDescripcion().equals("")){
	    		    	   usuarioCon.setDescripcion(usuarios[i].getDescripcion());
	    		       }else{
	    		    	   usuarioCon.setDescripcion(null);
	    		       }
	    			usuarioCon.setCentroEducativo(usuarios[i].getCentroEducativo());
	    			usuarioCon.setDescripcion(usuarios[i].getDescripcion());
	    			usuarioCon.setId(usuarios[i].getId());
	    			usuarioCon.setUsuario(usuarios[i].getUsuario());
	    			
	    			usuarioCon.setUrlUsuario(PerfilPublico.urlUsuarioPublico(usuarios[i].getUsuario()));
	    			collections.add(usuarioCon);
	    		}
	    		usuariosURL=collections.toArray(new UsuarioPublicoConURLs[0]);
	    		
	    	}catch(Exception e){
	    		logger.error("Error al insertar la url del usuario");
	    	}
	    	return usuariosURL;
	    }
	 
	 public class UsuarioPublicoConURLs {
		    /* Nombre de la foto del usuario */
		    private java.lang.String foto;

		    private java.lang.String usuario;

		    private java.lang.Long id;

		    /* Descripcion del usuario publico */
		    private java.lang.String descripcion;

		    private java.lang.String centroEducativo;
		    
		    public java.lang.String getFoto() {
				return foto;
			}

			public void setFoto(java.lang.String foto) {
				this.foto = foto;
			}


			public java.lang.String getCentroEducativo() {
				return centroEducativo;
			}

			public void setCentroEducativo(java.lang.String centroEducativo) {
				this.centroEducativo = centroEducativo;
			}

			public java.lang.String getUsuario() {
				return usuario;
			}

			public void setUsuario(java.lang.String usuario) {
				this.usuario = usuario;
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

			public String getUrlUsuario() {
				return urlUsuario;
			}

			public void setUrlUsuario(String urlUsuario) {
				this.urlUsuario = urlUsuario;
			}

			private String urlUsuario;

		    public UsuarioPublicoConURLs() {
		    }
	 }


	public String estaActivoGrupo(ActionMapping mapping,
			EstaActivoGrupoForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String vuelta="Activo";
		String nombre = form.getNombre();
		String actionOde=form.getActionOde();
		String actionUsuario=form.getActionUsuario();

		 GrupoPublicoVO grupoPublico=this.getSrvPerfilPublico().obtenerGrupoPublico(nombre);
		if(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PERFIL_PUBLICO).equals("true")){
			if(grupoPublico !=null && grupoPublico.getNombre()!=null){
				 vuelta="Activo";
			 }else{
	    	   //TODO Saltar exception NotFoundException
	    	   logger.error("Ese usuario no tiene usuario publico");
	    	   vuelta="GrupoDesactivado";
	       }
		}else{
			logger.error("Esta comunidad no tiene perfil publico activado");
			vuelta="PerfilDesactivado";
	 	   
		}
		return vuelta;
	}


   
    
    





}