// license-header java merge-point
package es.pode.visualizador.presentacion.acceso;

public class AccesoControllerFactory
{
    private final static AccesoController INSTANCE = new AccesoControllerTomcatImpl();

    public final static AccesoController getAccesoControllerInstance()
    {
        return INSTANCE;
    }
}