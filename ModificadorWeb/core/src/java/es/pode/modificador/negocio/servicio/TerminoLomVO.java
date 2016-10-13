/**
 * TerminoLomVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.modificador.negocio.servicio;


/**
 * VO para la navegacion por el arbol Lom en la seleccion del
 *                         termino a modificar, añadir, eliminar ...
 */
public class TerminoLomVO  implements java.io.Serializable {
    /* Identificador del termino (p.e: 3.2.1). */
    private java.lang.String idTermino;

    /* Nombre del termino lomes (p.e: metadata) */
    private java.lang.String nombreTermino;

    /* Ruta completa del termino separada por puntos (p.e:
     *                                 lom.metadata.contribute) */
    private java.lang.String rutaTermino;

    private java.lang.Boolean modificable;

    private java.lang.Boolean aniadir;

    private java.lang.Boolean eliminable;

    private es.pode.modificador.negocio.servicio.TerminoLomVO[] hijos;

    private es.pode.modificador.negocio.servicio.TerminoLomVO[] padres;

    public TerminoLomVO() {
    }

    public TerminoLomVO(
           java.lang.String idTermino,
           java.lang.String nombreTermino,
           java.lang.String rutaTermino,
           java.lang.Boolean modificable,
           java.lang.Boolean aniadir,
           java.lang.Boolean eliminable,
           es.pode.modificador.negocio.servicio.TerminoLomVO[] hijos,
           es.pode.modificador.negocio.servicio.TerminoLomVO[] padres) {
           this.idTermino = idTermino;
           this.nombreTermino = nombreTermino;
           this.rutaTermino = rutaTermino;
           this.modificable = modificable;
           this.aniadir = aniadir;
           this.eliminable = eliminable;
           this.hijos = hijos;
           this.padres = padres;
    }


    /**
     * Gets the idTermino value for this TerminoLomVO.
     * 
     * @return idTermino   * Identificador del termino (p.e: 3.2.1).
     */
    public java.lang.String getIdTermino() {
        return idTermino;
    }


    /**
     * Sets the idTermino value for this TerminoLomVO.
     * 
     * @param idTermino   * Identificador del termino (p.e: 3.2.1).
     */
    public void setIdTermino(java.lang.String idTermino) {
        this.idTermino = idTermino;
    }


    /**
     * Gets the nombreTermino value for this TerminoLomVO.
     * 
     * @return nombreTermino   * Nombre del termino lomes (p.e: metadata)
     */
    public java.lang.String getNombreTermino() {
        return nombreTermino;
    }


    /**
     * Sets the nombreTermino value for this TerminoLomVO.
     * 
     * @param nombreTermino   * Nombre del termino lomes (p.e: metadata)
     */
    public void setNombreTermino(java.lang.String nombreTermino) {
        this.nombreTermino = nombreTermino;
    }


    /**
     * Gets the rutaTermino value for this TerminoLomVO.
     * 
     * @return rutaTermino   * Ruta completa del termino separada por puntos (p.e:
     *                                 lom.metadata.contribute)
     */
    public java.lang.String getRutaTermino() {
        return rutaTermino;
    }


    /**
     * Sets the rutaTermino value for this TerminoLomVO.
     * 
     * @param rutaTermino   * Ruta completa del termino separada por puntos (p.e:
     *                                 lom.metadata.contribute)
     */
    public void setRutaTermino(java.lang.String rutaTermino) {
        this.rutaTermino = rutaTermino;
    }


    /**
     * Gets the modificable value for this TerminoLomVO.
     * 
     * @return modificable
     */
    public java.lang.Boolean getModificable() {
        return modificable;
    }


    /**
     * Sets the modificable value for this TerminoLomVO.
     * 
     * @param modificable
     */
    public void setModificable(java.lang.Boolean modificable) {
        this.modificable = modificable;
    }


    /**
     * Gets the aniadir value for this TerminoLomVO.
     * 
     * @return aniadir
     */
    public java.lang.Boolean getAniadir() {
        return aniadir;
    }


