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
 * Class StatusVocab.
 * 
 * @version $Revision$ $Date$
 */
public class StatusVocab implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _complexTypeStatusVocabSource.
     */
    private es.pode.parseadorXML.castor.ComplexTypeStatusVocabSource _complexTypeStatusVocabSource;

    /**
     * Field _complexTypeStatusVocabValue.
     */
    private es.pode.parseadorXML.castor.ComplexTypeStatusVocabValue _complexTypeStatusVocabValue;


      //----------------/
     //- Constructors -/
    //----------------/

    public StatusVocab() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'complexTypeStatusVocabSource'.
     * 
     * @return the value of field 'ComplexTypeStatusVocabSource'.
     */
    public es.pode.parseadorXML.castor.ComplexTypeStatusVocabSource getComplexTypeStatusVocabSource(
    ) {
        return this._complexTypeStatusVocabSource;
    }

    /**
     * Returns the value of field 'complexTypeStatusVocabValue'.
     * 
     * @return the value of field 'ComplexTypeStatusVocabValue'.
     */
    public es.pode.parseadorXML.castor.ComplexTypeStatusVocabValue getComplexTypeStatusVocabValue(
    ) {
        return this._complexTypeStatusVocabValue;
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
     * Sets the value of field 'complexTypeStatusVocabSource'.
     * 
     * @param complexTypeStatusVocabSource the value of field
     * 'complexTypeStatusVocabSource'.
     */
    public void setComplexTypeStatusVocabSource(
            final es.pode.parseadorXML.castor.ComplexTypeStatusVocabSource complexTypeStatusVocabSource) {
        this._complexTypeStatusVocabSource = complexTypeStatusVocabSource;
    }

    /**
     * Sets the value of field 'complexTypeStatusVocabValue'.
     * 
     * @param complexTypeStatusVocabValue the value of field
     * 'complexTypeStatusVocabValue'.
     */
    public void setComplexTypeStatusVocabValue(
            final es.pode.parseadorXML.castor.ComplexTypeStatusVocabValue complexTypeStatusVocabValue) {
        this._complexTypeStatusVocabValue = complexTypeStatusVocabValue;
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
     * es.pode.parseadorXML.castor.StatusVocab
     */
    public static es.pode.parseadorXML.castor.StatusVocab unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (es.pode.parseadorXML.castor.StatusVocab) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.StatusVocab.class, reader);
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
