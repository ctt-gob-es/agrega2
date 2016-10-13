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
