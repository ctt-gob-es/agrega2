/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1</a>, using an XML
 * Schema.
 * $Id$
 */

package es.pode.parseadorXML.oai_pmh;

/**
 * A record has a header, a metadata part, and
 *  an optional about container
 * 
 * @version $Revision$ $Date$
 */
public class RecordType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _recordTypeHeader.
     */
    private es.pode.parseadorXML.oai_pmh.RecordTypeHeader _recordTypeHeader;

    /**
     * Field _metadata.
     */
    private es.pode.parseadorXML.oai_pmh.Metadata _metadata;

    /**
     * Field _aboutList.
     */
    private java.util.List _aboutList;


      //----------------/
     //- Constructors -/
    //----------------/

    public RecordType() {
        super();
        this._aboutList = new java.util.ArrayList();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vAbout
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAbout(
            final es.pode.parseadorXML.oai_pmh.About vAbout)
    throws java.lang.IndexOutOfBoundsException {
        this._aboutList.add(vAbout);
    }

    /**
     * 
     * 
     * @param index
     * @param vAbout
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAbout(
            final int index,
            final es.pode.parseadorXML.oai_pmh.About vAbout)
    throws java.lang.IndexOutOfBoundsException {
        this._aboutList.add(index, vAbout);
    }

    /**
     * Method enumerateAbout.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration enumerateAbout(
    ) {
        return java.util.Collections.enumeration(this._aboutList);
    }

    /**
     * Method getAbout.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the es.pode.parseadorXML.oai_pmh.About
     * at the given index
     */
    public es.pode.parseadorXML.oai_pmh.About getAbout(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._aboutList.size()) {
            throw new IndexOutOfBoundsException("getAbout: Index value '" + index + "' not in range [0.." + (this._aboutList.size() - 1) + "]");
        }
        
        return (es.pode.parseadorXML.oai_pmh.About) _aboutList.get(index);
    }

    /**
     * Method getAbout.Returns the contents of the collection in an
     * Array.  <p>Note:  Just in case the collection contents are
     * changing in another thread, we pass a 0-length Array of the
     * correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public es.pode.parseadorXML.oai_pmh.About[] getAbout(
    ) {
        es.pode.parseadorXML.oai_pmh.About[] array = new es.pode.parseadorXML.oai_pmh.About[0];
        return (es.pode.parseadorXML.oai_pmh.About[]) this._aboutList.toArray(array);
    }

    /**
     * Method getAboutCount.
     * 
     * @return the size of this collection
     */
    public int getAboutCount(
    ) {
        return this._aboutList.size();
    }

    /**
     * Returns the value of field 'metadata'.
     * 
     * @return the value of field 'Metadata'.
     */
    public es.pode.parseadorXML.oai_pmh.Metadata getMetadata(
    ) {
        return this._metadata;
    }

    /**
     * Returns the value of field 'recordTypeHeader'.
     * 
     * @return the value of field 'RecordTypeHeader'.
     */
    public es.pode.parseadorXML.oai_pmh.RecordTypeHeader getRecordTypeHeader(
    ) {
        return this._recordTypeHeader;
    }

    /**
     * Method iterateAbout.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator iterateAbout(
    ) {
        return this._aboutList.iterator();
    }

    /**
     * Method removeAbout.
     * 
     * @param vAbout
     * @return true if the object was removed from the collection.
     */
    public boolean removeAbout(
            final es.pode.parseadorXML.oai_pmh.About vAbout) {
        boolean removed = _aboutList.remove(vAbout);
        return removed;
    }

    /**
     * Method removeAboutAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public es.pode.parseadorXML.oai_pmh.About removeAboutAt(
            final int index) {
        java.lang.Object obj = this._aboutList.remove(index);
        return (es.pode.parseadorXML.oai_pmh.About) obj;
    }

    /**
     */
    public void removeAllAbout(
    ) {
        this._aboutList.clear();
    }

    /**
     * 
     * 
     * @param index
     * @param vAbout
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setAbout(
            final int index,
            final es.pode.parseadorXML.oai_pmh.About vAbout)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._aboutList.size()) {
            throw new IndexOutOfBoundsException("setAbout: Index value '" + index + "' not in range [0.." + (this._aboutList.size() - 1) + "]");
        }
        
        this._aboutList.set(index, vAbout);
    }

    /**
     * 
     * 
     * @param vAboutArray
     */
    public void setAbout(
            final es.pode.parseadorXML.oai_pmh.About[] vAboutArray) {
        //-- copy array
        _aboutList.clear();
        
        for (int i = 0; i < vAboutArray.length; i++) {
                this._aboutList.add(vAboutArray[i]);
        }
    }

    /**
     * Sets the value of field 'metadata'.
     * 
     * @param metadata the value of field 'metadata'.
     */
    public void setMetadata(
            final es.pode.parseadorXML.oai_pmh.Metadata metadata) {
        this._metadata = metadata;
    }

    /**
     * Sets the value of field 'recordTypeHeader'.
     * 
     * @param recordTypeHeader the value of field 'recordTypeHeader'
     */
    public void setRecordTypeHeader(
            final es.pode.parseadorXML.oai_pmh.RecordTypeHeader recordTypeHeader) {
        this._recordTypeHeader = recordTypeHeader;
    }

}
