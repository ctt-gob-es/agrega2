// license-header java merge-point
package es.pode.visualizador.presentacion.gestionGruposPublicos;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.adminusuarios.negocio.servicios.GrupoPublicoAdminVO;
import es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO;
import es.pode.adminusuarios.negocio.servicios.SolicitudGrupoVO;
import es.pode.adminusuarios.negocio.servicios.UsuarioPublicoVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.url.ImagenesAgrega;



/**
 * @see es.pode.visualizador.presentacion.gestionGruposPublicos.GestionGruposPublicosController
 */
public class GestionGruposPublicosControllerImpl extends GestionGruposPublicosController
{



	private static Logger logger = Logger.getLogger(GestionGruposPublicosControllerImpl.class);
	private java.util.Properties pSpringProperties = null;

    /**
     * @see es.pode.visualizador.presentacion.gestionGruposPublicos.GestionGruposPublicosController#obtenerGruposDeUsuario(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionGruposPublicos.ObtenerGruposDeUsuarioForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerGruposDeUsuario(ActionMapping mapping, es.pode.visualizador.presentacion.gestionGruposPublicos.ObtenerGruposDeUsuarioForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String usuario = LdapUserDetailsUtils.getUsuario();
    	
//    	String usuarioRequest = (String)request.getSession().getAttribute("usuario").toString();
    	logger.info("El usuario que obtenemos es "+usuario);
    	String actionGrupos=form.getActionGrupos();
    	String actionSolicitudes=form.getActionSolicitudes();
    	String actionSolicitantes=form.getActionSolicitantes();
    	form.setUsuario(usuario);
    	logger.info("el action que recibimos es "+actionGrupos+" o "+actionSolicitudes+" o "+actionSolicitantes);
    	Collection verMas=new ArrayList();
    	 GrupoPublicoAdminVO[] gruposLimpios=null;

	       if(actionGrupos!=null && !actionGrupos.equals("")){
	    	   gruposLimpios=this.getSrvPerfilPublico().obtenerGrupoUsuario(LdapUserDetailsUtils.getUsuario());
	    	   verMas.add(Boolean.FALSE); 
	       }else{
	    	   GrupoPublicoAdminVO[] gruposSelec = this.getSrvPerfilPublico().obtenerGrupoUsuario(LdapUserDetailsUtils.getUsuario());
//		       GrupoPublicoVO[] gruposSelec = usuarioPublico.getGrupoPublico();
		       if(gruposSelec!=null && gruposSelec.length>0){
			       if(gruposSelec.length>new Long(getPropertyValue("cantidad_ver_publicos"))){
			    	   logger.info("Mas grupos que los que pintamos");
			    	   verMas.add(Boolean.TRUE);
			    	   gruposLimpios=new GrupoPublicoAdminVO[new Integer(getPropertyValue("cantidad_ver_publicos"))];
			    	   for(int i=0;i<gruposLimpios.length;i++){
			    		   gruposLimpios[i]=gruposSelec[i];
			    	   }
			    	   
			       }else{
			    	   verMas.add(Boolean.FALSE);
			    	   gruposLimpios=new GrupoPublicoAdminVO[gruposSelec.length];
			    	   gruposLimpios=gruposSelec;
			       }
		       }else{
		    	   verMas.add(Boolean.FALSE);
		    	   gruposLimpios=new GrupoPublicoAdminVO[0];
		       }
	       }
	       if(gruposLimpios!=null){
		       for(int i=0;i<gruposLimpios.length;i++){
		    	   String imagenGrupo=gruposLimpios[i].getImagenGrupo();

			       if(imagenGrupo.equals("ImagenDefectoGrupo1"))
			    	   gruposLimpios[i].setImagenGrupo(ImagenesAgrega.urlImagenDefectoGrupo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_1)));
		    	   else if(imagenGrupo.equals("ImagenDefectoGrupo2"))
		    		   gruposLimpios[i].setImagenGrupo(ImagenesAgrega.urlImagenDefectoGrupo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_2)));
		    	   else if(imagenGrupo.equals("ImagenDefectoGrupo3"))
		    		   gruposLimpios[i].setImagenGrupo(ImagenesAgrega.urlImagenDefectoGrupo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_3)));
		    	   else if(imagenGrupo.equals("ImagenDefectoGrupo4"))
		    		   gruposLimpios[i].setImagenGrupo(ImagenesAgrega.urlImagenDefectoGrupo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_4)));
		    	   else
		    		   gruposLimpios[i].setImagenGrupo(ImagenesAgrega.urlImagenDefectoGrupo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_5)));
		       }
	       }
	       form.setGruposPublicosAsArray(gruposLimpios);
	       SolicitudGrupoVO[] gruposSolicitadosLimpios=null;
	       if(actionSolicitudes!=null && !actionSolicitudes.equals("")){
	    	   gruposSolicitadosLimpios = this.getSrvPerfilPublico().listarSolicitudesRealizadasPorUsuario(usuario);
	    	   verMas.add(Boolean.FALSE); 
	       }else{
	    	   SolicitudGrupoVO[] gruposSolicitados=this.getSrvPerfilPublico().listarSolicitudesRealizadasPorUsuario(usuario);
	    	   if(gruposSolicitados!=null && gruposSolicitados.length>0){
			       if(gruposSolicitados.length>new Long(getPropertyValue("cantidad_ver_publicos"))){
			    	   logger.info("Mas grupos solicitados que los que pintamos");
			    	   verMas.add(Boolean.TRUE);
			    	   gruposSolicitadosLimpios=new SolicitudGrupoVO[new Integer(getPropertyValue("cantidad_ver_publicos"))];
			    	   for(int i=0;i<gruposSolicitadosLimpios.length;i++){
			    		   gruposSolicitadosLimpios[i]=gruposSolicitados[i];
			    	   }
			    	   
			       }else{
			    	   verMas.add(Boolean.FALSE);
			    	   gruposSolicitadosLimpios=new SolicitudGrupoVO[gruposSolicitados.length];
			    	   gruposSolicitadosLimpios=gruposSolicitados;
			       }
		       }else{
		    	   verMas.add(Boolean.FALSE);
		    	   gruposSolicitadosLimpios=new SolicitudGrupoVO[0];
		       }
	       }
	       
	       SolicitudGrupoVO[] solicitudesLimpios=null;
	       if(actionSolicitantes!=null && !actionSolicitantes.equals("")){
	    	   solicitudesLimpios = this.getSrvPerfilPublico().listarSolicitudesAdministrador(usuario);
	    	   verMas.add(Boolean.FALSE); 
	       }else{
	    	   SolicitudGrupoVO[] solicitantes=this.getSrvPerfilPublico().listarSolicitudesAdministrador(usuario);
	    	   if(solicitantes!=null && solicitantes.length>0){
			       if(solicitantes.length>new Long(getPropertyValue("cantidad_ver_publicos"))){
			    	   logger.info("Mas solicitudes que los que pintamos");
			    	   verMas.add(Boolean.TRUE);
			    	   solicitudesLimpios=new SolicitudGrupoVO[new Integer(getPropertyValue("cantidad_ver_publicos"))];
			    	   for(int i=0;i<solicitudesLimpios.length;i++){
			    		   solicitudesLimpios[i]=solicitantes[i];
			    	   }
			    	   
			       }else{
			    	   verMas.add(Boolean.FALSE);
			    	   solicitudesLimpios=new SolicitudGrupoVO[solicitantes.length];
			    	   solicitudesLimpios=solicitantes;
			       }
		       }else{
		    	   verMas.add(Boolean.FALSE);
		    	   solicitudesLimpios=new SolicitudGrupoVO[0];
		       }
	       }
	       
	       SolicitudesConImagen[] gruposSolicitudesLimpiosConImagen = insertarUrlImagen(gruposSolicitadosLimpios);
	       form.setSolicitudesAsArray(gruposSolicitudesLimpiosConImagen);
	       SolicitudesConImagenUsuario[] gruposSolicitudesLimpiosConImagenUsuario =insertarUrlImagenUsuario(solicitudesLimpios);
	       form.setSolicitantesAsArray(gruposSolicitudesLimpiosConImagenUsuario);
	       form.setVerMas(verMas);
	       form.setActionGrupos(null);
	       form.setActionSolicitantes(null);
	       form.setActionSolicitudes(null);
	       form.setTextoBusqueda(null);
     }




    private String getPropertyValue(String sKey) throws IOException {
    	try{
    		InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/spring_visualizadorcontenidos2.properties");
			if (this.pSpringProperties == null) {
				pSpringProperties = new java.util.Properties();
				pSpringProperties.load(fIsSpringProperties);
			}
			fIsSpringProperties.close();
			logger.debug("GestionGruposPublicos - getPropertyValue: Propiedad recuperada: " + sKey + " : "+ pSpringProperties.getProperty(sKey));
//				 devolvemos la propiedad
            
        }catch (Exception e){
 		logger.error("GestionGruposPublicos - Error recuperando propiedad de spring_visualizadorcontenidos2.properties=",e);
        }
		
		return pSpringProperties.getProperty(sKey);
	}


	 public class SolicitudesConImagen {
	        
		 	private java.lang.String usuarioSolicitante;
		 	
		 	public java.lang.String getUsuarioSolicitante() {
				return usuarioSolicitante;
			}


			public void setUsuarioSolicitante(java.lang.String usuarioSolicitante) {
				this.usuarioSolicitante = usuarioSolicitante;
			}


			public java.lang.String getGrupo() {
				return grupo;
			}


			public void setGrupo(java.lang.String grupo) {
				this.grupo = grupo;
			}


			public java.lang.String getUsuarioAdministrador() {
				return usuarioAdministrador;
			}


			public void setUsuarioAdministrador(java.lang.String usuarioAdministrador) {
				this.usuarioAdministrador = usuarioAdministrador;
			}


			public Date getFechaSolicitud() {
				return fechaSolicitud;
			}


			public void setFechaSolicitud(Date fechaSolicitud) {
				this.fechaSolicitud = fechaSolicitud;
			}


			public java.lang.String getUrlImagen() {
				return urlImagen;
			}


			public void setUrlImagen(java.lang.String urlImagen) {
				this.urlImagen = urlImagen;
			}


			public Long getId() {
				return id;
			}


			public void setId(Long id) {
				this.id = id;
			}


			private java.lang.String grupo;

		 	private java.lang.String usuarioAdministrador;
		 	
		 	private Date fechaSolicitud;
		 	
	        private java.lang.String urlImagen;
	        
	        private Long id;
	        
	        private String descripcionGrupo;
	
	
	        public String getDescripcionGrupo() {
				return descripcionGrupo;
			}


			public void setDescripcionGrupo(String descripcionGrupo) {
				this.descripcionGrupo = descripcionGrupo;
			}


			public SolicitudesConImagen() {
	        }
	 }
	 
	 private SolicitudesConImagen[] insertarUrlImagen(SolicitudGrupoVO[] solicitudes){
		 SolicitudesConImagen[] solictudesURL=null;
	    	try{
	    		Collection<SolicitudesConImagen> collections=new ArrayList();
	    		for(int i=0;i<solicitudes.length;i++){
	    			SolicitudesConImagen solicitudCon=new SolicitudesConImagen();
	    			solicitudCon.setFechaSolicitud(solicitudes[i].getFechaSolicitud().getTime());
	    			solicitudCon.setGrupo(solicitudes[i].getGrupo());
	    			solicitudCon.setId(solicitudes[i].getId());
	    			solicitudCon.setUsuarioAdministrador(solicitudes[i].getUsuarioAdministrador());
	    			solicitudCon.setUsuarioSolicitante(solicitudes[i].getUsuarioSolicitante());
	    			GrupoPublicoVO grupo = this.getSrvPerfilPublico().obtenerGrupoPublico(solicitudes[i].getGrupo());
	    			String imagen=grupo.getImagenGrupo();
	    			String imagenCon="";
	    		       if(imagen.equals("ImagenDefectoGrupo1"))
	    		    	   imagenCon=ImagenesAgrega.urlImagenDefectoGrupo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_1));
	    	    	   else if(imagen.equals("ImagenDefectoGrupo2"))
	    	    		   imagenCon=ImagenesAgrega.urlImagenDefectoGrupo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_2));
	    	    	   else if(imagen.equals("ImagenDefectoGrupo3"))
	    	    		   imagenCon=ImagenesAgrega.urlImagenDefectoGrupo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_3));
	    	    	   else if(imagen.equals("ImagenDefectoGrupo4"))
	    	    		   imagenCon=ImagenesAgrega.urlImagenDefectoGrupo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_4));
	    	    	   else
	    	    		   imagenCon=ImagenesAgrega.urlImagenDefectoGrupo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_5));
	    			solicitudCon.setUrlImagen(imagenCon);
	    			solicitudCon.setDescripcionGrupo(grupo.getDescripcion());
	    			collections.add(solicitudCon);
	    		}
	    		solictudesURL=collections.toArray(new SolicitudesConImagen[0]);
	    		
	    	}catch(Exception e){
	    		logger.error("Error al insertar la url del ode");
	    	}
	    	return solictudesURL;
	    }
	 
	 public class SolicitudesConImagenUsuario {
	        
		 	private java.lang.String usuarioSolicitante;
		 	
		 	public java.lang.String getUsuarioSolicitante() {
				return usuarioSolicitante;
			}


			public void setUsuarioSolicitante(java.lang.String usuarioSolicitante) {
				this.usuarioSolicitante = usuarioSolicitante;
			}


			public java.lang.String getGrupo() {
				return grupo;
			}


			public void setGrupo(java.lang.String grupo) {
				this.grupo = grupo;
			}


			public java.lang.String getUsuarioAdministrador() {
				return usuarioAdministrador;
			}


			public void setUsuarioAdministrador(java.lang.String usuarioAdministrador) {
				this.usuarioAdministrador = usuarioAdministrador;
			}


			public Date getFechaSolicitud() {
				return fechaSolicitud;
			}


			public void setFechaSolicitud(Date fechaSolicitud) {
				this.fechaSolicitud = fechaSolicitud;
			}


			public java.lang.String getUrlImagenUsuario() {
				return urlImagenUsuario;
			}


			public void setUrlImagenUsuario(java.lang.String urlImagenUsuario) {
				this.urlImagenUsuario = urlImagenUsuario;
			}


			public Long getId() {
				return id;
			}


			public void setId(Long id) {
				this.id = id;
			}


			private java.lang.String grupo;

		 	private java.lang.String usuarioAdministrador;
		 	
		 	private Date fechaSolicitud;
		 	
	        private java.lang.String urlImagenUsuario;
	        
	        private Long id;
	        
	        private String descripcionUsuario;
	
	
	        public String getDescripcionUsuario() {
				return descripcionUsuario;
			}


			public void setDescripcionUsuario(String descripcionUsuario) {
				this.descripcionUsuario = descripcionUsuario;
			}


			public SolicitudesConImagenUsuario() {
	        }
	 }
	 
	 private SolicitudesConImagenUsuario[] insertarUrlImagenUsuario(SolicitudGrupoVO[] solicitudes){
		 SolicitudesConImagenUsuario[] solictudesURL=null;
	    	try{
	    		Collection<SolicitudesConImagenUsuario> collections=new ArrayList();
	    		for(int i=0;i<solicitudes.length;i++){
	    			SolicitudesConImagenUsuario solicitudCon=new SolicitudesConImagenUsuario();
	    			solicitudCon.setFechaSolicitud(solicitudes[i].getFechaSolicitud().getTime());
	    			solicitudCon.setGrupo(solicitudes[i].getGrupo());
	    			solicitudCon.setId(solicitudes[i].getId());
	    			solicitudCon.setUsuarioAdministrador(solicitudes[i].getUsuarioAdministrador());
	    			solicitudCon.setUsuarioSolicitante(solicitudes[i].getUsuarioSolicitante());
	    			UsuarioPublicoVO usuarioPublico=this.getSrvPerfilPublico().obtenerUsuarioPublico(solicitudes[i].getUsuarioSolicitante());
	    			String fotoCon="";
	    			 if(usuarioPublico.getFoto().equals(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_USUARIO)){
	    				 fotoCon=ImagenesAgrega.urlImagenDefectoUsuario(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_USUARIO));
	  		       	}else{
	  		       		fotoCon=AgregaPropertiesImpl.getInstance().getUrlNodo()+AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.IMAGENES_USUARIO_PUBLICO_URL)+"/"+usuarioPublico.getFoto();
	  		       	}
	    			solicitudCon.setUrlImagenUsuario(fotoCon);
	    			solicitudCon.setDescripcionUsuario(usuarioPublico.getDescripcion());
	    			collections.add(solicitudCon);
	    		}
	    		solictudesURL=collections.toArray(new SolicitudesConImagenUsuario[0]);
	    		
	    	}catch(Exception e){
	    		logger.error("Error al insertar la url del ode");
	    	}
	    	return solictudesURL;
	    }




	public void getGruposBusqueda(ActionMapping mapping,
			GetGruposBusquedaForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String texto = ((MostrarListaGruposBuscarGruposFormImpl) form).getTextoBusqueda();
	    String textoBusqueda = texto.trim();
    	logger.info("La longitud es: " + texto.length());
    	if (textoBusqueda.length()==0 && textoBusqueda!=null )
    	{
    		logger.error("Hay que introducir algún criterio de búsqueda");
    		throw new ValidatorException("{grupos.error.grupo.vacio}");
    	}else{
    		form.setTextoBusqueda(textoBusqueda);
    	}
	}




	@Override
	public String tienePerfilPublico(ActionMapping mapping,
			TienePerfilPublicoForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String vuelta="FALSE";
		Boolean perfilPublico = LdapUserDetailsUtils.tienePerfilPublico();
		if(perfilPublico.equals(Boolean.TRUE))
			vuelta="TRUE";
		return vuelta;
	}


}