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
 * Class MetametadataType.
 * 
 * @version $Revision$ $Date$
 */
public class MetametadataType implements java.io.Serializable {


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
     * Field _catalogentryList
     */
    private java.util.Vector _catalogentryList;

    /**
     * Field _contributeList
     */
    private java.util.Vector _contributeList;

    /**
     * Field _metadataschemeList
     */
    private java.util.Vector _metadataschemeList;

    /**
     * Field _language
     */
    private java.lang.String _language;

    /**
     * Field _grp_any
     */
    private es.pode.parseadorXML.castor.lomieee.Grp_any _grp_any;


      //----------------/
     //- Constructors -/
    //----------------/

    public MetametadataType() 
     {
        super();
        setContent("");
        this._catalogentryList = new java.util.Vector();
        this._contributeList = new java.util.Vector();
        this._metadataschemeList = new java.util.Vector();
    } //-- es.pode.parseadorXML.castor.lomieee.MetametadataType()


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
     * 
     * 
     * @param vContribute
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addContribute(es.pode.parseadorXML.castor.lomieee.Contribute vContribute)
        throws java.lang.IndexOutOfBoundsException
    {
        this._contributeList.addElement(vContribute);
    } //-- void addContribute(es.pode.parseadorXML.castor.lomieee.Contribute) 

    /**
     * 
     * 
     * @param index
     * @param vContribute
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addContribute(int index, es.pode.parseadorXML.castor.lomieee.Contribute vContribute)
        throws java.lang.IndexOutOfBoundsException
    {
        this._contributeList.add(index, vContribute);
    } //-- void addContribute(int, es.pode.parseadorXML.castor.lomieee.Contribute) 

    /**
     * 
     * 
     * @param vMetadatascheme
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addMetadatascheme(java.lang.String vMetadatascheme)
        throws java.lang.IndexOutOfBoundsException
    {
        this._metadataschemeList.addElement(vMetadatascheme);
    } //-- void addMetadatascheme(java.lang.String) 

    /**
     * 
     * 
     * @param index
     * @param vMetadatascheme
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addMetadatascheme(int index, java.lang.String vMetadatascheme)
        throws java.lang.IndexOutOfBoundsException
    {
        this._metadataschemeList.add(index, vMetadatascheme);
    } //-- void addMetadatascheme(int, java.lang.String) 

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
     * Method enumerateContribute
     * 
     * 
     * 
     * @return an Enumeration over all
     * es.pode.parseadorXML.castor.lomieee.Contribute elements
     */
    public java.util.Enumeration enumerateContribute()
    {
        return this._contributeList.elements();
    } //-- java.util.Enumeration enumerateContribute() 

    /**
     * Method enumerateMetadatascheme
     * 
     * 
     * 
     * @return an Enumeration over all java.lang.String elements
     */
    public java.util.Enumeration enumerateMetadatascheme()
    {
        return this._metadataschemeList.elements();
    } //-- java.util.Enumeration enumerateMetadatascheme() 

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
     * Method getContribute
     * 
     * 
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * es.pode.parseadorXML.castor.lomieee.Contribute at the given
     * index
     */
    public es.pode.parseadorXML.castor.lomieee.Contribute getContribute(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._contributeList.size()) {
            throw new IndexOutOfBoundsException("getContribute: Index value '" + index + "' not in range [0.." + (this._contributeList.size() - 1) + "]");
        }
        
