package es.pode.soporte.url;

import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;

public class ImagenesAgrega {

	
	private static Logger logger = Logger.getLogger(ImagenesAgrega.class);
	protected final static String URL_SEPARATOR = "/";
	protected final static String URL_IMAGEN ="static/img/azul/";
	
	/**
	 * Construye la url de la imagen del logo de Agrega a partir de la imagen (localizada en los estáticos). 
	 *
	 * @return URL de la imagen del logo de Agrega.
	 */
	public static String urlImagenLogoAgrega()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(AgregaPropertiesImpl.getInstance().getUrlNodo());
		sb.append(URL_SEPARATOR);
		sb.append(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.URL_LOGO_AGREGA));
		return sb.toString();
	}
	
	
	public static String urlHrefLogo(){
		return(AgregaPropertiesImpl.getInstance().getUrlNodo());
	}

	
	public static String urlImagenDefectoUsuario(String nombreImagen){
		StringBuffer sb = new StringBuffer();
		sb.append(AgregaPropertiesImpl.getInstance().getUrlNodo());
		sb.append(URL_SEPARATOR);
		sb.append(URL_IMAGEN);
		sb.append(nombreImagen);
		return sb.toString();
		
	}
	
	public static String urlImagenDefectoGrupo(String nombreImagen){
		StringBuffer sb = new StringBuffer();
		sb.append(AgregaPropertiesImpl.getInstance().getUrlNodo());
		sb.append(URL_SEPARATOR);
		sb.append(URL_IMAGEN);
		sb.append(nombreImagen);
		return sb.toString();
		
	}
	
	public static String urlImagenDefectoAutopublicado(){
		StringBuffer sb = new StringBuffer();
		sb.append(AgregaPropertiesImpl.getInstance().getUrlNodo());
		sb.append(URL_SEPARATOR);
		sb.append(URL_IMAGEN);
		sb.append("caja_autopublicado.jpg");
		return sb.toString();
		
	}
	
	public static String[] urlImagenesDefectoGrupos(){
		StringBuffer sb = new StringBuffer();
		sb.append(AgregaPropertiesImpl.getInstance().getUrlNodo());
		sb.append(URL_SEPARATOR);
		sb.append(URL_IMAGEN);
		String comun=sb.toString();
		StringBuffer nuevo=new StringBuffer();

		String grupo1=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_1);
		String grupo2=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_2);
		String grupo3=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_3);
		String grupo4=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_4);
		String grupo5=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_5);
		String sb1=comun+grupo1;
		
		String sb2=comun+grupo2;

		String sb3=comun+grupo3;

		String sb4=comun+grupo4;

		String sb5=comun+grupo5;
		String[] sbs=new String [5];
		
		sbs[0]=sb1+";"+AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_1;
		sbs[1]=sb2+";"+AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_2;
		sbs[2]=sb3+";"+AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_3;
		sbs[3]=sb4+";"+AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_4;
		sbs[4]=sb5+";"+AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_5;
//		sb.append(nombreImagen);
		return sbs;
		
	}
	
}
