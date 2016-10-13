/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * CorreoOdeVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.gestorCorreo.negocio.servicios;


/**
 * Value Object con los datos necesarios para el envío de los
 *                         correos: enviar a un amigo y contenido inapropiado.
 */
public class CorreoOdeVO  implements java.io.Serializable {
    /* Url a la que enlazará el logo de Agrega */
    private java.lang.String hrefLogo;

    /* Url donde se encuentra el logo de Agrega */
    private java.lang.String urlImagenLogo;

    /* Email del usuario que envía el correo de contenido inapropiado
     * o
     *                                 enviar a un amigo */
    private java.lang.String emailRemitente;

    /* Título del ode sobre el que se envía el correo */
    private java.lang.String tituloOde;

    /* Url de la ficha del ode sobre el que se envía el correo */
    private java.lang.String urlFicha;

    /* Nombre del usuario que desea enviar el correo */
    private java.lang.String nombreRemitente;

    /* Nombre del destinatario del correo */
    private java.lang.String nombreDestinatario;

    /* Url de la imagen representativa del ode */
    private java.lang.String urlImagen;

    /* Comentario sobre el ode. */
    private java.lang.String comentario;

    /* Valoración que recibe el ode */
    private java.lang.String valoracion;

    /* Comunidad en la que se encuentra el ode */
    private java.lang.String comunidad;

    /* destinatarios */
    private java.lang.String[] to;

    /* Persona a la que se envia el correo */
    private java.lang.String from;

    /* idioma en el que irá el correo */
    private java.lang.String idiomaCorreo;

    /* Usuario que rechaza el ODE o bien el nombre del contacto al
     * que
     *                                 le publica un ODE */
    private java.lang.String usuario;

    public CorreoOdeVO() {
    }

    public CorreoOdeVO(
           java.lang.String hrefLogo,
           java.lang.String urlImagenLogo,
           java.lang.String emailRemitente,
           java.lang.String tituloOde,
           java.lang.String urlFicha,
           java.lang.String nombreRemitente,
           java.lang.String nombreDestinatario,
           java.lang.String urlImagen,
           java.lang.String comentario,
           java.lang.String valoracion,
           java.lang.String comunidad,
           java.lang.String[] to,
           java.lang.String from,
           java.lang.String idiomaCorreo,
           java.lang.String usuario) {
           this.hrefLogo = hrefLogo;
           this.urlImagenLogo = urlImagenLogo;
           this.emailRemitente = emailRemitente;
           this.tituloOde = tituloOde;
           this.urlFicha = urlFicha;
           this.nombreRemitente = nombreRemitente;
           this.nombreDestinatario = nombreDestinatario;
           this.urlImagen = urlImagen;
           this.comentario = comentario;
           this.valoracion = valoracion;
           this.comunidad = comunidad;
           this.to = to;
           this.from = from;
           this.idiomaCorreo = idiomaCorreo;
           this.usuario = usuario;
    }


    /**
     * Gets the hrefLogo value for this CorreoOdeVO.
     * 
     * @return hrefLogo   * Url a la que enlazará el logo de Agrega
     */
    public java.lang.String getHrefLogo() {
        return hrefLogo;
    }


    /**
     * Sets the hrefLogo value for this CorreoOdeVO.
     * 
     * @param hrefLogo   * Url a la que enlazará el logo de Agrega
     */
    public void setHrefLogo(java.lang.String hrefLogo) {
        this.hrefLogo = hrefLogo;
    }


    /**
     * Gets the urlImagenLogo value for this CorreoOdeVO.
     * 
     * @return urlImagenLogo   * Url donde se encuentra el logo de Agrega
     */
    public java.lang.String getUrlImagenLogo() {
        return urlImagenLogo;
    }


    /**
     * Sets the urlImagenLogo value for this CorreoOdeVO.
     * 
     * @param urlImagenLogo   * Url donde se encuentra el logo de Agrega
     */
    public void setUrlImagenLogo(java.lang.String urlImagenLogo) {
        this.urlImagenLogo = urlImagenLogo;
    }


    /**
     * Gets the emailRemitente value for this CorreoOdeVO.
     * 
     * @return emailRemitente   * Email del usuario que envía el correo de contenido inapropiado
     * o
     *                                 enviar a un amigo
     */
    public java.lang.String getEmailRemitente() {
        return emailRemitente;
    }


    /**
     * Sets the emailRemitente value for this CorreoOdeVO.
     * 
     * @param emailRemitente   * Email del usuario que envía el correo de contenido inapropiado
     * o
     *                                 enviar a un amigo
     */
    public void setEmailRemitente(java.lang.String emailRemitente) {
        this.emailRemitente = emailRemitente;
    }


    /**
     * Gets the tituloOde value for this CorreoOdeVO.
     * 
     * @return tituloOde   * Título del ode sobre el que se envía el correo
     */
    public java.lang.String getTituloOde() {
        return tituloOde;
    }


