/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * RepositorioVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.auditoria.negocio.servicios;


/**
 * VO con la información de cada uno de los contenidos que aparecen
 * en el repositorio. Se utilizará para obtener el contenido del
 *                         repositorio
 */
public class RepositorioVO  implements java.io.Serializable {
    /* Identificador del ode */
    private java.lang.String identificador;

    /* Título del ode */
    private java.lang.String titulo;

    /* Url con la imagen del ode */
    private java.lang.String urlImagen;

    /* Idioma del índice donde se encontrará ese contenido */
    private java.lang.String idioma;

    private java.lang.String fechaPublicacion;

    private java.lang.String nivelAgregacion;

    private java.lang.String descripcion;

    private java.lang.String edad;

    /* Cadena con el idioma en el que se encuentra ese contenido.
     *                                 Vendrá traducido en castellano por
     * defecto */
    private java.lang.String idiomaTexto;

    /* Objetivo del ode */
    private java.lang.String objetivos;

    /* Identificador del primer nivel del árbol curricular. Se utiliza
     * para la categorización en el catálogo */
    private java.lang.String nivel1Cobertura;

    /* Identificador del segundo nivel del área curricular. Se utiliza
     * para la categorización en el catálogo */
    private java.lang.String nivel2Cobertura;

    /* Nombre del primer nivel del árbol curricular */
    private java.lang.String nombreNivel1Cobertura;

    /* Nombre del segundo nivel del árbol curricular */
    private java.lang.String nombreNivel2Cobertura;

    public RepositorioVO() {
    }

    public RepositorioVO(
           java.lang.String identificador,
           java.lang.String titulo,
           java.lang.String urlImagen,
           java.lang.String idioma,
           java.lang.String fechaPublicacion,
           java.lang.String nivelAgregacion,
           java.lang.String descripcion,
           java.lang.String edad,
           java.lang.String idiomaTexto,
           java.lang.String objetivos,
           java.lang.String nivel1Cobertura,
           java.lang.String nivel2Cobertura,
           java.lang.String nombreNivel1Cobertura,
           java.lang.String nombreNivel2Cobertura) {
           this.identificador = identificador;
           this.titulo = titulo;
           this.urlImagen = urlImagen;
           this.idioma = idioma;
           this.fechaPublicacion = fechaPublicacion;
           this.nivelAgregacion = nivelAgregacion;
           this.descripcion = descripcion;
           this.edad = edad;
           this.idiomaTexto = idiomaTexto;
           this.objetivos = objetivos;
           this.nivel1Cobertura = nivel1Cobertura;
           this.nivel2Cobertura = nivel2Cobertura;
           this.nombreNivel1Cobertura = nombreNivel1Cobertura;
           this.nombreNivel2Cobertura = nombreNivel2Cobertura;
    }


    /**
     * Gets the identificador value for this RepositorioVO.
     * 
     * @return identificador   * Identificador del ode
     */
    public java.lang.String getIdentificador() {
        return identificador;
    }


    /**
     * Sets the identificador value for this RepositorioVO.
     * 
     * @param identificador   * Identificador del ode
     */
    public void setIdentificador(java.lang.String identificador) {
        this.identificador = identificador;
    }


    /**
     * Gets the titulo value for this RepositorioVO.
     * 
     * @return titulo   * Título del ode
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this RepositorioVO.
     * 
     * @param titulo   * Título del ode
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }


    /**
     * Gets the urlImagen value for this RepositorioVO.
     * 
     * @return urlImagen   * Url con la imagen del ode
     */
    public java.lang.String getUrlImagen() {
        return urlImagen;
    }


    /**
     * Sets the urlImagen value for this RepositorioVO.
     * 
     * @param urlImagen   * Url con la imagen del ode
     */
    public void setUrlImagen(java.lang.String urlImagen) {
        this.urlImagen = urlImagen;
    }


    /**
     * Gets the idioma value for this RepositorioVO.
     * 
     * @return idioma   * Idioma del índice donde se encontrará ese contenido
     */
    public java.lang.String getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this RepositorioVO.
     * 
     * @param idioma   * Idioma del índice donde se encontrará ese contenido
     */
    public void setIdioma(java.lang.String idioma) {
        this.idioma = idioma;
    }


