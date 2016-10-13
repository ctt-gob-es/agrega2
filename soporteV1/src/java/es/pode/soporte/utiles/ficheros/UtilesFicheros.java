/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.soporte.utiles.ficheros;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;

import es.pode.soporte.zip.TrueZipDaoImpl;


public class UtilesFicheros 
{
	private static Logger logger = Logger.getLogger(UtilesFicheros.class);
//	public final static String FILE_SEPARATOR = "/";
	/**
	 * Explora el path proporcionado en busca de una lista de paths apuntando a
	 * ODEs. Los criterios para considerar un path como ODE son:
	 * <ul>
	 * <li>El path apunta a un ZIP</li>
	 * <li>El path apunta a un directorio conteniendo un imsmanifest.xml</li>
	 * </ul>
	 * 
	 * @param path	Path de la carpeta que se desea explorar
	 * @param recursivo	Si es true, se interna en las subcarpetas para localizar ODEs
	 * @param soloZip Solo considera ODEs los ficheros ZIP
	 * @return Array de los paths que se consideran ODEs dentro del path proporcionado
	 * @throws NullPointerException Si path es null
	 */
	public static String[] obtenerOdesDePath(String path, Boolean recursivo, Boolean soloZip) {
		
		if (logger.isDebugEnabled())
			logger.debug("Explorando " + path
					+ " en busca de odes con opciones recursivo = " + recursivo
					+ " y soloZip = " + soloZip);
		if(path==null) throw new NullPointerException("path==null");
		ArrayList<String> listaPaths = new ArrayList<String>();
		
		java.io.File fPath = new java.io.File(path);
		if(!fPath.isDirectory()) {
			logger.warn("El path <" + path + "> no es un directorio");
		} else {
			TrueZipDaoImpl zipDao = new TrueZipDaoImpl();
			java.io.File[] subPaths = fPath.listFiles();
			for(int i=0;i<subPaths.length;i++) {
				if(subPaths[i].isDirectory()) {
					// Comprobar si esta entrada tiene un imsmanifest.xml. En
					// caso contrario, internarse si recursivo=true
					String[] manifiestos = subPaths[i].list(new FilenameFilter() {
						public boolean accept(File path, String name) {
							return "imsmanifest.xml".equals(name);
						}
					});
					if(!soloZip && manifiestos!=null && manifiestos.length > 0) {
						// El path corresponde a un posible ODE descomprimido
						if(logger.isDebugEnabled()) logger.debug("El path " + subPaths[i] + " es un posible ODE descomprimido");
						listaPaths.add(subPaths[i].getPath());					
					}
					else if(recursivo){ 
						// Explora la subcarpeta en busca de odes
						if(logger.isDebugEnabled()) logger.debug("El path " + subPaths[i] + " no es un ODE. Exploro contenidos de la carpeta");
						listaPaths.addAll(Arrays.asList(obtenerOdesDePath(subPaths[i].getPath(), recursivo, soloZip)));
					} else if(logger.isDebugEnabled()) logger.debug("El path " + subPaths[i] + " no es un ODE");
				} else if(zipDao.esZip(subPaths[i].getPath())) {
					if(logger.isDebugEnabled()) logger.debug("El path " + subPaths[i] + " es un posible ODE comprimido");
					listaPaths.add(subPaths[i].getPath());
				} else {
					if(logger.isDebugEnabled()) logger.debug("El path " + subPaths[i] + " no es un ODE");
				}
			}
		}
		
		return listaPaths.toArray(new String[listaPaths.size()]);
	}
	
