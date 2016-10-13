/**
 * SrvTaxonomiaService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.fuentestaxonomicas.negocio.servicio;

public interface SrvTaxonomiaService extends java.rmi.Remote {

    /**

     */
    public java.lang.Boolean chequearIdiomaTaxonomia(java.lang.String identificador, java.lang.String idioma) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO obtenerArbolVigente() throws java.rmi.RemoteException;

    /**
     * metodo obtenerNodos
     *                 Buscara en la Taxonomia nomTaxonmia del idioma correspondiente,
     * el identificador id y devolvera los hijos de ese identificador
     */
    public es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] obtenerNodos(java.lang.String id, java.lang.String nomTaxonomia, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * metodo obtenerTaxonomia
     *                 Carga el primer nivel de hijos de una Taxonomia; el
     * padre y los
     *                 hijos
     */
    public es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] obtenerTaxonomia(java.lang.String nomTaxonomia, java.lang.String idioma) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.fuentestaxonomicas.negocio.servicio.TaxonConRutaVO[] obtenerTaxonomiaCompletaPreorden(java.lang.String nomTaxonomia, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Método que permite obtener las taxonomías del idioma indicado.
     */
    public es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO[] obtenerTaxonomias(java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Metodo que obtendra toda la ruta taxonomica que define a un
     * taxon dado.
     *                 El metodo recogera un identificador del taxon a buscar.
     * El
     *                 metodo buscara en la taxonomia todos los taxones antecedentes
     * al
     *                 taxon dado. Se devolvera una lista de TaxonVO con
     * toda la ruta.
     */
    public es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] obtenerTaxonPath(java.lang.String id, java.lang.String nomTaxonomia, java.lang.String idioma) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.fuentestaxonomicas.negocio.servicio.TaxonPathVO[] obtenerVariosTaxonPaths(java.lang.String[] ides, java.lang.String nomTaxonomia, java.lang.String idioma) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.String obtenerVocabName(java.lang.String nomTaxonomia, java.lang.String idioma) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.String[] obtenerVocabNames(java.lang.String[] nomTaxonomias, java.lang.String idioma) throws java.rmi.RemoteException;
}
