/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.administracion.presentacion.gestionCargaMasiva.listarCargaMasiva;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.administracion.presentacion.InformeDateVO;
import es.pode.planificador.negocio.servicios.SrvPlanificadorService;



/**
 * @see es.pode.administracion.presentacion.cargaMasiva.listarCargaMasiva.InformesCargaController
 */
public class InformesCargaControllerImpl extends InformesCargaController
{

	private static Logger log = Logger.getLogger(InformesCargaControllerImpl.class);
	final int BUFFER_SIZE = 10000;
	
    /**
     * @see es.pode.administracion.presentacion.informesCarga.listarInformesCarga.InformesCargaController#listarInformes(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.informesCarga.listarInformesCarga.ListarInformesForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void listarInformes(ActionMapping mapping, es.pode.administracion.presentacion.gestionCargaMasiva.listarCargaMasiva.ListarInformesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
    		SrvPlanificadorService srvPlanificadorService = this.getSrvPlanificadorService();
    		es.pode.planificador.negocio.servicios.InformeODEsCargadosVO[] informes = srvPlanificadorService.listarInformesCarga();
    		form.setInformesAsArray(cambiarFechaInforme(informes));
    	}catch (Exception e){
        	log.error("Se ha producido un error al listar los informes: " + e);
        	throw new ValidatorException ("{errors.listarInformes}");
    	}
     }

    /**
     * @see es.pode.administracion.presentacion.informesCarga.listarInformesCarga.InformesCargaController#recuperarInforme(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.informesCarga.listarInformesCarga.RecuperarInformeForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void recuperarInforme(ActionMapping mapping, es.pode.administracion.presentacion.gestionCargaMasiva.listarCargaMasiva.RecuperarInformeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
			String fichero = form.getNombre();
			DataHandler dataHandler = null;
			SrvPlanificadorService planificadorService = this.getSrvPlanificadorService();
			try {
				dataHandler = planificadorService.recuperarInformeCarga(fichero);
				
				
			}catch (Exception e){
				log.error("Error al recuperar el fichero");
			}
			if (dataHandler==null){
				log.error("El fichero recuperado está vacio");
				throw new ValidatorException("{recuperarInforme.FALLO}");
			}
			if (fichero.endsWith(".pdf")){
				response.setContentType("application/pdf");
			} else if (fichero.endsWith(".html") || fichero.endsWith(".htm")) {
				response.setContentType("application/html");
			} else if (fichero.endsWith(".xls")) {
				response.setContentType("application/xls");
			} else if (fichero.endsWith(".doc")) {
				response.setContentType("application/doc");
			}
			response.setHeader("Content-Disposition", "attachment;filename="+fichero);
			OutputStream out = response.getOutputStream();
			InputStream in = dataHandler.getInputStream();
			if(log.isDebugEnabled())log.debug("recuperando el fichero " + fichero);
			byte[] buffer = new byte[BUFFER_SIZE];
			int count;
			while((count = in.read(buffer, 0, BUFFER_SIZE))!= -1)
			{
				out.write(buffer, 0, count);
			}
			
			out.flush();
			out.close();
			
		} catch (ValidatorException e){
			throw e;
		} catch (Exception e){
			log.error("Se ha producido el siguiente error: " +e);
			throw e;
		}
     }

    /**
     * @see es.pode.administracion.presentacion.informesCarga.listarInformesCarga.InformesCargaController#getInformes(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.informesCarga.listarInformesCarga.GetInformesForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void getInformes(ActionMapping mapping, es.pode.administracion.presentacion.gestionCargaMasiva.listarCargaMasiva.GetInformesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
    		List lista = ((FormularioListarInformesCargaEliminarFormImpl)form).getNombreRowSelection();
    		if (lista == null){
    			throw new ValidatorException ("{errors.eliminarInformes.nombreNulo}");
	       }
	    	form.setIds(lista);
	    	if(log.isDebugEnabled())log.debug("Se van a eliminar los siguientes informes: " + lista); 
    	} catch (Exception e){
    		log.error("Se ha producido un error al intentar recuperar los ids de los informes a borrar: " +e);
    		throw e;
    	}
     }

    private InformeDateVO[] cambiarFechaInforme (es.pode.planificador.negocio.servicios.InformeODEsCargadosVO[] lista){
    	InformeDateVO[] array=null;
    	if(lista!=null && lista.length>0){
	    	array = new InformeDateVO[lista.length];
	    	for (int i=0; i< lista.length; i++){
	    		array[i] = new InformeDateVO(); 
	    		array[i].setFecha(lista[i].getFechaModificacion().getTime());
	    		array[i].setNombre(lista[i].getNombreFichero());
	    	}
    	}else{
    		array = new InformeDateVO[0];
    	}
    	return array;
    }

}