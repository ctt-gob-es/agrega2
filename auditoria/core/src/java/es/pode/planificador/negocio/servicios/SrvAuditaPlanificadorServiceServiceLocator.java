/**
 * SrvAuditaPlanificadorServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.planificador.negocio.servicios;

public class SrvAuditaPlanificadorServiceServiceLocator extends org.apache.axis.client.Service implements es.pode.planificador.negocio.servicios.SrvAuditaPlanificadorServiceService {

/**
 * Servicio que sirve la información al servicio de Auditoria
 */

    public SrvAuditaPlanificadorServiceServiceLocator() {
    }


    public SrvAuditaPlanificadorServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrvAuditaPlanificadorServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrvAuditaPlanificadorService
    private java.lang.String SrvAuditaPlanificadorService_address = "http://localhost:8080/planificador-1.0/services/SrvAuditaPlanificadorService";

    public java.lang.String getSrvAuditaPlanificadorServiceAddress() {
        return SrvAuditaPlanificadorService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SrvAuditaPlanificadorServiceWSDDServiceName = "SrvAuditaPlanificadorService";

    public java.lang.String getSrvAuditaPlanificadorServiceWSDDServiceName() {
        return SrvAuditaPlanificadorServiceWSDDServiceName;
    }

    public void setSrvAuditaPlanificadorServiceWSDDServiceName(java.lang.String name) {
        SrvAuditaPlanificadorServiceWSDDServiceName = name;
    }

    public es.pode.planificador.negocio.servicios.SrvAuditaPlanificadorService getSrvAuditaPlanificadorService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrvAuditaPlanificadorService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrvAuditaPlanificadorService(endpoint);
    }

    public es.pode.planificador.negocio.servicios.SrvAuditaPlanificadorService getSrvAuditaPlanificadorService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.pode.planificador.negocio.servicios.SrvAuditaPlanificadorServiceSoapBindingStub _stub = new es.pode.planificador.negocio.servicios.SrvAuditaPlanificadorServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrvAuditaPlanificadorServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrvAuditaPlanificadorServiceEndpointAddress(java.lang.String address) {
        SrvAuditaPlanificadorService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.pode.planificador.negocio.servicios.SrvAuditaPlanificadorService.class.isAssignableFrom(serviceEndpointInterface)) {
                es.pode.planificador.negocio.servicios.SrvAuditaPlanificadorServiceSoapBindingStub _stub = new es.pode.planificador.negocio.servicios.SrvAuditaPlanificadorServiceSoapBindingStub(new java.net.URL(SrvAuditaPlanificadorService_address), this);
                _stub.setPortName(getSrvAuditaPlanificadorServiceWSDDServiceName());
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
        if ("SrvAuditaPlanificadorService".equals(inputPortName)) {
            return getSrvAuditaPlanificadorService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "SrvAuditaPlanificadorServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "SrvAuditaPlanificadorService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrvAuditaPlanificadorService".equals(portName)) {
            setSrvAuditaPlanificadorServiceEndpointAddress(address);
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
