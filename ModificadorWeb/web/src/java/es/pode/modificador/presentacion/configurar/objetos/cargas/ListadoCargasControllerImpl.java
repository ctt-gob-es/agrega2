/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.modificador.presentacion.configurar.objetos.cargas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.modificador.negocio.servicio.TareaCargaMasivaVO;
import es.pode.modificador.presentacion.configurar.objetos.odescarga.BusquedaOdesSession;


/**
 * @see es.pode.modificador.presentacion.configurar.objetos.cargas.ListadoCargasController
 */
public class ListadoCargasControllerImpl extends ListadoCargasController
{

	private static final Logger logger =Logger.getLogger(ListadoCargasControllerImpl.class);
    /**
     * @see es.pode.modificador.presentacion.configurar.objetos.cargas.ListadoCargasController#obtenerCargasMasivas(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.objetos.cargas.ObtenerCargasMasivasForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerCargasMasivas(ActionMapping mapping, es.pode.modificador.presentacion.configurar.objetos.cargas.ObtenerCargasMasivasForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	BusquedaOdesSession sesion=getBusquedaOdesSession(request);
    	if(sesion==null) sesion=new BusquedaOdesSession();{
    		
    		try{
    			
    			logger.debug("Vamos a obtener las cargas");
    			TareaCargaMasivaVO[] cargas = this.getSrvHerramientaModificacion().obtenerTareasCargaMasiva();
    			logger.info("Hemos obtenido las cargas");
    			
    			form.setCargasAsArray(cargas);
    			logger.info("Hemos añadido las cargas al form");
    		}catch (Exception e){

    			logger.error("Error al obtener las cargas: ", e);
    			throw e;
    		}
    		
    		setBusquedaOdesSession(request, sesion);
    	}
    }
	
    
    /**
     * Almacena la id en el objeto de sesión.
     * 
     */
	public void almacenarIdCarga(ActionMapping mapping, AlmacenarIdCargaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BusquedaOdesSession sesion = getBusquedaOdesSession(request);
		logger.debug("Almacenamos la id: "+form.getId());
		sesion.setId(form.getId());
		logger.debug("La id está en la sesion y es: "+sesion.getId());
		
	}

	/**
	 * Borra la sesión
	 * 
	 */
	public void borrarSesion(ActionMapping mapping, BorrarSesionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		removeBusquedaOdesSession(request);
		
	}

}