package es.pode.parseadorXML.lomes.lomesAgrega;

import java.util.ArrayList;

public class FechaAgrega {

	private String fecha = null;
	private ArrayList descripciones= new ArrayList();
	
	public ArrayList getDescripciones() {
		return descripciones;
	}
	public void setDescripciones(ArrayList descripciones) {
		this.descripciones = descripciones;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
}
