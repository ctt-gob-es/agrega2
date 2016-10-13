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
 * Class NodoEstadisticaType.
 * 
 * @version $Revision$ $Date$
 */
public class NodoEstadisticaType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _nombreNodoEstadistica.
     */
    private java.lang.String _nombreNodoEstadistica;

    /**
     * Field _valorNodoEstadistica.
     */
    private java.lang.String _valorNodoEstadistica;


      //----------------/
     //- Constructors -/
    //----------------/

    public NodoEstadisticaType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'nombreNodoEstadistica'.
     * 
     * @return the value of field 'NombreNodoEstadistica'.
     */
    public java.lang.String getNombreNodoEstadistica(
    ) {
        return this._nombreNodoEstadistica;
    }

    /**
     * Returns the value of field 'valorNodoEstadistica'.
     * 
     * @return the value of field 'ValorNodoEstadistica'.
     */
    public java.lang.String getValorNodoEstadistica(
    ) {
        return this._valorNodoEstadistica;
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
     * Sets the value of field 'nombreNodoEstadistica'.
     * 
     * @param nombreNodoEstadistica the value of field
     * 'nombreNodoEstadistica'.
     */
    public void setNombreNodoEstadistica(
            final java.lang.String nombreNodoEstadistica) {
        this._nombreNodoEstadistica = nombreNodoEstadistica;
    }

    /**
     * Sets the value of field 'valorNodoEstadistica'.
     * 
     * @param valorNodoEstadistica the value of field
     * 'valorNodoEstadistica'.
     */
    public void setValorNodoEstadistica(
            final java.lang.String valorNodoEstadistica) {
        this._valorNodoEstadistica = valorNodoEstadistica;
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
     * es.pode.buscar.negocio.administrar.estadisticas.castor.NodoEstadisticaType
     */
    public static es.pode.buscar.negocio.administrar.estadisticas.castor.NodoEstadisticaType unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (es.pode.buscar.negocio.administrar.estadisticas.castor.NodoEstadisticaType) Unmarshaller.unmarshal(es.pode.buscar.negocio.administrar.estadisticas.castor.NodoEstadisticaType.class, reader);
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
