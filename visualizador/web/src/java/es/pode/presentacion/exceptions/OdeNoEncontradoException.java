package es.pode.presentacion.exceptions;

public class OdeNoEncontradoException extends Exception {

	public OdeNoEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}

	public OdeNoEncontradoException(Throwable cause) {
		super(cause);
	}

	public OdeNoEncontradoException(String string) {
		super(string);
	}
	
}
