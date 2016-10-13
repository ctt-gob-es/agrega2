// license-header java merge-point
package es.pode.instalador.presentacion.inicio;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.instalador.presentacion.configuracionPlataforma.ConfiguracionPlataformaControllerImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;



/**
 * @see es.pode.instalador.presentacion.inicio.InicioController
 */
public class InicioControllerImpl extends InicioController
{

	private static Logger logger = Logger.getLogger(InicioControllerImpl.class);



    /**
     * @see es.pode.instalador.presentacion.inicio.InicioController#analizarRespuesta(org.apache.struts.action.ActionMapping, es.pode.instalador.presentacion.inicio.AnalizarRespuestaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String analizarRespuesta(ActionMapping mapping, es.pode.instalador.presentacion.inicio.AnalizarRespuestaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		String result = "CONFIGURAR";
		Locale loc = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		String actualizar = I18n.getInstance().getResource("inicio.actualizar", "application-resources", loc);
		
		if(logger.isDebugEnabled()) logger.debug("Action = " + form.getAction());
		
		if(actualizar!=null && form.getAction()!=null && actualizar.equals(form.getAction())) {
			result = "ACTUALIZAR";
		}
		return result;		
    }

}