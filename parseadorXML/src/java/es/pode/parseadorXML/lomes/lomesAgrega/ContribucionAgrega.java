/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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
