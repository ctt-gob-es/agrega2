/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.dao;

import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import es.pode.modelo.OdePublicado;
import es.pode.util.HibernateUtilPublicacion;

public class OdePublicadoDAO {

	
	private static Logger logger = Logger.getLogger(OdePublicadoDAO.class);
	
	public List<OdePublicado> listarTodos()
	{
		Session s = HibernateUtilPublicacion.getSession();
		logger.debug("listarTodos");
		List<OdePublicado> odes = s.createQuery("select o from OdePublicado o").list();
		s.close();
		return odes;
	}
	public List<OdePublicado> listarTodosSinMD5()
	{
		Session s = HibernateUtilPublicacion.getSession();
		logger.debug("listarTodosSinMD5");
		List<OdePublicado> odes = s.createQuery("select o from OdePublicado o where o.md5 = '' or o.md5 is null").list();
		s.close();
		return odes;
	}
	
	public OdePublicado buscar(int id)
	{
		Session s = HibernateUtilPublicacion.getSession();
		OdePublicado o = (OdePublicado) s.get(OdePublicado.class, id);
		s.close();
		return o;
	}
	
	/*
	 * Este metodo modifica los ODEs de la tabla publicados con los codigo sMD5 que le pasan.
	 * El parametro de entrada es un par clave valor con el id del ODE y el codigo MD5 con el 
	 * que se tiene que modificar su entrada en la tabla.
	 * Devuelve el numero de ODEs modificados
	 * */
	public int modificaMD5(Hashtable tablaIdMd5)
	{
		Session s = HibernateUtilPublicacion.getSession();

		int retorno = 0;
//		Sacamos todos los identificadores de los ODES.
		String[] ids = (String[])tablaIdMd5.keySet().toArray(new String[0]);
		String sentencia = "";
//		Vamos uno a uno modificando los ODES.
		for (int i = 0; i < ids.length; i++) {
			sentencia = "update OdePublicado o set o.md5 = '"+(String)tablaIdMd5.get(ids[i])+"' where o.idODE = '"+ids[i]+"'";
			retorno+= s.createQuery(sentencia).executeUpdate();
		}
		s.flush();
		s.close();
		
		logger.debug("Numero de ODEs modificados["+retorno+"]");
		return retorno; 
	}
}