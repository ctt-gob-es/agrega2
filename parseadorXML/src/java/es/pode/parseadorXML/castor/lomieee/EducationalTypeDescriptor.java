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

/**
 * Class EducationalTypeDescriptor.
 * 
 * @version $Revision$ $Date$
 */
public class EducationalTypeDescriptor extends org.exolab.castor.xml.util.XMLClassDescriptorImpl {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field elementDefinition
     */
    private boolean elementDefinition;

    /**
     * Field nsPrefix
     */
    private java.lang.String nsPrefix;

    /**
     * Field nsURI
     */
    private java.lang.String nsURI;

    /**
     * Field xmlName
     */
    private java.lang.String xmlName;

    /**
     * Field identity
     */
    private org.exolab.castor.xml.XMLFieldDescriptor identity;


      //----------------/
     //- Constructors -/
    //----------------/

    public EducationalTypeDescriptor() 
     {
        super();
        nsURI = "http://www.imsglobal.org/xsd/imsmd_rootv1p2p1";
        xmlName = "educationalType";
        elementDefinition = false;
        
        //-- set grouping compositor
        setCompositorAsSequence();
        org.exolab.castor.xml.util.XMLFieldDescriptorImpl  desc           = null;
        org.exolab.castor.mapping.FieldHandler             handler        = null;
        org.exolab.castor.xml.FieldValidator               fieldValidator = null;
        //-- _content
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_content", "PCDATA", org.exolab.castor.xml.NodeType.Text);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                EducationalType target = (EducationalType) object;
                return target.getContent();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    EducationalType target = (EducationalType) object;
                    target.setContent( (java.lang.String) value);
                }
                catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance( java.lang.Object parent ) {
                return null;
            }
        };
        desc.setHandler(handler);
        addFieldDescriptor(desc);
        
        //-- validation code for: _content
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            typeValidator.setWhiteSpace("preserve");
            fieldValidator.setValidator(typeValidator);
        }
        desc.setValidator(fieldValidator);
        //-- initialize attribute descriptors
        
        //-- initialize element descriptors
        
        //-- _interactivitytype
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(es.pode.parseadorXML.castor.lomieee.Interactivitytype.class, "_interactivitytype", "interactivitytype", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                EducationalType target = (EducationalType) object;
                return target.getInteractivitytype();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    EducationalType target = (EducationalType) object;
                    target.setInteractivitytype( (es.pode.parseadorXML.castor.lomieee.Interactivitytype) value);
                }
                catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance( java.lang.Object parent ) {
                return new es.pode.parseadorXML.castor.lomieee.Interactivitytype();
            }
        };
        desc.setHandler(handler);
        desc.setNameSpaceURI("http://www.imsglobal.org/xsd/imsmd_rootv1p2p1");
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        
        //-- validation code for: _interactivitytype
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
        //-- _learningresourcetypeList
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(es.pode.parseadorXML.castor.lomieee.Learningresourcetype.class, "_learningresourcetypeList", "learningresourcetype", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                EducationalType target = (EducationalType) object;
                return target.getLearningresourcetype();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    EducationalType target = (EducationalType) object;
                    target.addLearningresourcetype( (es.pode.parseadorXML.castor.lomieee.Learningresourcetype) value);
                }
                catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public void resetValue(Object object) throws IllegalStateException, IllegalArgumentException {
                try {
                    EducationalType target = (EducationalType) object;
                    target.removeAllLearningresourcetype();
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance( java.lang.Object parent ) {
                return new es.pode.parseadorXML.castor.lomieee.Learningresourcetype();
            }
        };
        desc.setHandler(handler);
        desc.setNameSpaceURI("http://www.imsglobal.org/xsd/imsmd_rootv1p2p1");
        desc.setMultivalued(true);
        addFieldDescriptor(desc);
        
        //-- validation code for: _learningresourcetypeList
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(0);
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
        //-- _interactivitylevel
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(es.pode.parseadorXML.castor.lomieee.Interactivitylevel.class, "_interactivitylevel", "interactivitylevel", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                EducationalType target = (EducationalType) object;
                return target.getInteractivitylevel();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    EducationalType target = (EducationalType) object;
                    target.setInteractivitylevel( (es.pode.parseadorXML.castor.lomieee.Interactivitylevel) value);
                }
                catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance( java.lang.Object parent ) {
                return new es.pode.parseadorXML.castor.lomieee.Interactivitylevel();
            }
        };
        desc.setHandler(handler);
        desc.setNameSpaceURI("http://www.imsglobal.org/xsd/imsmd_rootv1p2p1");
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        
        //-- validation code for: _interactivitylevel
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
        //-- _semanticdensity
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(es.pode.parseadorXML.castor.lomieee.Semanticdensity.class, "_semanticdensity", "semanticdensity", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                EducationalType target = (EducationalType) object;
                return target.getSemanticdensity();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    EducationalType target = (EducationalType) object;
                    target.setSemanticdensity( (es.pode.parseadorXML.castor.lomieee.Semanticdensity) value);
                }
                catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance( java.lang.Object parent ) {
                return new es.pode.parseadorXML.castor.lomieee.Semanticdensity();
            }
        };
        desc.setHandler(handler);
        desc.setNameSpaceURI("http://www.imsglobal.org/xsd/imsmd_rootv1p2p1");
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        
        //-- validation code for: _semanticdensity
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
        //-- _intendedenduserroleList
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(es.pode.parseadorXML.castor.lomieee.Intendedenduserrole.class, "_intendedenduserroleList", "intendedenduserrole", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                EducationalType target = (EducationalType) object;
                return target.getIntendedenduserrole();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    EducationalType target = (EducationalType) object;
                    target.addIntendedenduserrole( (es.pode.parseadorXML.castor.lomieee.Intendedenduserrole) value);
                }
                catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public void resetValue(Object object) throws IllegalStateException, IllegalArgumentException {
                try {
                    EducationalType target = (EducationalType) object;
                    target.removeAllIntendedenduserrole();
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance( java.lang.Object parent ) {
                return new es.pode.parseadorXML.castor.lomieee.Intendedenduserrole();
            }
        };
        desc.setHandler(handler);
        desc.setNameSpaceURI("http://www.imsglobal.org/xsd/imsmd_rootv1p2p1");
        desc.setMultivalued(true);
        addFieldDescriptor(desc);
        
        //-- validation code for: _intendedenduserroleList
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(0);
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
        //-- _contextList
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(es.pode.parseadorXML.castor.lomieee.Context.class, "_contextList", "context", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                EducationalType target = (EducationalType) object;
                return target.getContext();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    EducationalType target = (EducationalType) object;
                    target.addContext( (es.pode.parseadorXML.castor.lomieee.Context) value);
                }
                catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public void resetValue(Object object) throws IllegalStateException, IllegalArgumentException {
                try {
                    EducationalType target = (EducationalType) object;
                    target.removeAllContext();
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance( java.lang.Object parent ) {
                return new es.pode.parseadorXML.castor.lomieee.Context();
            }
        };
        desc.setHandler(handler);
        desc.setNameSpaceURI("http://www.imsglobal.org/xsd/imsmd_rootv1p2p1");
        desc.setMultivalued(true);
        addFieldDescriptor(desc);
        
        //-- validation code for: _contextList
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(0);
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
        //-- _typicalagerangeList
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(es.pode.parseadorXML.castor.lomieee.Typicalagerange.class, "_typicalagerangeList", "typicalagerange", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                EducationalType target = (EducationalType) object;
                return target.getTypicalagerange();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    EducationalType target = (EducationalType) object;
                    target.addTypicalagerange( (es.pode.parseadorXML.castor.lomieee.Typicalagerange) value);
                }
                catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public void resetValue(Object object) throws IllegalStateException, IllegalArgumentException {
                try {
                    EducationalType target = (EducationalType) object;
                    target.removeAllTypicalagerange();
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance( java.lang.Object parent ) {
                return new es.pode.parseadorXML.castor.lomieee.Typicalagerange();
            }
        };
        desc.setHandler(handler);
        desc.setNameSpaceURI("http://www.imsglobal.org/xsd/imsmd_rootv1p2p1");
        desc.setMultivalued(true);
        addFieldDescriptor(desc);
        
        //-- validation code for: _typicalagerangeList
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(0);
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
        //-- _difficulty
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(es.pode.parseadorXML.castor.lomieee.Difficulty.class, "_difficulty", "difficulty", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                EducationalType target = (EducationalType) object;
                return target.getDifficulty();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    EducationalType target = (EducationalType) object;
                    target.setDifficulty( (es.pode.parseadorXML.castor.lomieee.Difficulty) value);
                }
                catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance( java.lang.Object parent ) {
                return new es.pode.parseadorXML.castor.lomieee.Difficulty();
            }
        };
        desc.setHandler(handler);
        desc.setNameSpaceURI("http://www.imsglobal.org/xsd/imsmd_rootv1p2p1");
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        
        //-- validation code for: _difficulty
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
        //-- _typicallearningtime
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(es.pode.parseadorXML.castor.lomieee.Typicallearningtime.class, "_typicallearningtime", "typicallearningtime", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                EducationalType target = (EducationalType) object;
                return target.getTypicallearningtime();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    EducationalType target = (EducationalType) object;
                    target.setTypicallearningtime( (es.pode.parseadorXML.castor.lomieee.Typicallearningtime) value);
                }
                catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance( java.lang.Object parent ) {
                return new es.pode.parseadorXML.castor.lomieee.Typicallearningtime();
            }
        };
        desc.setHandler(handler);
        desc.setNameSpaceURI("http://www.imsglobal.org/xsd/imsmd_rootv1p2p1");
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        
        //-- validation code for: _typicallearningtime
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
        //-- _description
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(es.pode.parseadorXML.castor.lomieee.Description.class, "_description", "description", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                EducationalType target = (EducationalType) object;
                return target.getDescription();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    EducationalType target = (EducationalType) object;
                    target.setDescription( (es.pode.parseadorXML.castor.lomieee.Description) value);
                }
                catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance( java.lang.Object parent ) {
                return new es.pode.parseadorXML.castor.lomieee.Description();
            }
        };
        desc.setHandler(handler);
        desc.setNameSpaceURI("http://www.imsglobal.org/xsd/imsmd_rootv1p2p1");
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        
        //-- validation code for: _description
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
        //-- _languageList
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_languageList", "language", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                EducationalType target = (EducationalType) object;
                return target.getLanguage();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    EducationalType target = (EducationalType) object;
                    target.addLanguage( (java.lang.String) value);
                }
                catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public void resetValue(Object object) throws IllegalStateException, IllegalArgumentException {
                try {
                    EducationalType target = (EducationalType) object;
                    target.removeAllLanguage();
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance( java.lang.Object parent ) {
                return null;
            }
        };
        desc.setHandler(handler);
        desc.setNameSpaceURI("http://www.imsglobal.org/xsd/imsmd_rootv1p2p1");
        desc.setMultivalued(true);
        addFieldDescriptor(desc);
        
        //-- validation code for: _languageList
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(0);
        { //-- local scope
            org.exolab.castor.xml.validators.StringValidator typeValidator = new org.exolab.castor.xml.validators.StringValidator();
            typeValidator.setWhiteSpace("preserve");
            fieldValidator.setValidator(typeValidator);
        }
        desc.setValidator(fieldValidator);
        //-- _grp_any
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(es.pode.parseadorXML.castor.lomieee.Grp_any.class, "_grp_any", "-error-if-this-is-used-", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                EducationalType target = (EducationalType) object;
                return target.getGrp_any();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    EducationalType target = (EducationalType) object;
                    target.setGrp_any( (es.pode.parseadorXML.castor.lomieee.Grp_any) value);
                }
                catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance( java.lang.Object parent ) {
                return new es.pode.parseadorXML.castor.lomieee.Grp_any();
            }
        };
        desc.setHandler(handler);
        desc.setContainer(true);
        desc.setClassDescriptor(new es.pode.parseadorXML.castor.lomieee.Grp_anyDescriptor());
        desc.setNameSpaceURI("http://www.imsglobal.org/xsd/imsmd_rootv1p2p1");
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        
        //-- validation code for: _grp_any
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
    } //-- es.pode.parseadorXML.castor.lomieee.EducationalTypeDescriptor()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method getAccessMode
     * 
     * 
     * 
     * @return the access mode specified for this class.
     */
    public org.exolab.castor.mapping.AccessMode getAccessMode()
    {
        return null;
    } //-- org.exolab.castor.mapping.AccessMode getAccessMode() 

    /**
     * Method getExtends
     * 
     * 
     * 
     * @return the class descriptor of the class extended by this
     * class.
     */
    public org.exolab.castor.mapping.ClassDescriptor getExtends()
    {
        return null;
    } //-- org.exolab.castor.mapping.ClassDescriptor getExtends() 

    /**
     * Method getIdentity
     * 
     * 
     * 
     * @return the identity field, null if this class has no
     * identity.
     */
    public org.exolab.castor.mapping.FieldDescriptor getIdentity()
    {
        return identity;
    } //-- org.exolab.castor.mapping.FieldDescriptor getIdentity() 

    /**
     * Method getJavaClass
     * 
     * 
     * 
     * @return the Java class represented by this descriptor.
     */
    public java.lang.Class getJavaClass()
    {
        return es.pode.parseadorXML.castor.lomieee.EducationalType.class;
    } //-- java.lang.Class getJavaClass() 

    /**
     * Method getNameSpacePrefix
     * 
     * 
     * 
     * @return the namespace prefix to use when marshalling as XML.
     */
    public java.lang.String getNameSpacePrefix()
    {
        return nsPrefix;
    } //-- java.lang.String getNameSpacePrefix() 

    /**
     * Method getNameSpaceURI
     * 
     * 
     * 
     * @return the namespace URI used when marshalling and
     * unmarshalling as XML.
     */
    public java.lang.String getNameSpaceURI()
    {
        return nsURI;
    } //-- java.lang.String getNameSpaceURI() 

    /**
     * Method getValidator
     * 
     * 
     * 
     * @return a specific validator for the class described by this
     * ClassDescriptor.
     */
    public org.exolab.castor.xml.TypeValidator getValidator()
    {
        return this;
    } //-- org.exolab.castor.xml.TypeValidator getValidator() 

    /**
     * Method getXMLName
     * 
     * 
     * 
     * @return the XML Name for the Class being described.
     */
    public java.lang.String getXMLName()
    {
        return xmlName;
    } //-- java.lang.String getXMLName() 

    /**
     * Method isElementDefinition
     * 
     * 
     * 
     * @return true if XML schema definition of this Class is that
     * of a global
     * element or element with anonymous type definition.
     */
    public boolean isElementDefinition()
    {
        return elementDefinition;
    } //-- boolean isElementDefinition() 

}
