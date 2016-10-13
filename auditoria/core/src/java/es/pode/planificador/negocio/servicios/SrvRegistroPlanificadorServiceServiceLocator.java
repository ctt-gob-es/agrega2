/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvRegistroPlanificadorServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.planificador.negocio.servicios;

public class SrvRegistroPlanificadorServiceServiceLocator extends org.apache.axis.client.Service implements es.pode.planificador.negocio.servicios.SrvRegistroPlanificadorServiceService {

/**
 * Servicio de auditorÃ­a. Nos permite registrar el resultado de las
 * ejecuciones de los trabajos y subtrabajos.
 */

    public SrvRegistroPlanificadorServiceServiceLocator() {
    }


    public SrvRegistroPlanificadorServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrvRegistroPlanificadorServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrvRegistroPlanificadorService
    private java.lang.String SrvRegistroPlanificadorService_address = "http://localhost:8080/planificador-1.0/services/SrvRegistroPlanificadorService";

    public java.lang.String getSrvRegistroPlanificadorServiceAddress() {
        return SrvRegistroPlanificadorService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SrvRegistroPlanificadorServiceWSDDServiceName = "SrvRegistroPlanificadorService";

    public java.lang.String getSrvRegistroPlanificadorServiceWSDDServiceName() {
        return SrvRegistroPlanificadorServiceWSDDServiceName;
    }

    public void setSrvRegistroPlanificadorServiceWSDDServiceName(java.lang.String name) {
        SrvRegistroPlanificadorServiceWSDDServiceName = name;
    }

    public es.pode.planificador.negocio.servicios.SrvRegistroPlanificadorService getSrvRegistroPlanificadorService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrvRegistroPlanificadorService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrvRegistroPlanificadorService(endpoint);
    }

    public es.pode.planificador.negocio.servicios.SrvRegistroPlanificadorService getSrvRegistroPlanificadorService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.pode.planificador.negocio.servicios.SrvRegistroPlanificadorServiceSoapBindingStub _stub = new es.pode.planificador.negocio.servicios.SrvRegistroPlanificadorServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrvRegistroPlanificadorServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrvRegistroPlanificadorServiceEndpointAddress(java.lang.String address) {
        SrvRegistroPlanificadorService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.pode.planificador.negocio.servicios.SrvRegistroPlanificadorService.class.isAssignableFrom(serviceEndpointInterface)) {
                es.pode.planificador.negocio.servicios.SrvRegistroPlanificadorServiceSoapBindingStub _stub = new es.pode.planificador.negocio.servicios.SrvRegistroPlanificadorServiceSoapBindingStub(new java.net.URL(SrvRegistroPlanificadorService_address), this);
                _stub.setPortName(getSrvRegistroPlanificadorServiceWSDDServiceName());
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
        if ("SrvRegistroPlanificadorService".equals(inputPortName)) {
            return getSrvRegistroPlanificadorService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "SrvRegistroPlanificadorServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "SrvRegistroPlanificadorService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrvRegistroPlanificadorService".equals(portName)) {
            setSrvRegistroPlanificadorServiceEndpointAddress(address);
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
