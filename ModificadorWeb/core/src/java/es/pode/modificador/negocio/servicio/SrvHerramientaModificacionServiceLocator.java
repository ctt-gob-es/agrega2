/**
 * SrvHerramientaModificacionServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.modificador.negocio.servicio;

public class SrvHerramientaModificacionServiceLocator extends org.apache.axis.client.Service implements es.pode.modificador.negocio.servicio.SrvHerramientaModificacionService {

/**

 */

    public SrvHerramientaModificacionServiceLocator() {
    }


    public SrvHerramientaModificacionServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrvHerramientaModificacionServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrvHerramientaModificacion
    private java.lang.String SrvHerramientaModificacion_address = "http://localhost:8080/modificador-1/services/SrvHerramientaModificacion";

    public java.lang.String getSrvHerramientaModificacionAddress() {
        return SrvHerramientaModificacion_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SrvHerramientaModificacionWSDDServiceName = "SrvHerramientaModificacion";

    public java.lang.String getSrvHerramientaModificacionWSDDServiceName() {
        return SrvHerramientaModificacionWSDDServiceName;
    }

    public void setSrvHerramientaModificacionWSDDServiceName(java.lang.String name) {
        SrvHerramientaModificacionWSDDServiceName = name;
    }

    public es.pode.modificador.negocio.servicio.SrvHerramientaModificacion getSrvHerramientaModificacion() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrvHerramientaModificacion_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrvHerramientaModificacion(endpoint);
    }

    public es.pode.modificador.negocio.servicio.SrvHerramientaModificacion getSrvHerramientaModificacion(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.pode.modificador.negocio.servicio.SrvHerramientaModificacionSoapBindingStub _stub = new es.pode.modificador.negocio.servicio.SrvHerramientaModificacionSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrvHerramientaModificacionWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrvHerramientaModificacionEndpointAddress(java.lang.String address) {
        SrvHerramientaModificacion_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.pode.modificador.negocio.servicio.SrvHerramientaModificacion.class.isAssignableFrom(serviceEndpointInterface)) {
                es.pode.modificador.negocio.servicio.SrvHerramientaModificacionSoapBindingStub _stub = new es.pode.modificador.negocio.servicio.SrvHerramientaModificacionSoapBindingStub(new java.net.URL(SrvHerramientaModificacion_address), this);
                _stub.setPortName(getSrvHerramientaModificacionWSDDServiceName());
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
        if ("SrvHerramientaModificacion".equals(inputPortName)) {
            return getSrvHerramientaModificacion();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "SrvHerramientaModificacionService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "SrvHerramientaModificacion"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrvHerramientaModificacion".equals(portName)) {
            setSrvHerramientaModificacionEndpointAddress(address);
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
