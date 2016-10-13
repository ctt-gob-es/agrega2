/**
 * OrganizacionVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.empaquetador.negocio.servicio;


/**
 * Objeto que representa una orgacización de un manifiesto
 *                         SCORM2004. Esta clase representa a un objeto
 * Organization.
 */
public class OrganizacionVO  implements java.io.Serializable {
    /* Título de la organización. Representa el atributo title de
     * la
     *                                 clase Organization. */
    private java.lang.String title;

    /* Identificador de la organizacion. Representa el atributo
     *                                 identifier de la clase Organization. */
    private java.lang.String identifier;

    /* Conjunto de grupos contenidos en la organizacion. Se corresponde
     * con el atributo Item de la clase Organization. */
    private es.pode.empaquetador.negocio.servicio.GrupoVO[] grupos;

    /* Secuencia asociada a la organizacion. Para obtener la secuencia
     * de la clase Organizacion es necesario extraerla de array
     *                                 organization.getGrp_any().getAny_object(). */
    private es.pode.empaquetador.negocio.servicio.SecuenciaVO[] secuencia;

    private es.pode.empaquetador.negocio.servicio.LomVO lom;

    public OrganizacionVO() {
    }

    public OrganizacionVO(
           java.lang.String title,
           java.lang.String identifier,
           es.pode.empaquetador.negocio.servicio.GrupoVO[] grupos,
           es.pode.empaquetador.negocio.servicio.SecuenciaVO[] secuencia,
           es.pode.empaquetador.negocio.servicio.LomVO lom) {
           this.title = title;
           this.identifier = identifier;
           this.grupos = grupos;
           this.secuencia = secuencia;
           this.lom = lom;
    }


    /**
     * Gets the title value for this OrganizacionVO.
     * 
     * @return title   * Título de la organización. Representa el atributo title de
     * la
     *                                 clase Organization.
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this OrganizacionVO.
     * 
     * @param title   * Título de la organización. Representa el atributo title de
     * la
     *                                 clase Organization.
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }


    /**
     * Gets the identifier value for this OrganizacionVO.
     * 
     * @return identifier   * Identificador de la organizacion. Representa el atributo
     *                                 identifier de la clase Organization.
     */
    public java.lang.String getIdentifier() {
        return identifier;
    }


    /**
     * Sets the identifier value for this OrganizacionVO.
     * 
     * @param identifier   * Identificador de la organizacion. Representa el atributo
     *                                 identifier de la clase Organization.
     */
    public void setIdentifier(java.lang.String identifier) {
        this.identifier = identifier;
    }


    /**
     * Gets the grupos value for this OrganizacionVO.
     * 
     * @return grupos   * Conjunto de grupos contenidos en la organizacion. Se corresponde
     * con el atributo Item de la clase Organization.
     */
    public es.pode.empaquetador.negocio.servicio.GrupoVO[] getGrupos() {
        return grupos;
    }


    /**
     * Sets the grupos value for this OrganizacionVO.
     * 
     * @param grupos   * Conjunto de grupos contenidos en la organizacion. Se corresponde
     * con el atributo Item de la clase Organization.
     */
    public void setGrupos(es.pode.empaquetador.negocio.servicio.GrupoVO[] grupos) {
        this.grupos = grupos;
    }


    /**
     * Gets the secuencia value for this OrganizacionVO.
     * 
     * @return secuencia   * Secuencia asociada a la organizacion. Para obtener la secuencia
     * de la clase Organizacion es necesario extraerla de array
     *                                 organization.getGrp_any().getAny_object().
     */
    public es.pode.empaquetador.negocio.servicio.SecuenciaVO[] getSecuencia() {
        return secuencia;
    }


    /**
     * Sets the secuencia value for this OrganizacionVO.
     * 
     * @param secuencia   * Secuencia asociada a la organizacion. Para obtener la secuencia
     * de la clase Organizacion es necesario extraerla de array
     *                                 organization.getGrp_any().getAny_object().
     */
    public void setSecuencia(es.pode.empaquetador.negocio.servicio.SecuenciaVO[] secuencia) {
        this.secuencia = secuencia;
    }


    /**
     * Gets the lom value for this OrganizacionVO.
     * 
     * @return lom
     */
    public es.pode.empaquetador.negocio.servicio.LomVO getLom() {
        return lom;
    }


    /**
     * Sets the lom value for this OrganizacionVO.
     * 
     * @param lom
     */
    public void setLom(es.pode.empaquetador.negocio.servicio.LomVO lom) {
        this.lom = lom;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OrganizacionVO)) return false;
        OrganizacionVO other = (OrganizacionVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle()))) &&
            ((this.identifier==null && other.getIdentifier()==null) || 
             (this.identifier!=null &&
              this.identifier.equals(other.getIdentifier()))) &&
            ((this.grupos==null && other.getGrupos()==null) || 
             (this.grupos!=null &&
              java.util.Arrays.equals(this.grupos, other.getGrupos()))) &&
            ((this.secuencia==null && other.getSecuencia()==null) || 
             (this.secuencia!=null &&
              java.util.Arrays.equals(this.secuencia, other.getSecuencia()))) &&
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
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        if (getIdentifier() != null) {
            _hashCode += getIdentifier().hashCode();
        }
        if (getGrupos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getGrupos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getGrupos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSecuencia() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSecuencia());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSecuencia(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getLom() != null) {
            _hashCode += getLom().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OrganizacionVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "OrganizacionVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identifier");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "identifier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("grupos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "grupos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "GrupoVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secuencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "secuencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "SecuenciaVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lom");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "lom"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "LomVO"));
        elemField.setNillable(true);
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
