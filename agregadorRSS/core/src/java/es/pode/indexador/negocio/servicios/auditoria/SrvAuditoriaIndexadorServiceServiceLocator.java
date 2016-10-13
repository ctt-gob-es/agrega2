/**
 * SrvAuditoriaIndexadorServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.indexador.negocio.servicios.auditoria;

public class SrvAuditoriaIndexadorServiceServiceLocator extends org.apache.axis.client.Service implements es.pode.indexador.negocio.servicios.auditoria.SrvAuditoriaIndexadorServiceService {

/**
 * Esta clase implementa el servicio que da soporte a todos los
 *             metodos de auditoria del modulo de indexacion.
 */

    public SrvAuditoriaIndexadorServiceServiceLocator() {
    }


    public SrvAuditoriaIndexadorServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrvAuditoriaIndexadorServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrvAuditoriaIndexadorService
    private java.lang.String SrvAuditoriaIndexadorService_address = "http://localhost:8080/indexador-0.1/services/SrvAuditoriaIndexadorService";

    public java.lang.String getSrvAuditoriaIndexadorServiceAddress() {
        return SrvAuditoriaIndexadorService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SrvAuditoriaIndexadorServiceWSDDServiceName = "SrvAuditoriaIndexadorService";

    public java.lang.String getSrvAuditoriaIndexadorServiceWSDDServiceName() {
        return SrvAuditoriaIndexadorServiceWSDDServiceName;
    }

    public void setSrvAuditoriaIndexadorServiceWSDDServiceName(java.lang.String name) {
        SrvAuditoriaIndexadorServiceWSDDServiceName = name;
    }

    public es.pode.indexador.negocio.servicios.auditoria.SrvAuditoriaIndexadorService getSrvAuditoriaIndexadorService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrvAuditoriaIndexadorService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrvAuditoriaIndexadorService(endpoint);
    }

    public es.pode.indexador.negocio.servicios.auditoria.SrvAuditoriaIndexadorService getSrvAuditoriaIndexadorService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.pode.indexador.negocio.servicios.auditoria.SrvAuditoriaIndexadorServiceSoapBindingStub _stub = new es.pode.indexador.negocio.servicios.auditoria.SrvAuditoriaIndexadorServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrvAuditoriaIndexadorServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrvAuditoriaIndexadorServiceEndpointAddress(java.lang.String address) {
        SrvAuditoriaIndexadorService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.pode.indexador.negocio.servicios.auditoria.SrvAuditoriaIndexadorService.class.isAssignableFrom(serviceEndpointInterface)) {
                es.pode.indexador.negocio.servicios.auditoria.SrvAuditoriaIndexadorServiceSoapBindingStub _stub = new es.pode.indexador.negocio.servicios.auditoria.SrvAuditoriaIndexadorServiceSoapBindingStub(new java.net.URL(SrvAuditoriaIndexadorService_address), this);
                _stub.setPortName(getSrvAuditoriaIndexadorServiceWSDDServiceName());
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
        if ("SrvAuditoriaIndexadorService".equals(inputPortName)) {
            return getSrvAuditoriaIndexadorService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://auditoria.servicios.negocio.indexador.pode.es", "SrvAuditoriaIndexadorServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://auditoria.servicios.negocio.indexador.pode.es", "SrvAuditoriaIndexadorService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrvAuditoriaIndexadorService".equals(portName)) {
            setSrvAuditoriaIndexadorServiceEndpointAddress(address);
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
