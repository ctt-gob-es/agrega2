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
 * Class GeneralType.
 * 
 * @version $Revision$ $Date$
 */
public class GeneralType implements java.io.Serializable {


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
     * Field _title
     */
    private es.pode.parseadorXML.castor.lomieee.Title _title;

    /**
     * Field _catalogentryList
     */
    private java.util.Vector _catalogentryList;

    /**
     * Field _languageList
     */
    private java.util.Vector _languageList;

    /**
     * Field _descriptionList
     */
    private java.util.Vector _descriptionList;

    /**
     * Field _keywordList
     */
    private java.util.Vector _keywordList;

    /**
     * Field _coverageList
     */
    private java.util.Vector _coverageList;

    /**
     * Field _structure
     */
    private es.pode.parseadorXML.castor.lomieee.Structure _structure;

    /**
     * Field _aggregationlevel
     */
    private es.pode.parseadorXML.castor.lomieee.Aggregationlevel _aggregationlevel;

    /**
     * Field _grp_any
     */
    private es.pode.parseadorXML.castor.lomieee.Grp_any _grp_any;


      //----------------/
     //- Constructors -/
    //----------------/

    public GeneralType() 
     {
        super();
        setContent("");
        this._catalogentryList = new java.util.Vector();
        this._languageList = new java.util.Vector();
        this._descriptionList = new java.util.Vector();
        this._keywordList = new java.util.Vector();
        this._coverageList = new java.util.Vector();
    } //-- es.pode.parseadorXML.castor.lomieee.GeneralType()


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
     * @param vCoverage
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCoverage(es.pode.parseadorXML.castor.lomieee.Coverage vCoverage)
        throws java.lang.IndexOutOfBoundsException
    {
        this._coverageList.addElement(vCoverage);
    } //-- void addCoverage(es.pode.parseadorXML.castor.lomieee.Coverage) 

    /**
     * 
     * 
     * @param index
     * @param vCoverage
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCoverage(int index, es.pode.parseadorXML.castor.lomieee.Coverage vCoverage)
        throws java.lang.IndexOutOfBoundsException
    {
        this._coverageList.add(index, vCoverage);
    } //-- void addCoverage(int, es.pode.parseadorXML.castor.lomieee.Coverage) 

    /**
     * 
     * 
     * @param vDescription
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addDescription(es.pode.parseadorXML.castor.lomieee.Description vDescription)
        throws java.lang.IndexOutOfBoundsException
    {
        this._descriptionList.addElement(vDescription);
    } //-- void addDescription(es.pode.parseadorXML.castor.lomieee.Description) 

    /**
     * 
     * 
     * @param index
     * @param vDescription
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addDescription(int index, es.pode.parseadorXML.castor.lomieee.Description vDescription)
        throws java.lang.IndexOutOfBoundsException
    {
        this._descriptionList.add(index, vDescription);
    } //-- void addDescription(int, es.pode.parseadorXML.castor.lomieee.Description) 

    /**
     * 
     * 
     * @param vKeyword
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addKeyword(es.pode.parseadorXML.castor.lomieee.Keyword vKeyword)
        throws java.lang.IndexOutOfBoundsException
    {
        this._keywordList.addElement(vKeyword);
    } //-- void addKeyword(es.pode.parseadorXML.castor.lomieee.Keyword) 

    /**
     * 
     * 
     * @param index
     * @param vKeyword
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addKeyword(int index, es.pode.parseadorXML.castor.lomieee.Keyword vKeyword)
        throws java.lang.IndexOutOfBoundsException
    {
        this._keywordList.add(index, vKeyword);
    } //-- void addKeyword(int, es.pode.parseadorXML.castor.lomieee.Keyword) 

    /**
     * 
     * 
     * @param vLanguage
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addLanguage(java.lang.String vLanguage)
        throws java.lang.IndexOutOfBoundsException
    {
        this._languageList.addElement(vLanguage);
    } //-- void addLanguage(java.lang.String) 

    /**
     * 
     * 
     * @param index
     * @param vLanguage
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addLanguage(int index, java.lang.String vLanguage)
        throws java.lang.IndexOutOfBoundsException
    {
        this._languageList.add(index, vLanguage);
    } //-- void addLanguage(int, java.lang.String) 

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
     * Method enumerateCoverage
     * 
     * 
     * 
     * @return an Enumeration over all
     * es.pode.parseadorXML.castor.lomieee.Coverage elements
     */
    public java.util.Enumeration enumerateCoverage()
    {
        return this._coverageList.elements();
    } //-- java.util.Enumeration enumerateCoverage() 