	public static String[] obtenerZipsDePath(String path, Boolean recursivo) {

		
//		if (logger.isDebugEnabled())
//			logger.debug("Explorando " + path
//					+ " en busca de zips con opciones recursivo = " + recursivo);
		if(path==null) throw new NullPointerException("path==null");
		ArrayList<String> listaPaths = new ArrayList<String>();
		
		java.io.File fPath = new java.io.File(path);
		if(!fPath.isDirectory()) {
			logger.warn("El path " + path + " no es un directorio");
		} else {
			TrueZipDaoImpl zipDao = new TrueZipDaoImpl();
			java.io.File[] subPaths = fPath.listFiles();
			for(int i=0;i<subPaths.length;i++) {
				if(subPaths[i].isDirectory()) {
					if(recursivo){ 
						// Explora la subcarpeta en busca de odes
						if(logger.isDebugEnabled()) logger.debug("El path " + subPaths[i] + " es una carpeta. Exploro contenidos");
						listaPaths.addAll(Arrays.asList(obtenerZipsDePath(subPaths[i].getPath(), recursivo)));
					} else if(logger.isDebugEnabled()) logger.debug("El path " + subPaths[i] + " no tiene mas Zips");
				} else if(zipDao.esZip(subPaths[i].getPath())) {
					if(logger.isDebugEnabled()) logger.debug("El path " + subPaths[i] + " es un Zip");
					listaPaths.add(subPaths[i].getPath());
				} else {
					if(logger.isDebugEnabled()) logger.debug("El path " + subPaths[i] + " no es un Zip");
				}
			}
		}
		
		return listaPaths.toArray(new String[listaPaths.size()]);
	
	}
	
	
	public static void eliminar(File fichero) throws Exception 
	{
		if (fichero.exists())
		{
			// es un directorio?
			if (fichero.isDirectory()) 
			{
				// cargamos los archivos del directorio en un array
				File[] archivoEliminar = fichero.listFiles();
				// eliminamos elementos del array
				for (int i = 0; i < archivoEliminar.length; i++) 
				{
					UtilesFicheros.eliminar(archivoEliminar[i]);
				}
				// eliminamos directorio
				fichero.delete();
			} 
			else 
			{
				fichero.delete();
			}
		}
	}
	
	
    public static void copiar(File oldDir, File newDir) throws Exception 
    {
    	OutputStream os=null;
    	InputStream is=null;
    	try{
    		if (oldDir.isDirectory())
    		{
    			newDir.mkdirs();
    			String list[] = oldDir.list();

    			for (int i = 0; i < list.length; i++)
    			{
    				String dest1 = newDir.getAbsolutePath() + "/" + list[i];
    				String src1 = oldDir.getAbsolutePath() + "/" + list[i];
    				copiar(new File(src1), new File(dest1));
    			}
    		}
    		else if (oldDir.isFile() && newDir.isDirectory())
    		{
    			is = new BufferedInputStream(new FileInputStream(oldDir));
    			os = new BufferedOutputStream(new FileOutputStream (newDir.getPath()+"/"+oldDir.getName()));
    			copiar(is, os);
    		} 
    		else if (oldDir.isFile())
    		{
    			is = new BufferedInputStream(new FileInputStream(oldDir));
    			os = new BufferedOutputStream(new FileOutputStream (newDir));

    			copiar(is, os);
    		}

    	}catch(Exception e){
    		throw new Exception ("Estas intentando copiar un directorio en un archivo",e);
    	} finally {
    		if(is!=null) is.close();
    		if(os!=null) os.close();
    	}
    }

	public static void copiar(InputStream is, OutputStream os)
			throws IOException {
		byte[] bytes=new byte[1024];
		int byteLength;
		while((byteLength=is.read(bytes))>0)
		{
			os.write(bytes,0,byteLength);
		}
	}
    
    /**
     * Este método copia un fichero identificado por su URL a un fichero en disco
     * 
     * */
    public static void copiarFileFormURI(String address, String localFileName) throws Exception 
    {
    	try{
    		OutputStream out = null;
    		URLConnection conn = null;
    		InputStream  in = null;
    		try {
    			URL url = new URL(address);
    			out = new BufferedOutputStream(
    					new FileOutputStream(localFileName));
    			conn = url.openConnection();
    			in = conn.getInputStream();
    			byte[] buffer = new byte[1024];
    			int numRead;
    			long numWritten = 0;
    			while ((numRead = in.read(buffer)) != -1) {
    				out.write(buffer, 0, numRead);
    				numWritten += numRead;
    			}
    			if(logger.isDebugEnabled()) logger.debug(localFileName + "\t" + numWritten);
    		} catch (Exception exception) {
    			Exception exc=new Exception ("Error escribiendo fichero [ "+localFileName+" ] ",exception);
    			logger.error(exc);
    			throw exc;
    		} finally {
    			try {
    				if (in != null) {
    					in.close();
    				}
    				if (out != null) {
    					out.close();
    				}
    			} catch (IOException ioe) {
        			Exception exc=new Exception("Error cerrando ficheros [ "+in+" o "+out+" ]", ioe);
    				logger.error(exc);
    				throw exc;
    			}
    		}

    	}catch(Exception e){
    		logger.error(e);
      		throw new Exception ("Error:"+e,e);
      	}
    }
    
