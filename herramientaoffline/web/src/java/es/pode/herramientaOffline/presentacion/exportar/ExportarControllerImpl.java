// license-header java merge-point
package es.pode.herramientaOffline.presentacion.exportar;

import java.util.ResourceBundle;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.herramientaOffline.negocio.soporte.DescargaVO;
import es.pode.soporte.constantes.ConstantesAgrega;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @see es.pode.herramientaOffline.presentacion.exportar.ExportarController
 */
public class ExportarControllerImpl extends ExportarController
{
	private static Logger logger = Logger.getLogger(ExportarControllerImpl.class);

	/**
     * @see es.pode.herramientaOffline.presentacion.exportar.ExportarController#exportar(org.apache.struts.action.ActionMapping, es.pode.herramientaOffline.presentacion.exportar.ExportarForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String exportar(ActionMapping mapping, es.pode.herramientaOffline.presentacion.exportar.ExportarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
        ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
        String result = "Cancelar";
        
        String action = form.getAction();
        String tipo = form.getFormato();
        String idOde = form.getIdOde();
        if(logger.isDebugEnabled()) logger.debug("Datos recogidos del formulario: action = " + action + " ; formato = " + tipo + " ; idOde = " + idOde);
        if(i18n.getString("comun.Descargar").equals(action)) {
        	try {
        		DescargaVO descarga = getDescomprimeYvalida().descargar(idOde, tipo);
        		if(descarga.getValido().booleanValue()) {
        			logger.info("Redirigiendo a " + descarga.getUrl() + " para descargar ODE");
        			response.sendRedirect(descarga.getUrl());
        			result="bien";
        		} else {
        			logger.error("El ode " + idOde + " no es valido. Devuelvo los mensajes del validador");
        			form.setMensajes(descarga.getMensajes());
        			form.setHref(descarga.getUrl());
        			result="mal";
        		}
        	} catch (Exception e) {
        		logger.error("Error inesperado al descargar " + idOde,e);
        		throw new ValidatorException("{HerramientaOffline.error.inesperado}");
			}
        }
        
        if(logger.isDebugEnabled()) logger.debug("Retorno para punto de decision : " + result);
        return result;
    }

}