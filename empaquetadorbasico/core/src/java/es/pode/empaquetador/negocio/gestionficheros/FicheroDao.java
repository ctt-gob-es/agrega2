package es.pode.empaquetador.negocio.gestionficheros;

import es.pode.empaquetador.negocio.servicio.FicheroVO;

public interface FicheroDao {
	public Fichero cargar(Fichero fichero)throws FicheroException;
	public void almacenar(Fichero fichero)throws FicheroException;
	public void eliminar(Fichero fichero)throws FicheroException;
	public void crearCarpeta(String carpeta, String nuevaCarpeta) throws FicheroException;
	public Fichero fromFicheroVO(FicheroVO ficheroVO);
	public FicheroVO toFicheroVO(Fichero fichero);
}
