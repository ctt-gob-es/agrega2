/**
 * SrvNodoServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.administrar.servicio;

public class SrvNodoServiceServiceLocator extends org.apache.axis.client.Service implements es.pode.buscar.negocio.administrar.servicio.SrvNodoServiceService {

/**

 */

    public SrvNodoServiceServiceLocator() {
    }


    public SrvNodoServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrvNodoServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrvNodoService
    private java.lang.String SrvNodoService_address = "http://localhost:8080/buscar-1/services/SrvNodoService";

    public java.lang.String getSrvNodoServiceAddress() {
        return SrvNodoService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SrvNodoServiceWSDDServiceName = "SrvNodoService";

    public java.lang.String getSrvNodoServiceWSDDServiceName() {
        return SrvNodoServiceWSDDServiceName;
    }

    public void setSrvNodoServiceWSDDServiceName(java.lang.String name) {
        SrvNodoServiceWSDDServiceName = name;
    }

    public es.pode.buscar.negocio.administrar.servicio.SrvNodoService getSrvNodoService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrvNodoService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrvNodoService(endpoint);
    }

    public es.pode.buscar.negocio.administrar.servicio.SrvNodoService getSrvNodoService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.pode.buscar.negocio.administrar.servicio.SrvNodoServiceSoapBindingStub _stub = new es.pode.buscar.negocio.administrar.servicio.SrvNodoServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrvNodoServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrvNodoServiceEndpointAddress(java.lang.String address) {
        SrvNodoService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.pode.buscar.negocio.administrar.servicio.SrvNodoService.class.isAssignableFrom(serviceEndpointInterface)) {
                es.pode.buscar.negocio.administrar.servicio.SrvNodoServiceSoapBindingStub _stub = new es.pode.buscar.negocio.administrar.servicio.SrvNodoServiceSoapBindingStub(new java.net.URL(SrvNodoService_address), this);
                _stub.setPortName(getSrvNodoServiceWSDDServiceName());
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
        if ("SrvNodoService".equals(inputPortName)) {
            return getSrvNodoService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "SrvNodoServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "SrvNodoService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrvNodoService".equals(portName)) {
            setSrvNodoServiceEndpointAddress(address);
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
