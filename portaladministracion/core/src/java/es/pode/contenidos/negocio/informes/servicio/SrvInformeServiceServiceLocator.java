/**
 * SrvInformeServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.contenidos.negocio.informes.servicio;

public class SrvInformeServiceServiceLocator extends org.apache.axis.client.Service implements es.pode.contenidos.negocio.informes.servicio.SrvInformeServiceService {

/**

 */

    public SrvInformeServiceServiceLocator() {
    }


    public SrvInformeServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrvInformeServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrvInformeService
    private java.lang.String SrvInformeService_address = "http://localhost:8080/contenidosportal-F1/services/SrvInformeService";

    public java.lang.String getSrvInformeServiceAddress() {
        return SrvInformeService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SrvInformeServiceWSDDServiceName = "SrvInformeService";

    public java.lang.String getSrvInformeServiceWSDDServiceName() {
        return SrvInformeServiceWSDDServiceName;
    }

    public void setSrvInformeServiceWSDDServiceName(java.lang.String name) {
        SrvInformeServiceWSDDServiceName = name;
    }

    public es.pode.contenidos.negocio.informes.servicio.SrvInformeService getSrvInformeService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrvInformeService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrvInformeService(endpoint);
    }

    public es.pode.contenidos.negocio.informes.servicio.SrvInformeService getSrvInformeService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.pode.contenidos.negocio.informes.servicio.SrvInformeServiceSoapBindingStub _stub = new es.pode.contenidos.negocio.informes.servicio.SrvInformeServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrvInformeServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrvInformeServiceEndpointAddress(java.lang.String address) {
        SrvInformeService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.pode.contenidos.negocio.informes.servicio.SrvInformeService.class.isAssignableFrom(serviceEndpointInterface)) {
                es.pode.contenidos.negocio.informes.servicio.SrvInformeServiceSoapBindingStub _stub = new es.pode.contenidos.negocio.informes.servicio.SrvInformeServiceSoapBindingStub(new java.net.URL(SrvInformeService_address), this);
                _stub.setPortName(getSrvInformeServiceWSDDServiceName());
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
        if ("SrvInformeService".equals(inputPortName)) {
            return getSrvInformeService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicio.informes.negocio.contenidos.pode.es", "SrvInformeServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicio.informes.negocio.contenidos.pode.es", "SrvInformeService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrvInformeService".equals(portName)) {
            setSrvInformeServiceEndpointAddress(address);
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
