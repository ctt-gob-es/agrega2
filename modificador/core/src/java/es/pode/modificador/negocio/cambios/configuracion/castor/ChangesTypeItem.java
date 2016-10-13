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

package es.pode.modificador.negocio.cambios.configuracion.castor;

/**
 * Class ChangesTypeItem.
 * 
 * @version $Revision$ $Date$
 */
public class ChangesTypeItem implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Internal choice value storage
     */
    private java.lang.Object _choiceValue;

    /**
     * Field _validation.
     */
    private es.pode.modificador.negocio.cambios.configuracion.castor.Validation _validation;

    /**
     * Field _modification.
     */
    private es.pode.modificador.negocio.cambios.configuracion.castor.Modification _modification;

    /**
     * Field _addition.
     */
    private es.pode.modificador.negocio.cambios.configuracion.castor.Addition _addition;

    /**
     * Field _check.
     */
    private es.pode.modificador.negocio.cambios.configuracion.castor.Check _check;

    /**
     * Field _removal.
     */
    private es.pode.modificador.negocio.cambios.configuracion.castor.Removal _removal;

    /**
     * Field _export.
     */
    private es.pode.modificador.negocio.cambios.configuracion.castor.Export _export;

    /**
     * Field _transformation.
     */
    private es.pode.modificador.negocio.cambios.configuracion.castor.Transformation _transformation;

    /**
     * Field _metadata.
     */
    private es.pode.modificador.negocio.cambios.configuracion.castor.Metadata _metadata;


      //----------------/
     //- Constructors -/
    //----------------/

    public ChangesTypeItem() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'addition'.
     * 
     * @return the value of field 'Addition'.
     */
    public es.pode.modificador.negocio.cambios.configuracion.castor.Addition getAddition(
    ) {
        return this._addition;
    }

    /**
     * Returns the value of field 'check'.
     * 
     * @return the value of field 'Check'.
     */
    public es.pode.modificador.negocio.cambios.configuracion.castor.Check getCheck(
    ) {
        return this._check;
    }

    /**
     * Returns the value of field 'choiceValue'. The field
     * 'choiceValue' has the following description: Internal choice
     * value storage
     * 
     * @return the value of field 'ChoiceValue'.
     */
    public java.lang.Object getChoiceValue(
    ) {
        return this._choiceValue;
    }

    /**
     * Returns the value of field 'export'.
     * 
     * @return the value of field 'Export'.
     */
    public es.pode.modificador.negocio.cambios.configuracion.castor.Export getExport(
    ) {
        return this._export;
    }

    /**
     * Returns the value of field 'metadata'.
     * 
     * @return the value of field 'Metadata'.
     */
    public es.pode.modificador.negocio.cambios.configuracion.castor.Metadata getMetadata(
    ) {
        return this._metadata;
    }

    /**
     * Returns the value of field 'modification'.
     * 
     * @return the value of field 'Modification'.
     */
    public es.pode.modificador.negocio.cambios.configuracion.castor.Modification getModification(
    ) {
        return this._modification;
    }

    /**
     * Returns the value of field 'removal'.
     * 
     * @return the value of field 'Removal'.
     */
    public es.pode.modificador.negocio.cambios.configuracion.castor.Removal getRemoval(
    ) {
        return this._removal;
    }

    /**
     * Returns the value of field 'transformation'.
     * 
     * @return the value of field 'Transformation'.
     */
    public es.pode.modificador.negocio.cambios.configuracion.castor.Transformation getTransformation(
    ) {
        return this._transformation;
    }

    /**
     * Returns the value of field 'validation'.
     * 
     * @return the value of field 'Validation'.
     */
    public es.pode.modificador.negocio.cambios.configuracion.castor.Validation getValidation(
    ) {
        return this._validation;
    }

    /**
     * Sets the value of field 'addition'.
     * 
     * @param addition the value of field 'addition'.
     */
    public void setAddition(
            final es.pode.modificador.negocio.cambios.configuracion.castor.Addition addition) {
        this._addition = addition;
        this._choiceValue = addition;
    }

    /**
     * Sets the value of field 'check'.
     * 
     * @param check the value of field 'check'.
     */
    public void setCheck(
            final es.pode.modificador.negocio.cambios.configuracion.castor.Check check) {
        this._check = check;
        this._choiceValue = check;
    }

    /**
     * Sets the value of field 'export'.
     * 
     * @param export the value of field 'export'.
     */
    public void setExport(
            final es.pode.modificador.negocio.cambios.configuracion.castor.Export export) {
        this._export = export;
        this._choiceValue = export;
    }

    /**
     * Sets the value of field 'metadata'.
     * 
     * @param metadata the value of field 'metadata'.
     */
    public void setMetadata(
            final es.pode.modificador.negocio.cambios.configuracion.castor.Metadata metadata) {
        this._metadata = metadata;
        this._choiceValue = metadata;
    }

    /**
     * Sets the value of field 'modification'.
     * 
     * @param modification the value of field 'modification'.
     */
    public void setModification(
            final es.pode.modificador.negocio.cambios.configuracion.castor.Modification modification) {
        this._modification = modification;
        this._choiceValue = modification;
    }

    /**
     * Sets the value of field 'removal'.
     * 
     * @param removal the value of field 'removal'.
     */
    public void setRemoval(
            final es.pode.modificador.negocio.cambios.configuracion.castor.Removal removal) {
        this._removal = removal;
        this._choiceValue = removal;
    }

    /**
     * Sets the value of field 'transformation'.
     * 
     * @param transformation the value of field 'transformation'.
     */
    public void setTransformation(
            final es.pode.modificador.negocio.cambios.configuracion.castor.Transformation transformation) {
        this._transformation = transformation;
        this._choiceValue = transformation;
    }

    /**
     * Sets the value of field 'validation'.
     * 
     * @param validation the value of field 'validation'.
     */
    public void setValidation(
            final es.pode.modificador.negocio.cambios.configuracion.castor.Validation validation) {
        this._validation = validation;
        this._choiceValue = validation;
    }

}
