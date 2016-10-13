
package es.pode.visualizador.presentacion.modificarPerfil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimePartDataSource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.adminusuarios.negocio.servicios.UsuarioPublicoVO;
import es.pode.adminusuarios.negocio.servicios.UsuarioVO;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.i18n.LocalizacionIdiomaVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.url.ImagenesAgrega;

/**
 * @see es.pode.administracion.presentacion.adminusuarios.modificarPerfil.ModificarPerfilController
 */
public class ModificarPerfilControllerImpl extends ModificarPerfilController {

    private static Logger logger = Logger.getLogger(ModificarPerfilControllerImpl.class);

    private java.util.Properties pSpringProperties = null;
    public final static String PUNTO = ".";

    /**
     * @see es.pode.administracion.presentacion.adminusuarios.modificarPerfil.ModificarPerfilController#recuperarUsuario(org.apache.struts.action.ActionMapping,
     *      es.pode.administracion.presentacion.adminusuarios.modificarPerfil.RecuperarUsuarioForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public final void recuperarUsuario(
    		ActionMapping mapping, 
    		RecuperarUsuarioForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    throws Exception 
    {

    	
	  	String idiomaBusquedaSelected = LdapUserDetailsUtils.getIdiomaBusqueda();
		String idiomaSelected = LdapUserDetailsUtils.getIdioma();
		I18n i18n = I18n.getInstance();
		LocalizacionIdiomaVO[] localizadorBusquedaIdioma = i18n.obtenerIdiomasBuscablesLocalizados(idiomaSelected);
		form.setIdiomaBusquedaBackingList(Arrays.asList(localizadorBusquedaIdioma), "idLocalizacion", "nombre");
		LocalizacionIdiomaVO[] localizadorNavegacionIdioma = i18n.obtenerIdiomasPlataformaLocalizados(idiomaSelected);
		form.setIdiomaBackingList(Arrays.asList(localizadorNavegacionIdioma), "idLocalizacion", "nombre");
		String usuarioModificar = LdapUserDetailsUtils.getUsuario();
		UsuarioVO usuario = this.getSrvAdminUsuariosService().obtenerDatosUsuario(usuarioModificar);
		form.setTipoEmpaquetador(usuario.getTipoEmpaquetador());
		form.setApellido1(usuario.getApellido1());
		form.setNombre(usuario.getNombre());
		form.setUsuario(usuario.getUsuario());
		form.setId(usuario.getId());
		form.setEmail(usuario.getEmail());
		form.setApellido2(usuario.getApellido2());
		form.setIdioma(usuario.getIdioma());
		form.setIdiomaBusqueda(usuario.getIdiomaBusqueda());
		form.setNIF(usuario.getNIF());
		form.setTipoCatalogador(usuario.getTipoCatalogador());
		form.setTipoVisualizador(usuario.getTipoVisualizador());
		form.setOpenIdUrl(usuario.getOpenIdUrl());
		form.setRecibirCorreo((usuario.getRecibirCorreoPublicacion()));
		//convertimos los bytes de cuota a mbytes para mostrarlos por pantalla
		long cuota = usuario.getCuota().intValue()/1048576;
		form.setCuota(cuota);
		
		//recogemos los roles del usuario
		String[] roles = this.getSrvAdminUsuariosService().listarRolesUsuario(usuarioModificar);
		
		//Comprobamos si el rol del usuario es únicamente EMPAQUETADOR
		if((roles.length == 1)&&(roles[0].equalsIgnoreCase(getPropertyValue("rolEmpaquetador"))))
		{
			if(logger.isDebugEnabled())logger.debug("El usuario es empaquetador no puede modificar el tipo de empaquetador");
			form.setRolEmpaquetador("S");
		}else
		{
			form.setRolEmpaquetador("N");
		}
		
		//Comprobamos si el rol del usuario es únicamente CATALOGADOR
		if((roles.length == 1)&&(roles[0].equalsIgnoreCase(getPropertyValue("rolCatalogador"))))
		{
			if(logger.isDebugEnabled())logger.debug("El usuario es empaquetador no puede modificar el tipo de empaquetador");
			form.setRolCatalogador("S");
		}else
		{
			form.setRolCatalogador("N");
		}
		
		//Comprobamos si el usuario tiene rol de administrador
		String rolAdmin = new String("N");
		for(int i=0;i<roles.length;i++)
		{
			if(roles[i].equalsIgnoreCase(getPropertyValue("rolAdministrador")))
				rolAdmin = "S";
		}
			
		form.setRolAdministrador(rolAdmin);
		
		if(logger.isDebugEnabled())logger.debug("despues de cargar los datos en el formulario");
		logger.info("Vemos si tiene parte pública");
		
		if(usuario.getUsuarioPublico()!=null ){
			if(usuario.getUsuarioPublico().getActivo().equals(Boolean.TRUE)){
				form.setPerfilPublico(Boolean.TRUE);
			}else{
				form.setPerfilPublico(Boolean.FALSE);
			}
			UsuarioPublicoVO usuarioPublico = usuario.getUsuarioPublico();
			logger.info("Hemos obtenido el usuario publico "+usuarioModificar);
			form.setCentroEducativo(usuarioPublico.getCentroEducativo());
			form.setIdPublico(usuarioPublico.getId());
			form.setMostrarFavoritos(usuarioPublico.getMostrarFavoritos());
			form.setMostrarGrupos(usuarioPublico.getMostrarGrupos());
			form.setMostrarObjetos(usuarioPublico.getMostrarObjetos());
			form.setRecibirCorreoItinerarios(usuarioPublico.getRecibirCorreo());
			form.setUsuario(usuarioPublico.getUsuario());
			form.setNombreUsuario(usuarioPublico.getNombreUsuario());
			form.setDescripcionUsuario(usuarioPublico.getDescripcion());
			if(logger.isDebugEnabled())logger.debug("Estamos cargando el usuario publico con identificador "+usuario.getId());
			
			if(usuarioPublico.getFoto() != null && !usuarioPublico.getFoto().equals("")){
			
								
				 if(usuarioPublico.getFoto().equals(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_USUARIO)){
			    	   form.setFoto(ImagenesAgrega.urlImagenDefectoUsuario(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_USUARIO)));
			    	  
			       }else{
			    	   form.setFoto(AgregaPropertiesImpl.getInstance().getUrlNodo()+AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.IMAGENES_USUARIO_PUBLICO_URL)+"/"+usuarioPublico.getFoto());
			    	  
			       }
				
				
				//Esta variable no se debe pintar en la jsp como tal; hay que utilizarla para pintar la imagen:uploads/imagenesNodosExternos/nodo+nombreImagen
//				String url=AgregaPropertiesImpl.getInstance().getUrlNodo()+AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.IMAGENES_USUARIO_PUBLICO_URL)+"/"+usuarioPublico.getFoto();
//				if(logger.isDebugEnabled())logger.debug("Vamos a insertarle la url de la imagen "+url);
				
//	    		form.setFoto(url); 
	    		if(usuarioPublico.getFoto().equals(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_USUARIO))
	    		{
	    			
	    			form.setEliminarImagen(Boolean.FALSE);
	    		}else{
	    			
	    			form.setEliminarImagen(Boolean.TRUE);
	    		}
			}else{
				//form.setFoto(null);
				 form.setFoto(ImagenesAgrega.urlImagenDefectoUsuario(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_USUARIO)));
			}
		}else{
			logger.error("No tiene parte publica");
			form.setPerfilPublico(Boolean.FALSE);
			form.setFoto(null);
		}
    }

    /**
     * @see es.pode.administracion.presentacion.adminusuarios.modificarPerfil.ModificarPerfilController#modificarUsuarioMismo(org.apache.struts.action.ActionMapping,
     *      es.pode.administracion.presentacion.adminusuarios.modificarPerfil.ModificarUsuarioMismoForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public final void modificarUsuarioMismo(
    		ActionMapping mapping, 
    		ModificarUsuarioMismoForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response)
    throws Exception 
    {
		
    	String resultado="OK.MODIFICARUSUARIO";	
    	String resultado1= "OK.MODIFICARUSUARIO";
    	String resultado2="OK.MODIFICARUSUARIO";
		try {
		    //String idiomaSelected = LdapUserDetailsUtils.getIdioma();
		    String nombre = request.getParameter("nombre");
		    String apellido1 = request.getParameter("apellido1");
		    Long id = Long.valueOf(request.getParameter("id"));
		    String apellido2 = request.getParameter("apellido2");
		    String email = request.getParameter("email");
		    String idioma = request.getParameter("idioma");
		    String idiomaBusqueda = request.getParameter("idiomaBusqueda");
		    String tipoEmpaquetador = request.getParameter("tipoEmpaquetador");
		    String clave = request.getParameter("clave");
		    String openIdUrl = request.getParameter("openIdUrl");
		    String repitaClave = request.getParameter("repitaClave");
		    String tipoCatalogador = request.getParameter("tipoCatalogador");
		    String tipoVisualizador = request.getParameter("tipoVisualizador");
		    Boolean recibirCorreo= form.getRecibirCorreo();//CorreoPublicacion de Usuario
		    if(recibirCorreo==null){
		    	recibirCorreo=Boolean.FALSE;
		    }
		   
		   
		    
		    Boolean perfilPublico = form.getPerfilPublico();//No esta checkeado
		    logger.info("Se ha checkeado el perfil publico? "+perfilPublico);
		    if(perfilPublico==null)
		    	perfilPublico=Boolean.FALSE;
		    Boolean existeUsuarioPublico=LdapUserDetailsUtils.tienePerfilPublico();//Para ver si tenia usuario publico de antemano
		    logger.info("Tenia usuarioPublico de antemanao?"+existeUsuarioPublico);
		   
		    long cuota = 0;
		    Matcher matcher = null;
		    
		  
		    if(request.getParameter("cuota").length() > 0)
		    {
		    	Pattern maskNum = Pattern.compile("[0-9]+");
				matcher = maskNum.matcher(request.getParameter("cuota"));
			    if (!matcher.matches()) 
			    {
			    	if(logger.isDebugEnabled())logger.debug("cuota caracter ilegal");
			    	throw new ValidatorException("{errors.modificarusuario.cuota}");
			    }
			    	cuota = Long.valueOf(request.getParameter("cuota")).longValue();
			    	cuota = cuota * 1024 * 1024;
		    }
		    
		    UsuarioVO usuarioVO = this.getSrvAdminUsuariosService().descripcionUsuario(id);
		    UsuarioPublicoVO usuarioPublicoViejo=usuarioVO.getUsuarioPublico();
		    // Validaciones de los campos que recogemos del formulario
		    Pattern mask = Pattern.compile("[^\\¿\\?\\¡\\!\\>\\#\\&\\<\\@\\$\\/\\\'\\\"]+");
    	    // Validaciones para password
    	    Pattern maskPwd = Pattern.compile("[^Ññ\\¿\\?\\¡\\!\\>\\#\\&\\<\\@\\$\\/\\\'\\\"]+");
		    
	
		    if (nombre.length() <= 0) {
			if(logger.isDebugEnabled())logger.debug("nombre.length() <= 0");
			throw new ValidatorException("{errors.modificarusuario.nombre}");
		    }
		    matcher = mask.matcher(nombre);
		    if (!matcher.matches()) {
			if(logger.isDebugEnabled())logger.debug("nombre caracter ilegal");
	
			throw new ValidatorException("{errors.modificarusuario.nombre.caracterIlegal}");
		    }
	
		    if (apellido1.length() <= 0) {
			if(logger.isDebugEnabled())logger.debug("apellido1.length() <= 0");
	
			throw new ValidatorException("{errors.modificarusuario.apellido1}");
		    }
		    matcher = mask.matcher(apellido1);
		    if (!matcher.matches()) {
			if(logger.isDebugEnabled())logger.debug("apellido1 caracter ilegal");
			throw new ValidatorException("{errors.modificarusuario.apellido1.caracterIlegal}");
		    }
		    if (apellido2.length() > 0) {
			matcher = mask.matcher(apellido2);
			if (!matcher.matches()) {
			    if(logger.isDebugEnabled())logger.debug("apellido2 caracter ilegal");
			    throw new ValidatorException("{errors.modificarusuario.apellido2.caracterIlegal}");
			}
		    }
		    /*
		     * if (apellido2.length() <= 0) { if(logger.isDebugEnabled())logger.debug("apellido2.length() <=
		     * 0");
		     * 
		     * throw new
		     * ValidatorException("{errors.modificarusuario.apellido2}"); }
		     * matcher = mask.matcher(apellido2); if (!matcher.matches()) {
		     * if(logger.isDebugEnabled())logger.debug("apellido2 caracter ilegal"); throw new
		     * ValidatorException("{errors.altausuario.modificarusuario.caracterIlegal}"); }
		     */
		    if (email.length() <= 0) {
			if(logger.isDebugEnabled())logger.debug("email.length() <= 0");
			throw new ValidatorException("{errors.modificarusuario.email}");
		    }
		    if (!((email.indexOf("@") > 0) && (email.indexOf(".") > 0))) {
			throw new ValidatorException("{errors.modificarusuario.emailIncorrecto}");
		    }
		    
