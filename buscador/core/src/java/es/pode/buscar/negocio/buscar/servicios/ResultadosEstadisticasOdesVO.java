/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * ResultadosEstadisticasOdesVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.buscar.negocio.buscar.servicios;

public class ResultadosEstadisticasOdesVO  implements java.io.Serializable {
    private int numLocalObjetos;

    private int numTotalObjetos;

    private int numLocalCursos;

    private int numTotalCursos;

    private int numLocalSecuencias;

    private int numTotalSecuencias;

    private int numLocalObjetosAprendizaje;

    private int numTotalObjetosAprendizaje;

    private int numLocalMediosIntegrados;

    private int numTotalMediosIntegrados;

    private java.lang.String[] comunidadesCursos;

    private java.lang.String[] comunidadesObjetos;

    private java.lang.String[] comunidadesSecuencias;

    private java.lang.String[] comunidadesObjAprendizaje;

    private java.lang.String[] comunidadesMediosIntegrados;

    private int[] comunidadesCursosValores;

    private int[] comunidadesMediosIntegradosValores;

    private int[] comunidadesObjAprendizajeValores;

    private int[] comunidadesObjetosValores;

    private int[] comunidadesSecuenciasValores;

    public ResultadosEstadisticasOdesVO() {
    }

    public ResultadosEstadisticasOdesVO(
           int numLocalObjetos,
           int numTotalObjetos,
           int numLocalCursos,
           int numTotalCursos,
           int numLocalSecuencias,
           int numTotalSecuencias,
           int numLocalObjetosAprendizaje,
           int numTotalObjetosAprendizaje,
           int numLocalMediosIntegrados,
           int numTotalMediosIntegrados,
           java.lang.String[] comunidadesCursos,
           java.lang.String[] comunidadesObjetos,
           java.lang.String[] comunidadesSecuencias,
           java.lang.String[] comunidadesObjAprendizaje,
           java.lang.String[] comunidadesMediosIntegrados,
           int[] comunidadesCursosValores,
           int[] comunidadesMediosIntegradosValores,
           int[] comunidadesObjAprendizajeValores,
           int[] comunidadesObjetosValores,
           int[] comunidadesSecuenciasValores) {
           this.numLocalObjetos = numLocalObjetos;
           this.numTotalObjetos = numTotalObjetos;
           this.numLocalCursos = numLocalCursos;
           this.numTotalCursos = numTotalCursos;
           this.numLocalSecuencias = numLocalSecuencias;
           this.numTotalSecuencias = numTotalSecuencias;
           this.numLocalObjetosAprendizaje = numLocalObjetosAprendizaje;
           this.numTotalObjetosAprendizaje = numTotalObjetosAprendizaje;
           this.numLocalMediosIntegrados = numLocalMediosIntegrados;
           this.numTotalMediosIntegrados = numTotalMediosIntegrados;
           this.comunidadesCursos = comunidadesCursos;
           this.comunidadesObjetos = comunidadesObjetos;
           this.comunidadesSecuencias = comunidadesSecuencias;
           this.comunidadesObjAprendizaje = comunidadesObjAprendizaje;
           this.comunidadesMediosIntegrados = comunidadesMediosIntegrados;
           this.comunidadesCursosValores = comunidadesCursosValores;
           this.comunidadesMediosIntegradosValores = comunidadesMediosIntegradosValores;
           this.comunidadesObjAprendizajeValores = comunidadesObjAprendizajeValores;
           this.comunidadesObjetosValores = comunidadesObjetosValores;
           this.comunidadesSecuenciasValores = comunidadesSecuenciasValores;
    }


    /**
     * Gets the numLocalObjetos value for this ResultadosEstadisticasOdesVO.
     * 
     * @return numLocalObjetos
     */
    public int getNumLocalObjetos() {
        return numLocalObjetos;
    }


    /**
     * Sets the numLocalObjetos value for this ResultadosEstadisticasOdesVO.
     * 
     * @param numLocalObjetos
     */
    public void setNumLocalObjetos(int numLocalObjetos) {
        this.numLocalObjetos = numLocalObjetos;
    }


    /**
     * Gets the numTotalObjetos value for this ResultadosEstadisticasOdesVO.
     * 
     * @return numTotalObjetos
     */
    public int getNumTotalObjetos() {
        return numTotalObjetos;
    }


