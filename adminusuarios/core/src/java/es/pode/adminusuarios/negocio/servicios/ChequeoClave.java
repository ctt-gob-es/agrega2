/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.adminusuarios.negocio.servicios;

import java.util.regex.Pattern;

import org.apache.log4j.Logger;

class ChequeoClave {

	private static Pattern digito = Pattern.compile(".*\\d+.*");
	private static Pattern minuscula = Pattern.compile(".*[a-z|ñ|á|é|í|ó|ú]+.*");
	private static Pattern mayuscula = Pattern.compile(".*[A-Z|Ñ|Á|É|Í|Ó|Ú]+.*");
	private static Pattern caracteres = Pattern.compile(".*[\\.|,|;|:|\\{|\\}|\\[|\\]]+.*");
	private static int minLength = 8;
	private static Logger log = Logger.getLogger(ChequeoClave.class);
	
	/**
	 * Comprueba que se cumplan los requisitos mínimos de la clave
	 * @param clave
	 * @return <table>
	 * <tr><td>0</td> <td>Clave correcta</td></tr>
	 * <tr><td>1</td> <td>No hay d&iacute;gito</td></tr>
	 * <tr><td>2</td> <td>No hay min&uacute;scula</td></tr>
	 * <tr><td>3</td> <td>No hay may&uacute;scula</td></tr>
	 * <tr><td>4</td> <td>No hay caracteres especiales</td></tr>
	 * <tr><td>5</td> <td>No tiene longitud m&iacute;nima</td></tr>
	 *  </table>
	 */
	static int chequea(String clave) {
		
		if(clave.length()<minLength) {
			log.error("No tiene longitud minima");
			return 5;
		}
		
		if(!digito.matcher(clave).matches()) {
			log.error("No hay digitos");
			return 1;
		}
		if(!minuscula.matcher(clave).matches()) {
			log.error("No hay minusculas");
			return 2;
		}
		if(!mayuscula.matcher(clave).matches()) {
			log.error("No hay mayusculas");
			return 3;
		}
		
		if(!caracteres.matcher(clave).matches()) {
			log.error("No hay caracteres especiales");
			return 4;
		}
		
		return 0;
	}
}