		    //Validamos la parte pública; 1- Si no tiene parte pública y ha insertado valores en el formulario de comunidad se lo hacemos saber
		    //2-Si tiene perfil público el centro educativo es obligatorio
		    
		    Boolean mostrarFavoritos = form.getMostrarFavoritos();
		    if(mostrarFavoritos==null)
		    	mostrarFavoritos=Boolean.FALSE;
		    Boolean mostrarGrupos = form.getMostrarGrupos();
		    if(mostrarGrupos==null)
		    	mostrarGrupos=Boolean.FALSE;
		    Boolean mostrarObjetos =form.getMostrarObjetos();
		    if(mostrarObjetos==null)
		    	mostrarObjetos=Boolean.FALSE;
		   
		    
		    FormFile imagen =form.getFotoNueva();
		    Boolean insertadoImagen=Boolean.FALSE;
		    if(imagen!=null && imagen.getFileName()!=null && !imagen.getFileName().equals("")){
		    	insertadoImagen=Boolean.TRUE;
		    }
		    String descripcion =form.getDescripcionUsuario();
		    String centroEducativo = form.getCentroEducativo();
//		    if( perfilPublico==Boolean.TRUE && (centroEducativo==null || centroEducativo.equals("") || centroEducativo.length()<1)){
//		    	throw new ValidatorException("{errors.modificarusuario.parte.publica.activada.centro}");
//		    }
		    //Hacemos esta validacion para el caso de que siga sin activar el usaurio publico y modifica sus valores........
//		    if(existeUsuarioPublico==Boolean.FALSE && perfilPublico==Boolean.FALSE && usuarioPublicoViejo!=null &&( 
//		    		!usuarioPublicoViejo.getMostrarObjetos().equals(mostrarObjetos) || !usuarioPublicoViejo.getMostrarFavoritos().equals(mostrarFavoritos)
//		    		|| !usuarioPublicoViejo.getMostrarGrupos().equals(mostrarGrupos) || 
//		    		insertadoImagen==Boolean.TRUE || !usuarioPublicoViejo.getDescripcion().equals(descripcion)  || !usuarioPublicoViejo.getCentroEducativo().equals(centroEducativo))){
//		    	throw new ValidatorException("{errors.modificarusuario.parte.publica.desactivada}");
//		    }
		  
