/**
 * ResultadoRepositorioVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.indexador.negocio.servicios.busqueda;


/**
 * Este objeto de valor almacena los datos relevantes de un ODE
 *                         indexado en el repositorio cuando es consultado
 * de forma masiva.
 */
public class ResultadoRepositorioVO  implements java.io.Serializable {
    /* Identificador alfanumerico del ODE. */
    private java.lang.String identificador;

    /* Idioma en el que esta indexado el ODE. */
    private java.lang.String idioma;

    /* Fecha en la que se publico el ODE. */
    private java.lang.String fechaPublicacion;

    /* Valor del nivel de agregacion del ODE. */
    private java.lang.String nivelAgregacion;

    /* Descripcion del ODE. */
    private java.lang.String descripcion;

    /* Titulo del ODE. */
    private java.lang.String titulo;

    /* Edad a la que va destinado el ODE. */
    private java.lang.String edad;

    /* URL de la imagen del ODE. */
    private java.lang.String urlImagen;

    /* Idioma del ODE traducido (al castellano). */
    private java.lang.String idiomaTexto;

    /* Objetivos del ODE */
    private java.lang.String[] objetivos;

    /* Lista de paths de areas curriculares en las que esta catalogado
     * el ODE. */
    private java.lang.String[] areaCurricularPath;

    public ResultadoRepositorioVO() {
    }

    public ResultadoRepositorioVO(
           java.lang.String identificador,
           java.lang.String idioma,
           java.lang.String fechaPublicacion,
           java.lang.String nivelAgregacion,
           java.lang.String descripcion,
           java.lang.String titulo,
           java.lang.String edad,
           java.lang.String urlImagen,
           java.lang.String idiomaTexto,
           java.lang.String[] objetivos,
           java.lang.String[] areaCurricularPath) {
           this.identificador = identificador;
           this.idioma = idioma;
           this.fechaPublicacion = fechaPublicacion;
           this.nivelAgregacion = nivelAgregacion;
           this.descripcion = descripcion;
           this.titulo = titulo;
           this.edad = edad;
           this.urlImagen = urlImagen;
           this.idiomaTexto = idiomaTexto;
           this.objetivos = objetivos;
           this.areaCurricularPath = areaCurricularPath;
    }


    /**
     * Gets the identificador value for this ResultadoRepositorioVO.
     * 
     * @return identificador   * Identificador alfanumerico del ODE.
     */
    public java.lang.String getIdentificador() {
        return identificador;
    }


    /**
     * Sets the identificador value for this ResultadoRepositorioVO.
     * 
     * @param identificador   * Identificador alfanumerico del ODE.
     */
    public void setIdentificador(java.lang.String identificador) {
        this.identificador = identificador;
    }


    /**
     * Gets the idioma value for this ResultadoRepositorioVO.
     * 
     * @return idioma   * Idioma en el que esta indexado el ODE.
     */
    public java.lang.String getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this ResultadoRepositorioVO.
     * 
     * @param idioma   * Idioma en el que esta indexado el ODE.
     */
    public void setIdioma(java.lang.String idioma) {
        this.idioma = idioma;
    }


    /**
     * Gets the fechaPublicacion value for this ResultadoRepositorioVO.
     * 
     * @return fechaPublicacion   * Fecha en la que se publico el ODE.
     */
    public java.lang.String getFechaPublicacion() {
        return fechaPublicacion;
    }


    /**
     * Sets the fechaPublicacion value for this ResultadoRepositorioVO.
     * 
     * @param fechaPublicacion   * Fecha en la que se publico el ODE.
     */
    public void setFechaPublicacion(java.lang.String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }


    /**
     * Gets the nivelAgregacion value for this ResultadoRepositorioVO.
     * 
     * @return nivelAgregacion   * Valor del nivel de agregacion del ODE.
     */
    public java.lang.String getNivelAgregacion() {
        return nivelAgregacion;
    }


    /**
     * Sets the nivelAgregacion value for this ResultadoRepositorioVO.
     * 
     * @param nivelAgregacion   * Valor del nivel de agregacion del ODE.
     */
    public void setNivelAgregacion(java.lang.String nivelAgregacion) {
        this.nivelAgregacion = nivelAgregacion;
    }


    /**
     * Gets the descripcion value for this ResultadoRepositorioVO.
     * 
     * @return descripcion   * Descripcion del ODE.
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this ResultadoRepositorioVO.
     * 
     * @param descripcion   * Descripcion del ODE.
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the titulo value for this ResultadoRepositorioVO.
     * 
     * @return titulo   * Titulo del ODE.
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this ResultadoRepositorioVO.
     * 
     * @param titulo   * Titulo del ODE.
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }


    /**
     * Gets the edad value for this ResultadoRepositorioVO.
     * 
     * @return edad   * Edad a la que va destinado el ODE.
     */
    public java.lang.String getEdad() {
        return edad;
    }


    /**
     * Sets the edad value for this ResultadoRepositorioVO.
     * 
     * @param edad   * Edad a la que va destinado el ODE.
     */
    public void setEdad(java.lang.String edad) {
        this.edad = edad;
    }


    /**
     * Gets the urlImagen value for this ResultadoRepositorioVO.
     * 
     * @return urlImagen   * URL de la imagen del ODE.
     */
    public java.lang.String getUrlImagen() {
        return urlImagen;
    }


