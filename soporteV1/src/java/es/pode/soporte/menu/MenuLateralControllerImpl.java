/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.soporte.menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.tiles.ComponentContext;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.soporte.buscador.servicios.PalabraClaveVO;
import es.pode.soporte.buscador.servicios.ParamPalabrasClave;
import es.pode.soporte.buscador.servicios.PrioridadPalabrasClaveVO;
import es.pode.soporte.buscador.servicios.SrvBuscadorService;
import es.pode.soporte.configuracionPortal.ConfiguracionPortal;
import es.pode.soporte.configuracionPortal.ConfiguracionPortalImpl;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

public class MenuLateralControllerImpl extends MenuController {
	private static final String MENU_LATERAL = MenuController.getPropertyValue("menu_lateral",MenuController.FILE_NAME_PROPERTIES);
	private static final String MENU_LATERAL_CONTENIDOS = MenuController.getPropertyValue("menu_lateral_contenidos",MenuController.FILE_NAME_PROPERTIES);
	private static final String MENU_LATERAL_PLATAFORMA = MenuController.getPropertyValue("menu_lateral_plataforma",MenuController.FILE_NAME_PROPERTIES);
	private static final String MENU_LATERAL_PORTAL = MenuController.getPropertyValue("menu_lateral_portal",MenuController.FILE_NAME_PROPERTIES);
	private static final String TAMANIO_LETRA_NUBE = MenuController.getPropertyValue("tamaniosLetraTagNube",MenuController.FILE_NAME_PROPERTIES);
	private static final String KEY_MENU_LATERAL_CONTENIDOS = "submenu_contenidos";
	private static final String KEY_MENU_LATERAL_PLATAFORMA = "submenu_plataforma";
	private static final String KEY_MENU_LATERAL_PORTAL = "submenu_portal";
	private static final String SEPARADOR_CLAVES = "&";

