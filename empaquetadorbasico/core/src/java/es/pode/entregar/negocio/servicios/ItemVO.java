/**
 * ItemVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.entregar.negocio.servicios;

public class ItemVO  implements java.io.Serializable {
    private java.lang.String idItem;

    private java.lang.String idItem_ref;

    private java.lang.String titulo;

    private java.lang.String hrefRec;

    private java.lang.Boolean clicable;

    private es.pode.entregar.negocio.servicios.SecuenciaVO secuenciaItem;

    private java.lang.String idPadre;

    private java.lang.String idOrg;

    private es.pode.entregar.negocio.servicios.RecursoVO recurso;

    private es.pode.entregar.negocio.servicios.ItemVO[] itemHijos;

    public ItemVO() {
    }

    public ItemVO(
           java.lang.String idItem,
           java.lang.String idItem_ref,
           java.lang.String titulo,
           java.lang.String hrefRec,
           java.lang.Boolean clicable,
           es.pode.entregar.negocio.servicios.SecuenciaVO secuenciaItem,
           java.lang.String idPadre,
           java.lang.String idOrg,
           es.pode.entregar.negocio.servicios.RecursoVO recurso,
           es.pode.entregar.negocio.servicios.ItemVO[] itemHijos) {
           this.idItem = idItem;
           this.idItem_ref = idItem_ref;
           this.titulo = titulo;
           this.hrefRec = hrefRec;
           this.clicable = clicable;
           this.secuenciaItem = secuenciaItem;
           this.idPadre = idPadre;
           this.idOrg = idOrg;
           this.recurso = recurso;
           this.itemHijos = itemHijos;
    }


    /**
     * Gets the idItem value for this ItemVO.
     * 
     * @return idItem
     */
    public java.lang.String getIdItem() {
        return idItem;
    }


    /**
     * Sets the idItem value for this ItemVO.
     * 
     * @param idItem
     */
    public void setIdItem(java.lang.String idItem) {
        this.idItem = idItem;
    }


    /**
     * Gets the idItem_ref value for this ItemVO.
     * 
     * @return idItem_ref
     */
    public java.lang.String getIdItem_ref() {
        return idItem_ref;
    }


    /**
     * Sets the idItem_ref value for this ItemVO.
     * 
     * @param idItem_ref
     */
    public void setIdItem_ref(java.lang.String idItem_ref) {
        this.idItem_ref = idItem_ref;
    }


    /**
     * Gets the titulo value for this ItemVO.
     * 
     * @return titulo
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this ItemVO.
     * 
     * @param titulo
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }


    /**
     * Gets the hrefRec value for this ItemVO.
     * 
     * @return hrefRec
     */
    public java.lang.String getHrefRec() {
        return hrefRec;
    }


    /**
     * Sets the hrefRec value for this ItemVO.
     * 
     * @param hrefRec
     */
    public void setHrefRec(java.lang.String hrefRec) {
        this.hrefRec = hrefRec;
    }


    /**
     * Gets the clicable value for this ItemVO.
     * 
     * @return clicable
     */
    public java.lang.Boolean getClicable() {
        return clicable;
    }


    /**
     * Sets the clicable value for this ItemVO.
     * 
     * @param clicable
     */
    public void setClicable(java.lang.Boolean clicable) {
        this.clicable = clicable;
    }


    /**
     * Gets the secuenciaItem value for this ItemVO.
     * 
     * @return secuenciaItem
     */
    public es.pode.entregar.negocio.servicios.SecuenciaVO getSecuenciaItem() {
        return secuenciaItem;
    }


    /**
     * Sets the secuenciaItem value for this ItemVO.
     * 
     * @param secuenciaItem
     */
    public void setSecuenciaItem(es.pode.entregar.negocio.servicios.SecuenciaVO secuenciaItem) {
        this.secuenciaItem = secuenciaItem;
    }


    /**
     * Gets the idPadre value for this ItemVO.
     * 
     * @return idPadre
     */
    public java.lang.String getIdPadre() {
        return idPadre;
    }


    /**
     * Sets the idPadre value for this ItemVO.
     * 
     * @param idPadre
     */
    public void setIdPadre(java.lang.String idPadre) {
        this.idPadre = idPadre;
    }


    /**
     * Gets the idOrg value for this ItemVO.
     * 
     * @return idOrg
     */
    public java.lang.String getIdOrg() {
        return idOrg;
    }


    /**
     * Sets the idOrg value for this ItemVO.
     * 
     * @param idOrg
     */
    public void setIdOrg(java.lang.String idOrg) {
        this.idOrg = idOrg;
    }


    /**
     * Gets the recurso value for this ItemVO.
     * 
     * @return recurso
     */
    public es.pode.entregar.negocio.servicios.RecursoVO getRecurso() {
        return recurso;
    }


    /**
     * Sets the recurso value for this ItemVO.
     * 
     * @param recurso
     */
    public void setRecurso(es.pode.entregar.negocio.servicios.RecursoVO recurso) {
        this.recurso = recurso;
    }


    /**
     * Gets the itemHijos value for this ItemVO.
     * 
     * @return itemHijos
     */
    public es.pode.entregar.negocio.servicios.ItemVO[] getItemHijos() {
        return itemHijos;
    }


    /**
     * Sets the itemHijos value for this ItemVO.
     * 
     * @param itemHijos
     */
    public void setItemHijos(es.pode.entregar.negocio.servicios.ItemVO[] itemHijos) {
        this.itemHijos = itemHijos;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ItemVO)) return false;
        ItemVO other = (ItemVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idItem==null && other.getIdItem()==null) || 
             (this.idItem!=null &&
              this.idItem.equals(other.getIdItem()))) &&
            ((this.idItem_ref==null && other.getIdItem_ref()==null) || 
             (this.idItem_ref!=null &&
              this.idItem_ref.equals(other.getIdItem_ref()))) &&
            ((this.titulo==null && other.getTitulo()==null) || 
             (this.titulo!=null &&
              this.titulo.equals(other.getTitulo()))) &&
            ((this.hrefRec==null && other.getHrefRec()==null) || 
             (this.hrefRec!=null &&
              this.hrefRec.equals(other.getHrefRec()))) &&
            ((this.clicable==null && other.getClicable()==null) || 
             (this.clicable!=null &&
              this.clicable.equals(other.getClicable()))) &&
            ((this.secuenciaItem==null && other.getSecuenciaItem()==null) || 
             (this.secuenciaItem!=null &&
              this.secuenciaItem.equals(other.getSecuenciaItem()))) &&
            ((this.idPadre==null && other.getIdPadre()==null) || 
             (this.idPadre!=null &&
              this.idPadre.equals(other.getIdPadre()))) &&
            ((this.idOrg==null && other.getIdOrg()==null) || 
             (this.idOrg!=null &&
              this.idOrg.equals(other.getIdOrg()))) &&
            ((this.recurso==null && other.getRecurso()==null) || 
             (this.recurso!=null &&
              this.recurso.equals(other.getRecurso()))) &&
            ((this.itemHijos==null && other.getItemHijos()==null) || 
             (this.itemHijos!=null &&
              java.util.Arrays.equals(this.itemHijos, other.getItemHijos())));
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
        if (getIdItem() != null) {
            _hashCode += getIdItem().hashCode();
        }
        if (getIdItem_ref() != null) {
            _hashCode += getIdItem_ref().hashCode();
        }
        if (getTitulo() != null) {
            _hashCode += getTitulo().hashCode();
        }
        if (getHrefRec() != null) {
            _hashCode += getHrefRec().hashCode();
        }
        if (getClicable() != null) {
            _hashCode += getClicable().hashCode();
        }
        if (getSecuenciaItem() != null) {
            _hashCode += getSecuenciaItem().hashCode();
        }
        if (getIdPadre() != null) {
            _hashCode += getIdPadre().hashCode();
        }
        if (getIdOrg() != null) {
            _hashCode += getIdOrg().hashCode();
        }
        if (getRecurso() != null) {
            _hashCode += getRecurso().hashCode();
        }
        if (getItemHijos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getItemHijos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getItemHijos(), i);
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
        new org.apache.axis.description.TypeDesc(ItemVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "ItemVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idItem");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "idItem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idItem_ref");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "idItem_ref"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "titulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hrefRec");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "hrefRec"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clicable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "clicable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secuenciaItem");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "secuenciaItem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "SecuenciaVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPadre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "idPadre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idOrg");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "idOrg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recurso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "recurso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "RecursoVO"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("itemHijos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "itemHijos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "ItemVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "item"));
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
