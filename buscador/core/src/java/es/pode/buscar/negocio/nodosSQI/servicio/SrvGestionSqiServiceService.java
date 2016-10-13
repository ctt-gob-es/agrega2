/**
 * SrvGestionSqiServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.nodosSQI.servicio;

public interface SrvGestionSqiServiceService extends javax.xml.rpc.Service {

/**
 * El servicio para la gestion de los nodos SQI
 */
    public java.lang.String getSrvGestionSqiServiceAddress();

    public es.pode.buscar.negocio.nodosSQI.servicio.SrvGestionSqiService getSrvGestionSqiService() throws javax.xml.rpc.ServiceException;

    public es.pode.buscar.negocio.nodosSQI.servicio.SrvGestionSqiService getSrvGestionSqiService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
