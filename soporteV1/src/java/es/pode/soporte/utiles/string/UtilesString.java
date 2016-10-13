/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.soporte.utiles.string;

import java.net.URLDecoder;

import org.apache.log4j.Logger;

public class UtilesString {
	
	private static Logger logger = Logger.getLogger(UtilesString.class);

	static public String removeAccents(String s){
		try {
			if (logger.isDebugEnabled())logger.debug("removeAccents: Eliminar acentos para="+s);
			s = s.replaceAll( "[�����]", "A" );
			s = s.replaceAll( "[����]", "E" );
			s = s.replaceAll( "[����]", "I" );
			s = s.replaceAll( "[�����]", "O" );
			s = s.replaceAll( "[����]", "U" );
			s = s.replaceAll( "�", "C" );
			s = s.replaceAll( "[����]", "a" );
			s = s.replaceAll( "[����]", "e" );
			s = s.replaceAll( "[����]", "i" );
			s = s.replaceAll( "[�����]", "o" );
			s = s.replaceAll( "[����]", "u" ); 
			s = s.replaceAll( "�", "c" );
			s = s.replaceAll( "�", "'");
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
			logger.debug("PalabrasClave despu�s de urldecoder: "+s);
			s = s.replaceAll( "[�����]", "A" );
			s = s.replaceAll( "[����]", "E" );
			s = s.replaceAll( "[����]", "I" );
			s = s.replaceAll( "[�����]", "O" );
			s = s.replaceAll( "[����]", "U" );
			s = s.replaceAll( "�", "C" );
			s = s.replaceAll( "[����]", "a" );
			s = s.replaceAll( "[����]", "e" );
			s = s.replaceAll( "[����]", "i" );
			s = s.replaceAll( "[�����]", "o" );
			s = s.replaceAll( "[����]", "u" ); 
			s = s.replaceAll( "�", "c" );
			s = s.replaceAll( "�", "'");
			if (logger.isDebugEnabled())logger.debug("removeAccents: Acentos eliminados="+s);
			return s;
		} catch ( Exception ex ) {
			logger.error("removeAccents: Error generando claves con Normalizer, para cadena="+s, ex);
			return "";
		}
	}
	
}
