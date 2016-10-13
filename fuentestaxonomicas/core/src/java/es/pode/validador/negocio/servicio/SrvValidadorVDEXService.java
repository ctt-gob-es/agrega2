/**
 * SrvValidadorVDEXService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.validador.negocio.servicio;

public interface SrvValidadorVDEXService extends java.rmi.Remote {

    /**

     */
    public es.pode.validador.negocio.servicio.ValidaVdexVO obtenerValidacionTaxonomia(java.lang.String rutaVdex) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.validador.negocio.servicio.ValidaVdexVO[] obtenerValidacionTaxonomias(java.lang.String[] paramListaVdex) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.validador.negocio.servicio.ValidaVdexVO obtenerValidacionTesauro(java.lang.String rutaVdex) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.validador.negocio.servicio.ValidaVdexVO[] obtenerValidacionTesauros(java.lang.String[] paramListaVdex) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.validador.negocio.servicio.ValidaVdexVO obtenerValidacionVdex(java.lang.String rutaVdex) throws java.rmi.RemoteException;
}
