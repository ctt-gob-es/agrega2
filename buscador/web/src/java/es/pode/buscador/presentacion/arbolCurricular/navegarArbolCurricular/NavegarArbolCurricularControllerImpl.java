/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.buscador.presentacion.arbolCurricular.navegarArbolCurricular;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.pode.buscador.presentacion.BuscarSession;
import es.pode.buscador.presentacion.avanzado.areaCurricular.AreaCurricularControllerImpl;
import es.pode.buscador.presentacion.basico.detallar.DetallarControllerImpl;
import es.pode.buscar.negocio.buscar.servicios.ParametrosDocsCountVO30;
import es.pode.buscar.negocio.buscar.servicios.ResultadosNodoCountVO;
import es.pode.buscar.negocio.buscar.servicios.ResultadosTaxonVO;
import es.pode.contenidos.negocio.descargas.servicio.DescDescargaVO;
import es.pode.contenidos.negocio.descargas.servicio.DescargaVO;
import es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO;
import es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.i18n.LocalizacionIdiomaVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.visualizador.presentacion.descargas.DescargaInfo;
import es.pode.visualizador.presentacion.noticia.NoticiaCodex;

/**
 * @see es.pode.buscador.presentacion.arbolCurricular.navegarArbolCurricular.NavegarArbolCurricularController
 */
