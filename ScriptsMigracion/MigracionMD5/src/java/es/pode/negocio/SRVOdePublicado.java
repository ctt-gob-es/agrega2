/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.negocio;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;

import es.pode.dao.OdePublicadoDAO;
import es.pode.modelo.OdePublicado;

public class SRVOdePublicado {
	OdePublicadoDAO odeDAO = new OdePublicadoDAO();
	private static Logger logger = Logger.getLogger(SRVOdePublicado.class);
//	Listamos todos los id's de los ODES publicados de la plataforma.
	public List<String> listarTodos()
	{
		List<OdePublicado> listaODES = odeDAO.listarTodos();
		OdePublicado[] odesArray = listaODES.toArray(new OdePublicado[0]);
		List<String> listaIds = new ArrayList<String>();
		for (int i = 0; i < odesArray.length; i++) {
			listaIds.add(odesArray[i].getIdODE());
		}
		return listaIds;
	}
	
//	Listamos todos los id's de los ODES publicados de la plataforma que no tienen MD5 en su columna.
	public List<String> listarTodosSinMD5()
	{
		List<OdePublicado> listaODES = odeDAO.listarTodosSinMD5();
		OdePublicado[] odesArray = listaODES.toArray(new OdePublicado[0]);
		List<String> listaIds = new ArrayList<String>();
		for (int i = 0; i < odesArray.length; i++) {
			listaIds.add(odesArray[i].getIdODE());
		}
		return listaIds;
	}
	
	/* Modificamos todos los id's de los ODES publicados de la plataforma que nos pasan con el codigo MD5 que nos pasan.
	 * Se devuelve el numero de ODEs que se han modificado.
	 * */
	public int modificaODEsMD5(Hashtable listaODesMD5)
	{
//		Saneamiento de variables
		if (listaODesMD5 == null || listaODesMD5.isEmpty())
		{
			logger.error("Error modificando lista de ODEs publicados. Lista vacía.");
			return 0;
		}
		int numeroModificados = odeDAO.modificaMD5(listaODesMD5);
		logger.debug("Hemos modificado ["+numeroModificados+"] ODEs.");
		return numeroModificados;
	}
}
