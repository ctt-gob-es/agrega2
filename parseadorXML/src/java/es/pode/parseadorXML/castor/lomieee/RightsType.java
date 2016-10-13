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
 * Class RightsType.
 * 
 * @version $Revision$ $Date$
 */
public class RightsType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * internal content storage
     */
    private java.lang.String _content = "";

    /**
     * Field _cost
     */
    private es.pode.parseadorXML.castor.lomieee.Cost _cost;

    /**
     * Field _copyrightandotherrestrictions
     */
    private es.pode.parseadorXML.castor.lomieee.Copyrightandotherrestrictions _copyrightandotherrestrictions;

    /**
     * Field _description
     */
    private es.pode.parseadorXML.castor.lomieee.Description _description;

    /**
     * Field _grp_any
     */
    private es.pode.parseadorXML.castor.lomieee.Grp_any _grp_any;


      //----------------/
     //- Constructors -/
    //----------------/

    public RightsType() 
     {
        super();
        setContent("");
    } //-- es.pode.parseadorXML.castor.lomieee.RightsType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'content'. The field 'content'
     * has the following description: internal content storage
     * 
     * @return the value of field 'Content'.
     */
    public java.lang.String getContent()
    {
        return this._content;
    } //-- java.lang.String getContent() 

    /**
     * Returns the value of field 'copyrightandotherrestrictions'.
     * 
     * @return the value of field 'Copyrightandotherrestrictions'.
     */
    public es.pode.parseadorXML.castor.lomieee.Copyrightandotherrestrictions getCopyrightandotherrestrictions()
    {
        return this._copyrightandotherrestrictions;
    } //-- es.pode.parseadorXML.castor.lomieee.Copyrightandotherrestrictions getCopyrightandotherrestrictions() 

    /**
     * Returns the value of field 'cost'.
     * 
     * @return the value of field 'Cost'.
     */
    public es.pode.parseadorXML.castor.lomieee.Cost getCost()
    {
        return this._cost;
    } //-- es.pode.parseadorXML.castor.lomieee.Cost getCost() 

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
     * Returns the value of field 'grp_any'.
     * 
     * @return the value of field 'Grp_any'.
     */
    public es.pode.parseadorXML.castor.lomieee.Grp_any getGrp_any()
    {
        return this._grp_any;
    } //-- es.pode.parseadorXML.castor.lomieee.Grp_any getGrp_any() 

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
     * Sets the value of field 'content'. The field 'content' has
     * the following description: internal content storage
     * 
     * @param content the value of field 'content'.
     */
    public void setContent(java.lang.String content)
    {
        this._content = content;
    } //-- void setContent(java.lang.String) 

    /**
     * Sets the value of field 'copyrightandotherrestrictions'.
     * 
     * @param copyrightandotherrestrictions the value of field
     * 'copyrightandotherrestrictions'.
     */
    public void setCopyrightandotherrestrictions(es.pode.parseadorXML.castor.lomieee.Copyrightandotherrestrictions copyrightandotherrestrictions)
    {
        this._copyrightandotherrestrictions = copyrightandotherrestrictions;
    } //-- void setCopyrightandotherrestrictions(es.pode.parseadorXML.castor.lomieee.Copyrightandotherrestrictions) 

    /**
     * Sets the value of field 'cost'.
     * 
     * @param cost the value of field 'cost'.
     */
    public void setCost(es.pode.parseadorXML.castor.lomieee.Cost cost)
    {
        this._cost = cost;
    } //-- void setCost(es.pode.parseadorXML.castor.lomieee.Cost) 

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
     * Sets the value of field 'grp_any'.
     * 
     * @param grp_any the value of field 'grp_any'.
     */
    public void setGrp_any(es.pode.parseadorXML.castor.lomieee.Grp_any grp_any)
    {
        this._grp_any = grp_any;
    } //-- void setGrp_any(es.pode.parseadorXML.castor.lomieee.Grp_any) 

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
     * es.pode.parseadorXML.castor.lomieee.RightsType
     */
    public static es.pode.parseadorXML.castor.lomieee.RightsType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (es.pode.parseadorXML.castor.lomieee.RightsType) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.lomieee.RightsType.class, reader);
    } //-- es.pode.parseadorXML.castor.lomieee.RightsType unmarshal(java.io.Reader) 

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
