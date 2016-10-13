package es.pode.configuracionPlataforma.ficheros;

import java.io.File;
import java.io.InputStream;

import org.apache.log4j.Logger;

import es.pode.configuracionPlataforma.negocio.dominio.PropiedadVO;


public class SearchPluginFile {
	
	protected static final Logger logger = Logger.getLogger(SearchPluginFile.class);
	
	/**
	 * Fichero/s destino de las variables de configuracion
	 */
	public static String directorioDestino = File.separator + ".." + File.separator + ".." + File.separator + "bin" + File.separator + "uploads" + File.separator + "searchPlugin";		
	public static String ficheroDestino = "searchPlugin.xml";
	
	public static String ficheroPlantilla = "/plantillas/searchPlugin.xml";

	
	/**
	 * Este metodo modifica el valor de las variables que le pasan en la plantilla del fichero searchPlugin.xml
	 * Supone que el par variable-valor no es nulo ni vacio y que cubren todas las variables que se encuentran en el fichero.
	 * @param prop Propiedad con el nombre de la variable y el valor.
	 * @param homeFile Directorio raiz donde se encuentra el fichero destino de la sustitucion.
	 */
	public static void modificaVariables(PropiedadVO[] prop, String homeFile)throws Exception
	{

		String ficheroDestinoDisco = homeFile + File.separator + directorioDestino + File.separator +ficheroDestino;		
		if (logger.isDebugEnabled())
			logger.debug("Vamos a actualizar el fichero :" + ficheroDestinoDisco);

		Sed.crearDirConPermisos(homeFile);					
		InputStream ipstrm = AuthBackendFile.class.getResourceAsStream(ficheroPlantilla);
		Sed.sustituir(ipstrm, ficheroDestinoDisco,prop);
		
		if (logger.isDebugEnabled())
			logger.debug("Fichero actualizado correctamente");

		return;	
	}
	
	public static void crearBackup(String homeFile, String nombreInstancia)throws Exception
	{		
		
		String homeBackup = System.getProperty("jboss.server.data.dir");		
		
		String ficheroOrigen = homeFile + File.separator + directorioDestino + File.separator +ficheroDestino;
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
		
		String ficheroBackup = homeFile + File.separator + directorioDestino + File.separator +ficheroDestino;
		String ficheroOrigen = homeBackup + File.separator + nombreInstancia + File.separator +ficheroDestino;
		
		if (logger.isDebugEnabled())
			logger.debug("Vamos a restaurar backup del fichero :" + ficheroOrigen + " en :" + ficheroBackup);
						
		Sed.copiar(ficheroOrigen, ficheroBackup);
		
		if (logger.isDebugEnabled())
			logger.debug("Backup restaurado correctamente");

		return;
	}	
}
