package es.pode.parseadorXML.lomes.lomesAgrega;

public class RequisitoAgrega {

	private String tipo=null;
	private String nombre=null;
	private String versionMinima=null;
	private String versionMaxima=null;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getVersionMaxima() {
		return versionMaxima;
	}
	public void setVersionMaxima(String versionMaxima) {
		this.versionMaxima = versionMaxima;
	}
	public String getVersionMinima() {
		return versionMinima;
	}
	public void setVersionMinima(String versionMinima) {
		this.versionMinima = versionMinima;
	}
	
	
}
