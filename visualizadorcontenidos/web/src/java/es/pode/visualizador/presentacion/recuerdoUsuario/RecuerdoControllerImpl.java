// license-header java merge-point
package es.pode.visualizador.presentacion.recuerdoUsuario;


import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;
import es.pode.adminusuarios.negocio.servicios.UsuarioVO;


/**
 * @see es.pode.visualizador.presentacion.recuerdoUsuario.RecuerdoController
 */
public class RecuerdoControllerImpl extends RecuerdoController {

	private static Logger log = Logger.getLogger(RecuerdoControllerImpl.class);

	/**
	 * @see es.pode.visualizador.presentacion.recuerdoUsuario.RecuerdoController#nuevaClave(org.apache.struts.action.ActionMapping,
	 *      es.pode.visualizador.presentacion.recuerdoUsuario.NuevaClaveForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public final void nuevaClave(ActionMapping mapping,
			es.pode.visualizador.presentacion.recuerdoUsuario.NuevaClaveForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
	  	
		String email = form.getEmail();
		ResourceBundle ficheroRecursos = null;
		// El idioma se escogera del perfil del usuario
		Locale locale = request.getLocale();
		ficheroRecursos = this.getFicherRecursos(locale);
		//String idiomaSelected = locale.getLanguage();
		// El idioma se escogera del que tiene configurador en el navegador
		if (email.length() <= 0) {
			throw new ValidatorException("{errors.recuerdoUsuario.emailVacio}");
		}
		String nif = (form.getNif()).toUpperCase(locale);
		if (nif.length() <= 0) {
			throw new ValidatorException("{errors.recuerdoUsuario.nifVacio}");
		}

		UsuarioVO usuarioVO = (this.getSrvAdminUsuariosService()).obtenerUsuario(nif);
		if (usuarioVO == null) {
			throw new ValidatorException("{errors.recuerdoUsuario.nifNoExistente}");
		}
		if (!(usuarioVO.getEmail().equalsIgnoreCase(email))) {
			throw new ValidatorException("{errors.recuerdoUsuario.emailDistinto}");
		}
		log("Obtengo la informacion del administrador que esta gestionando el alta de usuario");
		Boolean resultadoModificacion = this.getSrvAdminUsuariosService().nuevaClave(usuarioVO);

		if (resultadoModificacion == Boolean.FALSE) {
			form.setResultadoNuevaClave("<em class=\"incorrecto\">"+ficheroRecursos.getString("errors.recuerdoUsuario")+"</em>");
		} else {
			form.setResultadoNuevaClave("<em class=\"correcto\">"+ficheroRecursos.getString("recuerdoUsuario.ok")+"</em>");
		}
	}

	/**
	 * @see es.pode.visualizador.presentacion.recuerdoUsuario.RecuerdoController#obtenerIdiomas(org.apache.struts.action.ActionMapping,
	 *      es.pode.visualizador.presentacion.recuerdoUsuario.ObtenerIdiomasForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	
	private void log(Object obj) {
		if (log.isDebugEnabled())
			log.debug(obj);
	}

	private ResourceBundle getFicherRecursos(Locale locale) {
		ResourceBundle ficheroRecursos = null;
		ficheroRecursos = ResourceBundle.getBundle("application-resources", locale);
		return ficheroRecursos;
	}

}