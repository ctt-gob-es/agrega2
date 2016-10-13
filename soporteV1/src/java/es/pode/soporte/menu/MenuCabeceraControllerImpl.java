/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.soporte.menu;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.tiles.ComponentContext;

public class MenuCabeceraControllerImpl extends MenuController {
	
	private static Logger logger = Logger.getLogger(MenuCabeceraControllerImpl.class);
	public static final String MENU_CABECERA = MenuController.getPropertyValue("key_cabecera",MenuController.FILE_NAME_PROPERTIES);
	public static final String MENU_CABECERA_KEY = MenuController.getPropertyValue("menu_cabecera",MenuController.FILE_NAME_PROPERTIES);

	/**
	* Process http request (controller)
	* @param context The current Tile context, containing Tile attributes
	* @param request The HTTP request we are processing
	* @param response The HTTP response we are creating
	*
	* @exception IOException if an input/output error occurs
	* @exception ServletException if a servlet exception occurs
	*/
	public void execute(ComponentContext context, HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception 
	{
		
		//TODO: Se chequeará el usuario para ver los permisos
		ArrayList arrayMenu = new ArrayList(3);   
		try {
			arrayMenu = this.menuFilter(MENU_CABECERA, this.getRoles(), request.getRequestURI(),request.getQueryString(), servletContext, request);
		} catch (Exception e) {
			//if (logger.isDebugEnabled())
				//logger.error("Mensaje: " + e.getMessage() + " Causa: " + e.getCause(), e);
			logger.error(e);
		}finally {
			context.putAttribute(MENU_CABECERA_KEY, arrayMenu);
		}
	}
}
