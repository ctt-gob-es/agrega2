package es.pode.fuentestaxonomicas.negocio.soporte;

import java.io.File;
import java.io.FilenameFilter;

public class TerminacionFileNameFilter implements FilenameFilter {

	private String terminacion;
	
	public TerminacionFileNameFilter(String terminacion) {
		super();
		this.terminacion = terminacion;
	}

	public boolean accept(File dir, String nombre) 
	{
		
		return nombre.endsWith(terminacion);
	}

}
