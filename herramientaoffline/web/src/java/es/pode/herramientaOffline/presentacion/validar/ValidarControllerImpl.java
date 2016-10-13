// license-header java merge-point
package es.pode.herramientaOffline.presentacion.validar;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.herramientaOffline.negocio.soporte.DescompriveYvalidaVO;
import es.pode.herramientaOffline.negocio.soporte.Validaciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @see es.pode.herramientaOffline.presentacion.validar.ValidarController
 */
public class ValidarControllerImpl extends ValidarController
{

	private static Logger logger = Logger.getLogger(ValidarControllerImpl.class);
	
    /**
     * @see es.pode.herramientaOffline.presentacion.validar.ValidarController#validar(org.apache.struts.action.ActionMapping, es.pode.herramientaOffline.presentacion.validar.ValidarForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void validar(ActionMapping mapping, es.pode.herramientaOffline.presentacion.validar.ValidarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String idOde = form.getIdOde();
    	if(logger.isDebugEnabled()) logger.debug("Validando ode " + idOde);
    	try {
    		DescompriveYvalidaVO resultado = getDescomprimeYvalida().validarOde(idOde, Validaciones.COMPLETA);
    		if(logger.isDebugEnabled()) logger.debug("Resultado de validacion : " + resultado.getValido());
    		form.setValido(resultado.getValido());
    		form.setMensajesAsArray(resultado.getMensajes());    		
    	} catch (Exception e) {
			logger.error("Error inesperado al validar " + idOde,e);
		}
    }

}