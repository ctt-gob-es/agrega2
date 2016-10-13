/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1</a>, using an XML
 * Schema.
 * $Id$
 */

package es.pode.modificador.negocio.cambios.configuracion.castor;

/**
 * Class OdeType.
 * 
 * @version $Revision$ $Date$
 */
public class OdeType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _published.
     */
    private boolean _published;

    /**
     * keeps track of state for field: _published
     */
    private boolean _has_published;

    /**
     * Field _path.
     */
    private java.lang.String _path;


      //----------------/
     //- Constructors -/
    //----------------/

    public OdeType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deletePublished(
    ) {
        this._has_published= false;
    }

    /**
     * Returns the value of field 'path'.
     * 
     * @return the value of field 'Path'.
     */
    public java.lang.String getPath(
    ) {
        return this._path;
    }

    /**
     * Returns the value of field 'published'.
     * 
     * @return the value of field 'Published'.
     */
    public boolean getPublished(
    ) {
        return this._published;
    }

    /**
     * Method hasPublished.
     * 
     * @return true if at least one Published has been added
     */
    public boolean hasPublished(
    ) {
        return this._has_published;
    }

    /**
     * Returns the value of field 'published'.
     * 
     * @return the value of field 'Published'.
     */
    public boolean isPublished(
    ) {
        return this._published;
    }

    /**
     * Sets the value of field 'path'.
     * 
     * @param path the value of field 'path'.
     */
    public void setPath(
            final java.lang.String path) {
        this._path = path;
    }

    /**
     * Sets the value of field 'published'.
     * 
     * @param published the value of field 'published'.
     */
    public void setPublished(
            final boolean published) {
        this._published = published;
        this._has_published = true;
    }

}
