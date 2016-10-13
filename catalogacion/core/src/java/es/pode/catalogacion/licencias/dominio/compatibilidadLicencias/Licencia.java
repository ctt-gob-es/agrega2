/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1</a>, using an XML
 * Schema.
 * $Id$
 */

package es.pode.catalogacion.licencias.dominio.compatibilidadLicencias;

/**
 * Class Licencia.
 * 
 * @version $Revision$ $Date$
 */
public class Licencia implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _codigo.
     */
    private java.lang.String _codigo;

    /**
     * Field _licenciaAdicionalList.
     */
    private java.util.List _licenciaAdicionalList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Licencia() {
        super();
        this._licenciaAdicionalList = new java.util.ArrayList();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vLicenciaAdicional
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addLicenciaAdicional(
            final es.pode.catalogacion.licencias.dominio.compatibilidadLicencias.LicenciaAdicional vLicenciaAdicional)
    throws java.lang.IndexOutOfBoundsException {
        this._licenciaAdicionalList.add(vLicenciaAdicional);
    }

    /**
     * 
     * 
     * @param index
     * @param vLicenciaAdicional
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addLicenciaAdicional(
            final int index,
            final es.pode.catalogacion.licencias.dominio.compatibilidadLicencias.LicenciaAdicional vLicenciaAdicional)
    throws java.lang.IndexOutOfBoundsException {
        this._licenciaAdicionalList.add(index, vLicenciaAdicional);
    }

    /**
     * Method enumerateLicenciaAdicional.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration enumerateLicenciaAdicional(
    ) {
        return java.util.Collections.enumeration(this._licenciaAdicionalList);
    }

    /**
     * Returns the value of field 'codigo'.
     * 
     * @return the value of field 'Codigo'.
     */
    public java.lang.String getCodigo(
    ) {
        return this._codigo;
    }

    /**
     * Method getLicenciaAdicional.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * es.pode.catalogacion.licencias.dominio.compatibilidadLicencias.LicenciaAdicional
     * at the given index
     */
    public es.pode.catalogacion.licencias.dominio.compatibilidadLicencias.LicenciaAdicional getLicenciaAdicional(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._licenciaAdicionalList.size()) {
            throw new IndexOutOfBoundsException("getLicenciaAdicional: Index value '" + index + "' not in range [0.." + (this._licenciaAdicionalList.size() - 1) + "]");
        }
        
        return (es.pode.catalogacion.licencias.dominio.compatibilidadLicencias.LicenciaAdicional) _licenciaAdicionalList.get(index);
    }

    /**
     * Method getLicenciaAdicional.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public es.pode.catalogacion.licencias.dominio.compatibilidadLicencias.LicenciaAdicional[] getLicenciaAdicional(
    ) {
        es.pode.catalogacion.licencias.dominio.compatibilidadLicencias.LicenciaAdicional[] array = new es.pode.catalogacion.licencias.dominio.compatibilidadLicencias.LicenciaAdicional[0];
        return (es.pode.catalogacion.licencias.dominio.compatibilidadLicencias.LicenciaAdicional[]) this._licenciaAdicionalList.toArray(array);
    }

    /**
     * Method getLicenciaAdicionalCount.
     * 
     * @return the size of this collection
     */
    public int getLicenciaAdicionalCount(
    ) {
        return this._licenciaAdicionalList.size();
    }

    /**
     * Method iterateLicenciaAdicional.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator iterateLicenciaAdicional(
    ) {
        return this._licenciaAdicionalList.iterator();
    }

    /**
     */
    public void removeAllLicenciaAdicional(
    ) {
        this._licenciaAdicionalList.clear();
    }

    /**
     * Method removeLicenciaAdicional.
     * 
     * @param vLicenciaAdicional
     * @return true if the object was removed from the collection.
     */
    public boolean removeLicenciaAdicional(
            final es.pode.catalogacion.licencias.dominio.compatibilidadLicencias.LicenciaAdicional vLicenciaAdicional) {
        boolean removed = _licenciaAdicionalList.remove(vLicenciaAdicional);
        return removed;
    }

    /**
     * Method removeLicenciaAdicionalAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public es.pode.catalogacion.licencias.dominio.compatibilidadLicencias.LicenciaAdicional removeLicenciaAdicionalAt(
            final int index) {
        java.lang.Object obj = this._licenciaAdicionalList.remove(index);
        return (es.pode.catalogacion.licencias.dominio.compatibilidadLicencias.LicenciaAdicional) obj;
    }

    /**
     * Sets the value of field 'codigo'.
     * 
     * @param codigo the value of field 'codigo'.
     */
    public void setCodigo(
            final java.lang.String codigo) {
        this._codigo = codigo;
    }

    /**
     * 
     * 
     * @param index
     * @param vLicenciaAdicional
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setLicenciaAdicional(
            final int index,
            final es.pode.catalogacion.licencias.dominio.compatibilidadLicencias.LicenciaAdicional vLicenciaAdicional)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._licenciaAdicionalList.size()) {
            throw new IndexOutOfBoundsException("setLicenciaAdicional: Index value '" + index + "' not in range [0.." + (this._licenciaAdicionalList.size() - 1) + "]");
        }
        
        this._licenciaAdicionalList.set(index, vLicenciaAdicional);
    }

    /**
     * 
     * 
     * @param vLicenciaAdicionalArray
     */
    public void setLicenciaAdicional(
            final es.pode.catalogacion.licencias.dominio.compatibilidadLicencias.LicenciaAdicional[] vLicenciaAdicionalArray) {
        //-- copy array
        _licenciaAdicionalList.clear();
        
        for (int i = 0; i < vLicenciaAdicionalArray.length; i++) {
                this._licenciaAdicionalList.add(vLicenciaAdicionalArray[i]);
        }
    }

}
