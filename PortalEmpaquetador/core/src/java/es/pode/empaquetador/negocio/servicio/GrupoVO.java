/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * GrupoVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.empaquetador.negocio.servicio;


/**
 * Clase que representa un elemento dentro de una organizacion. Se
 * corresponde con la clase Item.
 */
public class GrupoVO  implements java.io.Serializable {
    /* Identificador del grupo. Se corresponde con el atributo
     *                                 identifier de la clase Item. */
    private java.lang.String identifier;

    /* Titulo del grupo. Se corresponde con el atributo title de la
     * clase Item. */
    private java.lang.String title;

    /* Elemento referenciado por este grupo (recurso o submanifiesto).
     * Se corresponde con el atributo idRef de la clase Item. */
    private java.lang.String elementoReferenciado;

    /* Flag que identifica a este grupo como contenedor de grupos. */
    private java.lang.Boolean esCarpeta;

    /* Flag que indica si este grupo referencia a un recurso (i.e.,
     * el
     *                                 atributo elementoReferenciado tiene
     * como valor el identificador
     *                                 de un RecursoVO). */
    private java.lang.Boolean esRecurso;

    /* Flag que indica si este grupo referencia un manifiesto agregado
     * (i.e., elementoReferenciado tiene asignado el identificador de
     *                                 un submanifiesto). */
    private java.lang.Boolean esSubmanifiesto;

    /* URL que apunta al recurso en el servidor. */
    private java.lang.String recursoURL;

    /* Identificador del manifiesto que contiene a este item. */
    private java.lang.String idManifest;

    private java.lang.Boolean isVisible;

    /* Grupos hijos del grupo actual. Se corresponde con el atributo
     * item de la clase Item. */
    private es.pode.empaquetador.negocio.servicio.GrupoVO[] grupos;

    /* Secuencia asociada al Item. Se corresponde con el objeto
     *                                 Sequence almacenado en el atributo
     * Grp_any.Any_object de la
     *                                 clase Item. */
    private es.pode.empaquetador.negocio.servicio.SecuenciaVO[] secuencia;

    private es.pode.empaquetador.negocio.servicio.LomVO lom;

    private es.pode.empaquetador.negocio.servicio.PresentationVO presentation;

    private es.pode.empaquetador.negocio.servicio.TimeLimitActionVO timeLimitAction;

    private es.pode.empaquetador.negocio.servicio.DataFromLMSVO dataFromLMS;

    private es.pode.empaquetador.negocio.servicio.CompletionThresholdVO completionThreshold;

    public GrupoVO() {
    }

    public GrupoVO(
           java.lang.String identifier,
           java.lang.String title,
           java.lang.String elementoReferenciado,
           java.lang.Boolean esCarpeta,
           java.lang.Boolean esRecurso,
           java.lang.Boolean esSubmanifiesto,
           java.lang.String recursoURL,
           java.lang.String idManifest,
           java.lang.Boolean isVisible,
           es.pode.empaquetador.negocio.servicio.GrupoVO[] grupos,
           es.pode.empaquetador.negocio.servicio.SecuenciaVO[] secuencia,
           es.pode.empaquetador.negocio.servicio.LomVO lom,
           es.pode.empaquetador.negocio.servicio.PresentationVO presentation,
           es.pode.empaquetador.negocio.servicio.TimeLimitActionVO timeLimitAction,
           es.pode.empaquetador.negocio.servicio.DataFromLMSVO dataFromLMS,
           es.pode.empaquetador.negocio.servicio.CompletionThresholdVO completionThreshold) {
           this.identifier = identifier;
           this.title = title;
           this.elementoReferenciado = elementoReferenciado;
           this.esCarpeta = esCarpeta;
           this.esRecurso = esRecurso;
           this.esSubmanifiesto = esSubmanifiesto;
           this.recursoURL = recursoURL;
           this.idManifest = idManifest;
           this.isVisible = isVisible;
           this.grupos = grupos;
           this.secuencia = secuencia;
           this.lom = lom;
           this.presentation = presentation;
           this.timeLimitAction = timeLimitAction;
           this.dataFromLMS = dataFromLMS;
           this.completionThreshold = completionThreshold;
    }


    /**
     * Gets the identifier value for this GrupoVO.
     * 
     * @return identifier   * Identificador del grupo. Se corresponde con el atributo
     *                                 identifier de la clase Item.
     */
    public java.lang.String getIdentifier() {
        return identifier;
    }


    /**
     * Sets the identifier value for this GrupoVO.
     * 
     * @param identifier   * Identificador del grupo. Se corresponde con el atributo
     *                                 identifier de la clase Item.
     */
    public void setIdentifier(java.lang.String identifier) {
        this.identifier = identifier;
    }