    /**
     * Sets the numTotalObjetos value for this ResultadosEstadisticasOdesVO.
     * 
     * @param numTotalObjetos
     */
    public void setNumTotalObjetos(int numTotalObjetos) {
        this.numTotalObjetos = numTotalObjetos;
    }


    /**
     * Gets the numLocalCursos value for this ResultadosEstadisticasOdesVO.
     * 
     * @return numLocalCursos
     */
    public int getNumLocalCursos() {
        return numLocalCursos;
    }


    /**
     * Sets the numLocalCursos value for this ResultadosEstadisticasOdesVO.
     * 
     * @param numLocalCursos
     */
    public void setNumLocalCursos(int numLocalCursos) {
        this.numLocalCursos = numLocalCursos;
    }


    /**
     * Gets the numTotalCursos value for this ResultadosEstadisticasOdesVO.
     * 
     * @return numTotalCursos
     */
    public int getNumTotalCursos() {
        return numTotalCursos;
    }


    /**
     * Sets the numTotalCursos value for this ResultadosEstadisticasOdesVO.
     * 
     * @param numTotalCursos
     */
    public void setNumTotalCursos(int numTotalCursos) {
        this.numTotalCursos = numTotalCursos;
    }


    /**
     * Gets the numLocalSecuencias value for this ResultadosEstadisticasOdesVO.
     * 
     * @return numLocalSecuencias
     */
    public int getNumLocalSecuencias() {
        return numLocalSecuencias;
    }


    /**
     * Sets the numLocalSecuencias value for this ResultadosEstadisticasOdesVO.
     * 
     * @param numLocalSecuencias
     */
    public void setNumLocalSecuencias(int numLocalSecuencias) {
        this.numLocalSecuencias = numLocalSecuencias;
    }


    /**
     * Gets the numTotalSecuencias value for this ResultadosEstadisticasOdesVO.
     * 
     * @return numTotalSecuencias
     */
    public int getNumTotalSecuencias() {
        return numTotalSecuencias;
    }


    /**
     * Sets the numTotalSecuencias value for this ResultadosEstadisticasOdesVO.
     * 
     * @param numTotalSecuencias
     */
    public void setNumTotalSecuencias(int numTotalSecuencias) {
        this.numTotalSecuencias = numTotalSecuencias;
    }


    /**
     * Gets the numLocalObjetosAprendizaje value for this ResultadosEstadisticasOdesVO.
     * 
     * @return numLocalObjetosAprendizaje
     */
    public int getNumLocalObjetosAprendizaje() {
        return numLocalObjetosAprendizaje;
    }


    /**
     * Sets the numLocalObjetosAprendizaje value for this ResultadosEstadisticasOdesVO.
     * 
     * @param numLocalObjetosAprendizaje
     */
    public void setNumLocalObjetosAprendizaje(int numLocalObjetosAprendizaje) {
        this.numLocalObjetosAprendizaje = numLocalObjetosAprendizaje;
    }


    /**
     * Gets the numTotalObjetosAprendizaje value for this ResultadosEstadisticasOdesVO.
     * 
     * @return numTotalObjetosAprendizaje
     */
    public int getNumTotalObjetosAprendizaje() {
        return numTotalObjetosAprendizaje;
    }


    /**
     * Sets the numTotalObjetosAprendizaje value for this ResultadosEstadisticasOdesVO.
     * 
     * @param numTotalObjetosAprendizaje
     */
    public void setNumTotalObjetosAprendizaje(int numTotalObjetosAprendizaje) {
        this.numTotalObjetosAprendizaje = numTotalObjetosAprendizaje;
    }


    /**
     * Gets the numLocalMediosIntegrados value for this ResultadosEstadisticasOdesVO.
     * 
     * @return numLocalMediosIntegrados
     */
    public int getNumLocalMediosIntegrados() {
        return numLocalMediosIntegrados;
    }


    /**
     * Sets the numLocalMediosIntegrados value for this ResultadosEstadisticasOdesVO.
     * 
     * @param numLocalMediosIntegrados
     */
    public void setNumLocalMediosIntegrados(int numLocalMediosIntegrados) {
        this.numLocalMediosIntegrados = numLocalMediosIntegrados;
    }


    /**
     * Gets the numTotalMediosIntegrados value for this ResultadosEstadisticasOdesVO.
     * 
     * @return numTotalMediosIntegrados
     */
    public int getNumTotalMediosIntegrados() {
        return numTotalMediosIntegrados;
    }


