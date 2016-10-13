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
 * Class EntryType.
 * 
 * @version $Revision$ $Date$
 */
public class EntryType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _langstringList
     */
    private java.util.Vector _langstringList;


      //----------------/
     //- Constructors -/
    //----------------/

    public EntryType() 
     {
        super();
        this._langstringList = new java.util.Vector();
    } //-- es.pode.parseadorXML.castor.lomieee.EntryType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vLangstring
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addLangstring(es.pode.parseadorXML.castor.lomieee.Langstring vLangstring)
        throws java.lang.IndexOutOfBoundsException
    {
        this._langstringList.addElement(vLangstring);
    } //-- void addLangstring(es.pode.parseadorXML.castor.lomieee.Langstring) 

    /**
     * 
     * 
     * @param index
     * @param vLangstring
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addLangstring(int index, es.pode.parseadorXML.castor.lomieee.Langstring vLangstring)
        throws java.lang.IndexOutOfBoundsException
    {
        this._langstringList.add(index, vLangstring);
    } //-- void addLangstring(int, es.pode.parseadorXML.castor.lomieee.Langstring) 

    /**
     * Method enumerateLangstring
     * 
     * 
     * 
     * @return an Enumeration over all
     * es.pode.parseadorXML.castor.lomieee.Langstring elements
     */
    public java.util.Enumeration enumerateLangstring()
    {
        return this._langstringList.elements();
    } //-- java.util.Enumeration enumerateLangstring() 

    /**
     * Method getLangstring
     * 
     * 
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * es.pode.parseadorXML.castor.lomieee.Langstring at the given
     * index
     */
    public es.pode.parseadorXML.castor.lomieee.Langstring getLangstring(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._langstringList.size()) {
            throw new IndexOutOfBoundsException("getLangstring: Index value '" + index + "' not in range [0.." + (this._langstringList.size() - 1) + "]");
        }
        
        return (es.pode.parseadorXML.castor.lomieee.Langstring) _langstringList.get(index);
    } //-- es.pode.parseadorXML.castor.lomieee.Langstring getLangstring(int) 

    /**
     * Method getLangstring
     * 
     * 
     * 
     * @return this collection as an Array
     */
    public es.pode.parseadorXML.castor.lomieee.Langstring[] getLangstring()
    {
        int size = this._langstringList.size();
        es.pode.parseadorXML.castor.lomieee.Langstring[] array = new es.pode.parseadorXML.castor.lomieee.Langstring[size];
        for (int index = 0; index < size; index++){
            array[index] = (es.pode.parseadorXML.castor.lomieee.Langstring) _langstringList.get(index);
        }
        
        return array;
    } //-- es.pode.parseadorXML.castor.lomieee.Langstring[] getLangstring() 

    /**
     * Method getLangstringCount
     * 
     * 
     * 
     * @return the size of this collection
     */
    public int getLangstringCount()
    {
        return this._langstringList.size();
    } //-- int getLangstringCount() 

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
    public void removeAllLangstring()
    {
        this._langstringList.clear();
    } //-- void removeAllLangstring() 

    /**
     * Method removeLangstring
     * 
     * 
     * 
     * @param vLangstring
     * @return true if the object was removed from the collection.
     */
    public boolean removeLangstring(es.pode.parseadorXML.castor.lomieee.Langstring vLangstring)
    {
        boolean removed = _langstringList.remove(vLangstring);
        return removed;
    } //-- boolean removeLangstring(es.pode.parseadorXML.castor.lomieee.Langstring) 

    /**
     * Method removeLangstringAt
     * 
     * 
     * 
     * @param index
     * @return the element removed from the collection
     */
    public es.pode.parseadorXML.castor.lomieee.Langstring removeLangstringAt(int index)
    {
        Object obj = this._langstringList.remove(index);
        return (es.pode.parseadorXML.castor.lomieee.Langstring) obj;
    } //-- es.pode.parseadorXML.castor.lomieee.Langstring removeLangstringAt(int) 

    /**
     * 
     * 
     * @param index
     * @param vLangstring
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setLangstring(int index, es.pode.parseadorXML.castor.lomieee.Langstring vLangstring)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._langstringList.size()) {
            throw new IndexOutOfBoundsException("setLangstring: Index value '" + index + "' not in range [0.." + (this._langstringList.size() - 1) + "]");
        }
        
        this._langstringList.set(index, vLangstring);
    } //-- void setLangstring(int, es.pode.parseadorXML.castor.lomieee.Langstring) 

    /**
     * 
     * 
     * @param vLangstringArray
     */
    public void setLangstring(es.pode.parseadorXML.castor.lomieee.Langstring[] vLangstringArray)
    {
        //-- copy array
        _langstringList.clear();
        
        for (int i = 0; i < vLangstringArray.length; i++) {
                this._langstringList.add(vLangstringArray[i]);
        }
    } //-- void setLangstring(es.pode.parseadorXML.castor.lomieee.Langstring) 

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
     * es.pode.parseadorXML.castor.lomieee.EntryType
     */
    public static es.pode.parseadorXML.castor.lomieee.EntryType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (es.pode.parseadorXML.castor.lomieee.EntryType) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.lomieee.EntryType.class, reader);
    } //-- es.pode.parseadorXML.castor.lomieee.EntryType unmarshal(java.io.Reader) 

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
