/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.modificador.presentacion.configurar.cambios.transformacion;

import java.util.ResourceBundle;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.soporte.constantes.ConstantesAgrega;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @see es.pode.modificador.presentacion.configurar.cambios.transformacion.TransformationController
 */
public class TransformationControllerImpl extends TransformationController
{
	private Logger logger = Logger.getLogger(TransformationControllerImpl.class);








    /**
     * @see es.pode.modificador.presentacion.configurar.cambios.transformacion.TransformationController#selectAction(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.cambios.transformacion.SelectActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String selectAction(
    		ActionMapping mapping, 
    		SelectActionForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    throws Exception
    {
    	String origen = form.getAction();
    	
		String result="";
		java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		if (origen.equals(i18n.getString("transformacion.aceptar")) )
		{
			result="Aceptar";
		} 
		else if (origen.equals(i18n.getString("transformacion.volver"))) 
		{
			result = "Volver";
		} 
		else if (origen.equals(i18n.getString("transformacion.cancelar"))) 
		{
			result = "Cancelar";
		} 
		
         return result;
    	
    }







    /**
     * @see es.pode.modificador.presentacion.configurar.cambios.transformacion.TransformationController#transformar(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.cambios.transformacion.TransformarForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void transformar(
    		ActionMapping mapping,
    		TransformarForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response)
    throws Exception
    {
    	
       	if(form.getPathDestino() !=null && form.getPathDestino().length()>0 && !form.getPathDestino().equals(""))
       	{
       		if(form.getTipoTransformacion() !=null && form.getTipoTransformacion().length()>0 && !form.getTipoTransformacion().equals("")){
       			this.getCambioSession(request).setPathDestino(form.getPathDestino());
       			this.getCambioSession(request).setTipoTransformacion(form.getTipoTransformacion());
       		}
       		else{
       			throw new ValidatorException("{transformacion.tipoTransformacion}");
       		}
       	}
       	else
       	{
       		throw new ValidatorException("{transformacion.PathDestino}");
       	}
     }







    /**
     * @see es.pode.modificador.presentacion.configurar.cambios.transformacion.TransformationController#borrarSesion(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.cambios.transformacion.BorrarSesionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void borrarSesion(
    		ActionMapping mapping, 
    		BorrarSesionForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    throws Exception
    {
    	this.removeCambioSession(request);
    }









}