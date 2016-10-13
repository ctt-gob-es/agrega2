package es.pode.soporte.menu;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.tiles.ComponentContext;

public class MenuMetanavegacionControllerImpl extends MenuController {
	
	public static final String MENU_METANAVEGACION = MenuController.getPropertyValue("key_metanavegacion",MenuController.FILE_NAME_PROPERTIES);
	public static final String MENU_METANAVEGACION_KEY = MenuController.getPropertyValue("menu_metanavegacion",MenuController.FILE_NAME_PROPERTIES);
	private static Logger logger = Logger.getLogger(MenuMetanavegacionControllerImpl.class);

	/**
	* Process http request (controller)
	* @param request The HTTP request we are processing
	* @param response The HTTP response we are creating
	* @param servletContext
	*
	* @exception IOException if an input/output error occurs
	* @exception ServletException if a servlet exception occurs
	*/
	public void execute(ComponentContext context, HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception 
	{
		
		//TODO: Se chequeará el usuario para ver los permisos

		ArrayList arrayMenu = new ArrayList(3);   
		try {
			//TODO: revisar cuando tenamos los idiomas
			arrayMenu = this.menuFilter(MENU_METANAVEGACION, this.getRoles(), request.getRequestURI(), request.getQueryString(), servletContext, request);
		} catch (Exception e) {
			logger.error("ERROR: Mensaje " + e.getMessage() + " Causa = " + e.getCause()+" - ",e);
		}finally {
			context.putAttribute(MENU_METANAVEGACION_KEY, arrayMenu);
		}
	}

}
