/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1</a>, using an XML
 * Schema.
 * $Id$
 */

package es.pode.parseadorXML.oai_pmh;

/**
 * A resumptionToken may have 3 optional attributes
 *  and can be used in ListSets, ListIdentifiers, ListRecords
 *  responses.
 * 
 * @version $Revision$ $Date$
 */
public class ResumptionTokenType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * internal content storage
     */
    private java.lang.String _content = "";

    /**
     * Field _expirationDate.
     */
    private java.util.Date _expirationDate;

    /**
     * Field _completeListSize.
     */
    private long _completeListSize;

    /**
     * keeps track of state for field: _completeListSize
     */
    private boolean _has_completeListSize;

    /**
     * Field _cursor.
     */
    private long _cursor;

    /**
     * keeps track of state for field: _cursor
     */
    private boolean _has_cursor;


      //----------------/
     //- Constructors -/
    //----------------/

    public ResumptionTokenType() {
        super();
        setContent("");
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteCompleteListSize(
    ) {
        this._has_completeListSize= false;
    }

    /**
     */
    public void deleteCursor(
    ) {
        this._has_cursor= false;
    }

    /**
     * Returns the value of field 'completeListSize'.
     * 
     * @return the value of field 'CompleteListSize'.
     */
    public long getCompleteListSize(
    ) {
        return this._completeListSize;
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
     * Returns the value of field 'cursor'.
     * 
     * @return the value of field 'Cursor'.
     */
    public long getCursor(
    ) {
        return this._cursor;
    }

    /**
     * Returns the value of field 'expirationDate'.
     * 
     * @return the value of field 'ExpirationDate'.
     */
    public java.util.Date getExpirationDate(
    ) {
        return this._expirationDate;
    }

    /**
     * Method hasCompleteListSize.
     * 
     * @return true if at least one CompleteListSize has been added
     */
    public boolean hasCompleteListSize(
    ) {
        return this._has_completeListSize;
    }

    /**
     * Method hasCursor.
     * 
     * @return true if at least one Cursor has been added
     */
    public boolean hasCursor(
    ) {
        return this._has_cursor;
    }

    /**
     * Sets the value of field 'completeListSize'.
     * 
     * @param completeListSize the value of field 'completeListSize'
     */
    public void setCompleteListSize(
            final long completeListSize) {
        this._completeListSize = completeListSize;
        this._has_completeListSize = true;
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

    /**
     * Sets the value of field 'cursor'.
     * 
     * @param cursor the value of field 'cursor'.
     */
    public void setCursor(
            final long cursor) {
        this._cursor = cursor;
        this._has_cursor = true;
    }

    /**
     * Sets the value of field 'expirationDate'.
     * 
     * @param expirationDate the value of field 'expirationDate'.
     */
    public void setExpirationDate(
            final java.util.Date expirationDate) {
        this._expirationDate = expirationDate;
    }

}
