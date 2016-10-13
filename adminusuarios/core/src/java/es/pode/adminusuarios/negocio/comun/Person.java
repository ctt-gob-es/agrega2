package es.pode.adminusuarios.negocio.comun;


public class Person {
	
	private String usuario;
	private String nombreApellidos;
	private String hasedPasswd;

	public void setUsuario(String string) {
		this.usuario=string;
	}
	
	public String getUsuario() {
		return this.usuario;
	}

	public void setNombreApellidos(String string) {
		this.nombreApellidos=string;
	}

	public String getNombreApellidos() {
		return this.nombreApellidos;
	}

	public void setHasedPasswd(String string) {
		this.hasedPasswd=string;
	}

	public String getHasedPasswd() {
		return this.hasedPasswd;
	}
}
