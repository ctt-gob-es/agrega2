/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.fuentestaxonomicas.negocio.soporte;

import java.io.Serializable;
import java.util.Comparator;

import es.pode.fuentestaxonomicas.negocio.servicio.VdexVO;

public class VdexComparator implements Comparator<VdexVO>,Serializable {

	private static final long serialVersionUID = -3160273294135868043L;

	public int compare(VdexVO vdex1, VdexVO vdex2) 
	{
		VdexVO v1= vdex1;
		VdexVO v2=vdex2;
		return v1.getNombre().compareToIgnoreCase(v2.getNombre());
	}

}