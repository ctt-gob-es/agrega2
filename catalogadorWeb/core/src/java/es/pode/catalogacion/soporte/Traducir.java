package es.pode.catalogacion.soporte;

import java.io.Serializable;

public class Traducir implements Serializable {
	private String idTrad;
	private String nomTrad;
	
	public void setIdentificador (String idTrad){
		this.idTrad=idTrad;
	}
	
	public String getIdentificador () {
		return this.idTrad;
	}
	
	public void setNombre(String nomTrad){
		this.nomTrad=nomTrad;
	}
	
	public String getNombre (){
		return this.nomTrad;
	}
	
}