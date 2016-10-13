/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1</a>, using an XML
 * Schema.
 * $Id$
 */

package es.pode.parseadorXML.oai_pmh;

/**
 * Class OAIPMHtype.
 * 
 * @version $Revision$ $Date$
 */
public class OAIPMHtype implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _responseDate.
     */
    private java.util.Date _responseDate;

    /**
     * Field _request.
     */
    private es.pode.parseadorXML.oai_pmh.Request _request;

    /**
     * Field _OAIPMHtypeChoice.
     */
    private es.pode.parseadorXML.oai_pmh.OAIPMHtypeChoice _OAIPMHtypeChoice;


      //----------------/
     //- Constructors -/
    //----------------/

    public OAIPMHtype() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'OAIPMHtypeChoice'.
     * 
     * @return the value of field 'OAIPMHtypeChoice'.
     */
    public es.pode.parseadorXML.oai_pmh.OAIPMHtypeChoice getOAIPMHtypeChoice(
    ) {
        return this._OAIPMHtypeChoice;
    }

    /**
     * Returns the value of field 'request'.
     * 
     * @return the value of field 'Request'.
     */
    public es.pode.parseadorXML.oai_pmh.Request getRequest(
    ) {
        return this._request;
    }

    /**
     * Returns the value of field 'responseDate'.
     * 
     * @return the value of field 'ResponseDate'.
     */
    public java.util.Date getResponseDate(
    ) {
        return this._responseDate;
    }

    /**
     * Sets the value of field 'OAIPMHtypeChoice'.
     * 
     * @param OAIPMHtypeChoice the value of field 'OAIPMHtypeChoice'
     */
    public void setOAIPMHtypeChoice(
            final es.pode.parseadorXML.oai_pmh.OAIPMHtypeChoice OAIPMHtypeChoice) {
        this._OAIPMHtypeChoice = OAIPMHtypeChoice;
    }

    /**
     * Sets the value of field 'request'.
     * 
     * @param request the value of field 'request'.
     */
    public void setRequest(
            final es.pode.parseadorXML.oai_pmh.Request request) {
        this._request = request;
    }

    /**
     * Sets the value of field 'responseDate'.
     * 
     * @param responseDate the value of field 'responseDate'.
     */
    public void setResponseDate(
            final java.util.Date responseDate) {
        this._responseDate = responseDate;
    }

}
