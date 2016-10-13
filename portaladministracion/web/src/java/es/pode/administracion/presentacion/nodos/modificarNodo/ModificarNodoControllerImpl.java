/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.administracion.presentacion.nodos.modificarNodo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;

import es.pode.buscar.negocio.administrar.servicio.CcaaVO;
import es.pode.buscar.negocio.administrar.servicio.NodoVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;


/**
 * @see es.pode.administracion.presentacion.nodos.modificarNodo.ModificarNodoController
 */
public class ModificarNodoControllerImpl extends ModificarNodoController
{


	private static Logger log = Logger.getLogger(ModificarNodoControllerImpl.class);
	ResourceBundle ficheroRecursos = null;


    /**
     * @see es.pode.administracion.presentacion.nodos.modificarNodo.ModificarNodoController#modificarNodo(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.nodos.modificarNodo.ModificarNodoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
	public final void modificarNodo(ActionMapping mapping, es.pode.administracion.presentacion.nodos.modificarNodo.ModificarNodoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
    	
    	try{
    		
    		
			String nodo = form.getNodo().trim();
			String url = form.getUrl().trim();
			String puerto = form.getPuerto().trim();
			Long ccaaId = form.getCcaa();
			Long id = form.getId();
			String urlWS = form.getUrlWS().trim();
			String idNodo = form.getIdNodo().trim();
			
			
			//cargamos el NodoVO con los datos originales que tiene antes de modificarlo
			NodoVO nodoCargado = new NodoVO();
			nodoCargado = this.getSrvNodoService().obtenerNodo(id);

			
		    // Validaciones de los campos que recogemos del formulario
		    Pattern mask = Pattern.compile("[^\\¿\\?\\¡\\!\\>\\#\\&\\<\\@\\$\\\'\\\"]+");
		    Matcher matcher = null;

			
		    //Validamos el nombre del nodo
		    
			if (nodo == null || nodo.equals("")) {
				log.error("el nombre del nodo no puede estar vacio");
				throw new ValidatorException("{errors.modificarnodo.nodo}");
			}
			
		    matcher = mask.matcher(nodo);
		    if (!matcher.matches()) {
		    	if(log.isDebugEnabled())log.debug("nodo caracter ilegal");
		    	throw new ValidatorException(
							"{errors.modificarnodo.nodo.caracterIlegal}");
		    }
			
		    
		    //Validamos la url del nodo
		    
			if (url == null || url.equals("")) {
				log.error("la url del nodo no puede estar vacio");
				throw new ValidatorException("{errors.modificarnodo.url}");
			}
			
		    matcher = mask.matcher(url);
		    if (!matcher.matches()) {
		    	if(log.isDebugEnabled())log.debug("nodo caracter ilegal");
		    	throw new ValidatorException(
							"{errors.modificarnodo.url.caracterIlegal}");
		    }
			
		    
			//Validamos la url del Web Sevices
		    
			if (urlWS == null || urlWS.equals("")) {
				log.error("la url del WebService no puede estar vacio");
				throw new ValidatorException("{errors.modificarnodo.urlWS}");
			}
			
//			if (idNodo == null || idNodo.equals("")) {
//				log.error("el identificador del nodo no puede estar vacio");
//				throw new ValidatorException("{errors.modificarnodo.idNodo}");
//			}
			
		    matcher = mask.matcher(urlWS);
		    if (!matcher.matches()) {
		    	if(log.isDebugEnabled())log.debug("nodo caracter ilegal");
		    	throw new ValidatorException(
							"{errors.modificarnodo.urlWS.caracterIlegal}");
		    }
		    
		    
		    //Comprobamos que no existe ningun nodo con el nombre introducido para dar de alta
		    
		    
		    if(!(nodoCargado.getNodo().equalsIgnoreCase(nodo)))
		    {
			    if(this.getSrvNodoService().existeNombreNodo(nodo).booleanValue())
			    	throw new ValidatorException("{errors.altanodo.nombreNodoYaExiste}");   
		    }
		    
		    
		    //Comprobamos la url del nodo para evitar que un nodo se federe consigo mismo
		    
		    String urlHost=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOST);
		    if(log.isDebugEnabled())log.debug("El valor de urlHost es "+urlHost);
		    if(urlHost.trim().equalsIgnoreCase(url.trim()))
		    {
		    	if(log.isDebugEnabled())log.debug("Se esta intentando federar un nodo consigo mismo");
		    	throw new ValidatorException(
				"{errors.altanodo.url.federadoConsigoMismo}");
		    }
		    
		    
		    //Compruebo si ya existen otro nodo en la BD con los mismos valores de url y urlWS
		    
		    if(!(nodoCargado.getUrl().equalsIgnoreCase(url)) && !(nodoCargado.getUrlWS().equalsIgnoreCase(urlWS)))
		    {
			    Boolean estaDadoAlta = this.getSrvNodoService().estaDadoAlta(url, urlWS);
			    if(log.isDebugEnabled())log.debug("estaDadoAlta "+estaDadoAlta);
			    if(estaDadoAlta.booleanValue())
			    {
			    	if(log.isDebugEnabled())log.debug("esta dado de alta");
			    	throw new ValidatorException("{errors.altanodo.url.yaEstaDadoAlta}");
			    }
		    }
		    
		    //Comprobamos si se introduce puerto que este sea numerico
		    if(puerto != null && !puerto.matches("[0-9]*"))
		    	throw new ValidatorException("{errors.altanodo.puerto.NoNumerico}");
		    
			
    		NodoVO nodoVO = new NodoVO();
    		
	    	nodoVO.setId(id);
	    	nodoVO.setNodo(nodo);
	    	nodoVO.setUrl(url);
			nodoVO.setPuerto(puerto);
			nodoVO.setUrlWS(urlWS);
			nodoVO.setIdNodo(idNodo);			
			if (ccaaId != null){
				CcaaVO ccaa = new CcaaVO();
				ccaa.setId(ccaaId);
				nodoVO.setCcaa(ccaa);
			}
	    	
	    	//Comprobamos si al crear el nodo todo ha ido bien o se ha producido algun error//No en este caso recogemos el identificador del nodo modificado
			Long codigo_devuelto = this.getSrvNodoService().modificarNodo(nodoVO);
			log.debug("El codigo recibido es"+codigo_devuelto);
			if(codigo_devuelto.intValue() == (-1)){
				log.debug("El codigo de error"+codigo_devuelto);
				throw new ValidatorException("{errors.altanodo.urlWS.timeout}");
			}
			
			form.setIdModificado(codigo_devuelto);
	    	form.setResultado("ok.modificarnodo");
	    	if(log.isDebugEnabled())
				log.debug("Lo que ha devuelto es["+codigo_devuelto+"]");
			if(id.equals(codigo_devuelto)){
				form.setResultado("ok.modificarnodo");
			}
			else{
				form.setResultado("fallo.modificarnodo");
			}
    	} 
		catch (ValidatorException e)
		{
			throw e;
    	} catch (Exception e){
    		log.error("Se ha producido un error al modificar el nodo: " + e);
	    	form.setResultado("fallo.modificarnodo");
    	}
    	
    }

    /**
     * @see es.pode.administracion.presentacion.nodos.modificarNodo.ModificarNodoController#cargarNodo(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.nodos.modificarNodo.CargarNodoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cargarNodo(ActionMapping mapping, es.pode.administracion.presentacion.nodos.modificarNodo.CargarNodoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
    	try{
    		
    		
    		//recogemos las ccaa de la bbdd y las internacionalizamos desde el properties
			Locale locale = new Locale(LdapUserDetailsUtils.getIdioma());
			ficheroRecursos = ResourceBundle.getBundle("application-resources", request.getLocale());
			
			List ccaa = Arrays.asList(this.getSrvNodoService().obtenerCCAAs());
			CcaaVO[] ccaaVOArray = (CcaaVO[])ccaa.toArray();

			List ccaaList = new ArrayList();
			for(int i=0; i<ccaaVOArray.length; i++)
			{
				if(ccaaVOArray[i].getCodigo() != null)
				{
					String descripcion = null;
					
					try
					{
						descripcion = ficheroRecursos.getString(ccaaVOArray[i].getCodigo());
					}
					catch(Exception ex)
					{
						log.warn("El codigo de la comunidad " + ccaaVOArray[i].getCodigo() + " no esta en el fichero de internacionalizacion");
						descripcion = ccaaVOArray[i].getDescripcion();
					}
					ccaaList.add(descripcion.toLowerCase());
					ccaaVOArray[i].setDescripcion(descripcion);
				}
				else
					ccaaVOArray[i].setCodigo(""); 
				
			}

			Collections.sort(ccaaList);
			ccaa = ordenarLista(ccaaList, ccaaVOArray);
			
			// Rellena el combo de ccaa
    		((ModificarNodoFormImpl)form).setCcaaBackingList(ccaa, "id", "descripcion");
    		
    		Long id = Long.parseLong(request.getParameter("id"));
    		if (id == null){
    			throw new ValidatorException ("{errors.modificarnodo.idNulo}");
    		}
    		NodoVO nodo = this.getSrvNodoService().obtenerNodo(id);
    		form.setId(id);
    		form.setNodo(nodo.getNodo());
    		form.setUrl(nodo.getUrl());
    		form.setUrlWS(nodo.getUrlWS());
    		form.setIdNodo(nodo.getIdNodo());
    		if (nodo.getCcaa()!=null){
    			form.setCcaa(nodo.getCcaa().getId());
    		}
    		form.setPuerto(nodo.getPuerto());
    		
    	}catch (ValidatorException e){
    		log.error("Error al recuperar: " + e);
    		throw e;
    	}catch (Exception e){
    		log.error("Error al recuperar el nodo: " + e);
    		throw e;
    	}
    }
    
	private List ordenarLista (List listaOrdenada, CcaaVO[] array){
		CcaaVO[] ccaaOrdenado = new CcaaVO[array.length];
		int posicion=0;
		for (int i=0; i<array.length;i++){
			posicion = listaOrdenada.indexOf(array[i].getDescripcion().toLowerCase());
			ccaaOrdenado[posicion]= array[i];
		}
		return Arrays.asList(ccaaOrdenado);

	}

}