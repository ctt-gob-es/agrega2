/**
 * SrvAgregadorRssService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.agregador.negocio.agregador.servicio;

public interface SrvAgregadorRssService extends java.rmi.Remote {

    /**

     */
    public void crearXML(java.lang.Long idTarea) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.agregador.negocio.agregador.servicio.FeedVO[] devuelveFeeds() throws java.rmi.RemoteException;

    /**
     * Método que recoge un parámetro con todos los datos necesarios
     * para realizar una búsqueda. Tras la búsqueda genera un fichero
     *                 xml con los resultados obtenidos y lo devuelve en
     * un DataHandler
     *                 para poder visualizarlo.
     */
    public javax.activation.DataHandler devuelveRssBusqueda(es.pode.agregador.negocio.agregador.servicio.ParametrosBusquedaAgregadorVO parametrosBusqueda, java.lang.String tipoRSS) throws java.rmi.RemoteException;

    /**

     */
    public void eliminarFichero(es.pode.agregador.negocio.agregador.servicio.ParametrosBusquedaAgregadorVO param) throws java.rmi.RemoteException;

    /**

     */
    public javax.activation.DataHandler obtenerXMLFeed(java.lang.String id) throws java.rmi.RemoteException;
}
