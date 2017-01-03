package es.pode.soporte.elastic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.pode.soporte.url.Proxy;

public class BuscadorMetadatosFederados {
	
	private static Logger logger = Logger.getLogger(BuscadorMetadatosFederados.class);

	public static List<Source> buscarMetadatosFederados(String url,String idODE, String nodo, String fechaInicio, String fechaFin, int desde, int tamPagina) {

		logger.info("buscarMetadatosFederados " );
		
		List<Source> respuestas = new ArrayList<Source>();
		
		try
		{
			String filtro = prepararFiltro(idODE, nodo, fechaInicio, fechaFin, desde, tamPagina);					

			url = url.concat("_search");
			
			RespuestaES respuesta =  new ObjectMapper().readValue(Proxy.postUrlConnection(url, filtro),RespuestaES.class);
						
			logger.info("Respuesta " + respuesta.getHits());
			logger.info("Respuestas encontradas: " + respuesta.getHits().getTotal());
			
			for (Iterator<Hit> iterator = respuesta.getHits().getHits().iterator(); iterator.hasNext();) {
				Hit hit = iterator.next();								
				respuestas.add(hit.get_source());
			}
		}catch (Exception e) {
			logger.error("Error al realizar la búsqueda de metadatos federados : ",e );
		}
		return respuestas;
	}
	
	public static int obtenerTotalMetadatosFederados(String url,String idODE, String nodo, String fechaInicio, String fechaFin, int desde, int tamPagina) {

		logger.info("buscarMetadatosFederados " );
		
		int numTotalResultados=-1;
		
		try
		{
			String filtro = prepararFiltro(idODE, nodo, fechaInicio, fechaFin, desde, tamPagina);
			
			url = url.concat("_search");

			RespuestaES respuesta =  new ObjectMapper().readValue(Proxy.postUrlConnection(url, filtro),RespuestaES.class);
									
			logger.info("Respuestas encontradas: " + respuesta.getHits().getTotal());
			
			numTotalResultados = respuesta.getHits().getTotal();
			
		}catch (Exception e) {
			logger.error("Error al realizar la búsqueda de metadatos federados : ",e );
		}
		return numTotalResultados;
	}

	public static String prepararFiltro(String idODE, String nodo, String fechaInicio, String fechaFin, int desde, int tamPagina) {
				
		String requestBody = "";
		try
		{								
			boolean incluirComa=false;
			
			String paginacion = "\"from\" : ".concat(desde+"").concat(", \"size\" : ").concat(tamPagina+"");
			
			requestBody = "{".concat(paginacion);
			
			if((idODE!=null && !idODE.equals("")) ||
					(nodo!=null && !nodo.equals("")) ||
					(fechaInicio!=null && !fechaInicio.equals("")) ||
					(fechaFin!=null && !fechaFin.equals("")))
			{
				requestBody = requestBody.concat(",\"query\":{\"bool\":{\"must\":[");
				
				if (idODE!=null && !idODE.equals(""))
				{					
					requestBody = requestBody.concat("{\"match\":{\"idODE\":\"").concat(idODE).concat("\"}}");
					incluirComa=true;
				}
				if (nodo!=null && !nodo.equals(""))
				{
					if (incluirComa)
						requestBody = requestBody.concat(",");
					requestBody = requestBody.concat("{\"match\":{\"nodo\":\"").concat(nodo).concat("\"}}");
					incluirComa = true;
				}
				if ((fechaInicio!=null && !fechaInicio.equals(""))&& (fechaFin!=null && !fechaFin.equals("")))
				{
					fechaFin = fechaFin.replace("-", "");
					fechaInicio = fechaInicio.replace("-", "");
					if (incluirComa)
						requestBody = requestBody.concat(",");
					requestBody = requestBody.concat("{ \"range\": {\"fechaRegistro\": {");
					requestBody = requestBody.concat("\"gte\": \"").concat(fechaInicio).concat("\",\"lte\": \"").concat(fechaFin).concat("\"}}}");
					incluirComa=true;
				}
				
				requestBody = requestBody.concat("]}}");
			}				
				
			requestBody = requestBody.concat(",\"sort\": { \"fechaRegistro\": { \"order\": \"desc\" }}");
			
			requestBody = requestBody.concat("}");					
			
			logger.debug("body : "  + requestBody);
									
		}catch (Exception excp)
		{
			logger.error("Error al generar el filtro: ",  excp);
		}
		
		return requestBody;
	}
	
