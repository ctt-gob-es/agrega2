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
 * Class KindVocab.
 * 
 * @version $Revision$ $Date$
 */
public class KindVocab implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _complexTypeKindVocabSource.
     */
    private es.pode.parseadorXML.castor.ComplexTypeKindVocabSource _complexTypeKindVocabSource;

    /**
     * Field _complexTypeKindVocabValue.
     */
    private es.pode.parseadorXML.castor.ComplexTypeKindVocabValue _complexTypeKindVocabValue;


      //----------------/
     //- Constructors -/
    //----------------/

    public KindVocab() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'complexTypeKindVocabSource'.
     * 
     * @return the value of field 'ComplexTypeKindVocabSource'.
     */
    public es.pode.parseadorXML.castor.ComplexTypeKindVocabSource getComplexTypeKindVocabSource(
    ) {
        return this._complexTypeKindVocabSource;
    }

    /**
     * Returns the value of field 'complexTypeKindVocabValue'.
     * 
     * @return the value of field 'ComplexTypeKindVocabValue'.
     */
    public es.pode.parseadorXML.castor.ComplexTypeKindVocabValue getComplexTypeKindVocabValue(
    ) {
        return this._complexTypeKindVocabValue;
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
     * Sets the value of field 'complexTypeKindVocabSource'.
     * 
     * @param complexTypeKindVocabSource the value of field
     * 'complexTypeKindVocabSource'.
     */
    public void setComplexTypeKindVocabSource(
            final es.pode.parseadorXML.castor.ComplexTypeKindVocabSource complexTypeKindVocabSource) {
        this._complexTypeKindVocabSource = complexTypeKindVocabSource;
    }

    /**
     * Sets the value of field 'complexTypeKindVocabValue'.
     * 
     * @param complexTypeKindVocabValue the value of field
     * 'complexTypeKindVocabValue'.
     */
    public void setComplexTypeKindVocabValue(
            final es.pode.parseadorXML.castor.ComplexTypeKindVocabValue complexTypeKindVocabValue) {
        this._complexTypeKindVocabValue = complexTypeKindVocabValue;
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
     * es.pode.parseadorXML.castor.KindVocab
     */
    public static es.pode.parseadorXML.castor.KindVocab unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (es.pode.parseadorXML.castor.KindVocab) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.KindVocab.class, reader);
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
