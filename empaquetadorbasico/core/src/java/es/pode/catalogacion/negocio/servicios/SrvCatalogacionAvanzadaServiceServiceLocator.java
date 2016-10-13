/**
 * SrvCatalogacionAvanzadaServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.catalogacion.negocio.servicios;

public class SrvCatalogacionAvanzadaServiceServiceLocator extends org.apache.axis.client.Service implements es.pode.catalogacion.negocio.servicios.SrvCatalogacionAvanzadaServiceService {

/**

 */

    public SrvCatalogacionAvanzadaServiceServiceLocator() {
    }


    public SrvCatalogacionAvanzadaServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrvCatalogacionAvanzadaServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrvCatalogacionAvanzadaService
    private java.lang.String SrvCatalogacionAvanzadaService_address = "http://localhost:8080/catalogacion-1/services/SrvCatalogacionAvanzadaService";

    public java.lang.String getSrvCatalogacionAvanzadaServiceAddress() {
        return SrvCatalogacionAvanzadaService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SrvCatalogacionAvanzadaServiceWSDDServiceName = "SrvCatalogacionAvanzadaService";

    public java.lang.String getSrvCatalogacionAvanzadaServiceWSDDServiceName() {
        return SrvCatalogacionAvanzadaServiceWSDDServiceName;
    }

    public void setSrvCatalogacionAvanzadaServiceWSDDServiceName(java.lang.String name) {
        SrvCatalogacionAvanzadaServiceWSDDServiceName = name;
    }

    public es.pode.catalogacion.negocio.servicios.SrvCatalogacionAvanzadaService getSrvCatalogacionAvanzadaService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrvCatalogacionAvanzadaService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrvCatalogacionAvanzadaService(endpoint);
    }

    public es.pode.catalogacion.negocio.servicios.SrvCatalogacionAvanzadaService getSrvCatalogacionAvanzadaService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.pode.catalogacion.negocio.servicios.SrvCatalogacionAvanzadaServiceSoapBindingStub _stub = new es.pode.catalogacion.negocio.servicios.SrvCatalogacionAvanzadaServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrvCatalogacionAvanzadaServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrvCatalogacionAvanzadaServiceEndpointAddress(java.lang.String address) {
        SrvCatalogacionAvanzadaService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.pode.catalogacion.negocio.servicios.SrvCatalogacionAvanzadaService.class.isAssignableFrom(serviceEndpointInterface)) {
                es.pode.catalogacion.negocio.servicios.SrvCatalogacionAvanzadaServiceSoapBindingStub _stub = new es.pode.catalogacion.negocio.servicios.SrvCatalogacionAvanzadaServiceSoapBindingStub(new java.net.URL(SrvCatalogacionAvanzadaService_address), this);
                _stub.setPortName(getSrvCatalogacionAvanzadaServiceWSDDServiceName());
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
        if ("SrvCatalogacionAvanzadaService".equals(inputPortName)) {
            return getSrvCatalogacionAvanzadaService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "SrvCatalogacionAvanzadaServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "SrvCatalogacionAvanzadaService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrvCatalogacionAvanzadaService".equals(portName)) {
            setSrvCatalogacionAvanzadaServiceEndpointAddress(address);
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
