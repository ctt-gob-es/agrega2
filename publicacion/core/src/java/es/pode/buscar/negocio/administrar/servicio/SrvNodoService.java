/**
 * SrvNodoService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.administrar.servicio;

public interface SrvNodoService extends java.rmi.Remote {

    /**

     */
    public java.lang.Boolean actualizarIndices() throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Long crearNodo(es.pode.buscar.negocio.administrar.servicio.NodoVO nodo) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.buscar.negocio.administrar.servicio.ValidaBajaNodoVO eliminarNodo(java.lang.Long[] ids) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Boolean eliminarTodosLosNodos() throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Boolean estaDadoAlta(java.lang.String url, java.lang.String urlWS) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Boolean existeNombreNodo(java.lang.String nombreNodo) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.buscar.negocio.administrar.servicio.NodoVO[] listarNodos() throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Long modificarNodo(es.pode.buscar.negocio.administrar.servicio.NodoVO nodo) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.buscar.negocio.administrar.servicio.CcaaVO obtenerCCAA(java.lang.Long id) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.buscar.negocio.administrar.servicio.CcaaVO[] obtenerCCAAs() throws java.rmi.RemoteException;

    /**

     */
    public es.pode.buscar.negocio.administrar.servicio.NodoVO obtenerNodo(java.lang.Long id) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.buscar.negocio.administrar.servicio.NodoVO obtenerNodoID(java.lang.String id) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.buscar.negocio.administrar.servicio.ServicioVO obtenerServicio(java.lang.Long id) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.buscar.negocio.administrar.servicio.ServicioVO[] obtenerServicios() throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Boolean subirIndices() throws java.rmi.RemoteException;
}
