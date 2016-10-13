/*
Agrega2 es una federaci髇 de repositorios de objetos digitales educativos formada por todas las Comunidades Aut髇omas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * RegistroCargaMasivaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.auditoria.negocio.servicios;


/**
 * Value object con informaci贸n de cada uno de los odes que se
 *                         cargan
 */
public class RegistroCargaMasivaVO  implements java.io.Serializable {
    /* formato del ode */
    private java.lang.String formato;

    /* Identificador mec que se ha dado al ode */
    private java.lang.String id_mec;

    /* Idioma en el que se ha indexado el ode */
    private java.lang.String idioma;

    /* Nivel de agregaci贸n del ode */
    private java.lang.String nivelAgregacion;

    /* Nombre del zip original que ten铆a el ode */
    private java.lang.String nombreZip;

    /* Indica si el ode ya estaba publicado anteriormente en la
     *                                 plataforma */
    private java.lang.Boolean sobrescrito;

    /* T铆tulo del ode */
    private java.lang.String titulo;

    /* Fecha en la que se ha cargado el ode */
    private java.lang.String fecha;

    /* C贸digo resultado de la publicaci贸n */
    private java.lang.String descripcion;

    /* Estado final del ODE (publicado, no publicado) */
    private java.lang.String estado;

    /* Path donde se encontrar谩 el zip */
    private java.lang.String pathZip;

    private java.lang.String rutaTaxonomica;

    private java.lang.String pathZip_OK;

    private java.lang.String pathZip_KO;

    public RegistroCargaMasivaVO() {
    }

    public RegistroCargaMasivaVO(
           java.lang.String formato,
           java.lang.String id_mec,
           java.lang.String idioma,
           java.lang.String nivelAgregacion,
           java.lang.String nombreZip,
           java.lang.Boolean sobrescrito,
           java.lang.String titulo,
           java.lang.String fecha,
           java.lang.String descripcion,
           java.lang.String estado,
           java.lang.String pathZip,
           java.lang.String rutaTaxonomica,
           java.lang.String pathZip_OK,
           java.lang.String pathZip_KO) {
           this.formato = formato;
           this.id_mec = id_mec;
           this.idioma = idioma;
           this.nivelAgregacion = nivelAgregacion;
           this.nombreZip = nombreZip;
           this.sobrescrito = sobrescrito;
           this.titulo = titulo;
           this.fecha = fecha;
           this.descripcion = descripcion;
           this.estado = estado;
           this.pathZip = pathZip;
           this.rutaTaxonomica = rutaTaxonomica;
           this.pathZip_OK = pathZip_OK;
           this.pathZip_KO = pathZip_KO;
    }


    /**
     * Gets the formato value for this RegistroCargaMasivaVO.
     * 
     * @return formato   * formato del ode
     */
    public java.lang.String getFormato() {
        return formato;
    }


    /**
     * Sets the formato value for this RegistroCargaMasivaVO.
     * 
     * @param formato   * formato del ode
     */
    public void setFormato(java.lang.String formato) {
        this.formato = formato;
    }


    /**
     * Gets the id_mec value for this RegistroCargaMasivaVO.
     * 
     * @return id_mec   * Identificador mec que se ha dado al ode
     */
    public java.lang.String getId_mec() {
        return id_mec;
    }


    /**
     * Sets the id_mec value for this RegistroCargaMasivaVO.
     * 
     * @param id_mec   * Identificador mec que se ha dado al ode
     */
    public void setId_mec(java.lang.String id_mec) {
        this.id_mec = id_mec;
    }


    /**
     * Gets the idioma value for this RegistroCargaMasivaVO.
     * 
     * @return idioma   * Idioma en el que se ha indexado el ode
     */
    public java.lang.String getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this RegistroCargaMasivaVO.
     * 
     * @param idioma   * Idioma en el que se ha indexado el ode
     */
    public void setIdioma(java.lang.String idioma) {
        this.idioma = idioma;
    }


    /**
     * Gets the nivelAgregacion value for this RegistroCargaMasivaVO.
     * 
     * @return nivelAgregacion   * Nivel de agregaci贸n del ode
     */
    public java.lang.String getNivelAgregacion() {
        return nivelAgregacion;
    }


