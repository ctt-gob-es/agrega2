package es.pode.soporte.utiles.string;

import java.net.URLDecoder;

import org.apache.log4j.Logger;

public class UtilesString {
	
	private static Logger logger = Logger.getLogger(UtilesString.class);

	static public String removeAccents(String s){
		try {
			if (logger.isDebugEnabled())logger.debug("removeAccents: Eliminar acentos para="+s);
			s = s.replaceAll( "[ÂÀÄÁÃ]", "A" );
			s = s.replaceAll( "[ÊÈËÉ]", "E" );
			s = s.replaceAll( "[ÎÌÏÍ]", "I" );
			s = s.replaceAll( "[ÔÒÖÓÕ]", "O" );
			s = s.replaceAll( "[ÛÙÜÚ]", "U" );
			s = s.replaceAll( "Ç", "C" );
			s = s.replaceAll( "[àâäá]", "a" );
			s = s.replaceAll( "[éèêë]", "e" );
			s = s.replaceAll( "[ïîìí]", "i" );
			s = s.replaceAll( "[ôöòóõ]", "o" );
			s = s.replaceAll( "[ûüùú]", "u" ); 
			s = s.replaceAll( "ç", "c" );
			s = s.replaceAll( "‘", "'");
			if (logger.isDebugEnabled())logger.debug("removeAccents: Acentos eliminados="+s);
			return s;
		} catch ( Exception ex ) {
			logger.error("removeAccents: Error generando claves con Normalizer, para cadena="+s, ex);
			return "";
		}
	}
	
	static public String removeAccents(String s, String encoding){
		try {
			if (logger.isDebugEnabled())logger.debug("removeAccents: Eliminar acentos para="+s);

			s=URLDecoder.decode(s,encoding); //<-- Esto no estaba en el original
			logger.debug("PalabrasClave después de urldecoder: "+s);
			s = s.replaceAll( "[ÂÀÄÁÃ]", "A" );
			s = s.replaceAll( "[ÊÈËÉ]", "E" );
			s = s.replaceAll( "[ÎÌÏÍ]", "I" );
			s = s.replaceAll( "[ÔÒÖÓÕ]", "O" );
			s = s.replaceAll( "[ÛÙÜÚ]", "U" );
			s = s.replaceAll( "Ç", "C" );
			s = s.replaceAll( "[àâäá]", "a" );
			s = s.replaceAll( "[éèêë]", "e" );
			s = s.replaceAll( "[ïîìí]", "i" );
			s = s.replaceAll( "[ôöòóõ]", "o" );
			s = s.replaceAll( "[ûüùú]", "u" ); 
			s = s.replaceAll( "ç", "c" );
			s = s.replaceAll( "‘", "'");
			if (logger.isDebugEnabled())logger.debug("removeAccents: Acentos eliminados="+s);
			return s;
		} catch ( Exception ex ) {
			logger.error("removeAccents: Error generando claves con Normalizer, para cadena="+s, ex);
			return "";
		}
	}
	
	
	/**
	 * Elimina aquellos caracteres que no sean puntos, letras, numeros, espacios en blanco, guiones o barras / o \
	 * Metodo util para hacer que los nombres de ficheros no tengan caracteres que puedan causar problemas.
	 * Basicamente elimina caracteres que no son comunes en rutas de ficheros
	 * @param s
	 * @return
	 */
	static public String filtroCaracteresSimples(String s) {
		return s.replaceAll("[^.a-zA-Z0-9/\\\\ _-]", "");
	}

	
	/**
	 * The code snippet below remove the characters from a string that is not inside the range 
	 * of x20 and x7E ASCII code. The regex below strips non-printable and control characters. 
	 * But it also keeps the linefeed character \n (x0A) and the carriage return \r (x0D) characters.
	 */
	static public String removeNonASCIICharacters(String s) {
		return s.replaceAll("[^\\x0A\\x0D\\x20-\\x7E]", "");
	}
	

	//Convierte un caracter en su representacion en codigo unicade
	public static String unicodeEscaped(char ch) {
		if (ch < 0x10) {
			return "\\u000" + Integer.toHexString(ch).toLowerCase();
		} else if (ch < 0x100) {
			return "\\u00" + Integer.toHexString(ch).toLowerCase();
		} else if (ch < 0x1000) {
			return "\\u0" + Integer.toHexString(ch).toLowerCase();
		}
		return "\\u" + Integer.toHexString(ch).toLowerCase();
	}
  
	
	//Convierte un string de caracteres en su representacion
	//en codigos unicade
	public static String string2unicode (String s) {
		String retorno = "";
		for(int i=0; i<s.length(); i++)
			retorno=retorno+unicodeEscaped(s.charAt(i));
		return retorno;
	}	
	

