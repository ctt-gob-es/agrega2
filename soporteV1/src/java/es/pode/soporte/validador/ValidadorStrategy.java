package es.pode.soporte.validador;

/** 
 * Clase que implemente el patrón Strategy para las validaciones de los identificadores de los Contenidos digitales 
 * 
 * @author jsimon
 */
public class ValidadorStrategy {

    IValidador objetoValidador;
    
    public ValidadorStrategy(IValidador objeto) {
    	this.objetoValidador = objeto;
    }
 
    public boolean validar(String identificador) {
    	return objetoValidador.validar(identificador);
    }
	
}