	private static boolean insertarIndiceMetadatosFederados(
			String url, 
			String idODE,
			String estado, 
			String nodo, 
			String fechaPublicacion,
			String fechaDespublicacion,
			String ruta) {		
		logger.info(" insertarIndiceMetadatosFederados " );
		
		Source entradaES = new Source();
		entradaES.setEstado(estado);
		entradaES.setFechaPublicacion(fechaPublicacion);
		entradaES.setFechaDespublicacion(fechaDespublicacion);
		entradaES.setIdODE(idODE);
		entradaES.setRuta(ruta);
		entradaES.setNodo(nodo);
		
		if(fechaPublicacion!=null && !fechaPublicacion.isEmpty())
			entradaES.setFechaRegistro(fechaPublicacion);
		else if(fechaDespublicacion!=null && !fechaDespublicacion.isEmpty())
			entradaES.setFechaRegistro(fechaDespublicacion);
			
		// Quitamos los guiones medios en la fecha para facilitar la búsqueda por rangos posterior
		entradaES.setFechaRegistro(entradaES.getFechaRegistro().replace("-", ""));
		logger.debug(" entradaES.toJson(): " + entradaES.toJson());
		Proxy.postUrlConnection(url,entradaES.toJson());
		
		return true;
	}
	
	
	public static boolean insertarIndiceMetadatosFederadosOdePublicado(
			String url, 
			String idODE,
			String nodo, 
			String fechaPublicacion,
			String ruta) {		
		
		return insertarIndiceMetadatosFederados(
					url, 
					idODE,
					"PUBLICADO", 
					nodo, 
					fechaPublicacion,
					null,
					ruta);
	}
	
	
	public static boolean insertarIndiceMetadatosFederadosOdeDespublicado(
			String url, 
			String idODE,
			String nodo, 
			String fechaDespublicacion,
			String ruta) {		
		
		return insertarIndiceMetadatosFederados(
					url, 
					idODE,
					"DESPUBLICADO", 
					nodo, 
					null,
					fechaDespublicacion,
					ruta);
	}
	
	
	public static void main(String[] args)
	{
		String url="http://eva.indra.es/ES/odesfederados/ODE/";
		String idODE = "es_2013121313_9121702";
		String nodo = "es_cnice_20080623";
		String fechaInicio ="20161114";
		String fechaFin ="20171117";
		
//		List<Source> lRespuestas = BuscadorMetadatosFederados.buscarMetadatosFederados(url,idODE , nodo, fechaInicio, fechaFin, 0, 50);
//		
//		for (Iterator<Source> iterator = lRespuestas.iterator(); iterator.hasNext();) {
//			Source hit = iterator.next();				
//			logger.info("ID:  " + hit.getIdODE());
//			logger.info("ID:  " + hit.getNodo());
//			logger.info("ID:  " + hit.getFechaRegistro());
//		}

		BuscadorMetadatosFederados.insertarIndiceMetadatosFederados(url, "io", "cdcd", "momo", "2016-12-12", "2016-12-12", "momo");
		
//		File fichero=null;
//		Source entradaES = new Source();
//		entradaES.setEstado("PUBLICADO");
//		entradaES.setFechaPublicacion("2016-11-10T12:24:36");
//		entradaES.setFechaRegistro("2016-11-10T12:24:36");
//		entradaES.setFechaDespublicacion("");
//		entradaES.setIdODE(fichero.getName().substring(0,fichero.getName().lastIndexOf(".")));
//		entradaES.setRuta(fichero.getAbsolutePath().replace("\\", "\\\\"));
//		entradaES.setNodo(fichero.getParentFile().getName());

	}
	
	
}
