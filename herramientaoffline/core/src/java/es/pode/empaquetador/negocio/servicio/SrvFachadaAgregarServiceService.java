/**
 * SrvFachadaAgregarServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.empaquetador.negocio.servicio;

public interface SrvFachadaAgregarServiceService extends javax.xml.rpc.Service {

/**
 * Servicio que ofrece un único metodo para todos los posibles
 *             agregar que se ofrecen en el modulo de empaquetación.
 */
    public java.lang.String getSrvFachadaAgregarServiceAddress();

    public es.pode.empaquetador.negocio.servicio.SrvFachadaAgregarService getSrvFachadaAgregarService() throws javax.xml.rpc.ServiceException;

    public es.pode.empaquetador.negocio.servicio.SrvFachadaAgregarService getSrvFachadaAgregarService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
