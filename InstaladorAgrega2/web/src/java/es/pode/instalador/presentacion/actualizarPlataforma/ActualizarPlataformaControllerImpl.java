// license-header java merge-point
package es.pode.instalador.presentacion.actualizarPlataforma;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import es.pode.instalador.presentacion.configuracionPlataforma.AnalizarRespuestaForm;
import es.pode.instalador.presentacion.configuracionPlataforma.ConfiguracionPlataformaControllerImpl;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimePartDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;



/**
 * @see es.pode.instalador.presentacion.actualizarPlataforma.ActualizarPlataformaController
 */
public class ActualizarPlataformaControllerImpl extends ActualizarPlataformaController
{
	private static Logger logger = Logger.getLogger(ActualizarPlataformaControllerImpl.class);
	private static int BUFFER_SIZE = 8192;
	
	/* Nombres de los ficheros que pueden ir dentro de los paquetes de actualizacion. Todos son opcionales dependiendo de la actualizacion */
	private static String README_FILE="readme.txt";			/* Fichero changelog o con otras anotaciones que se mostraran antes de aplicar la actualizacion */
	private static String DB_SCRIPT_FILE="script.sql";		/* Script de modificacion de DB */
	private static String SH_SCRIPT_FILE="script.sh";		/* Script de modificacion de contenido en disco */
	/* Nombres de ficheros que ayudan a conseguir transaccionalidad en las operaciones de SH_SCRIPT_FILE.Todos son opcionales dependiendo de la actualizacion */
	private static String SH_BACKUP_FILE="backup.sh"; 		/* Realiza un backup antes de ejecutar el SH_SCRIPT_FILE */
	private static String SH_ROLLBACK_FILE="rollback.sh"; 	/* Restaura el backup creado previamente por SH_BACKUP_FILE */
	private static String SH_COMMIT_FILE="commit.sh"; 		/* Elimina el backup creado por SH_BACKUP_FILE */
	
	/* Variables de entorno que pueden usarse desde el script sh del paquete de actualizacion */
	private static String ENV_UPLOADS="UPLOADS";
	private static String ENV_JBOSS_HOME="JBOSS_HOME";
	private static String ENV_INSTANCIA="INSTANCIA";
	private static String ENV_JBOSS_SERVER="JBOSS_SERVER";
	private static String ENV_WORKING_DIRECTORY="WORKING_DIRECTORY";

	
    private String analizarRespuestaAceptarCancelar(String action, HttpServletRequest request) throws Exception {

		String result = "Cancelar";
		Locale loc = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		String aceptar = I18n.getInstance().getResource("comun.aceptar", "application-resources", loc);
		
		if(logger.isDebugEnabled()) logger.debug("Action = " + action);
		
		if(aceptar!=null && action!=null && aceptar.equals(action)) {
			result = "Aceptar";
		}
		return result;		
    }
    
    
	@Override
	public String analizarRespuesta(
			ActionMapping mapping,
			es.pode.instalador.presentacion.actualizarPlataforma.AnalizarRespuestaForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return analizarRespuestaAceptarCancelar(form.getAction(), request);
	}

	
	private boolean esUnFicheroZip(File file) {
		ZipFile zipfile = null;
		try {
			zipfile = new ZipFile(file);
			return true;
		} catch (ZipException e) {
			return false;
		} catch (IOException e) {
			return false;
		} finally {
			try {
				if (zipfile != null) {
					zipfile.close();
					zipfile = null;
				}
			} catch (IOException e) {
				return false;
			}
		}
	}
	

