/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * RegistroCargaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.planificador.negocio.servicios;


/**
 * Almacena la información de los ODEs cargados de la tarea de
 *                         carga masiva
 */
public class RegistroCargaVO  implements java.io.Serializable {
    /* El nombre del zip */
    private java.lang.String nombreZip;

    /* Path relativo respecto al path de la carga */
    private java.lang.String pathZip;

    /* Fecha de la publicaión o no del ode */
    private java.lang.String fecha;

    /* Estado final del ODE (publicado, no publicado) */
    private java.lang.String estado;

    /* Cadena con el resultado de la carga. Se corresponde con la
     *                                 columna Descripcion de la tabla registro_tarea_carga_ejecutada */
    private java.lang.String descripcion;

    /* Título del ode */
    private java.lang.String titulo;

    /* El identificador mec del ode */
    private java.lang.String id_mec;

    /* Idioma en el que se ha indexado el ODE */
    private java.lang.String idioma;

    /* El nivel de agregación del ode */
    private java.lang.String nivelAgregacion;

    /* Indica si ya existía un ODE anteriormente cargado en la
     *                                 plataforma, y si es así si lo ha sobrescrito
     * o no */
    private java.lang.Boolean sobrescrito;

    /* Cadena con todos los formatos que contiene el ODE, separados
     * por
     *                                 comas(,) */
    private java.lang.String formato;

    /* La ruta taxonomica del ode */
    private java.lang.String rutaTaxonomica;

    /* Lista de id_mecs separados por comas similares al ode cargado */
    private java.lang.String odesSimilares;

    public RegistroCargaVO() {
    }

    public RegistroCargaVO(
           java.lang.String nombreZip,
           java.lang.String pathZip,
           java.lang.String fecha,
           java.lang.String estado,
           java.lang.String descripcion,
           java.lang.String titulo,
           java.lang.String id_mec,
           java.lang.String idioma,
           java.lang.String nivelAgregacion,
           java.lang.Boolean sobrescrito,
           java.lang.String formato,
           java.lang.String rutaTaxonomica,
           java.lang.String odesSimilares) {
           this.nombreZip = nombreZip;
           this.pathZip = pathZip;
           this.fecha = fecha;
           this.estado = estado;
           this.descripcion = descripcion;
           this.titulo = titulo;
           this.id_mec = id_mec;
           this.idioma = idioma;
           this.nivelAgregacion = nivelAgregacion;
           this.sobrescrito = sobrescrito;
           this.formato = formato;
           this.rutaTaxonomica = rutaTaxonomica;
           this.odesSimilares = odesSimilares;
    }


    /**
     * Gets the nombreZip value for this RegistroCargaVO.
     * 
     * @return nombreZip   * El nombre del zip
     */
    public java.lang.String getNombreZip() {
        return nombreZip;
    }


    /**
     * Sets the nombreZip value for this RegistroCargaVO.
     * 
     * @param nombreZip   * El nombre del zip
     */
    public void setNombreZip(java.lang.String nombreZip) {
        this.nombreZip = nombreZip;
    }


    /**
     * Gets the pathZip value for this RegistroCargaVO.
     * 
     * @return pathZip   * Path relativo respecto al path de la carga
     */
    public java.lang.String getPathZip() {
        return pathZip;
    }


    /**
     * Sets the pathZip value for this RegistroCargaVO.
     * 
     * @param pathZip   * Path relativo respecto al path de la carga
     */
    public void setPathZip(java.lang.String pathZip) {
        this.pathZip = pathZip;
    }


    /**
     * Gets the fecha value for this RegistroCargaVO.
     * 
     * @return fecha   * Fecha de la publicaión o no del ode
     */
    public java.lang.String getFecha() {
        return fecha;
    }


    /**
     * Sets the fecha value for this RegistroCargaVO.
     * 
     * @param fecha   * Fecha de la publicaión o no del ode
     */
    public void setFecha(java.lang.String fecha) {
        this.fecha = fecha;
    }


