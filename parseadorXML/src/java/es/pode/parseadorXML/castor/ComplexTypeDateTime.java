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
 * Class ComplexTypeDateTime.
 * 
 * @version $Revision$ $Date$
 */
public class ComplexTypeDateTime implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _complexTypeDateTimeDateTime.
     */
    private es.pode.parseadorXML.castor.ComplexTypeDateTimeDateTime _complexTypeDateTimeDateTime;

    /**
     * Field _complexTypeDateTimeDescription.
     */
    private es.pode.parseadorXML.castor.ComplexTypeDateTimeDescription _complexTypeDateTimeDescription;


      //----------------/
     //- Constructors -/
    //----------------/

    public ComplexTypeDateTime() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'complexTypeDateTimeDateTime'.
     * 
     * @return the value of field 'ComplexTypeDateTimeDateTime'.
     */
    public es.pode.parseadorXML.castor.ComplexTypeDateTimeDateTime getComplexTypeDateTimeDateTime(
    ) {
        return this._complexTypeDateTimeDateTime;
    }

    /**
     * Returns the value of field 'complexTypeDateTimeDescription'.
     * 
     * @return the value of field 'ComplexTypeDateTimeDescription'.
     */
    public es.pode.parseadorXML.castor.ComplexTypeDateTimeDescription getComplexTypeDateTimeDescription(
    ) {
        return this._complexTypeDateTimeDescription;
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
     * Sets the value of field 'complexTypeDateTimeDateTime'.
     * 
     * @param complexTypeDateTimeDateTime the value of field
     * 'complexTypeDateTimeDateTime'.
     */
    public void setComplexTypeDateTimeDateTime(
            final es.pode.parseadorXML.castor.ComplexTypeDateTimeDateTime complexTypeDateTimeDateTime) {
        this._complexTypeDateTimeDateTime = complexTypeDateTimeDateTime;
    }

    /**
     * Sets the value of field 'complexTypeDateTimeDescription'.
     * 
     * @param complexTypeDateTimeDescription the value of field
     * 'complexTypeDateTimeDescription'.
     */
    public void setComplexTypeDateTimeDescription(
            final es.pode.parseadorXML.castor.ComplexTypeDateTimeDescription complexTypeDateTimeDescription) {
        this._complexTypeDateTimeDescription = complexTypeDateTimeDescription;
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
     * es.pode.parseadorXML.castor.ComplexTypeDateTime
     */
    public static es.pode.parseadorXML.castor.ComplexTypeDateTime unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (es.pode.parseadorXML.castor.ComplexTypeDateTime) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.ComplexTypeDateTime.class, reader);
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
