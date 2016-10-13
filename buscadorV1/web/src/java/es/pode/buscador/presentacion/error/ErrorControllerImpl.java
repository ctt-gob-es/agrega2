/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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