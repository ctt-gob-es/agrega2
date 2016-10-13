package es.pode.configuracionPlataforma.ficheros;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

import org.apache.log4j.Logger;

import es.pode.configuracionPlataforma.negocio.dominio.PropiedadVO;


public class AgregaPropertiesFile {

	protected static final Logger logger = Logger.getLogger(AgregaPropertiesFile.class);
	
	
	/**
	 * Fichero/s destino de las variables de configuracion
	 */
	public static String ficheroDestino 				= "agrega.properties";
	
	public static String ficheroPlantilla = "/plantillas/agrega.properties";
		
	
	/**
	 * Este metodo modifica el valor de las variables que le pasan en la plantilla del fichero agrega.properties
	 * Supone que el par variable-valor no es nulo y que cubren todas las variables que se encuentran en el fichero.
	 * @param prop Propiedad con el nombre de la variable y el valor.
	 * @param typeJboss Tipo de jboss.
	 * @param homeFile Directorio raiz donde se encuentra el fichero destino de la sustitucion.
	 */
	public static void modificaVariables(PropiedadVO[] prop, String homeFile)throws Exception
	{		
		
		crearBackup(homeFile,"default");
		
		String ficheroDestinoDisco = homeFile + File.separator + "conf" + File.separator +ficheroDestino;		

		if (logger.isDebugEnabled())
			logger.debug("Vamos a actualizar el fichero :" + ficheroDestinoDisco);
				
		Sed.crearDirConPermisos(homeFile);
		
		InputStream ipstrm = AgregaPropertiesFile.class.getResourceAsStream(ficheroPlantilla);
		Sed.sustituir(ipstrm, ficheroDestinoDisco,prop);
		
		if (logger.isDebugEnabled())
			logger.debug("Fichero actualizado correctamente");

		return;
	}

	public static void crearBackup(String homeFile, String nombreInstancia)throws Exception
	{		
		
		String homeBackup = System.getProperty("jboss.server.data.dir");		
		
		String ficheroOrigen = homeFile + File.separator + "conf" + File.separator +ficheroDestino;	
		String ficheroBackup = homeBackup + File.separator + nombreInstancia + File.separator +ficheroDestino;
		
		File dirBackup = new File(homeBackup + File.separator + nombreInstancia);
		
		if (!dirBackup.exists())
			dirBackup.mkdir();

		if (logger.isDebugEnabled())
			logger.debug("Vamos a crear backup del fichero :" + ficheroOrigen + " en :" + ficheroBackup);
						
		Sed.copiar(ficheroOrigen, ficheroBackup);
		
		if (logger.isDebugEnabled())
			logger.debug("Backup creado correctamente");

		return;
	}
	
	public static void restaurarBackup(String homeFile, String nombreInstancia)throws Exception
	{		
		
		String homeBackup = System.getProperty("jboss.server.data.dir");		
		
		String ficheroBackup = homeFile + File.separator + "conf" + File.separator +ficheroDestino;	
		String ficheroOrigen = homeBackup + File.separator + nombreInstancia + File.separator +ficheroDestino;
		
		if (logger.isDebugEnabled())
			logger.debug("Vamos a restaurar backup del fichero :" + ficheroOrigen + " en :" + ficheroBackup);
						
		Sed.copiar(ficheroOrigen, ficheroBackup);
		
		if (logger.isDebugEnabled())
			logger.debug("Backup restaurado correctamente");

		return;
	}
}
