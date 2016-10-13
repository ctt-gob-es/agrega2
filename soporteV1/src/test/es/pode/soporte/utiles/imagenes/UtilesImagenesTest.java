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
