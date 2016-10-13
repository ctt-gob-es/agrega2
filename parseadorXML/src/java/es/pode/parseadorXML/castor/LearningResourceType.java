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
 * Class LearningResourceType.
 * 
 * @version $Revision$ $Date$
 */
public class LearningResourceType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _groupLearningResourceTypeLearningResourceType.
     */
    private es.pode.parseadorXML.castor.GroupLearningResourceTypeLearningResourceType _groupLearningResourceTypeLearningResourceType;


      //----------------/
     //- Constructors -/
    //----------------/

    public LearningResourceType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field
     * 'groupLearningResourceTypeLearningResourceType'.
     * 
     * @return the value of field
     * 'GroupLearningResourceTypeLearningResourceType'.
     */
    public es.pode.parseadorXML.castor.GroupLearningResourceTypeLearningResourceType getGroupLearningResourceTypeLearningResourceType(
    ) {
        return this._groupLearningResourceTypeLearningResourceType;
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
     * 'groupLearningResourceTypeLearningResourceType'.
     * 
     * @param groupLearningResourceTypeLearningResourceType the
     * value of field
     * 'groupLearningResourceTypeLearningResourceType'.
     */
    public void setGroupLearningResourceTypeLearningResourceType(
            final es.pode.parseadorXML.castor.GroupLearningResourceTypeLearningResourceType groupLearningResourceTypeLearningResourceType) {
        this._groupLearningResourceTypeLearningResourceType = groupLearningResourceTypeLearningResourceType;
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
     * es.pode.parseadorXML.castor.LearningResourceType
     */
    public static es.pode.parseadorXML.castor.LearningResourceType unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (es.pode.parseadorXML.castor.LearningResourceType) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.LearningResourceType.class, reader);
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
