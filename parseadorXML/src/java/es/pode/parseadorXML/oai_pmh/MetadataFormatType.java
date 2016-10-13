/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1</a>, using an XML
 * Schema.
 * $Id$
 */

package es.pode.parseadorXML.oai_pmh;

/**
 * Class MetadataFormatType.
 * 
 * @version $Revision$ $Date$
 */
public class MetadataFormatType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _metadataPrefix.
     */
    private java.lang.String _metadataPrefix;

    /**
     * Field _schema.
     */
    private java.lang.String _schema;

    /**
     * Field _metadataNamespace.
     */
    private java.lang.String _metadataNamespace;


      //----------------/
     //- Constructors -/
    //----------------/

    public MetadataFormatType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'metadataNamespace'.
     * 
     * @return the value of field 'MetadataNamespace'.
     */
    public java.lang.String getMetadataNamespace(
    ) {
        return this._metadataNamespace;
    }

    /**
     * Returns the value of field 'metadataPrefix'.
     * 
     * @return the value of field 'MetadataPrefix'.
     */
    public java.lang.String getMetadataPrefix(
    ) {
        return this._metadataPrefix;
    }

    /**
     * Returns the value of field 'schema'.
     * 
     * @return the value of field 'Schema'.
     */
    public java.lang.String getSchema(
    ) {
        return this._schema;
    }

    /**
     * Sets the value of field 'metadataNamespace'.
     * 
     * @param metadataNamespace the value of field
     * 'metadataNamespace'.
     */
    public void setMetadataNamespace(
            final java.lang.String metadataNamespace) {
        this._metadataNamespace = metadataNamespace;
    }

    /**
     * Sets the value of field 'metadataPrefix'.
     * 
     * @param metadataPrefix the value of field 'metadataPrefix'.
     */
    public void setMetadataPrefix(
            final java.lang.String metadataPrefix) {
        this._metadataPrefix = metadataPrefix;
    }

    /**
     * Sets the value of field 'schema'.
     * 
     * @param schema the value of field 'schema'.
     */
    public void setSchema(
            final java.lang.String schema) {
        this._schema = schema;
    }

}
