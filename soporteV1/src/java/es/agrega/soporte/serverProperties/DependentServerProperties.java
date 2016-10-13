package es.agrega.soporte.serverProperties;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;

/**
 * Clase encargada de leer las propiedades dependeintes del servidor
 * 
 * @author aleal
 * @deprecated Desde la version 2.0 esta clase sirve de adaptador para leer las
 *             propiedades del viejo fichero de configuracion
 *             dependentServer.properties que ahora se encuentran en
 *             agrega.properties. Usar
 *             es.agrega.soporte.es.agrega.soporte.agregaProperties
 *             .AgregaProperties en su lugar
 */
public class DependentServerProperties implements DependentServerPropertiesItf
{
	private static DependentServerPropertiesItf obj = null;
	private ResourceBundle dependentProperties = null;
	
	private Logger logger = Logger.getLogger(DependentServerProperties.class);
	
	private DependentServerProperties()
	{
		super();
		this.dependentProperties = ResourceBundle.getBundle(AgregaProperties.PROPERTIES_FILE_NAME);
		
	}

	public static DependentServerPropertiesItf getInstance()
    {
		if (obj == null)
	    	  obj = new DependentServerProperties();

	      return obj;
    }
	

	public String getProperty(String sKey) 
	{
		String propertyValue = null;
		if (sKey != null) {
			// la propiedad ${server.on}.path se ha renombrado a server.path. Chequeo para que los tag que la usen no llamen a la erronea.
			if(sKey.equals("path")) propertyValue = this.dependentProperties.getString("server." + sKey);
			else propertyValue = this.dependentProperties.getString(sKey);
		}
		return propertyValue;
	}
	
	
	/* (non-Javadoc)
	 * @see es.agrega.soporte.serverProperties.DependentServerPropertiesItf#getNodos()
	 */

	public String getNodos() 
	{
		String propertyValue = null;		
		propertyValue = this.dependentProperties.getString(AgregaProperties.PROPERTY_NODOS);		
		return propertyValue;
	}
	
	/* (non-Javadoc)
	 * @see es.agrega.soporte.serverProperties.DependentServerPropertiesItf#getCCAAs()
	 */
	public String getCCAAs() 
	{
		String propertyValue = null;		
		propertyValue = this.dependentProperties.getString(AgregaProperties.PROPERTY_CCAAS);		
		return propertyValue;
	}
	
	/* (non-Javadoc)
	 * @see es.agrega.soporte.serverProperties.DependentServerPropertiesItf#getUrlNodo()
	 */
	public String getUrlNodo() 
	{
		String propertyValue = null;
		
		String host = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOST);
		String subdominio = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SUBDOMINIO);
		
		propertyValue = (subdominio == null || "".equals(subdominio))?"http://"+host:"http://"+host+subdominio;
		if(logger.isDebugEnabled()) logger.debug("getUrlNodo() devuelve : " + propertyValue);
		return propertyValue;
	}

	/* (non-Javadoc)
	 * @see es.agrega.soporte.serverProperties.DependentServerPropertiesItf#getUrlConsejeriaNodo()
	 */
	public String getUrlConsejeriaNodo() {
		String propertyValue = null;		
		propertyValue = this.dependentProperties.getString(AgregaProperties.PROPERTY_URLCONSEJERIANODO);		
		return propertyValue;
	}

	/* (non-Javadoc)
	 * @see es.agrega.soporte.serverProperties.DependentServerPropertiesItf#getServerOn()
	 */
	public String getServerOn() {
		return AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVER_ON);
	}

	/* (non-Javadoc)
	 * @see es.agrega.soporte.serverProperties.DependentServerPropertiesItf#getCCAA()
	 */
	public String getCCAA() {
		return AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVER_ON + ".ccaa");
	}

	/* (non-Javadoc)
	 * @see es.agrega.soporte.serverProperties.DependentServerPropertiesItf#getNodo()
	 */
	public String getNodo() {
		
		return AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVER_ON + ".nodo");
	}

	public String getServerPath() {
		// No uso constante de AgregaProperties, ya que es una solución provisional hasta que podamos eliminar esta propiedad. 
		return AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVER_PATH);
	}
	
}
