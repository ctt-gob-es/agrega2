package agrega;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;

import org.exolab.castor.xml.Unmarshaller;

import es.pode.parseadorXML.SCORM2004Dao;
import es.pode.parseadorXML.castor.Identifier;
import es.pode.parseadorXML.castor.Lom;
import es.pode.parseadorXML.castor.LomRoot;
import es.pode.parseadorXML.castor.Manifest;

public class ComprobacionManifest {

	
	static String newline=System.getProperty("line.separator");
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		if (args==null || args.length<2)
		{
			System.out.println("Este programa se encarga de identificar aquellos ODEs publicados y despublicados en Agrega que son de tipo Descartes o Newton.");
			System.out.println("Generara como maximo dos ficheros de texto; ODEsDescartes.txt y ODEsNewton.txt, cada unos de ellos con la lista de localizadores de los ODEs de cada tipo.");
			System.out.println("Para ello necesita que se le pasen como parametros lo siguiente:");
			System.out.println("- Un fichero de localizadores: es un fichero con una lista de las rutas en HD donde se encuentran todos los ODEs. El contenido de esta fichero obtiene con la siguiente consulta a la DB de Agrega: 'SELECT localizador from INDICE_BUSQUEDA'");
			System.out.println("- La ruta del repositorio: es la ruta absoluta en el HD de la carpeta UPLOADS de Agrega");
			System.out.println("Sintaxis correcta:    java -jar comprobacionManifest.jar ficheroLocalizadores.txt rutaRepositorio");
			System.out.println("Ejemplo de ejecución: java -jar comprobacionManifest.jar ./localizadoresODEs.txt /export/ccaa/ENTORNO/");
			return;
		}
		
		// Verificamos que los parámetros son correctos
		// Fichero de localizadores
		File ficLocalizadores = new File(args[0]);
		if (!ficLocalizadores.exists())
		{
			System.out.println("No existe el fichero de localizadores: " + ficLocalizadores.getAbsolutePath());
			System.out.println("El proceso no puede continuar.");
			return;
		}
		// Directorio de repositorio
		File dirRepositorio = new File(args[1]+"/uploads/repositorio/");
		if (!dirRepositorio.exists() || !dirRepositorio.isDirectory())
		{
			System.out.println("La ruta del repositorio no es correcta: " + dirRepositorio.getAbsolutePath());
			System.out.println("El proceso no puede continuar.");
			return;
		}

		System.out.println("Se inicia la ejecución de la localización de ODEs de tipo Descartes o Newton");// o Malted");
		procesarFicheroLocalizadores(args);
		System.out.println("Se ha terminado de procesar la lista de ODEs candidatos");
		
	}
	
	
	/**
	 * Este método obtiene el imsmanifest del ODE cuyo localizador se pasa como parámetro, lo parsea y verifica si pertenece a alguna de los tipos
	 * de ODE que se intenta localizar, Descartes, Newton y Malted.
	 * @param dirOde Directorio del ODE a verificar
	 * @return int Devolverá 1 si es Descartes, 2 si es Newton, 3 si es Malted y 0 si no es ninguno de ellos.
	 */
	public static int verificarManifest(String rutaRepositorio, String dirODE) {
		
		int tipoODE=0;
		
		try {
			
			Unmarshaller unmarshaller = new Unmarshaller(Manifest.class);
			unmarshaller.setValidation(false);

			File fichero = new File(rutaRepositorio +dirODE+"/imsmanifest.xml");

			System.out.println("Vamos a procesar el manifest : " + fichero.getAbsolutePath()); 
			
			SCORM2004Dao scormDAO = new SCORM2004Dao();
			Manifest imsManifest= scormDAO.parsearODE(0, fichero);
									
			for (int i = 0; i < imsManifest.getMetadata().getGrp_any().getAnyObject().length; i++) {
				Object obj = imsManifest.getMetadata().getGrp_any().getAnyObject()[i];
				
				Identifier[] aIdentificadores=null;
				
				if (obj instanceof LomRoot) {
					LomRoot lomRoot = (LomRoot) imsManifest.getMetadata().getGrp_any().getAnyObject()[i];
					aIdentificadores =  lomRoot.getGeneral().getGroupGeneralGeneral().getIdentifier();
				
				} else if (obj instanceof Lom) {
					Lom lom= (Lom) imsManifest.getMetadata().getGrp_any().getAnyObject()[i];
					aIdentificadores =  lom.getGeneral().getGroupGeneralGeneral().getIdentifier();
				}

				if (aIdentificadores!=null)
				{
						
					for (int j = 0; j < aIdentificadores.length; j++) {
						Identifier ident = aIdentificadores[j];
						
						if ("meducacion_agrega".equals(ident.getGroupIdentifierIdentifier().getCatalog().getGroupCatalogCatalog().getContent()))
						{
							System.out.println("El identificador es : " + ident.getGroupIdentifierIdentifier().getEntry().getGroupEntryEntry().getContent());
							if (ident.getGroupIdentifierIdentifier().getEntry().getGroupEntryEntry().getContent().startsWith("100_"))
									tipoODE=1;
							else if (ident.getGroupIdentifierIdentifier().getEntry().getGroupEntryEntry().getContent().startsWith("101_"))
									tipoODE=2;
							else if (ident.getGroupIdentifierIdentifier().getEntry().getGroupEntryEntry().getContent().startsWith("102_"))
									tipoODE=3;
						}
					}
				}
			}

			System.out.println("El tipo de ODE es :" + tipoODE);
			
		} catch (Exception e) {
				System.out.println("Error al procesar el fichero : " +e.getMessage());
				e.printStackTrace();
		}
		
		return tipoODE;
	}
	
	
	public static void procesarFicheroLocalizadores(String[] args) {
		//File f = new File("./localizadoresODEs.txt");		
		//File f = new File("d:/proyectos/agrega/ODEsNewton.txt");
		File f = new File(args[0]);	
		
		BufferedReader entrada;
		try {
			System.out.println("Procesando fichero : " + f.getAbsolutePath());
			entrada = new BufferedReader( new FileReader( f ) );			
			String dirODE;
			int tipoOde=0;
			while(entrada.ready()) {
				dirODE = entrada.readLine();
				System.out.println("Va a verificar el ODE : " + dirODE);
				tipoOde = verificarManifest(args[1],dirODE);				
				if (tipoOde>0)
				{
					informarODELocalizado(dirODE, tipoOde);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void informarODELocalizado(String localizador, int tipoODE) {
		
		String descartesFilename="ODEsDescartes.txt";
		String newtonFilename="ODEsNewton.txt";
		
		File ficheroODEs = null;		
		try {
			if (tipoODE==1) {
				System.out.println("Se han encontrado ODEs Descartes; creando el fichero '"+descartesFilename+"' con el listado de dichos ODEs");
				ficheroODEs = new File(descartesFilename);
			} else if (tipoODE==2) {
				System.out.println("Se han encontrado ODEs Newton; creando el fichero '"+newtonFilename+"' con el listado de dichos ODEs");
				ficheroODEs = new File(newtonFilename);
			}
			/*
			else if (tipoODE==3)
				ficheroODEs = new File("./ODEsMaltes.txt");
				*/
			else
				return;
			
			//Si no Existe el fichero lo crea  
			if(!ficheroODEs.exists())
				ficheroODEs.createNewFile();  
			   
			BufferedWriter bwFicheroODEs=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ficheroODEs,true), "utf-8"));  
			bwFicheroODEs.write(localizador + newline);    
			bwFicheroODEs.close();  
		} catch (Exception ex) {    
			System.out.println(ex.getMessage());  
		}   		
	}

}