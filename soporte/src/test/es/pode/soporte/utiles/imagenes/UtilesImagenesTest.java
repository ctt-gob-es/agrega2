/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.soporte.utiles.imagenes;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.log4j.Logger;

import junit.framework.TestCase;

public class UtilesImagenesTest extends TestCase {
	
	private static Logger logger = Logger.getLogger(UtilesImagenesTest.class);
	
	public void testEscala() {
		URL url = this.getClass().getResource("/original.jpg");
		File original = new File(url.getFile());
		File destino;
		try {
			destino = File.createTempFile("test", "image.png");
			UtilesImagenes.escala(original, destino, 800, 600, "png");
			assertTrue("Existe el fichero destino", destino.exists());
			logger.debug("Ruta final:"+destino.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
