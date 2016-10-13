/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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
