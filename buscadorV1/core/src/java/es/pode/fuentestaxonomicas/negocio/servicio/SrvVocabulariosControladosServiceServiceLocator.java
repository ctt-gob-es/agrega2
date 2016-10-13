/**
 * SrvVocabulariosControladosServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.fuentestaxonomicas.negocio.servicio;

public class SrvVocabulariosControladosServiceServiceLocator extends org.apache.axis.client.Service implements es.pode.fuentestaxonomicas.negocio.servicio.SrvVocabulariosControladosServiceService {

/**
 * El servicio de vocabularios controlados es el encargado de
 *             gestionar los terminos restringidos por LOM-ES, realizando
 * las
 *             traducciones oportunas para conseguir la validez de los
 * esquemas, asi como para mostrar los vocabularios en el idioma
 *             indicado por el usuario.
 */

    public SrvVocabulariosControladosServiceServiceLocator() {
    }


    public SrvVocabulariosControladosServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrvVocabulariosControladosServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrvVocabulariosControladosService
    private java.lang.String SrvVocabulariosControladosService_address = "http://localhost:8080/fuentestaxonomicas-1/services/SrvVocabulariosControladosService";

    public java.lang.String getSrvVocabulariosControladosServiceAddress() {
        return SrvVocabulariosControladosService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SrvVocabulariosControladosServiceWSDDServiceName = "SrvVocabulariosControladosService";

    public java.lang.String getSrvVocabulariosControladosServiceWSDDServiceName() {
        return SrvVocabulariosControladosServiceWSDDServiceName;
    }

    public void setSrvVocabulariosControladosServiceWSDDServiceName(java.lang.String name) {
        SrvVocabulariosControladosServiceWSDDServiceName = name;
    }

    public es.pode.fuentestaxonomicas.negocio.servicio.SrvVocabulariosControladosService getSrvVocabulariosControladosService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrvVocabulariosControladosService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrvVocabulariosControladosService(endpoint);
    }

    public es.pode.fuentestaxonomicas.negocio.servicio.SrvVocabulariosControladosService getSrvVocabulariosControladosService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.pode.fuentestaxonomicas.negocio.servicio.SrvVocabulariosControladosServiceSoapBindingStub _stub = new es.pode.fuentestaxonomicas.negocio.servicio.SrvVocabulariosControladosServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrvVocabulariosControladosServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrvVocabulariosControladosServiceEndpointAddress(java.lang.String address) {
        SrvVocabulariosControladosService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.pode.fuentestaxonomicas.negocio.servicio.SrvVocabulariosControladosService.class.isAssignableFrom(serviceEndpointInterface)) {
                es.pode.fuentestaxonomicas.negocio.servicio.SrvVocabulariosControladosServiceSoapBindingStub _stub = new es.pode.fuentestaxonomicas.negocio.servicio.SrvVocabulariosControladosServiceSoapBindingStub(new java.net.URL(SrvVocabulariosControladosService_address), this);
                _stub.setPortName(getSrvVocabulariosControladosServiceWSDDServiceName());
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
        if ("SrvVocabulariosControladosService".equals(inputPortName)) {
            return getSrvVocabulariosControladosService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "SrvVocabulariosControladosServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "SrvVocabulariosControladosService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrvVocabulariosControladosService".equals(portName)) {
            setSrvVocabulariosControladosServiceEndpointAddress(address);
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
