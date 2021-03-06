package es.pode.soporte.validador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * Clase que valida si el identificador del Contenido digital tiene un MEC correcto
 * 
 * @author jsimon
 */
public class ValidadorMEC implements IValidador {
	
	public boolean validar(String identificador) {
		 boolean vuelta=false;
		 Pattern mask = Pattern.compile("^([a-z]{2})(\\-[a-z][a-z])?\\_((?:19|20)\\d\\d)(?:0\\d|1[0-2])(3[01]|[012]\\d)\\_([0-9]{1})\\_([0-9]{7}$)");
		 Pattern mask2= Pattern.compile("^([A-Za-z]{2})(\\-[A-Za-z][A-Za-z])?\\_((?:19|20)\\d\\d)(?:0\\d|1[0-2])(3[01]|[012]\\d)([0-9]{1})([0-9]{1})\\_([0-9]{7}$)");
		 
		 Matcher matcher = mask.matcher(identificador);
		 Matcher matcher2=mask2.matcher(identificador);
		 if(matcher.matches() || matcher2.matches()){
			 vuelta=true;
		 }
		 
		 return vuelta;
	}
}

