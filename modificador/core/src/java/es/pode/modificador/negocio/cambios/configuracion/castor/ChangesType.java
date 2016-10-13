/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1</a>, using an XML
 * Schema.
 * $Id$
 */

package es.pode.modificador.negocio.cambios.configuracion.castor;

/**
 * Class ChangesType.
 * 
 * @version $Revision$ $Date$
 */
public class ChangesType implements java.io.Serializable {


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

    public ChangesType() {
        super();
        this._items = new java.util.ArrayList();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vChangesTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addChangesTypeItem(
            final es.pode.modificador.negocio.cambios.configuracion.castor.ChangesTypeItem vChangesTypeItem)
    throws java.lang.IndexOutOfBoundsException {
        this._items.add(vChangesTypeItem);
    }

    /**
     * 
     * 
     * @param index
     * @param vChangesTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addChangesTypeItem(
            final int index,
            final es.pode.modificador.negocio.cambios.configuracion.castor.ChangesTypeItem vChangesTypeItem)
    throws java.lang.IndexOutOfBoundsException {
        this._items.add(index, vChangesTypeItem);
    }

    /**
     * Method enumerateChangesTypeItem.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration enumerateChangesTypeItem(
    ) {
        return java.util.Collections.enumeration(this._items);
    }

    /**
     * Method getChangesTypeItem.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * es.pode.modificador.negocio.cambios.configuracion.castor.ChangesTypeItem
     * at the given index
     */
    public es.pode.modificador.negocio.cambios.configuracion.castor.ChangesTypeItem getChangesTypeItem(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("getChangesTypeItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }
        
        return (es.pode.modificador.negocio.cambios.configuracion.castor.ChangesTypeItem) _items.get(index);
    }

    /**
     * Method getChangesTypeItem.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public es.pode.modificador.negocio.cambios.configuracion.castor.ChangesTypeItem[] getChangesTypeItem(
    ) {
        es.pode.modificador.negocio.cambios.configuracion.castor.ChangesTypeItem[] array = new es.pode.modificador.negocio.cambios.configuracion.castor.ChangesTypeItem[0];
        return (es.pode.modificador.negocio.cambios.configuracion.castor.ChangesTypeItem[]) this._items.toArray(array);
    }

    /**
     * Method getChangesTypeItemCount.
     * 
     * @return the size of this collection
     */
    public int getChangesTypeItemCount(
    ) {
        return this._items.size();
    }

    /**
     * Method iterateChangesTypeItem.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator iterateChangesTypeItem(
    ) {
        return this._items.iterator();
    }

    /**
     */
    public void removeAllChangesTypeItem(
    ) {
        this._items.clear();
    }

    /**
     * Method removeChangesTypeItem.
     * 
     * @param vChangesTypeItem
     * @return true if the object was removed from the collection.
     */
    public boolean removeChangesTypeItem(
            final es.pode.modificador.negocio.cambios.configuracion.castor.ChangesTypeItem vChangesTypeItem) {
        boolean removed = _items.remove(vChangesTypeItem);
        return removed;
    }

    /**
     * Method removeChangesTypeItemAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public es.pode.modificador.negocio.cambios.configuracion.castor.ChangesTypeItem removeChangesTypeItemAt(
            final int index) {
        java.lang.Object obj = this._items.remove(index);
        return (es.pode.modificador.negocio.cambios.configuracion.castor.ChangesTypeItem) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vChangesTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setChangesTypeItem(
            final int index,
            final es.pode.modificador.negocio.cambios.configuracion.castor.ChangesTypeItem vChangesTypeItem)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("setChangesTypeItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }
        
        this._items.set(index, vChangesTypeItem);
    }

    /**
     * 
     * 
     * @param vChangesTypeItemArray
     */
    public void setChangesTypeItem(
            final es.pode.modificador.negocio.cambios.configuracion.castor.ChangesTypeItem[] vChangesTypeItemArray) {
        //-- copy array
        _items.clear();
        
        for (int i = 0; i < vChangesTypeItemArray.length; i++) {
                this._items.add(vChangesTypeItemArray[i]);
        }
    }

}
