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
 * Class LifecycleType.
 * 
 * @version $Revision$ $Date$
 */
public class LifecycleType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * internal content storage
     */
    private java.lang.String _content = "";

    /**
     * Field _version
     */
    private es.pode.parseadorXML.castor.lomieee.Version _version;

    /**
     * Field _status
     */
    private es.pode.parseadorXML.castor.lomieee.Status _status;

    /**
     * Field _contributeList
     */
    private java.util.Vector _contributeList;

    /**
     * Field _grp_any
     */
    private es.pode.parseadorXML.castor.lomieee.Grp_any _grp_any;


      //----------------/
     //- Constructors -/
    //----------------/

    public LifecycleType() 
     {
        super();
        setContent("");
        this._contributeList = new java.util.Vector();
    } //-- es.pode.parseadorXML.castor.lomieee.LifecycleType()


      //-----------/
     //- Methods -/
    //-----------/

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
     * Returns the value of field 'status'.
     * 
     * @return the value of field 'Status'.
     */
    public es.pode.parseadorXML.castor.lomieee.Status getStatus()
    {
        return this._status;
    } //-- es.pode.parseadorXML.castor.lomieee.Status getStatus() 

    /**
     * Returns the value of field 'version'.
     * 
     * @return the value of field 'Version'.
     */
    public es.pode.parseadorXML.castor.lomieee.Version getVersion()
    {
        return this._version;
    } //-- es.pode.parseadorXML.castor.lomieee.Version getVersion() 

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
    public void removeAllContribute()
    {
        this._contributeList.clear();
    } //-- void removeAllContribute() 

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
     * Sets the value of field 'status'.
     * 
     * @param status the value of field 'status'.
     */
    public void setStatus(es.pode.parseadorXML.castor.lomieee.Status status)
    {
        this._status = status;
    } //-- void setStatus(es.pode.parseadorXML.castor.lomieee.Status) 

    /**
     * Sets the value of field 'version'.
     * 
     * @param version the value of field 'version'.
     */
    public void setVersion(es.pode.parseadorXML.castor.lomieee.Version version)
    {
        this._version = version;
    } //-- void setVersion(es.pode.parseadorXML.castor.lomieee.Version) 

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
     * es.pode.parseadorXML.castor.lomieee.LifecycleType
     */
    public static es.pode.parseadorXML.castor.lomieee.LifecycleType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (es.pode.parseadorXML.castor.lomieee.LifecycleType) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.lomieee.LifecycleType.class, reader);
    } //-- es.pode.parseadorXML.castor.lomieee.LifecycleType unmarshal(java.io.Reader) 

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
