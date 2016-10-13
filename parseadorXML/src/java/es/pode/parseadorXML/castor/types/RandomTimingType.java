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
 * Class RandomTimingType.
 * 
 * @version $Revision$ $Date$
 */
public class RandomTimingType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The never type
     */
    public static final int NEVER_TYPE = 0;

    /**
     * The instance of the never type
     */
    public static final RandomTimingType NEVER = new RandomTimingType(NEVER_TYPE, "never");

    /**
     * The once type
     */
    public static final int ONCE_TYPE = 1;

    /**
     * The instance of the once type
     */
    public static final RandomTimingType ONCE = new RandomTimingType(ONCE_TYPE, "once");

    /**
     * The onEachNewAttempt type
     */
    public static final int ONEACHNEWATTEMPT_TYPE = 2;

    /**
     * The instance of the onEachNewAttempt type
     */
    public static final RandomTimingType ONEACHNEWATTEMPT = new RandomTimingType(ONEACHNEWATTEMPT_TYPE, "onEachNewAttempt");

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

    private RandomTimingType(final int type, final java.lang.String value) {
        super();
        this.type = type;
        this.stringValue = value;
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate.Returns an enumeration of all possible
     * instances of RandomTimingType
     * 
     * @return an Enumeration over all possible instances of
     * RandomTimingType
     */
    public static java.util.Enumeration enumerate(
    ) {
        return _memberTable.elements();
    }

    /**
     * Method getType.Returns the type of this RandomTimingType
     * 
     * @return the type of this RandomTimingType
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
        members.put("never", NEVER);
        members.put("once", ONCE);
        members.put("onEachNewAttempt", ONEACHNEWATTEMPT);
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
     * RandomTimingType
     * 
     * @return the String representation of this RandomTimingType
     */
    public java.lang.String toString(
    ) {
        return this.stringValue;
    }

    /**
     * Method valueOf.Returns a new RandomTimingType based on the
     * given String value.
     * 
     * @param string
     * @return the RandomTimingType value of parameter 'string'
     */
    public static RandomTimingType valueOf(
            final java.lang.String string) {
        java.lang.Object obj = null;
        if (string != null) {
            obj = _memberTable.get(string);
        }
        if (obj == null) {
            String err = "" + string + " is not a valid RandomTimingType";
            throw new IllegalArgumentException(err);
        }
        return (RandomTimingType) obj;
    }

}
