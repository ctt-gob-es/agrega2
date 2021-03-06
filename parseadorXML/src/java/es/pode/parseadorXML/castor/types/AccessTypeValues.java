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
 * Class AccessTypeValues.
 * 
 * @version $Revision$ $Date$
 */
public class AccessTypeValues implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The universal type
     */
    public static final int UNIVERSAL_TYPE = 0;

    /**
     * The instance of the universal type
     */
    public static final AccessTypeValues UNIVERSAL = new AccessTypeValues(UNIVERSAL_TYPE, "universal");

    /**
     * The non-universal type
     */
    public static final int NON_UNIVERSAL_TYPE = 1;

    /**
     * The instance of the non-universal type
     */
    public static final AccessTypeValues NON_UNIVERSAL = new AccessTypeValues(NON_UNIVERSAL_TYPE, "non-universal");

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

    private AccessTypeValues(final int type, final java.lang.String value) {
        super();
        this.type = type;
        this.stringValue = value;
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate.Returns an enumeration of all possible
     * instances of AccessTypeValues
     * 
     * @return an Enumeration over all possible instances of
     * AccessTypeValues
     */
    public static java.util.Enumeration enumerate(
    ) {
        return _memberTable.elements();
    }

    /**
     * Method getType.Returns the type of this AccessTypeValues
     * 
     * @return the type of this AccessTypeValues
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
        members.put("universal", UNIVERSAL);
        members.put("non-universal", NON_UNIVERSAL);
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
     * AccessTypeValues
     * 
     * @return the String representation of this AccessTypeValues
     */
    public java.lang.String toString(
    ) {
        return this.stringValue;
    }

    /**
     * Method valueOf.Returns a new AccessTypeValues based on the
     * given String value.
     * 
     * @param string
     * @return the AccessTypeValues value of parameter 'string'
     */
    public static es.pode.parseadorXML.castor.types.AccessTypeValues valueOf(
            final java.lang.String string) {
        java.lang.Object obj = null;
        if (string != null) {
            obj = _memberTable.get(string);
        }
        if (obj == null) {
            String err = "" + string + " is not a valid AccessTypeValues";
            throw new IllegalArgumentException(err);
        }
        return (AccessTypeValues) obj;
    }

}
