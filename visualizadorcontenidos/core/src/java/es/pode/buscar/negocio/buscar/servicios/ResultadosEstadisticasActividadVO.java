/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * ResultadosEstadisticasActividadVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.buscar.servicios;

public class ResultadosEstadisticasActividadVO  implements java.io.Serializable {
    private int busquedasLocales;

    private int descargasTotales;

    private int busquedasTotales;

    private int fichasAccedidasLocales;

    private int fichasAccedidasTotales;

    private int odesPrevisualizadosLocales;

    private int odesPrevisualizadosTotales;

    private int descargas;

    private java.lang.String[] comunidadesBusquedas;

    private int[] comunidadesBusquedasValores;

    private java.lang.String[] comunidadesDescargas;

    private int[] comunidadesDescargasValores;

    private java.lang.String[] comunidadesFichasAccedidas;

    private int[] comunidadesFichasAccedidasValores;

    private java.lang.String[] comunidadesOdesPrevisualizados;

    private int[] comunidadesOdesPrevisualizadosValores;

    public ResultadosEstadisticasActividadVO() {
    }

    public ResultadosEstadisticasActividadVO(
           int busquedasLocales,
           int descargasTotales,
           int busquedasTotales,
           int fichasAccedidasLocales,
           int fichasAccedidasTotales,
           int odesPrevisualizadosLocales,
           int odesPrevisualizadosTotales,
           int descargas,
           java.lang.String[] comunidadesBusquedas,
           int[] comunidadesBusquedasValores,
           java.lang.String[] comunidadesDescargas,
           int[] comunidadesDescargasValores,
           java.lang.String[] comunidadesFichasAccedidas,
           int[] comunidadesFichasAccedidasValores,
           java.lang.String[] comunidadesOdesPrevisualizados,
           int[] comunidadesOdesPrevisualizadosValores) {
           this.busquedasLocales = busquedasLocales;
           this.descargasTotales = descargasTotales;
           this.busquedasTotales = busquedasTotales;
           this.fichasAccedidasLocales = fichasAccedidasLocales;
           this.fichasAccedidasTotales = fichasAccedidasTotales;
           this.odesPrevisualizadosLocales = odesPrevisualizadosLocales;
           this.odesPrevisualizadosTotales = odesPrevisualizadosTotales;
           this.descargas = descargas;
           this.comunidadesBusquedas = comunidadesBusquedas;
           this.comunidadesBusquedasValores = comunidadesBusquedasValores;
           this.comunidadesDescargas = comunidadesDescargas;
           this.comunidadesDescargasValores = comunidadesDescargasValores;
           this.comunidadesFichasAccedidas = comunidadesFichasAccedidas;
           this.comunidadesFichasAccedidasValores = comunidadesFichasAccedidasValores;
           this.comunidadesOdesPrevisualizados = comunidadesOdesPrevisualizados;
           this.comunidadesOdesPrevisualizadosValores = comunidadesOdesPrevisualizadosValores;
    }


    /**
     * Gets the busquedasLocales value for this ResultadosEstadisticasActividadVO.
     * 
     * @return busquedasLocales
     */
    public int getBusquedasLocales() {
        return busquedasLocales;
    }


    /**
     * Sets the busquedasLocales value for this ResultadosEstadisticasActividadVO.
     * 
     * @param busquedasLocales
     */
    public void setBusquedasLocales(int busquedasLocales) {
        this.busquedasLocales = busquedasLocales;
    }


    /**
     * Gets the descargasTotales value for this ResultadosEstadisticasActividadVO.
     * 
     * @return descargasTotales
     */
    public int getDescargasTotales() {
        return descargasTotales;
    }


    /**
     * Sets the descargasTotales value for this ResultadosEstadisticasActividadVO.
     * 
     * @param descargasTotales
     */
    public void setDescargasTotales(int descargasTotales) {
        this.descargasTotales = descargasTotales;
    }


    /**
     * Gets the busquedasTotales value for this ResultadosEstadisticasActividadVO.
     * 
     * @return busquedasTotales
     */
    public int getBusquedasTotales() {
        return busquedasTotales;
    }


    /**
     * Sets the busquedasTotales value for this ResultadosEstadisticasActividadVO.
     * 
     * @param busquedasTotales
     */
    public void setBusquedasTotales(int busquedasTotales) {
        this.busquedasTotales = busquedasTotales;
    }


