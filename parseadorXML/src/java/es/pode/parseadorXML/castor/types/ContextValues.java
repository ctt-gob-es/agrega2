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
 * Class ContextValues.
 * 
 * @version $Revision$ $Date$
 */
public class ContextValues implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The school type
     */
    public static final int SCHOOL_TYPE = 0;

    /**
     * The instance of the school type
     */
    public static final ContextValues SCHOOL = new ContextValues(SCHOOL_TYPE, "school");

    /**
     * The higher education type
     */
    public static final int HIGHER_EDUCATION_TYPE = 1;

    /**
     * The instance of the higher education type
     */
    public static final ContextValues HIGHER_EDUCATION = new ContextValues(HIGHER_EDUCATION_TYPE, "higher education");

    /**
     * The training type
     */
    public static final int TRAINING_TYPE = 2;

    /**
     * The instance of the training type
     */
    public static final ContextValues TRAINING = new ContextValues(TRAINING_TYPE, "training");

    /**
     * The other type
     */
    public static final int OTHER_TYPE = 3;

    /**
     * The instance of the other type
     */
    public static final ContextValues OTHER = new ContextValues(OTHER_TYPE, "other");

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

    private ContextValues(final int type, final java.lang.String value) {
        super();
        this.type = type;
        this.stringValue = value;
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate.Returns an enumeration of all possible
     * instances of ContextValues
     * 
     * @return an Enumeration over all possible instances of
     * ContextValues
     */
    public static java.util.Enumeration enumerate(
    ) {
        return _memberTable.elements();
    }

    /**
     * Method getType.Returns the type of this ContextValues
     * 
     * @return the type of this ContextValues
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
        members.put("school", SCHOOL);
        members.put("higher education", HIGHER_EDUCATION);
        members.put("training", TRAINING);
        members.put("other", OTHER);
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
     * ContextValues
     * 
     * @return the String representation of this ContextValues
     */
    public java.lang.String toString(
    ) {
        return this.stringValue;
    }

    /**
     * Method valueOf.Returns a new ContextValues based on the
     * given String value.
     * 
     * @param string
     * @return the ContextValues value of parameter 'string'
     */
    public static es.pode.parseadorXML.castor.types.ContextValues valueOf(
            final java.lang.String string) {
        java.lang.Object obj = null;
        if (string != null) {
            obj = _memberTable.get(string);
        }
        if (obj == null) {
            String err = "" + string + " is not a valid ContextValues";
            throw new IllegalArgumentException(err);
        }
        return (ContextValues) obj;
    }

}
