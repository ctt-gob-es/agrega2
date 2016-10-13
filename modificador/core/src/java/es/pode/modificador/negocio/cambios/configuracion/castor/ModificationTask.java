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

package es.pode.modificador.negocio.cambios.configuracion.castor;

/**
 * Class ModificationTask.
 * 
 * @version $Revision$ $Date$
 */
public class ModificationTask implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _changes.
     */
    private es.pode.modificador.negocio.cambios.configuracion.castor.Changes _changes;

    /**
     * Field _objects.
     */
    private es.pode.modificador.negocio.cambios.configuracion.castor.Objects _objects;


      //----------------/
     //- Constructors -/
    //----------------/

    public ModificationTask() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'changes'.
     * 
     * @return the value of field 'Changes'.
     */
    public es.pode.modificador.negocio.cambios.configuracion.castor.Changes getChanges(
    ) {
        return this._changes;
    }

    /**
     * Returns the value of field 'objects'.
     * 
     * @return the value of field 'Objects'.
     */
    public es.pode.modificador.negocio.cambios.configuracion.castor.Objects getObjects(
    ) {
        return this._objects;
    }

    /**
     * Sets the value of field 'changes'.
     * 
     * @param changes the value of field 'changes'.
     */
    public void setChanges(
            final es.pode.modificador.negocio.cambios.configuracion.castor.Changes changes) {
        this._changes = changes;
    }

    /**
     * Sets the value of field 'objects'.
     * 
     * @param objects the value of field 'objects'.
     */
    public void setObjects(
            final es.pode.modificador.negocio.cambios.configuracion.castor.Objects objects) {
        this._objects = objects;
    }

}
