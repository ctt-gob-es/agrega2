package es.pode.buscar.negocio.monitorizar.pool;


public class PoolBuscarException extends RuntimeException {

    public PoolBuscarException() {
        super();
    }
    
    public PoolBuscarException(String message) {
        super(message);
    }

    public PoolBuscarException(String message, Throwable cause) {
        super(message, cause);
    }

    public PoolBuscarException(Throwable cause) {
        super(cause);
    }

    public final Throwable getInitialCause() {
        return super.getCause();
    }
}
