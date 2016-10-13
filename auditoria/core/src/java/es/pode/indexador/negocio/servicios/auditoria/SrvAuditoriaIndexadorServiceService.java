/**
 * SrvAuditoriaIndexadorServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.indexador.negocio.servicios.auditoria;

public interface SrvAuditoriaIndexadorServiceService extends javax.xml.rpc.Service {

/**
 * Esta clase implementa el servicio que da soporte a todos los
 *             metodos de auditoria del modulo de indexacion.
 */
    public java.lang.String getSrvAuditoriaIndexadorServiceAddress();

    public es.pode.indexador.negocio.servicios.auditoria.SrvAuditoriaIndexadorService getSrvAuditoriaIndexadorService() throws javax.xml.rpc.ServiceException;

    public es.pode.indexador.negocio.servicios.auditoria.SrvAuditoriaIndexadorService getSrvAuditoriaIndexadorService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
