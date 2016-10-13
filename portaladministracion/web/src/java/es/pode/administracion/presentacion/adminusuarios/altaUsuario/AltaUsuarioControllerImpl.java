// license-header java merge-point
package es.pode.administracion.presentacion.adminusuarios.altaUsuario;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;

import es.pode.adminusuarios.negocio.servicios.GrupoTrabajoVO;
import es.pode.adminusuarios.negocio.servicios.GrupoVO;
import es.pode.adminusuarios.negocio.servicios.RolVO;
import es.pode.adminusuarios.negocio.servicios.SrvAdminUsuariosService;
import es.pode.adminusuarios.negocio.servicios.UsuarioVO;
import es.pode.configuracionPlataforma.negocio.servicios.SrvPropiedadService;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.i18n.LocalizacionIdiomaVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

/**
 * @see es.pode.administracion.presentacion.adminusuarios.altaUsuario.AltaUsuarioController
 */
public class AltaUsuarioControllerImpl extends AltaUsuarioController {

	private static final long serialVersionUID = -3586068379593682161L;
	private static Logger logger = Logger.getLogger(AltaUsuarioControllerImpl.class);
    private SrvPropiedadService prop = null;
    
    /**
         * @see es.pode.administracion.presentacion.adminusuarios.altaUsuario.AltaUsuarioController#recuperarGrupos(org.apache.struts.action.ActionMapping,
         *      es.pode.administracion.presentacion.adminusuarios.altaUsuario.RecuperarGruposForm,
         *      javax.servlet.http.HttpServletRequest,
         *      javax.servlet.http.HttpServletResponse)
         */
    public final void recuperarGrupos(ActionMapping mapping,
	    es.pode.administracion.presentacion.adminusuarios.altaUsuario.RecuperarGruposForm form,
	    HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
	      	 
		String idiomaSelected = LdapUserDetailsUtils.getIdioma();
		I18n i18n = I18n.getInstance();
		LocalizacionIdiomaVO[] localizadorNavegacionIdioma = i18n.obtenerIdiomasPlataformaLocalizados(idiomaSelected);
		form.setIdiomaBackingList(Arrays.asList(localizadorNavegacionIdioma), "idLocalizacion", "nombre");
		LocalizacionIdiomaVO[] localizadorBusquedaIdioma = i18n.obtenerIdiomasBuscablesLocalizados(idiomaSelected);
		form.setIdiomaBusquedaBackingList(Arrays.asList(localizadorBusquedaIdioma), "idLocalizacion", "nombre");
		
		String usuario = form.getUsuario();
		LdapUserDetailsUtils.esAdministrador();
		try {
		    // VALIDACIONES DE LOS CAMPOS DEL FORMULARIO
		    String nombre = form.getNombre();
		    String apellido1 = form.getApellido1();
		    String apellido2 = form.getApellido2();
	
		    Pattern mask = Pattern.compile("[^\\�\\?\\�\\!\\>\\#\\&\\<\\@\\$\\/\\\'\\\"]+");
    	    // Validaciones para password
    	    Pattern maskPwd = Pattern.compile("[^��\\�\\?\\�\\!\\>\\#\\&\\<\\@\\$\\/\\\'\\\"]+");

		    Matcher matcher = null;
	
		    if (nombre.length() <= 0) {
				if(logger.isDebugEnabled())logger.debug("nombre.length() <= 0");
				throw new ValidatorException("{errors.altausuario.nombre}");
		    }
		    matcher = mask.matcher(nombre);
		    if (!matcher.matches()) {
		    	if(logger.isDebugEnabled())logger.debug("nombre caracter ilegal");
	
		    	throw new ValidatorException("{errors.altausuario.nombre.caracterIlegal}");
		    }
	
		    if (apellido1.length() <= 0) {
		    	if(logger.isDebugEnabled())logger.debug("apellido1.length() <= 0");
	
		    	throw new ValidatorException("{errors.altausuario.apellido1}");
		    }
		    matcher = mask.matcher(apellido1);
		    if (!matcher.matches()) {
		    	if(logger.isDebugEnabled())logger.debug("apellido1 caracter ilegal");
		    	throw new ValidatorException("{errors.altausuario.apellido1.caracterIlegal}");
		    }
		    
		    if(apellido2.length() > 0)
		    {
			    matcher = mask.matcher(apellido2);
			    if (!matcher.matches()) {
			    	if(logger.isDebugEnabled())logger.debug("apellido2 caracter ilegal");
			    	throw new ValidatorException("{errors.altausuario.apellido2.caracterIlegal}");
			    }
		    }
	
		    String nif = form.getNif();
		    if (nif.length() <= 0) {
		    	if(logger.isDebugEnabled())logger.debug("nif.length() <= 0");
		    	throw new ValidatorException("{errors.altausuario.nif}");
		    }
		    
		    Boolean recibirCorreo = form.getRecibirCorreo();
		    if (recibirCorreo != null) {
		    	recibirCorreo=Boolean.TRUE;
		    }else{
		    	recibirCorreo=Boolean.FALSE;
		    }
		    /*
	                 * if (!(validarNif(nif)).booleanValue()) { throw new
	                 * ValidatorException("{errors.altausuario.nifErroneo}"); }
	                 */
	
		    String email = form.getEmail();
	
		    if (email.length() <= 0) {
		    	if(logger.isDebugEnabled())logger.debug("email.length() <= 0");
		    	throw new ValidatorException("{errors.altausuario.email}");
		    }
		    if (!((email.indexOf("@") > 0) && (email.indexOf(".") > 0)) || (email.indexOf(" ") > 0)){
			throw new ValidatorException("{errors.altausuario.emailIncorrecto}");
		    }
	       // Mas validaciones del campo email
		    if(validaEmail(email) == Boolean.FALSE)
		    {
		    	if(logger.isDebugEnabled())logger.debug("el email es erroneo "+email);
		    	throw new ValidatorException("{errors.altausuario.emailIncorrecto}");
		    }
		    if (usuario.length() <= 0) {
		    	if(logger.isDebugEnabled())logger.debug("usuario length() <= 0");
		    	throw new ValidatorException("{errors.altausuario.usuario}");
		    }
//		    Pattern maskLogin = Pattern.compile("[^\\�\\?\\�\\!\\>\\#\\&\\<\\@\\$\\/\\\'\\\"\\�\\�\\�\\�\\�\\�\\�\\�\\�\\�]+");
		    /*
		     * 2010-02-17 dgonzalezd: Elimino @ de los caracteres prohibidos para permitir usar email como login
		     */
		    Pattern maskLogin = Pattern.compile("[^\\�\\?\\�\\!\\>\\#\\&\\<\\$\\/\\\'\\\"\\�\\�\\�\\�\\�\\�\\�\\�\\�\\�]+");
		    matcher = maskLogin.matcher(usuario);
		    if (!matcher.matches()) {
		    	if(logger.isDebugEnabled())logger.debug("nombre caracter ilegal");
		    	throw new ValidatorException(
							"{errors.altausuario.usuario.caracterIlegal}");
		    }
		    //el usuario debe ir en min�sculas - COMENTADO
		    /*
		    if (usuario.compareTo(usuario.toLowerCase())!=0){
		    	log("el usuario debe estar en min�sculas");
		    	throw new ValidatorException("{errors.altausuario.usuario.minusculas}");
		    }
		    */
		    
	
		    String clave = form.getClave();
		    if (clave.length() < 7) {
		    	if(logger.isDebugEnabled())logger.debug("clave .length() <= 7");
		    	throw new ValidatorException("{errors.altausuario.clave}");
		    }
		    matcher = maskPwd.matcher(clave);
		    if (!matcher.matches()) {
		    	if(logger.isDebugEnabled())logger.debug("contrase�a caracter ilegal");
		    	throw new ValidatorException(
							"{errors.altausuario.clave.caracterIlegal}");
		    }
		    
		    String repitaClave = form.getRepitaClave();
		    if (!(clave.equalsIgnoreCase(repitaClave))) {
			throw new ValidatorException("{errors.altausuario.claveDistintas}");
		    }
		    
		    String openIdUrl=form.getOpenIdUrl();
		    if(openIdUrl!=null && openIdUrl.length()>0){
		    	UsuarioVO usuariovo=this.getSrvAdminUsuariosService().obtenerUsuarioConOpenId(openIdUrl);
		    	if(usuariovo!=null){
		    		if(logger.isDebugEnabled())logger.debug("Ese identificador de openId ya existe en la base de datos");
			    	throw new ValidatorException("{errors.altausuario.existe.openId}");
		    	}
		    	
		    }
		    String idiomaBusqueda = form.getIdiomaBusqueda();
	
		    if (idiomaBusqueda == null) {
			throw new ValidatorException("{errors.altausuario.idiomaBusqueda}");
		    }
		    if (idiomaBusqueda.length() <= 0) {
		    	if(logger.isDebugEnabled())logger.debug("empaquetador .length() <= 0");
		    	throw new ValidatorException("{errors.altausuario.idiomaBusqueda}");
		    }
	
		    String idioma = form.getIdioma();
		    if (idioma == null) {
			throw new ValidatorException("{errors.altausuario.idioma}");
		    }
		    if (idioma.length() <= 0) {
		    	if(logger.isDebugEnabled())logger.debug("empaquetador .length() <= 0");
		    	throw new ValidatorException("{errors.altausuario.idioma}");
		    }
	
		    String tipoEmpaquetador = form.getTipoEmpaquetador();
		    if (tipoEmpaquetador == null) {
			throw new ValidatorException("{errors.altausuario.tipoEmpaquetador}");
		    }
		    if (tipoEmpaquetador.length() <= 0) {
		    	if(logger.isDebugEnabled())logger.debug("empaquetador .length() <= 0");
		    	throw new ValidatorException("{errors.altausuario.tipoEmpaquetador}");
		    }
		    
		    String tipoCatalogador = form.getTipoCatalogador();
		    if (tipoCatalogador == null) {
			throw new ValidatorException("{errors.altausuario.tipoCatalogador}");
		    }
		    if (tipoCatalogador.length() <= 0) {
		    	if(logger.isDebugEnabled())logger.debug("catalogador.length() <= 0");
		    	throw new ValidatorException("{errors.altausuario.tipoCatalogador}");
		    }

		    String tipoVisualizador= form.getTipoVisualizador();
		    if (tipoVisualizador == null) {
		    	throw new ValidatorException("{errors.altausuario.tipoVisualizador}");
		    }
		    if (tipoVisualizador.length() <= 0) {
		    	if(logger.isDebugEnabled())logger.debug("tipoVisualizador.length() <= 0");
		    	throw new ValidatorException("{errors.altausuario.tipoVisualizador}");
		    }

		    
		    long cuota = 0;
		    if (form.getCuota().length() > 0) 
		    {
				
				//comprobamos que la cuota son unicamente numeros
				Pattern maskNum = Pattern.compile("[0-9]+");
				matcher = maskNum.matcher(form.getCuota());
			    if (!matcher.matches()) 
			    {
			    	if(logger.isDebugEnabled())logger.debug("cuota caracter ilegal");
			    	throw new ValidatorException("{errors.altausuario.cuota.caracterIlegal}");
			    }
				
				//pasamos de mbytes a bytes la cuota
				cuota = Long.valueOf(form.getCuota()).longValue() * 1024 * 1024;
		    }else{//Por defecto,si no introducen nig�n valor le podremos 5 Mb
		    	cuota =Long.valueOf(ObtieneSrvPropiedad().getValorPropiedad(AgregaProperties.VALOR_CUOTA_DEFECTO)).longValue() * 1024 * 1024 ;
		    }
	
		    // Una vez recojo todos los datos del formulario compruebo si el
		    // usuario(NIF) ya esta dado de alta si lo esta y no tiene fecha
	                // de
		    // baja
		    // saco un mensaje de error.
		    UsuarioVO usuarioExistente = this.getSrvAdminUsuariosService().obtenerUsuario(nif.toUpperCase());
	
		    if (usuarioExistente == null) {
		    	if(logger.isDebugEnabled())logger.debug("No existe en BD otro usuario con ese mismo nif");
	
			// Chequeamos si el usuario introducido ya esta en la BD en ese
			// caso
			// sacamos un mensaje de error
			Boolean existeUsuario = this.getSrvAdminUsuariosService().existeUsuario(usuario);
			if (existeUsuario) {
			    logger.error("El usuario introducido en el formulario ya se encuentra registrado");
			    throw new ValidatorException("{errors.altausuario.usuarioExistente}");
			}
			if(logger.isDebugEnabled())logger.debug("El usuario introducido en el formulario no se encuentra en la BD");
			UsuarioVO usuarioVO = new UsuarioVO();
			usuarioVO.setApellido1(apellido1);
			usuarioVO.setApellido2(apellido2);
			usuarioVO.setClave(clave);
			usuarioVO.setEmail(email);
			usuarioVO.setIdiomaBusqueda(idiomaBusqueda);
			usuarioVO.setNIF(nif.toUpperCase());
			usuarioVO.setNombre(nombre);
			usuarioVO.setUsuario(usuario);
			usuarioVO.setTipoEmpaquetador(tipoEmpaquetador);
			usuarioVO.setFechaAlta(new GregorianCalendar());
			usuarioVO.setFechaSolicitudAlta(null);
			usuarioVO.setIdioma(idioma);
			usuarioVO.setCuota(cuota);
			usuarioVO.setTipoCatalogador(tipoCatalogador);
			usuarioVO.setTipoVisualizador(tipoVisualizador);
			usuarioVO.setOpenIdUrl(openIdUrl);
			usuarioVO.setRecibirCorreoPublicacion(recibirCorreo);
			AltaUsuarioBSession altaUsuarioBSesion = this.getAltaUsuarioBSession(request);
			if(logger.isDebugEnabled())logger.debug("altaUsuarioBSesion "+altaUsuarioBSesion);
			altaUsuarioBSesion.setUsuario(usuarioVO);
	
		    } else {
			if (usuarioExistente.getFechaBaja() == null) {
				if(logger.isDebugEnabled())logger.debug("Saco un mensaje de error ya existe un usuario con ese nif");
			    throw new ValidatorException("{errors.altausuario.nifExistente}");
			}
			// Se mantendr� la fecha de alta y el usuario, se
			            // modificara
			// la clave en el servidor ldap
			if(logger.isDebugEnabled())logger.debug("Voy a hacer una modificacion mantengo el usuario, la fecha de alta y el nif");
			usuarioExistente.setApellido1(apellido1);
			usuarioExistente.setApellido2(apellido2);
			usuarioExistente.setClave(clave);
			usuarioExistente.setEmail(email);
			usuarioExistente.setIdiomaBusqueda(idiomaBusqueda);
			usuarioExistente.setNombre(nombre);
			usuarioExistente.setTipoEmpaquetador(tipoEmpaquetador);
			usuarioExistente.setTipoVisualizador(tipoVisualizador);
			usuarioExistente.setIdioma(idioma);
			usuarioExistente.setOpenIdUrl(openIdUrl);
			usuarioExistente.setFechaBaja(null);
			usuarioExistente.setFechaSolicitudAlta(null);
			usuarioExistente.setRecibirCorreoPublicacion(recibirCorreo);
			if(logger.isDebugEnabled())logger.debug("Antes de guardar en sesion el usuarioVO ");
			this.getAltaUsuarioBSession(request).setUsuario(usuarioExistente);
			if(logger.isDebugEnabled())logger.debug("Se guarda en sesion el usuarioVO ");
		    }
		} catch (ValidatorException validator) {
		    logger.error("Se produce la siguiente excepcion ", validator);
		    throw validator;
		} catch (Exception e) {
		    logger.error("Se produce la siguiente excepcion ", e);
		    throw new ValidatorException("{errors.altausuario}");
		}
		try {
		    GrupoVO[] grupoVO = this.getSrvAdminUsuariosService().listarGrupos();
		    form.setGruposAsArray(grupoVO);
		} catch (Exception e) {
		    logger.error("Error al recoger los parametros del usuario y comprobar si esta registrado en el sistema " + e);
		    throw new ValidatorException("{errors.altausuario}");
		}
    }

