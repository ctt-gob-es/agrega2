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
