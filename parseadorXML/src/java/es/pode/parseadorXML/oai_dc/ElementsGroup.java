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
