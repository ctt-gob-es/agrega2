/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.visualizador.presentacion.gestionGrupoPublico;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.adminusuarios.negocio.servicios.ContactoVO;
import es.pode.adminusuarios.negocio.servicios.GrupoPublicoAdminVO;
import es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO;
import es.pode.adminusuarios.negocio.servicios.OdeGrupoVO;
import es.pode.adminusuarios.negocio.servicios.SolicitudGrupoVO;
import es.pode.adminusuarios.negocio.servicios.UsuarioPublicoVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.url.ImagenODE;
import es.pode.soporte.url.ImagenesAgrega;
import es.pode.soporte.url.ODEPublico;
import es.pode.soporte.url.PerfilPublico;



/**
 * @see es.pode.visualizador.presentacion.gestionGrupoPublico.GestionGrupoPublicoController
 */
public class GestionGrupoPublicoControllerImpl extends GestionGrupoPublicoController
{


	private java.util.Properties pSpringProperties = null;
	private static Logger logger = Logger.getLogger(GestionGrupoPublicoControllerImpl.class);


    /**
     * @see es.pode.visualizador.presentacion.gestionGrupoPublico.GestionGrupoPublicoController#obtenerAccion(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionGrupoPublico.ObtenerAccionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String obtenerAccion(ActionMapping mapping, es.pode.visualizador.presentacion.gestionGrupoPublico.ObtenerAccionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String accion="Buscar";
    	String volver=form.getVolver();
    	String textoBuscado=form.getTextoBuscado();
    	String texto = form.getTextoBusqueda();
	    String textoBusqueda = texto.trim();
    	logger.info("La longitud es: " + texto.length());
    	java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);

        ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
        if((volver!=null && !volver.equals("") && volver.equals(i18n.getString("usuarios.solicitarAlta.volver")))){
        	form.setTextoBusqueda(textoBuscado);
     		accion="Volver";
     	}
        else if (textoBusqueda== null || textoBusqueda.equals("") || textoBusqueda.length()==0)
    	{
    		logger.error("Hay que introducir algún criterio de búsqueda");
    		throw new ValidatorException("{grupos.error.grupo.vacio}");
    	}else{
    		form.setTextoBusqueda(textoBusqueda);
    	}
    	 
         return accion;
    }



   



    /**
     * @see es.pode.visualizador.presentacion.gestionGrupoPublico.GestionGrupoPublicoController#recuperarGrupoPublico(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionGrupoPublico.RecuperarGrupoPublicoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void recuperarGrupoPublico(ActionMapping mapping, es.pode.visualizador.presentacion.gestionGrupoPublico.RecuperarGrupoPublicoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String nombre=form.getNombre();
    	String busqueda=form.getBusqueda();
    	if(busqueda!=null && !busqueda.equals(""))
    		form.setBusqueda(busqueda);
    	form.setNombre(nombre);
    	String usuarioConsultor=LdapUserDetailsUtils.getUsuario();
    	form.setUsuarioConsultor(usuarioConsultor);
    	
    	form.setUrlGrupo(PerfilPublico.urlGrupoPublico(nombre));
//    	String valorNombre=request.getSession().getAttribute("nombre").toString();
//    	if(nombre==null && valorNombre!=null && !valorNombre.equals("")){
//    		nombre=valorNombre;
//    		form.setNombre(nombre);
//    		request.getSession().setAttribute("nombre",null);
//    	}
//    	String usuarioRequest = (String)request.getSession().getAttribute("usuario").toString();
    	logger.info("El grupo que obtenemos es "+nombre);
    	String actionOdes=form.getActionOdes();
    	String actionUsuarios=form.getActionUsuarios();
    	String actionDescipcion=form.getActionDescripcion();
    	

    	Collection verMas=new ArrayList();
       GrupoPublicoVO grupoPublico=this.getSrvPerfilPublico().obtenerGrupoPublico(nombre);
       if(grupoPublico !=null  && grupoPublico.getNombre()!=null){
	       form.setNombre(grupoPublico.getNombre());
	       form.setAdministrador(grupoPublico.getAdministrador());
	       if(grupoPublico.getDescripcion()!=null && !grupoPublico.getDescripcion().equals("")){
	    	   form.setDescripcion(grupoPublico.getDescripcion());
	       }else{
	    	   form.setDescripcion(null);
	       }
	       form.setId(grupoPublico.getId());
	       String imagenGrupo=grupoPublico.getImagenGrupo();
	       if(imagenGrupo.equals("ImagenDefectoGrupo1"))
	    	   form.setImagen(ImagenesAgrega.urlImagenDefectoGrupo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_1)));
    	   else if(imagenGrupo.equals("ImagenDefectoGrupo2"))
    		   form.setImagen(ImagenesAgrega.urlImagenDefectoGrupo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_2)));
    	   else if(imagenGrupo.equals("ImagenDefectoGrupo3"))
    		   form.setImagen(ImagenesAgrega.urlImagenDefectoGrupo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_3)));
    	   else if(imagenGrupo.equals("ImagenDefectoGrupo4"))
    		   form.setImagen(ImagenesAgrega.urlImagenDefectoGrupo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_4)));
    	   else
    		   form.setImagen(ImagenesAgrega.urlImagenDefectoGrupo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_5)));
	       OdeGrupoVO[] odesLimpios=null;
	       if(grupoPublico.getAdministrador().equals(LdapUserDetailsUtils.getUsuario())){
	    	   form.setEsAdministrador(Boolean.TRUE);
	       }else{
	    	   form.setEsAdministrador(Boolean.FALSE);
	       }
	       GrupoPublicoAdminVO[] gruposUsuario = this.getSrvPerfilPublico().obtenerGrupoUsuario(LdapUserDetailsUtils.getUsuario());
	       String[] nombres=new String[gruposUsuario.length];
	   		for(int j=0;j<gruposUsuario.length;j++){
	   			String nombreGrupo=gruposUsuario[j].getNombre();
	   			nombres[j]=nombreGrupo;
	   		}
	   		List list =Arrays.asList(nombres);
	       if(list.contains(grupoPublico.getNombre())){
				form.setAsociado(Boolean.TRUE);
			}else{
				SolicitudGrupoVO[] solicitudes = this.getSrvPerfilPublico().listarSolicitudesRealizadasPorUsuario(LdapUserDetailsUtils.getUsuario());
				nombres=new String[solicitudes.length];
	    		for(int j=0;j<solicitudes.length;j++){
	    			String nombreGrupo=solicitudes[j].getGrupo();
	    			nombres[j]=nombreGrupo;
	    		}
	    		list =Arrays.asList(nombres);
	    		if(list.contains(grupoPublico.getNombre())){
	    			form.setAsociado(Boolean.TRUE);
    			}else{
    				form.setAsociado(Boolean.FALSE);
    			}
				
			}
		       if(actionOdes!=null && !actionOdes.equals("")){
		    	   odesLimpios =grupoPublico.getOdeGrupo();
		    	   verMas.add(Boolean.FALSE); 
		       }else{
		    	   OdeGrupoVO[] odesSelec = grupoPublico.getOdeGrupo();
		    	   if(odesSelec!=null && odesSelec.length>0){
				       if(odesSelec.length >new Long(getPropertyValue("cantidad_ver_publicos"))){
				    	   logger.info("Mas odes que los que pintamos ");
				    	   verMas.add(Boolean.TRUE); 
				    	   odesLimpios=new OdeGrupoVO[new Integer(getPropertyValue("cantidad_ver_publicos"))];
				    	   for(int i=0;i<odesLimpios.length;i++){
				    		   odesLimpios[i]=odesSelec[i];
				    	   }
				       }else{
				    	   verMas.add(Boolean.FALSE); 
				    	   odesLimpios=new OdeGrupoVO[odesSelec.length];
				    	   odesLimpios=odesSelec;
				       }
		    	   }else{
		    		   verMas.add(Boolean.FALSE); 
		    		   odesLimpios=new OdeGrupoVO[0];
		    	   }
		       }

		       UsuarioPublicoVO[] usuariosLimpios=null;

		       if(actionUsuarios!=null && !actionUsuarios.equals("")){
		    	   usuariosLimpios=this.getSrvPerfilPublico().listarUsuariosDeGrupo(nombre);
		    	   verMas.add(Boolean.FALSE); 
		       }else{
		    	   UsuarioPublicoVO[] usuariosSelec = this.getSrvPerfilPublico().listarUsuariosDeGrupo(nombre);
//			       GrupoPublicoVO[] gruposSelec = usuarioPublico.getGrupoPublico();
			       if(usuariosSelec!=null && usuariosSelec.length>0){
				       if(usuariosSelec.length>new Long(getPropertyValue("cantidad_ver_publicos"))){
				    	   logger.info("Mas usuarios que los que pintamos");
				    	   verMas.add(Boolean.TRUE);
				    	   usuariosLimpios=new UsuarioPublicoVO[new Integer(getPropertyValue("cantidad_ver_publicos"))];
				    	   for(int i=0;i<usuariosLimpios.length;i++){
				    		   usuariosLimpios[i]=usuariosSelec[i];
				    	   }
				    	   
				       }else{
				    	   verMas.add(Boolean.FALSE);
				    	   usuariosLimpios=new UsuarioPublicoVO[usuariosSelec.length];
				    	   usuariosLimpios=usuariosSelec;
				       }
			       }else{
			    	   verMas.add(Boolean.FALSE);
			    	   usuariosLimpios=new UsuarioPublicoVO[0];
			       }
			      
		       }
		       UsuarioPublicoAsociadoVO[]  usuariosAsociado=null;
		       if(usuariosLimpios!=null){
		    	   usuariosAsociado=new UsuarioPublicoAsociadoVO[usuariosLimpios.length];
		    	   ContactoVO[] contactos = this.getSrvPerfilPublico().listarContactosDeAgenda(LdapUserDetailsUtils.getUsuario());
			       nombres=new String[contactos.length];
			   		for(int j=0;j<contactos.length;j++){
			   			String nombreContacto=contactos[j].getUsuarioContacto();
			   			nombres[j]=nombreContacto;
			   		}
			   		list =Arrays.asList(nombres);
			       for(int i=0;i<usuariosLimpios.length;i++){
			    	   String imagenUsuario=usuariosLimpios[i].getFoto();
			    	   if(usuariosLimpios[i].getFoto().equals(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_USUARIO)){
			    		   usuariosLimpios[i].setFoto(ImagenesAgrega.urlImagenDefectoUsuario(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_USUARIO)));
				       }else{
				    	   usuariosLimpios[i].setFoto(AgregaPropertiesImpl.getInstance().getUrlNodo()+AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.IMAGENES_USUARIO_PUBLICO_URL)+"/"+imagenUsuario);
				       }
			    	   UsuarioPublicoAsociadoVO  usuarioAsociado=new UsuarioPublicoAsociadoVO();
				       if(list.contains(usuariosLimpios[i].getNombreUsuario())){
				    	   usuarioAsociado.setAsociadoUsuario(Boolean.TRUE);
						}else{
							usuarioAsociado.setAsociadoUsuario(Boolean.FALSE);
						}
				       usuarioAsociado.setActivo(usuariosLimpios[i].getActivo());
				       usuarioAsociado.setCentroEducativo(usuariosLimpios[i].getCentroEducativo());
				       usuarioAsociado.setContacto(usuariosLimpios[i].getContacto());
				       usuarioAsociado.setContenidoImagen(usuariosLimpios[i].getContenidoImagen());
				       usuarioAsociado.setDescripcion(usuariosLimpios[i].getDescripcion());
				       usuarioAsociado.setFavoritoUsuario(usuariosLimpios[i].getFavoritoUsuario());
				       usuarioAsociado.setFoto(usuariosLimpios[i].getFoto());
				       usuarioAsociado.setGrupoPublico(usuariosLimpios[i].getGrupoPublico());
				       usuarioAsociado.setId(usuariosLimpios[i].getId());
				       usuarioAsociado.setMostrarFavoritos(usuariosLimpios[i].getMostrarFavoritos());
				       usuarioAsociado.setMostrarGrupos(usuariosLimpios[i].getMostrarGrupos());
				       usuarioAsociado.setMostrarObjetos(usuariosLimpios[i].getMostrarObjetos());
				       usuarioAsociado.setNombreUsuario(usuariosLimpios[i].getNombreUsuario());
				       usuarioAsociado.setRecibirCorreo(usuariosLimpios[i].getRecibirCorreo());
				       usuarioAsociado.setUsuario(usuariosLimpios[i].getUsuario());
				       usuariosAsociado[i]=usuarioAsociado;
			       }
		       }
		      
	       OdeConURLs[]odesUrl = insertarUrls(odesLimpios);
	       form.setOdesAsArray(odesUrl);
	       form.setUsuariosAsArray(usuariosAsociado);
	      
	       form.setVerMas(verMas);
	       
	       form.setActionUsuarios(null);
	       form.setActionOdes(null);
	       form.setActionDescripcion(null);
	       String imagenEncodada=PerfilPublico.encodarTexto(form.getImagen());
	       String urlFeed=PerfilPublico.encodarTexto(AgregaPropertiesImpl.getInstance().getUrlNodo()+"/widgets/grupo/"+nombre+"/"+this.getSrvPropiedadService().get(AgregaProperties.NUMERO_ODES_EN_FLASH));
	       String codigoEmbeb="<object classid='clsid:d27cdb6e-ae6d-11cf-96b8-444553540000' codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0' width='280' height='656' id='scrollerAgrega_vert' align='middle'>" +
      		" <param name='allowScriptAccess' value='sameDomain' />" +
      		"<param name='allowFullScreen' value='false' />" +
      		"<param name='FlashVars' value='imagenUsr="+imagenEncodada+"&nombreUsr="+form.getNombre()+"&urlFeed="+urlFeed+"'>" +
      				"  <param name='movie' value='"+AgregaPropertiesImpl.getInstance().getUrlNodo()+"/static/flash/scrollerAgrega_widget/scrollerAgrega_vert.swf' />" +
      						"<param name='quality' value='high' /><param name='bgcolor' value='#333333' />   " +
      						"<embed src='"+AgregaPropertiesImpl.getInstance().getUrlNodo()+"/static/flash/scrollerAgrega_widget/scrollerAgrega_vert.swf' quality='high' bgcolor='#333333' width='280' height='656' name='scrollerAgrega_vert' align='middle' allowScriptAccess='sameDomain' allowFullScreen='false' type='application/x-shockwave-flash' pluginspage='http://www.adobe.com/go/getflashplayer' FlashVars = 'imagenUsr="+imagenEncodada+"&nombreUsr="+form.getNombre()+"&urlFeed="+urlFeed+"' />" +
      								"</object>";
      form.setCodigoEmbeb(codigoEmbeb);
	       
       }else{
    	   logger.error("No existe ningún grupo con ese nombre");
    	   throw new ValidatorException("{errors.listarGrupoPublico}");
       }
     }

    private String getPropertyValue(String sKey) throws IOException {
    	try{
    		InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/spring_visualizadorcontenidos2.properties");
			if (this.pSpringProperties == null) {
				pSpringProperties = new java.util.Properties();
				pSpringProperties.load(fIsSpringProperties);
			}
			fIsSpringProperties.close();
			logger.debug("GestionGrupoPublico - getPropertyValue: Propiedad recuperada: " + sKey + " : "+ pSpringProperties.getProperty(sKey));
//				 devolvemos la propiedad
            
        }catch (Exception e){
 		logger.error("GestionGrupoPublico - Error recuperando propiedad de spring_visualizadorcontenidos2.properties=",e);
        }
		
		return pSpringProperties.getProperty(sKey);
	}

    private OdeConURLs[] insertarUrls(OdeGrupoVO[] odes){
    	OdeConURLs[] odesURL=null;
	    	try{
	    		Collection<OdeConURLs> collections=new ArrayList();
	    		for(int i=0;i<odes.length;i++){
	    			OdeConURLs odeCon=new OdeConURLs();
	    			String identificador=odes[i].getId_mec();
	    			String idioma=odes[i].getIdioma();
	    			String urlFicha=ODEPublico.urlFichaODEPublicado(identificador, idioma);
	    			String urlPrevisualizar=ODEPublico.urlPrevisualizaODEPublicado(identificador, idioma);
	    			String urlImagen=ImagenODE.urlImagenODE(identificador);
	    			odeCon.setUrlFicha(urlFicha);
	    			odeCon.setUrlPrevisualizar(urlPrevisualizar);
	    			odeCon.setUrlImagen(urlImagen);
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
		
		        private java.lang.String urlImagen;
		        
		        private java.lang.String urlFicha;
		        
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
		
		
		    	
		    }
		 public class UsuarioPublicoAsociadoVO {
		 /* Nombre de la foto del usuario */
		    private java.lang.String foto;

