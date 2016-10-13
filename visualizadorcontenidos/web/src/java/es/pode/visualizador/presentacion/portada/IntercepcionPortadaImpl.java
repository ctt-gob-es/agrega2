/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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
