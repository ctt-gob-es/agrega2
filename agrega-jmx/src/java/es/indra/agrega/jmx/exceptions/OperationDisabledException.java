/**
 * 
 */
package es.indra.agrega.jmx.exceptions;

/**
 * @author jlhuertas
 *
 */
public class OperationDisabledException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2732591396627961841L;

	public OperationDisabledException(String message){
		super(message);
	}
}
