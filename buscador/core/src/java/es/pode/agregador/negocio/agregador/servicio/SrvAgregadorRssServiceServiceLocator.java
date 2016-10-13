/**
 * SrvAgregadorRssServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.agregador.negocio.agregador.servicio;

public class SrvAgregadorRssServiceServiceLocator extends org.apache.axis.client.Service implements es.pode.agregador.negocio.agregador.servicio.SrvAgregadorRssServiceService {

/**

 */

    public SrvAgregadorRssServiceServiceLocator() {
    }


    public SrvAgregadorRssServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrvAgregadorRssServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrvAgregadorRssService
    private java.lang.String SrvAgregadorRssService_address = "http://localhost:8080/agregadorRSS-1.0/services/SrvAgregadorRssService";

    public java.lang.String getSrvAgregadorRssServiceAddress() {
        return SrvAgregadorRssService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SrvAgregadorRssServiceWSDDServiceName = "SrvAgregadorRssService";

    public java.lang.String getSrvAgregadorRssServiceWSDDServiceName() {
        return SrvAgregadorRssServiceWSDDServiceName;
    }

    public void setSrvAgregadorRssServiceWSDDServiceName(java.lang.String name) {
        SrvAgregadorRssServiceWSDDServiceName = name;
    }

    public es.pode.agregador.negocio.agregador.servicio.SrvAgregadorRssService getSrvAgregadorRssService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrvAgregadorRssService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrvAgregadorRssService(endpoint);
    }

    public es.pode.agregador.negocio.agregador.servicio.SrvAgregadorRssService getSrvAgregadorRssService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.pode.agregador.negocio.agregador.servicio.SrvAgregadorRssServiceSoapBindingStub _stub = new es.pode.agregador.negocio.agregador.servicio.SrvAgregadorRssServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrvAgregadorRssServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrvAgregadorRssServiceEndpointAddress(java.lang.String address) {
        SrvAgregadorRssService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.pode.agregador.negocio.agregador.servicio.SrvAgregadorRssService.class.isAssignableFrom(serviceEndpointInterface)) {
                es.pode.agregador.negocio.agregador.servicio.SrvAgregadorRssServiceSoapBindingStub _stub = new es.pode.agregador.negocio.agregador.servicio.SrvAgregadorRssServiceSoapBindingStub(new java.net.URL(SrvAgregadorRssService_address), this);
                _stub.setPortName(getSrvAgregadorRssServiceWSDDServiceName());
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
        if ("SrvAgregadorRssService".equals(inputPortName)) {
            return getSrvAgregadorRssService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "SrvAgregadorRssServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicio.agregador.negocio.agregador.pode.es", "SrvAgregadorRssService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrvAgregadorRssService".equals(portName)) {
            setSrvAgregadorRssServiceEndpointAddress(address);
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
