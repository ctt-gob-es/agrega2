// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.oaiore.negocio.servicio;

import static es.pode.oaiore.negocio.servicio.OAIOREProperties.creador;
import static es.pode.oaiore.negocio.servicio.OAIOREProperties.entradasPorXML;
import static es.pode.oaiore.negocio.servicio.OAIOREProperties.nombreFichero;
import static es.pode.oaiore.negocio.servicio.OAIOREProperties.resultadosPorConsulta;
import static es.pode.oaiore.negocio.servicio.OAIOREProperties.rutaArbolCurricular;
import static es.pode.oaiore.negocio.servicio.OAIOREProperties.rutaInicial;
import static es.pode.oaiore.negocio.servicio.OAIOREProperties.rutaNivelAgregacion;
import static es.pode.oaiore.negocio.servicio.OAIOREProperties.rutaTesauro;
import static es.pode.oaiore.negocio.servicio.OAIOREProperties.tipoAreaCurricular;
import static es.pode.oaiore.negocio.servicio.OAIOREProperties.tipoNivelAgregacion;
import static es.pode.oaiore.negocio.servicio.OAIOREProperties.tipoTesauro;
import static es.pode.oaiore.negocio.servicio.OAIOREProperties.tipoTodos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

import se.kb.oai.ore.AggregatedResource;
import se.kb.oai.ore.Aggregation;
import se.kb.oai.ore.Metadata;
import se.kb.oai.ore.Metadata.Namespace;
import se.kb.oai.ore.Type;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO;
import es.pode.indexador.negocio.servicios.busqueda.DocVO30;
import es.pode.indexador.negocio.servicios.busqueda.ParamAvanzadoVO;
import es.pode.soporte.i18n.I18n;

/**
 * @see es.pode.oaiore.negocio.servicio.SrvOaiOre
 */

