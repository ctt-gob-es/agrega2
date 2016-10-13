// license-header java merge-point
package es.pode.modificador.presentacion.importar;

import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimePartDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import es.pode.modificador.negocio.servicio.ConfiguracionTarea;
import es.pode.modificador.presentacion.idiomasBuscador.IdiomasBuscadorSingleton;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.modificador.presentacion.importar.ImportarModificacionController
 */
public class ImportarModificacionControllerImpl extends ImportarModificacionController
{
	private static final Logger logger = Logger.getLogger(ImportarModificacionControllerImpl.class);
	
    /**
     * @see es.pode.modificador.presentacion.importar.ImportarModificacionController#importar(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.importar.ImportarForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void importar(ActionMapping mapping, es.pode.modificador.presentacion.importar.ImportarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	FormFile xml = form.getFichero(); 
    	if(xml==null || xml.getFileSize()==0) {
    		throw new ValidatorException("{importarModificacion.error}");
    	}
//    	convierto el FormFile en un DataHandler
        InternetHeaders ih = new InternetHeaders();
		MimeBodyPart mbp = null;
		DataSource source = null;
		DataHandler dFichero = null;
		mbp = new MimeBodyPart(ih, xml.getFileData());
		source = new MimePartDataSource(mbp);
		dFichero = new DataHandler(source);
		
		// Llamo al servicio para parsear el fichero XML
		ConfiguracionTarea configuracion;
		try {
			configuracion = this.getSrvHerramientaModificacion()
					.importarModificacion(dFichero);
		} catch (Exception e) {
			throw new ValidatorException("{importarModificacion.error}");
		}		
		
		// Creo el objeto de sesion para editar la tarea
		this.getConfigurarModificacionSession(request).setConfiguracion(configuracion);
    }

	public String selectAction(ActionMapping mapping, SelectActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String result = null;
		String ACEPTAR = "Aceptar";
		String action = form.getAction();
		if(logger.isDebugEnabled()) logger.debug("Action = " + action);
//		MessageResources resources = MessageResources.getMessageResources("application-resources");
		java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);

		if(action.equals(i18n.getString("comun.aceptar"))) {
			result=ACEPTAR;
		} else {
			result = "Cancelar";
		}
		
		return result;
	}

	public void recuperarFormulario(ActionMapping mapping, RecuperarFormularioForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		form.setIdiomaBuscadorBackingList(IdiomasBuscadorSingleton.getInstance().obtenerIdiomas(LdapUserDetailsUtils.getIdioma()), "idLocalizacion", "nombre");		
	}

}