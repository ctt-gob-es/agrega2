/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.administracion.presentacion.repositoriosExternos.verRepositoriosExternos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.buscar.negocio.nodosSQI.servicio.NodoSQIVO;



/**
 * @see es.pode.administracion.presentacion.repositoriosExternos.verNodoSQI.VerNodoSQIController
 */
public class VerNodoSQIControllerImpl extends VerNodoSQIController
{


	private static Logger logger= Logger.getLogger(VerNodoSQIControllerImpl.class);


	public void cargaNodoSQI(ActionMapping mapping, CargaNodoSQIForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
    		
	    	Long id = Long.valueOf(request.getParameter("id"));
	    	Long[] ids=new Long[1];
	    	ids[0]=id;
	    	NodoSQIVO[] nodos = this.getSrvGestionSqiService().consultaNodosSQI(ids);
			NodoSQIVO nodo=nodos[0];
			form.setClave(nodo.getClave());
			form.setDescripcionNodo(nodo.getDescripcionNodo());
			form.setGestorSesion(nodo.getGestorSesion());
			form.setId(id);
			form.setIdentificadorSesion(nodo.getIdentificadorSesion());
			form.setLenguajeConsulta(nodo.getLenguajeConsulta());
			form.setLenguajeRespuesta(nodo.getLenguajeRespuesta());
			form.setNombreNodo(nodo.getNombreNodo());
			form.setUrlServicio(nodo.getUrlServicio());
			form.setUsuario(nodo.getUsuario());
			if(logger.isDebugEnabled())logger.debug("Estamos cargando el nodo con identificador "+id);
			if(nodo.getImagenNodo() != null && !nodo.getImagenNodo().equals("")){
			//Esta variable no se debe pintar en la jsp como tal; hay que utilizarla para pintar la imagen:uploads/imagenesNodosExternos/nodo+nombreImagen
				String url=nodo.getImagenNodo();
				if(logger.isDebugEnabled())logger.debug("Vamos a insertarle la url de la imagen "+url);
	    		form.setImagenNodo(url);
			}else{
				form.setImagenNodo(null);
			}
			//En la jsp hay que insertar la imagen en la parte derecha

			
    	} catch (Exception e) {
    		logger.error("Error: " + e);
    		throw new ValidatorException("{errors.vernodoSQI}");
    	}
		
	}









}