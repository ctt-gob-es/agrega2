
package es.agrega.soporte.sitemapProperties;

import java.util.Date;
import java.util.ResourceBundle;
import java.io.File;
import java.net.URL;

import org.apache.log4j.Logger;

/**
 * @author aadelgado
 *
 */
public class SitemapPropertiesImpl implements SitemapProperties {

	private ResourceBundle properties = null;
	private static SitemapProperties sitemapProperties = null;
	private static Date fecha = null;
	
	private static final String PROPERTIES_EXT=".properties";
	
	private static Logger log = Logger.getLogger(SitemapPropertiesImpl.class);
	
	private SitemapPropertiesImpl()
	{
		super();
		properties = ResourceBundle.getBundle(SitemapProperties.PROPERTIES_FILE_NAME);
		SitemapPropertiesImpl.fecha = new Date();
		if (log.isDebugEnabled())
			log.debug("Se han cargado los properties correctamente");
	}

	public static SitemapProperties getInstance()
    {
		if (SitemapPropertiesImpl.sitemapProperties == null)
		{
			SitemapPropertiesImpl.sitemapProperties = new SitemapPropertiesImpl();
		}
		/*
		 * Con este c�digo cualquier cambio que se realice en el fichero agrega.properties en tiempo de ejecuci�n se recoger�a din�micamente
		 * */
		  if (SitemapPropertiesImpl.sitemapProperties == null || SitemapPropertiesImpl.isFileChanged())
		  {
			  	if (log.isDebugEnabled() && SitemapPropertiesImpl.fecha == null)
			  		log.debug("Primera instancia");
			  	else if (log.isDebugEnabled() && SitemapPropertiesImpl.fecha != null )
					log.debug("Ha cambiado la fecha FECHA["+SitemapPropertiesImpl.fecha.getTime()+"]");
			  	SitemapPropertiesImpl.sitemapProperties = new SitemapPropertiesImpl();
		  }

	      return SitemapPropertiesImpl.sitemapProperties;
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
	
	public static boolean isFileChanged()
	{
		if (log.isDebugEnabled())
			log.debug("Se va chequear si el fichero ha cambiado");
		URL url = SitemapPropertiesImpl.class.getResource("/"+
				SitemapPropertiesImpl.PROPERTIES_FILE_NAME+
				SitemapPropertiesImpl.PROPERTIES_EXT);
		if (log.isDebugEnabled())
			log.debug("La ruta del fichero es ["+url.getFile()+"]");
		File file = new File(url.getFile());
		long lastModified = file.lastModified();
		if (log.isDebugEnabled())
			log.debug("La fecha del ultimo cambio fue ["+SitemapPropertiesImpl.fecha.getTime()+"] y la fecha actual del fichero es ["+lastModified+"]");
		
		return (lastModified != SitemapPropertiesImpl.fecha.getTime());
	}
	

}
