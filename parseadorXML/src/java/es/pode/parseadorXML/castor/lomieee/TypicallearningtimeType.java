/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.5</a>, using an XML
 * Schema.
 * $Id$
 */

package es.pode.parseadorXML.castor.lomieee;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class TypicallearningtimeType.
 * 
 * @version $Revision$ $Date$
 */
public class TypicallearningtimeType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _datetime
     */
    private java.lang.String _datetime;

    /**
     * Field _description
     */
    private es.pode.parseadorXML.castor.lomieee.Description _description;


      //----------------/
     //- Constructors -/
    //----------------/

    public TypicallearningtimeType() 
     {
        super();
    } //-- es.pode.parseadorXML.castor.lomieee.TypicallearningtimeType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'datetime'.
     * 
     * @return the value of field 'Datetime'.
     */
    public java.lang.String getDatetime()
    {
        return this._datetime;
    } //-- java.lang.String getDatetime() 

    /**
     * Returns the value of field 'description'.
     * 
     * @return the value of field 'Description'.
     */
    public es.pode.parseadorXML.castor.lomieee.Description getDescription()
    {
        return this._description;
    } //-- es.pode.parseadorXML.castor.lomieee.Description getDescription() 

    /**
     * Method isValid
     * 
     * 
     * 
     * @return true if this object is valid according to the schema
     */
    public boolean isValid()
    {
        try {
            validate();
        }
        catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    } //-- boolean isValid() 

    /**
     * 
     * 
     * @param out
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void marshal(java.io.Writer out)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, out);
    } //-- void marshal(java.io.Writer) 

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
    public void marshal(org.xml.sax.ContentHandler handler)
        throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, handler);
    } //-- void marshal(org.xml.sax.ContentHandler) 

    /**
     * Sets the value of field 'datetime'.
     * 
     * @param datetime the value of field 'datetime'.
     */
    public void setDatetime(java.lang.String datetime)
    {
        this._datetime = datetime;
    } //-- void setDatetime(java.lang.String) 

    /**
     * Sets the value of field 'description'.
     * 
     * @param description the value of field 'description'.
     */
    public void setDescription(es.pode.parseadorXML.castor.lomieee.Description description)
    {
        this._description = description;
    } //-- void setDescription(es.pode.parseadorXML.castor.lomieee.Description) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * es.pode.parseadorXML.castor.lomieee.TypicallearningtimeType
     */
    public static es.pode.parseadorXML.castor.lomieee.TypicallearningtimeType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (es.pode.parseadorXML.castor.lomieee.TypicallearningtimeType) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.lomieee.TypicallearningtimeType.class, reader);
    } //-- es.pode.parseadorXML.castor.lomieee.TypicallearningtimeType unmarshal(java.io.Reader) 

    /**
     * 
     * 
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
