/**
 * NodoVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.administrar.servicio;

public class NodoVO  implements java.io.Serializable {
    private java.lang.Long id;

    private java.lang.String nodo;

    private java.lang.String url;

    private java.util.Calendar fechaAlta;

    private java.lang.String puerto;

    private java.lang.String urlWS;

    /* Identificador del nodo que se va a publicar en la plataforma
     * Agrega. */
    private java.lang.String idNodo;

    private es.pode.buscar.negocio.administrar.servicio.CcaaVO ccaa;

    private es.pode.buscar.negocio.administrar.servicio.ServicioVO[] servicio;

    public NodoVO() {
    }

    public NodoVO(
           java.lang.Long id,
           java.lang.String nodo,
           java.lang.String url,
           java.util.Calendar fechaAlta,
           java.lang.String puerto,
           java.lang.String urlWS,
           java.lang.String idNodo,
           es.pode.buscar.negocio.administrar.servicio.CcaaVO ccaa,
           es.pode.buscar.negocio.administrar.servicio.ServicioVO[] servicio) {
           this.id = id;
           this.nodo = nodo;
           this.url = url;
           this.fechaAlta = fechaAlta;
           this.puerto = puerto;
           this.urlWS = urlWS;
           this.idNodo = idNodo;
           this.ccaa = ccaa;
           this.servicio = servicio;
    }


    /**
     * Gets the id value for this NodoVO.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this NodoVO.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the nodo value for this NodoVO.
     * 
     * @return nodo
     */
    public java.lang.String getNodo() {
        return nodo;
    }


    /**
     * Sets the nodo value for this NodoVO.
     * 
     * @param nodo
     */
    public void setNodo(java.lang.String nodo) {
        this.nodo = nodo;
    }


    /**
     * Gets the url value for this NodoVO.
     * 
     * @return url
     */
    public java.lang.String getUrl() {
        return url;
    }


    /**
     * Sets the url value for this NodoVO.
     * 
     * @param url
     */
    public void setUrl(java.lang.String url) {
        this.url = url;
    }


    /**
     * Gets the fechaAlta value for this NodoVO.
     * 
     * @return fechaAlta
     */
    public java.util.Calendar getFechaAlta() {
        return fechaAlta;
    }


    /**
     * Sets the fechaAlta value for this NodoVO.
     * 
     * @param fechaAlta
     */
    public void setFechaAlta(java.util.Calendar fechaAlta) {
        this.fechaAlta = fechaAlta;
    }


    /**
     * Gets the puerto value for this NodoVO.
     * 
     * @return puerto
     */
    public java.lang.String getPuerto() {
        return puerto;
    }


    /**
     * Sets the puerto value for this NodoVO.
     * 
     * @param puerto
     */
    public void setPuerto(java.lang.String puerto) {
        this.puerto = puerto;
    }


    /**
     * Gets the urlWS value for this NodoVO.
     * 
     * @return urlWS
     */
    public java.lang.String getUrlWS() {
        return urlWS;
    }


    /**
     * Sets the urlWS value for this NodoVO.
     * 
     * @param urlWS
     */
    public void setUrlWS(java.lang.String urlWS) {
        this.urlWS = urlWS;
    }


    /**
     * Gets the idNodo value for this NodoVO.
     * 
     * @return idNodo   * Identificador del nodo que se va a publicar en la plataforma
     * Agrega.
     */
    public java.lang.String getIdNodo() {
        return idNodo;
    }


    /**
     * Sets the idNodo value for this NodoVO.
     * 
     * @param idNodo   * Identificador del nodo que se va a publicar en la plataforma
     * Agrega.
     */
    public void setIdNodo(java.lang.String idNodo) {
        this.idNodo = idNodo;
    }


    /**
     * Gets the ccaa value for this NodoVO.
     * 
     * @return ccaa
     */
    public es.pode.buscar.negocio.administrar.servicio.CcaaVO getCcaa() {
        return ccaa;
    }


    /**
     * Sets the ccaa value for this NodoVO.
     * 
     * @param ccaa
     */
    public void setCcaa(es.pode.buscar.negocio.administrar.servicio.CcaaVO ccaa) {
        this.ccaa = ccaa;
    }


    /**
     * Gets the servicio value for this NodoVO.
     * 
     * @return servicio
     */
    public es.pode.buscar.negocio.administrar.servicio.ServicioVO[] getServicio() {
        return servicio;
    }


    /**
     * Sets the servicio value for this NodoVO.
     * 
     * @param servicio
     */
    public void setServicio(es.pode.buscar.negocio.administrar.servicio.ServicioVO[] servicio) {
        this.servicio = servicio;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NodoVO)) return false;
        NodoVO other = (NodoVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.nodo==null && other.getNodo()==null) || 
             (this.nodo!=null &&
              this.nodo.equals(other.getNodo()))) &&
            ((this.url==null && other.getUrl()==null) || 
             (this.url!=null &&
              this.url.equals(other.getUrl()))) &&
            ((this.fechaAlta==null && other.getFechaAlta()==null) || 
             (this.fechaAlta!=null &&
              this.fechaAlta.equals(other.getFechaAlta()))) &&
            ((this.puerto==null && other.getPuerto()==null) || 
             (this.puerto!=null &&
              this.puerto.equals(other.getPuerto()))) &&
            ((this.urlWS==null && other.getUrlWS()==null) || 
             (this.urlWS!=null &&
              this.urlWS.equals(other.getUrlWS()))) &&
            ((this.idNodo==null && other.getIdNodo()==null) || 
             (this.idNodo!=null &&
              this.idNodo.equals(other.getIdNodo()))) &&
            ((this.ccaa==null && other.getCcaa()==null) || 
             (this.ccaa!=null &&
              this.ccaa.equals(other.getCcaa()))) &&
            ((this.servicio==null && other.getServicio()==null) || 
             (this.servicio!=null &&
              java.util.Arrays.equals(this.servicio, other.getServicio())));
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getNodo() != null) {
            _hashCode += getNodo().hashCode();
        }
        if (getUrl() != null) {
            _hashCode += getUrl().hashCode();
        }
        if (getFechaAlta() != null) {
            _hashCode += getFechaAlta().hashCode();
        }
        if (getPuerto() != null) {
            _hashCode += getPuerto().hashCode();
        }
        if (getUrlWS() != null) {
            _hashCode += getUrlWS().hashCode();
        }
        if (getIdNodo() != null) {
            _hashCode += getIdNodo().hashCode();
        }
        if (getCcaa() != null) {
            _hashCode += getCcaa().hashCode();
        }
        if (getServicio() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getServicio());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getServicio(), i);
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
        new org.apache.axis.description.TypeDesc(NodoVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "NodoVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "nodo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("url");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "url"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaAlta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "fechaAlta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("puerto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "puerto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("urlWS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "urlWS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idNodo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "idNodo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ccaa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "ccaa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "CcaaVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servicio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "servicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "ServicioVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.administrar.negocio.buscar.pode.es", "item"));
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
