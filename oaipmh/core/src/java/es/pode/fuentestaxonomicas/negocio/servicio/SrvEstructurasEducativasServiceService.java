/**
 * SrvEstructurasEducativasServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.fuentestaxonomicas.negocio.servicio;

public interface SrvEstructurasEducativasServiceService extends javax.xml.rpc.Service {

/**
 * Servicio para la administracion de distintas Estructuras
 *             Educativas. Permite administrar ficheros xml que describen
 * diferentes estructuras educativas como:
 *             -Tesauros
 *             -Taxonomias
 *             -Arbol Curricular
 */
    public java.lang.String getSrvEstructurasEducativasServiceAddress();

    public es.pode.fuentestaxonomicas.negocio.servicio.SrvEstructurasEducativasService getSrvEstructurasEducativasService() throws javax.xml.rpc.ServiceException;

    public es.pode.fuentestaxonomicas.negocio.servicio.SrvEstructurasEducativasService getSrvEstructurasEducativasService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
