package es.pode.buscar.negocio.buscar.comparators;

import java.io.Serializable;
import java.util.Comparator;

import es.pode.buscar.negocio.buscar.servicios.ValoresBusquedaVO;

public class DocFechaPublicacionComparator implements Comparator, Serializable {
	
	public int compare(Object o1, Object o2) {
		if(((ValoresBusquedaVO) o2).getFechaPublicacion()==null || ((ValoresBusquedaVO) o2).getFechaPublicacion().equals(""))return -1;
		else if(((ValoresBusquedaVO) o1).getFechaPublicacion()==null || ((ValoresBusquedaVO) o1).getFechaPublicacion().equals(""))return 1;
		else if(Integer.parseInt(((ValoresBusquedaVO) o2).getFechaPublicacion()) < Integer.parseInt(((ValoresBusquedaVO) o1).getFechaPublicacion())) return -1;
		else if(Integer.parseInt(((ValoresBusquedaVO) o1).getFechaPublicacion()) < Integer.parseInt(((ValoresBusquedaVO) o2).getFechaPublicacion())) return 1;
		else if(((ValoresBusquedaVO) o2).getHoraPublicacion()==null || ((ValoresBusquedaVO) o2).getHoraPublicacion().equals(""))return -1;
		else if(((ValoresBusquedaVO) o1).getHoraPublicacion()==null || ((ValoresBusquedaVO) o1).getHoraPublicacion().equals(""))return 1;
		else if(Integer.parseInt(((ValoresBusquedaVO) o2).getHoraPublicacion()) < Integer.parseInt(((ValoresBusquedaVO) o1).getHoraPublicacion())) return -1;
		else if(Integer.parseInt(((ValoresBusquedaVO) o1).getHoraPublicacion()) < Integer.parseInt(((ValoresBusquedaVO) o2).getHoraPublicacion())) return 1;
		else return 0;
	}

}
