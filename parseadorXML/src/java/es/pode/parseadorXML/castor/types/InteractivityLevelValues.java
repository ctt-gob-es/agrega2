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
 * Class InteractivityLevelValues.
 * 
 * @version $Revision$ $Date$
 */
public class InteractivityLevelValues implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The very low type
     */
    public static final int VERY_LOW_TYPE = 0;

    /**
     * The instance of the very low type
     */
    public static final InteractivityLevelValues VERY_LOW = new InteractivityLevelValues(VERY_LOW_TYPE, "very low");

    /**
     * The low type
     */
    public static final int LOW_TYPE = 1;

    /**
     * The instance of the low type
     */
    public static final InteractivityLevelValues LOW = new InteractivityLevelValues(LOW_TYPE, "low");

    /**
     * The medium type
     */
    public static final int MEDIUM_TYPE = 2;

    /**
     * The instance of the medium type
     */
    public static final InteractivityLevelValues MEDIUM = new InteractivityLevelValues(MEDIUM_TYPE, "medium");

    /**
     * The high type
     */
    public static final int HIGH_TYPE = 3;

    /**
     * The instance of the high type
     */
    public static final InteractivityLevelValues HIGH = new InteractivityLevelValues(HIGH_TYPE, "high");

    /**
     * The very high type
     */
    public static final int VERY_HIGH_TYPE = 4;

    /**
     * The instance of the very high type
     */
    public static final InteractivityLevelValues VERY_HIGH = new InteractivityLevelValues(VERY_HIGH_TYPE, "very high");

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

    private InteractivityLevelValues(final int type, final java.lang.String value) {
        super();
        this.type = type;
        this.stringValue = value;
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate.Returns an enumeration of all possible
     * instances of InteractivityLevelValues
     * 
     * @return an Enumeration over all possible instances of
     * InteractivityLevelValues
     */
    public static java.util.Enumeration enumerate(
    ) {
        return _memberTable.elements();
    }

    /**
     * Method getType.Returns the type of this
     * InteractivityLevelValues
     * 
     * @return the type of this InteractivityLevelValues
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
        members.put("very low", VERY_LOW);
        members.put("low", LOW);
        members.put("medium", MEDIUM);
        members.put("high", HIGH);
        members.put("very high", VERY_HIGH);
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
     * InteractivityLevelValues
     * 
     * @return the String representation of this
     * InteractivityLevelValues
     */
    public java.lang.String toString(
    ) {
        return this.stringValue;
    }

    /**
     * Method valueOf.Returns a new InteractivityLevelValues based
     * on the given String value.
     * 
     * @param string
     * @return the InteractivityLevelValues value of parameter
     * 'string'
     */
    public static es.pode.parseadorXML.castor.types.InteractivityLevelValues valueOf(
            final java.lang.String string) {
        java.lang.Object obj = null;
        if (string != null) {
            obj = _memberTable.get(string);
        }
        if (obj == null) {
            String err = "" + string + " is not a valid InteractivityLevelValues";
            throw new IllegalArgumentException(err);
        }
        return (InteractivityLevelValues) obj;
    }

}
