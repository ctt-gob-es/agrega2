/**
 * SrvCatalogacionAvanzadaServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.catalogacion.negocio.servicios;

public class SrvCatalogacionAvanzadaServiceSoapBindingStub extends org.apache.axis.client.Stub implements es.pode.catalogacion.negocio.servicios.SrvCatalogacionAvanzadaService {
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
        oper.setName("cargarObjLom");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "identificador"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "usuario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "lom"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://xml.apache.org/xml-soap", "DataHandler"), javax.activation.DataHandler.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "cargarObjLomReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("eliminarObjLoms");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ids"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ArrayOfxsd_string"), java.lang.String[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item"));
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("exportarLomes");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "identificador"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "usuario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "lomAvanzado"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "LomAvanzadoVO"), es.pode.catalogacion.negocio.servicios.LomAvanzadoVO.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "idioma"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://xml.apache.org/xml-soap", "DataHandler"));
        oper.setReturnClass(javax.activation.DataHandler.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "exportarLomesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("exportarLomesDesdeLom");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "identificador"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "usuario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "idioma"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "lom"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://xml.apache.org/xml-soap", "DataHandler"), javax.activation.DataHandler.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://xml.apache.org/xml-soap", "DataHandler"));
        oper.setReturnClass(javax.activation.DataHandler.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "exportarLomesDesdeLomReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("generarMetadatos");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "identificador"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "usuario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "lomAvanzado"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "LomAvanzadoVO"), es.pode.catalogacion.negocio.servicios.LomAvanzadoVO.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "idioma"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "LomAvanzadoVO"));
        oper.setReturnClass(es.pode.catalogacion.negocio.servicios.LomAvanzadoVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "generarMetadatosReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("importarLomes");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ficheroLomes"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://xml.apache.org/xml-soap", "DataHandler"), javax.activation.DataHandler.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "LomAvanzadoVO"));
        oper.setReturnClass(es.pode.catalogacion.negocio.servicios.LomAvanzadoVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "importarLomesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerLomAvanzado");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "identificador"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "usuario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "idioma"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "LomAvanzadoVO"));
        oper.setReturnClass(es.pode.catalogacion.negocio.servicios.LomAvanzadoVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "obtenerLomAvanzadoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerObjLoms");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ids"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ArrayOfxsd_string"), java.lang.String[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item"));
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ArrayOfIdLomVO"));
        oper.setReturnClass(es.pode.catalogacion.negocio.servicios.IdLomVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "obtenerObjLomsReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("traducirLomAvanzado");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "identificador"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "usuario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "idiomaTrad"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "lomAv"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "LomAvanzadoVO"), es.pode.catalogacion.negocio.servicios.LomAvanzadoVO.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "idiomaOri"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "soloCoincidentes"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "LomAvanzadoVO"));
        oper.setReturnClass(es.pode.catalogacion.negocio.servicios.LomAvanzadoVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "traducirLomAvanzadoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

    }

    public SrvCatalogacionAvanzadaServiceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public SrvCatalogacionAvanzadaServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public SrvCatalogacionAvanzadaServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AccesoVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.AccesoVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AgregadorORVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.AgregadorORVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AmbitoVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.AmbitoVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ArrayOfAgregadorORVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.AgregadorORVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AgregadorORVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ArrayOfAmbitoVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.AmbitoVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AmbitoVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ArrayOfAvAnnotationVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.AvAnnotationVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AvAnnotationVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ArrayOfAvClassificationVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.AvClassificationVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AvClassificationVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ArrayOfAvEducationalVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.AvEducationalVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AvEducationalVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ArrayOfAvRelationVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.AvRelationVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AvRelationVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ArrayOfContribucionVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.ContribucionVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ContribucionVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ArrayOfDescripcionVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.DescripcionVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "DescripcionVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ArrayOfEntidadVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.EntidadVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "EntidadVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ArrayOfEsquemaDeMetadatosVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.EsquemaDeMetadatosVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "EsquemaDeMetadatosVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ArrayOfFormatoVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.FormatoVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "FormatoVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ArrayOfIdentificadorVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.IdentificadorVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "IdentificadorVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ArrayOfIdiomaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.IdiomaVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "IdiomaVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ArrayOfIdLomVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.IdLomVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "IdLomVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ArrayOfLangStringVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.LangStringVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "LangStringVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ArrayOfLocalizacionVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.LocalizacionVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "LocalizacionVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ArrayOfPalabraClaveVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.PalabraClaveVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "PalabraClaveVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ArrayOfRangoEdadVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.RangoEdadVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "RangoEdadVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ArrayOfRequisitoVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.RequisitoVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "RequisitoVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ArrayOfRutaTaxonomicaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.RutaTaxonomicaVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "RutaTaxonomicaVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ArrayOfSourceValueVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.SourceValueVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "SourceValueVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ArrayOfTaxonVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.TaxonVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "TaxonVO");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ArrayOfxsd_string");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AvAnnotationVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.AvAnnotationVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AvClassificationVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.AvClassificationVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AvEducationalVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.AvEducationalVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AvGeneralVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.AvGeneralVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AvLifeCycleVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.AvLifeCycleVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AvMetametadataVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.AvMetametadataVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AvRelationVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.AvRelationVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AvRightsVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.AvRightsVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "AvTechnicalVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.AvTechnicalVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "ContribucionVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.ContribucionVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "DescripcionVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.DescripcionVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "DuracionVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.DuracionVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "EntidadVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.EntidadVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "EntryVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.EntryVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "EsquemaDeMetadatosVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.EsquemaDeMetadatosVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "FechaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.FechaVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "FormatoVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.FormatoVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "FuenteVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.FuenteVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "IdentificadorVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.IdentificadorVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "IdiomaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.IdiomaVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "IdLomVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.IdLomVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "IdVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.IdVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "LangStringVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.LangStringVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "LocalizacionVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.LocalizacionVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "LomAvanzadoVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.LomAvanzadoVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "OtrosRequisitosVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.OtrosRequisitosVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "PalabraClaveVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.PalabraClaveVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "PautasInstalacionVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.PautasInstalacionVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "RangoEdadVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.RangoEdadVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "RecursoVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.RecursoVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "RequisitoVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.RequisitoVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "RutaTaxonomicaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.RutaTaxonomicaVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "SourceValueVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.SourceValueVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "TamanioVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.TamanioVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "TaxonVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.TaxonVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "TituloVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.TituloVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "VersionMaxVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.VersionMaxVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "VersionMinVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.VersionMinVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "VersionVO");
            cachedSerQNames.add(qName);
            cls = es.pode.catalogacion.negocio.servicios.VersionVO.class;
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

    public java.lang.String cargarObjLom(java.lang.String identificador, java.lang.String usuario, javax.activation.DataHandler lom) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("cargarObjLom");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "cargarObjLom"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {identificador, usuario, lom});

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

    public void eliminarObjLoms(java.lang.String[] ids) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eliminarObjLoms");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "eliminarObjLoms"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {ids});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public javax.activation.DataHandler exportarLomes(java.lang.String identificador, java.lang.String usuario, es.pode.catalogacion.negocio.servicios.LomAvanzadoVO lomAvanzado, java.lang.String idioma) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("exportarLomes");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "exportarLomes"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {identificador, usuario, lomAvanzado, idioma});

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

    public javax.activation.DataHandler exportarLomesDesdeLom(java.lang.String identificador, java.lang.String usuario, java.lang.String idioma, javax.activation.DataHandler lom) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("exportarLomesDesdeLom");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "exportarLomesDesdeLom"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {identificador, usuario, idioma, lom});

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

    public es.pode.catalogacion.negocio.servicios.LomAvanzadoVO generarMetadatos(java.lang.String identificador, java.lang.String usuario, es.pode.catalogacion.negocio.servicios.LomAvanzadoVO lomAvanzado, java.lang.String idioma) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("generarMetadatos");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "generarMetadatos"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {identificador, usuario, lomAvanzado, idioma});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.catalogacion.negocio.servicios.LomAvanzadoVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.catalogacion.negocio.servicios.LomAvanzadoVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.catalogacion.negocio.servicios.LomAvanzadoVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.catalogacion.negocio.servicios.LomAvanzadoVO importarLomes(javax.activation.DataHandler ficheroLomes) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("importarLomes");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "importarLomes"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {ficheroLomes});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.catalogacion.negocio.servicios.LomAvanzadoVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.catalogacion.negocio.servicios.LomAvanzadoVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.catalogacion.negocio.servicios.LomAvanzadoVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.catalogacion.negocio.servicios.LomAvanzadoVO obtenerLomAvanzado(java.lang.String identificador, java.lang.String usuario, java.lang.String idioma) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerLomAvanzado");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "obtenerLomAvanzado"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {identificador, usuario, idioma});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.catalogacion.negocio.servicios.LomAvanzadoVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.catalogacion.negocio.servicios.LomAvanzadoVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.catalogacion.negocio.servicios.LomAvanzadoVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.catalogacion.negocio.servicios.IdLomVO[] obtenerObjLoms(java.lang.String[] ids) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerObjLoms");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "obtenerObjLoms"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {ids});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.catalogacion.negocio.servicios.IdLomVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.catalogacion.negocio.servicios.IdLomVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.catalogacion.negocio.servicios.IdLomVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.catalogacion.negocio.servicios.LomAvanzadoVO traducirLomAvanzado(java.lang.String identificador, java.lang.String usuario, java.lang.String idiomaTrad, es.pode.catalogacion.negocio.servicios.LomAvanzadoVO lomAv, java.lang.String idiomaOri, boolean soloCoincidentes) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("traducirLomAvanzado");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicios.negocio.catalogacion.pode.es", "traducirLomAvanzado"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {identificador, usuario, idiomaTrad, lomAv, idiomaOri, new java.lang.Boolean(soloCoincidentes)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.catalogacion.negocio.servicios.LomAvanzadoVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.catalogacion.negocio.servicios.LomAvanzadoVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.catalogacion.negocio.servicios.LomAvanzadoVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
