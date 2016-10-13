package agrega;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import es.pode.parseadorXML.SCORM2004Dao;
import es.pode.parseadorXML.castor.Manifest;
import es.pode.parseadorXML.castor.Resource;

public class UnificacionSonidos {
	
	static Logger log =  Logger.getLogger(EliminacionSonidosDuplicados.class);
	static int BUFFER_SIZE =8192;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		
		try
		{
			
			log.info("Se inicia la ejecución de la eliminación de sonidos duplicados.");
			if (args==null || args.length<3)
			{
				System.out.println("Parametros incorrectos:");
				System.out.println("Sintaxis correcta:    java -jar unificacionSonidos.jar numeroODEsUnificar directorioODEsOriginales directorioODEsUnificados");
				System.out.println("Ejemplo de ejecución: java -jar unificacionSonidos.jar 300 dirODEsOriginales dirODEsUnificados");
				return;
			}
			
			int iNumODEsProcesar = 0;
			try
			{				
				iNumODEsProcesar = Integer.parseInt(args[0]);
			}catch (Exception e) {
				System.out.println("El parámetro numeroODESEliminar no es correcto (debe ser numérico) :" + args[0]);
						
				return;
			}																		
			
			//configurarLog();
			
						
			log.info("Inicio ejecución :" + new Date());

			procesarODEs(iNumODEsProcesar,args[1],args[2]);
									
			log.info("Ejecución finalizada :" + new Date());

		}catch (Exception e) {
			log.fatal("Error: ",e);
		}
	}
	
	public static void procesarODEs(int numODEsProcesar, String dirODEsOriginales, String dirODEsUnificados)
	{
		File dOriginalMPEG = new File(dirODEsOriginales + "/mpeg");
		
		String pathOriginalOGG = dirODEsOriginales + "/ogg/";
				
		File fOgg = null;
		
		File dODEsUnificados = new File(dirODEsUnificados);
		if(!dODEsUnificados.exists())
		{
			dODEsUnificados.mkdir();
		}
		
		if (dOriginalMPEG.exists() && dOriginalMPEG.isDirectory())
		{
			int iOdesUnificados =0;
			
			File[] ficheros = dOriginalMPEG.listFiles();
			
			for (int x = 0; x < ficheros.length; x++) {
				
				log.info("Procesando fichero : " + iOdesUnificados + ". Titulo : " + ficheros[x].getName());
				if (ficheros[x].isFile())
				{
					fOgg = new File( pathOriginalOGG+ficheros[x].getName());
					
					if (fOgg.exists() && fOgg.isFile() && ficheros[x].isFile())
					{					
						unificarODEs(dODEsUnificados, ficheros[x], fOgg);
					}
					else
						log.error("NO EXISTE LA VERSION OGG DEL ODE : " +ficheros[x].getName());
					
					iOdesUnificados++;
					
					if (iOdesUnificados >= numODEsProcesar)
						break;
				}else
					log.error("NO ES UN ZIP");
			}
			
		}
	}
	
	public static void unificarODEs(File dODEsUnificados, File ficMPEG, File ficOgg) 
	{
		/*
		 * 1. Descomprimir el zip del ODE en MPEG 
		 * 2. Descomprimir el zip del ODE en OGG 
		 * 3. Localizar sonidos en carpeta "Contenido" en OGG 
		 * 4. Copiar ogg a ODE MPEG 
		 * 5. Crear html5 con enlace a mpeg y ogg 
		 * 6. Modificar manifest para que apunte a html5 
		 * 7. Zipear ODE en mpeg
		 */

		try {
			
			// 1. Descomprimir el zip del ODE en MPEG 
			String dirSalidaMPEG = crearEstructuraODE(ficMPEG.getAbsolutePath());			
			descomprimirZipCarpeta(ficMPEG.getAbsolutePath(), dirSalidaMPEG);
			// 2. Descomprimir el zip del ODE en OGG 
			String dirSalidaOGG = crearEstructuraODE(ficOgg.getAbsolutePath());			
			descomprimirZipCarpeta(ficOgg.getAbsolutePath(), dirSalidaOGG);
			
			// 3. Localizar sonidos en carpeta "Contenido" en OGG 
			File dCarpetaContenidoMPEG = new File(dirSalidaMPEG+"/Contenido");			
			File dCarpetaContenidoOGG = new File(dirSalidaOGG+"/Contenido");			
			File[] listaFicContenido = dCarpetaContenidoOGG.listFiles();		
			String nombreFicSonido ="";
			for (int i = 0; i < listaFicContenido.length; i++) {
				if (listaFicContenido[i].getName().endsWith(".ogg"))
				{
					nombreFicSonido = listaFicContenido[i].getName();
					// 4. Copiar ogg a ODE MPEG
					File fNuevaRuta = new File(dCarpetaContenidoMPEG.getAbsolutePath()+ "/"+ listaFicContenido[i].getName() );
					listaFicContenido[i].renameTo(fNuevaRuta);
				}
			}			
			// 5. Crear html5 con enlace a mpeg y ogg
			crearHTML5(dCarpetaContenidoMPEG.getAbsolutePath(), nombreFicSonido.replace(".ogg", ".mp3"), nombreFicSonido, ficMPEG.getName().substring(0,ficMPEG.getName().length()-4));
			// 6. Modificar manifest para que apunte a html5 
			modificarManifest(dirSalidaMPEG);
			// 7. Zipear ODE en mpeg
			comprimirODEActualizado(dirSalidaMPEG,dODEsUnificados.getAbsolutePath());
			log.info("ODE unificado");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static int modificarManifest(String dirODE) {
		
		int tipoODE=0;
		
		try {
			
			File fichero = new File(dirODE+"/imsmanifest.xml");			 
			
			SCORM2004Dao scormDAO = new SCORM2004Dao();
			Manifest imsManifest= scormDAO.parsearODE(0, fichero);
				
			Resource recurso = imsManifest.getResources().getResource(0);
			es.pode.parseadorXML.castor.File fileRecurso = recurso.getFile(0);
			
			fileRecurso.setHref("Contenido/cargaSonidoHTML5.html");
			recurso.setHref("Contenido/cargaSonidoHTML5.html");
			recurso.setFile(0, fileRecurso);
			imsManifest.getResources().setResource(0, recurso);
			
			scormDAO.escribirODE(imsManifest, fichero);
			
		}catch (Exception e) {
				log.error("Error al procesar el fichero : " +e.getMessage(),e);				
		}
		
		return tipoODE;
	}

	public static void crearHTML5(String nombreDir, String nombreFicMPEG, String nombreFicOgg, String titulo) throws Exception {
		String sFichero = "cargaSonidoHTML5.html";
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(nombreDir + "/" + sFichero));

		bw.write("<!DOCTYPE html>\n");
		bw.write("<html>\n");
		bw.write("<head>\n");
		bw.write("<meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'>\n");
		bw.write("<title>"+ titulo +"</title>\n");
		bw.write("</head>\n");
		bw.write("<body>\n");
		bw.write("<audio controls>\n");		
		bw.write("<source src='"+ nombreFicOgg+"' type='audio/ogg'>\n");
		bw.write("<source src='"+ nombreFicMPEG+"' type='audio/mpeg'>\n");
		bw.write("Su navegador no soporta la tag audio.Pulse <a href='"+ nombreFicMPEG+"'>aqui</a> para descargarse el sonido.\n");
		bw.write("</audio>\n");
		bw.write("</body>\n");
		bw.write("</html>\n");
		
		bw.close();

	}

	public static String  crearEstructuraODE(String nombreDir) throws Exception {
		// Creamos la carpeta y subcarpetas del ODE para descomprimirlo
		File dZipDescomprimido = new File(nombreDir + "_Actualizado");
		dZipDescomprimido.mkdir();

		File dCarpetaContenido = new File(dZipDescomprimido.getAbsoluteFile()+"/Contenido");
		dCarpetaContenido.mkdir();

		File dCarpetaCommon = new File(dZipDescomprimido.getAbsoluteFile()+"/common");
		dCarpetaCommon.mkdir();

		File dCarpetaExtend = new File(dZipDescomprimido.getAbsoluteFile()+"/extend");
		dCarpetaExtend.mkdir();

		File dCarpetaUnique = new File(dZipDescomprimido.getAbsoluteFile()+"/unique");
		dCarpetaUnique.mkdir();
		
		File dCarpetaVocab = new File(dZipDescomprimido.getAbsoluteFile()+"/vocab");
		dCarpetaVocab.mkdir();
		
		return dZipDescomprimido.getAbsolutePath();
		
	}
	
	public static void descomprimirZipCarpeta(String pZipFile, String destination) throws Exception {
		try {
			// Create a ZipInputStream to read the zip file
			BufferedOutputStream dest = null;
			FileInputStream fis = new FileInputStream( pZipFile );
			ZipInputStream zis = new ZipInputStream( 

					new BufferedInputStream( fis ) );
			// Loop over all of the entries in the zip file
			int count;
			byte data[] = new byte[ BUFFER_SIZE ];
			ZipEntry entry;
			while( ( entry = zis.getNextEntry() ) != null )
			{						
				if( !entry.isDirectory() )
				{					

					String destFN = destination + File.separator + entry.getName();
					// Write the file to the file system
					FileOutputStream fos = new FileOutputStream( destFN );
					dest = new BufferedOutputStream( fos, BUFFER_SIZE );
					while( (count = zis.read( data, 0, BUFFER_SIZE ) ) != -1 )
					{
						dest.write( data, 0, count );
					}
					dest.flush();
					dest.close();
				}
			}
			zis.close();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	public static void getAllFiles(File dir, List<File> fileList) {
		File[] files = dir.listFiles();
		for (File file : files) {
			fileList.add(file);
			if (file.isDirectory()) {
				getAllFiles(file, fileList);
			}
		}
	}

	public static void writeZipFile(File directoryToZip, List<File> fileList, String directorioDestinoZips) {

		try {
			FileOutputStream fos = new FileOutputStream(directorioDestinoZips + "/" + directoryToZip.getName() + ".zip");
			ZipOutputStream zos = new ZipOutputStream(fos);

			for (File file : fileList) {
				if (!file.isDirectory()) { // we only zip files, not directories
					addToZip(directoryToZip, file, zos);
				}
			}

			zos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void comprimirODEActualizado(String nombreODEComprimir, String directorioDestinoZips) throws FileNotFoundException,
	IOException {
		
		File directoryToZip = new File(nombreODEComprimir);

		List<File> fileList = new ArrayList<File>();
		
		getAllFiles(directoryToZip, fileList);
		
		writeZipFile(directoryToZip, fileList, directorioDestinoZips);
		
	}
	public static void addToZip(File directoryToZip, File file, ZipOutputStream zos) throws FileNotFoundException,
			IOException {

		FileInputStream fis = new FileInputStream(file);

		// we want the zipEntry's path to be a relative path that is relative
		// to the directory being zipped, so chop off the rest of the path
		String zipFilePath = file.getCanonicalPath().substring(directoryToZip.getCanonicalPath().length() + 1,
				file.getCanonicalPath().length());
		
		ZipEntry zipEntry = new ZipEntry(zipFilePath);
		zos.putNextEntry(zipEntry);

		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
		}

		zos.closeEntry();
		fis.close();
	}

	public static void configurarLog()
	{
		Logger logger = Logger.getLogger(EliminacionSonidosDuplicados.class);
		String log4JPropertyFile = "log4j.properties";
		Properties p = new Properties();

		try {
			p.load(new FileInputStream(log4JPropertyFile));
			PropertyConfigurator.configure(p);
			logger.info("Configurado");
		} catch (IOException e) {
			System.out.println("Error al configurar el log :" + e.getMessage());
		}	
	}
}
