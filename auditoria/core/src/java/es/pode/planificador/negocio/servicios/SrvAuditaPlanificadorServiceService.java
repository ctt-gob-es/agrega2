/**
 * SrvAuditaPlanificadorServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.planificador.negocio.servicios;

public interface SrvAuditaPlanificadorServiceService extends javax.xml.rpc.Service {

/**
 * Servicio que sirve la información al servicio de Auditoria
 */
    public java.lang.String getSrvAuditaPlanificadorServiceAddress();

    public es.pode.planificador.negocio.servicios.SrvAuditaPlanificadorService getSrvAuditaPlanificadorService() throws javax.xml.rpc.ServiceException;

    public es.pode.planificador.negocio.servicios.SrvAuditaPlanificadorService getSrvAuditaPlanificadorService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