		    if ((clave == null) || (clave.equalsIgnoreCase(""))) {
			if(logger.isDebugEnabled())logger.debug("No se modifica la clave");
		    } else {
				if (clave.length() < 8) {
				    if(logger.isDebugEnabled())logger.debug("clave .length() <= 8");
				    throw new ValidatorException("{errors.modificarusuario.clave}");
				}
				
			    matcher = maskPwd.matcher(clave);
			    if (!matcher.matches()) {
			    	if(logger.isDebugEnabled())logger.debug("contraseña caracter ilegal");
			    	throw new ValidatorException(
								"{errors.modificarusuario.clave.caracterIlegal}");
			    }
		
				if (!(clave.equalsIgnoreCase(repitaClave))) {
				    throw new ValidatorException("{errors.modificarusuario.claveDistintas}");
				}
				usuarioVO.setClave(clave);
		    }
		    if(openIdUrl!=null && openIdUrl.length()>0){
		    	UsuarioVO usuariovo=this.getSrvAdminUsuariosService().obtenerUsuarioConOpenId(openIdUrl);
		    	if(usuariovo!=null && !usuariovo.getUsuario().equals(usuarioVO.getUsuario())){
		    		if(logger.isDebugEnabled())logger.debug("Ese identificador de openId ya existe en la base de datos");
			    	throw new ValidatorException("{errors.altausuario.existe.openId}");
		    	}
		    	
		    }
		    if (idiomaBusqueda == null) {
			throw new ValidatorException("{errors.modificarusuario.idiomaBusqueda}");
		    }
		    if (idiomaBusqueda.length() <= 0) {
			if(logger.isDebugEnabled())logger.debug("empaquetador .length() <= 0");
			throw new ValidatorException("{errors.modificarusuario.idiomaBusqueda}");
		    }
	
