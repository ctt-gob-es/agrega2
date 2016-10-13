/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.buscador.presentacion.arbolTesauros.navegarTesauro;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.buscador.presentacion.BuscarSession;
import es.pode.buscador.presentacion.avanzado.areaCurricular.AreaCurricularControllerImpl;
import es.pode.buscador.presentacion.basico.detallar.DetallarControllerImpl;
import es.pode.buscador.presentacion.seleccionTipoBusqueda.seleccionTipoBusquedaControllerImpl;
import es.pode.buscar.negocio.buscar.servicios.ParametrosDocsCountVO30;
import es.pode.buscar.negocio.buscar.servicios.ResultadosTaxonVO;
import es.pode.fuentestaxonomicas.negocio.servicio.JerarquiaVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.buscador.presentacion.arbolTesauros.navegarTesauro.NavegarTesauroController
 */
public class NavegarTesauroControllerImpl extends NavegarTesauroController
{
	private java.util.Properties pSpringProperties = null;
	private static Logger logger = Logger.getLogger(AreaCurricularControllerImpl.class);
	private static String VARIAS_RUTAS_DISPONIBLES = "0.0";
	public final static String MENSAJE_ERROR_TESAURO = "listar.odecu.mostrar.resultados.consulta.cabecera.errorBusqueda";

