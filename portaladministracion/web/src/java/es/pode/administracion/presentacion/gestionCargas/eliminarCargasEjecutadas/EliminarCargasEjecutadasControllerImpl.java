/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.administracion.presentacion.gestionCargas.eliminarCargasEjecutadas;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.planificador.negocio.servicios.SrvPlanificadorService;
import es.pode.planificador.negocio.servicios.TareaEjecutadaVO;



/**
 * @see es.pode.administracion.presentacion.gestionCargas.eliminarCargasEjecutadas.EliminarCargasEjecutadasController
 */
public class EliminarCargasEjecutadasControllerImpl extends EliminarCargasEjecutadasController
{


	private static Logger logger = Logger.getLogger(EliminarCargasEjecutadasControllerImpl.class);



    /**
     * @see es.pode.administracion.presentacion.gestionCargas.eliminarCargasEjecutadas.EliminarCargasEjecutadasController#eliminarCargaEjecutada(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.gestionCargas.eliminarCargasEjecutadas.EliminarCargaEjecutadaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarCargaEjecutada(ActionMapping mapping, es.pode.administracion.presentacion.gestionCargas.eliminarCargasEjecutadas.EliminarCargaEjecutadaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
    		if(form.getListaId()!=null && !form.getListaId().equals("")){
    			String[]listIDsStrings=form.getListaId().split(",");
    			if(logger.isDebugEnabled())
    				logger.debug("La lista de identificadores:"+form.getListaId());
				Long[] listIDs =new Long[listIDsStrings.length];
				for(int i=0; i<listIDsStrings.length;i++)
				{
					listIDs[i]=Long.valueOf(listIDsStrings[i]);
				}

	    		SrvPlanificadorService service = this.getSrvPlanificadorService();
	    		TareaEjecutadaVO[] cargas = service.consultarTareaEjecutadasCarga(listIDs);
	    		
	    		form.setEliminarCargasAsArray(cargas);
	    		Boolean vuelta = service.eliminarTareasCargaEjecutada(listIDsStrings);
	    		
	    		
    		}else{
    			throw new ValidatorException("{errors.eliminarCargasEjecutadas.idNulo}");
    		}
    		 		
    	}catch (Exception e){
    		logger.error("Se ha producido un error al eliminar la tarea: " + e);
    		throw new ValidatorException("{errors.listarCargasEjecutadas}");
    	}
     }







    /**
     * @see es.pode.administracion.presentacion.gestionCargas.eliminarCargasEjecutadas.EliminarCargasEjecutadasController#obtenerCargaEjecutada(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.gestionCargas.eliminarCargasEjecutadas.ObtenerCargaEjecutadaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerCargaEjecutada(ActionMapping mapping, es.pode.administracion.presentacion.gestionCargas.eliminarCargasEjecutadas.ObtenerCargaEjecutadaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
			
			   if(form.getIdsAsArray()!=null && form.getIdsAsArray().length>0){
				   Object[] arrayIds= form.getIdsAsArray();
				   if(logger.isDebugEnabled())
					   logger.debug("los ids de las tareas que se quieren eliminar son " + Arrays.toString(arrayIds));
		    		Long[] ident=new Long[arrayIds.length];
		    		StringBuilder lista=new StringBuilder();
		    		for(int i=0;i<arrayIds.length;i++){
		    			ident[i]= Long.parseLong((String) arrayIds[i]);
		    			lista.append(ident[i].toString() + ",");
		    		}
		  	     TareaEjecutadaVO[] cargas = this.getSrvPlanificadorService().consultarTareaEjecutadasCarga(ident);
		  	     form.setIdsAsArray(ident);
		  	     form.setListaId(lista.toString());
		  	     form.setCargas(cargas);
			   }else{
				   throw new ValidatorException("{errors.eliminarCargasEjecutadas.idNulo}");
			   }
	      	}catch (Exception e) {
	  	      	    logger.error("Se ha producido un error al recuperar las tareas: " + e);
	  	      	    throw new ValidatorException("{errors.eliminarCargasEjecutadas.recuperar}");
	  	     }  
     }









}