	private void descomprimirZipEnCarpeta(String pZipFile, String destination) throws Exception {

		// Create a ZipInputStream to read the zip file
		BufferedOutputStream dest = null;
		FileInputStream fis = new FileInputStream(pZipFile);
		ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
		
		// Loop over all of the entries in the zip file
		int count;
		byte data[] = new byte[ BUFFER_SIZE ];
		ZipEntry entry;
		
		while( ( entry = zis.getNextEntry() ) != null ) {

			String destFN = destination + File.separator + entry.getName();

			File destFile = new File(destFN); 
			File destinationParent = destFile.getParentFile();
			// create the parent directory structure if needed
			destinationParent.mkdirs();
			
			if( !entry.isDirectory() ) {
				
				// Write the file to the file system
				FileOutputStream fos = new FileOutputStream(destFN);
				dest = new BufferedOutputStream(fos, BUFFER_SIZE);
				
				while ((count = zis.read(data, 0, BUFFER_SIZE)) != -1) {
					dest.write( data, 0, count );
				}
				dest.flush();
				dest.close();
				zis.closeEntry();
			}
		}
		zis.close();
	}
	
	
	/**
	 * Metodo que, solo en el caso de que el usuario pulse Aceptar, decomprime el paquete de 
	 * actualizacion y ofrece informacion al usuario sobre el mismo.
	 * En caso de no ser un contenido valido devuelve FICHERO_NO_VALIDO, en otro caso la eleccion del
	 * usuario: Aceptar o Cancelar.
	 */
	@Override
	public String analizarRespuestaYDescomprimirPaquete(ActionMapping mapping,
			AnalizarRespuestaYDescomprimirPaqueteForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String respuestaUsuario=analizarRespuestaAceptarCancelar(form.getAction(), request);
		if(respuestaUsuario.contentEquals("Cancelar"))
			return respuestaUsuario;
		
		FormFile formFile=form.getPaqueteActualizacion();
		if(formFile==null || formFile.getFileName()==null) {
			saveErrorMessage(request, "errors.noHayPaqueteActualizacion");
			logger.info("No se ha facilitado ningun paquete de actualizacion");
			return "FICHERO_NO_VALIDO";			
		}

		// preparamos el formfile que hemos recibido 
		InternetHeaders ih = new InternetHeaders();
		MimeBodyPart mbp = new MimeBodyPart(ih, formFile.getFileData());

		DataSource dsource = new MimePartDataSource(mbp);
		DataHandler dFichero = new DataHandler(dsource);
		
		//Escribimos a disco el fichero zip
		String pathZip = System.getProperty("java.io.tmpdir")+File.separator+formFile.getFileName();
		File ficheroZip = new File(pathZip);
		OutputStream os = new FileOutputStream(ficheroZip);
		dFichero.writeTo(os);
		
		if(!esUnFicheroZip(ficheroZip)) {
			saveErrorMessage(request, "errors.noEsPaqueteActualizacion");
			logger.info("El fichero facilitado no corresponde a un paquete de actualizacion");
			return "FICHERO_NO_VALIDO";			
		}

		//Descomprimimos el zip
		String rutaContenidoActualizacion=System.getProperty("jboss.server.temp.dir")+File.separator+formFile.getFileName()+"_"+System.currentTimeMillis();
		File contenidoActualizacion = new File(rutaContenidoActualizacion);
		if(!contenidoActualizacion.exists()) contenidoActualizacion.mkdirs();
		
		try {
			descomprimirZipEnCarpeta(ficheroZip.getPath(), rutaContenidoActualizacion);
		} catch (Exception e) {
			saveErrorMessage(request, "errors.descomprimirZip");
			logger.error("Error al descomprimir el fichero de actualizacion "+ficheroZip.getAbsolutePath()+" en "+rutaContenidoActualizacion, e);
			if(ficheroZip.exists()) ficheroZip.delete();
			if(contenidoActualizacion.exists()) contenidoActualizacion.delete();
			return "FICHERO_NO_VALIDO";
		}
		
		logger.debug("Fichero de actualizacion "+ficheroZip.getAbsolutePath()+" descomprimido en "+rutaContenidoActualizacion);
		
		//analizamos contenido para avisar al usuario
		if(contenidoActualizacion.listFiles().length<1) {
			saveErrorMessage(request, "errors.zipVacio");
			logger.info("Error al descomprimir el fichero de actualizacion "+ficheroZip.getAbsolutePath()+" en "+rutaContenidoActualizacion+"; se ha descubierto que estaba vacio");
			if(ficheroZip.exists()) ficheroZip.delete();
			if(contenidoActualizacion.exists()) contenidoActualizacion.delete();
			return "FICHERO_NO_VALIDO";
		}
			
		File f=null;
		boolean contenidoValido=false;
		
		for(int i=0; i<contenidoActualizacion.listFiles().length; i++) {
			f=contenidoActualizacion.listFiles()[i];
			
			if(f.isFile() && f.length()>0 && f.getName().contentEquals(README_FILE)) {
				form.setContieneNotas(true);
				form.setNotas(FileUtils.readFileToString(f));
				
			} else if(f.isFile() && f.length()>0 && f.getName().contentEquals(SH_SCRIPT_FILE)) {
				contenidoValido=true;
				form.setContieneActualizacionSH(true);
				
			} else if(f.isFile() && f.length()>0 && f.getName().contentEquals(DB_SCRIPT_FILE)) {
				contenidoValido=true;
				form.setContieneActualizacionDB(true);
			}
		}
		
		if(!contenidoValido) {
			saveErrorMessage(request, "errors.zipInvalido");
			logger.info("Error al descomprimir el fichero de actualizacion "+ficheroZip.getAbsolutePath()+" en "+rutaContenidoActualizacion+"; no tenia contenido valido");
			if(ficheroZip.exists()) ficheroZip.delete();
			if(contenidoActualizacion.exists()) contenidoActualizacion.delete();
			return "FICHERO_NO_VALIDO";
			
		} else {
			form.setRutaContenidoActualizacion(rutaContenidoActualizacion);
		}
		
		if(ficheroZip.exists()) ficheroZip.delete();
		return respuestaUsuario;
	}
	

