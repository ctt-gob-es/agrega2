/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.indexador.negocio.compass;

import java.io.File;

import org.apache.log4j.Logger;
import org.compass.core.config.CompassConfiguration;
import org.compass.core.config.CompassEnvironment;
import org.compass.core.config.ConfigurationException;
import org.compass.spring.LocalCompassBeanPostProcessor;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;


public class CompassPostProcessor implements LocalCompassBeanPostProcessor{

	private static Logger logger = Logger.getLogger(CompassPostProcessor.class);
//la idea es inyectar mediante spring la dependencia con el servicio externo o llamar directamente al locator del servicio externo	
	public void process(CompassConfiguration config) throws ConfigurationException {

		String homeJboss = "";
		try {
			homeJboss = System.getProperty("jboss.server.home.dir");
		} catch (Exception e) {
			logger.error("Hubo un problema al recuperar el directorio HOME de la instancia de JBoss");
		}
		
		String connection = homeJboss+File.separator+AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_INDICE);
		if(logger.isDebugEnabled())logger.debug("Localización de los indices ----> " + connection);
		config.setSetting(CompassEnvironment.CONNECTION, connection);
	}

}