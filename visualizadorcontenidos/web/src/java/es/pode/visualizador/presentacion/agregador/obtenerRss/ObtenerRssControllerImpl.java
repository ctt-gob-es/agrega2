/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.visualizador.presentacion.agregador.obtenerRss;

import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import java.io.PrintWriter;


/**
 * @see es.pode.visualizador.presentacion.agregador.obtenerRss.ObtenerRssController
 */
public class ObtenerRssControllerImpl extends ObtenerRssController
{
 	private static Logger log = Logger.getLogger(ObtenerRssControllerImpl.class);
	

    /**
     * @see es.pode.visualizador.presentacion.agregador.obtenerRss.ObtenerRssController#obtenerRssXML(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.agregador.obtenerRss.ObtenerRssXMLForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerRssXML(ActionMapping mapping, es.pode.visualizador.presentacion.agregador.obtenerRss.ObtenerRssXMLForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
	    	String grupo = form.getGrupo();
	    	String usuario = form.getUsuario();
	    	String numOdes = form.getNumOdes();

		   	log.debug("Area Grupo = '" + grupo + "' usuario = '" + usuario + "' Num Odes = '" + numOdes + "'");
	        
	    	if (numOdes==null){
	   	   		log.error("numOdes no puede ser null.");
			}
	    	if (grupo==null && usuario==null){
	   	   		log.error("grupo y usuario no pueden ser null.");
			}

	    	if (grupo==null)grupo="";
	    	if (usuario==null)usuario="";
	
   	   		es.pode.agregador.negocio.agregador.servicio.ParamWidgetVO RSSwidget = new es.pode.agregador.negocio.agregador.servicio.ParamWidgetVO(usuario, numOdes, grupo);
//   	   		if (RSSwidget==null){
//	   	   		log.error("No se pudo crear un objeto ParamWidget.");
//			}
   	   		
			String xmlRss = getSrvAgregadorRssService().obtenerFeedWidgets(RSSwidget);
			if (xmlRss==null){
	   	   		log.error("No se pudo obtener XML del RSS.");
			}
			
			response.setContentType("text/xml");
			PrintWriter writer = response.getWriter();  		
			writer.write(xmlRss);
			writer.close();

    	}catch(Exception e){
			log.error("Error al intentar obtener el RSS.", e);
   			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}
}