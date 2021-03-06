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
 * Class CopyrightAndOtherRestrictionsValues.
 * 
 * @version $Revision$ $Date$
 */
public class CopyrightAndOtherRestrictionsValues implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The yes type
     */
    public static final int YES_TYPE = 0;

    /**
     * The instance of the yes type
     */
    public static final CopyrightAndOtherRestrictionsValues YES = new CopyrightAndOtherRestrictionsValues(YES_TYPE, "yes");

    /**
     * The no type
     */
    public static final int NO_TYPE = 1;

    /**
     * The instance of the no type
     */
    public static final CopyrightAndOtherRestrictionsValues NO = new CopyrightAndOtherRestrictionsValues(NO_TYPE, "no");

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

    private CopyrightAndOtherRestrictionsValues(final int type, final java.lang.String value) {
        super();
        this.type = type;
        this.stringValue = value;
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate.Returns an enumeration of all possible
     * instances of CopyrightAndOtherRestrictionsValues
     * 
     * @return an Enumeration over all possible instances of
     * CopyrightAndOtherRestrictionsValues
     */
    public static java.util.Enumeration enumerate(
    ) {
        return _memberTable.elements();
    }

    /**
     * Method getType.Returns the type of this
     * CopyrightAndOtherRestrictionsValues
     * 
     * @return the type of this CopyrightAndOtherRestrictionsValues
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
        members.put("yes", YES);
        members.put("no", NO);
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
     * CopyrightAndOtherRestrictionsValues
     * 
     * @return the String representation of this
     * CopyrightAndOtherRestrictionsValues
     */
    public java.lang.String toString(
    ) {
        return this.stringValue;
    }

    /**
     * Method valueOf.Returns a new
     * CopyrightAndOtherRestrictionsValues based on the given
     * String value.
     * 
     * @param string
     * @return the CopyrightAndOtherRestrictionsValues value of
     * parameter 'string'
     */
    public static es.pode.parseadorXML.castor.types.CopyrightAndOtherRestrictionsValues valueOf(
            final java.lang.String string) {
        java.lang.Object obj = null;
        if (string != null) {
            obj = _memberTable.get(string);
        }
        if (obj == null) {
            String err = "" + string + " is not a valid CopyrightAndOtherRestrictionsValues";
            throw new IllegalArgumentException(err);
        }
        return (CopyrightAndOtherRestrictionsValues) obj;
    }

}
