/**
 * ResultadoCargaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.planificador.negocio.servicios;


/**
 * Vo para recoger los datos que nos devuelve
 *                         ResultadoOperacionCargaVO
 */
public class ResultadoCargaVO  implements java.io.Serializable {
    /* Identificador que nos indica si ha ido bien la tarea */
    private java.lang.String idResultado;

    /* Descripción de la tarea */
    private java.lang.String descripcion;

    /* El identificador del ode */
    private java.lang.String idODE;

    /* El tamaño del ode */
    private java.lang.Long tamainoODE;

    /* El título del ode */
    private java.lang.String tituloODE;

    /* El idioma del ode */
    private java.lang.String idioma;

    /* El nivel de agregación del ode */
    private java.lang.String nivelAgregacion;

    /* Los formatos del ode; Están concatenados por comas */
    private java.lang.String formato;

    /* Nos indica si el ode ha sido sobrescrito */
    private java.lang.String sobrescrito;

    /* El nombre del zip */
    private java.lang.String nombreZip;

    /* El path donde se encuentra el ode, será relativo al path de
     * los
     *                                 odes de entrada( es decir, esto nos
     * indicará si estaba en una
     *                                 carpeta interna del path general que
     * nos dan) */
    private java.lang.String pathZip;

    /* La ruta taxonomica del ode a publicar */
    private java.lang.String rutaTaxonomica;

    public ResultadoCargaVO() {
    }

    public ResultadoCargaVO(
           java.lang.String idResultado,
           java.lang.String descripcion,
           java.lang.String idODE,
           java.lang.Long tamainoODE,
           java.lang.String tituloODE,
           java.lang.String idioma,
           java.lang.String nivelAgregacion,
           java.lang.String formato,
           java.lang.String sobrescrito,
           java.lang.String nombreZip,
           java.lang.String pathZip,
           java.lang.String rutaTaxonomica) {
           this.idResultado = idResultado;
           this.descripcion = descripcion;
           this.idODE = idODE;
           this.tamainoODE = tamainoODE;
           this.tituloODE = tituloODE;
           this.idioma = idioma;
           this.nivelAgregacion = nivelAgregacion;
           this.formato = formato;
           this.sobrescrito = sobrescrito;
           this.nombreZip = nombreZip;
           this.pathZip = pathZip;
           this.rutaTaxonomica = rutaTaxonomica;
    }


    /**
     * Gets the idResultado value for this ResultadoCargaVO.
     * 
     * @return idResultado   * Identificador que nos indica si ha ido bien la tarea
     */
    public java.lang.String getIdResultado() {
        return idResultado;
    }


    /**
     * Sets the idResultado value for this ResultadoCargaVO.
     * 
     * @param idResultado   * Identificador que nos indica si ha ido bien la tarea
     */
    public void setIdResultado(java.lang.String idResultado) {
        this.idResultado = idResultado;
    }


    /**
     * Gets the descripcion value for this ResultadoCargaVO.
     * 
     * @return descripcion   * Descripción de la tarea
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this ResultadoCargaVO.
     * 
     * @param descripcion   * Descripción de la tarea
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the idODE value for this ResultadoCargaVO.
     * 
     * @return idODE   * El identificador del ode
     */
    public java.lang.String getIdODE() {
        return idODE;
    }


    /**
     * Sets the idODE value for this ResultadoCargaVO.
     * 
     * @param idODE   * El identificador del ode
     */
    public void setIdODE(java.lang.String idODE) {
        this.idODE = idODE;
    }


    /**
     * Gets the tamainoODE value for this ResultadoCargaVO.
     * 
     * @return tamainoODE   * El tamaño del ode
     */
    public java.lang.Long getTamainoODE() {
        return tamainoODE;
    }


    /**
     * Sets the tamainoODE value for this ResultadoCargaVO.
     * 
     * @param tamainoODE   * El tamaño del ode
     */
    public void setTamainoODE(java.lang.Long tamainoODE) {
        this.tamainoODE = tamainoODE;
    }


