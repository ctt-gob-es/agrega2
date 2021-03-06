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
 * Class PostConditionRuleActionType.
 * 
 * @version $Revision$ $Date$
 */
public class PostConditionRuleActionType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The exitParent type
     */
    public static final int EXITPARENT_TYPE = 0;

    /**
     * The instance of the exitParent type
     */
    public static final PostConditionRuleActionType EXITPARENT = new PostConditionRuleActionType(EXITPARENT_TYPE, "exitParent");

    /**
     * The exitAll type
     */
    public static final int EXITALL_TYPE = 1;

    /**
     * The instance of the exitAll type
     */
    public static final PostConditionRuleActionType EXITALL = new PostConditionRuleActionType(EXITALL_TYPE, "exitAll");

    /**
     * The retry type
     */
    public static final int RETRY_TYPE = 2;

    /**
     * The instance of the retry type
     */
    public static final PostConditionRuleActionType RETRY = new PostConditionRuleActionType(RETRY_TYPE, "retry");

    /**
     * The retryAll type
     */
    public static final int RETRYALL_TYPE = 3;

    /**
     * The instance of the retryAll type
     */
    public static final PostConditionRuleActionType RETRYALL = new PostConditionRuleActionType(RETRYALL_TYPE, "retryAll");

    /**
     * The continue type
     */
    public static final int CONTINUE_TYPE = 4;

    /**
     * The instance of the continue type
     */
    public static final PostConditionRuleActionType CONTINUE = new PostConditionRuleActionType(CONTINUE_TYPE, "continue");

    /**
     * The previous type
     */
    public static final int PREVIOUS_TYPE = 5;

    /**
     * The instance of the previous type
     */
    public static final PostConditionRuleActionType PREVIOUS = new PostConditionRuleActionType(PREVIOUS_TYPE, "previous");

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

    private PostConditionRuleActionType(final int type, final java.lang.String value) {
        super();
        this.type = type;
        this.stringValue = value;
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate.Returns an enumeration of all possible
     * instances of PostConditionRuleActionType
     * 
     * @return an Enumeration over all possible instances of
     * PostConditionRuleActionType
     */
    public static java.util.Enumeration enumerate(
    ) {
        return _memberTable.elements();
    }

    /**
     * Method getType.Returns the type of this
     * PostConditionRuleActionType
     * 
     * @return the type of this PostConditionRuleActionType
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
        members.put("exitParent", EXITPARENT);
        members.put("exitAll", EXITALL);
        members.put("retry", RETRY);
        members.put("retryAll", RETRYALL);
        members.put("continue", CONTINUE);
        members.put("previous", PREVIOUS);
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
     * PostConditionRuleActionType
     * 
     * @return the String representation of this
     * PostConditionRuleActionType
     */
    public java.lang.String toString(
    ) {
        return this.stringValue;
    }

    /**
     * Method valueOf.Returns a new PostConditionRuleActionType
     * based on the given String value.
     * 
     * @param string
     * @return the PostConditionRuleActionType value of parameter
     * 'string'
     */
    public static PostConditionRuleActionType valueOf(
            final java.lang.String string) {
        java.lang.Object obj = null;
        if (string != null) {
            obj = _memberTable.get(string);
        }
        if (obj == null) {
            String err = "" + string + " is not a valid PostConditionRuleActionType";
            throw new IllegalArgumentException(err);
        }
        return (PostConditionRuleActionType) obj;
    }

}
