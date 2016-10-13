package es.pode.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import es.pode.modelo.Localizador;
import es.pode.modelo.OdePublicado;
import es.pode.negocio.SRVOdePublicado;
import es.pode.util.HibernateUtilLocalizador;
import es.pode.util.HibernateUtilPublicacion;

public class LocalizadorDAO {

	private static Logger logger = Logger.getLogger(LocalizadorDAO.class);
	private final int PAGINA_SELECT_IN = 100;
	public List<Localizador> buscarDeListaIds(List listaIDs)
	{
		Session s = HibernateUtilLocalizador.getSession();
		logger.debug("buscarDeListaIds");
		ArrayList resultadosLoc = new ArrayList();
//		Hacemos paginado el "in" porque puede dar un overflow, paginas de 100
		Iterator<String> it = listaIDs.iterator();
		String sentenciaIn = "";
		int actualesEnSentencia = 0;
		while (it.hasNext()) {
			String id = (String) it.next();
			if (actualesEnSentencia<this.PAGINA_SELECT_IN) // si aun no he llegado a tener el tamaño de la pagina, añado más eltos al "in"
			{
				if (actualesEnSentencia+1 == this.PAGINA_SELECT_IN) // es el ultimo de la secuencia de pagina
					sentenciaIn+=" '"+id+"'";
				else
					sentenciaIn+=" '"+id+"',";
				actualesEnSentencia++;
			}
			else // ya tengo los suficientes en la pagina, lanzo la consulta y recojo los resultados.
			{
				//lanzo la sentencia
				List<Localizador> localizadores = s.createQuery("select l from Localizador l where l.identificador in ("+sentenciaIn+")").list();
				//recojo los resulados
				resultadosLoc.addAll(localizadores);
				//reinicio las variables
				sentenciaIn="";
				actualesEnSentencia=0;
			}
		}
//		Tengo que ver si me quedan ODEs en el tintero
		if (actualesEnSentencia > 0) {
//			Tengo que limpiar la variable sentenciaIn, porque tiene una "," al final
			sentenciaIn= sentenciaIn.substring(0, sentenciaIn.length()-1);
			List<Localizador> localizadores = s.createQuery("select l from Localizador l where l.identificador in ("+sentenciaIn+")").list();
			//recojo los resulados
			resultadosLoc.addAll(localizadores);
		}
//		List<Localizador> odes = s.createQuery("select l from Localizador l where l.identificador in ('es_20081217_1_9221142','es_20080625_3_9025826')").list();
		s.close();
		return resultadosLoc;
		
	}
	public List<Localizador> listarTodos()
	{
		Session s = HibernateUtilLocalizador.getSession();
		logger.debug("listarTodos");
		List<Localizador> odes = s.createQuery("select o from Localizador o").list();
		s.close();
		return odes;
	}
	
	public Localizador buscar(int id)
	{
		Session s = HibernateUtilLocalizador.getSession();
		Localizador o = (Localizador) s.get(Localizador.class, id); 
		s.close();
		return o;		
	}	
}
