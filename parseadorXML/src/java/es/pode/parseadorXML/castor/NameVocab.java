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
 * Class NameVocab.
 * 
 * @version $Revision$ $Date$
 */
public class NameVocab implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _complexTypeNameVocabSource.
     */
    private es.pode.parseadorXML.castor.ComplexTypeNameVocabSource _complexTypeNameVocabSource;

    /**
     * Field _complexTypeNameVocabValue.
     */
    private es.pode.parseadorXML.castor.ComplexTypeNameVocabValue _complexTypeNameVocabValue;


      //----------------/
     //- Constructors -/
    //----------------/

    public NameVocab() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'complexTypeNameVocabSource'.
     * 
     * @return the value of field 'ComplexTypeNameVocabSource'.
     */
    public es.pode.parseadorXML.castor.ComplexTypeNameVocabSource getComplexTypeNameVocabSource(
    ) {
        return this._complexTypeNameVocabSource;
    }

    /**
     * Returns the value of field 'complexTypeNameVocabValue'.
     * 
     * @return the value of field 'ComplexTypeNameVocabValue'.
     */
    public es.pode.parseadorXML.castor.ComplexTypeNameVocabValue getComplexTypeNameVocabValue(
    ) {
        return this._complexTypeNameVocabValue;
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
     * Sets the value of field 'complexTypeNameVocabSource'.
     * 
     * @param complexTypeNameVocabSource the value of field
     * 'complexTypeNameVocabSource'.
     */
    public void setComplexTypeNameVocabSource(
            final es.pode.parseadorXML.castor.ComplexTypeNameVocabSource complexTypeNameVocabSource) {
        this._complexTypeNameVocabSource = complexTypeNameVocabSource;
    }

    /**
     * Sets the value of field 'complexTypeNameVocabValue'.
     * 
     * @param complexTypeNameVocabValue the value of field
     * 'complexTypeNameVocabValue'.
     */
    public void setComplexTypeNameVocabValue(
            final es.pode.parseadorXML.castor.ComplexTypeNameVocabValue complexTypeNameVocabValue) {
        this._complexTypeNameVocabValue = complexTypeNameVocabValue;
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
     * es.pode.parseadorXML.castor.NameVocab
     */
    public static es.pode.parseadorXML.castor.NameVocab unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (es.pode.parseadorXML.castor.NameVocab) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.NameVocab.class, reader);
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
