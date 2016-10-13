/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * IdentifyVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.oaipmh.negocio.servicios;


/**
 * Value Object con el resultado de la llamada del método Identify.
 * Se puede inlcuir un campo descripción, pero para nuestro
 *                         repositorio no se va a implementar. Para su
 * implementacion
 *                         consultar:
 *                         http://www.openarchives.org/OAI/2.0/guidelines.htm
 */
public class IdentifyVO  implements java.io.Serializable {
    /* Nombre del repositorio */
    private java.lang.String nombreRepositorio;

    /* Url base del repositorio */
    private java.lang.String urlRepositorio;

    /* Versión del protocolo oai-pmh */
    private java.lang.String versionProtocolo;

    /* Fecha de inicio del repositorio */
    private java.lang.String fechaInicioRepositorio;

    /* Política de borrado que sigue el repositorio, puede tener los
     * siguientes valores: no, transient, persistent */
    private java.lang.String politicaBorrado;

    /* Formato de fechas utilizadas para filtrar las búsquedas en
     * el
     *                                 repositorio */
    private java.lang.String temporalidad;

    /* Email del administrador del repositorio. ¿En nuestro caso será
     * el mismo que el correo de un administrador de la aplicación? */
    private java.lang.String emailAdmin;

    /* Descripcion del identificador OAI. Contiene tres
     *                                 características:
     *                                 Esquema: oai
     *                                 Identificador del repositorio: agrega.es
     * Delimitador:":"
     *                                 Ejemplo del identificador */
    private es.pode.oaipmh.negocio.servicios.DescripcionOaiIdentifierVO descripcionOaiIdentifier;

    public IdentifyVO() {
    }

    public IdentifyVO(
           java.lang.String nombreRepositorio,
           java.lang.String urlRepositorio,
           java.lang.String versionProtocolo,
           java.lang.String fechaInicioRepositorio,
           java.lang.String politicaBorrado,
           java.lang.String temporalidad,
           java.lang.String emailAdmin,
           es.pode.oaipmh.negocio.servicios.DescripcionOaiIdentifierVO descripcionOaiIdentifier) {
           this.nombreRepositorio = nombreRepositorio;
           this.urlRepositorio = urlRepositorio;
           this.versionProtocolo = versionProtocolo;
           this.fechaInicioRepositorio = fechaInicioRepositorio;
           this.politicaBorrado = politicaBorrado;
           this.temporalidad = temporalidad;
           this.emailAdmin = emailAdmin;
           this.descripcionOaiIdentifier = descripcionOaiIdentifier;
    }


    /**
     * Gets the nombreRepositorio value for this IdentifyVO.
     * 
     * @return nombreRepositorio   * Nombre del repositorio
     */
    public java.lang.String getNombreRepositorio() {
        return nombreRepositorio;
    }


    /**
     * Sets the nombreRepositorio value for this IdentifyVO.
     * 
     * @param nombreRepositorio   * Nombre del repositorio
     */
    public void setNombreRepositorio(java.lang.String nombreRepositorio) {
        this.nombreRepositorio = nombreRepositorio;
    }


    /**
     * Gets the urlRepositorio value for this IdentifyVO.
     * 
     * @return urlRepositorio   * Url base del repositorio
     */
    public java.lang.String getUrlRepositorio() {
        return urlRepositorio;
    }


    /**
     * Sets the urlRepositorio value for this IdentifyVO.
     * 
     * @param urlRepositorio   * Url base del repositorio
     */
    public void setUrlRepositorio(java.lang.String urlRepositorio) {
        this.urlRepositorio = urlRepositorio;
    }


    /**
     * Gets the versionProtocolo value for this IdentifyVO.
     * 
     * @return versionProtocolo   * Versión del protocolo oai-pmh
     */
    public java.lang.String getVersionProtocolo() {
        return versionProtocolo;
    }


    /**
     * Sets the versionProtocolo value for this IdentifyVO.
     * 
     * @param versionProtocolo   * Versión del protocolo oai-pmh
     */
    public void setVersionProtocolo(java.lang.String versionProtocolo) {
        this.versionProtocolo = versionProtocolo;
    }


    /**
     * Gets the fechaInicioRepositorio value for this IdentifyVO.
     * 
     * @return fechaInicioRepositorio   * Fecha de inicio del repositorio
     */
    public java.lang.String getFechaInicioRepositorio() {
        return fechaInicioRepositorio;
    }


    /**
     * Sets the fechaInicioRepositorio value for this IdentifyVO.
     * 
     * @param fechaInicioRepositorio   * Fecha de inicio del repositorio
     */
    public void setFechaInicioRepositorio(java.lang.String fechaInicioRepositorio) {
        this.fechaInicioRepositorio = fechaInicioRepositorio;
    }


    /**
     * Gets the politicaBorrado value for this IdentifyVO.
     * 
     * @return politicaBorrado   * Política de borrado que sigue el repositorio, puede tener los
     * siguientes valores: no, transient, persistent
     */
    public java.lang.String getPoliticaBorrado() {
        return politicaBorrado;
    }


    /**
     * Sets the politicaBorrado value for this IdentifyVO.
     * 
     * @param politicaBorrado   * Política de borrado que sigue el repositorio, puede tener los
     * siguientes valores: no, transient, persistent
     */
    public void setPoliticaBorrado(java.lang.String politicaBorrado) {
        this.politicaBorrado = politicaBorrado;
    }


