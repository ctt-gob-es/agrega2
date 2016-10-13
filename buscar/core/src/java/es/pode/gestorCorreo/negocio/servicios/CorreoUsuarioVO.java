/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * CorreoUsuarioVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.gestorCorreo.negocio.servicios;


/**
 * Este value object almacena toda la información complementaria
 *                         necesaría para el envío de correos en la gestión
 * de usuarios:
 *                         alta, baja, alta ldapExterno, alta nodo taller....
 */
public class CorreoUsuarioVO  implements java.io.Serializable {
    /* Login del usuario */
    private java.lang.String usuario;

    /* clave del usuario */
    private java.lang.String password;

    /* Nueva clave que se le asignará al usuario */
    private java.lang.String nuevaClave;

    /* Email del usuario */
    private java.lang.String emailUsuario;

    /* Indica si nos encontramos o no en un nodo taller, en el caso
     * de
     *                                 valer true el texto de los correos
     * de la gestión de usuarios
     *                                 cambian. */
    private java.lang.Boolean nodoTaller;

    /* Indica si existe un ldap externo para la gestión de los
     *                                 usuarios. En el caso de que valga
     * true además del correo de
     *                                 alta, baja, nueva clave habría que
     * enviar otro al administrador
     *                                 del ldap externo. */
    private java.lang.Boolean ldapExterno;

    /* destinatarios de correo */
    private java.lang.String[] to;

    /* la persona que envia el correi */
    private java.lang.String from;

    private java.lang.String idiomaCorreo;

    /* Url a la que apuntará el logo de agrega */
    private java.lang.String hrefLogo;

    /* Url de la imagen del logo de Agrega */
    private java.lang.String urlImagenLogo;

    public CorreoUsuarioVO() {
    }

    public CorreoUsuarioVO(
           java.lang.String usuario,
           java.lang.String password,
           java.lang.String nuevaClave,
           java.lang.String emailUsuario,
           java.lang.Boolean nodoTaller,
           java.lang.Boolean ldapExterno,
           java.lang.String[] to,
           java.lang.String from,
           java.lang.String idiomaCorreo,
           java.lang.String hrefLogo,
           java.lang.String urlImagenLogo) {
           this.usuario = usuario;
           this.password = password;
           this.nuevaClave = nuevaClave;
           this.emailUsuario = emailUsuario;
           this.nodoTaller = nodoTaller;
           this.ldapExterno = ldapExterno;
           this.to = to;
           this.from = from;
           this.idiomaCorreo = idiomaCorreo;
           this.hrefLogo = hrefLogo;
           this.urlImagenLogo = urlImagenLogo;
    }


    /**
     * Gets the usuario value for this CorreoUsuarioVO.
     * 
     * @return usuario   * Login del usuario
     */
    public java.lang.String getUsuario() {
        return usuario;
    }


    /**
     * Sets the usuario value for this CorreoUsuarioVO.
     * 
     * @param usuario   * Login del usuario
     */
    public void setUsuario(java.lang.String usuario) {
        this.usuario = usuario;
    }


    /**
     * Gets the password value for this CorreoUsuarioVO.
     * 
     * @return password   * clave del usuario
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this CorreoUsuarioVO.
     * 
     * @param password   * clave del usuario
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the nuevaClave value for this CorreoUsuarioVO.
     * 
     * @return nuevaClave   * Nueva clave que se le asignará al usuario
     */
    public java.lang.String getNuevaClave() {
        return nuevaClave;
    }


    /**
     * Sets the nuevaClave value for this CorreoUsuarioVO.
     * 
     * @param nuevaClave   * Nueva clave que se le asignará al usuario
     */
    public void setNuevaClave(java.lang.String nuevaClave) {
        this.nuevaClave = nuevaClave;
    }


    /**
     * Gets the emailUsuario value for this CorreoUsuarioVO.
     * 
     * @return emailUsuario   * Email del usuario
     */
    public java.lang.String getEmailUsuario() {
        return emailUsuario;
    }


    /**
     * Sets the emailUsuario value for this CorreoUsuarioVO.
     * 
     * @param emailUsuario   * Email del usuario
     */
    public void setEmailUsuario(java.lang.String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }


    /**
     * Gets the nodoTaller value for this CorreoUsuarioVO.
     * 
     * @return nodoTaller   * Indica si nos encontramos o no en un nodo taller, en el caso
     * de
     *                                 valer true el texto de los correos
     * de la gestión de usuarios
     *                                 cambian.
     */
    public java.lang.Boolean getNodoTaller() {
        return nodoTaller;
    }


    /**
     * Sets the nodoTaller value for this CorreoUsuarioVO.
     * 
     * @param nodoTaller   * Indica si nos encontramos o no en un nodo taller, en el caso
     * de
     *                                 valer true el texto de los correos
     * de la gestión de usuarios
     *                                 cambian.
     */
    public void setNodoTaller(java.lang.Boolean nodoTaller) {
        this.nodoTaller = nodoTaller;
    }


    /**
     * Gets the ldapExterno value for this CorreoUsuarioVO.
     * 
     * @return ldapExterno   * Indica si existe un ldap externo para la gestión de los
     *                                 usuarios. En el caso de que valga
     * true además del correo de
     *                                 alta, baja, nueva clave habría que
     * enviar otro al administrador
     *                                 del ldap externo.
     */
    public java.lang.Boolean getLdapExterno() {
        return ldapExterno;
    }


    /**
     * Sets the ldapExterno value for this CorreoUsuarioVO.
     * 
     * @param ldapExterno   * Indica si existe un ldap externo para la gestión de los
     *                                 usuarios. En el caso de que valga
     * true además del correo de
     *                                 alta, baja, nueva clave habría que
     * enviar otro al administrador
     *                                 del ldap externo.
     */
    public void setLdapExterno(java.lang.Boolean ldapExterno) {
        this.ldapExterno = ldapExterno;
    }


