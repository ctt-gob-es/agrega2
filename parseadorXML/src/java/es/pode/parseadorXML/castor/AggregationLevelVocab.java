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
 * Class AggregationLevelVocab.
 * 
 * @version $Revision$ $Date$
 */
public class AggregationLevelVocab implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _complexTypeAggregationLevelVocabSource.
     */
    private es.pode.parseadorXML.castor.ComplexTypeAggregationLevelVocabSource _complexTypeAggregationLevelVocabSource;

    /**
     * Field _complexTypeAggregationLevelVocabValue.
     */
    private es.pode.parseadorXML.castor.ComplexTypeAggregationLevelVocabValue _complexTypeAggregationLevelVocabValue;


      //----------------/
     //- Constructors -/
    //----------------/

    public AggregationLevelVocab() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field
     * 'complexTypeAggregationLevelVocabSource'.
     * 
     * @return the value of field
     * 'ComplexTypeAggregationLevelVocabSource'.
     */
    public es.pode.parseadorXML.castor.ComplexTypeAggregationLevelVocabSource getComplexTypeAggregationLevelVocabSource(
    ) {
        return this._complexTypeAggregationLevelVocabSource;
    }

    /**
     * Returns the value of field
     * 'complexTypeAggregationLevelVocabValue'.
     * 
     * @return the value of field
     * 'ComplexTypeAggregationLevelVocabValue'.
     */
    public es.pode.parseadorXML.castor.ComplexTypeAggregationLevelVocabValue getComplexTypeAggregationLevelVocabValue(
    ) {
        return this._complexTypeAggregationLevelVocabValue;
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
     * Sets the value of field
     * 'complexTypeAggregationLevelVocabSource'.
     * 
     * @param complexTypeAggregationLevelVocabSource the value of
     * field 'complexTypeAggregationLevelVocabSource'.
     */
    public void setComplexTypeAggregationLevelVocabSource(
            final es.pode.parseadorXML.castor.ComplexTypeAggregationLevelVocabSource complexTypeAggregationLevelVocabSource) {
        this._complexTypeAggregationLevelVocabSource = complexTypeAggregationLevelVocabSource;
    }

    /**
     * Sets the value of field
     * 'complexTypeAggregationLevelVocabValue'.
     * 
     * @param complexTypeAggregationLevelVocabValue the value of
     * field 'complexTypeAggregationLevelVocabValue'.
     */
    public void setComplexTypeAggregationLevelVocabValue(
            final es.pode.parseadorXML.castor.ComplexTypeAggregationLevelVocabValue complexTypeAggregationLevelVocabValue) {
        this._complexTypeAggregationLevelVocabValue = complexTypeAggregationLevelVocabValue;
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
     * es.pode.parseadorXML.castor.AggregationLevelVocab
     */
    public static es.pode.parseadorXML.castor.AggregationLevelVocab unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (es.pode.parseadorXML.castor.AggregationLevelVocab) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.AggregationLevelVocab.class, reader);
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
