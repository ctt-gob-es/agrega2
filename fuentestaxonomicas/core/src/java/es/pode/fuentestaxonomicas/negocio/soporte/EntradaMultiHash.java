package es.pode.fuentestaxonomicas.negocio.soporte;

import java.util.SortedSet;
import java.util.TreeSet;

public class EntradaMultiHash {

	private String identificador;
	private String nombre;
	private SortedSet<String> ficheros;
	
	public EntradaMultiHash()
	{
		this.ficheros= new TreeSet<String>();
	}
	
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void add(String fichero)
	{
		ficheros.add(fichero);
	}
	
	public String[] getFicheros()
	{
		return ficheros.toArray(new String[0]);
	}
	
}