    /**
     * Sets the numTotalMediosIntegrados value for this ResultadosEstadisticasOdesVO.
     * 
     * @param numTotalMediosIntegrados
     */
    public void setNumTotalMediosIntegrados(int numTotalMediosIntegrados) {
        this.numTotalMediosIntegrados = numTotalMediosIntegrados;
    }


    /**
     * Gets the comunidadesCursos value for this ResultadosEstadisticasOdesVO.
     * 
     * @return comunidadesCursos
     */
    public java.lang.String[] getComunidadesCursos() {
        return comunidadesCursos;
    }


    /**
     * Sets the comunidadesCursos value for this ResultadosEstadisticasOdesVO.
     * 
     * @param comunidadesCursos
     */
    public void setComunidadesCursos(java.lang.String[] comunidadesCursos) {
        this.comunidadesCursos = comunidadesCursos;
    }


    /**
     * Gets the comunidadesObjetos value for this ResultadosEstadisticasOdesVO.
     * 
     * @return comunidadesObjetos
     */
    public java.lang.String[] getComunidadesObjetos() {
        return comunidadesObjetos;
    }


    /**
     * Sets the comunidadesObjetos value for this ResultadosEstadisticasOdesVO.
     * 
     * @param comunidadesObjetos
     */
    public void setComunidadesObjetos(java.lang.String[] comunidadesObjetos) {
        this.comunidadesObjetos = comunidadesObjetos;
    }


    /**
     * Gets the comunidadesSecuencias value for this ResultadosEstadisticasOdesVO.
     * 
     * @return comunidadesSecuencias
     */
    public java.lang.String[] getComunidadesSecuencias() {
        return comunidadesSecuencias;
    }


    /**
     * Sets the comunidadesSecuencias value for this ResultadosEstadisticasOdesVO.
     * 
     * @param comunidadesSecuencias
     */
    public void setComunidadesSecuencias(java.lang.String[] comunidadesSecuencias) {
        this.comunidadesSecuencias = comunidadesSecuencias;
    }


    /**
     * Gets the comunidadesObjAprendizaje value for this ResultadosEstadisticasOdesVO.
     * 
     * @return comunidadesObjAprendizaje
     */
    public java.lang.String[] getComunidadesObjAprendizaje() {
        return comunidadesObjAprendizaje;
    }


    /**
     * Sets the comunidadesObjAprendizaje value for this ResultadosEstadisticasOdesVO.
     * 
     * @param comunidadesObjAprendizaje
     */
    public void setComunidadesObjAprendizaje(java.lang.String[] comunidadesObjAprendizaje) {
        this.comunidadesObjAprendizaje = comunidadesObjAprendizaje;
    }


    /**
     * Gets the comunidadesMediosIntegrados value for this ResultadosEstadisticasOdesVO.
     * 
     * @return comunidadesMediosIntegrados
     */
    public java.lang.String[] getComunidadesMediosIntegrados() {
        return comunidadesMediosIntegrados;
    }


    /**
     * Sets the comunidadesMediosIntegrados value for this ResultadosEstadisticasOdesVO.
     * 
     * @param comunidadesMediosIntegrados
     */
    public void setComunidadesMediosIntegrados(java.lang.String[] comunidadesMediosIntegrados) {
        this.comunidadesMediosIntegrados = comunidadesMediosIntegrados;
    }


    /**
     * Gets the comunidadesCursosValores value for this ResultadosEstadisticasOdesVO.
     * 
     * @return comunidadesCursosValores
     */
    public int[] getComunidadesCursosValores() {
        return comunidadesCursosValores;
    }


    /**
     * Sets the comunidadesCursosValores value for this ResultadosEstadisticasOdesVO.
     * 
     * @param comunidadesCursosValores
     */
    public void setComunidadesCursosValores(int[] comunidadesCursosValores) {
        this.comunidadesCursosValores = comunidadesCursosValores;
    }


    /**
     * Gets the comunidadesMediosIntegradosValores value for this ResultadosEstadisticasOdesVO.
     * 
     * @return comunidadesMediosIntegradosValores
     */
    public int[] getComunidadesMediosIntegradosValores() {
        return comunidadesMediosIntegradosValores;
    }


    /**
     * Sets the comunidadesMediosIntegradosValores value for this ResultadosEstadisticasOdesVO.
     * 
     * @param comunidadesMediosIntegradosValores
     */
    public void setComunidadesMediosIntegradosValores(int[] comunidadesMediosIntegradosValores) {
        this.comunidadesMediosIntegradosValores = comunidadesMediosIntegradosValores;
    }


