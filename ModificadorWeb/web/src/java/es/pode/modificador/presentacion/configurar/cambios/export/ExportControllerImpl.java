/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.modificador.presentacion.configurar.cambios.export;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.soporte.constantes.ConstantesAgrega;



/**
 * @see es.pode.modificador.presentacion.configurar.cambios.export.ExportController
 */
public class ExportControllerImpl extends ExportController
{


	private Logger logger = Logger.getLogger(ExportControllerImpl.class);



    /**
     * @see es.pode.modificador.presentacion.configurar.cambios.export.ExportController#obtenerDatos(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.cambios.export.ObtenerDatosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerDatos(ActionMapping mapping, es.pode.modificador.presentacion.configurar.cambios.export.ObtenerDatosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	logger.debug("Paso por obtenerDatos");
     }







    /**
     * @see es.pode.modificador.presentacion.configurar.cambios.export.ExportController#exportarOdes(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.cambios.export.ExportarOdesForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void exportarOdes(ActionMapping mapping, es.pode.modificador.presentacion.configurar.cambios.export.ExportarOdesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
       	if(form.getPathDestino() !=null && form.getPathDestino().length()>0 && !form.getPathDestino().equals(""))
       	{
//       		this.getCambioSession(request).setIdioma(null);
       		if(form.getTipoTransformacion() !=null && form.getTipoTransformacion().length()>0 && !form.getTipoTransformacion().equals("")){
       			this.getCambioSession(request).setPathDestino(form.getPathDestino());
       			this.getCambioSession(request).setTipoTransformacion(form.getTipoTransformacion());
       		}
       		else{
       			throw new ValidatorException("{export.tipoTransformacion}");
       		}
       	}
       	else
       	{
       		throw new ValidatorException("{export.PathDestino}");
       	}
        
  

     }







    /**
     * @see es.pode.modificador.presentacion.configurar.cambios.export.ExportController#selectAction(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.cambios.export.SelectActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String selectAction(ActionMapping mapping, es.pode.modificador.presentacion.configurar.cambios.export.SelectActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String origen = form.getAction();
    	
		String result="";
		java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		// Introduzco en el objeto de sesion la opcion elegida
		
		
		if (origen.equals(i18n.getString("export.aceptar")) )
		{
			
			result="Aceptar";
			
		} 
		else if (origen.equals(i18n.getString("export.volver"))) 
		{
			result = "Volver";
		} 
		else if (origen.equals(i18n.getString("export.cancelar"))) 
		{
			result = "Cancelar";
		} 
		
         return result;
 
    }







    /**
     * @see es.pode.modificador.presentacion.configurar.cambios.export.ExportController#borrarSesion(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.cambios.export.BorrarSesionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void borrarSesion(ActionMapping mapping, es.pode.modificador.presentacion.configurar.cambios.export.BorrarSesionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	this.removeCambioSession(request);
    }









}