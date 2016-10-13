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
 * Class InteractivityLevelVocab.
 * 
 * @version $Revision$ $Date$
 */
public class InteractivityLevelVocab implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _complexTypeInteractivityLevelVocabSource.
     */
    private es.pode.parseadorXML.castor.ComplexTypeInteractivityLevelVocabSource _complexTypeInteractivityLevelVocabSource;

    /**
     * Field _complexTypeInteractivityLevelVocabValue.
     */
    private es.pode.parseadorXML.castor.ComplexTypeInteractivityLevelVocabValue _complexTypeInteractivityLevelVocabValue;


      //----------------/
     //- Constructors -/
    //----------------/

    public InteractivityLevelVocab() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field
     * 'complexTypeInteractivityLevelVocabSource'.
     * 
     * @return the value of field
     * 'ComplexTypeInteractivityLevelVocabSource'.
     */
    public es.pode.parseadorXML.castor.ComplexTypeInteractivityLevelVocabSource getComplexTypeInteractivityLevelVocabSource(
    ) {
        return this._complexTypeInteractivityLevelVocabSource;
    }

    /**
     * Returns the value of field
     * 'complexTypeInteractivityLevelVocabValue'.
     * 
     * @return the value of field
     * 'ComplexTypeInteractivityLevelVocabValue'.
     */
    public es.pode.parseadorXML.castor.ComplexTypeInteractivityLevelVocabValue getComplexTypeInteractivityLevelVocabValue(
    ) {
        return this._complexTypeInteractivityLevelVocabValue;
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
     * 'complexTypeInteractivityLevelVocabSource'.
     * 
     * @param complexTypeInteractivityLevelVocabSource the value of
     * field 'complexTypeInteractivityLevelVocabSource'.
     */
    public void setComplexTypeInteractivityLevelVocabSource(
            final es.pode.parseadorXML.castor.ComplexTypeInteractivityLevelVocabSource complexTypeInteractivityLevelVocabSource) {
        this._complexTypeInteractivityLevelVocabSource = complexTypeInteractivityLevelVocabSource;
    }

    /**
     * Sets the value of field
     * 'complexTypeInteractivityLevelVocabValue'.
     * 
     * @param complexTypeInteractivityLevelVocabValue the value of
     * field 'complexTypeInteractivityLevelVocabValue'.
     */
    public void setComplexTypeInteractivityLevelVocabValue(
            final es.pode.parseadorXML.castor.ComplexTypeInteractivityLevelVocabValue complexTypeInteractivityLevelVocabValue) {
        this._complexTypeInteractivityLevelVocabValue = complexTypeInteractivityLevelVocabValue;
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
     * es.pode.parseadorXML.castor.InteractivityLevelVocab
     */
    public static es.pode.parseadorXML.castor.InteractivityLevelVocab unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (es.pode.parseadorXML.castor.InteractivityLevelVocab) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.InteractivityLevelVocab.class, reader);
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
