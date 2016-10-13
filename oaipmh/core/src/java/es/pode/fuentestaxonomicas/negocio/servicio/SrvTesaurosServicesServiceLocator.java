/**
 * SrvTesaurosServicesServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.fuentestaxonomicas.negocio.servicio;

public class SrvTesaurosServicesServiceLocator extends org.apache.axis.client.Service implements es.pode.fuentestaxonomicas.negocio.servicio.SrvTesaurosServicesService {

/**

 */

    public SrvTesaurosServicesServiceLocator() {
    }


    public SrvTesaurosServicesServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrvTesaurosServicesServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrvTesaurosServices
    private java.lang.String SrvTesaurosServices_address = "http://localhost:8080/fuentestaxonomicas-1/services/SrvTesaurosServices";

    public java.lang.String getSrvTesaurosServicesAddress() {
        return SrvTesaurosServices_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SrvTesaurosServicesWSDDServiceName = "SrvTesaurosServices";

    public java.lang.String getSrvTesaurosServicesWSDDServiceName() {
        return SrvTesaurosServicesWSDDServiceName;
    }

    public void setSrvTesaurosServicesWSDDServiceName(java.lang.String name) {
        SrvTesaurosServicesWSDDServiceName = name;
    }

    public es.pode.fuentestaxonomicas.negocio.servicio.SrvTesaurosServices getSrvTesaurosServices() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrvTesaurosServices_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrvTesaurosServices(endpoint);
    }

    public es.pode.fuentestaxonomicas.negocio.servicio.SrvTesaurosServices getSrvTesaurosServices(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.pode.fuentestaxonomicas.negocio.servicio.SrvTesaurosServicesSoapBindingStub _stub = new es.pode.fuentestaxonomicas.negocio.servicio.SrvTesaurosServicesSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrvTesaurosServicesWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrvTesaurosServicesEndpointAddress(java.lang.String address) {
        SrvTesaurosServices_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.pode.fuentestaxonomicas.negocio.servicio.SrvTesaurosServices.class.isAssignableFrom(serviceEndpointInterface)) {
                es.pode.fuentestaxonomicas.negocio.servicio.SrvTesaurosServicesSoapBindingStub _stub = new es.pode.fuentestaxonomicas.negocio.servicio.SrvTesaurosServicesSoapBindingStub(new java.net.URL(SrvTesaurosServices_address), this);
                _stub.setPortName(getSrvTesaurosServicesWSDDServiceName());
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
        if ("SrvTesaurosServices".equals(inputPortName)) {
            return getSrvTesaurosServices();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "SrvTesaurosServicesService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "SrvTesaurosServices"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrvTesaurosServices".equals(portName)) {
            setSrvTesaurosServicesEndpointAddress(address);
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