    /**
         * @see es.pode.administracion.presentacion.adminusuarios.altaUsuario.AltaUsuarioController#altaUsuario(org.apache.struts.action.ActionMapping,
         *      es.pode.administracion.presentacion.adminusuarios.altaUsuario.AltaUsuarioForm,
         *      javax.servlet.http.HttpServletRequest,
         *      javax.servlet.http.HttpServletResponse)
         */
    public final java.lang.String altaUsuario(ActionMapping mapping,
	    es.pode.administracion.presentacion.adminusuarios.altaUsuario.AltaUsuarioForm form,
	    HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	
		String resultado = "";
		Long idCreado = null;
		try {
		    AltaUsuarioBSession altaUsuarioBSession = this.getAltaUsuarioBSession(request);
		    UsuarioVO usuarioSesion = altaUsuarioBSession.getUsuario();
		    SrvAdminUsuariosService srvAdminUsuariosService = this.getSrvAdminUsuariosService();
	
		    if(logger.isDebugEnabled())logger.debug("Elimino de la sesion la descripcion del grupo");
		    if(logger.isDebugEnabled())logger.debug("Obtenemos el administrador que va a dar de alta al usuario");
		    String usuario = LdapUserDetailsUtils.getUsuario();
	
		  //  UsuarioVO adminVO = srvAdminUsuariosService.obtenerDatosUsuario(usuario);
		   // if(log.isDebugEnabled())log.debug("el administrador es " + adminVO.getId());
		    idCreado = srvAdminUsuariosService.altaUsuario(usuarioSesion);
		    form.setIdModificado(idCreado);
		    resultado = idCreado!=null?"OK":"FALLO";
		    logger.debug("Resultado es "+resultado);
		    this.removeAltaUsuarioBSession(request);
		    
	
		} catch (Exception e) {
		    logger.error("Error al dar de alta un usuario ", e);
		    resultado = "FALLO";
	
		} finally {
	
		    form.setResultado(resultado);
	
		}
		return resultado;
    }

