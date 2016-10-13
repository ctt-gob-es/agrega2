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
 * Class ContributeType.
 * 
 * @version $Revision$ $Date$
 */
public class ContributeType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * internal content storage
     */
    private java.lang.String _content = "";

    /**
     * Field _role
     */
    private es.pode.parseadorXML.castor.lomieee.Role _role;

    /**
     * Field _centityList
     */
    private java.util.Vector _centityList;

    /**
     * Field _date
     */
    private es.pode.parseadorXML.castor.lomieee.Date _date;

    /**
     * Field _grp_any
     */
    private es.pode.parseadorXML.castor.lomieee.Grp_any _grp_any;


      //----------------/
     //- Constructors -/
    //----------------/

    public ContributeType() 
     {
        super();
        setContent("");
        this._centityList = new java.util.Vector();
    } //-- es.pode.parseadorXML.castor.lomieee.ContributeType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vCentity
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCentity(es.pode.parseadorXML.castor.lomieee.Centity vCentity)
        throws java.lang.IndexOutOfBoundsException
    {
        this._centityList.addElement(vCentity);
    } //-- void addCentity(es.pode.parseadorXML.castor.lomieee.Centity) 

    /**
     * 
     * 
     * @param index
     * @param vCentity
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCentity(int index, es.pode.parseadorXML.castor.lomieee.Centity vCentity)
        throws java.lang.IndexOutOfBoundsException
    {
        this._centityList.add(index, vCentity);
    } //-- void addCentity(int, es.pode.parseadorXML.castor.lomieee.Centity) 

    /**
     * Method enumerateCentity
     * 
     * 
     * 
     * @return an Enumeration over all
     * es.pode.parseadorXML.castor.lomieee.Centity elements
     */
    public java.util.Enumeration enumerateCentity()
    {
        return this._centityList.elements();
    } //-- java.util.Enumeration enumerateCentity() 

    /**
     * Method getCentity
     * 
     * 
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * es.pode.parseadorXML.castor.lomieee.Centity at the given inde
     */
    public es.pode.parseadorXML.castor.lomieee.Centity getCentity(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._centityList.size()) {
            throw new IndexOutOfBoundsException("getCentity: Index value '" + index + "' not in range [0.." + (this._centityList.size() - 1) + "]");
        }
        
        return (es.pode.parseadorXML.castor.lomieee.Centity) _centityList.get(index);
    } //-- es.pode.parseadorXML.castor.lomieee.Centity getCentity(int) 

    /**
     * Method getCentity
     * 
     * 
     * 
     * @return this collection as an Array
     */
    public es.pode.parseadorXML.castor.lomieee.Centity[] getCentity()
    {
        int size = this._centityList.size();
        es.pode.parseadorXML.castor.lomieee.Centity[] array = new es.pode.parseadorXML.castor.lomieee.Centity[size];
        for (int index = 0; index < size; index++){
            array[index] = (es.pode.parseadorXML.castor.lomieee.Centity) _centityList.get(index);
        }
        
        return array;
    } //-- es.pode.parseadorXML.castor.lomieee.Centity[] getCentity() 

    /**
     * Method getCentityCount
     * 
     * 
     * 
     * @return the size of this collection
     */
    public int getCentityCount()
    {
        return this._centityList.size();
    } //-- int getCentityCount() 

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
     * Returns the value of field 'date'.
     * 
     * @return the value of field 'Date'.
     */
    public es.pode.parseadorXML.castor.lomieee.Date getDate()
    {
        return this._date;
    } //-- es.pode.parseadorXML.castor.lomieee.Date getDate() 

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
     * Returns the value of field 'role'.
     * 
     * @return the value of field 'Role'.
     */
    public es.pode.parseadorXML.castor.lomieee.Role getRole()
    {
        return this._role;
    } //-- es.pode.parseadorXML.castor.lomieee.Role getRole() 

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
    public void removeAllCentity()
    {
        this._centityList.clear();
    } //-- void removeAllCentity() 

    /**
     * Method removeCentity
     * 
     * 
     * 
     * @param vCentity
     * @return true if the object was removed from the collection.
     */
    public boolean removeCentity(es.pode.parseadorXML.castor.lomieee.Centity vCentity)
    {
        boolean removed = _centityList.remove(vCentity);
        return removed;
    } //-- boolean removeCentity(es.pode.parseadorXML.castor.lomieee.Centity) 

    /**
     * Method removeCentityAt
     * 
     * 
     * 
     * @param index
     * @return the element removed from the collection
     */
    public es.pode.parseadorXML.castor.lomieee.Centity removeCentityAt(int index)
    {
        Object obj = this._centityList.remove(index);
        return (es.pode.parseadorXML.castor.lomieee.Centity) obj;
    } //-- es.pode.parseadorXML.castor.lomieee.Centity removeCentityAt(int) 

    /**
     * 
     * 
     * @param index
     * @param vCentity
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setCentity(int index, es.pode.parseadorXML.castor.lomieee.Centity vCentity)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._centityList.size()) {
            throw new IndexOutOfBoundsException("setCentity: Index value '" + index + "' not in range [0.." + (this._centityList.size() - 1) + "]");
        }
        
        this._centityList.set(index, vCentity);
    } //-- void setCentity(int, es.pode.parseadorXML.castor.lomieee.Centity) 

    /**
     * 
     * 
     * @param vCentityArray
     */
    public void setCentity(es.pode.parseadorXML.castor.lomieee.Centity[] vCentityArray)
    {
        //-- copy array
        _centityList.clear();
        
        for (int i = 0; i < vCentityArray.length; i++) {
                this._centityList.add(vCentityArray[i]);
        }
    } //-- void setCentity(es.pode.parseadorXML.castor.lomieee.Centity) 

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
     * Sets the value of field 'date'.
     * 
     * @param date the value of field 'date'.
     */
    public void setDate(es.pode.parseadorXML.castor.lomieee.Date date)
    {
        this._date = date;
    } //-- void setDate(es.pode.parseadorXML.castor.lomieee.Date) 

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
     * Sets the value of field 'role'.
     * 
     * @param role the value of field 'role'.
     */
    public void setRole(es.pode.parseadorXML.castor.lomieee.Role role)
    {
        this._role = role;
    } //-- void setRole(es.pode.parseadorXML.castor.lomieee.Role) 

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
     * es.pode.parseadorXML.castor.lomieee.ContributeType
     */
    public static es.pode.parseadorXML.castor.lomieee.ContributeType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (es.pode.parseadorXML.castor.lomieee.ContributeType) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.lomieee.ContributeType.class, reader);
    } //-- es.pode.parseadorXML.castor.lomieee.ContributeType unmarshal(java.io.Reader) 

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