    /**
     * Sets the nivelAgregacion value for this RegistroCargaMasivaVO.
     * 
     * @param nivelAgregacion   * Nivel de agregaci贸n del ode
     */
    public void setNivelAgregacion(java.lang.String nivelAgregacion) {
        this.nivelAgregacion = nivelAgregacion;
    }


    /**
     * Gets the nombreZip value for this RegistroCargaMasivaVO.
     * 
     * @return nombreZip   * Nombre del zip original que ten铆a el ode
     */
    public java.lang.String getNombreZip() {
        return nombreZip;
    }


    /**
     * Sets the nombreZip value for this RegistroCargaMasivaVO.
     * 
     * @param nombreZip   * Nombre del zip original que ten铆a el ode
     */
    public void setNombreZip(java.lang.String nombreZip) {
        this.nombreZip = nombreZip;
    }


    /**
     * Gets the sobrescrito value for this RegistroCargaMasivaVO.
     * 
     * @return sobrescrito   * Indica si el ode ya estaba publicado anteriormente en la
     *                                 plataforma
     */
    public java.lang.Boolean getSobrescrito() {
        return sobrescrito;
    }


    /**
     * Sets the sobrescrito value for this RegistroCargaMasivaVO.
     * 
     * @param sobrescrito   * Indica si el ode ya estaba publicado anteriormente en la
     *                                 plataforma
     */
    public void setSobrescrito(java.lang.Boolean sobrescrito) {
        this.sobrescrito = sobrescrito;
    }


    /**
     * Gets the titulo value for this RegistroCargaMasivaVO.
     * 
     * @return titulo   * T铆tulo del ode
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this RegistroCargaMasivaVO.
     * 
     * @param titulo   * T铆tulo del ode
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }


    /**
     * Gets the fecha value for this RegistroCargaMasivaVO.
     * 
     * @return fecha   * Fecha en la que se ha cargado el ode
     */
    public java.lang.String getFecha() {
        return fecha;
    }


    /**
     * Sets the fecha value for this RegistroCargaMasivaVO.
     * 
     * @param fecha   * Fecha en la que se ha cargado el ode
     */
    public void setFecha(java.lang.String fecha) {
        this.fecha = fecha;
    }


    /**
     * Gets the descripcion value for this RegistroCargaMasivaVO.
     * 
     * @return descripcion   * C贸digo resultado de la publicaci贸n
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this RegistroCargaMasivaVO.
     * 
     * @param descripcion   * C贸digo resultado de la publicaci贸n
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the estado value for this RegistroCargaMasivaVO.
     * 
     * @return estado   * Estado final del ODE (publicado, no publicado)
     */
    public java.lang.String getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this RegistroCargaMasivaVO.
     * 
     * @param estado   * Estado final del ODE (publicado, no publicado)
     */
    public void setEstado(java.lang.String estado) {
        this.estado = estado;
    }


    /**
     * Gets the pathZip value for this RegistroCargaMasivaVO.
     * 
     * @return pathZip   * Path donde se encontrar谩 el zip
     */
    public java.lang.String getPathZip() {
        return pathZip;
    }


    /**
     * Sets the pathZip value for this RegistroCargaMasivaVO.
     * 
     * @param pathZip   * Path donde se encontrar谩 el zip
     */
    public void setPathZip(java.lang.String pathZip) {
        this.pathZip = pathZip;
    }


    /**
     * Gets the rutaTaxonomica value for this RegistroCargaMasivaVO.
     * 
     * @return rutaTaxonomica
     */
    public java.lang.String getRutaTaxonomica() {
        return rutaTaxonomica;
    }


    /**
     * Sets the rutaTaxonomica value for this RegistroCargaMasivaVO.
     * 
     * @param rutaTaxonomica
     */
    public void setRutaTaxonomica(java.lang.String rutaTaxonomica) {
        this.rutaTaxonomica = rutaTaxonomica;
    }


    /**
     * Gets the pathZip_OK value for this RegistroCargaMasivaVO.
     * 
     * @return pathZip_OK
     */
    public java.lang.String getPathZip_OK() {
        return pathZip_OK;
    }


    /**
     * Sets the pathZip_OK value for this RegistroCargaMasivaVO.
     * 
     * @param pathZip_OK
     */
    public void setPathZip_OK(java.lang.String pathZip_OK) {
        this.pathZip_OK = pathZip_OK;
    }


