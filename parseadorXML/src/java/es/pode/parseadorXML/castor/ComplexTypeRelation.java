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
 * Class ComplexTypeRelation.
 * 
 * @version $Revision$ $Date$
 */
public class ComplexTypeRelation implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _kind.
     */
    private es.pode.parseadorXML.castor.Kind _kind;

    /**
     * Field _resource.
     */
    private es.pode.parseadorXML.castor.ResourceLomes _resource;


      //----------------/
     //- Constructors -/
    //----------------/

    public ComplexTypeRelation() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'kind'.
     * 
     * @return the value of field 'Kind'.
     */
    public es.pode.parseadorXML.castor.Kind getKind(
    ) {
        return this._kind;
    }

    /**
     * Returns the value of field 'resource'.
     * 
     * @return the value of field 'ResourceLomes'.
     */
    public es.pode.parseadorXML.castor.ResourceLomes getResource(
    ) {
        return this._resource;
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
     * Sets the value of field 'kind'.
     * 
     * @param kind the value of field 'kind'.
     */
    public void setKind(
            final es.pode.parseadorXML.castor.Kind kind) {
        this._kind = kind;
    }

    /**
     * Sets the value of field 'resource'.
     * 
     * @param resource the value of field 'resource'.
     */
    public void setResource(
            final es.pode.parseadorXML.castor.ResourceLomes resource) {
        this._resource = resource;
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
     * es.pode.parseadorXML.castor.ComplexTypeRelation
     */
    public static es.pode.parseadorXML.castor.ComplexTypeRelation unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (es.pode.parseadorXML.castor.ComplexTypeRelation) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.ComplexTypeRelation.class, reader);
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