		    if (idioma == null) {
			throw new ValidatorException("{errors.modificarusuario.idioma}");
		    }
		    if (idioma.length() <= 0) {
			if(logger.isDebugEnabled())logger.debug("empaquetador .length() <= 0");
			throw new ValidatorException("{errors.modificarusuario.idioma}");
		    }
	
		    if (tipoEmpaquetador == null) {
			throw new ValidatorException("{errors.modificarusuario.tipoEmpaquetador}");
		    }
		    if (tipoEmpaquetador.length() <= 0) {
			if(logger.isDebugEnabled())logger.debug("empaquetador .length() <= 0");
			throw new ValidatorException("{errors.modificarusuario.tipoEmpaquetador}");
		    }
	
		    if (tipoVisualizador == null) {
				throw new ValidatorException("{errors.modificarusuario.tipoVisualizador}");
			 }
		    if (tipoVisualizador.length() <= 0) {
				if(logger.isDebugEnabled())logger.debug("tipoVisualizador.length() <= 0");
				throw new ValidatorException("{errors.modificarusuario.tipoVisualizador}");
		    }
		    // /////Fin de validaciones de campo //////////
		    
		    
		    String nombreUsuario="";
			
		    usuarioVO.setNombre(nombre);
		    usuarioVO.setApellido1(apellido1);
		    usuarioVO.setApellido2(apellido2);
		    nombreUsuario=nombre+" "+apellido1;
		    if(apellido2!=null && !apellido2.equals("")){
				nombreUsuario=nombreUsuario+" "+apellido2;
			}
		    usuarioVO.setEmail(email);
		    usuarioVO.setTipoEmpaquetador(tipoEmpaquetador);
		    usuarioVO.setIdioma(idioma);
		    usuarioVO.setIdiomaBusqueda(idiomaBusqueda);
		    usuarioVO.setTipoCatalogador(tipoCatalogador);
		    usuarioVO.setTipoVisualizador(tipoVisualizador);
		    usuarioVO.setCuota(new Long(cuota));
		    usuarioVO.setOpenIdUrl(openIdUrl);
		    usuarioVO.setRecibirCorreoPublicacion(recibirCorreo);//Mis objetos
		    usuarioVO.setId(id);
		    resultado1 = this.getSrvAdminUsuariosService().modificarUsuario(usuarioVO);
		    logger.info("Se ha modificado el usuario privado? "+resultado1);
		    Boolean recibirCorreoItinerarios = form.getRecibirCorreoItinerarios();
		    
