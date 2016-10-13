/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvIndexadorServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.pode.indexador.negocio.servicios.indexado;

public interface SrvIndexadorServiceService extends javax.xml.rpc.Service {

/**
 * Este servicio implementa todos los metodos necesarios para
 *             indexar informacion en los indices. Ademas de indexar,
 * se puede
 *             gestionar el reindexado y la eliminacion de elementos.
 */
    public java.lang.String getSrvIndexadorServiceAddress();

    public es.pode.indexador.negocio.servicios.indexado.SrvIndexadorService getSrvIndexadorService() throws javax.xml.rpc.ServiceException;

    public es.pode.indexador.negocio.servicios.indexado.SrvIndexadorService getSrvIndexadorService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
