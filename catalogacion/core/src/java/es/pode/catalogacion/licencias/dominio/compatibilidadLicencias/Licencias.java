/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1</a>, using an XML
 * Schema.
 * $Id$
 */

package es.pode.catalogacion.licencias.dominio.compatibilidadLicencias;

/**
 * Class Licencias.
 * 
 * @version $Revision$ $Date$
 */
public class Licencias implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _licenciaList.
     */
    private java.util.List _licenciaList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Licencias() {
        super();
        this._licenciaList = new java.util.ArrayList();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vLicencia
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addLicencia(
            final es.pode.catalogacion.licencias.dominio.compatibilidadLicencias.Licencia vLicencia)
    throws java.lang.IndexOutOfBoundsException {
        this._licenciaList.add(vLicencia);
    }

    /**
     * 
     * 
     * @param index
     * @param vLicencia
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addLicencia(
            final int index,
            final es.pode.catalogacion.licencias.dominio.compatibilidadLicencias.Licencia vLicencia)
    throws java.lang.IndexOutOfBoundsException {
        this._licenciaList.add(index, vLicencia);
    }

    /**
     * Method enumerateLicencia.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration enumerateLicencia(
    ) {
        return java.util.Collections.enumeration(this._licenciaList);
    }

    /**
     * Method getLicencia.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * es.pode.catalogacion.licencias.dominio.compatibilidadLicencias.Licencia
     * at the given index
     */
    public es.pode.catalogacion.licencias.dominio.compatibilidadLicencias.Licencia getLicencia(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._licenciaList.size()) {
            throw new IndexOutOfBoundsException("getLicencia: Index value '" + index + "' not in range [0.." + (this._licenciaList.size() - 1) + "]");
        }
        
        return (es.pode.catalogacion.licencias.dominio.compatibilidadLicencias.Licencia) _licenciaList.get(index);
    }

    /**
     * Method getLicencia.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public es.pode.catalogacion.licencias.dominio.compatibilidadLicencias.Licencia[] getLicencia(
    ) {
        es.pode.catalogacion.licencias.dominio.compatibilidadLicencias.Licencia[] array = new es.pode.catalogacion.licencias.dominio.compatibilidadLicencias.Licencia[0];
        return (es.pode.catalogacion.licencias.dominio.compatibilidadLicencias.Licencia[]) this._licenciaList.toArray(array);
    }

    /**
     * Method getLicenciaCount.
     * 
     * @return the size of this collection
     */
    public int getLicenciaCount(
    ) {
        return this._licenciaList.size();
    }

    /**
     * Method iterateLicencia.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator iterateLicencia(
    ) {
        return this._licenciaList.iterator();
    }

    /**
     */
    public void removeAllLicencia(
    ) {
        this._licenciaList.clear();
    }

    /**
     * Method removeLicencia.
     * 
     * @param vLicencia
     * @return true if the object was removed from the collection.
     */
    public boolean removeLicencia(
            final es.pode.catalogacion.licencias.dominio.compatibilidadLicencias.Licencia vLicencia) {
        boolean removed = _licenciaList.remove(vLicencia);
        return removed;
    }

    /**
     * Method removeLicenciaAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public es.pode.catalogacion.licencias.dominio.compatibilidadLicencias.Licencia removeLicenciaAt(
            final int index) {
        java.lang.Object obj = this._licenciaList.remove(index);
        return (es.pode.catalogacion.licencias.dominio.compatibilidadLicencias.Licencia) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vLicencia
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setLicencia(
            final int index,
            final es.pode.catalogacion.licencias.dominio.compatibilidadLicencias.Licencia vLicencia)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._licenciaList.size()) {
            throw new IndexOutOfBoundsException("setLicencia: Index value '" + index + "' not in range [0.." + (this._licenciaList.size() - 1) + "]");
        }
        
        this._licenciaList.set(index, vLicencia);
    }

    /**
     * 
     * 
     * @param vLicenciaArray
     */
    public void setLicencia(
            final es.pode.catalogacion.licencias.dominio.compatibilidadLicencias.Licencia[] vLicenciaArray) {
        //-- copy array
        _licenciaList.clear();
        
        for (int i = 0; i < vLicenciaArray.length; i++) {
                this._licenciaList.add(vLicenciaArray[i]);
        }
    }

}
