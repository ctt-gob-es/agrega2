/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvAuditaPublicacionServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.publicacion.negocio.servicios;

public class SrvAuditaPublicacionServiceSoapBindingStub extends org.apache.axis.client.Stub implements es.pode.publicacion.negocio.servicios.SrvAuditaPublicacionService {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[8];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("actividadTransiciones");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "parametroAuditoria"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "ParametroAuditPublicacionVO"), es.pode.publicacion.negocio.servicios.ParametroAuditPublicacionVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "ArrayOfODEsEstadoVO"));
        oper.setReturnClass(es.pode.publicacion.negocio.servicios.ODEsEstadoVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "actividadTransicionesReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("dimensionesODEsPublicados");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "parametroAuditoria"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "ParametroAuditPublicacionVO"), es.pode.publicacion.negocio.servicios.ParametroAuditPublicacionVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "ArrayOfPesoODEVO"));
        oper.setReturnClass(es.pode.publicacion.negocio.servicios.PesoODEVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "dimensionesODEsPublicadosReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerUltimosOdesPublicados");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "numeroOdes"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "ArrayOfOdePublicadoVO"));
        oper.setReturnClass(es.pode.publicacion.negocio.servicios.OdePublicadoVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "obtenerUltimosOdesPublicadosReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("odesPorEstados");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "parametroAuditoria"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "ParametroAuditPublicacionVO"), es.pode.publicacion.negocio.servicios.ParametroAuditPublicacionVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "ArrayOfODEsEstadoVO"));
        oper.setReturnClass(es.pode.publicacion.negocio.servicios.ODEsEstadoVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "odesPorEstadosReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("odesPorUsuario");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "parametroAuditoria"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "ParametroAuditPublicacionVO"), es.pode.publicacion.negocio.servicios.ParametroAuditPublicacionVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "ArrayOfEstadoActividadVO"));
        oper.setReturnClass(es.pode.publicacion.negocio.servicios.EstadoActividadVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "odesPorUsuarioReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("odesPublicados");
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "ODEsEstadoVO"));
        oper.setReturnClass(es.pode.publicacion.negocio.servicios.ODEsEstadoVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "odesPublicadosReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("odesPublicadosPorIdioma");
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "ArrayOfODEsEstadoIdiomaVO"));
        oper.setReturnClass(es.pode.publicacion.negocio.servicios.ODEsEstadoIdiomaVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "odesPublicadosPorIdiomaReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("titulosODEsPorID");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "idODEs"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "ArrayOfxsd_string"), java.lang.String[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "item"));
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "ArrayOfDetallePublicadoODEVO"));
        oper.setReturnClass(es.pode.publicacion.negocio.servicios.DetallePublicadoODEVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "titulosODEsPorIDReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

    }

    public SrvAuditaPublicacionServiceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public SrvAuditaPublicacionServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public SrvAuditaPublicacionServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "ArrayOfDetallePublicadoODEVO");
            cachedSerQNames.add(qName);
            cls = es.pode.publicacion.negocio.servicios.DetallePublicadoODEVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "DetallePublicadoODEVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "ArrayOfDetalleTransicionVO");
            cachedSerQNames.add(qName);
            cls = es.pode.publicacion.negocio.servicios.DetalleTransicionVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "DetalleTransicionVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "ArrayOfEstadoActividadVO");
            cachedSerQNames.add(qName);
            cls = es.pode.publicacion.negocio.servicios.EstadoActividadVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "EstadoActividadVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "ArrayOfOdePublicadoVO");
            cachedSerQNames.add(qName);
            cls = es.pode.publicacion.negocio.servicios.OdePublicadoVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "OdePublicadoVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "ArrayOfODEsEstadoIdiomaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.publicacion.negocio.servicios.ODEsEstadoIdiomaVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "ODEsEstadoIdiomaVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "ArrayOfODEsEstadoVO");
            cachedSerQNames.add(qName);
            cls = es.pode.publicacion.negocio.servicios.ODEsEstadoVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "ODEsEstadoVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "ArrayOfPesoODEVO");
            cachedSerQNames.add(qName);
            cls = es.pode.publicacion.negocio.servicios.PesoODEVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "PesoODEVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "ArrayOfxsd_string");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "DetallePublicadoODEVO");
            cachedSerQNames.add(qName);
            cls = es.pode.publicacion.negocio.servicios.DetallePublicadoODEVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "DetalleTransicionVO");
            cachedSerQNames.add(qName);
            cls = es.pode.publicacion.negocio.servicios.DetalleTransicionVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "EstadoActividadVO");
            cachedSerQNames.add(qName);
            cls = es.pode.publicacion.negocio.servicios.EstadoActividadVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "OdePublicadoVO");
            cachedSerQNames.add(qName);
            cls = es.pode.publicacion.negocio.servicios.OdePublicadoVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "ODEsEstadoIdiomaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.publicacion.negocio.servicios.ODEsEstadoIdiomaVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "ODEsEstadoVO");
            cachedSerQNames.add(qName);
            cls = es.pode.publicacion.negocio.servicios.ODEsEstadoVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "ParametroAuditPublicacionVO");
            cachedSerQNames.add(qName);
            cls = es.pode.publicacion.negocio.servicios.ParametroAuditPublicacionVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "PesoODEVO");
            cachedSerQNames.add(qName);
            cls = es.pode.publicacion.negocio.servicios.PesoODEVO.class;
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

    public es.pode.publicacion.negocio.servicios.ODEsEstadoVO[] actividadTransiciones(es.pode.publicacion.negocio.servicios.ParametroAuditPublicacionVO parametroAuditoria) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("actividadTransiciones");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "actividadTransiciones"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parametroAuditoria});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.publicacion.negocio.servicios.ODEsEstadoVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.publicacion.negocio.servicios.ODEsEstadoVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.publicacion.negocio.servicios.ODEsEstadoVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.publicacion.negocio.servicios.PesoODEVO[] dimensionesODEsPublicados(es.pode.publicacion.negocio.servicios.ParametroAuditPublicacionVO parametroAuditoria) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("dimensionesODEsPublicados");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "dimensionesODEsPublicados"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parametroAuditoria});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.publicacion.negocio.servicios.PesoODEVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.publicacion.negocio.servicios.PesoODEVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.publicacion.negocio.servicios.PesoODEVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.publicacion.negocio.servicios.OdePublicadoVO[] obtenerUltimosOdesPublicados(int numeroOdes) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerUltimosOdesPublicados");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "obtenerUltimosOdesPublicados"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(numeroOdes)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.publicacion.negocio.servicios.OdePublicadoVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.publicacion.negocio.servicios.OdePublicadoVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.publicacion.negocio.servicios.OdePublicadoVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.publicacion.negocio.servicios.ODEsEstadoVO[] odesPorEstados(es.pode.publicacion.negocio.servicios.ParametroAuditPublicacionVO parametroAuditoria) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("odesPorEstados");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "odesPorEstados"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parametroAuditoria});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.publicacion.negocio.servicios.ODEsEstadoVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.publicacion.negocio.servicios.ODEsEstadoVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.publicacion.negocio.servicios.ODEsEstadoVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.publicacion.negocio.servicios.EstadoActividadVO[] odesPorUsuario(es.pode.publicacion.negocio.servicios.ParametroAuditPublicacionVO parametroAuditoria) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("odesPorUsuario");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "odesPorUsuario"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parametroAuditoria});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.publicacion.negocio.servicios.EstadoActividadVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.publicacion.negocio.servicios.EstadoActividadVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.publicacion.negocio.servicios.EstadoActividadVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.publicacion.negocio.servicios.ODEsEstadoVO odesPublicados() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("odesPublicados");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "odesPublicados"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.publicacion.negocio.servicios.ODEsEstadoVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.publicacion.negocio.servicios.ODEsEstadoVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.publicacion.negocio.servicios.ODEsEstadoVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.publicacion.negocio.servicios.ODEsEstadoIdiomaVO[] odesPublicadosPorIdioma() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("odesPublicadosPorIdioma");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "odesPublicadosPorIdioma"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.publicacion.negocio.servicios.ODEsEstadoIdiomaVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.publicacion.negocio.servicios.ODEsEstadoIdiomaVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.publicacion.negocio.servicios.ODEsEstadoIdiomaVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.publicacion.negocio.servicios.DetallePublicadoODEVO[] titulosODEsPorID(java.lang.String[] idODEs) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("titulosODEsPorID");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "titulosODEsPorID"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {idODEs});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.publicacion.negocio.servicios.DetallePublicadoODEVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.publicacion.negocio.servicios.DetallePublicadoODEVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.publicacion.negocio.servicios.DetallePublicadoODEVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
