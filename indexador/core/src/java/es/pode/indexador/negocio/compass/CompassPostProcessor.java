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