    /**
     * Gets the fichasAccedidasLocales value for this ResultadosEstadisticasActividadVO.
     * 
     * @return fichasAccedidasLocales
     */
    public int getFichasAccedidasLocales() {
        return fichasAccedidasLocales;
    }


    /**
     * Sets the fichasAccedidasLocales value for this ResultadosEstadisticasActividadVO.
     * 
     * @param fichasAccedidasLocales
     */
    public void setFichasAccedidasLocales(int fichasAccedidasLocales) {
        this.fichasAccedidasLocales = fichasAccedidasLocales;
    }


    /**
     * Gets the fichasAccedidasTotales value for this ResultadosEstadisticasActividadVO.
     * 
     * @return fichasAccedidasTotales
     */
    public int getFichasAccedidasTotales() {
        return fichasAccedidasTotales;
    }


    /**
     * Sets the fichasAccedidasTotales value for this ResultadosEstadisticasActividadVO.
     * 
     * @param fichasAccedidasTotales
     */
    public void setFichasAccedidasTotales(int fichasAccedidasTotales) {
        this.fichasAccedidasTotales = fichasAccedidasTotales;
    }


    /**
     * Gets the odesPrevisualizadosLocales value for this ResultadosEstadisticasActividadVO.
     * 
     * @return odesPrevisualizadosLocales
     */
    public int getOdesPrevisualizadosLocales() {
        return odesPrevisualizadosLocales;
    }


    /**
     * Sets the odesPrevisualizadosLocales value for this ResultadosEstadisticasActividadVO.
     * 
     * @param odesPrevisualizadosLocales
     */
    public void setOdesPrevisualizadosLocales(int odesPrevisualizadosLocales) {
        this.odesPrevisualizadosLocales = odesPrevisualizadosLocales;
    }


    /**
     * Gets the odesPrevisualizadosTotales value for this ResultadosEstadisticasActividadVO.
     * 
     * @return odesPrevisualizadosTotales
     */
    public int getOdesPrevisualizadosTotales() {
        return odesPrevisualizadosTotales;
    }


    /**
     * Sets the odesPrevisualizadosTotales value for this ResultadosEstadisticasActividadVO.
     * 
     * @param odesPrevisualizadosTotales
     */
    public void setOdesPrevisualizadosTotales(int odesPrevisualizadosTotales) {
        this.odesPrevisualizadosTotales = odesPrevisualizadosTotales;
    }


    /**
     * Gets the descargas value for this ResultadosEstadisticasActividadVO.
     * 
     * @return descargas
     */
    public int getDescargas() {
        return descargas;
    }


    /**
     * Sets the descargas value for this ResultadosEstadisticasActividadVO.
     * 
     * @param descargas
     */
    public void setDescargas(int descargas) {
        this.descargas = descargas;
    }


    /**
     * Gets the comunidadesBusquedas value for this ResultadosEstadisticasActividadVO.
     * 
     * @return comunidadesBusquedas
     */
    public java.lang.String[] getComunidadesBusquedas() {
        return comunidadesBusquedas;
    }


    /**
     * Sets the comunidadesBusquedas value for this ResultadosEstadisticasActividadVO.
     * 
     * @param comunidadesBusquedas
     */
    public void setComunidadesBusquedas(java.lang.String[] comunidadesBusquedas) {
        this.comunidadesBusquedas = comunidadesBusquedas;
    }


    /**
     * Gets the comunidadesBusquedasValores value for this ResultadosEstadisticasActividadVO.
     * 
     * @return comunidadesBusquedasValores
     */
    public int[] getComunidadesBusquedasValores() {
        return comunidadesBusquedasValores;
    }


    /**
     * Sets the comunidadesBusquedasValores value for this ResultadosEstadisticasActividadVO.
     * 
     * @param comunidadesBusquedasValores
     */
    public void setComunidadesBusquedasValores(int[] comunidadesBusquedasValores) {
        this.comunidadesBusquedasValores = comunidadesBusquedasValores;
    }


    /**
     * Gets the comunidadesDescargas value for this ResultadosEstadisticasActividadVO.
     * 
     * @return comunidadesDescargas
     */
    public java.lang.String[] getComunidadesDescargas() {
        return comunidadesDescargas;
    }


    /**
     * Sets the comunidadesDescargas value for this ResultadosEstadisticasActividadVO.
     * 
     * @param comunidadesDescargas
     */
    public void setComunidadesDescargas(java.lang.String[] comunidadesDescargas) {
        this.comunidadesDescargas = comunidadesDescargas;
    }