    /**
     * Gets the comunidadesObjAprendizajeValores value for this ResultadosEstadisticasOdesVO.
     * 
     * @return comunidadesObjAprendizajeValores
     */
    public int[] getComunidadesObjAprendizajeValores() {
        return comunidadesObjAprendizajeValores;
    }


    /**
     * Sets the comunidadesObjAprendizajeValores value for this ResultadosEstadisticasOdesVO.
     * 
     * @param comunidadesObjAprendizajeValores
     */
    public void setComunidadesObjAprendizajeValores(int[] comunidadesObjAprendizajeValores) {
        this.comunidadesObjAprendizajeValores = comunidadesObjAprendizajeValores;
    }


    /**
     * Gets the comunidadesObjetosValores value for this ResultadosEstadisticasOdesVO.
     * 
     * @return comunidadesObjetosValores
     */
    public int[] getComunidadesObjetosValores() {
        return comunidadesObjetosValores;
    }


    /**
     * Sets the comunidadesObjetosValores value for this ResultadosEstadisticasOdesVO.
     * 
     * @param comunidadesObjetosValores
     */
    public void setComunidadesObjetosValores(int[] comunidadesObjetosValores) {
        this.comunidadesObjetosValores = comunidadesObjetosValores;
    }


    /**
     * Gets the comunidadesSecuenciasValores value for this ResultadosEstadisticasOdesVO.
     * 
     * @return comunidadesSecuenciasValores
     */
    public int[] getComunidadesSecuenciasValores() {
        return comunidadesSecuenciasValores;
    }