    /**
     * Este método calcula un código MD5 a partir de una colección de ficheros que le pasan relativos a una ruta.
     * Se calcula el código MD5 de cada fichero y de todos los códigos se calcula el MD5.
     * 
     * */
    public static String obtenerMD5Files(String base, String[] ficheros) throws Exception {
		// MD5: lo calculo para cada fichero y despues compongo una palabra para calcular el MD5 de la misma que es lo que se devuelve
		StringBuffer buffer = new StringBuffer();
		for(int i=0;i<ficheros.length;i++) {
			if((new File(base,ficheros[i])).isFile()) buffer.append(obtenerMD5SumFromFile((new File(base,ficheros[i])).getPath()));
		}
		return obtenerMD5SumFromString(buffer.toString());
	}
    
    /**
     * Este método calcula un código MD5 a partir de un String.
     * */
    public static String obtenerMD5SumFromString(String cadena) throws Exception {
		MessageDigest digest = MessageDigest.getInstance("MD5");
		String result = "";
		try {
			byte[] bytes = digest.digest(cadena.getBytes());
			BigInteger bigInt = new BigInteger(1, bytes);
			result = bigInt.toString(16);
		} catch (Exception e) {
			logger.error("Error realizando MD5 de cadena["+cadena+"]",e);
		}
		return result;
	}
    
    /**
     * Este método calcula un código MD5 a partir de un array de Strings.
     * */
    public static String obtenerMD5SumFromString(String[] cadenas) throws Exception {
//    	 MD5: lo calculo para cada string y despues compongo una palabra para calcular el MD5 de la misma que es lo que se devuelve
		StringBuffer buffer = new StringBuffer();
		for(int i=0;i<cadenas.length;i++) {
			buffer.append(obtenerMD5SumFromString(cadenas[i]));
		}
		return obtenerMD5SumFromString(buffer.toString());
	}
    
    /**
     * Este método calcula un código MD5 a partir de un Fichero.
     * */
    public static String obtenerMD5SumFromFile(String file) throws Exception {
		String result = "";
		FileInputStream fis = null;
		MessageDigest digest = MessageDigest.getInstance("MD5");
		try {
			fis = new FileInputStream(new File(file));
			byte[] buffer = new byte[8192];
			int read = 0;
			
			while( (read = fis.read(buffer)) > 0) {
				digest.update(buffer, 0, read);
			}		
			byte[] md5sum = digest.digest();
			BigInteger bigInt = new BigInteger(1, md5sum);
			result = bigInt.toString(16);
			
		} catch (Exception e) {
			logger.error("Error realizando MD5 de fichero <"+file+">",e);
		} finally {
			if (fis!=null) org.apache.commons.io.IOUtils.closeQuietly(fis);
		}
		return result;
	}
    
    /**
	 * Mueve el contenido de un directorio a otro directorio. No elimina el contenido del directorioç
	 * antiguo.
	 * @param oldDir
	 *            Directorio origen.
	 * @param newDir
	 *            Directorio destino.
	 * @return devuelve el tamanio del directorio movido.
	 * @throws Exception 
	 */
	public static Long moveDir(File oldDir, File newDir) throws IOException {
		long longitudTransferida = 0;
		if (oldDir.isDirectory()) {
			newDir.mkdirs();
			String list[] = oldDir.list();
			for (int i = 0; i < list.length; i++) {
				String dest1 = newDir.getAbsolutePath() + "/" + list[i];
				String src1 = oldDir.getAbsolutePath() + "/" + list[i];
				longitudTransferida += moveDir(new File(src1), new File(dest1)).longValue();
			}
		} else {
			FileInputStream fin = new FileInputStream(oldDir);
			FileOutputStream fos = new FileOutputStream(newDir);
			FileChannel sourceChannel = fin.getChannel();
			FileChannel targetChannel = fos.getChannel();
			longitudTransferida = sourceChannel.size();
			sourceChannel.transferTo(0, sourceChannel.size(), targetChannel);
			sourceChannel.close();
			targetChannel.close();
		}
		return longitudTransferida;
	}
	
	
	/*
	 * Devuelve como string el contenido de un fichero
	 */
	public static String obtenerContenidoDeFichero(String pathFichero) throws IOException {

		String lineSep = System.getProperty("line.separator");
		StringBuilder texto=new StringBuilder();
		String linea="";

		try{
			FileReader fr = new FileReader(pathFichero);
			BufferedReader br = new BufferedReader(fr);
			
			while ((linea=br.readLine()) != null) {
				texto.append(linea);
				texto.append(lineSep);
			}
			br.close();
			
		} catch(IOException ex) {
			logger.error("Error leyendo el fichero", ex);
			throw ex;
		}
		return texto.toString();
	}
}
