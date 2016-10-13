/**
 * SrvNodoServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.administrar.servicio;

public class SrvNodoServiceSoapBindingStub extends org.apache.axis.client.Stub implements es.pode.buscar.negocio.administrar.servicio.SrvNodoService {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[9];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("crearNodo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "nodo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "NodoVO"), es.pode.buscar.negocio.administrar.servicio.NodoVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("eliminarNodo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "ids"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "ArrayOfsoapenc_long"), java.lang.Long[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "item"));
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "ValidaBajaNodoVO"));
        oper.setReturnClass(es.pode.buscar.negocio.administrar.servicio.ValidaBajaNodoVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "eliminarNodoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listarNodos");
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "ArrayOfNodoVO"));
        oper.setReturnClass(es.pode.buscar.negocio.administrar.servicio.NodoVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "listarNodosReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("modificarNodo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "nodo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "NodoVO"), es.pode.buscar.negocio.administrar.servicio.NodoVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerCCAA");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"), java.lang.Long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "CcaaVO"));
        oper.setReturnClass(es.pode.buscar.negocio.administrar.servicio.CcaaVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "obtenerCCAAReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerCCAAs");
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "ArrayOfCcaaVO"));
        oper.setReturnClass(es.pode.buscar.negocio.administrar.servicio.CcaaVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "obtenerCCAAsReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerNodo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"), java.lang.Long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "NodoVO"));
        oper.setReturnClass(es.pode.buscar.negocio.administrar.servicio.NodoVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "obtenerNodoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerServicio");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"), java.lang.Long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "ServicioVO"));
        oper.setReturnClass(es.pode.buscar.negocio.administrar.servicio.ServicioVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "obtenerServicioReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerServicios");
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "ArrayOfServicioVO"));
        oper.setReturnClass(es.pode.buscar.negocio.administrar.servicio.ServicioVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "obtenerServiciosReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

    }

    public SrvNodoServiceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public SrvNodoServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public SrvNodoServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "ArrayOfCcaaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.administrar.servicio.CcaaVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "CcaaVO");
            qName2 = new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "ArrayOfNodoVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.administrar.servicio.NodoVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "NodoVO");
            qName2 = new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "ArrayOfServicioVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.administrar.servicio.ServicioVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "ServicioVO");
            qName2 = new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "ArrayOfsoapenc_long");
            cachedSerQNames.add(qName);
            cls = java.lang.Long[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long");
            qName2 = new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "CcaaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.administrar.servicio.CcaaVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "NodoVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.administrar.servicio.NodoVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "ServicioVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.administrar.servicio.ServicioVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "ValidaBajaNodoVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.administrar.servicio.ValidaBajaNodoVO.class;
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

    public void crearNodo(es.pode.buscar.negocio.administrar.servicio.NodoVO nodo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("crearNodo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "crearNodo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {nodo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.buscar.negocio.administrar.servicio.ValidaBajaNodoVO eliminarNodo(java.lang.Long[] ids) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eliminarNodo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "eliminarNodo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {ids});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.buscar.negocio.administrar.servicio.ValidaBajaNodoVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.buscar.negocio.administrar.servicio.ValidaBajaNodoVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.buscar.negocio.administrar.servicio.ValidaBajaNodoVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.buscar.negocio.administrar.servicio.NodoVO[] listarNodos() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("listarNodos");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "listarNodos"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.buscar.negocio.administrar.servicio.NodoVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.buscar.negocio.administrar.servicio.NodoVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.buscar.negocio.administrar.servicio.NodoVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void modificarNodo(es.pode.buscar.negocio.administrar.servicio.NodoVO nodo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("modificarNodo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "modificarNodo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {nodo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.buscar.negocio.administrar.servicio.CcaaVO obtenerCCAA(java.lang.Long id) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerCCAA");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "obtenerCCAA"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {id});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.buscar.negocio.administrar.servicio.CcaaVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.buscar.negocio.administrar.servicio.CcaaVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.buscar.negocio.administrar.servicio.CcaaVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.buscar.negocio.administrar.servicio.CcaaVO[] obtenerCCAAs() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerCCAAs");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "obtenerCCAAs"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.buscar.negocio.administrar.servicio.CcaaVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.buscar.negocio.administrar.servicio.CcaaVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.buscar.negocio.administrar.servicio.CcaaVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.buscar.negocio.administrar.servicio.NodoVO obtenerNodo(java.lang.Long id) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerNodo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "obtenerNodo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {id});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.buscar.negocio.administrar.servicio.NodoVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.buscar.negocio.administrar.servicio.NodoVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.buscar.negocio.administrar.servicio.NodoVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.buscar.negocio.administrar.servicio.ServicioVO obtenerServicio(java.lang.Long id) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerServicio");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "obtenerServicio"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {id});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.buscar.negocio.administrar.servicio.ServicioVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.buscar.negocio.administrar.servicio.ServicioVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.buscar.negocio.administrar.servicio.ServicioVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.buscar.negocio.administrar.servicio.ServicioVO[] obtenerServicios() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerServicios");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "obtenerServicios"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.buscar.negocio.administrar.servicio.ServicioVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.buscar.negocio.administrar.servicio.ServicioVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.buscar.negocio.administrar.servicio.ServicioVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
