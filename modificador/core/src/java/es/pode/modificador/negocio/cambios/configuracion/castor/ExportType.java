/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1</a>, using an XML
 * Schema.
 * $Id$
 */

package es.pode.modificador.negocio.cambios.configuracion.castor;

/**
 * Class ExportType.
 * 
 * @version $Revision$ $Date$
 */
public class ExportType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _format.
     */
    private es.pode.modificador.negocio.cambios.configuracion.castor.types.FormatType _format = es.pode.modificador.negocio.cambios.configuracion.castor.types.FormatType.valueOf("SCORM2004");

    /**
     * Field _targetPath.
     */
    private java.lang.String _targetPath;


      //----------------/
     //- Constructors -/
    //----------------/

    public ExportType() {
        super();
        setFormat(es.pode.modificador.negocio.cambios.configuracion.castor.types.FormatType.valueOf("SCORM2004"));
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'format'.
     * 
     * @return the value of field 'Format'.
     */
    public es.pode.modificador.negocio.cambios.configuracion.castor.types.FormatType getFormat(
    ) {
        return this._format;
    }

    /**
     * Returns the value of field 'targetPath'.
     * 
     * @return the value of field 'TargetPath'.
     */
    public java.lang.String getTargetPath(
    ) {
        return this._targetPath;
    }

    /**
     * Sets the value of field 'format'.
     * 
     * @param format the value of field 'format'.
     */
    public void setFormat(
            final es.pode.modificador.negocio.cambios.configuracion.castor.types.FormatType format) {
        this._format = format;
    }

    /**
     * Sets the value of field 'targetPath'.
     * 
     * @param targetPath the value of field 'targetPath'.
     */
    public void setTargetPath(
            final java.lang.String targetPath) {
        this._targetPath = targetPath;
    }

}
