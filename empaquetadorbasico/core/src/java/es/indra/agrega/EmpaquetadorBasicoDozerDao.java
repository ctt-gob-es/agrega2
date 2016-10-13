/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.indra.agrega;

import net.sf.dozer.util.mapping.MapperIF;
import es.pode.empaquetador.negocio.gestionficheros.Fichero;
import es.pode.empaquetador.negocio.servicio.FicheroVO;

public class EmpaquetadorBasicoDozerDao {
	//private static Logger logger = Logger.getLogger(EmpaquetadorBasicoDozerDao.class);
	
	public EmpaquetadorBasicoDozerDao() {
	}
	
	private MapperIF beanMapperAux = null;

	public MapperIF getBeanMapperAux() {
		return beanMapperAux;
	}

	public void setBeanMapperAux(MapperIF beanMapperAux) {
		this.beanMapperAux = beanMapperAux;
	}
	
	public FicheroVO toFicheroVO(Fichero fichero) {
		FicheroVO nuevoFichero = (FicheroVO) this.getBeanMapperAux().map(fichero, FicheroVO.class);
		
		return nuevoFichero;
	}
	
	public Fichero fromFicheroVO(FicheroVO ficheroVO) {
		Fichero nuevoFichero = (Fichero) this.getBeanMapperAux().map(ficheroVO, Fichero.class);
		return nuevoFichero;
	}
}
