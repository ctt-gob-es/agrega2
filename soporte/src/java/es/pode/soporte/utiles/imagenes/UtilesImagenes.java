/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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
