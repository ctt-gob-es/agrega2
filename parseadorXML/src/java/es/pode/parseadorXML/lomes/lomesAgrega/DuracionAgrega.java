package es.pode.parseadorXML.lomes.lomesAgrega;

import java.util.ArrayList;

public class DuracionAgrega {

	private String duracion = null;
	private ArrayList descripciones= new ArrayList();
	
	public ArrayList getDescripciones() {
		return descripciones;
	}
	public void setDescripciones(ArrayList descripciones) {
		this.descripciones = descripciones;
	}
	public String getDuracion() {
		return duracion;
	}
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	
}
