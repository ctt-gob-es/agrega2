/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.soporte.seguridad.identidadFederada;

import org.apache.log4j.Logger;


import es.pode.soporte.seguridad.servicios.SrvIdentidadFederadaService;


public class WrapperSrvIdentidadFederada
{

	private static Logger log = Logger.getLogger(WrapperSrvIdentidadFederada.class);
	
	
	/**
     * Returns a reference to the srvIdentidadFederadaService imported service.
	 * @param url Url del nodo origen de la petición federada
     * @return es.pode.soporte.seguridad.servicios.SrvAdminUsuariosServiceImpl 
     * @throws java.lang.Exception Exception
     */
    protected final static es.pode.soporte.seguridad.servicios.SrvIdentidadFederadaService getSrvIdentidadFederadaService(String url)
        throws java.lang.Exception
    {
       //La url tendra que venir con el subdominio si es el caso
    	log.info("getSrvIdentidadFederadaService con url'"+url);
    	String srvIdentidadFederadaServiceEndPointAddress="";
        es.pode.soporte.seguridad.servicios.SrvIdentidadFederadaServiceService srvIdentidadFederadaService = new es.pode.soporte.seguridad.servicios.SrvIdentidadFederadaServiceServiceLocator();
        srvIdentidadFederadaServiceEndPointAddress = "http://"+url+"/adminusuarios/services/SrvIdentidadFederadaService";
        log.debug("srvIdentidadFederadaServiceEndPointAddress'"+srvIdentidadFederadaServiceEndPointAddress+"'");
       	((es.pode.soporte.seguridad.servicios.SrvIdentidadFederadaServiceServiceLocator)srvIdentidadFederadaService).setSrvIdentidadFederadaServiceEndpointAddress(srvIdentidadFederadaServiceEndPointAddress);				
       	es.pode.soporte.seguridad.servicios.SrvIdentidadFederadaService port = srvIdentidadFederadaService.getSrvIdentidadFederadaService();	    
        return port;
    }
    
    /**
	 * Comprueba si un usuario esta autenticado en el nodo que se pasa por parámetro
     * @param usuario usuario del que se quiere saber si tiene una sesión activa en otro nodo
     * @param nodoOrigen nodo en el que se va a consultar la sesión del usuario
	 *  
	 * @return datosUsuario UsurioVO
	 * */
	public static Boolean tieneIdentidadFederada(String usuario,String nodoOrigen)
	{
		
		SrvIdentidadFederadaService servicio = null;
		Boolean resultado = Boolean.FALSE;
		log.info("tieneIdentidadFederada");
		log.debug("usuario tiene valor'"+usuario+"'");
		log.debug("nodoOrigen tiene valor'"+nodoOrigen+"'");
		try
		{	
			 servicio = getSrvIdentidadFederadaService(nodoOrigen);
             log.debug("Ref. Servicio web " + servicio);
             resultado = servicio.isAutenticated(usuario);
             log.debug("El usuario: " + usuario+" esta autenticado "+resultado+" en el nodo "+nodoOrigen);
		}
		catch (Exception e) 
		{
			log.error("Se ha producido un error al invocar al servicio web de Identidad federada:",e);				
		}			
		
		return resultado;
	}
	
	public static Boolean eliminarSesionFederada(String usuario)
	{
		SrvIdentidadFederadaService servicio = null;
		Boolean resultado = Boolean.FALSE;
		
		try
		{	
			 servicio = getSrvIdentidadFederadaService();
             log.debug("Ref. Servicio web " + servicio);
             resultado = servicio.deleteUserSession(usuario);
             log.debug("El usuario: " + usuario+" se ha eliminado correctamente su sesion del servidor "+resultado);
		}
		catch (Exception e) 
		{
			log.error("Se ha producido un error al invocar al servicio web de Identidad federada:",e);				
		}			
		
		return resultado;
	}
	
	public static Boolean addSesionFederada(String usuario)
	{
		SrvIdentidadFederadaService servicio = null;
		Boolean resultado = Boolean.FALSE;
		
		try
		{	
			 servicio = getSrvIdentidadFederadaService();
             log.debug("Ref. Servicio web " + servicio);
             resultado = servicio.addUserSession(usuario);
             log.debug("El usuario: " + usuario+" se ha añadido correctamente su sesion del servidor "+resultado);
		}
		catch (Exception e) 
		{
			log.error("Se ha producido un error al invocar al servicio web de Identidad federada:",e);				
		}			
		
		return resultado;
	}
	
	
	 /**
     * Returns a reference to the srvAdminUsuariosService imported service.
     * @return es.pode.soporte.seguridad.servicios.SrvAdminUsuariosServiceImpl 
     * @throws java.lang.Exception Exception
     */
    protected final static es.pode.soporte.seguridad.servicios.SrvIdentidadFederadaService getSrvIdentidadFederadaService()
        throws java.lang.Exception
    {
        String srvIdentidadFederadaServiceFile="importedServices.properties";	    
        java.io.InputStream srvIdentidadFederadaServiceInputStream=WrapperSrvIdentidadFederada.class.getClassLoader().getResourceAsStream(srvIdentidadFederadaServiceFile);
        java.util.Properties srvIdentidadFederadaServiceProperties = new java.util.Properties();
        srvIdentidadFederadaServiceProperties.load(srvIdentidadFederadaServiceInputStream);
        String srvIdentidadFederadaServiceEndPointAddress="";
        srvIdentidadFederadaServiceEndPointAddress=(String) srvIdentidadFederadaServiceProperties.get("srvIdentidadFederadaServicePort");
        log.debug("srvIdentidadFederadaServiceEndPointAddress del fichero --> " + srvIdentidadFederadaServiceEndPointAddress);
			es.pode.soporte.seguridad.servicios.SrvIdentidadFederadaServiceService srvIdentidadFederadaService = new es.pode.soporte.seguridad.servicios.SrvIdentidadFederadaServiceServiceLocator();                                                                                                                                                                                                                                                    
			 if (!(srvIdentidadFederadaServiceEndPointAddress == null)&&(srvIdentidadFederadaServiceEndPointAddress.length()>0)) 
		////	((es.pode.soporte.seguridad.servicios.SrvAdminUsuariosServiceServiceLocator)srvIdentidadFederadaService).setSrvAdminUsuariosServiceEndpointAddress(srvIdentidadFederadaServiceEndPointAddress);
				 ((es.pode.soporte.seguridad.servicios.SrvIdentidadFederadaServiceServiceLocator)srvIdentidadFederadaService).setSrvIdentidadFederadaServiceEndpointAddress(srvIdentidadFederadaServiceEndPointAddress);	
    	es.pode.soporte.seguridad.servicios.SrvIdentidadFederadaService port = srvIdentidadFederadaService.getSrvIdentidadFederadaService();	    
        return port;
    }
	
	
}