    /**
     * Sets the tituloOde value for this CorreoOdeVO.
     * 
     * @param tituloOde   * Título del ode sobre el que se envía el correo
     */
    public void setTituloOde(java.lang.String tituloOde) {
        this.tituloOde = tituloOde;
    }


    /**
     * Gets the urlFicha value for this CorreoOdeVO.
     * 
     * @return urlFicha   * Url de la ficha del ode sobre el que se envía el correo
     */
    public java.lang.String getUrlFicha() {
        return urlFicha;
    }


    /**
     * Sets the urlFicha value for this CorreoOdeVO.
     * 
     * @param urlFicha   * Url de la ficha del ode sobre el que se envía el correo
     */
    public void setUrlFicha(java.lang.String urlFicha) {
        this.urlFicha = urlFicha;
    }


    /**
     * Gets the nombreRemitente value for this CorreoOdeVO.
     * 
     * @return nombreRemitente   * Nombre del usuario que desea enviar el correo
     */
    public java.lang.String getNombreRemitente() {
        return nombreRemitente;
    }


    /**
     * Sets the nombreRemitente value for this CorreoOdeVO.
     * 
     * @param nombreRemitente   * Nombre del usuario que desea enviar el correo
     */
    public void setNombreRemitente(java.lang.String nombreRemitente) {
        this.nombreRemitente = nombreRemitente;
    }


    /**
     * Gets the nombreDestinatario value for this CorreoOdeVO.
     * 
     * @return nombreDestinatario   * Nombre del destinatario del correo
     */
    public java.lang.String getNombreDestinatario() {
        return nombreDestinatario;
    }


    /**
     * Sets the nombreDestinatario value for this CorreoOdeVO.
     * 
     * @param nombreDestinatario   * Nombre del destinatario del correo
     */
    public void setNombreDestinatario(java.lang.String nombreDestinatario) {
        this.nombreDestinatario = nombreDestinatario;
    }


    /**
     * Gets the urlImagen value for this CorreoOdeVO.
     * 
     * @return urlImagen   * Url de la imagen representativa del ode
     */
    public java.lang.String getUrlImagen() {
        return urlImagen;
    }


    /**
     * Sets the urlImagen value for this CorreoOdeVO.
     * 
     * @param urlImagen   * Url de la imagen representativa del ode
     */
    public void setUrlImagen(java.lang.String urlImagen) {
        this.urlImagen = urlImagen;
    }


    /**
     * Gets the comentario value for this CorreoOdeVO.
     * 
     * @return comentario   * Comentario sobre el ode.
     */
    public java.lang.String getComentario() {
        return comentario;
    }


    /**
     * Sets the comentario value for this CorreoOdeVO.
     * 
     * @param comentario   * Comentario sobre el ode.
     */
    public void setComentario(java.lang.String comentario) {
        this.comentario = comentario;
    }


    /**
     * Gets the valoracion value for this CorreoOdeVO.
     * 
     * @return valoracion   * Valoración que recibe el ode
     */
    public java.lang.String getValoracion() {
        return valoracion;
    }


    /**
     * Sets the valoracion value for this CorreoOdeVO.
     * 
     * @param valoracion   * Valoración que recibe el ode
     */
    public void setValoracion(java.lang.String valoracion) {
        this.valoracion = valoracion;
    }


    /**
     * Gets the comunidad value for this CorreoOdeVO.
     * 
     * @return comunidad   * Comunidad en la que se encuentra el ode
     */
    public java.lang.String getComunidad() {
        return comunidad;
    }


    /**
     * Sets the comunidad value for this CorreoOdeVO.
     * 
     * @param comunidad   * Comunidad en la que se encuentra el ode
     */
    public void setComunidad(java.lang.String comunidad) {
        this.comunidad = comunidad;
    }


    /**
     * Gets the to value for this CorreoOdeVO.
     * 
     * @return to   * destinatarios
     */
    public java.lang.String[] getTo() {
        return to;
    }


    /**
     * Sets the to value for this CorreoOdeVO.
     * 
     * @param to   * destinatarios
     */
    public void setTo(java.lang.String[] to) {
        this.to = to;
    }


    /**
     * Gets the from value for this CorreoOdeVO.
     * 
     * @return from   * Persona a la que se envia el correo
     */
    public java.lang.String getFrom() {
        return from;
    }


    /**
     * Sets the from value for this CorreoOdeVO.
     * 
     * @param from   * Persona a la que se envia el correo
     */
    public void setFrom(java.lang.String from) {
        this.from = from;
    }


    /**
     * Gets the idiomaCorreo value for this CorreoOdeVO.
     * 
     * @return idiomaCorreo   * idioma en el que irá el correo
     */
    public java.lang.String getIdiomaCorreo() {
        return idiomaCorreo;
    }


    /**
     * Sets the idiomaCorreo value for this CorreoOdeVO.
     * 
     * @param idiomaCorreo   * idioma en el que irá el correo
     */
    public void setIdiomaCorreo(java.lang.String idiomaCorreo) {
        this.idiomaCorreo = idiomaCorreo;
    }


