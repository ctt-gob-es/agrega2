/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1</a>, using an XML
 * Schema.
 * $Id$
 */

package es.pode.modificador.negocio.cambios.configuracion.castor;

/**
 * Class TransformationType.
 * 
 * @version $Revision$ $Date$
 */
public class TransformationType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _targetFormat.
     */
    private es.pode.modificador.negocio.cambios.configuracion.castor.types.FormatType _targetFormat = es.pode.modificador.negocio.cambios.configuracion.castor.types.FormatType.valueOf("SCORM2004");

    /**
     * Field _targetPath.
     */
    private java.lang.String _targetPath;


      //----------------/
     //- Constructors -/
    //----------------/

    public TransformationType() {
        super();
        setTargetFormat(es.pode.modificador.negocio.cambios.configuracion.castor.types.FormatType.valueOf("SCORM2004"));
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'targetFormat'.
     * 
     * @return the value of field 'TargetFormat'.
     */
    public es.pode.modificador.negocio.cambios.configuracion.castor.types.FormatType getTargetFormat(
    ) {
        return this._targetFormat;
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
     * Sets the value of field 'targetFormat'.
     * 
     * @param targetFormat the value of field 'targetFormat'.
     */
    public void setTargetFormat(
            final es.pode.modificador.negocio.cambios.configuracion.castor.types.FormatType targetFormat) {
        this._targetFormat = targetFormat;
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
