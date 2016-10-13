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
 * Class EstadisticaType.
 * 
 * @version $Revision$ $Date$
 */
public class EstadisticaType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _codigo.
     */
    private java.lang.String _codigo;

    /**
     * Field _descripcion.
     */
    private java.lang.String _descripcion;

    /**
     * Field _valor.
     */
    private java.lang.String _valor;

    /**
     * Field _valorTotal.
     */
    private java.lang.String _valorTotal;

    /**
     * Field _nodosEstadisticaTotal.
     */
    private es.pode.buscar.negocio.administrar.estadisticas.castor.NodosEstadisticaTotal _nodosEstadisticaTotal;


      //----------------/
     //- Constructors -/
    //----------------/

    public EstadisticaType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'codigo'.
     * 
     * @return the value of field 'Codigo'.
     */
    public java.lang.String getCodigo(
    ) {
        return this._codigo;
    }

    /**
     * Returns the value of field 'descripcion'.
     * 
     * @return the value of field 'Descripcion'.
     */
    public java.lang.String getDescripcion(
    ) {
        return this._descripcion;
    }

    /**
     * Returns the value of field 'nodosEstadisticaTotal'.
     * 
     * @return the value of field 'NodosEstadisticaTotal'.
     */
    public es.pode.buscar.negocio.administrar.estadisticas.castor.NodosEstadisticaTotal getNodosEstadisticaTotal(
    ) {
        return this._nodosEstadisticaTotal;
    }

    /**
     * Returns the value of field 'valor'.
     * 
     * @return the value of field 'Valor'.
     */
    public java.lang.String getValor(
    ) {
        return this._valor;
    }

    /**
     * Returns the value of field 'valorTotal'.
     * 
     * @return the value of field 'ValorTotal'.
     */
    public java.lang.String getValorTotal(
    ) {
        return this._valorTotal;
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
     * Sets the value of field 'codigo'.
     * 
     * @param codigo the value of field 'codigo'.
     */
    public void setCodigo(
            final java.lang.String codigo) {
        this._codigo = codigo;
    }

    /**
     * Sets the value of field 'descripcion'.
     * 
     * @param descripcion the value of field 'descripcion'.
     */
    public void setDescripcion(
            final java.lang.String descripcion) {
        this._descripcion = descripcion;
    }

    /**
     * Sets the value of field 'nodosEstadisticaTotal'.
     * 
     * @param nodosEstadisticaTotal the value of field
     * 'nodosEstadisticaTotal'.
     */
    public void setNodosEstadisticaTotal(
            final es.pode.buscar.negocio.administrar.estadisticas.castor.NodosEstadisticaTotal nodosEstadisticaTotal) {
        this._nodosEstadisticaTotal = nodosEstadisticaTotal;
    }

    /**
     * Sets the value of field 'valor'.
     * 
     * @param valor the value of field 'valor'.
     */
    public void setValor(
            final java.lang.String valor) {
        this._valor = valor;
    }

    /**
     * Sets the value of field 'valorTotal'.
     * 
     * @param valorTotal the value of field 'valorTotal'.
     */
    public void setValorTotal(
            final java.lang.String valorTotal) {
        this._valorTotal = valorTotal;
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
     * es.pode.buscar.negocio.administrar.estadisticas.castor.EstadisticaType
     */
    public static es.pode.buscar.negocio.administrar.estadisticas.castor.EstadisticaType unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (es.pode.buscar.negocio.administrar.estadisticas.castor.EstadisticaType) Unmarshaller.unmarshal(es.pode.buscar.negocio.administrar.estadisticas.castor.EstadisticaType.class, reader);
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
