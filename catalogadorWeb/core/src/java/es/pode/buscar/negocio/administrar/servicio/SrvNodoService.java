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
    public void crearNodo(es.pode.buscar.negocio.administrar.servicio.NodoVO nodo) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.buscar.negocio.administrar.servicio.ValidaBajaNodoVO eliminarNodo(java.lang.Long[] ids) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.buscar.negocio.administrar.servicio.NodoVO[] listarNodos() throws java.rmi.RemoteException;

    /**

     */
    public void modificarNodo(es.pode.buscar.negocio.administrar.servicio.NodoVO nodo) throws java.rmi.RemoteException;

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
    public es.pode.buscar.negocio.administrar.servicio.ServicioVO obtenerServicio(java.lang.Long id) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.buscar.negocio.administrar.servicio.ServicioVO[] obtenerServicios() throws java.rmi.RemoteException;
}
