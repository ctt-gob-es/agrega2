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
