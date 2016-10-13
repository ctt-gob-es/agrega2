package es.pode.visualizador.presentacion.portada;

/**
 * Esta clase implementa un bean para poder interceptar los accesos a la portada
 * porque no hay otra forma de saber, mediante las llamadas que se hacen desde la portada, cuando se 
 * accede a la misma.
 * @author crmunoz
 *
 */
public class IntercepcionPortadaImpl implements IntercepcionPortada {

	/* (non-Javadoc)
	 * @see es.pode.visualizador.presentacion.portada.IntercepcionPortada#accesoPortada(java.lang.String)
	 */
	@Override
	public void accesoPortada(String idioma)
	{
		return;
	}
}
