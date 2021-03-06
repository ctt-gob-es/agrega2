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
 * Class ChildActivityType.
 * 
 * @version $Revision$ $Date$
 */
public class ChildActivityType implements java.io.Serializable {


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
    public static final ChildActivityType ALL = new ChildActivityType(ALL_TYPE, "all");

    /**
     * The any type
     */
    public static final int ANY_TYPE = 1;

    /**
     * The instance of the any type
     */
    public static final ChildActivityType ANY = new ChildActivityType(ANY_TYPE, "any");

    /**
     * The none type
     */
    public static final int NONE_TYPE = 2;

    /**
     * The instance of the none type
     */
    public static final ChildActivityType NONE = new ChildActivityType(NONE_TYPE, "none");

    /**
     * The atLeastCount type
     */
    public static final int ATLEASTCOUNT_TYPE = 3;

    /**
     * The instance of the atLeastCount type
     */
    public static final ChildActivityType ATLEASTCOUNT = new ChildActivityType(ATLEASTCOUNT_TYPE, "atLeastCount");

    /**
     * The atLeastPercent type
     */
    public static final int ATLEASTPERCENT_TYPE = 4;

    /**
     * The instance of the atLeastPercent type
     */
    public static final ChildActivityType ATLEASTPERCENT = new ChildActivityType(ATLEASTPERCENT_TYPE, "atLeastPercent");

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

    private ChildActivityType(final int type, final java.lang.String value) {
        super();
        this.type = type;
        this.stringValue = value;
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate.Returns an enumeration of all possible
     * instances of ChildActivityType
     * 
     * @return an Enumeration over all possible instances of
     * ChildActivityType
     */
    public static java.util.Enumeration enumerate(
    ) {
        return _memberTable.elements();
    }

    /**
     * Method getType.Returns the type of this ChildActivityType
     * 
     * @return the type of this ChildActivityType
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
        members.put("none", NONE);
        members.put("atLeastCount", ATLEASTCOUNT);
        members.put("atLeastPercent", ATLEASTPERCENT);
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
     * ChildActivityType
     * 
     * @return the String representation of this ChildActivityType
     */
    public java.lang.String toString(
    ) {
        return this.stringValue;
    }

    /**
     * Method valueOf.Returns a new ChildActivityType based on the
     * given String value.
     * 
     * @param string
     * @return the ChildActivityType value of parameter 'string'
     */
    public static ChildActivityType valueOf(
            final java.lang.String string) {
        java.lang.Object obj = null;
        if (string != null) {
            obj = _memberTable.get(string);
        }
        if (obj == null) {
            String err = "" + string + " is not a valid ChildActivityType";
            throw new IllegalArgumentException(err);
        }
        return (ChildActivityType) obj;
    }

}