    /**
     * Method enumerateDescription
     * 
     * 
     * 
     * @return an Enumeration over all
     * es.pode.parseadorXML.castor.lomieee.Description elements
     */
    public java.util.Enumeration enumerateDescription()
    {
        return this._descriptionList.elements();
    } //-- java.util.Enumeration enumerateDescription() 

    /**
     * Method enumerateKeyword
     * 
     * 
     * 
     * @return an Enumeration over all
     * es.pode.parseadorXML.castor.lomieee.Keyword elements
     */
    public java.util.Enumeration enumerateKeyword()
    {
        return this._keywordList.elements();
    } //-- java.util.Enumeration enumerateKeyword() 

    /**
     * Method enumerateLanguage
     * 
     * 
     * 
     * @return an Enumeration over all java.lang.String elements
     */
    public java.util.Enumeration enumerateLanguage()
    {
        return this._languageList.elements();
    } //-- java.util.Enumeration enumerateLanguage() 

    /**
     * Returns the value of field 'aggregationlevel'.
     * 
     * @return the value of field 'Aggregationlevel'.
     */
    public es.pode.parseadorXML.castor.lomieee.Aggregationlevel getAggregationlevel()
    {
        return this._aggregationlevel;
    } //-- es.pode.parseadorXML.castor.lomieee.Aggregationlevel getAggregationlevel() 

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
     * Method getCoverage
     * 
     * 
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * es.pode.parseadorXML.castor.lomieee.Coverage at the given
     * index
     */
    public es.pode.parseadorXML.castor.lomieee.Coverage getCoverage(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._coverageList.size()) {
            throw new IndexOutOfBoundsException("getCoverage: Index value '" + index + "' not in range [0.." + (this._coverageList.size() - 1) + "]");
        }
        
