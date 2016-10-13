/**
 * SrvEmpaquetadorBasicoServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.empaquetador.negocio.servicio;

public class SrvEmpaquetadorBasicoServiceServiceLocator extends org.apache.axis.client.Service implements es.pode.empaquetador.negocio.servicio.SrvEmpaquetadorBasicoServiceService {

/**

 */

    public SrvEmpaquetadorBasicoServiceServiceLocator() {
    }


    public SrvEmpaquetadorBasicoServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrvEmpaquetadorBasicoServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrvEmpaquetadorBasicoService
    private java.lang.String SrvEmpaquetadorBasicoService_address = "http://localhost:8080/empaquetadorbasico-F1/services/SrvEmpaquetadorBasicoService";

    public java.lang.String getSrvEmpaquetadorBasicoServiceAddress() {
        return SrvEmpaquetadorBasicoService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SrvEmpaquetadorBasicoServiceWSDDServiceName = "SrvEmpaquetadorBasicoService";

    public java.lang.String getSrvEmpaquetadorBasicoServiceWSDDServiceName() {
        return SrvEmpaquetadorBasicoServiceWSDDServiceName;
    }

    public void setSrvEmpaquetadorBasicoServiceWSDDServiceName(java.lang.String name) {
        SrvEmpaquetadorBasicoServiceWSDDServiceName = name;
    }

    public es.pode.empaquetador.negocio.servicio.SrvEmpaquetadorBasicoService getSrvEmpaquetadorBasicoService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrvEmpaquetadorBasicoService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrvEmpaquetadorBasicoService(endpoint);
    }

    public es.pode.empaquetador.negocio.servicio.SrvEmpaquetadorBasicoService getSrvEmpaquetadorBasicoService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.pode.empaquetador.negocio.servicio.SrvEmpaquetadorBasicoServiceSoapBindingStub _stub = new es.pode.empaquetador.negocio.servicio.SrvEmpaquetadorBasicoServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrvEmpaquetadorBasicoServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrvEmpaquetadorBasicoServiceEndpointAddress(java.lang.String address) {
        SrvEmpaquetadorBasicoService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.pode.empaquetador.negocio.servicio.SrvEmpaquetadorBasicoService.class.isAssignableFrom(serviceEndpointInterface)) {
                es.pode.empaquetador.negocio.servicio.SrvEmpaquetadorBasicoServiceSoapBindingStub _stub = new es.pode.empaquetador.negocio.servicio.SrvEmpaquetadorBasicoServiceSoapBindingStub(new java.net.URL(SrvEmpaquetadorBasicoService_address), this);
                _stub.setPortName(getSrvEmpaquetadorBasicoServiceWSDDServiceName());
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
        if ("SrvEmpaquetadorBasicoService".equals(inputPortName)) {
            return getSrvEmpaquetadorBasicoService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "SrvEmpaquetadorBasicoServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "SrvEmpaquetadorBasicoService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrvEmpaquetadorBasicoService".equals(portName)) {
            setSrvEmpaquetadorBasicoServiceEndpointAddress(address);
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
