// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.buscar.negocio.federada.servicios;

import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.buscar.negocio.buscar.servicios.ParametrosBusquedaAvanzadaVO;
import es.pode.buscar.negocio.buscar.servicios.ParametrosBusquedaAvanzadaVO30;
import es.pode.buscar.negocio.buscar.servicios.ParametrosDocsCountVO;
import es.pode.buscar.negocio.buscar.servicios.ParametrosDocsCountVO30;
import es.pode.buscar.negocio.buscar.servicios.ResultadosNodoCountVO;
import es.pode.buscar.negocio.buscar.servicios.SrvBuscarService;
import es.pode.buscar.negocio.federada.compatibilidad.DocVO;
import es.pode.buscar.negocio.federada.compatibilidad.DocumentosVO;
import es.pode.buscar.negocio.federada.compatibilidad.TaxonVO;
import es.pode.indexador.negocio.servicios.busqueda.DocVO30;
import es.pode.indexador.negocio.servicios.busqueda.DocumentosVO30;
import es.pode.indexador.negocio.servicios.busqueda.TaxonVO30;

/**
 * @see es.pode.buscar.negocio.federada.servicios.SrvBuscarFederadaService
 */

public class SrvBuscarFederadaServiceImpl
    extends es.pode.buscar.negocio.federada.servicios.SrvBuscarFederadaServiceBase
{

	private static final Logger logger = Logger.getLogger(SrvBuscarFederadaServiceImpl.class);


	/**
	 * @see es.pode.buscar.negocio.federada.servicios.SrvBuscarFederadaService#busquedaFederada(es.pode.buscar.negocio.buscar.servicios.ParametrosBusquedaAvanzadaVO)
	 * @param parametros es.pode.buscar.negocio.servicios.ParametrosBusquedaAvanzadaVO Este VO alberga los parametros que configuran la busqueda avanzada
	 * @return es.pode.indexador.negocio.servicios.busqueda.DocumentosVO30 Esta clase representa los resultados de una busqueda encontrados en el repositorio de ODE's
	 */
    protected DocumentosVO30 handleBusquedaFederada30(es.pode.buscar.negocio.buscar.servicios.ParametrosBusquedaAvanzadaVO30 parametros)
        throws java.lang.Exception
    {
    	return handleBusquedaFederada30IndiceLocal(parametros, "");
    }

    //TODO QUITAR, no se usa
	@Override
	protected DocumentosVO30 handleBusquedaFederada30IndiceLocal(
			ParametrosBusquedaAvanzadaVO30 parametros, String idNodo)
			throws Exception {
    	if(logger.isDebugEnabled()){
    		logger.debug("SrvBuscarFederadaServiceImpl - handleBusquedaFederada Inicio");
			if(parametros.getComunidadesSeleccionadas()!=null && parametros.getComunidadesSeleccionadas().length>0){
				for(int i=0; i<parametros.getComunidadesSeleccionadas().length;i++){
					logger.debug("SrvBuscarFederadaServiceImpl - handleBusquedaFederada i="+i+ " ComunidadesToString:"+parametros.getComunidadesSeleccionadas()[i]+"######"+parametros.getComunidadesSeleccionadas()[i].getClass());
				}
			}
    	}
    	if(idNodo.equals(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVER_ID))) {
    		idNodo="";
    	}
    	
    	SrvBuscarService srvBuscar= this.getSrvBuscarService();
    	DocumentosVO30 documentos = srvBuscar.busquedaAvanzadaIndiceRemoto(parametros,idNodo);
    	logger.debug("SrvBuscarFederadaServiceImpl - handleBusquedaFederada: Comunidad peticion="+parametros.getComunidadPeticion()+" Resultados devueltos="+documentos.getNumeroResultados()+" Total resultados="+documentos.getTotalResultados());
    	return documentos;
    }

	/**
	 * @see es.pode.buscar.negocio.servicios.SrvBuscarFederadaService#solicitarDocsCountArbolCurricular(es.pode.buscar.negocio.servicios.ParametrosBusquedaAvanzadaVO30)
	 * @param parametros es.pode.buscar.negocio.buscar.servicios.ResultadosNodoCountVO Este VO alberga los parametros para la solicitud de número de documentos coincidentes con un área curricular
	 * @return es.pode.buscar.negocio.buscar.servicios.ResultadosNodoCountVO Esta clase representa el resultado de la solicitud de número de odes para un área curricular
	 */
	protected ResultadosNodoCountVO handleSolicitarDocsCountArbolCurricular30(ParametrosDocsCountVO30 parametros) throws Exception {
		logger.debug("SrvBuscarFederadaServiceImpl - handleSolicitarDocsCountArbolCurricular Inicio");
    	SrvBuscarService srvBuscar= this.getSrvBuscarService();
    	ResultadosNodoCountVO resultados = srvBuscar.solicitudDocsCountLocal(parametros);
    	logger.debug("SrvBuscarFederadaServiceImpl - handleSolicitarDocsCountArbolCurricular Comunidad peticion="+parametros.getComunidadPeticion()+" Resultados devueltos="+ resultados.getNumeroResultados());
    	return resultados;
	}

	/**
	 * @see es.pode.buscar.negocio.servicios.SrvBuscarFederadaService#estoyActivo()
	 * @return boolean Devuelve true si está activo el servicio buscar en un nodo
	 */
	protected boolean handleEstoyActivo() throws Exception {
		return true;
	}
	/**
	 * Metodo para obtener el identificador del nodo
	 * @return String
	 * 			El identificador del nodo
	 */
	protected String handleObtenerIdentificadorNodo() throws Exception {
		logger.debug("SrvBuscarFederadaServiceImpl - handleObtenerIdentificadorNodo Inicio");
		String identificadorNodo = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVER_ID);
		logger.debug("SrvBuscarFederadaServiceImpl - handleObtenerIdentificadorNodo identificadorNodo="+identificadorNodo);
		return identificadorNodo;
	}

	@Override
	protected String handleDevolverVersionNodo() throws Exception {
		logger.debug("SrvBuscarFederadaServiceImpl - handleDevolverVersionNodo Inicio");
		String versionNodo = this.getSrvPropiedadService().getValorPropiedad(AgregaProperties.VERSION);
		logger.debug("SrvBuscarFederadaServiceImpl - handleDevolverVersionNodo versionNodo="+versionNodo);
		return versionNodo;
	}

	@Override
	protected DocumentosVO handleBusquedaFederada(
			ParametrosBusquedaAvanzadaVO parametros) throws Exception {
		
		if(logger.isDebugEnabled()){
	 		logger.debug("SrvBuscarFederadaServiceImpl - handleBusquedaFederadaOld Inicio");
				if(parametros.getComunidadesSeleccionadas()!=null && parametros.getComunidadesSeleccionadas().length>0){
					for(int i=0; i<parametros.getComunidadesSeleccionadas().length;i++){
						logger.debug("SrvBuscarFederadaServiceImpl - handleBusquedaFederadaOld i="+i+ " ComunidadesToString:"+parametros.getComunidadesSeleccionadas()[i]+"######"+parametros.getComunidadesSeleccionadas()[i].getClass());
					}
				}
	 	}
	 	SrvBuscarService srvBuscar= this.getSrvBuscarService();
	 	// Traduccion entre objetos de distintas versiones: parametros
	 	ParametrosBusquedaAvanzadaVO30 parametrosV3 = this.parseParametrosBusqueda(parametros);
		DocumentosVO30 documentosVO30 = srvBuscar.busquedaAvanzadaLocal(parametrosV3);
	 	// Traduccion entre objetos de distintas versiones: documentos
	 	DocumentosVO documentos = this.parseDocumentosVO(documentosVO30);
	 	logger.debug("SrvBuscarFederadaServiceImpl - handleBusquedaFederada: Comunidad peticion="+parametros.getComunidadPeticion()+" Resultados devueltos="+documentos.getNumeroResultados()+" Total resultados="+documentos.getTotalResultados());
	 	return documentos;
	}
	
	
	@Override
	protected ResultadosNodoCountVO handleSolicitarDocsCountArbolCurricular(
			ParametrosDocsCountVO parametros) throws Exception {
		
		logger.debug("SrvBuscarFederadaServiceImpl - handleSolicitarDocsCountArbolCurricularOld Inicio");
    	SrvBuscarService srvBuscar= this.getSrvBuscarService();
    	// Traduccion entre objetos de distintas versiones: parametros
	 	ParametrosDocsCountVO30 parametros30 = this.parseParametrosDocsCount(parametros);
    	ResultadosNodoCountVO resultados = srvBuscar.solicitudDocsCountLocal(parametros30);
    	logger.debug("SrvBuscarFederadaServiceImpl - handleSolicitarDocsCountArbolCurricularOld Comunidad peticion="+parametros.getComunidadPeticion()+" Resultados devueltos="+ resultados.getNumeroResultados());
    	return resultados;
	}
	
	/* Metodos auxiliares para el parseo entre objetos con el fin de mantener
	 * compatibilidad entre versiones
	 */
	
	private ParametrosBusquedaAvanzadaVO30 parseParametrosBusqueda (ParametrosBusquedaAvanzadaVO parametros){
		
		ParametrosBusquedaAvanzadaVO30 parametros30 = new ParametrosBusquedaAvanzadaVO30();

		parametros30.setAmbito(parametros.getAmbito());
		parametros30.setAutor(parametros.getAutor());
		parametros30.setBusquedaSimpleAvanzada(parametros.getBusquedaSimpleAvanzada());
		parametros30.setComunidadDestino("");
		parametros30.setComunidadesSeleccionadas(parametros.getComunidadesSeleccionadas());
		parametros30.setComunidadPeticion(parametros.getComunidadPeticion());
		parametros30.setContexto(parametros.getContexto());
		parametros30.setDescripcion(parametros.getDescripcion());
		parametros30.setDestinatarios(parametros.getDestinatarios());
		parametros30.setEdad(parametros.getEdad());
		parametros30.setFechaPublicacion(parametros.getFechaPublicacion());
		parametros30.setFormato(new String[]{parametros.getFormato()});
		parametros30.setIdBusqueda(parametros.getIdBusqueda());
		parametros30.setIdentificador("");
		parametros30.setIdiomaBusqueda(parametros.getIdiomaBusqueda());
		parametros30.setIdiomaNavegacion(parametros.getIdiomaNavegacion());
		parametros30.setKeyword(parametros.getKeyword());
		parametros30.setNivelAgregacion(new String[]{parametros.getNivelAgregacion()});
		parametros30.setNumeroResultados(parametros.getNumeroResultados());
		parametros30.setOrigenPagina(parametros.getOrigenPagina());
		parametros30.setPalabrasClave(parametros.getPalabrasClave());
		parametros30.setProcesoCognitivo(parametros.getProcesoCognitivo());
		parametros30.setRecurso(parametros.getRecurso());
		parametros30.setResultadosPorPagina(parametros.getResultadosPorPagina());
		parametros30.setSecuencia(parametros.getSecuencia());
		String areaCurricular = parametros.getAreaCurricular();
		String taxonomia = parametros.getArbolCurricularVigente() + '/' + areaCurricular;
		parametros30.setTaxonomia(new String[]{taxonomia});
		parametros30.setTitulo(parametros.getTitulo());
		parametros30.setValoracion(parametros.getValoracion());
		parametros30.setVersionComunidadDestino("2.0");
		return parametros30;
	}
	
	private DocumentosVO parseDocumentosVO (DocumentosVO30 documentos30) throws Exception{
		
		DocumentosVO documentos = new DocumentosVO();

		documentos.setNumDocumentosIndice(documentos30.getNumDocumentosIndice());
		documentos.setNumeroResultados(documentos30.getNumeroResultados());
		// Traduccion entre objetos de distintas versiones: Doc
		// TODO comprobar si es valido coger unicamente el elemento 0
		DocVO resultados = parseDocVO(documentos30.getResultados()[0]);
		documentos.setResultados(resultados);
		documentos.setSugerencias(documentos30.getSugerencias());
		// Traduccion entre objetos de distintas versiones: taxon
		// TODO comprobar si es valido coger unicamente el elemento 0
		TaxonVO tesauros = parseTaxon(documentos30.getTesauros()[0]);
		documentos.setTesauros(tesauros);
		documentos.setTotalResultados(documentos30.getTotalResultados());
		return documentos;
	}
	
	private DocVO parseDocVO(DocVO30 doc30) throws Exception{
		
		// Propiedades del objeto nuevo que no encajan en el viejo:
		// java.lang.String[] autor
		// es.pode.indexador.negocio.servicios.busqueda.ContribucionVO[] contribuciones,
		// java.lang.String[] palabrasClave,
        // java.lang.String[] orientacionDidactica
		
		DocVO doc = new DocVO();
		doc.setAmbito(doc30.getAmbito());
		doc.setArbolCurricularVigente(getSrvTaxonomiaService().obtenerArbolVigente().getVocabIdentifier());
		doc.setAreaCurricular(doc30.getAreaCurricular());
		doc.setConSinSecuencia(doc30.getConSinSecuencia());
		doc.setCurso(doc.getCurso());
		doc.setDescripcion(doc30.getDescripcion());
		doc.setDestinatarios(doc30.getDestinatarios());
		doc.setFechaPublicacion(doc30.getFechaPublicacion());
		doc.setFormato(doc30.getFormato());
		doc.setHoraPublicacion(doc30.getHoraPublicacion());
		doc.setIdentificadorODE(doc30.getIdentificadorODE());
		doc.setIdioma(doc30.getIdioma());
		doc.setImagen(doc30.getImagen());
		doc.setLicencias(doc30.getLicencias());
		doc.setLocalizadorODE(doc30.getLocalizadorODE());
		doc.setNivelAgregacion(doc30.getNivelAgregacion());
		doc.setNodo(doc30.getNodo());
		doc.setRanking(doc30.getRanking());
		doc.setTamanio(doc30.getTamanio());
		//Propiedad no existente en el objeto nuevo. Estudiar que valor darle por defecto
		doc.setTesauros(new String[]{""});
		doc.setTipoRecurso(doc30.getTipoRecurso());
		doc.setTitulo(doc30.getTitulo());
		doc.setValoracion(doc30.getValoracion());
		return doc;
	}
	
	private TaxonVO parseTaxon(TaxonVO30 taxon30){
		
		TaxonVO taxon = new TaxonVO();
		taxon.setIdentificador(taxon30.getIdentificador());
		taxon.setNombre(taxon30.getNombre());
		return taxon;
	}
	
	private ParametrosDocsCountVO30 parseParametrosDocsCount (ParametrosDocsCountVO parametros) throws Exception{
		
		ParametrosDocsCountVO30 parametros30 = new ParametrosDocsCountVO30();		
		parametros30.setComunidadDestino("");
		parametros30.setComunidadPeticion(parametros.getComunidadPeticion());
		parametros30.setIdiomaBusqueda(parametros.getIdiomaBusqueda());
		parametros30.setIdiomaNavegacion(parametros.getIdiomaNavegacion());
		String[] taxonomias = new String[]{};
		
		String[] tesauros = parametros.getTesauros();
		if (tesauros.length!=0) {
			String tesauroIdentifier = getSrvTesaurosServices()
					.obtenerTesauroVigente().getVocabIdentifier();
			for (int i = 0; i < tesauros.length; i++) {
				taxonomias[i] = tesauroIdentifier + '/' + tesauros[i];
			}
		}
		
		String[] areaCurricular = parametros.getAreaCurricular();
		if (areaCurricular.length!=0) {
			for (int i = 0; i < areaCurricular.length; i++) {
				taxonomias[i] = parametros.getArbolCurricularVigente() + '/' + areaCurricular[i];
			}
		}
		
		parametros30.setTaxonomia(taxonomias);
		parametros30.setTipoBusqueda(parametros.getTipoBusqueda());
		parametros30.setVersionComunidadDestino("2.0");
		
		return parametros30;
	}
}