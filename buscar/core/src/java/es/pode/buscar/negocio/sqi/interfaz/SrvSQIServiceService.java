/**
 * SrvSQIServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.sqi.interfaz;

public interface SrvSQIServiceService extends javax.xml.rpc.Service {

/**
 * Este servicio (Web Service) implementa la interfaz SQI que tiene
 * que soportar el DRI. Esta interfaz esta definida como una
 *             coleccion de metodos los cuales tienen que cunplir cierta
 * funcionalidad que esta definida en el documento:
 *             ftp://ftp.cenorm.be/PUBLIC/CWAs/e-Europe/WS-LT/CWA15454-00-2005-Nov.pdf
 */
    public java.lang.String getSrvSQIServiceAddress();

    public es.pode.buscar.negocio.sqi.interfaz.SrvSQIService getSrvSQIService() throws javax.xml.rpc.ServiceException;

    public es.pode.buscar.negocio.sqi.interfaz.SrvSQIService getSrvSQIService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
