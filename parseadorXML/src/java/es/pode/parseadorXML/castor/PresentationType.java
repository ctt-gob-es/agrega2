/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

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
 * Class PresentationType.
 * 
 * @version $Revision$ $Date$
 */
public abstract class PresentationType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _navigationInterface.
     */
    private es.pode.parseadorXML.castor.NavigationInterface _navigationInterface;


      //----------------/
     //- Constructors -/
    //----------------/

    public PresentationType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'navigationInterface'.
     * 
     * @return the value of field 'NavigationInterface'.
     */
    public es.pode.parseadorXML.castor.NavigationInterface getNavigationInterface(
    ) {
        return this._navigationInterface;
    }

    /**
     * Sets the value of field 'navigationInterface'.
     * 
     * @param navigationInterface the value of field
     * 'navigationInterface'.
     */
    public void setNavigationInterface(
            final es.pode.parseadorXML.castor.NavigationInterface navigationInterface) {
        this._navigationInterface = navigationInterface;
    }

}
