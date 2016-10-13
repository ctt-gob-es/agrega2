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
 * Class ConditionOperatorType.
 * 
 * @version $Revision$ $Date$
 */
public class ConditionOperatorType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The not type
     */
    public static final int NOT_TYPE = 0;

    /**
     * The instance of the not type
     */
    public static final ConditionOperatorType NOT = new ConditionOperatorType(NOT_TYPE, "not");

    /**
     * The noOp type
     */
    public static final int NOOP_TYPE = 1;

    /**
     * The instance of the noOp type
     */
    public static final ConditionOperatorType NOOP = new ConditionOperatorType(NOOP_TYPE, "noOp");

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

    private ConditionOperatorType(final int type, final java.lang.String value) {
        super();
        this.type = type;
        this.stringValue = value;
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate.Returns an enumeration of all possible
     * instances of ConditionOperatorType
     * 
     * @return an Enumeration over all possible instances of
     * ConditionOperatorType
     */
    public static java.util.Enumeration enumerate(
    ) {
        return _memberTable.elements();
    }

    /**
     * Method getType.Returns the type of this
     * ConditionOperatorType
     * 
     * @return the type of this ConditionOperatorType
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
        members.put("not", NOT);
        members.put("noOp", NOOP);
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
     * ConditionOperatorType
     * 
     * @return the String representation of this
     * ConditionOperatorType
     */
    public java.lang.String toString(
    ) {
        return this.stringValue;
    }

    /**
     * Method valueOf.Returns a new ConditionOperatorType based on
     * the given String value.
     * 
     * @param string
     * @return the ConditionOperatorType value of parameter 'string'
     */
    public static ConditionOperatorType valueOf(
            final java.lang.String string) {
        java.lang.Object obj = null;
        if (string != null) {
            obj = _memberTable.get(string);
        }
        if (obj == null) {
            String err = "" + string + " is not a valid ConditionOperatorType";
            throw new IllegalArgumentException(err);
        }
        return (ConditionOperatorType) obj;
    }

}
