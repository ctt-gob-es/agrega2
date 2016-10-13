/**
 * SrvPublicacionServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.publicacion.negocio.servicios;

public class SrvPublicacionServiceServiceLocator extends org.apache.axis.client.Service implements es.pode.publicacion.negocio.servicios.SrvPublicacionServiceService {

/**

 */

    public SrvPublicacionServiceServiceLocator() {
    }


    public SrvPublicacionServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrvPublicacionServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrvPublicacionService
    private java.lang.String SrvPublicacionService_address = "http://localhost:8080/publicacion-1/services/SrvPublicacionService";

    public java.lang.String getSrvPublicacionServiceAddress() {
        return SrvPublicacionService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SrvPublicacionServiceWSDDServiceName = "SrvPublicacionService";

    public java.lang.String getSrvPublicacionServiceWSDDServiceName() {
        return SrvPublicacionServiceWSDDServiceName;
    }

    public void setSrvPublicacionServiceWSDDServiceName(java.lang.String name) {
        SrvPublicacionServiceWSDDServiceName = name;
    }

    public es.pode.publicacion.negocio.servicios.SrvPublicacionService getSrvPublicacionService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrvPublicacionService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrvPublicacionService(endpoint);
    }

    public es.pode.publicacion.negocio.servicios.SrvPublicacionService getSrvPublicacionService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.pode.publicacion.negocio.servicios.SrvPublicacionServiceSoapBindingStub _stub = new es.pode.publicacion.negocio.servicios.SrvPublicacionServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrvPublicacionServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrvPublicacionServiceEndpointAddress(java.lang.String address) {
        SrvPublicacionService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.pode.publicacion.negocio.servicios.SrvPublicacionService.class.isAssignableFrom(serviceEndpointInterface)) {
                es.pode.publicacion.negocio.servicios.SrvPublicacionServiceSoapBindingStub _stub = new es.pode.publicacion.negocio.servicios.SrvPublicacionServiceSoapBindingStub(new java.net.URL(SrvPublicacionService_address), this);
                _stub.setPortName(getSrvPublicacionServiceWSDDServiceName());
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
        if ("SrvPublicacionService".equals(inputPortName)) {
            return getSrvPublicacionService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "SrvPublicacionServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "SrvPublicacionService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrvPublicacionService".equals(portName)) {
            setSrvPublicacionServiceEndpointAddress(address);
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
