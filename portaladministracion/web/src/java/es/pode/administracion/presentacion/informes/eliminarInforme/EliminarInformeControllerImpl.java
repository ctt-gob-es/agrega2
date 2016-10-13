/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.administracion.presentacion.informes.eliminarInforme;


import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.administracion.presentacion.Utils;
import es.pode.contenidos.negocio.informes.servicio.SrvInformeService;
import es.pode.contenidos.negocio.informes.servicio.ValidaBajaInformeVO;



/**
 * @see es.pode.administracion.presentacion.informes.eliminarInforme.EliminarInformeController
 */
public class EliminarInformeControllerImpl extends EliminarInformeController
{

	private static Logger log = Logger.getLogger(EliminarInformeControllerImpl.class);


    /**
     * @see es.pode.administracion.presentacion.informes.eliminarInforme.EliminarInformeController#eliminarInforme(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.informes.eliminarInforme.EliminarInformeForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarInforme(ActionMapping mapping, es.pode.administracion.presentacion.informes.eliminarInforme.EliminarInformeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
    		String listaId = request.getParameter("listaId");
log.debug("LUIS listaId: "+listaId);    		
    		if(log.isDebugEnabled())log.debug("los ids de los informes que se quieren eliminar son " + listaId);
    		String[] informes = listaId.split(":");
for(int i=0; i<informes.length; i++)
log.debug("LUIS informe["+i+"]: "+informes[i]);
    		if(log.isDebugEnabled())log.debug("los ids de los informes que se quieren eliminar son " + listaId);
    		SrvInformeService informeService = this.getSrvInformeService();
    		ValidaBajaInformeVO bajaInformeVO = informeService.eliminarInforme(informes);
    		form.setInformesBorrados(bajaInformeVO.getInformesBorrados());
    		form.setResultado(bajaInformeVO.getDescripcionBaja());
    		
    	} catch (Exception e) {
    		log.error("Se ha producido un error al eliminar el informe: " + e);
    		throw new ValidatorException("{errors.eliminarInforme}");
    	}
     }


    /**
     * @see es.pode.administracion.presentacion.informes.eliminarInforme.EliminarInformeController#obtenerInforme(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.informes.eliminarInforme.ObtenerInformeForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerInforme(ActionMapping mapping, es.pode.administracion.presentacion.informes.eliminarInforme.ObtenerInformeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try {
    		Iterator iter = (form.getIds()).iterator();
    	    form.setListaId(Utils.ids2String(iter, ":").trim());
    	    form.setInformes((String[])form.getIds().toArray(new String[0]));
    	} catch (Exception e) {
    	    log.error("Se ha producido un error al recuperar los informes: " + e);
    	    throw new ValidatorException("{errors.obtenerInforme}");
    	}
     }


}