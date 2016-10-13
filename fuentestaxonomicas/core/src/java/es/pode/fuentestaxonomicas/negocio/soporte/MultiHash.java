/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.fuentestaxonomicas.negocio.soporte;

import java.util.HashMap;

public class MultiHash {
	
	private HashMap<String, EntradaMultiHash> lista = new HashMap<String, EntradaMultiHash>();
	
	public void put(String identificador, String nombre, String fichero)
	{
		if(lista.containsKey(identificador))
		{
			lista.get(identificador).add(fichero);
		}else
		{
			EntradaMultiHash e = new EntradaMultiHash();
			e.setIdentificador(identificador);
			e.setNombre(nombre);
			e.add(fichero);
			lista.put(identificador, e);
		}
	}
	
	public EntradaMultiHash get(String identificador)
	{
		if(lista.containsKey(identificador))
			return lista.get(identificador);
		return null;
	}
	
	public EntradaMultiHash[] getAll()
	{
		return lista.values().toArray(new EntradaMultiHash[0]);
	}
	
	
	public static void main(String[] args) {
		
		MultiHash  mhm= new MultiHash();
		
		mhm.put("acclomes", "Arbol Curricular", "aaaa_es.xml");
		mhm.put("acclomes", "Arbol Curricular", "aaaa_eu.xml");
		mhm.put("acclomes", "Arbol Curricular", "aaaa_va.xml");
		mhm.put("acclomes", "Arbol Curricular", "aaaa_en.xml");
		mhm.put("acclomes", "Arbol Curricular", "aaaa_ca.xml");
		mhm.put("acclomes", "Arbol Curricular", "aaaa_gl.xml");
		
		mhm.put("tesETB001", "tesauro Etb Agrega", "tetb_es.xml");
		mhm.put("tesETB001", "tesauro Etb Agrega", "tetb_es.xml");
		mhm.put("tesETB001", "tesauro Etb Agrega", "tetb_eu.xml");
		mhm.put("tesETB001", "tesauro Etb Agrega", "tetb_va.xml");
		mhm.put("tesETB001", "tesauro Etb Agrega", "tetb_en.xml");
		mhm.put("tesETB001", "tesauro Etb Agrega", "tetb_ca.xml");
		mhm.put("tesETB001", "tesauro Etb Agrega", "tetb_gl.xml");

		
		
	}
}
