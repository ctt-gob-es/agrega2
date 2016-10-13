/**
 * SrvCorreoSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.gestorCorreo.negocio.servicios;

public class SrvCorreoSoapBindingStub extends org.apache.axis.client.Stub implements es.pode.gestorCorreo.negocio.servicios.SrvCorreo {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[15];
        _initOperationDesc1();
        _initOperationDesc2();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("altaUsuario");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "correoUsuarioVO"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "CorreoUsuarioVO"), es.pode.gestorCorreo.negocio.servicios.CorreoUsuarioVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "ResultadoEnvioCorreoVO"));
        oper.setReturnClass(es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "altaUsuarioReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("bajaGrupo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "correoGrupoVO"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "CorreoGrupoVO"), es.pode.gestorCorreo.negocio.servicios.CorreoGrupoVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "ResultadoEnvioCorreoVO"));
        oper.setReturnClass(es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "bajaGrupoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("bajaUsuario");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "correoUsuarioVO"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "CorreoUsuarioVO"), es.pode.gestorCorreo.negocio.servicios.CorreoUsuarioVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "ResultadoEnvioCorreoVO"));
        oper.setReturnClass(es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "bajaUsuarioReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("comentarioODE");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "correoOdeVO"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "CorreoOdeVO"), es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "ResultadoEnvioCorreoVO"));
        oper.setReturnClass(es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "comentarioODEReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("contactoAutopublicaODE");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "correoOdeVO"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "CorreoOdeVO"), es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "ResultadoEnvioCorreoVO"));
        oper.setReturnClass(es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "contactoAutopublicaODEReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("contactoPublicaODE");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "correoOdeVO"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "CorreoOdeVO"), es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "ResultadoEnvioCorreoVO"));
        oper.setReturnClass(es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "contactoPublicaODEReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("correoContenidoInapropiado");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "correoODEVO"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "CorreoOdeVO"), es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "ResultadoEnvioCorreoVO"));
        oper.setReturnClass(es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "correoContenidoInapropiadoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("correoEnviarAmigo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "correoOdeVO"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "CorreoOdeVO"), es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "ResultadoEnvioCorreoVO"));
        oper.setReturnClass(es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "correoEnviarAmigoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("correoRechazoContenidoInapropiado");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "correoOdeVO"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "CorreoOdeVO"), es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "ResultadoEnvioCorreoVO"));
        oper.setReturnClass(es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "correoRechazoContenidoInapropiadoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("desactivarUsuario");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "correoUsuarioVO"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "CorreoUsuarioVO"), es.pode.gestorCorreo.negocio.servicios.CorreoUsuarioVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "ResultadoEnvioCorreoVO"));
        oper.setReturnClass(es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "desactivarUsuarioReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("envioODEGrupo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "correoGrupoVO"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "CorreoGrupoVO"), es.pode.gestorCorreo.negocio.servicios.CorreoGrupoVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "ResultadoEnvioCorreoVO"));
        oper.setReturnClass(es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "envioODEGrupoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("nuevaClave");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "correoUsuario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "CorreoUsuarioVO"), es.pode.gestorCorreo.negocio.servicios.CorreoUsuarioVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "ResultadoEnvioCorreoVO"));
        oper.setReturnClass(es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "nuevaClaveReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("publicacionODE");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "correoODEVO"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "CorreoOdeVO"), es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "ResultadoEnvioCorreoVO"));
        oper.setReturnClass(es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "publicacionODEReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("rechazoODE");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "correoODEVO"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "CorreoOdeVO"), es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "ResultadoEnvioCorreoVO"));
        oper.setReturnClass(es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "rechazoODEReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("solicitudBajaUsuario");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "correoUsuarioVO"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "CorreoUsuarioVO"), es.pode.gestorCorreo.negocio.servicios.CorreoUsuarioVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "ResultadoEnvioCorreoVO"));
        oper.setReturnClass(es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "solicitudBajaUsuarioReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[14] = oper;

    }

    public SrvCorreoSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public SrvCorreoSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public SrvCorreoSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "ArrayOfxsd_string");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "CorreoGrupoVO");
            cachedSerQNames.add(qName);
            cls = es.pode.gestorCorreo.negocio.servicios.CorreoGrupoVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "CorreoOdeVO");
            cachedSerQNames.add(qName);
            cls = es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "CorreoUsuarioVO");
            cachedSerQNames.add(qName);
            cls = es.pode.gestorCorreo.negocio.servicios.CorreoUsuarioVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "ResultadoEnvioCorreoVO");
            cachedSerQNames.add(qName);
            cls = es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO.class;
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

    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO altaUsuario(es.pode.gestorCorreo.negocio.servicios.CorreoUsuarioVO correoUsuarioVO) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("altaUsuario");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "altaUsuario"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {correoUsuarioVO});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO bajaGrupo(es.pode.gestorCorreo.negocio.servicios.CorreoGrupoVO correoGrupoVO) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("bajaGrupo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "bajaGrupo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {correoGrupoVO});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO bajaUsuario(es.pode.gestorCorreo.negocio.servicios.CorreoUsuarioVO correoUsuarioVO) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("bajaUsuario");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "bajaUsuario"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {correoUsuarioVO});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO comentarioODE(es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO correoOdeVO) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("comentarioODE");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "comentarioODE"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {correoOdeVO});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO contactoAutopublicaODE(es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO correoOdeVO) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("contactoAutopublicaODE");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "contactoAutopublicaODE"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {correoOdeVO});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO contactoPublicaODE(es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO correoOdeVO) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("contactoPublicaODE");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "contactoPublicaODE"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {correoOdeVO});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO correoContenidoInapropiado(es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO correoODEVO) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("correoContenidoInapropiado");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "correoContenidoInapropiado"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {correoODEVO});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO correoEnviarAmigo(es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO correoOdeVO) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("correoEnviarAmigo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "correoEnviarAmigo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {correoOdeVO});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO correoRechazoContenidoInapropiado(es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO correoOdeVO) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("correoRechazoContenidoInapropiado");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "correoRechazoContenidoInapropiado"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {correoOdeVO});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO desactivarUsuario(es.pode.gestorCorreo.negocio.servicios.CorreoUsuarioVO correoUsuarioVO) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("desactivarUsuario");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "desactivarUsuario"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {correoUsuarioVO});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO envioODEGrupo(es.pode.gestorCorreo.negocio.servicios.CorreoGrupoVO correoGrupoVO) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("envioODEGrupo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "envioODEGrupo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {correoGrupoVO});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO nuevaClave(es.pode.gestorCorreo.negocio.servicios.CorreoUsuarioVO correoUsuario) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("nuevaClave");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "nuevaClave"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {correoUsuario});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO publicacionODE(es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO correoODEVO) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("publicacionODE");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "publicacionODE"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {correoODEVO});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO rechazoODE(es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO correoODEVO) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("rechazoODE");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "rechazoODE"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {correoODEVO});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO solicitudBajaUsuario(es.pode.gestorCorreo.negocio.servicios.CorreoUsuarioVO correoUsuarioVO) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("solicitudBajaUsuario");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "solicitudBajaUsuario"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {correoUsuarioVO});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
