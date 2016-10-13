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
 * Class LomType.
 * 
 * @version $Revision$ $Date$
 */
public class LomType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _general
     */
    private es.pode.parseadorXML.castor.lomieee.General _general;

    /**
     * Field _lifecycle
     */
    private es.pode.parseadorXML.castor.lomieee.Lifecycle _lifecycle;

    /**
     * Field _metametadata
     */
    private es.pode.parseadorXML.castor.lomieee.Metametadata _metametadata;

    /**
     * Field _technical
     */
    private es.pode.parseadorXML.castor.lomieee.Technical _technical;

    /**
     * Field _educational
     */
    private es.pode.parseadorXML.castor.lomieee.Educational _educational;

    /**
     * Field _rights
     */
    private es.pode.parseadorXML.castor.lomieee.Rights _rights;

    /**
     * Field _relationList
     */
    private java.util.Vector _relationList;

    /**
     * Field _annotationList
     */
    private java.util.Vector _annotationList;

    /**
     * Field _classificationList
     */
    private java.util.Vector _classificationList;


      //----------------/
     //- Constructors -/
    //----------------/

    public LomType() 
     {
        super();
        this._relationList = new java.util.Vector();
        this._annotationList = new java.util.Vector();
        this._classificationList = new java.util.Vector();
    } //-- es.pode.parseadorXML.castor.lomieee.LomType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vAnnotation
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAnnotation(es.pode.parseadorXML.castor.lomieee.Annotation vAnnotation)
        throws java.lang.IndexOutOfBoundsException
    {
        this._annotationList.addElement(vAnnotation);
    } //-- void addAnnotation(es.pode.parseadorXML.castor.lomieee.Annotation) 

    /**
     * 
     * 
     * @param index
     * @param vAnnotation
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAnnotation(int index, es.pode.parseadorXML.castor.lomieee.Annotation vAnnotation)
        throws java.lang.IndexOutOfBoundsException
    {
        this._annotationList.add(index, vAnnotation);
    } //-- void addAnnotation(int, es.pode.parseadorXML.castor.lomieee.Annotation) 

    /**
     * 
     * 
     * @param vClassification
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addClassification(es.pode.parseadorXML.castor.lomieee.Classification vClassification)
        throws java.lang.IndexOutOfBoundsException
    {
        this._classificationList.addElement(vClassification);
    } //-- void addClassification(es.pode.parseadorXML.castor.lomieee.Classification) 

    /**
     * 
     * 
     * @param index
     * @param vClassification
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addClassification(int index, es.pode.parseadorXML.castor.lomieee.Classification vClassification)
        throws java.lang.IndexOutOfBoundsException
    {
        this._classificationList.add(index, vClassification);
    } //-- void addClassification(int, es.pode.parseadorXML.castor.lomieee.Classification) 

    /**
     * 
     * 
     * @param vRelation
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addRelation(es.pode.parseadorXML.castor.lomieee.Relation vRelation)
        throws java.lang.IndexOutOfBoundsException
    {
        this._relationList.addElement(vRelation);
    } //-- void addRelation(es.pode.parseadorXML.castor.lomieee.Relation) 

    /**
     * 
     * 
     * @param index
     * @param vRelation
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addRelation(int index, es.pode.parseadorXML.castor.lomieee.Relation vRelation)
        throws java.lang.IndexOutOfBoundsException
    {
        this._relationList.add(index, vRelation);
    } //-- void addRelation(int, es.pode.parseadorXML.castor.lomieee.Relation) 

    /**
     * Method enumerateAnnotation
     * 
     * 
     * 
     * @return an Enumeration over all
     * es.pode.parseadorXML.castor.lomieee.Annotation elements
     */
    public java.util.Enumeration enumerateAnnotation()
    {
        return this._annotationList.elements();
    } //-- java.util.Enumeration enumerateAnnotation() 

    /**
     * Method enumerateClassification
     * 
     * 
     * 
     * @return an Enumeration over all
     * es.pode.parseadorXML.castor.lomieee.Classification elements
     */
    public java.util.Enumeration enumerateClassification()
    {
        return this._classificationList.elements();
    } //-- java.util.Enumeration enumerateClassification() 

    /**
     * Method enumerateRelation
     * 
     * 
     * 
     * @return an Enumeration over all
     * es.pode.parseadorXML.castor.lomieee.Relation elements
     */
    public java.util.Enumeration enumerateRelation()
    {
        return this._relationList.elements();
    } //-- java.util.Enumeration enumerateRelation() 

    /**
     * Method getAnnotation
     * 
     * 
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * es.pode.parseadorXML.castor.lomieee.Annotation at the given
     * index
     */
    public es.pode.parseadorXML.castor.lomieee.Annotation getAnnotation(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._annotationList.size()) {
            throw new IndexOutOfBoundsException("getAnnotation: Index value '" + index + "' not in range [0.." + (this._annotationList.size() - 1) + "]");
        }
        
        return (es.pode.parseadorXML.castor.lomieee.Annotation) _annotationList.get(index);
    } //-- es.pode.parseadorXML.castor.lomieee.Annotation getAnnotation(int) 

    /**
     * Method getAnnotation
     * 
     * 
     * 
     * @return this collection as an Array
     */
    public es.pode.parseadorXML.castor.lomieee.Annotation[] getAnnotation()
    {
        int size = this._annotationList.size();
        es.pode.parseadorXML.castor.lomieee.Annotation[] array = new es.pode.parseadorXML.castor.lomieee.Annotation[size];
        for (int index = 0; index < size; index++){
            array[index] = (es.pode.parseadorXML.castor.lomieee.Annotation) _annotationList.get(index);
        }
        
        return array;
    } //-- es.pode.parseadorXML.castor.lomieee.Annotation[] getAnnotation() 

    /**
     * Method getAnnotationCount
     * 
     * 
     * 
     * @return the size of this collection
     */
    public int getAnnotationCount()
    {
        return this._annotationList.size();
    } //-- int getAnnotationCount() 

    /**
     * Method getClassification
     * 
     * 
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * es.pode.parseadorXML.castor.lomieee.Classification at the
     * given index
     */
    public es.pode.parseadorXML.castor.lomieee.Classification getClassification(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._classificationList.size()) {
            throw new IndexOutOfBoundsException("getClassification: Index value '" + index + "' not in range [0.." + (this._classificationList.size() - 1) + "]");
        }
        
        return (es.pode.parseadorXML.castor.lomieee.Classification) _classificationList.get(index);
    } //-- es.pode.parseadorXML.castor.lomieee.Classification getClassification(int) 

    /**
     * Method getClassification
     * 
     * 
     * 
     * @return this collection as an Array
     */
    public es.pode.parseadorXML.castor.lomieee.Classification[] getClassification()
    {
        int size = this._classificationList.size();
        es.pode.parseadorXML.castor.lomieee.Classification[] array = new es.pode.parseadorXML.castor.lomieee.Classification[size];
        for (int index = 0; index < size; index++){
            array[index] = (es.pode.parseadorXML.castor.lomieee.Classification) _classificationList.get(index);
        }
        
        return array;
    } //-- es.pode.parseadorXML.castor.lomieee.Classification[] getClassification() 

    /**
     * Method getClassificationCount
     * 
     * 
     * 
     * @return the size of this collection
     */
    public int getClassificationCount()
    {
        return this._classificationList.size();
    } //-- int getClassificationCount() 

    /**
     * Returns the value of field 'educational'.
     * 
     * @return the value of field 'Educational'.
     */
    public es.pode.parseadorXML.castor.lomieee.Educational getEducational()
    {
        return this._educational;
    } //-- es.pode.parseadorXML.castor.lomieee.Educational getEducational() 

    /**
     * Returns the value of field 'general'.
     * 
     * @return the value of field 'General'.
     */
    public es.pode.parseadorXML.castor.lomieee.General getGeneral()
    {
        return this._general;
    } //-- es.pode.parseadorXML.castor.lomieee.General getGeneral() 

    /**
     * Returns the value of field 'lifecycle'.
     * 
     * @return the value of field 'Lifecycle'.
     */
    public es.pode.parseadorXML.castor.lomieee.Lifecycle getLifecycle()
    {
        return this._lifecycle;
    } //-- es.pode.parseadorXML.castor.lomieee.Lifecycle getLifecycle() 

    /**
     * Returns the value of field 'metametadata'.
     * 
     * @return the value of field 'Metametadata'.
     */
    public es.pode.parseadorXML.castor.lomieee.Metametadata getMetametadata()
    {
        return this._metametadata;
    } //-- es.pode.parseadorXML.castor.lomieee.Metametadata getMetametadata() 

    /**
     * Method getRelation
     * 
     * 
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * es.pode.parseadorXML.castor.lomieee.Relation at the given
     * index
     */
    public es.pode.parseadorXML.castor.lomieee.Relation getRelation(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._relationList.size()) {
            throw new IndexOutOfBoundsException("getRelation: Index value '" + index + "' not in range [0.." + (this._relationList.size() - 1) + "]");
        }
        
        return (es.pode.parseadorXML.castor.lomieee.Relation) _relationList.get(index);
    } //-- es.pode.parseadorXML.castor.lomieee.Relation getRelation(int) 

    /**
     * Method getRelation
     * 
     * 
     * 
     * @return this collection as an Array
     */
    public es.pode.parseadorXML.castor.lomieee.Relation[] getRelation()
    {
        int size = this._relationList.size();
        es.pode.parseadorXML.castor.lomieee.Relation[] array = new es.pode.parseadorXML.castor.lomieee.Relation[size];
        for (int index = 0; index < size; index++){
            array[index] = (es.pode.parseadorXML.castor.lomieee.Relation) _relationList.get(index);
        }
        
        return array;
    } //-- es.pode.parseadorXML.castor.lomieee.Relation[] getRelation() 

    /**
     * Method getRelationCount
     * 
     * 
     * 
     * @return the size of this collection
     */
    public int getRelationCount()
    {
        return this._relationList.size();
    } //-- int getRelationCount() 

    /**
     * Returns the value of field 'rights'.
     * 
     * @return the value of field 'Rights'.
     */
    public es.pode.parseadorXML.castor.lomieee.Rights getRights()
    {
        return this._rights;
    } //-- es.pode.parseadorXML.castor.lomieee.Rights getRights() 

    /**
     * Returns the value of field 'technical'.
     * 
     * @return the value of field 'Technical'.
     */
    public es.pode.parseadorXML.castor.lomieee.Technical getTechnical()
    {
        return this._technical;
    } //-- es.pode.parseadorXML.castor.lomieee.Technical getTechnical() 

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
    public void removeAllAnnotation()
    {
        this._annotationList.clear();
    } //-- void removeAllAnnotation() 

    /**
     */
    public void removeAllClassification()
    {
        this._classificationList.clear();
    } //-- void removeAllClassification() 

    /**
     */
    public void removeAllRelation()
    {
        this._relationList.clear();
    } //-- void removeAllRelation() 

    /**
     * Method removeAnnotation
     * 
     * 
     * 
     * @param vAnnotation
     * @return true if the object was removed from the collection.
     */
    public boolean removeAnnotation(es.pode.parseadorXML.castor.lomieee.Annotation vAnnotation)
    {
        boolean removed = _annotationList.remove(vAnnotation);
        return removed;
    } //-- boolean removeAnnotation(es.pode.parseadorXML.castor.lomieee.Annotation) 

    /**
     * Method removeAnnotationAt
     * 
     * 
     * 
     * @param index
     * @return the element removed from the collection
     */
    public es.pode.parseadorXML.castor.lomieee.Annotation removeAnnotationAt(int index)
    {
        Object obj = this._annotationList.remove(index);
        return (es.pode.parseadorXML.castor.lomieee.Annotation) obj;
    } //-- es.pode.parseadorXML.castor.lomieee.Annotation removeAnnotationAt(int) 

    /**
     * Method removeClassification
     * 
     * 
     * 
     * @param vClassification
     * @return true if the object was removed from the collection.
     */
    public boolean removeClassification(es.pode.parseadorXML.castor.lomieee.Classification vClassification)
    {
        boolean removed = _classificationList.remove(vClassification);
        return removed;
    } //-- boolean removeClassification(es.pode.parseadorXML.castor.lomieee.Classification) 

    /**
     * Method removeClassificationAt
     * 
     * 
     * 
     * @param index
     * @return the element removed from the collection
     */
    public es.pode.parseadorXML.castor.lomieee.Classification removeClassificationAt(int index)
    {
        Object obj = this._classificationList.remove(index);
        return (es.pode.parseadorXML.castor.lomieee.Classification) obj;
    } //-- es.pode.parseadorXML.castor.lomieee.Classification removeClassificationAt(int) 

    /**
     * Method removeRelation
     * 
     * 
     * 
     * @param vRelation
     * @return true if the object was removed from the collection.
     */
    public boolean removeRelation(es.pode.parseadorXML.castor.lomieee.Relation vRelation)
    {
        boolean removed = _relationList.remove(vRelation);
        return removed;
    } //-- boolean removeRelation(es.pode.parseadorXML.castor.lomieee.Relation) 

    /**
     * Method removeRelationAt
     * 
     * 
     * 
     * @param index
     * @return the element removed from the collection
     */
    public es.pode.parseadorXML.castor.lomieee.Relation removeRelationAt(int index)
    {
        Object obj = this._relationList.remove(index);
        return (es.pode.parseadorXML.castor.lomieee.Relation) obj;
    } //-- es.pode.parseadorXML.castor.lomieee.Relation removeRelationAt(int) 

    /**
     * 
     * 
     * @param index
     * @param vAnnotation
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setAnnotation(int index, es.pode.parseadorXML.castor.lomieee.Annotation vAnnotation)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._annotationList.size()) {
            throw new IndexOutOfBoundsException("setAnnotation: Index value '" + index + "' not in range [0.." + (this._annotationList.size() - 1) + "]");
        }
        
        this._annotationList.set(index, vAnnotation);
    } //-- void setAnnotation(int, es.pode.parseadorXML.castor.lomieee.Annotation) 

    /**
     * 
     * 
     * @param vAnnotationArray
     */
    public void setAnnotation(es.pode.parseadorXML.castor.lomieee.Annotation[] vAnnotationArray)
    {
        //-- copy array
        _annotationList.clear();
        
        for (int i = 0; i < vAnnotationArray.length; i++) {
                this._annotationList.add(vAnnotationArray[i]);
        }
    } //-- void setAnnotation(es.pode.parseadorXML.castor.lomieee.Annotation) 

    /**
     * 
     * 
     * @param index
     * @param vClassification
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setClassification(int index, es.pode.parseadorXML.castor.lomieee.Classification vClassification)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._classificationList.size()) {
            throw new IndexOutOfBoundsException("setClassification: Index value '" + index + "' not in range [0.." + (this._classificationList.size() - 1) + "]");
        }
        
        this._classificationList.set(index, vClassification);
    } //-- void setClassification(int, es.pode.parseadorXML.castor.lomieee.Classification) 

    /**
     * 
     * 
     * @param vClassificationArray
     */
    public void setClassification(es.pode.parseadorXML.castor.lomieee.Classification[] vClassificationArray)
    {
        //-- copy array
        _classificationList.clear();
        
        for (int i = 0; i < vClassificationArray.length; i++) {
                this._classificationList.add(vClassificationArray[i]);
        }
    } //-- void setClassification(es.pode.parseadorXML.castor.lomieee.Classification) 

    /**
     * Sets the value of field 'educational'.
     * 
     * @param educational the value of field 'educational'.
     */
    public void setEducational(es.pode.parseadorXML.castor.lomieee.Educational educational)
    {
        this._educational = educational;
    } //-- void setEducational(es.pode.parseadorXML.castor.lomieee.Educational) 

    /**
     * Sets the value of field 'general'.
     * 
     * @param general the value of field 'general'.
     */
    public void setGeneral(es.pode.parseadorXML.castor.lomieee.General general)
    {
        this._general = general;
    } //-- void setGeneral(es.pode.parseadorXML.castor.lomieee.General) 

    /**
     * Sets the value of field 'lifecycle'.
     * 
     * @param lifecycle the value of field 'lifecycle'.
     */
    public void setLifecycle(es.pode.parseadorXML.castor.lomieee.Lifecycle lifecycle)
    {
        this._lifecycle = lifecycle;
    } //-- void setLifecycle(es.pode.parseadorXML.castor.lomieee.Lifecycle) 

    /**
     * Sets the value of field 'metametadata'.
     * 
     * @param metametadata the value of field 'metametadata'.
     */
    public void setMetametadata(es.pode.parseadorXML.castor.lomieee.Metametadata metametadata)
    {
        this._metametadata = metametadata;
    } //-- void setMetametadata(es.pode.parseadorXML.castor.lomieee.Metametadata) 

    /**
     * 
     * 
     * @param index
     * @param vRelation
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setRelation(int index, es.pode.parseadorXML.castor.lomieee.Relation vRelation)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._relationList.size()) {
            throw new IndexOutOfBoundsException("setRelation: Index value '" + index + "' not in range [0.." + (this._relationList.size() - 1) + "]");
        }
        
        this._relationList.set(index, vRelation);
    } //-- void setRelation(int, es.pode.parseadorXML.castor.lomieee.Relation) 

    /**
     * 
     * 
     * @param vRelationArray
     */
    public void setRelation(es.pode.parseadorXML.castor.lomieee.Relation[] vRelationArray)
    {
        //-- copy array
        _relationList.clear();
        
        for (int i = 0; i < vRelationArray.length; i++) {
                this._relationList.add(vRelationArray[i]);
        }
    } //-- void setRelation(es.pode.parseadorXML.castor.lomieee.Relation) 

    /**
     * Sets the value of field 'rights'.
     * 
     * @param rights the value of field 'rights'.
     */
    public void setRights(es.pode.parseadorXML.castor.lomieee.Rights rights)
    {
        this._rights = rights;
    } //-- void setRights(es.pode.parseadorXML.castor.lomieee.Rights) 

    /**
     * Sets the value of field 'technical'.
     * 
     * @param technical the value of field 'technical'.
     */
    public void setTechnical(es.pode.parseadorXML.castor.lomieee.Technical technical)
    {
        this._technical = technical;
    } //-- void setTechnical(es.pode.parseadorXML.castor.lomieee.Technical) 

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
     * es.pode.parseadorXML.castor.lomieee.LomType
     */
    public static es.pode.parseadorXML.castor.lomieee.LomType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (es.pode.parseadorXML.castor.lomieee.LomType) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.lomieee.LomType.class, reader);
    } //-- es.pode.parseadorXML.castor.lomieee.LomType unmarshal(java.io.Reader) 

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
