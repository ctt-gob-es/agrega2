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
 * Class CatalogentryType.
 * 
 * @version $Revision$ $Date$
 */
public class CatalogentryType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * internal content storage
     */
    private java.lang.String _content = "";

    /**
     * Field _catalog
     */
    private java.lang.String _catalog;

    /**
     * Field _entry
     */
    private es.pode.parseadorXML.castor.lomieee.Entry _entry;

    /**
     * Field _grp_any
     */
    private es.pode.parseadorXML.castor.lomieee.Grp_any _grp_any;


      //----------------/
     //- Constructors -/
    //----------------/

    public CatalogentryType() 
     {
        super();
        setContent("");
    } //-- es.pode.parseadorXML.castor.lomieee.CatalogentryType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'catalog'.
     * 
     * @return the value of field 'Catalog'.
     */
    public java.lang.String getCatalog()
    {
        return this._catalog;
    } //-- java.lang.String getCatalog() 

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
     * Returns the value of field 'entry'.
     * 
     * @return the value of field 'Entry'.
     */
    public es.pode.parseadorXML.castor.lomieee.Entry getEntry()
    {
        return this._entry;
    } //-- es.pode.parseadorXML.castor.lomieee.Entry getEntry() 

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
     * Sets the value of field 'catalog'.
     * 
     * @param catalog the value of field 'catalog'.
     */
    public void setCatalog(java.lang.String catalog)
    {
        this._catalog = catalog;
    } //-- void setCatalog(java.lang.String) 

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
     * Sets the value of field 'entry'.
     * 
     * @param entry the value of field 'entry'.
     */
    public void setEntry(es.pode.parseadorXML.castor.lomieee.Entry entry)
    {
        this._entry = entry;
    } //-- void setEntry(es.pode.parseadorXML.castor.lomieee.Entry) 

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
     * es.pode.parseadorXML.castor.lomieee.CatalogentryType
     */
    public static es.pode.parseadorXML.castor.lomieee.CatalogentryType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (es.pode.parseadorXML.castor.lomieee.CatalogentryType) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.lomieee.CatalogentryType.class, reader);
    } //-- es.pode.parseadorXML.castor.lomieee.CatalogentryType unmarshal(java.io.Reader) 

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