    /**
     * Gets the usuario value for this CorreoOdeVO.
     * 
     * @return usuario   * Usuario que rechaza el ODE o bien el nombre del contacto al
     * que
     *                                 le publica un ODE
     */
    public java.lang.String getUsuario() {
        return usuario;
    }


    /**
     * Sets the usuario value for this CorreoOdeVO.
     * 
     * @param usuario   * Usuario que rechaza el ODE o bien el nombre del contacto al
     * que
     *                                 le publica un ODE
     */
    public void setUsuario(java.lang.String usuario) {
        this.usuario = usuario;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CorreoOdeVO)) return false;
        CorreoOdeVO other = (CorreoOdeVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.hrefLogo==null && other.getHrefLogo()==null) || 
             (this.hrefLogo!=null &&
              this.hrefLogo.equals(other.getHrefLogo()))) &&
            ((this.urlImagenLogo==null && other.getUrlImagenLogo()==null) || 
             (this.urlImagenLogo!=null &&
              this.urlImagenLogo.equals(other.getUrlImagenLogo()))) &&
            ((this.emailRemitente==null && other.getEmailRemitente()==null) || 
             (this.emailRemitente!=null &&
              this.emailRemitente.equals(other.getEmailRemitente()))) &&
            ((this.tituloOde==null && other.getTituloOde()==null) || 
             (this.tituloOde!=null &&
              this.tituloOde.equals(other.getTituloOde()))) &&
            ((this.urlFicha==null && other.getUrlFicha()==null) || 
             (this.urlFicha!=null &&
              this.urlFicha.equals(other.getUrlFicha()))) &&
            ((this.nombreRemitente==null && other.getNombreRemitente()==null) || 
             (this.nombreRemitente!=null &&
              this.nombreRemitente.equals(other.getNombreRemitente()))) &&
            ((this.nombreDestinatario==null && other.getNombreDestinatario()==null) || 
             (this.nombreDestinatario!=null &&
              this.nombreDestinatario.equals(other.getNombreDestinatario()))) &&
            ((this.urlImagen==null && other.getUrlImagen()==null) || 
             (this.urlImagen!=null &&
              this.urlImagen.equals(other.getUrlImagen()))) &&
            ((this.comentario==null && other.getComentario()==null) || 
             (this.comentario!=null &&
              this.comentario.equals(other.getComentario()))) &&
            ((this.valoracion==null && other.getValoracion()==null) || 
             (this.valoracion!=null &&
              this.valoracion.equals(other.getValoracion()))) &&
            ((this.comunidad==null && other.getComunidad()==null) || 
             (this.comunidad!=null &&
              this.comunidad.equals(other.getComunidad()))) &&
            ((this.to==null && other.getTo()==null) || 
             (this.to!=null &&
              java.util.Arrays.equals(this.to, other.getTo()))) &&
            ((this.from==null && other.getFrom()==null) || 
             (this.from!=null &&
              this.from.equals(other.getFrom()))) &&
            ((this.idiomaCorreo==null && other.getIdiomaCorreo()==null) || 
             (this.idiomaCorreo!=null &&
              this.idiomaCorreo.equals(other.getIdiomaCorreo()))) &&
            ((this.usuario==null && other.getUsuario()==null) || 
             (this.usuario!=null &&
              this.usuario.equals(other.getUsuario())));
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
        if (getHrefLogo() != null) {
            _hashCode += getHrefLogo().hashCode();
        }
        if (getUrlImagenLogo() != null) {
            _hashCode += getUrlImagenLogo().hashCode();
        }
        if (getEmailRemitente() != null) {
            _hashCode += getEmailRemitente().hashCode();
        }
        if (getTituloOde() != null) {
            _hashCode += getTituloOde().hashCode();
        }
        if (getUrlFicha() != null) {
            _hashCode += getUrlFicha().hashCode();
        }
        if (getNombreRemitente() != null) {
            _hashCode += getNombreRemitente().hashCode();
        }
        if (getNombreDestinatario() != null) {
            _hashCode += getNombreDestinatario().hashCode();
        }
        if (getUrlImagen() != null) {
            _hashCode += getUrlImagen().hashCode();
        }
        if (getComentario() != null) {
            _hashCode += getComentario().hashCode();
        }
        if (getValoracion() != null) {
            _hashCode += getValoracion().hashCode();
        }
        if (getComunidad() != null) {
            _hashCode += getComunidad().hashCode();
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
        if (getFrom() != null) {
            _hashCode += getFrom().hashCode();
        }
        if (getIdiomaCorreo() != null) {
            _hashCode += getIdiomaCorreo().hashCode();
        }
        if (getUsuario() != null) {
            _hashCode += getUsuario().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CorreoOdeVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "CorreoOdeVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailRemitente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "emailRemitente"));
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
        elemField.setFieldName("urlFicha");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "urlFicha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreRemitente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "nombreRemitente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreDestinatario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "nombreDestinatario"));
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
        elemField.setFieldName("comentario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "comentario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valoracion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "valoracion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comunidad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "comunidad"));
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
        elemField.setFieldName("usuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.gestorCorreo.pode.es", "usuario"));
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
