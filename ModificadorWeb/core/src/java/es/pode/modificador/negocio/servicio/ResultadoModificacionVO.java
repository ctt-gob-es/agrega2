/**
 * ResultadoModificacionVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.modificador.negocio.servicio;

public class ResultadoModificacionVO  implements java.io.Serializable {
    private java.lang.String idOde;

    private es.pode.modificador.negocio.servicio.EstadosTarea status;

    private java.lang.String pathTraza;

    private java.lang.String pathBackup;

    private java.lang.String pathOriginal;

    private java.lang.String traza;

    private java.lang.Boolean publicado;

    private java.lang.String msgError;

    private java.lang.String titulo;

    private java.lang.Long id;

    private java.lang.Boolean esDescargable;

    private java.lang.String pathInforme;

    public ResultadoModificacionVO() {
    }

    public ResultadoModificacionVO(
           java.lang.String idOde,
           es.pode.modificador.negocio.servicio.EstadosTarea status,
           java.lang.String pathTraza,
           java.lang.String pathBackup,
           java.lang.String pathOriginal,
           java.lang.String traza,
           java.lang.Boolean publicado,
           java.lang.String msgError,
           java.lang.String titulo,
           java.lang.Long id,
           java.lang.Boolean esDescargable,
           java.lang.String pathInforme) {
           this.idOde = idOde;
           this.status = status;
           this.pathTraza = pathTraza;
           this.pathBackup = pathBackup;
           this.pathOriginal = pathOriginal;
           this.traza = traza;
           this.publicado = publicado;
           this.msgError = msgError;
           this.titulo = titulo;
           this.id = id;
           this.esDescargable = esDescargable;
           this.pathInforme = pathInforme;
    }


    /**
     * Gets the idOde value for this ResultadoModificacionVO.
     * 
     * @return idOde
     */
    public java.lang.String getIdOde() {
        return idOde;
    }


    /**
     * Sets the idOde value for this ResultadoModificacionVO.
     * 
     * @param idOde
     */
    public void setIdOde(java.lang.String idOde) {
        this.idOde = idOde;
    }


    /**
     * Gets the status value for this ResultadoModificacionVO.
     * 
     * @return status
     */
    public es.pode.modificador.negocio.servicio.EstadosTarea getStatus() {
        return status;
    }


    /**
     * Sets the status value for this ResultadoModificacionVO.
     * 
     * @param status
     */
    public void setStatus(es.pode.modificador.negocio.servicio.EstadosTarea status) {
        this.status = status;
    }


    /**
     * Gets the pathTraza value for this ResultadoModificacionVO.
     * 
     * @return pathTraza
     */
    public java.lang.String getPathTraza() {
        return pathTraza;
    }


    /**
     * Sets the pathTraza value for this ResultadoModificacionVO.
     * 
     * @param pathTraza
     */
    public void setPathTraza(java.lang.String pathTraza) {
        this.pathTraza = pathTraza;
    }


    /**
     * Gets the pathBackup value for this ResultadoModificacionVO.
     * 
     * @return pathBackup
     */
    public java.lang.String getPathBackup() {
        return pathBackup;
    }


    /**
     * Sets the pathBackup value for this ResultadoModificacionVO.
     * 
     * @param pathBackup
     */
    public void setPathBackup(java.lang.String pathBackup) {
        this.pathBackup = pathBackup;
    }


    /**
     * Gets the pathOriginal value for this ResultadoModificacionVO.
     * 
     * @return pathOriginal
     */
    public java.lang.String getPathOriginal() {
        return pathOriginal;
    }


    /**
     * Sets the pathOriginal value for this ResultadoModificacionVO.
     * 
     * @param pathOriginal
     */
    public void setPathOriginal(java.lang.String pathOriginal) {
        this.pathOriginal = pathOriginal;
    }


    /**
     * Gets the traza value for this ResultadoModificacionVO.
     * 
     * @return traza
     */
    public java.lang.String getTraza() {
        return traza;
    }


    /**
     * Sets the traza value for this ResultadoModificacionVO.
     * 
     * @param traza
     */
    public void setTraza(java.lang.String traza) {
        this.traza = traza;
    }


    /**
     * Gets the publicado value for this ResultadoModificacionVO.
     * 
     * @return publicado
     */
    public java.lang.Boolean getPublicado() {
        return publicado;
    }


    /**
     * Sets the publicado value for this ResultadoModificacionVO.
     * 
     * @param publicado
     */
    public void setPublicado(java.lang.Boolean publicado) {
        this.publicado = publicado;
    }


    /**
     * Gets the msgError value for this ResultadoModificacionVO.
     * 
     * @return msgError
     */
    public java.lang.String getMsgError() {
        return msgError;
    }


    /**
     * Sets the msgError value for this ResultadoModificacionVO.
     * 
     * @param msgError
     */
    public void setMsgError(java.lang.String msgError) {
        this.msgError = msgError;
    }


    /**
     * Gets the titulo value for this ResultadoModificacionVO.
     * 
     * @return titulo
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this ResultadoModificacionVO.
     * 
     * @param titulo
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }


    /**
     * Gets the id value for this ResultadoModificacionVO.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this ResultadoModificacionVO.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the esDescargable value for this ResultadoModificacionVO.
     * 
     * @return esDescargable
     */
    public java.lang.Boolean getEsDescargable() {
        return esDescargable;
    }


    /**
     * Sets the esDescargable value for this ResultadoModificacionVO.
     * 
     * @param esDescargable
     */
    public void setEsDescargable(java.lang.Boolean esDescargable) {
        this.esDescargable = esDescargable;
    }


    /**
     * Gets the pathInforme value for this ResultadoModificacionVO.
     * 
     * @return pathInforme
     */
    public java.lang.String getPathInforme() {
        return pathInforme;
    }


    /**
     * Sets the pathInforme value for this ResultadoModificacionVO.
     * 
     * @param pathInforme
     */
    public void setPathInforme(java.lang.String pathInforme) {
        this.pathInforme = pathInforme;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoModificacionVO)) return false;
        ResultadoModificacionVO other = (ResultadoModificacionVO) obj;
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
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.pathTraza==null && other.getPathTraza()==null) || 
             (this.pathTraza!=null &&
              this.pathTraza.equals(other.getPathTraza()))) &&
            ((this.pathBackup==null && other.getPathBackup()==null) || 
             (this.pathBackup!=null &&
              this.pathBackup.equals(other.getPathBackup()))) &&
            ((this.pathOriginal==null && other.getPathOriginal()==null) || 
             (this.pathOriginal!=null &&
              this.pathOriginal.equals(other.getPathOriginal()))) &&
            ((this.traza==null && other.getTraza()==null) || 
             (this.traza!=null &&
              this.traza.equals(other.getTraza()))) &&
            ((this.publicado==null && other.getPublicado()==null) || 
             (this.publicado!=null &&
              this.publicado.equals(other.getPublicado()))) &&
            ((this.msgError==null && other.getMsgError()==null) || 
             (this.msgError!=null &&
              this.msgError.equals(other.getMsgError()))) &&
            ((this.titulo==null && other.getTitulo()==null) || 
             (this.titulo!=null &&
              this.titulo.equals(other.getTitulo()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.esDescargable==null && other.getEsDescargable()==null) || 
             (this.esDescargable!=null &&
              this.esDescargable.equals(other.getEsDescargable()))) &&
            ((this.pathInforme==null && other.getPathInforme()==null) || 
             (this.pathInforme!=null &&
              this.pathInforme.equals(other.getPathInforme())));
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
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getPathTraza() != null) {
            _hashCode += getPathTraza().hashCode();
        }
        if (getPathBackup() != null) {
            _hashCode += getPathBackup().hashCode();
        }
        if (getPathOriginal() != null) {
            _hashCode += getPathOriginal().hashCode();
        }
        if (getTraza() != null) {
            _hashCode += getTraza().hashCode();
        }
        if (getPublicado() != null) {
            _hashCode += getPublicado().hashCode();
        }
        if (getMsgError() != null) {
            _hashCode += getMsgError().hashCode();
        }
        if (getTitulo() != null) {
            _hashCode += getTitulo().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getEsDescargable() != null) {
            _hashCode += getEsDescargable().hashCode();
        }
        if (getPathInforme() != null) {
            _hashCode += getPathInforme().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoModificacionVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "ResultadoModificacionVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idOde");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "idOde"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "EstadosTarea"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathTraza");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "pathTraza"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathBackup");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "pathBackup"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathOriginal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "pathOriginal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("traza");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "traza"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("publicado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "publicado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msgError");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "msgError"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "titulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esDescargable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "esDescargable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathInforme");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.modificador.pode.es", "pathInforme"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
