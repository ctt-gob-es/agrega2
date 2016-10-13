package es.pode.presentacion.exceptions;

public class NoAutenticadoException extends Exception {
	
	public NoAutenticadoException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoAutenticadoException(Throwable cause) {
		super(cause);
	}

	public NoAutenticadoException(String string) {
		super(string);
	}
	
}
