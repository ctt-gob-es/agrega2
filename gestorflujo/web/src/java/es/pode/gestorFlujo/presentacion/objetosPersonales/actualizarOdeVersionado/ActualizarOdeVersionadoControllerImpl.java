// license-header java merge-point
package es.pode.gestorFlujo.presentacion.objetosPersonales.actualizarOdeVersionado;

import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import es.pode.gestorFlujo.presentacion.objetosPersonales.importar.ImportarController;
import es.pode.publicacion.negocio.servicios.ResultadoOperacionVO;
import es.pode.publicacion.negocio.servicios.SrvPublicacionService;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimePartDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @see es.pode.gestorFlujo.presentacion.objetosPersonales.actualizarOdeVersionado.ActualizarOdeVersionadoController
 */
public class ActualizarOdeVersionadoControllerImpl extends ActualizarOdeVersionadoController
{


	private Logger logger = Logger.getLogger(ActualizarOdeVersionadoController.class);




    /**
     * @see es.pode.gestorFlujo.presentacion.objetosPersonales.actualizarOdeVersionado.ActualizarOdeVersionadoController#cargarFormularioActualizacion(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPersonales.actualizarOdeVersionado.CargarFormularioActualizacionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cargarFormularioActualizacion(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosPersonales.actualizarOdeVersionado.CargarFormularioActualizacionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		logger.debug("LUIS Formularo importar cargado correctamente");
		logger.debug("LUIS espacio libre: " + form.getEspacioLibre());
		logger.debug("LUIS ID ODE: " + form.getIdODE());
    }


    /**
     * @see es.pode.gestorFlujo.presentacion.objetosPersonales.actualizarOdeVersionado.ActualizarOdeVersionadoController#selectAction(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPersonales.actualizarOdeVersionado.SelectActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String selectAction(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosPersonales.actualizarOdeVersionado.SelectActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources", locale);
		String vuelta="Cancelar";
		if(form.getAction().equals(i18n.getString("gestorFlujo.aceptar")))
			vuelta= "Aceptar";
		return vuelta;
    }


    /**
     * @see es.pode.gestorFlujo.presentacion.objetosPersonales.actualizarOdeVersionado.ActualizarOdeVersionadoController#actualizarODE(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPersonales.actualizarOdeVersionado.ActualizarODEForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void actualizarODE(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosPersonales.actualizarOdeVersionado.ActualizarODEForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // this property receives a default value, just to have the application running on dummy data

		if (logger.isDebugEnabled())
			logger.error("Vamos a actualizar el ODE : " + form.getIdODE());
		
		Locale idio=(Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		String idiomaLdap=idio.getLanguage();

		// preparamos el formfile que hemos recibido y lo metemos con nuestra astuta técnica  de formfiles

		InternetHeaders ih = new InternetHeaders();
		MimeBodyPart mbp = new MimeBodyPart(ih, form.getFicheroODE().getFileData());

		DataSource dsource = new MimePartDataSource(mbp);
		DataHandler dFichero = new DataHandler(dsource);

		ResultadoOperacionVO res = null;
		try {			
			res = getSrvPublicacionService().actualizarVersionODE(dFichero, LdapUserDetailsUtils.getUsuario(),"Actualizando a nueva versión de ODE",
					 form.getFicheroODE().getFileName(), idiomaLdap,form.getIdODE());		
		} catch (Exception ex) {			
			logger.error("Excepcion al importar el ode: " + form.getFicheroODE().getFileName(), ex);
			form.setResultadoCorrecto(false);
			form.setMensaje(res.getDescripcion());
		}
		
		if (res.getIdResultado().equals("0.0")) {
			form.setResultadoCorrecto(true);
		} else if(res.getIdResultado().equals("0.1")) {
			form.setResultadoCorrecto(true);
		} else {			
			form.setResultadoCorrecto(false);
			form.setMensaje(res.getDescripcion());
		}
		
		if (logger.isDebugEnabled())
			logger.error("Se ha actualizado el ODE : " + form.getIdODE());
    }
}