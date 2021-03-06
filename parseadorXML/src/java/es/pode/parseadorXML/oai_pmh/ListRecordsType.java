/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1</a>, using an XML
 * Schema.
 * $Id$
 */

package es.pode.parseadorXML.oai_pmh;

/**
 * Class ListRecordsType.
 * 
 * @version $Revision$ $Date$
 */
public class ListRecordsType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _listRecordsTypeRecordList.
     */
    private java.util.List _listRecordsTypeRecordList;

    /**
     * Field _listRecordsTypeResumptionToken.
     */
    private es.pode.parseadorXML.oai_pmh.ListRecordsTypeResumptionToken _listRecordsTypeResumptionToken;


      //----------------/
     //- Constructors -/
    //----------------/

    public ListRecordsType() {
        super();
        this._listRecordsTypeRecordList = new java.util.ArrayList();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vListRecordsTypeRecord
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addListRecordsTypeRecord(
            final es.pode.parseadorXML.oai_pmh.ListRecordsTypeRecord vListRecordsTypeRecord)
    throws java.lang.IndexOutOfBoundsException {
        this._listRecordsTypeRecordList.add(vListRecordsTypeRecord);
    }

    /**
     * 
     * 
     * @param index
     * @param vListRecordsTypeRecord
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addListRecordsTypeRecord(
            final int index,
            final es.pode.parseadorXML.oai_pmh.ListRecordsTypeRecord vListRecordsTypeRecord)
    throws java.lang.IndexOutOfBoundsException {
        this._listRecordsTypeRecordList.add(index, vListRecordsTypeRecord);
    }

    /**
     * Method enumerateListRecordsTypeRecord.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration enumerateListRecordsTypeRecord(
    ) {
        return java.util.Collections.enumeration(this._listRecordsTypeRecordList);
    }

    /**
     * Method getListRecordsTypeRecord.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * es.pode.parseadorXML.oai_pmh.ListRecordsTypeRecord at the
     * given index
     */
    public es.pode.parseadorXML.oai_pmh.ListRecordsTypeRecord getListRecordsTypeRecord(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._listRecordsTypeRecordList.size()) {
            throw new IndexOutOfBoundsException("getListRecordsTypeRecord: Index value '" + index + "' not in range [0.." + (this._listRecordsTypeRecordList.size() - 1) + "]");
        }
        
        return (es.pode.parseadorXML.oai_pmh.ListRecordsTypeRecord) _listRecordsTypeRecordList.get(index);
    }

    /**
     * Method getListRecordsTypeRecord.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public es.pode.parseadorXML.oai_pmh.ListRecordsTypeRecord[] getListRecordsTypeRecord(
    ) {
        es.pode.parseadorXML.oai_pmh.ListRecordsTypeRecord[] array = new es.pode.parseadorXML.oai_pmh.ListRecordsTypeRecord[0];
        return (es.pode.parseadorXML.oai_pmh.ListRecordsTypeRecord[]) this._listRecordsTypeRecordList.toArray(array);
    }

    /**
     * Method getListRecordsTypeRecordCount.
     * 
     * @return the size of this collection
     */
    public int getListRecordsTypeRecordCount(
    ) {
        return this._listRecordsTypeRecordList.size();
    }

    /**
     * Returns the value of field 'listRecordsTypeResumptionToken'.
     * 
     * @return the value of field 'ListRecordsTypeResumptionToken'.
     */
    public es.pode.parseadorXML.oai_pmh.ListRecordsTypeResumptionToken getListRecordsTypeResumptionToken(
    ) {
        return this._listRecordsTypeResumptionToken;
    }

    /**
     * Method iterateListRecordsTypeRecord.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator iterateListRecordsTypeRecord(
    ) {
        return this._listRecordsTypeRecordList.iterator();
    }

    /**
     */
    public void removeAllListRecordsTypeRecord(
    ) {
        this._listRecordsTypeRecordList.clear();
    }

    /**
     * Method removeListRecordsTypeRecord.
     * 
     * @param vListRecordsTypeRecord
     * @return true if the object was removed from the collection.
     */
    public boolean removeListRecordsTypeRecord(
            final es.pode.parseadorXML.oai_pmh.ListRecordsTypeRecord vListRecordsTypeRecord) {
        boolean removed = _listRecordsTypeRecordList.remove(vListRecordsTypeRecord);
        return removed;
    }

    /**
     * Method removeListRecordsTypeRecordAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public es.pode.parseadorXML.oai_pmh.ListRecordsTypeRecord removeListRecordsTypeRecordAt(
            final int index) {
        java.lang.Object obj = this._listRecordsTypeRecordList.remove(index);
        return (es.pode.parseadorXML.oai_pmh.ListRecordsTypeRecord) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vListRecordsTypeRecord
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setListRecordsTypeRecord(
            final int index,
            final es.pode.parseadorXML.oai_pmh.ListRecordsTypeRecord vListRecordsTypeRecord)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._listRecordsTypeRecordList.size()) {
            throw new IndexOutOfBoundsException("setListRecordsTypeRecord: Index value '" + index + "' not in range [0.." + (this._listRecordsTypeRecordList.size() - 1) + "]");
        }
        
        this._listRecordsTypeRecordList.set(index, vListRecordsTypeRecord);
    }

    /**
     * 
     * 
     * @param vListRecordsTypeRecordArray
     */
    public void setListRecordsTypeRecord(
            final es.pode.parseadorXML.oai_pmh.ListRecordsTypeRecord[] vListRecordsTypeRecordArray) {
        //-- copy array
        _listRecordsTypeRecordList.clear();
        
        for (int i = 0; i < vListRecordsTypeRecordArray.length; i++) {
                this._listRecordsTypeRecordList.add(vListRecordsTypeRecordArray[i]);
        }
    }

    /**
     * Sets the value of field 'listRecordsTypeResumptionToken'.
     * 
     * @param listRecordsTypeResumptionToken the value of field
     * 'listRecordsTypeResumptionToken'.
     */
    public void setListRecordsTypeResumptionToken(
            final es.pode.parseadorXML.oai_pmh.ListRecordsTypeResumptionToken listRecordsTypeResumptionToken) {
        this._listRecordsTypeResumptionToken = listRecordsTypeResumptionToken;
    }

}
