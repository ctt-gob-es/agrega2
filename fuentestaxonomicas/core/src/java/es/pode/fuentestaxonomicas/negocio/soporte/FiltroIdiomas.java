package es.pode.fuentestaxonomicas.negocio.soporte;

import java.io.File;
import java.io.FilenameFilter;

public class FiltroIdiomas implements FilenameFilter {

	private String idioma;
	
	
	public FiltroIdiomas(String idioma) {
		super();
		this.idioma = idioma;
	}


	public boolean accept(File dir, String name) {
		return name.endsWith(idioma + ".xml");
	}

}
