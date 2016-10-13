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
 * Class SemanticDensityVocab.
 * 
 * @version $Revision$ $Date$
 */
public class SemanticDensityVocab implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _complexTypeSemanticDensityVocabSource.
     */
    private es.pode.parseadorXML.castor.ComplexTypeSemanticDensityVocabSource _complexTypeSemanticDensityVocabSource;

    /**
     * Field _complexTypeSemanticDensityVocabValue.
     */
    private es.pode.parseadorXML.castor.ComplexTypeSemanticDensityVocabValue _complexTypeSemanticDensityVocabValue;


      //----------------/
     //- Constructors -/
    //----------------/

    public SemanticDensityVocab() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field
     * 'complexTypeSemanticDensityVocabSource'.
     * 
     * @return the value of field
     * 'ComplexTypeSemanticDensityVocabSource'.
     */
    public es.pode.parseadorXML.castor.ComplexTypeSemanticDensityVocabSource getComplexTypeSemanticDensityVocabSource(
    ) {
        return this._complexTypeSemanticDensityVocabSource;
    }

    /**
     * Returns the value of field
     * 'complexTypeSemanticDensityVocabValue'.
     * 
     * @return the value of field
     * 'ComplexTypeSemanticDensityVocabValue'.
     */
    public es.pode.parseadorXML.castor.ComplexTypeSemanticDensityVocabValue getComplexTypeSemanticDensityVocabValue(
    ) {
        return this._complexTypeSemanticDensityVocabValue;
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
     * 'complexTypeSemanticDensityVocabSource'.
     * 
     * @param complexTypeSemanticDensityVocabSource the value of
     * field 'complexTypeSemanticDensityVocabSource'.
     */
    public void setComplexTypeSemanticDensityVocabSource(
            final es.pode.parseadorXML.castor.ComplexTypeSemanticDensityVocabSource complexTypeSemanticDensityVocabSource) {
        this._complexTypeSemanticDensityVocabSource = complexTypeSemanticDensityVocabSource;
    }

    /**
     * Sets the value of field
     * 'complexTypeSemanticDensityVocabValue'.
     * 
     * @param complexTypeSemanticDensityVocabValue the value of
     * field 'complexTypeSemanticDensityVocabValue'.
     */
    public void setComplexTypeSemanticDensityVocabValue(
            final es.pode.parseadorXML.castor.ComplexTypeSemanticDensityVocabValue complexTypeSemanticDensityVocabValue) {
        this._complexTypeSemanticDensityVocabValue = complexTypeSemanticDensityVocabValue;
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
     * es.pode.parseadorXML.castor.SemanticDensityVocab
     */
    public static es.pode.parseadorXML.castor.SemanticDensityVocab unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (es.pode.parseadorXML.castor.SemanticDensityVocab) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.SemanticDensityVocab.class, reader);
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
