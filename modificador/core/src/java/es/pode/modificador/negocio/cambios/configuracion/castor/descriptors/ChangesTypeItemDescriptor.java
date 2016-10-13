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

package es.pode.modificador.negocio.cambios.configuracion.castor.descriptors;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import es.pode.modificador.negocio.cambios.configuracion.castor.ChangesTypeItem;

/**
 * Class ChangesTypeItemDescriptor.
 * 
 * @version $Revision$ $Date$
 */
public class ChangesTypeItemDescriptor extends org.exolab.castor.xml.util.XMLClassDescriptorImpl {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _elementDefinition.
     */
    private boolean _elementDefinition;

    /**
     * Field _nsPrefix.
     */
    private java.lang.String _nsPrefix;

    /**
     * Field _nsURI.
     */
    private java.lang.String _nsURI;

    /**
     * Field _xmlName.
     */
    private java.lang.String _xmlName;

    /**
     * Field _identity.
     */
    private org.exolab.castor.xml.XMLFieldDescriptor _identity;


      //----------------/
     //- Constructors -/
    //----------------/

    public ChangesTypeItemDescriptor() {
        super();
        _nsURI = "Agrega";
        _xmlName = "changesType";
        _elementDefinition = false;
        
        //-- set grouping compositor
        setCompositorAsChoice();
        org.exolab.castor.xml.util.XMLFieldDescriptorImpl  desc           = null;
        org.exolab.castor.mapping.FieldHandler             handler        = null;
        org.exolab.castor.xml.FieldValidator               fieldValidator = null;
        //-- initialize attribute descriptors
        
        //-- initialize element descriptors
        
        //-- _validation
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(es.pode.modificador.negocio.cambios.configuracion.castor.Validation.class, "_validation", "validation", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ChangesTypeItem target = (ChangesTypeItem) object;
                return target.getValidation();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ChangesTypeItem target = (ChangesTypeItem) object;
                    target.setValidation( (es.pode.modificador.negocio.cambios.configuracion.castor.Validation) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return new es.pode.modificador.negocio.cambios.configuracion.castor.Validation();
            }
        };
        desc.setHandler(handler);
        desc.setNameSpaceURI("Agrega");
        desc.setRequired(true);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        
        //-- validation code for: _validation
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(1);
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
        //-- _modification
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(es.pode.modificador.negocio.cambios.configuracion.castor.Modification.class, "_modification", "modification", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ChangesTypeItem target = (ChangesTypeItem) object;
                return target.getModification();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ChangesTypeItem target = (ChangesTypeItem) object;
                    target.setModification( (es.pode.modificador.negocio.cambios.configuracion.castor.Modification) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return new es.pode.modificador.negocio.cambios.configuracion.castor.Modification();
            }
        };
        desc.setHandler(handler);
        desc.setNameSpaceURI("Agrega");
        desc.setRequired(true);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        
        //-- validation code for: _modification
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(1);
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
        //-- _addition
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(es.pode.modificador.negocio.cambios.configuracion.castor.Addition.class, "_addition", "addition", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ChangesTypeItem target = (ChangesTypeItem) object;
                return target.getAddition();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ChangesTypeItem target = (ChangesTypeItem) object;
                    target.setAddition( (es.pode.modificador.negocio.cambios.configuracion.castor.Addition) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return new es.pode.modificador.negocio.cambios.configuracion.castor.Addition();
            }
        };
        desc.setHandler(handler);
        desc.setNameSpaceURI("Agrega");
        desc.setRequired(true);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        
        //-- validation code for: _addition
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(1);
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
        //-- _check
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(es.pode.modificador.negocio.cambios.configuracion.castor.Check.class, "_check", "check", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ChangesTypeItem target = (ChangesTypeItem) object;
                return target.getCheck();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ChangesTypeItem target = (ChangesTypeItem) object;
                    target.setCheck( (es.pode.modificador.negocio.cambios.configuracion.castor.Check) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return new es.pode.modificador.negocio.cambios.configuracion.castor.Check();
            }
        };
        desc.setHandler(handler);
        desc.setNameSpaceURI("Agrega");
        desc.setRequired(true);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        
        //-- validation code for: _check
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(1);
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
        //-- _removal
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(es.pode.modificador.negocio.cambios.configuracion.castor.Removal.class, "_removal", "removal", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ChangesTypeItem target = (ChangesTypeItem) object;
                return target.getRemoval();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ChangesTypeItem target = (ChangesTypeItem) object;
                    target.setRemoval( (es.pode.modificador.negocio.cambios.configuracion.castor.Removal) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return new es.pode.modificador.negocio.cambios.configuracion.castor.Removal();
            }
        };
        desc.setHandler(handler);
        desc.setNameSpaceURI("Agrega");
        desc.setRequired(true);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        
        //-- validation code for: _removal
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(1);
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
        //-- _export
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(es.pode.modificador.negocio.cambios.configuracion.castor.Export.class, "_export", "export", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ChangesTypeItem target = (ChangesTypeItem) object;
                return target.getExport();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ChangesTypeItem target = (ChangesTypeItem) object;
                    target.setExport( (es.pode.modificador.negocio.cambios.configuracion.castor.Export) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return new es.pode.modificador.negocio.cambios.configuracion.castor.Export();
            }
        };
        desc.setHandler(handler);
        desc.setNameSpaceURI("Agrega");
        desc.setRequired(true);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        
        //-- validation code for: _export
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(1);
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
        //-- _transformation
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(es.pode.modificador.negocio.cambios.configuracion.castor.Transformation.class, "_transformation", "transformation", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ChangesTypeItem target = (ChangesTypeItem) object;
                return target.getTransformation();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ChangesTypeItem target = (ChangesTypeItem) object;
                    target.setTransformation( (es.pode.modificador.negocio.cambios.configuracion.castor.Transformation) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return new es.pode.modificador.negocio.cambios.configuracion.castor.Transformation();
            }
        };
        desc.setHandler(handler);
        desc.setNameSpaceURI("Agrega");
        desc.setRequired(true);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        
        //-- validation code for: _transformation
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(1);
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
        //-- _metadata
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(es.pode.modificador.negocio.cambios.configuracion.castor.Metadata.class, "_metadata", "metadata", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                ChangesTypeItem target = (ChangesTypeItem) object;
                return target.getMetadata();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    ChangesTypeItem target = (ChangesTypeItem) object;
                    target.setMetadata( (es.pode.modificador.negocio.cambios.configuracion.castor.Metadata) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance(java.lang.Object parent) {
                return new es.pode.modificador.negocio.cambios.configuracion.castor.Metadata();
            }
        };
        desc.setHandler(handler);
        desc.setNameSpaceURI("Agrega");
        desc.setRequired(true);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        
        //-- validation code for: _metadata
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(1);
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method getAccessMode.
     * 
     * @return the access mode specified for this class.
     */
    public org.exolab.castor.mapping.AccessMode getAccessMode(
    ) {
        return null;
    }

    /**
     * Method getIdentity.
     * 
     * @return the identity field, null if this class has no
     * identity.
     */
    public org.exolab.castor.mapping.FieldDescriptor getIdentity(
    ) {
        return _identity;
    }

    /**
     * Method getJavaClass.
     * 
     * @return the Java class represented by this descriptor.
     */
    public java.lang.Class getJavaClass(
    ) {
        return es.pode.modificador.negocio.cambios.configuracion.castor.ChangesTypeItem.class;
    }

    /**
     * Method getNameSpacePrefix.
     * 
     * @return the namespace prefix to use when marshaling as XML.
     */
    public java.lang.String getNameSpacePrefix(
    ) {
        return _nsPrefix;
    }

    /**
     * Method getNameSpaceURI.
     * 
     * @return the namespace URI used when marshaling and
     * unmarshaling as XML.
     */
    public java.lang.String getNameSpaceURI(
    ) {
        return _nsURI;
    }

    /**
     * Method getValidator.
     * 
     * @return a specific validator for the class described by this
     * ClassDescriptor.
     */
    public org.exolab.castor.xml.TypeValidator getValidator(
    ) {
        return this;
    }

    /**
     * Method getXMLName.
     * 
     * @return the XML Name for the Class being described.
     */
    public java.lang.String getXMLName(
    ) {
        return _xmlName;
    }

    /**
     * Method isElementDefinition.
     * 
     * @return true if XML schema definition of this Class is that
     * of a global
     * element or element with anonymous type definition.
     */
    public boolean isElementDefinition(
    ) {
        return _elementDefinition;
    }

}
