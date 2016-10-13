/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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