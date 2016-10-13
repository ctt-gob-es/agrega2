/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1</a>, using an XML
 * Schema.
 * $Id$
 */

package es.pode.catalogacion.licencias.dominio.gruposLicencias;

/**
 * Class Grupos.
 * 
 * @version $Revision$ $Date$
 */
public class Grupos implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _grupoList.
     */
    private java.util.List _grupoList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Grupos() {
        super();
        this._grupoList = new java.util.ArrayList();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vGrupo
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addGrupo(
            final es.pode.catalogacion.licencias.dominio.gruposLicencias.Grupo vGrupo)
    throws java.lang.IndexOutOfBoundsException {
        this._grupoList.add(vGrupo);
    }

    /**
     * 
     * 
     * @param index
     * @param vGrupo
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addGrupo(
            final int index,
            final es.pode.catalogacion.licencias.dominio.gruposLicencias.Grupo vGrupo)
    throws java.lang.IndexOutOfBoundsException {
        this._grupoList.add(index, vGrupo);
    }

    /**
     * Method enumerateGrupo.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration enumerateGrupo(
    ) {
        return java.util.Collections.enumeration(this._grupoList);
    }

    /**
     * Method getGrupo.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * es.pode.catalogacion.licencias.dominio.gruposLicencias.Grupo
     * at the given index
     */
    public es.pode.catalogacion.licencias.dominio.gruposLicencias.Grupo getGrupo(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._grupoList.size()) {
            throw new IndexOutOfBoundsException("getGrupo: Index value '" + index + "' not in range [0.." + (this._grupoList.size() - 1) + "]");
        }
        
        return (es.pode.catalogacion.licencias.dominio.gruposLicencias.Grupo) _grupoList.get(index);
    }

    /**
     * Method getGrupo.Returns the contents of the collection in an
     * Array.  <p>Note:  Just in case the collection contents are
     * changing in another thread, we pass a 0-length Array of the
     * correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public es.pode.catalogacion.licencias.dominio.gruposLicencias.Grupo[] getGrupo(
    ) {
        es.pode.catalogacion.licencias.dominio.gruposLicencias.Grupo[] array = new es.pode.catalogacion.licencias.dominio.gruposLicencias.Grupo[0];
        return (es.pode.catalogacion.licencias.dominio.gruposLicencias.Grupo[]) this._grupoList.toArray(array);
    }

    /**
     * Method getGrupoCount.
     * 
     * @return the size of this collection
     */
    public int getGrupoCount(
    ) {
        return this._grupoList.size();
    }

    /**
     * Method iterateGrupo.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator iterateGrupo(
    ) {
        return this._grupoList.iterator();
    }

    /**
     */
    public void removeAllGrupo(
    ) {
        this._grupoList.clear();
    }

    /**
     * Method removeGrupo.
     * 
     * @param vGrupo
     * @return true if the object was removed from the collection.
     */
    public boolean removeGrupo(
            final es.pode.catalogacion.licencias.dominio.gruposLicencias.Grupo vGrupo) {
        boolean removed = _grupoList.remove(vGrupo);
        return removed;
    }

    /**
     * Method removeGrupoAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public es.pode.catalogacion.licencias.dominio.gruposLicencias.Grupo removeGrupoAt(
            final int index) {
        java.lang.Object obj = this._grupoList.remove(index);
        return (es.pode.catalogacion.licencias.dominio.gruposLicencias.Grupo) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vGrupo
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setGrupo(
            final int index,
            final es.pode.catalogacion.licencias.dominio.gruposLicencias.Grupo vGrupo)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._grupoList.size()) {
            throw new IndexOutOfBoundsException("setGrupo: Index value '" + index + "' not in range [0.." + (this._grupoList.size() - 1) + "]");
        }
        
        this._grupoList.set(index, vGrupo);
    }

    /**
     * 
     * 
     * @param vGrupoArray
     */
    public void setGrupo(
            final es.pode.catalogacion.licencias.dominio.gruposLicencias.Grupo[] vGrupoArray) {
        //-- copy array
        _grupoList.clear();
        
        for (int i = 0; i < vGrupoArray.length; i++) {
                this._grupoList.add(vGrupoArray[i]);
        }
    }

}