    /**
     * Gets the fechaPublicacion value for this RepositorioVO.
     * 
     * @return fechaPublicacion
     */
    public java.lang.String getFechaPublicacion() {
        return fechaPublicacion;
    }


    /**
     * Sets the fechaPublicacion value for this RepositorioVO.
     * 
     * @param fechaPublicacion
     */
    public void setFechaPublicacion(java.lang.String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }


    /**
     * Gets the nivelAgregacion value for this RepositorioVO.
     * 
     * @return nivelAgregacion
     */
    public java.lang.String getNivelAgregacion() {
        return nivelAgregacion;
    }


    /**
     * Sets the nivelAgregacion value for this RepositorioVO.
     * 
     * @param nivelAgregacion
     */
    public void setNivelAgregacion(java.lang.String nivelAgregacion) {
        this.nivelAgregacion = nivelAgregacion;
    }


    /**
     * Gets the descripcion value for this RepositorioVO.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this RepositorioVO.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the edad value for this RepositorioVO.
     * 
     * @return edad
     */
    public java.lang.String getEdad() {
        return edad;
    }


    /**
     * Sets the edad value for this RepositorioVO.
     * 
     * @param edad
     */
    public void setEdad(java.lang.String edad) {
        this.edad = edad;
    }


    /**
     * Gets the idiomaTexto value for this RepositorioVO.
     * 
     * @return idiomaTexto   * Cadena con el idioma en el que se encuentra ese contenido.
     *                                 Vendrá traducido en castellano por
     * defecto
     */
    public java.lang.String getIdiomaTexto() {
        return idiomaTexto;
    }


    /**
     * Sets the idiomaTexto value for this RepositorioVO.
     * 
     * @param idiomaTexto   * Cadena con el idioma en el que se encuentra ese contenido.
     *                                 Vendrá traducido en castellano por
     * defecto
     */
    public void setIdiomaTexto(java.lang.String idiomaTexto) {
        this.idiomaTexto = idiomaTexto;
    }


    /**
     * Gets the objetivos value for this RepositorioVO.
     * 
     * @return objetivos   * Objetivo del ode
     */
    public java.lang.String getObjetivos() {
        return objetivos;
    }


    /**
     * Sets the objetivos value for this RepositorioVO.
     * 
     * @param objetivos   * Objetivo del ode
     */
    public void setObjetivos(java.lang.String objetivos) {
        this.objetivos = objetivos;
    }


    /**
     * Gets the nivel1Cobertura value for this RepositorioVO.
     * 
     * @return nivel1Cobertura   * Identificador del primer nivel del árbol curricular. Se utiliza
     * para la categorización en el catálogo
     */
    public java.lang.String getNivel1Cobertura() {
        return nivel1Cobertura;
    }


    /**
     * Sets the nivel1Cobertura value for this RepositorioVO.
     * 
     * @param nivel1Cobertura   * Identificador del primer nivel del árbol curricular. Se utiliza
     * para la categorización en el catálogo
     */
    public void setNivel1Cobertura(java.lang.String nivel1Cobertura) {
        this.nivel1Cobertura = nivel1Cobertura;
    }


    /**
     * Gets the nivel2Cobertura value for this RepositorioVO.
     * 
     * @return nivel2Cobertura   * Identificador del segundo nivel del área curricular. Se utiliza
     * para la categorización en el catálogo
     */
    public java.lang.String getNivel2Cobertura() {
        return nivel2Cobertura;
    }


    /**
     * Sets the nivel2Cobertura value for this RepositorioVO.
     * 
     * @param nivel2Cobertura   * Identificador del segundo nivel del área curricular. Se utiliza
     * para la categorización en el catálogo
     */
    public void setNivel2Cobertura(java.lang.String nivel2Cobertura) {
        this.nivel2Cobertura = nivel2Cobertura;
    }


    /**
     * Gets the nombreNivel1Cobertura value for this RepositorioVO.
     * 
     * @return nombreNivel1Cobertura   * Nombre del primer nivel del árbol curricular
     */
    public java.lang.String getNombreNivel1Cobertura() {
        return nombreNivel1Cobertura;
    }


    /**
     * Sets the nombreNivel1Cobertura value for this RepositorioVO.
     * 
     * @param nombreNivel1Cobertura   * Nombre del primer nivel del árbol curricular
     */
    public void setNombreNivel1Cobertura(java.lang.String nombreNivel1Cobertura) {
        this.nombreNivel1Cobertura = nombreNivel1Cobertura;
    }


