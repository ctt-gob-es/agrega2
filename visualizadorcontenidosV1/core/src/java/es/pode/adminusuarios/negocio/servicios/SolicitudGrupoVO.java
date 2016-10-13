/**
 * SolicitudGrupoVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.adminusuarios.negocio.servicios;

public class SolicitudGrupoVO  implements java.io.Serializable {
    /* Usuario que solicita ser miembro del grupo */
    private java.lang.String usuarioSolicitante;

    /* Grupo del que se desea ser miembro */
    private java.lang.String grupo;

    /* Administrador del grupo */
    private java.lang.String usuarioAdministrador;

    /* Fecha en la que se realiza la solicitud */
    private java.util.Calendar fechaSolicitud;

    private java.lang.Long id;

    public SolicitudGrupoVO() {
    }

    public SolicitudGrupoVO(
           java.lang.String usuarioSolicitante,
           java.lang.String grupo,
           java.lang.String usuarioAdministrador,
           java.util.Calendar fechaSolicitud,
           java.lang.Long id) {
           this.usuarioSolicitante = usuarioSolicitante;
           this.grupo = grupo;
           this.usuarioAdministrador = usuarioAdministrador;
           this.fechaSolicitud = fechaSolicitud;
           this.id = id;
    }


    /**
     * Gets the usuarioSolicitante value for this SolicitudGrupoVO.
     * 
     * @return usuarioSolicitante   * Usuario que solicita ser miembro del grupo
     */
    public java.lang.String getUsuarioSolicitante() {
        return usuarioSolicitante;
    }


    /**
     * Sets the usuarioSolicitante value for this SolicitudGrupoVO.
     * 
     * @param usuarioSolicitante   * Usuario que solicita ser miembro del grupo
     */
    public void setUsuarioSolicitante(java.lang.String usuarioSolicitante) {
        this.usuarioSolicitante = usuarioSolicitante;
    }


    /**
     * Gets the grupo value for this SolicitudGrupoVO.
     * 
     * @return grupo   * Grupo del que se desea ser miembro
     */
    public java.lang.String getGrupo() {
        return grupo;
    }


    /**
     * Sets the grupo value for this SolicitudGrupoVO.
     * 
     * @param grupo   * Grupo del que se desea ser miembro
     */
    public void setGrupo(java.lang.String grupo) {
        this.grupo = grupo;
    }


    /**
     * Gets the usuarioAdministrador value for this SolicitudGrupoVO.
     * 
     * @return usuarioAdministrador   * Administrador del grupo
     */
    public java.lang.String getUsuarioAdministrador() {
        return usuarioAdministrador;
    }


    /**
     * Sets the usuarioAdministrador value for this SolicitudGrupoVO.
     * 
     * @param usuarioAdministrador   * Administrador del grupo
     */
    public void setUsuarioAdministrador(java.lang.String usuarioAdministrador) {
        this.usuarioAdministrador = usuarioAdministrador;
    }


    /**
     * Gets the fechaSolicitud value for this SolicitudGrupoVO.
     * 
     * @return fechaSolicitud   * Fecha en la que se realiza la solicitud
     */
    public java.util.Calendar getFechaSolicitud() {
        return fechaSolicitud;
    }


    /**
     * Sets the fechaSolicitud value for this SolicitudGrupoVO.
     * 
     * @param fechaSolicitud   * Fecha en la que se realiza la solicitud
     */
    public void setFechaSolicitud(java.util.Calendar fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }


    /**
     * Gets the id value for this SolicitudGrupoVO.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this SolicitudGrupoVO.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SolicitudGrupoVO)) return false;
        SolicitudGrupoVO other = (SolicitudGrupoVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.usuarioSolicitante==null && other.getUsuarioSolicitante()==null) || 
             (this.usuarioSolicitante!=null &&
              this.usuarioSolicitante.equals(other.getUsuarioSolicitante()))) &&
            ((this.grupo==null && other.getGrupo()==null) || 
             (this.grupo!=null &&
              this.grupo.equals(other.getGrupo()))) &&
            ((this.usuarioAdministrador==null && other.getUsuarioAdministrador()==null) || 
             (this.usuarioAdministrador!=null &&
              this.usuarioAdministrador.equals(other.getUsuarioAdministrador()))) &&
            ((this.fechaSolicitud==null && other.getFechaSolicitud()==null) || 
             (this.fechaSolicitud!=null &&
              this.fechaSolicitud.equals(other.getFechaSolicitud()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId())));
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
        if (getUsuarioSolicitante() != null) {
            _hashCode += getUsuarioSolicitante().hashCode();
        }
        if (getGrupo() != null) {
            _hashCode += getGrupo().hashCode();
        }
        if (getUsuarioAdministrador() != null) {
            _hashCode += getUsuarioAdministrador().hashCode();
        }
        if (getFechaSolicitud() != null) {
            _hashCode += getFechaSolicitud().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SolicitudGrupoVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "SolicitudGrupoVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usuarioSolicitante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "usuarioSolicitante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("grupo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "grupo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usuarioAdministrador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "usuarioAdministrador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaSolicitud");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "fechaSolicitud"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
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