    /**
     * Gets the estado value for this RegistroCargaVO.
     * 
     * @return estado   * Estado final del ODE (publicado, no publicado)
     */
    public java.lang.String getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this RegistroCargaVO.
     * 
     * @param estado   * Estado final del ODE (publicado, no publicado)
     */
    public void setEstado(java.lang.String estado) {
        this.estado = estado;
    }


    /**
     * Gets the descripcion value for this RegistroCargaVO.
     * 
     * @return descripcion   * Cadena con el resultado de la carga. Se corresponde con la
     *                                 columna Descripcion de la tabla registro_tarea_carga_ejecutada
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this RegistroCargaVO.
     * 
     * @param descripcion   * Cadena con el resultado de la carga. Se corresponde con la
     *                                 columna Descripcion de la tabla registro_tarea_carga_ejecutada
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the titulo value for this RegistroCargaVO.
     * 
     * @return titulo   * Título del ode
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this RegistroCargaVO.
     * 
     * @param titulo   * Título del ode
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }


    /**
     * Gets the id_mec value for this RegistroCargaVO.
     * 
     * @return id_mec   * El identificador mec del ode
     */
    public java.lang.String getId_mec() {
        return id_mec;
    }


    /**
     * Sets the id_mec value for this RegistroCargaVO.
     * 
     * @param id_mec   * El identificador mec del ode
     */
    public void setId_mec(java.lang.String id_mec) {
        this.id_mec = id_mec;
    }


    /**
     * Gets the idioma value for this RegistroCargaVO.
     * 
     * @return idioma   * Idioma en el que se ha indexado el ODE
     */
    public java.lang.String getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this RegistroCargaVO.
     * 
     * @param idioma   * Idioma en el que se ha indexado el ODE
     */
    public void setIdioma(java.lang.String idioma) {
        this.idioma = idioma;
    }


    /**
     * Gets the nivelAgregacion value for this RegistroCargaVO.
     * 
     * @return nivelAgregacion   * El nivel de agregación del ode
     */
    public java.lang.String getNivelAgregacion() {
        return nivelAgregacion;
    }


    /**
     * Sets the nivelAgregacion value for this RegistroCargaVO.
     * 
     * @param nivelAgregacion   * El nivel de agregación del ode
     */
    public void setNivelAgregacion(java.lang.String nivelAgregacion) {
        this.nivelAgregacion = nivelAgregacion;
    }


    /**
     * Gets the sobrescrito value for this RegistroCargaVO.
     * 
     * @return sobrescrito   * Indica si ya existía un ODE anteriormente cargado en la
     *                                 plataforma, y si es así si lo ha sobrescrito
     * o no
     */
    public java.lang.Boolean getSobrescrito() {
        return sobrescrito;
    }


    /**
     * Sets the sobrescrito value for this RegistroCargaVO.
     * 
     * @param sobrescrito   * Indica si ya existía un ODE anteriormente cargado en la
     *                                 plataforma, y si es así si lo ha sobrescrito
     * o no
     */
    public void setSobrescrito(java.lang.Boolean sobrescrito) {
        this.sobrescrito = sobrescrito;
    }


    /**
     * Gets the formato value for this RegistroCargaVO.
     * 
     * @return formato   * Cadena con todos los formatos que contiene el ODE, separados
     * por
     *                                 comas(,)
     */
    public java.lang.String getFormato() {
        return formato;
    }


    /**
     * Sets the formato value for this RegistroCargaVO.
     * 
     * @param formato   * Cadena con todos los formatos que contiene el ODE, separados
     * por
     *                                 comas(,)
     */
    public void setFormato(java.lang.String formato) {
        this.formato = formato;
    }


    /**
     * Gets the rutaTaxonomica value for this RegistroCargaVO.
     * 
     * @return rutaTaxonomica   * La ruta taxonomica del ode
     */
    public java.lang.String getRutaTaxonomica() {
        return rutaTaxonomica;
    }


