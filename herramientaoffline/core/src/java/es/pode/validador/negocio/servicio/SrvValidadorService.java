/**
 * SrvValidadorService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.validador.negocio.servicio;

public interface SrvValidadorService extends java.rmi.Remote {

    /**

     */
    public boolean estoyActivo() throws java.rmi.RemoteException;

    /**

     */
    public es.pode.validador.negocio.servicio.ValidaVO obtenerValidacion(java.lang.String rutaOde) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.validador.negocio.servicio.ValidaVO obtenerValidacionBin(java.lang.String rutaManifest) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.validador.negocio.servicio.ValidaVO obtenervalidacionLigera(java.lang.String rutaManifest, java.lang.String tipoOde) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Boolean obtenerValidacionLomes(javax.activation.DataHandler fichero) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.validador.negocio.servicio.ValidaVO validarCargaOde(java.lang.String rutaOde) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.validador.negocio.servicio.ValidaVO validarMDBasicosObl(es.pode.validador.negocio.servicio.MDBasicosOblVO mDBasicos) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.String validarMec(java.lang.String rutaManifest) throws java.rmi.RemoteException;
}
