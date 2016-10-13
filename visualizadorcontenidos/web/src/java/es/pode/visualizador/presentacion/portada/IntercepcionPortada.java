package es.pode.visualizador.presentacion.portada;

public interface IntercepcionPortada {

	/**
	 * Metodo para ser interceptado cuando se accede a la portada.
	 * @param idioma Idioma en el que se ha hecho el acceso a la portada.
	 */
	public abstract void accesoPortada(String idioma);

}