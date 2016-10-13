/**
 * SrvLogService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.contenidos.negocio.logs.servicio;

public interface SrvLogService extends java.rmi.Remote {

    /**

     */
    public es.pode.contenidos.negocio.logs.servicio.ValidaBajaLogVO eliminarFicheroLog(java.lang.String[] ficheros) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.contenidos.negocio.logs.servicio.FileVO[] listarFicherosLog() throws java.rmi.RemoteException;

    /**

     */
    public javax.activation.DataHandler recuperarFicheroLog(java.lang.String fichero) throws java.rmi.RemoteException;
}
