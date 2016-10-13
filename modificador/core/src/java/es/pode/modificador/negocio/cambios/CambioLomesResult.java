/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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
