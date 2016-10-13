/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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
 * Class TermType.
 * 
 * @version $Revision$ $Date$
 */
public class TermType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The taxonomia type
     */
    public static final int TAXONOMIA_TYPE = 0;

    /**
     * The instance of the taxonomia type
     */
    public static final TermType TAXONOMIA = new TermType(TAXONOMIA_TYPE, "taxonomia");

    /**
     * The otro type
     */
    public static final int OTRO_TYPE = 1;

    /**
     * The instance of the otro type
     */
    public static final TermType OTRO = new TermType(OTRO_TYPE, "otro");

    /**
     * The etb type
     */
    public static final int ETB_TYPE = 2;

    /**
     * The instance of the etb type
     */
    public static final TermType ETB = new TermType(ETB_TYPE, "etb");

    /**
     * The arbol-curricular type
     */
    public static final int ARBOL_CURRICULAR_TYPE = 3;

    /**
     * The instance of the arbol-curricular type
     */
    public static final TermType ARBOL_CURRICULAR = new TermType(ARBOL_CURRICULAR_TYPE, "arbol-curricular");

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

    private TermType(final int type, final java.lang.String value) {
        super();
        this.type = type;
        this.stringValue = value;
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate.Returns an enumeration of all possible
     * instances of TermType
     * 
     * @return an Enumeration over all possible instances of TermTyp
     */
    public static java.util.Enumeration enumerate(
    ) {
        return _memberTable.elements();
    }

    /**
     * Method getType.Returns the type of this TermType
     * 
     * @return the type of this TermType
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
        members.put("taxonomia", TAXONOMIA);
        members.put("otro", OTRO);
        members.put("etb", ETB);
        members.put("arbol-curricular", ARBOL_CURRICULAR);
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
     * TermType
     * 
     * @return the String representation of this TermType
     */
    public java.lang.String toString(
    ) {
        return this.stringValue;
    }

    /**
     * Method valueOf.Returns a new TermType based on the given
     * String value.
     * 
     * @param string
     * @return the TermType value of parameter 'string'
     */
    public static es.pode.modificador.negocio.cambios.configuracion.castor.types.TermType valueOf(
            final java.lang.String string) {
        java.lang.Object obj = null;
        if (string != null) {
            obj = _memberTable.get(string);
        }
        if (obj == null) {
            String err = "" + string + " is not a valid TermType";
            throw new IllegalArgumentException(err);
        }
        return (TermType) obj;
    }

}
