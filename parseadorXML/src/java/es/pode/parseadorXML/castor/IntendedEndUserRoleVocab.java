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
 * Class IntendedEndUserRoleVocab.
 * 
 * @version $Revision$ $Date$
 */
public class IntendedEndUserRoleVocab implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _complexTypeIntendedEndUserRoleVocabSource.
     */
    private es.pode.parseadorXML.castor.ComplexTypeIntendedEndUserRoleVocabSource _complexTypeIntendedEndUserRoleVocabSource;

    /**
     * Field _complexTypeIntendedEndUserRoleVocabValue.
     */
    private es.pode.parseadorXML.castor.ComplexTypeIntendedEndUserRoleVocabValue _complexTypeIntendedEndUserRoleVocabValue;


      //----------------/
     //- Constructors -/
    //----------------/

    public IntendedEndUserRoleVocab() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field
     * 'complexTypeIntendedEndUserRoleVocabSource'.
     * 
     * @return the value of field
     * 'ComplexTypeIntendedEndUserRoleVocabSource'.
     */
    public es.pode.parseadorXML.castor.ComplexTypeIntendedEndUserRoleVocabSource getComplexTypeIntendedEndUserRoleVocabSource(
    ) {
        return this._complexTypeIntendedEndUserRoleVocabSource;
    }

    /**
     * Returns the value of field
     * 'complexTypeIntendedEndUserRoleVocabValue'.
     * 
     * @return the value of field
     * 'ComplexTypeIntendedEndUserRoleVocabValue'.
     */
    public es.pode.parseadorXML.castor.ComplexTypeIntendedEndUserRoleVocabValue getComplexTypeIntendedEndUserRoleVocabValue(
    ) {
        return this._complexTypeIntendedEndUserRoleVocabValue;
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
     * 'complexTypeIntendedEndUserRoleVocabSource'.
     * 
     * @param complexTypeIntendedEndUserRoleVocabSource the value
     * of field 'complexTypeIntendedEndUserRoleVocabSource'.
     */
    public void setComplexTypeIntendedEndUserRoleVocabSource(
            final es.pode.parseadorXML.castor.ComplexTypeIntendedEndUserRoleVocabSource complexTypeIntendedEndUserRoleVocabSource) {
        this._complexTypeIntendedEndUserRoleVocabSource = complexTypeIntendedEndUserRoleVocabSource;
    }

    /**
     * Sets the value of field
     * 'complexTypeIntendedEndUserRoleVocabValue'.
     * 
     * @param complexTypeIntendedEndUserRoleVocabValue the value of
     * field 'complexTypeIntendedEndUserRoleVocabValue'.
     */
    public void setComplexTypeIntendedEndUserRoleVocabValue(
            final es.pode.parseadorXML.castor.ComplexTypeIntendedEndUserRoleVocabValue complexTypeIntendedEndUserRoleVocabValue) {
        this._complexTypeIntendedEndUserRoleVocabValue = complexTypeIntendedEndUserRoleVocabValue;
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
     * es.pode.parseadorXML.castor.IntendedEndUserRoleVocab
     */
    public static es.pode.parseadorXML.castor.IntendedEndUserRoleVocab unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (es.pode.parseadorXML.castor.IntendedEndUserRoleVocab) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.IntendedEndUserRoleVocab.class, reader);
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
