package es.pode.fuentestaxonomicas.negocio.dominio;

import es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO;
import es.pode.fuentestaxonomicas.negocio.servicio.VdexListaVO;
import es.pode.fuentestaxonomicas.negocio.soporte.EstructuraVdex;

public interface EstructurasDaoInterface {
	
	public void recargarDatos() throws Exception;
	
	public EstructuraVdex obtenerTaxonomia(String key) throws Exception;
	
	public EstructuraVdex obtenerTaxonomia(String nombre, String idioma) throws Exception;
	
	public EstructuraVdexVO[] obtenerTaxonomias(String idioma) throws Exception;
	
	public EstructuraVdexVO[] obtenerTaxonomias() throws Exception;
	
	public VdexListaVO[] obtenerFicherosTaxonomias() throws Exception;
	
	public VdexListaVO obtenerTaxonomiaPorId(String identificador)throws Exception;
	
	public String[] traducirVocabNames(String[] claves) throws Exception;
	
	public void recargarTaxonomias() throws Exception;

	
	
	public EstructuraVdex obtenerTesauro(String key) throws Exception;
	
	public EstructuraVdex obtenerTerminoTesauro(String nomTesauro,String idioma) throws Exception; 
	
	public EstructuraVdex obtenerIdTesauro(String nomTesauro,String idioma)	throws Exception;
	
	public EstructuraVdex obtenerRelacionesTesauro(String nomTesauro,String idioma)	throws Exception;
	
	public EstructuraVdexVO[] obtenerTesauros(String idioma) throws Exception;
	
	public EstructuraVdexVO[] obtenerTesauros() throws Exception;

	public void recargarTesauros() throws Exception;
	
	public VdexListaVO[] obtenerFicherosTesauros()	throws Exception;
	
	public VdexListaVO obtenerTesauroPorId(String identificador) throws Exception;
	
	
	
	public String existeVocabName( String vocabName)throws Exception;
	
	public String existeVocabIdentifier( String identificador)throws Exception;
	
	public String obtenerNombreFicheroPorId(String identificador, String idioma) throws Exception;
	
}
