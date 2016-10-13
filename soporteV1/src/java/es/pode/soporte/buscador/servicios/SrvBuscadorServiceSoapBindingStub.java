/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvBuscadorServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.soporte.buscador.servicios;

public class SrvBuscadorServiceSoapBindingStub extends org.apache.axis.client.Stub implements es.pode.soporte.buscador.servicios.SrvBuscadorService {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[19];
        _initOperationDesc1();
        _initOperationDesc2();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("busquedaAvanzada");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "paramBusq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ParamAvanzadoVO"), es.pode.soporte.buscador.servicios.ParamAvanzadoVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "DocumentosVO"));
        oper.setReturnClass(es.pode.soporte.buscador.servicios.DocumentosVO30.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "busquedaAvanzadaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("busquedaHeadersRepositorio");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "paramBusq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ParamPeriodoRepositorioVO"), es.pode.soporte.buscador.servicios.ParamPeriodoRepositorioVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ArrayOfResultadoHeaderVO"));
        oper.setReturnClass(es.pode.soporte.buscador.servicios.ResultadoHeaderVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "busquedaHeadersRepositorioReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("busquedaLOM_ES");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "paramBusq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "QuerySimpleVO"), es.pode.soporte.buscador.servicios.QuerySimpleVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "DocumentosLOM_ESVO"));
        oper.setReturnClass(es.pode.soporte.buscador.servicios.DocumentosLOM_ESVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "busquedaLOM_ESReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("busquedaLOM_ESVSQL");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "paramBusqueda"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "QuerySimpleVO"), es.pode.soporte.buscador.servicios.QuerySimpleVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "DocumentosLOM_ESVO"));
        oper.setReturnClass(es.pode.soporte.buscador.servicios.DocumentosLOM_ESVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "busquedaLOM_ESVSQLReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("busquedaMEC");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "identificadorMEC"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "idiomaBusqueda"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "DocVO30"));
        oper.setReturnClass(es.pode.soporte.buscador.servicios.DocVO30.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "busquedaMECReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("busquedaMECRepositorio");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "idMEC"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ResultadoRecordVO"));
        oper.setReturnClass(es.pode.soporte.buscador.servicios.ResultadoRecordVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "busquedaMECRepositorioReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("busquedaMECSimple");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "identificadorMEC"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "idiomaBusqueda"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "DocMECSimpleVO"));
        oper.setReturnClass(es.pode.soporte.buscador.servicios.DocMECSimpleVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "busquedaMECSimpleReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("busquedaRepositorio");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "paramBusq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ParamPeriodoRepositorioVO"), es.pode.soporte.buscador.servicios.ParamPeriodoRepositorioVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ArrayOfResultadoRecordVO"));
        oper.setReturnClass(es.pode.soporte.buscador.servicios.ResultadoRecordVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "busquedaRepositorioReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("busquedaSimple");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "paramBusq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ParamSimpleVO"), es.pode.soporte.buscador.servicios.ParamSimpleVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "DocumentosVO30"));
        oper.setReturnClass(es.pode.soporte.buscador.servicios.DocumentosVO30.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "busquedaSimpleReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("busquedaVariosMEC");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "parametros"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ArrayOfParamBuscarMecVO"), es.pode.soporte.buscador.servicios.ParamBuscarMecVO[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "item"));
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ArrayOfDocVO30"));
        oper.setReturnClass(es.pode.soporte.buscador.servicios.DocVO30[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "busquedaVariosMECReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("fechaInicioRepositorio");
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        oper.setReturnClass(java.util.Calendar.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "fechaInicioRepositorioReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerCatalogoRepositorioParam");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "nivelAgregacion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"), java.lang.Integer.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ArrayOfResultadoRepositorioVO"));
        oper.setReturnClass(es.pode.soporte.buscador.servicios.ResultadoRepositorioVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "obtenerCatalogoRepositorioParamReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerODERandom");
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "DocVO30"));
        oper.setReturnClass(es.pode.soporte.buscador.servicios.DocVO30.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "obtenerODERandomReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerPalabrasClave");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "paramBusq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ParamPalabrasClave"), es.pode.soporte.buscador.servicios.ParamPalabrasClave.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "PrioridadPalabrasClaveVO"));
        oper.setReturnClass(es.pode.soporte.buscador.servicios.PrioridadPalabrasClaveVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "obtenerPalabrasClaveReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerRepositorio");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "paramRepositorio"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ParamPeriodoRepositorioVO"), es.pode.soporte.buscador.servicios.ParamPeriodoRepositorioVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ArrayOfResultadoRepositorioVO"));
        oper.setReturnClass(es.pode.soporte.buscador.servicios.ResultadoRepositorioVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "obtenerRepositorioReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerSugerencias");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "paramSug"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ParamAvanzadoVO"), es.pode.soporte.buscador.servicios.ParamAvanzadoVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ArrayOfSugerenciasVO"));
        oper.setReturnClass(es.pode.soporte.buscador.servicios.SugerenciasVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "obtenerSugerenciasReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerTotalesRepositorio");
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ResultadosCountVO"));
        oper.setReturnClass(es.pode.soporte.buscador.servicios.ResultadosCountVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "obtenerTotalesRepositorioReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerTotalesRepositorioFechas");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "paramPeriodoVO"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ParamPeriodoRepositorioVO"), es.pode.soporte.buscador.servicios.ParamPeriodoRepositorioVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ResultadosCountVO"));
        oper.setReturnClass(es.pode.soporte.buscador.servicios.ResultadosCountVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "obtenerTotalesRepositorioFechasReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("solicitudDocsCount");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "paramBusq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ParamDocsCountVO"), es.pode.soporte.buscador.servicios.ParamDocsCountVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ResultadosCountVO"));
        oper.setReturnClass(es.pode.soporte.buscador.servicios.ResultadosCountVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "solicitudDocsCountReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[18] = oper;

    }

    public SrvBuscadorServiceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public SrvBuscadorServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public SrvBuscadorServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ArrayOfContribucionVO");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.ContribucionVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ContribucionVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ArrayOfDocLOM_ESVO");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.DocLOM_ESVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "DocLOM_ESVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ArrayOfDocVO30");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.DocVO30[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "DocVO30");
            qName2 = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ArrayOfEntidadVO");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.EntidadVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "EntidadVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ArrayOfPalabraClaveVO");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.PalabraClaveVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "PalabraClaveVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ArrayOfParamBuscarMecVO");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.ParamBuscarMecVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ParamBuscarMecVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ArrayOfResultadoHeaderVO");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.ResultadoHeaderVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ResultadoHeaderVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ArrayOfResultadoRecordVO");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.ResultadoRecordVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ResultadoRecordVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ArrayOfResultadoRepositorioVO");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.ResultadoRepositorioVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ResultadoRepositorioVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ArrayOfsoapenc_int");
            cachedSerQNames.add(qName);
            cls = java.lang.Integer[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int");
            qName2 = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ArrayOfSugerenciasVO");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.SugerenciasVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "SugerenciasVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ArrayOfTaxonVO30");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.TaxonVO30[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "TaxonVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ArrayOfxsd_string");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ContribucionVO");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.ContribucionVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "DocLOM_ESVO");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.DocLOM_ESVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "DocMECSimpleVO");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.DocMECSimpleVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "DocumentosLOM_ESVO");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.DocumentosLOM_ESVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "DocumentosVO30");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.DocumentosVO30.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "DocVO30");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.DocVO30.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "EntidadVO");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.EntidadVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "PalabraClaveVO");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.PalabraClaveVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ParamAvanzadoVO");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.ParamAvanzadoVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ParamBuscarMecVO");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.ParamBuscarMecVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ParamDocsCountVO");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.ParamDocsCountVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ParamPalabrasClave");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.ParamPalabrasClave.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ParamPeriodoRepositorioVO");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.ParamPeriodoRepositorioVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ParamSimpleVO");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.ParamSimpleVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "PrioridadPalabrasClaveVO");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.PrioridadPalabrasClaveVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "QuerySimpleVO");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.QuerySimpleVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ResultadoHeaderVO");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.ResultadoHeaderVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ResultadoRecordVO");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.ResultadoRecordVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ResultadoRepositorioVO");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.ResultadoRepositorioVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "ResultadosCountVO");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.ResultadosCountVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "SugerenciasVO");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.SugerenciasVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "TaxonVO30");
            cachedSerQNames.add(qName);
            cls = es.pode.soporte.buscador.servicios.TaxonVO30.class;
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

    public es.pode.soporte.buscador.servicios.DocumentosVO30 busquedaAvanzada(es.pode.soporte.buscador.servicios.ParamAvanzadoVO paramBusq) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("busquedaAvanzada");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "busquedaAvanzada"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {paramBusq});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.soporte.buscador.servicios.DocumentosVO30) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.soporte.buscador.servicios.DocumentosVO30) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.soporte.buscador.servicios.DocumentosVO30.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.soporte.buscador.servicios.ResultadoHeaderVO[] busquedaHeadersRepositorio(es.pode.soporte.buscador.servicios.ParamPeriodoRepositorioVO paramBusq) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("busquedaHeadersRepositorio");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "busquedaHeadersRepositorio"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {paramBusq});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.soporte.buscador.servicios.ResultadoHeaderVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.soporte.buscador.servicios.ResultadoHeaderVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.soporte.buscador.servicios.ResultadoHeaderVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.soporte.buscador.servicios.DocumentosLOM_ESVO busquedaLOM_ES(es.pode.soporte.buscador.servicios.QuerySimpleVO paramBusq) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("busquedaLOM_ES");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "busquedaLOM_ES"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {paramBusq});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.soporte.buscador.servicios.DocumentosLOM_ESVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.soporte.buscador.servicios.DocumentosLOM_ESVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.soporte.buscador.servicios.DocumentosLOM_ESVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.soporte.buscador.servicios.DocumentosLOM_ESVO busquedaLOM_ESVSQL(es.pode.soporte.buscador.servicios.QuerySimpleVO paramBusqueda) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("busquedaLOM_ESVSQL");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "busquedaLOM_ESVSQL"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {paramBusqueda});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.soporte.buscador.servicios.DocumentosLOM_ESVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.soporte.buscador.servicios.DocumentosLOM_ESVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.soporte.buscador.servicios.DocumentosLOM_ESVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.soporte.buscador.servicios.DocVO30 busquedaMEC(java.lang.String identificadorMEC, java.lang.String idiomaBusqueda) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("busquedaMEC");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "busquedaMEC"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {identificadorMEC, idiomaBusqueda});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.soporte.buscador.servicios.DocVO30) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.soporte.buscador.servicios.DocVO30) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.soporte.buscador.servicios.DocVO30.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.soporte.buscador.servicios.ResultadoRecordVO busquedaMECRepositorio(java.lang.String idMEC) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("busquedaMECRepositorio");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "busquedaMECRepositorio"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {idMEC});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.soporte.buscador.servicios.ResultadoRecordVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.soporte.buscador.servicios.ResultadoRecordVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.soporte.buscador.servicios.ResultadoRecordVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.soporte.buscador.servicios.DocMECSimpleVO busquedaMECSimple(java.lang.String identificadorMEC, java.lang.String idiomaBusqueda) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("busquedaMECSimple");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "busquedaMECSimple"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {identificadorMEC, idiomaBusqueda});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.soporte.buscador.servicios.DocMECSimpleVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.soporte.buscador.servicios.DocMECSimpleVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.soporte.buscador.servicios.DocMECSimpleVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.soporte.buscador.servicios.ResultadoRecordVO[] busquedaRepositorio(es.pode.soporte.buscador.servicios.ParamPeriodoRepositorioVO paramBusq) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("busquedaRepositorio");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "busquedaRepositorio"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {paramBusq});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.soporte.buscador.servicios.ResultadoRecordVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.soporte.buscador.servicios.ResultadoRecordVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.soporte.buscador.servicios.ResultadoRecordVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.soporte.buscador.servicios.DocumentosVO30 busquedaSimple(es.pode.soporte.buscador.servicios.ParamSimpleVO paramBusq) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("busquedaSimple");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "busquedaSimple"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {paramBusq});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.soporte.buscador.servicios.DocumentosVO30) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.soporte.buscador.servicios.DocumentosVO30) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.soporte.buscador.servicios.DocumentosVO30.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.soporte.buscador.servicios.DocVO30[] busquedaVariosMEC(es.pode.soporte.buscador.servicios.ParamBuscarMecVO[] parametros) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("busquedaVariosMEC");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "busquedaVariosMEC"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parametros});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.soporte.buscador.servicios.DocVO30[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.soporte.buscador.servicios.DocVO30[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.soporte.buscador.servicios.DocVO30[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.util.Calendar fechaInicioRepositorio() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("fechaInicioRepositorio");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "fechaInicioRepositorio"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.util.Calendar) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.util.Calendar) org.apache.axis.utils.JavaUtils.convert(_resp, java.util.Calendar.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.soporte.buscador.servicios.ResultadoRepositorioVO[] obtenerCatalogoRepositorioParam(java.lang.Integer nivelAgregacion) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerCatalogoRepositorioParam");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "obtenerCatalogoRepositorioParam"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {nivelAgregacion});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.soporte.buscador.servicios.ResultadoRepositorioVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.soporte.buscador.servicios.ResultadoRepositorioVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.soporte.buscador.servicios.ResultadoRepositorioVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.soporte.buscador.servicios.DocVO30 obtenerODERandom() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerODERandom");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "obtenerODERandom"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.soporte.buscador.servicios.DocVO30) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.soporte.buscador.servicios.DocVO30) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.soporte.buscador.servicios.DocVO30.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.soporte.buscador.servicios.PrioridadPalabrasClaveVO obtenerPalabrasClave(es.pode.soporte.buscador.servicios.ParamPalabrasClave paramBusq) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerPalabrasClave");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "obtenerPalabrasClave"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {paramBusq});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.soporte.buscador.servicios.PrioridadPalabrasClaveVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.soporte.buscador.servicios.PrioridadPalabrasClaveVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.soporte.buscador.servicios.PrioridadPalabrasClaveVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.soporte.buscador.servicios.ResultadoRepositorioVO[] obtenerRepositorio(es.pode.soporte.buscador.servicios.ParamPeriodoRepositorioVO paramRepositorio) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerRepositorio");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "obtenerRepositorio"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {paramRepositorio});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.soporte.buscador.servicios.ResultadoRepositorioVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.soporte.buscador.servicios.ResultadoRepositorioVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.soporte.buscador.servicios.ResultadoRepositorioVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.soporte.buscador.servicios.SugerenciasVO[] obtenerSugerencias(es.pode.soporte.buscador.servicios.ParamAvanzadoVO paramSug) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerSugerencias");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "obtenerSugerencias"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {paramSug});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.soporte.buscador.servicios.SugerenciasVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.soporte.buscador.servicios.SugerenciasVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.soporte.buscador.servicios.SugerenciasVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.soporte.buscador.servicios.ResultadosCountVO obtenerTotalesRepositorio() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerTotalesRepositorio");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "obtenerTotalesRepositorio"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.soporte.buscador.servicios.ResultadosCountVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.soporte.buscador.servicios.ResultadosCountVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.soporte.buscador.servicios.ResultadosCountVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.soporte.buscador.servicios.ResultadosCountVO obtenerTotalesRepositorioFechas(es.pode.soporte.buscador.servicios.ParamPeriodoRepositorioVO paramPeriodoVO) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerTotalesRepositorioFechas");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "obtenerTotalesRepositorioFechas"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {paramPeriodoVO});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.soporte.buscador.servicios.ResultadosCountVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.soporte.buscador.servicios.ResultadosCountVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.soporte.buscador.servicios.ResultadosCountVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.soporte.buscador.servicios.ResultadosCountVO solicitudDocsCount(es.pode.soporte.buscador.servicios.ParamDocsCountVO paramBusq) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("solicitudDocsCount");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscador.soporte.pode.es", "solicitudDocsCount"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {paramBusq});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.soporte.buscador.servicios.ResultadosCountVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.soporte.buscador.servicios.ResultadosCountVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.soporte.buscador.servicios.ResultadosCountVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
