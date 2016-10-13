/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.soporte.seguridad.servicios;


/**
 * UsuarioPublicoVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */


import es.pode.soporte.seguridad.servicios.UsuarioPublicoVO;


/**
 * Usuario público de Agrega. Cada usuario público se correspondará
 * con un usuario registrado en la plataforma
 */
public class UsuarioPublicoVO  implements java.io.Serializable {
    /* Nombre de la foto del usuario */
    private java.lang.String foto;

    /* Indica si se va a mostrar los objetos públicos del usuario
     * en su
     *                                 página pública */
    private java.lang.Boolean mostrarObjetos;

    /* Indica si se va a mostrar el bloque de los odes favoritos de
     * un
     *                                 usuario en la página pública de un
     * usuario */
    private java.lang.Boolean mostrarFavoritos;

    /* Indica si se va a mostrar los grupos de un usuario en su página
     * pública */
    private java.lang.Boolean mostrarGrupos;

    /* Nombre del centro educativo del usuario */
    private java.lang.String centroEducativo;

    private java.lang.Boolean recibirCorreo;

    private java.lang.String usuario;

    private javax.activation.DataHandler contenidoImagen;

    private java.lang.Long id;

    /* Nombre y apellidos del usuario */
    private java.lang.String nombreUsuario;

    /* Descripcion del usuario publico */
    private java.lang.String descripcion;

    private java.lang.Boolean activo;

    private es.pode.soporte.seguridad.servicios.GrupoPublicoVO[] grupoPublico;

    private es.pode.soporte.seguridad.servicios.FavoritoVO[] favoritoUsuario;

    private es.pode.soporte.seguridad.servicios.ContactoVO[] contacto;

    public UsuarioPublicoVO() {
    }

    public UsuarioPublicoVO(
           java.lang.String foto,
           java.lang.Boolean mostrarObjetos,
           java.lang.Boolean mostrarFavoritos,
           java.lang.Boolean mostrarGrupos,
           java.lang.String centroEducativo,
           java.lang.Boolean recibirCorreo,
           java.lang.String usuario,
           javax.activation.DataHandler contenidoImagen,
           java.lang.Long id,
           java.lang.String nombreUsuario,
           java.lang.String descripcion,
           java.lang.Boolean activo,
           es.pode.soporte.seguridad.servicios.GrupoPublicoVO[] grupoPublico,
           es.pode.soporte.seguridad.servicios.FavoritoVO[] favoritoUsuario,
           es.pode.soporte.seguridad.servicios.ContactoVO[] contacto) {
           this.foto = foto;
           this.mostrarObjetos = mostrarObjetos;
           this.mostrarFavoritos = mostrarFavoritos;
           this.mostrarGrupos = mostrarGrupos;
           this.centroEducativo = centroEducativo;
           this.recibirCorreo = recibirCorreo;
           this.usuario = usuario;
           this.contenidoImagen = contenidoImagen;
           this.id = id;
           this.nombreUsuario = nombreUsuario;
           this.descripcion = descripcion;
           this.activo = activo;
           this.grupoPublico = grupoPublico;
           this.favoritoUsuario = favoritoUsuario;
           this.contacto = contacto;
    }


    /**
     * Gets the foto value for this UsuarioPublicoVO.
     * 
     * @return foto   * Nombre de la foto del usuario
     */
    public java.lang.String getFoto() {
        return foto;
    }


    /**
     * Sets the foto value for this UsuarioPublicoVO.
     * 
     * @param foto   * Nombre de la foto del usuario
     */
    public void setFoto(java.lang.String foto) {
        this.foto = foto;
    }


    /**
     * Gets the mostrarObjetos value for this UsuarioPublicoVO.
     * 
     * @return mostrarObjetos   * Indica si se va a mostrar los objetos públicos del usuario
     * en su
     *                                 página pública
     */
    public java.lang.Boolean getMostrarObjetos() {
        return mostrarObjetos;
    }


    /**
     * Sets the mostrarObjetos value for this UsuarioPublicoVO.
     * 
     * @param mostrarObjetos   * Indica si se va a mostrar los objetos públicos del usuario
     * en su
     *                                 página pública
     */
    public void setMostrarObjetos(java.lang.Boolean mostrarObjetos) {
        this.mostrarObjetos = mostrarObjetos;
    }


    /**
     * Gets the mostrarFavoritos value for this UsuarioPublicoVO.
     * 
     * @return mostrarFavoritos   * Indica si se va a mostrar el bloque de los odes favoritos de
     * un
     *                                 usuario en la página pública de un
     * usuario
     */
    public java.lang.Boolean getMostrarFavoritos() {
        return mostrarFavoritos;
    }


    /**
     * Sets the mostrarFavoritos value for this UsuarioPublicoVO.
     * 
     * @param mostrarFavoritos   * Indica si se va a mostrar el bloque de los odes favoritos de
     * un
     *                                 usuario en la página pública de un
     * usuario
     */
    public void setMostrarFavoritos(java.lang.Boolean mostrarFavoritos) {
        this.mostrarFavoritos = mostrarFavoritos;
    }


