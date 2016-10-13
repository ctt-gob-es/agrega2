package es.pode.contenidos.negocio.comun;

import java.io.Serializable;
import java.util.Comparator;

import es.pode.contenidos.negocio.faqs.servicio.FaqTraducidaIdiomaVO;

public class ListaOrdenada implements Comparator, Serializable
{
	public int compare (Object o1, Object o2)
	{
		FaqTraducidaIdiomaVO obj1 = (FaqTraducidaIdiomaVO) o1;
		FaqTraducidaIdiomaVO obj2 = (FaqTraducidaIdiomaVO) o2;
		
		int resultado = obj1.getPosicion().intValue() - obj2.getPosicion().intValue();
		return resultado;
	}	

}

