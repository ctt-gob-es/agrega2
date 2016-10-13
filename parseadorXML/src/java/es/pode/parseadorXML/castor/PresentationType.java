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
