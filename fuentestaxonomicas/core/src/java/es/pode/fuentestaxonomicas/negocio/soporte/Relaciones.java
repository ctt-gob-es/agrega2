package es.pode.fuentestaxonomicas.negocio.soporte;

import java.util.ArrayList;
import java.util.List;

public class Relaciones {

	List asociativas;
	List jerarquicas;
	List idPadres;
	
	public Relaciones(){
		asociativas = new ArrayList();
		jerarquicas = new ArrayList();
		idPadres = new ArrayList();
	}
	
	public List getAsociativas() {
		return asociativas;
	}
	public void setAsociativas(List asociativas) {
		this.asociativas = asociativas;
	}
	public List getJerarquicas() {
		return jerarquicas;
	}
	public void setJerarquicas(List jerarquicas) {
		this.jerarquicas = jerarquicas;
	}

	public List getIdPadres() {
		return idPadres;
	}

	public void setIdPadres(List idPadres) {
		this.idPadres = idPadres;
	}

	

}
