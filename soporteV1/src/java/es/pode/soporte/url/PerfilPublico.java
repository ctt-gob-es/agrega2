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
	 * Construye la url de la página pública del usuario
	 * @param nombreUsuario el logging del usuario
	 * @return URL que permite acceder a la página pública del usuario.
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
	 * Construye la url de la página pública del grupo público
	 * @param nombreGrupo el nombre del grupo público
	 * @return URL que permite acceder a la página pública del grupo público.
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
			s = s.replaceAll( "Á", "%C1" );
			s = s.replaceAll( "É", "%C9" );
			s = s.replaceAll( "Í", "%CD" );
			s = s.replaceAll( "Ó", "%D3" );
			s = s.replaceAll( "Ú", "%DA" );
			s = s.replaceAll( "á", "%E1" );
			s = s.replaceAll( "é", "%E9" );
			s = s.replaceAll( "í", "%ED" );
			s = s.replaceAll( "ó", "%F3" );
			s = s.replaceAll( "ú", "%FA" );
			s = s.replaceAll( " ", "%20" ); 
			s = s.replaceAll( "À", "%C0" ); 
			s = s.replaceAll( "Á", "%C1" ); 
			s = s.replaceAll( "Â", "%C2" ); 
			s = s.replaceAll( "Ã", "%C3" ); 
			s = s.replaceAll( "Ä", "%C4" ); 
			s = s.replaceAll( "È", "%C8" ); 
			s = s.replaceAll( "É", "%C9" ); 
			s = s.replaceAll( "Ê", "%CA" ); 
			s = s.replaceAll( "Ë", "%CB" ); 
			s = s.replaceAll( "Ì", "%CC" ); 
			s = s.replaceAll( "Í", "%CD" ); 
			s = s.replaceAll( "Î", "%CE" ); 
			s = s.replaceAll( "Ï", "%CF" ); 
			s = s.replaceAll( "Ò", "%D2" ); 
			s = s.replaceAll( "Ó", "%D3" ); 
			s = s.replaceAll( "Ô", "%D4" ); 
			s = s.replaceAll( "Õ", "%D5" ); 
			s = s.replaceAll( "Ö", "%D6" ); 
			s = s.replaceAll( "Ù", "%D9" );
			s = s.replaceAll( "Ú", "%DA" );
			s = s.replaceAll( "Û", "%DB" );
			s = s.replaceAll( "Ü", "%DC" );
			s = s.replaceAll( "à", "%E0" );
			s = s.replaceAll( "á", "%E1" );
			s = s.replaceAll( "â", "%E2" );
			s = s.replaceAll( "ã", "%E3" );
			s = s.replaceAll( "ä", "%E4" );
			s = s.replaceAll( "è", "%E8" );
			s = s.replaceAll( "é", "%E9" );
			s = s.replaceAll( "ê", "%EA" );
			s = s.replaceAll( "ë", "%EB" );
			s = s.replaceAll( "ì", "%EC" );
			s = s.replaceAll( "í", "%ED" );
			s = s.replaceAll( "î", "%EE" );
			s = s.replaceAll( "ï", "%EF" );
			s = s.replaceAll( "ò", "%F2" );
			s = s.replaceAll( "ó", "%F3" );
			s = s.replaceAll( "ô", "%F4" );
			s = s.replaceAll( "õ", "%F5" );
			s = s.replaceAll( "ö", "%F6" );
			s = s.replaceAll( "ù", "%F9" );
			s = s.replaceAll( "ú", "%FA" );
			s = s.replaceAll( "û", "%FB" );
			s = s.replaceAll( "ü", "%FC" );	
			s = s.replaceAll( "Ç", "%C7" );
			s = s.replaceAll( "ç", "%E7" );
			s = s.replaceAll( "ñ", "%F1" );
			s = s.replaceAll( "Ñ", "%D1" );
			s = s.replaceAll( "'", "%27" );
			s = s.replaceAll( "´", "%B4" );
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
