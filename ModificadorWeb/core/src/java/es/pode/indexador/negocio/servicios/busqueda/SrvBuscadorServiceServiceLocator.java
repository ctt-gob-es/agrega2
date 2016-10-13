/**
 * SrvBuscadorServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.indexador.negocio.servicios.busqueda;

public class SrvBuscadorServiceServiceLocator extends org.apache.axis.client.Service implements es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorServiceService {

/**
 * Este servicio implementa la funcionalidad de busqueda de odes
 *             sobre los odes indexados.
 */

    public SrvBuscadorServiceServiceLocator() {
    }


    public SrvBuscadorServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrvBuscadorServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrvBuscadorService
    private java.lang.String SrvBuscadorService_address = "http://localhost:8080/indexador-0.1/services/SrvBuscadorService";

    public java.lang.String getSrvBuscadorServiceAddress() {
        return SrvBuscadorService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SrvBuscadorServiceWSDDServiceName = "SrvBuscadorService";

    public java.lang.String getSrvBuscadorServiceWSDDServiceName() {
        return SrvBuscadorServiceWSDDServiceName;
    }

    public void setSrvBuscadorServiceWSDDServiceName(java.lang.String name) {
        SrvBuscadorServiceWSDDServiceName = name;
    }

    public es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorService getSrvBuscadorService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrvBuscadorService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrvBuscadorService(endpoint);
    }

    public es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorService getSrvBuscadorService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorServiceSoapBindingStub _stub = new es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrvBuscadorServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrvBuscadorServiceEndpointAddress(java.lang.String address) {
        SrvBuscadorService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorService.class.isAssignableFrom(serviceEndpointInterface)) {
                es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorServiceSoapBindingStub _stub = new es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorServiceSoapBindingStub(new java.net.URL(SrvBuscadorService_address), this);
                _stub.setPortName(getSrvBuscadorServiceWSDDServiceName());
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
        if ("SrvBuscadorService".equals(inputPortName)) {
            return getSrvBuscadorService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "SrvBuscadorServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "SrvBuscadorService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrvBuscadorService".equals(portName)) {
            setSrvBuscadorServiceEndpointAddress(address);
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
