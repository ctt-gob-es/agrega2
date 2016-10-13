/*
Agrega2 es una federaciÛn de repositorios de objetos digitales educativos formada por todas las Comunidades AutÛnomas propiedad de Red.es.

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
			s = s.replaceAll( "[¬¿ƒ¡√]", "A" );
			s = s.replaceAll( "[ »À…]", "E" );
			s = s.replaceAll( "[ŒÃœÕ]", "I" );
			s = s.replaceAll( "[‘“÷”’]", "O" );
			s = s.replaceAll( "[€Ÿ‹⁄]", "U" );
			s = s.replaceAll( "«", "C" );
			s = s.replaceAll( "[‡‚‰·]", "a" );
			s = s.replaceAll( "[ÈËÍÎ]", "e" );
			s = s.replaceAll( "[ÔÓÏÌ]", "i" );
			s = s.replaceAll( "[ÙˆÚÛı]", "o" );
			s = s.replaceAll( "[˚¸˘˙]", "u" ); 
			s = s.replaceAll( "Á", "c" );
			s = s.replaceAll( "ë", "'");
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
			logger.debug("PalabrasClave despuÈs de urldecoder: "+s);
			s = s.replaceAll( "[¬¿ƒ¡√]", "A" );
			s = s.replaceAll( "[ »À…]", "E" );
			s = s.replaceAll( "[ŒÃœÕ]", "I" );
			s = s.replaceAll( "[‘“÷”’]", "O" );
			s = s.replaceAll( "[€Ÿ‹⁄]", "U" );
			s = s.replaceAll( "«", "C" );
			s = s.replaceAll( "[‡‚‰·]", "a" );
			s = s.replaceAll( "[ÈËÍÎ]", "e" );
			s = s.replaceAll( "[ÔÓÏÌ]", "i" );
			s = s.replaceAll( "[ÙˆÚÛı]", "o" );
			s = s.replaceAll( "[˚¸˘˙]", "u" ); 
			s = s.replaceAll( "Á", "c" );
			s = s.replaceAll( "ë", "'");
			if (logger.isDebugEnabled())logger.debug("removeAccents: Acentos eliminados="+s);
			return s;
		} catch ( Exception ex ) {
			logger.error("removeAccents: Error generando claves con Normalizer, para cadena="+s, ex);
			return "";
		}
	}
	
}
