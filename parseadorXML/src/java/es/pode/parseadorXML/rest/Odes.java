/**
 * 
 */
package es.pode.parseadorXML.rest;

/**
 * Listado de resultados. Incluye informacion sobre paginacion.
 * 
 * @author fjespino
 */
public class Odes {

	/**
	 * Numero total de ODEs encontrados en la consulta.
	 */
	private Integer total = null;
	/**
	 * Numero de paginas en las que se divide el numero total de ODEs
	 * encontrados.
	 */
	private Integer pages = null;
	/**
	 * Pagina actual que se esta devolviendo en la respuesta.
	 */
	private Integer page = null;

	/**
	 * Listado de ODEs encontrados en la consulta y pagina solicitadas.
	 */
	private Ode[] odes = null;

	/**
	 * @return the total
	 */
	public Integer getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}

	/**
	 * @return the pages
	 */
	public Integer getPages() {
		return pages;
	}

	/**
	 * @param pages the pages to set
	 */
	public void setPages(Integer pages) {
		this.pages = pages;
	}

	/**
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Integer page) {
		this.page = page;
	}

	/**
	 * @return the odes
	 */
	public Ode[] getOdes() {
		return odes;
	}

	/**
	 * @param odes the odes to set
	 */
	public void setOdes(Ode[] odes) {
		this.odes = odes;
	}
}
