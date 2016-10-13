/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.presentacion.sugerirTags;

import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.presentacion.VisualizadorSession;
import es.pode.tagging.negocio.servicios.SrvTaggingServer;
import es.pode.tagging.negocio.servicios.SrvTaggingServerServiceLocator;
import es.pode.tagging.negocio.servicios.TaggingVO;



/**
 * @see es.pode.presentacion.sugerirTags.SugerirTagsController
 */
public class SugerirTagsControllerImpl extends SugerirTagsController
{

	private static Logger logger = Logger.getLogger(SugerirTagsController.class);

    /**
     * @see es.pode.presentacion.sugerirTags.SugerirTagsController#obtenerTags(org.apache.struts.action.ActionMapping, es.pode.presentacion.sugerirTags.ObtenerTagsForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerTags(
    		ActionMapping mapping, 
    		ObtenerTagsForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    throws Exception
    {
    	VisualizadorSession sesion= this.getVisualizadorSession(request);
    	
    	String texto= form.getQ();
    	logger.info("SugerirTagsController - obtenerTags - Vamos a obtener sugerencias de tags que comiencen por " +texto);
    	if(texto!=null && !texto.equals("")){
    		
    		try{
    			TaggingVO[] sugerencias;
    			SrvTaggingServer server;
    	        if(sesion.isIdentidadFederada())
    	        {

    	        	URL url= new URL(sesion.getNodoOrigen()+ "/valoracion-1/services/SrvTaggingServer");
    	        	server= new SrvTaggingServerServiceLocator().getSrvTaggingServer(url);
    	        	sugerencias = server.obtenerTagsLikeLetra(texto);
    	        }else
    	        {
    	        	sugerencias = this.getSrvTaggingServer().obtenerTagsLikeLetra(texto);
    	        }

    	        
        	    PrintWriter writer = response.getWriter();
        	    
        	    if(sugerencias != null  && sugerencias.length>0){
            		StringBuffer sug = new StringBuffer();	
    		        for(int i=0;i<sugerencias.length;i++){
    		        	String sugerencia = sugerencias[i].getTag();
    		        	sug.append(sugerencia  + "|" + sugerencia  + "\n");
    		        }
    		        writer.append(sug.toString());
            	}
        		
        	}
        	catch(Exception e){
        	logger.error("SugerirTagsController - Error obteniendo sugerencias de tags", e);
        	}
    	}
    	
    	
    	
    	
       
       
       
       
    }









}