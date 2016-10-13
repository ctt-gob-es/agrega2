package es.pode.configuracionPlataforma.ficheros;

import java.io.File;
import java.io.InputStream;

import org.apache.log4j.Logger;

import es.pode.configuracionPlataforma.negocio.dominio.PropiedadVO;



public class OpmlFile {

	protected static final Logger logger = Logger.getLogger(OpmlFile.class);
		
	/**
	 * Fichero/s destino de las variables de configuracion
	 */
	public static String ficheroDestino = File.separator +".."+File.separator +".." +File.separator +"bin"+ File.separator +"uploads" +File.separator + "rss" + File.separator ;
	
	public static String[] ficherosPlantilla = new String[]{
		"/plantillas/agrega_atom_ca.opml",
		"/plantillas/agrega_atom_en.opml",
		"/plantillas/agrega_atom_es.opml",
		"/plantillas/agrega_atom_eu.opml",
		"/plantillas/agrega_atom_gl.opml",
		"/plantillas/agrega_atom_va.opml",
		"/plantillas/agrega_rss_ca.opml",
		"/plantillas/agrega_rss_en.opml",
		"/plantillas/agrega_rss_es.opml",
		"/plantillas/agrega_rss_eu.opml",
		"/plantillas/agrega_rss_gl.opml",
		"/plantillas/agrega_rss_va.opml"};

	
	/**
	 * Este metodo modifica el valor de las variables que le pasan en la plantilla del fichero *.opml
	 * Supone que el par variable-valor no es nulo ni vacio y que cubren todas las variables que se encuentran en el fichero.
	 * @param prop Propiedad con el nombre de la variable y el valor.
	 * @param homeFile Directorio raiz donde se encuentra el fichero destino de la sustitucion.
	 */
	public static void modificaVariables(PropiedadVO[] prop, String homeFile)throws Exception
	{
		if (logger.isDebugEnabled())
			logger.debug("Vamos a actualizar los ficheros :" + ficheroDestino);
		
		Sed.crearDirConPermisos(homeFile + ficheroDestino);
		
//		Por cada fichero, me voy a haer su sustitucion.
		for (int i = 0; i < ficherosPlantilla.length; i++) {			
			String ficheroDestinoDisco = homeFile + ficheroDestino + ficherosPlantilla[i].substring(ficherosPlantilla[i].lastIndexOf("/")+1,ficherosPlantilla[i].length());
			InputStream ipstrm = OpmlFile.class.getResourceAsStream(ficherosPlantilla[i]);	
			Sed.sustituir(ipstrm, ficheroDestinoDisco, prop);
		}
		
		if (logger.isDebugEnabled())
			logger.debug("Ficheros actualizados correctamente");

		return;
	}
	
	public static void crearBackup(String homeFile, String nombreInstancia)throws Exception
	{		
		
		String homeBackup = System.getProperty("jboss.server.data.dir");		
		
		String ficheroOrigen = "";
	
		String ficheroBackup = "";
		
		File dirBackup = new File(homeBackup + File.separator + nombreInstancia);
		
		if (!dirBackup.exists())
			dirBackup.mkdir();

		for (int i = 0; i < ficherosPlantilla.length; i++) {			
			ficheroOrigen = homeFile + ficheroDestino + ficherosPlantilla[i].substring(ficherosPlantilla[i].lastIndexOf("/")+1,ficherosPlantilla[i].length());
			ficheroBackup = homeBackup + File.separator + nombreInstancia + File.separator + ficherosPlantilla[i].substring(ficherosPlantilla[i].lastIndexOf("/")+1,ficherosPlantilla[i].length());
			
			if (logger.isDebugEnabled())
				logger.debug("Vamos a crear backup del fichero :" + ficheroOrigen + " en :" + ficheroBackup);
					
			Sed.copiar(ficheroOrigen, ficheroBackup);
		}
				
		if (logger.isDebugEnabled())
			logger.debug("Backup creado correctamente");

		return;
	}

	public static void restaurarBackup(String homeFile, String nombreInstancia)throws Exception
	{		
		
		String homeBackup = System.getProperty("jboss.server.data.dir");		
		
		String ficheroOrigen = "";
	
		String ficheroBackup = "";		

		for (int i = 0; i < ficherosPlantilla.length; i++) {			
			ficheroBackup = homeFile + ficheroDestino + ficherosPlantilla[i].substring(ficherosPlantilla[i].lastIndexOf("/")+1,ficherosPlantilla[i].length());
			 ficheroOrigen = homeBackup + File.separator + nombreInstancia + File.separator + ficherosPlantilla[i].substring(ficherosPlantilla[i].lastIndexOf("/")+1,ficherosPlantilla[i].length());
			
			if (logger.isDebugEnabled())
				logger.debug("Vamos a restaurar backup del fichero :" + ficheroOrigen + " en :" + ficheroBackup);
					
			Sed.copiar(ficheroOrigen, ficheroBackup);
		}
				
		if (logger.isDebugEnabled())
			logger.debug("Backup restaurado correctamente");

		return;
	}
}
