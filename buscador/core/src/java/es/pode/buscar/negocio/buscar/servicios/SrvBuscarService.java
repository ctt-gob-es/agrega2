/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvBuscarService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.buscar.servicios;

public interface SrvBuscarService extends java.rmi.Remote {

    /**

     */
    public boolean borrarHitCache() throws java.rmi.RemoteException;

    /**
     * Este metodo busca en el repositorio de ODE's de acuerdo a los
     * parametros de busqueda avanzada que le pasan. La informacion de
     *                 busqueda es la que se configura en la Web. Este metodo
     * es
     *                 accesible desde el exterior a traves del interfaz
     * de Web
     *                 Services
     */
    public es.pode.buscar.negocio.buscar.servicios.ResultadoBusquedaVO buscarAvanzado(es.pode.buscar.negocio.buscar.servicios.ParametrosBusquedaAvanzadaVO30 parametros) throws java.rmi.RemoteException;

    /**
     * Este metodo implementa la busqueda deacuerdo a la informacion
     * suministrada por el modulo de SQI.
     *                 Devuelve el resultado de la busqueda en el formato
     * necesario
     *                 para el modulo de SQI.
     */
    public es.pode.buscar.negocio.buscar.servicios.ResultadoBusquedaSQIVO buscarLomEs(es.pode.buscar.negocio.buscar.servicios.ParametrosBusquedaSQIVO parametros) throws java.rmi.RemoteException;

    /**
     * Este metodo resuelve la búsqueda SQI sobre los nodos federados
     * en la plataforma que implementan el interfaz SQI.
     */
    public es.pode.buscar.negocio.buscar.servicios.ResultadoBusquedaSQIVO buscarSQI(es.pode.buscar.negocio.buscar.servicios.ParamBusquedaSQIVO consulta) throws java.rmi.RemoteException;

    /**
     * Este metodo genera un identificador para la cache de busqueda
     * para una busqueda avanzada.
     */
    public java.lang.String generadorIdentificadorAvanzado(es.pode.buscar.negocio.buscar.servicios.ParametrosBusquedaAvanzadaVO30 parametrosAvanzada) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasVO obtenerEstadisticas() throws java.rmi.RemoteException;

    /**

     */
    public es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasActividadVO obtenerEstadisticasActividadPorFechas(java.util.Calendar fechaDesde, java.util.Calendar fechaHasta) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasCoberturaCurricularVO obtenerEstadisticasCoberturaCurricularPorFechas(java.util.Calendar fechaDesde, java.util.Calendar fechaHasta) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasLicenciasVO obtenerEstadisticasLicenciasPorFechas(java.util.Calendar fechaDesde, java.util.Calendar fechaHasta) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasOdesVO obtenerEstadisticasOdesPorFechas(java.util.Calendar fechaDesde, java.util.Calendar fechaHasta) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasVO obtenerEstadisticasPorFechas(java.util.Calendar fechaDesde, java.util.Calendar fechaHasta) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasTerminosVO obtenerEstadisticasTerminosPorFechas(java.util.Calendar fechaDesde, java.util.Calendar fechaHasta) throws java.rmi.RemoteException;

    /**
     * Solicita el número de documentos existentes en el índice para
     * un
     *                 área curricular.
     */
    public es.pode.buscar.negocio.buscar.servicios.ResultadosNodoCountVO solicitarDocsCount(es.pode.buscar.negocio.buscar.servicios.ParametrosDocsCountVO30 parametros) throws java.rmi.RemoteException;

    /**
     * Este metodo realiza una busqueda directa de un ODE utilizando
     * su
     *                 identificador MEC y el idioma de busqueda sobre el
     * repositorio
     *                 local. Devuelve la meta informacion del ODE.
     */
    public es.pode.buscar.negocio.buscar.servicios.MetadatoBasicoVO solicitarMetadato(es.pode.buscar.negocio.buscar.servicios.ParametroMetadatoVO parametros) throws java.rmi.RemoteException;

    /**
     * Método que devuelve los metadatos necesarios para realizar
     *                 llamadas OaiOre
     */
    public es.pode.buscar.negocio.buscar.servicios.MetadatoOiaOreVO solicitarMetadatosOaiOre(es.pode.buscar.negocio.buscar.servicios.ParametroMetadatoVO parametros) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.buscar.negocio.buscar.servicios.MetadatoBasicoVO[] solicitarMetadatosOdes(es.pode.buscar.negocio.buscar.servicios.ParametroMetadatoVO[] parametros) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.buscar.negocio.buscar.servicios.SugerenciasBusquedaVO[] sugerirBusqueda(es.pode.buscar.negocio.buscar.servicios.ParametrosBusquedaAvanzadaVO30 parametros) throws java.rmi.RemoteException;
}