		    /* Indica si se va a mostrar los objetos pÃºblicos del usuario
		     * en su
		     *                                 pÃ¡gina pÃºblica */
		    private java.lang.Boolean mostrarObjetos;

		    /* Indica si se va a mostrar el bloque de los odes favoritos de
		     * un
		     *                                 usuario en la pÃ¡gina pÃºblica de un
		     * usuario */
		    private java.lang.Boolean mostrarFavoritos;

		    /* Indica si se va a mostrar los grupos de un usuario en su pÃ¡gina
		     * pÃºblica */
		    private java.lang.Boolean mostrarGrupos;

		    /* Nombre del centro educativo del usuario */
		    private java.lang.String centroEducativo;

		    private java.lang.Boolean recibirCorreo;

		    public java.lang.String getFoto() {
				return foto;
			}

			public void setFoto(java.lang.String foto) {
				this.foto = foto;
			}

			public java.lang.Boolean getMostrarObjetos() {
				return mostrarObjetos;
			}

			public void setMostrarObjetos(java.lang.Boolean mostrarObjetos) {
				this.mostrarObjetos = mostrarObjetos;
			}

			public java.lang.Boolean getMostrarFavoritos() {
				return mostrarFavoritos;
			}

			public void setMostrarFavoritos(java.lang.Boolean mostrarFavoritos) {
				this.mostrarFavoritos = mostrarFavoritos;
			}

