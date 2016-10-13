/**
 * ControlModeVO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.entregar.negocio.servicios;

public class ControlModeVO  implements java.io.Serializable {
    private java.lang.Boolean choice;

    private java.lang.Boolean choiceExit;

    private java.lang.Boolean flow;

    private java.lang.Boolean forwardOnly;

    public ControlModeVO() {
    }

    public ControlModeVO(
           java.lang.Boolean choice,
           java.lang.Boolean choiceExit,
           java.lang.Boolean flow,
           java.lang.Boolean forwardOnly) {
           this.choice = choice;
           this.choiceExit = choiceExit;
           this.flow = flow;
           this.forwardOnly = forwardOnly;
    }


    /**
     * Gets the choice value for this ControlModeVO.
     * 
     * @return choice
     */
    public java.lang.Boolean getChoice() {
        return choice;
    }


    /**
     * Sets the choice value for this ControlModeVO.
     * 
     * @param choice
     */
    public void setChoice(java.lang.Boolean choice) {
        this.choice = choice;
    }


    /**
     * Gets the choiceExit value for this ControlModeVO.
     * 
     * @return choiceExit
     */
    public java.lang.Boolean getChoiceExit() {
        return choiceExit;
    }


    /**
     * Sets the choiceExit value for this ControlModeVO.
     * 
     * @param choiceExit
     */
    public void setChoiceExit(java.lang.Boolean choiceExit) {
        this.choiceExit = choiceExit;
    }


    /**
     * Gets the flow value for this ControlModeVO.
     * 
     * @return flow
     */
    public java.lang.Boolean getFlow() {
        return flow;
    }


    /**
     * Sets the flow value for this ControlModeVO.
     * 
     * @param flow
     */
    public void setFlow(java.lang.Boolean flow) {
        this.flow = flow;
    }


    /**
     * Gets the forwardOnly value for this ControlModeVO.
     * 
     * @return forwardOnly
     */
    public java.lang.Boolean getForwardOnly() {
        return forwardOnly;
    }


    /**
     * Sets the forwardOnly value for this ControlModeVO.
     * 
     * @param forwardOnly
     */
    public void setForwardOnly(java.lang.Boolean forwardOnly) {
        this.forwardOnly = forwardOnly;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ControlModeVO)) return false;
        ControlModeVO other = (ControlModeVO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.choice==null && other.getChoice()==null) || 
             (this.choice!=null &&
              this.choice.equals(other.getChoice()))) &&
            ((this.choiceExit==null && other.getChoiceExit()==null) || 
             (this.choiceExit!=null &&
              this.choiceExit.equals(other.getChoiceExit()))) &&
            ((this.flow==null && other.getFlow()==null) || 
             (this.flow!=null &&
              this.flow.equals(other.getFlow()))) &&
            ((this.forwardOnly==null && other.getForwardOnly()==null) || 
             (this.forwardOnly!=null &&
              this.forwardOnly.equals(other.getForwardOnly())));
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
        if (getChoice() != null) {
            _hashCode += getChoice().hashCode();
        }
        if (getChoiceExit() != null) {
            _hashCode += getChoiceExit().hashCode();
        }
        if (getFlow() != null) {
            _hashCode += getFlow().hashCode();
        }
        if (getForwardOnly() != null) {
            _hashCode += getForwardOnly().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ControlModeVO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "ControlModeVO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("choice");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "choice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("choiceExit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "choiceExit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flow");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "flow"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("forwardOnly");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servicios.negocio.entregar.pode.es", "forwardOnly"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
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