    /**
     * Gets the pathZip_KO value for this RegistroCargaMasivaVO.
     * 
     * @return pathZip_KO
     */
    public java.lang.String getPathZip_KO() {
        return pathZip_KO;
    }


    /**
     * Sets the pathZip_KO value for this RegistroCargaMasivaVO.
     * 
     * @param pathZip_KO
     */
    public void setPathZip_KO(java.lang.String pathZip_KO) {
        this.pathZip_KO = pathZip_KO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RegistroCargaMasivaVO)) return false;
        RegistroCargaMasivaVO other = (RegistroCargaMasivaVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.formato==null && other.getFormato()==null) || 
             (this.formato!=null &&
              this.formato.equals(other.getFormato()))) &&
            ((this.id_mec==null && other.getId_mec()==null) || 
             (this.id_mec!=null &&
              this.id_mec.equals(other.getId_mec()))) &&
            ((this.idioma==null && other.getIdioma()==null) || 
             (this.idioma!=null &&
              this.idioma.equals(other.getIdioma()))) &&
            ((this.nivelAgregacion==null && other.getNivelAgregacion()==null) || 
             (this.nivelAgregacion!=null &&
              this.nivelAgregacion.equals(other.getNivelAgregacion()))) &&
            ((this.nombreZip==null && other.getNombreZip()==null) || 
             (this.nombreZip!=null &&
              this.nombreZip.equals(other.getNombreZip()))) &&
            ((this.sobrescrito==null && other.getSobrescrito()==null) || 
             (this.sobrescrito!=null &&
              this.sobrescrito.equals(other.getSobrescrito()))) &&
            ((this.titulo==null && other.getTitulo()==null) || 
             (this.titulo!=null &&
              this.titulo.equals(other.getTitulo()))) &&
            ((this.fecha==null && other.getFecha()==null) || 
             (this.fecha!=null &&
              this.fecha.equals(other.getFecha()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado()))) &&
            ((this.pathZip==null && other.getPathZip()==null) || 
             (this.pathZip!=null &&
              this.pathZip.equals(other.getPathZip()))) &&
            ((this.rutaTaxonomica==null && other.getRutaTaxonomica()==null) || 
             (this.rutaTaxonomica!=null &&
              this.rutaTaxonomica.equals(other.getRutaTaxonomica()))) &&
            ((this.pathZip_OK==null && other.getPathZip_OK()==null) || 
             (this.pathZip_OK!=null &&
              this.pathZip_OK.equals(other.getPathZip_OK()))) &&
            ((this.pathZip_KO==null && other.getPathZip_KO()==null) || 
             (this.pathZip_KO!=null &&
              this.pathZip_KO.equals(other.getPathZip_KO())));
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
        if (getFormato() != null) {
            _hashCode += getFormato().hashCode();
        }
        if (getId_mec() != null) {
            _hashCode += getId_mec().hashCode();
        }
        if (getIdioma() != null) {
            _hashCode += getIdioma().hashCode();
        }
        if (getNivelAgregacion() != null) {
            _hashCode += getNivelAgregacion().hashCode();
        }
        if (getNombreZip() != null) {
            _hashCode += getNombreZip().hashCode();
        }
        if (getSobrescrito() != null) {
            _hashCode += getSobrescrito().hashCode();
        }
        if (getTitulo() != null) {
            _hashCode += getTitulo().hashCode();
        }
        if (getFecha() != null) {
            _hashCode += getFecha().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        if (getPathZip() != null) {
            _hashCode += getPathZip().hashCode();
        }
        if (getRutaTaxonomica() != null) {
            _hashCode += getRutaTaxonomica().hashCode();
        }
        if (getPathZip_OK() != null) {
            _hashCode += getPathZip_OK().hashCode();
        }
        if (getPathZip_KO() != null) {
            _hashCode += getPathZip_KO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RegistroCargaMasivaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "RegistroCargaMasivaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formato");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "formato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id_mec");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "id_mec"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "idioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nivelAgregacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "nivelAgregacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreZip");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "nombreZip"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sobrescrito");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "sobrescrito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "titulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "fecha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathZip");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "pathZip"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rutaTaxonomica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "rutaTaxonomica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathZip_OK");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "pathZip_OK"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathZip_KO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "pathZip_KO"));
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
