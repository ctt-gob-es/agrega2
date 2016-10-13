/**
 * SrvGestorConfiguracionServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.configuracionPlataforma.negocio.servicios;

public class SrvGestorConfiguracionServiceServiceLocator extends org.apache.axis.client.Service implements es.pode.configuracionPlataforma.negocio.servicios.SrvGestorConfiguracionServiceService {

/**

 */

    public SrvGestorConfiguracionServiceServiceLocator() {
    }


    public SrvGestorConfiguracionServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrvGestorConfiguracionServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrvGestorConfiguracionService
    private java.lang.String SrvGestorConfiguracionService_address = "http://localhost:8080/configuracionPlataforma-1.0/services/SrvGestorConfiguracionService";

    public java.lang.String getSrvGestorConfiguracionServiceAddress() {
        return SrvGestorConfiguracionService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SrvGestorConfiguracionServiceWSDDServiceName = "SrvGestorConfiguracionService";

    public java.lang.String getSrvGestorConfiguracionServiceWSDDServiceName() {
        return SrvGestorConfiguracionServiceWSDDServiceName;
    }

    public void setSrvGestorConfiguracionServiceWSDDServiceName(java.lang.String name) {
        SrvGestorConfiguracionServiceWSDDServiceName = name;
    }

    public es.pode.configuracionPlataforma.negocio.servicios.SrvGestorConfiguracionService getSrvGestorConfiguracionService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrvGestorConfiguracionService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrvGestorConfiguracionService(endpoint);
    }

    public es.pode.configuracionPlataforma.negocio.servicios.SrvGestorConfiguracionService getSrvGestorConfiguracionService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.pode.configuracionPlataforma.negocio.servicios.SrvGestorConfiguracionServiceSoapBindingStub _stub = new es.pode.configuracionPlataforma.negocio.servicios.SrvGestorConfiguracionServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrvGestorConfiguracionServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrvGestorConfiguracionServiceEndpointAddress(java.lang.String address) {
        SrvGestorConfiguracionService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.pode.configuracionPlataforma.negocio.servicios.SrvGestorConfiguracionService.class.isAssignableFrom(serviceEndpointInterface)) {
                es.pode.configuracionPlataforma.negocio.servicios.SrvGestorConfiguracionServiceSoapBindingStub _stub = new es.pode.configuracionPlataforma.negocio.servicios.SrvGestorConfiguracionServiceSoapBindingStub(new java.net.URL(SrvGestorConfiguracionService_address), this);
                _stub.setPortName(getSrvGestorConfiguracionServiceWSDDServiceName());
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
        if ("SrvGestorConfiguracionService".equals(inputPortName)) {
            return getSrvGestorConfiguracionService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicios.negocio.configuracionPlataforma.pode.es", "SrvGestorConfiguracionServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicios.negocio.configuracionPlataforma.pode.es", "SrvGestorConfiguracionService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrvGestorConfiguracionService".equals(portName)) {
            setSrvGestorConfiguracionServiceEndpointAddress(address);
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
