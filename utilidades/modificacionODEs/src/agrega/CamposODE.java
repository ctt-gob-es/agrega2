/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package agrega;

/*
 * Clase usada por EliminacionImagenesDuplicadas
 */

public class CamposODE {

	private String identificador;
	private String title;
	private String alt_title;
	private String description;
	private String alt_description;
	private String keyword;
	private String alt_keyword;
	private String formato;
	private String tipo_recurso;
	private String licencias;
	private String ambito;	
	private String idioma;
	private String edad;
	private String nivel_agregacion;
	private String publicador;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAlt_title() {
		return alt_title;
	}
	public void setAlt_title(String altTitle) {
		alt_title = altTitle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAlt_description() {
		return alt_description;
	}
	public void setAlt_description(String altDescription) {
		alt_description = altDescription;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getAlt_keyword() {
		return alt_keyword;
	}
	public void setAlt_keyword(String altKeyword) {
		alt_keyword = altKeyword;
	}
	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	public String getTipo_recurso() {
		return tipo_recurso;
	}
	public void setTipo_recurso(String tipoRecurso) {
		tipo_recurso = tipoRecurso;
	}
	public String getLicencias() {
		return licencias;
	}
	public void setLicencias(String licencias) {
		this.licencias = licencias;
	}
	public String getAmbito() {
		return ambito;
	}
	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public String getEdad() {
		return edad;
	}
	public void setEdad(String edad) {
		this.edad = edad;
	}
	public String getNivel_agregacion() {
		return nivel_agregacion;
	}
	public void setNivel_agregacion(String nivelAgregacion) {
		nivel_agregacion = nivelAgregacion;
	}
	public String getPublicador() {
		return publicador;
	}
	public void setPublicador(String publicador) {
		this.publicador = publicador;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String obtenerValoresCamposConcatenados()
	{
		return title + "|" + alt_title + "|" + description + "|" + alt_description + "|" + keyword + "|" + alt_keyword + "|" + formato + "|" + tipo_recurso + "|" + licencias+ "|" +ambito+ "|" +idioma+ "|" +edad+ "|" +nivel_agregacion+ "|" +publicador;
	}
	public String toString()
	{
		return identificador + "|" + title + "|" + alt_title + "|" + description + "|" + alt_description + "|" + keyword + "|" + alt_keyword + "|" + formato + "|" + tipo_recurso + "|" + licencias+ "|" +ambito+ "|" +idioma+ "|" +edad+ "|" +nivel_agregacion+ "|" +publicador;
	}
	
}
