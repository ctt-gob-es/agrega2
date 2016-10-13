/**
 * SrvDescargas.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.contenidos.negocio.descargas.servicio;

public interface SrvDescargas extends java.rmi.Remote {

    /**
     * Devuelve DataHandler para descargar fichero indicado
     */
    public javax.activation.DataHandler descargar(java.lang.Long identificador) throws java.rmi.RemoteException;

    /**

     */
    public void eliminarCategoriaDesc(java.lang.String titulo, java.lang.String idioma, java.lang.Long categoria) throws java.rmi.RemoteException;

    /**
     * Elimina Descargas correspondientes a los identificadores dados
     */
    public void eliminarDescargas(java.lang.Long[] identificadores) throws java.rmi.RemoteException;

    /**

     */
    public void insertarCategoriaDesc(java.lang.String titulo, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Añade una categoría nuevo a la Descarga indicada
     */
    public void insertarCategoriaDescarga(java.lang.Long identificador, java.lang.Long id) throws java.rmi.RemoteException;

    /**
     * Añade un par título/descripción nuevo a la Descarga indicada
     */
    public void insertarDescDescarga(java.lang.Long identificador, java.lang.String idioma, java.lang.String desc, java.lang.String titulo) throws java.rmi.RemoteException;

    /**
     * Añade varios pares título/descripción nuevos a la Descarga
     *                 indicada
     */
    public void insertarDescDescargas(java.lang.Long identificador, es.pode.contenidos.negocio.descargas.servicio.DescDescargaVO[] descripciones) throws java.rmi.RemoteException;

    /**

     */
    public void modificarCategoriaDesc(java.lang.String titulo, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Modifica o crea una Descarga. Si no se indica identificador,
     * se
     *                 creará una nueva.
     *                 Devuelve identificador de la Descarga modificada.
     */
    public java.lang.Long modificarDescarga(java.lang.Long identificador, java.lang.String path, java.lang.Boolean activa, es.pode.contenidos.negocio.descargas.servicio.DescDescargaVO[] descripciones) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.contenidos.negocio.descargas.servicio.CategoriaDescargaDescVO obtenerCategoriaDesc(java.lang.String idioma, java.lang.Long categoria) throws java.rmi.RemoteException;

    /**
     * Devuelve categoría de la Descarga de identificador indicado
     */
    public es.pode.contenidos.negocio.descargas.servicio.CategoriaDescargaVO obtenerCategoriaDescarga(java.lang.Long identificador) throws java.rmi.RemoteException;

    /**
     * Devuelve lista de categorías
     */
    public es.pode.contenidos.negocio.descargas.servicio.CategoriaDescargaVO[] obtenerCategorias() throws java.rmi.RemoteException;

    /**
     * Devuelve Descarga correspondiente al identificador dado
     */
    public es.pode.contenidos.negocio.descargas.servicio.DescargaVO obtenerDescarga(java.lang.Long identificador) throws java.rmi.RemoteException;

    /**
     * Devuelve vector de Descargas cuyo estado sea activo
     */
    public es.pode.contenidos.negocio.descargas.servicio.DescargaVO[] obtenerDescargasActivas() throws java.rmi.RemoteException;

    /**
     * Devuelve vector de Descargas cuyo estado sea no activo
     */
    public es.pode.contenidos.negocio.descargas.servicio.DescargaVO[] obtenerDescargasNoActivas() throws java.rmi.RemoteException;

    /**
     * Devuelve lista de Descargas que pertenezcan a la categoría
     * dada
     */
    public es.pode.contenidos.negocio.descargas.servicio.DescargaVO[] obtenerDescargasPorCategoria(java.lang.Long idCategoria) throws java.rmi.RemoteException;

    /**
     * Devuelve vector de pares título/descripción para todos los
     *                 idiomas definidos para la Descarga de identificador
     * indicado
     */
    public es.pode.contenidos.negocio.descargas.servicio.DescDescargaVO[] obtenerDescDescargas(java.lang.Long identificador) throws java.rmi.RemoteException;

    /**
     * Devuelve lista de pares título/descripción de todas las
     *                 Descargas para un idioma concreto
     */
    public es.pode.contenidos.negocio.descargas.servicio.DescDescargaVO[] obtenerDescDescargasIdioma(es.pode.contenidos.negocio.descargas.servicio.DescargaVO[] descargas, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Devuelve el código de la última versión disponible
     */
    public java.lang.String obtenerVersion() throws java.rmi.RemoteException;
}
