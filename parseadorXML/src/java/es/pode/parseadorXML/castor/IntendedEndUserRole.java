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
 * Class IntendedEndUserRole.
 * 
 * @version $Revision$ $Date$
 */
public class IntendedEndUserRole implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _groupIntendedEndUserRoleIntendedEndUserRole.
     */
    private es.pode.parseadorXML.castor.GroupIntendedEndUserRoleIntendedEndUserRole _groupIntendedEndUserRoleIntendedEndUserRole;


      //----------------/
     //- Constructors -/
    //----------------/

    public IntendedEndUserRole() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field
     * 'groupIntendedEndUserRoleIntendedEndUserRole'.
     * 
     * @return the value of field
     * 'GroupIntendedEndUserRoleIntendedEndUserRole'.
     */
    public es.pode.parseadorXML.castor.GroupIntendedEndUserRoleIntendedEndUserRole getGroupIntendedEndUserRoleIntendedEndUserRole(
    ) {
        return this._groupIntendedEndUserRoleIntendedEndUserRole;
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
     * Sets the value of field
     * 'groupIntendedEndUserRoleIntendedEndUserRole'.
     * 
     * @param groupIntendedEndUserRoleIntendedEndUserRole the value
     * of field 'groupIntendedEndUserRoleIntendedEndUserRole'.
     */
    public void setGroupIntendedEndUserRoleIntendedEndUserRole(
            final es.pode.parseadorXML.castor.GroupIntendedEndUserRoleIntendedEndUserRole groupIntendedEndUserRoleIntendedEndUserRole) {
        this._groupIntendedEndUserRoleIntendedEndUserRole = groupIntendedEndUserRoleIntendedEndUserRole;
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
     * es.pode.parseadorXML.castor.IntendedEndUserRole
     */
    public static es.pode.parseadorXML.castor.IntendedEndUserRole unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (es.pode.parseadorXML.castor.IntendedEndUserRole) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.IntendedEndUserRole.class, reader);
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
