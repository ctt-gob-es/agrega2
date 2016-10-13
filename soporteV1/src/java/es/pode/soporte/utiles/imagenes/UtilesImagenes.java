/**
 * 
 */
package es.pode.soporte.utiles.imagenes;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

/**
 * @author dgonzalezd
 *
 */
public class UtilesImagenes {
	private static Logger logger = Logger.getLogger(UtilesImagenes.class);
	
	static public void escala(File imagenOriginal, File imagenFinal, int ancho, int alto, String formato) {
		try {
			Image image = ImageIO.read(imagenOriginal);
		
			BufferedImage bufferedImage = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2D = bufferedImage.createGraphics();
			//Interpolación bicúbica, que pinta mejor que bilinear
			graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			graphics2D.drawImage(image,0,0,ancho,alto,null);
			
			try {
				ImageIO.write(bufferedImage, formato, imagenFinal);
			} catch (IOException e) {
				logger.error("Error guardando imagen escalada.",e);
			}
			
		} catch (IOException e) {
			logger.error("Error al leer imagen origen.",e);
		}		

	}
}
