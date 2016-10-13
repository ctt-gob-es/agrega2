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
 * Class TechnicalType.
 * 
 * @version $Revision$ $Date$
 */
public class TechnicalType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * internal content storage
     */
    private java.lang.String _content = "";

    /**
     * Field _formatList
     */
    private java.util.Vector _formatList;

    /**
     * Field _size
     */
    private int _size;

    /**
     * keeps track of state for field: _size
     */
    private boolean _has_size;

    /**
     * Field _locationList
     */
    private java.util.Vector _locationList;

    /**
     * Field _requirementList
     */
    private java.util.Vector _requirementList;

    /**
     * Field _installationremarks
     */
    private es.pode.parseadorXML.castor.lomieee.Installationremarks _installationremarks;

    /**
     * Field _otherplatformrequirements
     */
    private es.pode.parseadorXML.castor.lomieee.Otherplatformrequirements _otherplatformrequirements;

    /**
     * Field _duration
     */
    private es.pode.parseadorXML.castor.lomieee.Duration _duration;

    /**
     * Field _grp_any
     */
    private es.pode.parseadorXML.castor.lomieee.Grp_any _grp_any;


      //----------------/
     //- Constructors -/
    //----------------/

    public TechnicalType() 
     {
        super();
        setContent("");
        this._formatList = new java.util.Vector();
        this._locationList = new java.util.Vector();
        this._requirementList = new java.util.Vector();
    } //-- es.pode.parseadorXML.castor.lomieee.TechnicalType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vFormat
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addFormat(java.lang.String vFormat)
        throws java.lang.IndexOutOfBoundsException
    {
        this._formatList.addElement(vFormat);
    } //-- void addFormat(java.lang.String) 

    /**
     * 
     * 
     * @param index
     * @param vFormat
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addFormat(int index, java.lang.String vFormat)
        throws java.lang.IndexOutOfBoundsException
    {
        this._formatList.add(index, vFormat);
    } //-- void addFormat(int, java.lang.String) 

    /**
     * 
     * 
     * @param vLocation
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addLocation(es.pode.parseadorXML.castor.lomieee.Location vLocation)
        throws java.lang.IndexOutOfBoundsException
    {
        this._locationList.addElement(vLocation);
    } //-- void addLocation(es.pode.parseadorXML.castor.lomieee.Location) 

    /**
     * 
     * 
     * @param index
     * @param vLocation
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addLocation(int index, es.pode.parseadorXML.castor.lomieee.Location vLocation)
        throws java.lang.IndexOutOfBoundsException
    {
        this._locationList.add(index, vLocation);
    } //-- void addLocation(int, es.pode.parseadorXML.castor.lomieee.Location) 

    /**
     * 
     * 
     * @param vRequirement
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addRequirement(es.pode.parseadorXML.castor.lomieee.Requirement vRequirement)
        throws java.lang.IndexOutOfBoundsException
    {
        this._requirementList.addElement(vRequirement);
    } //-- void addRequirement(es.pode.parseadorXML.castor.lomieee.Requirement) 

    /**
     * 
     * 
     * @param index
     * @param vRequirement
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addRequirement(int index, es.pode.parseadorXML.castor.lomieee.Requirement vRequirement)
        throws java.lang.IndexOutOfBoundsException
    {
        this._requirementList.add(index, vRequirement);
    } //-- void addRequirement(int, es.pode.parseadorXML.castor.lomieee.Requirement) 

    /**
     */
    public void deleteSize()
    {
        this._has_size= false;
    } //-- void deleteSize() 

    /**
     * Method enumerateFormat
     * 
     * 
     * 
     * @return an Enumeration over all java.lang.String elements
     */
    public java.util.Enumeration enumerateFormat()
    {
        return this._formatList.elements();
    } //-- java.util.Enumeration enumerateFormat() 

    /**
     * Method enumerateLocation
     * 
     * 
     * 
     * @return an Enumeration over all
     * es.pode.parseadorXML.castor.lomieee.Location elements
     */
    public java.util.Enumeration enumerateLocation()
    {
        return this._locationList.elements();
    } //-- java.util.Enumeration enumerateLocation() 

    /**
     * Method enumerateRequirement
     * 
     * 
     * 
     * @return an Enumeration over all
     * es.pode.parseadorXML.castor.lomieee.Requirement elements
     */
    public java.util.Enumeration enumerateRequirement()
    {
        return this._requirementList.elements();
    } //-- java.util.Enumeration enumerateRequirement() 

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
     * Returns the value of field 'duration'.
     * 
     * @return the value of field 'Duration'.
     */
    public es.pode.parseadorXML.castor.lomieee.Duration getDuration()
    {
        return this._duration;
    } //-- es.pode.parseadorXML.castor.lomieee.Duration getDuration() 

    /**
     * Method getFormat
     * 
     * 
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.String at the given index
     */
    public java.lang.String getFormat(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._formatList.size()) {
            throw new IndexOutOfBoundsException("getFormat: Index value '" + index + "' not in range [0.." + (this._formatList.size() - 1) + "]");
        }
        
        return (String)_formatList.get(index);
    } //-- java.lang.String getFormat(int) 

    /**
     * Method getFormat
     * 
     * 
     * 
     * @return this collection as an Array
     */
    public java.lang.String[] getFormat()
    {
        int size = this._formatList.size();
        java.lang.String[] array = new java.lang.String[size];
        for (int index = 0; index < size; index++){
            array[index] = (String)_formatList.get(index);
        }
        
        return array;
    } //-- java.lang.String[] getFormat() 

    /**
     * Method getFormatCount
     * 
     * 
     * 
     * @return the size of this collection
     */
    public int getFormatCount()
    {
        return this._formatList.size();
    } //-- int getFormatCount() 

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
     * Returns the value of field 'installationremarks'.
     * 
     * @return the value of field 'Installationremarks'.
     */
    public es.pode.parseadorXML.castor.lomieee.Installationremarks getInstallationremarks()
    {
        return this._installationremarks;
    } //-- es.pode.parseadorXML.castor.lomieee.Installationremarks getInstallationremarks() 

    /**
     * Method getLocation
     * 
     * 
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * es.pode.parseadorXML.castor.lomieee.Location at the given
     * index
     */
    public es.pode.parseadorXML.castor.lomieee.Location getLocation(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._locationList.size()) {
            throw new IndexOutOfBoundsException("getLocation: Index value '" + index + "' not in range [0.." + (this._locationList.size() - 1) + "]");
        }
        
        return (es.pode.parseadorXML.castor.lomieee.Location) _locationList.get(index);
    } //-- es.pode.parseadorXML.castor.lomieee.Location getLocation(int) 

    /**
     * Method getLocation
     * 
     * 
     * 
     * @return this collection as an Array
     */
    public es.pode.parseadorXML.castor.lomieee.Location[] getLocation()
    {
        int size = this._locationList.size();
        es.pode.parseadorXML.castor.lomieee.Location[] array = new es.pode.parseadorXML.castor.lomieee.Location[size];
        for (int index = 0; index < size; index++){
            array[index] = (es.pode.parseadorXML.castor.lomieee.Location) _locationList.get(index);
        }
        
        return array;
    } //-- es.pode.parseadorXML.castor.lomieee.Location[] getLocation() 

    /**
     * Method getLocationCount
     * 
     * 
     * 
     * @return the size of this collection
     */
    public int getLocationCount()
    {
        return this._locationList.size();
    } //-- int getLocationCount() 

    /**
     * Returns the value of field 'otherplatformrequirements'.
     * 
     * @return the value of field 'Otherplatformrequirements'.
     */
    public es.pode.parseadorXML.castor.lomieee.Otherplatformrequirements getOtherplatformrequirements()
    {
        return this._otherplatformrequirements;
    } //-- es.pode.parseadorXML.castor.lomieee.Otherplatformrequirements getOtherplatformrequirements() 

    /**
     * Method getRequirement
     * 
     * 
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * es.pode.parseadorXML.castor.lomieee.Requirement at the given
     * index
     */
    public es.pode.parseadorXML.castor.lomieee.Requirement getRequirement(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._requirementList.size()) {
            throw new IndexOutOfBoundsException("getRequirement: Index value '" + index + "' not in range [0.." + (this._requirementList.size() - 1) + "]");
        }
        
        return (es.pode.parseadorXML.castor.lomieee.Requirement) _requirementList.get(index);
    } //-- es.pode.parseadorXML.castor.lomieee.Requirement getRequirement(int) 

    /**
     * Method getRequirement
     * 
     * 
     * 
     * @return this collection as an Array
     */
    public es.pode.parseadorXML.castor.lomieee.Requirement[] getRequirement()
    {
        int size = this._requirementList.size();
        es.pode.parseadorXML.castor.lomieee.Requirement[] array = new es.pode.parseadorXML.castor.lomieee.Requirement[size];
        for (int index = 0; index < size; index++){
            array[index] = (es.pode.parseadorXML.castor.lomieee.Requirement) _requirementList.get(index);
        }
        
        return array;
    } //-- es.pode.parseadorXML.castor.lomieee.Requirement[] getRequirement() 

    /**
     * Method getRequirementCount
     * 
     * 
     * 
     * @return the size of this collection
     */
    public int getRequirementCount()
    {
        return this._requirementList.size();
    } //-- int getRequirementCount() 

    /**
     * Returns the value of field 'size'.
     * 
     * @return the value of field 'Size'.
     */
    public int getSize()
    {
        return this._size;
    } //-- int getSize() 

    /**
     * Method hasSize
     * 
     * 
     * 
     * @return true if at least one Size has been added
     */
    public boolean hasSize()
    {
        return this._has_size;
    } //-- boolean hasSize() 

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
    public void removeAllFormat()
    {
        this._formatList.clear();
    } //-- void removeAllFormat() 

    /**
     */
    public void removeAllLocation()
    {
        this._locationList.clear();
    } //-- void removeAllLocation() 

    /**
     */
    public void removeAllRequirement()
    {
        this._requirementList.clear();
    } //-- void removeAllRequirement() 

    /**
     * Method removeFormat
     * 
     * 
     * 
     * @param vFormat
     * @return true if the object was removed from the collection.
     */
    public boolean removeFormat(java.lang.String vFormat)
    {
        boolean removed = _formatList.remove(vFormat);
        return removed;
    } //-- boolean removeFormat(java.lang.String) 

    /**
     * Method removeFormatAt
     * 
     * 
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.String removeFormatAt(int index)
    {
        Object obj = this._formatList.remove(index);
        return (String)obj;
    } //-- java.lang.String removeFormatAt(int) 

    /**
     * Method removeLocation
     * 
     * 
     * 
     * @param vLocation
     * @return true if the object was removed from the collection.
     */
    public boolean removeLocation(es.pode.parseadorXML.castor.lomieee.Location vLocation)
    {
        boolean removed = _locationList.remove(vLocation);
        return removed;
    } //-- boolean removeLocation(es.pode.parseadorXML.castor.lomieee.Location) 

    /**
     * Method removeLocationAt
     * 
     * 
     * 
     * @param index
     * @return the element removed from the collection
     */
    public es.pode.parseadorXML.castor.lomieee.Location removeLocationAt(int index)
    {
        Object obj = this._locationList.remove(index);
        return (es.pode.parseadorXML.castor.lomieee.Location) obj;
    } //-- es.pode.parseadorXML.castor.lomieee.Location removeLocationAt(int) 

    /**
     * Method removeRequirement
     * 
     * 
     * 
     * @param vRequirement
     * @return true if the object was removed from the collection.
     */
    public boolean removeRequirement(es.pode.parseadorXML.castor.lomieee.Requirement vRequirement)
    {
        boolean removed = _requirementList.remove(vRequirement);
        return removed;
    } //-- boolean removeRequirement(es.pode.parseadorXML.castor.lomieee.Requirement) 

    /**
     * Method removeRequirementAt
     * 
     * 
     * 
     * @param index
     * @return the element removed from the collection
     */
    public es.pode.parseadorXML.castor.lomieee.Requirement removeRequirementAt(int index)
    {
        Object obj = this._requirementList.remove(index);
        return (es.pode.parseadorXML.castor.lomieee.Requirement) obj;
    } //-- es.pode.parseadorXML.castor.lomieee.Requirement removeRequirementAt(int) 

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
     * Sets the value of field 'duration'.
     * 
     * @param duration the value of field 'duration'.
     */
    public void setDuration(es.pode.parseadorXML.castor.lomieee.Duration duration)
    {
        this._duration = duration;
    } //-- void setDuration(es.pode.parseadorXML.castor.lomieee.Duration) 

    /**
     * 
     * 
     * @param index
     * @param vFormat
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setFormat(int index, java.lang.String vFormat)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._formatList.size()) {
            throw new IndexOutOfBoundsException("setFormat: Index value '" + index + "' not in range [0.." + (this._formatList.size() - 1) + "]");
        }
        
        this._formatList.set(index, vFormat);
    } //-- void setFormat(int, java.lang.String) 

    /**
     * 
     * 
     * @param vFormatArray
     */
    public void setFormat(java.lang.String[] vFormatArray)
    {
        //-- copy array
        _formatList.clear();
        
        for (int i = 0; i < vFormatArray.length; i++) {
                this._formatList.add(vFormatArray[i]);
        }
    } //-- void setFormat(java.lang.String) 

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
     * Sets the value of field 'installationremarks'.
     * 
     * @param installationremarks the value of field
     * 'installationremarks'.
     */
    public void setInstallationremarks(es.pode.parseadorXML.castor.lomieee.Installationremarks installationremarks)
    {
        this._installationremarks = installationremarks;
    } //-- void setInstallationremarks(es.pode.parseadorXML.castor.lomieee.Installationremarks) 

    /**
     * 
     * 
     * @param index
     * @param vLocation
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setLocation(int index, es.pode.parseadorXML.castor.lomieee.Location vLocation)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._locationList.size()) {
            throw new IndexOutOfBoundsException("setLocation: Index value '" + index + "' not in range [0.." + (this._locationList.size() - 1) + "]");
        }
        
        this._locationList.set(index, vLocation);
    } //-- void setLocation(int, es.pode.parseadorXML.castor.lomieee.Location) 

    /**
     * 
     * 
     * @param vLocationArray
     */
    public void setLocation(es.pode.parseadorXML.castor.lomieee.Location[] vLocationArray)
    {
        //-- copy array
        _locationList.clear();
        
        for (int i = 0; i < vLocationArray.length; i++) {
                this._locationList.add(vLocationArray[i]);
        }
    } //-- void setLocation(es.pode.parseadorXML.castor.lomieee.Location) 

    /**
     * Sets the value of field 'otherplatformrequirements'.
     * 
     * @param otherplatformrequirements the value of field
     * 'otherplatformrequirements'.
     */
    public void setOtherplatformrequirements(es.pode.parseadorXML.castor.lomieee.Otherplatformrequirements otherplatformrequirements)
    {
        this._otherplatformrequirements = otherplatformrequirements;
    } //-- void setOtherplatformrequirements(es.pode.parseadorXML.castor.lomieee.Otherplatformrequirements) 

    /**
     * 
     * 
     * @param index
     * @param vRequirement
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setRequirement(int index, es.pode.parseadorXML.castor.lomieee.Requirement vRequirement)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._requirementList.size()) {
            throw new IndexOutOfBoundsException("setRequirement: Index value '" + index + "' not in range [0.." + (this._requirementList.size() - 1) + "]");
        }
        
        this._requirementList.set(index, vRequirement);
    } //-- void setRequirement(int, es.pode.parseadorXML.castor.lomieee.Requirement) 

    /**
     * 
     * 
     * @param vRequirementArray
     */
    public void setRequirement(es.pode.parseadorXML.castor.lomieee.Requirement[] vRequirementArray)
    {
        //-- copy array
        _requirementList.clear();
        
        for (int i = 0; i < vRequirementArray.length; i++) {
                this._requirementList.add(vRequirementArray[i]);
        }
    } //-- void setRequirement(es.pode.parseadorXML.castor.lomieee.Requirement) 

    /**
     * Sets the value of field 'size'.
     * 
     * @param size the value of field 'size'.
     */
    public void setSize(int size)
    {
        this._size = size;
        this._has_size = true;
    } //-- void setSize(int) 

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
     * es.pode.parseadorXML.castor.lomieee.TechnicalType
     */
    public static es.pode.parseadorXML.castor.lomieee.TechnicalType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (es.pode.parseadorXML.castor.lomieee.TechnicalType) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.lomieee.TechnicalType.class, reader);
    } //-- es.pode.parseadorXML.castor.lomieee.TechnicalType unmarshal(java.io.Reader) 

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
