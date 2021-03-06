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
 * Class SequencingRuleConditionType.
 * 
 * @version $Revision$ $Date$
 */
public class SequencingRuleConditionType implements java.io.Serializable {


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
    public static final SequencingRuleConditionType SATISFIED = new SequencingRuleConditionType(SATISFIED_TYPE, "satisfied");

    /**
     * The objectiveStatusKnown type
     */
    public static final int OBJECTIVESTATUSKNOWN_TYPE = 1;

    /**
     * The instance of the objectiveStatusKnown type
     */
    public static final SequencingRuleConditionType OBJECTIVESTATUSKNOWN = new SequencingRuleConditionType(OBJECTIVESTATUSKNOWN_TYPE, "objectiveStatusKnown");

    /**
     * The objectiveMeasureKnown type
     */
    public static final int OBJECTIVEMEASUREKNOWN_TYPE = 2;

    /**
     * The instance of the objectiveMeasureKnown type
     */
    public static final SequencingRuleConditionType OBJECTIVEMEASUREKNOWN = new SequencingRuleConditionType(OBJECTIVEMEASUREKNOWN_TYPE, "objectiveMeasureKnown");

    /**
     * The objectiveMeasureGreaterThan type
     */
    public static final int OBJECTIVEMEASUREGREATERTHAN_TYPE = 3;

    /**
     * The instance of the objectiveMeasureGreaterThan type
     */
    public static final SequencingRuleConditionType OBJECTIVEMEASUREGREATERTHAN = new SequencingRuleConditionType(OBJECTIVEMEASUREGREATERTHAN_TYPE, "objectiveMeasureGreaterThan");

    /**
     * The objectiveMeasureLessThan type
     */
    public static final int OBJECTIVEMEASURELESSTHAN_TYPE = 4;

    /**
     * The instance of the objectiveMeasureLessThan type
     */
    public static final SequencingRuleConditionType OBJECTIVEMEASURELESSTHAN = new SequencingRuleConditionType(OBJECTIVEMEASURELESSTHAN_TYPE, "objectiveMeasureLessThan");

    /**
     * The completed type
     */
    public static final int COMPLETED_TYPE = 5;

    /**
     * The instance of the completed type
     */
    public static final SequencingRuleConditionType COMPLETED = new SequencingRuleConditionType(COMPLETED_TYPE, "completed");

    /**
     * The activityProgressKnown type
     */
    public static final int ACTIVITYPROGRESSKNOWN_TYPE = 6;

    /**
     * The instance of the activityProgressKnown type
     */
    public static final SequencingRuleConditionType ACTIVITYPROGRESSKNOWN = new SequencingRuleConditionType(ACTIVITYPROGRESSKNOWN_TYPE, "activityProgressKnown");

    /**
     * The attempted type
     */
    public static final int ATTEMPTED_TYPE = 7;

    /**
     * The instance of the attempted type
     */
    public static final SequencingRuleConditionType ATTEMPTED = new SequencingRuleConditionType(ATTEMPTED_TYPE, "attempted");

    /**
     * The attemptLimitExceeded type
     */
    public static final int ATTEMPTLIMITEXCEEDED_TYPE = 8;

    /**
     * The instance of the attemptLimitExceeded type
     */
    public static final SequencingRuleConditionType ATTEMPTLIMITEXCEEDED = new SequencingRuleConditionType(ATTEMPTLIMITEXCEEDED_TYPE, "attemptLimitExceeded");

    /**
     * The timeLimitExceeded type
     */
    public static final int TIMELIMITEXCEEDED_TYPE = 9;

    /**
     * The instance of the timeLimitExceeded type
     */
    public static final SequencingRuleConditionType TIMELIMITEXCEEDED = new SequencingRuleConditionType(TIMELIMITEXCEEDED_TYPE, "timeLimitExceeded");

    /**
     * The outsideAvailableTimeRange type
     */
    public static final int OUTSIDEAVAILABLETIMERANGE_TYPE = 10;

    /**
     * The instance of the outsideAvailableTimeRange type
     */
    public static final SequencingRuleConditionType OUTSIDEAVAILABLETIMERANGE = new SequencingRuleConditionType(OUTSIDEAVAILABLETIMERANGE_TYPE, "outsideAvailableTimeRange");

    /**
     * The always type
     */
    public static final int ALWAYS_TYPE = 11;

    /**
     * The instance of the always type
     */
    public static final SequencingRuleConditionType ALWAYS = new SequencingRuleConditionType(ALWAYS_TYPE, "always");

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

    private SequencingRuleConditionType(final int type, final java.lang.String value) {
        super();
        this.type = type;
        this.stringValue = value;
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate.Returns an enumeration of all possible
     * instances of SequencingRuleConditionType
     * 
     * @return an Enumeration over all possible instances of
     * SequencingRuleConditionType
     */
    public static java.util.Enumeration enumerate(
    ) {
        return _memberTable.elements();
    }

    /**
     * Method getType.Returns the type of this
     * SequencingRuleConditionType
     * 
     * @return the type of this SequencingRuleConditionType
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
        members.put("objectiveStatusKnown", OBJECTIVESTATUSKNOWN);
        members.put("objectiveMeasureKnown", OBJECTIVEMEASUREKNOWN);
        members.put("objectiveMeasureGreaterThan", OBJECTIVEMEASUREGREATERTHAN);
        members.put("objectiveMeasureLessThan", OBJECTIVEMEASURELESSTHAN);
        members.put("completed", COMPLETED);
        members.put("activityProgressKnown", ACTIVITYPROGRESSKNOWN);
        members.put("attempted", ATTEMPTED);
        members.put("attemptLimitExceeded", ATTEMPTLIMITEXCEEDED);
        members.put("timeLimitExceeded", TIMELIMITEXCEEDED);
        members.put("outsideAvailableTimeRange", OUTSIDEAVAILABLETIMERANGE);
        members.put("always", ALWAYS);
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
     * SequencingRuleConditionType
     * 
     * @return the String representation of this
     * SequencingRuleConditionType
     */
    public java.lang.String toString(
    ) {
        return this.stringValue;
    }

    /**
     * Method valueOf.Returns a new SequencingRuleConditionType
     * based on the given String value.
     * 
     * @param string
     * @return the SequencingRuleConditionType value of parameter
     * 'string'
     */
    public static SequencingRuleConditionType valueOf(
            final java.lang.String string) {
        java.lang.Object obj = null;
        if (string != null) {
            obj = _memberTable.get(string);
        }
        if (obj == null) {
            String err = "" + string + " is not a valid SequencingRuleConditionType";
            throw new IllegalArgumentException(err);
        }
        return (SequencingRuleConditionType) obj;
    }

}
