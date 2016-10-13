/**
 * SrvBuscarFederadaService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.federada.servicios;

public interface SrvBuscarFederadaService extends java.rmi.Remote {

    /**

     */
    public es.pode.buscar.negocio.federada.servicios.DocumentosVO30 busquedaFederada30(es.pode.buscar.negocio.federada.servicios.ParametrosBusquedaAvanzadaVO30 parametros) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.String devolverVersionNodo() throws java.rmi.RemoteException;

    /**

     */
    public boolean estoyActivo() throws java.rmi.RemoteException;

    /**

     */
    public java.lang.String obtenerIdentificadorNodo() throws java.rmi.RemoteException;

    /**

     */
    public es.pode.buscar.negocio.federada.servicios.ResultadosNodoCountVO solicitarDocsCountArbolCurricular30(es.pode.buscar.negocio.federada.servicios.ParametrosDocsCountVO30 parametros) throws java.rmi.RemoteException;
}
