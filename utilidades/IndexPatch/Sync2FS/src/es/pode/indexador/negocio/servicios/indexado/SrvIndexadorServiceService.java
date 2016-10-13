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
