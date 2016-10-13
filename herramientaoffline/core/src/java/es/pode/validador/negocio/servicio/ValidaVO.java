/**
 * ValidaVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.validador.negocio.servicio;


/**
 * Tipo que sera devuelto por los metodos del servicio. Esta
 *                         compuesto por tres Atributos:
 *                         rutaManifest: su tipo es string e informa
 * de la ruta donde se
 *                         encuentra el fichero                     
 * 
 *                         principal imsmanifest.xml
 *                         esValidoManifes: su tipo es Boolean e informa
 * de si la
 *                         validacion ha sido correcta o no
 *                         resultadoValidacion: su tipo es String e informa
 * de los errores
 *                         que se han producido con una serie de codigos
 * que se detallan a
 *                         continuacion:
 *                         0.0 sin errores
 *                         1.1 imsmanifest es incorrecto
 *                         1.2 error de parseo en el manifest
 *                         1.3 error, la etiqueta principal debe ser
 * manifest o lom
 *                         1.4 error de chequeo de contenidos
 *                         2.1 el LOM-ES es obligatorio para metadata
 * 2.2 el LOM-ES es incorrecto
 *                         2.3 el LOM-ES es incorrecto para la etiqueta
 * de organizaciones
 *                         3.1 no existe el ODE
 *                         4.1 los datos del formulario son obligatorios
 * 4.2 el Titulo es obligatorio
 *                         4.3 el Idioma es obligatorio
 *                         4.4 la Descripcion es obligatoria
 *                         4.5 el Tipo es obligatorio
 *                         4.6 el Contexto es obligatorio
 *                         4.7 la Edad es obligatoria
 *                         4.8 el Idioma Destino es obligatorio
 *                         4.9 el Proceso Cognitivo es obligatorio
 *                         detalleValidacion: de tipo String, donde se
 * recogerán los
 *                         errores de Xerces cuando se produzca un error
 * en el parseo
 *                         contra esquemas.
 */
public class ValidaVO  implements java.io.Serializable {
    private java.lang.String rutaManifest;

    private java.lang.Boolean esValidoManifest;

    private java.lang.String resultadoValidacion;

    private es.pode.validador.negocio.servicio.ErrorParseoVO[] errores;

    public ValidaVO() {
    }

    public ValidaVO(
           java.lang.String rutaManifest,
           java.lang.Boolean esValidoManifest,
           java.lang.String resultadoValidacion,
           es.pode.validador.negocio.servicio.ErrorParseoVO[] errores) {
           this.rutaManifest = rutaManifest;
           this.esValidoManifest = esValidoManifest;
           this.resultadoValidacion = resultadoValidacion;
           this.errores = errores;
    }


    /**
     * Gets the rutaManifest value for this ValidaVO.
     * 
     * @return rutaManifest
     */
    public java.lang.String getRutaManifest() {
        return rutaManifest;
    }


    /**
     * Sets the rutaManifest value for this ValidaVO.
     * 
     * @param rutaManifest
     */
    public void setRutaManifest(java.lang.String rutaManifest) {
        this.rutaManifest = rutaManifest;
    }


    /**
     * Gets the esValidoManifest value for this ValidaVO.
     * 
     * @return esValidoManifest
     */
    public java.lang.Boolean getEsValidoManifest() {
        return esValidoManifest;
    }


    /**
     * Sets the esValidoManifest value for this ValidaVO.
     * 
     * @param esValidoManifest
     */
    public void setEsValidoManifest(java.lang.Boolean esValidoManifest) {
        this.esValidoManifest = esValidoManifest;
    }


    /**
     * Gets the resultadoValidacion value for this ValidaVO.
     * 
     * @return resultadoValidacion
     */
    public java.lang.String getResultadoValidacion() {
        return resultadoValidacion;
    }


    /**
     * Sets the resultadoValidacion value for this ValidaVO.
     * 
     * @param resultadoValidacion
     */
    public void setResultadoValidacion(java.lang.String resultadoValidacion) {
        this.resultadoValidacion = resultadoValidacion;
    }


    /**
     * Gets the errores value for this ValidaVO.
     * 
     * @return errores
     */
    public es.pode.validador.negocio.servicio.ErrorParseoVO[] getErrores() {
        return errores;
    }


    /**
     * Sets the errores value for this ValidaVO.
     * 
     * @param errores
     */
    public void setErrores(es.pode.validador.negocio.servicio.ErrorParseoVO[] errores) {
        this.errores = errores;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ValidaVO)) return false;
        ValidaVO other = (ValidaVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.rutaManifest==null && other.getRutaManifest()==null) || 
             (this.rutaManifest!=null &&
              this.rutaManifest.equals(other.getRutaManifest()))) &&
            ((this.esValidoManifest==null && other.getEsValidoManifest()==null) || 
             (this.esValidoManifest!=null &&
              this.esValidoManifest.equals(other.getEsValidoManifest()))) &&
            ((this.resultadoValidacion==null && other.getResultadoValidacion()==null) || 
             (this.resultadoValidacion!=null &&
              this.resultadoValidacion.equals(other.getResultadoValidacion()))) &&
            ((this.errores==null && other.getErrores()==null) || 
             (this.errores!=null &&
              java.util.Arrays.equals(this.errores, other.getErrores())));
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
        if (getRutaManifest() != null) {
            _hashCode += getRutaManifest().hashCode();
        }
        if (getEsValidoManifest() != null) {
            _hashCode += getEsValidoManifest().hashCode();
        }
        if (getResultadoValidacion() != null) {
            _hashCode += getResultadoValidacion().hashCode();
        }
        if (getErrores() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getErrores());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getErrores(), i);
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
        new org.apache.axis.description.TypeDesc(ValidaVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "ValidaVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rutaManifest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "rutaManifest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esValidoManifest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "esValidoManifest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoValidacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "resultadoValidacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errores");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "errores"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "ErrorParseoVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.validador.pode.es", "item"));
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
