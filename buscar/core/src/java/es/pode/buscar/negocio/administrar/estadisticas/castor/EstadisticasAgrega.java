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
 * Class EstadisticasAgrega.
 * 
 * @version $Revision$ $Date$
 */
public class EstadisticasAgrega implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _nodo.
     */
    private java.lang.String _nodo;

    /**
     * Field _fecha.
     */
    private java.lang.String _fecha;

    /**
     * Field _estadisticaList.
     */
    private java.util.Vector _estadisticaList;


      //----------------/
     //- Constructors -/
    //----------------/

    public EstadisticasAgrega() {
        super();
        this._estadisticaList = new java.util.Vector();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vEstadistica
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addEstadistica(
            final es.pode.buscar.negocio.administrar.estadisticas.castor.Estadistica vEstadistica)
    throws java.lang.IndexOutOfBoundsException {
        // check for the maximum size
        if (this._estadisticaList.size() >= 60) {
            throw new IndexOutOfBoundsException("addEstadistica has a maximum of 60");
        }
        
        this._estadisticaList.addElement(vEstadistica);
    }

    /**
     * 
     * 
     * @param index
     * @param vEstadistica
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addEstadistica(
            final int index,
            final es.pode.buscar.negocio.administrar.estadisticas.castor.Estadistica vEstadistica)
    throws java.lang.IndexOutOfBoundsException {
        // check for the maximum size
        if (this._estadisticaList.size() >= 60) {
            throw new IndexOutOfBoundsException("addEstadistica has a maximum of 60");
        }
        
        this._estadisticaList.add(index, vEstadistica);
    }

    /**
     * Method enumerateEstadistica.
     * 
     * @return an Enumeration over all
     * es.pode.buscar.negocio.administrar.estadisticas.castor.Estadistica
     * elements
     */
    public java.util.Enumeration enumerateEstadistica(
    ) {
        return this._estadisticaList.elements();
    }

    /**
     * Method getEstadistica.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * es.pode.buscar.negocio.administrar.estadisticas.castor.Estadistica
     * at the given index
     */
    public es.pode.buscar.negocio.administrar.estadisticas.castor.Estadistica getEstadistica(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._estadisticaList.size()) {
            throw new IndexOutOfBoundsException("getEstadistica: Index value '" + index + "' not in range [0.." + (this._estadisticaList.size() - 1) + "]");
        }
        
        return (es.pode.buscar.negocio.administrar.estadisticas.castor.Estadistica) _estadisticaList.get(index);
    }

    /**
     * Method getEstadistica.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public es.pode.buscar.negocio.administrar.estadisticas.castor.Estadistica[] getEstadistica(
    ) {
        es.pode.buscar.negocio.administrar.estadisticas.castor.Estadistica[] array = new es.pode.buscar.negocio.administrar.estadisticas.castor.Estadistica[0];
        return (es.pode.buscar.negocio.administrar.estadisticas.castor.Estadistica[]) this._estadisticaList.toArray(array);
    }

    /**
     * Method getEstadisticaCount.
     * 
     * @return the size of this collection
     */
    public int getEstadisticaCount(
    ) {
        return this._estadisticaList.size();
    }

    /**
     * Returns the value of field 'fecha'.
     * 
     * @return the value of field 'Fecha'.
     */
    public java.lang.String getFecha(
    ) {
        return this._fecha;
    }

    /**
     * Returns the value of field 'nodo'.
     * 
     * @return the value of field 'Nodo'.
     */
    public java.lang.String getNodo(
    ) {
        return this._nodo;
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
    public void removeAllEstadistica(
    ) {
        this._estadisticaList.clear();
    }

    /**
     * Method removeEstadistica.
     * 
     * @param vEstadistica
     * @return true if the object was removed from the collection.
     */
    public boolean removeEstadistica(
            final es.pode.buscar.negocio.administrar.estadisticas.castor.Estadistica vEstadistica) {
        boolean removed = _estadisticaList.remove(vEstadistica);
        return removed;
    }

    /**
     * Method removeEstadisticaAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public es.pode.buscar.negocio.administrar.estadisticas.castor.Estadistica removeEstadisticaAt(
            final int index) {
        java.lang.Object obj = this._estadisticaList.remove(index);
        return (es.pode.buscar.negocio.administrar.estadisticas.castor.Estadistica) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vEstadistica
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setEstadistica(
            final int index,
            final es.pode.buscar.negocio.administrar.estadisticas.castor.Estadistica vEstadistica)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._estadisticaList.size()) {
            throw new IndexOutOfBoundsException("setEstadistica: Index value '" + index + "' not in range [0.." + (this._estadisticaList.size() - 1) + "]");
        }
        
        this._estadisticaList.set(index, vEstadistica);
    }

    /**
     * 
     * 
     * @param vEstadisticaArray
     */
    public void setEstadistica(
            final es.pode.buscar.negocio.administrar.estadisticas.castor.Estadistica[] vEstadisticaArray) {
        //-- copy array
        _estadisticaList.clear();
        
        for (int i = 0; i < vEstadisticaArray.length; i++) {
                this._estadisticaList.add(vEstadisticaArray[i]);
        }
    }

    /**
     * Sets the value of field 'fecha'.
     * 
     * @param fecha the value of field 'fecha'.
     */
    public void setFecha(
            final java.lang.String fecha) {
        this._fecha = fecha;
    }

    /**
     * Sets the value of field 'nodo'.
     * 
     * @param nodo the value of field 'nodo'.
     */
    public void setNodo(
            final java.lang.String nodo) {
        this._nodo = nodo;
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
     * es.pode.buscar.negocio.administrar.estadisticas.castor.EstadisticasAgrega
     */
    public static es.pode.buscar.negocio.administrar.estadisticas.castor.EstadisticasAgrega unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (es.pode.buscar.negocio.administrar.estadisticas.castor.EstadisticasAgrega) Unmarshaller.unmarshal(es.pode.buscar.negocio.administrar.estadisticas.castor.EstadisticasAgrega.class, reader);
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
