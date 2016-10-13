/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvSitemapServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.sitemap.negocio.servicios;

public class SrvSitemapServiceServiceLocator extends org.apache.axis.client.Service implements es.pode.sitemap.negocio.servicios.SrvSitemapServiceService {

/**

 */

    public SrvSitemapServiceServiceLocator() {
    }


    public SrvSitemapServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrvSitemapServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrvSitemapService
    private java.lang.String SrvSitemapService_address = "http://localhost:8080/oaipmh/services/SrvSitemapService";

    public java.lang.String getSrvSitemapServiceAddress() {
        return SrvSitemapService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SrvSitemapServiceWSDDServiceName = "SrvSitemapService";

    public java.lang.String getSrvSitemapServiceWSDDServiceName() {
        return SrvSitemapServiceWSDDServiceName;
    }

    public void setSrvSitemapServiceWSDDServiceName(java.lang.String name) {
        SrvSitemapServiceWSDDServiceName = name;
    }

    public es.pode.sitemap.negocio.servicios.SrvSitemapService getSrvSitemapService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrvSitemapService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrvSitemapService(endpoint);
    }

    public es.pode.sitemap.negocio.servicios.SrvSitemapService getSrvSitemapService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.pode.sitemap.negocio.servicios.SrvSitemapServiceSoapBindingStub _stub = new es.pode.sitemap.negocio.servicios.SrvSitemapServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrvSitemapServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrvSitemapServiceEndpointAddress(java.lang.String address) {
        SrvSitemapService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.pode.sitemap.negocio.servicios.SrvSitemapService.class.isAssignableFrom(serviceEndpointInterface)) {
                es.pode.sitemap.negocio.servicios.SrvSitemapServiceSoapBindingStub _stub = new es.pode.sitemap.negocio.servicios.SrvSitemapServiceSoapBindingStub(new java.net.URL(SrvSitemapService_address), this);
                _stub.setPortName(getSrvSitemapServiceWSDDServiceName());
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
        if ("SrvSitemapService".equals(inputPortName)) {
            return getSrvSitemapService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicios.negocio.sitemap.pode.es", "SrvSitemapServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicios.negocio.sitemap.pode.es", "SrvSitemapService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrvSitemapService".equals(portName)) {
            setSrvSitemapServiceEndpointAddress(address);
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
