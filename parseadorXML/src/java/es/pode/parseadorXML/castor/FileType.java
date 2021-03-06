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
 * Class FileType.
 * 
 * @version $Revision$ $Date$
 */
public class FileType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _href.
     */
    private java.lang.String _href;

    /**
     * Field _metadata.
     */
    private es.pode.parseadorXML.castor.Metadata _metadata;

    /**
     * Field _grp_any.
     */
    private es.pode.parseadorXML.castor.Grp_any _grp_any;


      //----------------/
     //- Constructors -/
    //----------------/

    public FileType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'grp_any'.
     * 
     * @return the value of field 'Grp_any'.
     */
    public es.pode.parseadorXML.castor.Grp_any getGrp_any(
    ) {
        return this._grp_any;
    }

    /**
     * Returns the value of field 'href'.
     * 
     * @return the value of field 'Href'.
     */
    public java.lang.String getHref(
    ) {
        return this._href;
    }

    /**
     * Returns the value of field 'metadata'.
     * 
     * @return the value of field 'Metadata'.
     */
    public es.pode.parseadorXML.castor.Metadata getMetadata(
    ) {
        return this._metadata;
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
     * Sets the value of field 'grp_any'.
     * 
     * @param grp_any the value of field 'grp_any'.
     */
    public void setGrp_any(
            final es.pode.parseadorXML.castor.Grp_any grp_any) {
        this._grp_any = grp_any;
    }

    /**
     * Sets the value of field 'href'.
     * 
     * @param href the value of field 'href'.
     */
    public void setHref(
            final java.lang.String href) {
        this._href = href;
    }

    /**
     * Sets the value of field 'metadata'.
     * 
     * @param metadata the value of field 'metadata'.
     */
    public void setMetadata(
            final es.pode.parseadorXML.castor.Metadata metadata) {
        this._metadata = metadata;
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
     * es.pode.parseadorXML.castor.FileType
     */
    public static es.pode.parseadorXML.castor.FileType unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (es.pode.parseadorXML.castor.FileType) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.FileType.class, reader);
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
