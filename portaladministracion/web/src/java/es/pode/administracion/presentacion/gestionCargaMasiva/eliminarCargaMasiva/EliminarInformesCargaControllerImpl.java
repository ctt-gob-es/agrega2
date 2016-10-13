/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.administracion.presentacion.gestionCargaMasiva.eliminarCargaMasiva;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.administracion.presentacion.Utils;
import es.pode.planificador.negocio.servicios.SrvPlanificadorService;
import es.pode.planificador.negocio.servicios.ValidaBajaInformeCargaVO;



/**
 * @see es.pode.administracion.presentacion.cargaMasiva.eliminarCargaMasiva.EliminarInformesCargaController
 */
public class EliminarInformesCargaControllerImpl extends EliminarInformesCargaController
{






	private static Logger log = Logger.getLogger(EliminarInformesCargaControllerImpl.class);




    /**
     * @see es.pode.administracion.presentacion.informesCarga.eliminarInformesCarga.EliminarInformesCargaController#eliminarInformeCarga(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.informesCarga.eliminarInformesCarga.EliminarInformeCargaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarInformeCarga(ActionMapping mapping, es.pode.administracion.presentacion.gestionCargaMasiva.eliminarCargaMasiva.EliminarInformeCargaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
    		
    		String listaId = request.getParameter("listaId");
    		
    		if(log.isDebugEnabled())log.debug("los ids de los informes que se quieren eliminar son " + listaId);
    		String[] informes = listaId.split(":");

    		if(log.isDebugEnabled())log.debug("los ids de los informes que se quieren eliminar son " + listaId);
    		SrvPlanificadorService informeService = this.getSrvPlanificadorService();
    		//SrvPlanificadorService srvPlanificadorService = this.getSrvPlanificadorService();
    		ValidaBajaInformeCargaVO bajaInformeVO = informeService.eliminarInformesCarga(informes);
    		form.setInformesBorrados(bajaInformeVO.getInformesBorrados());
    		form.setResultado(bajaInformeVO.getDescripcionBaja());
    		
    	}catch (Exception e){
    		log.error("Se ha producido un error al eliminar el informe: " + e);
    		throw new ValidatorException("{errors.eliminarInforme}");
    	}
     }





 

    /**
     * @see es.pode.administracion.presentacion.informesCarga.eliminarInformesCarga.EliminarInformesCargaController#obtenerInformeCarga(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.informesCarga.eliminarInformesCarga.ObtenerInformeCargaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerInformeCarga(ActionMapping mapping, es.pode.administracion.presentacion.gestionCargaMasiva.eliminarCargaMasiva.ObtenerInformeCargaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {

    	try {
    		Iterator iter = (form.getIds()).iterator();

    	    form.setListaId(Utils.ids2String(iter, ":").trim());
    	    form.setInformesCarga((String[])form.getIds().toArray(new String[0]));
    		
    	} catch (Exception e) {
    	    log.error("Se ha producido un error al recuperar los informes: " + e);
    	    throw new ValidatorException("{errors.obtenerInformeFederado}");
    	}
    	
    
     }



}