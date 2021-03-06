/**
 * SrvTesaurosServicesSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.fuentestaxonomicas.negocio.servicio;

public class SrvTesaurosServicesSoapBindingStub extends org.apache.axis.client.Stub implements es.pode.fuentestaxonomicas.negocio.servicio.SrvTesaurosServices {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[14];
        _initOperationDesc1();
        _initOperationDesc2();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("chequearExistenciaIdioma");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "idioma"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "chequearExistenciaIdiomaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("chequearIdiomaTesauro");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "identificador"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "idioma"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        oper.setReturnClass(java.lang.Boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "chequearIdiomaTesauroReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerJerarquia");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "nomTesauro"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "idioma"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "ArrayOfJerarquiaVO"));
        oper.setReturnClass(es.pode.fuentestaxonomicas.negocio.servicio.JerarquiaVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "obtenerJerarquiaReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerPrimerNivelTesauro");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "nomTesauro"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "idioma"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "ArrayOfTaxonVO"));
        oper.setReturnClass(es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "obtenerPrimerNivelTesauroReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerTerminos");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "texto"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "nomTesauro"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "idioma"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "ArrayOfJerarquiaVO"));
        oper.setReturnClass(es.pode.fuentestaxonomicas.negocio.servicio.JerarquiaVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "obtenerTerminosReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerTerminosRelacionadosPorId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "nomTesauro"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "idioma"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "ArrayOfTaxonVO"));
        oper.setReturnClass(es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "obtenerTerminosRelacionadosPorIdReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerTerminosRelacionadosPorTexto");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "textoTesauro"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "nomTesauro"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "idioma"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "ArrayOfTaxonVO"));
        oper.setReturnClass(es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "obtenerTerminosRelacionadosPorTextoReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerTerminosRelacionAsociativa");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "nomTesauro"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "idioma"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "ArrayOfTaxonVO"));
        oper.setReturnClass(es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "obtenerTerminosRelacionAsociativaReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerTerminosRelacionJerarquica");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "nomTesauro"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "idioma"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "ArrayOfTaxonVO"));
        oper.setReturnClass(es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "obtenerTerminosRelacionJerarquicaReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerTesauros");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "idioma"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "ArrayOfEstructuraVdexVO"));
        oper.setReturnClass(es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "obtenerTesaurosReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerTesauroVigente");
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "EstructuraVdexVO"));
        oper.setReturnClass(es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "obtenerTesauroVigenteReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerTextosDeIds");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "ids"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "ArrayOfxsd_string"), java.lang.String[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "item"));
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "idioma"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "nomTesauro"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "ArrayOfTaxonVO"));
        oper.setReturnClass(es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "obtenerTextosDeIdsReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerVocabName");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "nomTesauro"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "idioma"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "obtenerVocabNameReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerVocabNames");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "nomTesauros"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "ArrayOfxsd_string"), java.lang.String[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "item"));
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "idioma"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "ArrayOfxsd_string"));
        oper.setReturnClass(java.lang.String[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "obtenerVocabNamesReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[13] = oper;

    }

    public SrvTesaurosServicesSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public SrvTesaurosServicesSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public SrvTesaurosServicesSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "ArrayOfEstructuraVdexVO");
            cachedSerQNames.add(qName);
            cls = es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "EstructuraVdexVO");
            qName2 = new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "ArrayOfJerarquiaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.fuentestaxonomicas.negocio.servicio.JerarquiaVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "JerarquiaVO");
            qName2 = new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "ArrayOfTaxonVO");
            cachedSerQNames.add(qName);
            cls = es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "TaxonVO");
            qName2 = new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "ArrayOfxsd_string");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "EstructuraVdexVO");
            cachedSerQNames.add(qName);
            cls = es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "JerarquiaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.fuentestaxonomicas.negocio.servicio.JerarquiaVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "TaxonVO");
            cachedSerQNames.add(qName);
            cls = es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO.class;
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

    public boolean chequearExistenciaIdioma(java.lang.String idioma) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("chequearExistenciaIdioma");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "chequearExistenciaIdioma"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {idioma});

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

    public java.lang.Boolean chequearIdiomaTesauro(java.lang.String identificador, java.lang.String idioma) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("chequearIdiomaTesauro");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "chequearIdiomaTesauro"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {identificador, idioma});

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

    public es.pode.fuentestaxonomicas.negocio.servicio.JerarquiaVO[] obtenerJerarquia(java.lang.String id, java.lang.String nomTesauro, java.lang.String idioma) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerJerarquia");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "obtenerJerarquia"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {id, nomTesauro, idioma});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.fuentestaxonomicas.negocio.servicio.JerarquiaVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.fuentestaxonomicas.negocio.servicio.JerarquiaVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.fuentestaxonomicas.negocio.servicio.JerarquiaVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] obtenerPrimerNivelTesauro(java.lang.String nomTesauro, java.lang.String idioma) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerPrimerNivelTesauro");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "obtenerPrimerNivelTesauro"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {nomTesauro, idioma});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.fuentestaxonomicas.negocio.servicio.JerarquiaVO[] obtenerTerminos(java.lang.String texto, java.lang.String nomTesauro, java.lang.String idioma) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerTerminos");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "obtenerTerminos"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {texto, nomTesauro, idioma});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.fuentestaxonomicas.negocio.servicio.JerarquiaVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.fuentestaxonomicas.negocio.servicio.JerarquiaVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.fuentestaxonomicas.negocio.servicio.JerarquiaVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] obtenerTerminosRelacionadosPorId(java.lang.String id, java.lang.String nomTesauro, java.lang.String idioma) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerTerminosRelacionadosPorId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "obtenerTerminosRelacionadosPorId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {id, nomTesauro, idioma});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] obtenerTerminosRelacionadosPorTexto(java.lang.String textoTesauro, java.lang.String nomTesauro, java.lang.String idioma) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerTerminosRelacionadosPorTexto");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "obtenerTerminosRelacionadosPorTexto"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {textoTesauro, nomTesauro, idioma});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] obtenerTerminosRelacionAsociativa(java.lang.String id, java.lang.String nomTesauro, java.lang.String idioma) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerTerminosRelacionAsociativa");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "obtenerTerminosRelacionAsociativa"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {id, nomTesauro, idioma});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] obtenerTerminosRelacionJerarquica(java.lang.String id, java.lang.String nomTesauro, java.lang.String idioma) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerTerminosRelacionJerarquica");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "obtenerTerminosRelacionJerarquica"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {id, nomTesauro, idioma});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO[] obtenerTesauros(java.lang.String idioma) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerTesauros");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "obtenerTesauros"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {idioma});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO obtenerTesauroVigente() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerTesauroVigente");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "obtenerTesauroVigente"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] obtenerTextosDeIds(java.lang.String[] ids, java.lang.String idioma, java.lang.String nomTesauro) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerTextosDeIds");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "obtenerTextosDeIds"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {ids, idioma, nomTesauro});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String obtenerVocabName(java.lang.String nomTesauro, java.lang.String idioma) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerVocabName");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "obtenerVocabName"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {nomTesauro, idioma});

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

    public java.lang.String[] obtenerVocabNames(java.lang.String[] nomTesauros, java.lang.String idioma) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerVocabNames");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.negocio.fuentestaxonomicas.pode.es", "obtenerVocabNames"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {nomTesauros, idioma});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
