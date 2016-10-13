/**
 * CorreoGrupoVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.gestorCorreo.negocio.servicios;


/**
 * En este value object se almacenrá la información acerca del
 *                         grupo
 */
public class CorreoGrupoVO  implements java.io.Serializable {
    /* Se guardará el nombre del grupo */
    private java.lang.String nombreGrupo;

    /* Aqui guardaremos el titulo del ODE */
    private java.lang.String tituloOde;

    /* destinatarios */
    private java.lang.String[] to;

    /* Url de la ficha del ode sobre el que se envía el correo */
    private java.lang.String urlFicha;

    /* Url de la imagen representativa del ode */
    private java.lang.String urlImagen;

    /* Persona que manda el correo */
    private java.lang.String from;

    /* Idioma del correo */
    private java.lang.String idiomaCorreo;

    /* Url a la que enlazará el logo de Agrega */
    private java.lang.String hrefLogo;

    /* Url donde se encuentra el logo de Agrega */
    private java.lang.String urlImagenLogo;

    public CorreoGrupoVO() {
    }

    public CorreoGrupoVO(
           java.lang.String nombreGrupo,
           java.lang.String tituloOde,
           java.lang.String[] to,
           java.lang.String urlFicha,
           java.lang.String urlImagen,
           java.lang.String from,
           java.lang.String idiomaCorreo,
           java.lang.String hrefLogo,
           java.lang.String urlImagenLogo) {
           this.nombreGrupo = nombreGrupo;
           this.tituloOde = tituloOde;
           this.to = to;
           this.urlFicha = urlFicha;
           this.urlImagen = urlImagen;
           this.from = from;
           this.idiomaCorreo = idiomaCorreo;
           this.hrefLogo = hrefLogo;
           this.urlImagenLogo = urlImagenLogo;
    }


    /**
     * Gets the nombreGrupo value for this CorreoGrupoVO.
     * 
     * @return nombreGrupo   * Se guardará el nombre del grupo
     */
    public java.lang.String getNombreGrupo() {
        return nombreGrupo;
    }


    /**
     * Sets the nombreGrupo value for this CorreoGrupoVO.
     * 
     * @param nombreGrupo   * Se guardará el nombre del grupo
     */
    public void setNombreGrupo(java.lang.String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }


    /**
     * Gets the tituloOde value for this CorreoGrupoVO.
     * 
     * @return tituloOde   * Aqui guardaremos el titulo del ODE
     */
    public java.lang.String getTituloOde() {
        return tituloOde;
    }


    /**
     * Sets the tituloOde value for this CorreoGrupoVO.
     * 
     * @param tituloOde   * Aqui guardaremos el titulo del ODE
     */
    public void setTituloOde(java.lang.String tituloOde) {
        this.tituloOde = tituloOde;
    }


    /**
     * Gets the to value for this CorreoGrupoVO.
     * 
     * @return to   * destinatarios
     */
    public java.lang.String[] getTo() {
        return to;
    }


    /**
     * Sets the to value for this CorreoGrupoVO.
     * 
     * @param to   * destinatarios
     */
    public void setTo(java.lang.String[] to) {
        this.to = to;
    }


    /**
     * Gets the urlFicha value for this CorreoGrupoVO.
     * 
     * @return urlFicha   * Url de la ficha del ode sobre el que se envía el correo
     */
    public java.lang.String getUrlFicha() {
        return urlFicha;
    }


    /**
     * Sets the urlFicha value for this CorreoGrupoVO.
     * 
     * @param urlFicha   * Url de la ficha del ode sobre el que se envía el correo
     */
    public void setUrlFicha(java.lang.String urlFicha) {
        this.urlFicha = urlFicha;
    }


    /**
     * Gets the urlImagen value for this CorreoGrupoVO.
     * 
     * @return urlImagen   * Url de la imagen representativa del ode
     */
    public java.lang.String getUrlImagen() {
        return urlImagen;
    }


    /**
     * Sets the urlImagen value for this CorreoGrupoVO.
     * 
     * @param urlImagen   * Url de la imagen representativa del ode
     */
    public void setUrlImagen(java.lang.String urlImagen) {
        this.urlImagen = urlImagen;
    }


    /**
     * Gets the from value for this CorreoGrupoVO.
     * 
     * @return from   * Persona que manda el correo
     */
    public java.lang.String getFrom() {
        return from;
    }


    /**
     * Sets the from value for this CorreoGrupoVO.
     * 
     * @param from   * Persona que manda el correo
     */
    public void setFrom(java.lang.String from) {
        this.from = from;
    }


