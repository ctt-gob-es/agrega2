// license-header java merge-point
package es.pode.administracion.presentacion.adminusuarios.altaUsuarioPendiente;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.GrupoVO;
import es.pode.adminusuarios.negocio.servicios.SrvAdminUsuariosService;
import es.pode.adminusuarios.negocio.servicios.UsuarioVO;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.i18n.LocalizacionIdiomaVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.administracion.presentacion.adminusuarios.altaUsuarioPendiente.AltaUsuarioPendienteController
 */
public class AltaUsuarioPendienteControllerImpl extends AltaUsuarioPendienteController
{
	private static final long serialVersionUID = 6727025743287009241L;
	private static Logger logger = Logger.getLogger(AltaUsuarioPendienteControllerImpl.class);




    /**
     * @see es.pode.administracion.presentacion.adminusuarios.altaUsuarioPendiente.AltaUsuarioPendienteController#recuperarGrupos(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.adminusuarios.altaUsuarioPendiente.RecuperarGruposForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void recuperarGrupos(ActionMapping mapping, es.pode.administracion.presentacion.adminusuarios.altaUsuarioPendiente.RecuperarGruposForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
    	
    	try {
    	    
    	    String nombre = request.getParameter("nombre");
    	    String apellido1 = request.getParameter("apellido1");
    	    Long id = Long.valueOf(request.getParameter("id"));
    	    String apellido2 = request.getParameter("apellido2");
    	    String email = request.getParameter("email");
    	    String idioma = request.getParameter("idioma");
    	    String idiomaBusqueda = request.getParameter("idiomaBusqueda");
    	    String tipoEmpaquetador = request.getParameter("tipoEmpaquetador");
    	    String clave = request.getParameter("clave");
    	    String repitaClave = request.getParameter("repitaClave");
    	    UsuarioVO usuarioVO = this.getSrvAdminUsuariosService().descripcionUsuario(id);
    	    // Validaciones de los campos que recogemos del formulario
    	    Pattern mask = Pattern.compile("[^\\¿\\?\\¡\\!\\>\\#\\&\\<\\@\\$\\/\\\'\\\"]+");
    	    // Validaciones para password
    	    Pattern maskPwd = Pattern.compile("[^Ññ\\¿\\?\\¡\\!\\>\\#\\&\\<\\@\\$\\/\\\'\\\"]+");

    	    Matcher matcher = null;

    	    if (nombre.length() <= 0) {
    		if(logger.isDebugEnabled())logger.debug("nombre.length() <= 0");
    		throw new ValidatorException("{errors.altaUsuarioPendiente.nombre}");
    	    }
    	    matcher = mask.matcher(nombre);
    	    if (!matcher.matches()) {
    		if(logger.isDebugEnabled())logger.debug("nombre caracter ilegal");
    		throw new ValidatorException("{errors.altaUsuarioPendiente.nombre.caracterIlegal}");
    	    }

    	    if (apellido1.length() <= 0) {
    		if(logger.isDebugEnabled())logger.debug("apellido1.length() <= 0");
    		throw new ValidatorException("{errors.altaUsuarioPendiente.apellido1}");
    	    }
    	    matcher = mask.matcher(apellido1);
    	    if (!matcher.matches()) {
    		if(logger.isDebugEnabled())logger.debug("apellido1 caracter ilegal");
    		throw new ValidatorException("{errors.altaUsuarioPendiente.apellido1.caracterIlegal}");
    	    }

    	    if(apellido2.length() > 0)
    	    {
    		    matcher = mask.matcher(apellido2);
    		    if (!matcher.matches()) {
    			if(logger.isDebugEnabled())logger.debug("apellido2 caracter ilegal");
    			throw new ValidatorException("{errors.altaUsuarioPendiente.apellido2.caracterIlegal}");
    		    }
    	    }

    	    if (email.length() <= 0) {
    		if(logger.isDebugEnabled())logger.debug("email.length() <= 0");
    		throw new ValidatorException("{errors.altaUsuarioPendiente.email}");
    	    }
    	    if (!((email.indexOf("@") > 0) && (email.indexOf(".") > 0))) {
    		throw new ValidatorException("{errors.altaUsuarioPendiente.emailIncorrecto}");
    	    }
    	    if ((clave == null) || (clave.equalsIgnoreCase(""))) {
    		if(logger.isDebugEnabled())logger.debug("La clave no se modifica");
    	    } else {
	    		if (clave.length() < 7) {
	    		    if(logger.isDebugEnabled())logger.debug("clave .length() <= 7");
	    		    throw new ValidatorException("{errors.altaUsuarioPendiente.clave}");
	    		}
    		
			    matcher = maskPwd.matcher(clave);
			    if (!matcher.matches()) {
			    	if(logger.isDebugEnabled())logger.debug("contraseña caracter ilegal");
			    	throw new ValidatorException(
								"{errors.altaUsuarioPendiente.clave.caracterIlegal}");
			    }

	    		if (!(clave.equalsIgnoreCase(repitaClave))) {
	    		    throw new ValidatorException("{errors.altaUsuarioPendiente.claveDistintas}");
	    		}
	    		usuarioVO.setClave(clave);
    	    }

    	    if (idiomaBusqueda == null) {
    		throw new ValidatorException("{errors.altaUsuarioPendiente.idiomaBusqueda}");
    	    }
    	    if (idiomaBusqueda.length() <= 0) {
    		if(logger.isDebugEnabled())logger.debug("empaquetador .length() <= 0");
    		throw new ValidatorException("{errors.altaUsuarioPendiente.idiomaBusqueda}");
    	    }

    	    if (idioma == null) {
    		throw new ValidatorException("{errors.altaUsuarioPendiente.idioma}");
    	    }
    	    if (idioma.length() <= 0) {
    		if(logger.isDebugEnabled())logger.debug("empaquetador .length() <= 0");
    		throw new ValidatorException("{errors.altaUsuarioPendiente.idioma}");
    	    }

    	    if (tipoEmpaquetador == null) {
    		throw new ValidatorException("{errors.altaUsuarioPendiente.tipoEmpaquetador}");
    	    }
    	    if (tipoEmpaquetador.length() <= 0) {
    		if(logger.isDebugEnabled())logger.debug("empaquetador .length() <= 0");
    		throw new ValidatorException("{errors.altaUsuarioPendiente.tipoEmpaquetador}");
    	    }

    	    usuarioVO.setNombre(nombre);
    	    usuarioVO.setApellido1(apellido1);
    	    usuarioVO.setApellido2(apellido2);
    	    usuarioVO.setEmail(email);
    	    usuarioVO.setTipoEmpaquetador(tipoEmpaquetador);
    	    usuarioVO.setIdioma(idioma);
    	    usuarioVO.setIdiomaBusqueda(idiomaBusqueda);
    	    usuarioVO.setId(id);
		    usuarioVO.setFechaAlta(new GregorianCalendar());
		    usuarioVO.setFechaSolicitudAlta(null);

    	    this.getAltaUsuarioPendienteBSession(request).setUsuarioPendiente(usuarioVO);
    	    GrupoVO[] grupoVO = this.getSrvAdminUsuariosService().listarGrupos();
    	    form.setGruposAsArray(grupoVO);
    	} catch (ValidatorException ve) {
    	    logger.error("se ha producido una validatorException");
    	    throw ve;
    	} catch (Exception e) {
    	    logger.error("Error: " + e);
    	    throw new ValidatorException("{altaUsuarioPendiente.error}");
    	}
     }







    /**
     * @see es.pode.administracion.presentacion.adminusuarios.altaUsuarioPendiente.AltaUsuarioPendienteController#altaUsuarioPendiente(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.adminusuarios.altaUsuarioPendiente.AltaUsuarioPendienteForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String altaUsuarioPendiente(ActionMapping mapping, es.pode.administracion.presentacion.adminusuarios.altaUsuarioPendiente.AltaUsuarioPendienteForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String resultado = "";
    	Long idCreado = null;
    	try {
    	    AltaUsuarioPendienteBSession altaUsuarioBSession = this.getAltaUsuarioPendienteBSession(request);
    	    UsuarioVO usuarioSesion = altaUsuarioBSession.getUsuarioPendiente();
    	    SrvAdminUsuariosService srvAdminUsuariosService = this.getSrvAdminUsuariosService();

    	    if(logger.isDebugEnabled())logger.debug("Elimino de la sesion la descripcion del grupo");
    	    if(logger.isDebugEnabled())logger.debug("Obtenemos el administrador que va a dar de alta al usuario pendiente");
    	    String usuario = LdapUserDetailsUtils.getUsuario();

    	    UsuarioVO adminVO = srvAdminUsuariosService.obtenerDatosUsuario(usuario);
    	    if(logger.isDebugEnabled())logger.debug("el administrador es " + adminVO.getId());
    	    idCreado = srvAdminUsuariosService.altaUsuario(usuarioSesion);
    	    resultado = idCreado!=null?"OK":"FALLO";
    	    this.removeAltaUsuarioPendienteBSession(request);
    	   
    	} catch (Exception e) {
    	    logger.error("Error al dar de alta un usuario pendiente ", e);
    	    resultado = "FALLO";

    	} finally {

    	    form.setResultado(resultado);

    	}
    	return resultado;
    }


//    private ResourceBundle getFicherRecursos(Locale locale) {
//    	ResourceBundle ficheroRecursos = null;
//    	ficheroRecursos = ResourceBundle.getBundle("application-resources", locale);
//    	return ficheroRecursos;
//        }




    /**
     * @see es.pode.administracion.presentacion.adminusuarios.altaUsuarioPendiente.AltaUsuarioPendienteController#submit(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.adminusuarios.altaUsuarioPendiente.SubmitForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void submit(ActionMapping mapping, es.pode.administracion.presentacion.adminusuarios.altaUsuarioPendiente.SubmitForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try {
    	    AltaUsuarioPendienteBSession altaUsuarioBSession = this.getAltaUsuarioPendienteBSession(request);
    	    UsuarioVO usuarioSesion = altaUsuarioBSession.getUsuarioPendiente();
    	    String[] ids = form.getIdRowSelectionAsArray();
    	    SrvAdminUsuariosService srvAdminUsuariosService = this.getSrvAdminUsuariosService();

    	    if (ids == null) {
    		if(logger.isDebugEnabled())logger.debug("No se ha seleccionado ningun grupo");
    		 throw new ValidatorException("{errors.altaUsuarioPendiente.gruposVacio}");
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
    	    this.getAltaUsuarioPendienteBSession(request).setUsuarioPendiente(usuarioSesion);
    	} catch(ValidatorException ve)
    	{
    	   logger.error("Se produce un error ValidatorException "+ve); 
    	   throw ve;
    	} catch (Exception e) {
    	    logger.error("Error al obtener los grupos seleccionados ", e);
    	}     }







	public void recuperarUsuario(ActionMapping mapping, RecuperarUsuarioForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		try {

		    String idiomaSelected = LdapUserDetailsUtils.getIdioma();
		    I18n i18n = I18n.getInstance();
			LocalizacionIdiomaVO[] localizadorNavegacionIdioma = i18n.obtenerIdiomasPlataformaLocalizados(idiomaSelected);
			form.setIdiomaBackingList(Arrays.asList(localizadorNavegacionIdioma), "idLocalizacion", "nombre");
			LocalizacionIdiomaVO[] localizadorBusquedaIdioma = i18n.obtenerIdiomasBuscablesLocalizados(idiomaSelected);
			form.setIdiomaBusquedaBackingList(Arrays.asList(localizadorBusquedaIdioma), "idLocalizacion", "nombre");
			Long id = Long.valueOf(request.getParameter("id"));
		    if (id == null) {
		    	throw new ValidatorException("{errors.altaUsuarioPendiente.idNulo}");
		    }
		    
			UsuarioVO usuario = this.getSrvAdminUsuariosService().descripcionUsuario(id);
			form.setTipoEmpaquetador(usuario.getTipoEmpaquetador());
			form.setApellido1(usuario.getApellido1());
			form.setNombre(usuario.getNombre());
			form.setUsuario(usuario.getUsuario());
			form.setId(usuario.getId());
			form.setEmail(usuario.getEmail());
			form.setApellido2(usuario.getApellido2());
			form.setIdioma(usuario.getIdioma());
			form.setIdiomaBusqueda(usuario.getIdiomaBusqueda());
			form.setNif(usuario.getNIF());
			// form.setRepitaClave(usuario.getClave());
		} catch (ValidatorException va) {
		    throw va;
		} catch (Exception e) {
		    logger.error("Error!!: " + e);
		    throw new ValidatorException("{altaUsuarioPendiente.error}");
		}

	}

}