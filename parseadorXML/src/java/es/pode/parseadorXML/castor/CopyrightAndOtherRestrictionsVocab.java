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
 * Class CopyrightAndOtherRestrictionsVocab.
 * 
 * @version $Revision$ $Date$
 */
public class CopyrightAndOtherRestrictionsVocab implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _complexTypeCopyrightAndOtherRestrictionsVocabSource.
     */
    private es.pode.parseadorXML.castor.ComplexTypeCopyrightAndOtherRestrictionsVocabSource _complexTypeCopyrightAndOtherRestrictionsVocabSource;

    /**
     * Field _complexTypeCopyrightAndOtherRestrictionsVocabValue.
     */
    private es.pode.parseadorXML.castor.ComplexTypeCopyrightAndOtherRestrictionsVocabValue _complexTypeCopyrightAndOtherRestrictionsVocabValue;


      //----------------/
     //- Constructors -/
    //----------------/

    public CopyrightAndOtherRestrictionsVocab() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field
     * 'complexTypeCopyrightAndOtherRestrictionsVocabSource'.
     * 
     * @return the value of field
     * 'ComplexTypeCopyrightAndOtherRestrictionsVocabSource'.
     */
    public es.pode.parseadorXML.castor.ComplexTypeCopyrightAndOtherRestrictionsVocabSource getComplexTypeCopyrightAndOtherRestrictionsVocabSource(
    ) {
        return this._complexTypeCopyrightAndOtherRestrictionsVocabSource;
    }

    /**
     * Returns the value of field
     * 'complexTypeCopyrightAndOtherRestrictionsVocabValue'.
     * 
     * @return the value of field
     * 'ComplexTypeCopyrightAndOtherRestrictionsVocabValue'.
     */
    public es.pode.parseadorXML.castor.ComplexTypeCopyrightAndOtherRestrictionsVocabValue getComplexTypeCopyrightAndOtherRestrictionsVocabValue(
    ) {
        return this._complexTypeCopyrightAndOtherRestrictionsVocabValue;
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
     * 'complexTypeCopyrightAndOtherRestrictionsVocabSource'.
     * 
     * @param complexTypeCopyrightAndOtherRestrictionsVocabSource
     * the value of field
     * 'complexTypeCopyrightAndOtherRestrictionsVocabSource'.
     */
    public void setComplexTypeCopyrightAndOtherRestrictionsVocabSource(
            final es.pode.parseadorXML.castor.ComplexTypeCopyrightAndOtherRestrictionsVocabSource complexTypeCopyrightAndOtherRestrictionsVocabSource) {
        this._complexTypeCopyrightAndOtherRestrictionsVocabSource = complexTypeCopyrightAndOtherRestrictionsVocabSource;
    }

    /**
     * Sets the value of field
     * 'complexTypeCopyrightAndOtherRestrictionsVocabValue'.
     * 
     * @param complexTypeCopyrightAndOtherRestrictionsVocabValue
     * the value of field
     * 'complexTypeCopyrightAndOtherRestrictionsVocabValue'.
     */
    public void setComplexTypeCopyrightAndOtherRestrictionsVocabValue(
            final es.pode.parseadorXML.castor.ComplexTypeCopyrightAndOtherRestrictionsVocabValue complexTypeCopyrightAndOtherRestrictionsVocabValue) {
        this._complexTypeCopyrightAndOtherRestrictionsVocabValue = complexTypeCopyrightAndOtherRestrictionsVocabValue;
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
     * es.pode.parseadorXML.castor.CopyrightAndOtherRestrictionsVocab
     */
    public static es.pode.parseadorXML.castor.CopyrightAndOtherRestrictionsVocab unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (es.pode.parseadorXML.castor.CopyrightAndOtherRestrictionsVocab) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.CopyrightAndOtherRestrictionsVocab.class, reader);
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
