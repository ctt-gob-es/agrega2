/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1</a>, using an XML
 * Schema.
 * $Id$
 */

package es.pode.parseadorXML.castor;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class AuxiliaryResourcesType.
 * 
 * @version $Revision$ $Date$
 */
public class AuxiliaryResourcesType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _auxiliaryResourceList.
     */
    private java.util.List _auxiliaryResourceList;


      //----------------/
     //- Constructors -/
    //----------------/

    public AuxiliaryResourcesType() {
        super();
        this._auxiliaryResourceList = new java.util.ArrayList();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vAuxiliaryResource
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAuxiliaryResource(
            final es.pode.parseadorXML.castor.AuxiliaryResource vAuxiliaryResource)
    throws java.lang.IndexOutOfBoundsException {
        this._auxiliaryResourceList.add(vAuxiliaryResource);
    }

    /**
     * 
     * 
     * @param index
     * @param vAuxiliaryResource
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAuxiliaryResource(
            final int index,
            final es.pode.parseadorXML.castor.AuxiliaryResource vAuxiliaryResource)
    throws java.lang.IndexOutOfBoundsException {
        this._auxiliaryResourceList.add(index, vAuxiliaryResource);
    }

    /**
     * Method enumerateAuxiliaryResource.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration enumerateAuxiliaryResource(
    ) {
        return java.util.Collections.enumeration(this._auxiliaryResourceList);
    }

    /**
     * Method getAuxiliaryResource.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * es.pode.parseadorXML.castorAuxiliaryResource at the
     * given index
     */
    public es.pode.parseadorXML.castor.AuxiliaryResource getAuxiliaryResource(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._auxiliaryResourceList.size()) {
            throw new IndexOutOfBoundsException("getAuxiliaryResource: Index value '" + index + "' not in range [0.." + (this._auxiliaryResourceList.size() - 1) + "]");
        }
        
        return (es.pode.parseadorXML.castor.AuxiliaryResource) _auxiliaryResourceList.get(index);
    }

    /**
     * Method getAuxiliaryResource.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public es.pode.parseadorXML.castor.AuxiliaryResource[] getAuxiliaryResource(
    ) {
        es.pode.parseadorXML.castor.AuxiliaryResource[] array = new es.pode.parseadorXML.castor.AuxiliaryResource[0];
        return (es.pode.parseadorXML.castor.AuxiliaryResource[]) this._auxiliaryResourceList.toArray(array);
    }

    /**
     * Method getAuxiliaryResourceCount.
     * 
     * @return the size of this collection
     */
    public int getAuxiliaryResourceCount(
    ) {
        return this._auxiliaryResourceList.size();
    }

    /**
     * Method isValid.
     * 
     * @return true if this object is valid according to the schema
     */
    public boolean isValid(
    ) {
        try {
            validate();
        } catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    }

    /**
     * Method iterateAuxiliaryResource.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator iterateAuxiliaryResource(
    ) {
        return this._auxiliaryResourceList.iterator();
    }

    /**
     * 
     * 
     * @param out
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void marshal(
            final java.io.Writer out)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        Marshaller.marshal(this, out);
    }

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
    public void marshal(
            final org.xml.sax.ContentHandler handler)
    throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        Marshaller.marshal(this, handler);
    }

    /**
     */
    public void removeAllAuxiliaryResource(
    ) {
        this._auxiliaryResourceList.clear();
    }

    /**
     * Method removeAuxiliaryResource.
     * 
     * @param vAuxiliaryResource
     * @return true if the object was removed from the collection.
     */
    public boolean removeAuxiliaryResource(
            final es.pode.parseadorXML.castor.AuxiliaryResource vAuxiliaryResource) {
        boolean removed = _auxiliaryResourceList.remove(vAuxiliaryResource);
        return removed;
    }

    /**
     * Method removeAuxiliaryResourceAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public es.pode.parseadorXML.castor.AuxiliaryResource removeAuxiliaryResourceAt(
            final int index) {
        java.lang.Object obj = this._auxiliaryResourceList.remove(index);
        return (es.pode.parseadorXML.castor.AuxiliaryResource) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vAuxiliaryResource
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setAuxiliaryResource(
            final int index,
            final es.pode.parseadorXML.castor.AuxiliaryResource vAuxiliaryResource)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._auxiliaryResourceList.size()) {
            throw new IndexOutOfBoundsException("setAuxiliaryResource: Index value '" + index + "' not in range [0.." + (this._auxiliaryResourceList.size() - 1) + "]");
        }
        
        this._auxiliaryResourceList.set(index, vAuxiliaryResource);
    }

    /**
     * 
     * 
     * @param vAuxiliaryResourceArray
     */
    public void setAuxiliaryResource(
            final es.pode.parseadorXML.castor.AuxiliaryResource[] vAuxiliaryResourceArray) {
        //-- copy array
        _auxiliaryResourceList.clear();
        
        for (int i = 0; i < vAuxiliaryResourceArray.length; i++) {
                this._auxiliaryResourceList.add(vAuxiliaryResourceArray[i]);
        }
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * es.pode.parseadorXML.castorAuxiliaryResourcesType
     */
    public static es.pode.parseadorXML.castor.AuxiliaryResourcesType unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (es.pode.parseadorXML.castor.AuxiliaryResourcesType) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.AuxiliaryResourcesType.class, reader);
    }

    /**
     * 
     * 
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void validate(
    )
    throws org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    }

}
