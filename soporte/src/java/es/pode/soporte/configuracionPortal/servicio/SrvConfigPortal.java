/**
 * SrvConfigPortal.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.soporte.configuracionPortal.servicio;

public interface SrvConfigPortal extends java.rmi.Remote {

    /**

     */
    public void almacenarImagen(es.pode.soporte.configuracionPortal.servicio.ImagenVO imagen) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Long crearConfiguracion(es.pode.soporte.configuracionPortal.servicio.ConfiguracionPortalVO configuracion) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.soporte.configuracionPortal.servicio.ConfiguracionPortalVO obtenerConfiguracion() throws java.rmi.RemoteException;
}
