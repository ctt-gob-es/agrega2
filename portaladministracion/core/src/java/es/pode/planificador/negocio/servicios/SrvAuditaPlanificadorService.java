/**
 * SrvAuditaPlanificadorService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.planificador.negocio.servicios;

public interface SrvAuditaPlanificadorService extends java.rmi.Remote {

    /**
     * este método devuelve la información que se mostrará en el
     *                 informe de carga
     */
    public es.pode.planificador.negocio.servicios.InformeCargaVO obtenerInformeCarga(java.lang.Long idTarea) throws java.rmi.RemoteException;

    /**
     * Método que devulve un array de VO con todos los procesos
     *                 planificados entre dos fechas
     */
    public es.pode.planificador.negocio.servicios.TareaEjecutadaVO[] obtenerTrabajosEjecutadosDesdeHasta(es.pode.planificador.negocio.servicios.ParametrosVO fechas) throws java.rmi.RemoteException;
}
