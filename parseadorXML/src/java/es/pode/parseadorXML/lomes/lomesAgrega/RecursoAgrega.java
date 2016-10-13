package es.pode.parseadorXML.lomes.lomesAgrega;

import java.util.ArrayList;

public class RecursoAgrega {
	
	private IdentificadorAgrega identificador=null;
	private ArrayList descripciones=null;
	
	public ArrayList getDescripciones() {
		return descripciones;
	}
	public void setDescripciones(ArrayList descripciones) {
		this.descripciones = descripciones;
	}
	public IdentificadorAgrega getIdentificador() {
		return identificador;
	}
	public void setIdentificador(IdentificadorAgrega identificador) {
		this.identificador = identificador;
	}

}