		    if(recibirCorreoItinerarios==null)
		    	recibirCorreoItinerarios=Boolean.FALSE; 
		    //Guardamos el perfil público si existe
//		    String fotoViejo="";
//		    if(usuarioPublicoViejo!=null && usuarioPublicoViejo.getUsuario()!=null){
//		    	fotoViejo= usuarioPublicoViejo.getFoto();
//		    }
    		
    		DataHandler contenidoImagenNodo = null;
    		String imagenNodo= "";
 	       if(logger.isDebugEnabled())logger.debug("Lo que hemos recibido del formulario es "+imagen);
 	        
 	       if(imagen!=null && imagen.getFileName()!=null && !imagen.getFileName().equals("")){
 	    	    imagenNodo= imagen.getFileName();
		    	InternetHeaders ih = new InternetHeaders();
				MimeBodyPart mbp = new MimeBodyPart(ih, imagen.getFileData());
				DataSource dsource = new MimePartDataSource(mbp);
				contenidoImagenNodo = new DataHandler(dsource);
				if(logger.isDebugEnabled())logger.debug("Creamos el datahandler de la imagen "+ contenidoImagenNodo);
			}else{
				logger.info("No se le ha insertado una nueva imagen, nos quedamos con el que tenía antes");
			}
 	      String usuario = form.getUsuario();
 	      String nombreImagen=null;
			if(imagenNodo!=null && !imagenNodo.equals("") && imagenNodo.length()>0){
				//TODO Hay que validar que la extensión que sea uno de los que viene en el agrega.properties ; ESta hecho en el CambiarImagenController del busacador
				// el metodo esImagen();
				//Si no valida habrá que lanzar un validatorException que diga que la extensión de la imagen no es válida.
				String extension = imagenNodo.substring(imagenNodo.lastIndexOf(PUNTO)+1,imagenNodo.length());
				if (!esImagen(extension)){
					throw new ValidatorException("{errors.imagen.extension.incorrecta}");
				}
				
			}else{
				logger.info("No se ha seleccionado ninguna imagen para el usuario publico");
			}
			UsuarioPublicoVO usuarioPublicoVO =new UsuarioPublicoVO();
			Long  resultadoModificacion=null;
		    if(perfilPublico==Boolean.TRUE){
		    	logger.info("Esta chekeado");
		    	if(existeUsuarioPublico==Boolean.FALSE && usuarioPublicoViejo==null){
		    		logger.info("No existia usuario publico" +usuarioPublicoViejo +" o está desactivado "+existeUsuarioPublico);
		    		logger.info("Tenemos que crear el usuario publico");
		    		usuarioPublicoVO.setCentroEducativo(centroEducativo);
		    		 if(insertadoImagen==Boolean.TRUE){
					    usuarioPublicoVO.setContenidoImagen(contenidoImagenNodo);
					    usuarioPublicoVO.setFoto(imagenNodo);
		    		 }else{
					    	usuarioPublicoVO.setContenidoImagen(null);
						    usuarioPublicoVO.setFoto("");
					    }
				    usuarioPublicoVO.setMostrarFavoritos(mostrarFavoritos);
				    usuarioPublicoVO.setMostrarGrupos(mostrarGrupos);
				    usuarioPublicoVO.setMostrarObjetos(mostrarObjetos);
				    usuarioPublicoVO.setRecibirCorreo(recibirCorreoItinerarios);//Itinerarios
				    usuarioPublicoVO.setUsuario(usuario);
				    usuarioPublicoVO.setNombreUsuario(nombreUsuario);
				    usuarioPublicoVO.setActivo(Boolean.TRUE);
				  //La descripcion debe ser menor que 2000
					String desc="";
					   if(descripcion.length()>1995){
						   desc=descripcion.substring(0, 1995);
					   }else{
						   desc=descripcion;
					   }
				    usuarioPublicoVO.setDescripcion(desc);
				    usuarioPublicoVO.setDescripcion(descripcion);
				    resultadoModificacion=this.getSrvPerfilPublico().crearUsuarioPublico(usuarioPublicoVO, usuario);
				    logger.info("El resultado de creacion "+resultadoModificacion);
				    if(resultadoModificacion!=null){
				    	resultado2="OK.MODIFICARUSUARIO";
				    }else{
				    	resultado2="FALLO.MODIFICARUSUARIO";
				    }
		    		
		    	}else{
		    		logger.info("Tenemos que modificar el usuario existente, existe usuario publico y desactivado "+existeUsuarioPublico+" o usuarioPublicoViejo "+usuarioPublicoViejo);
		    		 	usuarioPublicoVO = this.getSrvPerfilPublico().obtenerUsuarioPublico(usuario);
		    		 	usuarioPublicoVO.setActivo(Boolean.TRUE);
					    logger.info("Exite usuario publico "+usuarioPublicoVO.getId());
					    usuarioPublicoVO.setCentroEducativo(centroEducativo);
					    if(insertadoImagen==Boolean.TRUE){
						    usuarioPublicoVO.setContenidoImagen(contenidoImagenNodo);
						    usuarioPublicoVO.setFoto(imagenNodo);
					    }else{
					    	usuarioPublicoVO.setContenidoImagen(null);
						    usuarioPublicoVO.setFoto("");
					    }
					    usuarioPublicoVO.setMostrarFavoritos(mostrarFavoritos);
					    usuarioPublicoVO.setMostrarGrupos(mostrarGrupos);
					    usuarioPublicoVO.setMostrarObjetos(mostrarObjetos);
					    usuarioPublicoVO.setRecibirCorreo(recibirCorreoItinerarios);//Itinerarios
					    usuarioPublicoVO.setUsuario(usuario);
					    usuarioPublicoVO.setNombreUsuario(nombreUsuario);
					   //La descripcion debe ser menor que 2000
						String desc="";
						   if(descripcion.length()>1995){
							   desc=descripcion.substring(0, 1995);
						   }else{
							   desc=descripcion;
						   }
					    usuarioPublicoVO.setDescripcion(desc);
					    resultadoModificacion=this.getSrvPerfilPublico().modificarUsuarioPublico(usuarioPublicoVO, usuario);
					    logger.info("El resultado de modificacion es "+resultadoModificacion);
					    if(resultadoModificacion!=null && resultadoModificacion.equals(usuarioPublicoVO.getId())){
					    	resultado2="OK.MODIFICARUSUARIO";
					    }else{
					    	resultado2="FALLO.MODIFICARUSUARIO";
					    }
		    	}
		    }else{
		    	
		    	if(existeUsuarioPublico==Boolean.FALSE && usuarioPublicoViejo==null){
		    		logger.info("No ha activado el perfil publico");
		    		
		    	}else{
		    		logger.info("Ha desactivado el usuario publico");
		    	
		    		//Guardamos los datos y activo false
		    		usuarioPublicoVO = this.getSrvPerfilPublico().obtenerUsuarioPublico(usuario);
	    		 	usuarioPublicoVO.setActivo(Boolean.FALSE);
				    logger.info("Exite usuario publico "+usuarioPublicoVO.getId());
				    usuarioPublicoVO.setCentroEducativo(centroEducativo);
				    if(insertadoImagen==Boolean.TRUE){
					    usuarioPublicoVO.setContenidoImagen(contenidoImagenNodo);
					    usuarioPublicoVO.setFoto(imagenNodo);
				    }else{
				    	usuarioPublicoVO.setContenidoImagen(null);
					    usuarioPublicoVO.setFoto("");
				    }
				    usuarioPublicoVO.setMostrarFavoritos(mostrarFavoritos);
				    usuarioPublicoVO.setMostrarGrupos(mostrarGrupos);
				    usuarioPublicoVO.setMostrarObjetos(mostrarObjetos);
				    usuarioPublicoVO.setRecibirCorreo(recibirCorreoItinerarios);//Itinerarios
				    usuarioPublicoVO.setUsuario(usuario);
				    usuarioPublicoVO.setNombreUsuario(nombreUsuario);
				   //La descripcion debe ser menor que 2000
					String desc="";
					   if(descripcion.length()>1995){
						   desc=descripcion.substring(0, 1995);
					   }else{
						   desc=descripcion;
					   }
				    usuarioPublicoVO.setDescripcion(desc);
				    resultadoModificacion=this.getSrvPerfilPublico().modificarUsuarioPublico(usuarioPublicoVO, usuario);
				    logger.info("El resultaod de la modificacion "+resultadoModificacion);
				    if(resultadoModificacion!=null && resultadoModificacion.equals(usuarioPublicoVO.getId())){
				    	resultado2="OK.MODIFICARUSUARIO";
				    }else{
				    	resultado2="FALLO.MODIFICARUSUARIO";
				    }
		    	}
		    }
		   
//		    Boolean teniaUsuarioPublico=Boolean.FALSE;
//		    if(usuarioPublicoViejo!=null && usuarioPublicoViejo.getUsuario()!=null){
//		    	teniaUsuarioPublico=Boolean.TRUE;
//		    }
		   
		   
//		    Long idPublico =form.getIdPublico();
		    

