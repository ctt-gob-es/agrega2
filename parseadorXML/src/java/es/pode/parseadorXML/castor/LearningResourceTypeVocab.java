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
 * Class LearningResourceTypeVocab.
 * 
 * @version $Revision$ $Date$
 */
public class LearningResourceTypeVocab implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _complexTypeLearningResourceTypeVocabSource.
     */
    private es.pode.parseadorXML.castor.ComplexTypeLearningResourceTypeVocabSource _complexTypeLearningResourceTypeVocabSource;

    /**
     * Field _complexTypeLearningResourceTypeVocabValue.
     */
    private es.pode.parseadorXML.castor.ComplexTypeLearningResourceTypeVocabValue _complexTypeLearningResourceTypeVocabValue;


      //----------------/
     //- Constructors -/
    //----------------/

    public LearningResourceTypeVocab() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field
     * 'complexTypeLearningResourceTypeVocabSource'.
     * 
     * @return the value of field
     * 'ComplexTypeLearningResourceTypeVocabSource'.
     */
    public es.pode.parseadorXML.castor.ComplexTypeLearningResourceTypeVocabSource getComplexTypeLearningResourceTypeVocabSource(
    ) {
        return this._complexTypeLearningResourceTypeVocabSource;
    }

    /**
     * Returns the value of field
     * 'complexTypeLearningResourceTypeVocabValue'.
     * 
     * @return the value of field
     * 'ComplexTypeLearningResourceTypeVocabValue'.
     */
    public es.pode.parseadorXML.castor.ComplexTypeLearningResourceTypeVocabValue getComplexTypeLearningResourceTypeVocabValue(
    ) {
        return this._complexTypeLearningResourceTypeVocabValue;
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
     * 'complexTypeLearningResourceTypeVocabSource'.
     * 
     * @param complexTypeLearningResourceTypeVocabSource the value
     * of field 'complexTypeLearningResourceTypeVocabSource'.
     */
    public void setComplexTypeLearningResourceTypeVocabSource(
            final es.pode.parseadorXML.castor.ComplexTypeLearningResourceTypeVocabSource complexTypeLearningResourceTypeVocabSource) {
        this._complexTypeLearningResourceTypeVocabSource = complexTypeLearningResourceTypeVocabSource;
    }

    /**
     * Sets the value of field
     * 'complexTypeLearningResourceTypeVocabValue'.
     * 
     * @param complexTypeLearningResourceTypeVocabValue the value
     * of field 'complexTypeLearningResourceTypeVocabValue'.
     */
    public void setComplexTypeLearningResourceTypeVocabValue(
            final es.pode.parseadorXML.castor.ComplexTypeLearningResourceTypeVocabValue complexTypeLearningResourceTypeVocabValue) {
        this._complexTypeLearningResourceTypeVocabValue = complexTypeLearningResourceTypeVocabValue;
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
     * es.pode.parseadorXML.castor.LearningResourceTypeVocab
     */
    public static es.pode.parseadorXML.castor.LearningResourceTypeVocab unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (es.pode.parseadorXML.castor.LearningResourceTypeVocab) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.LearningResourceTypeVocab.class, reader);
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
