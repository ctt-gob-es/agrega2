/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1</a>, using an XML
 * Schema.
 * $Id$
 */

package es.pode.parseadorXML.oai_dc;

/**
 * Class ElementsGroupChoice.
 * 
 * @version $Revision$ $Date$
 */
public class ElementsGroupChoice implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _items.
     */
    private java.util.List _items;


      //----------------/
     //- Constructors -/
    //----------------/

    public ElementsGroupChoice() {
        super();
        this._items = new java.util.ArrayList();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vElementsGroupChoiceItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addElementsGroupChoiceItem(
            final es.pode.parseadorXML.oai_dc.ElementsGroupChoiceItem vElementsGroupChoiceItem)
    throws java.lang.IndexOutOfBoundsException {
        this._items.add(vElementsGroupChoiceItem);
    }

    /**
     * 
     * 
     * @param index
     * @param vElementsGroupChoiceItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addElementsGroupChoiceItem(
            final int index,
            final es.pode.parseadorXML.oai_dc.ElementsGroupChoiceItem vElementsGroupChoiceItem)
    throws java.lang.IndexOutOfBoundsException {
        this._items.add(index, vElementsGroupChoiceItem);
    }

    /**
     * Method enumerateElementsGroupChoiceItem.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration enumerateElementsGroupChoiceItem(
    ) {
        return java.util.Collections.enumeration(this._items);
    }

    /**
     * Method getElementsGroupChoiceItem.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * es.pode.parseadorXML.oai_dc.ElementsGroupChoiceItem at the
     * given index
     */
    public es.pode.parseadorXML.oai_dc.ElementsGroupChoiceItem getElementsGroupChoiceItem(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("getElementsGroupChoiceItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }
        
        return (es.pode.parseadorXML.oai_dc.ElementsGroupChoiceItem) _items.get(index);
    }

    /**
     * Method getElementsGroupChoiceItem.Returns the contents of
     * the collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public es.pode.parseadorXML.oai_dc.ElementsGroupChoiceItem[] getElementsGroupChoiceItem(
    ) {
        es.pode.parseadorXML.oai_dc.ElementsGroupChoiceItem[] array = new es.pode.parseadorXML.oai_dc.ElementsGroupChoiceItem[0];
        return (es.pode.parseadorXML.oai_dc.ElementsGroupChoiceItem[]) this._items.toArray(array);
    }

    /**
     * Method getElementsGroupChoiceItemCount.
     * 
     * @return the size of this collection
     */
    public int getElementsGroupChoiceItemCount(
    ) {
        return this._items.size();
    }

    /**
     * Method iterateElementsGroupChoiceItem.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator iterateElementsGroupChoiceItem(
    ) {
        return this._items.iterator();
    }

    /**
     */
    public void removeAllElementsGroupChoiceItem(
    ) {
        this._items.clear();
    }

    /**
     * Method removeElementsGroupChoiceItem.
     * 
     * @param vElementsGroupChoiceItem
     * @return true if the object was removed from the collection.
     */
    public boolean removeElementsGroupChoiceItem(
            final es.pode.parseadorXML.oai_dc.ElementsGroupChoiceItem vElementsGroupChoiceItem) {
        boolean removed = _items.remove(vElementsGroupChoiceItem);
        return removed;
    }

    /**
     * Method removeElementsGroupChoiceItemAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public es.pode.parseadorXML.oai_dc.ElementsGroupChoiceItem removeElementsGroupChoiceItemAt(
            final int index) {
        java.lang.Object obj = this._items.remove(index);
        return (es.pode.parseadorXML.oai_dc.ElementsGroupChoiceItem) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vElementsGroupChoiceItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setElementsGroupChoiceItem(
            final int index,
            final es.pode.parseadorXML.oai_dc.ElementsGroupChoiceItem vElementsGroupChoiceItem)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("setElementsGroupChoiceItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }
        
        this._items.set(index, vElementsGroupChoiceItem);
    }

    /**
     * 
     * 
     * @param vElementsGroupChoiceItemArray
     */
    public void setElementsGroupChoiceItem(
            final es.pode.parseadorXML.oai_dc.ElementsGroupChoiceItem[] vElementsGroupChoiceItemArray) {
        //-- copy array
        _items.clear();
        
        for (int i = 0; i < vElementsGroupChoiceItemArray.length; i++) {
                this._items.add(vElementsGroupChoiceItemArray[i]);
        }
    }

}
