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
 * Class RequirementType.
 * 
 * @version $Revision$ $Date$
 */
public class RequirementType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * internal content storage
     */
    private java.lang.String _content = "";

    /**
     * Field _type
     */
    private es.pode.parseadorXML.castor.lomieee.Type _type;

    /**
     * Field _name
     */
    private es.pode.parseadorXML.castor.lomieee.Name _name;

    /**
     * Field _minimumversion
     */
    private java.lang.String _minimumversion;

    /**
     * Field _maximumversion
     */
    private java.lang.String _maximumversion;

    /**
     * Field _grp_any
     */
    private es.pode.parseadorXML.castor.lomieee.Grp_any _grp_any;


      //----------------/
     //- Constructors -/
    //----------------/

    public RequirementType() 
     {
        super();
        setContent("");
    } //-- es.pode.parseadorXML.castor.lomieee.RequirementType()


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
     * Returns the value of field 'grp_any'.
     * 
     * @return the value of field 'Grp_any'.
     */
    public es.pode.parseadorXML.castor.lomieee.Grp_any getGrp_any()
    {
        return this._grp_any;
    } //-- es.pode.parseadorXML.castor.lomieee.Grp_any getGrp_any() 

    /**
     * Returns the value of field 'maximumversion'.
     * 
     * @return the value of field 'Maximumversion'.
     */
    public java.lang.String getMaximumversion()
    {
        return this._maximumversion;
    } //-- java.lang.String getMaximumversion() 

    /**
     * Returns the value of field 'minimumversion'.
     * 
     * @return the value of field 'Minimumversion'.
     */
    public java.lang.String getMinimumversion()
    {
        return this._minimumversion;
    } //-- java.lang.String getMinimumversion() 

    /**
     * Returns the value of field 'name'.
     * 
     * @return the value of field 'Name'.
     */
    public es.pode.parseadorXML.castor.lomieee.Name getName()
    {
        return this._name;
    } //-- es.pode.parseadorXML.castor.lomieee.Name getName() 

    /**
     * Returns the value of field 'type'.
     * 
     * @return the value of field 'Type'.
     */
    public es.pode.parseadorXML.castor.lomieee.Type getType()
    {
        return this._type;
    } //-- es.pode.parseadorXML.castor.lomieee.Type getType() 

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
     * Sets the value of field 'grp_any'.
     * 
     * @param grp_any the value of field 'grp_any'.
     */
    public void setGrp_any(es.pode.parseadorXML.castor.lomieee.Grp_any grp_any)
    {
        this._grp_any = grp_any;
    } //-- void setGrp_any(es.pode.parseadorXML.castor.lomieee.Grp_any) 

    /**
     * Sets the value of field 'maximumversion'.
     * 
     * @param maximumversion the value of field 'maximumversion'.
     */
    public void setMaximumversion(java.lang.String maximumversion)
    {
        this._maximumversion = maximumversion;
    } //-- void setMaximumversion(java.lang.String) 

    /**
     * Sets the value of field 'minimumversion'.
     * 
     * @param minimumversion the value of field 'minimumversion'.
     */
    public void setMinimumversion(java.lang.String minimumversion)
    {
        this._minimumversion = minimumversion;
    } //-- void setMinimumversion(java.lang.String) 

    /**
     * Sets the value of field 'name'.
     * 
     * @param name the value of field 'name'.
     */
    public void setName(es.pode.parseadorXML.castor.lomieee.Name name)
    {
        this._name = name;
    } //-- void setName(es.pode.parseadorXML.castor.lomieee.Name) 

    /**
     * Sets the value of field 'type'.
     * 
     * @param type the value of field 'type'.
     */
    public void setType(es.pode.parseadorXML.castor.lomieee.Type type)
    {
        this._type = type;
    } //-- void setType(es.pode.parseadorXML.castor.lomieee.Type) 

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
     * es.pode.parseadorXML.castor.lomieee.RequirementType
     */
    public static es.pode.parseadorXML.castor.lomieee.RequirementType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (es.pode.parseadorXML.castor.lomieee.RequirementType) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.lomieee.RequirementType.class, reader);
    } //-- es.pode.parseadorXML.castor.lomieee.RequirementType unmarshal(java.io.Reader) 

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
