/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * 
 */
package es.agrega.soporte.agregaProperties;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * @author aleal
 *
 */
public class AgregaPropertiesImpl implements AgregaProperties {

	private ResourceBundle properties = null;
	private static AgregaProperties agregaProperties = null;
	
	private static Logger log = Logger.getLogger(AgregaPropertiesImpl.class);
	
	/**
	 * Inicializa un objeto único con las propiedades de configuracion cargadas en memoria.
	 */
	private AgregaPropertiesImpl()
	{
		super();
		properties = ResourceBundle.getBundle(AgregaProperties.PROPERTIES_FILE_NAME);
		if (log.isDebugEnabled())
			log.debug("Se han cargado los properties correctamente");
	}

	/**
	 * Obtiene una referencia al singleton.
	 */
	public static AgregaProperties getInstance()
    {
		if (AgregaPropertiesImpl.agregaProperties == null)
		{
			AgregaPropertiesImpl.agregaProperties = new AgregaPropertiesImpl();
		}
		
	    return AgregaPropertiesImpl.agregaProperties;
    }

	/* (non-Javadoc)
	 * @see es.agrega.soporte.agregaProperties.AgregaProperties#getProperty(java.lang.String)
	 */
	public String getProperty(String sKey) 
	{
		if (log.isDebugEnabled())
			log.debug("Se va a devolver el valor de la propiedad["+sKey+"]");
		String salida = null;
		try {
			salida=properties.getString(sKey);
		} catch (Exception e) {
			if(log.isDebugEnabled())
				log.error("No se encontró propiedad "+sKey);
		}
		if (salida==null) {
			salida="";
		}
		return salida;
	}
	
	public String getUrlNodo() 
	{
		String propertyValue = null;
		
		String host = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOST);
		String subdominio = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SUBDOMINIO);
		
		propertyValue = (subdominio == null || "".equals(subdominio))?"http://"+host:"http://"+host+subdominio;
		if(log.isDebugEnabled()) log.debug("getUrlNodo() devuelve : " + propertyValue);
		return propertyValue;
	}
}