    /**
     * Gets the comunidadesDescargasValores value for this ResultadosEstadisticasActividadVO.
     * 
     * @return comunidadesDescargasValores
     */
    public int[] getComunidadesDescargasValores() {
        return comunidadesDescargasValores;
    }


    /**
     * Sets the comunidadesDescargasValores value for this ResultadosEstadisticasActividadVO.
     * 
     * @param comunidadesDescargasValores
     */
    public void setComunidadesDescargasValores(int[] comunidadesDescargasValores) {
        this.comunidadesDescargasValores = comunidadesDescargasValores;
    }


    /**
     * Gets the comunidadesFichasAccedidas value for this ResultadosEstadisticasActividadVO.
     * 
     * @return comunidadesFichasAccedidas
     */
    public java.lang.String[] getComunidadesFichasAccedidas() {
        return comunidadesFichasAccedidas;
    }


    /**
     * Sets the comunidadesFichasAccedidas value for this ResultadosEstadisticasActividadVO.
     * 
     * @param comunidadesFichasAccedidas
     */
    public void setComunidadesFichasAccedidas(java.lang.String[] comunidadesFichasAccedidas) {
        this.comunidadesFichasAccedidas = comunidadesFichasAccedidas;
    }


    /**
     * Gets the comunidadesFichasAccedidasValores value for this ResultadosEstadisticasActividadVO.
     * 
     * @return comunidadesFichasAccedidasValores
     */
    public int[] getComunidadesFichasAccedidasValores() {
        return comunidadesFichasAccedidasValores;
    }


    /**
     * Sets the comunidadesFichasAccedidasValores value for this ResultadosEstadisticasActividadVO.
     * 
     * @param comunidadesFichasAccedidasValores
     */
    public void setComunidadesFichasAccedidasValores(int[] comunidadesFichasAccedidasValores) {
        this.comunidadesFichasAccedidasValores = comunidadesFichasAccedidasValores;
    }


    /**
     * Gets the comunidadesOdesPrevisualizados value for this ResultadosEstadisticasActividadVO.
     * 
     * @return comunidadesOdesPrevisualizados
     */
    public java.lang.String[] getComunidadesOdesPrevisualizados() {
        return comunidadesOdesPrevisualizados;
    }


    /**
     * Sets the comunidadesOdesPrevisualizados value for this ResultadosEstadisticasActividadVO.
     * 
     * @param comunidadesOdesPrevisualizados
     */
    public void setComunidadesOdesPrevisualizados(java.lang.String[] comunidadesOdesPrevisualizados) {
        this.comunidadesOdesPrevisualizados = comunidadesOdesPrevisualizados;
    }


    /**
     * Gets the comunidadesOdesPrevisualizadosValores value for this ResultadosEstadisticasActividadVO.
     * 
     * @return comunidadesOdesPrevisualizadosValores
     */
    public int[] getComunidadesOdesPrevisualizadosValores() {
        return comunidadesOdesPrevisualizadosValores;
    }