    /**
         * @see es.pode.administracion.presentacion.adminusuarios.altaUsuario.AltaUsuarioController#obtenerIdiomas(org.apache.struts.action.ActionMapping,
         *      es.pode.administracion.presentacion.adminusuarios.altaUsuario.ObtenerIdiomasForm,
         *      javax.servlet.http.HttpServletRequest,
         *      javax.servlet.http.HttpServletResponse)
         */
    public final void obtenerIdiomas(ActionMapping mapping,
	    es.pode.administracion.presentacion.adminusuarios.altaUsuario.ObtenerIdiomasForm form,
	    HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	String idiomaSelected = LdapUserDetailsUtils.getIdioma();
    	I18n i18n = I18n.getInstance();
		LocalizacionIdiomaVO[] localizadorNavegacionIdioma = i18n.obtenerIdiomasPlataformaLocalizados(idiomaSelected);
		form.setIdiomaBackingList(Arrays.asList(localizadorNavegacionIdioma), "idLocalizacion", "nombre");
		LocalizacionIdiomaVO[] localizadorBusquedaIdioma = i18n.obtenerIdiomasBuscablesLocalizados(idiomaSelected);
		form.setIdiomaBusquedaBackingList(Arrays.asList(localizadorBusquedaIdioma), "idLocalizacion", "nombre");
		
    }

