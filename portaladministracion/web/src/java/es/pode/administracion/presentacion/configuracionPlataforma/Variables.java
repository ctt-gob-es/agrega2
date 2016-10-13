/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * 
 */
package es.pode.administracion.presentacion.configuracionPlataforma;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dgonzalezd
 *
 */
public class Variables {

	private String nombre;
	private String[] valoresPosibles;
	private String valor;
	
	public Variables(String nombre, String[] valoresPosibles, String valor) {
		super();
		this.nombre = nombre;
		this.valoresPosibles = valoresPosibles;
		this.valor=valor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String[] getValoresPosibles() {
		return valoresPosibles;
	}
	public void setValoresPosibles(String[] valoresPosibles) {
		this.valoresPosibles = valoresPosibles;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	static List<Variables> getVariablesDummy() {
		ArrayList<Variables> lista = new ArrayList<Variables>();
		lista.add(new Variables("check",new String[]{"uno","dos"},""));
		lista.add(new Variables("texto libre",new String[]{},""));
		lista.add(new Variables("combo",new String[]{"uno","dos","tres"},""));
		
		return lista;
	}
	
}
