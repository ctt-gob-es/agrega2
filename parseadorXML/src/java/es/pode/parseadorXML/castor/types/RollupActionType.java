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
 * Class RollupActionType.
 * 
 * @version $Revision$ $Date$
 */
public class RollupActionType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The satisfied type
     */
    public static final int SATISFIED_TYPE = 0;

    /**
     * The instance of the satisfied type
     */
    public static final RollupActionType SATISFIED = new RollupActionType(SATISFIED_TYPE, "satisfied");

    /**
     * The notSatisfied type
     */
    public static final int NOTSATISFIED_TYPE = 1;

    /**
     * The instance of the notSatisfied type
     */
    public static final RollupActionType NOTSATISFIED = new RollupActionType(NOTSATISFIED_TYPE, "notSatisfied");

    /**
     * The completed type
     */
    public static final int COMPLETED_TYPE = 2;

    /**
     * The instance of the completed type
     */
    public static final RollupActionType COMPLETED = new RollupActionType(COMPLETED_TYPE, "completed");

    /**
     * The incomplete type
     */
    public static final int INCOMPLETE_TYPE = 3;

    /**
     * The instance of the incomplete type
     */
    public static final RollupActionType INCOMPLETE = new RollupActionType(INCOMPLETE_TYPE, "incomplete");

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

    private RollupActionType(final int type, final java.lang.String value) {
        super();
        this.type = type;
        this.stringValue = value;
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate.Returns an enumeration of all possible
     * instances of RollupActionType
     * 
     * @return an Enumeration over all possible instances of
     * RollupActionType
     */
    public static java.util.Enumeration enumerate(
    ) {
        return _memberTable.elements();
    }

    /**
     * Method getType.Returns the type of this RollupActionType
     * 
     * @return the type of this RollupActionType
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
        members.put("satisfied", SATISFIED);
        members.put("notSatisfied", NOTSATISFIED);
        members.put("completed", COMPLETED);
        members.put("incomplete", INCOMPLETE);
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
     * RollupActionType
     * 
     * @return the String representation of this RollupActionType
     */
    public java.lang.String toString(
    ) {
        return this.stringValue;
    }

    /**
     * Method valueOf.Returns a new RollupActionType based on the
     * given String value.
     * 
     * @param string
     * @return the RollupActionType value of parameter 'string'
     */
    public static RollupActionType valueOf(
            final java.lang.String string) {
        java.lang.Object obj = null;
        if (string != null) {
            obj = _memberTable.get(string);
        }
        if (obj == null) {
            String err = "" + string + " is not a valid RollupActionType";
            throw new IllegalArgumentException(err);
        }
        return (RollupActionType) obj;
    }

}