    /**
     * Sets the rutaTaxonomica value for this RegistroCargaVO.
     * 
     * @param rutaTaxonomica   * La ruta taxonomica del ode
     */
    public void setRutaTaxonomica(java.lang.String rutaTaxonomica) {
        this.rutaTaxonomica = rutaTaxonomica;
    }


    /**
     * Gets the odesSimilares value for this RegistroCargaVO.
     * 
     * @return odesSimilares   * Lista de id_mecs separados por comas similares al ode cargado
     */
    public java.lang.String getOdesSimilares() {
        return odesSimilares;
    }


    /**
     * Sets the odesSimilares value for this RegistroCargaVO.
     * 
     * @param odesSimilares   * Lista de id_mecs separados por comas similares al ode cargado
     */
    public void setOdesSimilares(java.lang.String odesSimilares) {
        this.odesSimilares = odesSimilares;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RegistroCargaVO)) return false;
        RegistroCargaVO other = (RegistroCargaVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nombreZip==null && other.getNombreZip()==null) || 
             (this.nombreZip!=null &&
              this.nombreZip.equals(other.getNombreZip()))) &&
            ((this.pathZip==null && other.getPathZip()==null) || 
             (this.pathZip!=null &&
              this.pathZip.equals(other.getPathZip()))) &&
            ((this.fecha==null && other.getFecha()==null) || 
             (this.fecha!=null &&
              this.fecha.equals(other.getFecha()))) &&
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.titulo==null && other.getTitulo()==null) || 
             (this.titulo!=null &&
              this.titulo.equals(other.getTitulo()))) &&
            ((this.id_mec==null && other.getId_mec()==null) || 
             (this.id_mec!=null &&
              this.id_mec.equals(other.getId_mec()))) &&
            ((this.idioma==null && other.getIdioma()==null) || 
             (this.idioma!=null &&
              this.idioma.equals(other.getIdioma()))) &&
            ((this.nivelAgregacion==null && other.getNivelAgregacion()==null) || 
             (this.nivelAgregacion!=null &&
              this.nivelAgregacion.equals(other.getNivelAgregacion()))) &&
            ((this.sobrescrito==null && other.getSobrescrito()==null) || 
             (this.sobrescrito!=null &&
              this.sobrescrito.equals(other.getSobrescrito()))) &&
            ((this.formato==null && other.getFormato()==null) || 
             (this.formato!=null &&
              this.formato.equals(other.getFormato()))) &&
            ((this.rutaTaxonomica==null && other.getRutaTaxonomica()==null) || 
             (this.rutaTaxonomica!=null &&
              this.rutaTaxonomica.equals(other.getRutaTaxonomica()))) &&
            ((this.odesSimilares==null && other.getOdesSimilares()==null) || 
             (this.odesSimilares!=null &&
              this.odesSimilares.equals(other.getOdesSimilares())));
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
        if (getNombreZip() != null) {
            _hashCode += getNombreZip().hashCode();
        }
        if (getPathZip() != null) {
            _hashCode += getPathZip().hashCode();
        }
        if (getFecha() != null) {
            _hashCode += getFecha().hashCode();
        }
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getTitulo() != null) {
            _hashCode += getTitulo().hashCode();
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
        if (getSobrescrito() != null) {
            _hashCode += getSobrescrito().hashCode();
        }
        if (getFormato() != null) {
            _hashCode += getFormato().hashCode();
        }
        if (getRutaTaxonomica() != null) {
            _hashCode += getRutaTaxonomica().hashCode();
        }
        if (getOdesSimilares() != null) {
            _hashCode += getOdesSimilares().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RegistroCargaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "RegistroCargaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreZip");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "nombreZip"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathZip");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "pathZip"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "fecha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "titulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id_mec");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "id_mec"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
        elemField.setFieldName("sobrescrito");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "sobrescrito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formato");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "formato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rutaTaxonomica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "rutaTaxonomica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("odesSimilares");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "odesSimilares"));
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
