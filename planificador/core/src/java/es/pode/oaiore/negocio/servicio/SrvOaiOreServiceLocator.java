/**
 * SrvOaiOreServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.oaiore.negocio.servicio;

public class SrvOaiOreServiceLocator extends org.apache.axis.client.Service implements es.pode.oaiore.negocio.servicio.SrvOaiOreService {

/**

 */

    public SrvOaiOreServiceLocator() {
    }


    public SrvOaiOreServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrvOaiOreServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrvOaiOre
    private java.lang.String SrvOaiOre_address = "http://localhost:8080/oaipmh/services/SrvOaiOre";

    public java.lang.String getSrvOaiOreAddress() {
        return SrvOaiOre_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SrvOaiOreWSDDServiceName = "SrvOaiOre";

    public java.lang.String getSrvOaiOreWSDDServiceName() {
        return SrvOaiOreWSDDServiceName;
    }

    public void setSrvOaiOreWSDDServiceName(java.lang.String name) {
        SrvOaiOreWSDDServiceName = name;
    }

    public es.pode.oaiore.negocio.servicio.SrvOaiOre getSrvOaiOre() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrvOaiOre_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrvOaiOre(endpoint);
    }

    public es.pode.oaiore.negocio.servicio.SrvOaiOre getSrvOaiOre(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.pode.oaiore.negocio.servicio.SrvOaiOreSoapBindingStub _stub = new es.pode.oaiore.negocio.servicio.SrvOaiOreSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrvOaiOreWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrvOaiOreEndpointAddress(java.lang.String address) {
        SrvOaiOre_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.pode.oaiore.negocio.servicio.SrvOaiOre.class.isAssignableFrom(serviceEndpointInterface)) {
                es.pode.oaiore.negocio.servicio.SrvOaiOreSoapBindingStub _stub = new es.pode.oaiore.negocio.servicio.SrvOaiOreSoapBindingStub(new java.net.URL(SrvOaiOre_address), this);
                _stub.setPortName(getSrvOaiOreWSDDServiceName());
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
        if ("SrvOaiOre".equals(inputPortName)) {
            return getSrvOaiOre();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicio.negocio.oaiore.pode.es", "SrvOaiOreService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicio.negocio.oaiore.pode.es", "SrvOaiOre"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrvOaiOre".equals(portName)) {
            setSrvOaiOreEndpointAddress(address);
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
