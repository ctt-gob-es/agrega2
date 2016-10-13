/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * RecursoVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.empaquetador.negocio.servicio;


/**
 * Objeto que representa un recurso de SCORM2004. Es una version
 *                         simplificada de la clase Resource.
 */
public class RecursoVO  implements java.io.Serializable {
    /* Identificador del recurso. Se corresponde con el atributo
     *                                 identifier de la clase Resource. */
    private java.lang.String identifier;

    /* Fichero principal del recurso. Se corresponde con el atributo
     * href de la clase Resource. */
    private java.lang.String href;

    /* Tipo de recurso. Se corresponde con el atributo type de la
     * clase
     *                                 Resource. */
    private java.lang.String type;

    /* Tipo de recurso (ADL) */
    private java.lang.String scormType;

    /* Identificador del Manifest que contiene este recurso. */
    private java.lang.String idManifest;

    /* URL del fichero principal del recurso (localizador + bases
     * +
     *                                 href) */
    private java.lang.String recursoURL;

    /* Lista de ficheros que componen el recurso. Se corresponde con
     * el
     *                                 atributo file de la clase Resource. */
    private es.pode.empaquetador.negocio.servicio.FileVO[] fileList;

    private es.pode.empaquetador.negocio.servicio.DependenciaVO[] dependencias;

    private es.pode.empaquetador.negocio.servicio.LomVO lom;

    public RecursoVO() {
    }

    public RecursoVO(
           java.lang.String identifier,
           java.lang.String href,
           java.lang.String type,
           java.lang.String scormType,
           java.lang.String idManifest,
           java.lang.String recursoURL,
           es.pode.empaquetador.negocio.servicio.FileVO[] fileList,
           es.pode.empaquetador.negocio.servicio.DependenciaVO[] dependencias,
           es.pode.empaquetador.negocio.servicio.LomVO lom) {
           this.identifier = identifier;
           this.href = href;
           this.type = type;
           this.scormType = scormType;
           this.idManifest = idManifest;
           this.recursoURL = recursoURL;
           this.fileList = fileList;
           this.dependencias = dependencias;
           this.lom = lom;
    }


    /**
     * Gets the identifier value for this RecursoVO.
     * 
     * @return identifier   * Identificador del recurso. Se corresponde con el atributo
     *                                 identifier de la clase Resource.
     */
    public java.lang.String getIdentifier() {
        return identifier;
    }


    /**
     * Sets the identifier value for this RecursoVO.
     * 
     * @param identifier   * Identificador del recurso. Se corresponde con el atributo
     *                                 identifier de la clase Resource.
     */
    public void setIdentifier(java.lang.String identifier) {
        this.identifier = identifier;
    }


    /**
     * Gets the href value for this RecursoVO.
     * 
     * @return href   * Fichero principal del recurso. Se corresponde con el atributo
     * href de la clase Resource.
     */
    public java.lang.String getHref() {
        return href;
    }


    /**
     * Sets the href value for this RecursoVO.
     * 
     * @param href   * Fichero principal del recurso. Se corresponde con el atributo
     * href de la clase Resource.
     */
    public void setHref(java.lang.String href) {
        this.href = href;
    }


    /**
     * Gets the type value for this RecursoVO.
     * 
     * @return type   * Tipo de recurso. Se corresponde con el atributo type de la
     * clase
     *                                 Resource.
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the type value for this RecursoVO.
     * 
     * @param type   * Tipo de recurso. Se corresponde con el atributo type de la
     * clase
     *                                 Resource.
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }


    /**
     * Gets the scormType value for this RecursoVO.
     * 
     * @return scormType   * Tipo de recurso (ADL)
     */
    public java.lang.String getScormType() {
        return scormType;
    }


    /**
     * Sets the scormType value for this RecursoVO.
     * 
     * @param scormType   * Tipo de recurso (ADL)
     */
    public void setScormType(java.lang.String scormType) {
        this.scormType = scormType;
    }


    /**
     * Gets the idManifest value for this RecursoVO.
     * 
     * @return idManifest   * Identificador del Manifest que contiene este recurso.
     */
    public java.lang.String getIdManifest() {
        return idManifest;
    }


    /**
     * Sets the idManifest value for this RecursoVO.
     * 
     * @param idManifest   * Identificador del Manifest que contiene este recurso.
     */
    public void setIdManifest(java.lang.String idManifest) {
        this.idManifest = idManifest;
    }


