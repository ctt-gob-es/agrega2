/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1</a>, using an XML
 * Schema.
 * $Id$
 */

package es.pode.modificador.negocio.cambios.configuracion.castor;

/**
 * Class AdditionType.
 * 
 * @version $Revision$ $Date$
 */
public class AdditionType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _lomTerm.
     */
    private java.lang.String _lomTerm;

    /**
     * Field _newValue.
     */
    private java.lang.String _newValue;

    /**
     * Field _termType.
     */
    private es.pode.modificador.negocio.cambios.configuracion.castor.types.TermType _termType = es.pode.modificador.negocio.cambios.configuracion.castor.types.TermType.valueOf("otro");

    /**
     * Field _purpose.
     */
    private java.lang.String _purpose;

    /**
     * Field _source.
     */
    private java.lang.String _source;

    /**
     * Field _mainLomOnly.
     */
    private boolean _mainLomOnly = true;

    /**
     * keeps track of state for field: _mainLomOnly
     */
    private boolean _has_mainLomOnly;


      //----------------/
     //- Constructors -/
    //----------------/

    public AdditionType() {
        super();
        setTermType(es.pode.modificador.negocio.cambios.configuracion.castor.types.TermType.valueOf("otro"));
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteMainLomOnly(
    ) {
        this._has_mainLomOnly= false;
    }

    /**
     * Returns the value of field 'lomTerm'.
     * 
     * @return the value of field 'LomTerm'.
     */
    public java.lang.String getLomTerm(
    ) {
        return this._lomTerm;
    }

    /**
     * Returns the value of field 'mainLomOnly'.
     * 
     * @return the value of field 'MainLomOnly'.
     */
    public boolean getMainLomOnly(
    ) {
        return this._mainLomOnly;
    }

    /**
     * Returns the value of field 'newValue'.
     * 
     * @return the value of field 'NewValue'.
     */
    public java.lang.String getNewValue(
    ) {
        return this._newValue;
    }

    /**
     * Returns the value of field 'purpose'.
     * 
     * @return the value of field 'Purpose'.
     */
    public java.lang.String getPurpose(
    ) {
        return this._purpose;
    }

    /**
     * Returns the value of field 'source'.
     * 
     * @return the value of field 'Source'.
     */
    public java.lang.String getSource(
    ) {
        return this._source;
    }

    /**
     * Returns the value of field 'termType'.
     * 
     * @return the value of field 'TermType'.
     */
    public es.pode.modificador.negocio.cambios.configuracion.castor.types.TermType getTermType(
    ) {
        return this._termType;
    }

    /**
     * Method hasMainLomOnly.
     * 
     * @return true if at least one MainLomOnly has been added
     */
    public boolean hasMainLomOnly(
    ) {
        return this._has_mainLomOnly;
    }

    /**
     * Returns the value of field 'mainLomOnly'.
     * 
     * @return the value of field 'MainLomOnly'.
     */
    public boolean isMainLomOnly(
    ) {
        return this._mainLomOnly;
    }

    /**
     * Sets the value of field 'lomTerm'.
     * 
     * @param lomTerm the value of field 'lomTerm'.
     */
    public void setLomTerm(
            final java.lang.String lomTerm) {
        this._lomTerm = lomTerm;
    }

    /**
     * Sets the value of field 'mainLomOnly'.
     * 
     * @param mainLomOnly the value of field 'mainLomOnly'.
     */
    public void setMainLomOnly(
            final boolean mainLomOnly) {
        this._mainLomOnly = mainLomOnly;
        this._has_mainLomOnly = true;
    }

    /**
     * Sets the value of field 'newValue'.
     * 
     * @param newValue the value of field 'newValue'.
     */
    public void setNewValue(
            final java.lang.String newValue) {
        this._newValue = newValue;
    }

    /**
     * Sets the value of field 'purpose'.
     * 
     * @param purpose the value of field 'purpose'.
     */
    public void setPurpose(
            final java.lang.String purpose) {
        this._purpose = purpose;
    }

    /**
     * Sets the value of field 'source'.
     * 
     * @param source the value of field 'source'.
     */
    public void setSource(
            final java.lang.String source) {
        this._source = source;
    }

    /**
     * Sets the value of field 'termType'.
     * 
     * @param termType the value of field 'termType'.
     */
    public void setTermType(
            final es.pode.modificador.negocio.cambios.configuracion.castor.types.TermType termType) {
        this._termType = termType;
    }

}