    /**
     * Gets the tituloODE value for this ResultadoCargaVO.
     * 
     * @return tituloODE   * El título del ode
     */
    public java.lang.String getTituloODE() {
        return tituloODE;
    }


    /**
     * Sets the tituloODE value for this ResultadoCargaVO.
     * 
     * @param tituloODE   * El título del ode
     */
    public void setTituloODE(java.lang.String tituloODE) {
        this.tituloODE = tituloODE;
    }


    /**
     * Gets the idioma value for this ResultadoCargaVO.
     * 
     * @return idioma   * El idioma del ode
     */
    public java.lang.String getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this ResultadoCargaVO.
     * 
     * @param idioma   * El idioma del ode
     */
    public void setIdioma(java.lang.String idioma) {
        this.idioma = idioma;
    }


    /**
     * Gets the nivelAgregacion value for this ResultadoCargaVO.
     * 
     * @return nivelAgregacion   * El nivel de agregación del ode
     */
    public java.lang.String getNivelAgregacion() {
        return nivelAgregacion;
    }


    /**
     * Sets the nivelAgregacion value for this ResultadoCargaVO.
     * 
     * @param nivelAgregacion   * El nivel de agregación del ode
     */
    public void setNivelAgregacion(java.lang.String nivelAgregacion) {
        this.nivelAgregacion = nivelAgregacion;
    }


    /**
     * Gets the formato value for this ResultadoCargaVO.
     * 
     * @return formato   * Los formatos del ode; Están concatenados por comas
     */
    public java.lang.String getFormato() {
        return formato;
    }


    /**
     * Sets the formato value for this ResultadoCargaVO.
     * 
     * @param formato   * Los formatos del ode; Están concatenados por comas
     */
    public void setFormato(java.lang.String formato) {
        this.formato = formato;
    }


    /**
     * Gets the sobrescrito value for this ResultadoCargaVO.
     * 
     * @return sobrescrito   * Nos indica si el ode ha sido sobrescrito
     */
    public java.lang.String getSobrescrito() {
        return sobrescrito;
    }


    /**
     * Sets the sobrescrito value for this ResultadoCargaVO.
     * 
     * @param sobrescrito   * Nos indica si el ode ha sido sobrescrito
     */
    public void setSobrescrito(java.lang.String sobrescrito) {
        this.sobrescrito = sobrescrito;
    }


    /**
     * Gets the nombreZip value for this ResultadoCargaVO.
     * 
     * @return nombreZip   * El nombre del zip
     */
    public java.lang.String getNombreZip() {
        return nombreZip;
    }


    /**
     * Sets the nombreZip value for this ResultadoCargaVO.
     * 
     * @param nombreZip   * El nombre del zip
     */
    public void setNombreZip(java.lang.String nombreZip) {
        this.nombreZip = nombreZip;
    }


    /**
     * Gets the pathZip value for this ResultadoCargaVO.
     * 
     * @return pathZip   * El path donde se encuentra el ode, será relativo al path de
     * los
     *                                 odes de entrada( es decir, esto nos
     * indicará si estaba en una
     *                                 carpeta interna del path general que
     * nos dan)
     */
    public java.lang.String getPathZip() {
        return pathZip;
    }


    /**
     * Sets the pathZip value for this ResultadoCargaVO.
     * 
     * @param pathZip   * El path donde se encuentra el ode, será relativo al path de
     * los
     *                                 odes de entrada( es decir, esto nos
     * indicará si estaba en una
     *                                 carpeta interna del path general que
     * nos dan)
     */
    public void setPathZip(java.lang.String pathZip) {
        this.pathZip = pathZip;
    }


