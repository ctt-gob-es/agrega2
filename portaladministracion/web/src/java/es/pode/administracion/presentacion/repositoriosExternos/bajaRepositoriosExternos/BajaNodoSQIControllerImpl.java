/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.administracion.presentacion.repositoriosExternos.bajaRepositoriosExternos;


import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.buscar.negocio.nodosSQI.servicio.NodoSQIVO;
import es.pode.buscar.negocio.nodosSQI.servicio.SrvGestionSqiService;



/**
 * @see es.pode.administracion.presentacion.repositoriosExternos.bajaNodoSQI.BajaNodoSQIController
 */
public class BajaNodoSQIControllerImpl extends BajaNodoSQIController
{



	private static Logger logger = Logger.getLogger(BajaNodoSQIControllerImpl.class);

	public void bajaNodoSQI(ActionMapping mapping, BajaNodoSQIForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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

	    		SrvGestionSqiService nodoService = this.getSrvGestionSqiService();
	    		NodoSQIVO[] nodo = nodoService.consultaNodosSQI(listIDs);
	    		form.setNodosSQIEliminarAsArray(nodo);
	    		nodoService.bajaNodosSQI(listIDs);
	    		
    		}else{
    			throw new ValidatorException("{errors.bajanodosSQI.idNulo}");
    		}
    		 		
    	}catch (Exception e){
    		logger.error("Se ha producido un error al eliminar el nodoSQI: " + e);
    		throw new ValidatorException("{errors.bajanodoSQI}");
    	}
		
	}



	public void obtenerNodosSQI(ActionMapping mapping, ObtenerNodosSQIForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			
			   if(form.getIdsAsArray()!=null && form.getIdsAsArray().length>0){
				   Object[] arrayIds= form.getIdsAsArray();
				   if(logger.isDebugEnabled())
					   logger.debug("los ids de nodosSQI que se quieren eliminar son " + Arrays.toString(arrayIds));
		    		Long[] ident=new Long[arrayIds.length];
		    		StringBuilder lista=new StringBuilder();
		    		for(int i=0;i<arrayIds.length;i++){
		    			ident[i]= Long.parseLong((String) arrayIds[i]);
		    			lista.append(ident[i].toString() + ",");
		    		}
		  	     NodoSQIVO[] nodos = this.getSrvGestionSqiService().consultaNodosSQI(ident);
		  	     form.setIdsAsArray(ident);
		  	     form.setListaId(lista.toString());
		  	     form.setNodosSQI(nodos);
			   }else{
				   throw new ValidatorException("{errors.bajanodosSQI.idNulo}");
			   }
	      	}catch (Exception e) {
	  	      	    logger.error("Se ha producido un error al recuperar los nodosSQI: " + e);
	  	      	    throw new ValidatorException("{errors.bajanodoSQI.recuperar}");
	  	     }  
	}









}