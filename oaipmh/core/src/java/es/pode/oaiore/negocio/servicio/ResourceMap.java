/**
 * 
 */
package es.pode.oaiore.negocio.servicio;

import static es.pode.oaiore.negocio.servicio.OAIOREProperties.creador;
import static es.pode.oaiore.negocio.servicio.OAIOREProperties.licencia;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;

import org.apache.log4j.Logger;

import se.kb.oai.ore.Aggregation;
import se.kb.oai.ore.impl.AtomFactory;
import se.kb.oai.ore.impl.AtomSerializer;

/**
 * @author dgonzalezd
 *
 */
public class ResourceMap {
	
	static AtomFactory factory = new AtomFactory();
	private static Logger logger = Logger.getLogger(ResourceMap.class);
	se.kb.oai.ore.ResourceMap resourceMapOriginal;
	
	private ResourceMap() {
	}
	
	static ResourceMap nuevoResourceMap(String ruta, String urlNodo) throws URISyntaxException {
		ResourceMap map = new ResourceMap(); 
		map.resourceMapOriginal = factory.newResourceMap(urlNodo+ruta);

		map.resourceMapOriginal.setCreator(creador.toString());
		map.resourceMapOriginal.setModified(new Date());
		map.resourceMapOriginal.setRights(licencia.toString());
		return map;
	}
	
	void toFile(String ruta) throws IOException {
		AtomSerializer serializer = new AtomSerializer();
		//Comprobar la ruta
		File fichero = new File(ruta);
		if(!fichero.exists()){
			logger.debug("Ruta no existe, creamos "+fichero);
			File carpeta;
			if(!ruta.endsWith("/")) {
				logger.debug(fichero.getCanonicalFile()+" es fichero");
				carpeta=fichero.getParentFile();
				logger.debug("Intentaremos crear "+carpeta.getCanonicalPath());
			} else {
				logger.debug(fichero.getCanonicalFile()+" es directorio");
				carpeta=fichero;
			}
			if (!carpeta.exists()) {
				if (!carpeta.mkdirs()) {
					throw new IOException("No se pudo crear ruta "
							+ carpeta.getCanonicalPath());
				}
			}
		}
		logger.debug("Guardamos en "+ruta);
		serializer.serializeToFile(this.resourceMapOriginal, new File(ruta));
		logger.debug("Se guardo en "+ruta);
	}

	public Aggregation getAggregation() {
		return resourceMapOriginal.getAggregation();
	}
}
