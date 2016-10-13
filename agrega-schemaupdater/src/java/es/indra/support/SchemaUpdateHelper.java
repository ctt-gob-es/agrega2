/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.indra.support;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.hibernate.tool.hbm2ddl.SchemaUpdateTask;

/**
 * Tarea de Ant que intercepta la salida estándar (el script DDL) 
 * de la tarea SchemaUpdateTask y la vuelca a un archivo, 
 * añadiendo ";" en cada línea
 * 
 * @author indra
 */
public class SchemaUpdateHelper extends SchemaUpdateTask {

	private String output;
	
	/**
	 * Devuelve la ruta del archivo en el que se va a volcar el script DDL
	 * @return
	 */
	public String getOutput() {
		return output;
	}

	/**
	 * Establece la ruta del archivo en el que se va a escribir el script DDL
	 * @param output Ruta del archivo en el que se va a escribir el script DDL
	 * @throws Exception
	 */
	public void setOutput(String output) throws Exception {
		this.output = output;

		if (output == null) {
			System.setOut(System.out);
		}
		else {
			// Redirige la salida estándar al archivo configurado
			System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(output)) {

				public void write(byte[] b) throws IOException {
					write(b, 0, b.length);
				}
				
				public synchronized void write(byte[] b, int off, int len) throws IOException {
					String str = new String(b, off, len);
					str = str.replace("\r", ";\r");
					super.write(str.getBytes(), 0, str.getBytes().length);
				}
			}));
		}
	}
}
