/**
 * UsuarioVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.adminusuarios.negocio.servicios;

public class UsuarioVO  implements java.io.Serializable {
    private java.lang.Long id;

    private java.lang.String nombre;

    private java.lang.String apellido1;

    private java.lang.String apellido2;

    private java.lang.String email;

    private java.lang.String NIF;

    private java.lang.String usuario;

    private java.lang.String clave;

    private java.lang.String idioma;

    private java.lang.String idiomaBusqueda;

    private java.lang.String tipoEmpaquetador;

    private java.util.Calendar fechaAlta;

    private java.util.Calendar fechaBaja;

    private java.util.Calendar fechaSolicitudAlta;

    private es.pode.adminusuarios.negocio.servicios.GrupoVO[] grupos;

    public UsuarioVO() {
    }

    public UsuarioVO(
           java.lang.Long id,
           java.lang.String nombre,
           java.lang.String apellido1,
           java.lang.String apellido2,
           java.lang.String email,
           java.lang.String NIF,
           java.lang.String usuario,
           java.lang.String clave,
           java.lang.String idioma,
           java.lang.String idiomaBusqueda,
           java.lang.String tipoEmpaquetador,
           java.util.Calendar fechaAlta,
           java.util.Calendar fechaBaja,
           java.util.Calendar fechaSolicitudAlta,
           es.pode.adminusuarios.negocio.servicios.GrupoVO[] grupos) {
           this.id = id;
           this.nombre = nombre;
           this.apellido1 = apellido1;
           this.apellido2 = apellido2;
           this.email = email;
           this.NIF = NIF;
           this.usuario = usuario;
           this.clave = clave;
           this.idioma = idioma;
           this.idiomaBusqueda = idiomaBusqueda;
           this.tipoEmpaquetador = tipoEmpaquetador;
           this.fechaAlta = fechaAlta;
           this.fechaBaja = fechaBaja;
           this.fechaSolicitudAlta = fechaSolicitudAlta;
           this.grupos = grupos;
    }


    /**
     * Gets the id value for this UsuarioVO.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this UsuarioVO.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the nombre value for this UsuarioVO.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this UsuarioVO.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the apellido1 value for this UsuarioVO.
     * 
     * @return apellido1
     */
    public java.lang.String getApellido1() {
        return apellido1;
    }


    /**
     * Sets the apellido1 value for this UsuarioVO.
     * 
     * @param apellido1
     */
    public void setApellido1(java.lang.String apellido1) {
        this.apellido1 = apellido1;
    }


    /**
     * Gets the apellido2 value for this UsuarioVO.
     * 
     * @return apellido2
     */
    public java.lang.String getApellido2() {
        return apellido2;
    }


    /**
     * Sets the apellido2 value for this UsuarioVO.
     * 
     * @param apellido2
     */
    public void setApellido2(java.lang.String apellido2) {
        this.apellido2 = apellido2;
    }


    /**
     * Gets the email value for this UsuarioVO.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this UsuarioVO.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the NIF value for this UsuarioVO.
     * 
     * @return NIF
     */
    public java.lang.String getNIF() {
        return NIF;
    }


    /**
     * Sets the NIF value for this UsuarioVO.
     * 
     * @param NIF
     */
    public void setNIF(java.lang.String NIF) {
        this.NIF = NIF;
    }


    /**
     * Gets the usuario value for this UsuarioVO.
     * 
     * @return usuario
     */
    public java.lang.String getUsuario() {
        return usuario;
    }


    /**
     * Sets the usuario value for this UsuarioVO.
     * 
     * @param usuario
     */
    public void setUsuario(java.lang.String usuario) {
        this.usuario = usuario;
    }


    /**
     * Gets the clave value for this UsuarioVO.
     * 
     * @return clave
     */
    public java.lang.String getClave() {
        return clave;
    }


    /**
     * Sets the clave value for this UsuarioVO.
     * 
     * @param clave
     */
    public void setClave(java.lang.String clave) {
        this.clave = clave;
    }


    /**
     * Gets the idioma value for this UsuarioVO.
     * 
     * @return idioma
     */
    public java.lang.String getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this UsuarioVO.
     * 
     * @param idioma
     */
    public void setIdioma(java.lang.String idioma) {
        this.idioma = idioma;
    }


    /**
     * Gets the idiomaBusqueda value for this UsuarioVO.
     * 
     * @return idiomaBusqueda
     */
    public java.lang.String getIdiomaBusqueda() {
        return idiomaBusqueda;
    }


    /**
     * Sets the idiomaBusqueda value for this UsuarioVO.
     * 
     * @param idiomaBusqueda
     */
    public void setIdiomaBusqueda(java.lang.String idiomaBusqueda) {
        this.idiomaBusqueda = idiomaBusqueda;
    }


    /**
     * Gets the tipoEmpaquetador value for this UsuarioVO.
     * 
     * @return tipoEmpaquetador
     */
    public java.lang.String getTipoEmpaquetador() {
        return tipoEmpaquetador;
    }


    /**
     * Sets the tipoEmpaquetador value for this UsuarioVO.
     * 
     * @param tipoEmpaquetador
     */
    public void setTipoEmpaquetador(java.lang.String tipoEmpaquetador) {
        this.tipoEmpaquetador = tipoEmpaquetador;
    }


    /**
     * Gets the fechaAlta value for this UsuarioVO.
     * 
     * @return fechaAlta
     */
    public java.util.Calendar getFechaAlta() {
        return fechaAlta;
    }


    /**
     * Sets the fechaAlta value for this UsuarioVO.
     * 
     * @param fechaAlta
     */
    public void setFechaAlta(java.util.Calendar fechaAlta) {
        this.fechaAlta = fechaAlta;
    }


    /**
     * Gets the fechaBaja value for this UsuarioVO.
     * 
     * @return fechaBaja
     */
    public java.util.Calendar getFechaBaja() {
        return fechaBaja;
    }


    /**
     * Sets the fechaBaja value for this UsuarioVO.
     * 
     * @param fechaBaja
     */
    public void setFechaBaja(java.util.Calendar fechaBaja) {
        this.fechaBaja = fechaBaja;
    }


    /**
     * Gets the fechaSolicitudAlta value for this UsuarioVO.
     * 
     * @return fechaSolicitudAlta
     */
    public java.util.Calendar getFechaSolicitudAlta() {
        return fechaSolicitudAlta;
    }


    /**
     * Sets the fechaSolicitudAlta value for this UsuarioVO.
     * 
     * @param fechaSolicitudAlta
     */
    public void setFechaSolicitudAlta(java.util.Calendar fechaSolicitudAlta) {
        this.fechaSolicitudAlta = fechaSolicitudAlta;
    }


    /**
     * Gets the grupos value for this UsuarioVO.
     * 
     * @return grupos
     */
    public es.pode.adminusuarios.negocio.servicios.GrupoVO[] getGrupos() {
        return grupos;
    }


    /**
     * Sets the grupos value for this UsuarioVO.
     * 
     * @param grupos
     */
    public void setGrupos(es.pode.adminusuarios.negocio.servicios.GrupoVO[] grupos) {
        this.grupos = grupos;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UsuarioVO)) return false;
        UsuarioVO other = (UsuarioVO) obj;
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
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.apellido1==null && other.getApellido1()==null) || 
             (this.apellido1!=null &&
              this.apellido1.equals(other.getApellido1()))) &&
            ((this.apellido2==null && other.getApellido2()==null) || 
             (this.apellido2!=null &&
              this.apellido2.equals(other.getApellido2()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.NIF==null && other.getNIF()==null) || 
             (this.NIF!=null &&
              this.NIF.equals(other.getNIF()))) &&
            ((this.usuario==null && other.getUsuario()==null) || 
             (this.usuario!=null &&
              this.usuario.equals(other.getUsuario()))) &&
            ((this.clave==null && other.getClave()==null) || 
             (this.clave!=null &&
              this.clave.equals(other.getClave()))) &&
            ((this.idioma==null && other.getIdioma()==null) || 
             (this.idioma!=null &&
              this.idioma.equals(other.getIdioma()))) &&
            ((this.idiomaBusqueda==null && other.getIdiomaBusqueda()==null) || 
             (this.idiomaBusqueda!=null &&
              this.idiomaBusqueda.equals(other.getIdiomaBusqueda()))) &&
            ((this.tipoEmpaquetador==null && other.getTipoEmpaquetador()==null) || 
             (this.tipoEmpaquetador!=null &&
              this.tipoEmpaquetador.equals(other.getTipoEmpaquetador()))) &&
            ((this.fechaAlta==null && other.getFechaAlta()==null) || 
             (this.fechaAlta!=null &&
              this.fechaAlta.equals(other.getFechaAlta()))) &&
            ((this.fechaBaja==null && other.getFechaBaja()==null) || 
             (this.fechaBaja!=null &&
              this.fechaBaja.equals(other.getFechaBaja()))) &&
            ((this.fechaSolicitudAlta==null && other.getFechaSolicitudAlta()==null) || 
             (this.fechaSolicitudAlta!=null &&
              this.fechaSolicitudAlta.equals(other.getFechaSolicitudAlta()))) &&
            ((this.grupos==null && other.getGrupos()==null) || 
             (this.grupos!=null &&
              java.util.Arrays.equals(this.grupos, other.getGrupos())));
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
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getApellido1() != null) {
            _hashCode += getApellido1().hashCode();
        }
        if (getApellido2() != null) {
            _hashCode += getApellido2().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getNIF() != null) {
            _hashCode += getNIF().hashCode();
        }
        if (getUsuario() != null) {
            _hashCode += getUsuario().hashCode();
        }
        if (getClave() != null) {
            _hashCode += getClave().hashCode();
        }
        if (getIdioma() != null) {
            _hashCode += getIdioma().hashCode();
        }
        if (getIdiomaBusqueda() != null) {
            _hashCode += getIdiomaBusqueda().hashCode();
        }
        if (getTipoEmpaquetador() != null) {
            _hashCode += getTipoEmpaquetador().hashCode();
        }
        if (getFechaAlta() != null) {
            _hashCode += getFechaAlta().hashCode();
        }
        if (getFechaBaja() != null) {
            _hashCode += getFechaBaja().hashCode();
        }
        if (getFechaSolicitudAlta() != null) {
            _hashCode += getFechaSolicitudAlta().hashCode();
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UsuarioVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "UsuarioVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apellido1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "apellido1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apellido2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "apellido2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NIF");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "NIF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "usuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clave");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "clave"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "idioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idiomaBusqueda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "idiomaBusqueda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoEmpaquetador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "tipoEmpaquetador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaAlta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "fechaAlta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaBaja");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "fechaBaja"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaSolicitudAlta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "fechaSolicitudAlta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("grupos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "grupos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "GrupoVO"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "item"));
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
