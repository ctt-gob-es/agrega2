package es.agrega.soporte.agregaProperties;

import java.io.File;
import java.net.URL;
import java.util.Date;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

public class AgregaPropertiesTest extends TestCase {
	
	public static Logger log = Logger.getLogger(AgregaPropertiesTest.class);

	public void testGetProperties() 
	{
		String sImgApachePath = this.getPropertyApachePath();
		assertEquals(sImgApachePath, "/galeriaimg");
	}

	public void testChangeFile() 
	{
		try
		{
			String sImgApachePath = this.getPropertyApachePath();
			
			assertEquals(sImgApachePath, "/galeriaimg");
			
			Date fecha = new Date();
			if (log.isDebugEnabled())
				log.debug("Voy a cambiar la fecha del fichero a ["+fecha.getTime()+"]");
			
			URL url = AgregaPropertiesImpl.class.getResource("/"+
					AgregaPropertiesImpl.PROPERTIES_FILE_NAME+
					".properties");
			
			if (log.isDebugEnabled())
				log.debug("La ruta del fichero es ["+url.getFile()+"]");
			File file = new File(url.getFile());
			
			file.setLastModified(fecha.getTime());
			if (log.isDebugEnabled())
				log.debug("Cojo de nuevo la propiedad chequeando que ha cambiado la hora");
			sImgApachePath = this.getPropertyApachePath();
			assertEquals(sImgApachePath, "/galeriaimg");
		}
		catch (Exception ex)
		{
			log.error("Se ha producido una excepcion en testChangeFile", ex);
			ex.printStackTrace();
		}
		

	}
	
	private String getPropertyApachePath()
	{
		String sImgApachePath = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PROPERTY_IMAGE_APACHE_PATH);
		if (log.isDebugEnabled())
			log.debug("El valor de la propiedad ["+AgregaProperties.PROPERTY_IMAGE_APACHE_PATH+"] es [" + sImgApachePath+"]");
		return sImgApachePath;
		
	}
}