public class SrvOaiOreImpl
    extends es.pode.oaiore.negocio.servicio.SrvOaiOreBase
{
	private static final String TYPE_TEXT = "http://purl.org/dc/dcmitype/Text";
	
    /**
     * @param tipo
     * @throws Exception
     * @see es.pode.oaiore.negocio.servicio.SrvOaiOre#generarOreAtom(java.lang.Integer)
     */
    protected void handleGenerarOreAtom(java.lang.Integer tipo)
        throws java.lang.Exception
    {
    	if(tipo==Integer.parseInt(tipoNivelAgregacion.toString())
    			||tipo==Integer.parseInt(tipoTodos.toString())) {
    		for(int i=1; i<5;i++) {
    			logger.debug("Generamos OAI-ORE para nivel agregación "+i);
    			generarOreNivelAgregacion(i);
    		}
    	}
    	if(tipo==Integer.parseInt(tipoAreaCurricular.toString()) 
    			||tipo==Integer.parseInt(tipoTodos.toString())) {
    		//1. Con el vigente, llamada a taxonomia.obtenerTaxonomia, recibimos lista de 1er nivel, que NO irá en la lista
    		TaxonVO[] taxones1erNivel = getSrvTaxonomiaService().obtenerTaxonomia(arbolCurricularVigente(), I18n.getInstance().obtenerIdiomaDefectoPlataforma());
    		//2. Con cada 1er nivel, llamada a taxonomia.obtenerTaxonPath, recursivo hasta que no salga nada
    		ArrayList<String> taxonesFinales = recorreArbolCurricular(taxones1erNivel);
    	}
    	if(tipo==Integer.parseInt(tipoTesauro.toString()) 
    			||tipo==Integer.parseInt(tipoTodos.toString())) {
    		//1. Con el vigente, llamada a tesauros.obtenerPrimerNivelTesauro, lista 1er nivel, que irá en la lista
    		TaxonVO[] taxones1erNivel = getSrvTesaurosServices().obtenerPrimerNivelTesauro(tesauroVigente(), I18n.getInstance().obtenerIdiomaDefectoPlataforma());
    		//2. Con cada 1er nivel, llamada a obtenerTerminosRelacionadosPorId, recursivo hasta que no salga nada
    		ArrayList<String> taxonesFinales = new ArrayList<String>();
    		for (int i = 0; i < taxones1erNivel.length; i++) {
				TaxonVO taxonVO = taxones1erNivel[i];
				taxonesFinales=recorreTesauros(taxonesFinales, taxonVO.getId());
			}
    		
    		generarOreTesauro(taxonesFinales);
    	}
    }

	private ArrayList<String> recorreArbolCurricular(TaxonVO[] taxones1erNivel)
			throws RemoteException, Exception {
		ArrayList<String> lista = mapTaxones(taxones1erNivel);
		logger.debug("Vamos a recorrer taxones "+lista);
		for (Iterator<String> iterator = lista.iterator(); iterator.hasNext();) {
			String taxon = iterator.next();
			TaxonVO[] taxones = getSrvTaxonomiaService().obtenerNodos(taxon, arbolCurricularVigente(), I18n.getInstance().obtenerIdiomaDefectoPlataforma());
			logger.debug("Recuperamos "+taxones.length+" taxones");
			if((taxones!=null)&&(taxones.length>0)) {
				recorreArbolCurricular(taxones);
			}
		}
		generarOreArbolCurricular(lista);
		return lista;
	}
	
	private ArrayList<String> recorreTesauros(ArrayList<String> lista, String taxon)
			throws RemoteException, Exception {

		logger.debug("Lista ahora es "+lista+" y vamos a ver los relacionados con "+taxon);
			TaxonVO[] taxones = getSrvTesaurosServices().obtenerTerminosRelacionadosPorId(taxon, tesauroVigente(), I18n.getInstance().obtenerIdiomaDefectoPlataforma());
			if(taxones!=null&&taxones.length>0) {
				for (int i = 0; i < taxones.length; i++) {
					String taxonNuevo = taxones[i].getId();
					if(!lista.contains(taxonNuevo)) {
						lista.add(taxonNuevo);
						lista=recorreTesauros(lista,taxonNuevo);
					}
				}
			}
		return lista;
	}

    private ArrayList<String> mapTaxones(TaxonVO[] taxones) {
    	ArrayList<String> salida=new ArrayList<String>();
    	for (int i = 0; i < taxones.length; i++) {
			salida.add(taxones[i].getId());
		}
    	return salida;
    }
    /**
     * @see es.pode.oaiore.negocio.servicio.SrvOaiOre#entregarAtom(es.pode.oaiore.negocio.servicio.TipoAtomOre)
     * @param tipo
     * @return String
     * @throws Exception
     */
    protected java.lang.String handleEntregarAtom(es.pode.oaiore.negocio.servicio.TipoAtomOre tipo)
        throws java.lang.Exception
    {
    	if(tipo.getTipo()!=null) {
    		String ruta="";
	    	if(tipo.getTipo()==Integer.parseInt(tipoNivelAgregacion.toString())&&tipo.getNivelAgregacion()!=null) {
	    		//Construimos ruta
	    		ruta=generarRutaNivelAgregacion(tipo.getNivelAgregacion());
	    	}
	    	if(tipo.getTipo()==Integer.parseInt(tipoAreaCurricular.toString())&&tipo.getAreaCurricular()!=null) {
	    		//Nos quedamos con el último
	    		String[] partes = tipo.getAreaCurricular().split("/");
	    		String actual = partes[partes.length-1];
	    		ruta=generarRutaArbolCurricular(arbolCurricularVigente(), actual);
	    	}
	    	if(tipo.getTipo()==Integer.parseInt(tipoTesauro.toString())&&tipo.getTesauro()!=null) {
	    		//Recorremos tipo.getTesauro()
	    		String[] partes = tipo.getTesauro().split("/");
	    		String actual = partes[partes.length-1];
				ruta = generarRutaTesauro(tesauroVigente(), actual);
	    	}
	    	return devolverXML(ruta);
    	}
        return "";
    }

	private java.lang.String devolverXML(String ruta)
			throws FileNotFoundException, IOException {
		logger.debug("Devolveremos XML situado en "+ruta);
		//Abrimos contenido fichero
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = new BufferedReader(new FileReader(ruta));
		String linea=reader.readLine();;
		while (linea!=null) {
//			logger.debug("Leo linea: "+linea);
			buffer.append(linea);
			linea=reader.readLine();
		}
		//y la devolvemos como String
		return buffer.toString();
	}
    
    private void generarOreNivelAgregacion(Integer nivelAgregacion) {
    	ParamAvanzadoVO param = new ParamAvanzadoVO();

    	param.setNivelAgregacion(new String[]{nivelAgregacion.toString()});
     	logger.debug("Escribiremos resultados en "+generarRutaNivelAgregacion(nivelAgregacion));
    	consultaAFichero(param, generarRutaNivelAgregacion(nivelAgregacion));

    }

	private String generarRutaNivelAgregacion(Integer nivelAgregacion) {
		return rutaInicial.toString()+rutaNivelAgregacion+nivelAgregacion+"/"+nombreFichero;
	}
    
    private void generarOreArbolCurricular(ArrayList<String> areaCurricular) {
    	ParamAvanzadoVO param = new ParamAvanzadoVO();
    	try {
    		for (Iterator iterator = areaCurricular.iterator(); iterator
					.hasNext();) {
				String actual = (String) iterator.next();
				//buscamos con él
	    		param.setTaxonomia(new String[]{arbolCurricularVigente()+"/"+actual});

				consultaAFichero(param, generarRutaArbolCurricular(arbolCurricularVigente(), actual));				
			}
//			//Tomamos inicio del array de String
//			String actual = areaCurricular.get(0);
//			//buscamos con él
//    		param.setTaxonomia(new String[]{actual});
//
//			consultaAFichero(param, generarRutaArbolCurricular(arbolCurricularVigente(), actual));
			
			
			
//			//Condición de salida
//			if(areaCurricular.size()==1) return;
//			
//			//Llamada recursiva sin el término usado
//			areaCurricular.remove(0);
//			generarOreArbolCurricular(areaCurricular);
		} catch (RemoteException e) {
			logger.error("Error al acceder a SrvTaxonomiaService",e);
		} catch (Exception e) {
			logger.error("Se ha producido un error.",e);
		}
    }

	private String generarRutaArbolCurricular(String vigente, String actual) {
		String[] partesActual=actual.split("\\W");
		StringBuilder actualExpandido = new StringBuilder();
		for (int i = 0; i < partesActual.length; i++) {
			actualExpandido.append(partesActual[i]+"/");
		}
		String rutaFinal = rutaInicial.toString()+rutaArbolCurricular+vigente+"/"+actualExpandido+nombreFichero;
		logger.debug("Ruta arbol curricular = "+rutaFinal);
		return rutaFinal;
	}

	private String arbolCurricularVigente() throws RemoteException, Exception {
		EstructuraVdexVO estructuraVdex=getSrvTaxonomiaService().obtenerArbolVigente();
		return estructuraVdex.getVocabIdentifier();
	}

    private void generarOreTesauro(ArrayList<String> tesauro) {
    	ParamAvanzadoVO param = new ParamAvanzadoVO();
    	try {
    		for (Iterator iterator = tesauro.iterator(); iterator.hasNext();) {
				String actual = (String) iterator.next();
				//buscamos con él
				param.setTaxonomia(new String[]{tesauroVigente()+"/"+actual});
				
				consultaAFichero(param, generarRutaTesauro(tesauroVigente(), actual));				
			}
			//Tomamos final del array de String
//			String actual = tesauro.get(tesauro.size()-1);
			//buscamos con él
//			param.setTaxonomia(new String[]{tesauroVigente()+"/"+actual});
//			
//			consultaAFichero(param, generarRutaTesauro(tesauroVigente(), actual));
			
			
//			//Condición de salida
//			if(tesauro.size()==1) return;
//			
//			//Llamada recursiva sin el término usado
//			tesauro.remove(tesauro.size()-1);
//			generarOreTesauro(tesauro);
		} catch (RemoteException e) {
			logger.error("Error al acceder a SrvTeasurosService",e);
		} catch (Exception e) {
			logger.error("Se ha producido un error.",e);
		}
    }

	private String tesauroVigente() throws RemoteException, Exception {
		EstructuraVdexVO vdexTesauro = getSrvTesaurosServices().obtenerTesauroVigente();
		return vdexTesauro.getVocabIdentifier();
	}

	private String generarRutaTesauro(String vigente, String actual) {
//		return rutaInicial.toString()+rutaTesauro+vigente+"/"+actual+"/"+nombreFichero;
		
		String[] partesActual=actual.split("\\W");
		StringBuilder actualExpandido = new StringBuilder();
		for (int i = 0; i < partesActual.length; i++) {
			actualExpandido.append(partesActual[i]+"/");
		}
		String rutaFinal = rutaInicial.toString()+rutaTesauro+vigente+"/"+actualExpandido+nombreFichero;
		logger.debug("Ruta tesauro = "+rutaFinal);
		return rutaFinal;
	}
    
	private void consultaAFichero(ParamAvanzadoVO param, String ruta) {
		try {
			param.setNumeroResultados(Integer.parseInt(resultadosPorConsulta.toString()));
			param.setIdiomaNavegacion(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
			param.setIdiomaBusqueda(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
			DocVO30[] resultados = getSrvBuscadorService().busquedaAvanzada(param).getResultados();

			if (resultados!=null) {
				logger.debug("Encontrados "+resultados.length+" resultados.");
				//			AtomFactory factory = new AtomFactory();
				Aggregation aggregation;
				String urlNodo = AgregaPropertiesImpl.getInstance().getUrlNodo();
				ResourceMap map = ResourceMap.nuevoResourceMap(ruta, urlNodo);
				aggregation = map.getAggregation();
				int numFicherosXML = 0;
				int entradas = Integer.parseInt(entradasPorXML.toString());
				for (int i = 0; i < resultados.length; i++) {
					//A efectos prácticos, entradasPorXML = 0 es igual a infinito
					if (i != 0 && entradas != 0 && i % entradas == 0) {
						logger
						.debug("Hemos superado numero maximo de entradas por xml, generando otro");
						String siguiente = ruta + "-" + Integer.toString(++numFicherosXML);

						anadirReferencia(aggregation, siguiente);
						map.toFile(ruta);

						String previo = ruta;
						ruta = siguiente;

						//Nuevo atom
						//					map = nuevoResourceMap(factory, ruta);
						map = ResourceMap.nuevoResourceMap(ruta, urlNodo);
						aggregation = map.getAggregation();

						anadirReferencia(aggregation, previo);
					}
					//Rellenamos resource
					logger.debug("Añado " + urlNodo
							+ "/ODE/" + resultados[i].getIdioma() + "/"
							+ resultados[i].getIdentificadorODE() + " a XML");
					AggregatedResource resource = new AggregatedResource(
							urlNodo + "/ODE/"
							+ resultados[i].getIdioma() + "/"
							+ resultados[i].getIdentificadorODE());
					resource.addType(new Type(TYPE_TEXT));
					// Add the author of the article.
					//resource.addMetadata(new Metadata(Namespace.DC, "creator", "Philip J. Fry"));
					// Add the AggregatedResource to the Aggregation.
					aggregation.addResource(resource);
				}
				logger.debug("Finalizamos generación");
				map.toFile(ruta);
			}

		} catch (Exception e) {
			logger.error("Se ha producido un error al realizar una busqueda avanzada: "+e,e);
		}
	}

	private void anadirReferencia(Aggregation aggregation, String siguiente)
			throws URISyntaxException {
		AggregatedResource resource = new AggregatedResource(siguiente);
		resource.addType(new Type(TYPE_TEXT));
		resource.addMetadata(new Metadata(Namespace.DC, "creator", creador.toString()));					
		aggregation.addResource(resource);
	}
}