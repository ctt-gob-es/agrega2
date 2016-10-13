/**
 * SrvGestorConfiguracionService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.configuracionPlataforma.negocio.servicios;

public interface SrvGestorConfiguracionService extends java.rmi.Remote {

    /**

     */
    public es.pode.configuracionPlataforma.negocio.servicios.ResultadoOperacionVO modificarPropiedades(es.pode.configuracionPlataforma.negocio.servicios.PropiedadVO[] propiedades, java.lang.String instanciaJboss) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.configuracionPlataforma.negocio.servicios.ResultadoOperacionVO modificarPropiedadesLocal(es.pode.configuracionPlataforma.negocio.servicios.PropiedadVO[] propiedades) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.configuracionPlataforma.negocio.servicios.ResultadoOperacionVO modificarValorPropiedad(java.lang.String nombrePropiedad, java.lang.String valor, java.lang.String instanciaJboss) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.configuracionPlataforma.negocio.servicios.ResultadoOperacionVO modificarValorPropiedadLocal(java.lang.String nombrePropiedad, java.lang.String valor) throws java.rmi.RemoteException;
}
