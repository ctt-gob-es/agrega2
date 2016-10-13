// license-header java merge-point
package es.pode.presentacion.error;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionMapping;



/**
 * @see es.pode.presentacion.error.ErroresController
 */
public class ErroresControllerImpl extends ErroresController
{
	public void borrarSession(
			ActionMapping mapping, 
			BorrarSessionForm form,
			HttpServletRequest request, 
			HttpServletResponse response)
	throws Exception 
	{
		form.setCodigo_error( (String) request.getAttribute("codigo_error"));
	}




}