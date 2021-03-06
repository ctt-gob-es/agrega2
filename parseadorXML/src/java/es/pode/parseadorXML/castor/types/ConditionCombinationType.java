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
 * Class ConditionCombinationType.
 * 
 * @version $Revision$ $Date$
 */
public class ConditionCombinationType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The all type
     */
    public static final int ALL_TYPE = 0;

    /**
     * The instance of the all type
     */
    public static final ConditionCombinationType ALL = new ConditionCombinationType(ALL_TYPE, "all");

    /**
     * The any type
     */
    public static final int ANY_TYPE = 1;

    /**
     * The instance of the any type
     */
    public static final ConditionCombinationType ANY = new ConditionCombinationType(ANY_TYPE, "any");

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

    private ConditionCombinationType(final int type, final java.lang.String value) {
        super();
        this.type = type;
        this.stringValue = value;
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate.Returns an enumeration of all possible
     * instances of ConditionCombinationType
     * 
     * @return an Enumeration over all possible instances of
     * ConditionCombinationType
     */
    public static java.util.Enumeration enumerate(
    ) {
        return _memberTable.elements();
    }

    /**
     * Method getType.Returns the type of this
     * ConditionCombinationType
     * 
     * @return the type of this ConditionCombinationType
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
        members.put("all", ALL);
        members.put("any", ANY);
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
     * ConditionCombinationType
     * 
     * @return the String representation of this
     * ConditionCombinationType
     */
    public java.lang.String toString(
    ) {
        return this.stringValue;
    }

    /**
     * Method valueOf.Returns a new ConditionCombinationType based
     * on the given String value.
     * 
     * @param string
     * @return the ConditionCombinationType value of parameter
     * 'string'
     */
    public static ConditionCombinationType valueOf(
            final java.lang.String string) {
        java.lang.Object obj = null;
        if (string != null) {
            obj = _memberTable.get(string);
        }
        if (obj == null) {
            String err = "" + string + " is not a valid ConditionCombinationType";
            throw new IllegalArgumentException(err);
        }
        return (ConditionCombinationType) obj;
    }

}
