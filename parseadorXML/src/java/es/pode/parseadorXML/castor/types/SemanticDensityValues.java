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
 * Class SemanticDensityValues.
 * 
 * @version $Revision$ $Date$
 */
public class SemanticDensityValues implements java.io.Serializable {


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
    public static final SemanticDensityValues VERY_LOW = new SemanticDensityValues(VERY_LOW_TYPE, "very low");

    /**
     * The low type
     */
    public static final int LOW_TYPE = 1;

    /**
     * The instance of the low type
     */
    public static final SemanticDensityValues LOW = new SemanticDensityValues(LOW_TYPE, "low");

    /**
     * The medium type
     */
    public static final int MEDIUM_TYPE = 2;

    /**
     * The instance of the medium type
     */
    public static final SemanticDensityValues MEDIUM = new SemanticDensityValues(MEDIUM_TYPE, "medium");

    /**
     * The high type
     */
    public static final int HIGH_TYPE = 3;

    /**
     * The instance of the high type
     */
    public static final SemanticDensityValues HIGH = new SemanticDensityValues(HIGH_TYPE, "high");

    /**
     * The very high type
     */
    public static final int VERY_HIGH_TYPE = 4;

    /**
     * The instance of the very high type
     */
    public static final SemanticDensityValues VERY_HIGH = new SemanticDensityValues(VERY_HIGH_TYPE, "very high");

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

    private SemanticDensityValues(final int type, final java.lang.String value) {
        super();
        this.type = type;
        this.stringValue = value;
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate.Returns an enumeration of all possible
     * instances of SemanticDensityValues
     * 
     * @return an Enumeration over all possible instances of
     * SemanticDensityValues
     */
    public static java.util.Enumeration enumerate(
    ) {
        return _memberTable.elements();
    }

    /**
     * Method getType.Returns the type of this
     * SemanticDensityValues
     * 
     * @return the type of this SemanticDensityValues
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
     * SemanticDensityValues
     * 
     * @return the String representation of this
     * SemanticDensityValues
     */
    public java.lang.String toString(
    ) {
        return this.stringValue;
    }

    /**
     * Method valueOf.Returns a new SemanticDensityValues based on
     * the given String value.
     * 
     * @param string
     * @return the SemanticDensityValues value of parameter 'string'
     */
    public static es.pode.parseadorXML.castor.types.SemanticDensityValues valueOf(
            final java.lang.String string) {
        java.lang.Object obj = null;
        if (string != null) {
            obj = _memberTable.get(string);
        }
        if (obj == null) {
            String err = "" + string + " is not a valid SemanticDensityValues";
            throw new IllegalArgumentException(err);
        }
        return (SemanticDensityValues) obj;
    }

}