    /**
     * Gets the title value for this GrupoVO.
     * 
     * @return title   * Titulo del grupo. Se corresponde con el atributo title de la
     * clase Item.
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this GrupoVO.
     * 
     * @param title   * Titulo del grupo. Se corresponde con el atributo title de la
     * clase Item.
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }


    /**
     * Gets the elementoReferenciado value for this GrupoVO.
     * 
     * @return elementoReferenciado   * Elemento referenciado por este grupo (recurso o submanifiesto).
     * Se corresponde con el atributo idRef de la clase Item.
     */
    public java.lang.String getElementoReferenciado() {
        return elementoReferenciado;
    }


    /**
     * Sets the elementoReferenciado value for this GrupoVO.
     * 
     * @param elementoReferenciado   * Elemento referenciado por este grupo (recurso o submanifiesto).
     * Se corresponde con el atributo idRef de la clase Item.
     */
    public void setElementoReferenciado(java.lang.String elementoReferenciado) {
        this.elementoReferenciado = elementoReferenciado;
    }


    /**
     * Gets the esCarpeta value for this GrupoVO.
     * 
     * @return esCarpeta   * Flag que identifica a este grupo como contenedor de grupos.
     */
    public java.lang.Boolean getEsCarpeta() {
        return esCarpeta;
    }


    /**
     * Sets the esCarpeta value for this GrupoVO.
     * 
     * @param esCarpeta   * Flag que identifica a este grupo como contenedor de grupos.
     */
    public void setEsCarpeta(java.lang.Boolean esCarpeta) {
        this.esCarpeta = esCarpeta;
    }


    /**
     * Gets the esRecurso value for this GrupoVO.
     * 
     * @return esRecurso   * Flag que indica si este grupo referencia a un recurso (i.e.,
     * el
     *                                 atributo elementoReferenciado tiene
     * como valor el identificador
     *                                 de un RecursoVO).
     */
    public java.lang.Boolean getEsRecurso() {
        return esRecurso;
    }


    /**
     * Sets the esRecurso value for this GrupoVO.
     * 
     * @param esRecurso   * Flag que indica si este grupo referencia a un recurso (i.e.,
     * el
     *                                 atributo elementoReferenciado tiene
     * como valor el identificador
     *                                 de un RecursoVO).
     */
    public void setEsRecurso(java.lang.Boolean esRecurso) {
        this.esRecurso = esRecurso;
    }


    /**
     * Gets the esSubmanifiesto value for this GrupoVO.
     * 
     * @return esSubmanifiesto   * Flag que indica si este grupo referencia un manifiesto agregado
     * (i.e., elementoReferenciado tiene asignado el identificador de
     *                                 un submanifiesto).
     */
    public java.lang.Boolean getEsSubmanifiesto() {
        return esSubmanifiesto;
    }


    /**
     * Sets the esSubmanifiesto value for this GrupoVO.
     * 
     * @param esSubmanifiesto   * Flag que indica si este grupo referencia un manifiesto agregado
     * (i.e., elementoReferenciado tiene asignado el identificador de
     *                                 un submanifiesto).
     */
    public void setEsSubmanifiesto(java.lang.Boolean esSubmanifiesto) {
        this.esSubmanifiesto = esSubmanifiesto;
    }


    /**
     * Gets the recursoURL value for this GrupoVO.
     * 
     * @return recursoURL   * URL que apunta al recurso en el servidor.
     */
    public java.lang.String getRecursoURL() {
        return recursoURL;
    }


    /**
     * Sets the recursoURL value for this GrupoVO.
     * 
     * @param recursoURL   * URL que apunta al recurso en el servidor.
     */
    public void setRecursoURL(java.lang.String recursoURL) {
        this.recursoURL = recursoURL;
    }


    /**
     * Gets the idManifest value for this GrupoVO.
     * 
     * @return idManifest   * Identificador del manifiesto que contiene a este item.
     */
    public java.lang.String getIdManifest() {
        return idManifest;
    }


    /**
     * Sets the idManifest value for this GrupoVO.
     * 
     * @param idManifest   * Identificador del manifiesto que contiene a este item.
     */
    public void setIdManifest(java.lang.String idManifest) {
        this.idManifest = idManifest;
    }


    /**
     * Gets the isVisible value for this GrupoVO.
     * 
     * @return isVisible
     */
    public java.lang.Boolean getIsVisible() {
        return isVisible;
    }