    /**
     * Gets the temporalidad value for this IdentifyVO.
     * 
     * @return temporalidad   * Formato de fechas utilizadas para filtrar las búsquedas en
     * el
     *                                 repositorio
     */
    public java.lang.String getTemporalidad() {
        return temporalidad;
    }


    /**
     * Sets the temporalidad value for this IdentifyVO.
     * 
     * @param temporalidad   * Formato de fechas utilizadas para filtrar las búsquedas en
     * el
     *                                 repositorio
     */
    public void setTemporalidad(java.lang.String temporalidad) {
        this.temporalidad = temporalidad;
    }


    /**
     * Gets the emailAdmin value for this IdentifyVO.
     * 
     * @return emailAdmin   * Email del administrador del repositorio. ¿En nuestro caso será
     * el mismo que el correo de un administrador de la aplicación?
     */
    public java.lang.String getEmailAdmin() {
        return emailAdmin;
    }


    /**
     * Sets the emailAdmin value for this IdentifyVO.
     * 
     * @param emailAdmin   * Email del administrador del repositorio. ¿En nuestro caso será
     * el mismo que el correo de un administrador de la aplicación?
     */
    public void setEmailAdmin(java.lang.String emailAdmin) {
        this.emailAdmin = emailAdmin;
    }


    /**
     * Gets the descripcionOaiIdentifier value for this IdentifyVO.
     * 
     * @return descripcionOaiIdentifier   * Descripcion del identificador OAI. Contiene tres
     *                                 características:
     *                                 Esquema: oai
     *                                 Identificador del repositorio: agrega.es
     * Delimitador:":"
     *                                 Ejemplo del identificador
     */
    public es.pode.oaipmh.negocio.servicios.DescripcionOaiIdentifierVO getDescripcionOaiIdentifier() {
        return descripcionOaiIdentifier;
    }


    /**
     * Sets the descripcionOaiIdentifier value for this IdentifyVO.
     * 
     * @param descripcionOaiIdentifier   * Descripcion del identificador OAI. Contiene tres
     *                                 características:
     *                                 Esquema: oai
     *                                 Identificador del repositorio: agrega.es
     * Delimitador:":"
     *                                 Ejemplo del identificador
     */
    public void setDescripcionOaiIdentifier(es.pode.oaipmh.negocio.servicios.DescripcionOaiIdentifierVO descripcionOaiIdentifier) {
        this.descripcionOaiIdentifier = descripcionOaiIdentifier;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IdentifyVO)) return false;
        IdentifyVO other = (IdentifyVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nombreRepositorio==null && other.getNombreRepositorio()==null) || 
             (this.nombreRepositorio!=null &&
              this.nombreRepositorio.equals(other.getNombreRepositorio()))) &&
            ((this.urlRepositorio==null && other.getUrlRepositorio()==null) || 
             (this.urlRepositorio!=null &&
              this.urlRepositorio.equals(other.getUrlRepositorio()))) &&
            ((this.versionProtocolo==null && other.getVersionProtocolo()==null) || 
             (this.versionProtocolo!=null &&
              this.versionProtocolo.equals(other.getVersionProtocolo()))) &&
            ((this.fechaInicioRepositorio==null && other.getFechaInicioRepositorio()==null) || 
             (this.fechaInicioRepositorio!=null &&
              this.fechaInicioRepositorio.equals(other.getFechaInicioRepositorio()))) &&
            ((this.politicaBorrado==null && other.getPoliticaBorrado()==null) || 
             (this.politicaBorrado!=null &&
              this.politicaBorrado.equals(other.getPoliticaBorrado()))) &&
            ((this.temporalidad==null && other.getTemporalidad()==null) || 
             (this.temporalidad!=null &&
              this.temporalidad.equals(other.getTemporalidad()))) &&
            ((this.emailAdmin==null && other.getEmailAdmin()==null) || 
             (this.emailAdmin!=null &&
              this.emailAdmin.equals(other.getEmailAdmin()))) &&
            ((this.descripcionOaiIdentifier==null && other.getDescripcionOaiIdentifier()==null) || 
             (this.descripcionOaiIdentifier!=null &&
              this.descripcionOaiIdentifier.equals(other.getDescripcionOaiIdentifier())));
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
        if (getNombreRepositorio() != null) {
            _hashCode += getNombreRepositorio().hashCode();
        }
        if (getUrlRepositorio() != null) {
            _hashCode += getUrlRepositorio().hashCode();
        }
        if (getVersionProtocolo() != null) {
            _hashCode += getVersionProtocolo().hashCode();
        }
        if (getFechaInicioRepositorio() != null) {
            _hashCode += getFechaInicioRepositorio().hashCode();
        }
        if (getPoliticaBorrado() != null) {
            _hashCode += getPoliticaBorrado().hashCode();
        }
        if (getTemporalidad() != null) {
            _hashCode += getTemporalidad().hashCode();
        }
        if (getEmailAdmin() != null) {
            _hashCode += getEmailAdmin().hashCode();
        }
        if (getDescripcionOaiIdentifier() != null) {
            _hashCode += getDescripcionOaiIdentifier().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IdentifyVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "IdentifyVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreRepositorio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "nombreRepositorio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("urlRepositorio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "urlRepositorio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("versionProtocolo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "versionProtocolo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaInicioRepositorio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "fechaInicioRepositorio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("politicaBorrado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "politicaBorrado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("temporalidad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "temporalidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailAdmin");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "emailAdmin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcionOaiIdentifier");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "descripcionOaiIdentifier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.oaipmh.pode.es", "DescripcionOaiIdentifierVO"));
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
