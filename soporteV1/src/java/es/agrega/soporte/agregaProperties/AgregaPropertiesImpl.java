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
	 * Inicializa un objeto �nico con las propiedades de configuracion cargadas en memoria.
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
				log.error("No se encontr� propiedad "+sKey);
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
