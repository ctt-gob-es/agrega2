/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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
