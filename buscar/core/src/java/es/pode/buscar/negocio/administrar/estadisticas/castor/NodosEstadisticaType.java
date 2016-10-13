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

package es.pode.buscar.negocio.administrar.estadisticas.castor;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class NodosEstadisticaType.
 * 
 * @version $Revision$ $Date$
 */
public class NodosEstadisticaType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _nodoEstadisticaList.
     */
    private java.util.Vector _nodoEstadisticaList;


      //----------------/
     //- Constructors -/
    //----------------/

    public NodosEstadisticaType() {
        super();
        this._nodoEstadisticaList = new java.util.Vector();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vNodoEstadistica
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addNodoEstadistica(
            final es.pode.buscar.negocio.administrar.estadisticas.castor.NodoEstadistica vNodoEstadistica)
    throws java.lang.IndexOutOfBoundsException {
        // check for the maximum size
        if (this._nodoEstadisticaList.size() >= 20) {
            throw new IndexOutOfBoundsException("addNodoEstadistica has a maximum of 20");
        }
        
        this._nodoEstadisticaList.addElement(vNodoEstadistica);
    }

    /**
     * 
     * 
     * @param index
     * @param vNodoEstadistica
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addNodoEstadistica(
            final int index,
            final es.pode.buscar.negocio.administrar.estadisticas.castor.NodoEstadistica vNodoEstadistica)
    throws java.lang.IndexOutOfBoundsException {
        // check for the maximum size
        if (this._nodoEstadisticaList.size() >= 20) {
            throw new IndexOutOfBoundsException("addNodoEstadistica has a maximum of 20");
        }
        
        this._nodoEstadisticaList.add(index, vNodoEstadistica);
    }

    /**
     * Method enumerateNodoEstadistica.
     * 
     * @return an Enumeration over all
     * es.pode.buscar.negocio.administrar.estadisticas.castor.NodoEstadistica
     * elements
     */
    public java.util.Enumeration enumerateNodoEstadistica(
    ) {
        return this._nodoEstadisticaList.elements();
    }

    /**
     * Method getNodoEstadistica.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * es.pode.buscar.negocio.administrar.estadisticas.castor.NodoEstadistica
     * at the given index
     */
    public es.pode.buscar.negocio.administrar.estadisticas.castor.NodoEstadistica getNodoEstadistica(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._nodoEstadisticaList.size()) {
            throw new IndexOutOfBoundsException("getNodoEstadistica: Index value '" + index + "' not in range [0.." + (this._nodoEstadisticaList.size() - 1) + "]");
        }
        
        return (es.pode.buscar.negocio.administrar.estadisticas.castor.NodoEstadistica) _nodoEstadisticaList.get(index);
    }

    /**
     * Method getNodoEstadistica.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public es.pode.buscar.negocio.administrar.estadisticas.castor.NodoEstadistica[] getNodoEstadistica(
    ) {
        es.pode.buscar.negocio.administrar.estadisticas.castor.NodoEstadistica[] array = new es.pode.buscar.negocio.administrar.estadisticas.castor.NodoEstadistica[0];
        return (es.pode.buscar.negocio.administrar.estadisticas.castor.NodoEstadistica[]) this._nodoEstadisticaList.toArray(array);
    }

    /**
     * Method getNodoEstadisticaCount.
     * 
     * @return the size of this collection
     */
    public int getNodoEstadisticaCount(
    ) {
        return this._nodoEstadisticaList.size();
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
    public void removeAllNodoEstadistica(
    ) {
        this._nodoEstadisticaList.clear();
    }

    /**
     * Method removeNodoEstadistica.
     * 
     * @param vNodoEstadistica
     * @return true if the object was removed from the collection.
     */
    public boolean removeNodoEstadistica(
            final es.pode.buscar.negocio.administrar.estadisticas.castor.NodoEstadistica vNodoEstadistica) {
        boolean removed = _nodoEstadisticaList.remove(vNodoEstadistica);
        return removed;
    }

    /**
     * Method removeNodoEstadisticaAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public es.pode.buscar.negocio.administrar.estadisticas.castor.NodoEstadistica removeNodoEstadisticaAt(
            final int index) {
        java.lang.Object obj = this._nodoEstadisticaList.remove(index);
        return (es.pode.buscar.negocio.administrar.estadisticas.castor.NodoEstadistica) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vNodoEstadistica
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setNodoEstadistica(
            final int index,
            final es.pode.buscar.negocio.administrar.estadisticas.castor.NodoEstadistica vNodoEstadistica)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._nodoEstadisticaList.size()) {
            throw new IndexOutOfBoundsException("setNodoEstadistica: Index value '" + index + "' not in range [0.." + (this._nodoEstadisticaList.size() - 1) + "]");
        }
        
        this._nodoEstadisticaList.set(index, vNodoEstadistica);
    }

    /**
     * 
     * 
     * @param vNodoEstadisticaArray
     */
    public void setNodoEstadistica(
            final es.pode.buscar.negocio.administrar.estadisticas.castor.NodoEstadistica[] vNodoEstadisticaArray) {
        //-- copy array
        _nodoEstadisticaList.clear();
        
        for (int i = 0; i < vNodoEstadisticaArray.length; i++) {
                this._nodoEstadisticaList.add(vNodoEstadisticaArray[i]);
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
     * es.pode.buscar.negocio.administrar.estadisticas.castor.NodosEstadisticaType
     */
    public static es.pode.buscar.negocio.administrar.estadisticas.castor.NodosEstadisticaType unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (es.pode.buscar.negocio.administrar.estadisticas.castor.NodosEstadisticaType) Unmarshaller.unmarshal(es.pode.buscar.negocio.administrar.estadisticas.castor.NodosEstadisticaType.class, reader);
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
