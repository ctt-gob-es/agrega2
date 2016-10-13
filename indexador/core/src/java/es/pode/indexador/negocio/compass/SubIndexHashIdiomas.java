/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.indexador.negocio.compass;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.compass.core.Property;
import org.compass.core.engine.SearchEngineException;

import es.pode.soporte.i18n.I18n;

public class SubIndexHashIdiomas implements org.compass.core.engine.subindex.SubIndexHash{

	private String prefijo_subindex = null;
	private String campo_idioma = null;
	private static Logger logger = Logger.getLogger(SubIndexHashIdiomas.class);
	
	
	public String[] getSubIndexes() {
		String[] nombresIndices =null;
		try {
			Properties props = new Properties();
			props.load(this.getClass().getResourceAsStream("/indexador.properties"));
			campo_idioma = props.getProperty("campo_idiomaBusqueda");
			prefijo_subindex = props.getProperty("prefijo_subindex");
			String[] idiomas = I18n.getInstance().obtenerIdiomasBuscables();
			nombresIndices =new String[idiomas.length];
			for(int i = 0; i < idiomas.length;i++){
				nombresIndices[i]=prefijo_subindex + idiomas[i];
			}
		} catch (Exception e) {
			logger.error("ERROR: fichero no encontrado: indexador.properties",
					e);
			throw new RuntimeException(e);
		}
		
		return nombresIndices;
	}

	public String mapSubIndex(String arg0, Property[] arg1) throws SearchEngineException {
		for (int j = 0; j < arg1.length; j++) {
			if (arg1[j].getName().endsWith(campo_idioma))
			{
				return (prefijo_subindex + arg1[j].getStringValue());
			}	
		}
		return prefijo_subindex;
	}

}
