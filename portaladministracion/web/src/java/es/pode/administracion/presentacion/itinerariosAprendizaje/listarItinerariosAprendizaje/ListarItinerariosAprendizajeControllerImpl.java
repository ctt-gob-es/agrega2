/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.administracion.presentacion.itinerariosAprendizaje.listarItinerariosAprendizaje;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO;
import es.pode.adminusuarios.negocio.servicios.SrvPerfilPublico;



/**
 * @see es.pode.administracion.presentacion.itinerariosAprendizaje.listarItinerariosAprendizaje.ListarItinerariosAprendizajeController
 */
public class ListarItinerariosAprendizajeControllerImpl extends ListarItinerariosAprendizajeController
{




	
	private static Logger logger = Logger.getLogger(ListarItinerariosAprendizajeControllerImpl.class);
    /**
     * @see es.pode.administracion.presentacion.itinerariosAprendizaje.listarItinerariosAprendizaje.ListarItinerariosAprendizajeController#getIds(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.itinerariosAprendizaje.listarItinerariosAprendizaje.GetIdsForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void getIds(ActionMapping mapping, es.pode.administracion.presentacion.itinerariosAprendizaje.listarItinerariosAprendizaje.GetIdsForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
	    	List lista =((FormularioItinerariosEliminarItinerariosFormImpl)form).getIdRowSelection();
	    	if(logger.isDebugEnabled())
	    		logger.debug("Los nodos que se van a eliminar son ["+lista);
	       if (lista == null){
	    	   throw new ValidatorException ("{errors.eliminarItinerario.idNulo}");
	       }
	       form.setIds(lista);
	       logger.info("Se van a eliminar los siguientes itinerarios: " + lista);
	       
       } catch (ValidatorException e){
    	   throw e;
	    	   
       } catch (Exception e){
    	   
    	   logger.error("Se ha producido un error al intentar recuperar los ids para borrar: " +e);
    	   throw new ValidatorException ("{errors.eliminarItinerarios.idError}");
    	   
       }
     }







    /**
     * @see es.pode.administracion.presentacion.itinerariosAprendizaje.listarItinerariosAprendizaje.ListarItinerariosAprendizajeController#listarItinerariosAprendizaje(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.itinerariosAprendizaje.listarItinerariosAprendizaje.ListarItinerariosAprendizajeForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void listarItinerariosAprendizaje(ActionMapping mapping, es.pode.administracion.presentacion.itinerariosAprendizaje.listarItinerariosAprendizaje.ListarItinerariosAprendizajeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
        	
	    	 SrvPerfilPublico service = this.getSrvPerfilPublico();
        	 GrupoPublicoVO[] itinerarios = service.listarGrupoPublicos();
        	form.setItinerariosAsArray(itinerarios);
        } catch (Exception e){
        	logger.error("Se ha producido un error al listar los itinerarios de aprendizaje: " + e);
        	throw new ValidatorException ("{errors.listarItinerariosAprendizaje} ");
        }
     }






    /**
     * This dummy variable is used to populate the "itinerarios" table.
     * You may delete it when you add you own code in this controller.
     */
    private static final java.util.Collection itinerariosDummyList =
        java.util.Arrays.asList( new Object[] {
            new ItinerariosDummy("id-1"),
            new ItinerariosDummy("id-2"),
            new ItinerariosDummy("id-3"),
            new ItinerariosDummy("id-4"),
            new ItinerariosDummy("id-5")
        } );

    /**
     * This inner class is used in the dummy implementation in order to get the web application
     * running without any manual programming.
     * You may delete this class when you add you own code in this controller.
     */
    public static final class ItinerariosDummy implements java.io.Serializable
    {
        private String id = null;

        public ItinerariosDummy(String id)
        {
            this.id = id;
        }
        
        public void setId(String id)
        {
            this.id = id;
        }

        public String getId()
        {
            return this.id;
        }
        
    }



}