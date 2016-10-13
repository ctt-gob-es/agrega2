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
