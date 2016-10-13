/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.herramientaOffline.presentacion.eliminar;

import java.util.Collection;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.soporte.constantes.ConstantesAgrega;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @see es.pode.herramientaOffline.presentacion.eliminar.EliminarController
 */
public class EliminarControllerImpl extends EliminarController
{
	private static Logger logger = Logger.getLogger(EliminarControllerImpl.class);
    /**
     * @see es.pode.herramientaOffline.presentacion.eliminar.EliminarController#prepararConfirmacion(org.apache.struts.action.ActionMapping, es.pode.herramientaOffline.presentacion.eliminar.PrepararConfirmacionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void prepararConfirmacion(ActionMapping mapping, es.pode.herramientaOffline.presentacion.eliminar.PrepararConfirmacionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // Almaceno los ODEs a eliminar en la sesion para recuperarlos despues de la confirmacion
    	if (logger.isDebugEnabled()) logger.debug("Lista de identificadores a eliminar : " + form.getIdentificadores() + (form.getIdentificadores() == null ? "" : " con " + form.getIdentificadores().size() + " elementos"));
    	request.getSession().setAttribute("identificadores", form.getIdentificadores());
    }

    /**
     * @see es.pode.herramientaOffline.presentacion.eliminar.EliminarController#eliminar(org.apache.struts.action.ActionMapping, es.pode.herramientaOffline.presentacion.eliminar.EliminarForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminar(ActionMapping mapping, es.pode.herramientaOffline.presentacion.eliminar.EliminarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        Collection identificadores = (Collection)request.getSession().getAttribute("identificadores");
        request.getSession().removeAttribute("identificadores");
        String action = form.getAction();
        if(logger.isDebugEnabled()) logger.debug("Action : " + action + "; identificadores = " + identificadores);
        java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
        ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
        if(i18n.getString("comun.si").equals(action)) {
        	logger.info("Eliminando " + (identificadores!=null?identificadores.size()+" odes":" ERROR: identificadores = null"));
        	if(identificadores!=null) getDescomprimeYvalida().eliminarOdes((String[])identificadores.toArray(new String[identificadores.size()]));
        } else {
        	if(logger.isDebugEnabled()) logger.debug("Cancelar en Eliminar");
        }
     }
}