    /**
     * Gets the nombreNivel2Cobertura value for this RepositorioVO.
     * 
     * @return nombreNivel2Cobertura   * Nombre del segundo nivel del árbol curricular
     */
    public java.lang.String getNombreNivel2Cobertura() {
        return nombreNivel2Cobertura;
    }


    /**
     * Sets the nombreNivel2Cobertura value for this RepositorioVO.
     * 
     * @param nombreNivel2Cobertura   * Nombre del segundo nivel del árbol curricular
     */
    public void setNombreNivel2Cobertura(java.lang.String nombreNivel2Cobertura) {
        this.nombreNivel2Cobertura = nombreNivel2Cobertura;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RepositorioVO)) return false;
        RepositorioVO other = (RepositorioVO) obj;
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
            ((this.titulo==null && other.getTitulo()==null) || 
             (this.titulo!=null &&
              this.titulo.equals(other.getTitulo()))) &&
            ((this.urlImagen==null && other.getUrlImagen()==null) || 
             (this.urlImagen!=null &&
              this.urlImagen.equals(other.getUrlImagen()))) &&
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
            ((this.edad==null && other.getEdad()==null) || 
             (this.edad!=null &&
              this.edad.equals(other.getEdad()))) &&
            ((this.idiomaTexto==null && other.getIdiomaTexto()==null) || 
             (this.idiomaTexto!=null &&
              this.idiomaTexto.equals(other.getIdiomaTexto()))) &&
            ((this.objetivos==null && other.getObjetivos()==null) || 
             (this.objetivos!=null &&
              this.objetivos.equals(other.getObjetivos()))) &&
            ((this.nivel1Cobertura==null && other.getNivel1Cobertura()==null) || 
             (this.nivel1Cobertura!=null &&
              this.nivel1Cobertura.equals(other.getNivel1Cobertura()))) &&
            ((this.nivel2Cobertura==null && other.getNivel2Cobertura()==null) || 
             (this.nivel2Cobertura!=null &&
              this.nivel2Cobertura.equals(other.getNivel2Cobertura()))) &&
            ((this.nombreNivel1Cobertura==null && other.getNombreNivel1Cobertura()==null) || 
             (this.nombreNivel1Cobertura!=null &&
              this.nombreNivel1Cobertura.equals(other.getNombreNivel1Cobertura()))) &&
            ((this.nombreNivel2Cobertura==null && other.getNombreNivel2Cobertura()==null) || 
             (this.nombreNivel2Cobertura!=null &&
              this.nombreNivel2Cobertura.equals(other.getNombreNivel2Cobertura())));
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
        if (getTitulo() != null) {
            _hashCode += getTitulo().hashCode();
        }
        if (getUrlImagen() != null) {
            _hashCode += getUrlImagen().hashCode();
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
        if (getEdad() != null) {
            _hashCode += getEdad().hashCode();
        }
        if (getIdiomaTexto() != null) {
            _hashCode += getIdiomaTexto().hashCode();
        }
        if (getObjetivos() != null) {
            _hashCode += getObjetivos().hashCode();
        }
        if (getNivel1Cobertura() != null) {
            _hashCode += getNivel1Cobertura().hashCode();
        }
        if (getNivel2Cobertura() != null) {
            _hashCode += getNivel2Cobertura().hashCode();
        }
        if (getNombreNivel1Cobertura() != null) {
            _hashCode += getNombreNivel1Cobertura().hashCode();
        }
        if (getNombreNivel2Cobertura() != null) {
            _hashCode += getNombreNivel2Cobertura().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RepositorioVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "RepositorioVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "identificador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "titulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("urlImagen");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "urlImagen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idioma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "idioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaPublicacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "fechaPublicacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nivelAgregacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "nivelAgregacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("edad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "edad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idiomaTexto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "idiomaTexto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objetivos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "objetivos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nivel1Cobertura");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "nivel1Cobertura"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nivel2Cobertura");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "nivel2Cobertura"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreNivel1Cobertura");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "nombreNivel1Cobertura"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreNivel2Cobertura");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.auditoria.pode.es", "nombreNivel2Cobertura"));
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
