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
