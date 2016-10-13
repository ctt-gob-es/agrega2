/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.soporte.estadisticas;

import java.util.ResourceBundle;
import org.apache.log4j.Logger;

public class EstadisticasPropertiesImpl implements EstadisticasProperties {

	private ResourceBundle properties = null;
	private static EstadisticasProperties estadisticasProperties = null;
	
	//private static final String PROPERTIES_EXT=".properties";
	
	private static Logger log = Logger.getLogger(EstadisticasPropertiesImpl.class);
	
	private EstadisticasPropertiesImpl()
	{
		super();
		properties = ResourceBundle.getBundle(EstadisticasProperties.PROPERTIES_FILE_NAME);
		if (log.isDebugEnabled())
			log.debug("Se han cargado los properties correctamente");
	}

	public static EstadisticasProperties getInstance()
    {
		if (EstadisticasPropertiesImpl.estadisticasProperties == null)
		{
			EstadisticasPropertiesImpl.estadisticasProperties = new EstadisticasPropertiesImpl();
		}
		
	      return EstadisticasPropertiesImpl.estadisticasProperties;
    }

	/* (non-Javadoc)
	 * @see es.agrega.soporte.agregaProperties.AgregaProperties#getProperty(java.lang.String)
	 */
	public String getProperty(String sKey) 
	{
		if (log.isDebugEnabled())
			log.debug("Se va a devolver el valor de la propiedad["+sKey+"]");
		return this.properties.getString(sKey);
	}
	
}