	private static Logger logger = Logger.getLogger(MenuLateralControllerImpl.class);
	/**
	* Process http request (controller)
	* @param context The current Tile context, containing Tile attributes
	* @param request The HTTP request we are processing
	* @param response The HTTP response we are creating
	* @param servletContext
	*
	* @exception IOException if an input/output error occurs
	* @exception ServletException if a servlet exception occurs
	*/
	public void execute(ComponentContext context, HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception 
	{
		ConfiguracionPortal configuracionPortal = ConfiguracionPortalImpl.getInstance();
		
		ArrayList arrayMenu = new ArrayList(3);
		
		try {
			String sUrl = request.getRequestURI();
			String queryString = request.getQueryString();
			//TODO: revisar cuando tengamos los idiomas
			Iterator it = MenuController.menuHandler.hashMenu.keySet().iterator();
			boolean encontrado = false;
			Menu menu = null;
			while (!encontrado && it.hasNext())
			{
				menu = MenuController.getMenu(String.valueOf(it.next()));
				if (menu != null)
					encontrado = MenuController.checkUrlPattern(sUrl, queryString, menu.getPattern());
			}
			//TODO: Se chequeará el usuario para ver los permisos
			if (encontrado)
				arrayMenu = this.menuFilter(menu.getName(), this.getRoles(), sUrl, servletContext,request);

		} catch (Exception e) {
			if (logger.isDebugEnabled())
				logger.debug("ERROR: Mensaje =" + e.getMessage() + " Causa = " + e.getCause());
		}finally	{
			context.putAttribute(MENU_LATERAL, arrayMenu);
			ArrayList menuConfiguracion=generarMenus(KEY_MENU_LATERAL_PORTAL, arrayMenu);
			context.putAttribute(MENU_LATERAL_PORTAL, menuConfiguracion);
			ArrayList menuPlataforma=generarMenus(KEY_MENU_LATERAL_PLATAFORMA, arrayMenu);
			context.putAttribute(MENU_LATERAL_PLATAFORMA, menuPlataforma);
			ArrayList menuContenidos=generarMenus(KEY_MENU_LATERAL_CONTENIDOS, arrayMenu);		
			context.putAttribute(MENU_LATERAL_CONTENIDOS, menuContenidos);
			
			
		}
		
		//Evaluamos el valor de RSS en la DDBB, para pintarlo o no.
		Integer rss_conf = 1;
		Integer rss_noconf = 0;
		Integer rss = configuracionPortal.getRss();
		if(rss == 1){
			context.putAttribute("RSS", "Mostrar");
		}
		else {context.putAttribute("RSS", "");}
		
		// Cargamos la nube de tags 
		ArrayList nubeTags = new ArrayList();
		try {
			Integer etiquetas = configuracionPortal.getEtiquetas();
			if(etiquetas == 1){
				String maximoTags = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NUMERO_TAGS);
		    	if (maximoTags == null || maximoTags.equals("")) maximoTags = "13";
				//if (logger.isDebugEnabled()) logger.debug( "Cargamos la nube de tags" );
				
				SrvBuscadorService servicio = getSrvBuscadorService();
				
				ParamPalabrasClave paramPalabrasClave = new ParamPalabrasClave();
				paramPalabrasClave.setIdiomaBusqueda(obtenerIdiomaBusqueda());
				paramPalabrasClave.setIdiomaNavegacion(((Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage());
				paramPalabrasClave.setNumeroResultados(new Integer(maximoTags));
				PrioridadPalabrasClaveVO prioridadPalabrasClaveVO = servicio.obtenerPalabrasClave(paramPalabrasClave);
				PalabraClaveVO[] palabraClaveVOs = prioridadPalabrasClaveVO.getPalabrasClave();
				if (logger.isDebugEnabled())logger.debug( "palabraClaveVOs.lenght="+palabraClaveVOs.length );
				PalabraClave[] palabrasClave = new PalabraClave[palabraClaveVOs.length];
				// Arrays.sort(palabraClaveVOs);
				String[] tamanios = TAMANIO_LETRA_NUBE.split(SEPARADOR_CLAVES);
				for (int i=0; i<palabraClaveVOs.length; i++){
					palabrasClave[i] = new PalabraClave();
					palabrasClave[i].setPalabraClave(palabraClaveVOs[i].getPalabraClave());
					palabrasClave[i].setRanking(palabraClaveVOs[i].getRanking().toString());
					palabrasClave[i].setTamanio(tamanios[i]);
				}
				GeneradorAleatorios gen = new GeneradorAleatorios(palabraClaveVOs.length);
				for (int i=0; i<palabraClaveVOs.length; i++){
					nubeTags.add(palabrasClave[Integer.parseInt(gen.getValor())]);
				}
				if(logger.isDebugEnabled())logger.debug("La nubeTags tiene una longitud de <" + nubeTags.size() + "> y se ha cargado correctamente");
			}
			
			}catch (Exception e){
				logger.error("NO ha cargado correctamente la nube de tags - ",e);
			}finally{
				context.putAttribute("NUBE_TAGS", nubeTags);
			}
			
	}
	

	private String obtenerIdiomaBusqueda() throws Exception{

        String idiomaNavegacion = "";
        if(LdapUserDetailsUtils.estaAutenticado()){
            try{
                 idiomaNavegacion=LdapUserDetailsUtils.getIdiomaBusqueda();
                 if (idiomaNavegacion == null) idiomaNavegacion = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
            } catch (Exception e) {
                 logger.error("Error en al obtener idioma búsqueda usuario LDAP, se recoge idioma de fichero propiedades.",e);
                 idiomaNavegacion = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
            }
        }else idiomaNavegacion = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
            return idiomaNavegacion;
	}
	
	
	private ArrayList generarMenus(String nomMenu, ArrayList arrayMenu){
		ArrayList menuCon=new ArrayList();
		String[] arraySubmenus;
		ArrayList listaSubmenus;
		arraySubmenus = (MenuController.getPropertyValue(nomMenu,MenuController.FILE_NAME_PROPERTIES)).split(",");
		listaSubmenus = new ArrayList(Arrays.asList(arraySubmenus));
		for(int i=0;i< arrayMenu.size();i++){
				MenuItem menu=(MenuItem)(arrayMenu.get(i));
				String id=menu.getId();
				if(listaSubmenus.contains(id)){
					menuCon.add(menu);
				}			
		}
		
		return menuCon;
	}
	
	public static class PalabraClave {
		String palabraClave;
		String ranking;
		String tamanio;
		
		public String getPalabraClave() {
			return palabraClave;
		}
		public void setPalabraClave(String palabraClave) {
			this.palabraClave = palabraClave;
		}
		public String getRanking() {
			return ranking;
		}
		public void setRanking(String ranking) {
			this.ranking = ranking;
		}
		public String getTamanio() {
			return tamanio;
		}
		public void setTamanio(String tamanio) {
			this.tamanio = tamanio;
		}
		
	}
	
	protected final es.pode.soporte.buscador.servicios.SrvBuscadorService getSrvBuscadorService()
    throws java.lang.Exception
{
	
    String srvBuscadorServiceFile="importedServices.properties";	    
    java.io.InputStream srvBuscadorServiceInputStream=this.getClass().getClassLoader().getResourceAsStream(srvBuscadorServiceFile);
    java.util.Properties srvBuscadorServiceProperties = new java.util.Properties();
    srvBuscadorServiceProperties.load(srvBuscadorServiceInputStream);
    String srvBuscadorServiceEndPointAddress="";
    srvBuscadorServiceEndPointAddress=(String) srvBuscadorServiceProperties.get("srvBuscadorServicePort");
    if(logger.isDebugEnabled())logger.debug("srvBuscadorServiceEndPointAddress del fichero --> <" + srvBuscadorServiceEndPointAddress+">");
	es.pode.soporte.buscador.servicios.SrvBuscadorServiceService srvBuscadorService = new es.pode.soporte.buscador.servicios.SrvBuscadorServiceServiceLocator();                                                                                                                                                                                                                                                    
    if (srvBuscadorServiceEndPointAddress.length()>0) 
		((es.pode.soporte.buscador.servicios.SrvBuscadorServiceServiceLocator)srvBuscadorService).setSrvBuscadorServiceEndpointAddress(srvBuscadorServiceEndPointAddress);
	es.pode.soporte.buscador.servicios.SrvBuscadorService port = srvBuscadorService.getSrvBuscadorService();	    
    return port;
    
}
}
