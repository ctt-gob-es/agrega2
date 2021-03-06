/**
 * SrvTaggingServerServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.tagging.negocio.servicios;

public class SrvTaggingServerServiceLocator extends org.apache.axis.client.Service implements es.pode.tagging.negocio.servicios.SrvTaggingServerService {

/**

 */

    public SrvTaggingServerServiceLocator() {
    }


    public SrvTaggingServerServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrvTaggingServerServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrvTaggingServer
    private java.lang.String SrvTaggingServer_address = "http://localhost:8080/valoracion-1/services/SrvTaggingServer";

    public java.lang.String getSrvTaggingServerAddress() {
        return SrvTaggingServer_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SrvTaggingServerWSDDServiceName = "SrvTaggingServer";

    public java.lang.String getSrvTaggingServerWSDDServiceName() {
        return SrvTaggingServerWSDDServiceName;
    }

    public void setSrvTaggingServerWSDDServiceName(java.lang.String name) {
        SrvTaggingServerWSDDServiceName = name;
    }

    public es.pode.tagging.negocio.servicios.SrvTaggingServer getSrvTaggingServer() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrvTaggingServer_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrvTaggingServer(endpoint);
    }

    public es.pode.tagging.negocio.servicios.SrvTaggingServer getSrvTaggingServer(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.pode.tagging.negocio.servicios.SrvTaggingServerSoapBindingStub _stub = new es.pode.tagging.negocio.servicios.SrvTaggingServerSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrvTaggingServerWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrvTaggingServerEndpointAddress(java.lang.String address) {
        SrvTaggingServer_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.pode.tagging.negocio.servicios.SrvTaggingServer.class.isAssignableFrom(serviceEndpointInterface)) {
                es.pode.tagging.negocio.servicios.SrvTaggingServerSoapBindingStub _stub = new es.pode.tagging.negocio.servicios.SrvTaggingServerSoapBindingStub(new java.net.URL(SrvTaggingServer_address), this);
                _stub.setPortName(getSrvTaggingServerWSDDServiceName());
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
        if ("SrvTaggingServer".equals(inputPortName)) {
            return getSrvTaggingServer();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicios.negocio.tagging.pode.es", "SrvTaggingServerService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicios.negocio.tagging.pode.es", "SrvTaggingServer"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrvTaggingServer".equals(portName)) {
            setSrvTaggingServerEndpointAddress(address);
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
