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
 * Class TypeVocab.
 * 
 * @version $Revision$ $Date$
 */
public class TypeVocab implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _complexTypeTypeVocabSource.
     */
    private es.pode.parseadorXML.castor.ComplexTypeTypeVocabSource _complexTypeTypeVocabSource;

    /**
     * Field _complexTypeTypeVocabValue.
     */
    private es.pode.parseadorXML.castor.ComplexTypeTypeVocabValue _complexTypeTypeVocabValue;


      //----------------/
     //- Constructors -/
    //----------------/

    public TypeVocab() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'complexTypeTypeVocabSource'.
     * 
     * @return the value of field 'ComplexTypeTypeVocabSource'.
     */
    public es.pode.parseadorXML.castor.ComplexTypeTypeVocabSource getComplexTypeTypeVocabSource(
    ) {
        return this._complexTypeTypeVocabSource;
    }

    /**
     * Returns the value of field 'complexTypeTypeVocabValue'.
     * 
     * @return the value of field 'ComplexTypeTypeVocabValue'.
     */
    public es.pode.parseadorXML.castor.ComplexTypeTypeVocabValue getComplexTypeTypeVocabValue(
    ) {
        return this._complexTypeTypeVocabValue;
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
     * Sets the value of field 'complexTypeTypeVocabSource'.
     * 
     * @param complexTypeTypeVocabSource the value of field
     * 'complexTypeTypeVocabSource'.
     */
    public void setComplexTypeTypeVocabSource(
            final es.pode.parseadorXML.castor.ComplexTypeTypeVocabSource complexTypeTypeVocabSource) {
        this._complexTypeTypeVocabSource = complexTypeTypeVocabSource;
    }

    /**
     * Sets the value of field 'complexTypeTypeVocabValue'.
     * 
     * @param complexTypeTypeVocabValue the value of field
     * 'complexTypeTypeVocabValue'.
     */
    public void setComplexTypeTypeVocabValue(
            final es.pode.parseadorXML.castor.ComplexTypeTypeVocabValue complexTypeTypeVocabValue) {
        this._complexTypeTypeVocabValue = complexTypeTypeVocabValue;
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
     * es.pode.parseadorXML.castor.TypeVocab
     */
    public static es.pode.parseadorXML.castor.TypeVocab unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (es.pode.parseadorXML.castor.TypeVocab) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.TypeVocab.class, reader);
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
