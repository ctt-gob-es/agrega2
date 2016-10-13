/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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
