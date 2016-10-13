/**
 * OdeVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.empaquetador.negocio.servicio;


/**
 * Objeto VO que representa un objeto Manifest de SCORM2004
 *                         simplificado. Para mapear objetos Manifest
 * a OdeVO y viceversa
 *                         se emplea la librería Dozer. La configuración
 * del mapeo se
 *                         define en un fichero de configuracion de Dozer.
 */
public class OdeVO  implements java.io.Serializable {
    /* Identificador del Ode. Se corresponde con el atributo identifier
     * de la clase Manifest. */
    private java.lang.String identifier;

    /* Ruta relativa en la que se almacena el OdeVO. Toma valor cuando
     * el OdeVO es un Submanifiesto. Se corresponde con el atributo
     *                                 base de la clase Manifest. */
    private java.lang.String ruta;

    /* Código MEC del Ode en edicion. */
    private java.lang.String codigoMEC;

    /* Identificador de la organización principal del OdeVO. Se
     *                                 corresponde con el atributo default
     * de la clase Organizations. */
    private java.lang.String organizacionPrincipal;

    /* Variable booleana para marcar cuando un ODE ha sido catalogado. */
    private java.lang.Boolean catalogado;

    /* URL de la ruta base del Ode. */
    private java.lang.String localizadorURL;

    /* Identificador del usuario propietario del ODE. */
    private java.lang.String usuario;

    private java.lang.Boolean conSecuencia;

    /* Array de submanifiestos (OdeVO) agregados al OdeVO. Se
     *                                 corresponde con el atributo manifest
     * de la clase Manifest. */
    private es.pode.empaquetador.negocio.servicio.OdeVO[] submanifiestos;

    /* Array de organizaciones contenidas en el OdeVO. Se corresponde
     * con el atributo organization de la clase Organizations. */
    private es.pode.empaquetador.negocio.servicio.OrganizacionVO[] organizaciones;

    /* Array de recursos del OdeVO. Se corresponde con el atributo
     * resource de la clase Resources. */
    private es.pode.empaquetador.negocio.servicio.RecursoVO[] recursos;

    /* Metadatos del Manifest. Se corresponde con el atributo metadata
     * de la clase Manifest. */
    private es.pode.empaquetador.negocio.servicio.MetadataVO metadata;

    /* Lom asociado al OdeVO. Se corresponde con el objeto Lom
     *                                 contenido en la clase Metadata del
     * Manifest. */
    private es.pode.empaquetador.negocio.servicio.LomVO lom;

    public OdeVO() {
    }

    public OdeVO(
           java.lang.String identifier,
           java.lang.String ruta,
           java.lang.String codigoMEC,
           java.lang.String organizacionPrincipal,
           java.lang.Boolean catalogado,
           java.lang.String localizadorURL,
           java.lang.String usuario,
           java.lang.Boolean conSecuencia,
           es.pode.empaquetador.negocio.servicio.OdeVO[] submanifiestos,
           es.pode.empaquetador.negocio.servicio.OrganizacionVO[] organizaciones,
           es.pode.empaquetador.negocio.servicio.RecursoVO[] recursos,
           es.pode.empaquetador.negocio.servicio.MetadataVO metadata,
           es.pode.empaquetador.negocio.servicio.LomVO lom) {
           this.identifier = identifier;
           this.ruta = ruta;
           this.codigoMEC = codigoMEC;
           this.organizacionPrincipal = organizacionPrincipal;
           this.catalogado = catalogado;
           this.localizadorURL = localizadorURL;
           this.usuario = usuario;
           this.conSecuencia = conSecuencia;
           this.submanifiestos = submanifiestos;
           this.organizaciones = organizaciones;
           this.recursos = recursos;
           this.metadata = metadata;
           this.lom = lom;
    }


    /**
     * Gets the identifier value for this OdeVO.
     * 
     * @return identifier   * Identificador del Ode. Se corresponde con el atributo identifier
     * de la clase Manifest.
     */
    public java.lang.String getIdentifier() {
        return identifier;
    }


