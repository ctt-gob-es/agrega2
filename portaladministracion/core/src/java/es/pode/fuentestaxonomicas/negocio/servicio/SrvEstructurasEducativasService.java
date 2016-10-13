/**
 * SrvEstructurasEducativasService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.fuentestaxonomicas.negocio.servicio;

public interface SrvEstructurasEducativasService extends java.rmi.Remote {

    /**

     */
    public es.pode.fuentestaxonomicas.negocio.servicio.VdexVO[] actualizarFicherosVdex(es.pode.fuentestaxonomicas.negocio.servicio.ParamVdexVO[] listaVdex, es.pode.fuentestaxonomicas.negocio.servicio.TipoVdex tipo, java.lang.String identificador) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.fuentestaxonomicas.negocio.servicio.VdexVO[] actualizarVigente(es.pode.fuentestaxonomicas.negocio.servicio.ParamVdexVO[] listaVdex, es.pode.fuentestaxonomicas.negocio.servicio.TipoVdex tipo) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Boolean chequearIdiomaArbolCurricular(java.lang.String nombre) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Boolean chequearIdiomaTesauro(java.lang.String nombre) throws java.rmi.RemoteException;

    /**

     */
    public int[] eliminarFicherosVdex(es.pode.fuentestaxonomicas.negocio.servicio.ParamEliminarVO[] listaEliminar) throws java.rmi.RemoteException;

    /**

     */
    public javax.activation.DataHandler exportarVdex(java.lang.String nombre, es.pode.fuentestaxonomicas.negocio.servicio.TipoVdex tipoVdex) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.fuentestaxonomicas.negocio.servicio.VdexVO[] listarArbolCurricular() throws java.rmi.RemoteException;

    /**

     */
    public es.pode.fuentestaxonomicas.negocio.servicio.VdexVO[] listarBackups() throws java.rmi.RemoteException;

    /**

     */
    public es.pode.fuentestaxonomicas.negocio.servicio.VdexListaVO listarFicherosTaxonomia(java.lang.String identificador) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.fuentestaxonomicas.negocio.servicio.VdexListaVO[] listarFicherosTaxonomias() throws java.rmi.RemoteException;

    /**

     */
    public es.pode.fuentestaxonomicas.negocio.servicio.VdexListaVO listarFicherosTesauro(java.lang.String identificador) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.fuentestaxonomicas.negocio.servicio.VdexListaVO[] listarFicherosTesauros() throws java.rmi.RemoteException;

    /**

     */
    public es.pode.fuentestaxonomicas.negocio.servicio.VdexVO[] listarTesauros() throws java.rmi.RemoteException;

    /**

     */
    public java.lang.String[] obtenerIdsConVocabIdentifier(java.lang.String[] ids) throws java.rmi.RemoteException;

    /**

     */
    public void recargarTaxonomiasTesauros() throws java.rmi.RemoteException;

    /**

     */
    public es.pode.fuentestaxonomicas.negocio.servicio.VdexVO[] subirFicherosVdex(es.pode.fuentestaxonomicas.negocio.servicio.ParamVdexVO[] listaVdex, es.pode.fuentestaxonomicas.negocio.servicio.TipoVdex tipo) throws java.rmi.RemoteException;
}
