// license-header java merge-point
package es.pode.administracion.presentacion.itinerariosAprendizaje.eliminarItinerariosAprendizaje;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO;
import es.pode.adminusuarios.negocio.servicios.ResultadoOperacionVO;
import es.pode.adminusuarios.negocio.servicios.SrvPerfilPublico;



/**
 * @see es.pode.administracion.presentacion.itinerariosAprendizaje.eliminarItinerariosAprendizaje.EliminarItinerariosAprendizajeController
 */
public class EliminarItinerariosAprendizajeControllerImpl extends EliminarItinerariosAprendizajeController
{



	private static Logger logger = Logger.getLogger(EliminarItinerariosAprendizajeControllerImpl.class);


    /**
     * @see es.pode.administracion.presentacion.itinerariosAprendizaje.eliminarItinerariosAprendizaje.EliminarItinerariosAprendizajeController#eliminarItinerario(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.itinerariosAprendizaje.eliminarItinerariosAprendizaje.EliminarItinerarioForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarItinerario(ActionMapping mapping, es.pode.administracion.presentacion.itinerariosAprendizaje.eliminarItinerariosAprendizaje.EliminarItinerarioForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
    		if(form.getListaId()!=null && !form.getListaId().equals("")){
    			String[]listIDsStrings=form.getListaId().split(",");
    			if(logger.isDebugEnabled())
    				logger.debug("La lista de nombres:"+form.getListaId());
				Long[] listIDs =new Long[listIDsStrings.length];
				for(int i=0; i<listIDsStrings.length;i++)
				{
					listIDs[i]=Long.valueOf(listIDsStrings[i]);
				}

	    		 SrvPerfilPublico perfilservice = this.getSrvPerfilPublico();
	    		 
	    		GrupoPublicoVO[] grupos=perfilservice.obtenerGruposPublicosPorIdentificador(listIDs);
	    		form.setItinerariosEliminarAsArray(grupos);
	    		ResultadoOperacionVO[] resultado = perfilservice.eliminarGrupoPublicoPorAdministrador(listIDs);
	    		
    		}else{
    			throw new ValidatorException("{errors.eliminarItinerario.idNulo}");
    		}
    		 		
    	}catch (Exception e){
    		logger.error("Se ha producido un error al eliminar el itinerarios: " + e);
    		throw new ValidatorException("{errors.eliminarItinerarios.recuperar}");
    	}
     }







    /**
     * @see es.pode.administracion.presentacion.itinerariosAprendizaje.eliminarItinerariosAprendizaje.EliminarItinerariosAprendizajeController#obtenerItinerarios(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.itinerariosAprendizaje.eliminarItinerariosAprendizaje.ObtenerItinerariosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerItinerarios(ActionMapping mapping, es.pode.administracion.presentacion.itinerariosAprendizaje.eliminarItinerariosAprendizaje.ObtenerItinerariosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
			
			   if(form.getIdsAsArray()!=null && form.getIdsAsArray().length>0){
				   Object[] arrayIds= form.getIdsAsArray();
				   if(logger.isDebugEnabled())
					   logger.debug("los ids de itinerarios que se quieren eliminar son " + Arrays.toString(arrayIds));
		    		Long[] ident=new Long[arrayIds.length];
		    		StringBuilder lista=new StringBuilder();
		    		for(int i=0;i<arrayIds.length;i++){
		    			ident[i]= Long.parseLong((String) arrayIds[i]);
		    			lista.append((String) arrayIds[i] + ",");
		    		}
		  	     GrupoPublicoVO[] grupos = this.getSrvPerfilPublico().obtenerGruposPublicosPorIdentificador(ident);
		  	     form.setIdsAsArray(ident);
		  	     form.setListaId(lista.toString());
		  	     form.setGrupos(grupos);
			   }else{
				   throw new ValidatorException("{errors.eliminarItinerario.idNulo}");
			   }
	      	}catch (Exception e) {
	  	      	    logger.error("Se ha producido un error al recuperar los itinerarios: " + e);
	  	      	    throw new ValidatorException("{errors.eliminarItinerarios.recuperar}");
	  	     }  
     }









}