    /**
     * Gets the to value for this CorreoUsuarioVO.
     * 
     * @return to   * destinatarios de correo
     */
    public java.lang.String[] getTo() {
        return to;
    }


    /**
     * Sets the to value for this CorreoUsuarioVO.
     * 
     * @param to   * destinatarios de correo
     */
    public void setTo(java.lang.String[] to) {
        this.to = to;
    }


    /**
     * Gets the from value for this CorreoUsuarioVO.
     * 
     * @return from   * la persona que envia el correi
     */
    public java.lang.String getFrom() {
        return from;
    }


    /**
     * Sets the from value for this CorreoUsuarioVO.
     * 
     * @param from   * la persona que envia el correi
     */
    public void setFrom(java.lang.String from) {
        this.from = from;
    }


    /**
     * Gets the idiomaCorreo value for this CorreoUsuarioVO.
     * 
     * @return idiomaCorreo
     */
    public java.lang.String getIdiomaCorreo() {
        return idiomaCorreo;
    }


    /**
     * Sets the idiomaCorreo value for this CorreoUsuarioVO.
     * 
     * @param idiomaCorreo
     */
    public void setIdiomaCorreo(java.lang.String idiomaCorreo) {
        this.idiomaCorreo = idiomaCorreo;
    }


    /**
     * Gets the hrefLogo value for this CorreoUsuarioVO.
     * 
     * @return hrefLogo   * Url a la que apuntará el logo de agrega
     */
    public java.lang.String getHrefLogo() {
        return hrefLogo;
    }


    /**
     * Sets the hrefLogo value for this CorreoUsuarioVO.
     * 
     * @param hrefLogo   * Url a la que apuntará el logo de agrega
     */
    public void setHrefLogo(java.lang.String hrefLogo) {
        this.hrefLogo = hrefLogo;
    }


    /**
     * Gets the urlImagenLogo value for this CorreoUsuarioVO.
     * 
     * @return urlImagenLogo   * Url de la imagen del logo de Agrega
     */
    public java.lang.String getUrlImagenLogo() {
        return urlImagenLogo;
    }


    /**
     * Sets the urlImagenLogo value for this CorreoUsuarioVO.
     * 
     * @param urlImagenLogo   * Url de la imagen del logo de Agrega
     */
    public void setUrlImagenLogo(java.lang.String urlImagenLogo) {
        this.urlImagenLogo = urlImagenLogo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CorreoUsuarioVO)) return false;
        CorreoUsuarioVO other = (CorreoUsuarioVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.usuario==null && other.getUsuario()==null) || 
             (this.usuario!=null &&
              this.usuario.equals(other.getUsuario()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.nuevaClave==null && other.getNuevaClave()==null) || 
             (this.nuevaClave!=null &&
              this.nuevaClave.equals(other.getNuevaClave()))) &&
            ((this.emailUsuario==null && other.getEmailUsuario()==null) || 
             (this.emailUsuario!=null &&
              this.emailUsuario.equals(other.getEmailUsuario()))) &&
            ((this.nodoTaller==null && other.getNodoTaller()==null) || 
             (this.nodoTaller!=null &&
              this.nodoTaller.equals(other.getNodoTaller()))) &&
            ((this.ldapExterno==null && other.getLdapExterno()==null) || 
             (this.ldapExterno!=null &&
              this.ldapExterno.equals(other.getLdapExterno()))) &&
            ((this.to==null && other.getTo()==null) || 
             (this.to!=null &&
              java.util.Arrays.equals(this.to, other.getTo()))) &&
            ((this.from==null && other.getFrom()==null) || 
             (this.from!=null &&
              this.from.equals(other.getFrom()))) &&
            ((this.idiomaCorreo==null && other.getIdiomaCorreo()==null) || 
             (this.idiomaCorreo!=null &&
              this.idiomaCorreo.equals(other.getIdiomaCorreo()))) &&
            ((this.hrefLogo==null && other.getHrefLogo()==null) || 
             (this.hrefLogo!=null &&
              this.hrefLogo.equals(other.getHrefLogo()))) &&
            ((this.urlImagenLogo==null && other.getUrlImagenLogo()==null) || 
             (this.urlImagenLogo!=null &&
              this.urlImagenLogo.equals(other.getUrlImagenLogo())));
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
        if (getUsuario() != null) {
            _hashCode += getUsuario().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getNuevaClave() != null) {
            _hashCode += getNuevaClave().hashCode();
        }
        if (getEmailUsuario() != null) {
            _hashCode += getEmailUsuario().hashCode();
        }
        if (getNodoTaller() != null) {
            _hashCode += getNodoTaller().hashCode();
        }
        if (getLdapExterno() != null) {
            _hashCode += getLdapExterno().hashCode();
        }
        if (getTo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFrom() != null) {
            _hashCode += getFrom().hashCode();
        }
        if (getIdiomaCorreo() != null) {
            _hashCode += getIdiomaCorreo().hashCode();
        }
        if (getHrefLogo() != null) {
            _hashCode += getHrefLogo().hashCode();
        }
        if (getUrlImagenLogo() != null) {
            _hashCode += getUrlImagenLogo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CorreoUsuarioVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "CorreoUsuarioVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "usuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nuevaClave");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "nuevaClave"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "emailUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodoTaller");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "nodoTaller"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ldapExterno");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "ldapExterno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("to");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "to"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("from");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "from"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idiomaCorreo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "idiomaCorreo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hrefLogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "hrefLogo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("urlImagenLogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "urlImagenLogo"));
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