    /**
     * Sets the comunidadesOdesPrevisualizadosValores value for this ResultadosEstadisticasActividadVO.
     * 
     * @param comunidadesOdesPrevisualizadosValores
     */
    public void setComunidadesOdesPrevisualizadosValores(int[] comunidadesOdesPrevisualizadosValores) {
        this.comunidadesOdesPrevisualizadosValores = comunidadesOdesPrevisualizadosValores;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadosEstadisticasActividadVO)) return false;
        ResultadosEstadisticasActividadVO other = (ResultadosEstadisticasActividadVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.busquedasLocales == other.getBusquedasLocales() &&
            this.descargasTotales == other.getDescargasTotales() &&
            this.busquedasTotales == other.getBusquedasTotales() &&
            this.fichasAccedidasLocales == other.getFichasAccedidasLocales() &&
            this.fichasAccedidasTotales == other.getFichasAccedidasTotales() &&
            this.odesPrevisualizadosLocales == other.getOdesPrevisualizadosLocales() &&
            this.odesPrevisualizadosTotales == other.getOdesPrevisualizadosTotales() &&
            this.descargas == other.getDescargas() &&
            ((this.comunidadesBusquedas==null && other.getComunidadesBusquedas()==null) || 
             (this.comunidadesBusquedas!=null &&
              java.util.Arrays.equals(this.comunidadesBusquedas, other.getComunidadesBusquedas()))) &&
            ((this.comunidadesBusquedasValores==null && other.getComunidadesBusquedasValores()==null) || 
             (this.comunidadesBusquedasValores!=null &&
              java.util.Arrays.equals(this.comunidadesBusquedasValores, other.getComunidadesBusquedasValores()))) &&
            ((this.comunidadesDescargas==null && other.getComunidadesDescargas()==null) || 
             (this.comunidadesDescargas!=null &&
              java.util.Arrays.equals(this.comunidadesDescargas, other.getComunidadesDescargas()))) &&
            ((this.comunidadesDescargasValores==null && other.getComunidadesDescargasValores()==null) || 
             (this.comunidadesDescargasValores!=null &&
              java.util.Arrays.equals(this.comunidadesDescargasValores, other.getComunidadesDescargasValores()))) &&
            ((this.comunidadesFichasAccedidas==null && other.getComunidadesFichasAccedidas()==null) || 
             (this.comunidadesFichasAccedidas!=null &&
              java.util.Arrays.equals(this.comunidadesFichasAccedidas, other.getComunidadesFichasAccedidas()))) &&
            ((this.comunidadesFichasAccedidasValores==null && other.getComunidadesFichasAccedidasValores()==null) || 
             (this.comunidadesFichasAccedidasValores!=null &&
              java.util.Arrays.equals(this.comunidadesFichasAccedidasValores, other.getComunidadesFichasAccedidasValores()))) &&
            ((this.comunidadesOdesPrevisualizados==null && other.getComunidadesOdesPrevisualizados()==null) || 
             (this.comunidadesOdesPrevisualizados!=null &&
              java.util.Arrays.equals(this.comunidadesOdesPrevisualizados, other.getComunidadesOdesPrevisualizados()))) &&
            ((this.comunidadesOdesPrevisualizadosValores==null && other.getComunidadesOdesPrevisualizadosValores()==null) || 
             (this.comunidadesOdesPrevisualizadosValores!=null &&
              java.util.Arrays.equals(this.comunidadesOdesPrevisualizadosValores, other.getComunidadesOdesPrevisualizadosValores())));
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
        _hashCode += getBusquedasLocales();
        _hashCode += getDescargasTotales();
        _hashCode += getBusquedasTotales();
        _hashCode += getFichasAccedidasLocales();
        _hashCode += getFichasAccedidasTotales();
        _hashCode += getOdesPrevisualizadosLocales();
        _hashCode += getOdesPrevisualizadosTotales();
        _hashCode += getDescargas();
        if (getComunidadesBusquedas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getComunidadesBusquedas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getComunidadesBusquedas(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getComunidadesBusquedasValores() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getComunidadesBusquedasValores());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getComunidadesBusquedasValores(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getComunidadesDescargas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getComunidadesDescargas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getComunidadesDescargas(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getComunidadesDescargasValores() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getComunidadesDescargasValores());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getComunidadesDescargasValores(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getComunidadesFichasAccedidas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getComunidadesFichasAccedidas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getComunidadesFichasAccedidas(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getComunidadesFichasAccedidasValores() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getComunidadesFichasAccedidasValores());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getComunidadesFichasAccedidasValores(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getComunidadesOdesPrevisualizados() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getComunidadesOdesPrevisualizados());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getComunidadesOdesPrevisualizados(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getComunidadesOdesPrevisualizadosValores() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getComunidadesOdesPrevisualizadosValores());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getComunidadesOdesPrevisualizadosValores(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadosEstadisticasActividadVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ResultadosEstadisticasActividadVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("busquedasLocales");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "busquedasLocales"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descargasTotales");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "descargasTotales"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("busquedasTotales");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "busquedasTotales"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fichasAccedidasLocales");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "fichasAccedidasLocales"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fichasAccedidasTotales");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "fichasAccedidasTotales"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("odesPrevisualizadosLocales");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "odesPrevisualizadosLocales"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("odesPrevisualizadosTotales");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "odesPrevisualizadosTotales"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descargas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "descargas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comunidadesBusquedas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "comunidadesBusquedas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comunidadesBusquedasValores");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "comunidadesBusquedasValores"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comunidadesDescargas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "comunidadesDescargas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comunidadesDescargasValores");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "comunidadesDescargasValores"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comunidadesFichasAccedidas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "comunidadesFichasAccedidas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comunidadesFichasAccedidasValores");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "comunidadesFichasAccedidasValores"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comunidadesOdesPrevisualizados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "comunidadesOdesPrevisualizados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comunidadesOdesPrevisualizadosValores");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "comunidadesOdesPrevisualizadosValores"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
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
