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
 * Class TaxonpathType.
 * 
 * @version $Revision$ $Date$
 */
public class TaxonpathType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _source
     */
    private es.pode.parseadorXML.castor.lomieee.Source _source;

    /**
     * Field _taxon
     */
    private es.pode.parseadorXML.castor.lomieee.Taxon _taxon;


      //----------------/
     //- Constructors -/
    //----------------/

    public TaxonpathType() 
     {
        super();
    } //-- es.pode.parseadorXML.castor.lomieee.TaxonpathType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'source'.
     * 
     * @return the value of field 'Source'.
     */
    public es.pode.parseadorXML.castor.lomieee.Source getSource()
    {
        return this._source;
    } //-- es.pode.parseadorXML.castor.lomieee.Source getSource() 

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
     * Sets the value of field 'source'.
     * 
     * @param source the value of field 'source'.
     */
    public void setSource(es.pode.parseadorXML.castor.lomieee.Source source)
    {
        this._source = source;
    } //-- void setSource(es.pode.parseadorXML.castor.lomieee.Source) 

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
     * es.pode.parseadorXML.castor.lomieee.TaxonpathType
     */
    public static es.pode.parseadorXML.castor.lomieee.TaxonpathType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (es.pode.parseadorXML.castor.lomieee.TaxonpathType) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.lomieee.TaxonpathType.class, reader);
    } //-- es.pode.parseadorXML.castor.lomieee.TaxonpathType unmarshal(java.io.Reader) 

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
