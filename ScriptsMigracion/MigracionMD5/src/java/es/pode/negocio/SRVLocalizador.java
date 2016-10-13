/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.negocio;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import es.pode.dao.LocalizadorDAO;
import es.pode.modelo.Localizador;

public class SRVLocalizador {
	LocalizadorDAO locDAO = new LocalizadorDAO();
	private static Logger logger = Logger.getLogger(SRVLocalizador.class);
//	Devolvemos la lista de los paths asociados a la lista de ODES que nos pasan. El Hash devuelto tiene la relación entre id(clave) y path(valor)
	public Hashtable<String, String> obtenerPathODES(List<String> ids)
	{
		//LN
		
		List<Localizador> listaRetorno = locDAO.buscarDeListaIds(ids);
		Hashtable<String, String> tablaLoc = new Hashtable();
		if (ids.size() != listaRetorno.size())
			logger.error("Mal rollo porque el numero de localizadores["+listaRetorno.size()+"] y el de ODEs sin MD5 publicados["+ids.size()+"] no son el mismo");
//		Iteramos sobre los localizadores para poder calcular path
		Localizador[] arrayLocalizadores = listaRetorno.toArray(new Localizador[0]);
		Hashtable<String, String> resultados = new Hashtable();
		for (int i = 0; i < arrayLocalizadores.length; i++) {
			// sacamos el path del ODE
			resultados.put(arrayLocalizadores[i].getIdentificador(), arrayLocalizadores[i].getPath()); // no se si es path o url.
		}
		return resultados;
	}
	
////	Listamos todos los id's de los ODES publicados de la plataforma que no tienen MD5 en su columna.
//	public List<String> listarTodosSinMD5()
//	{
//		logger.debug("EN GP");
//		//LN
//		List<OdePublicado> listaODES = odeDAO.listarTodosSinMD5();
//		OdePublicado[] odesArray = listaODES.toArray(new OdePublicado[0]);
//		List<String> listaIds = new ArrayList();
//		for (int i = 0; i < odesArray.length; i++) {
//			listaIds.add(odesArray[i].getIdODE());
//		}
//		return listaIds;
//	}
}