        return (es.pode.parseadorXML.castor.lomieee.Contribute) _contributeList.get(index);
    } //-- es.pode.parseadorXML.castor.lomieee.Contribute getContribute(int) 

    /**
     * Method getContribute
     * 
     * 
     * 
     * @return this collection as an Array
     */
    public es.pode.parseadorXML.castor.lomieee.Contribute[] getContribute()
    {
        int size = this._contributeList.size();
        es.pode.parseadorXML.castor.lomieee.Contribute[] array = new es.pode.parseadorXML.castor.lomieee.Contribute[size];
        for (int index = 0; index < size; index++){
            array[index] = (es.pode.parseadorXML.castor.lomieee.Contribute) _contributeList.get(index);
        }
        
        return array;
    } //-- es.pode.parseadorXML.castor.lomieee.Contribute[] getContribute() 

    /**
     * Method getContributeCount
     * 
     * 
     * 
     * @return the size of this collection
     */
    public int getContributeCount()
    {
        return this._contributeList.size();
    } //-- int getContributeCount() 

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
     * Returns the value of field 'language'.
     * 
     * @return the value of field 'Language'.
     */
    public java.lang.String getLanguage()
    {
        return this._language;
    } //-- java.lang.String getLanguage() 

    /**
     * Method getMetadatascheme
     * 
     * 
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.String at the given index
     */
    public java.lang.String getMetadatascheme(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._metadataschemeList.size()) {
            throw new IndexOutOfBoundsException("getMetadatascheme: Index value '" + index + "' not in range [0.." + (this._metadataschemeList.size() - 1) + "]");
        }
        
        return (String)_metadataschemeList.get(index);
    } //-- java.lang.String getMetadatascheme(int) 

    /**
     * Method getMetadatascheme
     * 
     * 
     * 
     * @return this collection as an Array
     */
    public java.lang.String[] getMetadatascheme()
    {
        int size = this._metadataschemeList.size();
        java.lang.String[] array = new java.lang.String[size];
        for (int index = 0; index < size; index++){
            array[index] = (String)_metadataschemeList.get(index);
        }
        
        return array;
    } //-- java.lang.String[] getMetadatascheme() 

    /**
     * Method getMetadataschemeCount
     * 
     * 
     * 
     * @return the size of this collection
     */
    public int getMetadataschemeCount()
    {
        return this._metadataschemeList.size();
    } //-- int getMetadataschemeCount() 

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
     */
    public void removeAllContribute()
    {
        this._contributeList.clear();
    } //-- void removeAllContribute() 

    /**
     */
    public void removeAllMetadatascheme()
    {
        this._metadataschemeList.clear();
    } //-- void removeAllMetadatascheme() 

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
     * Method removeContribute
     * 
     * 
     * 
     * @param vContribute
     * @return true if the object was removed from the collection.
     */
    public boolean removeContribute(es.pode.parseadorXML.castor.lomieee.Contribute vContribute)
    {
        boolean removed = _contributeList.remove(vContribute);
        return removed;
    } //-- boolean removeContribute(es.pode.parseadorXML.castor.lomieee.Contribute) 

    /**
     * Method removeContributeAt
     * 
     * 
     * 
     * @param index
     * @return the element removed from the collection
     */
    public es.pode.parseadorXML.castor.lomieee.Contribute removeContributeAt(int index)
    {
        Object obj = this._contributeList.remove(index);
        return (es.pode.parseadorXML.castor.lomieee.Contribute) obj;
    } //-- es.pode.parseadorXML.castor.lomieee.Contribute removeContributeAt(int) 

    /**
     * Method removeMetadatascheme
     * 
     * 
     * 
     * @param vMetadatascheme
     * @return true if the object was removed from the collection.
     */
    public boolean removeMetadatascheme(java.lang.String vMetadatascheme)
    {
        boolean removed = _metadataschemeList.remove(vMetadatascheme);
        return removed;
    } //-- boolean removeMetadatascheme(java.lang.String) 

    /**
     * Method removeMetadataschemeAt
     * 
     * 
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.String removeMetadataschemeAt(int index)
    {
        Object obj = this._metadataschemeList.remove(index);
        return (String)obj;
    } //-- java.lang.String removeMetadataschemeAt(int) 

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
     * 
     * 
     * @param index
     * @param vContribute
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setContribute(int index, es.pode.parseadorXML.castor.lomieee.Contribute vContribute)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._contributeList.size()) {
            throw new IndexOutOfBoundsException("setContribute: Index value '" + index + "' not in range [0.." + (this._contributeList.size() - 1) + "]");
        }
        
        this._contributeList.set(index, vContribute);
    } //-- void setContribute(int, es.pode.parseadorXML.castor.lomieee.Contribute) 

    /**
     * 
     * 
     * @param vContributeArray
     */
    public void setContribute(es.pode.parseadorXML.castor.lomieee.Contribute[] vContributeArray)
    {
        //-- copy array
        _contributeList.clear();
        
        for (int i = 0; i < vContributeArray.length; i++) {
                this._contributeList.add(vContributeArray[i]);
        }
    } //-- void setContribute(es.pode.parseadorXML.castor.lomieee.Contribute) 

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
     * Sets the value of field 'language'.
     * 
     * @param language the value of field 'language'.
     */
    public void setLanguage(java.lang.String language)
    {
        this._language = language;
    } //-- void setLanguage(java.lang.String) 

    /**
     * 
     * 
     * @param index
     * @param vMetadatascheme
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setMetadatascheme(int index, java.lang.String vMetadatascheme)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._metadataschemeList.size()) {
            throw new IndexOutOfBoundsException("setMetadatascheme: Index value '" + index + "' not in range [0.." + (this._metadataschemeList.size() - 1) + "]");
        }
        
        this._metadataschemeList.set(index, vMetadatascheme);
    } //-- void setMetadatascheme(int, java.lang.String) 

    /**
     * 
     * 
     * @param vMetadataschemeArray
     */
    public void setMetadatascheme(java.lang.String[] vMetadataschemeArray)
    {
        //-- copy array
        _metadataschemeList.clear();
        
        for (int i = 0; i < vMetadataschemeArray.length; i++) {
                this._metadataschemeList.add(vMetadataschemeArray[i]);
        }
    } //-- void setMetadatascheme(java.lang.String) 

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
     * es.pode.parseadorXML.castor.lomieee.MetametadataType
     */
    public static es.pode.parseadorXML.castor.lomieee.MetametadataType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (es.pode.parseadorXML.castor.lomieee.MetametadataType) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.lomieee.MetametadataType.class, reader);
    } //-- es.pode.parseadorXML.castor.lomieee.MetametadataType unmarshal(java.io.Reader) 

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
