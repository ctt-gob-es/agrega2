/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.visualizador.comun;

import java.util.Comparator;

import es.pode.contenidos.negocio.faqs.servicio.FaqTraducidaVO;



public class ListaOrdenadaFaq implements Comparator
{
	public int compare (Object o1, Object o2)
	{
		FaqTraducidaVO obj1 = (FaqTraducidaVO) o1;
		FaqTraducidaVO obj2 = (FaqTraducidaVO) o2;
		
		int resultado = obj1.getPosicion().intValue() - obj2.getPosicion().intValue();
		return resultado;
	}	

}
