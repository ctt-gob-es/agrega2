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

package es.pode.parseadorXML.castor;

/**
 * Class LanguageStringItem.
 * 
 * @version $Revision$ $Date$
 */
public class LanguageStringItem implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Internal choice value storage
     */
    private java.lang.Object _choiceValue;

    /**
     * Field _string.
     */
    private es.pode.parseadorXML.castor.String _string;


      //----------------/
     //- Constructors -/
    //----------------/

    public LanguageStringItem() {
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
     * Returns the value of field 'string'.
     * 
     * @return the value of field 'String'.
     */
    public es.pode.parseadorXML.castor.String getString(
    ) {
        return this._string;
    }

    /**
     * Sets the value of field 'string'.
     * 
     * @param string the value of field 'string'.
     */
    public void setString(
            final es.pode.parseadorXML.castor.String string) {
        this._string = string;
        this._choiceValue = string;
    }

}