    /**
     * Gets the mostrarGrupos value for this UsuarioPublicoVO.
     * 
     * @return mostrarGrupos   * Indica si se va a mostrar los grupos de un usuario en su página
     * pública
     */
    public java.lang.Boolean getMostrarGrupos() {
        return mostrarGrupos;
    }


    /**
     * Sets the mostrarGrupos value for this UsuarioPublicoVO.
     * 
     * @param mostrarGrupos   * Indica si se va a mostrar los grupos de un usuario en su página
     * pública
     */
    public void setMostrarGrupos(java.lang.Boolean mostrarGrupos) {
        this.mostrarGrupos = mostrarGrupos;
    }


    /**
     * Gets the centroEducativo value for this UsuarioPublicoVO.
     * 
     * @return centroEducativo   * Nombre del centro educativo del usuario
     */
    public java.lang.String getCentroEducativo() {
        return centroEducativo;
    }


    /**
     * Sets the centroEducativo value for this UsuarioPublicoVO.
     * 
     * @param centroEducativo   * Nombre del centro educativo del usuario
     */
    public void setCentroEducativo(java.lang.String centroEducativo) {
        this.centroEducativo = centroEducativo;
    }


    /**
     * Gets the recibirCorreo value for this UsuarioPublicoVO.
     * 
     * @return recibirCorreo
     */
    public java.lang.Boolean getRecibirCorreo() {
        return recibirCorreo;
    }


    /**
     * Sets the recibirCorreo value for this UsuarioPublicoVO.
     * 
     * @param recibirCorreo
     */
    public void setRecibirCorreo(java.lang.Boolean recibirCorreo) {
        this.recibirCorreo = recibirCorreo;
    }


    /**
     * Gets the usuario value for this UsuarioPublicoVO.
     * 
     * @return usuario
     */
    public java.lang.String getUsuario() {
        return usuario;
    }


    /**
     * Sets the usuario value for this UsuarioPublicoVO.
     * 
     * @param usuario
     */
    public void setUsuario(java.lang.String usuario) {
        this.usuario = usuario;
    }


    /**
     * Gets the contenidoImagen value for this UsuarioPublicoVO.
     * 
     * @return contenidoImagen
     */
    public javax.activation.DataHandler getContenidoImagen() {
        return contenidoImagen;
    }


    /**
     * Sets the contenidoImagen value for this UsuarioPublicoVO.
     * 
     * @param contenidoImagen
     */
    public void setContenidoImagen(javax.activation.DataHandler contenidoImagen) {
        this.contenidoImagen = contenidoImagen;
    }


    /**
     * Gets the id value for this UsuarioPublicoVO.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this UsuarioPublicoVO.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the nombreUsuario value for this UsuarioPublicoVO.
     * 
     * @return nombreUsuario   * Nombre y apellidos del usuario
     */
    public java.lang.String getNombreUsuario() {
        return nombreUsuario;
    }


    /**
     * Sets the nombreUsuario value for this UsuarioPublicoVO.
     * 
     * @param nombreUsuario   * Nombre y apellidos del usuario
     */
    public void setNombreUsuario(java.lang.String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }


    /**
     * Gets the descripcion value for this UsuarioPublicoVO.
     * 
     * @return descripcion   * Descripcion del usuario publico
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this UsuarioPublicoVO.
     * 
     * @param descripcion   * Descripcion del usuario publico
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the activo value for this UsuarioPublicoVO.
     * 
     * @return activo
     */
    public java.lang.Boolean getActivo() {
        return activo;
    }


    /**
     * Sets the activo value for this UsuarioPublicoVO.
     * 
     * @param activo
     */
    public void setActivo(java.lang.Boolean activo) {
        this.activo = activo;
    }


    /**
     * Gets the grupoPublico value for this UsuarioPublicoVO.
     * 
     * @return grupoPublico
     */
    public es.pode.soporte.seguridad.servicios.GrupoPublicoVO[] getGrupoPublico() {
        return grupoPublico;
    }


    /**
     * Sets the grupoPublico value for this UsuarioPublicoVO.
     * 
     * @param grupoPublico
     */
    public void setGrupoPublico(es.pode.soporte.seguridad.servicios.GrupoPublicoVO[] grupoPublico) {
        this.grupoPublico = grupoPublico;
    }


    /**
     * Gets the favoritoUsuario value for this UsuarioPublicoVO.
     * 
     * @return favoritoUsuario
     */
    public es.pode.soporte.seguridad.servicios.FavoritoVO[] getFavoritoUsuario() {
        return favoritoUsuario;
    }


    /**
     * Sets the favoritoUsuario value for this UsuarioPublicoVO.
     * 
     * @param favoritoUsuario
     */
    public void setFavoritoUsuario(es.pode.soporte.seguridad.servicios.FavoritoVO[] favoritoUsuario) {
        this.favoritoUsuario = favoritoUsuario;
    }