    /**
     * Sets the urlImagen value for this ResultadoRepositorioVO.
     * 
     * @param urlImagen   * URL de la imagen del ODE.
     */
    public void setUrlImagen(java.lang.String urlImagen) {
        this.urlImagen = urlImagen;
    }


    /**
     * Gets the idiomaTexto value for this ResultadoRepositorioVO.
     * 
     * @return idiomaTexto   * Idioma del ODE traducido (al castellano).
     */
    public java.lang.String getIdiomaTexto() {
        return idiomaTexto;
    }


    /**
     * Sets the idiomaTexto value for this ResultadoRepositorioVO.
     * 
     * @param idiomaTexto   * Idioma del ODE traducido (al castellano).
     */
    public void setIdiomaTexto(java.lang.String idiomaTexto) {
        this.idiomaTexto = idiomaTexto;
    }


    /**
     * Gets the objetivos value for this ResultadoRepositorioVO.
     * 
     * @return objetivos   * Objetivos del ODE
     */
    public java.lang.String[] getObjetivos() {
        return objetivos;
    }


    /**
     * Sets the objetivos value for this ResultadoRepositorioVO.
     * 
     * @param objetivos   * Objetivos del ODE
     */
    public void setObjetivos(java.lang.String[] objetivos) {
        this.objetivos = objetivos;
    }


    /**
     * Gets the areaCurricularPath value for this ResultadoRepositorioVO.
     * 
     * @return areaCurricularPath   * Lista de paths de areas curriculares en las que esta catalogado
     * el ODE.
     */
    public java.lang.String[] getAreaCurricularPath() {
        return areaCurricularPath;
    }


    /**
     * Sets the areaCurricularPath value for this ResultadoRepositorioVO.
     * 
     * @param areaCurricularPath   * Lista de paths de areas curriculares en las que esta catalogado
     * el ODE.
     */
    public void setAreaCurricularPath(java.lang.String[] areaCurricularPath) {
        this.areaCurricularPath = areaCurricularPath;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoRepositorioVO)) return false;
        ResultadoRepositorioVO other = (ResultadoRepositorioVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.identificador==null && other.getIdentificador()==null) || 
             (this.identificador!=null &&
              this.identificador.equals(other.getIdentificador()))) &&
            ((this.idioma==null && other.getIdioma()==null) || 
             (this.idioma!=null &&
              this.idioma.equals(other.getIdioma()))) &&
            ((this.fechaPublicacion==null && other.getFechaPublicacion()==null) || 
             (this.fechaPublicacion!=null &&
              this.fechaPublicacion.equals(other.getFechaPublicacion()))) &&
            ((this.nivelAgregacion==null && other.getNivelAgregacion()==null) || 
             (this.nivelAgregacion!=null &&
              this.nivelAgregacion.equals(other.getNivelAgregacion()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.titulo==null && other.getTitulo()==null) || 
             (this.titulo!=null &&
              this.titulo.equals(other.getTitulo()))) &&
            ((this.edad==null && other.getEdad()==null) || 
             (this.edad!=null &&
              this.edad.equals(other.getEdad()))) &&
            ((this.urlImagen==null && other.getUrlImagen()==null) || 
             (this.urlImagen!=null &&
              this.urlImagen.equals(other.getUrlImagen()))) &&
            ((this.idiomaTexto==null && other.getIdiomaTexto()==null) || 
             (this.idiomaTexto!=null &&
              this.idiomaTexto.equals(other.getIdiomaTexto()))) &&
            ((this.objetivos==null && other.getObjetivos()==null) || 
             (this.objetivos!=null &&
              java.util.Arrays.equals(this.objetivos, other.getObjetivos()))) &&
            ((this.areaCurricularPath==null && other.getAreaCurricularPath()==null) || 
             (this.areaCurricularPath!=null &&
              java.util.Arrays.equals(this.areaCurricularPath, other.getAreaCurricularPath())));
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
        if (getIdentificador() != null) {
            _hashCode += getIdentificador().hashCode();
        }
        if (getIdioma() != null) {
            _hashCode += getIdioma().hashCode();
        }
        if (getFechaPublicacion() != null) {
            _hashCode += getFechaPublicacion().hashCode();
        }
        if (getNivelAgregacion() != null) {
            _hashCode += getNivelAgregacion().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getTitulo() != null) {
            _hashCode += getTitulo().hashCode();
        }
        if (getEdad() != null) {
            _hashCode += getEdad().hashCode();
        }
        if (getUrlImagen() != null) {
            _hashCode += getUrlImagen().hashCode();
        }
        if (getIdiomaTexto() != null) {
            _hashCode += getIdiomaTexto().hashCode();
        }
        if (getObjetivos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getObjetivos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getObjetivos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAreaCurricularPath() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAreaCurricularPath());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAreaCurricularPath(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoRepositorioVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "ResultadoRepositorioVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "identificador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "idioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaPublicacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "fechaPublicacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nivelAgregacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "nivelAgregacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "titulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("edad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "edad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("urlImagen");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "urlImagen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idiomaTexto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "idiomaTexto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objetivos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "objetivos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("areaCurricularPath");
        elemField.setXmlName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "areaCurricularPath"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://busqueda.servicios.negocio.indexador.pode.es", "item"));
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
