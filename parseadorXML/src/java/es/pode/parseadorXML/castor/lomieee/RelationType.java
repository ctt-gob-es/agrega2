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
 * Class RelationType.
 * 
 * @version $Revision$ $Date$
 */
public class RelationType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * internal content storage
     */
    private java.lang.String _content = "";

    /**
     * Field _kind
     */
    private es.pode.parseadorXML.castor.lomieee.Kind _kind;

    /**
     * Field _resource
     */
    private es.pode.parseadorXML.castor.lomieee.Resource _resource;

    /**
     * Field _grp_any
     */
    private es.pode.parseadorXML.castor.lomieee.Grp_any _grp_any;


      //----------------/
     //- Constructors -/
    //----------------/

    public RelationType() 
     {
        super();
        setContent("");
    } //-- es.pode.parseadorXML.castor.lomieee.RelationType()


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
     * Returns the value of field 'kind'.
     * 
     * @return the value of field 'Kind'.
     */
    public es.pode.parseadorXML.castor.lomieee.Kind getKind()
    {
        return this._kind;
    } //-- es.pode.parseadorXML.castor.lomieee.Kind getKind() 

    /**
     * Returns the value of field 'resource'.
     * 
     * @return the value of field 'Resource'.
     */
    public es.pode.parseadorXML.castor.lomieee.Resource getResource()
    {
        return this._resource;
    } //-- es.pode.parseadorXML.castor.lomieee.Resource getResource() 

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
     * Sets the value of field 'kind'.
     * 
     * @param kind the value of field 'kind'.
     */
    public void setKind(es.pode.parseadorXML.castor.lomieee.Kind kind)
    {
        this._kind = kind;
    } //-- void setKind(es.pode.parseadorXML.castor.lomieee.Kind) 

    /**
     * Sets the value of field 'resource'.
     * 
     * @param resource the value of field 'resource'.
     */
    public void setResource(es.pode.parseadorXML.castor.lomieee.Resource resource)
    {
        this._resource = resource;
    } //-- void setResource(es.pode.parseadorXML.castor.lomieee.Resource) 

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
     * es.pode.parseadorXML.castor.lomieee.RelationType
     */
    public static es.pode.parseadorXML.castor.lomieee.RelationType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (es.pode.parseadorXML.castor.lomieee.RelationType) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.lomieee.RelationType.class, reader);
    } //-- es.pode.parseadorXML.castor.lomieee.RelationType unmarshal(java.io.Reader) 

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
