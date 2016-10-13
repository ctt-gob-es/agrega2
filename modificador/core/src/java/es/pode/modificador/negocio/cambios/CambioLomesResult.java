package es.pode.modificador.negocio.cambios;

import es.pode.parseadorXML.castor.Lom;

/**
 *  Bean para encapsular el resultado de una Modificacion Lomes.
 * 
 * @author fjespino
 *
 */
public class CambioLomesResult {
	private Lom[] lomArray = null;
	private boolean result = false;
	/**
	 * @return the lomArray
	 */
	public Lom[] getLomArray() {
		return lomArray;
	}
	/**
	 * @param lomArray the lomArray to set
	 */
	public void setLomArray(Lom[] lomArray) {
		this.lomArray = lomArray;
	}
	/**
	 * @return the result
	 */
	public boolean isResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(boolean result) {
		this.result = result;
	}
}