    /**
     * Sets the aniadir value for this TerminoLomVO.
     * 
     * @param aniadir
     */
    public void setAniadir(java.lang.Boolean aniadir) {
        this.aniadir = aniadir;
    }


    /**
     * Gets the eliminable value for this TerminoLomVO.
     * 
     * @return eliminable
     */
    public java.lang.Boolean getEliminable() {
        return eliminable;
    }


    /**
     * Sets the eliminable value for this TerminoLomVO.
     * 
     * @param eliminable
     */
    public void setEliminable(java.lang.Boolean eliminable) {
        this.eliminable = eliminable;
    }


    /**
     * Gets the hijos value for this TerminoLomVO.
     * 
     * @return hijos
     */
    public es.pode.modificador.negocio.servicio.TerminoLomVO[] getHijos() {
        return hijos;
    }


    /**
     * Sets the hijos value for this TerminoLomVO.
     * 
     * @param hijos
     */
    public void setHijos(es.pode.modificador.negocio.servicio.TerminoLomVO[] hijos) {
        this.hijos = hijos;
    }


    /**
     * Gets the padres value for this TerminoLomVO.
     * 
     * @return padres
     */
    public es.pode.modificador.negocio.servicio.TerminoLomVO[] getPadres() {
        return padres;
    }


    /**
     * Sets the padres value for this TerminoLomVO.
     * 
     * @param padres
     */
    public void setPadres(es.pode.modificador.negocio.servicio.TerminoLomVO[] padres) {
        this.padres = padres;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TerminoLomVO)) return false;
        TerminoLomVO other = (TerminoLomVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idTermino==null && other.getIdTermino()==null) || 
             (this.idTermino!=null &&
              this.idTermino.equals(other.getIdTermino()))) &&
            ((this.nombreTermino==null && other.getNombreTermino()==null) || 
             (this.nombreTermino!=null &&
              this.nombreTermino.equals(other.getNombreTermino()))) &&
            ((this.rutaTermino==null && other.getRutaTermino()==null) || 
             (this.rutaTermino!=null &&
              this.rutaTermino.equals(other.getRutaTermino()))) &&
            ((this.modificable==null && other.getModificable()==null) || 
             (this.modificable!=null &&
              this.modificable.equals(other.getModificable()))) &&
            ((this.aniadir==null && other.getAniadir()==null) || 
             (this.aniadir!=null &&
              this.aniadir.equals(other.getAniadir()))) &&
            ((this.eliminable==null && other.getEliminable()==null) || 
             (this.eliminable!=null &&
              this.eliminable.equals(other.getEliminable()))) &&
            ((this.hijos==null && other.getHijos()==null) || 
             (this.hijos!=null &&
              java.util.Arrays.equals(this.hijos, other.getHijos()))) &&
            ((this.padres==null && other.getPadres()==null) || 
             (this.padres!=null &&
              java.util.Arrays.equals(this.padres, other.getPadres())));
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
        if (getIdTermino() != null) {
            _hashCode += getIdTermino().hashCode();
        }
        if (getNombreTermino() != null) {
            _hashCode += getNombreTermino().hashCode();
        }
        if (getRutaTermino() != null) {
            _hashCode += getRutaTermino().hashCode();
        }
        if (getModificable() != null) {
            _hashCode += getModificable().hashCode();
        }
        if (getAniadir() != null) {
            _hashCode += getAniadir().hashCode();
        }
        if (getEliminable() != null) {
            _hashCode += getEliminable().hashCode();
        }
        if (getHijos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getHijos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getHijos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPadres() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPadres());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPadres(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TerminoLomVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "TerminoLomVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTermino");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "idTermino"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreTermino");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "nombreTermino"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rutaTermino");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "rutaTermino"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modificable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "modificable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aniadir");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "aniadir"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("eliminable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "eliminable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hijos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "hijos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "TerminoLomVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("padres");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "padres"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "TerminoLomVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "item"));
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
