package es.pode.visualizador.presentacion.noticia;

import es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaTraducidaVO;




public class CategoriaCodex {	
	
	 /* Nombre de la categoria. */
    private java.lang.String nombreCategoria;

    /* ID de la categoria. */
    private java.lang.Long idCategoriaNoticia;

    /* ID de la categoria en el idioma correspondiente. */
    private java.lang.Long idCategoriaIdiomaNoticia;
    
    /* Idioma de la categoria. */
    private java.lang.String idioma;
    
    /*
     * Para poder tener disponible el título codificado al estilo URL bonito
     */
    private String nombreCodex;

	public java.lang.Long getIdCategoriaNoticia() {
		return idCategoriaNoticia;
	}

	public void setIdCategoriaNoticia(java.lang.Long idCategoriaNoticia) {
		this.idCategoriaNoticia = idCategoriaNoticia;
	}
	
	public java.lang.Long getIdCategoriaIdiomaNoticia() {
		return idCategoriaIdiomaNoticia;
	}

	public void setIdCategoriaIdiomaNoticia(java.lang.Long idCategoriaIdiomaNoticia) {
		this.idCategoriaIdiomaNoticia = idCategoriaIdiomaNoticia;
	}

	public java.lang.String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(java.lang.String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
	
	public java.lang.String getIdioma() 
	{
		return idioma;
	}
	public void setIdioma(java.lang.String idioma) {
		this.idioma = idioma;
	}
	

	public String getNombreCodex() {
		return nombreCodex;
	}

	public void setNombreCodex(String nombreCodex) {
		this.nombreCodex = nombreCodex;
	}
	
	
	public static CategoriaCodex mapToCodex(CategoriaNoticiaTraducidaVO tr) {
		if(tr==null){
			return null;
		}
		CategoriaCodex tr_codex = new CategoriaCodex();

		tr_codex.setIdCategoriaNoticia(tr.getIdCategoriaNoticia());
		tr_codex.setIdCategoriaIdiomaNoticia(tr.getIdCategoriaIdiomaNoticia());
		tr_codex.setIdioma(tr.getIdioma());
		tr_codex.setNombreCategoria(tr.getNombreCategoria());			
		String nombreCodex="";
		nombreCodex=tr.getNombreCategoria().replaceAll(" ", "/");
		nombreCodex=nombreCodex.replaceAll("á", "a");//Para que no haya tíldes
		nombreCodex=nombreCodex.replaceAll("é", "e");
		nombreCodex=nombreCodex.replaceAll("í", "i");
		nombreCodex=nombreCodex.replaceAll("ó", "o");
		nombreCodex=nombreCodex.replaceAll("ú", "u");
		nombreCodex=nombreCodex.replaceAll("Á", "A");
		nombreCodex=nombreCodex.replaceAll("É", "E");
		nombreCodex=nombreCodex.replaceAll("Í", "I");
		nombreCodex=nombreCodex.replaceAll("Ó", "O");
		nombreCodex=nombreCodex.replaceAll("Ú", "U");
		tr_codex.setNombreCodex(nombreCodex);
		return tr_codex;

	}
}
