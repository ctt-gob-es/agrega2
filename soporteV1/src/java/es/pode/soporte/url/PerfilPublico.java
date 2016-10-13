package es.pode.soporte.url;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;

public class PerfilPublico {

	
	private static Logger logger = Logger.getLogger(PerfilPublico.class);
	protected final static String URL_SEPARATOR = "/";
	protected final static String USUARIOS_URL_PREFIX = "usuarios";
	protected final static String GRUPOS_URL_PREFIX = "grupos";
	
	/**
	 * Construye la url de la p�gina p�blica del usuario
	 * @param nombreUsuario el logging del usuario
	 * @return URL que permite acceder a la p�gina p�blica del usuario.
	 * @throws UnsupportedEncodingException 
	 */
	public static String urlUsuarioPublico(String nombreUsuario) throws UnsupportedEncodingException
	{
		StringBuffer sb = new StringBuffer();
		sb.append(AgregaPropertiesImpl.getInstance().getUrlNodo());
		sb.append(URL_SEPARATOR);
		sb.append(USUARIOS_URL_PREFIX);
		sb.append(URL_SEPARATOR);

		String nombreCodificado=replaceAll(nombreUsuario);
		
		sb.append(nombreCodificado);
//		String codificado = URLEncoder.encode(nombreUsuario, "UTF-8");
		
		return sb.toString();
	}
	
	/**
	 * Construye la url de la p�gina p�blica del grupo p�blico
	 * @param nombreGrupo el nombre del grupo p�blico
	 * @return URL que permite acceder a la p�gina p�blica del grupo p�blico.
	 * @throws UnsupportedEncodingException 
	 */
	public static String urlGrupoPublico(String nombreGrupo) throws UnsupportedEncodingException
	{
		StringBuffer sbGrupo = new StringBuffer();
		sbGrupo.append(AgregaPropertiesImpl.getInstance().getUrlNodo());
		sbGrupo.append(URL_SEPARATOR);
		sbGrupo.append(GRUPOS_URL_PREFIX);
		sbGrupo.append(URL_SEPARATOR);

		String nombreCodificado=replaceAll(nombreGrupo);
		sbGrupo.append(nombreCodificado);
//		String codificado = URLEncoder.encode(nombreGrupo,"UTF-8");
		return sbGrupo.toString();
	}
	/**
	 * Encoda el texto que le pasamos
	 * @param texto El texto a encodar
	 * @return String  el texto encodado
	 * @throws UnsupportedEncodingException 
	 */
	public static String encodarTexto(String texto) throws UnsupportedEncodingException
	{
		String nombreCodificado=replaceAll(texto);

		return nombreCodificado;
	}
	private static String replaceAll(String s){
		try {
			if (logger.isDebugEnabled())logger.debug("codificar para="+s);
			s = s.replaceAll( "�", "%C1" );
			s = s.replaceAll( "�", "%C9" );
			s = s.replaceAll( "�", "%CD" );
			s = s.replaceAll( "�", "%D3" );
			s = s.replaceAll( "�", "%DA" );
			s = s.replaceAll( "�", "%E1" );
			s = s.replaceAll( "�", "%E9" );
			s = s.replaceAll( "�", "%ED" );
			s = s.replaceAll( "�", "%F3" );
			s = s.replaceAll( "�", "%FA" );
			s = s.replaceAll( " ", "%20" ); 
			s = s.replaceAll( "�", "%C0" ); 
			s = s.replaceAll( "�", "%C1" ); 
			s = s.replaceAll( "�", "%C2" ); 
			s = s.replaceAll( "�", "%C3" ); 
			s = s.replaceAll( "�", "%C4" ); 
			s = s.replaceAll( "�", "%C8" ); 
			s = s.replaceAll( "�", "%C9" ); 
			s = s.replaceAll( "�", "%CA" ); 
			s = s.replaceAll( "�", "%CB" ); 
			s = s.replaceAll( "�", "%CC" ); 
			s = s.replaceAll( "�", "%CD" ); 
			s = s.replaceAll( "�", "%CE" ); 
			s = s.replaceAll( "�", "%CF" ); 
			s = s.replaceAll( "�", "%D2" ); 
			s = s.replaceAll( "�", "%D3" ); 
			s = s.replaceAll( "�", "%D4" ); 
			s = s.replaceAll( "�", "%D5" ); 
			s = s.replaceAll( "�", "%D6" ); 
			s = s.replaceAll( "�", "%D9" );
			s = s.replaceAll( "�", "%DA" );
			s = s.replaceAll( "�", "%DB" );
			s = s.replaceAll( "�", "%DC" );
			s = s.replaceAll( "�", "%E0" );
			s = s.replaceAll( "�", "%E1" );
			s = s.replaceAll( "�", "%E2" );
			s = s.replaceAll( "�", "%E3" );
			s = s.replaceAll( "�", "%E4" );
			s = s.replaceAll( "�", "%E8" );
			s = s.replaceAll( "�", "%E9" );
			s = s.replaceAll( "�", "%EA" );
			s = s.replaceAll( "�", "%EB" );
			s = s.replaceAll( "�", "%EC" );
			s = s.replaceAll( "�", "%ED" );
			s = s.replaceAll( "�", "%EE" );
			s = s.replaceAll( "�", "%EF" );
			s = s.replaceAll( "�", "%F2" );
			s = s.replaceAll( "�", "%F3" );
			s = s.replaceAll( "�", "%F4" );
			s = s.replaceAll( "�", "%F5" );
			s = s.replaceAll( "�", "%F6" );
			s = s.replaceAll( "�", "%F9" );
			s = s.replaceAll( "�", "%FA" );
			s = s.replaceAll( "�", "%FB" );
			s = s.replaceAll( "�", "%FC" );	
			s = s.replaceAll( "�", "%C7" );
			s = s.replaceAll( "�", "%E7" );
			s = s.replaceAll( "�", "%F1" );
			s = s.replaceAll( "�", "%D1" );
			s = s.replaceAll( "'", "%27" );
			s = s.replaceAll( "�", "%B4" );
			s = s.replaceAll( ":", "%3A" );
			s = s.replaceAll( "/", "%2F" );
			if(logger.isDebugEnabled())logger.debug("texto codificado="+s);
			return s;
		} catch ( Exception ex ) {
			logger.error("Error codificando texto="+s, ex);
			return "";
		}
	}
	
}
