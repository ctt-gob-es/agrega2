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