    /**
     * Sets the identifier value for this OdeVO.
     * 
     * @param identifier   * Identificador del Ode. Se corresponde con el atributo identifier
     * de la clase Manifest.
     */
    public void setIdentifier(java.lang.String identifier) {
        this.identifier = identifier;
    }


    /**
     * Gets the ruta value for this OdeVO.
     * 
     * @return ruta   * Ruta relativa en la que se almacena el OdeVO. Toma valor cuando
     * el OdeVO es un Submanifiesto. Se corresponde con el atributo
     *                                 base de la clase Manifest.
     */
    public java.lang.String getRuta() {
        return ruta;
    }


    /**
     * Sets the ruta value for this OdeVO.
     * 
     * @param ruta   * Ruta relativa en la que se almacena el OdeVO. Toma valor cuando
     * el OdeVO es un Submanifiesto. Se corresponde con el atributo
     *                                 base de la clase Manifest.
     */
    public void setRuta(java.lang.String ruta) {
        this.ruta = ruta;
    }


    /**
     * Gets the codigoMEC value for this OdeVO.
     * 
     * @return codigoMEC   * Código MEC del Ode en edicion.
     */
    public java.lang.String getCodigoMEC() {
        return codigoMEC;
    }


    /**
     * Sets the codigoMEC value for this OdeVO.
     * 
     * @param codigoMEC   * Código MEC del Ode en edicion.
     */
    public void setCodigoMEC(java.lang.String codigoMEC) {
        this.codigoMEC = codigoMEC;
    }


    /**
     * Gets the organizacionPrincipal value for this OdeVO.
     * 
     * @return organizacionPrincipal   * Identificador de la organización principal del OdeVO. Se
     *                                 corresponde con el atributo default
     * de la clase Organizations.
     */
    public java.lang.String getOrganizacionPrincipal() {
        return organizacionPrincipal;
    }


    /**
     * Sets the organizacionPrincipal value for this OdeVO.
     * 
     * @param organizacionPrincipal   * Identificador de la organización principal del OdeVO. Se
     *                                 corresponde con el atributo default
     * de la clase Organizations.
     */
    public void setOrganizacionPrincipal(java.lang.String organizacionPrincipal) {
        this.organizacionPrincipal = organizacionPrincipal;
    }


    /**
     * Gets the catalogado value for this OdeVO.
     * 
     * @return catalogado   * Variable booleana para marcar cuando un ODE ha sido catalogado.
     */
    public java.lang.Boolean getCatalogado() {
        return catalogado;
    }


    /**
     * Sets the catalogado value for this OdeVO.
     * 
     * @param catalogado   * Variable booleana para marcar cuando un ODE ha sido catalogado.
     */
    public void setCatalogado(java.lang.Boolean catalogado) {
        this.catalogado = catalogado;
    }


    /**
     * Gets the localizadorURL value for this OdeVO.
     * 
     * @return localizadorURL   * URL de la ruta base del Ode.
     */
    public java.lang.String getLocalizadorURL() {
        return localizadorURL;
    }


    /**
     * Sets the localizadorURL value for this OdeVO.
     * 
     * @param localizadorURL   * URL de la ruta base del Ode.
     */
    public void setLocalizadorURL(java.lang.String localizadorURL) {
        this.localizadorURL = localizadorURL;
    }


    /**
     * Gets the usuario value for this OdeVO.
     * 
     * @return usuario   * Identificador del usuario propietario del ODE.
     */
    public java.lang.String getUsuario() {
        return usuario;
    }


    /**
     * Sets the usuario value for this OdeVO.
     * 
     * @param usuario   * Identificador del usuario propietario del ODE.
     */
    public void setUsuario(java.lang.String usuario) {
        this.usuario = usuario;
    }


    /**
     * Gets the conSecuencia value for this OdeVO.
     * 
     * @return conSecuencia
     */
    public java.lang.Boolean getConSecuencia() {
        return conSecuencia;
    }