    /**
         * @see es.pode.administracion.presentacion.adminusuarios.altaUsuario.AltaUsuarioController#submit(org.apache.struts.action.ActionMapping,
         *      es.pode.administracion.presentacion.adminusuarios.altaUsuario.SubmitForm,
         *      javax.servlet.http.HttpServletRequest,
         *      javax.servlet.http.HttpServletResponse)
         */
    public final void submit(ActionMapping mapping,
	    es.pode.administracion.presentacion.adminusuarios.altaUsuario.SubmitForm form, HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
		try {
		    AltaUsuarioBSession altaUsuarioBSession = this.getAltaUsuarioBSession(request);
		    UsuarioVO usuarioSesion = altaUsuarioBSession.getUsuario();
		    String[] ids = form.getIdRowSelectionAsArray();
		    SrvAdminUsuariosService srvAdminUsuariosService = this.getSrvAdminUsuariosService();
	
		    if (ids == null) {
		    	if(logger.isDebugEnabled())logger.debug("No se ha seleccionado ningun grupo");
			 throw new ValidatorException("{errors.altausuario.gruposVacio}");
			//usuarioSesion.setGrupos(null);
	
		    }
			GrupoVO[] grupoVO = new GrupoVO[ids.length];
			Long idL = null;
			for (int i = 0; i < ids.length; i++) {
			    idL = new Long(ids[i]);
	
			    GrupoVO grupoG = srvAdminUsuariosService.descripcionGrupo(idL);
			    grupoVO[i] = grupoG;
	
			}
			usuarioSesion.setGrupos(grupoVO);
		    this.getAltaUsuarioBSession(request).setUsuario(usuarioSesion);
		} catch(ValidatorException ve)
		{
		   logger.error("Se produce un error ValidatorException "+ve); 
		   throw ve;
		} catch (Exception e) {
		    logger.error("Error al obtener los grupos seleccionados ", e);
		}
    }

