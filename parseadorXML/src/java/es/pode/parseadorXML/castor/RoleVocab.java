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
 * Class RoleVocab.
 * 
 * @version $Revision$ $Date$
 */
public class RoleVocab implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _complexTypeRoleVocabSource.
     */
    private es.pode.parseadorXML.castor.ComplexTypeRoleVocabSource _complexTypeRoleVocabSource;

    /**
     * Field _complexTypeRoleVocabValue.
     */
    private es.pode.parseadorXML.castor.ComplexTypeRoleVocabValue _complexTypeRoleVocabValue;


      //----------------/
     //- Constructors -/
    //----------------/

    public RoleVocab() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'complexTypeRoleVocabSource'.
     * 
     * @return the value of field 'ComplexTypeRoleVocabSource'.
     */
    public es.pode.parseadorXML.castor.ComplexTypeRoleVocabSource getComplexTypeRoleVocabSource(
    ) {
        return this._complexTypeRoleVocabSource;
    }

    /**
     * Returns the value of field 'complexTypeRoleVocabValue'.
     * 
     * @return the value of field 'ComplexTypeRoleVocabValue'.
     */
    public es.pode.parseadorXML.castor.ComplexTypeRoleVocabValue getComplexTypeRoleVocabValue(
    ) {
        return this._complexTypeRoleVocabValue;
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
     * Sets the value of field 'complexTypeRoleVocabSource'.
     * 
     * @param complexTypeRoleVocabSource the value of field
     * 'complexTypeRoleVocabSource'.
     */
    public void setComplexTypeRoleVocabSource(
            final es.pode.parseadorXML.castor.ComplexTypeRoleVocabSource complexTypeRoleVocabSource) {
        this._complexTypeRoleVocabSource = complexTypeRoleVocabSource;
    }

    /**
     * Sets the value of field 'complexTypeRoleVocabValue'.
     * 
     * @param complexTypeRoleVocabValue the value of field
     * 'complexTypeRoleVocabValue'.
     */
    public void setComplexTypeRoleVocabValue(
            final es.pode.parseadorXML.castor.ComplexTypeRoleVocabValue complexTypeRoleVocabValue) {
        this._complexTypeRoleVocabValue = complexTypeRoleVocabValue;
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
     * es.pode.parseadorXML.castor.RoleVocab
     */
    public static es.pode.parseadorXML.castor.RoleVocab unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (es.pode.parseadorXML.castor.RoleVocab) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.RoleVocab.class, reader);
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
