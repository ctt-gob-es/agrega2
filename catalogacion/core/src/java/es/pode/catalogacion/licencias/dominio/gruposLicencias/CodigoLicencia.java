/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1</a>, using an XML
 * Schema.
 * $Id$
 */

package es.pode.catalogacion.licencias.dominio.gruposLicencias;

/**
 * Class CodigoLicencia.
 * 
 * @version $Revision$ $Date$
 */
public class CodigoLicencia implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * internal content storage
     */
    private java.lang.String _content = "";

    /**
     * Field _orden.
     */
    private java.lang.String _orden;


      //----------------/
     //- Constructors -/
    //----------------/

    public CodigoLicencia() {
        super();
        setContent("");
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'content'. The field 'content'
     * has the following description: internal content storage
     * 
     * @return the value of field 'Content'.
     */
    public java.lang.String getContent(
    ) {
        return this._content;
    }

    /**
     * Returns the value of field 'orden'.
     * 
     * @return the value of field 'Orden'.
     */
    public java.lang.String getOrden(
    ) {
        return this._orden;
    }

    /**
     * Sets the value of field 'content'. The field 'content' has
     * the following description: internal content storage
     * 
     * @param content the value of field 'content'.
     */
    public void setContent(
            final java.lang.String content) {
        this._content = content;
    }

    /**
     * Sets the value of field 'orden'.
     * 
     * @param orden the value of field 'orden'.
     */
    public void setOrden(
            final java.lang.String orden) {
        this._orden = orden;
    }

}
