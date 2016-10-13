package es.pode.soporte.url;

import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.soporte.seguridad.encriptacion.EncriptacionUtiles;

public class ImagenODE {

	
	private static Logger logger = Logger.getLogger(ImagenODE.class);
	protected final static String URL_SEPARATOR = "/";
	
	/**
	 * Construye la url de la imagen del ODE a partir de su identificador. El path que da es el de la imagen peque�a.
	 * @param idODE Identificador del ODE publicado.
	 * @return URL de la imagen peque�a asociada al ODE.
	 */
	public static String urlImagenODE(String idODE)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(AgregaPropertiesImpl.getInstance().getUrlNodo());
		sb.append(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PROPERTY_IMAGE_APACHE_PATH));
		sb.append(URL_SEPARATOR);
		sb.append(EncriptacionUtiles.md5String(idODE).substring(0, 2));
		sb.append(URL_SEPARATOR);
		sb.append(idODE);
		sb.append(URL_SEPARATOR);
		sb.append(idODE);
		sb.append(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PROPERTY_IMAGE_EXTENSION));
		return sb.toString();
	}
	
	/**
	 * Devuelve un array de urls de imagenes de ODES a partir de una lista de identificadores. 
	 * El path que da es el de la imagen peque�a.
	 * @param idODEs Lista de identificadores de ODEs publicados.
	 * @return URLs de imagenes peque�as asociadas al ODE.
	 */
	public static String[] urlImagenesODES(String[] idODEs)
	{
		String[] urlImagenes = new String[idODEs.length]; 
		if(idODEs!=null && idODEs.length>0){
			for (int i = 0; i < idODEs.length; i++) {
			  urlImagenes[i]=urlImagenODE(idODEs[i]);
			}
		}
		return urlImagenes;
	}
	
	
	/**
	 * Construye la url de la imagen del ODE a partir de su identificador. El path que da es el de la imagen grande.
	 * @param idODE Identificador del ODE publicado.
	 * @return URL de la imagen grande asociada al ODE.
	 */
	public static String urlImagenGrandeODE(String idODE)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(AgregaPropertiesImpl.getInstance().getUrlNodo());
		sb.append(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PROPERTY_IMAGE_APACHE_PATH));
		sb.append(URL_SEPARATOR);
		sb.append(EncriptacionUtiles.md5String(idODE).substring(0, 2));
		sb.append(URL_SEPARATOR);
		sb.append(idODE);
		sb.append(URL_SEPARATOR);
		sb.append(idODE);
		sb.append("_captured.jpg");
		return sb.toString();
	}
	
	/**
	 * Construye la url de la imagen del ODE a partir de su identificador. El path que da es el de la imagen mediana.
	 * @param idODE Identificador del ODE publicado.
	 * @return URL de la imagen mediana asociada al ODE.
	 */
	public static String urlImagenMedianaODE(String idODE)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(AgregaPropertiesImpl.getInstance().getUrlNodo());
		sb.append(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PROPERTY_IMAGE_APACHE_PATH));
		sb.append(URL_SEPARATOR);
		sb.append(EncriptacionUtiles.md5String(idODE).substring(0, 2));
		sb.append(URL_SEPARATOR);
		sb.append(idODE);
		sb.append(URL_SEPARATOR);
		sb.append(idODE);
		sb.append("_medium.jpg");
		return sb.toString();
	}
	
	
}
