/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package agrega;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


import es.pode.entregar.negocio.servicios.PaquetePifVO;
import es.pode.entregar.negocio.servicios.SrvEntregarService;
import es.pode.entregar.negocio.servicios.SrvEntregarServiceServiceLocator;
import es.pode.entregar.negocio.servicios.TipoPifVO;
import es.pode.publicacion.negocio.servicios.ResultadoOperacionVO;
import es.pode.publicacion.negocio.servicios.SrvPublicacionService;
import es.pode.publicacion.negocio.servicios.SrvPublicacionServiceServiceLocator;



public class EliminacionImagenesDuplicadas {
	
	/* Datos de conexion a la DB de agrega para poder consultar los datos de los ODEs de la tabla INDICE_BUSQUEDA */
	static String DB_HOST;
	static String DB_USER;
	static String DB_PASS;
	static String DB_NAME;
	
	/* Tipo de recurso de los ODEs que estan duplicados */
	/* Solo puede tomar valor photograph o illustration */
	//static String tipoRecurso="photograph";
	static String tipoRecurso="illustration";

	/* Datos para llamar a los webservices de agrega */
	static String nodo;
	static String urlSrvEntregar="/entregar-1/services/SrvEntregarService";
	static String urlSrvPublicar="/publicacion-1/services/SrvPublicacionService";
		
	/* Variables que deciden que acciones tomar con cada ODE cuplicado */
	/* Si una accion esta a false esta no se realizara */
	static boolean CREAR_BACKUP=false;
	static boolean DESPUBLICAR=false;
	static boolean ELIMINAR=true;
	
