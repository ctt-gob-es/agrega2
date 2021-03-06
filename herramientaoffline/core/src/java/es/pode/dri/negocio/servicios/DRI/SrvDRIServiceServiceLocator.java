/**
 * SrvDRIServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.dri.negocio.servicios.DRI;

public class SrvDRIServiceServiceLocator extends org.apache.axis.client.Service implements es.pode.dri.negocio.servicios.DRI.SrvDRIServiceService {

/**

 */

    public SrvDRIServiceServiceLocator() {
    }


    public SrvDRIServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrvDRIServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrvDRIService
    private java.lang.String SrvDRIService_address = "http://localhost:8080/dri-1/services/SrvDRIService";

    public java.lang.String getSrvDRIServiceAddress() {
        return SrvDRIService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SrvDRIServiceWSDDServiceName = "SrvDRIService";

    public java.lang.String getSrvDRIServiceWSDDServiceName() {
        return SrvDRIServiceWSDDServiceName;
    }

    public void setSrvDRIServiceWSDDServiceName(java.lang.String name) {
        SrvDRIServiceWSDDServiceName = name;
    }

    public es.pode.dri.negocio.servicios.DRI.SrvDRIService getSrvDRIService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrvDRIService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrvDRIService(endpoint);
    }

    public es.pode.dri.negocio.servicios.DRI.SrvDRIService getSrvDRIService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.pode.dri.negocio.servicios.DRI.SrvDRIServiceSoapBindingStub _stub = new es.pode.dri.negocio.servicios.DRI.SrvDRIServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrvDRIServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrvDRIServiceEndpointAddress(java.lang.String address) {
        SrvDRIService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.pode.dri.negocio.servicios.DRI.SrvDRIService.class.isAssignableFrom(serviceEndpointInterface)) {
                es.pode.dri.negocio.servicios.DRI.SrvDRIServiceSoapBindingStub _stub = new es.pode.dri.negocio.servicios.DRI.SrvDRIServiceSoapBindingStub(new java.net.URL(SrvDRIService_address), this);
                _stub.setPortName(getSrvDRIServiceWSDDServiceName());
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
        if ("SrvDRIService".equals(inputPortName)) {
            return getSrvDRIService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://DRI.servicios.negocio.dri.pode.es", "SrvDRIServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://DRI.servicios.negocio.dri.pode.es", "SrvDRIService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrvDRIService".equals(portName)) {
            setSrvDRIServiceEndpointAddress(address);
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