	/**
	 * Metodo que recibe la ruta donde se descomprimio el paquete de actualizacion
	 * y aplica los scripts que contiene.
	 */
	@Override
	public void realizarActualizacion(ActionMapping mapping,
			RealizarActualizacionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String rutaContenidoActualizacion=form.getRutaContenidoActualizacion();
		if(rutaContenidoActualizacion==null || rutaContenidoActualizacion.isEmpty()) {
			logger.error("Falta la ruta donde se descomprimio el paquete de actualizacion");
			saveErrorMessage(request, "errors.genericoActualizacion");
			return;
		}
		
		File contenidoActualizacion = new File(rutaContenidoActualizacion);
		if(!contenidoActualizacion.exists() || !contenidoActualizacion.isDirectory()) {
			logger.error("Falta la ruta donde se descomprimio el paquete de actualizacion "+rutaContenidoActualizacion+" no existe o no es un derectorio");
			saveErrorMessage(request, "errors.genericoActualizacion");
			return;
		}

		if(contenidoActualizacion.listFiles().length<1) {
			logger.error("En la ruta donde se descomprimio el paquete de actualizacion "+rutaContenidoActualizacion+" no se encuentra contenido");
			saveErrorMessage(request, "errors.genericoActualizacion");
			return;
		}
		
		File scriptSH=null;
		File backupSH=null;
		File rollbackSH=null;
		File commitSH=null;
		File scriptDB=null;
		File f=null;
		
		//Buscamos los scripts de DB y SH
		for(int i=0; i<contenidoActualizacion.listFiles().length; i++) {
			f=contenidoActualizacion.listFiles()[i];
				
			if(f.isFile() && f.length()>0 && f.getName().contentEquals(SH_SCRIPT_FILE)) {
				scriptSH=f;				
			} else if(f.isFile() && f.length()>0 && f.getName().contentEquals(DB_SCRIPT_FILE)) {
				scriptDB=f;
			} else if(f.isFile() && f.length()>0 && f.getName().contentEquals(SH_BACKUP_FILE)) {
				backupSH=f;
			} else if(f.isFile() && f.length()>0 && f.getName().contentEquals(SH_ROLLBACK_FILE)) {
				rollbackSH=f;
			} else if(f.isFile() && f.length()>0 && f.getName().contentEquals(SH_COMMIT_FILE)) {
				commitSH=f;
			}
		}
		
		if(scriptDB!=null) {
			if(!actualizarDB(scriptDB, request)) {
				contenidoActualizacion.delete();
				return;
			}
		}

		if(scriptSH!=null) {
			if(!actualizarHD(scriptSH, request, rutaContenidoActualizacion, backupSH, rollbackSH, commitSH)) {
				contenidoActualizacion.delete();
				return;
			}
			contenidoActualizacion.delete();
			saveSuccessMessage(request, "actualizarPlataforma.actualizacion.fin");
			return;
		}
		contenidoActualizacion.delete();
		saveSuccessMessage(request, "actualizarPlataforma.actualizacion.correcta");
	}

	
	/**
	 * Devuelve el nombre de la carpeta en la que esta la instancia de JBoss donde se
	 * ejecuta el codigo
	 */
	private String obtenerHomeJboss() throws Exception {
		String homeJboss = "";
		try {
			homeJboss = System.getProperty("jboss.home.dir");
		} catch (Exception e) {
			logger.error("Hubo un problema al recuperar el directorio HOME de JBoss");
		}
		return homeJboss;
	}
	
	
	/**
	 * Devuelve el nombre de la carpeta en la que esta la instancia de JBoss donde se
	 * ejecuta el codigo
	 */
	private String obtenerInstanciaJbossLocal() throws Exception {
		String nombreInstancia = "";
		try {
			nombreInstancia = System.getProperty("jboss.server.name");
		} catch (Exception e) {
			logger.error("Hubo un problema al recuperar el directorio HOME de la instancia de JBoss");
		}
		return nombreInstancia;
	}


