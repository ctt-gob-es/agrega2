package es.pode.indexador.negocio.soporte;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import net.sf.dozer.util.mapping.converters.CustomConverter;

import org.apache.log4j.Logger;

import es.pode.indexador.negocio.dominio.IndiceBusquedaImpl;
import es.pode.indexador.negocio.servicios.indexado.IdODEVO;
import es.pode.indexador.negocio.servicios.indexado.LomESPrimarioVO;
import es.pode.indexador.negocio.servicios.indexado.LomESSecundarioVO;
import es.pode.indexador.negocio.servicios.indexado.SrvIndexadorServiceImpl;
import es.pode.soporte.utiles.string.UtilesString;

public class IndiceBusquedaConverter implements CustomConverter {

	private static Logger logger = Logger.getLogger(IndiceBusquedaConverter.class);
	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.dozer.util.mapping.converters.CustomConverter#convert(java.lang.Object,
	 *      java.lang.Object, java.lang.Class, java.lang.Class)
	 */
	public Object convert(Object destination, Object source, Class destClass,
			Class srcClass) {
		if (source == null) {
			return null;
		}
    	if(source instanceof es.pode.indexador.negocio.servicios.indexado.IdODEVO)
    	{
    		es.pode.indexador.negocio.servicios.indexado.IdODEVO actual = (es.pode.indexador.negocio.servicios.indexado.IdODEVO)source;
    		if (actual.getIdODE()==null || actual.getIdODE().equals(""))
    		{
    			logger.debug("mapeando IdODEVO nulo, volvemos");
    			return new IndiceBusquedaImpl();
    		}
    		IndiceBusquedaImpl destino = (IndiceBusquedaImpl)destination;
    		if (destination == null)
    			destino = new IndiceBusquedaImpl();
    		try {    			
    			/*IDODE*/
				destino.setIdentificador(actual.getIdODE());
				destino.setLocalizador(actual.getLocalizadorODE());
				destino.setValoracion(actual.getValoracion().toString());
				destino.setConSinSecuencia(actual.getSecuencia().toString());
				destino.setImagen(actual.getImgFile());
				destino.setTamanio(actual.getTamanio().toString());
				/*CATALOGACION PRIMARIA*/
				String title = actual.getCatalogacionPrimaria().getTitulo();
				destino.setTitle(title);
				destino.setAltTitle(UtilesString.removeAccents(title));
				String description = actual.getCatalogacionPrimaria().getDescripcion();
				destino.setDescription(description);
				destino.setAltDescription(UtilesString.removeAccents(description));
				String keyword = DocumentoIndexacion.arrayToString(actual.getCatalogacionPrimaria().getPalabrasClave());
				destino.setKeyword(keyword);
				destino.setAltKeyword(UtilesString.removeAccents(keyword));
				/*literales arbol curricular*/
				String literalesArbolC = DocumentoIndexacion.arrayToString(actual.getCatalogacionPrimaria().getLiteralesArbolCurricular());
				destino.setLiteralArbolC(literalesArbolC);
				destino.setAltLiteralArbolC(UtilesString.removeAccents(literalesArbolC));
				destino.setAutor(DocumentoIndexacion.arrayToString(actual.getCatalogacionPrimaria().getAutor()));
				//fecha y hora de publicacion
				destino.setFechaPublicacion(DocumentoIndexacion.obtenerFechaPublicacion(actual.getCatalogacionPrimaria().getFechaPublicacion().getTime())[0]);
				destino.setHoraPublicacion(DocumentoIndexacion.obtenerFechaPublicacion(actual.getCatalogacionPrimaria().getFechaPublicacion().getTime())[1]);
				destino.setFormato(DocumentoIndexacion.arrayToString(actual.getCatalogacionPrimaria().getFormatos()));
				//Taxonomias
				String taxonomiaPath = DocumentoIndexacion.arrayToString(actual.getCatalogacionPrimaria().getTaxonomia());
				destino.setTaxonomiaPath(taxonomiaPath);
				String taxonomia = DocumentoIndexacion.arrayToString(DocumentoIndexacion.arrayString2ArrayStringAtomizado(actual.getCatalogacionPrimaria().getTaxonomia(), "/"));
				destino.setTaxonomia(taxonomia);
				String taxonomiaNodo = DocumentoIndexacion.arrayToString(DocumentoIndexacion.arrayString2ArrayStringAtomizadoUltimo(actual.getCatalogacionPrimaria().getTaxonomia(), "/"));
				destino.setTaxonomiaNodo(taxonomiaNodo);
				destino.setTipoRecurso(DocumentoIndexacion.arrayToString(actual.getCatalogacionPrimaria().getRecurso()));
				destino.setDestinatarios(DocumentoIndexacion.arrayToString(actual.getCatalogacionPrimaria().getDestinatarios()));
				destino.setLicencias(actual.getCatalogacionPrimaria().getLicencia());
				destino.setAmbito(DocumentoIndexacion.arrayToString(actual.getCatalogacionPrimaria().getAmbito()));
				destino.setIdioma(actual.getCatalogacionPrimaria().getIdioma());
				destino.setEdad(DocumentoIndexacion.arrayToString(actual.getCatalogacionPrimaria().getEdades()));
				destino.setAmbito(DocumentoIndexacion.arrayToString(actual.getCatalogacionPrimaria().getAmbito()));
				destino.setContexto(DocumentoIndexacion.arrayToString(actual.getCatalogacionPrimaria().getContextos()));
				destino.setProcesosCognitivos(DocumentoIndexacion.arrayToString(actual.getCatalogacionPrimaria().getProcesosCognitivos()));
				destino.setNivelAgregacion(actual.getCatalogacionPrimaria().getNivelAgregacion().toString());
				destino.setFuente(DocumentoIndexacion.arrayToString(actual.getCatalogacionPrimaria().getFuente()));
				destino.setRelacion(DocumentoIndexacion.arrayToString(actual.getCatalogacionPrimaria().getRelacion()));
				destino.setPublicador(DocumentoIndexacion.arrayToString(actual.getCatalogacionPrimaria().getPublicador()));
				destino.setContribuidor(DocumentoIndexacion.arrayToString(actual.getCatalogacionPrimaria().getContribuidor()));
				destino.setAlcance(DocumentoIndexacion.arrayToString(actual.getCatalogacionPrimaria().getAlcance()));
				destino.setDescriptionObjetivo(DocumentoIndexacion.arrayToString(actual.getCatalogacionPrimaria().getDescripciones()));
				
				destino.setContribucion(DocumentoIndexacion.obtenerContribucionesIndexar(actual.getCatalogacionPrimaria().getContribuciones()));
				
				/*CATALOGACION SECUNDARIA*/
				StringBuilder descripcion=new StringBuilder("");
				StringBuilder titulo = new StringBuilder("");
				StringBuilder keywords = new StringBuilder("");
				for(int j=0; actual.getCatalogacionSecundaria()!= null && j<actual.getCatalogacionSecundaria().length; j++){
					keywords.append(DocumentoIndexacion.arrayToString(actual.getCatalogacionSecundaria()[j].getPalabrasClave()));
					titulo.append(actual.getCatalogacionSecundaria()[j].getTitulo());
					descripcion.append(actual.getCatalogacionSecundaria()[j].getDescripcion());
					if((j+1)<actual.getCatalogacionSecundaria().length){
						keywords.append(SrvIndexadorServiceImpl.SEPARADOR_CAMPOS_MULTIVALUADOS); 
						titulo.append(SrvIndexadorServiceImpl.SEPARADOR_CAMPOS_MULTIVALUADOS); 
						descripcion.append(SrvIndexadorServiceImpl.SEPARADOR_CAMPOS_MULTIVALUADOS); 
					}
				}
				destino.setSecond_keywords(keywords.toString());
				destino.setSecond_title(titulo.toString());
				destino.setSecond_description(descripcion.toString());
    			
    		} catch (Exception e) {
    			Logger.getLogger(this.getClass()).error("ERROR: Error mapeando ["+source.getClass()+"]->["+destination.getClass()+"] intentando traducir el identificador: ["+actual.getIdODE()+"]. Devolvemos el mismo texto.",e);
    		}
    		return destino;
    	}
		//        	Nunca deberia mapear Impl a VO, nunca debería entrar por aqui;
		//    		No esta probado!
		    		IndiceBusquedaImpl actual = (IndiceBusquedaImpl)source;
		    		if (actual.getIdentificador()==null || actual.getIdentificador().equals(""))
		    		{
		    			logger.debug("mapeando IndiceBusquedaImpl nulo, volvemos");
		    			return new IdODEVO();
		    		}
		    		es.pode.indexador.negocio.servicios.indexado.IdODEVO destino = (es.pode.indexador.negocio.servicios.indexado.IdODEVO)destination;
		    		if (destination == null)
		    			destino = new IdODEVO();
		    		
		    		try {
		    			/*IDODE*/
		    			destino.setIdODE(actual.getIdentificador());
		    			destino.setImgFile(actual.getImagen());
		    			destino.setLocalizadorODE(actual.getLocalizador());
		    			destino.setSecuencia(Boolean.parseBoolean(actual.getConSinSecuencia()));
		    			destino.setTamanio(Float.parseFloat(actual.getTamanio()));
		    			destino.setValoracion(Float.parseFloat(actual.getValoracion()));
		    			
		    			/*CATALOGACION PRIMARIA*/
		    			LomESPrimarioVO catalogacionPrimaria = new LomESPrimarioVO();
		    			String[] alcance = {actual.getAlcance()};
		    			catalogacionPrimaria.setAlcance(alcance);
		    			String[] ambito = {actual.getAmbito()};
		    			catalogacionPrimaria.setAmbito(ambito);
		    			String[] autor = {actual.getAutor()};
		    			catalogacionPrimaria.setAutor(autor);
		    			String[] contextos = {actual.getContexto()};
		    			catalogacionPrimaria.setContextos(contextos);
		    			String[] contribuidor = {actual.getContribuidor()};
		    			catalogacionPrimaria.setContribuidor(contribuidor);
		    		
		    			catalogacionPrimaria.setDescripcion(actual.getDescription());
		    			String[] descripcion = {actual.getDescriptionObjetivo()};
		    			catalogacionPrimaria.setDescripciones(descripcion);
		    			String[] destinatarios = {actual.getDestinatarios()};
		    			catalogacionPrimaria.setDestinatarios(destinatarios);
		    			String[] edad = {actual.getEdad()};
		    			catalogacionPrimaria.setEdades(edad);
		    			
		    			//TODO
		    			Date date;
		    			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		    			date = formatter.parse(actual.getFechaPublicacion());
		    			Calendar cal =  Calendar.getInstance();
		    			cal.setTime(date);
		    			catalogacionPrimaria.setFechaPublicacion(cal);
		    			
		    			String[] formato = {actual.getFormato()};
		    			catalogacionPrimaria.setFormatos(formato);
		    			String[] fuente = {actual.getFuente()};
		    			catalogacionPrimaria.setFuente(fuente);
		    			catalogacionPrimaria.setIdioma(actual.getIdioma());
		    			catalogacionPrimaria.setLicencia(actual.getLicencias());
		    			catalogacionPrimaria.setNivelAgregacion(Integer.parseInt(actual.getNivelAgregacion()));
		    			String[] palabrasClave = {actual.getKeyword()};
		    			catalogacionPrimaria.setPalabrasClave(palabrasClave);
		    			String[] procesosCognitivos = {actual.getProcesosCognitivos()};
		    			catalogacionPrimaria.setProcesosCognitivos(procesosCognitivos);
		    			String[] publicador = {actual.getPublicador()};
		    			catalogacionPrimaria.setPublicador(publicador);
		    			String[] recurso = {actual.getTipoRecurso()};
		    			catalogacionPrimaria.setRecurso(recurso);
		    			String[] relacion = {actual.getRelacion()};
		    			catalogacionPrimaria.setRelacion(relacion);
		    			String[] taxonomia = {actual.getTaxonomia()};
		    			catalogacionPrimaria.setTaxonomia(taxonomia);
		    			catalogacionPrimaria.setTitulo(actual.getTitle());
		    			
						destino.setCatalogacionPrimaria(catalogacionPrimaria);
						
						/*CATALOGACION SECUNDARIA*/
						LomESSecundarioVO catalogacionSecundaria = new LomESSecundarioVO();
						catalogacionSecundaria.setDescripcion(actual.getSecond_description());
						String[] second_keywords = {actual.getSecond_keywords()};
						catalogacionSecundaria.setPalabrasClave(second_keywords);
						catalogacionSecundaria.setTitulo(actual.getSecond_title());
						
						LomESSecundarioVO[] catalogacionSec = new LomESSecundarioVO[]{catalogacionSecundaria};
						destino.setCatalogacionSecundaria(catalogacionSec);
						
		    		} catch (Exception e) {
		    			Logger.getLogger(this.getClass()).error("ERROR: Error mapeando ["+source.getClass()+"]->["+destination.getClass()+"] intentando traducir el identificador: ["+destino.getIdODE()+"]. Devolvemos el mismo texto.");
		    		}
		    		return destino;

    	}
	}