			public java.lang.Boolean getMostrarGrupos() {
				return mostrarGrupos;
			}

			public void setMostrarGrupos(java.lang.Boolean mostrarGrupos) {
				this.mostrarGrupos = mostrarGrupos;
			}

			public java.lang.String getCentroEducativo() {
				return centroEducativo;
			}

			public void setCentroEducativo(java.lang.String centroEducativo) {
				this.centroEducativo = centroEducativo;
			}

			public java.lang.Boolean getRecibirCorreo() {
				return recibirCorreo;
			}

			public void setRecibirCorreo(java.lang.Boolean recibirCorreo) {
				this.recibirCorreo = recibirCorreo;
			}

			public java.lang.String getUsuario() {
				return usuario;
			}

			public void setUsuario(java.lang.String usuario) {
				this.usuario = usuario;
			}

			public javax.activation.DataHandler getContenidoImagen() {
				return contenidoImagen;
			}

			public void setContenidoImagen(javax.activation.DataHandler contenidoImagen) {
				this.contenidoImagen = contenidoImagen;
			}

			public java.lang.Long getId() {
				return id;
			}

			public void setId(java.lang.Long id) {
				this.id = id;
			}

			public java.lang.String getNombreUsuario() {
				return nombreUsuario;
			}

			public void setNombreUsuario(java.lang.String nombreUsuario) {
				this.nombreUsuario = nombreUsuario;
			}

