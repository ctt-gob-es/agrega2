/**
 * SrvIdentidadFederadaServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.adminusuarios.negocio.servicios;

public class SrvIdentidadFederadaServiceServiceLocator extends org.apache.axis.client.Service implements es.pode.adminusuarios.negocio.servicios.SrvIdentidadFederadaServiceService {

/**

 */

    public SrvIdentidadFederadaServiceServiceLocator() {
    }


    public SrvIdentidadFederadaServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrvIdentidadFederadaServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrvIdentidadFederadaService
    private java.lang.String SrvIdentidadFederadaService_address = "http://localhost:8080/adminusuarios/services/SrvIdentidadFederadaService";

    public java.lang.String getSrvIdentidadFederadaServiceAddress() {
        return SrvIdentidadFederadaService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SrvIdentidadFederadaServiceWSDDServiceName = "SrvIdentidadFederadaService";

    public java.lang.String getSrvIdentidadFederadaServiceWSDDServiceName() {
        return SrvIdentidadFederadaServiceWSDDServiceName;
    }

    public void setSrvIdentidadFederadaServiceWSDDServiceName(java.lang.String name) {
        SrvIdentidadFederadaServiceWSDDServiceName = name;
    }

    public es.pode.adminusuarios.negocio.servicios.SrvIdentidadFederadaService getSrvIdentidadFederadaService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrvIdentidadFederadaService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrvIdentidadFederadaService(endpoint);
    }

    public es.pode.adminusuarios.negocio.servicios.SrvIdentidadFederadaService getSrvIdentidadFederadaService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.pode.adminusuarios.negocio.servicios.SrvIdentidadFederadaServiceSoapBindingStub _stub = new es.pode.adminusuarios.negocio.servicios.SrvIdentidadFederadaServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrvIdentidadFederadaServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrvIdentidadFederadaServiceEndpointAddress(java.lang.String address) {
        SrvIdentidadFederadaService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.pode.adminusuarios.negocio.servicios.SrvIdentidadFederadaService.class.isAssignableFrom(serviceEndpointInterface)) {
                es.pode.adminusuarios.negocio.servicios.SrvIdentidadFederadaServiceSoapBindingStub _stub = new es.pode.adminusuarios.negocio.servicios.SrvIdentidadFederadaServiceSoapBindingStub(new java.net.URL(SrvIdentidadFederadaService_address), this);
                _stub.setPortName(getSrvIdentidadFederadaServiceWSDDServiceName());
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
        if ("SrvIdentidadFederadaService".equals(inputPortName)) {
            return getSrvIdentidadFederadaService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "SrvIdentidadFederadaServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "SrvIdentidadFederadaService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrvIdentidadFederadaService".equals(portName)) {
            setSrvIdentidadFederadaServiceEndpointAddress(address);
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
