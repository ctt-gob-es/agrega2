/**
 * SrvGeneracionDinamicaService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.contenidos.negocio.generacionDinamica.servicio;

public interface SrvGeneracionDinamicaService extends java.rmi.Remote {

    /**
     * Este metodo se encarga de generar el ODE diario que se hace
     * publico. Genera un ODE para el dia actual y lo inserta en la
     *                 BBDD.
     */
    public void generaODEDiario() throws java.rmi.RemoteException;

    /**
     * Este metodo devuelve el ODE generado el dia que se le pasa.
     */
    public es.pode.contenidos.negocio.generacionDinamica.servicio.ContenidoODEVO obtenODEDiario(java.util.Calendar fecha) throws java.rmi.RemoteException;
}