    /**
     * Sets the comunidadesSecuenciasValores value for this ResultadosEstadisticasOdesVO.
     * 
     * @param comunidadesSecuenciasValores
     */
    public void setComunidadesSecuenciasValores(int[] comunidadesSecuenciasValores) {
        this.comunidadesSecuenciasValores = comunidadesSecuenciasValores;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadosEstadisticasOdesVO)) return false;
        ResultadosEstadisticasOdesVO other = (ResultadosEstadisticasOdesVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.numLocalObjetos == other.getNumLocalObjetos() &&
            this.numTotalObjetos == other.getNumTotalObjetos() &&
            this.numLocalCursos == other.getNumLocalCursos() &&
            this.numTotalCursos == other.getNumTotalCursos() &&
            this.numLocalSecuencias == other.getNumLocalSecuencias() &&
            this.numTotalSecuencias == other.getNumTotalSecuencias() &&
            this.numLocalObjetosAprendizaje == other.getNumLocalObjetosAprendizaje() &&
            this.numTotalObjetosAprendizaje == other.getNumTotalObjetosAprendizaje() &&
            this.numLocalMediosIntegrados == other.getNumLocalMediosIntegrados() &&
            this.numTotalMediosIntegrados == other.getNumTotalMediosIntegrados() &&
            ((this.comunidadesCursos==null && other.getComunidadesCursos()==null) || 
             (this.comunidadesCursos!=null &&
              java.util.Arrays.equals(this.comunidadesCursos, other.getComunidadesCursos()))) &&
            ((this.comunidadesObjetos==null && other.getComunidadesObjetos()==null) || 
             (this.comunidadesObjetos!=null &&
              java.util.Arrays.equals(this.comunidadesObjetos, other.getComunidadesObjetos()))) &&
            ((this.comunidadesSecuencias==null && other.getComunidadesSecuencias()==null) || 
             (this.comunidadesSecuencias!=null &&
              java.util.Arrays.equals(this.comunidadesSecuencias, other.getComunidadesSecuencias()))) &&
            ((this.comunidadesObjAprendizaje==null && other.getComunidadesObjAprendizaje()==null) || 
             (this.comunidadesObjAprendizaje!=null &&
              java.util.Arrays.equals(this.comunidadesObjAprendizaje, other.getComunidadesObjAprendizaje()))) &&
            ((this.comunidadesMediosIntegrados==null && other.getComunidadesMediosIntegrados()==null) || 
             (this.comunidadesMediosIntegrados!=null &&
              java.util.Arrays.equals(this.comunidadesMediosIntegrados, other.getComunidadesMediosIntegrados()))) &&
            ((this.comunidadesCursosValores==null && other.getComunidadesCursosValores()==null) || 
             (this.comunidadesCursosValores!=null &&
              java.util.Arrays.equals(this.comunidadesCursosValores, other.getComunidadesCursosValores()))) &&
            ((this.comunidadesMediosIntegradosValores==null && other.getComunidadesMediosIntegradosValores()==null) || 
             (this.comunidadesMediosIntegradosValores!=null &&
              java.util.Arrays.equals(this.comunidadesMediosIntegradosValores, other.getComunidadesMediosIntegradosValores()))) &&
            ((this.comunidadesObjAprendizajeValores==null && other.getComunidadesObjAprendizajeValores()==null) || 
             (this.comunidadesObjAprendizajeValores!=null &&
              java.util.Arrays.equals(this.comunidadesObjAprendizajeValores, other.getComunidadesObjAprendizajeValores()))) &&
            ((this.comunidadesObjetosValores==null && other.getComunidadesObjetosValores()==null) || 
             (this.comunidadesObjetosValores!=null &&
              java.util.Arrays.equals(this.comunidadesObjetosValores, other.getComunidadesObjetosValores()))) &&
            ((this.comunidadesSecuenciasValores==null && other.getComunidadesSecuenciasValores()==null) || 
             (this.comunidadesSecuenciasValores!=null &&
              java.util.Arrays.equals(this.comunidadesSecuenciasValores, other.getComunidadesSecuenciasValores())));
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
        _hashCode += getNumLocalObjetos();
        _hashCode += getNumTotalObjetos();
        _hashCode += getNumLocalCursos();
        _hashCode += getNumTotalCursos();
        _hashCode += getNumLocalSecuencias();
        _hashCode += getNumTotalSecuencias();
        _hashCode += getNumLocalObjetosAprendizaje();
        _hashCode += getNumTotalObjetosAprendizaje();
        _hashCode += getNumLocalMediosIntegrados();
        _hashCode += getNumTotalMediosIntegrados();
        if (getComunidadesCursos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getComunidadesCursos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getComunidadesCursos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getComunidadesObjetos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getComunidadesObjetos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getComunidadesObjetos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getComunidadesSecuencias() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getComunidadesSecuencias());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getComunidadesSecuencias(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getComunidadesObjAprendizaje() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getComunidadesObjAprendizaje());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getComunidadesObjAprendizaje(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getComunidadesMediosIntegrados() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getComunidadesMediosIntegrados());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getComunidadesMediosIntegrados(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getComunidadesCursosValores() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getComunidadesCursosValores());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getComunidadesCursosValores(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getComunidadesMediosIntegradosValores() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getComunidadesMediosIntegradosValores());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getComunidadesMediosIntegradosValores(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getComunidadesObjAprendizajeValores() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getComunidadesObjAprendizajeValores());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getComunidadesObjAprendizajeValores(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getComunidadesObjetosValores() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getComunidadesObjetosValores());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getComunidadesObjetosValores(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getComunidadesSecuenciasValores() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getComunidadesSecuenciasValores());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getComunidadesSecuenciasValores(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadosEstadisticasOdesVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "ResultadosEstadisticasOdesVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numLocalObjetos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "numLocalObjetos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numTotalObjetos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "numTotalObjetos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numLocalCursos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "numLocalCursos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numTotalCursos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "numTotalCursos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numLocalSecuencias");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "numLocalSecuencias"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numTotalSecuencias");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "numTotalSecuencias"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numLocalObjetosAprendizaje");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "numLocalObjetosAprendizaje"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numTotalObjetosAprendizaje");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "numTotalObjetosAprendizaje"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numLocalMediosIntegrados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "numLocalMediosIntegrados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numTotalMediosIntegrados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "numTotalMediosIntegrados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comunidadesCursos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "comunidadesCursos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comunidadesObjetos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "comunidadesObjetos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comunidadesSecuencias");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "comunidadesSecuencias"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comunidadesObjAprendizaje");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "comunidadesObjAprendizaje"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comunidadesMediosIntegrados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "comunidadesMediosIntegrados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comunidadesCursosValores");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "comunidadesCursosValores"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comunidadesMediosIntegradosValores");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "comunidadesMediosIntegradosValores"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comunidadesObjAprendizajeValores");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "comunidadesObjAprendizajeValores"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comunidadesObjetosValores");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "comunidadesObjetosValores"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comunidadesSecuenciasValores");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.buscar.negocio.buscar.pode.es", "comunidadesSecuenciasValores"));
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
