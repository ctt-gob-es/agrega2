/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvValidadorVDEXServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.validador.negocio.servicio;

public class SrvValidadorVDEXServiceServiceLocator extends org.apache.axis.client.Service implements es.pode.validador.negocio.servicio.SrvValidadorVDEXServiceService {

/**
 * El servicio contiene metodos para las siguientes tareas:
 *             A) obtenerValidacionTesauro:
 *             recibe como parÃ¡metro la ruta al xml que se quiere validar.
 * 
 *             realiza la validacion contra esquemas requerida y devuelve
 * un VO
 *             ValidaVdexVO con el resultado de la validacion.
 *             valida contra el esquema imsvdex_v1p0_thesaurus.xsd
 *             B) obtenerValidacionTaxonomia::
 *             recibe como parÃ¡metro la ruta al xml que se quiere validar.
 * 
 *             realiza la validacion contra esquemas requerida y devuelve
 * un VO
 *             ValidaVdexVO con el resultado de la validacion.
 *             valida contra el esquema imsvdex_v1p0_hierarchical.xsd
 */

    public SrvValidadorVDEXServiceServiceLocator() {
    }


    public SrvValidadorVDEXServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrvValidadorVDEXServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrvValidadorVDEXService
    private java.lang.String SrvValidadorVDEXService_address = "http://localhost:8080/validador-1/services/SrvValidadorVDEXService";

    public java.lang.String getSrvValidadorVDEXServiceAddress() {
        return SrvValidadorVDEXService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SrvValidadorVDEXServiceWSDDServiceName = "SrvValidadorVDEXService";

    public java.lang.String getSrvValidadorVDEXServiceWSDDServiceName() {
        return SrvValidadorVDEXServiceWSDDServiceName;
    }

    public void setSrvValidadorVDEXServiceWSDDServiceName(java.lang.String name) {
        SrvValidadorVDEXServiceWSDDServiceName = name;
    }

    public es.pode.validador.negocio.servicio.SrvValidadorVDEXService getSrvValidadorVDEXService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrvValidadorVDEXService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrvValidadorVDEXService(endpoint);
    }

    public es.pode.validador.negocio.servicio.SrvValidadorVDEXService getSrvValidadorVDEXService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.pode.validador.negocio.servicio.SrvValidadorVDEXServiceSoapBindingStub _stub = new es.pode.validador.negocio.servicio.SrvValidadorVDEXServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrvValidadorVDEXServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrvValidadorVDEXServiceEndpointAddress(java.lang.String address) {
        SrvValidadorVDEXService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.pode.validador.negocio.servicio.SrvValidadorVDEXService.class.isAssignableFrom(serviceEndpointInterface)) {
                es.pode.validador.negocio.servicio.SrvValidadorVDEXServiceSoapBindingStub _stub = new es.pode.validador.negocio.servicio.SrvValidadorVDEXServiceSoapBindingStub(new java.net.URL(SrvValidadorVDEXService_address), this);
                _stub.setPortName(getSrvValidadorVDEXServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SrvValidadorVDEXService".equals(inputPortName)) {
            return getSrvValidadorVDEXService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "SrvValidadorVDEXServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "SrvValidadorVDEXService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrvValidadorVDEXService".equals(portName)) {
            setSrvValidadorVDEXServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
