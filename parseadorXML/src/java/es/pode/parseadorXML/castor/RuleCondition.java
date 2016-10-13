/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1</a>, using an XML
 * Schema.
 * $Id$
 */

package es.pode.parseadorXML.castor;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class RuleCondition.
 * 
 * @version $Revision$ $Date$
 */
public class RuleCondition implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _referencedObjective.
     */
    private java.lang.String _referencedObjective;

    /**
     * Field _measureThreshold.
     */
    private java.math.BigDecimal _measureThreshold;

    /**
     * Field _operator.
     */
    private es.pode.parseadorXML.castor.types.ConditionOperatorType _operator = es.pode.parseadorXML.castor.types.ConditionOperatorType.valueOf("noOp");

    /**
     * Field _condition.
     */
    private es.pode.parseadorXML.castor.types.SequencingRuleConditionType _condition;


      //----------------/
     //- Constructors -/
    //----------------/

    public RuleCondition() {
        super();
        setOperator(es.pode.parseadorXML.castor.types.ConditionOperatorType.valueOf("noOp"));
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'condition'.
     * 
     * @return the value of field 'Condition'.
     */
    public es.pode.parseadorXML.castor.types.SequencingRuleConditionType getCondition(
    ) {
        return this._condition;
    }

    /**
     * Returns the value of field 'measureThreshold'.
     * 
     * @return the value of field 'MeasureThreshold'.
     */
    public java.math.BigDecimal getMeasureThreshold(
    ) {
        return this._measureThreshold;
    }

    /**
     * Returns the value of field 'operator'.
     * 
     * @return the value of field 'Operator'.
     */
    public es.pode.parseadorXML.castor.types.ConditionOperatorType getOperator(
    ) {
        return this._operator;
    }

    /**
     * Returns the value of field 'referencedObjective'.
     * 
     * @return the value of field 'ReferencedObjective'.
     */
    public java.lang.String getReferencedObjective(
    ) {
        return this._referencedObjective;
    }

    /**
     * Method isValid.
     * 
     * @return true if this object is valid according to the schema
     */
    public boolean isValid(
    ) {
        try {
            validate();
        } catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    }

    /**
     * 
     * 
     * @param out
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void marshal(
            final java.io.Writer out)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        Marshaller.marshal(this, out);
    }

    /**
     * 
     * 
     * @param handler
     * @throws java.io.IOException if an IOException occurs during
     * marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     */
    public void marshal(
            final org.xml.sax.ContentHandler handler)
    throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        Marshaller.marshal(this, handler);
    }

    /**
     * Sets the value of field 'condition'.
     * 
     * @param condition the value of field 'condition'.
     */
    public void setCondition(
            final es.pode.parseadorXML.castor.types.SequencingRuleConditionType condition) {
        this._condition = condition;
    }

    /**
     * Sets the value of field 'measureThreshold'.
     * 
     * @param measureThreshold the value of field 'measureThreshold'
     */
    public void setMeasureThreshold(
            final java.math.BigDecimal measureThreshold) {
        this._measureThreshold = measureThreshold;
    }

    /**
     * Sets the value of field 'operator'.
     * 
     * @param operator the value of field 'operator'.
     */
    public void setOperator(
            final es.pode.parseadorXML.castor.types.ConditionOperatorType operator) {
        this._operator = operator;
    }

    /**
     * Sets the value of field 'referencedObjective'.
     * 
     * @param referencedObjective the value of field
     * 'referencedObjective'.
     */
    public void setReferencedObjective(
            final java.lang.String referencedObjective) {
        this._referencedObjective = referencedObjective;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * es.pode.parseadorXML.castorRuleCondition
     */
    public static es.pode.parseadorXML.castor.RuleCondition unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (es.pode.parseadorXML.castor.RuleCondition) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.RuleCondition.class, reader);
    }

    /**
     * 
     * 
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void validate(
    )
    throws org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    }

}
