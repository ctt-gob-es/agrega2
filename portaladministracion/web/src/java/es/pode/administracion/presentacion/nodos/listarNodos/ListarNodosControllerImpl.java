/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.administracion.presentacion.nodos.listarNodos;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.buscar.negocio.administrar.servicio.NodoVO;
import es.pode.buscar.negocio.administrar.servicio.SrvNodoService;



/**
 * @see es.pode.administracion.presentacion.nodos.listarNodos.ListarNodosController
 */
public class ListarNodosControllerImpl extends ListarNodosController
{


	private static Logger log = Logger.getLogger(ListarNodosControllerImpl.class);



	 public final void listarNodos(ActionMapping mapping, es.pode.administracion.presentacion.nodos.listarNodos.ListarNodosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	    {
	    	
	    	
	    	try{
	    	
		    	log.debug("Listar Nodos");
	        	SrvNodoService service =  this.getSrvNodoService();
	        	NodoVO[] nodos = service.listarNodos();
	        	form.setNodosAsArray(nodos);
	        } catch (Exception e){
	        	log.error("Se ha producido un error al listar los nodos: " + e);
	        	throw new ValidatorException ("{errors.listarnodos}");
	        }

	    }

	    /**
	     * @see es.pode.administracion.presentacion.nodos.listarNodos.ListarNodosController#getIds(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.nodos.listarNodos.GetIdsForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	     */
	    public final void getIds(ActionMapping mapping, es.pode.administracion.presentacion.nodos.listarNodos.GetIdsForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	    {
	    	log.debug("getIds ");
	       try{
		    	List lista = ((FormularioListarNodosEliminarFormImpl)form).getIdRowSelection();
		    
		    	
		       if (lista == null){
		    	   log.error("LISTA NULL");
		    	   throw new ValidatorException ("{errors.eliminarnodos.idNulo}");
		       }
		     
		       form.setIds(lista);
		       log.info("Se van a eliminar los siguientes nodos: " + lista);
		       
	       } catch (ValidatorException e){
	    	   log.error("error al eliminar nodo "+e);
	    	   throw e;
		    	   
	       } catch (Exception e){
	    	   
	    	   log.error("Se ha producido un error al intentar recuperar los ids para borrar: " +e);
	    	   throw new ValidatorException ("{errors.eliminarnodos.idError}");
	    	   
	       }
	       
	     }
	
	}