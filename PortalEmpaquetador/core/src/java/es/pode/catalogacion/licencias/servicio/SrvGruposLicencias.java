/**
 * SrvGruposLicencias.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.catalogacion.licencias.servicio;

public interface SrvGruposLicencias extends java.rmi.Remote {

    /**

     */
    public es.pode.catalogacion.licencias.servicio.ValidarLicenciasVO cambiaLicenciaCompatible(java.lang.String pathManifest, java.lang.String idNuevoElemento, java.lang.String usuario, java.lang.String idioma) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.catalogacion.licencias.servicio.ValidarLicenciasVO cambiaLicenciaCompatibleRuta(java.lang.String pathManifest, java.lang.String pathNuevoElemento, java.lang.String usuario, java.lang.String idioma) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.catalogacion.licencias.servicio.GruposLicenciasVO[] obtieneGrupoLicencias(java.lang.String codigosLicencias) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.catalogacion.licencias.servicio.GruposLicenciasVO[] obtieneGrupoLicenciasFichero(java.lang.String rutaManifest, java.lang.String identificador) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.String obtieneLicenciaResultante(java.lang.String licenciaOrigen, java.lang.String licenciaAdicional) throws java.rmi.RemoteException;

    /**

     */
    public boolean permiteObraDerivada(java.lang.String rutaManifest, java.lang.String identificador) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.catalogacion.licencias.servicio.ValidarLicenciasVO validarLicencias(java.lang.String pathManifest) throws java.rmi.RemoteException;
}