    /**
     * Gets the recursoURL value for this RecursoVO.
     * 
     * @return recursoURL   * URL del fichero principal del recurso (localizador + bases
     * +
     *                                 href)
     */
    public java.lang.String getRecursoURL() {
        return recursoURL;
    }


    /**
     * Sets the recursoURL value for this RecursoVO.
     * 
     * @param recursoURL   * URL del fichero principal del recurso (localizador + bases
     * +
     *                                 href)
     */
    public void setRecursoURL(java.lang.String recursoURL) {
        this.recursoURL = recursoURL;
    }


    /**
     * Gets the fileList value for this RecursoVO.
     * 
     * @return fileList   * Lista de ficheros que componen el recurso. Se corresponde con
     * el
     *                                 atributo file de la clase Resource.
     */
    public es.pode.empaquetador.negocio.servicio.FileVO[] getFileList() {
        return fileList;
    }


    /**
     * Sets the fileList value for this RecursoVO.
     * 
     * @param fileList   * Lista de ficheros que componen el recurso. Se corresponde con
     * el
     *                                 atributo file de la clase Resource.
     */
    public void setFileList(es.pode.empaquetador.negocio.servicio.FileVO[] fileList) {
        this.fileList = fileList;
    }


    /**
     * Gets the dependencias value for this RecursoVO.
     * 
     * @return dependencias
     */
    public es.pode.empaquetador.negocio.servicio.DependenciaVO[] getDependencias() {
        return dependencias;
    }


    /**
     * Sets the dependencias value for this RecursoVO.
     * 
     * @param dependencias
     */
    public void setDependencias(es.pode.empaquetador.negocio.servicio.DependenciaVO[] dependencias) {
        this.dependencias = dependencias;
    }


    /**
     * Gets the lom value for this RecursoVO.
     * 
     * @return lom
     */
    public es.pode.empaquetador.negocio.servicio.LomVO getLom() {
        return lom;
    }


    /**
     * Sets the lom value for this RecursoVO.
     * 
     * @param lom
     */
    public void setLom(es.pode.empaquetador.negocio.servicio.LomVO lom) {
        this.lom = lom;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RecursoVO)) return false;
        RecursoVO other = (RecursoVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.identifier==null && other.getIdentifier()==null) || 
             (this.identifier!=null &&
              this.identifier.equals(other.getIdentifier()))) &&
            ((this.href==null && other.getHref()==null) || 
             (this.href!=null &&
              this.href.equals(other.getHref()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.scormType==null && other.getScormType()==null) || 
             (this.scormType!=null &&
              this.scormType.equals(other.getScormType()))) &&
            ((this.idManifest==null && other.getIdManifest()==null) || 
             (this.idManifest!=null &&
              this.idManifest.equals(other.getIdManifest()))) &&
            ((this.recursoURL==null && other.getRecursoURL()==null) || 
             (this.recursoURL!=null &&
              this.recursoURL.equals(other.getRecursoURL()))) &&
            ((this.fileList==null && other.getFileList()==null) || 
             (this.fileList!=null &&
              java.util.Arrays.equals(this.fileList, other.getFileList()))) &&
            ((this.dependencias==null && other.getDependencias()==null) || 
             (this.dependencias!=null &&
              java.util.Arrays.equals(this.dependencias, other.getDependencias()))) &&
            ((this.lom==null && other.getLom()==null) || 
             (this.lom!=null &&
              this.lom.equals(other.getLom())));
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
        if (getIdentifier() != null) {
            _hashCode += getIdentifier().hashCode();
        }
        if (getHref() != null) {
            _hashCode += getHref().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getScormType() != null) {
            _hashCode += getScormType().hashCode();
        }
        if (getIdManifest() != null) {
            _hashCode += getIdManifest().hashCode();
        }
        if (getRecursoURL() != null) {
            _hashCode += getRecursoURL().hashCode();
        }
        if (getFileList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFileList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFileList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDependencias() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDependencias());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDependencias(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getLom() != null) {
            _hashCode += getLom().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RecursoVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "RecursoVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identifier");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "identifier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("href");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "href"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scormType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "scormType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idManifest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "idManifest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recursoURL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "recursoURL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "fileList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "FileVO"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dependencias");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "dependencias"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "DependenciaVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lom");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "lom"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "LomVO"));
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
