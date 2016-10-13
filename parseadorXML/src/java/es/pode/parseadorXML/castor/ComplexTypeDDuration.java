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
 * Class ComplexTypeDDuration.
 * 
 * @version $Revision$ $Date$
 */
public class ComplexTypeDDuration implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _complexTypeDurationDuration.
     */
    private es.pode.parseadorXML.castor.ComplexTypeDurationDuration _complexTypeDurationDuration;

    /**
     * Field _complexTypeDurationDescription.
     */
    private es.pode.parseadorXML.castor.ComplexTypeDurationDescription _complexTypeDurationDescription;


      //----------------/
     //- Constructors -/
    //----------------/

    public ComplexTypeDDuration() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'complexTypeDurationDescription'.
     * 
     * @return the value of field 'ComplexTypeDurationDescription'.
     */
    public es.pode.parseadorXML.castor.ComplexTypeDurationDescription getComplexTypeDurationDescription(
    ) {
        return this._complexTypeDurationDescription;
    }

    /**
     * Returns the value of field 'complexTypeDurationDuration'.
     * 
     * @return the value of field 'ComplexTypeDurationDuration'.
     */
    public es.pode.parseadorXML.castor.ComplexTypeDurationDuration getComplexTypeDurationDuration(
    ) {
        return this._complexTypeDurationDuration;
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
     * Sets the value of field 'complexTypeDurationDescription'.
     * 
     * @param complexTypeDurationDescription the value of field
     * 'complexTypeDurationDescription'.
     */
    public void setComplexTypeDurationDescription(
            final es.pode.parseadorXML.castor.ComplexTypeDurationDescription complexTypeDurationDescription) {
        this._complexTypeDurationDescription = complexTypeDurationDescription;
    }

    /**
     * Sets the value of field 'complexTypeDurationDuration'.
     * 
     * @param complexTypeDurationDuration the value of field
     * 'complexTypeDurationDuration'.
     */
    public void setComplexTypeDurationDuration(
            final es.pode.parseadorXML.castor.ComplexTypeDurationDuration complexTypeDurationDuration) {
        this._complexTypeDurationDuration = complexTypeDurationDuration;
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
     * es.pode.parseadorXML.castor.ComplexTypeDDuration
     */
    public static es.pode.parseadorXML.castor.ComplexTypeDDuration unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (es.pode.parseadorXML.castor.ComplexTypeDDuration) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.ComplexTypeDDuration.class, reader);
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
