/*
Agrega2 es una federaci髇 de repositorios de objetos digitales educativos formada por todas las Comunidades Aut髇omas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * RegistroTareaCargaEjecutadaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.planificador.negocio.servicios;

public class RegistroTareaCargaEjecutadaVO  implements java.io.Serializable {
    private java.util.Calendar fecha;

    private java.lang.String nombreZip;

    private java.lang.String pathOde;

    private java.lang.String estado;

    private java.lang.String codigo;

    private java.lang.String titulo;

    private java.lang.String id_mec;

    private java.lang.String idioma;

    private java.lang.String nivelAgregacion;

    private java.lang.Boolean sobrescrito;

    private java.lang.String formato;

    /* La descripci贸n de como ha ido la publicaci贸n del ode */
    private java.lang.String descripcion;

    /* Las rutas taxonomicas del ode */
    private java.lang.String rutaTaxonomica;

    private es.pode.planificador.negocio.servicios.TareaEjecutadaVO tarea_carga_ejecutada;

    public RegistroTareaCargaEjecutadaVO() {
    }

    public RegistroTareaCargaEjecutadaVO(
           java.util.Calendar fecha,
           java.lang.String nombreZip,
           java.lang.String pathOde,
           java.lang.String estado,
           java.lang.String codigo,
           java.lang.String titulo,
           java.lang.String id_mec,
           java.lang.String idioma,
           java.lang.String nivelAgregacion,
           java.lang.Boolean sobrescrito,
           java.lang.String formato,
           java.lang.String descripcion,
           java.lang.String rutaTaxonomica,
           es.pode.planificador.negocio.servicios.TareaEjecutadaVO tarea_carga_ejecutada) {
           this.fecha = fecha;
           this.nombreZip = nombreZip;
           this.pathOde = pathOde;
           this.estado = estado;
           this.codigo = codigo;
           this.titulo = titulo;
           this.id_mec = id_mec;
           this.idioma = idioma;
           this.nivelAgregacion = nivelAgregacion;
           this.sobrescrito = sobrescrito;
           this.formato = formato;
           this.descripcion = descripcion;
           this.rutaTaxonomica = rutaTaxonomica;
           this.tarea_carga_ejecutada = tarea_carga_ejecutada;
    }


    /**
     * Gets the fecha value for this RegistroTareaCargaEjecutadaVO.
     * 
     * @return fecha
     */
    public java.util.Calendar getFecha() {
        return fecha;
    }


    /**
     * Sets the fecha value for this RegistroTareaCargaEjecutadaVO.
     * 
     * @param fecha
     */
    public void setFecha(java.util.Calendar fecha) {
        this.fecha = fecha;
    }


    /**
     * Gets the nombreZip value for this RegistroTareaCargaEjecutadaVO.
     * 
     * @return nombreZip
     */
    public java.lang.String getNombreZip() {
        return nombreZip;
    }


    /**
     * Sets the nombreZip value for this RegistroTareaCargaEjecutadaVO.
     * 
     * @param nombreZip
     */
    public void setNombreZip(java.lang.String nombreZip) {
        this.nombreZip = nombreZip;
    }


    /**
     * Gets the pathOde value for this RegistroTareaCargaEjecutadaVO.
     * 
     * @return pathOde
     */
    public java.lang.String getPathOde() {
        return pathOde;
    }


    /**
     * Sets the pathOde value for this RegistroTareaCargaEjecutadaVO.
     * 
     * @param pathOde
     */
    public void setPathOde(java.lang.String pathOde) {
        this.pathOde = pathOde;
    }


    /**
     * Gets the estado value for this RegistroTareaCargaEjecutadaVO.
     * 
     * @return estado
     */
    public java.lang.String getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this RegistroTareaCargaEjecutadaVO.
     * 
     * @param estado
     */
    public void setEstado(java.lang.String estado) {
        this.estado = estado;
    }


    /**
     * Gets the codigo value for this RegistroTareaCargaEjecutadaVO.
     * 
     * @return codigo
     */
    public java.lang.String getCodigo() {
        return codigo;
    }


    /**
     * Sets the codigo value for this RegistroTareaCargaEjecutadaVO.
     * 
     * @param codigo
     */
    public void setCodigo(java.lang.String codigo) {
        this.codigo = codigo;
    }


    /**
     * Gets the titulo value for this RegistroTareaCargaEjecutadaVO.
     * 
     * @return titulo
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this RegistroTareaCargaEjecutadaVO.
     * 
     * @param titulo
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }


    /**
     * Gets the id_mec value for this RegistroTareaCargaEjecutadaVO.
     * 
     * @return id_mec
     */
    public java.lang.String getId_mec() {
        return id_mec;
    }


    /**
     * Sets the id_mec value for this RegistroTareaCargaEjecutadaVO.
     * 
     * @param id_mec
     */
    public void setId_mec(java.lang.String id_mec) {
        this.id_mec = id_mec;
    }


    /**
     * Gets the idioma value for this RegistroTareaCargaEjecutadaVO.
     * 
     * @return idioma
     */
    public java.lang.String getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this RegistroTareaCargaEjecutadaVO.
     * 
     * @param idioma
     */
    public void setIdioma(java.lang.String idioma) {
        this.idioma = idioma;
    }


    /**
     * Gets the nivelAgregacion value for this RegistroTareaCargaEjecutadaVO.
     * 
     * @return nivelAgregacion
     */
    public java.lang.String getNivelAgregacion() {
        return nivelAgregacion;
    }


    /**
     * Sets the nivelAgregacion value for this RegistroTareaCargaEjecutadaVO.
     * 
     * @param nivelAgregacion
     */
    public void setNivelAgregacion(java.lang.String nivelAgregacion) {
        this.nivelAgregacion = nivelAgregacion;
    }


    /**
     * Gets the sobrescrito value for this RegistroTareaCargaEjecutadaVO.
     * 
     * @return sobrescrito
     */
    public java.lang.Boolean getSobrescrito() {
        return sobrescrito;
    }


    /**
     * Sets the sobrescrito value for this RegistroTareaCargaEjecutadaVO.
     * 
     * @param sobrescrito
     */
    public void setSobrescrito(java.lang.Boolean sobrescrito) {
        this.sobrescrito = sobrescrito;
    }


    /**
     * Gets the formato value for this RegistroTareaCargaEjecutadaVO.
     * 
     * @return formato
     */
    public java.lang.String getFormato() {
        return formato;
    }


    /**
     * Sets the formato value for this RegistroTareaCargaEjecutadaVO.
     * 
     * @param formato
     */
    public void setFormato(java.lang.String formato) {
        this.formato = formato;
    }


    /**
     * Gets the descripcion value for this RegistroTareaCargaEjecutadaVO.
     * 
     * @return descripcion   * La descripci贸n de como ha ido la publicaci贸n del ode
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this RegistroTareaCargaEjecutadaVO.
     * 
     * @param descripcion   * La descripci贸n de como ha ido la publicaci贸n del ode
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the rutaTaxonomica value for this RegistroTareaCargaEjecutadaVO.
     * 
     * @return rutaTaxonomica   * Las rutas taxonomicas del ode
     */
    public java.lang.String getRutaTaxonomica() {
        return rutaTaxonomica;
    }


    /**
     * Sets the rutaTaxonomica value for this RegistroTareaCargaEjecutadaVO.
     * 
     * @param rutaTaxonomica   * Las rutas taxonomicas del ode
     */
    public void setRutaTaxonomica(java.lang.String rutaTaxonomica) {
        this.rutaTaxonomica = rutaTaxonomica;
    }


    /**
     * Gets the tarea_carga_ejecutada value for this RegistroTareaCargaEjecutadaVO.
     * 
     * @return tarea_carga_ejecutada
     */
    public es.pode.planificador.negocio.servicios.TareaEjecutadaVO getTarea_carga_ejecutada() {
        return tarea_carga_ejecutada;
    }


    /**
     * Sets the tarea_carga_ejecutada value for this RegistroTareaCargaEjecutadaVO.
     * 
     * @param tarea_carga_ejecutada
     */
    public void setTarea_carga_ejecutada(es.pode.planificador.negocio.servicios.TareaEjecutadaVO tarea_carga_ejecutada) {
        this.tarea_carga_ejecutada = tarea_carga_ejecutada;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RegistroTareaCargaEjecutadaVO)) return false;
        RegistroTareaCargaEjecutadaVO other = (RegistroTareaCargaEjecutadaVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fecha==null && other.getFecha()==null) || 
             (this.fecha!=null &&
              this.fecha.equals(other.getFecha()))) &&
            ((this.nombreZip==null && other.getNombreZip()==null) || 
             (this.nombreZip!=null &&
              this.nombreZip.equals(other.getNombreZip()))) &&
            ((this.pathOde==null && other.getPathOde()==null) || 
             (this.pathOde!=null &&
              this.pathOde.equals(other.getPathOde()))) &&
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado()))) &&
            ((this.codigo==null && other.getCodigo()==null) || 
             (this.codigo!=null &&
              this.codigo.equals(other.getCodigo()))) &&
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
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.rutaTaxonomica==null && other.getRutaTaxonomica()==null) || 
             (this.rutaTaxonomica!=null &&
              this.rutaTaxonomica.equals(other.getRutaTaxonomica()))) &&
            ((this.tarea_carga_ejecutada==null && other.getTarea_carga_ejecutada()==null) || 
             (this.tarea_carga_ejecutada!=null &&
              this.tarea_carga_ejecutada.equals(other.getTarea_carga_ejecutada())));
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
        if (getFecha() != null) {
            _hashCode += getFecha().hashCode();
        }
        if (getNombreZip() != null) {
            _hashCode += getNombreZip().hashCode();
        }
        if (getPathOde() != null) {
            _hashCode += getPathOde().hashCode();
        }
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        if (getCodigo() != null) {
            _hashCode += getCodigo().hashCode();
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
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getRutaTaxonomica() != null) {
            _hashCode += getRutaTaxonomica().hashCode();
        }
        if (getTarea_carga_ejecutada() != null) {
            _hashCode += getTarea_carga_ejecutada().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RegistroTareaCargaEjecutadaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "RegistroTareaCargaEjecutadaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "fecha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreZip");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "nombreZip"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathOde");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "pathOde"));
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
        elemField.setFieldName("codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rutaTaxonomica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "rutaTaxonomica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tarea_carga_ejecutada");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "tarea_carga_ejecutada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.planificador.pode.es", "TareaEjecutadaVO"));
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
