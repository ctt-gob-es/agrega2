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
 * Class InteractivityType.
 * 
 * @version $Revision$ $Date$
 */
public class InteractivityType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _groupInteractivityTypeInteractivityType.
     */
    private es.pode.parseadorXML.castor.GroupInteractivityTypeInteractivityType _groupInteractivityTypeInteractivityType;


      //----------------/
     //- Constructors -/
    //----------------/

    public InteractivityType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field
     * 'groupInteractivityTypeInteractivityType'.
     * 
     * @return the value of field
     * 'GroupInteractivityTypeInteractivityType'.
     */
    public es.pode.parseadorXML.castor.GroupInteractivityTypeInteractivityType getGroupInteractivityTypeInteractivityType(
    ) {
        return this._groupInteractivityTypeInteractivityType;
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
     * 'groupInteractivityTypeInteractivityType'.
     * 
     * @param groupInteractivityTypeInteractivityType the value of
     * field 'groupInteractivityTypeInteractivityType'.
     */
    public void setGroupInteractivityTypeInteractivityType(
            final es.pode.parseadorXML.castor.GroupInteractivityTypeInteractivityType groupInteractivityTypeInteractivityType) {
        this._groupInteractivityTypeInteractivityType = groupInteractivityTypeInteractivityType;
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
     * es.pode.parseadorXML.castor.InteractivityType
     */
    public static es.pode.parseadorXML.castor.InteractivityType unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (es.pode.parseadorXML.castor.InteractivityType) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.InteractivityType.class, reader);
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
