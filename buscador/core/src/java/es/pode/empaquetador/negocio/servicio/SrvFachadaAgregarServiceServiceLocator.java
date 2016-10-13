/**
 * SrvFachadaAgregarServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.empaquetador.negocio.servicio;

public class SrvFachadaAgregarServiceServiceLocator extends org.apache.axis.client.Service implements es.pode.empaquetador.negocio.servicio.SrvFachadaAgregarServiceService {

/**
 * Servicio que ofrece un único metodo para todos los posibles
 *             agregar que se ofrecen en el modulo de empaquetación.
 */

    public SrvFachadaAgregarServiceServiceLocator() {
    }


    public SrvFachadaAgregarServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrvFachadaAgregarServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrvFachadaAgregarService
    private java.lang.String SrvFachadaAgregarService_address = "http://localhost:8080/empaquetadorbasico-F1/services/SrvFachadaAgregarService";

    public java.lang.String getSrvFachadaAgregarServiceAddress() {
        return SrvFachadaAgregarService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SrvFachadaAgregarServiceWSDDServiceName = "SrvFachadaAgregarService";

    public java.lang.String getSrvFachadaAgregarServiceWSDDServiceName() {
        return SrvFachadaAgregarServiceWSDDServiceName;
    }

    public void setSrvFachadaAgregarServiceWSDDServiceName(java.lang.String name) {
        SrvFachadaAgregarServiceWSDDServiceName = name;
    }

    public es.pode.empaquetador.negocio.servicio.SrvFachadaAgregarService getSrvFachadaAgregarService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrvFachadaAgregarService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrvFachadaAgregarService(endpoint);
    }

    public es.pode.empaquetador.negocio.servicio.SrvFachadaAgregarService getSrvFachadaAgregarService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.pode.empaquetador.negocio.servicio.SrvFachadaAgregarServiceSoapBindingStub _stub = new es.pode.empaquetador.negocio.servicio.SrvFachadaAgregarServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrvFachadaAgregarServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrvFachadaAgregarServiceEndpointAddress(java.lang.String address) {
        SrvFachadaAgregarService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.pode.empaquetador.negocio.servicio.SrvFachadaAgregarService.class.isAssignableFrom(serviceEndpointInterface)) {
                es.pode.empaquetador.negocio.servicio.SrvFachadaAgregarServiceSoapBindingStub _stub = new es.pode.empaquetador.negocio.servicio.SrvFachadaAgregarServiceSoapBindingStub(new java.net.URL(SrvFachadaAgregarService_address), this);
                _stub.setPortName(getSrvFachadaAgregarServiceWSDDServiceName());
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
        if ("SrvFachadaAgregarService".equals(inputPortName)) {
            return getSrvFachadaAgregarService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "SrvFachadaAgregarServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "SrvFachadaAgregarService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrvFachadaAgregarService".equals(portName)) {
            setSrvFachadaAgregarServiceEndpointAddress(address);
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
