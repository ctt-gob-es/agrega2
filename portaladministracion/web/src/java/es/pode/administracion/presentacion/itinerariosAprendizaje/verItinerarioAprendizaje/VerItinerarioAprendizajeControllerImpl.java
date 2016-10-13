/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.administracion.presentacion.itinerariosAprendizaje.verItinerarioAprendizaje;

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
import es.pode.configuracionPlataforma.servicios.SrvPropiedadService;
import es.pode.soporte.url.ImagenODE;
import es.pode.soporte.url.ImagenesAgrega;
import es.pode.soporte.url.ODEPublico;


/**
 * @see es.pode.administracion.presentacion.itinerariosAprendizaje.verItinerarioAprendizaje.VerItinerarioAprendizajeController
 */
public class VerItinerarioAprendizajeControllerImpl extends VerItinerarioAprendizajeController
{


	private static Logger logger = Logger.getLogger(VerItinerarioAprendizajeControllerImpl.class);
	private java.util.Properties pSpringProperties = null;
	private SrvPropiedadService prop = null;


    /**
     * @see es.pode.administracion.presentacion.itinerariosAprendizaje.verItinerarioAprendizaje.VerItinerarioAprendizajeController#obtenerDatosItinerario(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.itinerariosAprendizaje.verItinerarioAprendizaje.ObtenerDatosItinerarioForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerDatosItinerario(ActionMapping mapping, es.pode.administracion.presentacion.itinerariosAprendizaje.verItinerarioAprendizaje.ObtenerDatosItinerarioForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // Este método es igual que el de la pagina publica, solo que tiene un atributo mas que es el volver='Itinerario'; Y la jsp tendra un boton volver. Y un link a eliminarItinerario igual que el el caso de tus itinerarios(Ver jsp de GestionGrupoPublico, tiene este link si eres el administrador, en este caso siempre lo llevara).
    	//Para saber si lo que tenemos que hacer es recargar la página o volver se utiliza el metodo recuperarDAtos.
    	//Tener en cuenta que el itinerario tiene usaurios--> sus url-s iran a VerUsuarioPublico que tienes que implemetar en este mismo modulo.
    	//Por ese motivo podemos ir de los itinerarios a usaurios publicos y viceversa; y para saber si hemos entrado desde usuario publico o itinerario utilizamos el parametro volver, que le daremos valor al principio y no lo modificamos.
    	String nombre = form.getNombre();
    	String id = form.getId();
    	
//    	String usuarioRequest = (String)request.getSession().getAttribute("usuario").toString();
    	logger.info("El grupo que obtenemos es "+nombre);
    	String actionOde=form.getActionOde();
    	String actionUsuario=form.getActionUsuario();
    	logger.info("el action que recibimos es "+actionOde+" o "+actionUsuario);
    	Collection<Boolean> verMas=new ArrayList<Boolean>();
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
	       form.setId(id);
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
	       if(form.getVolver() !=null && !form.getVolver().equals("")){
	    	   logger.debug("Hemos entrado de "+form.getVolver());
	       }else{
	    	   form.setVolver("Itinerario");
	       }
    	
     }







    /**
     * @see es.pode.administracion.presentacion.itinerariosAprendizaje.verItinerarioAprendizaje.VerItinerarioAprendizajeController#recuperarDatos(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.itinerariosAprendizaje.verItinerarioAprendizaje.RecuperarDatosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String recuperarDatos(ActionMapping mapping, es.pode.administracion.presentacion.itinerariosAprendizaje.verItinerarioAprendizaje.RecuperarDatosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	// Este metodo es para el punto de decision. Si alguno de los actionOde o actionGRupo no es nulo la salida es Action.
    	// En caso contrario a pulsado el boton volver y tenemos que saber a donde tenemos que volver, si el volver='Itinerario' hemos entrado desde la lista de itinerarios de aprendizaje  ( modificar el link y que se abra en la misma pagina,
    	// ahora va a la pagina publica del grupo), y tenemos que volver a la lista de itinerarios.
    	String accion="Usuario";
    	String volver=form.getVolver();
    	String accionOde=form.getActionOde();
    	String accionUsuario = form.getActionUsuario();


//    	java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);

//        ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
        if((accionOde!=null && !accionOde.equals("")) || (accionUsuario!=null && !accionUsuario.equals("") )){
        	accion="Accion"; 	
        }
        else if((volver!=null && !volver.equals("") && volver.equals("Usuario"))){
     		accion="Usuario";
     	}
        else if (volver!=null && !volver.equals("") && volver.equals("Itinerario"))
    	{
        	accion="Itinerario";
    	}
    	 
         return accion;
    }

    private String getPropertyValue(String sKey) {
    	try{
    		InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/spring_portaladministracion.properties");
			if (this.pSpringProperties == null) {
				pSpringProperties = new java.util.Properties();
				pSpringProperties.load(fIsSpringProperties);
			}
			fIsSpringProperties.close();
			logger.debug("VerItinerarioAprendizaje - getPropertyValue: Propiedad recuperada: " + sKey + " : "+ pSpringProperties.getProperty(sKey));
//				 devolvemos la propiedad
            
        }catch (Exception e){
 		logger.error("VerItinerarioAprendizaje - Error recuperando propiedad de spring_portaladministracion.properties=",e);
        }
		
		return pSpringProperties.getProperty(sKey);
	}

    private OdeConURLs[] insertarUrl(OdeGrupoVO[] odes){
    	OdeConURLs[] odesURL=null;
    	try{
    		Collection<OdeConURLs> collections=new ArrayList<OdeConURLs>();
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
		 String ruta="/VerUsuarioPublico/VerUsuarioPublico.do?usuario=";
	    	try{
	    		Collection<UsuarioPublicoConURLs> collections=new ArrayList<UsuarioPublicoConURLs>();
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
	    			
	    			usuarioCon.setUrlUsuario(ruta+usuarios[i].getUsuario());
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

	 private SrvPropiedadService ObtieneSrvPropiedad() throws Exception {
	 	if (this.prop==null)
	 	{
	 		prop = this.getSrvPropiedadService();
	 	}
	 	return prop;
	 }

}