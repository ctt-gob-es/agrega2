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

package es.pode.parseadorXML.oai_pmh;

/**
 * Class OAIPMHerrorType.
 * 
 * @version $Revision$ $Date$
 */
public class OAIPMHerrorType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * internal content storage
     */
    private java.lang.String _content = "";

    /**
     * Field _code.
     */
    private es.pode.parseadorXML.oai_pmh.types.OAIPMHerrorcodeType _code;


      //----------------/
     //- Constructors -/
    //----------------/

    public OAIPMHerrorType() {
        super();
        setContent("");
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'code'.
     * 
     * @return the value of field 'Code'.
     */
    public es.pode.parseadorXML.oai_pmh.types.OAIPMHerrorcodeType getCode(
    ) {
        return this._code;
    }

    /**
     * Returns the value of field 'content'. The field 'content'
     * has the following description: internal content storage
     * 
     * @return the value of field 'Content'.
     */
    public java.lang.String getContent(
    ) {
        return this._content;
    }

    /**
     * Sets the value of field 'code'.
     * 
     * @param code the value of field 'code'.
     */
    public void setCode(
            final es.pode.parseadorXML.oai_pmh.types.OAIPMHerrorcodeType code) {
        this._code = code;
    }

    /**
     * Sets the value of field 'content'. The field 'content' has
     * the following description: internal content storage
     * 
     * @param content the value of field 'content'.
     */
    public void setContent(
            final java.lang.String content) {
        this._content = content;
    }

}