	//Traduce los codigos unicode combinados mas comunes en codigos simples.
	//Los codigos combinados unicode producen problemas en el visualizador de agrega
	//provocando que no se vea el ODE o una parte de el.
	//http://en.wikipedia.org/wiki/List_of_Unicode_characters
	//http://en.wikipedia.org/wiki/List_of_XML_and_HTML_character_entity_references
	//http://unicode-table.com/en/
	public static String translateUnicodeSpecialCharacters (String s) {
		
		boolean carateresEspeciales=false;
		  
		//Obtenemos el string en su equivalente con caracteres unicode
		String unicodeString = string2unicode(s);
		  
		//Examinamos si existen el acento combinado (combining acute accent)
		//http://www.fileformat.info/info/unicode/char/0301/index.htm
		if(unicodeString.contains("\\u0301"))
			carateresEspeciales=true;
		//Examinamos si existen la tilde combinada de la ñ (combining tilde)
		//http://www.fileformat.info/info/unicode/char/0303/index.htm
		if(unicodeString.contains("\\u0303"))
			carateresEspeciales=true;
		//Examinamos si existen dieresis combinada
		//http://www.fileformat.info/info/unicode/char/0308/index.htm
		if(unicodeString.contains("\\u0308"))
			carateresEspeciales=true;
		//Examinamos si existen los tres puntos seguidos (horizontal ellipsis)
		//http://www.fileformat.info/info/unicode/char/2026/index.htm
		if(unicodeString.contains("\\u2026"))
			carateresEspeciales=true;
		//Examinamos si existen dobles comillas iniciales (double grave accent)
		//http://www.fileformat.info/info/unicode/char/02F5/index.htm
		if(unicodeString.contains("\\u02F5"))
			carateresEspeciales=true;
		//Examinamos si existen dobles comillas finales (double acute accent)
		//http://www.fileformat.info/info/unicode/char/02F6/index.htm
		if(unicodeString.contains("\\u02F6"))
			carateresEspeciales=true;
		//Examinamos si existen dobles comillas iniciales (LEFT DOUBLE QUOTATION MARK)
		//http://www.fileformat.info/info/unicode/char/201C/index.htm
		if(unicodeString.contains("\\u201c"))
			carateresEspeciales=true;
		//Examinamos si existen dobles comillas finales (RIGHT DOUBLE QUOTATION MARK)
		//http://www.fileformat.info/info/unicode/char/201D/index.htm
		if(unicodeString.contains("\\u201d"))
			carateresEspeciales=true;
		  
		if(!carateresEspeciales) return s;
		
		StringBuffer retorno = new StringBuffer(s);
		String codigoUnicode = "";
		int longString=s.length();
		  		  
		for(int i=0; i<longString; i++) {
			
			codigoUnicode=unicodeEscaped(retorno.charAt(i));
				  
			if(codigoUnicode.contentEquals("\\u0301")) {
				//eliminamos el caracter unicode problematico
				retorno.deleteCharAt(i);
				longString--;
				//el caracter anterior al eliminado es con el
				//que estaba asociado, por lo que lo modificamos
				//de esta forma la informacion de dos caracteres la metemos en uno solo
				if(i>0) {
					codigoUnicode=unicodeEscaped(retorno.charAt(i-1));
							  
					if(codigoUnicode.contentEquals("\\u0061")) {
						retorno.setCharAt(i-1, 'á');
									  
					} else if(codigoUnicode.contentEquals("\\u0065")) {
						retorno.setCharAt(i-1, 'é');
						  
					} else if(codigoUnicode.contentEquals("\\u0069")) {
						retorno.setCharAt(i-1, 'í');
						  
					} else if(codigoUnicode.contentEquals("\\u006f")) {
						retorno.setCharAt(i-1, 'ó');
						  
					} else if(codigoUnicode.contentEquals("\\u0075")) {
						retorno.setCharAt(i-1, 'ú');
						
					} else if(codigoUnicode.contentEquals("\\u0041")) {
						retorno.setCharAt(i-1, 'Á');
										  
					} else if(codigoUnicode.contentEquals("\\u0045")) {
						retorno.setCharAt(i-1, 'É');
						  
					} else if(codigoUnicode.contentEquals("\\u0049")) {
						retorno.setCharAt(i-1, 'Í');
						  
					} else if(codigoUnicode.contentEquals("\\u004f")) {
						retorno.setCharAt(i-1, 'Ó');
						  
					} else if(codigoUnicode.contentEquals("\\u0055")) {
						retorno.setCharAt(i-1, 'Ú');
					}
					i--;
				}
				  
			} else if(codigoUnicode.contentEquals("\\u0303")) {
				
				retorno.deleteCharAt(i);
				longString--;
				
				if(i>0) {
					codigoUnicode=unicodeEscaped(retorno.charAt(i-1));
					  
					if(codigoUnicode.contentEquals("\\u006e")) {
						retorno.setCharAt(i-1, 'ñ');
						
					} else if(codigoUnicode.contentEquals("\\u004e")) {
						retorno.setCharAt(i-1, 'ñ');
					}
					i--;
				}
				  
			} else if(codigoUnicode.contentEquals("\\u0308")) {
				
				retorno.deleteCharAt(i);
				longString--;
				
				if(i>0) {
					codigoUnicode=unicodeEscaped(retorno.charAt(i-1));
					  
					if(codigoUnicode.contentEquals("\\u0075")) {
						retorno.setCharAt(i-1, 'ü');
						
					} else if(codigoUnicode.contentEquals("\\u0055")) {
						retorno.setCharAt(i-1, 'Ü');
					}
					i--;
				}

			} else if(codigoUnicode.contentEquals("\\u2026")) {
				retorno.deleteCharAt(i);
				retorno.insert(i, "...");
				longString=longString+2;
				i=i+2;
				
			} else if(
					codigoUnicode.contentEquals("\\u02f5")||
					codigoUnicode.contentEquals("\\u02f6")||
					codigoUnicode.contentEquals("\\u201c")||
					codigoUnicode.contentEquals("\\u201d")
					) {
				retorno.deleteCharAt(i);
				retorno.insert(i, "\"");
				
			} else {
				if(retorno.charAt(i) >= 0x100)
					logger.warn("Posible caracter problematico no traducido, codigo unicode: "+codigoUnicode);
			}
			  
		}
		return retorno.toString();
	}
	
	
	static public String eliminaCaracteresProblematicosParaVisualizador (String s) {
		s = translateUnicodeSpecialCharacters(s);
		s=s.replaceAll("\\[", "_");
		s=s.replaceAll("\\]", "_");
		return s;
	}
	
}
