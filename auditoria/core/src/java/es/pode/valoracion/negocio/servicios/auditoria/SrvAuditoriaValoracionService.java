/**
 * SrvAuditoriaValoracionService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.valoracion.negocio.servicios.auditoria;

public interface SrvAuditoriaValoracionService extends java.rmi.Remote {

    /**
     * Este metodo permite consultar los N contenidos mas valorados
     * en
     *                 un periodo dado.
     */
    public es.pode.valoracion.negocio.servicios.auditoria.ValoracionODEVO[] contenidosMasValorados(es.pode.valoracion.negocio.servicios.auditoria.ParametroAuditValoracionVO parametro) throws java.rmi.RemoteException;
}
