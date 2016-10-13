/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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
     * Metodo que dado una licencia A y un manifest predice si al
     *                 cambiar la licencia del manifesto por la licencia
     * A el manifest
     */
    public es.pode.catalogacion.licencias.servicio.ValidarLicenciasVO predecirCompatibilidad(java.lang.String pathManifest, java.lang.String licencia) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.catalogacion.licencias.servicio.ValidarLicenciasVO validarLicencias(java.lang.String pathManifest) throws java.rmi.RemoteException;
}
