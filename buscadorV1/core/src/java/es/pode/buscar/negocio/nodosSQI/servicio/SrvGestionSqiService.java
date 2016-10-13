/**
 * SrvGestionSqiService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.nodosSQI.servicio;

public interface SrvGestionSqiService extends java.rmi.Remote {

    /**
     * Metodo para dar de alta nodos de tipo SQI. Devuelve el id del
     * nodoSQI que hemos creado.
     */
    public java.lang.Long altaNodoSQI(es.pode.buscar.negocio.nodosSQI.servicio.NodoSQIVO nodoSQI) throws java.rmi.RemoteException;

    /**
     * Metodo para dar de baja los nodos de tipo SQI
     */
    public void bajaNodosSQI(java.lang.Long[] ids) throws java.rmi.RemoteException;

    /**
     * Metodo para consultar los nodos de tipo SQI
     */
    public es.pode.buscar.negocio.nodosSQI.servicio.NodoSQIVO[] consultaNodosSQI(java.lang.Long[] ids) throws java.rmi.RemoteException;

    /**
     * Método para verificar que no exista ningún otro nodo SQI dado
     * de
     *                 alta con ese nombre
     */
    public java.lang.Boolean existeNombreNodoSQI(java.lang.String nombreNodo, java.lang.Long id) throws java.rmi.RemoteException;

    /**
     * Metodo para listar los nodos de tipo SQI
     */
    public es.pode.buscar.negocio.nodosSQI.servicio.NodoDescripcionSQIVO[] listarNodosSQI() throws java.rmi.RemoteException;

    /**
     * Metodo para modificar nodos de tipo SQI. Devuelve el id del
     * nodoSQI que hemos modificado
     */
    public java.lang.Long modificarNodoSQI(es.pode.buscar.negocio.nodosSQI.servicio.NodoSQIVO nodo) throws java.rmi.RemoteException;

    /**
     * Este metodo devuelve todos los nodos SQI que estan dados de
     * alta
     *                 en la plataforma.
     */
    public es.pode.buscar.negocio.nodosSQI.servicio.NodoSQIVO[] obtenerNodosSQI() throws java.rmi.RemoteException;
}
