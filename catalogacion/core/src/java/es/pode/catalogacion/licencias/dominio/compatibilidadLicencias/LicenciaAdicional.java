/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1</a>, using an XML
 * Schema.
 * $Id$
 */

package es.pode.catalogacion.licencias.dominio.compatibilidadLicencias;

/**
 * Class LicenciaAdicional.
 * 
 * @version $Revision$ $Date$
 */
public class LicenciaAdicional implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * internal content storage
     */
    private java.lang.String _content = "";

    /**
     * Field _codigo.
     */
    private java.lang.String _codigo;


      //----------------/
     //- Constructors -/
    //----------------/

    public LicenciaAdicional() {
        super();
        setContent("");
    }


      //-----------/
     //- Methods -/
    //-----------/

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
     * Sets the value of field 'codigo'.
     * 
     * @param codigo the value of field 'codigo'.
     */
    public void setCodigo(
            final java.lang.String codigo) {
        this._codigo = codigo;
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

}
