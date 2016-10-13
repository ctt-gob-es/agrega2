/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1</a>, using an XML
 * Schema.
 * $Id$
 */

package es.pode.parseadorXML.castor.types;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.util.Hashtable;

/**
 * Class TypeValues.
 * 
 * @version $Revision$ $Date$
 */
public class TypeValues implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The operating system type
     */
    public static final int OPERATING_SYSTEM_TYPE = 0;

    /**
     * The instance of the operating system type
     */
    public static final TypeValues OPERATING_SYSTEM = new TypeValues(OPERATING_SYSTEM_TYPE, "operating system");

    /**
     * The browser type
     */
    public static final int BROWSER_TYPE = 1;

    /**
     * The instance of the browser type
     */
    public static final TypeValues BROWSER = new TypeValues(BROWSER_TYPE, "browser");

    /**
     * Field _memberTable.
     */
    private static java.util.Hashtable _memberTable = init();

    /**
     * Field type.
     */
    private int type = -1;

    /**
     * Field stringValue.
     */
    private java.lang.String stringValue = null;


      //----------------/
     //- Constructors -/
    //----------------/

    private TypeValues(final int type, final java.lang.String value) {
        super();
        this.type = type;
        this.stringValue = value;
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate.Returns an enumeration of all possible
     * instances of TypeValues
     * 
     * @return an Enumeration over all possible instances of
     * TypeValues
     */
    public static java.util.Enumeration enumerate(
    ) {
        return _memberTable.elements();
    }

    /**
     * Method getType.Returns the type of this TypeValues
     * 
     * @return the type of this TypeValues
     */
    public int getType(
    ) {
        return this.type;
    }

    /**
     * Method init.
     * 
     * @return the initialized Hashtable for the member table
     */
    private static java.util.Hashtable init(
    ) {
        Hashtable members = new Hashtable();
        members.put("operating system", OPERATING_SYSTEM);
        members.put("browser", BROWSER);
        return members;
    }

    /**
     * Method readResolve. will be called during deserialization to
     * replace the deserialized object with the correct constant
     * instance.
     * 
     * @return this deserialized object
     */
    private java.lang.Object readResolve(
    ) {
        return valueOf(this.stringValue);
    }

    /**
     * Method toString.Returns the String representation of this
     * TypeValues
     * 
     * @return the String representation of this TypeValues
     */
    public java.lang.String toString(
    ) {
        return this.stringValue;
    }

    /**
     * Method valueOf.Returns a new TypeValues based on the given
     * String value.
     * 
     * @param string
     * @return the TypeValues value of parameter 'string'
     */
    public static es.pode.parseadorXML.castor.types.TypeValues valueOf(
            final java.lang.String string) {
        java.lang.Object obj = null;
        if (string != null) {
            obj = _memberTable.get(string);
        }
        if (obj == null) {
            String err = "" + string + " is not a valid TypeValues";
            throw new IllegalArgumentException(err);
        }
        return (TypeValues) obj;
    }

}
