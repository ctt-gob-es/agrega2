/**
 * SrvDescargasServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.contenidos.negocio.descargas.servicio;

public class SrvDescargasServiceLocator extends org.apache.axis.client.Service implements es.pode.contenidos.negocio.descargas.servicio.SrvDescargasService {

/**

 */

    public SrvDescargasServiceLocator() {
    }


    public SrvDescargasServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrvDescargasServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrvDescargas
    private java.lang.String SrvDescargas_address = "http://localhost:8080/contenidosportal-F1/services/SrvDescargas";

    public java.lang.String getSrvDescargasAddress() {
        return SrvDescargas_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SrvDescargasWSDDServiceName = "SrvDescargas";

    public java.lang.String getSrvDescargasWSDDServiceName() {
        return SrvDescargasWSDDServiceName;
    }

    public void setSrvDescargasWSDDServiceName(java.lang.String name) {
        SrvDescargasWSDDServiceName = name;
    }

    public es.pode.contenidos.negocio.descargas.servicio.SrvDescargas getSrvDescargas() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrvDescargas_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrvDescargas(endpoint);
    }

    public es.pode.contenidos.negocio.descargas.servicio.SrvDescargas getSrvDescargas(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.pode.contenidos.negocio.descargas.servicio.SrvDescargasSoapBindingStub _stub = new es.pode.contenidos.negocio.descargas.servicio.SrvDescargasSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrvDescargasWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrvDescargasEndpointAddress(java.lang.String address) {
        SrvDescargas_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.pode.contenidos.negocio.descargas.servicio.SrvDescargas.class.isAssignableFrom(serviceEndpointInterface)) {
                es.pode.contenidos.negocio.descargas.servicio.SrvDescargasSoapBindingStub _stub = new es.pode.contenidos.negocio.descargas.servicio.SrvDescargasSoapBindingStub(new java.net.URL(SrvDescargas_address), this);
                _stub.setPortName(getSrvDescargasWSDDServiceName());
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
        if ("SrvDescargas".equals(inputPortName)) {
            return getSrvDescargas();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicio.descargas.negocio.contenidos.pode.es", "SrvDescargasService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicio.descargas.negocio.contenidos.pode.es", "SrvDescargas"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrvDescargas".equals(portName)) {
            setSrvDescargasEndpointAddress(address);
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
