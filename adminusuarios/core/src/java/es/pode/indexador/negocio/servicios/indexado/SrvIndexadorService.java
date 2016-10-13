/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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
