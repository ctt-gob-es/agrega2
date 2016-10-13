/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * 
 */
package es.pode.instalador.negocio.buscarysustituir;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import es.pode.instalador.negocio.servicios.PropiedadVO;

/**
 * @author dgonzalezd
 *
 */
public class Sed {

	static String ENCODING ="UTF-8";
	
	public static boolean sustituir(String fileOrigen, String fileDestino, String cadenaVieja, String cadenaNueva) {
		try {
			
			copiar(fileOrigen,fileDestino);
			
			cadenaVieja="\\$\\{"+cadenaVieja+"\\}";
			
			String contenido = IOUtils.toString(new FileInputStream(fileDestino), ENCODING);
			//Esta comprobacion es util en ORACLE ya que para ORACLE vacio es null
			if(cadenaNueva==null)
				contenido = contenido.replaceAll(cadenaVieja, "");
			else
				contenido = contenido.replaceAll(cadenaVieja, cadenaNueva);
			IOUtils.write(contenido, new FileOutputStream(fileDestino), ENCODING);
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean sustituir(String fileOrigen, String fileDestino, PropiedadVO[] propiedades) {
		try {
			
			copiar(fileOrigen,fileDestino);
			
			for (int i = 0; i < propiedades.length; i++) {
				String plantilla = "\\$\\{"+propiedades[i].getNombre()+"\\}";
				String contenido = IOUtils.toString(new FileInputStream(fileDestino), ENCODING);
				//Esta comprobacion es util en ORACLE ya que para ORACLE vacio es null
				if(propiedades[i].getValor()==null)
					contenido = contenido.replaceAll(plantilla, "");
				else
					contenido = contenido.replaceAll(plantilla, propiedades[i].getValor());
				IOUtils.write(contenido, new FileOutputStream(fileDestino), ENCODING);
			}
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean sustituir(InputStream fileOrigen, String fileDestino, PropiedadVO[] propiedades) throws Exception{
		try {
			
			copiar(fileOrigen,fileDestino);
			
			for (int i = 0; i < propiedades.length; i++) {
				String plantilla = "\\$\\{"+propiedades[i].getNombre()+"\\}";
				String contenido = IOUtils.toString(new FileInputStream(fileDestino), ENCODING);
				//Esta comprobacion es util en ORACLE ya que para ORACLE vacio es null
				if(propiedades[i].getValor()==null)
					contenido = contenido.replaceAll(plantilla, "");
				else
					contenido = contenido.replaceAll(plantilla, propiedades[i].getValor());
				FileOutputStream salida = new FileOutputStream(fileDestino);
				IOUtils.write(contenido, salida, ENCODING);
				salida.flush();
				salida.close();
			}
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String file="agrega_atom_es.opml";
		if(args.length>0) {
			file=args[0];
		}
//		sustituir(file, "nodo", "desarrollo");

	}

	//Copiado de soporte
    public static void copiar(String fileOrigen, String fileDestino) throws Exception 
    {
    	OutputStream os=null;
    	InputStream is=null;
    	File oldDir = new File(fileOrigen);
		File newDir = new File(fileDestino);
    	try{
    		if (oldDir.isDirectory())
    		{
    			newDir.mkdirs();
    			String list[] = oldDir.list();

    			for (int i = 0; i < list.length; i++)
    			{
    				String dest1 = newDir.getAbsolutePath() + "/" + list[i];
    				String src1 = oldDir.getAbsolutePath() + "/" + list[i];
    				copiar(src1, dest1);
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
    public static void copiar(InputStream fileOrigen, String fileDestino) throws Exception 
    {
    	OutputStream os=null;
		File newFile = new File(fileDestino);
    	try{
    			os = new BufferedOutputStream(new FileOutputStream (newFile));
    			copiar(fileOrigen, os);

    	}catch(Exception e){
    		throw new Exception ("Estas intentando copiar un directorio en un archivo",e);
    	} finally {
    		if(fileOrigen!=null) fileOrigen.close();
    		if(os!=null) os.close();
    	}
    }
	//Copiado de soporte
	public static void copiar(InputStream is, OutputStream os)
			throws IOException {
		byte[] bytes=new byte[1024];
		int byteLength;
		while((byteLength=is.read(bytes))>0)
		{
			os.write(bytes,0,byteLength);
		}
	}
	
	public static boolean crearDirConPermisos(String directorio) throws Exception
	{
		File oldDir = new File(directorio);
		if (oldDir.isDirectory() && oldDir.canRead() && oldDir.canWrite())
			return true;

		//		Si la ruta es un fichero, me quejo.
		if (oldDir.isFile())
			throw new Exception("El directorio ["+directorio+"] es un fichero en el sistema. Elija otra una ruta de disco que sea un directorio o cree el directorio.");

//		Si no existe la ruta y no es un fichero, la creo
		if (!oldDir.exists())
		{
			try {
				oldDir.mkdirs(); // creamos el directorio y los directorios padres que hagan falta.
			} catch (Exception e) {
				throw new Exception("Imposible crear el directorio["+directorio+"] en el sistema. Elija otra ruta, revise la sintaxis o cree el directorio.");
			}
		}
//		El directorio existe, le damos permisos de lectura
		if (!oldDir.canWrite())
		{
			try {
				oldDir.setWritable(true);
			} catch (Exception e) {
				throw new Exception("Directorio["+directorio+"] existe, pero es imposible dar permisos de escritura al directorio. Elija otra ruta, revise la sintaxis o dele permisos de escritura.");
			}
		}
//		El directorio existe, tiene permisos de lectura, le damos permisos de escritura 
		if (!oldDir.canRead())
		{
			try {
				oldDir.setReadable(true);
			} catch (Exception e) {
				throw new Exception("Directorio["+directorio+"] existe, pero es imposible dar permisos de lectura al directorio. Elija otra ruta, revise la sintaxis o dele permisos de lectura.");
			}
		}
		return true;
	}

	public static void chequearDirectorioConPermisos(String valor) throws Exception{
		File oldDir = new File(valor);
		if (oldDir.isDirectory() && oldDir.canRead() && oldDir.canWrite())
			return;
		else
			throw new Exception("Directorio["+valor+"] no es un directorio, o no es legible, o no es escribible.");
	}
}
