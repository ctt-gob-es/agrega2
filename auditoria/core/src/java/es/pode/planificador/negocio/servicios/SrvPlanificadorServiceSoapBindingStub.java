/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvPlanificadorServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.planificador.negocio.servicios;

public class SrvPlanificadorServiceSoapBindingStub extends org.apache.axis.client.Stub implements es.pode.planificador.negocio.servicios.SrvPlanificadorService {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[56];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
        _initOperationDesc4();
        _initOperationDesc5();
        _initOperationDesc6();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("crearInformeCargaMasiva");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "parametro"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ParametroInformeCargaVO"), es.pode.planificador.negocio.servicios.ParametroInformeCargaVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("crearTareaCargaODEs");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "datosTarea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaCargaODEsVO"), es.pode.planificador.negocio.servicios.TareaCargaODEsVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaCargaODEsVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.TareaCargaODEsVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "crearTareaCargaODEsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("crearTareaEliminarNoDisponibles");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "datosTarea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaEliminaNoDisponiblesVO"), es.pode.planificador.negocio.servicios.TareaEliminaNoDisponiblesVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaEliminaNoDisponiblesVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.TareaEliminaNoDisponiblesVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "crearTareaEliminarNoDisponiblesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("crearTareaInformes");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "datosTarea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaInformesVO"), es.pode.planificador.negocio.servicios.TareaInformesVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaInformesVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.TareaInformesVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "crearTareaInformesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("crearTareaInformesCatalogo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "datosTarea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaInformeCatalogoVO"), es.pode.planificador.negocio.servicios.TareaInformeCatalogoVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaInformeCatalogoVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.TareaInformeCatalogoVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "crearTareaInformesCatalogoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("crearTareaInformesFederado");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "datosTarea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaInformesVO"), es.pode.planificador.negocio.servicios.TareaInformesVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaInformesVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.TareaInformesVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "crearTareaInformesFederadoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("crearTareaModificacion");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "datosTarea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaModificacionVO"), es.pode.planificador.negocio.servicios.TareaModificacionVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaModificacionVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.TareaModificacionVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "crearTareaModificacionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("crearTareaRegenerarImagemes");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "datosTarea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaRegenerarImagenesVO"), es.pode.planificador.negocio.servicios.TareaRegenerarImagenesVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaRegenerarImagenesVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.TareaRegenerarImagenesVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "crearTareaRegenerarImagemesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("crearTareaReindexado");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "datosTarea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaReindexadoVO"), es.pode.planificador.negocio.servicios.TareaReindexadoVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaReindexadoVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.TareaReindexadoVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "crearTareaReindexadoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("eliminarInformesCarga");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ficheros"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ArrayOfxsd_string"), java.lang.String[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "item"));
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ValidaBajaInformeCargaVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.ValidaBajaInformeCargaVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "eliminarInformesCargaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("eliminarNoDisponibles");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "fechaInicio"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "fechaHasta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "idTarea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"), java.lang.Long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "eliminarNoDisponiblesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("eliminarTareas");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "tareas"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ArrayOfTrabajoVO"), es.pode.planificador.negocio.servicios.TrabajoVO[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "item"));
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        oper.setReturnClass(java.lang.Boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "eliminarTareasReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("eliminarTareasAdm");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "trabajo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ArrayOfTrabajoVO"), es.pode.planificador.negocio.servicios.TrabajoVO[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "item"));
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        oper.setReturnClass(java.lang.Boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "eliminarTareasAdmReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("eliminarTrabajoEjecutado");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "idTrabajo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ArrayOfsoapenc_long"), java.lang.Long[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "item"));
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        oper.setReturnClass(java.lang.Boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "eliminarTrabajoEjecutadoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("estaIniciado");
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        oper.setReturnClass(java.lang.Boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "estaIniciadoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("existeTrabajo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "trabajo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TrabajoVO"), es.pode.planificador.negocio.servicios.TrabajoVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        oper.setReturnClass(java.lang.Boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "existeTrabajoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("generarCatalogo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "idioma"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("generarInforme");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "datosInforme"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaInformesVO"), es.pode.planificador.negocio.servicios.TareaInformesVO.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "idTarea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"), java.lang.Long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("generarInformeFederado");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "datosInforme"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaInformesVO"), es.pode.planificador.negocio.servicios.TareaInformesVO.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "idTarea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"), java.lang.Long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("generarSitemaps");
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("informesPortada");
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[20] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("iniciarPlanificador");
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        oper.setReturnClass(java.lang.Boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "iniciarPlanificadorReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[21] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("lanzarRSS");
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[22] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("lanzarTarea");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "tarea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaVO"), es.pode.planificador.negocio.servicios.TareaVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        oper.setReturnClass(java.lang.Boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "lanzarTareaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[23] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listarInformesCarga");
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ArrayOfInformeODEsCargadosVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.InformeODEsCargadosVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "listarInformesCargaReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[24] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("modificarTareaCargaODEs");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "datosTareaCargaODE"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaCargaODEsVO"), es.pode.planificador.negocio.servicios.TareaCargaODEsVO.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "trabajo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TrabajoVO"), es.pode.planificador.negocio.servicios.TrabajoVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaCargaODEsVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.TareaCargaODEsVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "modificarTareaCargaODEsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[25] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("modificarTareaEliminarNoDisponibles");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "datosTarea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaEliminaNoDisponiblesVO"), es.pode.planificador.negocio.servicios.TareaEliminaNoDisponiblesVO.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "trabajo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TrabajoVO"), es.pode.planificador.negocio.servicios.TrabajoVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaEliminaNoDisponiblesVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.TareaEliminaNoDisponiblesVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "modificarTareaEliminarNoDisponiblesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[26] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("modificarTareaInformes");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "datosTarea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaInformesVO"), es.pode.planificador.negocio.servicios.TareaInformesVO.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "trabajo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TrabajoVO"), es.pode.planificador.negocio.servicios.TrabajoVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaInformesVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.TareaInformesVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "modificarTareaInformesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[27] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("modificarTareaInformesCatalogo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "datosTarea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaInformeCatalogoVO"), es.pode.planificador.negocio.servicios.TareaInformeCatalogoVO.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "trabajo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TrabajoVO"), es.pode.planificador.negocio.servicios.TrabajoVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaInformeCatalogoVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.TareaInformeCatalogoVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "modificarTareaInformesCatalogoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[28] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("modificarTareaInformesFederado");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "datosTarea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaInformesVO"), es.pode.planificador.negocio.servicios.TareaInformesVO.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "trabajo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TrabajoVO"), es.pode.planificador.negocio.servicios.TrabajoVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaInformesVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.TareaInformesVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "modificarTareaInformesFederadoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[29] = oper;

    }

    private static void _initOperationDesc4(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("modificarTareaModificacion");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "datosTarea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaModificacionVO"), es.pode.planificador.negocio.servicios.TareaModificacionVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaModificacionVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.TareaModificacionVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "modificarTareaModificacionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[30] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("modificarTareaRegenerarImagenes");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "datosTarea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaRegenerarImagenesVO"), es.pode.planificador.negocio.servicios.TareaRegenerarImagenesVO.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "trabajo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TrabajoVO"), es.pode.planificador.negocio.servicios.TrabajoVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaRegenerarImagenesVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.TareaRegenerarImagenesVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "modificarTareaRegenerarImagenesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[31] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("modificarTareaReindexado");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "datosTarea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaReindexadoVO"), es.pode.planificador.negocio.servicios.TareaReindexadoVO.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "trabajo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TrabajoVO"), es.pode.planificador.negocio.servicios.TrabajoVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaReindexadoVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.TareaReindexadoVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "modificarTareaReindexadoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[32] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerInformeTrabajo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "idTarea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"), java.lang.Long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ArrayOfRegistroTareaEjecutadaVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.RegistroTareaEjecutadaVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "obtenerInformeTrabajoReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[33] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerInformeTrabajoCargaMasiva");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "idTrabajo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"), java.lang.Long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ArrayOfRegistroTareaCargaEjecutadaVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.RegistroTareaCargaEjecutadaVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "obtenerInformeTrabajoCargaMasivaReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[34] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerInformeTrabajoErroneos");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"), java.lang.Long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ArrayOfRegistroTareaEjecutadaVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.RegistroTareaEjecutadaVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "obtenerInformeTrabajoErroneosReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[35] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerTareaEliminarrNoDisponibles");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "tarea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaVO"), es.pode.planificador.negocio.servicios.TareaVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaEliminaNoDisponiblesVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.TareaEliminaNoDisponiblesVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "obtenerTareaEliminarrNoDisponiblesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[36] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerTareaInformeCatalogo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "tarea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaInformeCatalogoVO"), es.pode.planificador.negocio.servicios.TareaInformeCatalogoVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaInformeCatalogoVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.TareaInformeCatalogoVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "obtenerTareaInformeCatalogoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[37] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerTareaInformes");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "tarea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaInformesVO"), es.pode.planificador.negocio.servicios.TareaInformesVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaInformesVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.TareaInformesVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "obtenerTareaInformesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[38] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerTareaModificarCargaODEs");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "tarea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaVO"), es.pode.planificador.negocio.servicios.TareaVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaCargaODEsVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.TareaCargaODEsVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "obtenerTareaModificarCargaODEsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[39] = oper;

    }

    private static void _initOperationDesc5(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerTareaRegenerarImagenes");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "tarea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaVO"), es.pode.planificador.negocio.servicios.TareaVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaRegenerarImagenesVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.TareaRegenerarImagenesVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "obtenerTareaRegenerarImagenesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[40] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerTareaReindexado");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "tarea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaVO"), es.pode.planificador.negocio.servicios.TareaVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaReindexadoVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.TareaReindexadoVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "obtenerTareaReindexadoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[41] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerTareasEnEjecucion");
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ArrayOfTareaVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.TareaVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "obtenerTareasEnEjecucionReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[42] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerTareasPendientes");
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ArrayOfTareaVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.TareaVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "obtenerTareasPendientesReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[43] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerTareasPendientesTodas");
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ArrayOfTareaVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.TareaVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "obtenerTareasPendientesTodasReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[44] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerTipoTarea");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "trabajo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TrabajoVO"), es.pode.planificador.negocio.servicios.TrabajoVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "obtenerTipoTareaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[45] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerTrabajoEjecutado");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"), java.lang.Long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaEjecutadaVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.TareaEjecutadaVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "obtenerTrabajoEjecutadoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[46] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerTrabajosEjecutados");
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ArrayOfTareaEjecutadaVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.TareaEjecutadaVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "obtenerTrabajosEjecutadosReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[47] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("pararPlanificador");
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        oper.setReturnClass(java.lang.Boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "pararPlanificadorReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[48] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("pararTarea");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "job"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TrabajoVO"), es.pode.planificador.negocio.servicios.TrabajoVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        oper.setReturnClass(java.lang.Boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "pararTareaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[49] = oper;

    }

    private static void _initOperationDesc6(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("portadaODE");
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[50] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("publicarPIF");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "odes"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ArrayOfxsd_string"), java.lang.String[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "item"));
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "idUsuario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "nombreCarga"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "sobrescribir"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "pathCarga"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ArrayOfResultadoCargaVO"));
        oper.setReturnClass(es.pode.planificador.negocio.servicios.ResultadoCargaVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "publicarPIFReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[51] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("recuperarInformeCarga");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "nombreInforme"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://xml.apache.org/xml-soap", "DataHandler"));
        oper.setReturnClass(javax.activation.DataHandler.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "recuperarInformeCargaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[52] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("regeneracionImagenes");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "idTarea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"), java.lang.Long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "regeneracionImagenesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[53] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("reindexado");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "repositorio"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "idTarea"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"), java.lang.Long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        oper.setReturnClass(java.lang.Long.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "reindexadoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[54] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("reiniciarPlanificador");
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        oper.setReturnClass(java.lang.Boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "reiniciarPlanificadorReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[55] = oper;

    }

    public SrvPlanificadorServiceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public SrvPlanificadorServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public SrvPlanificadorServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ArrayOfInformeODEsCargadosVO");
            cachedSerQNames.add(qName);
            cls = es.pode.planificador.negocio.servicios.InformeODEsCargadosVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "InformeODEsCargadosVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ArrayOfParametrosCargaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.planificador.negocio.servicios.ParametrosCargaVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ParametrosCargaVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ArrayOfRegistroTareaCargaEjecutadaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.planificador.negocio.servicios.RegistroTareaCargaEjecutadaVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "RegistroTareaCargaEjecutadaVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ArrayOfRegistroTareaEjecutadaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.planificador.negocio.servicios.RegistroTareaEjecutadaVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "RegistroTareaEjecutadaVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ArrayOfResultadoCargaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.planificador.negocio.servicios.ResultadoCargaVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ResultadoCargaVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ArrayOfsoapenc_long");
            cachedSerQNames.add(qName);
            cls = java.lang.Long[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ArrayOfTareaEjecutadaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.planificador.negocio.servicios.TareaEjecutadaVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaEjecutadaVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ArrayOfTareaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.planificador.negocio.servicios.TareaVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ArrayOfTrabajoVO");
            cachedSerQNames.add(qName);
            cls = es.pode.planificador.negocio.servicios.TrabajoVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TrabajoVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ArrayOfxsd_string");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "InformeODEsCargadosVO");
            cachedSerQNames.add(qName);
            cls = es.pode.planificador.negocio.servicios.InformeODEsCargadosVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ParametroInformeCargaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.planificador.negocio.servicios.ParametroInformeCargaVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ParametrosCargaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.planificador.negocio.servicios.ParametrosCargaVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "RegistroTareaCargaEjecutadaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.planificador.negocio.servicios.RegistroTareaCargaEjecutadaVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "RegistroTareaEjecutadaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.planificador.negocio.servicios.RegistroTareaEjecutadaVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ResultadoCargaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.planificador.negocio.servicios.ResultadoCargaVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaCargaODEsVO");
            cachedSerQNames.add(qName);
            cls = es.pode.planificador.negocio.servicios.TareaCargaODEsVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaEjecutadaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.planificador.negocio.servicios.TareaEjecutadaVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaEliminaNoDisponiblesVO");
            cachedSerQNames.add(qName);
            cls = es.pode.planificador.negocio.servicios.TareaEliminaNoDisponiblesVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaInformeCatalogoVO");
            cachedSerQNames.add(qName);
            cls = es.pode.planificador.negocio.servicios.TareaInformeCatalogoVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaInformesVO");
            cachedSerQNames.add(qName);
            cls = es.pode.planificador.negocio.servicios.TareaInformesVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaModificacionVO");
            cachedSerQNames.add(qName);
            cls = es.pode.planificador.negocio.servicios.TareaModificacionVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaRegenerarImagenesVO");
            cachedSerQNames.add(qName);
            cls = es.pode.planificador.negocio.servicios.TareaRegenerarImagenesVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaReindexadoVO");
            cachedSerQNames.add(qName);
            cls = es.pode.planificador.negocio.servicios.TareaReindexadoVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.planificador.negocio.servicios.TareaVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TrabajoVO");
            cachedSerQNames.add(qName);
            cls = es.pode.planificador.negocio.servicios.TrabajoVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ValidaBajaInformeCargaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.planificador.negocio.servicios.ValidaBajaInformeCargaVO.class;
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

    public void crearInformeCargaMasiva(es.pode.planificador.negocio.servicios.ParametroInformeCargaVO parametro) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("crearInformeCargaMasiva");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "crearInformeCargaMasiva"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parametro});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.TareaCargaODEsVO crearTareaCargaODEs(es.pode.planificador.negocio.servicios.TareaCargaODEsVO datosTarea) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("crearTareaCargaODEs");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "crearTareaCargaODEs"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {datosTarea});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.TareaCargaODEsVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.TareaCargaODEsVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.TareaCargaODEsVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.TareaEliminaNoDisponiblesVO crearTareaEliminarNoDisponibles(es.pode.planificador.negocio.servicios.TareaEliminaNoDisponiblesVO datosTarea) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("crearTareaEliminarNoDisponibles");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "crearTareaEliminarNoDisponibles"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {datosTarea});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.TareaEliminaNoDisponiblesVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.TareaEliminaNoDisponiblesVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.TareaEliminaNoDisponiblesVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.TareaInformesVO crearTareaInformes(es.pode.planificador.negocio.servicios.TareaInformesVO datosTarea) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("crearTareaInformes");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "crearTareaInformes"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {datosTarea});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.TareaInformesVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.TareaInformesVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.TareaInformesVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.TareaInformeCatalogoVO crearTareaInformesCatalogo(es.pode.planificador.negocio.servicios.TareaInformeCatalogoVO datosTarea) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("crearTareaInformesCatalogo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "crearTareaInformesCatalogo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {datosTarea});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.TareaInformeCatalogoVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.TareaInformeCatalogoVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.TareaInformeCatalogoVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.TareaInformesVO crearTareaInformesFederado(es.pode.planificador.negocio.servicios.TareaInformesVO datosTarea) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("crearTareaInformesFederado");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "crearTareaInformesFederado"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {datosTarea});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.TareaInformesVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.TareaInformesVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.TareaInformesVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.TareaModificacionVO crearTareaModificacion(es.pode.planificador.negocio.servicios.TareaModificacionVO datosTarea) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("crearTareaModificacion");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "crearTareaModificacion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {datosTarea});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.TareaModificacionVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.TareaModificacionVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.TareaModificacionVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.TareaRegenerarImagenesVO crearTareaRegenerarImagemes(es.pode.planificador.negocio.servicios.TareaRegenerarImagenesVO datosTarea) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("crearTareaRegenerarImagemes");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "crearTareaRegenerarImagemes"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {datosTarea});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.TareaRegenerarImagenesVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.TareaRegenerarImagenesVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.TareaRegenerarImagenesVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.TareaReindexadoVO crearTareaReindexado(es.pode.planificador.negocio.servicios.TareaReindexadoVO datosTarea) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("crearTareaReindexado");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "crearTareaReindexado"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {datosTarea});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.TareaReindexadoVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.TareaReindexadoVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.TareaReindexadoVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.ValidaBajaInformeCargaVO eliminarInformesCarga(java.lang.String[] ficheros) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eliminarInformesCarga");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "eliminarInformesCarga"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {ficheros});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.ValidaBajaInformeCargaVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.ValidaBajaInformeCargaVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.ValidaBajaInformeCargaVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String eliminarNoDisponibles(java.util.Calendar fechaInicio, java.util.Calendar fechaHasta, java.lang.Long idTarea) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eliminarNoDisponibles");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "eliminarNoDisponibles"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {fechaInicio, fechaHasta, idTarea});

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

    public java.lang.Boolean eliminarTareas(es.pode.planificador.negocio.servicios.TrabajoVO[] tareas) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eliminarTareas");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "eliminarTareas"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {tareas});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Boolean) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Boolean.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Boolean eliminarTareasAdm(es.pode.planificador.negocio.servicios.TrabajoVO[] trabajo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eliminarTareasAdm");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "eliminarTareasAdm"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {trabajo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Boolean) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Boolean.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Boolean eliminarTrabajoEjecutado(java.lang.Long[] idTrabajo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eliminarTrabajoEjecutado");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "eliminarTrabajoEjecutado"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {idTrabajo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Boolean) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Boolean.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Boolean estaIniciado() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("estaIniciado");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "estaIniciado"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Boolean) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Boolean.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Boolean existeTrabajo(es.pode.planificador.negocio.servicios.TrabajoVO trabajo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("existeTrabajo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "existeTrabajo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {trabajo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Boolean) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Boolean.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void generarCatalogo(java.lang.String idioma) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("generarCatalogo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "generarCatalogo"));

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

    public void generarInforme(es.pode.planificador.negocio.servicios.TareaInformesVO datosInforme, java.lang.Long idTarea) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("generarInforme");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "generarInforme"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {datosInforme, idTarea});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void generarInformeFederado(es.pode.planificador.negocio.servicios.TareaInformesVO datosInforme, java.lang.Long idTarea) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("generarInformeFederado");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "generarInformeFederado"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {datosInforme, idTarea});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void generarSitemaps() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("generarSitemaps");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "generarSitemaps"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void informesPortada() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[20]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("informesPortada");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "informesPortada"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Boolean iniciarPlanificador() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[21]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("iniciarPlanificador");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "iniciarPlanificador"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Boolean) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Boolean.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void lanzarRSS() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[22]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("lanzarRSS");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "lanzarRSS"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Boolean lanzarTarea(es.pode.planificador.negocio.servicios.TareaVO tarea) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[23]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("lanzarTarea");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "lanzarTarea"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {tarea});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Boolean) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Boolean.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.InformeODEsCargadosVO[] listarInformesCarga() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[24]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("listarInformesCarga");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "listarInformesCarga"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.InformeODEsCargadosVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.InformeODEsCargadosVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.InformeODEsCargadosVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.TareaCargaODEsVO modificarTareaCargaODEs(es.pode.planificador.negocio.servicios.TareaCargaODEsVO datosTareaCargaODE, es.pode.planificador.negocio.servicios.TrabajoVO trabajo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[25]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("modificarTareaCargaODEs");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "modificarTareaCargaODEs"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {datosTareaCargaODE, trabajo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.TareaCargaODEsVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.TareaCargaODEsVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.TareaCargaODEsVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.TareaEliminaNoDisponiblesVO modificarTareaEliminarNoDisponibles(es.pode.planificador.negocio.servicios.TareaEliminaNoDisponiblesVO datosTarea, es.pode.planificador.negocio.servicios.TrabajoVO trabajo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[26]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("modificarTareaEliminarNoDisponibles");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "modificarTareaEliminarNoDisponibles"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {datosTarea, trabajo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.TareaEliminaNoDisponiblesVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.TareaEliminaNoDisponiblesVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.TareaEliminaNoDisponiblesVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.TareaInformesVO modificarTareaInformes(es.pode.planificador.negocio.servicios.TareaInformesVO datosTarea, es.pode.planificador.negocio.servicios.TrabajoVO trabajo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[27]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("modificarTareaInformes");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "modificarTareaInformes"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {datosTarea, trabajo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.TareaInformesVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.TareaInformesVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.TareaInformesVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.TareaInformeCatalogoVO modificarTareaInformesCatalogo(es.pode.planificador.negocio.servicios.TareaInformeCatalogoVO datosTarea, es.pode.planificador.negocio.servicios.TrabajoVO trabajo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[28]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("modificarTareaInformesCatalogo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "modificarTareaInformesCatalogo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {datosTarea, trabajo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.TareaInformeCatalogoVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.TareaInformeCatalogoVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.TareaInformeCatalogoVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.TareaInformesVO modificarTareaInformesFederado(es.pode.planificador.negocio.servicios.TareaInformesVO datosTarea, es.pode.planificador.negocio.servicios.TrabajoVO trabajo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[29]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("modificarTareaInformesFederado");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "modificarTareaInformesFederado"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {datosTarea, trabajo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.TareaInformesVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.TareaInformesVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.TareaInformesVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.TareaModificacionVO modificarTareaModificacion(es.pode.planificador.negocio.servicios.TareaModificacionVO datosTarea) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[30]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("modificarTareaModificacion");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "modificarTareaModificacion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {datosTarea});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.TareaModificacionVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.TareaModificacionVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.TareaModificacionVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.TareaRegenerarImagenesVO modificarTareaRegenerarImagenes(es.pode.planificador.negocio.servicios.TareaRegenerarImagenesVO datosTarea, es.pode.planificador.negocio.servicios.TrabajoVO trabajo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[31]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("modificarTareaRegenerarImagenes");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "modificarTareaRegenerarImagenes"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {datosTarea, trabajo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.TareaRegenerarImagenesVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.TareaRegenerarImagenesVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.TareaRegenerarImagenesVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.TareaReindexadoVO modificarTareaReindexado(es.pode.planificador.negocio.servicios.TareaReindexadoVO datosTarea, es.pode.planificador.negocio.servicios.TrabajoVO trabajo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[32]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("modificarTareaReindexado");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "modificarTareaReindexado"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {datosTarea, trabajo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.TareaReindexadoVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.TareaReindexadoVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.TareaReindexadoVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.RegistroTareaEjecutadaVO[] obtenerInformeTrabajo(java.lang.Long idTarea) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[33]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerInformeTrabajo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "obtenerInformeTrabajo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {idTarea});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.RegistroTareaEjecutadaVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.RegistroTareaEjecutadaVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.RegistroTareaEjecutadaVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.RegistroTareaCargaEjecutadaVO[] obtenerInformeTrabajoCargaMasiva(java.lang.Long idTrabajo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[34]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerInformeTrabajoCargaMasiva");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "obtenerInformeTrabajoCargaMasiva"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {idTrabajo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.RegistroTareaCargaEjecutadaVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.RegistroTareaCargaEjecutadaVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.RegistroTareaCargaEjecutadaVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.RegistroTareaEjecutadaVO[] obtenerInformeTrabajoErroneos(java.lang.Long id) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[35]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerInformeTrabajoErroneos");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "obtenerInformeTrabajoErroneos"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {id});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.RegistroTareaEjecutadaVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.RegistroTareaEjecutadaVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.RegistroTareaEjecutadaVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.TareaEliminaNoDisponiblesVO obtenerTareaEliminarrNoDisponibles(es.pode.planificador.negocio.servicios.TareaVO tarea) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[36]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerTareaEliminarrNoDisponibles");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "obtenerTareaEliminarrNoDisponibles"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {tarea});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.TareaEliminaNoDisponiblesVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.TareaEliminaNoDisponiblesVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.TareaEliminaNoDisponiblesVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.TareaInformeCatalogoVO obtenerTareaInformeCatalogo(es.pode.planificador.negocio.servicios.TareaInformeCatalogoVO tarea) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[37]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerTareaInformeCatalogo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "obtenerTareaInformeCatalogo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {tarea});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.TareaInformeCatalogoVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.TareaInformeCatalogoVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.TareaInformeCatalogoVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.TareaInformesVO obtenerTareaInformes(es.pode.planificador.negocio.servicios.TareaInformesVO tarea) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[38]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerTareaInformes");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "obtenerTareaInformes"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {tarea});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.TareaInformesVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.TareaInformesVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.TareaInformesVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.TareaCargaODEsVO obtenerTareaModificarCargaODEs(es.pode.planificador.negocio.servicios.TareaVO tarea) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[39]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerTareaModificarCargaODEs");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "obtenerTareaModificarCargaODEs"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {tarea});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.TareaCargaODEsVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.TareaCargaODEsVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.TareaCargaODEsVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.TareaRegenerarImagenesVO obtenerTareaRegenerarImagenes(es.pode.planificador.negocio.servicios.TareaVO tarea) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[40]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerTareaRegenerarImagenes");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "obtenerTareaRegenerarImagenes"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {tarea});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.TareaRegenerarImagenesVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.TareaRegenerarImagenesVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.TareaRegenerarImagenesVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.TareaReindexadoVO obtenerTareaReindexado(es.pode.planificador.negocio.servicios.TareaVO tarea) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[41]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerTareaReindexado");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "obtenerTareaReindexado"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {tarea});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.TareaReindexadoVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.TareaReindexadoVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.TareaReindexadoVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.TareaVO[] obtenerTareasEnEjecucion() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[42]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerTareasEnEjecucion");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "obtenerTareasEnEjecucion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.TareaVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.TareaVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.TareaVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.TareaVO[] obtenerTareasPendientes() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[43]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerTareasPendientes");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "obtenerTareasPendientes"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.TareaVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.TareaVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.TareaVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.TareaVO[] obtenerTareasPendientesTodas() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[44]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerTareasPendientesTodas");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "obtenerTareasPendientesTodas"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.TareaVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.TareaVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.TareaVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String obtenerTipoTarea(es.pode.planificador.negocio.servicios.TrabajoVO trabajo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[45]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerTipoTarea");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "obtenerTipoTarea"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {trabajo});

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

    public es.pode.planificador.negocio.servicios.TareaEjecutadaVO obtenerTrabajoEjecutado(java.lang.Long id) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[46]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerTrabajoEjecutado");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "obtenerTrabajoEjecutado"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {id});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.TareaEjecutadaVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.TareaEjecutadaVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.TareaEjecutadaVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.TareaEjecutadaVO[] obtenerTrabajosEjecutados() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[47]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerTrabajosEjecutados");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "obtenerTrabajosEjecutados"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.TareaEjecutadaVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.TareaEjecutadaVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.TareaEjecutadaVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Boolean pararPlanificador() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[48]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("pararPlanificador");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "pararPlanificador"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Boolean) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Boolean.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Boolean pararTarea(es.pode.planificador.negocio.servicios.TrabajoVO job) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[49]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("pararTarea");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "pararTarea"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {job});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Boolean) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Boolean.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void portadaODE() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[50]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("portadaODE");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "portadaODE"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.planificador.negocio.servicios.ResultadoCargaVO[] publicarPIF(java.lang.String[] odes, java.lang.String idUsuario, java.lang.String nombreCarga, java.lang.String sobrescribir, java.lang.String pathCarga) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[51]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("publicarPIF");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "publicarPIF"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {odes, idUsuario, nombreCarga, sobrescribir, pathCarga});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.planificador.negocio.servicios.ResultadoCargaVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.planificador.negocio.servicios.ResultadoCargaVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.planificador.negocio.servicios.ResultadoCargaVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public javax.activation.DataHandler recuperarInformeCarga(java.lang.String nombreInforme) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[52]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("recuperarInformeCarga");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "recuperarInformeCarga"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {nombreInforme});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (javax.activation.DataHandler) _resp;
            } catch (java.lang.Exception _exception) {
                return (javax.activation.DataHandler) org.apache.axis.utils.JavaUtils.convert(_resp, javax.activation.DataHandler.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String regeneracionImagenes(java.lang.Long idTarea) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[53]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("regeneracionImagenes");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "regeneracionImagenes"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {idTarea});

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

    public java.lang.Long reindexado(java.lang.String repositorio, java.lang.Long idTarea) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[54]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("reindexado");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "reindexado"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {repositorio, idTarea});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Long) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Long) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Long.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Boolean reiniciarPlanificador() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[55]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("reiniciarPlanificador");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "reiniciarPlanificador"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Boolean) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Boolean.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
