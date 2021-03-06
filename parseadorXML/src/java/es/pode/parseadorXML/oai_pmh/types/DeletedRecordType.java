/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1</a>, using an XML
 * Schema.
 * $Id$
 */

package es.pode.parseadorXML.oai_pmh.types;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.util.Hashtable;

/**
 * Class DeletedRecordType.
 * 
 * @version $Revision$ $Date$
 */
public class DeletedRecordType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The no type
     */
    public static final int NO_TYPE = 0;

    /**
     * The instance of the no type
     */
    public static final DeletedRecordType NO = new DeletedRecordType(NO_TYPE, "no");

    /**
     * The persistent type
     */
    public static final int PERSISTENT_TYPE = 1;

    /**
     * The instance of the persistent type
     */
    public static final DeletedRecordType PERSISTENT = new DeletedRecordType(PERSISTENT_TYPE, "persistent");

    /**
     * The transient type
     */
    public static final int TRANSIENT_TYPE = 2;

    /**
     * The instance of the transient type
     */
    public static final DeletedRecordType TRANSIENT = new DeletedRecordType(TRANSIENT_TYPE, "transient");

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

    private DeletedRecordType(final int type, final java.lang.String value) {
        super();
        this.type = type;
        this.stringValue = value;
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate.Returns an enumeration of all possible
     * instances of DeletedRecordType
     * 
     * @return an Enumeration over all possible instances of
     * DeletedRecordType
     */
    public static java.util.Enumeration enumerate(
    ) {
        return _memberTable.elements();
    }

    /**
     * Method getType.Returns the type of this DeletedRecordType
     * 
     * @return the type of this DeletedRecordType
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
        members.put("no", NO);
        members.put("persistent", PERSISTENT);
        members.put("transient", TRANSIENT);
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
     * DeletedRecordType
     * 
     * @return the String representation of this DeletedRecordType
     */
    public java.lang.String toString(
    ) {
        return this.stringValue;
    }

    /**
     * Method valueOf.Returns a new DeletedRecordType based on the
     * given String value.
     * 
     * @param string
     * @return the DeletedRecordType value of parameter 'string'
     */
    public static es.pode.parseadorXML.oai_pmh.types.DeletedRecordType valueOf(
            final java.lang.String string) {
        java.lang.Object obj = null;
        if (string != null) {
            obj = _memberTable.get(string);
        }
        if (obj == null) {
            String err = "" + string + " is not a valid DeletedRecordType";
            throw new IllegalArgumentException(err);
        }
        return (DeletedRecordType) obj;
    }

}
