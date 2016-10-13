package es.pode.parseadorXML.lomes.lomesAgrega;

import java.util.ArrayList;

public class TaxonAgrega {

	private String identificador=null;
	private ArrayList taxones=new ArrayList();
	
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public ArrayList getTaxones() {
		return taxones;
	}
	public void setTaxones(ArrayList taxones) {
		this.taxones = taxones;
	}
}
