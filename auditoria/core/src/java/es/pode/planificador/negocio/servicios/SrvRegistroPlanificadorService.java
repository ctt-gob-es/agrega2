/**
 * SrvRegistroPlanificadorService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.planificador.negocio.servicios;

public interface SrvRegistroPlanificadorService extends java.rmi.Remote {

    /**
     * Registro del como ha ido la ejecución de un trabajo.
     */
    public java.lang.Long registrarTrabajo(es.pode.planificador.negocio.servicios.TareaEjecutadaVO trabajo) throws java.rmi.RemoteException;

    /**
     * Registro de la fecha de fin de ejecución de un trabajo.
     */
    public java.lang.Long registrarTrabajoFechaFin(es.pode.planificador.negocio.servicios.TareaEjecutadaVO trabajoEjecutado) throws java.rmi.RemoteException;

    /**
     * Nos permite registrar el resultado de la ejecucución de un
     *                 trabajo derivado (subtrabajo).
     */
    public java.lang.Long registrarTrabajoHijo(es.pode.planificador.negocio.servicios.RegistroTareaEjecutadaVO trabajoHijo) throws java.rmi.RemoteException;

    /**
     * Nos permite registrar el resultado de la ejecucución de un
     *                 trabajo derivado (subtrabajo) de la tarea de carga
     * masiva.
     */
    public java.lang.Long registrarTrabajoHijoCarga(es.pode.planificador.negocio.servicios.RegistroTareaCargaEjecutadaVO[] trabajoHijoCarga) throws java.rmi.RemoteException;

    /**
     * Servicio para cambiar de estado a interrumpido las tareas no
     * finalizadas correctamente
     */
    public java.lang.Long registrarTrabajoInterrumpido() throws java.rmi.RemoteException;

    /**
     * Nos permite registrar el resultado de la ejecucución de un
     *                 trabajo derivado de una tarea de carga masiva.
     */
    public java.lang.Long registroTrabajoTareaCarga(es.pode.planificador.negocio.servicios.RegistroTareaCargaEjecutadaVO[] registroTareaCarga) throws java.rmi.RemoteException;
}
