/**
 * SrvIndexadorService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.indexador.negocio.servicios.indexado;

public interface SrvIndexadorService extends java.rmi.Remote {

    /**
     * Eliminacion de un ODE de los indices
     */
    public es.pode.indexador.negocio.servicios.indexado.IndexadorVO[] eliminarODE(es.pode.indexador.negocio.servicios.indexado.IdODEVO[] localizador) throws java.rmi.RemoteException;

    /**
     * Se indexan todos los objetos del repositorio
     */
    public es.pode.indexador.negocio.servicios.indexado.IndexadorVO[] indexacionTotal(es.pode.indexador.negocio.servicios.indexado.IdODEVO[] localizadores) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.indexador.negocio.servicios.indexado.IndexadorVO[] indexarODE(es.pode.indexador.negocio.servicios.indexado.IdODEVO[] localizador) throws java.rmi.RemoteException;

    /**
     * Reindexado de todo el repositorio
     */
    public java.lang.Long reindexado(java.lang.String identificador) throws java.rmi.RemoteException;

    /**
     * Reindexado de un ODE
     */
    public es.pode.indexador.negocio.servicios.indexado.IndexadorVO[] reindexarODE(es.pode.indexador.negocio.servicios.indexado.IdODEVO[] localizador) throws java.rmi.RemoteException;
}