	/**
	 * Devuelve el path en el que esta la carpeta server de JBoss
	 */
	private String obtenerDirectorioServerJboss() throws Exception {
		String serverJboss = "";
		try {
			serverJboss = System.getProperty("jboss.server.base.dir");
		} catch (Exception e) {
			logger.error("Hubo un problema al recuperar el el path de la carpeta server de Jboss");
		}
		return serverJboss;
	}
	
	
	/**
	 * Introduce las variables de entorno que pueden ser usadas en scripts de shell
	 * @param pb
	 * @throws Exception
	 */
	private void setEnvironment (ProcessBuilder pb, String rutaScripts) throws Exception {
		//Metemos variables de entorno que podra usar el script
		Map<String, String> env = pb.environment();
		env.put(ENV_UPLOADS, obtenerHomeJboss()+File.separator+"bin"+File.separator+"uploads");
		env.put(ENV_JBOSS_HOME, obtenerHomeJboss());
		env.put(ENV_INSTANCIA, obtenerInstanciaJbossLocal());
		env.put(ENV_JBOSS_SERVER, obtenerDirectorioServerJboss());
		//Ajustamos el working directory
		env.put(ENV_WORKING_DIRECTORY, rutaScripts);
		pb.directory(new File(rutaScripts));
		//Redirigimos los errores al InputStream
		pb.redirectErrorStream(true);
	}
	
	
	private void inputStreamToFile (InputStream is, File f) throws Exception {
		
		OutputStream os = new FileOutputStream(f);
		int read = 0;
		byte[] bytes = new byte[1024];
		
		while ((read = is.read(bytes)) != -1) {
			os.write(bytes, 0, read);
		}
		 
		if (is != null) is.close();
		
		if (os != null) {
			// os.flush();
			os.close();
		}
    }
	
	
	/**
	 * Metodo que se encarga de lanzar un script generico
	 * @param script
	 * @param request: para introducir mensajes de error que se mostraran por pantalla
	 * @param rutaAbsolutaScripts: para ajustar el directorio de trabajo
	 * @param log: fichero de log 
	 * @return
	 * @throws Exception
	 */
	private boolean lanzarSH (File script, HttpServletRequest request, String rutaAbsolutaScripts, File log, String nombreFicherosLog) throws Exception {
		
		if(script==null) return false;
		
		script.setExecutable(true);
		ProcessBuilder pb = new ProcessBuilder(script.getAbsolutePath());
		setEnvironment(pb, rutaAbsolutaScripts);
		
		try {
			Process p = pb.start();
			inputStreamToFile(p.getInputStream(), log);
			p.waitFor(); // Wait for the process to finish
			
		} catch (Exception e) {
			logger.error("Error al lanzar el script "+script.getName(), e);
			String[] error = new String[2];
			error[0]=e.getMessage();
			error[1]=nombreFicherosLog;
			saveErrorMessage(request, "errors.falloScriptHD", error);
			return false;
		}
		return true;
	}
	
	
	private boolean actualizarHD (File scriptSH, HttpServletRequest request, String rutaAbsolutaScripts, File backupSH, File rollbackSH, File commitSH) throws Exception {
		
		Long milis=System.currentTimeMillis();
		String prefijoPathLogs=System.getProperty("jboss.server.log.dir")+File.separator+"update";
		String nombreFicherosLog=prefijoPathLogs+"*"+milis+".log";
		
		String logScriptSH=prefijoPathLogs+"Script"+milis+".log";
		String logBackupSH=prefijoPathLogs+"Backup"+milis+".log";
		String logRollbackSH=prefijoPathLogs+"Rollback"+milis+".log";
		String logCommitSH=prefijoPathLogs+"Commit"+milis+".log";
				
		if(scriptSH==null) {
			logger.error("actualizarHD no ha recibido el scriptSH");
			saveErrorMessage(request, "errors.falloScriptHD");
			return false;
		}
		
		//Ejecutamos backup si existe
		if(backupSH!=null) {
			if(!lanzarSH(backupSH, request, rutaAbsolutaScripts, new File(logBackupSH), nombreFicherosLog))
				return false;
		}

		//Ejecutamos el script principal
		if(!lanzarSH(scriptSH, request, rutaAbsolutaScripts, new File(logScriptSH), nombreFicherosLog)) {

			//Ejecutamos rollback si existe
			if(rollbackSH!=null) {
				if(!lanzarSH(rollbackSH, request, rutaAbsolutaScripts, new File(logRollbackSH), nombreFicherosLog)) {
					return false;
				}
			}
			return false;
		}
		
		//Ejecutamos commit si existe
		if(commitSH!=null) {
			if(!lanzarSH(commitSH, request, rutaAbsolutaScripts, new File(logCommitSH), nombreFicherosLog))
				return false;
		}

		String[] argsOK = new String[1]; 
		argsOK[0] = nombreFicherosLog;
		saveSuccessMessage(request, "actualizarPlataforma.OKscriptHD", argsOK);
		return true;
	}
	
	
	/**
	 * Metodo que parsea un script SQL devolviendo un vector de String
	 * Cada posicion del vector devuelto es un unico statement, o dicho 
	 * de otra forma, una sola orden SQL.
	 * 
	 * Este splitter necesita que la sintaxis SQL sea muy estricta:
	 * - Cada orden SQL debe acabar con ; para delimitar su fin
	 * - El caracter ; de fin de orden SQL siempre debe ir seguido de un salto de linea
	 * - Los comentarios deben empezar por -- en cada linea comentada
	 * 
	 * @param scriptDB
	 * @return
	 */
	private ArrayList<String> sqlSplitter (File scriptDB) throws Exception {
		
        String lineaLeida = new String();  
		
		FileReader fr = new FileReader(scriptDB);  
        BufferedReader br = new BufferedReader(fr);  
        
        ArrayList<String> statementsList = new ArrayList<String>();
        String statement = "";

        while((lineaLeida = br.readLine()) != null) {  

        	// be sure to not have line starting with "--"   
            if(!lineaLeida.trim().startsWith("--")) {
        		
            	statement=statement+(lineaLeida);
        		
            	if(lineaLeida.trim().endsWith(";")) {
            		statementsList.add(statement);
            		statement="";
            	} 
            }
        }  
        br.close();  
        return statementsList;
	}
	
	
	/**
	 * Metodo que se encarga de aplicar un script a la DB
	 * @param scriptDB: script de DB a aplicar
	 * @param request: para meter mensajes de error o de otro tipo
	 * @return true si todo fue bien o false en caso contrario
	 */
	private boolean actualizarDB (File scriptDB, HttpServletRequest request) throws Exception {
		
		if(scriptDB==null) {
			logger.error("actualizarDB no ha recibido el scriptDB");
			saveErrorMessage(request, "errors.falloScriptDB");
			return false;
		}
		
		XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("userContext-dataSource.xml"));

