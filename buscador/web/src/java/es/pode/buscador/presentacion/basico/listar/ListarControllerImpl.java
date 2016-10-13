/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.buscador.presentacion.basico.listar;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.buscador.presentacion.BuscarSession;
import es.pode.buscador.presentacion.avanzado.buscarAvanzado.BuscarAvanzadoControllerImpl;
import es.pode.buscador.presentacion.basico.detallar.DetallarControllerImpl;
import es.pode.soporte.constantes.ConstantesAgrega;



/**
 * @see es.pode.buscador.presentacion.basico.listar.ListarController
 */
public class ListarControllerImpl extends ListarController
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8488797616127926807L;
	
	private static Logger logger = Logger.getLogger(ListarControllerImpl.class);
	public static final String LOCALE_KEY = "org.apache.struts.action.LOCALE";
	private static final String NOMBRECOOKIE = "idiomaNavegacionCookie";
	
	
	public void prepararConsulta(ActionMapping mapping, PrepararConsultaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			log("ListarControllerImpl - prepararConsulta.");
			form.setBuscContenido(form.getBuscadorGeneral());
	    	form.setBusqSimpleAvanz(DetallarControllerImpl.BUSCAR_SIMPLE);
	    	try{
	    		if("true".equals(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NEUTRO))) form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_NEUTRO);
		    	else form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_BUSCADOR);
	    		
	    		if(form.getIdiomaNavegacion()!= null && !form.getIdiomaNavegacion().equals("")){
	    			this.setCookieIdioma(response, form.getIdiomaNavegacion());
	    			request.getSession().setAttribute(ConstantesAgrega.DEFAULT_LOCALE, new Locale(form.getIdiomaNavegacion()));
	    		}
	    	}catch(Exception e){
	    		log("Error recuperando la propiedad neutro del agregaProperties");
	    		form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_BUSCADOR);
	    	}
	    	this.setBuscarSession(request, new BuscarSession());
		} catch (Exception ex) {
     		logger.error("ListarControllerImpl - prepararConsulta ERROR:",ex);
			saveErrorMessage(request,BuscarAvanzadoControllerImpl.MENSAJE_GENERICO_BUSQUEDA);
		}
	}
	
	private void log(String traza){
		if (logger.isDebugEnabled())logger.debug(traza);
	}
	
	private void setCookieIdioma(HttpServletResponse response, String valorCookie)
    {
    	if (logger.isDebugEnabled()) logger.debug("cambiarIdioma-- Se pone la cookie con nombre: ["+NOMBRECOOKIE+"]");
		Cookie cookie = new Cookie(NOMBRECOOKIE, valorCookie);
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24*30*600);
		response.addCookie(cookie);

		if (logger.isDebugEnabled()) logger.debug("cambiarIdioma-- Despues de añadir la cookie " + NOMBRECOOKIE + ": " + cookie + " con valor: " + valorCookie + " al objeto response");
	}

}