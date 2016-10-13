/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvOaiPmhService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.oaipmh.negocio.servicios;

public interface SrvOaiPmhService extends java.rmi.Remote {

    /**

     */
    public java.lang.Boolean estasActivo() throws java.rmi.RemoteException;

    /**
     * Obtiene información de un registro del repositorio
     */
    public es.pode.oaipmh.negocio.servicios.ResultadoOAIRequest getRecord(es.pode.oaipmh.negocio.servicios.ParametrosOaiPmhVO parametroLlamada) throws java.rmi.RemoteException;

    /**
     * Implementa el método Identify del protocolo OAI-PMH. Obtendrá
     * información general del repositorio
     */
    public es.pode.oaipmh.negocio.servicios.ResultadoOAIRequest identify() throws java.rmi.RemoteException;

    /**
     * Implementa el método ListIdentifiers del protocolo OAI-PMH
     */
    public es.pode.oaipmh.negocio.servicios.ResultadoOAIRequest listIdentifiers(es.pode.oaipmh.negocio.servicios.ParametrosOaiPmhVO parametroLlamada) throws java.rmi.RemoteException;

    /**
     * Implementa el método ListMetadataFormat. Obtiene los distintos
     * formatos de datos que pueden existir en la plataforma
     */
    public es.pode.oaipmh.negocio.servicios.ResultadoOAIRequest listMetadataFormat(es.pode.oaipmh.negocio.servicios.ParametrosOaiPmhVO parametrosLlamada) throws java.rmi.RemoteException;

    /**
     * Implementa el método ListRecords de OAI-PMH. Obtiene todos
     * los
     *                 registros almacenados en la plataforma
     */
    public es.pode.oaipmh.negocio.servicios.ResultadoOAIRequest listRecords(es.pode.oaipmh.negocio.servicios.ParametrosOaiPmhVO parametrosLlamada) throws java.rmi.RemoteException;

    /**
     * Implementa el método ListSet. Obtiene un listado con todos
     * los
     *                 conjuntos que existen en la plataforma. Por ahora
     * no se
     *                 devolverá ningún conjunto. La salida al usuario será
     * "This
     *                 repository does not support sets".
     */
    public es.pode.oaipmh.negocio.servicios.ResultadoOAIRequest listSets() throws java.rmi.RemoteException;
}
