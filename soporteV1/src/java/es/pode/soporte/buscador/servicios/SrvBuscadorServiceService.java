/**
 * SrvBuscadorServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.soporte.buscador.servicios;

public interface SrvBuscadorServiceService extends javax.xml.rpc.Service {

/**
 * Este servicio implementa la funcionalidad de busqueda de odes
 *             sobre los odes indexados.
 */
    public java.lang.String getSrvBuscadorServiceAddress();

    public es.pode.soporte.buscador.servicios.SrvBuscadorService getSrvBuscadorService() throws javax.xml.rpc.ServiceException;

    public es.pode.soporte.buscador.servicios.SrvBuscadorService getSrvBuscadorService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
