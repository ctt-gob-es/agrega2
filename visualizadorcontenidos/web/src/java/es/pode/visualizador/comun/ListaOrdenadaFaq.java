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