		    if(resultado1.equals("OK.MODIFICARUSUARIO") && resultado2.equals("OK.MODIFICARUSUARIO")){
		    	resultado="OK.MODIFICARUSUARIO";
		    }else{
		    	resultado="FALLO.MODIFICARUSUARIO";
		    }
		    // Fijamos la cookie de modificarPerfil
		    String cookiesPerfil = this.getPropertyValue("cookieModificarPerfil");
		    Long tiempo = new Long(System.currentTimeMillis());
		    Cookie cookie = new Cookie(cookiesPerfil, tiempo.toString());
		    cookie.setPath("/");
		    response.addCookie(cookie);
		    if(logger.isDebugEnabled())logger.debug("se ha creado la cookie MODIFICAR_PERFIL " + tiempo);
		} catch (ValidatorException ve) {
		    logger.error("se ha producido una validatorException");
		    resultado = "FALLO.MODIFICARUSUARIO";
		    throw ve;
		} catch (Exception e) {
		    logger.error(e);
		    resultado = "FALLO.MODIFICARUSUARIO";
		    throw new ValidatorException("{modificarUsuario.error}");
	
		} finally {
	
		    // /Elimino de la sesion la descripcion del grupo
			
		    form.setResultadoModificacion(resultado);
		}

    }


    private String getPropertyValue(String sKey) throws IOException {
    	
		InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/spring_visualizadorcontenidos.properties");
		if (this.pSpringProperties == null) {
		    pSpringProperties = new java.util.Properties();
		    pSpringProperties.load(fIsSpringProperties);
		}

		// devolvemos la propiedad
		return pSpringProperties.getProperty(sKey);
    }
    
    private boolean esImagen(String extension){
		boolean resultado = false;
		AgregaProperties agregaProperties = AgregaPropertiesImpl.getInstance();
		String extensionValidas = agregaProperties.getProperty(AgregaProperties.IMAGENES_NODOS_SQI_EXT);
		resultado = extensionValidas.contains(extension.toLowerCase());
		return resultado;
	}
}