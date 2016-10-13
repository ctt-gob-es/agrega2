/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.migracion;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import org.apache.log4j.Logger;
///import org.apache.log4j.PropertyConfigurator;
import es.pode.parseadorXML.castor.Lom;
import es.pode.parseadorXML.castor.Manifest;
import es.pode.parseadorXML.lomes.lomesAgrega.LomAgrega;
import es.pode.parseadorXML.scorm2004.agrega.ManifestAgrega;
import es.pode.parseadorXML.SCORM2004Dao;
import es.pode.soporte.utiles.ficheros.*;


public class Migracion
{
	private static java.util.Properties pAuditoriaProperties = null;
	
	private static Logger logger = Logger.getLogger(Migracion.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		System.out.println("Migracion de odes");
		logger.info("Migracion de odes");
		String repositorio = System.getProperty("repositorio");
		logger.info("Path del repositorio de odes "+repositorio);
		String sobrescribir = System.getProperty("sobrescribir");
		logger.info("sobrescribir vale "+sobrescribir);
			
		//Se recorren las carpetas del repositorio si existe el fichero licencias.txt no hacemos nada en caso contrario
		//sacamos del fichero imsmanifest.xml la licencia y copiamos de uploads el fichero licencia.txt
		//que se corresponda con la licencia seleccionada
		//Para que se encuentren las licencias tengo q quitar los  blancos puedo hacer un split por blanco
		
		logger.info("Comenzamos a explorar la carpeta repositorio buscando el fichero licencia.txt");
		validarLicencia(repositorio,sobrescribir);
		System.out.println("Finalización migracion de odes");
	}
	
	
	private static String getPropertyValue(String nombreFichero, String key) throws IOException {
		logger.debug("key "+key);
		InputStream fIsSpringProperties = ClassLoader.getSystemResourceAsStream(nombreFichero);
		System.out.println("ClassLoader.getSystemClassLoader() "+ClassLoader.getSystemClassLoader());
		logger.debug("fIsSpringProperties "+fIsSpringProperties);
		if (pAuditoriaProperties == null)
		{
			pAuditoriaProperties = new java.util.Properties();
			pAuditoriaProperties.load(fIsSpringProperties);
		}
		fIsSpringProperties.close();

		// devolvemos la propiedad
		return pAuditoriaProperties.getProperty(key);
			
	}
	
	private static void validarLicencia(String repositorio,String sobrescribir)
	{
			
		java.io.File fPath = null;
		java.io.File fileLicenciaPath = null;
		java.io.File[] subPaths = null;
		java.io.File[] subPathsLicencias = null;
		String[] licencia = null;
		String textoLicencia = "";
		String textoLicenciaOriginal = "";
		
		if(repositorio==null)
			{
				logger.error("El path repositorio de odes es nulo");
				throw new NullPointerException("path==null");
			}
		
		fPath = new java.io.File(repositorio);
		if(!fPath.isDirectory()) {
			logger.error("El path repositorio " + repositorio + " no es un directorio. Por favor chequee la ruta y vuelva a ejecutar el script");
		} else {
			
			subPaths = fPath.listFiles();
			int contador = 0;
			for(int i=0;i<subPaths.length;i++) {
				try
				{
				
				if(subPaths[i].isDirectory()) {
					// Comprobar si esta entrada tiene un imsmanifest.xml. En
					// caso contrario, internarse si recursivo=true
					String[] manifiestos = subPaths[i].list(new FilenameFilter() {
						public boolean accept(File path, String name) {
							return "imsmanifest.xml".equals(name);
						}
					});
					if(manifiestos!=null && manifiestos.length > 0) {
						// El path corresponde a un posible ODE descomprimido
						logger.info("EL PATH " + subPaths[i] + " ES UN ODE");
						//Comprobamos si existe el fichero licencia.txt en el caso de no existir lo copiamos del directorio uploads
						 licencia = subPaths[i].list(new FilenameFilter() {
							public boolean accept(File path, String name) {
								return "licencia.txt".equals(name);
							}
						});
						
						
						
						if((licencia == null) || (licencia.length == 0) || (licencia.length < -1) || (sobrescribir.equalsIgnoreCase("y")))
						{
							
							/////Obtenemos la licencia
							logger.info("No tiene el fichero licencia.txt o tiene activa la opcion de sobrescribir");
							File extraeSubmanifest = new File(subPaths[i], "imsmanifest.xml");
							SCORM2004Dao scorm2004Dao = new SCORM2004Dao();
							Manifest imsmanifest = scorm2004Dao.parsearODEEager(extraeSubmanifest);
							ManifestAgrega manAgrega = new ManifestAgrega(imsmanifest);
							Lom lom = manAgrega.obtenerLom(manAgrega.getManifest().getIdentifier(), null);
							LomAgrega lomAgrega = new LomAgrega(lom);
							textoLicencia = lomAgrega.getRightsAgrega().getDerechosDeAutor();
							textoLicenciaOriginal = textoLicencia;
							textoLicencia = textoLicencia.replaceAll(":", "-");
							System.out.println("TEXTOLICENCIA "+textoLicencia);
							System.out.println("licencias/textoLicencia/licencia.txt "+"licencias/"+textoLicencia+"/licencia.txt");
							fileLicenciaPath = new java.io.File("licencias/"+textoLicencia+"/licencia.txt");
						
							if(!(fileLicenciaPath.exists()))
							{
								logger.error("No existe el fichero licencia.txt para el tipo de licencia "+textoLicenciaOriginal);
								logger.info("---------");
								
							}else
							{
								logger.info("Se añade el fichero licencia.txt para la licencia "+textoLicenciaOriginal);
								//Copiamos en el repositorio el fichero licencia.txt
								UtilesFicheros.copiar(fileLicenciaPath,subPaths[i]);
								logger.info("---------");
							}
						}else
						{
							logger.info("El ode tiene ya un fichero licencia.txt");
							logger.info("---------");
						}
					}else
					{
						
						//Es un directorio volvemos a llamar al metodo
						validarLicencia(subPaths[i].getPath(),sobrescribir);
					}
					 
	}
				}catch(Exception e)
				{
					logger.debug("i vale "+i);
					logger.error("Exception durante la obtencion de la licencia del ode "+subPaths[i]+" se produce un error "+e);
					continue;
				}
			}
}
}
}
