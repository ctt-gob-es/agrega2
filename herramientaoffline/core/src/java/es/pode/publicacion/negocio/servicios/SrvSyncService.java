/**
 * SrvSyncService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.publicacion.negocio.servicios;

public interface SrvSyncService extends java.rmi.Remote {

    /**

     */
    public java.lang.String[] descargarODEs(es.pode.publicacion.negocio.servicios.ODESyncVO[] odes, java.lang.String clave, java.lang.String usuario) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.publicacion.negocio.servicios.ODESyncVO[] obtenerODEs(java.lang.String usuario, java.lang.String clave) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.publicacion.negocio.servicios.ResultadoOperacionVO subirODE(java.lang.String usuario, java.lang.String clave, javax.activation.DataHandler pif, java.lang.String titulo, java.lang.String idioma, java.lang.String identificador) throws java.rmi.RemoteException;
}
