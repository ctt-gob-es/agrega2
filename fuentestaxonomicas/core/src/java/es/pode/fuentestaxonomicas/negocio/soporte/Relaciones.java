/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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
