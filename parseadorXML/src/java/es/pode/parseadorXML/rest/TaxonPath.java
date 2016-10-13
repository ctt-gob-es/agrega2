/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * 
 */
package es.pode.parseadorXML.rest;

/**
 * Ruta del arbol curricular en que se encuentra un ODE. Se proporcionan
 * identificador de ruta y descripción.
 * 
 * @author fjespino
 */
public class TaxonPath {

	/**
	 * Identificador de la ruta taxonomica (por ejemplo, 1.1)
	 */
	private String taxonId = null;
	/**
	 * Descripcion de la ruta taxonomica (por ejemplo, Educacion Primaria/Primer
	 * Ciclo).
	 */
	private String description = null;
	/**
	 * @return the taxonId
	 */
	public String getTaxonId() {
		return taxonId;
	}
	/**
	 * @param taxonId the taxonId to set
	 */
	public void setTaxonId(String taxonId) {
		this.taxonId = taxonId;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
