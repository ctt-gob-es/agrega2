/**
 * SrvIndexadorServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.indexador.negocio.servicios.indexado;

public class SrvIndexadorServiceSoapBindingStub extends org.apache.axis.client.Stub implements es.pode.indexador.negocio.servicios.indexado.SrvIndexadorService {
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
        oper.setName("borrarOdesPorIdioma");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "idioma"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "num"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("borrarPorIdioma");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "idioma"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("eliminarODE");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "idODE"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "ArrayOfxsd_string"), java.lang.String[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "ArrayOfIndexadorVO"));
        oper.setReturnClass(es.pode.indexador.negocio.servicios.indexado.IndexadorVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "eliminarODEReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("indexarODE");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "odes"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "ArrayOfIdODEVO"), es.pode.indexador.negocio.servicios.indexado.IdODEVO[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "ArrayOfIndexadorVO"));
        oper.setReturnClass(es.pode.indexador.negocio.servicios.indexado.IndexadorVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "indexarODEReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("optimizarIndice");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "idioma"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("regenerarIndice");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "idioma"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "odes"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "ArrayOfIdODEVO"), es.pode.indexador.negocio.servicios.indexado.IdODEVO[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "ArrayOfIndexadorVO"));
        oper.setReturnClass(es.pode.indexador.negocio.servicios.indexado.IndexadorVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "regenerarIndiceReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("regenerarIndices");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "idTarea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"), java.lang.Long.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "odes"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "ArrayOfIdODEVO"), es.pode.indexador.negocio.servicios.indexado.IdODEVO[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "ArrayOfIndexadorVO"));
        oper.setReturnClass(es.pode.indexador.negocio.servicios.indexado.IndexadorVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "regenerarIndicesReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("reindexarODE");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "odes"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "ArrayOfIdODEVO"), es.pode.indexador.negocio.servicios.indexado.IdODEVO[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "ArrayOfIndexadorVO"));
        oper.setReturnClass(es.pode.indexador.negocio.servicios.indexado.IndexadorVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "reindexarODEReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("sincronizarIndiceCompass");
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "sincronizarIndiceCompassReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

    }

    public SrvIndexadorServiceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public SrvIndexadorServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public SrvIndexadorServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "ArrayOfContribucionVO");
            cachedSerQNames.add(qName);
            cls = es.pode.indexador.negocio.servicios.indexado.ContribucionVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "ContribucionVO");
            qName2 = new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "ArrayOfEntidadVO");
            cachedSerQNames.add(qName);
            cls = es.pode.indexador.negocio.servicios.indexado.EntidadVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "EntidadVO");
            qName2 = new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "ArrayOfIdODEVO");
            cachedSerQNames.add(qName);
            cls = es.pode.indexador.negocio.servicios.indexado.IdODEVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "IdODEVO");
            qName2 = new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "ArrayOfIndexadorVO");
            cachedSerQNames.add(qName);
            cls = es.pode.indexador.negocio.servicios.indexado.IndexadorVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "IndexadorVO");
            qName2 = new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "ArrayOfLomESSecundarioVO");
            cachedSerQNames.add(qName);
            cls = es.pode.indexador.negocio.servicios.indexado.LomESSecundarioVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "LomESSecundarioVO");
            qName2 = new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "ArrayOfxsd_string");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "ContribucionVO");
            cachedSerQNames.add(qName);
            cls = es.pode.indexador.negocio.servicios.indexado.ContribucionVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "EntidadVO");
            cachedSerQNames.add(qName);
            cls = es.pode.indexador.negocio.servicios.indexado.EntidadVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "IdODEVO");
            cachedSerQNames.add(qName);
            cls = es.pode.indexador.negocio.servicios.indexado.IdODEVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "IndexadorVO");
            cachedSerQNames.add(qName);
            cls = es.pode.indexador.negocio.servicios.indexado.IndexadorVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "LomESPrimarioVO");
            cachedSerQNames.add(qName);
            cls = es.pode.indexador.negocio.servicios.indexado.LomESPrimarioVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "LomESSecundarioVO");
            cachedSerQNames.add(qName);
            cls = es.pode.indexador.negocio.servicios.indexado.LomESSecundarioVO.class;
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

    public void borrarOdesPorIdioma(java.lang.String idioma, int num) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("borrarOdesPorIdioma");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "borrarOdesPorIdioma"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {idioma, new java.lang.Integer(num)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void borrarPorIdioma(java.lang.String idioma) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("borrarPorIdioma");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "borrarPorIdioma"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {idioma});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.indexador.negocio.servicios.indexado.IndexadorVO[] eliminarODE(java.lang.String[] idODE) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eliminarODE");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "eliminarODE"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {idODE});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.indexador.negocio.servicios.indexado.IndexadorVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.indexador.negocio.servicios.indexado.IndexadorVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.indexador.negocio.servicios.indexado.IndexadorVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.indexador.negocio.servicios.indexado.IndexadorVO[] indexarODE(es.pode.indexador.negocio.servicios.indexado.IdODEVO[] odes) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("indexarODE");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "indexarODE"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {odes});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.indexador.negocio.servicios.indexado.IndexadorVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.indexador.negocio.servicios.indexado.IndexadorVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.indexador.negocio.servicios.indexado.IndexadorVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void optimizarIndice(java.lang.String idioma) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("optimizarIndice");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "optimizarIndice"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {idioma});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.indexador.negocio.servicios.indexado.IndexadorVO[] regenerarIndice(java.lang.String idioma, es.pode.indexador.negocio.servicios.indexado.IdODEVO[] odes) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("regenerarIndice");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "regenerarIndice"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {idioma, odes});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.indexador.negocio.servicios.indexado.IndexadorVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.indexador.negocio.servicios.indexado.IndexadorVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.indexador.negocio.servicios.indexado.IndexadorVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.indexador.negocio.servicios.indexado.IndexadorVO[] regenerarIndices(java.lang.Long idTarea, es.pode.indexador.negocio.servicios.indexado.IdODEVO[] odes) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("regenerarIndices");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "regenerarIndices"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {idTarea, odes});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.indexador.negocio.servicios.indexado.IndexadorVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.indexador.negocio.servicios.indexado.IndexadorVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.indexador.negocio.servicios.indexado.IndexadorVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.indexador.negocio.servicios.indexado.IndexadorVO[] reindexarODE(es.pode.indexador.negocio.servicios.indexado.IdODEVO[] odes) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("reindexarODE");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "reindexarODE"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {odes});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.indexador.negocio.servicios.indexado.IndexadorVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.indexador.negocio.servicios.indexado.IndexadorVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.indexador.negocio.servicios.indexado.IndexadorVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public boolean sincronizarIndiceCompass() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("sincronizarIndiceCompass");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://indexado.servicios.negocio.indexador.pode.es", "sincronizarIndiceCompass"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