		javax.sql.DataSource ds = (javax.sql.DataSource) factory.getBean("dataSource");
		if(ds==null) {
			logger.error("No se pudo obtener el datasource");
			saveErrorMessage(request, "errors.datasource");
			return false;
		}

		Connection con = ds.getConnection();
		if(con==null) {
			logger.error("No se pudo establecer conexion con la DB");
			saveErrorMessage(request, "errors.conexionDB");
			return false;
		}

		con.setAutoCommit(false);
		//PreparedStatement ps = con.prepareStatement();		
		Statement s = con.createStatement();
		//s.addBatch(FileUtils.readFileToString(scriptDB));
		
		ArrayList<String> ordenesSql = sqlSplitter(scriptDB);
		
        for(int i = 0; i<ordenesSql.size(); i++) {
        	s.addBatch(ordenesSql.get(i));
			try {
				s.executeBatch();
				//con.commit();
			} catch (SQLException se){
				con.rollback();
				con.close();
				logger.error("Fallo la actualización de la DB en la orden "+i+" ("+ordenesSql.get(i)+")", se);
				String[] error = new String[3];
				error[0]=String.valueOf(i);
				error[1]=ordenesSql.get(i);
				error[2]=se.getMessage();
				saveErrorMessage(request, "errors.falloScriptDB", error);
				return false;
			}
        }
		con.commit();
		con.close();

		saveSuccessMessage(request, "actualizarPlataforma.OKscriptDB");
		return true;
	}
	
	
}

