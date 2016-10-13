// license-header java merge-point
package es.pode.administracion.presentacion.adminusuarios.verUsuarioPublico;

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
import es.pode.configuracionPlataforma.negocio.servicios.SrvPropiedadService;
import es.pode.publicacion.negocio.servicios.OdePublicadoVO;
import es.pode.publicacion.negocio.servicios.TransicionVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.url.ImagenODE;
import es.pode.soporte.url.ImagenesAgrega;
import es.pode.soporte.url.ODEPublico;



/**
 * @see es.pode.administracion.presentacion.adminusuarios.verUsuarioPublico.VerUsuarioPublicoController
 */
public class VerUsuarioPublicoControllerImpl extends VerUsuarioPublicoController
{

//    private SrvPropiedadService prop = null;
	private static Logger logger = Logger.getLogger(VerUsuarioPublicoControllerImpl.class);
	private java.util.Properties pSpringProperties = null;
	private SrvPropiedadService prop = null;


    /**
     * @see es.pode.administracion.presentacion.adminusuarios.verUsuarioPublico.VerUsuarioPublicoController#obtenerDatosUsuarioPublico(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.adminusuarios.verUsuarioPublico.ObtenerDatosUsuarioPublicoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerDatosUsuarioPublico(ActionMapping mapping, es.pode.administracion.presentacion.adminusuarios.verUsuarioPublico.ObtenerDatosUsuarioPublicoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // Este metodo es igual que el metodo obtenerDatos de la página pública, exceptuando que en esa pagina verica si el usaurio quiere enseñar los datos (Objetos, Favoritos, y Grupos) en este caso siempre se enseñara todo(no hacer esas validaciones), sólo que lleva un nuevo atributo que sera  volver='Usuario'; y la jsp también es identica. La única diferencia es que  esta jsp tiene un boton volver;
    	//Para saber si lo que tenemos que hacer es recargar la página o volver se utiliza el metodo recuperarDAtos
    	//Tener en cuenta que el usuario publico tiene itienrarios--> sus url-s iran a VerItinerarioAprendizaje que tienes que implemetar en este mismo modulo.
    	//Por ese motivo podemos ir de los itinerarios a usaurios publicos y viceversa; y para saber si hemos entrado desde usuario publico o itinerario utilizamos el parametro volver, que le daremos valor al principio y no lo modificamos.
    	String usuario = form.getUsuario();
    	
    	//    	String usuarioRequest = (String)request.getSession().getAttribute("usuario").toString();
    	    	logger.info("El usuario que obtenemos es "+usuario);
    	    	String actionFavorito=form.getActionFavorito();
    	    	String actionManual=form.getActionManual();
    	    	String actionAuto=form.getActionAuto();
    	    	String actionGrupos=form.getActionGrupos();
    	    	

    	    	logger.info("el action que recibimos es "+actionFavorito+" o "+actionManual+"o "+actionAuto+"o "+actionGrupos);
    	    	Collection<Boolean> verMas=new ArrayList<Boolean>();
    	       UsuarioPublicoVO usuarioPublico=this.getSrvPerfilPublico().obtenerUsuarioPublico(usuario);
    	    
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

    			       if(actionFavorito!=null && !actionFavorito.equals("")){
    			    	   
    			    	   favoritosLimpios =usuarioPublico.getFavoritoUsuario();
    			    	   verMas.add(false); 
    			       }else{
    			    	   FavoritoVO[] favoritosSelec = usuarioPublico.getFavoritoUsuario();
    			    	   if(favoritosSelec!=null && favoritosSelec.length>0){
    					       if(favoritosSelec.length >Long.parseLong(getPropertyValue("cantidad_ver_publicos"))){
    					    	   logger.info("Mas favoritos que los que pintamos ");
    					    	   verMas.add(Boolean.TRUE); 
    					    	   favoritosLimpios=new FavoritoVO[Integer.parseInt(getPropertyValue("cantidad_ver_publicos"))];
    					    	   for(int i=0;i<favoritosLimpios.length;i++){
    					    		   favoritosLimpios[i]=favoritosSelec[i];
    					    	   }
    					       }else{
    					    	   verMas.add(false); 
    					    	   favoritosLimpios=new FavoritoVO[favoritosSelec.length];
    					    	   favoritosLimpios=favoritosSelec;
    					       }
    			    	   }else{
    			    		   verMas.add(false); 
    			    		   favoritosLimpios=new FavoritoVO[0];
    			    	   }
    			       }

    		       GrupoPublicoVO[] gruposLimpios=null;


    			       if(actionGrupos!=null && !actionGrupos.equals("")){
    			    	   gruposLimpios=usuarioPublico.getGrupoPublico();
    			    	   verMas.add(false); 
    			       }else{
    				       GrupoPublicoVO[] gruposSelec = usuarioPublico.getGrupoPublico();
    				       if(gruposSelec!=null && gruposSelec.length>0){
    					       if(gruposSelec.length>Long.parseLong(getPropertyValue("cantidad_ver_publicos"))){
    					    	   logger.info("Mas grupos que los que pintamos");
    					    	   verMas.add(Boolean.TRUE);
    					    	   gruposLimpios=new GrupoPublicoVO[Integer.parseInt(getPropertyValue("cantidad_ver_publicos"))];
    					    	   for(int i=0;i<gruposLimpios.length;i++){
    					    		   gruposLimpios[i]=gruposSelec[i];
    					    	   }
    					    	   
    					       }else{
    					    	   verMas.add(false);
    					    	   gruposLimpios=new GrupoPublicoVO[gruposSelec.length];
    					    	   gruposLimpios=gruposSelec;
    					       }
    				       }else{
    				    	   verMas.add(false);
    				    	   gruposLimpios=new GrupoPublicoVO[0];
    				       }
    			       }
    		      
    		       
    		       OdePublicadoVO[] publicadosLimpios=null;
    		       TransicionVO[] autonomosLimpios=null;

    			       if(actionManual!=null && !actionManual.equals("")){
    			    	   publicadosLimpios= this.getSrvPublicacionService().obtenODEsPublicadoUsuario(usuario);
    			    	   verMas.add(false); 
    			       }else{
    			    	   OdePublicadoVO[] odesPublicados = this.getSrvPublicacionService().obtenODEsPublicadoUsuario(usuario);
    				       if(odesPublicados!=null && odesPublicados.length>0){
    					       if(odesPublicados.length>Long.parseLong(getPropertyValue("cantidad_ver_publicos"))){
    					    	   logger.info("Mas publicados que los que pintamos");
    					    	   verMas.add(true);
    					    	   publicadosLimpios=new OdePublicadoVO[Integer.parseInt(getPropertyValue("cantidad_ver_publicos"))];
    					    	   for(int i=0;i<publicadosLimpios.length;i++){
    					    		   publicadosLimpios[i]=odesPublicados[i];
    					    	   }
    					    	   
    					       }else{
    					    	   verMas.add(false);
    					    	   publicadosLimpios=new OdePublicadoVO[odesPublicados.length];
    					    	   publicadosLimpios=odesPublicados;
    					       }
    				       }else{
    				    	   verMas.add(false);
    				    	   publicadosLimpios=new OdePublicadoVO[0];
    				       }
    			       }
    		       
    		       
    		      
    			       if(actionAuto!=null && !actionAuto.equals("")){
    			    	   autonomosLimpios= this.getSrvPublicacionService().obtenODEsPublicadosAutonomoPorUsuario(usuario);
    			    	   verMas.add(false); 
    			       }else{
    				       TransicionVO[] autonomosPublicados = this.getSrvPublicacionService().obtenODEsPublicadosAutonomoPorUsuario(usuario);
    				       if(autonomosPublicados!=null && autonomosPublicados.length>0){
    					       if(autonomosPublicados.length>Long.parseLong(getPropertyValue("cantidad_ver_publicos"))){
    					    	   logger.info("Mas publicados que los que pintamos");
    					    	   verMas.add(Boolean.TRUE);
    					    	   autonomosLimpios=new TransicionVO[Integer.parseInt(getPropertyValue("cantidad_ver_publicos"))];
    					    	   for(int i=0;i<autonomosLimpios.length;i++){
    					    		   autonomosLimpios[i]=autonomosPublicados[i];
    					    	   }
    					    	   
    					       }else{
    					    	   verMas.add(false);
    					    	   autonomosLimpios=new TransicionVO[autonomosPublicados.length];
    					    	   autonomosLimpios=autonomosPublicados;
    					       }
    				       }else{
    				    	   verMas.add(false);
    				    	   autonomosLimpios=new TransicionVO[0];
    				       }
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
    		       if(form.getVolver() !=null && !form.getVolver().equals("")){
    		    	   logger.debug("Hemos entrado de "+form.getVolver());
    		       }else{
    		    	   form.setVolver("Usuario");
    		       }
     }

    /**
     * @see es.pode.administracion.presentacion.adminusuarios.verUsuarioPublico.VerUsuarioPublicoController#recuperarDatos(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.adminusuarios.verUsuarioPublico.RecuperarDatosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String recuperarDatos(ActionMapping mapping, es.pode.administracion.presentacion.adminusuarios.verUsuarioPublico.RecuperarDatosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // Este metodo es para el punto de decision. Si alguno de los action no es nulo la salida es Action.
    	// En caso contrario a pulsado el boton volver y tenemos que saber a donde tenemos que volver, si el volver='Usuario' hemos entrado desde la lista de usuarios pulsando "VEr Usuario Publico" ( modificar el link y que se abra en la misma pagina,
    	// ahora va a la pagina publica), y tenemos que volver a la lista de usuariosPublicos.
    	
    	String accion="Usuario";
    	String volver=form.getVolver();
    	String accionAuto=form.getActionAuto();
    	String accionFavorito = form.getActionFavorito();
    	String accionGrupos = form.getActionGrupos();
    	String accionManual = form.getActionManual();


       
        if((accionAuto!=null && !accionAuto.equals("")) || (accionFavorito!=null && !accionFavorito.equals("") )|| (accionGrupos!=null && !accionGrupos.equals("")) || (accionManual!=null && !accionManual.equals(""))){
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





    private String getPropertyValue(String sKey) throws IOException {
    	try{
    		InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/spring_portaladministracion.properties");
			if (this.pSpringProperties == null) {
				pSpringProperties = new java.util.Properties();
				pSpringProperties.load(fIsSpringProperties);
			}
			fIsSpringProperties.close();
			logger.debug("VerUsuarioPublico - getPropertyValue: Propiedad recuperada: " + sKey + " : "+ pSpringProperties.getProperty(sKey));
//				 devolvemos la propiedad
            
        }catch (Exception e){
 		logger.error("VerUsuarioPublico - Error recuperando propiedad de spring_portaladministracion.properties=",e);
        }
		
		return pSpringProperties.getProperty(sKey);
	}

    private FavoritoConURLs[] insertarUrl(FavoritoVO[] favoritos){
    	FavoritoConURLs[] favoritosURL=null;
    	try{
    		Collection<FavoritoConURLs> collections=new ArrayList<FavoritoConURLs>();
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

	 public static class FavoritoConURLs {
	        
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
	    		Collection<OdePublicadoVOConUrls> collections=new ArrayList<OdePublicadoVOConUrls>();
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
	 
	 public static class OdePublicadoVOConUrls {
	        
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
	    		Collection<AutonomoConURLs> collections=new ArrayList<AutonomoConURLs>();
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
	 
	 public static class AutonomoConURLs {
	        
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
		 String ruta="/VerItinerarioAprendizaje/VerItinerarioAprendizaje.do?nombre=";
	    	try{
	    		Collection<GrupoPublicoConURLs> collections=new ArrayList<GrupoPublicoConURLs>();
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
		    		grupoCon.setUrlGrupo(ruta+grupos[i].getNombre());
		    		grupoCon.setAdministrador(grupos[i].getAdministrador());
		    		if(grupos[i].getDescripcion()!=null && !grupos[i].getDescripcion().equals("")){
		    			if(grupos[i].getDescripcion().length()<180)
		    				grupoCon.setDescripcion(grupos[i].getDescripcion());
		    			else{
		    				logger.debug("Vamos a reducir la descripcion");
		    				String subDescripcion=grupos[i].getDescripcion().substring(0, 175)+"...";
		    				grupoCon.setDescripcion(subDescripcion);
		    			}
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
	 public static class GrupoPublicoConURLs {
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

	 private SrvPropiedadService ObtieneSrvPropiedad() throws Exception {
	 	if (this.prop==null)
	 	{
	 		prop = this.getSrvPropiedadService();
	 	}
	 	return prop;
	 }

}