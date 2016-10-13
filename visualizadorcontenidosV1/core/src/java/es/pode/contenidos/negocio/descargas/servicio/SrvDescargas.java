/**
 * SrvDescargas.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.contenidos.negocio.descargas.servicio;

public interface SrvDescargas extends java.rmi.Remote {

    /**

     */
    public javax.activation.DataHandler descargar(java.lang.Long identificador) throws java.rmi.RemoteException;

    /**

     */
    public void eliminarDescargas(java.lang.Long[] identificadores) throws java.rmi.RemoteException;

    /**

     */
    public void insertarDescDescarga(java.lang.Long identificador, java.lang.String idioma, java.lang.String desc, java.lang.String titulo) throws java.rmi.RemoteException;

    /**

     */
    public void insertarDescDescargas(java.lang.Long identificador, es.pode.contenidos.negocio.descargas.servicio.DescDescargaVO[] descripciones) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Long modificarDescarga(java.lang.Long identificador, java.lang.String path, java.lang.Boolean activa, es.pode.contenidos.negocio.descargas.servicio.DescDescargaVO[] descripciones) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.contenidos.negocio.descargas.servicio.DescargaVO obtenerDescarga(java.lang.Long identificador) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.contenidos.negocio.descargas.servicio.DescargaVO[] obtenerDescargasActivas() throws java.rmi.RemoteException;

    /**

     */
    public es.pode.contenidos.negocio.descargas.servicio.DescargaVO[] obtenerDescargasNoActivas() throws java.rmi.RemoteException;

    /**

     */
    public es.pode.contenidos.negocio.descargas.servicio.DescDescargaVO[] obtenerDescDescargas(java.lang.Long identificador) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.contenidos.negocio.descargas.servicio.DescDescargaVO[] obtenerDescDescargasIdioma(es.pode.contenidos.negocio.descargas.servicio.DescargaVO[] descargas, java.lang.String idioma) throws java.rmi.RemoteException;
}
