/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.soporte.menu;

import java.util.ArrayList;
import java.util.Random;

public class GeneradorAleatorios {

	private static ArrayList valores = new ArrayList();

	private static Random rnd = new Random();

	public GeneradorAleatorios(int max) {
		for(int i=0;i<max;i++) valores.add(i);
	}

	public String getValor() {
		if (valores.size()==0) return (null);

		int n = getRandomNumber();
		String valor = String.valueOf(valores.get(n));
		valores.remove(n);
		return valor;
	}

	private int getRandomNumber() {
		int max = valores.size();
		int n = rnd.nextInt(max);
		return (n);
	}
}
