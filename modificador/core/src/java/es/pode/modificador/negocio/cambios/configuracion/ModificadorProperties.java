package es.pode.modificador.negocio.cambios.configuracion;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 
 * Wrapper para obtener una propiedad del fichero modificador.properties. Esta
 * clase se registrara como singleton en el contexto de spring.
 * 
 * @author fjespino
 * 
 */
public class ModificadorProperties {
	private static Properties props = null;
	private static Logger logger = Logger.getLogger(ModificadorProperties.class);

	public ModificadorProperties() {
		if(props==null) {
			Properties properties = new Properties();
			InputStream is = this.getClass().getResourceAsStream("/modificador.properties");
			if(is==null) {
				logger.error("No se pudo abrir modificador.properties para lectura");
			} else {
				try {
					properties.load(is);
					props=properties;
				} catch (IOException e) {
					logger.error("No se pudo abrir modificador.properties para lectura");
				} finally {
					try {
						is.close();
					} catch (Exception e) {
						// No hacer nada
					}
				}
			}
		}
	}
	
	public String getProperty(String key) {
		String resultado = null;
		if(props!=null) {
			resultado = props.getProperty(key);
		} else logger.error("El objeto de propiedades no esta instanciado");
		if(resultado==null) logger.error("No se ha encontrado la propiedad " + key);
		if(logger.isDebugEnabled()) logger.debug("Propiedad recuperada " + key +" = " + resultado);
		return resultado;
	}
}
