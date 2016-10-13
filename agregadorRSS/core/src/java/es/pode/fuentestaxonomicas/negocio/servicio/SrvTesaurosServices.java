/**
 * SrvTesaurosServices.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.fuentestaxonomicas.negocio.servicio;

public interface SrvTesaurosServices extends java.rmi.Remote {

    /**

     */
    public boolean chequearExistenciaIdioma(java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Obtiene la jerarquia de un término.
     */
    public es.pode.fuentestaxonomicas.negocio.servicio.JerarquiaVO[] obtenerJerarquia(java.lang.String id, java.lang.String nomTesauro, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Obtiene el primer nivel del Tesauro, es decir, aquellos términos
     * que no tengan padre.
     */
    public es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] obtenerPrimerNivelTesauro(java.lang.String nomTesauro, java.lang.String idioma) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.fuentestaxonomicas.negocio.servicio.JerarquiaVO[] obtenerTerminos(java.lang.String texto, java.lang.String nomTesauro, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Obtiene los términos que están relacionados pasándole como
     *                 parámetro el identificador de un término.
     */
    public es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] obtenerTerminosRelacionadosPorId(java.lang.String id, java.lang.String nomTesauro, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Obtiene los términos que están relacionados pasándole como
     *                 parámetro el texto de un término
     */
    public es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] obtenerTerminosRelacionadosPorTexto(java.lang.String textoTesauro, java.lang.String nomTesauro, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Obtiene los términos relacionados asociativamente con el
     *                 identificador que le pasamos como parámetro.
     */
    public es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] obtenerTerminosRelacionAsociativa(java.lang.String id, java.lang.String nomTesauro, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Obtiene los términos relacionados jerarquicamente con el
     *                 identificador que le pasamos como parámetro
     */
    public es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] obtenerTerminosRelacionJerarquica(java.lang.String id, java.lang.String nomTesauro, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Método que devuelve un listado con los tesauros del idioma
     *                 indicado.
     */
    public es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO[] obtenerTesauros(java.lang.String idioma) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO obtenerTesauroVigente(java.lang.String nombre) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] obtenerTextosDeIds(java.lang.String[] ids, java.lang.String idioma, java.lang.String nomTesauro) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.String obtenerVocabName(java.lang.String nomTesauro, java.lang.String idioma) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.String[] obtenerVocabNames(java.lang.String[] nomTesauros, java.lang.String idioma) throws java.rmi.RemoteException;
}
