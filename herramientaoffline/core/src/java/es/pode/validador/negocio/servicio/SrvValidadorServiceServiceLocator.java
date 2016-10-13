/**
 * SrvValidadorServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.validador.negocio.servicio;

public class SrvValidadorServiceServiceLocator extends org.apache.axis.client.Service implements es.pode.validador.negocio.servicio.SrvValidadorServiceService {

/**
 * El servicio de validacion consta de cuatro metodos para
 *             diferentes tipos de validacion:
 *             a) obtenerValidacionBin: parametro de entrada rutaManifest
 * de
 *             tipo String, es la ruta en la      que    se   encuentra
 * el
 *             fichero imsmanifest.xml; parametro de salida, el tipo
 * ValidaVO
 *             Este tipo de validacion realiza las siguientes tareas:
 * 1.- chequea que el fichero se encuentre en la ruta
 *             correspondiente
 *             2.- realiza el parseo del fichero y comprueba que los
 * nodos y
 *             atributos sean correctos
 *             3.- chequeo de los contenidos, en los que se testea que
 * los
 *             ficheros a los que referencia           el imsmanifest.xml
 * sean
 *             correctos y esten en su ruta correspondiente
 *             El resultado de esta validacion sera del tipo ValidaVO,
 * con sus
 *             atributos rellenos seguno
 *             se ha comentado en el mismo
 *             b) obtenerValidacionLigera: parametro de entrada rutaManifest
 * de
 *             tipo String, es la ruta en
 *             la que se encuentra el fichero imsmanifest.xml; parametro
 * de
 *             salida, el tipo ValidaVO
 *             Este tipo de validacion realiza las siguientes tareas:
 * 1.- chequea que el fichero se encuentre en la ruta
 *             correspondiente
 *             2.- realiza el parseo del fichero y comprueba que los
 * nodos y
 *             atributos sean correctos;
 *             esta comprobacion tendra mayor o menor restriccion dependiendo
 * del tipo de ODE que
 *             puede ser: CA (contentAggregation--> si es obligatorio
 * que tenga
 *             al menos una
 *             organizacion) o RCP (ResourceContentPackage --> la etiqueta
 * organizations tiene que ir
 *             vacia)
 *             El resultado de esta validacion sera del tipo ValidaVO,
 * con sus
 *             atributos rellenos seguno
 *             se ha comentado en el mismo
 *             c) validarCargaOde: parametro de entrada rutaManifest
 * de tipo
 *             String, es la ruta en
 *             la que se encuentra el fichero imsmanifest.xml; parametro
 * de
 *             salida, el tipo ValidaVO
 *             Este tipo de validacion realiza las siguientes tareas:
 * 1.- valida que el fichero se encuentre en la ruta
 *             correspondiente
 *             2.- chequea que todos los metadatos de tipo LOM-ES sean
 * correctos
 *             3.- Realiza la validacion Binaria (explicado en apartado
 * a)
 *             4.- chequea que esten rellenos los campos de metadatos
 * basicos
 *             obligatorios
 *             d) validarMDBasicosObl: parametro de entrada MDBasicosOblVO,
 * es
 *             la ruta en
 *             la que se encuentra el fichero imsmanifest.xml; parametro
 * de
 *             salida, el tipo ValidaVO
 *             Con este metodo se obliga a que se rellenen los metadatos
 * basicos obligatorios, si no estan
 *             devolvera un error
 */

    public SrvValidadorServiceServiceLocator() {
    }


    public SrvValidadorServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrvValidadorServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrvValidadorService
    private java.lang.String SrvValidadorService_address = "http://localhost:8080/validador-1/services/SrvValidadorService";

    public java.lang.String getSrvValidadorServiceAddress() {
        return SrvValidadorService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SrvValidadorServiceWSDDServiceName = "SrvValidadorService";

    public java.lang.String getSrvValidadorServiceWSDDServiceName() {
        return SrvValidadorServiceWSDDServiceName;
    }

    public void setSrvValidadorServiceWSDDServiceName(java.lang.String name) {
        SrvValidadorServiceWSDDServiceName = name;
    }

    public es.pode.validador.negocio.servicio.SrvValidadorService getSrvValidadorService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrvValidadorService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrvValidadorService(endpoint);
    }

    public es.pode.validador.negocio.servicio.SrvValidadorService getSrvValidadorService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            es.pode.validador.negocio.servicio.SrvValidadorServiceSoapBindingStub _stub = new es.pode.validador.negocio.servicio.SrvValidadorServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrvValidadorServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrvValidadorServiceEndpointAddress(java.lang.String address) {
        SrvValidadorService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (es.pode.validador.negocio.servicio.SrvValidadorService.class.isAssignableFrom(serviceEndpointInterface)) {
                es.pode.validador.negocio.servicio.SrvValidadorServiceSoapBindingStub _stub = new es.pode.validador.negocio.servicio.SrvValidadorServiceSoapBindingStub(new java.net.URL(SrvValidadorService_address), this);
                _stub.setPortName(getSrvValidadorServiceWSDDServiceName());
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
        if ("SrvValidadorService".equals(inputPortName)) {
            return getSrvValidadorService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "SrvValidadorServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "SrvValidadorService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrvValidadorService".equals(portName)) {
            setSrvValidadorServiceEndpointAddress(address);
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