    /**
     *  Metodo que recoge los roles del usuario y mira si tiene el rol catalogador o empaquetador para mostrar la pantalla de Grupos de Trabajo
     *  o no mostrarla
     */
    public final java.lang.String rolParaGruposTrabajo(org.apache.struts.action.ActionMapping mapping, es.pode.administracion.presentacion.adminusuarios.altaUsuario.RolParaGruposTrabajoForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
    {
    	AltaUsuarioBSession altaUsuarioBSession = this.getAltaUsuarioBSession(request);
	    UsuarioVO usuarioSesion = altaUsuarioBSession.getUsuario();
	    String resultado = "FALLO";
	    boolean siRoles = true;
	    try
	    {
	    
		    //recogemos los grupos que se han asigando al usuario y vemos que roles contienen
		    GrupoVO[] grupos = usuarioSesion.getGrupos();
		    
		    siRoles = validarRolesDocenteYCatalogador(grupos);
		    
		    if(siRoles)
		    	resultado = "OK";
	    }
	    catch(Exception e)
	    {
	    	logger.error("Error al obtener los roles del usuario ", e);
	    	throw new ValidatorException("{errors.altausuario}");
	    }
	    
	    finally 
	    {
		    form.setResultado(resultado);
		}
	    
    	return resultado;
    }
    
    /** Metodo que asigna los grupos de trabajo seleccionados por el administrador al usuario
	 * @param form formulario con los datos del usuario que se quiere dar de alta
	 * @throws Exception
	 */
   
	public void asignarGruposTrabajo(ActionMapping mapping, AsignarGruposTrabajoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try {
		    AltaUsuarioBSession altaUsuarioBSession = this.getAltaUsuarioBSession(request);
		    UsuarioVO usuarioSesion = altaUsuarioBSession.getUsuario();
		    
		    //recogemos los grupos que se han asigando al usuario y vemos que roles contienen
		    GrupoVO[] grupos = usuarioSesion.getGrupos();
		    
		    //comprobamos si ha pasado por la pantalla de grupos de catalogadores o no para validarlos
		    if(validarRolesDocenteYCatalogador(grupos))
		    {
		    
			    
			    String[] ids = form.getIdentificadorRowSelectionAsArray();
			    if(logger.isDebugEnabled())logger.debug("Los identificadores seleccionados son los siguientes "+Arrays.toString(ids));
			    
			    SrvAdminUsuariosService srvAdminUsuariosService = this.getSrvAdminUsuariosService();
		
			    if (ids == null) 
			    {
			    	if(logger.isDebugEnabled())logger.debug("No se ha seleccionado ningun grupo");
					throw new ValidatorException("{errors.altausuario.gruposTrabajoVacio}");
			    }
				GrupoTrabajoVO[] grupoTrabajoVO = new GrupoTrabajoVO[ids.length];
				if(logger.isDebugEnabled())logger.debug("grupoTrabajoVO.length "+grupoTrabajoVO.length);
				Long idL = null;
				for (int i = 0; i < ids.length; i++) 
				{
				    idL = new Long(ids[i]);

				    GrupoTrabajoVO grupoG = srvAdminUsuariosService.descripcionGrupoTrabajo(idL);
				    grupoTrabajoVO[i] = grupoG;

				}
				usuarioSesion.setGrupoTrabajo(grupoTrabajoVO);
		    }
		    
		    //metemos en sesion el usuario
		    this.getAltaUsuarioBSession(request).setUsuario(usuarioSesion);
		    
		} 
		catch(ValidatorException ve)
		{
		   logger.error("Se produce un error ValidatorException "+ve); 
		   throw ve;
		} 
		catch (Exception e) {
		    logger.error("Error al obtener los grupos seleccionados ", e);
		}
		
	}

	/**
	 * Metodo que recupera todos los grupos de trabajo que existen en la plataforma
	 * @param form formulario con los datos del usuario que se quiere dar de alta
	 * @throws Exception
	 */
	
	public void recuperarGruposTrabajo(ActionMapping mapping, RecuperarGruposTrabajoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try {
		    GrupoTrabajoVO[] grupoTrabajoVO = this.getSrvAdminUsuariosService().listarGruposTrabajo();
		    form.setGruposTrabajoAsArray(grupoTrabajoVO);
		} catch (Exception e) {
		    logger.error("Error al obtener los grupos de trabajo " + e);
		    throw new ValidatorException("{errors.altausuario}");
		}
		
	}


//    private Boolean validarNif(String nif) {
//
//		Boolean resultado = Boolean.TRUE;
//		// el valor debe tener 9 posiciones, de las cuales las primeras deben
//		// ser d�gitos y la �ltima letra
//		try {
//		    int longitud = nif.length();
//		    String dni = nif.substring(0, longitud - 1);
//		    dni = dni.toUpperCase();
//		    String digitoControl = nif.substring(longitud - 1, longitud);
//		    digitoControl = digitoControl.toUpperCase();
//	
//		    if (digitoControl.matches("[A-Z]")) {
//	
//			String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
//			int posicion_modulo = Integer.parseInt(dni) % 23;
//	
//			String digitoControlCalculado = letras.substring(posicion_modulo, posicion_modulo + 1);
//	
//			if (!digitoControl.equalsIgnoreCase(digitoControlCalculado)) {
//			    resultado = Boolean.FALSE;
//			}
//	
//		    } else {
//			resultado = Boolean.FALSE;
//		    }
//		} catch (Exception e) {
//		    log.error("Error al validar el nif " + e);
//		    resultado = Boolean.FALSE;
//		}
//		return resultado;
//    }
//    
    private Boolean validaEmail(String email){
		Boolean resultado = Boolean.TRUE;
		if(logger.isDebugEnabled())logger.debug("VALIDAEMAIL");
		//Pattern pattern = Pattern.compile("[^A-Za-z0-9\\.\\@_\\-~#]+");
		Pattern pattern = Pattern.compile("[A-Za-z0-9.-_]([A-Za-z0-9.-_])*@[A-Za-z0-9]([A-Za-z0-9.-_])*.([A-Za-z0-9])*");
		if(logger.isDebugEnabled())logger.debug("pattern.toString() "+pattern.toString());
		Matcher matcher = pattern.matcher(email);
		if (matcher.find()) {
			if(logger.isDebugEnabled())logger.debug("El email coincide con el patron es correcto el campo email");
		} else {
			resultado = Boolean.FALSE;
		}
		return resultado;

    }

    
    /*
     * validamos si los roles que se pasan son Docente y Catalogador o Publicador
     */
    private boolean validarRolesDocenteYCatalogador(GrupoVO[] grupos) throws RemoteException, Exception
    {
    	boolean resultado = false;
    	
    	Vector<String> roles = new Vector<String>();
	    RolVO[] rolParcial = null;
//	    int h = 0;
	    
	    for (int i = 0; i < grupos.length; i++) 
	    {
	    	rolParcial = grupos[i].getRols();
	    	
	    	for (int j = 0; j < rolParcial.length; j++) 
	    	{
	    		roles.add(rolParcial[j].getDescripcion());
			}
		}
	    
	    String rol_catalogador=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ROL_CATALOGADOR);
	    String rol_empaquetador=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ROL_EMPAQUETADOR);
	    String rol_publicador=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ROL_PUBLICADOR);
	   
	    
	    if(logger.isDebugEnabled())logger.debug("recogemos los roles -> " + rol_catalogador + rol_empaquetador);
	    if(roles.indexOf(rol_catalogador) >= 0 || roles.indexOf(rol_empaquetador) >= 0 || roles.indexOf(rol_publicador) >= 0)
	    	resultado = true;
    	return resultado;
    }

    private SrvPropiedadService ObtieneSrvPropiedad() throws Exception {
    	if (this.prop==null)
    	{
    		prop = this.getSrvPropiedadService();
    	}
    	return prop;
    }

}