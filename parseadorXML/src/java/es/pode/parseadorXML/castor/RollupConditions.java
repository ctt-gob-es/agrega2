/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1</a>, using an XML
 * Schema.
 * $Id$
 */

package es.pode.parseadorXML.castor;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class RollupConditions.
 * 
 * @version $Revision$ $Date$
 */
public class RollupConditions implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _conditionCombination.
     */
    private es.pode.parseadorXML.castor.types.ConditionCombinationType _conditionCombination = es.pode.parseadorXML.castor.types.ConditionCombinationType.valueOf("any");

    /**
     * Field _rollupConditionList.
     */
    private java.util.List _rollupConditionList;


      //----------------/
     //- Constructors -/
    //----------------/

    public RollupConditions() {
        super();
        setConditionCombination(es.pode.parseadorXML.castor.types.ConditionCombinationType.valueOf("any"));
        this._rollupConditionList = new java.util.ArrayList();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vRollupCondition
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addRollupCondition(
            final es.pode.parseadorXML.castor.RollupCondition vRollupCondition)
    throws java.lang.IndexOutOfBoundsException {
        this._rollupConditionList.add(vRollupCondition);
    }

    /**
     * 
     * 
     * @param index
     * @param vRollupCondition
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addRollupCondition(
            final int index,
            final es.pode.parseadorXML.castor.RollupCondition vRollupCondition)
    throws java.lang.IndexOutOfBoundsException {
        this._rollupConditionList.add(index, vRollupCondition);
    }

    /**
     * Method enumerateRollupCondition.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration enumerateRollupCondition(
    ) {
        return java.util.Collections.enumeration(this._rollupConditionList);
    }

    /**
     * Returns the value of field 'conditionCombination'.
     * 
     * @return the value of field 'ConditionCombination'.
     */
    public es.pode.parseadorXML.castor.types.ConditionCombinationType getConditionCombination(
    ) {
        return this._conditionCombination;
    }

    /**
     * Method getRollupCondition.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * es.pode.parseadorXML.castorRollupCondition at the
     * given index
     */
    public es.pode.parseadorXML.castor.RollupCondition getRollupCondition(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._rollupConditionList.size()) {
            throw new IndexOutOfBoundsException("getRollupCondition: Index value '" + index + "' not in range [0.." + (this._rollupConditionList.size() - 1) + "]");
        }
        
        return (es.pode.parseadorXML.castor.RollupCondition) _rollupConditionList.get(index);
    }

    /**
     * Method getRollupCondition.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public es.pode.parseadorXML.castor.RollupCondition[] getRollupCondition(
    ) {
        es.pode.parseadorXML.castor.RollupCondition[] array = new es.pode.parseadorXML.castor.RollupCondition[0];
        return (es.pode.parseadorXML.castor.RollupCondition[]) this._rollupConditionList.toArray(array);
    }

    /**
     * Method getRollupConditionCount.
     * 
     * @return the size of this collection
     */
    public int getRollupConditionCount(
    ) {
        return this._rollupConditionList.size();
    }

    /**
     * Method isValid.
     * 
     * @return true if this object is valid according to the schema
     */
    public boolean isValid(
    ) {
        try {
            validate();
        } catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    }

    /**
     * Method iterateRollupCondition.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator iterateRollupCondition(
    ) {
        return this._rollupConditionList.iterator();
    }

    /**
     * 
     * 
     * @param out
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void marshal(
            final java.io.Writer out)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        Marshaller.marshal(this, out);
    }

    /**
     * 
     * 
     * @param handler
     * @throws java.io.IOException if an IOException occurs during
     * marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     */
    public void marshal(
            final org.xml.sax.ContentHandler handler)
    throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        Marshaller.marshal(this, handler);
    }

    /**
     */
    public void removeAllRollupCondition(
    ) {
        this._rollupConditionList.clear();
    }

    /**
     * Method removeRollupCondition.
     * 
     * @param vRollupCondition
     * @return true if the object was removed from the collection.
     */
    public boolean removeRollupCondition(
            final es.pode.parseadorXML.castor.RollupCondition vRollupCondition) {
        boolean removed = _rollupConditionList.remove(vRollupCondition);
        return removed;
    }

    /**
     * Method removeRollupConditionAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public es.pode.parseadorXML.castor.RollupCondition removeRollupConditionAt(
            final int index) {
        java.lang.Object obj = this._rollupConditionList.remove(index);
        return (es.pode.parseadorXML.castor.RollupCondition) obj;
    }

    /**
     * Sets the value of field 'conditionCombination'.
     * 
     * @param conditionCombination the value of field
     * 'conditionCombination'.
     */
    public void setConditionCombination(
            final es.pode.parseadorXML.castor.types.ConditionCombinationType conditionCombination) {
        this._conditionCombination = conditionCombination;
    }

    /**
     * 
     * 
     * @param index
     * @param vRollupCondition
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setRollupCondition(
            final int index,
            final es.pode.parseadorXML.castor.RollupCondition vRollupCondition)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._rollupConditionList.size()) {
            throw new IndexOutOfBoundsException("setRollupCondition: Index value '" + index + "' not in range [0.." + (this._rollupConditionList.size() - 1) + "]");
        }
        
        this._rollupConditionList.set(index, vRollupCondition);
    }

    /**
     * 
     * 
     * @param vRollupConditionArray
     */
    public void setRollupCondition(
            final es.pode.parseadorXML.castor.RollupCondition[] vRollupConditionArray) {
        //-- copy array
        _rollupConditionList.clear();
        
        for (int i = 0; i < vRollupConditionArray.length; i++) {
                this._rollupConditionList.add(vRollupConditionArray[i]);
        }
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * es.pode.parseadorXML.castorRollupConditions
     */
    public static es.pode.parseadorXML.castor.RollupConditions unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (es.pode.parseadorXML.castor.RollupConditions) Unmarshaller.unmarshal(es.pode.parseadorXML.castor.RollupConditions.class, reader);
    }

    /**
     * 
     * 
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void validate(
    )
    throws org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    }

}
