/**
 * SrvAuditoriaFederadaServicioService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.auditoria.negocio.servicios;

public interface SrvAuditoriaFederadaServicioService extends javax.xml.rpc.Service {

/**
 * Clase encargada de la generación de los informes federados
 */
    public java.lang.String getSrvAuditoriaFederadaServicioAddress();

    public es.pode.auditoria.negocio.servicios.SrvAuditoriaFederadaImportService getSrvAuditoriaFederadaServicio() throws javax.xml.rpc.ServiceException;

    public es.pode.auditoria.negocio.servicios.SrvAuditoriaFederadaImportService getSrvAuditoriaFederadaServicio(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