    /**
     * Sets the conSecuencia value for this OdeVO.
     * 
     * @param conSecuencia
     */
    public void setConSecuencia(java.lang.Boolean conSecuencia) {
        this.conSecuencia = conSecuencia;
    }


    /**
     * Gets the submanifiestos value for this OdeVO.
     * 
     * @return submanifiestos   * Array de submanifiestos (OdeVO) agregados al OdeVO. Se
     *                                 corresponde con el atributo manifest
     * de la clase Manifest.
     */
    public es.pode.empaquetador.negocio.servicio.OdeVO[] getSubmanifiestos() {
        return submanifiestos;
    }


    /**
     * Sets the submanifiestos value for this OdeVO.
     * 
     * @param submanifiestos   * Array de submanifiestos (OdeVO) agregados al OdeVO. Se
     *                                 corresponde con el atributo manifest
     * de la clase Manifest.
     */
    public void setSubmanifiestos(es.pode.empaquetador.negocio.servicio.OdeVO[] submanifiestos) {
        this.submanifiestos = submanifiestos;
    }


    /**
     * Gets the organizaciones value for this OdeVO.
     * 
     * @return organizaciones   * Array de organizaciones contenidas en el OdeVO. Se corresponde
     * con el atributo organization de la clase Organizations.
     */
    public es.pode.empaquetador.negocio.servicio.OrganizacionVO[] getOrganizaciones() {
        return organizaciones;
    }


    /**
     * Sets the organizaciones value for this OdeVO.
     * 
     * @param organizaciones   * Array de organizaciones contenidas en el OdeVO. Se corresponde
     * con el atributo organization de la clase Organizations.
     */
    public void setOrganizaciones(es.pode.empaquetador.negocio.servicio.OrganizacionVO[] organizaciones) {
        this.organizaciones = organizaciones;
    }


    /**
     * Gets the recursos value for this OdeVO.
     * 
     * @return recursos   * Array de recursos del OdeVO. Se corresponde con el atributo
     * resource de la clase Resources.
     */
    public es.pode.empaquetador.negocio.servicio.RecursoVO[] getRecursos() {
        return recursos;
    }


    /**
     * Sets the recursos value for this OdeVO.
     * 
     * @param recursos   * Array de recursos del OdeVO. Se corresponde con el atributo
     * resource de la clase Resources.
     */
    public void setRecursos(es.pode.empaquetador.negocio.servicio.RecursoVO[] recursos) {
        this.recursos = recursos;
    }


    /**
     * Gets the metadata value for this OdeVO.
     * 
     * @return metadata   * Metadatos del Manifest. Se corresponde con el atributo metadata
     * de la clase Manifest.
     */
    public es.pode.empaquetador.negocio.servicio.MetadataVO getMetadata() {
        return metadata;
    }


    /**
     * Sets the metadata value for this OdeVO.
     * 
     * @param metadata   * Metadatos del Manifest. Se corresponde con el atributo metadata
     * de la clase Manifest.
     */
    public void setMetadata(es.pode.empaquetador.negocio.servicio.MetadataVO metadata) {
        this.metadata = metadata;
    }


    /**
     * Gets the lom value for this OdeVO.
     * 
     * @return lom   * Lom asociado al OdeVO. Se corresponde con el objeto Lom
     *                                 contenido en la clase Metadata del
     * Manifest.
     */
    public es.pode.empaquetador.negocio.servicio.LomVO getLom() {
        return lom;
    }


