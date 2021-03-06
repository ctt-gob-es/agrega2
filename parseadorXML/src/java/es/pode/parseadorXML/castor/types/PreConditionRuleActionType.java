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
 * Class PreConditionRuleActionType.
 * 
 * @version $Revision$ $Date$
 */
public class PreConditionRuleActionType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The skip type
     */
    public static final int SKIP_TYPE = 0;

    /**
     * The instance of the skip type
     */
    public static final PreConditionRuleActionType SKIP = new PreConditionRuleActionType(SKIP_TYPE, "skip");

    /**
     * The disabled type
     */
    public static final int DISABLED_TYPE = 1;

    /**
     * The instance of the disabled type
     */
    public static final PreConditionRuleActionType DISABLED = new PreConditionRuleActionType(DISABLED_TYPE, "disabled");

    /**
     * The hiddenFromChoice type
     */
    public static final int HIDDENFROMCHOICE_TYPE = 2;

    /**
     * The instance of the hiddenFromChoice type
     */
    public static final PreConditionRuleActionType HIDDENFROMCHOICE = new PreConditionRuleActionType(HIDDENFROMCHOICE_TYPE, "hiddenFromChoice");

    /**
     * The stopForwardTraversal type
     */
    public static final int STOPFORWARDTRAVERSAL_TYPE = 3;

    /**
     * The instance of the stopForwardTraversal type
     */
    public static final PreConditionRuleActionType STOPFORWARDTRAVERSAL = new PreConditionRuleActionType(STOPFORWARDTRAVERSAL_TYPE, "stopForwardTraversal");

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

    private PreConditionRuleActionType(final int type, final java.lang.String value) {
        super();
        this.type = type;
        this.stringValue = value;
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate.Returns an enumeration of all possible
     * instances of PreConditionRuleActionType
     * 
     * @return an Enumeration over all possible instances of
     * PreConditionRuleActionType
     */
    public static java.util.Enumeration enumerate(
    ) {
        return _memberTable.elements();
    }

    /**
     * Method getType.Returns the type of this
     * PreConditionRuleActionType
     * 
     * @return the type of this PreConditionRuleActionType
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
        members.put("skip", SKIP);
        members.put("disabled", DISABLED);
        members.put("hiddenFromChoice", HIDDENFROMCHOICE);
        members.put("stopForwardTraversal", STOPFORWARDTRAVERSAL);
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
     * PreConditionRuleActionType
     * 
     * @return the String representation of this
     * PreConditionRuleActionType
     */
    public java.lang.String toString(
    ) {
        return this.stringValue;
    }

    /**
     * Method valueOf.Returns a new PreConditionRuleActionType
     * based on the given String value.
     * 
     * @param string
     * @return the PreConditionRuleActionType value of parameter
     * 'string'
     */
    public static PreConditionRuleActionType valueOf(
            final java.lang.String string) {
        java.lang.Object obj = null;
        if (string != null) {
            obj = _memberTable.get(string);
        }
        if (obj == null) {
            String err = "" + string + " is not a valid PreConditionRuleActionType";
            throw new IllegalArgumentException(err);
        }
        return (PreConditionRuleActionType) obj;
    }

}
