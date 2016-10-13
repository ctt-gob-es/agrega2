/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.modificador.negocio.servicio;



public class ComboEstados {
	private String  identificador;
	private String  valor;
	
	
	public static ComboEstados[] getInstance(String[] identificadores, String[] textos )
	{
		
		ComboEstados[] vuelta=new ComboEstados[identificadores.length];
		for(int i=0;i<identificadores.length;i++){
			
			ComboEstados combo=new ComboEstados();
			combo.setIdentificador(identificadores[i]);
			combo.setValor(textos[i]);
			vuelta[i]=combo;
		}
		return vuelta;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
