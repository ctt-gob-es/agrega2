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
    public es.pode.catalogacion.licencias.servicio.GruposLicenciasVO[] obtieneGrupoLicencias(java.lang.String codigosLicencias) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.String obtieneLicenciaResultante(java.lang.String licenciaAdicional) throws java.rmi.RemoteException;
}
