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