			public java.lang.String getDescripcion() {
				return descripcion;
			}

			public void setDescripcion(java.lang.String descripcion) {
				this.descripcion = descripcion;
			}

			public java.lang.Boolean getActivo() {
				return activo;
			}

			public void setActivo(java.lang.Boolean activo) {
				this.activo = activo;
			}

			public es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO[] getGrupoPublico() {
				return grupoPublico;
			}

			public void setGrupoPublico(
					es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO[] grupoPublico) {
				this.grupoPublico = grupoPublico;
			}

			public es.pode.adminusuarios.negocio.servicios.FavoritoVO[] getFavoritoUsuario() {
				return favoritoUsuario;
			}

			public void setFavoritoUsuario(
					es.pode.adminusuarios.negocio.servicios.FavoritoVO[] favoritoUsuario) {
				this.favoritoUsuario = favoritoUsuario;
			}

			public es.pode.adminusuarios.negocio.servicios.ContactoVO[] getContacto() {
				return contacto;
			}

			public void setContacto(
					es.pode.adminusuarios.negocio.servicios.ContactoVO[] contacto) {
				this.contacto = contacto;
			}

			public Boolean getAsociadoUsuario() {
				return asociadoUsuario;
			}

			public void setAsociadoUsuario(Boolean asociadoUsuario) {
				this.asociadoUsuario = asociadoUsuario;
			}

			private java.lang.String usuario;

		    private javax.activation.DataHandler contenidoImagen;

		    private java.lang.Long id;

		    /* Nombre y apellidos del usuario */
		    private java.lang.String nombreUsuario;

		    /* Descripcion del usuario publico */
		    private java.lang.String descripcion;

		    private java.lang.Boolean activo;

		    private es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO[] grupoPublico;

		    private es.pode.adminusuarios.negocio.servicios.FavoritoVO[] favoritoUsuario;

		    private es.pode.adminusuarios.negocio.servicios.ContactoVO[] contacto;
		    
		    private Boolean asociadoUsuario;

		    public UsuarioPublicoAsociadoVO() {
		    }
		 }


}