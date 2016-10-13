// license-header java merge-point
package es.pode.buscador.presentacion.error;

import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @see es.pode.buscador.presentacion.error.ErrorController
 */
public class ErrorControllerImpl extends ErrorController
{






    /**
     * @see es.pode.buscador.presentacion.error.ErrorController#capturarException(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.error.CapturarExceptionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void borrarSesion(ActionMapping mapping, es.pode.buscador.presentacion.error.BorrarSesionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
//    	String codigo_error = (String) request.getAttribute("error_codigo");
//    	form.setCodigo_error(codigo_error);
    	request.getSession().invalidate();
    }











}