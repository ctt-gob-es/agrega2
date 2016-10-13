/**
 * SrvNoticiasServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.contenidos.negocio.noticias.servicio;

public class SrvNoticiasServiceSoapBindingStub extends org.apache.axis.client.Stub implements es.pode.contenidos.negocio.noticias.servicio.SrvNoticiasService {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[23];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("almacenarImagenNoticia");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "imagen"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "ImagenVO"), es.pode.contenidos.negocio.noticias.servicio.ImagenVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "almacenarImagenNoticiaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("crearCategoria");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "categoria"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "CategoriaNoticiaVO"), es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        oper.setReturnClass(java.lang.Long.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "crearCategoriaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("crearNoticia");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "noticia"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "NoticiaVO"), es.pode.contenidos.negocio.noticias.servicio.NoticiaVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        oper.setReturnClass(java.lang.Long.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "crearNoticiaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("eliminarCategoria");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"), java.lang.Long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("eliminarCategorias");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "idList"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "ArrayOfsoapenc_long"), java.lang.Long[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "item"));
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("eliminarNoticia");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"), java.lang.Long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("eliminarNoticias");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "idList"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "ArrayOfsoapenc_long"), java.lang.Long[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "item"));
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("limpiarCategoria");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"), java.lang.Long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("limpiarNoticia");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"), java.lang.Long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listarNoticiasActivasPorIdioma");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "idioma"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "ArrayOfNoticiaTraducidaVO"));
        oper.setReturnClass(es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "listarNoticiasActivasPorIdiomaReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("modificarCategoria");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "categoria"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "CategoriaNoticiaVO"), es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("modificarNoticia");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "noticia"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "NoticiaVO"), es.pode.contenidos.negocio.noticias.servicio.NoticiaVO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerCategoria");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"), java.lang.Long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "CategoriaNoticiaVO"));
        oper.setReturnClass(es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "obtenerCategoriaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerCategorias");
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "ArrayOfCategoriaNoticiaVO"));
        oper.setReturnClass(es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "obtenerCategoriasReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerCategoriasTraducidas");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "idiomasTraducibles"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "ArrayOfxsd_string"), java.lang.String[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "item"));
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "ArrayOfCategoriaNoticiaTraducidaVO"));
        oper.setReturnClass(es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaTraducidaVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "obtenerCategoriasTraducidasReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerCategoriaTraducida");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"), java.lang.Long.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "idioma"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "CategoriaNoticiaTraducidaVO"));
        oper.setReturnClass(es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaTraducidaVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "obtenerCategoriaTraducidaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerNoticia");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"), java.lang.Long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "NoticiaVO"));
        oper.setReturnClass(es.pode.contenidos.negocio.noticias.servicio.NoticiaVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "obtenerNoticiaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerNoticias");
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "ArrayOfNoticiaVO"));
        oper.setReturnClass(es.pode.contenidos.negocio.noticias.servicio.NoticiaVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "obtenerNoticiasReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerNoticiasActivas");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "numResultados"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"), java.lang.Integer.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "ArrayOfNoticiaVO"));
        oper.setReturnClass(es.pode.contenidos.negocio.noticias.servicio.NoticiaVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "obtenerNoticiasActivasReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerNoticiasActivasPorIdiomayCategoria");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "idioma"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"), java.lang.Long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "ArrayOfNoticiaTraducidaVO"));
        oper.setReturnClass(es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "obtenerNoticiasActivasPorIdiomayCategoriaReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerNoticiasFromIdCategoria");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "idCategoria"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"), java.lang.Long.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "idiomasTraducibles"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "ArrayOfxsd_string"), java.lang.String[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "item"));
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "ArrayOfNoticiaTraducidaVO"));
        oper.setReturnClass(es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "obtenerNoticiasFromIdCategoriaReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[20] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerNoticiasTraducidas");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "idiomasTraducibles"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "ArrayOfxsd_string"), java.lang.String[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "item"));
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "ArrayOfNoticiaTraducidaVO"));
        oper.setReturnClass(es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "obtenerNoticiasTraducidasReturn"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "item"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[21] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerNoticiaTraducida");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"), java.lang.Long.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "idioma"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "NoticiaTraducidaVO"));
        oper.setReturnClass(es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "obtenerNoticiaTraducidaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[22] = oper;

    }

    public SrvNoticiasServiceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public SrvNoticiasServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public SrvNoticiasServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "ArrayOfCategoriaIdiomaNoticiaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.contenidos.negocio.noticias.servicio.CategoriaIdiomaNoticiaVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "CategoriaIdiomaNoticiaVO");
            qName2 = new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "ArrayOfCategoriaNoticiaTraducidaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaTraducidaVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "CategoriaNoticiaTraducidaVO");
            qName2 = new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "ArrayOfCategoriaNoticiaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "CategoriaNoticiaVO");
            qName2 = new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "ArrayOfDescripcionNoticiaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.contenidos.negocio.noticias.servicio.DescripcionNoticiaVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "DescripcionNoticiaVO");
            qName2 = new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "ArrayOfNoticiaTraducidaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "NoticiaTraducidaVO");
            qName2 = new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "ArrayOfNoticiaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.contenidos.negocio.noticias.servicio.NoticiaVO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "NoticiaVO");
            qName2 = new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "ArrayOfsoapenc_long");
            cachedSerQNames.add(qName);
            cls = java.lang.Long[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long");
            qName2 = new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "ArrayOfxsd_string");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "CategoriaIdiomaNoticiaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.contenidos.negocio.noticias.servicio.CategoriaIdiomaNoticiaVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "CategoriaNoticiaTraducidaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaTraducidaVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "CategoriaNoticiaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "DescripcionNoticiaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.contenidos.negocio.noticias.servicio.DescripcionNoticiaVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "ImagenVO");
            cachedSerQNames.add(qName);
            cls = es.pode.contenidos.negocio.noticias.servicio.ImagenVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "NoticiaTraducidaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "NoticiaVO");
            cachedSerQNames.add(qName);
            cls = es.pode.contenidos.negocio.noticias.servicio.NoticiaVO.class;
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

    public java.lang.String almacenarImagenNoticia(es.pode.contenidos.negocio.noticias.servicio.ImagenVO imagen) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("almacenarImagenNoticia");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "almacenarImagenNoticia"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {imagen});

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

    public java.lang.Long crearCategoria(es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO categoria) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("crearCategoria");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "crearCategoria"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {categoria});

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

    public java.lang.Long crearNoticia(es.pode.contenidos.negocio.noticias.servicio.NoticiaVO noticia) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("crearNoticia");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "crearNoticia"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {noticia});

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

    public void eliminarCategoria(java.lang.Long id) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eliminarCategoria");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "eliminarCategoria"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {id});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void eliminarCategorias(java.lang.Long[] idList) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eliminarCategorias");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "eliminarCategorias"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {idList});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void eliminarNoticia(java.lang.Long id) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eliminarNoticia");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "eliminarNoticia"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {id});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void eliminarNoticias(java.lang.Long[] idList) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eliminarNoticias");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "eliminarNoticias"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {idList});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void limpiarCategoria(java.lang.Long id) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("limpiarCategoria");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "limpiarCategoria"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {id});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void limpiarNoticia(java.lang.Long id) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("limpiarNoticia");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "limpiarNoticia"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {id});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO[] listarNoticiasActivasPorIdioma(java.lang.String idioma) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("listarNoticiasActivasPorIdioma");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "listarNoticiasActivasPorIdioma"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {idioma});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void modificarCategoria(es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO categoria) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("modificarCategoria");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "modificarCategoria"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {categoria});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void modificarNoticia(es.pode.contenidos.negocio.noticias.servicio.NoticiaVO noticia) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("modificarNoticia");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "modificarNoticia"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {noticia});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO obtenerCategoria(java.lang.Long id) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerCategoria");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "obtenerCategoria"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {id});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO[] obtenerCategorias() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerCategorias");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "obtenerCategorias"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaTraducidaVO[] obtenerCategoriasTraducidas(java.lang.String[] idiomasTraducibles) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerCategoriasTraducidas");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "obtenerCategoriasTraducidas"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {idiomasTraducibles});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaTraducidaVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaTraducidaVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaTraducidaVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaTraducidaVO obtenerCategoriaTraducida(java.lang.Long id, java.lang.String idioma) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerCategoriaTraducida");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "obtenerCategoriaTraducida"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {id, idioma});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaTraducidaVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaTraducidaVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaTraducidaVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.contenidos.negocio.noticias.servicio.NoticiaVO obtenerNoticia(java.lang.Long id) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerNoticia");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "obtenerNoticia"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {id});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.contenidos.negocio.noticias.servicio.NoticiaVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.contenidos.negocio.noticias.servicio.NoticiaVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.contenidos.negocio.noticias.servicio.NoticiaVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.contenidos.negocio.noticias.servicio.NoticiaVO[] obtenerNoticias() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerNoticias");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "obtenerNoticias"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.contenidos.negocio.noticias.servicio.NoticiaVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.contenidos.negocio.noticias.servicio.NoticiaVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.contenidos.negocio.noticias.servicio.NoticiaVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.contenidos.negocio.noticias.servicio.NoticiaVO[] obtenerNoticiasActivas(java.lang.Integer numResultados) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerNoticiasActivas");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "obtenerNoticiasActivas"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {numResultados});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.contenidos.negocio.noticias.servicio.NoticiaVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.contenidos.negocio.noticias.servicio.NoticiaVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.contenidos.negocio.noticias.servicio.NoticiaVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO[] obtenerNoticiasActivasPorIdiomayCategoria(java.lang.String idioma, java.lang.Long id) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerNoticiasActivasPorIdiomayCategoria");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "obtenerNoticiasActivasPorIdiomayCategoria"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {idioma, id});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO[] obtenerNoticiasFromIdCategoria(java.lang.Long idCategoria, java.lang.String[] idiomasTraducibles) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[20]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerNoticiasFromIdCategoria");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "obtenerNoticiasFromIdCategoria"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {idCategoria, idiomasTraducibles});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO[] obtenerNoticiasTraducidas(java.lang.String[] idiomasTraducibles) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[21]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerNoticiasTraducidas");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "obtenerNoticiasTraducidas"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {idiomasTraducibles});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO[]) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO obtenerNoticiaTraducida(java.lang.Long id, java.lang.String idioma) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[22]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("obtenerNoticiaTraducida");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servicio.noticias.negocio.contenidos.pode.es", "obtenerNoticiaTraducida"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {id, idioma});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO) _resp;
            } catch (java.lang.Exception _exception) {
                return (es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO) org.apache.axis.utils.JavaUtils.convert(_resp, es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
