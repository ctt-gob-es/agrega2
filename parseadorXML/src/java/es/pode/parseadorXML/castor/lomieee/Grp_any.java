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
 * Any namespaced element from any namespace may be used for an
 * "any" element. The namespace for the imported element must be
 * defined in the instance, and the schema must be imported. 
 * 
 * @version $Revision$ $Date$
 */
public class Grp_any implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _anyObject
     */
    private java.util.Vector _anyObject;


      //----------------/
     //- Constructors -/
    //----------------/

    public Grp_any() 
     {
        super();
        this._anyObject = new java.util.Vector();
    } //-- es.pode.parseadorXML.castor.lomieee.Grp_any()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vAnyObject
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAnyObject(java.lang.Object vAnyObject)
        throws java.lang.IndexOutOfBoundsException
    {
        this._anyObject.addElement(vAnyObject);
    } //-- void addAnyObject(java.lang.Object) 

    /**
     * 
     * 
     * @param index
     * @param vAnyObject
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAnyObject(int index, java.lang.Object vAnyObject)
        throws java.lang.IndexOutOfBoundsException
    {
        this._anyObject.add(index, vAnyObject);
    } //-- void addAnyObject(int, java.lang.Object) 

    /**
     * Method enumerateAnyObject
     * 
     * 
     * 
     * @return an Enumeration over all java.lang.Object elements
     */
    public java.util.Enumeration enumerateAnyObject()
    {
        return this._anyObject.elements();
    } //-- java.util.Enumeration enumerateAnyObject() 

    /**
     * Method getAnyObject
     * 
     * 
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.Object at the given index
     */
    public java.lang.Object getAnyObject(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._anyObject.size()) {
            throw new IndexOutOfBoundsException("getAnyObject: Index value '" + index + "' not in range [0.." + (this._anyObject.size() - 1) + "]");
        }
        
        return (java.lang.Object) _anyObject.get(index);
    } //-- java.lang.Object getAnyObject(int) 

    /**
     * Method getAnyObject
     * 
     * 
     * 
     * @return this collection as an Array
     */
    public java.lang.Object[] getAnyObject()
    {
        int size = this._anyObject.size();
        java.lang.Object[] array = new java.lang.Object[size];
        for (int index = 0; index < size; index++){
            array[index] = (java.lang.Object) _anyObject.get(index);
        }
        
        return array;
    } //-- java.lang.Object[] getAnyObject() 

    /**
     * Method getAnyObjectCount
     * 
     * 
     * 
     * @return the size of this collection
     */
    public int getAnyObjectCount()
    {
        return this._anyObject.size();
    } //-- int getAnyObjectCount() 

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
    public void removeAllAnyObject()
    {
        this._anyObject.clear();
    } //-- void removeAllAnyObject() 

    /**
     * Method removeAnyObject
     * 
     * 
     * 
     * @param vAnyObject
     * @return true if the object was removed from the collection.
     */
    public boolean removeAnyObject(java.lang.Object vAnyObject)
    {
        boolean removed = _anyObject.remove(vAnyObject);
        return removed;
    } //-- boolean removeAnyObject(java.lang.Object) 

    /**
     * Method removeAnyObjectAt
     * 
     * 
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.Object removeAnyObjectAt(int index)
    {
        Object obj = this._anyObject.remove(index);
        return (java.lang.Object) obj;
    } //-- java.lang.Object removeAnyObjectAt(int) 

    /**
     * 
     * 
     * @param index
     * @param vAnyObject
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setAnyObject(int index, java.lang.Object vAnyObject)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._anyObject.size()) {
            throw new IndexOutOfBoundsException("setAnyObject: Index value '" + index + "' not in range [0.." + (this._anyObject.size() - 1) + "]");
        }
        
        this._anyObject.set(index, vAnyObject);
    } //-- void setAnyObject(int, java.lang.Object) 

    /**
     * 
     * 
     * @param vAnyObjectArray
     */
    public void setAnyObject(java.lang.Object[] vAnyObjectArray)
    {
        //-- copy array
        _anyObject.clear();
        
        for (int i = 0; i < vAnyObjectArray.length; i++) {
                this._anyObject.add(vAnyObjectArray[i]);
        }
    } //-- void setAnyObject(java.lang.Object) 

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
     * es.pode.parseadorXML.castor.lomieee.Grp_any
     */
    public static es.pode.parseadorXML.castor.lomieee.Grp_any unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (es.pode.parseadorXML.castor.lomieee.Grp_any) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.lomieee.Grp_any.class, reader);
    } //-- es.pode.parseadorXML.castor.lomieee.Grp_any unmarshal(java.io.Reader) 

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
