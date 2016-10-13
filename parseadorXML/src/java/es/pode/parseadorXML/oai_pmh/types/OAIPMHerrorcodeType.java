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
 * Class OAIPMHerrorcodeType.
 * 
 * @version $Revision$ $Date$
 */
public class OAIPMHerrorcodeType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The cannotDisseminateFormat type
     */
    public static final int CANNOTDISSEMINATEFORMAT_TYPE = 0;

    /**
     * The instance of the cannotDisseminateFormat type
     */
    public static final OAIPMHerrorcodeType CANNOTDISSEMINATEFORMAT = new OAIPMHerrorcodeType(CANNOTDISSEMINATEFORMAT_TYPE, "cannotDisseminateFormat");

    /**
     * The idDoesNotExist type
     */
    public static final int IDDOESNOTEXIST_TYPE = 1;

    /**
     * The instance of the idDoesNotExist type
     */
    public static final OAIPMHerrorcodeType IDDOESNOTEXIST = new OAIPMHerrorcodeType(IDDOESNOTEXIST_TYPE, "idDoesNotExist");

    /**
     * The badArgument type
     */
    public static final int BADARGUMENT_TYPE = 2;

    /**
     * The instance of the badArgument type
     */
    public static final OAIPMHerrorcodeType BADARGUMENT = new OAIPMHerrorcodeType(BADARGUMENT_TYPE, "badArgument");

    /**
     * The badVerb type
     */
    public static final int BADVERB_TYPE = 3;

    /**
     * The instance of the badVerb type
     */
    public static final OAIPMHerrorcodeType BADVERB = new OAIPMHerrorcodeType(BADVERB_TYPE, "badVerb");

    /**
     * The noMetadataFormats type
     */
    public static final int NOMETADATAFORMATS_TYPE = 4;

    /**
     * The instance of the noMetadataFormats type
     */
    public static final OAIPMHerrorcodeType NOMETADATAFORMATS = new OAIPMHerrorcodeType(NOMETADATAFORMATS_TYPE, "noMetadataFormats");

    /**
     * The noRecordsMatch type
     */
    public static final int NORECORDSMATCH_TYPE = 5;

    /**
     * The instance of the noRecordsMatch type
     */
    public static final OAIPMHerrorcodeType NORECORDSMATCH = new OAIPMHerrorcodeType(NORECORDSMATCH_TYPE, "noRecordsMatch");

    /**
     * The badResumptionToken type
     */
    public static final int BADRESUMPTIONTOKEN_TYPE = 6;

    /**
     * The instance of the badResumptionToken type
     */
    public static final OAIPMHerrorcodeType BADRESUMPTIONTOKEN = new OAIPMHerrorcodeType(BADRESUMPTIONTOKEN_TYPE, "badResumptionToken");

    /**
     * The noSetHierarchy type
     */
    public static final int NOSETHIERARCHY_TYPE = 7;

    /**
     * The instance of the noSetHierarchy type
     */
    public static final OAIPMHerrorcodeType NOSETHIERARCHY = new OAIPMHerrorcodeType(NOSETHIERARCHY_TYPE, "noSetHierarchy");

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

    private OAIPMHerrorcodeType(final int type, final java.lang.String value) {
        super();
        this.type = type;
        this.stringValue = value;
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate.Returns an enumeration of all possible
     * instances of OAIPMHerrorcodeType
     * 
     * @return an Enumeration over all possible instances of
     * OAIPMHerrorcodeType
     */
    public static java.util.Enumeration enumerate(
    ) {
        return _memberTable.elements();
    }

    /**
     * Method getType.Returns the type of this OAIPMHerrorcodeType
     * 
     * @return the type of this OAIPMHerrorcodeType
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
        members.put("cannotDisseminateFormat", CANNOTDISSEMINATEFORMAT);
        members.put("idDoesNotExist", IDDOESNOTEXIST);
        members.put("badArgument", BADARGUMENT);
        members.put("badVerb", BADVERB);
        members.put("noMetadataFormats", NOMETADATAFORMATS);
        members.put("noRecordsMatch", NORECORDSMATCH);
        members.put("badResumptionToken", BADRESUMPTIONTOKEN);
        members.put("noSetHierarchy", NOSETHIERARCHY);
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
     * OAIPMHerrorcodeType
     * 
     * @return the String representation of this OAIPMHerrorcodeType
     */
    public java.lang.String toString(
    ) {
        return this.stringValue;
    }

    /**
     * Method valueOf.Returns a new OAIPMHerrorcodeType based on
     * the given String value.
     * 
     * @param string
     * @return the OAIPMHerrorcodeType value of parameter 'string'
     */
    public static es.pode.parseadorXML.oai_pmh.types.OAIPMHerrorcodeType valueOf(
            final java.lang.String string) {
        java.lang.Object obj = null;
        if (string != null) {
            obj = _memberTable.get(string);
        }
        if (obj == null) {
            String err = "" + string + " is not a valid OAIPMHerrorcodeType";
            throw new IllegalArgumentException(err);
        }
        return (OAIPMHerrorcodeType) obj;
    }

}
