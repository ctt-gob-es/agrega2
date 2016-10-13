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
 * Class AccessTypeVocab.
 * 
 * @version $Revision$ $Date$
 */
public class AccessTypeVocab implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _complexTypeAccessTypeVocabSource.
     */
    private es.pode.parseadorXML.castor.ComplexTypeAccessTypeVocabSource _complexTypeAccessTypeVocabSource;

    /**
     * Field _complexTypeAccessTypeVocabValue.
     */
    private es.pode.parseadorXML.castor.ComplexTypeAccessTypeVocabValue _complexTypeAccessTypeVocabValue;


      //----------------/
     //- Constructors -/
    //----------------/

    public AccessTypeVocab() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field
     * 'complexTypeAccessTypeVocabSource'.
     * 
     * @return the value of field 'ComplexTypeAccessTypeVocabSource'
     */
    public es.pode.parseadorXML.castor.ComplexTypeAccessTypeVocabSource getComplexTypeAccessTypeVocabSource(
    ) {
        return this._complexTypeAccessTypeVocabSource;
    }

    /**
     * Returns the value of field
     * 'complexTypeAccessTypeVocabValue'.
     * 
     * @return the value of field 'ComplexTypeAccessTypeVocabValue'.
     */
    public es.pode.parseadorXML.castor.ComplexTypeAccessTypeVocabValue getComplexTypeAccessTypeVocabValue(
    ) {
        return this._complexTypeAccessTypeVocabValue;
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
     * Sets the value of field 'complexTypeAccessTypeVocabSource'.
     * 
     * @param complexTypeAccessTypeVocabSource the value of field
     * 'complexTypeAccessTypeVocabSource'.
     */
    public void setComplexTypeAccessTypeVocabSource(
            final es.pode.parseadorXML.castor.ComplexTypeAccessTypeVocabSource complexTypeAccessTypeVocabSource) {
        this._complexTypeAccessTypeVocabSource = complexTypeAccessTypeVocabSource;
    }

    /**
     * Sets the value of field 'complexTypeAccessTypeVocabValue'.
     * 
     * @param complexTypeAccessTypeVocabValue the value of field
     * 'complexTypeAccessTypeVocabValue'.
     */
    public void setComplexTypeAccessTypeVocabValue(
            final es.pode.parseadorXML.castor.ComplexTypeAccessTypeVocabValue complexTypeAccessTypeVocabValue) {
        this._complexTypeAccessTypeVocabValue = complexTypeAccessTypeVocabValue;
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
     * es.pode.parseadorXML.castor.AccessTypeVocab
     */
    public static es.pode.parseadorXML.castor.AccessTypeVocab unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (es.pode.parseadorXML.castor.AccessTypeVocab) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.AccessTypeVocab.class, reader);
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
