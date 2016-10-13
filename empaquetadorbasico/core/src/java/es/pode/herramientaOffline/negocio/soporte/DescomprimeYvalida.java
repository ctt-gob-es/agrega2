/**
 * DescomprimeYvalida.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.herramientaOffline.negocio.soporte;

public interface DescomprimeYvalida extends java.rmi.Remote {

    /**

     */
    public void creacion(java.lang.String idOde, java.lang.String titulo) throws java.rmi.RemoteException;

    /**

     */
    public void eliminarOdes(java.lang.String[] identificadores) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.herramientaOffline.negocio.soporte.OdeVO[] listarOdes() throws java.rmi.RemoteException;

    /**

     */
    public void modificarOde(java.lang.String idOde, java.lang.String titulo) throws java.rmi.RemoteException;
}
