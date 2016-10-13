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

package es.pode.parseadorXML.oai_dc;

/**
 * Class ElementsGroup.
 * 
 * @version $Revision$ $Date$
 */
public class ElementsGroup implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Internal choice value storage
     */
    private java.lang.Object _choiceValue;

    /**
     * Field _elementsGroupChoice.
     */
    private ElementsGroupChoice _elementsGroupChoice;


      //----------------/
     //- Constructors -/
    //----------------/

    public ElementsGroup() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

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
     * Returns the value of field 'elementsGroupChoice'.
     * 
     * @return the value of field 'ElementsGroupChoice'.
     */
    public ElementsGroupChoice getElementsGroupChoice(
    ) {
        return this._elementsGroupChoice;
    }

    /**
     * Sets the value of field 'elementsGroupChoice'.
     * 
     * @param elementsGroupChoice the value of field
     * 'elementsGroupChoice'.
     */
    public void setElementsGroupChoice(
            final ElementsGroupChoice elementsGroupChoice) {
        this._elementsGroupChoice = elementsGroupChoice;
        this._choiceValue = elementsGroupChoice;
    }

}
