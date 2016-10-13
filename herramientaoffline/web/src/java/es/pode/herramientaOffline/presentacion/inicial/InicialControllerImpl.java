/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.herramientaOffline.presentacion.inicial;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.contenidos.negocio.descargas.servicio.SrvDescargas;
import es.pode.herramientaOffline.negocio.soporte.Utilidades;

/**
 * @see es.pode.herramientaOffline.presentacion.inicial.InicialController
 */
public class InicialControllerImpl extends InicialController
{

	private static Logger logger = Logger.getLogger(InicialControllerImpl.class);

	/**
     * @see es.pode.herramientaOffline.presentacion.inicial.InicialController#iniciar(org.apache.struts.action.ActionMapping, es.pode.herramientaOffline.presentacion.inicial.IniciarForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
	@Override
    public final void iniciar(ActionMapping mapping, es.pode.herramientaOffline.presentacion.inicial.IniciarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // Limpio el parametro de sesion urlCerrar
		if(request.getSession().getAttribute("urlCerrar")!=null) request.getSession().removeAttribute("urlCerrar");
		
		        
		try{
			
			//Obtener datos del properties
			String url = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVIDOR_UPDATES);
			String puerto = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVIDOR_UPDATES_PORT);
			
	        String version = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.VERSION);
	        logger.debug("Nuestra versión es: "+version);
	        form.setVersion(version);
	        
			SrvDescargas servicio=Utilidades.obtenerConexionSrvDescargas(url, puerto);
			
	        String versionact = servicio.obtenerVersion();
	        logger.debug("La versión actual es: "+versionact);
	        
	        String ver1[] = version.split("\\.");
	        String ver2[] = versionact.split("\\.");
	        int i = 0;
	        boolean laHay = false;

	        logger.debug("Hemos obtenido un array de longitud: "+ ver1.length + " y otro con longitud: "+ ver2.length );
	       
	        //si deja de existir un valor en el string de nuestra versión, se acaba la comparación
	        while ((ver1.length > i) && (!laHay)){
	        	
	        	logger.debug("Comparamos los valores: " + Integer.parseInt(ver1[i])+" y "+ Integer.parseInt(ver2[i]));
	        	//comparo los dígitos de la versión
	        	if (Integer.parseInt(ver1[i]) < Integer.parseInt(ver2[i])){
	        		
	        		logger.debug("Se necesita actualización");
	        		laHay = true;
	        		
	        	}else{
	        		
	        		logger.debug("No se necesita actualización");
	        		laHay = false;        		
	        	}
	        	logger.debug("El valor de i es: " + i);
	        	i++;
	        }
	        
	        logger.debug("El valor de i ahora es: " + i);
	        //probamos si hay más dígitos
	        if (ver2.length > i){
	        	
	        	logger.debug("Se necesita actualización");
	        	laHay = true;
	        }
	        
	        form.setHayUpdate(laHay);
	        logger.debug("El valor de laHay es: " + laHay);
	        form.setVersionRemota(versionact);
	        logger.debug("El valor de la version del servidor es: " + versionact);		
	        
		}catch (IOException e){
			
			logger.debug("Error en la conexión con el servicio", e);
		}
    }

}