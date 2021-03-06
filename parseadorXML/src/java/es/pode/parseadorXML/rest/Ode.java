/**
 * 
 */
package es.pode.parseadorXML.rest;

/**
 * Clase con la informacion de un ODE en el resultado de la consulta REST.
 * 
 * @author fjespino
 */
public class Ode {
	
	/**
	 * Identificador unico del ODE en la plataforma.
	 */
	private String identifier = null;
	/**
	 * Titulo del ODE
	 */
	private String title = null;
	/**
	 * URL a la ficha del ODE.
	 */
	private String url = null;
	/**
	 * URL para previsualizar el ODE.
	 */
	private String urlPreview = null;
	/**
	 * Rutas del arbol curricular a las que pertenece el ODE encontrado.
	 */
	private TaxonPath[] taxonPath = null;
	
	private Short agregationLevel = null;
	
	/**
	 * @return the aggregationLevel
	 */
	public Short getAgregationLevel() {
		return agregationLevel;
	}
	/**
	 * @param aggregationLevel the aggregationLevel to set
	 */
	public void setAgregationLevel(Short agregationLevel) {
		this.agregationLevel = agregationLevel;
	}
	/**
	 * @return the evaluation
	 */
	public Float getEvaluation() {
		return evaluation;
	}
	/**
	 * @param evaluation the evaluation to set
	 */
	public void setEvaluation(Float evaluation) {
		this.evaluation = evaluation;
	}
	private Float evaluation = null;
	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}
	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the urlPreview
	 */
	public String getUrlPreview() {
		return urlPreview;
	}
	/**
	 * @param urlPreview the urlPreview to set
	 */
	public void setUrlPreview(String urlPreview) {
		this.urlPreview = urlPreview;
	}
	/**
	 * @return the taxonPath
	 */
	public TaxonPath[] getTaxonPath() {
		return taxonPath;
	}
	/**
	 * @param taxonPath the taxonPath to set
	 */
	public void setTaxonPath(TaxonPath[] taxonPath) {
		this.taxonPath = taxonPath;
	}

	
}