    /**
     * Sets the lom value for this OdeVO.
     * 
     * @param lom   * Lom asociado al OdeVO. Se corresponde con el objeto Lom
     *                                 contenido en la clase Metadata del
     * Manifest.
     */
    public void setLom(es.pode.empaquetador.negocio.servicio.LomVO lom) {
        this.lom = lom;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OdeVO)) return false;
        OdeVO other = (OdeVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.identifier==null && other.getIdentifier()==null) || 
             (this.identifier!=null &&
              this.identifier.equals(other.getIdentifier()))) &&
            ((this.ruta==null && other.getRuta()==null) || 
             (this.ruta!=null &&
              this.ruta.equals(other.getRuta()))) &&
            ((this.codigoMEC==null && other.getCodigoMEC()==null) || 
             (this.codigoMEC!=null &&
              this.codigoMEC.equals(other.getCodigoMEC()))) &&
            ((this.organizacionPrincipal==null && other.getOrganizacionPrincipal()==null) || 
             (this.organizacionPrincipal!=null &&
              this.organizacionPrincipal.equals(other.getOrganizacionPrincipal()))) &&
            ((this.catalogado==null && other.getCatalogado()==null) || 
             (this.catalogado!=null &&
              this.catalogado.equals(other.getCatalogado()))) &&
            ((this.localizadorURL==null && other.getLocalizadorURL()==null) || 
             (this.localizadorURL!=null &&
              this.localizadorURL.equals(other.getLocalizadorURL()))) &&
            ((this.usuario==null && other.getUsuario()==null) || 
             (this.usuario!=null &&
              this.usuario.equals(other.getUsuario()))) &&
            ((this.conSecuencia==null && other.getConSecuencia()==null) || 
             (this.conSecuencia!=null &&
              this.conSecuencia.equals(other.getConSecuencia()))) &&
            ((this.submanifiestos==null && other.getSubmanifiestos()==null) || 
             (this.submanifiestos!=null &&
              java.util.Arrays.equals(this.submanifiestos, other.getSubmanifiestos()))) &&
            ((this.organizaciones==null && other.getOrganizaciones()==null) || 
             (this.organizaciones!=null &&
              java.util.Arrays.equals(this.organizaciones, other.getOrganizaciones()))) &&
            ((this.recursos==null && other.getRecursos()==null) || 
             (this.recursos!=null &&
              java.util.Arrays.equals(this.recursos, other.getRecursos()))) &&
            ((this.metadata==null && other.getMetadata()==null) || 
             (this.metadata!=null &&
              this.metadata.equals(other.getMetadata()))) &&
            ((this.lom==null && other.getLom()==null) || 
             (this.lom!=null &&
              this.lom.equals(other.getLom())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getIdentifier() != null) {
            _hashCode += getIdentifier().hashCode();
        }
        if (getRuta() != null) {
            _hashCode += getRuta().hashCode();
        }
        if (getCodigoMEC() != null) {
            _hashCode += getCodigoMEC().hashCode();
        }
        if (getOrganizacionPrincipal() != null) {
            _hashCode += getOrganizacionPrincipal().hashCode();
        }
        if (getCatalogado() != null) {
            _hashCode += getCatalogado().hashCode();
        }
        if (getLocalizadorURL() != null) {
            _hashCode += getLocalizadorURL().hashCode();
        }
        if (getUsuario() != null) {
            _hashCode += getUsuario().hashCode();
        }
        if (getConSecuencia() != null) {
            _hashCode += getConSecuencia().hashCode();
        }
        if (getSubmanifiestos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSubmanifiestos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSubmanifiestos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getOrganizaciones() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOrganizaciones());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOrganizaciones(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRecursos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRecursos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRecursos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMetadata() != null) {
            _hashCode += getMetadata().hashCode();
        }
        if (getLom() != null) {
            _hashCode += getLom().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OdeVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "OdeVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identifier");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "identifier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ruta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "ruta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoMEC");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "codigoMEC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("organizacionPrincipal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "organizacionPrincipal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("catalogado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "catalogado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("localizadorURL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "localizadorURL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "usuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conSecuencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "conSecuencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("submanifiestos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "submanifiestos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "OdeVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("organizaciones");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "organizaciones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "OrganizacionVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recursos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "recursos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "RecursoVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("metadata");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "metadata"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "MetadataVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lom");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "lom"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "LomVO"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