public class NavegarArbolCurricularControllerImpl extends
		NavegarArbolCurricularController {

	public final static String APPLICATION_PROPERTIES = "application-resources";
	public final static String MENSAJE_GENERICO_BUSQUEDA = "listar.odecu.mostrar.resultados.consulta.cabecera.errorBusqueda";
	public final static String MENSAJE_ERROR_ARBOL = "listar.odecu.mostrar.resultados.consulta.cabecera.errorBusqueda";

	private static final long serialVersionUID = -8190956967834990490L;
	private static Logger logger = Logger
			.getLogger(AreaCurricularControllerImpl.class);

	/**
	 * @see es.pode.buscador.presentacion.arbolCurricular.navegarArbolCurricular.NavegarArbolCurricularController#obtenerAreasCurriculares(org.apache.struts.action.ActionMapping,
	 *      es.pode.buscador.presentacion.arbolCurricular.navegarArbolCurricular.ObtenerAreasCurricularesForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void obtenerAreasCurriculares(ActionMapping mapping,
			ObtenerAreasCurricularesForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		try {
			TaxonVO[] taxVO = null;
			Object[] taxVORutaPadre = null;
			String idiomaNavegacion = ((Locale) request.getSession()
					.getAttribute(ConstantesAgrega.DEFAULT_LOCALE))
					.getLanguage();
			form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_BUSCADOR);

			String taxonomia = form.getTipoNavegacion();

			BuscarSession os = this.getBuscarSession(request);
			String tipoBusqueda = form.getTipoBusqueda();
			form.setTipoBusqueda(tipoBusqueda);
			ResultadosNodoCountVO count = null;
			ResultadosTaxonVO[] resultadosTaxon = null;
			ResultadosTaxonVO[] resultadosTaxon_bis = null;
			

			if (form.getIdiomBusc()==null || form.getIdiomBusc().equals(""))
				form.setIdiomBusc(obtenerIdiomaBusqueda());
			
			if (form.getTipoBusqueda() == null || form.getTipoBusqueda().equals(""))
				//danielgd: Cambio tipo a 6 para que sea igual que antes
				form.setTipoBusqueda("05");
			
			
			try {
				// Detectamos si viene de la pagina nueva (no viene de
				// seleccionar tipo de busqueda:
				// si tipoBusqueda, taxonomia no existen, es nuestro caso
				if ((os.getTesauroBusqueda() == null || os.getTesauroBusqueda().equals(""))
						&& (taxonomia == null || taxonomia.equals(""))) {

					// Taxonomia
					EstructuraVdexVO estructura = this.getSrvTaxonomiaService()
							.obtenerArbolVigente();
					taxonomia = estructura.getVocabIdentifier();
					form.setTipoNavegacion(taxonomia);

					// Taxones
					taxVO = this.getSrvTaxonomiaService().obtenerTaxonomia(
							taxonomia, idiomaNavegacion);
					taxVORutaPadre = new TaxonVO[0];
					
					String[] areasCurriculares = new String[taxVO.length];
					for (int i = 0; i < taxVO.length; i++) {
						areasCurriculares[i] = taxonomia + "/"
								+ taxVO[i].getId();
					}
					try {
						ParametrosDocsCountVO30 parametrosCount = new ParametrosDocsCountVO30(
								obtenerIdiomaBusqueda(), areasCurriculares,
								idiomaNavegacion, form.getTipoBusqueda(),
								LdapUserDetailsUtils.getHost(), null, null);
						count = this.getSrvBuscarService().solicitarDocsCount(
								parametrosCount);
					} catch (Exception e) {
						if(logger.isDebugEnabled())
							logger.error(
										"NavegarArbolCurricularControllerImpl - obtenerAreasCurriculares ERROR: Error cargando solicitud de numero de documentos ["
												+ taxonomia
												+ "] en busqueda por arbol curricular cuando el tipo de busqueda era nulo y se ajusto automaticamente al tipo 05",
										e);
						saveErrorMessage(request, MENSAJE_ERROR_ARBOL);
					}
					
					if(logger.isDebugEnabled())
						logger.debug("Recuperados["
							+ taxVO.length
							+ "] taxones de vacio en busqueda por arbol curricular.");
					resultadosTaxon = new ResultadosTaxonVO[taxVO.length];
					for (int i = 0; i < resultadosTaxon.length; i++) {
						ResultadosTaxonVO resul = new ResultadosTaxonVO();
						resul.setIdentificador(new String(taxVO[i].getId()));
						resul.setNombre(new String(taxVO[i].getValorTax()));
						resul.setEsHoja(taxVO[i].getEsHoja());
						resul.setNumeroOdesAsociados(count!=null?count.getConteo()[i]:0);
						resultadosTaxon[i] = resul;
					}
					

					if (form.getAreaCurricularBotones() == null || form.getAreaCurricularBotones().equals("")) {
						/////////////CODIGO CORRESPONDIENTE A LOS SELECTS
						////////NIVEL 1 de una ruta del arbol curricular para los select
						form.setOpcionesRutaArbolNivel1AsArray(resultadosTaxon);
					}

				} else {
					// CASO DE SIEMPRE
					// Detectamos si se ha pulsado sobre un identificador de la
					// lista de taxones desplegada
					if (form.getAreaCurricularBusqueda() == null|| form.getAreaCurricularBusqueda().equals("")) {
						if(logger.isDebugEnabled())
							logger.debug("Cargando taxones vacios en busqueda por arbol curricular.");
						
						taxVO = this.getSrvTaxonomiaService().obtenerTaxonomia(taxonomia, idiomaNavegacion);
						taxVORutaPadre = new TaxonVO[0];
						String[] areasCurriculares = new String[taxVO.length];
						
						for (int i = 0; i < taxVO.length; i++) 
							areasCurriculares[i] = taxonomia + "/" + taxVO[i].getId();
						
						try {
							ParametrosDocsCountVO30 parametrosCount = new ParametrosDocsCountVO30(
									!form.getIdiomBusc().equals("")?form.getIdiomBusc():obtenerIdiomaBusqueda(), areasCurriculares,
									idiomaNavegacion, form.getTipoBusqueda(),
									LdapUserDetailsUtils.getHost(), null, null);
							count = this.getSrvBuscarService()
									.solicitarDocsCount(parametrosCount);
							// if(form.getNumeroResultados()!=null &&
							// !form.getNumeroResultados().equals(""))form.setNumeroResultados(count.getNumeroResultados());
						} catch (Exception e) {
							logger
									.error(
											"NavegarArbolCurricularControllerImpl - obtenerAreasCurriculares ERROR: Error cargando solicitud de numero de documentos ["
													+ taxonomia
													+ "] en busqueda por arbol curricular cuando el tipo de busqueda era " + form.getTipoBusqueda() + " y el area curricular de busqueda era nulo",
											e);
							saveErrorMessage(request, MENSAJE_ERROR_ARBOL);
						}
						if(logger.isDebugEnabled())
							logger.debug("Recuperados["
								+ taxVO.length
								+ "] taxones de vacio en busqueda por arbol curricular.");
						resultadosTaxon = new ResultadosTaxonVO[taxVO.length];
						for (int i = 0; i < resultadosTaxon.length; i++) {
							ResultadosTaxonVO resul = new ResultadosTaxonVO();
							resul.setIdentificador(new String(taxVO[i].getId()));
							resul.setNombre(new String(taxVO[i].getValorTax()));
							resul.setEsHoja(taxVO[i].getEsHoja());
							resul.setNumeroOdesAsociados(count!=null?count.getConteo()[i]:0);
							resultadosTaxon[i] = resul;
						}
						
					} else {

						// mjb: CREO QUE AQUI ENTRA CUANDO VIENE DE PADRE O DE HIJO O ALGO ASI, CON UNA TAXONOMIA YA CARGADA
						if(logger.isDebugEnabled())
							logger.debug("Cargando taxones del identificador["
								+ form.getAreaCurricularBusqueda()
								+ "] en busqueda por arbol curricular.");
						taxVO = this.getSrvTaxonomiaService().obtenerNodos(
								form.getAreaCurricularBusqueda(), taxonomia,
								idiomaNavegacion);
						List rutaPadrevo = Arrays.asList(this
								.getSrvTaxonomiaService().obtenerTaxonPath(
										form.getAreaCurricularBusqueda(),
										taxonomia, idiomaNavegacion));
						if(logger.isDebugEnabled())
							logger.debug("Recuperados[" + taxVO.length
								+ "] taxones de del identificador["
								+ form.getAreaCurricularBusqueda()
								+ "] en busqueda por arbol curricular.");
						Collections.reverse(rutaPadrevo);
						taxVORutaPadre = rutaPadrevo.toArray();
						String[] areasCurriculares = new String[taxVO.length + 1];
						for (int i = 0; i < taxVO.length; i++) {
							areasCurriculares[i] = taxonomia + "/"
									+ taxVO[i].getId();
						}
						areasCurriculares[taxVO.length] = taxonomia + "/"
								+ form.getAreaCurricularBusqueda();

						try {
							// java.lang.String idiomaBusqueda,
							// java.lang.String[] taxonomia,
							// java.lang.String idiomaNavegacion,
							// java.lang.String tipoBusqueda,
							// java.lang.String comunidadPeticion,
							// java.lang.String comunidadDestino,
							// java.lang.String versionComunidadDestino)
							
							ParametrosDocsCountVO30 parametrosCount = new ParametrosDocsCountVO30(
									!form.getIdiomBusc().equals("")?form.getIdiomBusc():obtenerIdiomaBusqueda(), areasCurriculares,
									idiomaNavegacion, form.getTipoBusqueda(),
									LdapUserDetailsUtils.getHost(), null, null);
							count = this.getSrvBuscarService()
									.solicitarDocsCount(parametrosCount);
							// if(form.getNumeroResultados()==null &&
							// !form.getNumeroResultados().equals(""))form.setNumeroResultados(count.getNumeroResultados());
						} catch (Exception e) {
							logger
									.error(
											"NavegarArbolCurricularControllerImpl - obtenerAreasCurriculares ERROR: Error cargando solicitud de numero de documentos ["
													+ taxonomia
													+ "] en busqueda por arbol curricular cuando el tipo de busqueda era " + form.getTipoBusqueda() +" y el area curricular de busqueda era " + form.getAreaCurricularBusqueda(),
											e);
							saveErrorMessage(request, MENSAJE_ERROR_ARBOL);
						}
						resultadosTaxon = new ResultadosTaxonVO[taxVO.length];
						for (int i = 0; i < resultadosTaxon.length; i++) {
							ResultadosTaxonVO resul = new ResultadosTaxonVO();
							resul.setIdentificador(taxVO[i].getId());
							resul.setNombre(taxVO[i].getValorTax());
							resul.setEsHoja(taxVO[i].getEsHoja());
							resul.setNumeroOdesAsociados(count!=null&&count.getConteo()!=null&&
														count.getConteo()[i]!=null?count.getConteo()[i]:0);
							resultadosTaxon[i] = resul;
						}
						form.setNumeroResultados(count!=null?count.getConteo()[count
								.getConteo().length - 1]:0);			
					}

				////////////CODIGO EXCLUSIVO PARA LOS 4 SELECTS
				/////////////////////////////////////////////////////

				}
				//OpcionesRutaArbolNivel -> donde le paso al JSP el collection con el contenido de los combos
				//areaCurricularBotones -> donde recibo del JSP la rutaArbol

				
				////////CALCULA DEL NIVEL 1 de una ruta del arbol curricular
				if (form.getOpcionesRutaArbolNivel1AsArray()==null || form.getOpcionesRutaArbolNivel1AsArray().equals("")) {					
									
					//Las opciones de cada combo se devuelven en OpcionesRutaArbolNivel
					//La ruta del arbol se recibe en AreaCurricularBotones
					
					if (taxonomia == null || taxonomia.equals("")) {
						EstructuraVdexVO estructura = this.getSrvTaxonomiaService().obtenerArbolVigente();
						taxonomia = estructura.getVocabIdentifier();
					}
					form.setTipoNavegacion(taxonomia);

					// Taxones
					taxVO = this.getSrvTaxonomiaService().obtenerTaxonomia(
							taxonomia, idiomaNavegacion);
					//taxVORutaPadre = new TaxonVO[0];
					
					String[] areasCurriculares = new String[taxVO.length];
					for (int i = 0; i < taxVO.length; i++) {
						areasCurriculares[i] = taxonomia + "/"
								+ taxVO[i].getId();
					}
					try {
						ParametrosDocsCountVO30 parametrosCount = new ParametrosDocsCountVO30(
								!form.getIdiomBusc().equals("")?form.getIdiomBusc():obtenerIdiomaBusqueda(), areasCurriculares,
								idiomaNavegacion, form.getTipoBusqueda(),
								LdapUserDetailsUtils.getHost(), null, null);
						count = this.getSrvBuscarService().solicitarDocsCount(
								parametrosCount);
					} catch (Exception e) {
						logger
								.error(
										"NavegarArbolCurricularControllerImpl - obtenerAreasCurriculares ERROR: Error cargando solicitud de numero de documentos ["
												+ taxonomia
												+ "] en busqueda por arbol curricular cuando las opciones de la ruta de nivel 1 del arbol eran nulas",
										e);
						saveErrorMessage(request, MENSAJE_ERROR_ARBOL);
					}
					
					if(logger.isDebugEnabled())
						logger.debug("Recuperados["
							+ taxVO.length
							+ "] taxones de vacio en busqueda por arbol curricular.");
					resultadosTaxon_bis = new ResultadosTaxonVO[taxVO.length];
					for (int i = 0; i < resultadosTaxon_bis.length; i++) {
						ResultadosTaxonVO resul = new ResultadosTaxonVO();
						resul.setIdentificador(new String(taxVO[i].getId()));
						resul.setNombre(new String(taxVO[i].getValorTax()));
						resul.setEsHoja(taxVO[i].getEsHoja());
						resul.setNumeroOdesAsociados(count!=null?count.getConteo()[i]:0);
						resultadosTaxon_bis[i] = resul;
					}

					form.setOpcionesRutaArbolNivel1AsArray(resultadosTaxon_bis);
				} 

				////////CALCULA DEL NIVEL 2 al N de una ruta del arbol curricular
				if ( (form.getAreaCurricularBotones() != null) && (!form.getAreaCurricularBotones().equals("")) ) {					
	
					//Las opciones de cada combo se devuelven en OpcionesRutaArbolNivel
					//La ruta del arbol se recibe en AreaCurricularBotones
					
					String[] ruta_arbol=null;
					//Revisar que combo fue cambiado
					if (form.getAreaCurricularBotones().contains(".")) {
						ruta_arbol = form.getAreaCurricularBotones().split("\\.");
					} else {
						ruta_arbol = new String[1]; 
						ruta_arbol[0] = form.getAreaCurricularBotones();
					}
					
					for (int k=0; k<ruta_arbol.length; k++) {
						String ruta;
						switch (k) {
							case 0: ruta = ruta_arbol[0];
									break;
							case 1: ruta = ruta_arbol[0] + "." + ruta_arbol[1];
									break;
							case 2: ruta = ruta_arbol[0] + "." + ruta_arbol[1] + "." + ruta_arbol[2];
									break;
							default: ruta = "";
						}
								
						if(logger.isDebugEnabled())
							logger.debug("NavegarArbolCurricularControllerImpl - obtenerAreasCurriculares: Cargando taxones del identificador["
								+ ruta
								+ "] en busqueda por arbol curricular.");
						taxVO = this.getSrvTaxonomiaService().obtenerNodos(
								ruta, taxonomia,
								idiomaNavegacion);
						List rutaPadrevo = Arrays.asList(this
								.getSrvTaxonomiaService().obtenerTaxonPath(
										ruta,
										taxonomia, idiomaNavegacion));
						if(logger.isDebugEnabled())
							logger.debug("Recuperados[" + taxVO.length
								+ "] taxones de del identificador["
								+ ruta
								+ "] en busqueda por arbol curricular.");
						Collections.reverse(rutaPadrevo);
						//taxVORutaPadre = rutaPadrevo.toArray();
						String[] areasCurriculares = new String[taxVO.length + 1];
						for (int i = 0; i < taxVO.length; i++) {
							areasCurriculares[i] = taxonomia + "/"
									+ taxVO[i].getId();
						}
						areasCurriculares[taxVO.length] = taxonomia + "/"
								+ ruta;
					
						try {
					
							ParametrosDocsCountVO30 parametrosCount = new ParametrosDocsCountVO30(
									!form.getIdiomBusc().equals("")?form.getIdiomBusc():obtenerIdiomaBusqueda(), areasCurriculares,
									idiomaNavegacion, form.getTipoBusqueda(),
									LdapUserDetailsUtils.getHost(), null, null);
							count = this.getSrvBuscarService()
									.solicitarDocsCount(parametrosCount);
							logger.debug("Recibimos <"+count.getConteo().length+"> cuentas");
							// if(form.getNumeroResultados()==null &&
							// !form.getNumeroResultados().equals(""))form.setNumeroResultados(count.getNumeroResultados());
						} catch (Exception e) {
							logger
									.error(
											"NavegarArbolCurricularControllerImpl - obtenerAreasCurriculares ERROR: Error cargando solicitud de numero de documentos ["
													+ taxonomia
													+ "] en busqueda por arbol curricular cuando las opciones de la ruta de nivel 1 del arbol eran " + form.getAreaCurricularBotones(),
											e);
							saveErrorMessage(request, MENSAJE_ERROR_ARBOL);
						}
						resultadosTaxon_bis = new ResultadosTaxonVO[taxVO.length];
						for (int i = 0; i < resultadosTaxon_bis.length; i++) {
							ResultadosTaxonVO resul = new ResultadosTaxonVO();
							resul.setIdentificador(taxVO[i].getId());
							resul.setNombre(taxVO[i].getValorTax());
							resul.setEsHoja(taxVO[i].getEsHoja());
							resul.setNumeroOdesAsociados(count!=null&&count.getConteo()[i]!=null?count.getConteo()[i]:0);
							resultadosTaxon_bis[i] = resul;
						}
						form.setNumeroResultados(count!=null?count.getConteo()[count
								.getConteo().length - 1]:0);	

						switch (k) {
							case 0:	
								form.setOpcionesRutaArbolNivel2AsArray(resultadosTaxon_bis);
								form.setRutaArbol1Niveles(ruta);
								break;
							case 1:
								form.setOpcionesRutaArbolNivel3AsArray(resultadosTaxon_bis);
								form.setRutaArbol2Niveles(ruta);
								break;
							case 2:
								form.setOpcionesRutaArbolNivel4AsArray(resultadosTaxon_bis);
								form.setRutaArbol3Niveles(ruta);
								break;
						}
					}
				}
				/////////// FIN DEL CODIGO DE LOS SELECTS
 
			} catch (Exception e) {
				logger
						.error(
								"NavegarArbolCurricularControllerImpl - obtenerAreasCurriculares ERROR: Error cargando taxonomias de "
										+ (form.getAreaCurricularBusqueda() == null ? "null"
												: form
														.getAreaCurricularBusqueda())
										+ " en busqueda por arbol curricular.",
								e);
				saveErrorMessage(request,
						DetallarControllerImpl.MENSAJE_TRADUCCION_BUSQUEDA);
			}

			
			if (logger.isDebugEnabled())
				logger.debug("NavegarArbolCurricularControllerImpl - obtenerAreasCurriculares: Cargados["
					+ (taxVO!=null?taxVO.length:0)
					+ "] taxones del identificador ["
					+ (form.getAreaCurricularBusqueda() == null ? "null" : form
							.getAreaCurricularBusqueda())
					+ "] en busqueda por arbol curricular.");
			
			// Introducimos los valores obtenidos en el formulario
			form.setNodosAsArray(resultadosTaxon);
			form.setRutaArbolAsArray(taxVORutaPadre);
			
			if (taxVO != null && taxVO.length > 0) {
				form.setTaxNombre(taxVO[0] != null ? taxVO[0].getVocabName()
						: "");
			}
		} catch (Exception e) {
			logger
					.error(
							"NavegarArbolCurricularControllerImpl - obtenerAreasCurriculares ERROR: Error cargando taxonomias de en busqueda por arbol curricular.",
							e);
			saveErrorMessage(request, MENSAJE_ERROR_ARBOL);
		}
				
		
	    //Obtenemos la lista de noticias
		try{		
			NoticiaTraducidaVO[] arrayNoticias = this.getSrvNoticiasService().listarNoticiasActivasPorIdioma(((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage());
			
			if (arrayNoticias!=null && arrayNoticias.length>0) {
				
				int numNoticiasMostradas = Integer.parseInt(this.getSrvPropiedadService().get(AgregaProperties.NUM_NOTICIAS_MOSTRADAS_EN_RESUMEN));
				ArrayList<NoticiaTraducidaVO> lista_noticias = new ArrayList<NoticiaTraducidaVO>();
//				NoticiaTraducidaVO[] array_noticias = new NoticiaTraducidaVO[numNoticiasMostradas];
				
				for(int h=0; h<numNoticiasMostradas && h<arrayNoticias.length; h++)
				{
					//sustituimos los retorno de carro por <br> en el resumen
					arrayNoticias[h].setResumen(arrayNoticias[h].getResumen().replaceAll("\n\r", "<br/>"));
					arrayNoticias[h].setResumen(arrayNoticias[h].getResumen().replaceAll("\r\n", "<br/>"));
					arrayNoticias[h].setResumen(arrayNoticias[h].getResumen().replaceAll("\n", "<br/>"));
					arrayNoticias[h].setResumen(arrayNoticias[h].getResumen().replaceAll("\r", "<br/>"));
					
					//sustituimos @,",:,%,+,-,' por su codigo correspondiente en ASCII
					arrayNoticias[h].setTitulo(arrayNoticias[h].getTitulo().replaceAll("@","&#64"));
					arrayNoticias[h].setTitulo(arrayNoticias[h].getTitulo().replaceAll("\\\"","&#34"));
					arrayNoticias[h].setTitulo(arrayNoticias[h].getTitulo().replaceAll(":","&#58"));
					arrayNoticias[h].setTitulo(arrayNoticias[h].getTitulo().replaceAll("%","&#37"));
					arrayNoticias[h].setTitulo(arrayNoticias[h].getTitulo().replaceAll("\\+","&#43"));
					arrayNoticias[h].setTitulo(arrayNoticias[h].getTitulo().replaceAll("-","&#45"));
					arrayNoticias[h].setTitulo(arrayNoticias[h].getTitulo().replaceAll("'","&#39"));
					
					lista_noticias.add(arrayNoticias[h]);
				}
				//Cojo solo las que debo
				//int iTotal = Integer.parseInt(this.getPropertyValue("portada.noticias.total"));
				//form.setNumNoticias(iTotal);
				
				NoticiaCodex[] noticiaCodex=NoticiaCodex.mapToCodexArray(lista_noticias.toArray(new NoticiaTraducidaVO[lista_noticias.size()]));
				form.setNoticiasAsArray(noticiaCodex);
				
				//logger.debug("Noticias de portada obtenidas correctamente("+iTotal+").");
				logger.debug("Noticias de portada obtenidas correctamente. Se mostraran <"+numNoticiasMostradas+"> noticias.");
			} else {	
				NoticiaCodex[] noticiaCodex=NoticiaCodex.mapToCodexArray(arrayNoticias);
				form.setNoticiasAsArray(noticiaCodex);
				logger.debug("Noticias de portada obtenidas correctamente. Se mostraran 0 noticias.");
			}
			// Ficheros OPML
//	        String idioma = ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
//			form.setIdiomaNavegacion(idioma);
			
		} catch (Exception e) {
			logger.error("Excepcion inesperada obteniendo noticias - "+ e);
			throw e;
		}
		
		
		
		//Obtenemos la lista de descargas
		try {
    		Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
			String idioma = locale.getLanguage();
			logger.debug("Recuperado idioma <"+idioma+">");
			DescargaVO descargas[] = getSrvDescargas().obtenerDescargasActivas();
			logger.debug("Recuperadas <"+descargas.length!=null?descargas.length:0+"> descargas.");
			ArrayList<DescargaInfo> listaDescargas = new ArrayList<DescargaInfo>();
			
			if (descargas!=null && descargas.length>0) {	
				DescDescargaVO[] descs = getSrvDescargas().obtenerDescDescargasIdioma(descargas, idioma);
				logger.debug("Recuperadas " + descs.length + " descripciones de descargas");
				int numDescargasMostradas = Integer.parseInt(this.getSrvPropiedadService().get(AgregaProperties.NUM_DESCARGAS_MOSTRADAS_EN_RESUMEN));
				
				for (int i = 0; i<numDescargasMostradas && i<descargas.length; i++) {
					DescargaInfo info = new DescargaInfo();
					info.setTitulo(descs[i]!=null&&descs[i].getTitulo()!=null?descs[i].getTitulo():"");
					info.setDescripcion(descs[i]!=null&&descs[i].getDescripcion()!=null?descs[i].getDescripcion():"");
					info.setIdentificador(descargas[i]!=null&&descargas[i].getIdentificador()!=null?descargas[i].getIdentificador().toString():"");
					info.setPeso(descargas[i]!=null&&descargas[i].getPeso()!=null?descargas[i].getPeso():0L,locale);
					info.setRuta(descargas[i]!=null&&descargas[i].getPath()!=null?request.getServerName()+"/"+descargas[i].getPath():"");
					listaDescargas.add(info);
				}
				form.setDescargas(listaDescargas);
				logger.debug("Lista de descargas de portada obtenidas correctamente. Se mostraran "+numDescargasMostradas+" descargas.");
			}
		} catch (Exception e) {
			logger.debug("Error al recuperar descargas.",e);
			throw new ValidatorException("{listaDescargas.error}");
		}
		
    	I18n i18n = I18n.getInstance();
    	//Recogemos el idioma por defecto para mostrar en ese idioma la lista desplegable de idiomas
    	Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
    	//recogemos un array de objetos con la lista de idiomas
    	LocalizacionIdiomaVO[] localizacionArray = i18n.obtenerIdiomasBuscablesLocalizados(locale.getLanguage());
    	//sacamos los idiomas del array de objetos y lo asignamos al combo
    	form.setIdiomBuscBackingList(Arrays.asList(localizacionArray), "idLocalizacion", "nombre");
    	
		// Ambito de la busqueda en la parte de los combos
		// 0 = Todo Agrega; 1 = Local
		String ambitoBusquedaCombos = form.getAmbitoBusquedaCombos();
		if (ambitoBusquedaCombos == null || ambitoBusquedaCombos.equals(""))
			ambitoBusquedaCombos = "0";
		form.setAmbitoBusquedaCombos(ambitoBusquedaCombos); 	

		// Idioma de la busqueda en la parte de los combos
		if (form.getLenguajeBusquedaCombos() == null || form.getLenguajeBusquedaCombos().equals(""))
			form.setLenguajeBusquedaCombos(obtenerIdiomaBusqueda());
		
		if (form.getCajaArribaAbierta()==null) form.setCajaArribaAbierta(false);
		if (form.getCajaAbajoAbierta()==null) form.setCajaAbajoAbierta(false);
		if (form.getMostrarNoResultados()!=null && form.getMostrarNoResultados()==true) {
			form.setCajaArribaAbierta(false);
			form.setCajaAbajoAbierta(false);
			form.setRutaArbol1Niveles(null);
		}
	}


	public void buscarIdiomasBuscador(ActionMapping mapping,
			BuscarIdiomasBuscadorForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setBuscarSession(request, new BuscarSession());
		//form.setTipoBusqueda("06");
		form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_BUSCADOR);
	}

	private String obtenerIdiomaBusqueda() throws Exception {
		String idiomaNavegacion = "";
		if (LdapUserDetailsUtils.estaAutenticado()) {
			try {
				idiomaNavegacion = LdapUserDetailsUtils.getIdiomaBusqueda();
				if (idiomaNavegacion == null)
					idiomaNavegacion = I18n.getInstance()
							.obtenerIdiomaDefectoPlataforma();
			} catch (Exception e) {
				logger
						.error(
								"BuscarAvanzadoControllerImpl - cargaFormularioBusquedaAvanzada: Error en al obtener idioma búsqueda usuario LDAP, se recoge idioma de fichero propiedades.",
								e);
				idiomaNavegacion = I18n.getInstance()
						.obtenerIdiomaDefectoPlataforma();
			}
		} else
			idiomaNavegacion = I18n.getInstance()
					.obtenerIdiomaDefectoPlataforma();
		return idiomaNavegacion;
	}

}