    /**
     * Gets the contacto value for this UsuarioPublicoVO.
     * 
     * @return contacto
     */
    public es.pode.soporte.seguridad.servicios.ContactoVO[] getContacto() {
        return contacto;
    }


    /**
     * Sets the contacto value for this UsuarioPublicoVO.
     * 
     * @param contacto
     */
    public void setContacto(es.pode.soporte.seguridad.servicios.ContactoVO[] contacto) {
        this.contacto = contacto;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UsuarioPublicoVO)) return false;
        UsuarioPublicoVO other = (UsuarioPublicoVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.foto==null && other.getFoto()==null) || 
             (this.foto!=null &&
              this.foto.equals(other.getFoto()))) &&
            ((this.mostrarObjetos==null && other.getMostrarObjetos()==null) || 
             (this.mostrarObjetos!=null &&
              this.mostrarObjetos.equals(other.getMostrarObjetos()))) &&
            ((this.mostrarFavoritos==null && other.getMostrarFavoritos()==null) || 
             (this.mostrarFavoritos!=null &&
              this.mostrarFavoritos.equals(other.getMostrarFavoritos()))) &&
            ((this.mostrarGrupos==null && other.getMostrarGrupos()==null) || 
             (this.mostrarGrupos!=null &&
              this.mostrarGrupos.equals(other.getMostrarGrupos()))) &&
            ((this.centroEducativo==null && other.getCentroEducativo()==null) || 
             (this.centroEducativo!=null &&
              this.centroEducativo.equals(other.getCentroEducativo()))) &&
            ((this.recibirCorreo==null && other.getRecibirCorreo()==null) || 
             (this.recibirCorreo!=null &&
              this.recibirCorreo.equals(other.getRecibirCorreo()))) &&
            ((this.usuario==null && other.getUsuario()==null) || 
             (this.usuario!=null &&
              this.usuario.equals(other.getUsuario()))) &&
            ((this.contenidoImagen==null && other.getContenidoImagen()==null) || 
             (this.contenidoImagen!=null &&
              this.contenidoImagen.equals(other.getContenidoImagen()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.nombreUsuario==null && other.getNombreUsuario()==null) || 
             (this.nombreUsuario!=null &&
              this.nombreUsuario.equals(other.getNombreUsuario()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.activo==null && other.getActivo()==null) || 
             (this.activo!=null &&
              this.activo.equals(other.getActivo()))) &&
            ((this.grupoPublico==null && other.getGrupoPublico()==null) || 
             (this.grupoPublico!=null &&
              java.util.Arrays.equals(this.grupoPublico, other.getGrupoPublico()))) &&
            ((this.favoritoUsuario==null && other.getFavoritoUsuario()==null) || 
             (this.favoritoUsuario!=null &&
              java.util.Arrays.equals(this.favoritoUsuario, other.getFavoritoUsuario()))) &&
            ((this.contacto==null && other.getContacto()==null) || 
             (this.contacto!=null &&
              java.util.Arrays.equals(this.contacto, other.getContacto())));
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
        if (getFoto() != null) {
            _hashCode += getFoto().hashCode();
        }
        if (getMostrarObjetos() != null) {
            _hashCode += getMostrarObjetos().hashCode();
        }
        if (getMostrarFavoritos() != null) {
            _hashCode += getMostrarFavoritos().hashCode();
        }
        if (getMostrarGrupos() != null) {
            _hashCode += getMostrarGrupos().hashCode();
        }
        if (getCentroEducativo() != null) {
            _hashCode += getCentroEducativo().hashCode();
        }
        if (getRecibirCorreo() != null) {
            _hashCode += getRecibirCorreo().hashCode();
        }
        if (getUsuario() != null) {
            _hashCode += getUsuario().hashCode();
        }
        if (getContenidoImagen() != null) {
            _hashCode += getContenidoImagen().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getNombreUsuario() != null) {
            _hashCode += getNombreUsuario().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getActivo() != null) {
            _hashCode += getActivo().hashCode();
        }
        if (getGrupoPublico() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getGrupoPublico());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getGrupoPublico(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFavoritoUsuario() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFavoritoUsuario());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFavoritoUsuario(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getContacto() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getContacto());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getContacto(), i);
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
        new org.apache.axis.description.TypeDesc(UsuarioPublicoVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "UsuarioPublicoVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("foto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "foto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mostrarObjetos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "mostrarObjetos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mostrarFavoritos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "mostrarFavoritos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mostrarGrupos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "mostrarGrupos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("centroEducativo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "centroEducativo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recibirCorreo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "recibirCorreo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "usuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contenidoImagen");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "contenidoImagen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xml.apache.org/xml-soap", "DataHandler"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "nombreUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "activo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("grupoPublico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "grupoPublico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "GrupoPublicoVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("favoritoUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "favoritoUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "FavoritoVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contacto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "contacto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.adminusuarios.pode.es", "ContactoVO"));
        elemField.setNillable(true);
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
