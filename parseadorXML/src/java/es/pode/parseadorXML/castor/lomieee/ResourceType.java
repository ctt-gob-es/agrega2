/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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
 * Class ResourceType.
 * 
 * @version $Revision$ $Date$
 */
public class ResourceType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * internal content storage
     */
    private java.lang.String _content = "";

    /**
     * Field _identifier
     */
    private java.lang.String _identifier;

    /**
     * Field _description
     */
    private es.pode.parseadorXML.castor.lomieee.Description _description;

    /**
     * Field _catalogentryList
     */
    private java.util.Vector _catalogentryList;

    /**
     * Field _grp_any
     */
    private es.pode.parseadorXML.castor.lomieee.Grp_any _grp_any;


      //----------------/
     //- Constructors -/
    //----------------/

    public ResourceType() 
     {
        super();
        setContent("");
        this._catalogentryList = new java.util.Vector();
    } //-- es.pode.parseadorXML.castor.lomieee.ResourceType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vCatalogentry
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCatalogentry(es.pode.parseadorXML.castor.lomieee.Catalogentry vCatalogentry)
        throws java.lang.IndexOutOfBoundsException
    {
        this._catalogentryList.addElement(vCatalogentry);
    } //-- void addCatalogentry(es.pode.parseadorXML.castor.lomieee.Catalogentry) 

    /**
     * 
     * 
     * @param index
     * @param vCatalogentry
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCatalogentry(int index, es.pode.parseadorXML.castor.lomieee.Catalogentry vCatalogentry)
        throws java.lang.IndexOutOfBoundsException
    {
        this._catalogentryList.add(index, vCatalogentry);
    } //-- void addCatalogentry(int, es.pode.parseadorXML.castor.lomieee.Catalogentry) 

    /**
     * Method enumerateCatalogentry
     * 
     * 
     * 
     * @return an Enumeration over all
     * es.pode.parseadorXML.castor.lomieee.Catalogentry elements
     */
    public java.util.Enumeration enumerateCatalogentry()
    {
        return this._catalogentryList.elements();
    } //-- java.util.Enumeration enumerateCatalogentry() 

    /**
     * Method getCatalogentry
     * 
     * 
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * es.pode.parseadorXML.castor.lomieee.Catalogentry at the
     * given index
     */
    public es.pode.parseadorXML.castor.lomieee.Catalogentry getCatalogentry(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._catalogentryList.size()) {
            throw new IndexOutOfBoundsException("getCatalogentry: Index value '" + index + "' not in range [0.." + (this._catalogentryList.size() - 1) + "]");
        }
        
        return (es.pode.parseadorXML.castor.lomieee.Catalogentry) _catalogentryList.get(index);
    } //-- es.pode.parseadorXML.castor.lomieee.Catalogentry getCatalogentry(int) 

    /**
     * Method getCatalogentry
     * 
     * 
     * 
     * @return this collection as an Array
     */
    public es.pode.parseadorXML.castor.lomieee.Catalogentry[] getCatalogentry()
    {
        int size = this._catalogentryList.size();
        es.pode.parseadorXML.castor.lomieee.Catalogentry[] array = new es.pode.parseadorXML.castor.lomieee.Catalogentry[size];
        for (int index = 0; index < size; index++){
            array[index] = (es.pode.parseadorXML.castor.lomieee.Catalogentry) _catalogentryList.get(index);
        }
        
        return array;
    } //-- es.pode.parseadorXML.castor.lomieee.Catalogentry[] getCatalogentry() 

    /**
     * Method getCatalogentryCount
     * 
     * 
     * 
     * @return the size of this collection
     */
    public int getCatalogentryCount()
    {
        return this._catalogentryList.size();
    } //-- int getCatalogentryCount() 

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
     * Returns the value of field 'identifier'.
     * 
     * @return the value of field 'Identifier'.
     */
    public java.lang.String getIdentifier()
    {
        return this._identifier;
    } //-- java.lang.String getIdentifier() 

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
     */
    public void removeAllCatalogentry()
    {
        this._catalogentryList.clear();
    } //-- void removeAllCatalogentry() 

    /**
     * Method removeCatalogentry
     * 
     * 
     * 
     * @param vCatalogentry
     * @return true if the object was removed from the collection.
     */
    public boolean removeCatalogentry(es.pode.parseadorXML.castor.lomieee.Catalogentry vCatalogentry)
    {
        boolean removed = _catalogentryList.remove(vCatalogentry);
        return removed;
    } //-- boolean removeCatalogentry(es.pode.parseadorXML.castor.lomieee.Catalogentry) 

    /**
     * Method removeCatalogentryAt
     * 
     * 
     * 
     * @param index
     * @return the element removed from the collection
     */
    public es.pode.parseadorXML.castor.lomieee.Catalogentry removeCatalogentryAt(int index)
    {
        Object obj = this._catalogentryList.remove(index);
        return (es.pode.parseadorXML.castor.lomieee.Catalogentry) obj;
    } //-- es.pode.parseadorXML.castor.lomieee.Catalogentry removeCatalogentryAt(int) 

    /**
     * 
     * 
     * @param index
     * @param vCatalogentry
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setCatalogentry(int index, es.pode.parseadorXML.castor.lomieee.Catalogentry vCatalogentry)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._catalogentryList.size()) {
            throw new IndexOutOfBoundsException("setCatalogentry: Index value '" + index + "' not in range [0.." + (this._catalogentryList.size() - 1) + "]");
        }
        
        this._catalogentryList.set(index, vCatalogentry);
    } //-- void setCatalogentry(int, es.pode.parseadorXML.castor.lomieee.Catalogentry) 

    /**
     * 
     * 
     * @param vCatalogentryArray
     */
    public void setCatalogentry(es.pode.parseadorXML.castor.lomieee.Catalogentry[] vCatalogentryArray)
    {
        //-- copy array
        _catalogentryList.clear();
        
        for (int i = 0; i < vCatalogentryArray.length; i++) {
                this._catalogentryList.add(vCatalogentryArray[i]);
        }
    } //-- void setCatalogentry(es.pode.parseadorXML.castor.lomieee.Catalogentry) 

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
     * Sets the value of field 'identifier'.
     * 
     * @param identifier the value of field 'identifier'.
     */
    public void setIdentifier(java.lang.String identifier)
    {
        this._identifier = identifier;
    } //-- void setIdentifier(java.lang.String) 

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
     * es.pode.parseadorXML.castor.lomieee.ResourceType
     */
    public static es.pode.parseadorXML.castor.lomieee.ResourceType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (es.pode.parseadorXML.castor.lomieee.ResourceType) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.lomieee.ResourceType.class, reader);
    } //-- es.pode.parseadorXML.castor.lomieee.ResourceType unmarshal(java.io.Reader) 

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
