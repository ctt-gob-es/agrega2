/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.visualizador.presentacion.atomOre.obtenerAtomOre;

import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import java.io.PrintWriter;


/**
 * @see es.pode.visualizador.presentacion.atomOre.obtenerAtomOre.ObtenerAtomOreController
 */
public class ObtenerAtomOreControllerImpl extends ObtenerAtomOreController
{  
 	private static Logger log = Logger.getLogger(ObtenerAtomOreControllerImpl.class);
	

    /**
     * @see es.pode.visualizador.presentacion.atomOre.obtenerAtomOre.ObtenerAtomOreController#obtenerAtomOreURL(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.atomOre.obtenerAtomOre.ObtenerAtomOreURLForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerAtomOreXML(ActionMapping mapping, es.pode.visualizador.presentacion.atomOre.obtenerAtomOre.ObtenerAtomOreXMLForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {         
    	try{
	    	String areaCurricular=form.getAreaCurricular();
	    	Integer nivelAgregacion=form.getNivelAgregacion();
	    	String tesauro=form.getTesauro();
	    	Integer tipo=form.getTipo();
   	   		log.debug("Area Curricular = '" + areaCurricular + "' Nivel de Agregacion = '" + nivelAgregacion + "' Tesauro = '" + tesauro + "' Tipo = '" + tipo + "'");
			
		    /* Tipo de informe:
		     *                                 1 es nivel agregacion
		     *                                 2 es area curricular
		     *                                 3 es tesauro 
		     */
        	if (tipo==null){
        		log.error("El tipo nunca puede ser null.");
        	}
   	   		
	        switch (tipo) {
	            case 1:  
	            	if (nivelAgregacion==null){
	            		log.error("El nivel de agregacion no puede ser null si tipo = " + tipo);
	            	}
	            	areaCurricular = "";
	            	tesauro="";
	            	break;
	            case 2:  
	            	if (areaCurricular==null){
	            		log.error("El area curricular no puede ser null si tipo = " + tipo);
	            	}
		            nivelAgregacion=0;
	            	tesauro="";
	            	break;
	            case 3:  
	            	if (tesauro==null){
	            		log.error("El tesauro no puede ser null si tipo = " + tipo);
	            	}
	            	areaCurricular = "";
	            	nivelAgregacion=0;
	            	break;
	            default:
	            	log.error("El valor del tipo es incorrecto. tipo = " + tipo);
	            	areaCurricular = "";
	            	tesauro="";
		            nivelAgregacion=0;
		            break;
	        }
	        
   	   		es.pode.oaiore.negocio.servicio.TipoAtomOre atom = new es.pode.oaiore.negocio.servicio.TipoAtomOre(nivelAgregacion,areaCurricular,tesauro,tipo);
   	   		if (atom==null){
	   	   		log.error("No se pudo crear un objeto atom.");
			}
   	   		
			String xmlAtom = getSrvOaiOre().entregarAtom(atom);
			if (xmlAtom==null){
	   	   		log.error("No se pudo obtener XML del atom.");
			}
			
			response.setContentType("text/xml");
			PrintWriter writer = response.getWriter();  		
			writer.write(xmlAtom);
			writer.close();

    	}catch(Exception e){
			log.error("Error al intentar obtener el Atom.", e);
   			response.sendError(response.SC_NOT_FOUND);
		}
    }
         
}
