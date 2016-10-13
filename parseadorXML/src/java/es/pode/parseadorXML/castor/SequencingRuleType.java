/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1</a>, using an XML
 * Schema.
 * $Id$
 */

package es.pode.parseadorXML.castor;

/**
 * Class SequencingRuleType.
 * 
 * @version $Revision$ $Date$
 */
public abstract class SequencingRuleType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _ruleConditions.
     */
    private es.pode.parseadorXML.castor.RuleConditions _ruleConditions;


      //----------------/
     //- Constructors -/
    //----------------/

    public SequencingRuleType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'ruleConditions'.
     * 
     * @return the value of field 'RuleConditions'.
     */
    public es.pode.parseadorXML.castor.RuleConditions getRuleConditions(
    ) {
        return this._ruleConditions;
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
     * Sets the value of field 'ruleConditions'.
     * 
     * @param ruleConditions the value of field 'ruleConditions'.
     */
    public void setRuleConditions(
            final es.pode.parseadorXML.castor.RuleConditions ruleConditions) {
        this._ruleConditions = ruleConditions;
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
