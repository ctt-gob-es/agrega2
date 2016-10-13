package es.pode.parseadorXML.lomes.lomesAgrega;

import java.util.ArrayList;

public class ContribucionAgrega {

	private String tipo=null;
	private ArrayList entidades=null;
	private FechaAgrega fecha=null;
	
	public ArrayList getEntidades() {
		return entidades;
	}
	public void setEntidades(ArrayList entidades) {
		this.entidades = entidades;
	}
	public FechaAgrega getFecha() {
		return fecha;
	}
	public void setFecha(FechaAgrega fecha) {
		this.fecha = fecha;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
