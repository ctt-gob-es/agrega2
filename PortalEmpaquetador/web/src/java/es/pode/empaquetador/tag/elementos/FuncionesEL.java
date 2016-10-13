package es.pode.empaquetador.tag.elementos;

import java.util.Collection;

import es.pode.empaquetador.negocio.servicio.FileVO;

public class FuncionesEL {

	public static boolean esFichero(String principal, Collection<FileVO> ficheros) {
		boolean result = false;
		
		for(FileVO fichero:ficheros) {
			if(fichero.getHref().equals(principal)) {
				return true;
			}
		}
		
		return result;
	}
	
}
