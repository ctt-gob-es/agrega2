/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.fuentestaxonomicas.negocio.soporte;

import java.util.ArrayList;

public class Taxon {
	private String padre = "";
	private String id = "";
	private String valorTax = "";
	private String ambito ="";
	private ArrayList<?> hijos = new ArrayList<Object>();
	public ArrayList<?> getHijos() {
		return hijos;
	}
	public void setHijos(ArrayList<?> hijos) {
		this.hijos = hijos;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPadre() {
		return padre;
	}
	public void setPadre(String padre) {
		this.padre = padre;
	}
	public String getValorTax() {
		return valorTax;
	}
	public void setValorTax(String valorTax) {
		this.valorTax = valorTax;
	}
	public String getAmbito() {
		return ambito;
	}
	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}


}