    /**
     * Sets the isVisible value for this GrupoVO.
     * 
     * @param isVisible
     */
    public void setIsVisible(java.lang.Boolean isVisible) {
        this.isVisible = isVisible;
    }


    /**
     * Gets the grupos value for this GrupoVO.
     * 
     * @return grupos   * Grupos hijos del grupo actual. Se corresponde con el atributo
     * item de la clase Item.
     */
    public es.pode.empaquetador.negocio.servicio.GrupoVO[] getGrupos() {
        return grupos;
    }


    /**
     * Sets the grupos value for this GrupoVO.
     * 
     * @param grupos   * Grupos hijos del grupo actual. Se corresponde con el atributo
     * item de la clase Item.
     */
    public void setGrupos(es.pode.empaquetador.negocio.servicio.GrupoVO[] grupos) {
        this.grupos = grupos;
    }


    /**
     * Gets the secuencia value for this GrupoVO.
     * 
     * @return secuencia   * Secuencia asociada al Item. Se corresponde con el objeto
     *                                 Sequence almacenado en el atributo
     * Grp_any.Any_object de la
     *                                 clase Item.
     */
    public es.pode.empaquetador.negocio.servicio.SecuenciaVO[] getSecuencia() {
        return secuencia;
    }


    /**
     * Sets the secuencia value for this GrupoVO.
     * 
     * @param secuencia   * Secuencia asociada al Item. Se corresponde con el objeto
     *                                 Sequence almacenado en el atributo
     * Grp_any.Any_object de la
     *                                 clase Item.
     */
    public void setSecuencia(es.pode.empaquetador.negocio.servicio.SecuenciaVO[] secuencia) {
        this.secuencia = secuencia;
    }


    /**
     * Gets the lom value for this GrupoVO.
     * 
     * @return lom
     */
    public es.pode.empaquetador.negocio.servicio.LomVO getLom() {
        return lom;
    }


    /**
     * Sets the lom value for this GrupoVO.
     * 
     * @param lom
     */
    public void setLom(es.pode.empaquetador.negocio.servicio.LomVO lom) {
        this.lom = lom;
    }


    /**
     * Gets the presentation value for this GrupoVO.
     * 
     * @return presentation
     */
    public es.pode.empaquetador.negocio.servicio.PresentationVO getPresentation() {
        return presentation;
    }


    /**
     * Sets the presentation value for this GrupoVO.
     * 
     * @param presentation
     */
    public void setPresentation(es.pode.empaquetador.negocio.servicio.PresentationVO presentation) {
        this.presentation = presentation;
    }


    /**
     * Gets the timeLimitAction value for this GrupoVO.
     * 
     * @return timeLimitAction
     */
    public es.pode.empaquetador.negocio.servicio.TimeLimitActionVO getTimeLimitAction() {
        return timeLimitAction;
    }


    /**
     * Sets the timeLimitAction value for this GrupoVO.
     * 
     * @param timeLimitAction
     */
    public void setTimeLimitAction(es.pode.empaquetador.negocio.servicio.TimeLimitActionVO timeLimitAction) {
        this.timeLimitAction = timeLimitAction;
    }


    /**
     * Gets the dataFromLMS value for this GrupoVO.
     * 
     * @return dataFromLMS
     */
    public es.pode.empaquetador.negocio.servicio.DataFromLMSVO getDataFromLMS() {
        return dataFromLMS;
    }


    /**
     * Sets the dataFromLMS value for this GrupoVO.
     * 
     * @param dataFromLMS
     */
    public void setDataFromLMS(es.pode.empaquetador.negocio.servicio.DataFromLMSVO dataFromLMS) {
        this.dataFromLMS = dataFromLMS;
    }


    /**
     * Gets the completionThreshold value for this GrupoVO.
     * 
     * @return completionThreshold
     */
    public es.pode.empaquetador.negocio.servicio.CompletionThresholdVO getCompletionThreshold() {
        return completionThreshold;
    }


