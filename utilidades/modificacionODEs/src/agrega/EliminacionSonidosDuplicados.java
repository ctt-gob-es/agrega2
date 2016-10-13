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



public class EliminacionSonidosDuplicados {

	static String nodo="";
	static String urlSrvEntregar="/entregar-1/services/SrvEntregarService";
	static String urlSrvPublicar="/publicacion-1/services/SrvPublicacionService";
	
	static final String sFicBackupWAV= "backupODEs_WAV/"; 
	static final String sFicBackupOGG= "backupODEs_OGG/";
	static final String sFicBackupMPEG= "backupODEs_MPEG/";
	
	static final String formato_wav = "audio_wav";	
	static final String formato_ogg = "application_ogg";
	static final String formato_mpeg = "audio_mpeg";		
	
	static Logger log =  Logger.getLogger(EliminacionSonidosDuplicados.class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		try
		{
			
			log.info("Se inicia la ejecución de la eliminación de sonidos duplicados.");
			if (args==null || args.length<6)
			{
				System.out.println("Parametros incorrectos:");
				System.out.println("Sintaxis correcta:    java -jar eliminacionSonidosDuplicados.jar numeroODESEliminar cadenaConexionBBDD usuarioBBDD passwordBBDD urlNodo modoEjecucion ");
				System.out.println("Ejemplo de ejecución: java -jar eliminacionSonidosDuplicados.jar 300 jdbc:mysql://maquinaBBDD/nombreBBDD root passRoot agrega-2hapre.pntic.mec.es modoBackup|modoDespublicar|modoEliminar|modoCompleto");
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
			
			nodo = "http://" + args[4] +"/";
			
			configurarLog();
			crearDirectoriosBackup();
						
			log.info("Inicio ejecución :" + new Date());
			
			String modo = args[5];
			
			boolean bModoBackup=false;
			boolean bModoEliminar=false;
			boolean bModoDespublicar=false;
			
			if (modo.equals("modoBackup"))
				bModoBackup = true;
			else if (modo.equals("modoEliminar"))
				bModoEliminar = true;
			else if (modo.equals("modoDespublicar"))
				bModoDespublicar = true;
			else if (modo.equals("modoCompleto")) {
				bModoBackup = true;
				bModoEliminar = true;
				bModoDespublicar = true;
			} else {
				log.info("Ejecución finalizada :" + new Date());
				System.out.println("Parametros incorrectos. Los modos válidos son modoBackup, modoDespublicar, modoEliminar o modoCompleto");
				
			}
			
			if (bModoBackup || bModoDespublicar || bModoEliminar)
				limpiarSonidosDuplicados(iNumODEsProcesar, args[1],args[2],args[3],bModoBackup,bModoDespublicar,bModoEliminar);
									
			log.info("Ejecución finalizada :" + new Date());

		}catch (Exception e) {
			log.fatal("Error: ",e);
		}
	}
	
	public static void crearDirectoriosBackup() {
		
		File dirBackup = new File (sFicBackupWAV);
		dirBackup.mkdir();		
		
		dirBackup = new File (sFicBackupOGG);
		dirBackup.mkdir();
			
		dirBackup = new File (sFicBackupMPEG);
		dirBackup.mkdir();
			
	}
	public static void limpiarSonidosDuplicados(int iNumODEsProcesar, String cadenaConexion, String usuario, String password, boolean bModoBackup, boolean bModoDespublicar, boolean bModoEliminar) {

		log.warn("limpiarSonidosDuplicados. Inicio ejecución");
		log.warn("limpiarSonidosDuplicados. Modo backup : " + bModoBackup);
		log.warn("limpiarSonidosDuplicados. Modo despublicar : " + bModoDespublicar);
		log.warn("limpiarSonidosDuplicados. Modo eliminar : " + bModoEliminar);
		
		try
		{			


			int iNumOdesOGG=0;
			int iNumOdesMPEG=0;

			// Obtenemos los ODEs en formato Wav
			log.warn("limpiarSonidosDuplicados. Va a procesar wav");
						
			int iNumOdesWav = procesarODEsFormato(cadenaConexion, usuario, password, formato_wav, iNumODEsProcesar,bModoBackup,bModoDespublicar,bModoEliminar);
			log.warn("limpiarSonidosDuplicados. Termina de procesar wav");
			if (iNumODEsProcesar > iNumOdesWav)
			{
				// Obtenemos los ODEs en formato ogg
				log.warn("limpiarSonidosDuplicados. Va a procesar ogg");							
				iNumOdesOGG =procesarODEsFormato(cadenaConexion, usuario, password, formato_ogg , iNumODEsProcesar-iNumOdesWav,bModoBackup,bModoDespublicar,bModoEliminar);
				log.warn("limpiarSonidosDuplicados. Termina de procesar ogg");
			}	
			
			if (iNumODEsProcesar > iNumOdesMPEG)
			{
				// Obtenemos los ODEs en formato mp3
				log.warn("limpiarSonidosDuplicados. Va a procesar mpeg");	
				iNumOdesMPEG =procesarODEsFormato(cadenaConexion, usuario, password, formato_mpeg , iNumODEsProcesar-iNumOdesWav - iNumOdesOGG,bModoBackup,bModoDespublicar,bModoEliminar);
				log.warn("limpiarSonidosDuplicados. Termina de procesar mpeg");
			}
		}catch (Exception e) {
			log.error("Error: ");
			log.error(e);
		}
	}
	
	public static int procesarODEsFormato(String cadenaConexion, String usuario, String password, String formato, int iNumODEsProcesar, boolean bModoBackup, boolean bModoDespublicar, boolean bModoEliminar) {

		int iNumODEs =0;
		log.warn("limpiarSonidosDuplicados. Inicio backup ODEs con formato :" + formato);
		try
		{			
			
			List<CamposODE> odes = obtenerODEsFormato( cadenaConexion,  usuario,  password, formato);

			iNumODEs= odes.size();		
			
			log.info("ODEs en formato " + formato + " = " + odes.size());						
	
			int iODESProcesados = 0;
			for (Iterator<CamposODE> iterator = odes.iterator(); iterator.hasNext();) {
				
				if (iODESProcesados< iNumODEsProcesar)
				{
					iODESProcesados++;
					
					CamposODE ode =  iterator.next();
							
					if (bModoBackup)
						realizarBackupODE(ode.getIdentificador(), formato, ode.getTitle());
					
					// Los mp3 no se despublican. Sólo hacemos backup
					if (bModoDespublicar && !formato.equals(formato_mpeg))					
						pasarODENoDisponible(ode.getIdentificador(), ode.getTitle());										

					// Los mp3 no se eliminan. Sólo hacemos backup
					if (bModoEliminar && !formato.equals(formato_mpeg))									
						eliminarODE(ode.getIdentificador());
					
					
					//if (iNumBackups % 100 == 0)
					log.warn("Procesado ODE : " + iODESProcesados + " : " + ode.getTitle());
					
				}else
					break;
					
			}
														
			log.info("Se ha hecho backup de " + iODESProcesados + " ODEs conformato " + formato );
			
		}catch (Exception e) {
			log.error("Error: ");
			log.error(e);
		}
		
		return iNumODEs;
	}
	
	public static void pasarODENoDisponible(String identificador, String titulo) throws Exception {
		try{		
			log.warn(" pasarODENoDisponible  " + identificador);
			SrvPublicacionServiceServiceLocator locator = new SrvPublicacionServiceServiceLocator();
			
			SrvPublicacionService servicio = locator.getSrvPublicacionService(new URL(nodo+urlSrvPublicar));
			ResultadoOperacionVO resultado=servicio.noDisponible(identificador, "administrador", "Despublicación automática por limpieza de imágenes repetidas", titulo);
			
			log.warn(" Resultado operación  " + resultado.getIdResultado());
		}catch (Exception e) {
			log.error(" Error al pasar a no disponible el ODE :" + identificador, e);
		}
	}	
	
	
	public static void volcarADisco(String url, String titulo, String formatoODE) throws Exception {
		
		String sFicBackup= "";
		
		if (formatoODE.contains("wav"))
			sFicBackup = sFicBackupWAV+titulo+".zip";
		else if (formatoODE.contains("ogg"))
			sFicBackup = sFicBackupOGG+titulo+".zip";
		else 
			sFicBackup = sFicBackupMPEG+titulo+".zip";
		
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
		try{
			log.warn(" eliminarODE  " + identificador);
			
			SrvPublicacionServiceServiceLocator locator = new SrvPublicacionServiceServiceLocator();
			
			SrvPublicacionService servicio = locator.getSrvPublicacionService(new URL(nodo+urlSrvPublicar));
			ResultadoOperacionVO resultado=servicio.eliminar(identificador, "administrador");
			
			log.warn(" Resultado operación  " + resultado.getIdResultado());
		}catch (Exception e) {
			log.error(" Error al eliminar el ODE :" + identificador, e);
		}

	}	
	public static void realizarBackupODE(String identificador,String formato, String titulo) throws Exception {
		
		try{
			log.warn(" realizarBackupODE  " + identificador);
			
			SrvEntregarServiceServiceLocator locator = new SrvEntregarServiceServiceLocator();
			
			SrvEntregarService servicio = locator.getSrvEntregarService(new URL(nodo+urlSrvEntregar));
			PaquetePifVO resultado=servicio.generarPaquetePIFTipoPIF(new TipoPifVO(identificador, "SCORM_2004", "es"));
			
			log.warn(" Url del backup = " + resultado.getHref());
			volcarADisco(nodo+resultado.getHref(),titulo,formato);
			log.warn(" Backup realizado");
		}catch (Exception e) {
			log.error(" Error al realizar el backup del ODE :" + identificador, e);
		}
	}

	public static Connection obtenerConexion(String cadenaConexion, String usuario, String password) throws Exception {

		log.warn(" obtenerConexion. Obteniendo conexión a: " + cadenaConexion + " con usuario : " + usuario );
		String myDriver = "com.mysql.jdbc.Driver";		
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(cadenaConexion, usuario,password);

		return conn;

	}
	
	
	
	public static List<CamposODE> obtenerODEsFormato(String cadenaConexion, String usuario, String password, String formato) {
		List<CamposODE> odes = new ArrayList<CamposODE>();
		try {
			
			Connection conn = obtenerConexion(cadenaConexion, usuario, password);
			
			String query = "SELECT identificador, title FROM INDICE_BUSQUEDA where formato like '" + formato +"'";

			Statement st = conn.createStatement();

			ResultSet rs = st.executeQuery(query);

			int i = 0;

			CamposODE ode = null;
			while (rs.next()) {
				i++;

				String identificador = rs.getString("identificador");
				String title = rs.getString("title");

				ode = new CamposODE();
				ode.setIdentificador(identificador);
				ode.setTitle(title);
				
				odes.add(ode);
//				if (i % 100 == 0)
//					log.warn("ODE : " + i + " : " + identificador);
			}

			st.close();

			conn.close();
		} catch (Exception e) {
			log.error("Error: ");
			log.error(e.getMessage());
		}
		return odes;
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
