/**
 * SrvEstructurasEducativasServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.fuentestaxonomicas.negocio.servicio;

public class SrvEstructurasEducativasServiceServiceLocator extends org.apache.axis.client.Service implements es.pode.fuentestaxonomicas.negocio.servicio.SrvEstructurasEducativasServiceService {

/**
 * Servicio para la administracion de distintas Estructuras
 *             Educativas. Permite administrar ficheros xml que describen
 * diferentes estructuras educativas como:
 *             -Tesauros
 *             -Taxonomias
 *             -Arbol Curricular
 */

    public SrvEstructurasEducativasServiceServiceLocator() {
    }


    public SrvEstructurasEducativasServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrvEstructurasEducativasServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrvEstructurasEducativasService
    private java.lang.String SrvEstructurasEducativasService_address = "http://localhost:8080/fuentestaxonomicas-1/services/SrvEstructurasEducativasService";

    public java.lang.String getSrvEstructurasEducativasServiceAddress() {
        return SrvEstructurasEducativasService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SrvEstructurasEducativasServiceWSDDServiceName = "SrvEstructurasEducativasService";

    public java.lang.String getSrvEstructurasEducativasServiceWSDDServiceName() {
        return SrvEstructurasEducativasServiceWSDDServiceName;
    }

    public void setSrvEstructurasEducativasServiceWSDDServiceName(java.lang.String name) {
        SrvEstructurasEducativasServiceWSDDServiceName = name;
    }

    public es.pode.fuentestaxonomicas.negocio.servicio.SrvEstructurasEducativasService getSrvEstructurasEducativasService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrvEstructurasEducativasService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrvEstructurasEducativasService(endpoint);
    }

    public es.pode.fuentestaxonomicas.negocio.servicio.SrvEstructurasEducativasService getSrvEstructurasEducativasService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.pode.fuentestaxonomicas.negocio.servicio.SrvEstructurasEducativasServiceSoapBindingStub _stub = new es.pode.fuentestaxonomicas.negocio.servicio.SrvEstructurasEducativasServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrvEstructurasEducativasServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrvEstructurasEducativasServiceEndpointAddress(java.lang.String address) {
        SrvEstructurasEducativasService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.pode.fuentestaxonomicas.negocio.servicio.SrvEstructurasEducativasService.class.isAssignableFrom(serviceEndpointInterface)) {
                es.pode.fuentestaxonomicas.negocio.servicio.SrvEstructurasEducativasServiceSoapBindingStub _stub = new es.pode.fuentestaxonomicas.negocio.servicio.SrvEstructurasEducativasServiceSoapBindingStub(new java.net.URL(SrvEstructurasEducativasService_address), this);
                _stub.setPortName(getSrvEstructurasEducativasServiceWSDDServiceName());
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
        if ("SrvEstructurasEducativasService".equals(inputPortName)) {
            return getSrvEstructurasEducativasService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "SrvEstructurasEducativasServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "SrvEstructurasEducativasService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrvEstructurasEducativasService".equals(portName)) {
            setSrvEstructurasEducativasServiceEndpointAddress(address);
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
