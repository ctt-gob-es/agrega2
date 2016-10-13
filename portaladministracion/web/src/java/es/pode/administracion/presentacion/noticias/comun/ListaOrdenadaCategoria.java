package es.pode.administracion.presentacion.noticias.comun;

import java.text.Collator;
import java.util.Comparator;

import es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaTraducidaVO;

public class ListaOrdenadaCategoria implements Comparator{

	
	public int compare (Object o1, Object o2)
	{
		CategoriaNoticiaTraducidaVO obj1 = (CategoriaNoticiaTraducidaVO) o1;
		CategoriaNoticiaTraducidaVO obj2 = (CategoriaNoticiaTraducidaVO) o2;
		
		Collator esCollator = Collator.getInstance();

		if(obj1.getIdCategoriaNoticia().intValue() == obj2.getIdCategoriaNoticia().intValue())
			return esCollator.compare(obj1.getNombreCategoria(),obj2.getNombreCategoria())<0?-1:1;
	
		else
			return esCollator.compare(obj1.getNombreCategoria(),obj2.getNombreCategoria())<0?-1:1;
	}	

}
