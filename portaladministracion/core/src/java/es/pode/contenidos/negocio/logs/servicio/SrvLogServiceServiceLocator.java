/**
 * SrvLogServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.contenidos.negocio.logs.servicio;

public class SrvLogServiceServiceLocator extends org.apache.axis.client.Service implements es.pode.contenidos.negocio.logs.servicio.SrvLogServiceService {

/**

 */

    public SrvLogServiceServiceLocator() {
    }


    public SrvLogServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrvLogServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrvLogService
    private java.lang.String SrvLogService_address = "http://localhost:8080/contenidosportal-F1/services/SrvLogService";

    public java.lang.String getSrvLogServiceAddress() {
        return SrvLogService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SrvLogServiceWSDDServiceName = "SrvLogService";

    public java.lang.String getSrvLogServiceWSDDServiceName() {
        return SrvLogServiceWSDDServiceName;
    }

    public void setSrvLogServiceWSDDServiceName(java.lang.String name) {
        SrvLogServiceWSDDServiceName = name;
    }

    public es.pode.contenidos.negocio.logs.servicio.SrvLogService getSrvLogService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrvLogService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrvLogService(endpoint);
    }

    public es.pode.contenidos.negocio.logs.servicio.SrvLogService getSrvLogService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.pode.contenidos.negocio.logs.servicio.SrvLogServiceSoapBindingStub _stub = new es.pode.contenidos.negocio.logs.servicio.SrvLogServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrvLogServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrvLogServiceEndpointAddress(java.lang.String address) {
        SrvLogService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.pode.contenidos.negocio.logs.servicio.SrvLogService.class.isAssignableFrom(serviceEndpointInterface)) {
                es.pode.contenidos.negocio.logs.servicio.SrvLogServiceSoapBindingStub _stub = new es.pode.contenidos.negocio.logs.servicio.SrvLogServiceSoapBindingStub(new java.net.URL(SrvLogService_address), this);
                _stub.setPortName(getSrvLogServiceWSDDServiceName());
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
        if ("SrvLogService".equals(inputPortName)) {
            return getSrvLogService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicio.logs.negocio.contenidos.pode.es", "SrvLogServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicio.logs.negocio.contenidos.pode.es", "SrvLogService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrvLogService".equals(portName)) {
            setSrvLogServiceEndpointAddress(address);
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
