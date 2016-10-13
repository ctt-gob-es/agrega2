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
 * Class GetRecordType.
 * 
 * @version $Revision$ $Date$
 */
public class GetRecordType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _getRecordTypeRecord.
     */
    private es.pode.parseadorXML.oai_pmh.GetRecordTypeRecord _getRecordTypeRecord;


      //----------------/
     //- Constructors -/
    //----------------/

    public GetRecordType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'getRecordTypeRecord'.
     * 
     * @return the value of field 'GetRecordTypeRecord'.
     */
    public es.pode.parseadorXML.oai_pmh.GetRecordTypeRecord getGetRecordTypeRecord(
    ) {
        return this._getRecordTypeRecord;
    }

    /**
     * Sets the value of field 'getRecordTypeRecord'.
     * 
     * @param getRecordTypeRecord the value of field
     * 'getRecordTypeRecord'.
     */
    public void setGetRecordTypeRecord(
            final es.pode.parseadorXML.oai_pmh.GetRecordTypeRecord getRecordTypeRecord) {
        this._getRecordTypeRecord = getRecordTypeRecord;
    }

}
