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
 * Class ExitConditionRuleActionType.
 * 
 * @version $Revision$ $Date$
 */
public class ExitConditionRuleActionType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The exit type
     */
    public static final int EXIT_TYPE = 0;

    /**
     * The instance of the exit type
     */
    public static final ExitConditionRuleActionType EXIT = new ExitConditionRuleActionType(EXIT_TYPE, "exit");

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

    private ExitConditionRuleActionType(final int type, final java.lang.String value) {
        super();
        this.type = type;
        this.stringValue = value;
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate.Returns an enumeration of all possible
     * instances of ExitConditionRuleActionType
     * 
     * @return an Enumeration over all possible instances of
     * ExitConditionRuleActionType
     */
    public static java.util.Enumeration enumerate(
    ) {
        return _memberTable.elements();
    }

    /**
     * Method getType.Returns the type of this
     * ExitConditionRuleActionType
     * 
     * @return the type of this ExitConditionRuleActionType
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
        members.put("exit", EXIT);
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
     * ExitConditionRuleActionType
     * 
     * @return the String representation of this
     * ExitConditionRuleActionType
     */
    public java.lang.String toString(
    ) {
        return this.stringValue;
    }

    /**
     * Method valueOf.Returns a new ExitConditionRuleActionType
     * based on the given String value.
     * 
     * @param string
     * @return the ExitConditionRuleActionType value of parameter
     * 'string'
     */
    public static ExitConditionRuleActionType valueOf(
            final java.lang.String string) {
        java.lang.Object obj = null;
        if (string != null) {
            obj = _memberTable.get(string);
        }
        if (obj == null) {
            String err = "" + string + " is not a valid ExitConditionRuleActionType";
            throw new IllegalArgumentException(err);
        }
        return (ExitConditionRuleActionType) obj;
    }

}
