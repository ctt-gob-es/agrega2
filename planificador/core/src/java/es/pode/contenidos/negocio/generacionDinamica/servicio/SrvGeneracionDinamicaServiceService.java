/**
 * SrvGeneracionDinamicaServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.contenidos.negocio.generacionDinamica.servicio;

public interface SrvGeneracionDinamicaServiceService extends javax.xml.rpc.Service {

/**
 * Este servicio implementa la gestion de los contenidos dinamicos
 * que se generan en la plataforma.
 */
    public java.lang.String getSrvGeneracionDinamicaServiceAddress();

    public es.pode.contenidos.negocio.generacionDinamica.servicio.SrvGeneracionDinamicaService getSrvGeneracionDinamicaService() throws javax.xml.rpc.ServiceException;

    public es.pode.contenidos.negocio.generacionDinamica.servicio.SrvGeneracionDinamicaService getSrvGeneracionDinamicaService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