	private static final String FICHERO = "application-resources";
	private static final String EUSKERA = "eu";
	private static final String INGLES = "en";
	private static final String SPACE = " ";
	
	
    /**
     * @see es.pode.buscador.presentacion.arbolTesauros.navegarTesauro.NavegarTesauroController#buscarIdiomasBuscador(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.arbolTesauros.navegarTesauro.BuscarIdiomasBuscadorForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void buscarIdiomasBuscador(ActionMapping mapping, es.pode.buscador.presentacion.arbolTesauros.navegarTesauro.BuscarIdiomasBuscadorForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	this.setBuscarSession(request, new BuscarSession());
    	form.setTipoBusqueda("08");
    	form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_BUSCADOR);
     }

    /**
     * @see es.pode.buscador.presentacion.arbolTesauros.navegarTesauro.NavegarTesauroController#cargarTesauros(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.arbolTesauros.navegarTesauro.CargarTesaurosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cargarTesauros(ActionMapping mapping, es.pode.buscador.presentacion.arbolTesauros.navegarTesauro.CargarTesaurosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try {
    		if(logger.isDebugEnabled())logger.debug("NavegarTesauroControllerImpl - cargarTesauros");
    		form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_BUSCADOR);
    		String idiomaNavegacion = ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
    		BuscarSession os = this.getBuscarSession(request);
			os.setJerarquias(null);
			form.setTipoBusqueda(os.getTipoBusqueda());
    		
			String vocabIdentifier = form.getTipoNavegacion();
			
			TaxonVO[] taxVO =this.getSrvTesaurosServices().obtenerPrimerNivelTesauro(vocabIdentifier, idiomaNavegacion);//Se carga el primer nivel del tesauro
			String[] tesauros=null;
			if (taxVO !=null && taxVO.length>0){
				tesauros=new String[taxVO.length];
				for (int i=0;i<taxVO.length;i++){
					tesauros[i]=vocabIdentifier + "/" + taxVO[i].getId();
				}
				
				//Se obtiene el nombre del tesauro en el idioma de navegacion
				Locale locale = null;
				try {
					locale = devuelveLocale(request);
				} catch (Exception e) {
					logger.error("Error recuperando el locale",e);
				}
				
				
				String tituloTesauro = "";
				String literalTituloTesauro = seleccionTipoBusquedaControllerImpl.getResource(seleccionTipoBusquedaControllerImpl.TITULOTESAURO, FICHERO, locale);	
				if (EUSKERA.equals(idiomaNavegacion) || INGLES.equals(idiomaNavegacion))tituloTesauro= taxVO[0].getVocabName() + SPACE + literalTituloTesauro;
     			else tituloTesauro= literalTituloTesauro + SPACE + taxVO[0].getVocabName();
				form.setTituloTesauro(tituloTesauro);
			}
			
			
			
			
			es.pode.buscar.negocio.buscar.servicios.ResultadosNodoCountVO count=null;
			if(logger.isDebugEnabled())logger.debug("NavegarTesauroControllerImpl - cargarTesauros: Obtenidos tesauros, se van a obtener odes por item de tesauro");
			try{
				ParametrosDocsCountVO30 parametrosCount = new ParametrosDocsCountVO30(obtenerIdiomaBusqueda(),tesauros,idiomaNavegacion,form.getTipoBusqueda(),LdapUserDetailsUtils.getHost(),null,null);
				count = this.getSrvBuscarService().solicitarDocsCount(parametrosCount);
		    } catch (Exception e) {
				logger.error("NavegarTesauroControllerImpl - cargarTesauros ERROR: Error cargando solicitud de numero de documentos en busqueda por tesauro.",e);
				saveErrorMessage(request, MENSAJE_ERROR_TESAURO);
			}
		    form.setNumeroResultados(count.getNumeroResultados());
		    ResultadosTaxonVO[] resultadosTaxon=new ResultadosTaxonVO[taxVO.length];
		    for (int i=0;i<count.getConteo().length;i++){
				ResultadosTaxonVO resul=new ResultadosTaxonVO();
				resul.setIdentificador(taxVO[i].getId());
				resul.setNombre(taxVO[i].getValorTax());
				resul.setEsHoja(taxVO[i].getEsHoja());
				resul.setNumeroOdesAsociados(count.getConteo()[i]);
				resultadosTaxon[i]=resul;
			}
		    form.setIdioma(idiomaNavegacion);
//			form.setNomTesauros("");
			form.setTaxonesVOAsArray(resultadosTaxon);
			TaxonVO[] taxVORutaPadre
			= new TaxonVO[0];//En la cabecera sólo pondrá Inicio
			form.setRutaPadreVOAsArray(taxVORutaPadre);
			form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_BUSCADOR);
			
		} catch (Exception e) {
			logger.error("NavegarTesauroControllerImpl - cargarTesauros: Error en CargarTesauro", e);
		}
     }

    /**
     * @see es.pode.buscador.presentacion.arbolTesauros.navegarTesauro.NavegarTesauroController#consultarId(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.arbolTesauros.navegarTesauro.ConsultarIdForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void consultarId(ActionMapping mapping, es.pode.buscador.presentacion.arbolTesauros.navegarTesauro.ConsultarIdForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	if(logger.isDebugEnabled())logger.debug("NavegarTesauroControllerImpl - consultarId");
    	form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_BUSCADOR);
    	String idiomaNavegacion = ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
		form.setIdioma(idiomaNavegacion);
		
		String vocabIdentifierTes = form.getTipoNavegacion();
		
		try{
			BuscarSession os = this.getBuscarSession(request);
			es.pode.buscar.negocio.buscar.servicios.ResultadosNodoCountVO count=null;
			if(os.getJerarquias()!=null && form.isEsRuta()){//Si en el objeto de sesion las rutas jerarquicas son nulas
				if(logger.isDebugEnabled())logger.debug("NavegarTesauroControllerImpl - consultarId:Si en el objeto de sesion las rutas jerarquicas son nulas");
				TaxonVO[] taxones = ((JerarquiaVO)os.getJerarquias().toArray()[Integer.parseInt(form.getIdTesauro())]).getJerarquia();
				
				JerarquiaVO jerar=new JerarquiaVO();
				jerar.setJerarquia(taxones);
				//Los terminos relacionados del ultimo taxon del array
				TaxonVO ultimoTaxon=taxones[taxones.length-1];
				TaxonVO[] terminosRelacionados=this.getSrvTesaurosServices().obtenerTerminosRelacionadosPorId(ultimoTaxon.getId(), vocabIdentifierTes, idiomaNavegacion);
				String[] tesauros=null;
				if (terminosRelacionados !=null && terminosRelacionados.length>0){
					tesauros=new String[terminosRelacionados.length+1];
					for (int i=0;i<terminosRelacionados.length;i++){
						tesauros[i]=vocabIdentifierTes + "/" + terminosRelacionados[i].getId(); 
					}
					tesauros[tesauros.length-1]=vocabIdentifierTes + "/" + ultimoTaxon.getId();
				}
			    TaxonVO[] taxVORutaPadre = new TaxonVO[0];//En la cabecera sólo pondrá Inicio
				form.setRutaPadreVOAsArray(taxVORutaPadre);
				form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_BUSCADOR);
				//Insertamos en el formulario los taxones relacionados y la rutaPadre
				
				form.setRutaPadreVO(Arrays.asList(((JerarquiaVO)os.getJerarquias().toArray()[Integer.parseInt(form.getIdTesauro())]).getJerarquia()));
//				form.setNomTesauros(ultimoTaxon.getValorTax());
				form.setIdTesauro(ultimoTaxon.getId());//Cambiamos el identificador de la posicion por la del ultimo taxon
				//os.setJerarquias(null);
				form.setEsRuta(false);
				try{
					ParametrosDocsCountVO30 parametrosCount = new ParametrosDocsCountVO30(obtenerIdiomaBusqueda(),tesauros,idiomaNavegacion,form.getTipoBusqueda(),LdapUserDetailsUtils.getHost(),null,null);
					count = this.getSrvBuscarService().solicitarDocsCount(parametrosCount);
			    } catch (Exception e) {
					logger.error("NavegarTesauroControllerImpl - consultarId ERROR: Error cargando solicitud de numero de documentos en busqueda por tesauro.",e);
					saveErrorMessage(request, MENSAJE_ERROR_TESAURO);
				}
			    
			    ResultadosTaxonVO[] resultadosTaxon=new ResultadosTaxonVO[terminosRelacionados.length];
			    taxonVOToResultadosTaxonVO(count, terminosRelacionados,
						resultadosTaxon);
			    form.setNumeroResultados(count.getConteo()[count.getConteo().length-1]);
			    form.setTaxonesVOAsArray(resultadosTaxon);
			    form.setTaxonesVO(Arrays.asList(resultadosTaxon));
			}else{//Si en el objeto de sesion las rutas jerarquicas no son nulas
				//Accedemos y recogemos el array de taxones de la posicion id; que sera la rutaPadre
				if(logger.isDebugEnabled())logger.debug("NavegarTesauroControllerImpl - consultarId:Si en el objeto de sesion las rutas jerarquicas no son nulas, accedemos y recogemos el array de taxones de la posicion id; que sera la rutaPadre");
				TaxonVO[] taxonesRelacionados=this.getSrvTesaurosServices().obtenerTerminosRelacionadosPorId(form.getIdTesauro(), vocabIdentifierTes, idiomaNavegacion);
				String[] tesauros=null;
				if (taxonesRelacionados !=null && taxonesRelacionados.length>0){
					tesauros=new String[taxonesRelacionados.length+1];
					for (int i=0;i<taxonesRelacionados.length;i++){
						tesauros[i]=vocabIdentifierTes + "/" + taxonesRelacionados[i].getId();
					}
					tesauros[tesauros.length-1]=vocabIdentifierTes + "/" + form.getIdTesauro();
				}
				JerarquiaVO[] jerarquia=this.getSrvTesaurosServices().obtenerJerarquia(form.getIdTesauro(), vocabIdentifierTes, idiomaNavegacion);//Obtenemos las jerarquias
				form.setRutaPadreVO(obtenerJerarquias(jerarquia, os , idiomaNavegacion));
				try{
					ParametrosDocsCountVO30 parametrosCount = new ParametrosDocsCountVO30(obtenerIdiomaBusqueda(),tesauros,idiomaNavegacion,form.getTipoBusqueda(),LdapUserDetailsUtils.getHost(),null,null);
					count = this.getSrvBuscarService().solicitarDocsCount(parametrosCount);
					
			    } catch (Exception e) {
					logger.error("NavegarTesauroControllerImpl - consultarId ERROR: Error cargando solicitud de numero de documentos en busqueda por tesauro.",e);
					saveErrorMessage(request, MENSAJE_ERROR_TESAURO);
				}
			    ResultadosTaxonVO[] resultadosTaxon=new ResultadosTaxonVO[taxonesRelacionados.length];
			    taxonVOToResultadosTaxonVO(count, taxonesRelacionados,
						resultadosTaxon);
			    //form.setNumeroResultados(count.getConteo()[count.getConteo().length-1]);
			    form.setTaxonesVOAsArray(resultadosTaxon);
			    form.setTaxonesVO(Arrays.asList(resultadosTaxon));
			}
		}catch (Exception e) {
			logger.error("NavegarTesauroControllerImpl - consultarId: Error en consultaTesId", e);
		}
     }

	private void taxonVOToResultadosTaxonVO(
			es.pode.buscar.negocio.buscar.servicios.ResultadosNodoCountVO count,
			TaxonVO[] taxonesRelacionados, ResultadosTaxonVO[] resultadosTaxon) {
		for (int i=0;i<taxonesRelacionados.length;i++){
			ResultadosTaxonVO resul=new ResultadosTaxonVO();
			resul.setIdentificador(taxonesRelacionados[i].getId());
			resul.setNombre("("+taxonesRelacionados[i].getTipoRelacion()+") "+taxonesRelacionados[i].getValorTax());
			resul.setEsHoja(taxonesRelacionados[i].getEsHoja());
			resul.setNumeroOdesAsociados(count.getConteo()[i]);
			resultadosTaxon[i]=resul;
		}
	}

    /**
     * @see es.pode.buscador.presentacion.arbolTesauros.navegarTesauro.NavegarTesauroController#consultarPadreTaxon(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.arbolTesauros.navegarTesauro.ConsultarPadreTaxonForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void consultarPadreTaxon(ActionMapping mapping, es.pode.buscador.presentacion.arbolTesauros.navegarTesauro.ConsultarPadreTaxonForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	if(logger.isDebugEnabled())logger.debug("NavegarTesauroControllerImpl - consultarPadreTaxon.");
    	String idiomaNavegacion = ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
		form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_BUSCADOR);
//		String nomTesauro = this.getPropertyValue("nombreFichTesauros");
		BuscarSession os = this.getBuscarSession(request);
		
		String vocabIdentifierTes = form.getTipoNavegacion();
		
		try{
			es.pode.buscar.negocio.buscar.servicios.ResultadosNodoCountVO count=null;
			if(form.getIdTesauro()!=null && !form.getIdTesauro().equals(VARIAS_RUTAS_DISPONIBLES)){//Cuando viene con un identificador que no le hemos insertado nosotros
				if(logger.isDebugEnabled())logger.debug("NavegarTesauroControllerImpl - consultarPadreTaxon:Cuando viene con un identificador que no le hemos insertado nosotros");
				TaxonVO[] taxonesRelacionados=this.getSrvTesaurosServices().obtenerTerminosRelacionadosPorId(form.getIdTesauro(), vocabIdentifierTes, idiomaNavegacion);
				String[] tesauros=null;
				if (taxonesRelacionados !=null && taxonesRelacionados.length>0){
					tesauros=new String[taxonesRelacionados.length+1];
					for (int i=0;i<taxonesRelacionados.length;i++){
						tesauros[i]=vocabIdentifierTes + "/" + taxonesRelacionados[i].getId();
					}
					tesauros[tesauros.length-1]=vocabIdentifierTes + "/" + form.getIdTesauro();
				}
				
				JerarquiaVO[] jerarquia=this.getSrvTesaurosServices().obtenerJerarquia(form.getIdTesauro(), vocabIdentifierTes, idiomaNavegacion);//Obtenemos las jerarquias
				form.setRutaPadreVO(obtenerJerarquias(jerarquia, os , idiomaNavegacion));
				try{
					ParametrosDocsCountVO30 parametrosCount = new ParametrosDocsCountVO30(obtenerIdiomaBusqueda(),tesauros,idiomaNavegacion,form.getTipoBusqueda(),LdapUserDetailsUtils.getHost(),null,null);
					count = this.getSrvBuscarService().solicitarDocsCount(parametrosCount);
					
			    } catch (Exception e) {
					logger.error("NavegarTesauroControllerImpl - consultarPadreTaxon ERROR: Error cargando solicitud de numero de documentos en busqueda por tesauro.",e);
					saveErrorMessage(request, MENSAJE_ERROR_TESAURO);
				}
			    if (taxonesRelacionados !=null && taxonesRelacionados.length>0) {
					ResultadosTaxonVO[] resultadosTaxon = new ResultadosTaxonVO[taxonesRelacionados.length];
					taxonVOToResultadosTaxonVO(count, taxonesRelacionados,
							resultadosTaxon);
					form.setNumeroResultados(count.getConteo()[count
							.getConteo().length - 1]);
					form.setTaxonesVO(Arrays.asList(resultadosTaxon));//insertamos los taxones en el formulario
				}
			}else{//Si viene con varias rutas disponibles, por eso lleva el identificador 0.0
				if(logger.isDebugEnabled())logger.debug("NavegarTesauroControllerImpl - consultarPadreTaxon:Si viene con varias rutas disponibles, por eso lleva el identificador 0.0");
				Collection coleccionRutas=os.getJerarquias();
			    String[] tesauros= new String[coleccionRutas.size()];
			    try{
					ParametrosDocsCountVO30 parametrosCount = new ParametrosDocsCountVO30(obtenerIdiomaBusqueda(),tesauros,idiomaNavegacion,form.getTipoBusqueda(),LdapUserDetailsUtils.getHost(),null,null);
					count = this.getSrvBuscarService().solicitarDocsCount(parametrosCount);
					form.setNumeroResultados(count.getNumeroResultados());
			    } catch (Exception e) {
					logger.error("NavegarTesauroControllerImpl - consultarPadreTaxon ERROR: Error cargando solicitud de numero de documentos en busqueda por tesauro.",e);
					saveErrorMessage(request, MENSAJE_ERROR_TESAURO);
				}
			    ResultadosTaxonVO[] resultadosTaxon=new ResultadosTaxonVO[coleccionRutas.size()];
				for(int i=0;i<coleccionRutas.size();i++){//Recorremos la coleccion de jerarquias en sesion
					ResultadosTaxonVO nuevoTaxon=new ResultadosTaxonVO();//Generamos un nuevo taxon
					nuevoTaxon.setEsHoja(Boolean.FALSE);	
					form.setEsRuta(true);
					nuevoTaxon.setIdentificador(new Integer(i).toString());//Llevara por identificador la i(de la posicion en las rutas)					
					JerarquiaVO jerarquias=(JerarquiaVO)coleccionRutas.toArray()[i];
					TaxonVO[] jerarTax=jerarquias.getJerarquia();			
					StringBuilder texto = taxonVOToString(jerarTax);
					nuevoTaxon.setNombre(texto.toString());
					nuevoTaxon.setNumeroOdesAsociados(count.getConteo()[i]);
					resultadosTaxon[i]=nuevoTaxon;//Array de taxones			
					tesauros[i]=nuevoTaxon.getIdentificador();
				}
				TaxonVO[] taxVORutaPadre = new TaxonVO[0];
				JerarquiaVO jerarquia=new JerarquiaVO();
				jerarquia.setJerarquia(taxVORutaPadre);//En la cabecera sólo aparecera Inicio
				form.setRutaPadreVOAsArray(taxVORutaPadre);
				form.setTaxonesVO(Arrays.asList(resultadosTaxon));//insertamos los taxones en el formulario
			}	
		}catch (Exception e) {
			logger.error("NavegarTesauroControllerImpl - consultarPadreTaxon: Error en consultaPadreTaxon", e);
		}
     }
    
    
    private List obtenerJerarquias(JerarquiaVO[] jerarquia, BuscarSession os, String idioma){
    	TaxonVO[] lTaxones = null;
	    if(jerarquia.length>1){//Si hay más de una jerarquia
			
			os.setJerarquias(Arrays.asList(jerarquia));
			TaxonVO taxonVariosDisponibles=new TaxonVO();				
			taxonVariosDisponibles.setEsHoja(Boolean.FALSE);
			taxonVariosDisponibles.setId(VARIAS_RUTAS_DISPONIBLES);
			ResourceBundle datosResources = ResourceBundle.getBundle("application-resources", new Locale(idioma));
			taxonVariosDisponibles.setValorTax(datosResources.getString("tesauro.variasRutasDisponibles"));
			lTaxones = new TaxonVO[2];
			lTaxones[0]=taxonVariosDisponibles;
			lTaxones[1]=jerarquia[0].getJerarquia()[jerarquia[0].getJerarquia().length-1];	
		}else{
			lTaxones = jerarquia[0].getJerarquia();//a la colecion rutaPadre le insertamos la única jerarquia que tenemos y lo metemos en el formulario
		}
	    return Arrays.asList(lTaxones);
	}
    
    
    public void buscarTerminos(ActionMapping mapping, BuscarTerminosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		try{
			if(logger.isDebugEnabled())logger.debug("NavegarTesauroControllerImpl - buscarTerminos.");
	    	String idiomaNavegacion = ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
	    	String nomTesauro = this.getPropertyValue("nombreFichTesauros");
			
	    	String vocabIdentifierTes = form.getTipoNavegacion();
	    	
			form.setIdioma(idiomaNavegacion);
			form.setNomTesauro(vocabIdentifierTes);//form.setNomTesauro(nomTesauro);
			form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_BUSCADOR);
			String textoBusqueda = form.getTextoBusqueda();
			if(textoBusqueda==null)
				textoBusqueda="";
			
			JerarquiaVO[] jerarquias=this.getSrvTesaurosServices().obtenerTerminos(textoBusqueda,vocabIdentifierTes, idiomaNavegacion);
			
			BuscarSession os = this.getBuscarSession(request);
	    	Collection rutas=new ArrayList();
	    	es.pode.buscar.negocio.buscar.servicios.ResultadosNodoCountVO count=null;
	    	if (jerarquias.length==1){
	
	    		String id = jerarquias[0].getJerarquia()[jerarquias[0].getJerarquia().length-1].getId();
				TaxonVO[] taxonesRelacionados=this.getSrvTesaurosServices().obtenerTerminosRelacionadosPorId(id, vocabIdentifierTes, idiomaNavegacion);
				String[] tesauros=null;
				if (taxonesRelacionados !=null && taxonesRelacionados.length>0){
					tesauros=new String[taxonesRelacionados.length+1];
					for (int i=0;i<taxonesRelacionados.length;i++){
						tesauros[i]=vocabIdentifierTes + "/" + taxonesRelacionados[i].getId();
					}
					tesauros[tesauros.length-1]=vocabIdentifierTes + "/" + id;
				}
				try{
					ParametrosDocsCountVO30 parametrosCount = new ParametrosDocsCountVO30(obtenerIdiomaBusqueda(),tesauros,idiomaNavegacion,form.getTipoBusqueda(),LdapUserDetailsUtils.getHost(),null,null);
					count = this.getSrvBuscarService().solicitarDocsCount(parametrosCount);
					form.setNumeroResultados(count.getNumeroResultados());
			    } catch (Exception e) {
					logger.error("NavegarTesauroControllerImpl - buscarTerminos ERROR: Error cargando solicitud de numero de documentos en busqueda por tesauro.",e);
					saveErrorMessage(request, MENSAJE_ERROR_TESAURO);
				}
			    ResultadosTaxonVO[] resultadosTaxon=new ResultadosTaxonVO[taxonesRelacionados.length];
			    for (int i=0;i<taxonesRelacionados.length;i++){
					ResultadosTaxonVO resul=new ResultadosTaxonVO();
					resul.setIdentificador(new String (taxonesRelacionados[i].getId()));
					resul.setNombre("("+taxonesRelacionados[i].getTipoRelacion()+") "+taxonesRelacionados[i].getValorTax());
					resul.setEsHoja(taxonesRelacionados[i].getEsHoja());
					resul.setNumeroOdesAsociados(count.getConteo()[i]);
					resultadosTaxon[i]=resul;
				}
			    form.setNumeroResultados(count.getConteo()[count.getConteo().length-1]);
				form.setTaxonesVOAsArray(resultadosTaxon);
				form.setRutaPadreVOAsArray(jerarquias[0].getJerarquia());
				form.setIdTesauro(id);
	    	}
	    	else{
	    		rutas=rutasBusqueda(jerarquias);
	    		TaxonVO[] rutasTaxon=new TaxonVO[rutas.size()];
	    		String[] tesauros=new String[rutas.size()];
	    		for (int i=0;i<rutas.size();i++){
	    			rutasTaxon[i]=(TaxonVO)rutas.toArray()[i];
	    			tesauros[i]=vocabIdentifierTes + "/" + rutasTaxon[i].getId();
	    		}
	    		
	    		try{
					ParametrosDocsCountVO30 parametrosCount = new ParametrosDocsCountVO30(obtenerIdiomaBusqueda(),tesauros,idiomaNavegacion,form.getTipoBusqueda(),LdapUserDetailsUtils.getHost(),null,null);
					count = this.getSrvBuscarService().solicitarDocsCount(parametrosCount);
					form.setNumeroResultados(count.getNumeroResultados());
			    } catch (Exception e) {
					logger.error("NavegarTesauroControllerImpl - buscarTerminos ERROR: Error cargando solicitud de numero de documentos en busqueda por tesauro.",e);
					saveErrorMessage(request, MENSAJE_ERROR_TESAURO);
				}
			    
			    
	    		ResultadosTaxonVO[] resultadosTaxon=new ResultadosTaxonVO[rutasTaxon.length];
			    for (int i=0;i<rutasTaxon.length;i++){
					ResultadosTaxonVO resul=new ResultadosTaxonVO();
					resul.setIdentificador(rutasTaxon[i].getId());
					resul.setNombre(rutasTaxon[i].getValorTax());
					resul.setEsHoja(rutasTaxon[i].getEsHoja());
					resul.setNumeroOdesAsociados(count.getConteo()[i]);
					resultadosTaxon[i]=resul;
				}
				form.setTaxonesVOAsArray(resultadosTaxon);
		    	form.setRutaPadreVO(null);
		    	form.setEsRuta(true);
		    	Collection cJerarquias = new ArrayList();
		    	for(int i=0;i<jerarquias.length;i++){
		    		cJerarquias.add(jerarquias[i]);
		    	}
		    	os.setJerarquias(cJerarquias);
	    	}
		}catch (Exception e) {
			logger.error("NavegarTesauroControllerImpl - buscarTerminos: Error en buscarTerminos", e);
		}
	}
    
	public Collection rutasBusqueda(JerarquiaVO[] jerarquias){
    	Collection aux=new ArrayList();
    	for(int i=0;i<jerarquias.length;i++){
    		
			TaxonVO nuevoTaxon=new TaxonVO();//Generamos un nuevo taxon que tenga como identificador la i y como texto la concatenacion de los textos del array de taxones
			nuevoTaxon.setEsHoja(Boolean.FALSE);
			Integer entero=new Integer(i);
			
			nuevoTaxon.setId(entero.toString());
			JerarquiaVO jerarquia=jerarquias[i];
			TaxonVO[] jerarTax=jerarquia.getJerarquia();
			StringBuilder valorTax = taxonVOToString(jerarTax);
			nuevoTaxon.setValorTax(valorTax.toString());
			aux.add(nuevoTaxon);
		}
    	return aux;
	}

	private StringBuilder taxonVOToString(TaxonVO[] jerarTax) {
		StringBuilder valorTax=new StringBuilder("");
		for(int j=0;j<jerarTax.length;j++){	
			valorTax.append(jerarTax[j].getValorTax());
			if(j<jerarTax.length-1)
				valorTax.append(" -> ");
		}
		return valorTax;
	}

    private String getPropertyValue(String sKey) throws IOException {
		InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/spring_buscador.properties");
		if (this.pSpringProperties == null) {
			pSpringProperties = new java.util.Properties();
			pSpringProperties.load(fIsSpringProperties);
		}
		fIsSpringProperties.close();
		if(logger.isDebugEnabled())logger.debug("NavegarTesauroControllerImpl - getPropertyValue:Propiedad recuperada: " + sKey + " : "+ pSpringProperties.getProperty(sKey));
		// devolvemos la propiedad
		return pSpringProperties.getProperty(sKey);
	}
    
    private String obtenerIdiomaBusqueda() throws Exception{
	   	String idiomaNavegacion = "";
	   	if(LdapUserDetailsUtils.estaAutenticado()){
				try{
					idiomaNavegacion=LdapUserDetailsUtils.getIdiomaBusqueda();
					if (idiomaNavegacion == null) idiomaNavegacion = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
				} catch (Exception e) {
					logger.error("NavegarTesauroControllerImpl - obtenerIdiomaBusqueda: Error en al obtener idioma búsqueda usuario LDAP, se recoge idioma de fichero propiedades.",e);
					idiomaNavegacion = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
				}
	   	}else idiomaNavegacion = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
			return idiomaNavegacion;
	}

    
	private Locale devuelveLocale(HttpServletRequest request) throws Exception
	{	
		
		Locale locale=null;
//		if (request instanceof HttpServletRequest) {
			locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
			
//		}
//		else{ 	    					
//			try {
//				locale=new Locale(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
//			} catch (Exception e) {								
//				logger.error("DevuelveLocale-- Error recuperando el locale del request", e);
//			}
//		}
		return locale;
	}
}