    /**
     * Gets the rutaTaxonomica value for this ResultadoCargaVO.
     * 
     * @return rutaTaxonomica   * La ruta taxonomica del ode a publicar
     */
    public java.lang.String getRutaTaxonomica() {
        return rutaTaxonomica;
    }


    /**
     * Sets the rutaTaxonomica value for this ResultadoCargaVO.
     * 
     * @param rutaTaxonomica   * La ruta taxonomica del ode a publicar
     */
    public void setRutaTaxonomica(java.lang.String rutaTaxonomica) {
        this.rutaTaxonomica = rutaTaxonomica;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoCargaVO)) return false;
        ResultadoCargaVO other = (ResultadoCargaVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idResultado==null && other.getIdResultado()==null) || 
             (this.idResultado!=null &&
              this.idResultado.equals(other.getIdResultado()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.idODE==null && other.getIdODE()==null) || 
             (this.idODE!=null &&
              this.idODE.equals(other.getIdODE()))) &&
            ((this.tamainoODE==null && other.getTamainoODE()==null) || 
             (this.tamainoODE!=null &&
              this.tamainoODE.equals(other.getTamainoODE()))) &&
            ((this.tituloODE==null && other.getTituloODE()==null) || 
             (this.tituloODE!=null &&
              this.tituloODE.equals(other.getTituloODE()))) &&
            ((this.idioma==null && other.getIdioma()==null) || 
             (this.idioma!=null &&
              this.idioma.equals(other.getIdioma()))) &&
            ((this.nivelAgregacion==null && other.getNivelAgregacion()==null) || 
             (this.nivelAgregacion!=null &&
              this.nivelAgregacion.equals(other.getNivelAgregacion()))) &&
            ((this.formato==null && other.getFormato()==null) || 
             (this.formato!=null &&
              this.formato.equals(other.getFormato()))) &&
            ((this.sobrescrito==null && other.getSobrescrito()==null) || 
             (this.sobrescrito!=null &&
              this.sobrescrito.equals(other.getSobrescrito()))) &&
            ((this.nombreZip==null && other.getNombreZip()==null) || 
             (this.nombreZip!=null &&
              this.nombreZip.equals(other.getNombreZip()))) &&
            ((this.pathZip==null && other.getPathZip()==null) || 
             (this.pathZip!=null &&
              this.pathZip.equals(other.getPathZip()))) &&
            ((this.rutaTaxonomica==null && other.getRutaTaxonomica()==null) || 
             (this.rutaTaxonomica!=null &&
              this.rutaTaxonomica.equals(other.getRutaTaxonomica())));
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
        if (getIdResultado() != null) {
            _hashCode += getIdResultado().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getIdODE() != null) {
            _hashCode += getIdODE().hashCode();
        }
        if (getTamainoODE() != null) {
            _hashCode += getTamainoODE().hashCode();
        }
        if (getTituloODE() != null) {
            _hashCode += getTituloODE().hashCode();
        }
        if (getIdioma() != null) {
            _hashCode += getIdioma().hashCode();
        }
        if (getNivelAgregacion() != null) {
            _hashCode += getNivelAgregacion().hashCode();
        }
        if (getFormato() != null) {
            _hashCode += getFormato().hashCode();
        }
        if (getSobrescrito() != null) {
            _hashCode += getSobrescrito().hashCode();
        }
        if (getNombreZip() != null) {
            _hashCode += getNombreZip().hashCode();
        }
        if (getPathZip() != null) {
            _hashCode += getPathZip().hashCode();
        }
        if (getRutaTaxonomica() != null) {
            _hashCode += getRutaTaxonomica().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoCargaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "ResultadoCargaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idResultado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "idResultado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "idODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tamainoODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "tamainoODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tituloODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "tituloODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "idioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nivelAgregacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "nivelAgregacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formato");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "formato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sobrescrito");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "sobrescrito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreZip");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "nombreZip"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathZip");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "pathZip"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rutaTaxonomica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "rutaTaxonomica"));
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
