/**
 * SrvRegistroPlanificadorServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.planificador.negocio.servicios;

public interface SrvRegistroPlanificadorServiceService extends javax.xml.rpc.Service {

/**
 * Servicio de auditoría. Nos permite registrar el resultado de las
 * ejecuciones de los trabajos y subtrabajos.
 */
    public java.lang.String getSrvRegistroPlanificadorServiceAddress();

    public es.pode.planificador.negocio.servicios.SrvRegistroPlanificadorService getSrvRegistroPlanificadorService() throws javax.xml.rpc.ServiceException;

    public es.pode.planificador.negocio.servicios.SrvRegistroPlanificadorService getSrvRegistroPlanificadorService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
