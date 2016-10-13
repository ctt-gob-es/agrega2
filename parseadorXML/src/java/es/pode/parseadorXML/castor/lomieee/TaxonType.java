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
 * Class TaxonType.
 * 
 * @version $Revision$ $Date$
 */
public class TaxonType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _id
     */
    private java.lang.String _id;

    /**
     * Field _entry
     */
    private es.pode.parseadorXML.castor.lomieee.Entry _entry;

    /**
     * Field _taxon
     */
    private es.pode.parseadorXML.castor.lomieee.Taxon _taxon;


      //----------------/
     //- Constructors -/
    //----------------/

    public TaxonType() 
     {
        super();
    } //-- es.pode.parseadorXML.castor.lomieee.TaxonType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'entry'.
     * 
     * @return the value of field 'Entry'.
     */
    public es.pode.parseadorXML.castor.lomieee.Entry getEntry()
    {
        return this._entry;
    } //-- es.pode.parseadorXML.castor.lomieee.Entry getEntry() 

    /**
     * Returns the value of field 'id'.
     * 
     * @return the value of field 'Id'.
     */
    public java.lang.String getId()
    {
        return this._id;
    } //-- java.lang.String getId() 

    /**
     * Returns the value of field 'taxon'.
     * 
     * @return the value of field 'Taxon'.
     */
    public es.pode.parseadorXML.castor.lomieee.Taxon getTaxon()
    {
        return this._taxon;
    } //-- es.pode.parseadorXML.castor.lomieee.Taxon getTaxon() 

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
     * Sets the value of field 'entry'.
     * 
     * @param entry the value of field 'entry'.
     */
    public void setEntry(es.pode.parseadorXML.castor.lomieee.Entry entry)
    {
        this._entry = entry;
    } //-- void setEntry(es.pode.parseadorXML.castor.lomieee.Entry) 

    /**
     * Sets the value of field 'id'.
     * 
     * @param id the value of field 'id'.
     */
    public void setId(java.lang.String id)
    {
        this._id = id;
    } //-- void setId(java.lang.String) 

    /**
     * Sets the value of field 'taxon'.
     * 
     * @param taxon the value of field 'taxon'.
     */
    public void setTaxon(es.pode.parseadorXML.castor.lomieee.Taxon taxon)
    {
        this._taxon = taxon;
    } //-- void setTaxon(es.pode.parseadorXML.castor.lomieee.Taxon) 

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
     * es.pode.parseadorXML.castor.lomieee.TaxonType
     */
    public static es.pode.parseadorXML.castor.lomieee.TaxonType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (es.pode.parseadorXML.castor.lomieee.TaxonType) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.lomieee.TaxonType.class, reader);
    } //-- es.pode.parseadorXML.castor.lomieee.TaxonType unmarshal(java.io.Reader) 

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
