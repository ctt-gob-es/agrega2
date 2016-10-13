/*
Agrega2 es una federaci髇 de repositorios de objetos digitales educativos formada por todas las Comunidades Aut髇omas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvRegistroPlanificadorService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.planificador.negocio.servicios;

public interface SrvRegistroPlanificadorService extends java.rmi.Remote {

    /**
     * Registro del como ha ido la ejecuci贸n de un trabajo.
     */
    public java.lang.Long registrarTrabajo(es.pode.planificador.negocio.servicios.TareaEjecutadaVO trabajo) throws java.rmi.RemoteException;

    /**
     * Registro de la fecha de fin de ejecuci贸n de un trabajo.
     */
    public java.lang.Long registrarTrabajoFechaFin(es.pode.planificador.negocio.servicios.TareaEjecutadaVO trabajoEjecutado) throws java.rmi.RemoteException;

    /**
     * Nos permite registrar el resultado de la ejecucuci贸n de un
     *                 trabajo derivado (subtrabajo).
     */
    public java.lang.Long registrarTrabajoHijo(es.pode.planificador.negocio.servicios.RegistroTareaEjecutadaVO trabajoHijo) throws java.rmi.RemoteException;

    /**
     * Nos permite registrar el resultado de la ejecucuci贸n de un
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
     * Nos permite registrar el resultado de la ejecucuci贸n de un
     *                 trabajo derivado de una tarea de carga masiva.
     */
    public java.lang.Long registroTrabajoTareaCarga(es.pode.planificador.negocio.servicios.RegistroTareaCargaEjecutadaVO[] registroTareaCarga) throws java.rmi.RemoteException;
}
