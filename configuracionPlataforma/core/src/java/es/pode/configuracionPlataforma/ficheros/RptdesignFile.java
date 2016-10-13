package es.pode.configuracionPlataforma.ficheros;

import java.io.File;
import java.io.InputStream;

import org.apache.log4j.Logger;

import es.pode.configuracionPlataforma.negocio.dominio.PropiedadVO;


public class RptdesignFile {

	protected static final Logger logger = Logger.getLogger(RptdesignFile.class);

	/**
	 * Fichero/s destino de las variables de configuracion
	 */
	public static String directorioDestino = File.separator + "informes" + File.separator + "plantillasInformes";

	public static String[] ficherosPlantilla = new String[]{
		"/coberturaCurricular.rptdesign",
		"/coberturaCurricularFederada.rptdesign",
		"/estadoOdes.rptdesign",
		"/informeCarga.rptdesign",
		"/masDescargado.rptdesign",
		"/masMostrado.rptdesign",
		"/masPrevisualizado.rptdesign",
		"/masValorado.rptdesign",
		"/nivelAgregacion.rptdesign",
		"/nivelAgregacionFederada.rptdesign",
		"/odesCargados.rptdesign",
		"/odesIdiomaFederada.rptdesign",
		"/odesLicencias.rptdesign",
		"/odesPublicadosFederada.rptdesign",
		"/odesUsuario.rptdesign",
		"/operacionesRealizadas.rptdesign",
		"/procesosPlanificados.rptdesign",
		"/repositorio.rptdesign",
		"/tamanio.rptdesign",
		"/terminosBusqueda.rptdesign",
		"/usuarios.rptdesign"};


	/**
	 * Este metodo modifica el valor de las variables que le pasan en la plantilla del fichero *.rptdesign
	 * Supone que el par variable-valor no es nulo ni vacio y que cubren todas las variables que se encuentran en el fichero.
	 * @param prop Propiedad con el nombre de la variable y el valor.
	 * @param homeFile Directorio raiz donde se encuentra el fichero destino de la sustitucion.
	 */
	public static void modificaVariables(PropiedadVO[] prop, String homeFile)throws Exception
	{

		if (logger.isDebugEnabled())
			logger.debug("Vamos a actualizar los ficheros :" + directorioDestino);

		
		String ficheroDestinoDisco = "";

		Sed.crearDirConPermisos(homeFile + directorioDestino);


		//Por cada fichero, me voy a hacer su sustitucion.
		for (int i = 0; i < ficherosPlantilla.length; i++) {
			ficheroDestinoDisco = homeFile + directorioDestino + File.separator + ficherosPlantilla[i];			
			InputStream ipstrm = RptdesignFile.class.getResourceAsStream("/plantillas" +ficherosPlantilla[i]);		
			Sed.sustituir(ipstrm, ficheroDestinoDisco, prop);
			ipstrm.close();
		}

		if (logger.isDebugEnabled())
			logger.debug("Ficheros actualizados correctamente");

	}
	
	public static void crearBackup(String homeFile, String nombreInstancia)throws Exception
	{		
		
		String homeBackup = System.getProperty("jboss.server.data.dir");		
		
		String ficheroOrigen = "";
	
		String ficheroBackup = "";
		
		File dirBackup = new File(homeBackup + File.separator + nombreInstancia);
		
		if (!dirBackup.exists())
			dirBackup.mkdir();


		//Por cada fichero, me voy a hacer su sustitucion.
		for (int i = 0; i < ficherosPlantilla.length; i++) {
			ficheroOrigen = homeFile + directorioDestino + File.separator + ficherosPlantilla[i];			

			
			ficheroBackup = homeBackup + File.separator + nombreInstancia + File.separator + ficherosPlantilla[i];		
			
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
		
		//Por cada fichero, me voy a hacer su sustitucion.
		for (int i = 0; i < ficherosPlantilla.length; i++) {
			ficheroBackup= homeFile + directorioDestino + File.separator + ficherosPlantilla[i];					
			ficheroOrigen = homeBackup + File.separator + nombreInstancia + File.separator + ficherosPlantilla[i];		
			
			if (logger.isDebugEnabled())
				logger.debug("Vamos a restaurar backup del fichero :" + ficheroOrigen + " en :" + ficheroBackup);
					
			Sed.copiar(ficheroOrigen, ficheroBackup);
		}		
		
		if (logger.isDebugEnabled())
			logger.debug("Backup restaurado correctamente");

		return;
	}		
}