    /**
     * Sets the completionThreshold value for this GrupoVO.
     * 
     * @param completionThreshold
     */
    public void setCompletionThreshold(es.pode.empaquetador.negocio.servicio.CompletionThresholdVO completionThreshold) {
        this.completionThreshold = completionThreshold;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GrupoVO)) return false;
        GrupoVO other = (GrupoVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.identifier==null && other.getIdentifier()==null) || 
             (this.identifier!=null &&
              this.identifier.equals(other.getIdentifier()))) &&
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle()))) &&
            ((this.elementoReferenciado==null && other.getElementoReferenciado()==null) || 
             (this.elementoReferenciado!=null &&
              this.elementoReferenciado.equals(other.getElementoReferenciado()))) &&
            ((this.esCarpeta==null && other.getEsCarpeta()==null) || 
             (this.esCarpeta!=null &&
              this.esCarpeta.equals(other.getEsCarpeta()))) &&
            ((this.esRecurso==null && other.getEsRecurso()==null) || 
             (this.esRecurso!=null &&
              this.esRecurso.equals(other.getEsRecurso()))) &&
            ((this.esSubmanifiesto==null && other.getEsSubmanifiesto()==null) || 
             (this.esSubmanifiesto!=null &&
              this.esSubmanifiesto.equals(other.getEsSubmanifiesto()))) &&
            ((this.recursoURL==null && other.getRecursoURL()==null) || 
             (this.recursoURL!=null &&
              this.recursoURL.equals(other.getRecursoURL()))) &&
            ((this.idManifest==null && other.getIdManifest()==null) || 
             (this.idManifest!=null &&
              this.idManifest.equals(other.getIdManifest()))) &&
            ((this.isVisible==null && other.getIsVisible()==null) || 
             (this.isVisible!=null &&
              this.isVisible.equals(other.getIsVisible()))) &&
            ((this.grupos==null && other.getGrupos()==null) || 
             (this.grupos!=null &&
              java.util.Arrays.equals(this.grupos, other.getGrupos()))) &&
            ((this.secuencia==null && other.getSecuencia()==null) || 
             (this.secuencia!=null &&
              java.util.Arrays.equals(this.secuencia, other.getSecuencia()))) &&
            ((this.lom==null && other.getLom()==null) || 
             (this.lom!=null &&
              this.lom.equals(other.getLom()))) &&
            ((this.presentation==null && other.getPresentation()==null) || 
             (this.presentation!=null &&
              this.presentation.equals(other.getPresentation()))) &&
            ((this.timeLimitAction==null && other.getTimeLimitAction()==null) || 
             (this.timeLimitAction!=null &&
              this.timeLimitAction.equals(other.getTimeLimitAction()))) &&
            ((this.dataFromLMS==null && other.getDataFromLMS()==null) || 
             (this.dataFromLMS!=null &&
              this.dataFromLMS.equals(other.getDataFromLMS()))) &&
            ((this.completionThreshold==null && other.getCompletionThreshold()==null) || 
             (this.completionThreshold!=null &&
              this.completionThreshold.equals(other.getCompletionThreshold())));
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
        if (getIdentifier() != null) {
            _hashCode += getIdentifier().hashCode();
        }
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        if (getElementoReferenciado() != null) {
            _hashCode += getElementoReferenciado().hashCode();
        }
        if (getEsCarpeta() != null) {
            _hashCode += getEsCarpeta().hashCode();
        }
        if (getEsRecurso() != null) {
            _hashCode += getEsRecurso().hashCode();
        }
        if (getEsSubmanifiesto() != null) {
            _hashCode += getEsSubmanifiesto().hashCode();
        }
        if (getRecursoURL() != null) {
            _hashCode += getRecursoURL().hashCode();
        }
        if (getIdManifest() != null) {
            _hashCode += getIdManifest().hashCode();
        }
        if (getIsVisible() != null) {
            _hashCode += getIsVisible().hashCode();
        }
        if (getGrupos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getGrupos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getGrupos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSecuencia() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSecuencia());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSecuencia(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getLom() != null) {
            _hashCode += getLom().hashCode();
        }
        if (getPresentation() != null) {
            _hashCode += getPresentation().hashCode();
        }
        if (getTimeLimitAction() != null) {
            _hashCode += getTimeLimitAction().hashCode();
        }
        if (getDataFromLMS() != null) {
            _hashCode += getDataFromLMS().hashCode();
        }
        if (getCompletionThreshold() != null) {
            _hashCode += getCompletionThreshold().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GrupoVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "GrupoVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identifier");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "identifier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elementoReferenciado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "elementoReferenciado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esCarpeta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "esCarpeta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esRecurso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "esRecurso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esSubmanifiesto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "esSubmanifiesto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recursoURL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "recursoURL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idManifest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "idManifest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isVisible");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "isVisible"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("grupos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "grupos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "GrupoVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secuencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "secuencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "SecuenciaVO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lom");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "lom"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "LomVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("presentation");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "presentation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "PresentationVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeLimitAction");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "timeLimitAction"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "TimeLimitActionVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataFromLMS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "dataFromLMS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "DataFromLMSVO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("completionThreshold");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "completionThreshold"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://servicio.negocio.empaquetador.pode.es", "CompletionThresholdVO"));
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
