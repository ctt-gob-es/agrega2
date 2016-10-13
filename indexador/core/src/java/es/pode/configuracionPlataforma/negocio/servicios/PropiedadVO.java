/**
 * PropiedadVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.configuracionPlataforma.negocio.servicios;

public class PropiedadVO  implements java.io.Serializable {
    private java.lang.String nombre;

    private java.lang.String valor;

    private java.lang.String ejemplo;

    private java.lang.String descripcion;

    private java.lang.String categoria;

    private java.lang.String tipologia;

    private boolean modificable;

    private java.lang.String instanciaJboss;

    private boolean requiereReinicioJboss;

    private java.lang.String ficherosAfectados;

    public PropiedadVO() {
    }

    public PropiedadVO(
           java.lang.String nombre,
           java.lang.String valor,
           java.lang.String ejemplo,
           java.lang.String descripcion,
           java.lang.String categoria,
           java.lang.String tipologia,
           boolean modificable,
           java.lang.String instanciaJboss,
           boolean requiereReinicioJboss,
           java.lang.String ficherosAfectados) {
           this.nombre = nombre;
           this.valor = valor;
           this.ejemplo = ejemplo;
           this.descripcion = descripcion;
           this.categoria = categoria;
           this.tipologia = tipologia;
           this.modificable = modificable;
           this.instanciaJboss = instanciaJboss;
           this.requiereReinicioJboss = requiereReinicioJboss;
           this.ficherosAfectados = ficherosAfectados;
    }


    /**
     * Gets the nombre value for this PropiedadVO.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this PropiedadVO.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the valor value for this PropiedadVO.
     * 
     * @return valor
     */
    public java.lang.String getValor() {
        return valor;
    }


    /**
     * Sets the valor value for this PropiedadVO.
     * 
     * @param valor
     */
    public void setValor(java.lang.String valor) {
        this.valor = valor;
    }


    /**
     * Gets the ejemplo value for this PropiedadVO.
     * 
     * @return ejemplo
     */
    public java.lang.String getEjemplo() {
        return ejemplo;
    }


    /**
     * Sets the ejemplo value for this PropiedadVO.
     * 
     * @param ejemplo
     */
    public void setEjemplo(java.lang.String ejemplo) {
        this.ejemplo = ejemplo;
    }


    /**
     * Gets the descripcion value for this PropiedadVO.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this PropiedadVO.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the categoria value for this PropiedadVO.
     * 
     * @return categoria
     */
    public java.lang.String getCategoria() {
        return categoria;
    }


    /**
     * Sets the categoria value for this PropiedadVO.
     * 
     * @param categoria
     */
    public void setCategoria(java.lang.String categoria) {
        this.categoria = categoria;
    }


    /**
     * Gets the tipologia value for this PropiedadVO.
     * 
     * @return tipologia
     */
    public java.lang.String getTipologia() {
        return tipologia;
    }


    /**
     * Sets the tipologia value for this PropiedadVO.
     * 
     * @param tipologia
     */
    public void setTipologia(java.lang.String tipologia) {
        this.tipologia = tipologia;
    }


    /**
     * Gets the modificable value for this PropiedadVO.
     * 
     * @return modificable
     */
    public boolean isModificable() {
        return modificable;
    }


    /**
     * Sets the modificable value for this PropiedadVO.
     * 
     * @param modificable
     */
    public void setModificable(boolean modificable) {
        this.modificable = modificable;
    }


    /**
     * Gets the instanciaJboss value for this PropiedadVO.
     * 
     * @return instanciaJboss
     */
    public java.lang.String getInstanciaJboss() {
        return instanciaJboss;
    }


    /**
     * Sets the instanciaJboss value for this PropiedadVO.
     * 
     * @param instanciaJboss
     */
    public void setInstanciaJboss(java.lang.String instanciaJboss) {
        this.instanciaJboss = instanciaJboss;
    }


    /**
     * Gets the requiereReinicioJboss value for this PropiedadVO.
     * 
     * @return requiereReinicioJboss
     */
    public boolean isRequiereReinicioJboss() {
        return requiereReinicioJboss;
    }


    /**
     * Sets the requiereReinicioJboss value for this PropiedadVO.
     * 
     * @param requiereReinicioJboss
     */
    public void setRequiereReinicioJboss(boolean requiereReinicioJboss) {
        this.requiereReinicioJboss = requiereReinicioJboss;
    }


    /**
     * Gets the ficherosAfectados value for this PropiedadVO.
     * 
     * @return ficherosAfectados
     */
    public java.lang.String getFicherosAfectados() {
        return ficherosAfectados;
    }


    /**
     * Sets the ficherosAfectados value for this PropiedadVO.
     * 
     * @param ficherosAfectados
     */
    public void setFicherosAfectados(java.lang.String ficherosAfectados) {
        this.ficherosAfectados = ficherosAfectados;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PropiedadVO)) return false;
        PropiedadVO other = (PropiedadVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.valor==null && other.getValor()==null) || 
             (this.valor!=null &&
              this.valor.equals(other.getValor()))) &&
            ((this.ejemplo==null && other.getEjemplo()==null) || 
             (this.ejemplo!=null &&
              this.ejemplo.equals(other.getEjemplo()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.categoria==null && other.getCategoria()==null) || 
             (this.categoria!=null &&
              this.categoria.equals(other.getCategoria()))) &&
            ((this.tipologia==null && other.getTipologia()==null) || 
             (this.tipologia!=null &&
              this.tipologia.equals(other.getTipologia()))) &&
            this.modificable == other.isModificable() &&
            ((this.instanciaJboss==null && other.getInstanciaJboss()==null) || 
             (this.instanciaJboss!=null &&
              this.instanciaJboss.equals(other.getInstanciaJboss()))) &&
            this.requiereReinicioJboss == other.isRequiereReinicioJboss() &&
            ((this.ficherosAfectados==null && other.getFicherosAfectados()==null) || 
             (this.ficherosAfectados!=null &&
              this.ficherosAfectados.equals(other.getFicherosAfectados())));
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
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getValor() != null) {
            _hashCode += getValor().hashCode();
        }
        if (getEjemplo() != null) {
            _hashCode += getEjemplo().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getCategoria() != null) {
            _hashCode += getCategoria().hashCode();
        }
        if (getTipologia() != null) {
            _hashCode += getTipologia().hashCode();
        }
        _hashCode += (isModificable() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getInstanciaJboss() != null) {
            _hashCode += getInstanciaJboss().hashCode();
        }
        _hashCode += (isRequiereReinicioJboss() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getFicherosAfectados() != null) {
            _hashCode += getFicherosAfectados().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PropiedadVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.configuracionPlataforma.pode.es", "PropiedadVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.configuracionPlataforma.pode.es", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.configuracionPlataforma.pode.es", "valor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ejemplo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.configuracionPlataforma.pode.es", "ejemplo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.configuracionPlataforma.pode.es", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("categoria");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.configuracionPlataforma.pode.es", "categoria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.configuracionPlataforma.pode.es", "tipologia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modificable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.configuracionPlataforma.pode.es", "modificable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("instanciaJboss");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.configuracionPlataforma.pode.es", "instanciaJboss"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requiereReinicioJboss");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.configuracionPlataforma.pode.es", "requiereReinicioJboss"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ficherosAfectados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.configuracionPlataforma.pode.es", "ficherosAfectados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
