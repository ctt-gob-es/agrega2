package es.pode.fuentestaxonomicas.negocio.dominio;

public class EstructurasDaoFactory {

	public EstructurasDaoInterface getInstance()
	{
		return new EstructurasDaoImpl();
	}
}
