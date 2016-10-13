// license-header java merge-point
package es.pode.visualizador.presentacion.salir;

public class SalirControllerFactory
{
    private final static SalirController INSTANCE = new SalirControllerTomcatImpl();

    public final static SalirController getSalirControllerInstance()
    {
        return INSTANCE;
    }
}