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