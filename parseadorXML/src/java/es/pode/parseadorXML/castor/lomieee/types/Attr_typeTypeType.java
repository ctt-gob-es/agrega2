/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.5</a>, using an XML
 * Schema.
 * $Id$
 */

package es.pode.parseadorXML.castor.lomieee.types;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.util.Hashtable;

/**
 * Class Attr_typeTypeType.
 * 
 * @version $Revision$ $Date$
 */
public class Attr_typeTypeType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The URI type
     */
    public static final int URI_TYPE = 0;

    /**
     * The instance of the URI type
     */
    public static final Attr_typeTypeType URI = new Attr_typeTypeType(URI_TYPE, "URI");

    /**
     * The TEXT type
     */
    public static final int TEXT_TYPE = 1;

    /**
     * The instance of the TEXT type
     */
    public static final Attr_typeTypeType TEXT = new Attr_typeTypeType(TEXT_TYPE, "TEXT");

    /**
     * Field _memberTable
     */
    private static java.util.Hashtable _memberTable = init();

    /**
     * Field type
     */
    private int type = -1;

    /**
     * Field stringValue
     */
    private java.lang.String stringValue = null;


      //----------------/
     //- Constructors -/
    //----------------/

    private Attr_typeTypeType(int type, java.lang.String value) 
     {
        super();
        this.type = type;
        this.stringValue = value;
    } //-- es.pode.parseadorXML.castor.lomieee.types.Attr_typeTypeType(int, java.lang.String)


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate
     * 
     * Returns an enumeration of all possible instances of
     * Attr_typeTypeType
     * 
     * @return an Enumeration over all possible instances of
     * Attr_typeTypeType
     */
    public static java.util.Enumeration enumerate()
    {
        return _memberTable.elements();
    } //-- java.util.Enumeration enumerate() 

    /**
     * Method getType
     * 
     * Returns the type of this Attr_typeTypeType
     * 
     * @return the type of this Attr_typeTypeType
     */
    public int getType()
    {
        return this.type;
    } //-- int getType() 

    /**
     * Method init
     * 
     * 
     * 
     * @return the initialized Hashtable for the member table
     */
    private static java.util.Hashtable init()
    {
        Hashtable members = new Hashtable();
        members.put("URI", URI);
        members.put("TEXT", TEXT);
        return members;
    } //-- java.util.Hashtable init() 

    /**
     * Method readResolve
     * 
     *  will be called during deserialization to replace the
     * deserialized object with the correct constant instance.
     * 
     * @return this deserialized object
     */
    private java.lang.Object readResolve()
    {
        return valueOf(this.stringValue);
    } //-- java.lang.Object readResolve() 

    /**
     * Method toString
     * 
     * Returns the String representation of this Attr_typeTypeType
     * 
     * @return the String representation of this Attr_typeTypeType
     */
    public java.lang.String toString()
    {
        return this.stringValue;
    } //-- java.lang.String toString() 

    /**
     * Method valueOf
     * 
     * Returns a new Attr_typeTypeType based on the given String
     * value.
     * 
     * @param string
     * @return the Attr_typeTypeType value of parameter 'string'
     */
    public static es.pode.parseadorXML.castor.lomieee.types.Attr_typeTypeType valueOf(java.lang.String string)
    {
        java.lang.Object obj = null;
        if (string != null) obj = _memberTable.get(string);
        if (obj == null) {
            String err = "'" + string + "' is not a valid Attr_typeTypeType";
            throw new IllegalArgumentException(err);
        }
        return (Attr_typeTypeType) obj;
    } //-- es.pode.parseadorXML.castor.lomieee.types.Attr_typeTypeType valueOf(java.lang.String) 

}
