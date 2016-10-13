/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvBuscarServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.buscar.servicios;

public class SrvBuscarServiceSoapBindingStub extends org.apache.axis.client.Stub implements es.pode.buscar.negocio.buscar.servicios.SrvBuscarService {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[17];
        _initOperationDesc1();
        _initOperationDesc2();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("borrarHitCache");
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "borrarHitCacheReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscarAvanzado");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "parametros"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ParametrosBusquedaAvanzadaVO30"), es.pode.buscar.negocio.buscar.servicios.ParametrosBusquedaAvanzadaVO30.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ResultadoBusquedaVO"));
        oper.setReturnClass(es.pode.buscar.negocio.buscar.servicios.ResultadoBusquedaVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "buscarAvanzadoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscarLomEs");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "parametros"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ParametrosBusquedaSQIVO"), es.pode.buscar.negocio.buscar.servicios.ParametrosBusquedaSQIVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ResultadoBusquedaSQIVO"));
        oper.setReturnClass(es.pode.buscar.negocio.buscar.servicios.ResultadoBusquedaSQIVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "buscarLomEsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscarSQI");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "consulta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ParamBusquedaSQIVO"), es.pode.buscar.negocio.buscar.servicios.ParamBusquedaSQIVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ResultadoBusquedaSQIVO"));
        oper.setReturnClass(es.pode.buscar.negocio.buscar.servicios.ResultadoBusquedaSQIVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "buscarSQIReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("generadorIdentificadorAvanzado");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "parametrosAvanzada"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ParametrosBusquedaAvanzadaVO30"), es.pode.buscar.negocio.buscar.servicios.ParametrosBusquedaAvanzadaVO30.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "generadorIdentificadorAvanzadoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerEstadisticas");
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ResultadosEstadisticasVO"));
        oper.setReturnClass(es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "obtenerEstadisticasReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerEstadisticasActividadPorFechas");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "fechaDesde"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "fechaHasta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ResultadosEstadisticasActividadVO"));
        oper.setReturnClass(es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasActividadVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "obtenerEstadisticasActividadPorFechasReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerEstadisticasCoberturaCurricularPorFechas");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "fechaDesde"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "fechaHasta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ResultadosEstadisticasCoberturaCurricularVO"));
        oper.setReturnClass(es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasCoberturaCurricularVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "obtenerEstadisticasCoberturaCurricularPorFechasReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerEstadisticasLicenciasPorFechas");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "fechaDesde"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "fechaHasta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ResultadosEstadisticasLicenciasVO"));
        oper.setReturnClass(es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasLicenciasVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "obtenerEstadisticasLicenciasPorFechasReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerEstadisticasOdesPorFechas");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "fechaDesde"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "fechaHasta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ResultadosEstadisticasOdesVO"));
        oper.setReturnClass(es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasOdesVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "obtenerEstadisticasOdesPorFechasReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerEstadisticasPorFechas");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "fechaDesde"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "fechaHasta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ResultadosEstadisticasVO"));
        oper.setReturnClass(es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "obtenerEstadisticasPorFechasReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerEstadisticasTerminosPorFechas");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "fechaDesde"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "fechaHasta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ResultadosEstadisticasTerminosVO"));
        oper.setReturnClass(es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasTerminosVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "obtenerEstadisticasTerminosPorFechasReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("solicitarDocsCount");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "parametros"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ParametrosDocsCountVO30"), es.pode.buscar.negocio.buscar.servicios.ParametrosDocsCountVO30.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ResultadosNodoCountVO"));
        oper.setReturnClass(es.pode.buscar.negocio.buscar.servicios.ResultadosNodoCountVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "solicitarDocsCountReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("solicitarMetadato");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "parametros"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ParametroMetadatoVO"), es.pode.buscar.negocio.buscar.servicios.ParametroMetadatoVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "MetadatoBasicoVO"));
        oper.setReturnClass(es.pode.buscar.negocio.buscar.servicios.MetadatoBasicoVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "solicitarMetadatoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("solicitarMetadatosOaiOre");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "parametros"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ParametroMetadatoVO"), es.pode.buscar.negocio.buscar.servicios.ParametroMetadatoVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "MetadatoOiaOreVO"));
        oper.setReturnClass(es.pode.buscar.negocio.buscar.servicios.MetadatoOiaOreVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "solicitarMetadatosOaiOreReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("solicitarMetadatosOdes");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "parametros"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ArrayOfParametroMetadatoVO"), es.pode.buscar.negocio.buscar.servicios.ParametroMetadatoVO[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ArrayOfMetadatoBasicoVO"));
        oper.setReturnClass(es.pode.buscar.negocio.buscar.servicios.MetadatoBasicoVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "solicitarMetadatosOdesReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("sugerirBusqueda");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "parametros"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ParametrosBusquedaAvanzadaVO30"), es.pode.buscar.negocio.buscar.servicios.ParametrosBusquedaAvanzadaVO30.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ArrayOfSugerenciasBusquedaVO"));
        oper.setReturnClass(es.pode.buscar.negocio.buscar.servicios.SugerenciasBusquedaVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "sugerirBusquedaReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[16] = oper;

    }

    public SrvBuscarServiceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public SrvBuscarServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public SrvBuscarServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ArrayOfContribucionOdeVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.buscar.servicios.ContribucionOdeVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ContribucionOdeVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ArrayOfEntidadOdeVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.buscar.servicios.EntidadOdeVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "EntidadOdeVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ArrayOfLomEsVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.buscar.servicios.LomEsVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "LomEsVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ArrayOfMetadatoBasicoVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.buscar.servicios.MetadatoBasicoVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "MetadatoBasicoVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ArrayOfParametroMetadatoVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.buscar.servicios.ParametroMetadatoVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ParametroMetadatoVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ArrayOfResultadosTaxonVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.buscar.servicios.ResultadosTaxonVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ResultadosTaxonVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ArrayOfsoapenc_int");
            cachedSerQNames.add(qName);
            cls = java.lang.Integer[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int");
            qName2 = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ArrayOfSugerenciasBusquedaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.buscar.servicios.SugerenciasBusquedaVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "SugerenciasBusquedaVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ArrayOfValoresBusquedaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.buscar.servicios.ValoresBusquedaVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ValoresBusquedaVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ArrayOfxsd_int");
            cachedSerQNames.add(qName);
            cls = int[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int");
            qName2 = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ArrayOfxsd_string");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ContribucionOdeVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.buscar.servicios.ContribucionOdeVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "EntidadOdeVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.buscar.servicios.EntidadOdeVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "LomEsVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.buscar.servicios.LomEsVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "MetadatoBasicoVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.buscar.servicios.MetadatoBasicoVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "MetadatoOiaOreVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.buscar.servicios.MetadatoOiaOreVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ParamBusquedaSQIVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.buscar.servicios.ParamBusquedaSQIVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ParametroMetadatoVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.buscar.servicios.ParametroMetadatoVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ParametrosBusquedaAvanzadaVO30");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.buscar.servicios.ParametrosBusquedaAvanzadaVO30.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ParametrosBusquedaSQIVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.buscar.servicios.ParametrosBusquedaSQIVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ParametrosDocsCountVO30");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.buscar.servicios.ParametrosDocsCountVO30.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ResultadoBusquedaSQIVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.buscar.servicios.ResultadoBusquedaSQIVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ResultadoBusquedaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.buscar.servicios.ResultadoBusquedaVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ResultadosEstadisticasActividadVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasActividadVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ResultadosEstadisticasCoberturaCurricularVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasCoberturaCurricularVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ResultadosEstadisticasLicenciasVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasLicenciasVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ResultadosEstadisticasOdesVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasOdesVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ResultadosEstadisticasTerminosVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasTerminosVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ResultadosEstadisticasVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ResultadosNodoCountVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.buscar.servicios.ResultadosNodoCountVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ResultadosTaxonVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.buscar.servicios.ResultadosTaxonVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "SugerenciasBusquedaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.buscar.servicios.SugerenciasBusquedaVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ValoresBusquedaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.buscar.negocio.buscar.servicios.ValoresBusquedaVO.class;
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

    public boolean borrarHitCache() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("borrarHitCache");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "borrarHitCache"));

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

    public es.pode.buscar.negocio.buscar.servicios.ResultadoBusquedaVO buscarAvanzado(es.pode.buscar.negocio.buscar.servicios.ParametrosBusquedaAvanzadaVO30 parametros) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("buscarAvanzado");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "buscarAvanzado"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parametros});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.buscar.negocio.buscar.servicios.ResultadoBusquedaVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.buscar.negocio.buscar.servicios.ResultadoBusquedaVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.buscar.negocio.buscar.servicios.ResultadoBusquedaVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.buscar.negocio.buscar.servicios.ResultadoBusquedaSQIVO buscarLomEs(es.pode.buscar.negocio.buscar.servicios.ParametrosBusquedaSQIVO parametros) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("buscarLomEs");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "buscarLomEs"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parametros});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.buscar.negocio.buscar.servicios.ResultadoBusquedaSQIVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.buscar.negocio.buscar.servicios.ResultadoBusquedaSQIVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.buscar.negocio.buscar.servicios.ResultadoBusquedaSQIVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.buscar.negocio.buscar.servicios.ResultadoBusquedaSQIVO buscarSQI(es.pode.buscar.negocio.buscar.servicios.ParamBusquedaSQIVO consulta) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("buscarSQI");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "buscarSQI"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {consulta});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.buscar.negocio.buscar.servicios.ResultadoBusquedaSQIVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.buscar.negocio.buscar.servicios.ResultadoBusquedaSQIVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.buscar.negocio.buscar.servicios.ResultadoBusquedaSQIVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String generadorIdentificadorAvanzado(es.pode.buscar.negocio.buscar.servicios.ParametrosBusquedaAvanzadaVO30 parametrosAvanzada) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("generadorIdentificadorAvanzado");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "generadorIdentificadorAvanzado"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parametrosAvanzada});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasVO obtenerEstadisticas() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerEstadisticas");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "obtenerEstadisticas"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasActividadVO obtenerEstadisticasActividadPorFechas(java.util.Calendar fechaDesde, java.util.Calendar fechaHasta) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerEstadisticasActividadPorFechas");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "obtenerEstadisticasActividadPorFechas"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {fechaDesde, fechaHasta});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasActividadVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasActividadVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasActividadVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasCoberturaCurricularVO obtenerEstadisticasCoberturaCurricularPorFechas(java.util.Calendar fechaDesde, java.util.Calendar fechaHasta) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerEstadisticasCoberturaCurricularPorFechas");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "obtenerEstadisticasCoberturaCurricularPorFechas"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {fechaDesde, fechaHasta});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasCoberturaCurricularVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasCoberturaCurricularVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasCoberturaCurricularVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasLicenciasVO obtenerEstadisticasLicenciasPorFechas(java.util.Calendar fechaDesde, java.util.Calendar fechaHasta) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerEstadisticasLicenciasPorFechas");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "obtenerEstadisticasLicenciasPorFechas"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {fechaDesde, fechaHasta});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasLicenciasVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasLicenciasVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasLicenciasVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasOdesVO obtenerEstadisticasOdesPorFechas(java.util.Calendar fechaDesde, java.util.Calendar fechaHasta) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerEstadisticasOdesPorFechas");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "obtenerEstadisticasOdesPorFechas"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {fechaDesde, fechaHasta});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasOdesVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasOdesVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasOdesVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasVO obtenerEstadisticasPorFechas(java.util.Calendar fechaDesde, java.util.Calendar fechaHasta) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerEstadisticasPorFechas");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "obtenerEstadisticasPorFechas"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {fechaDesde, fechaHasta});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasTerminosVO obtenerEstadisticasTerminosPorFechas(java.util.Calendar fechaDesde, java.util.Calendar fechaHasta) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerEstadisticasTerminosPorFechas");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "obtenerEstadisticasTerminosPorFechas"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {fechaDesde, fechaHasta});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasTerminosVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasTerminosVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasTerminosVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.buscar.negocio.buscar.servicios.ResultadosNodoCountVO solicitarDocsCount(es.pode.buscar.negocio.buscar.servicios.ParametrosDocsCountVO30 parametros) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("solicitarDocsCount");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "solicitarDocsCount"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parametros});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.buscar.negocio.buscar.servicios.ResultadosNodoCountVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.buscar.negocio.buscar.servicios.ResultadosNodoCountVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.buscar.negocio.buscar.servicios.ResultadosNodoCountVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.buscar.negocio.buscar.servicios.MetadatoBasicoVO solicitarMetadato(es.pode.buscar.negocio.buscar.servicios.ParametroMetadatoVO parametros) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("solicitarMetadato");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "solicitarMetadato"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parametros});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.buscar.negocio.buscar.servicios.MetadatoBasicoVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.buscar.negocio.buscar.servicios.MetadatoBasicoVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.buscar.negocio.buscar.servicios.MetadatoBasicoVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.buscar.negocio.buscar.servicios.MetadatoOiaOreVO solicitarMetadatosOaiOre(es.pode.buscar.negocio.buscar.servicios.ParametroMetadatoVO parametros) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("solicitarMetadatosOaiOre");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "solicitarMetadatosOaiOre"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parametros});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.buscar.negocio.buscar.servicios.MetadatoOiaOreVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.buscar.negocio.buscar.servicios.MetadatoOiaOreVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.buscar.negocio.buscar.servicios.MetadatoOiaOreVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.buscar.negocio.buscar.servicios.MetadatoBasicoVO[] solicitarMetadatosOdes(es.pode.buscar.negocio.buscar.servicios.ParametroMetadatoVO[] parametros) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("solicitarMetadatosOdes");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "solicitarMetadatosOdes"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parametros});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.buscar.negocio.buscar.servicios.MetadatoBasicoVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.buscar.negocio.buscar.servicios.MetadatoBasicoVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.buscar.negocio.buscar.servicios.MetadatoBasicoVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.buscar.negocio.buscar.servicios.SugerenciasBusquedaVO[] sugerirBusqueda(es.pode.buscar.negocio.buscar.servicios.ParametrosBusquedaAvanzadaVO30 parametros) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("sugerirBusqueda");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "sugerirBusqueda"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parametros});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.buscar.negocio.buscar.servicios.SugerenciasBusquedaVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.buscar.negocio.buscar.servicios.SugerenciasBusquedaVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.buscar.negocio.buscar.servicios.SugerenciasBusquedaVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
