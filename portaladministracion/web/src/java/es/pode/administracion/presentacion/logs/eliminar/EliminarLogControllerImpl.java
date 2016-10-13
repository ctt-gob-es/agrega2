/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.administracion.presentacion.logs.eliminar;


import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.administracion.presentacion.Utils;
import es.pode.contenidos.negocio.logs.servicio.SrvLogService;
import es.pode.contenidos.negocio.logs.servicio.ValidaBajaLogVO;



/**
 * @see es.pode.administracion.presentacion.logs.eliminar.EliminarLogController
 */
public class EliminarLogControllerImpl extends EliminarLogController
{


	private static Logger log = Logger.getLogger(EliminarLogControllerImpl.class);



    /**
     * @see es.pode.administracion.presentacion.logs.eliminar.EliminarLogController#eliminarLogs(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.logs.eliminar.EliminarLogsForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarLogs(ActionMapping mapping, es.pode.administracion.presentacion.logs.eliminar.EliminarLogsForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
    	try{
    		
    		String listaId = request.getParameter("listaId");
    		if(log.isDebugEnabled())log.debug("los ids de nodos que se quieren eliminar son " + listaId);
    		String[] logs = listaId.split(":");
    		if(log.isDebugEnabled())log.debug("los ids de nodos que se quieren eliminar son " + listaId);
    		SrvLogService logService = this.getSrvLogService();
    		ValidaBajaLogVO bajaLogVO = logService.eliminarFicheroLog(logs);
    		form.setLogsBorrados(bajaLogVO.getLogsBorrados());
    		form.setResultado(bajaLogVO.getDescripcionBaja());
    		
    		
    		
    	}catch (Exception e){
    		log.error("Se ha producido un error al eliminar el nodo: " + e);
    		throw new ValidatorException("{errors.borrarNodo}");
    	}
     }



	



    /**
     * @see es.pode.administracion.presentacion.logs.eliminar.EliminarLogController#obtenerLogs(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.logs.eliminar.ObtenerLogsForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerLogs(ActionMapping mapping, es.pode.administracion.presentacion.logs.eliminar.ObtenerLogsForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
    	try {
    	
    		Iterator iter = (form.getIds()).iterator();
    		
    	    form.setListaId(Utils.ids2String(iter, ":").trim());
    	    form.setLogs((String[])form.getIds().toArray(new String[0]));
    		
    	} catch (Exception e) {
    	    log.error("Se ha producido un error al recuperar los ficheros de log: " + e);
    	    throw new ValidatorException("{errors.obtenerLog}");
    	}
     }









}