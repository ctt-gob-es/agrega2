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
 * Class DifficultyVocab.
 * 
 * @version $Revision$ $Date$
 */
public class DifficultyVocab implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _complexTypeDifficultyVocabSource.
     */
    private es.pode.parseadorXML.castor.ComplexTypeDifficultyVocabSource _complexTypeDifficultyVocabSource;

    /**
     * Field _complexTypeDifficultyVocabValue.
     */
    private es.pode.parseadorXML.castor.ComplexTypeDifficultyVocabValue _complexTypeDifficultyVocabValue;


      //----------------/
     //- Constructors -/
    //----------------/

    public DifficultyVocab() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field
     * 'complexTypeDifficultyVocabSource'.
     * 
     * @return the value of field 'ComplexTypeDifficultyVocabSource'
     */
    public es.pode.parseadorXML.castor.ComplexTypeDifficultyVocabSource getComplexTypeDifficultyVocabSource(
    ) {
        return this._complexTypeDifficultyVocabSource;
    }

    /**
     * Returns the value of field
     * 'complexTypeDifficultyVocabValue'.
     * 
     * @return the value of field 'ComplexTypeDifficultyVocabValue'.
     */
    public es.pode.parseadorXML.castor.ComplexTypeDifficultyVocabValue getComplexTypeDifficultyVocabValue(
    ) {
        return this._complexTypeDifficultyVocabValue;
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
     * Sets the value of field 'complexTypeDifficultyVocabSource'.
     * 
     * @param complexTypeDifficultyVocabSource the value of field
     * 'complexTypeDifficultyVocabSource'.
     */
    public void setComplexTypeDifficultyVocabSource(
            final es.pode.parseadorXML.castor.ComplexTypeDifficultyVocabSource complexTypeDifficultyVocabSource) {
        this._complexTypeDifficultyVocabSource = complexTypeDifficultyVocabSource;
    }

    /**
     * Sets the value of field 'complexTypeDifficultyVocabValue'.
     * 
     * @param complexTypeDifficultyVocabValue the value of field
     * 'complexTypeDifficultyVocabValue'.
     */
    public void setComplexTypeDifficultyVocabValue(
            final es.pode.parseadorXML.castor.ComplexTypeDifficultyVocabValue complexTypeDifficultyVocabValue) {
        this._complexTypeDifficultyVocabValue = complexTypeDifficultyVocabValue;
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
     * es.pode.parseadorXML.castor.DifficultyVocab
     */
    public static es.pode.parseadorXML.castor.DifficultyVocab unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (es.pode.parseadorXML.castor.DifficultyVocab) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.DifficultyVocab.class, reader);
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
