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
 * Class InteractivityTypeValues.
 * 
 * @version $Revision$ $Date$
 */
public class InteractivityTypeValues implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The active type
     */
    public static final int ACTIVE_TYPE = 0;

    /**
     * The instance of the active type
     */
    public static final InteractivityTypeValues ACTIVE = new InteractivityTypeValues(ACTIVE_TYPE, "active");

    /**
     * The expositive type
     */
    public static final int EXPOSITIVE_TYPE = 1;

    /**
     * The instance of the expositive type
     */
    public static final InteractivityTypeValues EXPOSITIVE = new InteractivityTypeValues(EXPOSITIVE_TYPE, "expositive");

    /**
     * The mixed type
     */
    public static final int MIXED_TYPE = 2;

    /**
     * The instance of the mixed type
     */
    public static final InteractivityTypeValues MIXED = new InteractivityTypeValues(MIXED_TYPE, "mixed");

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

    private InteractivityTypeValues(final int type, final java.lang.String value) {
        super();
        this.type = type;
        this.stringValue = value;
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate.Returns an enumeration of all possible
     * instances of InteractivityTypeValues
     * 
     * @return an Enumeration over all possible instances of
     * InteractivityTypeValues
     */
    public static java.util.Enumeration enumerate(
    ) {
        return _memberTable.elements();
    }

    /**
     * Method getType.Returns the type of this
     * InteractivityTypeValues
     * 
     * @return the type of this InteractivityTypeValues
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
        members.put("active", ACTIVE);
        members.put("expositive", EXPOSITIVE);
        members.put("mixed", MIXED);
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
     * InteractivityTypeValues
     * 
     * @return the String representation of this
     * InteractivityTypeValues
     */
    public java.lang.String toString(
    ) {
        return this.stringValue;
    }

    /**
     * Method valueOf.Returns a new InteractivityTypeValues based
     * on the given String value.
     * 
     * @param string
     * @return the InteractivityTypeValues value of parameter
     * 'string'
     */
    public static es.pode.parseadorXML.castor.types.InteractivityTypeValues valueOf(
            final java.lang.String string) {
        java.lang.Object obj = null;
        if (string != null) {
            obj = _memberTable.get(string);
        }
        if (obj == null) {
            String err = "" + string + " is not a valid InteractivityTypeValues";
            throw new IllegalArgumentException(err);
        }
        return (InteractivityTypeValues) obj;
    }

}
