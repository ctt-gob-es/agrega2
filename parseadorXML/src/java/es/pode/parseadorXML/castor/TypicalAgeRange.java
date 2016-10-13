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
 * Class TypicalAgeRange.
 * 
 * @version $Revision$ $Date$
 */
public class TypicalAgeRange implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _groupTypicalAgeRangeTypicalAgeRange.
     */
    private es.pode.parseadorXML.castor.GroupTypicalAgeRangeTypicalAgeRange _groupTypicalAgeRangeTypicalAgeRange;


      //----------------/
     //- Constructors -/
    //----------------/

    public TypicalAgeRange() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field
     * 'groupTypicalAgeRangeTypicalAgeRange'.
     * 
     * @return the value of field
     * 'GroupTypicalAgeRangeTypicalAgeRange'.
     */
    public es.pode.parseadorXML.castor.GroupTypicalAgeRangeTypicalAgeRange getGroupTypicalAgeRangeTypicalAgeRange(
    ) {
        return this._groupTypicalAgeRangeTypicalAgeRange;
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
     * 'groupTypicalAgeRangeTypicalAgeRange'.
     * 
     * @param groupTypicalAgeRangeTypicalAgeRange the value of
     * field 'groupTypicalAgeRangeTypicalAgeRange'.
     */
    public void setGroupTypicalAgeRangeTypicalAgeRange(
            final es.pode.parseadorXML.castor.GroupTypicalAgeRangeTypicalAgeRange groupTypicalAgeRangeTypicalAgeRange) {
        this._groupTypicalAgeRangeTypicalAgeRange = groupTypicalAgeRangeTypicalAgeRange;
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
     * es.pode.parseadorXML.castor.TypicalAgeRange
     */
    public static es.pode.parseadorXML.castor.TypicalAgeRange unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (es.pode.parseadorXML.castor.TypicalAgeRange) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.TypicalAgeRange.class, reader);
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