    /**
     * Gets the idiomaCorreo value for this CorreoGrupoVO.
     * 
     * @return idiomaCorreo   * Idioma del correo
     */
    public java.lang.String getIdiomaCorreo() {
        return idiomaCorreo;
    }


    /**
     * Sets the idiomaCorreo value for this CorreoGrupoVO.
     * 
     * @param idiomaCorreo   * Idioma del correo
     */
    public void setIdiomaCorreo(java.lang.String idiomaCorreo) {
        this.idiomaCorreo = idiomaCorreo;
    }


    /**
     * Gets the hrefLogo value for this CorreoGrupoVO.
     * 
     * @return hrefLogo   * Url a la que enlazará el logo de Agrega
     */
    public java.lang.String getHrefLogo() {
        return hrefLogo;
    }


    /**
     * Sets the hrefLogo value for this CorreoGrupoVO.
     * 
     * @param hrefLogo   * Url a la que enlazará el logo de Agrega
     */
    public void setHrefLogo(java.lang.String hrefLogo) {
        this.hrefLogo = hrefLogo;
    }


    /**
     * Gets the urlImagenLogo value for this CorreoGrupoVO.
     * 
     * @return urlImagenLogo   * Url donde se encuentra el logo de Agrega
     */
    public java.lang.String getUrlImagenLogo() {
        return urlImagenLogo;
    }


    /**
     * Sets the urlImagenLogo value for this CorreoGrupoVO.
     * 
     * @param urlImagenLogo   * Url donde se encuentra el logo de Agrega
     */
    public void setUrlImagenLogo(java.lang.String urlImagenLogo) {
        this.urlImagenLogo = urlImagenLogo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CorreoGrupoVO)) return false;
        CorreoGrupoVO other = (CorreoGrupoVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nombreGrupo==null && other.getNombreGrupo()==null) || 
             (this.nombreGrupo!=null &&
              this.nombreGrupo.equals(other.getNombreGrupo()))) &&
            ((this.tituloOde==null && other.getTituloOde()==null) || 
             (this.tituloOde!=null &&
              this.tituloOde.equals(other.getTituloOde()))) &&
            ((this.to==null && other.getTo()==null) || 
             (this.to!=null &&
              java.util.Arrays.equals(this.to, other.getTo()))) &&
            ((this.urlFicha==null && other.getUrlFicha()==null) || 
             (this.urlFicha!=null &&
              this.urlFicha.equals(other.getUrlFicha()))) &&
            ((this.urlImagen==null && other.getUrlImagen()==null) || 
             (this.urlImagen!=null &&
              this.urlImagen.equals(other.getUrlImagen()))) &&
            ((this.from==null && other.getFrom()==null) || 
             (this.from!=null &&
              this.from.equals(other.getFrom()))) &&
            ((this.idiomaCorreo==null && other.getIdiomaCorreo()==null) || 
             (this.idiomaCorreo!=null &&
              this.idiomaCorreo.equals(other.getIdiomaCorreo()))) &&
            ((this.hrefLogo==null && other.getHrefLogo()==null) || 
             (this.hrefLogo!=null &&
              this.hrefLogo.equals(other.getHrefLogo()))) &&
            ((this.urlImagenLogo==null && other.getUrlImagenLogo()==null) || 
             (this.urlImagenLogo!=null &&
              this.urlImagenLogo.equals(other.getUrlImagenLogo())));
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
        if (getNombreGrupo() != null) {
            _hashCode += getNombreGrupo().hashCode();
        }
        if (getTituloOde() != null) {
            _hashCode += getTituloOde().hashCode();
        }
        if (getTo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUrlFicha() != null) {
            _hashCode += getUrlFicha().hashCode();
        }
        if (getUrlImagen() != null) {
            _hashCode += getUrlImagen().hashCode();
        }
        if (getFrom() != null) {
            _hashCode += getFrom().hashCode();
        }
        if (getIdiomaCorreo() != null) {
            _hashCode += getIdiomaCorreo().hashCode();
        }
        if (getHrefLogo() != null) {
            _hashCode += getHrefLogo().hashCode();
        }
        if (getUrlImagenLogo() != null) {
            _hashCode += getUrlImagenLogo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CorreoGrupoVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "CorreoGrupoVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreGrupo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "nombreGrupo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tituloOde");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "tituloOde"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("to");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "to"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("urlFicha");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "urlFicha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("urlImagen");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "urlImagen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("from");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "from"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idiomaCorreo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "idiomaCorreo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hrefLogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "hrefLogo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("urlImagenLogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "urlImagenLogo"));
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