        return (es.pode.parseadorXML.castor.lomieee.Coverage) _coverageList.get(index);
    } //-- es.pode.parseadorXML.castor.lomieee.Coverage getCoverage(int) 

    /**
     * Method getCoverage
     * 
     * 
     * 
     * @return this collection as an Array
     */
    public es.pode.parseadorXML.castor.lomieee.Coverage[] getCoverage()
    {
        int size = this._coverageList.size();
        es.pode.parseadorXML.castor.lomieee.Coverage[] array = new es.pode.parseadorXML.castor.lomieee.Coverage[size];
        for (int index = 0; index < size; index++){
            array[index] = (es.pode.parseadorXML.castor.lomieee.Coverage) _coverageList.get(index);
        }
        
        return array;
    } //-- es.pode.parseadorXML.castor.lomieee.Coverage[] getCoverage() 

    /**
     * Method getCoverageCount
     * 
     * 
     * 
     * @return the size of this collection
     */
    public int getCoverageCount()
    {
        return this._coverageList.size();
    } //-- int getCoverageCount() 

    /**
     * Method getDescription
     * 
     * 
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * es.pode.parseadorXML.castor.lomieee.Description at the given
     * index
     */
    public es.pode.parseadorXML.castor.lomieee.Description getDescription(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._descriptionList.size()) {
            throw new IndexOutOfBoundsException("getDescription: Index value '" + index + "' not in range [0.." + (this._descriptionList.size() - 1) + "]");
        }
        
        return (es.pode.parseadorXML.castor.lomieee.Description) _descriptionList.get(index);
    } //-- es.pode.parseadorXML.castor.lomieee.Description getDescription(int) 

    /**
     * Method getDescription
     * 
     * 
     * 
     * @return this collection as an Array
     */
    public es.pode.parseadorXML.castor.lomieee.Description[] getDescription()
    {
        int size = this._descriptionList.size();
        es.pode.parseadorXML.castor.lomieee.Description[] array = new es.pode.parseadorXML.castor.lomieee.Description[size];
        for (int index = 0; index < size; index++){
            array[index] = (es.pode.parseadorXML.castor.lomieee.Description) _descriptionList.get(index);
        }
        
        return array;
    } //-- es.pode.parseadorXML.castor.lomieee.Description[] getDescription() 

    /**
     * Method getDescriptionCount
     * 
     * 
     * 
     * @return the size of this collection
     */
    public int getDescriptionCount()
    {
        return this._descriptionList.size();
    } //-- int getDescriptionCount() 

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
     * Method getKeyword
     * 
     * 
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * es.pode.parseadorXML.castor.lomieee.Keyword at the given inde
     */
    public es.pode.parseadorXML.castor.lomieee.Keyword getKeyword(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._keywordList.size()) {
            throw new IndexOutOfBoundsException("getKeyword: Index value '" + index + "' not in range [0.." + (this._keywordList.size() - 1) + "]");
        }
        
        return (es.pode.parseadorXML.castor.lomieee.Keyword) _keywordList.get(index);
    } //-- es.pode.parseadorXML.castor.lomieee.Keyword getKeyword(int) 

    /**
     * Method getKeyword
     * 
     * 
     * 
     * @return this collection as an Array
     */
    public es.pode.parseadorXML.castor.lomieee.Keyword[] getKeyword()
    {
        int size = this._keywordList.size();
        es.pode.parseadorXML.castor.lomieee.Keyword[] array = new es.pode.parseadorXML.castor.lomieee.Keyword[size];
        for (int index = 0; index < size; index++){
            array[index] = (es.pode.parseadorXML.castor.lomieee.Keyword) _keywordList.get(index);
        }
        
        return array;
    } //-- es.pode.parseadorXML.castor.lomieee.Keyword[] getKeyword() 

    /**
     * Method getKeywordCount
     * 
     * 
     * 
     * @return the size of this collection
     */
    public int getKeywordCount()
    {
        return this._keywordList.size();
    } //-- int getKeywordCount() 

    /**
     * Method getLanguage
     * 
     * 
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.String at the given index
     */
    public java.lang.String getLanguage(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._languageList.size()) {
            throw new IndexOutOfBoundsException("getLanguage: Index value '" + index + "' not in range [0.." + (this._languageList.size() - 1) + "]");
        }
        
        return (String)_languageList.get(index);
    } //-- java.lang.String getLanguage(int) 

    /**
     * Method getLanguage
     * 
     * 
     * 
     * @return this collection as an Array
     */
    public java.lang.String[] getLanguage()
    {
        int size = this._languageList.size();
        java.lang.String[] array = new java.lang.String[size];
        for (int index = 0; index < size; index++){
            array[index] = (String)_languageList.get(index);
        }
        
        return array;
    } //-- java.lang.String[] getLanguage() 

    /**
     * Method getLanguageCount
     * 
     * 
     * 
     * @return the size of this collection
     */
    public int getLanguageCount()
    {
        return this._languageList.size();
    } //-- int getLanguageCount() 

    /**
     * Returns the value of field 'structure'.
     * 
     * @return the value of field 'Structure'.
     */
    public es.pode.parseadorXML.castor.lomieee.Structure getStructure()
    {
        return this._structure;
    } //-- es.pode.parseadorXML.castor.lomieee.Structure getStructure() 

    /**
     * Returns the value of field 'title'.
     * 
     * @return the value of field 'Title'.
     */
    public es.pode.parseadorXML.castor.lomieee.Title getTitle()
    {
        return this._title;
    } //-- es.pode.parseadorXML.castor.lomieee.Title getTitle() 

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
    public void removeAllCoverage()
    {
        this._coverageList.clear();
    } //-- void removeAllCoverage() 

    /**
     */
    public void removeAllDescription()
    {
        this._descriptionList.clear();
    } //-- void removeAllDescription() 

    /**
     */
    public void removeAllKeyword()
    {
        this._keywordList.clear();
    } //-- void removeAllKeyword() 

    /**
     */
    public void removeAllLanguage()
    {
        this._languageList.clear();
    } //-- void removeAllLanguage() 

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
     * Method removeCoverage
     * 
     * 
     * 
     * @param vCoverage
     * @return true if the object was removed from the collection.
     */
    public boolean removeCoverage(es.pode.parseadorXML.castor.lomieee.Coverage vCoverage)
    {
        boolean removed = _coverageList.remove(vCoverage);
        return removed;
    } //-- boolean removeCoverage(es.pode.parseadorXML.castor.lomieee.Coverage) 

    /**
     * Method removeCoverageAt
     * 
     * 
     * 
     * @param index
     * @return the element removed from the collection
     */
    public es.pode.parseadorXML.castor.lomieee.Coverage removeCoverageAt(int index)
    {
        Object obj = this._coverageList.remove(index);
        return (es.pode.parseadorXML.castor.lomieee.Coverage) obj;
    } //-- es.pode.parseadorXML.castor.lomieee.Coverage removeCoverageAt(int) 

    /**
     * Method removeDescription
     * 
     * 
     * 
     * @param vDescription
     * @return true if the object was removed from the collection.
     */
    public boolean removeDescription(es.pode.parseadorXML.castor.lomieee.Description vDescription)
    {
        boolean removed = _descriptionList.remove(vDescription);
        return removed;
    } //-- boolean removeDescription(es.pode.parseadorXML.castor.lomieee.Description) 

    /**
     * Method removeDescriptionAt
     * 
     * 
     * 
     * @param index
     * @return the element removed from the collection
     */
    public es.pode.parseadorXML.castor.lomieee.Description removeDescriptionAt(int index)
    {
        Object obj = this._descriptionList.remove(index);
        return (es.pode.parseadorXML.castor.lomieee.Description) obj;
    } //-- es.pode.parseadorXML.castor.lomieee.Description removeDescriptionAt(int) 

    /**
     * Method removeKeyword
     * 
     * 
     * 
     * @param vKeyword
     * @return true if the object was removed from the collection.
     */
    public boolean removeKeyword(es.pode.parseadorXML.castor.lomieee.Keyword vKeyword)
    {
        boolean removed = _keywordList.remove(vKeyword);
        return removed;
    } //-- boolean removeKeyword(es.pode.parseadorXML.castor.lomieee.Keyword) 

    /**
     * Method removeKeywordAt
     * 
     * 
     * 
     * @param index
     * @return the element removed from the collection
     */
    public es.pode.parseadorXML.castor.lomieee.Keyword removeKeywordAt(int index)
    {
        Object obj = this._keywordList.remove(index);
        return (es.pode.parseadorXML.castor.lomieee.Keyword) obj;
    } //-- es.pode.parseadorXML.castor.lomieee.Keyword removeKeywordAt(int) 

    /**
     * Method removeLanguage
     * 
     * 
     * 
     * @param vLanguage
     * @return true if the object was removed from the collection.
     */
    public boolean removeLanguage(java.lang.String vLanguage)
    {
        boolean removed = _languageList.remove(vLanguage);
        return removed;
    } //-- boolean removeLanguage(java.lang.String) 

    /**
     * Method removeLanguageAt
     * 
     * 
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.String removeLanguageAt(int index)
    {
        Object obj = this._languageList.remove(index);
        return (String)obj;
    } //-- java.lang.String removeLanguageAt(int) 

    /**
     * Sets the value of field 'aggregationlevel'.
     * 
     * @param aggregationlevel the value of field 'aggregationlevel'
     */
    public void setAggregationlevel(es.pode.parseadorXML.castor.lomieee.Aggregationlevel aggregationlevel)
    {
        this._aggregationlevel = aggregationlevel;
    } //-- void setAggregationlevel(es.pode.parseadorXML.castor.lomieee.Aggregationlevel) 

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
     * @param vCoverage
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setCoverage(int index, es.pode.parseadorXML.castor.lomieee.Coverage vCoverage)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._coverageList.size()) {
            throw new IndexOutOfBoundsException("setCoverage: Index value '" + index + "' not in range [0.." + (this._coverageList.size() - 1) + "]");
        }
        
        this._coverageList.set(index, vCoverage);
    } //-- void setCoverage(int, es.pode.parseadorXML.castor.lomieee.Coverage) 

    /**
     * 
     * 
     * @param vCoverageArray
     */
    public void setCoverage(es.pode.parseadorXML.castor.lomieee.Coverage[] vCoverageArray)
    {
        //-- copy array
        _coverageList.clear();
        
        for (int i = 0; i < vCoverageArray.length; i++) {
                this._coverageList.add(vCoverageArray[i]);
        }
    } //-- void setCoverage(es.pode.parseadorXML.castor.lomieee.Coverage) 

    /**
     * 
     * 
     * @param index
     * @param vDescription
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setDescription(int index, es.pode.parseadorXML.castor.lomieee.Description vDescription)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._descriptionList.size()) {
            throw new IndexOutOfBoundsException("setDescription: Index value '" + index + "' not in range [0.." + (this._descriptionList.size() - 1) + "]");
        }
        
        this._descriptionList.set(index, vDescription);
    } //-- void setDescription(int, es.pode.parseadorXML.castor.lomieee.Description) 

    /**
     * 
     * 
     * @param vDescriptionArray
     */
    public void setDescription(es.pode.parseadorXML.castor.lomieee.Description[] vDescriptionArray)
    {
        //-- copy array
        _descriptionList.clear();
        
        for (int i = 0; i < vDescriptionArray.length; i++) {
                this._descriptionList.add(vDescriptionArray[i]);
        }
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
     * 
     * 
     * @param index
     * @param vKeyword
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setKeyword(int index, es.pode.parseadorXML.castor.lomieee.Keyword vKeyword)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._keywordList.size()) {
            throw new IndexOutOfBoundsException("setKeyword: Index value '" + index + "' not in range [0.." + (this._keywordList.size() - 1) + "]");
        }
        
        this._keywordList.set(index, vKeyword);
    } //-- void setKeyword(int, es.pode.parseadorXML.castor.lomieee.Keyword) 

    /**
     * 
     * 
     * @param vKeywordArray
     */
    public void setKeyword(es.pode.parseadorXML.castor.lomieee.Keyword[] vKeywordArray)
    {
        //-- copy array
        _keywordList.clear();
        
        for (int i = 0; i < vKeywordArray.length; i++) {
                this._keywordList.add(vKeywordArray[i]);
        }
    } //-- void setKeyword(es.pode.parseadorXML.castor.lomieee.Keyword) 

    /**
     * 
     * 
     * @param index
     * @param vLanguage
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setLanguage(int index, java.lang.String vLanguage)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._languageList.size()) {
            throw new IndexOutOfBoundsException("setLanguage: Index value '" + index + "' not in range [0.." + (this._languageList.size() - 1) + "]");
        }
        
        this._languageList.set(index, vLanguage);
    } //-- void setLanguage(int, java.lang.String) 

    /**
     * 
     * 
     * @param vLanguageArray
     */
    public void setLanguage(java.lang.String[] vLanguageArray)
    {
        //-- copy array
        _languageList.clear();
        
        for (int i = 0; i < vLanguageArray.length; i++) {
                this._languageList.add(vLanguageArray[i]);
        }
    } //-- void setLanguage(java.lang.String) 

    /**
     * Sets the value of field 'structure'.
     * 
     * @param structure the value of field 'structure'.
     */
    public void setStructure(es.pode.parseadorXML.castor.lomieee.Structure structure)
    {
        this._structure = structure;
    } //-- void setStructure(es.pode.parseadorXML.castor.lomieee.Structure) 

    /**
     * Sets the value of field 'title'.
     * 
     * @param title the value of field 'title'.
     */
    public void setTitle(es.pode.parseadorXML.castor.lomieee.Title title)
    {
        this._title = title;
    } //-- void setTitle(es.pode.parseadorXML.castor.lomieee.Title) 

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
     * es.pode.parseadorXML.castor.lomieee.GeneralType
     */
    public static es.pode.parseadorXML.castor.lomieee.GeneralType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (es.pode.parseadorXML.castor.lomieee.GeneralType) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.lomieee.GeneralType.class, reader);
    } //-- es.pode.parseadorXML.castor.lomieee.GeneralType unmarshal(java.io.Reader) 

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
