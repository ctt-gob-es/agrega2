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
 * Class IntendedEndUserRoleValues.
 * 
 * @version $Revision$ $Date$
 */
public class IntendedEndUserRoleValues implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The teacher type
     */
    public static final int TEACHER_TYPE = 0;

    /**
     * The instance of the teacher type
     */
    public static final IntendedEndUserRoleValues TEACHER = new IntendedEndUserRoleValues(TEACHER_TYPE, "teacher");

    /**
     * The author type
     */
    public static final int AUTHOR_TYPE = 1;

    /**
     * The instance of the author type
     */
    public static final IntendedEndUserRoleValues AUTHOR = new IntendedEndUserRoleValues(AUTHOR_TYPE, "author");

    /**
     * The learner type
     */
    public static final int LEARNER_TYPE = 2;

    /**
     * The instance of the learner type
     */
    public static final IntendedEndUserRoleValues LEARNER = new IntendedEndUserRoleValues(LEARNER_TYPE, "learner");

    /**
     * The manager type
     */
    public static final int MANAGER_TYPE = 3;

    /**
     * The instance of the manager type
     */
    public static final IntendedEndUserRoleValues MANAGER = new IntendedEndUserRoleValues(MANAGER_TYPE, "manager");

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

    private IntendedEndUserRoleValues(final int type, final java.lang.String value) {
        super();
        this.type = type;
        this.stringValue = value;
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate.Returns an enumeration of all possible
     * instances of IntendedEndUserRoleValues
     * 
     * @return an Enumeration over all possible instances of
     * IntendedEndUserRoleValues
     */
    public static java.util.Enumeration enumerate(
    ) {
        return _memberTable.elements();
    }

    /**
     * Method getType.Returns the type of this
     * IntendedEndUserRoleValues
     * 
     * @return the type of this IntendedEndUserRoleValues
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
        members.put("teacher", TEACHER);
        members.put("author", AUTHOR);
        members.put("learner", LEARNER);
        members.put("manager", MANAGER);
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
     * IntendedEndUserRoleValues
     * 
     * @return the String representation of this
     * IntendedEndUserRoleValues
     */
    public java.lang.String toString(
    ) {
        return this.stringValue;
    }

    /**
     * Method valueOf.Returns a new IntendedEndUserRoleValues based
     * on the given String value.
     * 
     * @param string
     * @return the IntendedEndUserRoleValues value of parameter
     * 'string'
     */
    public static es.pode.parseadorXML.castor.types.IntendedEndUserRoleValues valueOf(
            final java.lang.String string) {
        java.lang.Object obj = null;
        if (string != null) {
            obj = _memberTable.get(string);
        }
        if (obj == null) {
            String err = "" + string + " is not a valid IntendedEndUserRoleValues";
            throw new IllegalArgumentException(err);
        }
        return (IntendedEndUserRoleValues) obj;
    }

}
