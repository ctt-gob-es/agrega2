/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * 
 */
package es.pode.configuracion.buscarysustituir;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

/**
 * @author dgonzalezd
 *
 */
public class Sed {

	static String ENCODING ="UTF-8";
	
	static boolean sustituir(String fileName, String cadenaVieja, String cadenaNueva) {
		try {
			File origen = new File(fileName);
			File destino = new File(fileName+".bak");
			copiar(origen,destino);
			
			cadenaVieja="\\$\\{"+cadenaVieja+"\\}";
			
			String contenido = IOUtils.toString(new FileInputStream(fileName), ENCODING);
			contenido = contenido.replaceAll(cadenaVieja, cadenaNueva);
			IOUtils.write(contenido, new FileOutputStream(fileName), ENCODING);
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
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String file="agrega_atom_es.opml";
		if(args.length>0) {
			file=args[0];
		}
		sustituir(file, "nodo", "desarrollo");

	}

	//Copiado de soporte
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
	
}
