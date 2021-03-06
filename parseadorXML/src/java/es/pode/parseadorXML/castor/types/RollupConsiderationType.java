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
 * Class RollupConsiderationType.
 * 
 * @version $Revision$ $Date$
 */
public class RollupConsiderationType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The always type
     */
    public static final int ALWAYS_TYPE = 0;

    /**
     * The instance of the always type
     */
    public static final RollupConsiderationType ALWAYS = new RollupConsiderationType(ALWAYS_TYPE, "always");

    /**
     * The ifAttempted type
     */
    public static final int IFATTEMPTED_TYPE = 1;

    /**
     * The instance of the ifAttempted type
     */
    public static final RollupConsiderationType IFATTEMPTED = new RollupConsiderationType(IFATTEMPTED_TYPE, "ifAttempted");

    /**
     * The ifNotSkipped type
     */
    public static final int IFNOTSKIPPED_TYPE = 2;

    /**
     * The instance of the ifNotSkipped type
     */
    public static final RollupConsiderationType IFNOTSKIPPED = new RollupConsiderationType(IFNOTSKIPPED_TYPE, "ifNotSkipped");

    /**
     * The ifNotSuspended type
     */
    public static final int IFNOTSUSPENDED_TYPE = 3;

    /**
     * The instance of the ifNotSuspended type
     */
    public static final RollupConsiderationType IFNOTSUSPENDED = new RollupConsiderationType(IFNOTSUSPENDED_TYPE, "ifNotSuspended");

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

    private RollupConsiderationType(final int type, final java.lang.String value) {
        super();
        this.type = type;
        this.stringValue = value;
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate.Returns an enumeration of all possible
     * instances of RollupConsiderationType
     * 
     * @return an Enumeration over all possible instances of
     * RollupConsiderationType
     */
    public static java.util.Enumeration enumerate(
    ) {
        return _memberTable.elements();
    }

    /**
     * Method getType.Returns the type of this
     * RollupConsiderationType
     * 
     * @return the type of this RollupConsiderationType
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
        members.put("always", ALWAYS);
        members.put("ifAttempted", IFATTEMPTED);
        members.put("ifNotSkipped", IFNOTSKIPPED);
        members.put("ifNotSuspended", IFNOTSUSPENDED);
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
     * RollupConsiderationType
     * 
     * @return the String representation of this
     * RollupConsiderationType
     */
    public java.lang.String toString(
    ) {
        return this.stringValue;
    }

    /**
     * Method valueOf.Returns a new RollupConsiderationType based
     * on the given String value.
     * 
     * @param string
     * @return the RollupConsiderationType value of parameter
     * 'string'
     */
    public static es.pode.parseadorXML.castor.types.RollupConsiderationType valueOf(
            final java.lang.String string) {
        java.lang.Object obj = null;
        if (string != null) {
            obj = _memberTable.get(string);
        }
        if (obj == null) {
            String err = "" + string + " is not a valid RollupConsiderationType";
            throw new IllegalArgumentException(err);
        }
        return (RollupConsiderationType) obj;
    }

}
