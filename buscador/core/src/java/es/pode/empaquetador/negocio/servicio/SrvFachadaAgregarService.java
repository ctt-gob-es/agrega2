/**
 * SrvFachadaAgregarService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.empaquetador.negocio.servicio;

public interface SrvFachadaAgregarService extends java.rmi.Remote {

    /**

     */
    public void agregar(java.lang.String idOde, java.lang.String idDestino, java.lang.String tipoEmpaquetador, java.lang.String idAgregar) throws java.rmi.RemoteException;

    /**

     */
    public void agregarFederado(java.lang.String idODE, java.lang.String idDestino, java.lang.String tipoEmpaquetador, java.lang.String url) throws java.rmi.RemoteException;

    /**
     * Analiza el archivo dado y determina si es un RCP, un ODE
     *                 (devuelve "CA"), un archivo ZIP o un fichero individual.
     */
    public es.pode.empaquetador.negocio.servicio.AnalizaArchivoVO analizarArchivo(java.lang.String rutaArchivo) throws java.rmi.RemoteException;

    /**
     * Descarga el metadato visualizado de la memoria del catalogador.
     */
    public void descargarMetadato(java.lang.String identificador) throws java.rmi.RemoteException;

    /**

     */
    public void generarManifest(java.lang.String identificador, java.lang.String[] files, java.lang.String href, java.lang.String idioma) throws java.rmi.RemoteException;

    /**

     */
    public void generarManifestLom(java.lang.String identificador, java.lang.String[] files, java.lang.String href, java.lang.String idioma, java.lang.String titulo, java.lang.String descripcion, java.lang.String idiomaDestinatario, java.lang.String tipo) throws java.rmi.RemoteException;

    /**

     */
    public void generarManifestRCP(java.lang.String identificador, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Realiza las gestiones necesarias con el catalogador para poder
     * visualizar el metadato de un ODE publicado.
     */
    public java.lang.String prepararMetadatos(java.lang.String identificador) throws java.rmi.RemoteException;
}