	static Logger log =  Logger.getLogger(EliminacionImagenesDuplicadas.class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try
		{
			if (args==null || args.length<6)
			{
				System.out.println("Parametros incorrectos:");
				System.out.println("Sintaxis correcta:    java -jar eliminacionImagenesDuplicadas.jar tiempoMaximoEjecucionMinutos hostBBDD usuarioBBDD passwordBBDD nombreBBDD urlNodo");
				System.out.println("Ejemplo de ejecución: java -jar eliminacionImagenesDuplicadas.jar 30 hostDatabase user password agregaDatabase agrega-2hapre.pntic.mec.es");
				return;																					
			}
			
			int tMinEjecucion = 0;
			try {				
				tMinEjecucion = Integer.parseInt(args[0]);
			} catch (Exception e) {
				System.out.println("El parametro tiempoMaximoEjecucionMinutos no es correcto (debe ser numerico): " + args[0]);
				return;
			}												

			DB_HOST=args[1];
			DB_USER=args[2];
			DB_PASS=args[3];
			DB_NAME=args[4];
			nodo = "http://" + args[5] +"/";

			
			if(CREAR_BACKUP || DESPUBLICAR || ELIMINAR) {
				
				System.out.println("Para cada ODE duplicado se realizaran las siguientes acciones:");
				if(CREAR_BACKUP)System.out.println("- Creacion backup");
				if(DESPUBLICAR) System.out.println("- Despublicacion en Agrega");
				if(ELIMINAR) 	System.out.println("- Eliminacion de Agrega");
				System.out.print("Desea continuar? [y]es, [N]o: ");

				char[] readBuffer= new char[10];
				try {
					System.console().reader().read(readBuffer);
				} catch (Exception e) {
					System.out.println("Es necesario que responda a una pregunta por consola y no se puede continuar sin esta condicion.");
					System.out.println("Probablemente este intentando redirigir el std input o el std output o usando pipes, redirecciones o herramientas como tee.");
					return;
				}
				String input=new String(readBuffer);
				
				if(!input.startsWith("y") && !input.startsWith("Y") &&
						!input.startsWith("yes") && !input.startsWith("YES")) {
					System.out.println("Saliendo");
					return;
				}
			} 

			System.out.println("Se inicia la ejecucion de la eliminacion de imagentes duplicadas");
			configurarLog();

			if(CREAR_BACKUP || DESPUBLICAR || ELIMINAR) {
				log.info("Para cada ODE duplicado se realizaran las siguientes acciones:");
				if(CREAR_BACKUP)log.info("* Creacion backup");
				if(DESPUBLICAR)	log.info("* Despublicacion en Agrega");
				if(ELIMINAR) 	log.info("* Eliminacion de Agrega");
			}
			
			log.info("Se inicia la ejecucion de la eliminación de imagentes duplicadas");
			log.info("Inicio ejecución: " + new Date());
			//Suponemos que cada ODE tarda en procesarse 1 min
			limpiarImagenesDuplicadas(tMinEjecucion, DB_HOST, DB_USER, DB_PASS, DB_NAME);
			log.info("Ejecucion finalizada: " + new Date());
			
			System.out.println("Ejecucion finalizada");

		} catch (Exception e) {
			log.fatal("Error: ",e);
		}
	}
	
	
	public static void limpiarImagenesDuplicadas(int numODEsAProcesar, String host, String usuario, String password, String dbName) {

		try
		{			
			Connection conn = obtenerConexion(host, usuario, password, dbName);
			
			// Obtener la lista de ODEs de imagen grande (fecha 2007)
			List<CamposODE> odes = obtenerODEsOriginales(conn);
			log.info("ODEs a revisar si tienen duplicado: " + odes.size());
			
			// Para cada uno verificar si existe la imagen pequeña (fecha 2008)
			int nCopias=0;
			int iguales=0;			
			int numODEsProcesados=0;
			int numODEsProcesadosSinError=0;
			int numODEsProcesadosConError=0;
			//List<String> listaIdsParaDespublicar = new ArrayList<String>();
			List<String> listaIdsCopiasNoIguales = new ArrayList<String>();
			
			log.info("Numero maximo de ODEs a procesar:" +numODEsAProcesar);
			log.info("================= INICIO PROCESADO =================");
			
			for (Iterator<CamposODE> iterator=odes.iterator(); iterator.hasNext(); numODEsProcesados++) {		
				
				if(numODEsProcesados>=numODEsAProcesar) break;
				if(numODEsProcesadosConError>=100) {
					log.error("Se detiene la ejecucion por haber encontrado mas de 100 ODEs procesados con error");
					break;
				}
				
				//Obtenemos la posible copia del ODE
				CamposODE odeOriginal = iterator.next();
				String idCopia = odeOriginal.getIdentificador().replace("es_20071227","es_20080430");
				
				log.info("Procesando ODE num "+(numODEsProcesados+1)+" con id "+odeOriginal.getIdentificador()+" y con posible copia en el ODE "+idCopia);
				CamposODE odeCopia = obtenerODECopia(conn, idCopia);
								
				//Revisamos si hemos encontrado una copia en DB
				if (odeCopia!=null && odeCopia.getIdentificador()!=null && !odeCopia.getIdentificador().equals("")) {
					
					//El ODE tiene una copia
					nCopias++;
					log.info(" El ODE tiene una copia");
					/*
					if ((numODEsProcesados+1)%1000==0) {						
						log.info("Procesando el ODE num: " + numODEsProcesados+1);
						log.info(odeOriginal.getIdentificador()+"|"+odeOriginal.getTitle());
						log.info(odeCopia.getIdentificador()+"|"+odeCopia.getTitle());
					}
					*/
					
					//Verificamos que los ODEs son realmente identicos y que no es un falso positivo
					String infoOdeCopia = odeCopia.obtenerValoresCamposConcatenados();
					String infoOdeOriginal = odeOriginal.obtenerValoresCamposConcatenados();
					
					if (infoOdeCopia.equals(infoOdeOriginal) || infoOdeCopia.trim().equals(infoOdeOriginal.trim())) {
						
						iguales++;
						log.info(" La copia del ODE es igual al original");
							
						try {
							//listaIdsParaDespublicar.add(odeCopia.getIdentificador());
							if (CREAR_BACKUP) realizarBackupODE(odeCopia.getIdentificador());
							if (DESPUBLICAR) {
								pasarODENoDisponible(odeCopia.getIdentificador(), odeCopia.getTitle());
								/*
								 * PARECE SER QUE DESPUBLICAR VARIOS ODES A LA VEZ NO FUNCIONA
								if ((listaIdsParaDespublicar.size()%100) ==0) {
									pasarBloqueODENoDisponible(listaIdsParaDespublicar);
									listaIdsParaDespublicar=new ArrayList<String>();
								}
								*/
							}
							if (ELIMINAR) eliminarODE(odeCopia.getIdentificador());	
							numODEsProcesadosSinError++;
							
						} catch (Exception e) {
							numODEsProcesadosConError++;
							log.error("Error al procesar el ODE: " + odeCopia.getIdentificador(), e);
						}
						
					} else {
						log.info(" La copia del ODE es diferente al original");
						listaIdsCopiasNoIguales.add(idCopia);		
					}
					
				//Puede ser que los ODEs duplicados ya hayan sido despublicados y por tanto no esten en DB
				//En este caso debemos permitir realizar la eliminacion
				} else if (!CREAR_BACKUP && !DESPUBLICAR && ELIMINAR) {

					try {
						eliminarODE(idCopia);	
						numODEsProcesadosSinError++;
						
					} catch (Exception e) {
						numODEsProcesadosConError++;
						log.error("Error al procesar el ODE: " + idCopia, e);
					}
				}
			}
			
			// Pasamos a no disponible los que queden en la lista
			//if (DESPUBLICAR && listaIdsParaDespublicar.size()>0)
			//	pasarBloqueODENoDisponible(listaIdsParaDespublicar);

			log.info("================= RESUMEN PROCESADO =================");
			log.info("ODEs con posibles copias: " + odes.size());
			log.info("ODEs procesados: " + numODEsProcesados);
			log.info("ODEs con copias encontradas: " + nCopias);
			log.info("ODEs con copias iguales: " + iguales);
			log.info("ODEs con copias iguales procesados correctamente: " + numODEsProcesadosSinError);
			log.info("ODEs con copias iguales procesados erroneamente: " + numODEsProcesadosConError);
			
			if(!listaIdsCopiasNoIguales.isEmpty()) {
				log.info("Lista de IDs de ODEs con copias no iguales que es necesario revisar a mano: ");
				for(int i=0; i<listaIdsCopiasNoIguales.size(); i++)
					log.info(listaIdsCopiasNoIguales.get(i).replace("es_20080430","es_20071227")+" tiene una copia en "+listaIdsCopiasNoIguales.get(i));
			}
			
		} catch (Exception e) {
			log.error("Error: ", e);
		}
	}
	
	
	public static void pasarODENoDisponible(String identificador, String titulo) throws Exception {
		
		log.warn("  pasarODENoDisponible " + identificador);
		SrvPublicacionServiceServiceLocator locator = new SrvPublicacionServiceServiceLocator();
		SrvPublicacionService servicio = locator.getSrvPublicacionService(new URL(nodo+urlSrvPublicar));
		ResultadoOperacionVO resultado=servicio.noDisponible(identificador, "administrador", "Despublicación automática por limpieza de imágenes repetidas", titulo);
		log.warn("  Resultado operación  " + resultado.getIdResultado());
	}	
	
	
	public static void pasarBloqueODENoDisponible(List<String> identificadores) throws Exception {
		try {
			log.warn(" pasarBloqueODENoDisponible  " + identificadores.size());
			SrvPublicacionServiceServiceLocator locator = new SrvPublicacionServiceServiceLocator();
			SrvPublicacionService servicio = locator.getSrvPublicacionService(new URL(nodo+urlSrvPublicar));
			ResultadoOperacionVO[] resultado=servicio.noDisponibles((String[])identificadores.toArray(new String[0]),"administrador", "Despublicación automática por limpieza de imágenes repetidas");
			for (int i = 0; i < resultado.length; i++) 
				log.warn(" Resultado operación: " + i +": "  + resultado[i].getIdResultado());
			
		} catch (Exception e) {
			log.error("ERROR al despublicar en bloque "+identificadores.size()+" ODEs");
			log.error("Lista de identificadores de algunos de ellos:");			
			for (int i = 0; (i<identificadores.size()) && (i<10); i++) 
				log.error(" "+identificadores.get(i));
			throw(e);
		}
	}	
	
	
	public static void volcarADisco(String url, String identificador) throws Exception {

		//Creamos la ruta donde guardaremos los backups de los ODEs si no existe
		String backupFolder= "backupODEs/"; 
		File dirBackup = new File (backupFolder);
		if(!dirBackup.exists()) {
			dirBackup.mkdir();
			log.info("Los backups de los ODEs se guardaran en: "+dirBackup.getAbsolutePath());
		}
				
		String sFicBackup= backupFolder+identificador+".zip"; 
		URL urlConn = new URL(url);
		URLConnection yc = urlConn.openConnection();

		BufferedInputStream bufferedInput = new BufferedInputStream(yc.getInputStream());
		
		FileOutputStream fileOutput = new FileOutputStream (sFicBackup);
        BufferedOutputStream bufferedOutput = new BufferedOutputStream(fileOutput);

        byte [] array = new byte[1000];
        int leidos = bufferedInput.read(array);
        while (leidos > 0)
        {
            bufferedOutput.write(array,0,leidos);
            leidos=bufferedInput.read(array);
        }
        bufferedInput.close();
        bufferedOutput.close();    
	}

	
	public static void eliminarODE(String identificador) throws Exception {
		
		log.warn("  eliminarODE " + identificador);
		
		SrvPublicacionServiceServiceLocator locator = new SrvPublicacionServiceServiceLocator();
		SrvPublicacionService servicio = locator.getSrvPublicacionService(new URL(nodo+urlSrvPublicar));
		ResultadoOperacionVO resultado=servicio.eliminar(identificador, "administrador");
		
		log.warn("  Resultado operación " + resultado.getIdResultado());
	}	
	
	
	public static void realizarBackupODE(String identificador) throws Exception {
		
		try{
			log.warn("  realizarBackupODE " + identificador);
			
			SrvEntregarServiceServiceLocator locator = new SrvEntregarServiceServiceLocator();
			SrvEntregarService servicio = locator.getSrvEntregarService(new URL(nodo+urlSrvEntregar));
			PaquetePifVO resultado=servicio.generarPaquetePIFTipoPIF(new TipoPifVO(identificador, "SCORM_2004", "es"));
			
			log.warn("  Url del backup = " + resultado.getHref());
			volcarADisco(nodo+resultado.getHref(),identificador);
			log.warn("  Backup realizado");
			
		} catch (Exception e) {
			log.error("  Error al realizar el backup del ODE: " + identificador,e);			
		}
	}

	
	public static Connection obtenerConexion(String host, String usuario, String password, String dbName) throws Exception {

		String myDriver = "com.mysql.jdbc.Driver";		
		String cadenaConexion = "jdbc:mysql://"+host+"/"+dbName;
		log.warn("Obteniendo conexión a la db de Agrega con: " + cadenaConexion + " y usuario : " + usuario);
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(cadenaConexion, usuario, password);
		return conn;
	}
	
	
	public static CamposODE obtenerODECopia(Connection conn, String identificador) {

		CamposODE ode = new CamposODE();
		
		try {
			String query = "SELECT identificador, title, alt_title, description, alt_description, keyword, alt_keyword, formato, tipo_recurso, licencias," +
					" ambito, idioma, edad, nivel_agregacion, publicador " +
					" FROM INDICE_BUSQUEDA where identificador = '" + identificador+"'";

			Statement st = conn.createStatement();

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				String identificadorOde = rs.getString("identificador");
				String title = rs.getString("title");
				String alt_title = rs.getString("alt_title");
				String description = rs.getString("description");
				String alt_description = rs.getString("alt_description");
				String keyword = rs.getString("keyword");
				String alt_keyword = rs.getString("alt_keyword");
				String formato = rs.getString("formato");
				String tipo_recurso = rs.getString("tipo_recurso");
				String licencias = rs.getString("licencias");
				String ambito = rs.getString("ambito");
				String idioma = rs.getString("idioma");
				String edad = rs.getString("edad");
				String nivel_agregacion = rs.getString("nivel_agregacion");
				String publicador = rs.getString("publicador");

				ode.setIdentificador(identificadorOde);
				ode.setTitle(title);
				ode.setAlt_keyword(alt_title);
				ode.setDescription(description);
				ode.setAlt_description(alt_description);
				ode.setKeyword(keyword);
				ode.setAlt_keyword(alt_keyword);
				ode.setFormato(formato);
				ode.setTipo_recurso(tipo_recurso);
				ode.setLicencias(licencias);
				ode.setAmbito(ambito);
				ode.setIdioma(idioma);
				ode.setEdad(edad);
				ode.setNivel_agregacion(nivel_agregacion);
				ode.setPublicador(publicador);
			}
			st.close();

		} catch (Exception e) {
			log.error("Error: ", e);
		}
		return ode;
	}
	
	
	public static List<CamposODE> obtenerODEsOriginales(Connection conn) {
		List<CamposODE> odes = new ArrayList<CamposODE>();
		try {
			
			String query = "SELECT identificador, title, alt_title, description, alt_description, keyword, alt_keyword, formato, tipo_recurso, licencias," +
					" ambito, idioma, edad, nivel_agregacion, publicador " +
					" FROM INDICE_BUSQUEDA where identificador like 'es_20071227%' and tipo_recurso like '"+tipoRecurso+"%'";

			Statement st = conn.createStatement();

			ResultSet rs = st.executeQuery(query);

			int i = 0;

			while (rs.next()) {
				i++;

				String identificador = rs.getString("identificador");
				String title = rs.getString("title");
				String alt_title = rs.getString("alt_title");
				String description = rs.getString("description");
				String alt_description = rs.getString("alt_description");
				String keyword = rs.getString("keyword");
				String alt_keyword = rs.getString("alt_keyword");
				String formato = rs.getString("formato");
				String tipo_recurso = rs.getString("tipo_recurso");
				String licencias = rs.getString("licencias");
				String ambito = rs.getString("ambito");
				String idioma = rs.getString("idioma");
				String edad = rs.getString("edad");
				String nivel_agregacion = rs.getString("nivel_agregacion");
				String publicador = rs.getString("publicador");

				CamposODE ode = new CamposODE();
				ode.setIdentificador(identificador);
				ode.setTitle(title);
				ode.setAlt_keyword(alt_title);
				ode.setDescription(description);
				ode.setAlt_description(alt_description);
				ode.setKeyword(keyword);
				ode.setAlt_keyword(alt_keyword);
				ode.setFormato(formato);
				ode.setTipo_recurso(tipo_recurso);
				ode.setLicencias(licencias);
				ode.setAmbito(ambito);
				ode.setIdioma(idioma);
				ode.setEdad(edad);
				ode.setNivel_agregacion(nivel_agregacion);
				ode.setPublicador(publicador);
				
				odes.add(ode);
//				if (i % 100 == 0)
//					System.out.println("ODE : " + i + " : " + ode.toString());
			}
			st.close();

		} catch (Exception e) {
			log.error("Error: ", e);
		}
		return odes;
	}
	
	
	public static void configurarLog()
	{
		Logger logger = Logger.getLogger(EliminacionImagenesDuplicadas.class);
		String log4JPropertyFile = "log4j.properties";
		Properties p = new Properties();

		try {
			p.load(new FileInputStream(log4JPropertyFile));
			PropertyConfigurator.configure(p);
			logger.info("Configurado");
		} catch (IOException e) {
			System.out.println("Error al configurar el log: " + e.getMessage());
		}	
	}
	
	
}
