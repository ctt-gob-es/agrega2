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
 * Class ClassificationType.
 * 
 * @version $Revision$ $Date$
 */
public class ClassificationType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * internal content storage
     */
    private java.lang.String _content = "";

    /**
     * Field _purpose
     */
    private es.pode.parseadorXML.castor.lomieee.Purpose _purpose;

    /**
     * Field _taxonpathList
     */
    private java.util.Vector _taxonpathList;

    /**
     * Field _description
     */
    private es.pode.parseadorXML.castor.lomieee.Description _description;

    /**
     * Field _keywordList
     */
    private java.util.Vector _keywordList;

    /**
     * Field _grp_any
     */
    private es.pode.parseadorXML.castor.lomieee.Grp_any _grp_any;


      //----------------/
     //- Constructors -/
    //----------------/

    public ClassificationType() 
     {
        super();
        setContent("");
        this._taxonpathList = new java.util.Vector();
        this._keywordList = new java.util.Vector();
    } //-- es.pode.parseadorXML.castor.lomieee.ClassificationType()


      //-----------/
     //- Methods -/
    //-----------/

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
     * @param vTaxonpath
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addTaxonpath(es.pode.parseadorXML.castor.lomieee.Taxonpath vTaxonpath)
        throws java.lang.IndexOutOfBoundsException
    {
        this._taxonpathList.addElement(vTaxonpath);
    } //-- void addTaxonpath(es.pode.parseadorXML.castor.lomieee.Taxonpath) 

    /**
     * 
     * 
     * @param index
     * @param vTaxonpath
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addTaxonpath(int index, es.pode.parseadorXML.castor.lomieee.Taxonpath vTaxonpath)
        throws java.lang.IndexOutOfBoundsException
    {
        this._taxonpathList.add(index, vTaxonpath);
    } //-- void addTaxonpath(int, es.pode.parseadorXML.castor.lomieee.Taxonpath) 

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
     * Method enumerateTaxonpath
     * 
     * 
     * 
     * @return an Enumeration over all
     * es.pode.parseadorXML.castor.lomieee.Taxonpath elements
     */
    public java.util.Enumeration enumerateTaxonpath()
    {
        return this._taxonpathList.elements();
    } //-- java.util.Enumeration enumerateTaxonpath() 

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
     * Returns the value of field 'purpose'.
     * 
     * @return the value of field 'Purpose'.
     */
    public es.pode.parseadorXML.castor.lomieee.Purpose getPurpose()
    {
        return this._purpose;
    } //-- es.pode.parseadorXML.castor.lomieee.Purpose getPurpose() 

    /**
     * Method getTaxonpath
     * 
     * 
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * es.pode.parseadorXML.castor.lomieee.Taxonpath at the given
     * index
     */
    public es.pode.parseadorXML.castor.lomieee.Taxonpath getTaxonpath(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._taxonpathList.size()) {
            throw new IndexOutOfBoundsException("getTaxonpath: Index value '" + index + "' not in range [0.." + (this._taxonpathList.size() - 1) + "]");
        }
        
        return (es.pode.parseadorXML.castor.lomieee.Taxonpath) _taxonpathList.get(index);
    } //-- es.pode.parseadorXML.castor.lomieee.Taxonpath getTaxonpath(int) 

    /**
     * Method getTaxonpath
     * 
     * 
     * 
     * @return this collection as an Array
     */
    public es.pode.parseadorXML.castor.lomieee.Taxonpath[] getTaxonpath()
    {
        int size = this._taxonpathList.size();
        es.pode.parseadorXML.castor.lomieee.Taxonpath[] array = new es.pode.parseadorXML.castor.lomieee.Taxonpath[size];
        for (int index = 0; index < size; index++){
            array[index] = (es.pode.parseadorXML.castor.lomieee.Taxonpath) _taxonpathList.get(index);
        }
        
        return array;
    } //-- es.pode.parseadorXML.castor.lomieee.Taxonpath[] getTaxonpath() 

    /**
     * Method getTaxonpathCount
     * 
     * 
     * 
     * @return the size of this collection
     */
    public int getTaxonpathCount()
    {
        return this._taxonpathList.size();
    } //-- int getTaxonpathCount() 

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
    public void removeAllKeyword()
    {
        this._keywordList.clear();
    } //-- void removeAllKeyword() 

    /**
     */
    public void removeAllTaxonpath()
    {
        this._taxonpathList.clear();
    } //-- void removeAllTaxonpath() 

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
     * Method removeTaxonpath
     * 
     * 
     * 
     * @param vTaxonpath
     * @return true if the object was removed from the collection.
     */
    public boolean removeTaxonpath(es.pode.parseadorXML.castor.lomieee.Taxonpath vTaxonpath)
    {
        boolean removed = _taxonpathList.remove(vTaxonpath);
        return removed;
    } //-- boolean removeTaxonpath(es.pode.parseadorXML.castor.lomieee.Taxonpath) 

    /**
     * Method removeTaxonpathAt
     * 
     * 
     * 
     * @param index
     * @return the element removed from the collection
     */
    public es.pode.parseadorXML.castor.lomieee.Taxonpath removeTaxonpathAt(int index)
    {
        Object obj = this._taxonpathList.remove(index);
        return (es.pode.parseadorXML.castor.lomieee.Taxonpath) obj;
    } //-- es.pode.parseadorXML.castor.lomieee.Taxonpath removeTaxonpathAt(int) 

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
     * Sets the value of field 'purpose'.
     * 
     * @param purpose the value of field 'purpose'.
     */
    public void setPurpose(es.pode.parseadorXML.castor.lomieee.Purpose purpose)
    {
        this._purpose = purpose;
    } //-- void setPurpose(es.pode.parseadorXML.castor.lomieee.Purpose) 

    /**
     * 
     * 
     * @param index
     * @param vTaxonpath
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setTaxonpath(int index, es.pode.parseadorXML.castor.lomieee.Taxonpath vTaxonpath)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._taxonpathList.size()) {
            throw new IndexOutOfBoundsException("setTaxonpath: Index value '" + index + "' not in range [0.." + (this._taxonpathList.size() - 1) + "]");
        }
        
        this._taxonpathList.set(index, vTaxonpath);
    } //-- void setTaxonpath(int, es.pode.parseadorXML.castor.lomieee.Taxonpath) 

    /**
     * 
     * 
     * @param vTaxonpathArray
     */
    public void setTaxonpath(es.pode.parseadorXML.castor.lomieee.Taxonpath[] vTaxonpathArray)
    {
        //-- copy array
        _taxonpathList.clear();
        
        for (int i = 0; i < vTaxonpathArray.length; i++) {
                this._taxonpathList.add(vTaxonpathArray[i]);
        }
    } //-- void setTaxonpath(es.pode.parseadorXML.castor.lomieee.Taxonpath) 

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
     * es.pode.parseadorXML.castor.lomieee.ClassificationType
     */
    public static es.pode.parseadorXML.castor.lomieee.ClassificationType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (es.pode.parseadorXML.castor.lomieee.ClassificationType) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.lomieee.ClassificationType.class, reader);
    } //-- es.pode.parseadorXML.castor.lomieee.ClassificationType unmarshal(java.io.Reader) 

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
