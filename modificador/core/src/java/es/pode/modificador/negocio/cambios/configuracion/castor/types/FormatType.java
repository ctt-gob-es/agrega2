/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1</a>, using an XML
 * Schema.
 * $Id$
 */

package es.pode.modificador.negocio.cambios.configuracion.castor.types;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.util.Hashtable;

/**
 * Class FormatType.
 * 
 * @version $Revision$ $Date$
 */
public class FormatType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The SCORM2004 type
     */
    public static final int SCORM2004_TYPE = 0;

    /**
     * The instance of the SCORM2004 type
     */
    public static final FormatType SCORM2004 = new FormatType(SCORM2004_TYPE, "SCORM2004");

    /**
     * The SCORM2004SS type
     */
    public static final int SCORM2004SS_TYPE = 1;

    /**
     * The instance of the SCORM2004SS type
     */
    public static final FormatType SCORM2004SS = new FormatType(SCORM2004SS_TYPE, "SCORM2004SS");

    /**
     * The SCORM12 type
     */
    public static final int SCORM12_TYPE = 2;

    /**
     * The instance of the SCORM12 type
     */
    public static final FormatType SCORM12 = new FormatType(SCORM12_TYPE, "SCORM12");

    /**
     * The IMSCP type
     */
    public static final int IMSCP_TYPE = 3;

    /**
     * The instance of the IMSCP type
     */
    public static final FormatType IMSCP = new FormatType(IMSCP_TYPE, "IMSCP");

    /**
     * The HTML type
     */
    public static final int HTML_TYPE = 4;

    /**
     * The instance of the HTML type
     */
    public static final FormatType HTML = new FormatType(HTML_TYPE, "HTML");

    /**
     * The CONTENIDOS type
     */
    public static final int CONTENIDOS_TYPE = 5;

    /**
     * The instance of the CONTENIDOS type
     */
    public static final FormatType CONTENIDOS = new FormatType(CONTENIDOS_TYPE, "CONTENIDOS");

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

    private FormatType(final int type, final java.lang.String value) {
        super();
        this.type = type;
        this.stringValue = value;
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate.Returns an enumeration of all possible
     * instances of FormatType
     * 
     * @return an Enumeration over all possible instances of
     * FormatType
     */
    public static java.util.Enumeration enumerate(
    ) {
        return _memberTable.elements();
    }

    /**
     * Method getType.Returns the type of this FormatType
     * 
     * @return the type of this FormatType
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
        members.put("SCORM2004", SCORM2004);
        members.put("SCORM2004SS", SCORM2004SS);
        members.put("SCORM12", SCORM12);
        members.put("IMSCP", IMSCP);
        members.put("HTML", HTML);
        members.put("CONTENIDOS", CONTENIDOS);
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
     * FormatType
     * 
     * @return the String representation of this FormatType
     */
    public java.lang.String toString(
    ) {
        return this.stringValue;
    }

    /**
     * Method valueOf.Returns a new FormatType based on the given
     * String value.
     * 
     * @param string
     * @return the FormatType value of parameter 'string'
     */
    public static es.pode.modificador.negocio.cambios.configuracion.castor.types.FormatType valueOf(
            final java.lang.String string) {
        java.lang.Object obj = null;
        if (string != null) {
            obj = _memberTable.get(string);
        }
        if (obj == null) {
            String err = "" + string + " is not a valid FormatType";
            throw new IllegalArgumentException(err);
        }
        return (FormatType) obj;
    }

}
