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
 * Class EducationalType.
 * 
 * @version $Revision$ $Date$
 */
public class EducationalType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * internal content storage
     */
    private java.lang.String _content = "";

    /**
     * Field _interactivitytype
     */
    private es.pode.parseadorXML.castor.lomieee.Interactivitytype _interactivitytype;

    /**
     * Field _learningresourcetypeList
     */
    private java.util.Vector _learningresourcetypeList;

    /**
     * Field _interactivitylevel
     */
    private es.pode.parseadorXML.castor.lomieee.Interactivitylevel _interactivitylevel;

    /**
     * Field _semanticdensity
     */
    private es.pode.parseadorXML.castor.lomieee.Semanticdensity _semanticdensity;

    /**
     * Field _intendedenduserroleList
     */
    private java.util.Vector _intendedenduserroleList;

    /**
     * Field _contextList
     */
    private java.util.Vector _contextList;

    /**
     * Field _typicalagerangeList
     */
    private java.util.Vector _typicalagerangeList;

    /**
     * Field _difficulty
     */
    private es.pode.parseadorXML.castor.lomieee.Difficulty _difficulty;

    /**
     * Field _typicallearningtime
     */
    private es.pode.parseadorXML.castor.lomieee.Typicallearningtime _typicallearningtime;

    /**
     * Field _description
     */
    private es.pode.parseadorXML.castor.lomieee.Description _description;

    /**
     * Field _languageList
     */
    private java.util.Vector _languageList;

    /**
     * Field _grp_any
     */
    private es.pode.parseadorXML.castor.lomieee.Grp_any _grp_any;


      //----------------/
     //- Constructors -/
    //----------------/

    public EducationalType() 
     {
        super();
        setContent("");
        this._learningresourcetypeList = new java.util.Vector();
        this._intendedenduserroleList = new java.util.Vector();
        this._contextList = new java.util.Vector();
        this._typicalagerangeList = new java.util.Vector();
        this._languageList = new java.util.Vector();
    } //-- es.pode.parseadorXML.castor.lomieee.EducationalType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vContext
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addContext(es.pode.parseadorXML.castor.lomieee.Context vContext)
        throws java.lang.IndexOutOfBoundsException
    {
        this._contextList.addElement(vContext);
    } //-- void addContext(es.pode.parseadorXML.castor.lomieee.Context) 

    /**
     * 
     * 
     * @param index
     * @param vContext
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addContext(int index, es.pode.parseadorXML.castor.lomieee.Context vContext)
        throws java.lang.IndexOutOfBoundsException
    {
        this._contextList.add(index, vContext);
    } //-- void addContext(int, es.pode.parseadorXML.castor.lomieee.Context) 

    /**
     * 
     * 
     * @param vIntendedenduserrole
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addIntendedenduserrole(es.pode.parseadorXML.castor.lomieee.Intendedenduserrole vIntendedenduserrole)
        throws java.lang.IndexOutOfBoundsException
    {
        this._intendedenduserroleList.addElement(vIntendedenduserrole);
    } //-- void addIntendedenduserrole(es.pode.parseadorXML.castor.lomieee.Intendedenduserrole) 

    /**
     * 
     * 
     * @param index
     * @param vIntendedenduserrole
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addIntendedenduserrole(int index, es.pode.parseadorXML.castor.lomieee.Intendedenduserrole vIntendedenduserrole)
        throws java.lang.IndexOutOfBoundsException
    {
        this._intendedenduserroleList.add(index, vIntendedenduserrole);
    } //-- void addIntendedenduserrole(int, es.pode.parseadorXML.castor.lomieee.Intendedenduserrole) 

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
     * 
     * 
     * @param vLearningresourcetype
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addLearningresourcetype(es.pode.parseadorXML.castor.lomieee.Learningresourcetype vLearningresourcetype)
        throws java.lang.IndexOutOfBoundsException
    {
        this._learningresourcetypeList.addElement(vLearningresourcetype);
    } //-- void addLearningresourcetype(es.pode.parseadorXML.castor.lomieee.Learningresourcetype) 

    /**
     * 
     * 
     * @param index
     * @param vLearningresourcetype
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addLearningresourcetype(int index, es.pode.parseadorXML.castor.lomieee.Learningresourcetype vLearningresourcetype)
        throws java.lang.IndexOutOfBoundsException
    {
        this._learningresourcetypeList.add(index, vLearningresourcetype);
    } //-- void addLearningresourcetype(int, es.pode.parseadorXML.castor.lomieee.Learningresourcetype) 

    /**
     * 
     * 
     * @param vTypicalagerange
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addTypicalagerange(es.pode.parseadorXML.castor.lomieee.Typicalagerange vTypicalagerange)
        throws java.lang.IndexOutOfBoundsException
    {
        this._typicalagerangeList.addElement(vTypicalagerange);
    } //-- void addTypicalagerange(es.pode.parseadorXML.castor.lomieee.Typicalagerange) 

    /**
     * 
     * 
     * @param index
     * @param vTypicalagerange
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addTypicalagerange(int index, es.pode.parseadorXML.castor.lomieee.Typicalagerange vTypicalagerange)
        throws java.lang.IndexOutOfBoundsException
    {
        this._typicalagerangeList.add(index, vTypicalagerange);
    } //-- void addTypicalagerange(int, es.pode.parseadorXML.castor.lomieee.Typicalagerange) 

    /**
     * Method enumerateContext
     * 
     * 
     * 
     * @return an Enumeration over all
     * es.pode.parseadorXML.castor.lomieee.Context elements
     */
    public java.util.Enumeration enumerateContext()
    {
        return this._contextList.elements();
    } //-- java.util.Enumeration enumerateContext() 

    /**
     * Method enumerateIntendedenduserrole
     * 
     * 
     * 
     * @return an Enumeration over all
     * es.pode.parseadorXML.castor.lomieee.Intendedenduserrole
     * elements
     */
    public java.util.Enumeration enumerateIntendedenduserrole()
    {
        return this._intendedenduserroleList.elements();
    } //-- java.util.Enumeration enumerateIntendedenduserrole() 

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
     * Method enumerateLearningresourcetype
     * 
     * 
     * 
     * @return an Enumeration over all
     * es.pode.parseadorXML.castor.lomieee.Learningresourcetype
     * elements
     */
    public java.util.Enumeration enumerateLearningresourcetype()
    {
        return this._learningresourcetypeList.elements();
    } //-- java.util.Enumeration enumerateLearningresourcetype() 

    /**
     * Method enumerateTypicalagerange
     * 
     * 
     * 
     * @return an Enumeration over all
     * es.pode.parseadorXML.castor.lomieee.Typicalagerange elements
     */
    public java.util.Enumeration enumerateTypicalagerange()
    {
        return this._typicalagerangeList.elements();
    } //-- java.util.Enumeration enumerateTypicalagerange() 

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
     * Method getContext
     * 
     * 
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * es.pode.parseadorXML.castor.lomieee.Context at the given inde
     */
    public es.pode.parseadorXML.castor.lomieee.Context getContext(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._contextList.size()) {
            throw new IndexOutOfBoundsException("getContext: Index value '" + index + "' not in range [0.." + (this._contextList.size() - 1) + "]");
        }
        
        return (es.pode.parseadorXML.castor.lomieee.Context) _contextList.get(index);
    } //-- es.pode.parseadorXML.castor.lomieee.Context getContext(int) 

    /**
     * Method getContext
     * 
     * 
     * 
     * @return this collection as an Array
     */
    public es.pode.parseadorXML.castor.lomieee.Context[] getContext()
    {
        int size = this._contextList.size();
        es.pode.parseadorXML.castor.lomieee.Context[] array = new es.pode.parseadorXML.castor.lomieee.Context[size];
        for (int index = 0; index < size; index++){
            array[index] = (es.pode.parseadorXML.castor.lomieee.Context) _contextList.get(index);
        }
        
        return array;
    } //-- es.pode.parseadorXML.castor.lomieee.Context[] getContext() 

    /**
     * Method getContextCount
     * 
     * 
     * 
     * @return the size of this collection
     */
    public int getContextCount()
    {
        return this._contextList.size();
    } //-- int getContextCount() 

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
     * Returns the value of field 'difficulty'.
     * 
     * @return the value of field 'Difficulty'.
     */
    public es.pode.parseadorXML.castor.lomieee.Difficulty getDifficulty()
    {
        return this._difficulty;
    } //-- es.pode.parseadorXML.castor.lomieee.Difficulty getDifficulty() 

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
     * Method getIntendedenduserrole
     * 
     * 
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * es.pode.parseadorXML.castor.lomieee.Intendedenduserrole at
     * the given index
     */
    public es.pode.parseadorXML.castor.lomieee.Intendedenduserrole getIntendedenduserrole(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._intendedenduserroleList.size()) {
            throw new IndexOutOfBoundsException("getIntendedenduserrole: Index value '" + index + "' not in range [0.." + (this._intendedenduserroleList.size() - 1) + "]");
        }
        
        return (es.pode.parseadorXML.castor.lomieee.Intendedenduserrole) _intendedenduserroleList.get(index);
    } //-- es.pode.parseadorXML.castor.lomieee.Intendedenduserrole getIntendedenduserrole(int) 

    /**
     * Method getIntendedenduserrole
     * 
     * 
     * 
     * @return this collection as an Array
     */
    public es.pode.parseadorXML.castor.lomieee.Intendedenduserrole[] getIntendedenduserrole()
    {
        int size = this._intendedenduserroleList.size();
        es.pode.parseadorXML.castor.lomieee.Intendedenduserrole[] array = new es.pode.parseadorXML.castor.lomieee.Intendedenduserrole[size];
        for (int index = 0; index < size; index++){
            array[index] = (es.pode.parseadorXML.castor.lomieee.Intendedenduserrole) _intendedenduserroleList.get(index);
        }
        
        return array;
    } //-- es.pode.parseadorXML.castor.lomieee.Intendedenduserrole[] getIntendedenduserrole() 

    /**
     * Method getIntendedenduserroleCount
     * 
     * 
     * 
     * @return the size of this collection
     */
    public int getIntendedenduserroleCount()
    {
        return this._intendedenduserroleList.size();
    } //-- int getIntendedenduserroleCount() 

    /**
     * Returns the value of field 'interactivitylevel'.
     * 
     * @return the value of field 'Interactivitylevel'.
     */
    public es.pode.parseadorXML.castor.lomieee.Interactivitylevel getInteractivitylevel()
    {
        return this._interactivitylevel;
    } //-- es.pode.parseadorXML.castor.lomieee.Interactivitylevel getInteractivitylevel() 

    /**
     * Returns the value of field 'interactivitytype'.
     * 
     * @return the value of field 'Interactivitytype'.
     */
    public es.pode.parseadorXML.castor.lomieee.Interactivitytype getInteractivitytype()
    {
        return this._interactivitytype;
    } //-- es.pode.parseadorXML.castor.lomieee.Interactivitytype getInteractivitytype() 

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
     * Method getLearningresourcetype
     * 
     * 
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * es.pode.parseadorXML.castor.lomieee.Learningresourcetype at
     * the given index
     */
    public es.pode.parseadorXML.castor.lomieee.Learningresourcetype getLearningresourcetype(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._learningresourcetypeList.size()) {
            throw new IndexOutOfBoundsException("getLearningresourcetype: Index value '" + index + "' not in range [0.." + (this._learningresourcetypeList.size() - 1) + "]");
        }
        
        return (es.pode.parseadorXML.castor.lomieee.Learningresourcetype) _learningresourcetypeList.get(index);
    } //-- es.pode.parseadorXML.castor.lomieee.Learningresourcetype getLearningresourcetype(int) 

    /**
     * Method getLearningresourcetype
     * 
     * 
     * 
     * @return this collection as an Array
     */
    public es.pode.parseadorXML.castor.lomieee.Learningresourcetype[] getLearningresourcetype()
    {
        int size = this._learningresourcetypeList.size();
        es.pode.parseadorXML.castor.lomieee.Learningresourcetype[] array = new es.pode.parseadorXML.castor.lomieee.Learningresourcetype[size];
        for (int index = 0; index < size; index++){
            array[index] = (es.pode.parseadorXML.castor.lomieee.Learningresourcetype) _learningresourcetypeList.get(index);
        }
        
        return array;
    } //-- es.pode.parseadorXML.castor.lomieee.Learningresourcetype[] getLearningresourcetype() 

    /**
     * Method getLearningresourcetypeCount
     * 
     * 
     * 
     * @return the size of this collection
     */
    public int getLearningresourcetypeCount()
    {
        return this._learningresourcetypeList.size();
    } //-- int getLearningresourcetypeCount() 

    /**
     * Returns the value of field 'semanticdensity'.
     * 
     * @return the value of field 'Semanticdensity'.
     */
    public es.pode.parseadorXML.castor.lomieee.Semanticdensity getSemanticdensity()
    {
        return this._semanticdensity;
    } //-- es.pode.parseadorXML.castor.lomieee.Semanticdensity getSemanticdensity() 

    /**
     * Method getTypicalagerange
     * 
     * 
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * es.pode.parseadorXML.castor.lomieee.Typicalagerange at the
     * given index
     */
    public es.pode.parseadorXML.castor.lomieee.Typicalagerange getTypicalagerange(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._typicalagerangeList.size()) {
            throw new IndexOutOfBoundsException("getTypicalagerange: Index value '" + index + "' not in range [0.." + (this._typicalagerangeList.size() - 1) + "]");
        }
        
        return (es.pode.parseadorXML.castor.lomieee.Typicalagerange) _typicalagerangeList.get(index);
    } //-- es.pode.parseadorXML.castor.lomieee.Typicalagerange getTypicalagerange(int) 

    /**
     * Method getTypicalagerange
     * 
     * 
     * 
     * @return this collection as an Array
     */
    public es.pode.parseadorXML.castor.lomieee.Typicalagerange[] getTypicalagerange()
    {
        int size = this._typicalagerangeList.size();
        es.pode.parseadorXML.castor.lomieee.Typicalagerange[] array = new es.pode.parseadorXML.castor.lomieee.Typicalagerange[size];
        for (int index = 0; index < size; index++){
            array[index] = (es.pode.parseadorXML.castor.lomieee.Typicalagerange) _typicalagerangeList.get(index);
        }
        
        return array;
    } //-- es.pode.parseadorXML.castor.lomieee.Typicalagerange[] getTypicalagerange() 

    /**
     * Method getTypicalagerangeCount
     * 
     * 
     * 
     * @return the size of this collection
     */
    public int getTypicalagerangeCount()
    {
        return this._typicalagerangeList.size();
    } //-- int getTypicalagerangeCount() 

    /**
     * Returns the value of field 'typicallearningtime'.
     * 
     * @return the value of field 'Typicallearningtime'.
     */
    public es.pode.parseadorXML.castor.lomieee.Typicallearningtime getTypicallearningtime()
    {
        return this._typicallearningtime;
    } //-- es.pode.parseadorXML.castor.lomieee.Typicallearningtime getTypicallearningtime() 

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
    public void removeAllContext()
    {
        this._contextList.clear();
    } //-- void removeAllContext() 

    /**
     */
    public void removeAllIntendedenduserrole()
    {
        this._intendedenduserroleList.clear();
    } //-- void removeAllIntendedenduserrole() 

    /**
     */
    public void removeAllLanguage()
    {
        this._languageList.clear();
    } //-- void removeAllLanguage() 

    /**
     */
    public void removeAllLearningresourcetype()
    {
        this._learningresourcetypeList.clear();
    } //-- void removeAllLearningresourcetype() 

    /**
     */
    public void removeAllTypicalagerange()
    {
        this._typicalagerangeList.clear();
    } //-- void removeAllTypicalagerange() 

    /**
     * Method removeContext
     * 
     * 
     * 
     * @param vContext
     * @return true if the object was removed from the collection.
     */
    public boolean removeContext(es.pode.parseadorXML.castor.lomieee.Context vContext)
    {
        boolean removed = _contextList.remove(vContext);
        return removed;
    } //-- boolean removeContext(es.pode.parseadorXML.castor.lomieee.Context) 

    /**
     * Method removeContextAt
     * 
     * 
     * 
     * @param index
     * @return the element removed from the collection
     */
    public es.pode.parseadorXML.castor.lomieee.Context removeContextAt(int index)
    {
        Object obj = this._contextList.remove(index);
        return (es.pode.parseadorXML.castor.lomieee.Context) obj;
    } //-- es.pode.parseadorXML.castor.lomieee.Context removeContextAt(int) 

    /**
     * Method removeIntendedenduserrole
     * 
     * 
     * 
     * @param vIntendedenduserrole
     * @return true if the object was removed from the collection.
     */
    public boolean removeIntendedenduserrole(es.pode.parseadorXML.castor.lomieee.Intendedenduserrole vIntendedenduserrole)
    {
        boolean removed = _intendedenduserroleList.remove(vIntendedenduserrole);
        return removed;
    } //-- boolean removeIntendedenduserrole(es.pode.parseadorXML.castor.lomieee.Intendedenduserrole) 

    /**
     * Method removeIntendedenduserroleAt
     * 
     * 
     * 
     * @param index
     * @return the element removed from the collection
     */
    public es.pode.parseadorXML.castor.lomieee.Intendedenduserrole removeIntendedenduserroleAt(int index)
    {
        Object obj = this._intendedenduserroleList.remove(index);
        return (es.pode.parseadorXML.castor.lomieee.Intendedenduserrole) obj;
    } //-- es.pode.parseadorXML.castor.lomieee.Intendedenduserrole removeIntendedenduserroleAt(int) 

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
     * Method removeLearningresourcetype
     * 
     * 
     * 
     * @param vLearningresourcetype
     * @return true if the object was removed from the collection.
     */
    public boolean removeLearningresourcetype(es.pode.parseadorXML.castor.lomieee.Learningresourcetype vLearningresourcetype)
    {
        boolean removed = _learningresourcetypeList.remove(vLearningresourcetype);
        return removed;
    } //-- boolean removeLearningresourcetype(es.pode.parseadorXML.castor.lomieee.Learningresourcetype) 

    /**
     * Method removeLearningresourcetypeAt
     * 
     * 
     * 
     * @param index
     * @return the element removed from the collection
     */
    public es.pode.parseadorXML.castor.lomieee.Learningresourcetype removeLearningresourcetypeAt(int index)
    {
        Object obj = this._learningresourcetypeList.remove(index);
        return (es.pode.parseadorXML.castor.lomieee.Learningresourcetype) obj;
    } //-- es.pode.parseadorXML.castor.lomieee.Learningresourcetype removeLearningresourcetypeAt(int) 

    /**
     * Method removeTypicalagerange
     * 
     * 
     * 
     * @param vTypicalagerange
     * @return true if the object was removed from the collection.
     */
    public boolean removeTypicalagerange(es.pode.parseadorXML.castor.lomieee.Typicalagerange vTypicalagerange)
    {
        boolean removed = _typicalagerangeList.remove(vTypicalagerange);
        return removed;
    } //-- boolean removeTypicalagerange(es.pode.parseadorXML.castor.lomieee.Typicalagerange) 

    /**
     * Method removeTypicalagerangeAt
     * 
     * 
     * 
     * @param index
     * @return the element removed from the collection
     */
    public es.pode.parseadorXML.castor.lomieee.Typicalagerange removeTypicalagerangeAt(int index)
    {
        Object obj = this._typicalagerangeList.remove(index);
        return (es.pode.parseadorXML.castor.lomieee.Typicalagerange) obj;
    } //-- es.pode.parseadorXML.castor.lomieee.Typicalagerange removeTypicalagerangeAt(int) 

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
     * @param vContext
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setContext(int index, es.pode.parseadorXML.castor.lomieee.Context vContext)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._contextList.size()) {
            throw new IndexOutOfBoundsException("setContext: Index value '" + index + "' not in range [0.." + (this._contextList.size() - 1) + "]");
        }
        
        this._contextList.set(index, vContext);
    } //-- void setContext(int, es.pode.parseadorXML.castor.lomieee.Context) 

    /**
     * 
     * 
     * @param vContextArray
     */
    public void setContext(es.pode.parseadorXML.castor.lomieee.Context[] vContextArray)
    {
        //-- copy array
        _contextList.clear();
        
        for (int i = 0; i < vContextArray.length; i++) {
                this._contextList.add(vContextArray[i]);
        }
    } //-- void setContext(es.pode.parseadorXML.castor.lomieee.Context) 

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
     * Sets the value of field 'difficulty'.
     * 
     * @param difficulty the value of field 'difficulty'.
     */
    public void setDifficulty(es.pode.parseadorXML.castor.lomieee.Difficulty difficulty)
    {
        this._difficulty = difficulty;
    } //-- void setDifficulty(es.pode.parseadorXML.castor.lomieee.Difficulty) 

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
     * @param vIntendedenduserrole
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setIntendedenduserrole(int index, es.pode.parseadorXML.castor.lomieee.Intendedenduserrole vIntendedenduserrole)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._intendedenduserroleList.size()) {
            throw new IndexOutOfBoundsException("setIntendedenduserrole: Index value '" + index + "' not in range [0.." + (this._intendedenduserroleList.size() - 1) + "]");
        }
        
        this._intendedenduserroleList.set(index, vIntendedenduserrole);
    } //-- void setIntendedenduserrole(int, es.pode.parseadorXML.castor.lomieee.Intendedenduserrole) 

    /**
     * 
     * 
     * @param vIntendedenduserroleArray
     */
    public void setIntendedenduserrole(es.pode.parseadorXML.castor.lomieee.Intendedenduserrole[] vIntendedenduserroleArray)
    {
        //-- copy array
        _intendedenduserroleList.clear();
        
        for (int i = 0; i < vIntendedenduserroleArray.length; i++) {
                this._intendedenduserroleList.add(vIntendedenduserroleArray[i]);
        }
    } //-- void setIntendedenduserrole(es.pode.parseadorXML.castor.lomieee.Intendedenduserrole) 

    /**
     * Sets the value of field 'interactivitylevel'.
     * 
     * @param interactivitylevel the value of field
     * 'interactivitylevel'.
     */
    public void setInteractivitylevel(es.pode.parseadorXML.castor.lomieee.Interactivitylevel interactivitylevel)
    {
        this._interactivitylevel = interactivitylevel;
    } //-- void setInteractivitylevel(es.pode.parseadorXML.castor.lomieee.Interactivitylevel) 

    /**
     * Sets the value of field 'interactivitytype'.
     * 
     * @param interactivitytype the value of field
     * 'interactivitytype'.
     */
    public void setInteractivitytype(es.pode.parseadorXML.castor.lomieee.Interactivitytype interactivitytype)
    {
        this._interactivitytype = interactivitytype;
    } //-- void setInteractivitytype(es.pode.parseadorXML.castor.lomieee.Interactivitytype) 

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
     * 
     * 
     * @param index
     * @param vLearningresourcetype
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setLearningresourcetype(int index, es.pode.parseadorXML.castor.lomieee.Learningresourcetype vLearningresourcetype)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._learningresourcetypeList.size()) {
            throw new IndexOutOfBoundsException("setLearningresourcetype: Index value '" + index + "' not in range [0.." + (this._learningresourcetypeList.size() - 1) + "]");
        }
        
        this._learningresourcetypeList.set(index, vLearningresourcetype);
    } //-- void setLearningresourcetype(int, es.pode.parseadorXML.castor.lomieee.Learningresourcetype) 

    /**
     * 
     * 
     * @param vLearningresourcetypeArray
     */
    public void setLearningresourcetype(es.pode.parseadorXML.castor.lomieee.Learningresourcetype[] vLearningresourcetypeArray)
    {
        //-- copy array
        _learningresourcetypeList.clear();
        
        for (int i = 0; i < vLearningresourcetypeArray.length; i++) {
                this._learningresourcetypeList.add(vLearningresourcetypeArray[i]);
        }
    } //-- void setLearningresourcetype(es.pode.parseadorXML.castor.lomieee.Learningresourcetype) 

    /**
     * Sets the value of field 'semanticdensity'.
     * 
     * @param semanticdensity the value of field 'semanticdensity'.
     */
    public void setSemanticdensity(es.pode.parseadorXML.castor.lomieee.Semanticdensity semanticdensity)
    {
        this._semanticdensity = semanticdensity;
    } //-- void setSemanticdensity(es.pode.parseadorXML.castor.lomieee.Semanticdensity) 

    /**
     * 
     * 
     * @param index
     * @param vTypicalagerange
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setTypicalagerange(int index, es.pode.parseadorXML.castor.lomieee.Typicalagerange vTypicalagerange)
        throws java.lang.IndexOutOfBoundsException
    {
        // check bounds for index
        if (index < 0 || index >= this._typicalagerangeList.size()) {
            throw new IndexOutOfBoundsException("setTypicalagerange: Index value '" + index + "' not in range [0.." + (this._typicalagerangeList.size() - 1) + "]");
        }
        
        this._typicalagerangeList.set(index, vTypicalagerange);
    } //-- void setTypicalagerange(int, es.pode.parseadorXML.castor.lomieee.Typicalagerange) 

    /**
     * 
     * 
     * @param vTypicalagerangeArray
     */
    public void setTypicalagerange(es.pode.parseadorXML.castor.lomieee.Typicalagerange[] vTypicalagerangeArray)
    {
        //-- copy array
        _typicalagerangeList.clear();
        
        for (int i = 0; i < vTypicalagerangeArray.length; i++) {
                this._typicalagerangeList.add(vTypicalagerangeArray[i]);
        }
    } //-- void setTypicalagerange(es.pode.parseadorXML.castor.lomieee.Typicalagerange) 

    /**
     * Sets the value of field 'typicallearningtime'.
     * 
     * @param typicallearningtime the value of field
     * 'typicallearningtime'.
     */
    public void setTypicallearningtime(es.pode.parseadorXML.castor.lomieee.Typicallearningtime typicallearningtime)
    {
        this._typicallearningtime = typicallearningtime;
    } //-- void setTypicallearningtime(es.pode.parseadorXML.castor.lomieee.Typicallearningtime) 

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
     * es.pode.parseadorXML.castor.lomieee.EducationalType
     */
    public static es.pode.parseadorXML.castor.lomieee.EducationalType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (es.pode.parseadorXML.castor.lomieee.EducationalType) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.lomieee.EducationalType.class, reader);
    } //-- es.pode.parseadorXML.castor.lomieee.EducationalType unmarshal(java.io.Reader) 

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
