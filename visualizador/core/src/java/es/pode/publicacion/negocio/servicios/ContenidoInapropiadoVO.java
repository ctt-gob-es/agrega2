/**
 * ContenidoInapropiadoVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.publicacion.negocio.servicios;

public class ContenidoInapropiadoVO  implements java.io.Serializable {
    private java.lang.String idOde;

    private java.lang.String titulo;

    private java.util.Calendar fecha;

    private java.lang.String usuario;

    private java.lang.String comentario;

    private java.lang.String estado;

    private java.lang.Boolean estado_ci;

    private java.lang.Long id;

    private java.util.Calendar fecha_inactividad;

    private java.lang.String idioma_cat;

    public ContenidoInapropiadoVO() {
    }

    public ContenidoInapropiadoVO(
           java.lang.String idOde,
           java.lang.String titulo,
           java.util.Calendar fecha,
           java.lang.String usuario,
           java.lang.String comentario,
           java.lang.String estado,
           java.lang.Boolean estado_ci,
           java.lang.Long id,
           java.util.Calendar fecha_inactividad,
           java.lang.String idioma_cat) {
           this.idOde = idOde;
           this.titulo = titulo;
           this.fecha = fecha;
           this.usuario = usuario;
           this.comentario = comentario;
           this.estado = estado;
           this.estado_ci = estado_ci;
           this.id = id;
           this.fecha_inactividad = fecha_inactividad;
           this.idioma_cat = idioma_cat;
    }


    /**
     * Gets the idOde value for this ContenidoInapropiadoVO.
     * 
     * @return idOde
     */
    public java.lang.String getIdOde() {
        return idOde;
    }


    /**
     * Sets the idOde value for this ContenidoInapropiadoVO.
     * 
     * @param idOde
     */
    public void setIdOde(java.lang.String idOde) {
        this.idOde = idOde;
    }


    /**
     * Gets the titulo value for this ContenidoInapropiadoVO.
     * 
     * @return titulo
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this ContenidoInapropiadoVO.
     * 
     * @param titulo
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }


    /**
     * Gets the fecha value for this ContenidoInapropiadoVO.
     * 
     * @return fecha
     */
    public java.util.Calendar getFecha() {
        return fecha;
    }


    /**
     * Sets the fecha value for this ContenidoInapropiadoVO.
     * 
     * @param fecha
     */
    public void setFecha(java.util.Calendar fecha) {
        this.fecha = fecha;
    }


    /**
     * Gets the usuario value for this ContenidoInapropiadoVO.
     * 
     * @return usuario
     */
    public java.lang.String getUsuario() {
        return usuario;
    }


    /**
     * Sets the usuario value for this ContenidoInapropiadoVO.
     * 
     * @param usuario
     */
    public void setUsuario(java.lang.String usuario) {
        this.usuario = usuario;
    }


    /**
     * Gets the comentario value for this ContenidoInapropiadoVO.
     * 
     * @return comentario
     */
    public java.lang.String getComentario() {
        return comentario;
    }


    /**
     * Sets the comentario value for this ContenidoInapropiadoVO.
     * 
     * @param comentario
     */
    public void setComentario(java.lang.String comentario) {
        this.comentario = comentario;
    }


    /**
     * Gets the estado value for this ContenidoInapropiadoVO.
     * 
     * @return estado
     */
    public java.lang.String getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this ContenidoInapropiadoVO.
     * 
     * @param estado
     */
    public void setEstado(java.lang.String estado) {
        this.estado = estado;
    }


    /**
     * Gets the estado_ci value for this ContenidoInapropiadoVO.
     * 
     * @return estado_ci
     */
    public java.lang.Boolean getEstado_ci() {
        return estado_ci;
    }


    /**
     * Sets the estado_ci value for this ContenidoInapropiadoVO.
     * 
     * @param estado_ci
     */
    public void setEstado_ci(java.lang.Boolean estado_ci) {
        this.estado_ci = estado_ci;
    }


    /**
     * Gets the id value for this ContenidoInapropiadoVO.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this ContenidoInapropiadoVO.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the fecha_inactividad value for this ContenidoInapropiadoVO.
     * 
     * @return fecha_inactividad
     */
    public java.util.Calendar getFecha_inactividad() {
        return fecha_inactividad;
    }


    /**
     * Sets the fecha_inactividad value for this ContenidoInapropiadoVO.
     * 
     * @param fecha_inactividad
     */
    public void setFecha_inactividad(java.util.Calendar fecha_inactividad) {
        this.fecha_inactividad = fecha_inactividad;
    }


    /**
     * Gets the idioma_cat value for this ContenidoInapropiadoVO.
     * 
     * @return idioma_cat
     */
    public java.lang.String getIdioma_cat() {
        return idioma_cat;
    }


    /**
     * Sets the idioma_cat value for this ContenidoInapropiadoVO.
     * 
     * @param idioma_cat
     */
    public void setIdioma_cat(java.lang.String idioma_cat) {
        this.idioma_cat = idioma_cat;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ContenidoInapropiadoVO)) return false;
        ContenidoInapropiadoVO other = (ContenidoInapropiadoVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idOde==null && other.getIdOde()==null) || 
             (this.idOde!=null &&
              this.idOde.equals(other.getIdOde()))) &&
            ((this.titulo==null && other.getTitulo()==null) || 
             (this.titulo!=null &&
              this.titulo.equals(other.getTitulo()))) &&
            ((this.fecha==null && other.getFecha()==null) || 
             (this.fecha!=null &&
              this.fecha.equals(other.getFecha()))) &&
            ((this.usuario==null && other.getUsuario()==null) || 
             (this.usuario!=null &&
              this.usuario.equals(other.getUsuario()))) &&
            ((this.comentario==null && other.getComentario()==null) || 
             (this.comentario!=null &&
              this.comentario.equals(other.getComentario()))) &&
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado()))) &&
            ((this.estado_ci==null && other.getEstado_ci()==null) || 
             (this.estado_ci!=null &&
              this.estado_ci.equals(other.getEstado_ci()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.fecha_inactividad==null && other.getFecha_inactividad()==null) || 
             (this.fecha_inactividad!=null &&
              this.fecha_inactividad.equals(other.getFecha_inactividad()))) &&
            ((this.idioma_cat==null && other.getIdioma_cat()==null) || 
             (this.idioma_cat!=null &&
              this.idioma_cat.equals(other.getIdioma_cat())));
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
        if (getIdOde() != null) {
            _hashCode += getIdOde().hashCode();
        }
        if (getTitulo() != null) {
            _hashCode += getTitulo().hashCode();
        }
        if (getFecha() != null) {
            _hashCode += getFecha().hashCode();
        }
        if (getUsuario() != null) {
            _hashCode += getUsuario().hashCode();
        }
        if (getComentario() != null) {
            _hashCode += getComentario().hashCode();
        }
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        if (getEstado_ci() != null) {
            _hashCode += getEstado_ci().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getFecha_inactividad() != null) {
            _hashCode += getFecha_inactividad().hashCode();
        }
        if (getIdioma_cat() != null) {
            _hashCode += getIdioma_cat().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ContenidoInapropiadoVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "ContenidoInapropiadoVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idOde");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "idOde"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "titulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "fecha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "usuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comentario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "comentario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado_ci");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "estado_ci"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha_inactividad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "fecha_inactividad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idioma_cat");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.publicacion.pode.es", "idioma_cat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
