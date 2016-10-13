/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvValidadorVDEXServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.validador.negocio.servicio;

public class SrvValidadorVDEXServiceSoapBindingStub extends org.apache.axis.client.Stub implements es.pode.validador.negocio.servicio.SrvValidadorVDEXService {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[5];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerValidacionTaxonomia");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "rutaVdex"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "ValidaVdexVO"));
        oper.setReturnClass(es.pode.validador.negocio.servicio.ValidaVdexVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "obtenerValidacionTaxonomiaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerValidacionTaxonomias");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "paramListaVdex"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "ArrayOfxsd_string"), java.lang.String[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "item"));
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "ArrayOfValidaVdexVO"));
        oper.setReturnClass(es.pode.validador.negocio.servicio.ValidaVdexVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "obtenerValidacionTaxonomiasReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerValidacionTesauro");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "rutaVdex"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "ValidaVdexVO"));
        oper.setReturnClass(es.pode.validador.negocio.servicio.ValidaVdexVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "obtenerValidacionTesauroReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerValidacionTesauros");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "paramListaVdex"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "ArrayOfxsd_string"), java.lang.String[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "item"));
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "ArrayOfValidaVdexVO"));
        oper.setReturnClass(es.pode.validador.negocio.servicio.ValidaVdexVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "obtenerValidacionTesaurosReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerValidacionVdex");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "rutaVdex"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "ValidaVdexVO"));
        oper.setReturnClass(es.pode.validador.negocio.servicio.ValidaVdexVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "obtenerValidacionVdexReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

    }

    public SrvValidadorVDEXServiceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public SrvValidadorVDEXServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public SrvValidadorVDEXServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "ArrayOfValidaVdexVO");
            cachedSerQNames.add(qName);
            cls = es.pode.validador.negocio.servicio.ValidaVdexVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "ValidaVdexVO");
            qName2 = new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "ArrayOfxsd_string");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "ValidaVdexVO");
            cachedSerQNames.add(qName);
            cls = es.pode.validador.negocio.servicio.ValidaVdexVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public es.pode.validador.negocio.servicio.ValidaVdexVO obtenerValidacionTaxonomia(java.lang.String rutaVdex) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerValidacionTaxonomia");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "obtenerValidacionTaxonomia"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {rutaVdex});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.validador.negocio.servicio.ValidaVdexVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.validador.negocio.servicio.ValidaVdexVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.validador.negocio.servicio.ValidaVdexVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.validador.negocio.servicio.ValidaVdexVO[] obtenerValidacionTaxonomias(java.lang.String[] paramListaVdex) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerValidacionTaxonomias");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "obtenerValidacionTaxonomias"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {paramListaVdex});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.validador.negocio.servicio.ValidaVdexVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.validador.negocio.servicio.ValidaVdexVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.validador.negocio.servicio.ValidaVdexVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.validador.negocio.servicio.ValidaVdexVO obtenerValidacionTesauro(java.lang.String rutaVdex) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerValidacionTesauro");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "obtenerValidacionTesauro"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {rutaVdex});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.validador.negocio.servicio.ValidaVdexVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.validador.negocio.servicio.ValidaVdexVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.validador.negocio.servicio.ValidaVdexVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.validador.negocio.servicio.ValidaVdexVO[] obtenerValidacionTesauros(java.lang.String[] paramListaVdex) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerValidacionTesauros");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "obtenerValidacionTesauros"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {paramListaVdex});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.validador.negocio.servicio.ValidaVdexVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.validador.negocio.servicio.ValidaVdexVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.validador.negocio.servicio.ValidaVdexVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.validador.negocio.servicio.ValidaVdexVO obtenerValidacionVdex(java.lang.String rutaVdex) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerValidacionVdex");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "obtenerValidacionVdex"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {rutaVdex});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.validador.negocio.servicio.ValidaVdexVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.validador.negocio.servicio.ValidaVdexVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.validador.negocio.servicio.ValidaVdexVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
