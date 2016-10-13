package es.indra.agrega.jmx.exceptions;

public interface FailureDetectionStrategy {

	public boolean isFailure(Throwable exception);
}
