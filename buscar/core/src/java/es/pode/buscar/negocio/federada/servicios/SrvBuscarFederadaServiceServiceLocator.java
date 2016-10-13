/**
 * SrvBuscarFederadaServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.federada.servicios;


public class SrvBuscarFederadaServiceServiceLocator extends org.apache.axis.client.Service implements es.pode.buscar.negocio.federada.servicios.SrvBuscarFederadaServiceService {

/**

 */

    public SrvBuscarFederadaServiceServiceLocator() {
    }


    public SrvBuscarFederadaServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrvBuscarFederadaServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrvBuscarFederadaService
    private java.lang.String SrvBuscarFederadaService_address = "";

    public java.lang.String getSrvBuscarFederadaServiceAddress() {
        return SrvBuscarFederadaService_address;
    }

    public void setSrvBuscarFederadaServiceAddress(String srvBuscarFederadaService_address) {
        this.SrvBuscarFederadaService_address = srvBuscarFederadaService_address;
    }
    
    // The WSDD service name defaults to the port name.
    private java.lang.String SrvBuscarFederadaServiceWSDDServiceName = "SrvBuscarFederadaService";

    public java.lang.String getSrvBuscarFederadaServiceWSDDServiceName() {
        return SrvBuscarFederadaServiceWSDDServiceName;
    }

    public void setSrvBuscarFederadaServiceWSDDServiceName(java.lang.String name) {
        SrvBuscarFederadaServiceWSDDServiceName = name;
    }

    public es.pode.buscar.negocio.federada.servicios.SrvBuscarFederadaImportService getSrvBuscarFederadaService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrvBuscarFederadaService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrvBuscarFederadaService(endpoint);
    }

    public es.pode.buscar.negocio.federada.servicios.SrvBuscarFederadaImportService getSrvBuscarFederadaService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.pode.buscar.negocio.federada.servicios.SrvBuscarFederadaServiceSoapBindingStub _stub = new es.pode.buscar.negocio.federada.servicios.SrvBuscarFederadaServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrvBuscarFederadaServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrvBuscarFederadaServiceEndpointAddress(java.lang.String address) {
        SrvBuscarFederadaService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {   
            if (es.pode.buscar.negocio.federada.servicios.SrvBuscarFederadaService.class.isAssignableFrom(serviceEndpointInterface)) {
                es.pode.buscar.negocio.federada.servicios.SrvBuscarFederadaServiceSoapBindingStub _stub = new es.pode.buscar.negocio.federada.servicios.SrvBuscarFederadaServiceSoapBindingStub(new java.net.URL(SrvBuscarFederadaService_address), this);
                _stub.setPortName(getSrvBuscarFederadaServiceWSDDServiceName());
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
        if ("SrvBuscarFederadaService".equals(inputPortName)) {
            return getSrvBuscarFederadaService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicios.federada.negocio.buscar.pode.es", "SrvBuscarFederadaServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicios.federada.negocio.buscar.pode.es", "SrvBuscarFederadaService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
     * @param portName 
     * @param address 
     * @throws javax.xml.rpc.ServiceException 
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrvBuscarFederadaService".equals(portName)) {
            setSrvBuscarFederadaServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
     * @param portName 
     * @param address 
     * @throws javax.xml.rpc.ServiceException 
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
