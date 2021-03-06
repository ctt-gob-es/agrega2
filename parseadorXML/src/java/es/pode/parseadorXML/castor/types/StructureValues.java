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
 * Class StructureValues.
 * 
 * @version $Revision$ $Date$
 */
public class StructureValues implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The atomic type
     */
    public static final int ATOMIC_TYPE = 0;

    /**
     * The instance of the atomic type
     */
    public static final StructureValues ATOMIC = new StructureValues(ATOMIC_TYPE, "atomic");

    /**
     * The collection type
     */
    public static final int COLLECTION_TYPE = 1;

    /**
     * The instance of the collection type
     */
    public static final StructureValues COLLECTION = new StructureValues(COLLECTION_TYPE, "collection");

    /**
     * The networked type
     */
    public static final int NETWORKED_TYPE = 2;

    /**
     * The instance of the networked type
     */
    public static final StructureValues NETWORKED = new StructureValues(NETWORKED_TYPE, "networked");

    /**
     * The hierarchical type
     */
    public static final int HIERARCHICAL_TYPE = 3;

    /**
     * The instance of the hierarchical type
     */
    public static final StructureValues HIERARCHICAL = new StructureValues(HIERARCHICAL_TYPE, "hierarchical");

    /**
     * The linear type
     */
    public static final int LINEAR_TYPE = 4;

    /**
     * The instance of the linear type
     */
    public static final StructureValues LINEAR = new StructureValues(LINEAR_TYPE, "linear");

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

    private StructureValues(final int type, final java.lang.String value) {
        super();
        this.type = type;
        this.stringValue = value;
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate.Returns an enumeration of all possible
     * instances of StructureValues
     * 
     * @return an Enumeration over all possible instances of
     * StructureValues
     */
    public static java.util.Enumeration enumerate(
    ) {
        return _memberTable.elements();
    }

    /**
     * Method getType.Returns the type of this StructureValues
     * 
     * @return the type of this StructureValues
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
        members.put("atomic", ATOMIC);
        members.put("collection", COLLECTION);
        members.put("networked", NETWORKED);
        members.put("hierarchical", HIERARCHICAL);
        members.put("linear", LINEAR);
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
     * StructureValues
     * 
     * @return the String representation of this StructureValues
     */
    public java.lang.String toString(
    ) {
        return this.stringValue;
    }

    /**
     * Method valueOf.Returns a new StructureValues based on the
     * given String value.
     * 
     * @param string
     * @return the StructureValues value of parameter 'string'
     */
    public static es.pode.parseadorXML.castor.types.StructureValues valueOf(
            final java.lang.String string) {
        java.lang.Object obj = null;
        if (string != null) {
            obj = _memberTable.get(string);
        }
        if (obj == null) {
            String err = "" + string + " is not a valid StructureValues";
            throw new IllegalArgumentException(err);
        }
        return (StructureValues) obj;
    }

}
