/**
 * SrvTaxonomiaServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.fuentestaxonomicas.negocio.servicio;

public class SrvTaxonomiaServiceServiceLocator extends org.apache.axis.client.Service implements es.pode.fuentestaxonomicas.negocio.servicio.SrvTaxonomiaServiceService {

/**

 */

    public SrvTaxonomiaServiceServiceLocator() {
    }


    public SrvTaxonomiaServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrvTaxonomiaServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrvTaxonomiaService
    private java.lang.String SrvTaxonomiaService_address = "http://localhost:8080/fuentestaxonomicas-1/services/SrvTaxonomiaService";

    public java.lang.String getSrvTaxonomiaServiceAddress() {
        return SrvTaxonomiaService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SrvTaxonomiaServiceWSDDServiceName = "SrvTaxonomiaService";

    public java.lang.String getSrvTaxonomiaServiceWSDDServiceName() {
        return SrvTaxonomiaServiceWSDDServiceName;
    }

    public void setSrvTaxonomiaServiceWSDDServiceName(java.lang.String name) {
        SrvTaxonomiaServiceWSDDServiceName = name;
    }

    public es.pode.fuentestaxonomicas.negocio.servicio.SrvTaxonomiaService getSrvTaxonomiaService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrvTaxonomiaService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrvTaxonomiaService(endpoint);
    }

    public es.pode.fuentestaxonomicas.negocio.servicio.SrvTaxonomiaService getSrvTaxonomiaService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.pode.fuentestaxonomicas.negocio.servicio.SrvTaxonomiaServiceSoapBindingStub _stub = new es.pode.fuentestaxonomicas.negocio.servicio.SrvTaxonomiaServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrvTaxonomiaServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrvTaxonomiaServiceEndpointAddress(java.lang.String address) {
        SrvTaxonomiaService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.pode.fuentestaxonomicas.negocio.servicio.SrvTaxonomiaService.class.isAssignableFrom(serviceEndpointInterface)) {
                es.pode.fuentestaxonomicas.negocio.servicio.SrvTaxonomiaServiceSoapBindingStub _stub = new es.pode.fuentestaxonomicas.negocio.servicio.SrvTaxonomiaServiceSoapBindingStub(new java.net.URL(SrvTaxonomiaService_address), this);
                _stub.setPortName(getSrvTaxonomiaServiceWSDDServiceName());
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
        if ("SrvTaxonomiaService".equals(inputPortName)) {
            return getSrvTaxonomiaService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "SrvTaxonomiaServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "SrvTaxonomiaService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrvTaxonomiaService".equals(portName)) {
            setSrvTaxonomiaServiceEndpointAddress(address);
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
