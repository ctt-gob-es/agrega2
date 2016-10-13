/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1</a>, using an XML
 * Schema.
 * $Id$
 */

package es.pode.catalogacion.licencias.dominio.gruposLicencias;

/**
 * Class Grupo.
 * 
 * @version $Revision$ $Date$
 */
public class Grupo implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _nivel.
     */
    private java.lang.String _nivel;

    /**
     * Field _nombre.
     */
    private java.lang.String _nombre;

    /**
     * Field _codigoLicenciaList.
     */
    private java.util.List _codigoLicenciaList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Grupo() {
        super();
        this._codigoLicenciaList = new java.util.ArrayList();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vCodigoLicencia
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCodigoLicencia(
            final es.pode.catalogacion.licencias.dominio.gruposLicencias.CodigoLicencia vCodigoLicencia)
    throws java.lang.IndexOutOfBoundsException {
        this._codigoLicenciaList.add(vCodigoLicencia);
    }

    /**
     * 
     * 
     * @param index
     * @param vCodigoLicencia
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCodigoLicencia(
            final int index,
            final es.pode.catalogacion.licencias.dominio.gruposLicencias.CodigoLicencia vCodigoLicencia)
    throws java.lang.IndexOutOfBoundsException {
        this._codigoLicenciaList.add(index, vCodigoLicencia);
    }

    /**
     * Method enumerateCodigoLicencia.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration enumerateCodigoLicencia(
    ) {
        return java.util.Collections.enumeration(this._codigoLicenciaList);
    }

    /**
     * Method getCodigoLicencia.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * es.pode.catalogacion.licencias.dominio.gruposLicencias.CodigoLicencia
     * at the given index
     */
    public es.pode.catalogacion.licencias.dominio.gruposLicencias.CodigoLicencia getCodigoLicencia(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._codigoLicenciaList.size()) {
            throw new IndexOutOfBoundsException("getCodigoLicencia: Index value '" + index + "' not in range [0.." + (this._codigoLicenciaList.size() - 1) + "]");
        }
        
        return (es.pode.catalogacion.licencias.dominio.gruposLicencias.CodigoLicencia) _codigoLicenciaList.get(index);
    }

    /**
     * Method getCodigoLicencia.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public es.pode.catalogacion.licencias.dominio.gruposLicencias.CodigoLicencia[] getCodigoLicencia(
    ) {
        es.pode.catalogacion.licencias.dominio.gruposLicencias.CodigoLicencia[] array = new es.pode.catalogacion.licencias.dominio.gruposLicencias.CodigoLicencia[0];
        return (es.pode.catalogacion.licencias.dominio.gruposLicencias.CodigoLicencia[]) this._codigoLicenciaList.toArray(array);
    }

    /**
     * Method getCodigoLicenciaCount.
     * 
     * @return the size of this collection
     */
    public int getCodigoLicenciaCount(
    ) {
        return this._codigoLicenciaList.size();
    }

    /**
     * Returns the value of field 'nivel'.
     * 
     * @return the value of field 'Nivel'.
     */
    public java.lang.String getNivel(
    ) {
        return this._nivel;
    }

    /**
     * Returns the value of field 'nombre'.
     * 
     * @return the value of field 'Nombre'.
     */
    public java.lang.String getNombre(
    ) {
        return this._nombre;
    }

    /**
     * Method iterateCodigoLicencia.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator iterateCodigoLicencia(
    ) {
        return this._codigoLicenciaList.iterator();
    }

    /**
     */
    public void removeAllCodigoLicencia(
    ) {
        this._codigoLicenciaList.clear();
    }

    /**
     * Method removeCodigoLicencia.
     * 
     * @param vCodigoLicencia
     * @return true if the object was removed from the collection.
     */
    public boolean removeCodigoLicencia(
            final es.pode.catalogacion.licencias.dominio.gruposLicencias.CodigoLicencia vCodigoLicencia) {
        boolean removed = _codigoLicenciaList.remove(vCodigoLicencia);
        return removed;
    }

    /**
     * Method removeCodigoLicenciaAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public es.pode.catalogacion.licencias.dominio.gruposLicencias.CodigoLicencia removeCodigoLicenciaAt(
            final int index) {
        java.lang.Object obj = this._codigoLicenciaList.remove(index);
        return (es.pode.catalogacion.licencias.dominio.gruposLicencias.CodigoLicencia) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vCodigoLicencia
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setCodigoLicencia(
            final int index,
            final es.pode.catalogacion.licencias.dominio.gruposLicencias.CodigoLicencia vCodigoLicencia)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._codigoLicenciaList.size()) {
            throw new IndexOutOfBoundsException("setCodigoLicencia: Index value '" + index + "' not in range [0.." + (this._codigoLicenciaList.size() - 1) + "]");
        }
        
        this._codigoLicenciaList.set(index, vCodigoLicencia);
    }

    /**
     * 
     * 
     * @param vCodigoLicenciaArray
     */
    public void setCodigoLicencia(
            final es.pode.catalogacion.licencias.dominio.gruposLicencias.CodigoLicencia[] vCodigoLicenciaArray) {
        //-- copy array
        _codigoLicenciaList.clear();
        
        for (int i = 0; i < vCodigoLicenciaArray.length; i++) {
                this._codigoLicenciaList.add(vCodigoLicenciaArray[i]);
        }
    }

    /**
     * Sets the value of field 'nivel'.
     * 
     * @param nivel the value of field 'nivel'.
     */
    public void setNivel(
            final java.lang.String nivel) {
        this._nivel = nivel;
    }

    /**
     * Sets the value of field 'nombre'.
     * 
     * @param nombre the value of field 'nombre'.
     */
    public void setNombre(
            final java.lang.String nombre) {
        this._nombre = nombre;
    }

}
