/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.buscador.presentacion.avanzado.buscarAvanzado;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.agregador.negocio.agregador.servicio.ParametrosBusquedaAgregadorVO;
import es.pode.buscador.presentacion.BuscarSession;
import es.pode.buscador.presentacion.basico.detallar.DetallarControllerImpl;
import es.pode.buscar.negocio.administrar.servicio.NodoVO;
import es.pode.buscar.negocio.administrar.servicio.SrvNodoService;
import es.pode.buscar.negocio.buscar.servicios.ParametrosBusquedaAvanzadaVO30;
import es.pode.buscar.negocio.buscar.servicios.ParametrosDocsCountVO30;
import es.pode.buscar.negocio.buscar.servicios.ResultadoBusquedaVO;
import es.pode.buscar.negocio.buscar.servicios.ResultadosNodoCountVO;
import es.pode.buscar.negocio.buscar.servicios.SrvBuscarService;
import es.pode.buscar.negocio.buscar.servicios.ValoresBusquedaVO;
import es.pode.buscar.negocio.nodosSQI.servicio.NodoSQIVO;
import es.pode.configuracionPlataforma.servicios.SrvPropiedadService;
import es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TaxonPathVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TerminoVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TerminoYPadreVO;
import es.pode.fuentestaxonomicas.negocio.servicio.VocabularioVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.i18n.LocalizacionIdiomaVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

/**
 * @see es.pode.buscador.presentacion.avanzado.buscarAvanzado.BuscarAvanzadoController
 */

public class BuscarAvanzadoControllerImpl extends BuscarAvanzadoController{
	private static final long serialVersionUID = -8190956967832345670L;
	private SrvPropiedadService prop = null;
	private java.util.Properties pSpringProperties = null;
	public final static String APPLICATION_PROPERTIES = "application-resources";
	private static Logger logger = Logger.getLogger(BuscarAvanzadoControllerImpl.class);
	
//	public final static String SLASH = "/";
//	public final static String ASTERISK = "*";
	public final static String ANDPERSAND = "&";
	public static final String HTML_PLUS = "%2B";
	public static final String JAVA_PLUS = "\\+";
	public static final String TAX_ASTERISCO = "\\*";
	public static final String UNIVERSAL = "universal";
	public static final String ACCION_SQI = "SQI";
	private final static String ACCION_CONFIGURAR_AVANZADO = "CONFIGURAR_AVANZADO";
	private final static String ACCION_ESCOGER_TAXONOMIA = "ESCOGER_TAXONOMIA";
	private final static String ACCION_NO_SELEC_TAX="NO_SELEC_TAX";
	private final static String ACCION_ELIMINAR_TAX = "ELIMINAR_TAX";
	private final static String ACCION_ESCOGER_TESAURO = "ESCOGER_TESAURO";
	private final static String ACCION_ELIMINAR_TESAURO = "ELIMINAR_TESAURO";
	private final static String ACCION_BUSCAR = "BUSCAR";
	private final static String ACCION_LIMPIAR = "LIMPIAR";
	private final static String ACCION_FORMULARIO_INVALIDO_AVANZADO = "FORMULARIO_INVALIDO_AVANZADO";
	private final static String ACCION_FORMULARIO_INVALIDO = "FORMULARIO_INVALIDO";
	private final static String ACCION_FORMULARIO_VALIDO = "FORMULARIO_VALIDO";
	private static final String ACCION_CON_RESULTADOS = "CON_RESULTADOS";
	private static final String ACCION_CON_SUGERENCIAS = "CON_SUGERENCIAS";
	private static final String ACCION_SIN_RESULTADOS = "SIN_RESULTADOS";
	private static final String NOMBRECOOKIE = "idiomaNavegacionCookie";
	
	public final static String MENSAJE_GENERICO_BUSQUEDA = "listar.odecu.mostrar.resultados.consulta.cabecera.errorBusqueda";
	public final static String MENSAJE_ANYO_INCORRECTO = "configurar.avanzado.controller.exception.anyoFechaIncorrecta";
	public final static String MENSAJE_CARACTER_FECHA = "configurar.avanzado.controller.exception.caracterComoFecha";
	public final static String MENSAJE_WILDCARD_ERRONEA = "configurar.avanzado.controller.exception.noAsteriscos";
	public final static String MENSAJE_WILDCARD_SOLA = "configurar.avanzado.controller.exception.noAsterisco";
	public final static String MENSAJE_SUMATORIO_INICIO = "configurar.avanzado.controller.exception.sumatorioInicio";
	public final static String MENSAJE_ERROR_GENERAR_RSS = "configurar.avanzado.controller.exception.mensajeErrorGenerarRSS";
	public final static String MENSAJE_ERROR_GENERAR_ATOM = "configurar.avanzado.controller.exception.mensajeErrorGenerarATOM";
	public final static String MENSAJE_USUARIO_NO_LOGADO="configurar.avanzado.controller.exception.usuarioNoLogado";
	public final static String MENSAJE_ERROR_ELIMINAR_ODE="configurar.avanzado.controller.exception.errorEliminarODE";
	public final static String MENSAJE_ERROR_AUTENTIFICACION="configurar.avanzado.controller.exception.errorAutentificacion";
	public final static String MENSAJE_ERROR_COMUNIDADES="configurar.avanzado.controller.exception.errorComunidades";
	public final static String MENSAJE_ERROR_TRADUCCIONES="listar.odecu.mostrar.resultados.detalles.errorTraduccionTermino";
	public final static String MENSAJE_ERROR_NO_SELEC_TAX="configurar.avanzado.controller.exception.noSelecTaxonomia";
	public final static String ERROR_ELIMINANDO_ODES="errors.odes.eliminar";
	
	public static final String DETALLE_ODE = "DETALLE_ODE";	
	public final static String DETALLE_ODE_FEDERADA = "DETALLE_ODE_FEDERADA";	
	public final static String TITULOTESAURO = "navegar.tesauro.nombre";	
	
	private static final String FICHERO = "application-resources";
	private static final String EUSKERA = "eu";
	private static final String INGLES = "en";
	private static final String SPACE = " ";
    /**
     * @see es.pode.buscador.presentacion.avanzado.buscarAvanzado.BuscarAvanzadoController#cargaFormularioBusquedaAvanzada(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.avanzado.buscarAvanzado.CargaFormularioBusquedaAvanzadaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cargaFormularioBusquedaAvanzada(ActionMapping mapping, es.pode.buscador.presentacion.avanzado.buscarAvanzado.CargaFormularioBusquedaAvanzadaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
     	try{
	    	BuscarSession sesion = this.getBuscarSession(request);
			if (sesion == null) {
				inicializarSesion(request);

				form.setContPropsContenido("0");
				form.setContAreasCurric("0");
				form.setContAmbito("0");
			}
//			llamada traer nodos
			SrvNodoService nodo = this.getSrvNodoService();
			NodoVO[] listaNodos = nodo.listarNodos();
			if(listaNodos == null || listaNodos.length == 0) form.setMostrarAmbito(false);
			else form.setMostrarAmbito(true);
			if(form.getComunidades()==null){
				try{
					if(listaNodos.length > 0){
						ArrayList listaComunidades = new ArrayList();
						//Añadimos checkbox para marcar todas las comunidades
						NodoVO todas = new NodoVO();
						todas.setNodo(I18n.getInstance().getResource("configurar.avanzado.comunidades.Todas", BuscarAvanzadoControllerImpl.APPLICATION_PROPERTIES, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)));
						todas.setId(0L);
						listaComunidades.add(todas);
						//Añadimos nodo local
						NodoVO nodoLocal = new NodoVO();
						nodoLocal.setNodo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVER_ON));
						nodoLocal.setId(-1L);
						listaComunidades.add(nodoLocal);
						//Añadimos nodos federados
						for(int i=0; i<listaNodos.length;i++){
							listaComunidades.add(listaNodos[i]);
						}
						form.setComunidades(listaComunidades);
					}else{
						form.setComunidades(new ArrayList());
					}
				}catch(Exception ex){
					form.setComunidades(new ArrayList());
					logger.error("ERROR: al obtener nodos para configuracion busqueda avanzada:",ex);
					saveErrorMessage(request,BuscarAvanzadoControllerImpl.MENSAJE_GENERICO_BUSQUEDA);
				}

			}else{
				form.setContAmbito(String.valueOf(form.getComunidades().size()+1));
			}
			
			String idiomaNavegacion = ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();

			if(form.getBuscContenido()==null || form.getBuscContenido().equals(""))form.setBuscContenido((comprobarAtributoConValor(sesion.getBuscadorContenido()))?sesion.getBuscadorContenido():getPropertyValue("avanzado.buscadorContenido"));
			else form.setBuscContenido(form.getBuscContenido().replaceAll(DetallarControllerImpl.JAVA_QUOTES, "&quot;"));
			if(form.getEdad()==null || form.getEdad().equals(""))form.setEdad((comprobarAtributoConValor(sesion.getEdad()))?sesion.getEdad():getPropertyValue("avanzado.edad"));
			else form.setEdad(form.getEdad().replaceAll(DetallarControllerImpl.JAVA_QUOTES, "&quot;"));
			if(form.getIdODE()==null || form.getIdODE().equals(""))form.setIdODE((comprobarAtributoConValor(sesion.getIdODE()))?sesion.getIdODE():getPropertyValue("avanzado.identificadorODE"));
			else form.setIdODE(form.getIdODE().replaceAll(DetallarControllerImpl.JAVA_QUOTES, "&quot;"));
			if(form.getAutor()==null || form.getAutor().equals(""))form.setAutor((comprobarAtributoConValor(sesion.getAutor()))?sesion.getAutor():getPropertyValue("avanzado.autor"));
			else form.setAutor(form.getAutor().replaceAll(DetallarControllerImpl.JAVA_QUOTES, "&quot;"));
			if(form.getDiaPublic()==null || form.getDiaPublic().equals(""))form.setDiaPublic((comprobarAtributoConValor(sesion.getDiaPublicacion()))?sesion.getDiaPublicacion():getPropertyValue("avanzado.fechaDia"));	
			if(form.getMesPublic()==null || form.getMesPublic().equals(""))form.setMesPublic((comprobarAtributoConValor(sesion.getMesPublicacion()))?sesion.getMesPublicacion():getPropertyValue("avanzado.fechaMes"));		
			if(form.getAnyoPublic()==null || form.getAnyoPublic().equals(""))form.setAnyoPublic((comprobarAtributoConValor(sesion.getAnyoPublicacion()))?sesion.getAnyoPublicacion():getPropertyValue("avanzado.fechaAnyo"));
			if(form.getIdiomBusc()==null || form.getIdiomBusc().equals(""))form.setIdiomBusc((comprobarAtributoConValor(sesion.getIdioma()))?sesion.getIdioma():obtenerIdiomaBusqueda());
			if(form.getFormato()==null || form.getFormato().equals(""))form.setFormato((comprobarAtributoConValor(sesion.getFormato()))?sesion.getFormato():"");
			if(form.getRecurso()==null || form.getRecurso().equals(""))form.setRecurso((comprobarAtributoConValor(sesion.getRecurso()))?sesion.getRecurso():"");
			if(form.getProcesoCognitivo()==null || form.getProcesoCognitivo().equals(""))form.setProcesoCognitivo((comprobarAtributoConValor(sesion.getProcesoCognitivo()))?sesion.getProcesoCognitivo():"");
			if(form.getContexto()==null || form.getContexto().equals(""))form.setContexto((comprobarAtributoConValor(sesion.getContexto()))?sesion.getContexto():"");
			if(form.getC_s_secuencia()==null || form.getC_s_secuencia().equals("")) form.setC_s_secuencia((comprobarAtributoConValor(sesion.getSecuencia()))?sesion.getSecuencia():"");
			if(form.getValoracion()==null || form.getValoracion().equals("")) form.setValoracion((comprobarAtributoConValor(sesion.getValoracion()))?sesion.getValoracion():"");
			if(form.getNivelAgreg()==null || form.getNivelAgreg().equals("")) form.setNivelAgreg((comprobarAtributoConValor(sesion.getNivelAgregacion()))?sesion.getNivelAgregacion():"");
			if(form.getDestinatarios()==null || form.getDestinatarios().equals("")) form.setDestinatarios((comprobarAtributoConValor(sesion.getDestinatarios()))?sesion.getDestinatarios():"");
			if(form.getTipoLayoutBuscador()==null)form.setTipoLayoutBuscador((sesion.getTipoLayoutBuscador()==null)?DetallarControllerImpl.LAYOUT_BUSCADOR:sesion.getTipoLayoutBuscador());
			
			// TODO Pendiente de meter en el BuscarSesion
			//if(form.getLiLicencia()==null || form.getLicencia().equals("")) form.setLicencia((atributoConValor(sesion.get()))?sesion.getNivelAgregacion():"");

			/*********************************************************************************************
			 *********************************************************************************************
			 *********************************************************************************************/
			String[]comunidadesSeleccAux=null;
			if(form.getTipoBusqueda()!=null && form.getTipoBusqueda().equals("01")){
				String[] comunidadesIds = obtenerComunidades();
				if(comunidadesIds!=null){
					form.setContAmbito(String.valueOf(comunidadesIds.length+2));
					comunidadesSeleccAux = new String[comunidadesIds.length+2];
					comunidadesSeleccAux[0]=String.valueOf(0);//Marcamos todas
					comunidadesSeleccAux[1]=String.valueOf(-1);//Marcamos local
					for(int i=2; i<comunidadesSeleccAux.length;i++) comunidadesSeleccAux[i]=String.valueOf(comunidadesIds[i-2]);
				}
				form.setComuSelec(comunidadesSeleccAux);
			}
			else if(form.getTipoBusqueda()!=null && form.getTipoBusqueda().equals("02")){

				form.setContAmbito("1");
				comunidadesSeleccAux=new String[]{"-1"};
				form.setComuSelec(comunidadesSeleccAux);
			}
			else if(form.getTipoBusqueda()!=null && form.getTipoBusqueda().equals("03")){
				if(form.getComuSelec()==null){//venimos de otra página 
											  //o no ha habido resultados ni sugerencias buscando en nodo local y por tanto el enlaceComuSelec será vacío
					logger.info("Tipo de busqueda personalizada con enlace --> "+ (form.getEnlaceComuSelec()==null?"":form.getEnlaceComuSelec()));
					if(form.getEnlaceComuSelec()==null || (form.getEnlaceComuSelec()!=null && form.getEnlaceComuSelec().equals(""))){
						form.setContAmbito("1");
						comunidadesSeleccAux=new String[]{"-1"};
					}
					else if(form.getEnlaceComuSelec().equals("0")){
						String[] comunidadesIds = obtenerComunidades();
						if(comunidadesIds!=null){
							form.setContAmbito(String.valueOf(comunidadesIds.length+2));
							comunidadesSeleccAux = new String[comunidadesIds.length+2];
							comunidadesSeleccAux[0]=String.valueOf(0);//Marcamos todas
							comunidadesSeleccAux[1]=String.valueOf(-1);//Marcamos local
							for(int i=2; i<comunidadesSeleccAux.length;i++) comunidadesSeleccAux[i]=String.valueOf(comunidadesIds[i-2]);
						}
					}
					else{
						String[] comunidadesIds = form.getEnlaceComuSelec().split("-");
						comunidadesSeleccAux = new String[comunidadesIds.length+1];
						comunidadesSeleccAux[0]=String.valueOf(-1);//Marcamos local
						for(int i=1; i<comunidadesSeleccAux.length;i++) comunidadesSeleccAux[i]=String.valueOf(comunidadesIds[i-1]);
					}
					form.setComuSelec(comunidadesSeleccAux);
				}
				else{
					//venimos de una recarga del formulario por haber mostrado algún mensaje de error
					//tenemos que marca el nodo local o porque no ha habido resultados
					String[] comuSelec = form.getComuSelec();
					comunidadesSeleccAux = new String[comuSelec.length+1];
					comunidadesSeleccAux[0]="-1";
					for(int i=0; i< comuSelec.length; i++) comunidadesSeleccAux[i+1]=comuSelec[i];
					form.setComuSelec(comunidadesSeleccAux);
				}
			}else{
				logger.info("No definido tipo de busqueda. Marcamos todos los nodos.");
				String[] comunidadesIds = obtenerComunidades();
				if(comunidadesIds!=null){
					form.setContAmbito(String.valueOf(comunidadesIds.length+2));
					comunidadesSeleccAux = new String[comunidadesIds.length+2];
					comunidadesSeleccAux[0]=String.valueOf(0);//Marcamos todas
					comunidadesSeleccAux[1]=String.valueOf(-1);//Marcamos local
					for(int i=2; i<comunidadesSeleccAux.length;i++) comunidadesSeleccAux[i]=String.valueOf(comunidadesIds[i-2]);
				}
				form.setComuSelec(comunidadesSeleccAux);
			}

			/*********************************************************************************************
			 *********************************************************************************************
			 *********************************************************************************************/
			form.setContPropsContenido(String.valueOf(contarCamposCargados(form.getIdODE(),form.getNivelAgreg(), form.getDestinatarios(),form.getFormato(), form.getIdiomBusc(), form.getRecurso(), form.getProcesoCognitivo(), form.getContexto(), form.getEdad(),form.getAutor(), form.getDiaPublic(), form.getMesPublic(), form.getAnyoPublic(), form.getC_s_secuencia(), form.getValoracion(),form.getLicencia())));
			form.setTipoBusqueda("03");
//			seleccion de los combos de fuentes taxonomicas

			String[] identificadoresLOMES = new String[]{getPropertyValue("destinatarios"),getPropertyValue("recurso"),getPropertyValue("procesoCognitivo"),getPropertyValue("contexto")};
			try{	
				VocabularioVO[] traducciones = this.getSrvVocabulariosControladosService().obtenerCombos(identificadoresLOMES, getPropertyValue("idioma.terminos"));
				VocabularioVO[] traducciones_espanyol = this.getSrvVocabulariosControladosService().obtenerCombos(identificadoresLOMES, idiomaNavegacion);

//				Compruebo si tenia ya sesion el formato
				form.setDestinatariosLabelList(transformarTerminoVOaString(traducciones_espanyol[0].getTerminos(),(Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)));
				form.setDestinatariosValueList(transformarTerminoValueVOaString(traducciones[0].getTerminos(),traducciones_espanyol[0].getTerminos(),form.getDestinatariosLabelList()));
				form.setRecursoLabelList(transformarTerminoVOaString(traducciones_espanyol[1].getTerminos(),(Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)));
				form.setRecursoValueList(transformarTerminoValueVOaString(traducciones[1].getTerminos(),traducciones_espanyol[1].getTerminos(),form.getRecursoLabelList()));
				form.setProcesoCognitivoLabelList(transformarTerminoVOaString(traducciones_espanyol[2].getTerminos(),(Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)));
				form.setProcesoCognitivoValueList(transformarTerminoValueVOaString(traducciones[2].getTerminos(),traducciones_espanyol[2].getTerminos(),form.getProcesoCognitivoLabelList()));
				form.setContextoLabelList(transformarTerminoVOaString(traducciones_espanyol[3].getTerminos(),(Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)));
				form.setContextoValueList(transformarTerminoValueVOaString(traducciones[3].getTerminos(),traducciones_espanyol[3].getTerminos(),form.getContextoLabelList()));
			}catch(Exception ex){
	//			Si no hay traducciones posibles a los vocabularios controlados que se pintan, se muestran vacios
				logger.error("Error invocando el servicio de vocabularios controlados. Imposible traducir los indices["+identificadoresLOMES[0]+"]["+identificadoresLOMES[1]+"]["+identificadoresLOMES[2]+"]["+identificadoresLOMES[3]+"]",ex);
				form.setDestinatarios(""); 
				form.setDestinatariosLabelList(new String[0]); 
				form.setDestinatariosValueList(new String[0]);
				form.setRecurso(""); 
				form.setRecursoLabelList(new String[0]); 
				form.setRecursoValueList(new String[0]);
				form.setProcesoCognitivo(""); 
				form.setProcesoCognitivoLabelList(new String[0]); 
				form.setProcesoCognitivoValueList(new String[0]);
				form.setContexto(""); 
				form.setContextoLabelList(new String[0]); 
				form.setContextoValueList(new String[0]);
				saveErrorMessage(request,DetallarControllerImpl.MENSAJE_TRADUCCION_BUSQUEDA);
			}

			try{
				LocalizacionIdiomaVO[] localizacionesIdioma = I18n.getInstance().obtenerIdiomasBuscablesLocalizados(idiomaNavegacion);
				form.setIdiomBuscBackingList(Arrays.asList(localizacionesIdioma), "idLocalizacion", "nombre");
			}catch(Exception ex){
	//			Si no hay traducciones posibles a los vocabularios controlados que se pintan, se muestran vacios
				logger.error("BuscarAvanzadoControllerImpl - cargaFormularioBusquedaAvanzada: Error invocando el servicio de obtener idiomas localizados. Imposible obtener idiomas localizados para:["+idiomaNavegacion+"]",ex);
				form.setIdiomBusc("");
				form.setIdiomBuscBackingList(new ArrayList(), "idLocalizacion", "nombre");
				saveErrorMessage(request,DetallarControllerImpl.MENSAJE_IDIOMAS_BUSQUEDA);
			}

			try{
				form.setFormatoLabelList(cargarFormatoLabel(new Locale(idiomaNavegacion)));
				form.setFormatoValueList(cargarFormatoValue());
			}catch(Exception ex){
				logger.error(ex);
				form.setFormato(""); 
				form.setFormatoLabelList(new String[0]); 
				form.setFormatoValueList(new String[0]);
				saveErrorMessage(request,MENSAJE_GENERICO_BUSQUEDA);
			}
			try{
				form.setC_s_secuenciaLabelList(cargarC_S_SecuenciaLabel(new Locale(idiomaNavegacion)));
				form.setC_s_secuenciaValueList(cargarC_S_SecuenciaValue());
     		}catch(Exception ex){
//				Si no hay traducciones posibles a los vocabularios controlados que se pintan, se muestran vacios
				logger.error("Error obteniendo valores para los combos de secuencia desde properties para idioma:["+idiomaNavegacion+"]",ex);
				form.setC_s_secuencia("");
				form.setC_s_secuenciaLabelList(new String[0]);
				form.setC_s_secuenciaValueList(new String[0]);
				saveErrorMessage(request,MENSAJE_GENERICO_BUSQUEDA);
			}
     		try{
	     		form.setValoracionLabelList(cargarValoracionLabel(new Locale(idiomaNavegacion)));
				form.setValoracionValueList(cargarValoracionValue());
	     	}catch(Exception ex){
//				Si no hay traducciones posibles a los vocabularios controlados que se pintan, se muestran vacios
				logger.error("Error obteniendo valores para los combos de valoración desde properties para idioma:["+idiomaNavegacion+"]",ex);
				form.setValoracion("");
				form.setValoracionLabelList(new String[0]);
				form.setValoracionValueList(new String[0]);
				saveErrorMessage(request,MENSAJE_GENERICO_BUSQUEDA);
			}
	     	try{
	     		form.setNivelAgregLabelList(cargarNivelAgregacionLabel(new Locale(idiomaNavegacion)));
				form.setNivelAgregValueList(cargarNivelAgregacionValue());
	     	}catch(Exception ex){
//				Si no hay traducciones posibles a los vocabularios controlados que se pintan, se muestran vacios
				logger.error("Error obteniendo valores para los combos de NivelAgregacion desde properties para idioma:["+idiomaNavegacion+"]",ex);
				form.setNivelAgreg("");
				form.setNivelAgregLabelList(new String[0]);
				form.setNivelAgregValueList(new String[0]);
				saveErrorMessage(request,MENSAJE_GENERICO_BUSQUEDA);
			}
	     	
//	     	Carga combo con taxonomias
	     	try{
	     		EstructuraVdexVO[] taxonomias = this.getSrvTaxonomiaService().obtenerTaxonomias(idiomaNavegacion);
	     		String[] taxonomiasLabel = new String[taxonomias.length];
	     		String[] taxonomiasValue = new String[taxonomias.length];
	     		for(int i=0;i<taxonomias.length;i++){
	     			taxonomiasLabel[i]=taxonomias[i].getVocabName();
	     			taxonomiasValue[i]=taxonomias[i].getVocabIdentifier();
	     		}
	     		form.setTaxonomiasLabelList(taxonomiasLabel);
	     		form.setTaxonomiasValueList(taxonomiasValue);
		     	
		     	//se cargan las rutas taxonómicas seleccionadas hasta el momento
	     		if(form.getEnlaceTaxSelec()!=null && !form.getEnlaceTaxSelec().equals("")){
		     		try{
		     			String[] rutasTax = form.getEnlaceTaxSelec().split(BuscarAvanzadoControllerImpl.TAX_ASTERISCO);
		     			List <String>rutasTaxSinRepetir = new ArrayList<String>();
		     			for(int i=0 ; i<rutasTax.length ;i++){
		     				if(!rutasTaxSinRepetir.contains(rutasTax[i])){
		     					rutasTaxSinRepetir.add(rutasTax[i]);
		     				}
		     			}
		     			StringBuilder enlaceTaxSelec = new StringBuilder(rutasTaxSinRepetir.get(0));
		     			for(int i=1 ; i<rutasTaxSinRepetir.size() ;i++){
		     				enlaceTaxSelec.append("*" + rutasTaxSinRepetir.get(i));
		     			}
		     			form.setRutasTax(tranformarEnlaceTaxSelecATaxonVO(enlaceTaxSelec.toString(),taxonomias,idiomaNavegacion));
		     			form.setEnlaceTaxSelec(enlaceTaxSelec.toString());
		     		}
		     		catch(Exception e){
		     			logger.error("Error al obtener las rutas taxonómicas seleccionadas",e);
		     			form.setEnlaceTaxSelec("");
		     		}
		     	}
	     		
				try{
					String idiomaLicencia =  getPropertyValue("idioma.licencia");
					
					form.setLicenciaLabelList(cargarLicenciaIdioma(idiomaNavegacion));
					form.setLicenciaValueList(cargarLicenciaIdioma(idiomaLicencia));

		     	}catch(Exception ex){
//					Si no hay traducciones posibles a los vocabularios controlados que se pintan, se muestran vacios
					logger.warn("Error obteniendo valores para el combo de licencias Agregacion desde properties para idioma:["+idiomaNavegacion+"]",ex);
					form.setLicencia("");
					form.setLicenciaLabelList(new String[0]);
					form.setLicenciaValueList(new String[0]);
					saveErrorMessage(request,MENSAJE_GENERICO_BUSQUEDA);
				}
	     	}catch(Exception ex){
	     		logger.error("Error obteniendo valores para los combos de Taxonomías", ex);
	     		form.setTaxonomiasLabelList(new String[0]);
	     		form.setTaxonomiasValueList(new String[0]);
	     	}

			if(comprobarAtributoConValor(form.getNomTesauros()))form.setContTesauros(String.valueOf(1));
			else form.setContTesauros(String.valueOf(0));

     	} catch (Exception ex) {
     		logger.error("ERROR: - ",ex);
			saveErrorMessage(request,MENSAJE_GENERICO_BUSQUEDA);
		}
    }

	public void limpiarFormulario(ActionMapping mapping, LimpiarFormularioForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		form.setAnyoPublic("");
		form.setAutor("");
		form.setBuscContenido("");
		form.setC_s_secuencia("");
		form.setContAreasCurric("0");
		form.setContPropsContenido("1");
		form.setContTesauros("0");
		form.setContexto("");
		form.setDescrip("");
		form.setDestinatarios("");
		form.setDiaPublic("");
		form.setEdad("");
		form.setIdODE("");
		form.setEnlaceComuSelec("");
		form.setEnlaceTaxSelec("");
		form.setFormato("");
		form.setIdiomBusc(((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage());
		form.setIdTesauro("");
		form.setMesPublic("");
		form.setNivelAgreg("");
		form.setNomTesauros("");
		form.setProcesoCognitivo("");
		form.setRecurso("");
		form.setTitulo("");
		form.setValoracion("");
		form.setTaxonomias("");
		form.setLicencia("");
		String[] comunidades = obtenerComunidades();
		if(comunidades!=null){//
			form.setComuSelec(null);//borramos lo que teniamos marcado
			form.setEnlaceComuSelec("0");//marcamos todas
		}
//		if(comunidades!=null){
//			form.setContAmbito(String.valueOf(comunidades.length+2));
//			String[] comunidadesSeleccAux = new String[comunidades.length+2];
//			for(int i=0; i<comunidadesSeleccAux.length;i++) comunidadesSeleccAux[i]=String.valueOf(i);
//			form.setEnlaceComuSelec("0-1"+DetallarControllerImpl.GUION+this.generarEnlaceComunidades(comunidadesSeleccAux));
//		}
	}
    
	public void cargarFormularioQuisoDecir(ActionMapping mapping, CargarFormularioQuisoDecirForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String idiomaNavegacion = null;
		try{
			if(form.getTipoBusqueda()==null)form.setTipoBusqueda("02");
			form.setIdiomBusc(devolverIdiomaBuscador(form.getIdiomBusc()));
			idiomaNavegacion = ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
			LocalizacionIdiomaVO[] localizacionesIdioma = I18n.getInstance().obtenerIdiomasBuscablesLocalizados(idiomaNavegacion);
			form.setIdiomBuscBackingList(Arrays.asList(localizacionesIdioma), "idLocalizacion", "nombre");
			try{
				form.setNumeroResultadosLabelList(cargarNumeroResultadosLabel(new Locale(idiomaNavegacion)));
				form.setNumeroResultadosValueList(cargarNumeroResultadosValue());
			}catch(Exception ex){
				form.setNumeroResultadosLabelList(new String[0]); 
				form.setNumeroResultadosValueList(new String[0]);
				saveErrorMessage(request,MENSAJE_GENERICO_BUSQUEDA);
			}
			try{
				NodoSQIVO[] nodos = this.getSrvGestionSqiService().obtenerNodosSQI();
				if (nodos!=null && nodos.length>0){
					form.setNodosSQI(true);
					logger.debug("Existen <" + nodos.length + "> nodos SQI indexados en la BBDD");
				}else{
					form.setNodosSQI(false);
					logger.info("No existe ningun nodo SQI indexado en la BBDD");
				}
				
			}catch (Exception e){
				logger.error("Error al intentar recuperar el numero de nodos SQI indexados en la tabla - ",e);
				form.setNodosSQI(false);
			}
		}catch(Exception ex){
//			Si no hay traducciones posibles a los vocabularios controlados que se pintan, se muestran vacios
			logger.error("Error invocando el servicio de obtener idiomas localizados. Imposible obtener idiomas localizados para <"+idiomaNavegacion+"> - ",ex);
			form.setIdiomBusc("");
			form.setIdiomBuscBackingList(new ArrayList(), "idLocalizacion", "nombre");
			saveErrorMessage(request,DetallarControllerImpl.MENSAJE_IDIOMAS_BUSQUEDA);
		}	
	}
    
    public void eliminarTaxonomia(ActionMapping mapping, EliminarTaxonomiaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	form.setEnlaceComuSelec(this.generarEnlaceComunidades(form.getComuSelec()));
    	String enlaceTaxSelec = form.getEnlaceTaxSelec();
    	String[] rutasTax = enlaceTaxSelec.split(TAX_ASTERISCO);
    	enlaceTaxSelec="";
//    	StringBuilder enlaceTax=new StringBuilder();
    	if(rutasTax!=null && rutasTax.length>0){
	    	for(int i = 0; i < rutasTax.length; i++){
	    		if(!eliminarPosicion(i,form.getTaxSelec()))
	    			enlaceTaxSelec = enlaceTaxSelec.equals("")?rutasTax[i]:enlaceTaxSelec + "*" + rutasTax[i];
	    	}
	    	form.setEnlaceTaxSelec(enlaceTaxSelec);
	    	form.setTaxSelec(new String[0]);
    	}else saveErrorMessage(request,MENSAJE_ERROR_NO_SELEC_TAX);
    	BuscarSession sesion = this.getBuscarSession(request);
    	if(sesion != null) sesion.setEnlaceTaxSelec(enlaceTaxSelec);
	}
    
	public void eliminarTesauro(ActionMapping mapping, EliminarTesauroForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	form.setNomTesauros("");
    	form.setIdTesauro("");
    	form.setEnlaceComuSelec(this.generarEnlaceComunidades(form.getComuSelec()));
    	BuscarSession sesion = this.getBuscarSession(request);
    	if(sesion != null) sesion.setIdTesauro("");
	}
    
	public String analizaEntrada(ActionMapping mapping, AnalizaEntradaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BuscarSession sesion = this.getBuscarSession(request);
		
		if((form.getTipoBusqueda()!=null &&( form.getTipoBusqueda().equalsIgnoreCase("05")||form.getTipoBusqueda().equalsIgnoreCase("06")||form.getTipoBusqueda().equalsIgnoreCase("07")||form.getTipoBusqueda().equalsIgnoreCase("08"))))
		{
			return ACCION_BUSCAR;
		}
		if(form.getTipoLayoutBuscador()==null){
			try{	    	
		    	if("true".equals(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NEUTRO))) form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_NEUTRO);
		    	else form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_BUSCADOR);
			}catch (Exception e){
				logger.debug("No existe la propiedad neutro del agregaProperties - ",e);
				form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_BUSCADOR);
			}
		}
		if(form.getEmpIdOde() != null || form.getEmpIdDestino() != null || form.getEmpTipoEmpaquetador() != null){
//			 Inicializo sesión
			this.setBuscarSession(request,new BuscarSession());
			sesion = this.getBuscarSession(request);
			sesion.setEmpIdOde(form.getEmpIdOde());
			sesion.setEmpIdDestino(form.getEmpIdDestino());
			sesion.setEmpTipoEmpaquetador(form.getEmpTipoEmpaquetador());
			this.setBuscarSession(request, sesion);
//			logger.debug("agregarOdes: Se agrega ode federado al empaquetador con  IdOde: <" +sesion.getEmpIdOde()+"> IdDestino: <"+sesion.getEmpIdDestino()+" TipoEmpaquetador:"+ sesion.getEmpTipoEmpaquetador()+">");
			form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_EMPAQUETADOR);
		}else if(form.getBusqSimpleAvanz() == null && sesion.getBusquedaSimpleAvanzada() != null && sesion.getBusquedaSimpleAvanzada().trim().equals(DetallarControllerImpl.BUSCAR)){
			cargarFormularioSesion(form,request);
			return ACCION_BUSCAR;
		}else if(form.getBusqSimpleAvanz() != null && form.getBusqSimpleAvanz().trim().equals(DetallarControllerImpl.BUSCAR_SIMPLE)){
			//form.setBusquedaSimpleAvanzada(ACCION_BUSCAR);
			return ACCION_BUSCAR;
		}
		cargarFormularioSesion(form,request);
		inicializarSesion(request);
		return ACCION_CONFIGURAR_AVANZADO;
	}
	
    
	public String analizaPulsacion(ActionMapping mapping, AnalizaPulsacionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			ResourceBundle datosResources = I18n.getInstance().getResource(APPLICATION_PROPERTIES, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
//			log("<"+form.getPulsacion()+"> con valores <"+datosResources.getString("configurar.avanzado.taxonomias.aniadir")+"> <"+datosResources.getString("configurar.avanzado.taxonomias.eliminar")+"> <"+datosResources.getString("configurar.avanzado.buscar.buscar")+">");
			String pulsacion = form.getPulsacion();
			if(pulsacion == null || pulsacion.equals("")){
				pulsacion = obtenerPulsacion(request,datosResources.getString("configurar.avanzado.taxonomias.pulsacion.eliminar"));
				form.setPulsacion(pulsacion);
			}
			if (datosResources.getString("configurar.avanzado.taxonomias.pulsacion.aniadir").equals(pulsacion) && (form.getTaxonomias()==null || form.getTaxonomias().trim().equals(""))) {
				this.saveErrorMessage(request,MENSAJE_ERROR_NO_SELEC_TAX);
				return ACCION_NO_SELEC_TAX;
				}
			else if (datosResources.getString("configurar.avanzado.taxonomias.pulsacion.aniadir").equals(pulsacion) && form.getTaxonomias()!=null && !form.getTaxonomias().trim().equals("")) return ACCION_ESCOGER_TAXONOMIA;
			else if (pulsacion!=null && pulsacion.startsWith(datosResources.getString("configurar.avanzado.taxonomias.pulsacion.eliminar"))) return ACCION_ELIMINAR_TAX;
			else if (datosResources.getString("configurar.avanzado.tesauros.pulsacion.aniadir").equals(pulsacion)) return ACCION_ESCOGER_TESAURO;
			else if (datosResources.getString("configurar.avanzado.tesauros.eliminar").equals(pulsacion)) return ACCION_ELIMINAR_TESAURO;
			else if (datosResources.getString("configurar.avanzado.buscar.pulsacion.limpiar").equals(pulsacion)) return ACCION_LIMPIAR;
    		if(form.getIdiomaNavegacion()!= null && !form.getIdiomaNavegacion().equals("")){
    			this.setCookieIdioma(response, form.getIdiomaNavegacion());
    			request.getSession().setAttribute(ConstantesAgrega.DEFAULT_LOCALE, new Locale(form.getIdiomaNavegacion()));
    		}
			return ACCION_BUSCAR;
		} catch (Exception ex) {
			logger.error("Excepcion: ",ex);
			saveErrorMessage(request,MENSAJE_GENERICO_BUSQUEDA);
			return ACCION_FORMULARIO_INVALIDO;
		}
	}
  
	public void validarFormulario(ActionMapping mapping, ValidarFormularioForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//Recupero los valores de los filtros seleccionados y los refresco

		List errorMessages = null;
		try{	
			if((form.getTipoBusqueda()!=null &&( form.getTipoBusqueda().equalsIgnoreCase("05")||form.getTipoBusqueda().equalsIgnoreCase("06")||form.getTipoBusqueda().equalsIgnoreCase("07")||form.getTipoBusqueda().equalsIgnoreCase("08"))))
		
			{
				form.setValidado(ACCION_FORMULARIO_VALIDO);
			}
			else
			{
				//Gestionamos los checkbox's segun las distintas opciones
				
				//Al utilizar campos comunes en el formulario-avanzado y en el mostrar imagenes tenemos que observar distintas situaciones para 
				//esos checkbox
				//1.Busqueda inicial
				//2.Marcar un nuevo checkbox y realizar la busqueda
				//3.Desmarcar todos
				//4.Paginación
				
				
				
				
				if(form.getFiltroNivelAgregacion()==null)
				{
					if(form.getFormularioInicial()==null&&form.getPagina()!=null)
					{
					//Quiere decir que estamos en el formulario de resultados y que estamos paginando
					String [] nivelAgregAux=form.getNivelAgreg().split("-");
					form.setFiltroNivelAgregacion(nivelAgregAux);
					
					}
					else if(form.getFormularioInicial()==null&&form.getPagina()==null)
					{
					//En este caso estamos desmarcando todos los checkbox
					form.setFiltroNivelAgregacion(new String[0]);
					}
					else
					{
					//Estamos en el formulario inicial
						String [] nivelAgregAux=form.getNivelAgreg().split("-");
						form.setFiltroNivelAgregacion(nivelAgregAux);
					}
				
				}
				else
				//Hemos marcado los checkbox
				form.setFiltroNivelAgregacion(form.getFiltroNivelAgregacion());
		
				if(form.getFiltroFormato()==null)
				{
					if(form.getFormularioInicial()==null&&form.getPagina()!=null)
					{
					//Quiere decir que estamos en el formulario de resultados y que estamos paginando
					String [] formatoAux=form.getFormato().split("-");
					form.setFiltroFormato(formatoAux);
					
					}
					else if(form.getFormularioInicial()==null&&form.getPagina()==null)
					{
					//En este caso estamos desmarcando todos los checkbox
					form.setFiltroFormato(new String[0]);
					}
					else
					{
					//Estamos en el formulario inicial
						String [] formatoAux=form.getFormato().split("-");
						form.setFiltroFormato(formatoAux);
					}
				
				}
				else
				//Hemos marcado los checkbox
				form.setFiltroFormato(form.getFiltroFormato());
				
			form.setContPropsContenido(contarCamposCargados(form.getIdODE(),form.getNivelAgreg(),form.getDestinatarios(),form.getFormato(), form.getIdiomBusc(), form.getRecurso(), form.getProcesoCognitivo(), form.getContexto(), form.getEdad(),form.getAutor(), form.getDiaPublic(), form.getMesPublic(), form.getAnyoPublic(), form.getC_s_secuencia(), form.getValoracion(),form.getLicencia()));
			form.setContAreasCurric(contarCamposArbolCurricular(form));
			//Si "form.getBuscadorContenido()" tiene valor venimos de busquedaSQI
			if(form.getBuscContenido()==null && form.getBuscadorContenido()!=null)form.setBuscContenido(form.getBuscadorContenido());
			form.setBuscContenido(devolverTextoBusqueda(form.getBuscContenido(), form.getBuscContenSolo()));
			if(form.getIdODE()!=null && !form.getIdODE().trim().equals(""))form.setIdODE(form.getIdODE().replaceAll(DetallarControllerImpl.JAVA_QUOTES, "&quot;"));
			if(form.getAutor()!=null && !form.getAutor().trim().equals(""))form.setAutor(form.getAutor().replaceAll(DetallarControllerImpl.JAVA_QUOTES, "&quot;"));
			if(form.getEdad()!=null && !form.getEdad().trim().equals(""))form.setEdad(form.getEdad().replaceAll(DetallarControllerImpl.JAVA_QUOTES, "&quot;"));
//			Valido el formulario para ver si es correcto
			errorMessages=validarCamposFecha(form.getDiaPublic(),form.getMesPublic(),form.getAnyoPublic());
			if(esVacioFormulario(form) && esVacioCajaTexto(form)) 
				errorMessages.add("configurar.avanzado.controller.exception.NingunCampoLleno");
			else{
				if(!validarAsterisco(form.getBuscContenido()) || !validarAsterisco(form.getIdODE()) || !validarAsterisco(form.getEdad()) || !validarAsterisco(form.getAutor())) errorMessages.add(MENSAJE_WILDCARD_SOLA);
				if(!validarAndQuery(form.getBuscContenido()) || !validarAndQuery(form.getIdODE()) || !validarAndQuery(form.getEdad()) || !validarAndQuery(form.getAutor()) || !validarAndQuery(form.getLicencia())) errorMessages.add(MENSAJE_SUMATORIO_INICIO);
				if(form.getTipoBusqueda() == null || !form.getTipoBusqueda().equals("03")) inicializarFormulario(form);
				if(form.getTipoBusqueda()!=null && form.getTipoBusqueda().equals("03")) validarPersonalizada(form);
			}
			if(errorMessages!=null && errorMessages.size()>0){

				logger.error("3");
				SrvNodoService nodo = this.getSrvNodoService();
				NodoVO[] listaNodos = nodo.listarNodos();
				if(listaNodos == null || listaNodos.length == 0) form.setMostrarAmbito(false);
				else form.setMostrarAmbito(true);
					logger.warn("Existen errores de validacion="+errorMessages.toString());
				if(form.getBusqSimpleAvanz()!=null && form.getBusqSimpleAvanz().equals(DetallarControllerImpl.BUSCAR_SIMPLE))form.setValidado(ACCION_FORMULARIO_INVALIDO);
				else form.setValidado(ACCION_FORMULARIO_INVALIDO_AVANZADO);
				if(form.getEnlaceComuSelec()==null || form.getEnlaceComuSelec().equals(""))
					form.setEnlaceComuSelec(this.generarEnlaceComunidades(form.getComuSelec()));
				saveErrorMessage(request, errorMessages);
			}else{
					logger.debug("validarFormulario: OK");
				form.setValidado(ACCION_FORMULARIO_VALIDO);
			}
			
			this.inicializarSesion(request);
			}
			
		}catch(Exception ex){
			logger.error("linea 1/2: excepcion durante el validado de formulario - ",ex);
			form.setValidado("ACCION_FORMULARIO_INVALIDO");
			saveErrorMessage(request, MENSAJE_GENERICO_BUSQUEDA);
			String peticion=request.toString();
			logger.error("linea 2/2, request: <"+peticion+">");
		}
	}
	
	public void ejecutarBusquedaAvanzada(ActionMapping mapping, EjecutarBusquedaAvanzadaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ParametrosBusquedaAvanzadaVO30 param = null;
    	ResultadoBusquedaVO resultados = null;
    	String textoBusqueda = "";
//    	ValoresBusquedaVO[] valoresBusquedaVOTax=null;
    //	Integer pagina= null;
    	List<String> errores = new ArrayList<String>();
    	try{
    		if(form.getTipoBusqueda().equalsIgnoreCase("07")||form.getTipoBusqueda().equalsIgnoreCase("08"))
    		{
    		//En el caso de que el flujo venga de Busqueda Taxonómica-Tesauro
    			busquedaTesauro(form,request,response);
    		}

    		
    		//En el caso de que el flujo venga de Busqueda taxonómica-Arbol Curricular
    		
    		else if(form.getTipoBusqueda().equalsIgnoreCase("05")||form.getTipoBusqueda().equalsIgnoreCase("06"))
    		{
    			busquedaArbol(form,request,response);
    			    		
    		}
    		//Busqueda Avanzada
    		else
    		{
    		
    		if("true".equals(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NEUTRO))) form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_NEUTRO);
    		else if(form.getTipoLayoutBuscador()==null) form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_BUSCADOR);
			if(form.getTipoLayoutBuscador().equals(DetallarControllerImpl.LAYOUT_BUSCADOR) || form.getTipoLayoutBuscador().equals(DetallarControllerImpl.LAYOUT_NEUTRO)){
				try{
					Boolean administrador=false;
					//if(form.getKeyword()==null || form.getKeyword().trim().equals("")) 
					administrador = LdapUserDetailsUtils.esAdministrador();
					form.setUsuarioAdmin(administrador);
					Boolean publicador=false;
					//if(form.getKeyword()==null || form.getKeyword().trim().equals("")) 
					publicador = LdapUserDetailsUtils.esPublicador();
					
					form.setUsuarioPublicador(publicador);

				}catch(Exception e){
					form.setUsuarioAdmin(false);
					errores.add(MENSAJE_ERROR_AUTENTIFICACION);					
						logger.error("Error al llamar a LdapUserDetailsUtils.esAdministrador() de Soporte: <"+MENSAJE_ERROR_AUTENTIFICACION+"> - ",e);
				}
			}else form.setUsuarioAdmin(Boolean.FALSE);			
			
			String idiomaNavegacion = ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
	    		
	
    		//Añadimos checkbox
			String[] nivelesAgregacionLabel=new String[cargarNivelAgregacionValue().length];
			
			nivelesAgregacionLabel=cargarNivelAgregacionLabel(new Locale(idiomaNavegacion));
			
			
			String[] nivelesAgregacionValue=new String[cargarNivelAgregacionValue().length];
			
			nivelesAgregacionValue=cargarNivelAgregacionValue();
	

			form.setNivelAgregLabelList(nivelesAgregacionLabel);
			form.setNivelAgregValueList(nivelesAgregacionValue);

			
			//Añadimos checkbox
			String[] formatoLabel=new String[cargarFormatoValue().length];
			
			formatoLabel=cargarFormatoLabel(new Locale(idiomaNavegacion));
			
			
			String[] formatoValue=new String[cargarFormatoValue().length];
			
			formatoValue=cargarFormatoValue();
			
				
			
			form.setFormatoLabelList(formatoLabel);
			form.setFormatoValueList(formatoValue);
			
			// 15062012 Ejfente Añadimos licencias				
			String idiomaLicencia =  getPropertyValue("idioma.licencia");
			
			form.setLicenciaLabelList(cargarLicenciaIdioma(idiomaNavegacion));
			form.setLicenciaValueList(cargarLicenciaIdioma(idiomaLicencia));

	
//        	Elimino los espacios en blanco del formulario de los campos de wild-card
    		if(prepararFormulario(form)){
    			form.setIdiomBusc((form.getIdioma()==null)?devolverIdiomaBuscador(form.getIdiomBusc()):form.getIdioma());
    			form.setIdioma((form.getIdioma()==null)?form.getIdiomBusc():form.getIdioma());
//		    	Los idiomas que deben aparecer en el desplegable de buscar dependen del idioma de navegacion
				LocalizacionIdiomaVO[] localizacionesIdioma = I18n.getInstance().obtenerIdiomasBuscablesLocalizados(idiomaNavegacion);
				form.setIdiomBuscBackingList(Arrays.asList(localizacionesIdioma), "idLocalizacion", "nombre");
				try{
					form.setNumeroResultadosLabelList(cargarNumeroResultadosLabel(new Locale(idiomaNavegacion)));
					form.setNumeroResultadosValueList(cargarNumeroResultadosValue());
				}catch(Exception ex){
					form.setNumeroResultadosLabelList(new String[0]); 
					form.setNumeroResultadosValueList(new String[0]);
					saveErrorMessage(request,MENSAJE_GENERICO_BUSQUEDA);
				}
		    	if (form.getPagina() == null || form.getPagina().equals("")) form.setPagina("1");
				textoBusqueda = devolverTextoBusqueda(form.getBuscContenido(), form.getBuscContenSolo());
	    		String encoding = response.getCharacterEncoding();
				form.setBuscContenEnlace(removeAccents(textoBusqueda,encoding).replaceAll(JAVA_PLUS, HTML_PLUS));
					//logger.debug("ejecutarBusquedaAvanzada con textoBusqueda["+textoBusqueda+"], idiomaBuscador["+form.getIdiomBusc()+"] y paginaRequerida["+form.getPagina()+"]");
//	        	PREPARAMOS LA CONSULTA DE BUSQUEDA
//	        	Por ahora utilizamos el locale. Cuando tengamos usuarios sera el idioma de navegacion por la aplicacion
	    		param = new ParametrosBusquedaAvanzadaVO30();
	    		param.setIdiomaNavegacion(idiomaNavegacion);
	    		param.setIdiomaBusqueda(form.getIdioma());
	    		param.setOrigenPagina(new Integer(form.getPagina()));
	    		param.setPalabrasClave(prepararTextoBusqueda(eliminarEspaciosBlanco(textoBusqueda)));
	    		if(form.getFiltroFormato()!=null && form.getFiltroFormato().length>0 && !form.getFiltroFormato()[0].equalsIgnoreCase(""))
	    		{
	    		//En el caso de tener todas las opciones seleccionadas no se rellena parametro
	    		if(formatoValue.length!=form.getFiltroFormato().length)
	    		{
	    		    param.setFormato(form.getFiltroFormato());//Hay que cambiar form.getFormato() de String a String[]
	    		}
	    		StringBuilder formatoAux=new StringBuilder("");
	    		for(int i=0;i<form.getFiltroFormato().length;i++)
	    		{
	    			if(i+1==form.getFiltroFormato().length)
	    				formatoAux.append(form.getFiltroFormato()[i]);
	    			else
	    			formatoAux.append(form.getFiltroFormato()[i].concat("-"));
	    		}
	    		form.setFormato(formatoAux.toString());
	    		}

	    		param.setKeyword(form.getKeyword());
	    		param.setDestinatarios(form.getDestinatarios());
	    		param.setRecurso(form.getRecurso());
	    		param.setBusquedaSimpleAvanzada((form.getBusqSimpleAvanz() != null && !form.getBusqSimpleAvanz().trim().equals(""))?form.getBusqSimpleAvanz():DetallarControllerImpl.BUSCAR);
	    		param.setProcesoCognitivo(form.getProcesoCognitivo());
	    		param.setFechaPublicacion(construirFecha(form.getAnyoPublic(),form.getMesPublic(),form.getDiaPublic()));
				param.setContexto(form.getContexto());
				if(form.getFiltroNivelAgregacion()!=null&& form.getFiltroNivelAgregacion().length>0 && !form.getFiltroNivelAgregacion()[0].equalsIgnoreCase(""))
				{
				//En el caso de tener todas las opciones seleccionadas no se rellena el parametro param para que se produzca cacheo en la busqueda
				if(nivelesAgregacionValue.length!=form.getFiltroNivelAgregacion().length)
		  		{			
				param.setNivelAgregacion(form.getFiltroNivelAgregacion());//Hay que cambiar form.getNivelAgreg() de String a String[]
		    		}
				String nivelAgregAux="";
	    		for(int i=0;i<form.getFiltroNivelAgregacion().length;i++)
	    		{
	    			if(i+1==form.getFiltroNivelAgregacion().length)
	    				nivelAgregAux+=form.getFiltroNivelAgregacion()[i];
	    			else
	    			nivelAgregAux+=form.getFiltroNivelAgregacion()[i].concat("-");
	    		}
	    		form.setNivelAgreg(nivelAgregAux);
				}
				
		
 				if(form.getEdad()!=null && !form.getEdad().trim().equals(""))param.setEdad(prepararTextoBusqueda(eliminarEspaciosBlanco(form.getEdad())));
				if(form.getIdODE()!=null && !form.getIdODE().trim().equals(""))param.setIdentificador(prepararTextoBusqueda(eliminarEspaciosBlanco(form.getIdODE())));
				if(form.getAutor()!=null && !form.getAutor().trim().equals(""))param.setAutor(prepararTextoBusqueda(eliminarEspaciosBlanco(form.getAutor())));
				param.setSecuencia(form.getC_s_secuencia()!=null && !form.getC_s_secuencia().equals("")?new Boolean(form.getC_s_secuencia()):null);
				param.setValoracion(form.getValoracion()!=null && !form.getValoracion().equals("")?form.getValoracion().replaceAll("-", DetallarControllerImpl.SEPARADOR_PALABRAS):null);
					
				String [] taxTesSelec = this.obtenerTaxYTes(form.getEnlaceTaxSelec(), form.getIdTesauro(), form.getIdTesauroSug());
				
				param.setTaxonomia(taxTesSelec);
				
				String[] comuni=obtenerComunidades();
//				Comprobacion del nodo neutro
				if(comuni == null || comuni.length == 0) form.setMostrarAmbito(false);
				else form.setMostrarAmbito(true);

				//Nodo local
				param.setComunidadPeticion(LdapUserDetailsUtils.getHost());

				//Obtenemos los nodos federados seleccionados
				if(comuni!=null && comuni.length>0 && form.getTipoBusqueda()!=null && form.getTipoBusqueda().equals("01")){
					// 03072012 Incluimos el nodo local con valor "-1" para que se tenga en cuenta en la cache 
					// porque si no una búsqueda desde portada por Todo Agrega y desde avanzado con Todo Agrega menos 
					// el nodo local la tomaba como la misma
					String[] comunidadesConLocal = new String[comuni.length+1];
					comunidadesConLocal[0]="-1";
					for(int i=0; i<comuni.length;i++) comunidadesConLocal[i+1]=String.valueOf(comuni[i]);
					
					param.setComunidadesSeleccionadas(comunidadesConLocal);
				}
				else if(comuni!=null && comuni.length>0 && form.getTipoBusqueda().equals("03")){
					String[] comuniSelec = form.getComuSelec();
					if(comuniSelec!=null){//venimos del formulario avanzado						
						//generamos el enlace
						String enlace=generarEnlaceComunidades(comuniSelec);
						form.setEnlaceComuSelec(enlace);
						
						// 26062012 Pasamos siempre la lista de comunidades seleccionadas para que el servicio sea capaz de distinguir si el usuario
						// ha seleccionado o no el nodo local
						param.setComunidadesSeleccionadas(comuniSelec);
						
					}
					else if (form.getEnlaceComuSelec()!=null && !form.getEnlaceComuSelec().equals("")){
						if(form.getEnlaceComuSelec().equals("0")){//Nos llaman desde contenidos. Seleccionamos todas
							param.setComunidadesSeleccionadas(comuni);
						}else if("-1".equals(form.getEnlaceComuSelec())) // Venimos de un enlace de paginación con búsqueda en nodo local
								{
									comuniSelec = new String[]{"-1"};
									param.setComunidadesSeleccionadas(comuniSelec);
								}else{//venimos del listado de resultados
									comuniSelec = form.getEnlaceComuSelec().split("-");
									if(comuniSelec.length>0)
										param.setComunidadesSeleccionadas(comuniSelec);											
									else
										param.setComunidadesSeleccionadas(comuni);
								}
						
					}
					
				}

				param.setNumeroResultados(new Integer(request.getSession().getServletContext().getInitParameter("max_res_totales")));
				Integer maxResPagina = 0;
				if(form.getNumeroResultados()!=null && !form.getNumeroResultados().equals(""))maxResPagina=new Integer(form.getNumeroResultados());
				else maxResPagina = Integer.valueOf(request.getSession().getServletContext().getInitParameter("max_res_pagina"));
				param.setResultadosPorPagina(maxResPagina);
				
				
				// 15062012 Metemos la licencia en el vo de búsqueda
				
				if (form.getLicencia()!=null && !form.getLicencia().equals(""))
				{
					logger.debug("Se incluye la licencia como parámetro de búsqueda : " + form.getLicencia());
					String[] licencias= new String[1];
					licencias[0]=form.getLicencia();
					param.setLicencias(licencias);					
				}
				
//		    	Invocamos el servicio de busqueda
				SrvBuscarService buscarService = this.getSrvBuscarService();
				resultados = buscarService.buscarAvanzado(param);
					logger.debug("Resultados de busqueda avanzada <"+(resultados.getResultadoBusqueda()!= null?resultados.getResultadoBusqueda().length:0)+">, sugerencias <"+(resultados.getSugerencias()!= null&&resultados.getSugerencias().length>0?resultados.getSugerencias()[0]+" ...":"SIN SUGERENCIAS")+">");					
//		    	Hay que traducir los valores de "tipoRecurso" por sus identificadores en lom-es y pasarselos asi al formulario
				ValoresBusquedaVO[] valoresBusquedaVO = traducirResultadosBusqueda(form.getPagina(), resultados.getResultadoBusqueda(), request, idiomaNavegacion, maxResPagina);				
				form.setResultadosVO(Arrays.asList(valoresBusquedaVO));
				form.setBuscContenido(textoBusqueda);
				int resultadosDesde = (Integer.parseInt(form.getPagina()) -1 ) * maxResPagina + 1;
				form.setResultadosDesde(String.valueOf(resultadosDesde));

				int resultadosHasta = resultadosDesde + maxResPagina - 1;
				resultadosHasta = (resultadosHasta > resultados.getNumeroResultados().intValue()? resultados.getNumeroResultados().intValue(): resultadosHasta);
				form.setResultadosHasta(String.valueOf(resultadosHasta));
				form.setNumMaxResultados(resultados.getTotalResultados());
				form.setQuisoDecir(resultados.getSugerencias());
				form.setTesauros(resultados.getTesauros());
				
				
		
				
				
				
				if(valoresBusquedaVO.length>0 && form.getFormato()!=null)
				{
					form.setFormatoURL(form.getFormato().replace('/','$'));
				}
		
				try{
					NodoSQIVO[] nodos = this.getSrvGestionSqiService().obtenerNodosSQI();
					if (nodos!=null && nodos.length>0){
						form.setNodosSQI(true);
							logger.debug("Existen <" + nodos.length + "> nodos SQI indexados en la BBDD");
					}else{
						form.setNodosSQI(false);
							logger.debug("No existe ningun nodo SQI indexado en la BBDD");
					}
					
				}catch (Exception e){
						logger.error("Error al intentar recuperar el numero de nodos SQI indexados en la tabla - ",e);
					form.setNodosSQI(false);
				}
//		    	Este metodo resuelve toda la problematica del banner de paginacion sobre el formulario.
				generarPaginas(	form,
						form.getPagina(),
	    						resultados.getNumeroResultados(),
	    						maxResPagina,
	    						maxResPagina);
    		}
    		}
		}catch (Exception ex){
			resultados=null;
			logger.error("ERROR: en la invocacion a la Busqueda Avanzada con parametros IdBusqueda["+param.getIdBusqueda()
					+"], IdiomaBusqueda["+param.getIdiomaBusqueda()+"], IdiomaNavegacion["+param.getIdiomaNavegacion()
					+"], PalabrasClave["+param.getPalabrasClave()+"], NumeroResultados["+param.getNumeroResultados()
					+"], OrigenPagina["+param.getOrigenPagina()+"],Formato["+Arrays.toString(param.getFormato())
					+"], IdentificadorODE["+param.getIdentificador()+"], Recurso["+param.getRecurso()+"], ProcesoCognitivo["+param.getProcesoCognitivo()
					+"], Contexto["+param.getContexto()+"], Edad["+param.getEdad()
					+"], Autor["+param.getAutor()+"], FechaPublicacion["+param.getFechaPublicacion()
					+"], Taxonomia["+Arrays.toString(param.getTaxonomia())+"]"
					+"], Secuencia["+param.getSecuencia()+"], NivelAgregacion["+Arrays.toString(param.getNivelAgregacion())+"], Valoracion["+param.getValoracion()
					+"], ResultadosPorPagina["+param.getResultadosPorPagina()+"]");
			logger.error("BuscarAvanzadoControllerImpl - ejecutarBusquedaAvanzada ERROR:", ex);
			errores.add(MENSAJE_GENERICO_BUSQUEDA);			
		}
		if(errores!=null && errores.size()>0)saveErrorMessage(request, errores);
	}
	
	private void busquedaTesauro(EjecutarBusquedaAvanzadaForm form,HttpServletRequest request, HttpServletResponse response)
	{
		ValoresBusquedaVO[] valoresBusquedaVOTax=null;
		try{
    		BuscarSession sesion = this.getBuscarSession(request);
    		//logger.debug("La sesion tiene un valor de ["+sesion+"] y el valor del campo de la sesion tituloTesauro es ["+sesion.getTituloTesauro()+"]");
    		if (sesion.getNodoOrigen()!=null && !sesion.getNodoOrigen().equals("")){
    			form.setTesauroBusqueda(sesion.getTesauroBusqueda());
    			form.setPagina(sesion.getPagina().toString());
    			form.setTipoBusqueda(sesion.getTipoBusqueda());
    			form.setTipoNavegacion(sesion.getTipoNavegacion());//cuando volvemos del detalle del ODE
    			this.setBuscarSession(request,new BuscarSession());
    		}
    		String idiomaNavegacion = ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
	    	
    		//tesauro seleccionado para buscar
    		String vocabIdentifierTes="";
    		
    		//Busqueda Taxonomica.Se ha seleccionado el Tipo de Navegacion
    		if(form.getTipoNavegacion()!=null && !form.getTipoNavegacion().equalsIgnoreCase(""))
    		{
    		vocabIdentifierTes = form.getTipoNavegacion();
    		}
    		//Desde RSS generado
    		else
    		{
    			//Va a almacenar el IdTesauro y el código del tesauro seleccionado (en circunstancias normales se recoge el tesauro vigente)
    			String[] tesauro = new String[2];
    			tesauro=form.getIdTesauro().replace('$', '/').split("/");
    			form.setIdTesauro(tesauro[1]);
    			
    			//Recogemos el tesauro seleccionado previamente al RSS
    			vocabIdentifierTes=tesauro[0];
    			form.setTipoNavegacion(tesauro[0]);
    			form.setTesauroBusqueda(tesauro[1]);
    		}
  
    		String vocabNameTesauro = this.getSrvTesaurosServices().obtenerVocabName(vocabIdentifierTes, idiomaNavegacion);
    		if (sesion.getTituloTesauro() != null && !sesion.getTituloTesauro().equals(""))
    			form.setTituloTesauro(sesion.getTituloTesauro());
    		else
    		{
    			
    			Locale locale = null;
    			try {
    				locale = devuelveLocale(request);
    			} catch (Exception e) {
    				logger.error("Error recuperando el locale - ",e);
    			}
    			String tituloTesauro = "";
//    			Se forma el titulo del tesauro. El orden cambia dependiendo del idioma		 
    			String literalTituloTesauro = BuscarAvanzadoControllerImpl.getResource(TITULOTESAURO, FICHERO, locale);	
    			if (EUSKERA.equals(idiomaNavegacion) || INGLES.equals(idiomaNavegacion))tituloTesauro = vocabNameTesauro + SPACE + literalTituloTesauro;
    			else tituloTesauro = literalTituloTesauro + SPACE + vocabNameTesauro;
    			form.setTituloTesauro(tituloTesauro);  
    			sesion.setTituloTesauro(tituloTesauro);
    		}
    		TaxonVO[] taxVO = null;
    		Object[] taxVORutaPadre = null;
    		String idiomaBuscador = obtenerIdiomaBusqueda();
    		form.setIdiomBusc(idiomaBuscador);
    		form.setIdioma((form.getIdioma()==null)?form.getIdiomBusc():form.getIdioma());
    		form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_BUSCADOR);
    		try {
    			//Detectamos si se ha pulsado sobre un identificador de la lista de taxones desplegada
    			if (form.getTesauroBusqueda() != null && !form.getTesauroBusqueda().equals("")){
    				if (logger.isDebugEnabled())logger.debug("ListarODETesauroControllerImpl - buscarDocumentosTesauro: Cargando taxones del identificador["+form.getTesauroBusqueda()+"] en busqueda por tesauro.");
//    				taxVO = this.getSrvTaxonomiaService().obtenerNodos(form.getTesauroBusqueda(),vocabIdentifierTes, idiomaNavegacion);
    				taxVO =this.getSrvTesaurosServices().obtenerTerminosRelacionadosPorId(form.getTesauroBusqueda(), vocabIdentifierTes, idiomaNavegacion);
    				List rutaPadrevo = Arrays.asList(this.getSrvTesaurosServices().obtenerJerarquia(form.getTesauroBusqueda(), vocabIdentifierTes, idiomaNavegacion)[0].getJerarquia());
    				logger.debug("Recuperados["+taxVO.length+"] taxones de del identificador["+form.getTesauroBusqueda()+"] en busqueda por tesauro.");
    				taxVORutaPadre = rutaPadrevo.toArray();
    			}
    			
    			//taxonomia seleccionada para buscar
        		form.setIdTesauro(vocabIdentifierTes+"$"+form.getTesauroBusqueda());
    		
//    			if (logger.isDebugEnabled() && taxVO!=null)logger.debug("Cargados["+taxVO.length+"] taxones del identificador ["+(form.getTesauroBusqueda() == null?"null":form.getTesauroBusqueda())+"] en busqueda por tesauro.");
    			form.setRutaTesauroAsArray(taxVORutaPadre);
    			//Determinamos cuales han sido los parametros de busqueda con los que se nos ha invocado.
    			//PAGINA DE RESULTADOS REQUERIDA
    			Integer pagina = 1;
    			if (form.getPagina() != null) pagina = Integer.valueOf(form.getPagina());
    			logger.debug("Busqueda con tesauro <"+form.getTesauroBusqueda()+">, idiomaBuscador <"+idiomaNavegacion+"> y paginaRequerida <"+pagina+">");
    			//PREPARAMOS LA CONSULTA DE BUSQUEDA
    			
    			ParametrosBusquedaAvanzadaVO30 param12 = new ParametrosBusquedaAvanzadaVO30();
    			//Por ahora utilizamos el locale. Cuando tengamos usuarios sera el idioma de navegacion por la aplicacion
    			param12.setIdiomaNavegacion(idiomaNavegacion);
    			param12.setIdiomaBusqueda(obtenerIdiomaBusqueda());
    			form.setIdioma((form.getIdioma()==null)?form.getIdiomBusc():form.getIdioma());

    			param12.setBusquedaSimpleAvanzada(DetallarControllerImpl.BUSQUEDA_TESAURO);
    			String maxRes = request.getSession().getServletContext().getInitParameter("max_res_totales");
    			if (maxRes == null || maxRes.equals("")) maxRes = "100";
    			param12.setNumeroResultados(Integer.valueOf(maxRes));
    			param12.setOrigenPagina(pagina);
    			param12.setTaxonomia(new String[]{vocabIdentifierTes+ "/" +form.getTesauroBusqueda()});
    			String resPag = request.getSession().getServletContext().getInitParameter("max_res_pagina");
    			if (resPag == null || resPag.equals("")) resPag = "10";
    			param12.setResultadosPorPagina(Integer.valueOf(resPag));
    			if(form.getTipoBusqueda()!=null && form.getTipoBusqueda().equals("07")){
    				param12.setComunidadesSeleccionadas(obtenerComunidades()) ;
    				param12.setComunidadPeticion(LdapUserDetailsUtils.getHost());
    			}
    			
    			param12.setIdBusqueda("");
    			//Invocamos el servicio de busqueda
    			ResultadoBusquedaVO resultadosTaxonomica;
//    			logger.debug("ListarODETesauroControllerImpl - buscarDocumentosTesauro: La invocacion a la Busqueda con parametros IdBusqueda["+param12.getIdBusqueda()
//    					+"], IdiomaBusqueda["+param12.getIdiomaBusqueda()+"], IdiomaNavegacion["+param12.getIdiomaNavegacion()
//    					+"], Tesauro["+param12.getTaxonomia()[0]+"], NumeroResultados["+param12.getNumeroResultados()
//    					+"], OrigenPagina["+param12.getOrigenPagina()
//    					+"], ResultadosPorPagina["+param12.getResultadosPorPagina()+"]");
				SrvBuscarService buscarService = this.getSrvBuscarService();
				
				Integer maxResPagina=0;
				
				if(form.getNumeroResultados()!=null && !form.getNumeroResultados().equals(""))maxResPagina=new Integer(form.getNumeroResultados());
				else maxResPagina = new Integer(request.getSession().getServletContext().getInitParameter("max_res_pagina"));
				param12.setResultadosPorPagina(maxResPagina);
//		    
				resultadosTaxonomica = buscarService.buscarAvanzado(param12);
				for (int i = 0; i < resultadosTaxonomica.getResultadoBusqueda().length; i++) {
					if (resultadosTaxonomica.getResultadoBusqueda()[i].getAmbito()[0]!=null && resultadosTaxonomica.getResultadoBusqueda()[i].getAmbito()[0].equals(BuscarAvanzadoControllerImpl.UNIVERSAL)){
						resultadosTaxonomica.getResultadoBusqueda()[i].setEsVisualizable(true);
					}else{
						boolean encontrado=false;
						for (int j=0;j<resultadosTaxonomica.getResultadoBusqueda()[i].getAmbito().length;j++){
//							if (resultadosTaxonomica.getResultadoBusqueda()[i].getAmbito()[j]!=null && resultadosTaxonomica.getResultadoBusqueda()[i].getAmbito()[j].equals(DependentServerProperties.getInstance().getProperty(BuscarAvanzadoControllerImpl.IDENTIFICADOR_NODO).toString())){
							if (resultadosTaxonomica.getResultadoBusqueda()[i].getAmbito()[j]!=null && resultadosTaxonomica.getResultadoBusqueda()[i].getAmbito()[j].equals(ObtieneSrvPropiedad().get(AgregaProperties.SERVER_ID))){
								resultadosTaxonomica.getResultadoBusqueda()[i].setEsVisualizable(true);
								encontrado=true;
							}
						}
						if (!encontrado){
							resultadosTaxonomica.getResultadoBusqueda()[i].setEsVisualizable(false);
						}
					}
				}
 				logger.debug("resultadosTaxonomica de busqueda <"+(resultadosTaxonomica.getResultadoBusqueda()!= null?resultadosTaxonomica.getResultadoBusqueda().length:0)+">, sugerencias <"+(resultadosTaxonomica.getSugerencias()!= null&&resultadosTaxonomica.getSugerencias().length>0?resultadosTaxonomica.getSugerencias()[0]+" ...":"SIN SUGERENCIAS")+">");  	
				//Rellenamos los parametros del formulario
 				ValoresBusquedaVO[] valoresBusquedaVO = traducirResultadosBusqueda(form.getPagina(), resultadosTaxonomica.getResultadoBusqueda(), request, idiomaNavegacion, maxResPagina);				
				form.setResultadosVO(Arrays.asList(valoresBusquedaVO));
		
				//Hay que traducir los valores de "tipoRecurso" por sus identificadores en lom-es y pasarselos asi al formulario
				form.setPagina(pagina.toString());
				form.setTesauroBusqueda(form.getTesauroBusqueda());
	    	
				int resultadosDesde = (pagina -1 ) * (Integer.valueOf(request.getSession().getServletContext().getInitParameter("max_res_pagina"))) + 1;
				form.setResultadosDesde(String.valueOf(resultadosDesde));
	    	
				int resultadosHasta = resultadosDesde + (Integer.valueOf(request.getSession().getServletContext().getInitParameter("max_res_pagina"))) - 1;
				resultadosHasta = (resultadosHasta > resultadosTaxonomica.getNumeroResultados()? resultadosTaxonomica.getNumeroResultados(): resultadosHasta);
				form.setResultadosHasta(String.valueOf(resultadosHasta));
				if(form.getTesauroBusqueda()!=null && !form.getTesauroBusqueda().equals("")){
					if(resultadosTaxonomica.getTotalResultados()<11){
						form.setNumMaxResultados(resultadosTaxonomica.getTotalResultados());
					}else if(form.getNumMaxResultados()==null){
						//Antes estaba TipoBusquedaArbol
						ParametrosDocsCountVO30 parametrosCount = new ParametrosDocsCountVO30(obtenerIdiomaBusqueda(),new String[]{vocabIdentifierTes+ "/" +form.getTesauroBusqueda()},idiomaNavegacion,form.getTipoBusqueda(),LdapUserDetailsUtils.getHost(),null,null);
					ResultadosNodoCountVO count = this.getSrvBuscarService().solicitarDocsCount(parametrosCount);
						form.setNumMaxResultados(count.getConteo()[count.getConteo().length-1]);
					}
				}
				//Este metodo resuelve toda la problematica del banner de paginacion sobre el formulario.
				generarPaginas(	form,
						form.getPagina(),
						resultadosTaxonomica.getNumeroResultados(),
	    						maxResPagina,
	    						maxResPagina);
			}catch (Exception ex){
    			if(taxVO==null){
    				form.setResultadosVO(Arrays.asList(valoresBusquedaVOTax));
	    				form.setRutaTesauro(new ArrayList());
    				form.setPaginas(new ArrayList());
    				logger.error("ERROR cargando taxonomias de <"+this.getPropertyValue("nombreFichTesauros")+"> en busqueda por tesauro - ", ex);
    			}else{
    				logger.error("ERROR en la invocacion a la Busqueda - ",ex);
    			}
    			saveErrorMessage(request, MENSAJE_GENERICO_BUSQUEDA);
    		}
    	}catch (Exception e){
    		form.setResultadosVO(Arrays.asList(valoresBusquedaVOTax));
			form.setRutaTesauro(new ArrayList());
			form.setPaginas(new ArrayList());
    		logger.error("ERROR: error al buscar los documentos.",e);
    	}
		
		
		
	}
	
	private void busquedaArbol(EjecutarBusquedaAvanzadaForm form,HttpServletRequest request, HttpServletResponse response)
	{
		String textoBusqueda = "";
		ValoresBusquedaVO[] valoresBusquedaVOTax=null;
		try{
    		BuscarSession sesion = this.getBuscarSession(request);
    		if (sesion.getNodoOrigen()!=null && !sesion.getNodoOrigen().equals("")){
    			form.setAreaCurricularBusqueda(sesion.getAreaCurricularBusqueda());
    			form.setPagina(sesion.getPagina()!=null?sesion.getPagina().toString():"1");
    			form.setTipoBusqueda(sesion.getTipoBusqueda());
    			//form.setTipoBusquedaArbol(sesion.getTipoBusquedaArbol());
    			form.setTipoNavegacion(sesion.getTipoNavegacion());//cuando volvemos del detalle del ODE
    			this.setBuscarSession(request,new BuscarSession());
    		}
    		if (form.getPagina() == null || form.getPagina().equals("")) form.setPagina("1");
			
    		TaxonVO[] taxVO = null;
    		Object[] taxVORutaPadre = null;
    		String idiomaNavegacion = ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
    		String idiomaBuscador = obtenerIdiomaBusqueda();
    		form.setIdiomBusc(idiomaBuscador);
    		form.setIdioma((form.getIdioma()==null)?form.getIdiomBusc():form.getIdioma());
    		form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_BUSCADOR);
    		
    		//taxonomia seleccionada para buscar
    		String taxonomia ="";
    		
    		//Busqueda Taxonomica.Se ha seleccionado el Tipo de Navegacion
    		if(form.getTipoNavegacion()!=null && !form.getTipoNavegacion().equalsIgnoreCase(""))
    		{
    			taxonomia = form.getTipoNavegacion();
    		}
    		//Desde RSS generado
    		else
    		{
    			//Va a almacenar el Arbol Curricular y el código del arbol seleccionado previamente a realizar el RSS
    			
    			String[] acurricular = new String[2];
    			acurricular=form.getEnlaceTaxSelec().replace('$', '/').split("/");
    			
    			//Recogemos el arbol curricular seleccionado previamente a la ejecucion de RSS/ATOM y rellenamos los form's necesarios para
    			//posteriormente poder realizar correctamente(aparte de la vuelta a la pantalla de busqueda),la paginación
    			taxonomia=acurricular[0];
    			form.setTipoNavegacion(acurricular[0]);
    			form.setAreaCurricularBusqueda(acurricular[1]);
    		}
  
    		
    		
    		try {
    			//Detectamos si se ha pulsado sobre un identificador de la lista de taxones desplegada
    			if (form.getAreaCurricularBusqueda() != null && !form.getAreaCurricularBusqueda().equals("")){
    				taxVO = this.getSrvTaxonomiaService().obtenerNodos(form.getAreaCurricularBusqueda(),taxonomia, idiomaNavegacion);
    				List rutaPadrevo = Arrays.asList(this.getSrvTaxonomiaService().obtenerTaxonPath(form.getAreaCurricularBusqueda(), taxonomia, idiomaNavegacion));
    				Collections.reverse(rutaPadrevo);
    				taxVORutaPadre = rutaPadrevo.toArray();
    				
        			//Nombre de taxonomia (TITULO)
        			String nombreTaxonomia="";
        			if(taxVORutaPadre!=null && taxVORutaPadre.length>0){
        				nombreTaxonomia = ((TaxonVO)taxVORutaPadre[0]).getVocabName();
        				form.setTaxNombre(nombreTaxonomia);
        				
        			}
    			}
    			int buscarEn=taxVORutaPadre.length;
    			String area = sortAreaCurricularDescendente(((TaxonVO)taxVORutaPadre[buscarEn-1]).getId().split("/"),((TaxonVO)taxVORutaPadre[buscarEn-1]).getId().split("/").length);
    			form.setEnlaceTaxSelec(taxonomia+"$"+area);
    			
    	    			
    			if(taxVO!=null && taxVO.length>0)form.setEsHoja(false);
    			else form.setEsHoja(true);
    			if (logger.isDebugEnabled() && taxVO!=null)
    				logger.debug("buscarDocumentosArea: Cargados["+taxVO.length+"] taxones del identificador ["+(form.getAreaCurricularBusqueda() == null?"null":form.getAreaCurricularBusqueda())+"] en busqueda por arbol curricular.");
    			form.setRutaArbolAsArray(taxVORutaPadre);
    			//Determinamos cuales han sido los parametros de busqueda con los que se nos ha invocado.
    			//PAGINA DE RESULTADOS REQUERIDA
    			Integer pagina = new Integer(1);
    			if (form.getPagina() != null) pagina = new Integer(form.getPagina());

    			//PREPARAMOS LA CONSULTA DE BUSQUEDA
    			
    			ParametrosBusquedaAvanzadaVO30 param12 = new ParametrosBusquedaAvanzadaVO30();
    			//Por ahora utilizamos el locale. Cuando tengamos usuarios sera el idioma de navegacion por la aplicacion
    			param12.setIdiomaNavegacion(idiomaNavegacion);
    			param12.setIdiomaBusqueda(idiomaBuscador);
    			param12.setBusquedaSimpleAvanzada(DetallarControllerImpl.BUSQUEDA_ARBOL);
    			String maxRes = request.getSession().getServletContext().getInitParameter("max_res_totales");
    			if (maxRes == null || maxRes.equals("")) maxRes = "100";
    			param12.setNumeroResultados(Integer.valueOf(maxRes));
    			param12.setOrigenPagina(pagina);
//    			param.setTaxonomia(new String[]{this.getSrvTaxonomiaService().obtenerVocabName(this.getPropertyValue("nombreAreaCurricularTax"), idiomaNavegacion)+"/"+form.getAreaCurricularBusqueda()});
    			param12.setTaxonomia(new String[]{taxonomia + "/" + form.getAreaCurricularBusqueda()});
    			String resPag = request.getSession().getServletContext().getInitParameter("max_res_pagina");
    			if (resPag == null || resPag.equals("")) resPag = "10";
    			param12.setResultadosPorPagina(Integer.valueOf(resPag));
    			if(form.getTipoBusqueda()!=null && form.getTipoBusqueda().equals("05")){
    				param12.setComunidadesSeleccionadas(obtenerComunidadesIncluidoNodoLocal()) ;
    				param12.setComunidadPeticion(LdapUserDetailsUtils.getHost());
    			}
    			param12.setIdBusqueda("");
    			//Invocamos el servicio de busqueda
    			
    			param12.setNumeroResultados(Integer.valueOf(request.getSession().getServletContext().getInitParameter("max_res_totales")));
				Integer maxResPagina = 0;
				if(form.getNumeroResultados()!=null && !form.getNumeroResultados().equals(""))maxResPagina=Integer.valueOf(form.getNumeroResultados());
				else maxResPagina = Integer.valueOf(request.getSession().getServletContext().getInitParameter("max_res_pagina"));
				param12.setResultadosPorPagina(maxResPagina);
//		    
    			ResultadoBusquedaVO resultadosTaxonomica;
    		/*	log("ListarODEAreaControllerImpl - buscarDocumentosArea: La invocacion a la Busqueda con parametros IdBusqueda["+param.getIdBusqueda()
    					+"], IdiomaBusqueda["+param.getIdiomaBusqueda()+"], IdiomaNavegacion["+param.getIdiomaNavegacion()
    					+"], areaCurricular["+param.getTaxonomia()[0]+"], NumeroResultados["+param.getNumeroResultados()
    					+"], OrigenPagina["+param.getOrigenPagina()
    					+"], ResultadosPorPagina["+param.getResultadosPorPagina()+"]");*/
				SrvBuscarService buscarService = this.getSrvBuscarService();
				resultadosTaxonomica = buscarService.buscarAvanzado(param12);
				//Hay que traducir los valores de "tipoRecurso" por sus identificadores en lom-es y pasarselos asi al formulario
				ValoresBusquedaVO[] valoresBusquedaVO = traducirResultadosBusqueda(form.getPagina(), resultadosTaxonomica.getResultadoBusqueda(), request, idiomaNavegacion, maxResPagina);				
				form.setResultadosVO(Arrays.asList(valoresBusquedaVO));
				form.setBuscContenido(textoBusqueda);
				int resultadosDesde = (Integer.parseInt(form.getPagina()) -1 ) * maxResPagina + 1;
				form.setResultadosDesde(String.valueOf(resultadosDesde));

				int resultadosHasta = resultadosDesde + maxResPagina - 1;
				resultadosHasta = (resultadosHasta > resultadosTaxonomica.getNumeroResultados().intValue()? resultadosTaxonomica.getNumeroResultados().intValue(): resultadosHasta);
				form.setResultadosHasta(String.valueOf(resultadosHasta));
				if (form.getAreaCurricularBusqueda() != null && !form.getAreaCurricularBusqueda().equals("")){
					if(resultadosTaxonomica.getTotalResultados()<11){
						form.setNumMaxResultados(resultadosTaxonomica.getTotalResultados());
					}else if(form.getNumMaxResultados()==null){
						ParametrosDocsCountVO30 parametrosCount = new ParametrosDocsCountVO30(obtenerIdiomaBusqueda(),new String[]{taxonomia+"/"+form.getAreaCurricularBusqueda()},idiomaNavegacion,form.getTipoBusqueda(),LdapUserDetailsUtils.getHost(),null,null);
						ResultadosNodoCountVO count = this.getSrvBuscarService().solicitarDocsCount(parametrosCount);
						form.setNumMaxResultados(count.getConteo()[count.getConteo().length-1]);
					}
				}
				form.setQuisoDecir(resultadosTaxonomica.getSugerencias());
				form.setTesauros(resultadosTaxonomica.getTesauros());
				if(valoresBusquedaVO.length>0 && form.getFormato()!=null)
				{
					form.setFormatoURL(form.getFormato().replace('/','$'));
				}
				try{
					NodoSQIVO[] nodos = this.getSrvGestionSqiService().obtenerNodosSQI();
					if (nodos!=null && nodos.length>0){
						form.setNodosSQI(true);
						logger.debug("Existen <" + nodos.length + "> nodos SQI indexados en la BBDD");
					}else{
						form.setNodosSQI(false);
						logger.debug("No existe ningún nodo SQI indexado en la BBDD");
					}
					
				}catch (Exception e){
					logger.error("Error al intentar recuperar el numero de nodos SQI indexados en la tabla",e);
					form.setNodosSQI(false);
				}
				generarPaginas(	form,
						form.getPagina(),
						resultadosTaxonomica.getNumeroResultados(),
	    						maxResPagina,
	    						maxResPagina);
		
    		}catch (Exception ex){
    			if(taxVO==null){
    				form.setResultadosVO(Arrays.asList(valoresBusquedaVOTax));
    				
    				form.setRutaArbol(new ArrayList());
    				form.setPaginas(new ArrayList());
    				logger.error("ERROR: cargando taxonomias de ["+taxonomia+"] en busqueda por arbol curricular.", ex);
    			}else{
    				logger.error("ERROR: en la invocacion a la Busqueda.",ex);
    			}
    			saveErrorMessage(request, MENSAJE_GENERICO_BUSQUEDA);
    		}
    	}catch (Exception e){
			form.setResultadosVO(Arrays.asList(valoresBusquedaVOTax));
			form.setRutaArbol(new ArrayList());
			form.setPaginas(new ArrayList());
    		logger.error("ERROR: al buscar los documentos.",e);
    	}

	}
	
	public String analizaValidacion(ActionMapping mapping, AnalizaValidacionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(form.getValidado().equals(ACCION_FORMULARIO_VALIDO))return ACCION_FORMULARIO_VALIDO;
		else if(form.getValidado().equals(ACCION_FORMULARIO_INVALIDO_AVANZADO)) return ACCION_FORMULARIO_INVALIDO_AVANZADO;
		return ACCION_FORMULARIO_INVALIDO;
	}
	
	public String analizaResultados(ActionMapping mapping, AnalizaResultadosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		if(form.getResultadosVO()!=null && form.getResultadosVO().size() > 0) return ACCION_CON_RESULTADOS;
		else if((form.getBusqSimpleAvanz()!=null && form.getBusqSimpleAvanz().equals(DetallarControllerImpl.BUSCAR_SIMPLE)) || (form.getQuisoDecir()!=null && form.getQuisoDecir().length > 0) || (form.getTesauros()!=null && form.getTesauros().length > 0)) return ACCION_CON_SUGERENCIAS;
		form.setSinResult(true);
		logger.debug("analizaResultados: Sin resultados ni sugerencias.");
		return ACCION_SIN_RESULTADOS;
	}
	
	public String analizaSeleccionEliminacion(ActionMapping mapping, AnalizaSeleccionEliminacionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return form.getEliminados();
	}
	
	public void guardarBusquedaEnSesion(ActionMapping mapping, GuardarBusquedaEnSesionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			BuscarSession sesion = inicializarSesion(request);
			if(form.getTipoLayoutBuscador()!=null && form.getTipoLayoutBuscador().equals(DetallarControllerImpl.LAYOUT_EMPAQUETADOR))logger.debug("BuscarAvanzadoControllerImpl - agregarOdes: Se agrega ode federado al empaquetador con  IdOde:" +sesion.getEmpIdOde()+" IdDestino:"+sesion.getEmpIdDestino()+" TipoEmpaquetador:"+ sesion.getEmpTipoEmpaquetador());
			if (sesion == null) this.setBuscarSession(request, new BuscarSession());
//			Propiedades del contenido
			sesion.setBuscadorContenido(form.getBuscContenido());		
			sesion.setIdioma(form.getIdioma());
			sesion.setPagina((form.getPagina()!=null && !form.getPagina().trim().equals(""))?new Integer(form.getPagina()):null);
			sesion.setFormato(form.getFormato());
			sesion.setRecurso(form.getRecurso());
			sesion.setProcesoCognitivo(form.getProcesoCognitivo());
			sesion.setContexto(form.getContexto());
			sesion.setEdad(form.getEdad());
			sesion.setIdentificadorODE(form.getIdentificadorODE());
			sesion.setAutor(form.getAutor());
			sesion.setDiaPublicacion(form.getDiaPublic());
			sesion.setMesPublicacion(form.getMesPublic());
			sesion.setAnyoPublicacion(form.getAnyoPublic());
			sesion.setSecuencia(form.getC_s_secuencia());
			sesion.setKeyword(form.getKeyword());
			sesion.setValoracion(form.getValoracion());
			sesion.setNivelAgregacion(form.getNivelAgreg());
			sesion.setDestinatarios(form.getDestinatarios());
			sesion.setTipoVisualizacion(form.getTipoVisualiz());
			sesion.setTipoBusqueda(form.getTipoBusqueda());
			sesion.setEnlaceTaxSelec(form.getEnlaceTaxSelec());
			sesion.setIdODE(form.getIdODE());

		
			String enlace = generarEnlaceComunidades(form.getComuSelec());
			sesion.setEnlaceComunidadesSeleccionadas(enlace);
			//cuando se eliminan ODEs ComunidadesSeleccionadas esta vacio por eso cogemos las posibles
			//comunidades seleccionadas del enlaceComunidadesSeleccionadas
			if ((enlace ==null || enlace.equals(""))&& form.getEnlaceComuSelec()!=null && !form.getEnlaceComuSelec().equals("")) sesion.setEnlaceComunidadesSeleccionadas(form.getEnlaceComuSelec());
						
			sesion.setTipoLayoutBuscador(form.getTipoLayoutBuscador());
			sesion.setIdTesauro(form.getIdTesauro());
			sesion.setTesauroBusqueda(form.getIdTesauroSug());
			sesion.setNomTesauros(form.getNomTesauros());
			sesion.setNumeroResultados(form.getNumeroResultados());
//			Si venimos desde la selección del detalle de un ode en el listado, guardamos el origen para una posible vuelta del listado
			if((form.getListaIds()!=null && !form.getListaIds().equals("")) || (form.getIdentificadorODE()!= null && !form.getIdentificadorODE().trim().equals("")))sesion.setBusquedaSimpleAvanzada(DetallarControllerImpl.BUSCAR);
		}catch (Exception ex){
    		logger.error("ERROR: Error al guardar la búsqueda en sesion. - ",ex);
		}
	}
	
	public void listarIds(ActionMapping mapping, ListarIdsForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			if (LdapUserDetailsUtils.esAdministrador() || LdapUserDetailsUtils.esPublicador()){		
				
				Object[] identificadoresODE=form.getIdRowSelectionAsArray();
				if(identificadoresODE!=null && identificadoresODE.length>0)
				{
					form.setEliminados("CON_RESULTADOS");
					logger.debug("eliminar <" + identificadoresODE.length + "> ODE como Administrador");
					
					if (identificadoresODE.length>1)
					{
						form.setListaIds(identificadoresODE [0].toString()+ANDPERSAND);
						for (int k=1;k<identificadoresODE.length;k++) 
						{
							form.setListaIds(form.getListaIds()+(String) identificadoresODE [k]+ANDPERSAND);
						}
					}
					else if(identificadoresODE.length==1)
					{
						form.setListaIds(identificadoresODE [0].toString()+ANDPERSAND);
					}
					
//					ParametrosBusquedaAvanzadaVO30 param=this.recuperarParametrosAvanzada(form, ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage());
//					form.setIdHits(this.getSrvBuscarService().generadorIdentificadorAvanzado(param));
					try{
						form.setNumeroResultadosLabelList(cargarNumeroResultadosLabel(new Locale(form.getIdioma())));
						form.setNumeroResultadosValueList(cargarNumeroResultadosValue());
					}catch(Exception ex){
						form.setFormato(""); 
						form.setNumeroResultadosLabelList(new String[0]); 
						form.setNumeroResultadosValueList(new String[0]);
						saveErrorMessage(request,MENSAJE_GENERICO_BUSQUEDA);
						logger.error("listarIds: error al cargar num. resultados. - "+ex);
					}
				}
				else
				{
					logger.warn("No ha seleccionado ODEs");
					form.setEliminados("SIN_RESULTADOS");
					saveErrorMessage(request, ERROR_ELIMINANDO_ODES);
				}
			}else{
				logger.warn("No tiene permisos para eliminar ODEs");
				saveErrorMessage(request, BuscarAvanzadoControllerImpl.MENSAJE_USUARIO_NO_LOGADO);
			}
		}catch(Exception e){
			logger.error("Error al intentar eliminar los ODEs seleccionados",e);
			saveErrorMessage(request, BuscarAvanzadoControllerImpl.MENSAJE_ERROR_ELIMINAR_ODE);
		}
	}
	
	//TODO Duplicado de SrvBuscarServiceImpl, pensar si merece la pena agrupar o mover fuera del servicio
	private String removeAccents(String s, String encoding){
		try {
			if (logger.isDebugEnabled())logger.debug("DocumentoIndexacion - removeAccents: Eliminar acentos para="+s);

			s=URLDecoder.decode(s,encoding); //<-- Esto no estaba en el original
			logger.debug("PalabrasClave después de urldecoder: "+s);
			s = s.replaceAll( "[ÂÀÄÁÃ]", "A" );
			s = s.replaceAll( "[ÊÈËÉ]", "E" );
			s = s.replaceAll( "[ÎÌÏÍ]", "I" );
			s = s.replaceAll( "[ÔÒÖÓÕ]", "O" );
			s = s.replaceAll( "[ÛÙÜÚ]", "U" );
			s = s.replaceAll( "Ç", "C" );
			s = s.replaceAll( "[àâäá]", "a" );
			s = s.replaceAll( "[éèêë]", "e" );
			s = s.replaceAll( "[ïîìí]", "i" );
			s = s.replaceAll( "[ôöòóõ]", "o" );
			s = s.replaceAll( "[ûüùú]", "u" ); 
			s = s.replaceAll( "ç", "c" );
			s = s.replaceAll( "‘", "'");
			if (logger.isDebugEnabled())logger.debug("DocumentoIndexacion - removeAccents: Acentos eliminados="+s);
			return s;
		} catch ( Exception ex ) {
			logger.error("DocumentoIndexacion - removeAccents: Error generando claves con Normalizer, para cadena="+s, ex);
			return "";
		}
	}
	
	@Override
	public void generarRSS(ActionMapping mapping, GenerarRSSForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String data = null;
		try{
			ParametrosBusquedaAgregadorVO param = new ParametrosBusquedaAgregadorVO();
			param.setBusquedaSimpleAvanzada(DetallarControllerImpl.BUSCARRSS);
			param.setIdiomaNavegacion(((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage());
			param.setIdiomaBusqueda(form.getIdioma());
			param.setOrigenPagina(1);

			// 25092012
			// Reemplazamos la ñ porque viene con caracteres extraños por el paso de parámetros por la redirección de apache
			form.setBuscContenido(form.getBuscContenido().replace("ã±", "ñ"));
			form.setBuscContenido(form.getBuscContenido().replace("Ã±", "Ñ"));
			form.setBuscContenido(form.getBuscContenido().replace("ã‘", "ñ"));
			form.setBuscContenido(form.getBuscContenido().replace("Ã‘", "Ñ"));
			form.setBuscContenido(form.getBuscContenido().replace("Ã", "Ñ"));
			form.setBuscContenido(form.getBuscContenido().replace("ã", "ñ"));
			form.setBuscContenido(form.getBuscContenido().replace("~Q", ""));
			param.setPalabrasClave(eliminarEspaciosBlanco(form.getBuscContenido()).replaceAll("&quot;", DetallarControllerImpl.JAVA_QUOTES).replaceAll(HTML_PLUS, JAVA_PLUS).replaceAll(DetallarControllerImpl.DOT, DetallarControllerImpl.SEPARADOR_PALABRAS).replaceAll(DetallarControllerImpl.DOUBLE_DOT, DetallarControllerImpl.SEPARADOR_PALABRAS).replaceAll(DetallarControllerImpl.OPEN_QUESTIONMARK, DetallarControllerImpl.SEPARADOR_PALABRAS).replaceAll(DetallarControllerImpl.CLOSE_QUESTIONMARK, DetallarControllerImpl.SEPARADOR_PALABRAS).replaceAll(DetallarControllerImpl.OPEN_PARENTHESES, DetallarControllerImpl.SEPARADOR_PALABRAS).replaceAll(DetallarControllerImpl.CLOSE_PARENTHESES, DetallarControllerImpl.SEPARADOR_PALABRAS).replaceAll(DetallarControllerImpl.OPEN_EXCLAMATIONMARK, DetallarControllerImpl.SEPARADOR_PALABRAS).replaceAll(DetallarControllerImpl.CLOSE_EXCLAMATIONMARK, DetallarControllerImpl.SEPARADOR_PALABRAS).replaceAll(DetallarControllerImpl.COMA, DetallarControllerImpl.SEPARADOR_PALABRAS).replaceAll(DetallarControllerImpl.SLASH, DetallarControllerImpl.SEPARADOR_PALABRAS));
			
			if(form.getFormatoURL()!=null)
			{
				//Estas dos lineas estaban aqui desde tiempos inmemoriables y resulta que no compilan :S				
//				String[] formato=form.getFormatoURL().replace('$', '/').split("-");
//				param.setFormato(formato);
					param.setFormato(form.getFormatoURL().replace('$', '/'));
			}
			param.setKeyword(form.getKeyword());
			param.setTipoBusqueda(form.getTipoBusqueda());
			param.setDestinatarios(form.getDestinatarios());
			param.setRecurso(form.getRecurso());
			param.setProcesoCognitivo(form.getProcesoCognitivo());
			param.setFechaPublicacion(construirFecha(form.getAnyoPublic(),form.getMesPublic(),form.getDiaPublic()));
			param.setContexto(form.getContexto());
			
			if(form.getNivelAgreg()!=null)
			{
				//Estas dos lineas estaban aqui desde tiempos inmemoriables y resulta que no compilan :S
//				String[] nivelAgreg=form.getNivelAgreg().split("-");
//				param.setNivelAgregacion(nivelAgreg);
				param.setNivelAgregacion(form.getNivelAgreg());
			}
			param.setEdad(form.getEdad());

			param.setAutor(form.getAutor());
			param.setComunidadPeticion(LdapUserDetailsUtils.getHost());
			param.setSecuencia(form.getC_s_secuencia()!=null && !form.getC_s_secuencia().equals("")?new Boolean(form.getC_s_secuencia()):null);
			param.setValoracion(form.getValoracion());
					if(form.getIdODE()!=null && !form.getIdODE().equalsIgnoreCase(""))
			param.setIdODE(form.getIdODE());
			else
				param.setIdODE("");	
					
			String [] taxTesSelec=null;
			
			if(form.getTipoBusqueda().equalsIgnoreCase("07")||form.getTipoBusqueda().equalsIgnoreCase("08"))
			{
			//Va a almacenar el IdTesauro y el código del tesauro seleccionado (en circunstancias normales se recoge el tesauro vigente)
			String[] tesauro = new String[2];
			taxTesSelec=new String[1];
			//Esto se le pasa para que genere la URL de  con el Tesauro Seleccionado en el caso de ser Busqueda Taxonomica por Tesauro
			param.setIdTesauro(form.getIdTesauro());
			tesauro=form.getIdTesauro().replace('$', '/').split("/");
			form.setIdTesauro(tesauro[1]);
			taxTesSelec[0] = tesauro[0] + "/" + form.getIdTesauro();
			}
			else
			{
			taxTesSelec = this.obtenerTaxYTes(form.getEnlaceTaxSelec(), form.getIdTesauro(), form.getIdTesauroSug());
			param.setIdTesauro((form.getIdTesauroSug()!=null && !form.getIdTesauroSug().trim().equals(""))?form.getIdTesauroSug():form.getIdTesauro());
			}
			param.setTaxonomia(taxTesSelec);
			param.setEnlaceTaxSelec(form.getEnlaceTaxSelec());
			
			//si búsqueda federada entonces se selecciona TODAS
			if(form.getTipoBusqueda()!=null && form.getTipoBusqueda().equals("01"))
			{
				param.setComunidadesSeleccionadas(obtenerComunidadesIncluidoNodoLocal());
			}
			else
			{
				if(form.getEnlaceComuSelec()!=null && !form.getEnlaceComuSelec().equals(""))
				param.setComunidadesSeleccionadas((form.getEnlaceComuSelec().split("-")[0]!=null && form.getEnlaceComuSelec().split("-")[0].equals("0"))?obtenerComunidades():form.getEnlaceComuSelec().split("-"));
			}
			param.setEnlaceComunidadesSeleccionadas(form.getEnlaceComuSelec());
			String maxRes = request.getSession().getServletContext().getInitParameter("max_res_totales");
			if (maxRes == null || maxRes.equals("")) maxRes = "1000";
			param.setNumeroResultados(new Integer(maxRes));
			param.setResultadosPorPagina(new Integer(20));
	
	//    	Invocamos el servicio de busqueda
			
			data=this.getSrvAgregadorRssService().devuelveRssBusqueda(param,form.getTipoRSS(),form.getFeedNum());
			 response.setContentType("text/xml");
	    		PrintWriter writer = response.getWriter();  		
	    		writer.write(data);
	    		writer.close();
			form.setEstadoRSS("RSS_OK");
		}catch(Exception e){
			logger.error("Error al intentar generar el Rss de la busqueda realizada - ",e);
			if(data==null) form.setEstadoRSS("RSS_KO");
			if(form.getTipoRSS().equals("ATOM"))
				saveErrorMessage(request, BuscarAvanzadoControllerImpl.MENSAJE_ERROR_GENERAR_ATOM);
			else
				saveErrorMessage(request, BuscarAvanzadoControllerImpl.MENSAJE_ERROR_GENERAR_RSS);
		}
	}
	
	@Override
	public String analizaEstadoRSS(ActionMapping mapping, AnalizaEstadoRSSForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return form.getEstadoRSS();
	}
	
	@Override
	public String analizaTipoBusqueda(ActionMapping mapping, AnalizaTipoBusquedaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(form.getTipoBusqueda()!=null && form.getTipoBusqueda().equals("04")) return ACCION_SQI;
		return "AGREGA";
	}
	
	private boolean eliminarPosicion(int i,String[] posiciones){
		if(posiciones!=null){
			for(int j=0;j < posiciones.length; j++){
				if(Integer.parseInt(posiciones[j])==i)return true;
			}
		}
		return false;
	}
	
	private String traducirNivelAgregacion(Integer nivelAgregacion,Locale locale) throws IOException{
		ResourceBundle datosResources = ResourceBundle.getBundle("application-resources",locale);
		String nivelAgregacionLabel = datosResources.getString(this.getPropertyValue("avanzado.agregacion."+nivelAgregacion.toString()));
		return nivelAgregacionLabel;
	}

	private void validarPersonalizada(ValidarFormularioForm form) throws Exception{
		if ((form.getAnyoPublic()==null || form.getAnyoPublic().equals(""))&& (form.getEnlaceTaxSelec()==null || form.getEnlaceTaxSelec().equals(""))&&
				(form.getAutor()==null || form.getAutor().equals(""))&& (form.getC_s_secuencia()==null || form.getC_s_secuencia().equals(""))&&		
				(form.getContexto()==null || form.getContexto().equals(""))&& (form.getDescrip()==null || form.getDescrip().equals(""))&& 
				(form.getDestinatarios()==null || form.getDestinatarios().equals(""))&& (form.getDiaPublic()==null || form.getDiaPublic().equals(""))&&
				(form.getEdad()==null || form.getEdad().equals(""))&&	(form.getFormato()==null || form.getFormato().equals(""))&&(form.getKeyword()==null || form.getKeyword().equals(""))&& 
				(form.getIdTesauro()==null || form.getIdTesauro().equals(""))&& (form.getMesPublic()==null || form.getMesPublic().equals(""))&& 
				(form.getNivelAgreg()==null || form.getNivelAgreg().equals(""))&& (form.getNomTesauros()==null || form.getNomTesauros().equals(""))&& 
				(form.getProcesoCognitivo()==null || form.getProcesoCognitivo().equals(""))&& (form.getRecurso()==null || form.getRecurso().equals(""))&& 
				(form.getTitulo()==null || form.getTitulo().equals(""))&&(form.getValoracion()==null || form.getValoracion().equals(""))&&
				(form.getIdODE()==null || form.getIdODE().equals(""))){
				String[] comunidades=obtenerComunidades();
//				if ((form.getComuSelec()==null || form.getComuSelec().length==0) && (form.getEnlaceComuSelec()==null || form.getEnlaceComuSelec().equals("")) && (form.getEnlaceTodoAgrega()==null || form.getEnlaceTodoAgrega().equals(""))){
				if ((form.getComuSelec()==null || form.getComuSelec().length==0) && (form.getEnlaceComuSelec()==null || form.getEnlaceComuSelec().equals(""))){
					form.setTipoBusqueda("02");
				}
		}else{
			form.setTipoBusqueda("03");
		}
		
	}
	
	private String generarEnlaceComunidades(String[] comunidadesSeleccionadas){
		String enlaceComunidades="";
		if(comunidadesSeleccionadas != null && comunidadesSeleccionadas.length>0) {
			for(int i=0; i<comunidadesSeleccionadas.length;i++){
				if(enlaceComunidades!="") enlaceComunidades=enlaceComunidades+"-"+comunidadesSeleccionadas[i];
				else enlaceComunidades=comunidadesSeleccionadas[i];
			}
		}
		return enlaceComunidades;
	}
	
	private TaxonVO[] tranformarEnlaceTaxSelecATaxonVO(String enlaceTaxSelec,EstructuraVdexVO[] taxonomias, String idioma) throws Exception{
		String[] rutasTax = enlaceTaxSelec.split(BuscarAvanzadoControllerImpl.TAX_ASTERISCO);
		TaxonVO[] resultado = new TaxonVO[rutasTax.length];
		for(int i = 0; i < rutasTax.length; i ++){
			TaxonVO ruta = new TaxonVO();
			String[] partesRuta = rutasTax[i].split("\\$");
			String vocabIdentifier = partesRuta[0];
			String ultTaxon = partesRuta[1];
			
			ruta.setId(ultTaxon);
			//obtenemos el vocabName de la taxonomia a partir del vocabIdentifier
			String vocabName = "";
			boolean enc = false;
			for(int j = 0;!enc && j< taxonomias.length;j++){
				if(taxonomias[j].getVocabIdentifier().equals(vocabIdentifier)){
					vocabName = taxonomias[j].getVocabName();
					enc = true;
				}
			}
			ruta.setVocabName(vocabName);
			//obtenemos la ruta a partir del ultimo taxon de dicha ruta
			String sRuta = this.convertirStringRutaTax(ultTaxon,vocabIdentifier, idioma);//sustituir por vocabIdentifier "Arbol_Curricular_Agrega"
			ruta.setValorTax(sRuta);
			resultado[i]= ruta;
		}
		return resultado;
		
	}
	
	private boolean prepararFormulario(EjecutarBusquedaAvanzadaForm form){
//		log("BuscarAvanzadoControllerImpl - prepararFormulario");
		if(form.getMesPublic()!=null) form.setMesPublic((!form.getMesPublic().trim().equals("") && Integer.parseInt(form.getMesPublic().trim())<10 && Integer.parseInt(form.getMesPublic().trim())>0)?"0"+Integer.parseInt(form.getMesPublic().trim()):form.getMesPublic().trim());
    	if(form.getDiaPublic()!=null) form.setDiaPublic((!form.getDiaPublic().trim().equals("") && Integer.parseInt(form.getDiaPublic().trim())<10 && Integer.parseInt(form.getDiaPublic().trim())>0)?"0"+Integer.parseInt(form.getDiaPublic().trim()):form.getDiaPublic().trim());
    	if(form.getAnyoPublic()!=null) form.setAnyoPublic(form.getAnyoPublic().trim());
//    	log("BuscarAvanzadoControllerImpl - prepararFormulario FIN");
    	return true;
	}
	
    //Metodo que transforma un array de TerminoVO en un array de string; donde el valor del string será el valor del nom de cada TerminoVO
    private String[] transformarTerminoVOaString(TerminoVO[] termino, Locale locale){  
//    	Chequeamos que la informacion que nos pasan sea correcta.
    	if (termino != null && termino.length >0 ){
    		String[] var=new String[termino.length];
    		List varList=new ArrayList();
//  		Recorremos el vector de TerminoVO y vamos realizando los cambios
    		for(int i=0;i<var.length ; i++){
    			var[i]=termino[i].getNomTermino();
    			varList.add(var[i]);
    		}
    		 Collections.sort(varList);
    		 for(int i=0;i<varList.size() ; i++)	
     		 {
    			 var[i]=(String) varList.get(i);
     		 }
    		return var;
    	}
    	return new String[]{""};
    }
    
    
    private String[] transformarTerminoValueVOaString(TerminoVO[] terminoValue , TerminoVO[] termino, Object[] terminoOrdenado){   	
//    	Chequeamos que la informacion que nos pasan sea correcta.
    	if (terminoValue != null && terminoValue.length >0 ){
    		String[] var=new String[terminoOrdenado.length];
    		var[0]="";
//  		Recorremos el vector de TerminoVO y vamos realizando los cambios
    		for(int i=0;i<termino.length ; i++){
    			for(int j = 0; j < terminoOrdenado.length ; j++){
    				if(termino[i].getNomTermino().equals(terminoOrdenado[j].toString())){
    					var[j]=terminoValue[i].getNomTermino();
    				}
    			}
    		}
    		logger.debug("Obtenemos labels <"+Arrays.toString(terminoOrdenado)+"> para values <"+Arrays.toString(var)+">");
    		return var;
    	}
    	return new String[]{""};
    }
	//  Este metodo recibe el identificador del nivel educativo que se ha elegido y 
	//  extrae toda la ruta hasta el padre y la convierte a string con el texto ordenado
	//  desde el padre hasta el hijo traducido al idioma que se le diga.
//	  private String convertirStringAreaCurricular(String areaCurricular, String idioma) throws Exception{
//		  List rutaPadrevo = Arrays.asList(this.getSrvTaxonomiaService().obtenerTaxonPath(areaCurricular, this.getPropertyValue("nombreAreaCurricularTax"), idioma));
//		  Collections.reverse(rutaPadrevo);
//		  TaxonVO[] taxVORutaPadre = (TaxonVO[])rutaPadrevo.toArray();
//		  String rutaPadre[] = new String[taxVORutaPadre.length];
//		  for (int i = 0; i < taxVORutaPadre.length; i++) rutaPadre[i]= taxVORutaPadre[i].getValorTax();
//		  return array2String(rutaPadre);
//	  }
	  
	  private String array2String(String[] array) {
			StringBuilder str = new StringBuilder("");
			if (array != null && array.length > 0) {
				for (int i = 0; i < array.length; i++) {
					str.append(array[i]);
					if ((i + 1) < array.length) str.append("/");
				}
			}
			return str.toString();
	  }
	  
	  private String[] cargarNumeroResultadosLabel(Locale locale){
		  ResourceBundle datosResources = I18n.getInstance().getResource(APPLICATION_PROPERTIES, locale);

		  String[] formatoLabel = {datosResources.getString("listar.odecu.mostrar.resultados.consulta.cabecera.numeroResultados.diez"),
		  datosResources.getString("listar.odecu.mostrar.resultados.consulta.cabecera.numeroResultados.veinte"),datosResources.getString("listar.odecu.mostrar.resultados.consulta.cabecera.numeroResultados.treinta"),
		  datosResources.getString("listar.odecu.mostrar.resultados.consulta.cabecera.numeroResultados.cuarenta"),datosResources.getString("listar.odecu.mostrar.resultados.consulta.cabecera.numeroResultados.cincuenta")};
		  return formatoLabel;
	  }
	
	  private String[] cargarNumeroResultadosValue() throws IOException{

		  String[] formatoArray = getPropertyValue("resultados.valor").split(",");
		  String[] formatoValue = {formatoArray[0], formatoArray[1], formatoArray[2], formatoArray[3], formatoArray[4]};
		  return formatoValue;
	  }
  
	  private String[] cargarFormatoLabel(Locale locale){
		  ResourceBundle datosResources = I18n.getInstance().getResource(APPLICATION_PROPERTIES, locale);

		  String[] formatoLabel = {datosResources.getString("configurar.avanzado.formato.texto.uno"),
		  datosResources.getString("configurar.avanzado.formato.texto.dos"),datosResources.getString("configurar.avanzado.formato.texto.tres"),
		  datosResources.getString("configurar.avanzado.formato.texto.cuatro"),datosResources.getString("configurar.avanzado.formato.texto.cinco")};
		  return formatoLabel;
	  }
	
	  private String[] cargarFormatoValue() throws IOException{
		  
		  String[] formatoArray = this.getPropertyValue("avanzado.formato").split(",");
		  String[] formatoValue = {formatoArray[0], formatoArray[1], formatoArray[2], formatoArray[3], formatoArray[4]};
		  return formatoValue;
	  }

	  private String[] cargarC_S_SecuenciaLabel(Locale locale){
		  ResourceBundle datosResources = I18n.getInstance().getResource(APPLICATION_PROPERTIES, locale);

		  String[] c_s_secuenciaLabel = {
		  datosResources.getString("configurar.avanzado.c_s_secuencia.texto.conSecuencia"),
		  datosResources.getString("configurar.avanzado.c_s_secuencia.texto.sinSecuencia")};
		  return c_s_secuenciaLabel;
	  }
  
	  private String[] cargarC_S_SecuenciaValue() throws IOException{

		  String[] c_s_secArray =  this.getPropertyValue("avanzado.c_s_secuencia").split(",");		 
		  String[] c_s_secuenciaValue = {c_s_secArray[0], c_s_secArray[1]};
		  return c_s_secuenciaValue;
	  }

	private String[] cargarValoracionLabel(Locale locale){
		ResourceBundle datosResources = I18n.getInstance().getResource(APPLICATION_PROPERTIES, locale);
//		logger.debug("BuscarAvanzadoControllerImpl - cargarValoracionLabel: cargando labels de valoracion localizados en["+locale.getDisplayLanguage()+"]");
		String[] valoracionLabel = {datosResources.getString("configurar.avanzado.valoracion.texto.cero"),
			datosResources.getString("configurar.avanzado.valoracion.texto.uno"),datosResources.getString("configurar.avanzado.valoracion.texto.dos"),
			datosResources.getString("configurar.avanzado.valoracion.texto.tres"),datosResources.getString("configurar.avanzado.valoracion.texto.cuatro")};
		return valoracionLabel;
	}
	
	private String[] cargarValoracionValue() throws IOException{
//		logger.debug("BuscarAvanzadoControllerImpl - cargarValoracionValue: cargando valores de valoracion");
		String[] valoracionArray = this.getPropertyValue("avanzado.valoracion").split(",");
		return valoracionArray;
	}
	
	private String[] cargarNivelAgregacionLabel(Locale locale){
		ResourceBundle datosResources = I18n.getInstance().getResource(APPLICATION_PROPERTIES, locale);
//		logger.debug("BuscarAvanzadoControllerImpl - cargarNivelAgregacionLabel: cargando labels de nivelAgregacion localizados en["+locale.getDisplayLanguage()+"]");
		String[] nivelAgregacionLabel = {datosResources.getString("configurar.avanzado.agregacion.texto.uno"),
			datosResources.getString("configurar.avanzado.agregacion.texto.dos"),datosResources.getString("configurar.avanzado.agregacion.texto.tres"),
			datosResources.getString("configurar.avanzado.agregacion.texto.cuatro")};
		return nivelAgregacionLabel;
	}
	
	private String[] cargarNivelAgregacionValue() throws IOException{
		
		String[] nivelAgregacionArray = this.getPropertyValue("avanzado.agregacion").split(",");
		return nivelAgregacionArray;
	}
	
	private String[] cargarLicenciaIdioma(String idioma) throws Exception{
		
		String[] identificadoresLicencias = new String[]{getPropertyValue("licencias")};		
		
		VocabularioVO[] licencias = this.getSrvVocabulariosControladosService().obtenerCombos(identificadoresLicencias, idioma);
		
		String[] licenciaLabel =new String[licencias[0].getTerminos().length];
		
		for (int i = 0; i < licencias[0].getTerminos().length; i++) {
			TerminoVO termino = licencias[0].getTerminos()[i];
			licenciaLabel[i]=termino.getNomTermino();		
		}
		
		return licenciaLabel;
	}	
	
	private boolean esVacioFormulario(ValidarFormularioForm form) throws Exception{
//		Ahora tenemos que comprobar que se haya seleccionado almenos un concepto entre todos los del
//		formulario para poder realizar una busqueda por algun concepto. Si no es asi, tenemos
//		que dar una excepcion. No comprobamos el idioma por	que nunca va a estar vacio, pero no vamos a permitir
//		buscar por todo el indice del idioma.
		if (!comprobarAtributoConValor(form.getAnyoPublic()) &&
			!comprobarAtributoConValor(form.getEnlaceTaxSelec()) &&
			!comprobarAtributoConValor(form.getAutor()) &&
			!comprobarAtributoConValor(form.getC_s_secuencia()) &&
			!comprobarAtributoConValor(form.getContexto()) &&
			!comprobarAtributoConValor(form.getDiaPublic()) &&
			!comprobarAtributoConValor(form.getEdad()) &&
			!comprobarAtributoConValor(form.getIdODE()) &&
			!comprobarAtributoConValor(form.getFormato()) &&
			!comprobarAtributoConValor(form.getKeyword()) &&
			!comprobarAtributoConValor(form.getMesPublic()) &&
			!comprobarAtributoConValor(form.getProcesoCognitivo()) &&
			!comprobarAtributoConValor(form.getRecurso()) &&
			!comprobarAtributoConValor(form.getIdTesauro()) &&
			!comprobarAtributoConValor(form.getIdTesauroSug()) &&
			!comprobarAtributoConValor(form.getNivelAgreg()) &&
			!comprobarAtributoConValor(form.getDestinatarios()) &&
			!comprobarAtributoConValor(form.getLicencia()) &&
			!comprobarAtributoConValor(form.getValoracion())){
			form.setContPropsContenido("0");
			return true;
		}
		return false;
	}
	
//	private boolean esVacioFormulario(CargaFormularioBusquedaAvanzadaForm form) throws Exception{
////		Ahora tenemos que comprobar que se haya seleccionado almenos un concepto entre todos los del
////		formulario para poder realizar una busqueda por algun concepto. Si no es asi, tenemos
////		que dar una excepcion. No comprobamos el idioma por	que nunca va a estar vacio, pero no vamos a permitir
////		buscar por todo el indice del idioma.
//		if (!comprobarAtributoConValor(form.getAnyoPublic()) &&
//			!comprobarAtributoConValor(form.getEnlaceTaxSelec()) &&
//			!comprobarAtributoConValor(form.getAutor()) &&
//			!comprobarAtributoConValor(form.getC_s_secuencia()) &&
//			!comprobarAtributoConValor(form.getContexto()) &&
//			!comprobarAtributoConValor(form.getDiaPublic()) &&
//			!comprobarAtributoConValor(form.getIdODE()) &&
//			!comprobarAtributoConValor(form.getEdad()) &&
//			!comprobarAtributoConValor(form.getFormato()) &&
//			!comprobarAtributoConValor(form.getMesPublic()) &&
//			!comprobarAtributoConValor(form.getProcesoCognitivo()) &&
//			!comprobarAtributoConValor(form.getRecurso()) &&
//			!comprobarAtributoConValor(form.getIdTesauro()) &&
//			!comprobarAtributoConValor(form.getNivelAgreg()) &&
//			!comprobarAtributoConValor(form.getDestinatarios()) &&
//			!comprobarAtributoConValor(form.getValoracion())){
//			return true;
//		}
//		return false;
//	}
	
	private boolean esVacioCajaTexto(ValidarFormularioForm form){
		if(!comprobarAtributoConValor(form.getBuscContenido()) &&
		!comprobarAtributoConValor(form.getBuscContenSolo()))return true;
		return false;
	}
	
	private String contarCamposCargados(String idODE,String nivelAgregacion, String destinatarios,String formato, String idiomaBuscador, String recurso, String procesoCognitivo, String contexto, String edad, String autor, String diaPublicacion, String mesPublicacion, String anyoPublicacion, String secuencia, String valoracion, String licencia){
		int contador=0;
		if(comprobarAtributoConValor(idODE)) contador++;
		if(comprobarAtributoConValor(formato)) contador++;
		if(comprobarAtributoConValor(idiomaBuscador)) contador++;
		if(comprobarAtributoConValor(recurso)) contador++;
		if(comprobarAtributoConValor(procesoCognitivo))contador++;
		if(comprobarAtributoConValor(contexto))contador++;
		if(comprobarAtributoConValor(edad))contador++;
		if(comprobarAtributoConValor(autor))contador++;
		if(comprobarAtributoConValor(diaPublicacion) || comprobarAtributoConValor(mesPublicacion) || comprobarAtributoConValor(anyoPublicacion))contador++;
		if(comprobarAtributoConValor(secuencia))contador++;
		if(comprobarAtributoConValor(valoracion))contador++;
		if(comprobarAtributoConValor(destinatarios))contador++;
		if(comprobarAtributoConValor(nivelAgregacion))contador++;
		if(comprobarAtributoConValor(licencia))contador++;
		logger.debug("BuscarAvanzadoControllerImpl - contarCamposCargados=["+String.valueOf(contador)+"]");
		return String.valueOf(contador);
	}
	
	private String contarCamposArbolCurricular(ValidarFormularioForm form){
//		log("BuscarAvanzadoControllerImpl - contadorCamposArbolCurricular - areaCurricular=["+form.getAreaCurricular()+"]");
//		if(comprobarAtributoConValor(form.getAreaCurricular())) return String.valueOf(1);
		return String.valueOf(0);
	}
	
	private boolean comprobarAtributoConValor(String variable){
		//Compruebo que el existe el campo analizado
		return(variable!=null && !variable.equals(""));
	}
	
//	Método que comprueba que en los campos de fecha son valores son correctos
	private List validarCamposFecha(String dia, String mes, String anyo) throws Exception{
		
		List errorMessages = new ArrayList();
//		Comprobamos si los campos de fecha son numeros
		try {
			if (dia != null && !dia.trim().equals("") && (Integer.valueOf(dia.trim()).intValue() > 31 || Integer.valueOf(dia.trim()).intValue() < 1))
				errorMessages.add("configurar.avanzado.controller.exception.diaFechaIncorrecta");
			if (mes != null && !mes.trim().equals("") && (Integer.valueOf(mes.trim()).intValue() > 12 || Integer.valueOf(mes.trim()).intValue() < 1))
				errorMessages.add("configurar.avanzado.controller.exception.mesFechaIncorrecta");
			if (anyo != null && !anyo.trim().equals("") && (Integer.valueOf(anyo.trim()).intValue() > 2999 || Integer.valueOf(anyo.trim()).intValue() < 1900))
				errorMessages.add(MENSAJE_ANYO_INCORRECTO);
		} catch (Exception e) {
			errorMessages.add(MENSAJE_CARACTER_FECHA);
		}
		logger.debug("validaCamposFecha <"+dia+"/"+mes+"/"+anyo+">. Validado: <"+errorMessages.isEmpty()+">");
		return errorMessages;
	}

	private void saveErrorMessage(HttpServletRequest request, List messages) throws Exception{
		logger.warn("--------------Formulario incorrecto------------");
		boolean encontrado;		
		if(request!=null && messages!=null && messages.size()>0)
		{
			//Pintamos la posición cero de la lista
			saveErrorMessage(request, messages.get(0).toString());
			//Recorremos el vector comprobando si el mensaje de error ha sido pintado anteriormente
			for(int i = 1; messages.size() > i ; i++){ 
				encontrado = false;
				for(int j=i; j>0 ;j--){
					if(messages.get(j-1).equals(messages.get(i)))
						encontrado = true;
				}
				if(!encontrado)
					saveErrorMessage(request, messages.get(i).toString());
			}
		}
	}
	
	private String getPropertyValue(String sKey) throws IOException {
		InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/spring_buscador.properties");
		if (this.pSpringProperties == null) {
			pSpringProperties = new java.util.Properties();
			pSpringProperties.load(fIsSpringProperties);
		}
		fIsSpringProperties.close();
		logger.debug("Propiedad recuperada: <" + sKey + ">  <"+ pSpringProperties.getProperty(sKey)+">");
//		 devolvemos la propiedad
		return pSpringProperties.getProperty(sKey);
	}
	
	private boolean validarAsterisco(String campo){
		if(campo!=null){
			String[] palabras= campo.trim().split(" ");
			if(palabras.length>0)for(int i=0; i < palabras.length;i++) if(palabras[i].startsWith("*")||palabras[i].startsWith("?")||palabras[i].startsWith("&quot;"+"*")||palabras[i].startsWith("'*")) return false;
		}
		return true;
	}
	
	private boolean validarAndQuery(String campo){
		if(campo!=null && !campo.trim().equals("") && (campo.trim().startsWith("+ ") || campo.trim().endsWith(" +")))return false;
		return true;
	}
	
//  Metodo que elimina los espacios en blanco de la variable
	//TODO Ver si no vale un simple trim
    private String eliminarEspaciosBlanco(String campo){
    	if(campo!=null){
    		String retorno = "";
    		while(!campo.trim().equals("")){
    			if(campo.contains("&quot;") && campo.substring(campo.indexOf("&quot;")+1).contains("&quot;")){
	    			StringBuffer resultado = new StringBuffer();
	    			int indiceFinalFrase = campo.substring(campo.indexOf("&quot;")+1).indexOf("&quot;")+campo.indexOf("&quot;")+7;
	    			String[] palabras = campo.substring(campo.indexOf("&quot;"),indiceFinalFrase).trim().split(DetallarControllerImpl.SEPARADOR_PALABRAS);
			    	if(palabras.length>0){
			    		for(int i=0; i < palabras.length;i++) if(!palabras[i].equals("")) resultado.append((palabras[i].equals("&quot;") || resultado.toString().equals("&quot;"))? palabras[i] : DetallarControllerImpl.SEPARADOR_PALABRAS + palabras[i]);	
			    	}
			    	if(!campo.substring(0 , campo.indexOf("&quot;")).trim().equals(""))retorno = retorno +  DetallarControllerImpl.SEPARADOR_PALABRAS + campo.substring(0 , campo.indexOf("&quot;")).trim();
			    	retorno = retorno + DetallarControllerImpl.SEPARADOR_PALABRAS + resultado.toString();
			    	campo = DetallarControllerImpl.SEPARADOR_PALABRAS + campo.substring(indiceFinalFrase).trim();
    			}else{
    				String[] palabras = campo.trim().split(DetallarControllerImpl.SEPARADOR_PALABRAS);
    				StringBuilder returnoBuild = new StringBuilder();
			    	for(int i=0; i < palabras.length ;i++) if(!palabras[i].equals("")) returnoBuild.append(DetallarControllerImpl.SEPARADOR_PALABRAS + palabras[i]);
			    	retorno=returnoBuild.toString();
			    	campo="";
    			}
    		}
	    	return retorno.trim();
    	}
    	return campo;
    }
    
    private void generarPaginas(EjecutarBusquedaAvanzadaForm form, String pagina, Integer numRes, Integer numMaxResPagina, Integer numMaxPaginas){
    	try{
//    		Calculo el numero de paginas que salen del numero de resultados que se otienen por la busqueda.
    		int numeroTotalPaginas = numRes.intValue() / numMaxResPagina.intValue();
    		int paginaInt = Integer.parseInt(pagina);
    		if ((numRes.intValue() % numMaxResPagina.intValue()) > 0) numeroTotalPaginas++;
//    		Si el número de resultados por página es 1 no se pinta nada.
    		if(numeroTotalPaginas==1){
    			form.setPaginasAsArray(new String[1]);
    			form.setAnterior(null);
    			form.setSiguiente(null);   
    		}else{
//        		Calculo el siguiente y el anterior.
    			if ((paginaInt -1) <= 0) form.setAnterior(null);
    			else form.setAnterior(""+ (paginaInt - 1));
    			
    			if ((paginaInt) >= numeroTotalPaginas) form.setSiguiente(null);
    				else form.setSiguiente(""+ (paginaInt + 1));
    			
//        		Calculo la horquilla de paginas por delante y por detras de la actual.
    			int orquilla = numMaxPaginas.intValue() / 2;
    			int listaDesde;
    			int listaHasta;
    			if ((paginaInt - orquilla) <= 0) listaDesde = 1;
    				else listaDesde = paginaInt - orquilla;
    			if ((paginaInt + orquilla) >= numeroTotalPaginas) listaHasta = numeroTotalPaginas;
    				else listaHasta = paginaInt + orquilla;
    			String paginas[] = new String[listaHasta - listaDesde + 1]; 
    			for (int i = 0; i < paginas.length; i++) {
    				paginas[i] = ""+ listaDesde;
    				listaDesde++;
    			}
    			form.setPaginasAsArray(paginas);
//    			logger.debug("listaDesde= <"+listaDesde+"> listaHasta= <"+listaHasta+">");
    		}
    	}catch (Exception ex){
    		logger.error("ERROR: al generar las páginas. ",ex);
    	}
    }
    
//  Este método nos devuelve el texto de búsqueda; que puede venir de tres sitios diferentes:del buscador general, de la página de resultados el buscador de arriba y de la página de resultados del buscador de abajo
    private String devolverTextoBusqueda(String buscadorContenido, String buscadorContenidoSolo){

//    	Determinamos cuales han sido los parametros de busqueda con los que se nos ha invocado.
//    	Se pulsa sobre el buscador general
    	if (buscadorContenido!= null && !buscadorContenido.trim().equals("")) return buscadorContenido.replaceAll(DetallarControllerImpl.JAVA_QUOTES, "&quot;");    	 	
//    	Se pulsa sobre el buscador inferior
    	else if (buscadorContenidoSolo!= null && !buscadorContenidoSolo.trim().equals("")) return buscadorContenidoSolo.replaceAll(DetallarControllerImpl.JAVA_QUOTES, "&quot;");
    	else return "";
    }
	
	/**
	 * Metodo que construye el string de la fecha de publicacion necesario para la busqueda
	 */
	private String construirFecha(String anyoFechaPublicacion,String mesFechaPublicacion,String diaFechaPublicacion){
		if(!comprobarAtributoConValor(anyoFechaPublicacion) && !comprobarAtributoConValor(mesFechaPublicacion) &&	!comprobarAtributoConValor(diaFechaPublicacion)) return "";
		logger.debug("Fecha a enviar=<"+traducirFecha(anyoFechaPublicacion) + traducirFecha(mesFechaPublicacion) + traducirFecha(diaFechaPublicacion)+">");
		return traducirFecha(anyoFechaPublicacion) + traducirFecha(mesFechaPublicacion) + traducirFecha(diaFechaPublicacion);
		
	}
	
	private String traducirFecha(String campo){
		if(campo!=null && !campo.trim().equals(""))return campo;
		return "*";
	}
	
//  Este metodo traduce los campos de resultados de busqueda que hagan falta.
//  
//  de los valores de busqueda que le pasan a sus identificadores
//  en el vocabulario del lom-es. Devuelve la misma lista de resultados de busqueda con las listas de tipo de recurso
//  traducidas de esta manera y sin tocar el resto de datos.
	private ValoresBusquedaVO[] traducirResultadosBusqueda(String pagina,ValoresBusquedaVO[] resultadosBusqueda, HttpServletRequest request, String idioma, Integer maxResPagina) throws Exception{
		if (resultadosBusqueda !=null && resultadosBusqueda.length > 0){
			ValoresBusquedaVO[] valoresRetorno = new ValoresBusquedaVO[resultadosBusqueda.length];
			logger.debug("Traduciendo <"+resultadosBusqueda.length+"> resultados de busqueda avanzada.");			
			List <String>listaAreaCurricular = new ArrayList<String>();
			List <String>listaRecurso = new ArrayList<String>();
			for (int i = 0; i < resultadosBusqueda.length; i++) {
				for (int j = 0; j < resultadosBusqueda[i].getTipoRecurso().length; j++) {
					listaRecurso.add(resultadosBusqueda[i].getTipoRecurso()[j]);
				}
				for (int j = 0; resultadosBusqueda[i].getAreaCurricular()!=null && j < resultadosBusqueda[i].getAreaCurricular().length; j++) {
					String area = sortAreaCurricularDescendente(resultadosBusqueda[i].getAreaCurricular()[j].split("/"),resultadosBusqueda[i].getAreaCurricular()[j].split("/").length);
					logger.debug("traduceAreaCurricular arbol vigente: <"+this.getSrvTaxonomiaService().obtenerArbolVigente().getVocabName()+"> ruta a traducir: <"+area + ">");
					if(resultadosBusqueda[i].getAreaCurricular()!=null && !resultadosBusqueda[i].getAreaCurricular()[0].equals("") && !listaAreaCurricular.contains(area))listaAreaCurricular.add(area);
				}
			}
			TerminoVO[] terminosTraducidos = new TerminoVO[0];
			try{
				TerminoYPadreVO[] terminoArray = new TerminoYPadreVO[listaRecurso.size()];
				logger.debug("Traduciendo <"+listaRecurso.size()+"> tipos de recurso en resultados avanzados");
				for (int i = 0; i < listaRecurso.size(); i++) {
		//		Vamos rellenando este objeto con todos los terminos que nos encontremos y traducirlos al mismo tiempo
					terminoArray[i] = new TerminoYPadreVO(this.getPropertyValue("tipoRecurso"),listaRecurso.get(i),"en","");
				}
				terminoArray = this.getSrvVocabulariosControladosService().obtenerIdTermino(terminoArray);
				List <String>retornoArray = new ArrayList<String>();
				for (int i = 0; i < terminoArray.length; i++) {
					if (terminoArray[i].getIdTermino() != null) retornoArray.add(terminoArray[i].getIdTermino());
				}
				terminosTraducidos = this.getSrvVocabulariosControladosService().crearTraduccionAIdioma(retornoArray.toArray(new String[0]),idioma);
				logger.debug("Obtenida traduccion de ["+terminosTraducidos.length+"] terminos para idioma["+"idioma"+"]");
			} catch (RemoteException e) {
				logger.error("ERROR: Error en la invocacion al servicio de vocabularios controlados traduciendo tipos de recurso"+array2String(listaRecurso.toArray(new String[0]))+"] idioma en LOM-ES[en]",e);
			} catch (IOException e) {
				logger.error("ERROR: Error obteniendo propiedad \"tipoRecurso\" del fichero de propiedades del modulo Buscador traduciendo tipos de recurso"+array2String(listaRecurso.toArray(new String[0]))+"] idioma en LOM-ES[en]",e);
			} catch (Exception e) {
				logger.error("traduceTipoRecurso ERROR: Error general traduciendo tipos de recurso["+array2String(listaRecurso.toArray(new String[0]))+"] idioma en LOM-ES[en]",e);
			}
			
			TaxonPathVO[] taxonPath = new TaxonPathVO[0];
			try{
				taxonPath = this.getSrvTaxonomiaService().obtenerVariosTaxonPaths(listaAreaCurricular.toArray(new String[0]), this.getPropertyValue("nombreAreaCurricularTax"), idioma);
			}catch(Exception e){
//				 Puede pasar que la traduccion no funcione, pero no por ello me tengo que parar.
				 logger.error("convertirStringAreaCurricular ERROR: Error al traducir area curricular con taxon <"+array2String(listaAreaCurricular.toArray(new String[0]))+"> al idioma <"+idioma+">. No hay traduccion posible." , e);
			}
			
			int h=0;
		
			for (int i = 0; i < resultadosBusqueda.length; i++) {
				String[] recurso = new String[0];
				recurso = new String[resultadosBusqueda[i].getTipoRecurso().length];
				for (int j = 0; j < resultadosBusqueda[i].getTipoRecurso().length; j++,h++) {
					recurso[j]=terminosTraducidos[h].getNomTermino();
				}
				String[] areaCurricular = new String[0];
				if(resultadosBusqueda[i].getAreaCurricular()!=null && resultadosBusqueda[i].getAreaCurricular().length>0 && !resultadosBusqueda[i].getAreaCurricular()[0].equals("")){
					areaCurricular = new String[resultadosBusqueda[i].getAreaCurricular().length];
					for (int j = 0; j < resultadosBusqueda[i].getAreaCurricular().length; j++) {
						String ruta = "";
						for (int k= 0; k< taxonPath.length && areaCurricular[j]==null; k++){
							if(sortAreaCurricularDescendente(resultadosBusqueda[i].getAreaCurricular()[j].split("/"),resultadosBusqueda[i].getAreaCurricular()[j].split("/").length).equals(taxonPath[k].getDatos()[0].getId())){
								for (int l= 0; l< taxonPath[k].getDatos().length; l++){
									if (l == (taxonPath[k].getDatos().length-1))ruta= taxonPath[k].getDatos()[l].getValorTax() + ruta;
									else if (l==0)ruta= "/" + taxonPath[k].getDatos()[l].getValorTax();
									else ruta= "/" + taxonPath[k].getDatos()[l].getValorTax() + ruta;
									logger.debug("traduceAreaCurricular ruta traducida: <"+ ruta +">");
								}
							}
						}
						areaCurricular[j]=ruta;
					}
				}
				valoresRetorno[i]= new ValoresBusquedaVO(resultadosBusqueda[i].getValoracion(),	resultadosBusqueda[i].getTitulo(), recurso, resultadosBusqueda[i].getRanking(), areaCurricular, resultadosBusqueda[i].getCurso(), resultadosBusqueda[i].getId(),calcularNumeroOde(pagina, i+1, maxResPagina),resultadosBusqueda[i].getUrlImagen(),resultadosBusqueda[i].getNodo(),resultadosBusqueda[i].getNivelAgregacion(),resultadosBusqueda[i].getFormato(),this.traducirNivelAgregacion(resultadosBusqueda[i].getNivelAgregacion(), new Locale(idioma)),resultadosBusqueda[i].getAmbito(),resultadosBusqueda[i].getEsVisualizable(),resultadosBusqueda[i].getDescripcion(),resultadosBusqueda[i].getFechaPublicacion(), resultadosBusqueda[i].getHoraPublicacion(),resultadosBusqueda[i].getConSecuencia(),0);
				boolean visualizable = false;
				if(valoresRetorno[i].getAmbito()!=null && valoresRetorno[i].getAmbito()[0]!=null && valoresRetorno[i].getAmbito()[0].equals(UNIVERSAL)){
					valoresRetorno[i].setEsVisualizable(true);
					visualizable=true;
				} else {
					if ((LdapUserDetailsUtils.estaAutenticado())||(LdapUserDetailsUtils.tieneIdentidadFederada(request))){
						for (int j = 0; j < valoresRetorno[i].getAmbito().length && !visualizable; j++) {
							if(valoresRetorno[i].getAmbito()[j].trim().equals(ObtieneSrvPropiedad().get(AgregaProperties.SERVER_ID))){
								valoresRetorno[i].setEsVisualizable(true);
								visualizable=true;
							}
						}
					}
				}
				if(!visualizable){
					valoresRetorno[i].setEsVisualizable(false);
				}
			}
			return valoresRetorno;
		}
		return resultadosBusqueda;// si no hay nada que hacer, pues devolvemos lo mismo.
	}	
	
	private String calcularNumeroOde(String numeroPagina, int numOde, Integer maxResPagina){
		String numeroOde="";
		if(numeroPagina !=null){
			String decenasString="";
			String unidadesString = "";
			int numPag=Integer.parseInt(numeroPagina);
			if(numPag==1){
				if (numOde>=10){
					unidadesString = String.valueOf(numOde).substring(1);
					decenasString = String.valueOf(numOde).substring(0,1);
				}else{
					unidadesString = String.valueOf(numOde);
				}
			}else{
				if (numOde>=10){
					unidadesString = String.valueOf(numOde).substring(1);
					int decenas=(maxResPagina*(numPag-1))+numOde;
					decenasString = String.valueOf(decenas).substring(0,String.valueOf(decenas).length()-1);
				}else{
					int decenas=(maxResPagina*(numPag-1))+numOde;
					decenasString = String.valueOf(decenas).substring(0,String.valueOf(decenas).length()-1);
					unidadesString = String.valueOf(numOde);
				}
			}
	//		Quitamos el cero de la primera pagina
			if(decenasString.equals("0")) numeroOde = unidadesString;
			else numeroOde = decenasString + unidadesString;
		}
		return numeroOde;
	}
	
//	Este metodo recibe un array de identificadores de areas curriculares que han sido encontradas y 

//	traduce cada entrada del indice por su correspondiente etiqueta
	private String[] convertirStringAreaCurricular(String[] areaCurricular, String idioma) throws Exception{
		if (areaCurricular != null && areaCurricular.length > 0 ){
			ArrayList taxones = new ArrayList();
			for (int i = 0; i < areaCurricular.length; i++){
				String[] split=null;
				if (!areaCurricular[i].equals("")){
//					Hago el split por '/'
					split=areaCurricular[i].split("/");
//					Ordenamos los taxones, ya que nadie me asegura que esten ordenados. El resultado me da el taxon más
//					largo el primero de la lista despues de una comparacion lexicográfica.
					sortAreaCurricularDescendente(split,split.length);
//					Ahora cogemos el primer elemento y lo mandamos traducir. Nos devuelve la ruta traducida hasta el padre.
					try{
						List rutaPadrevo = Arrays.asList(this.getSrvTaxonomiaService().obtenerTaxonPath(split[0], this.getPropertyValue("nombreAreaCurricularTax"), idioma));
						TaxonVO[] taxVORutaPadre = (TaxonVO[])rutaPadrevo.toArray();
						String ruta = "";
						for (int j= 0; j< taxVORutaPadre.length; j++){
							if (j == (taxVORutaPadre.length-1))ruta= taxVORutaPadre[j].getValorTax() + ruta;
							else if (j==0)ruta= "/" + taxVORutaPadre[j].getValorTax();
							else ruta= "/" + taxVORutaPadre[j].getValorTax() + ruta;
						}
//						log("BuscarAvanzadoControllerImpl - convertirStringAreaCurricular: Traducido path de arbol curricular ["+ruta+"] a idioma["+idioma+"]");
						taxones.add(ruta);
					}catch(Exception e){
//						 Puede pasar que la traduccion no funcione, pero no por ello me tengo que parar.
						 logger.error("BuscarAvanzadoControllerImpl - convertirStringAreaCurricular ERROR: Error al traducir area curricular con taxon["+split[0]+"] al idioma["+idioma+"]. No hay traduccion posible." , e);
					}
				}
			}
			return (taxones.size()>0?(String[])taxones.toArray(new String[taxones.size()]):new String[]{""});
		}
		return new String[]{""};
	}

	private static String sortAreaCurricularDescendente(String [] array, int len){
		int a,b;
		String temp;
		int sortTheStrings = len - 1;
		for (a = 0; a < sortTheStrings; ++a)
		for (b = 0; b < sortTheStrings; ++b)
		if(array[b].compareTo(array[b + 1]) < 0){
			temp = array[b];
			array[b] = array[b + 1];
			array[b + 1] = temp;
		}
		return array[0];
	}
    
    private String[] obtenerComunidades() throws RemoteException, Exception{		
		NodoVO[] nodoLista = this.getSrvNodoService().listarNodos();
   	 	String[]comuni = new String[nodoLista.length];
   	 	for(int j=0; j<nodoLista.length;j++) comuni[j]=nodoLista[j].getId().toString();
		return comuni;
	}
    
    private String[] obtenerComunidadesIncluidoNodoLocal() throws RemoteException, Exception{		
		NodoVO[] nodoLista = this.getSrvNodoService().listarNodos();

   	 	String[]comuni = new String[nodoLista.length+1];

   	 	for(int j=0; j<nodoLista.length;j++) comuni[j]=nodoLista[j].getId().toString();

   	 	comuni[nodoLista.length]="-1";

		return comuni;
	}
    
    private void cargarFormularioSesion (AnalizaEntradaForm form, HttpServletRequest request){
		BuscarSession sesion = this.getBuscarSession(request);
		form.setAutor(sesion.getAutor());
		form.setBuscContenido(sesion.getBuscadorContenido());
		form.setBusqSimpleAvanz(sesion.getBusquedaSimpleAvanzada());
		form.setC_s_secuencia(sesion.getSecuencia());

		if(sesion.getEnlaceComunidadesSeleccionadas()!=null){
			form.setEnlaceComuSelec(sesion.getEnlaceComunidadesSeleccionadas());
		}
		form.setContexto(sesion.getContexto());
		form.setDescrip(sesion.getDescripcion());
		form.setEdad(sesion.getEdad());
		form.setIdODE(sesion.getIdODE());
		form.setDiaPublic(sesion.getDiaPublicacion());
		form.setMesPublic(sesion.getMesPublicacion());
		form.setAnyoPublic(sesion.getAnyoPublicacion());
		form.setFormato(sesion.getFormato());
		form.setIdioma(sesion.getIdioma());
		form.setKeyword(sesion.getKeyword());
		
		form.setEnlaceTaxSelec(sesion.getEnlaceTaxSelec());
				
 		if(sesion.getPagina()!=null)form.setPagina(sesion.getPagina().toString());
		form.setProcesoCognitivo(sesion.getProcesoCognitivo());
		form.setRecurso(sesion.getRecurso());
		form.setTitulo(sesion.getTitulo());
		form.setValoracion(sesion.getValoracion());
		form.setNivelAgreg(sesion.getNivelAgregacion());
		form.setTipoVisualiz(sesion.getTipoVisualizacion());
		form.setTipoBusqueda(sesion.getTipoBusqueda());
		logger.debug("CARGA FORMULARIO SESION <" + form.getTipoBusqueda() + ">");
		form.setDestinatarios(sesion.getDestinatarios());
		form.setNumeroResultados(sesion.getNumeroResultados());
		if(sesion.getTipoLayoutBuscador()!=null && !sesion.getTipoLayoutBuscador().equals(""))form.setTipoLayoutBuscador(sesion.getTipoLayoutBuscador());
		if(sesion.getIdTesauro()!=null && !sesion.getIdTesauro().equals(""))form.setIdTesauro(sesion.getIdTesauro());
		if(sesion.getTesauroBusqueda()!=null && !sesion.getTesauroBusqueda().equals(""))form.setIdTesauroSug(sesion.getTesauroBusqueda());
		if(sesion.getNomTesauros()!=null && !sesion.getNomTesauros().equals(""))form.setNomTesauros(sesion.getNomTesauros());
	}
	
	private BuscarSession inicializarSesion(HttpServletRequest request){
		BuscarSession sesion = this.getBuscarSession(request);
//		Conservo los únicos parámetros constantes
		if(sesion.getEmpIdOde() != null || sesion.getEmpIdDestino() != null || sesion.getEmpTipoEmpaquetador() != null){
			String idODe=sesion.getEmpIdOde();
			String idDestino=sesion.getEmpIdDestino();
			String tipoEmpaquetador=sesion.getEmpTipoEmpaquetador();
			sesion = new BuscarSession();
			sesion.setEmpIdOde(idODe);
			sesion.setEmpIdDestino(idDestino);
			sesion.setEmpTipoEmpaquetador(tipoEmpaquetador);
		}else{
			sesion = new BuscarSession();
		}
		this.setBuscarSession(request,sesion);
		return sesion;
	}
	
	private void inicializarFormulario(ValidarFormularioForm form){
//		Conservo los únicos parámetros constantes
		form.setAutor(null);
		form.setC_s_secuencia(null);
		form.setComuSelec(null);
		form.setEnlaceComuSelec(null);
		form.setContexto(null);
		form.setDescrip(null);
		form.setEdad(null);
		form.setIdODE(null);
		form.setDiaPublic(null);
		form.setMesPublic(null);
		form.setAnyoPublic(null);
		if(form.getFormato()!=null && form.getFormato().equalsIgnoreCase(""))
		form.setFormato(null);
		form.setEnlaceTaxSelec(null);
		form.setProcesoCognitivo(null);
		form.setRecurso(null);
		form.setTitulo(null);
		form.setValoracion(null);
		form.setKeyword(null);
		if(form.getNivelAgreg()!=null && form.getNivelAgreg().equalsIgnoreCase(""))
		form.setNivelAgreg(null);
		form.setIdTesauro(null);
		form.setNomTesauros(null);
	}
	
	private String devolverIdiomaBuscador(String idioma) throws Exception{
		String idiomaNavegacion = "";
		if ((idioma != null) && !idioma.equals("") && !idioma.equals("#")) return idioma;
		else if(LdapUserDetailsUtils.estaAutenticado()){
				try{
					idiomaNavegacion=LdapUserDetailsUtils.getIdiomaBusqueda();
					if (idiomaNavegacion == null) idiomaNavegacion = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
				} catch (Exception e) {
					logger.error("BuscarAvanzadoControllerImpl - cargaFormularioBusquedaAvanzada: Error en al obtener idioma búsqueda usuario LDAP, se recoge idioma de fichero propiedades.",e);
					idiomaNavegacion = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
				}
	   	}else idiomaNavegacion = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
		return idiomaNavegacion;
	}
	
	private String obtenerIdiomaBusqueda() throws Exception{
	   	String idiomaNavegacion = "";
	   	if(LdapUserDetailsUtils.estaAutenticado()){
				try{
					idiomaNavegacion=LdapUserDetailsUtils.getIdiomaBusqueda();
					if (idiomaNavegacion == null) idiomaNavegacion = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
				} catch (Exception e) {
					logger.error("BuscarAvanzadoControllerImpl - cargaFormularioBusquedaAvanzada: Error en al obtener idioma búsqueda usuario LDAP, se recoge idioma de fichero propiedades.",e);
					idiomaNavegacion = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
				}
	   	}else idiomaNavegacion = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
			return idiomaNavegacion;   
	}
	
	private String prepararTextoBusqueda(String texto){
		
		if(texto!=null && !texto.equals("")){ 
			if(!texto.trim().toLowerCase().startsWith(pSpringProperties.getProperty("agrega_admin") + " ") && !texto.trim().toLowerCase().startsWith(pSpringProperties.getProperty("agrega_todos") + " "))
				return texto.replaceAll("&quot;", DetallarControllerImpl.JAVA_QUOTES).replaceAll(BuscarAvanzadoControllerImpl.HTML_PLUS, BuscarAvanzadoControllerImpl.JAVA_PLUS).replaceAll(DetallarControllerImpl.DOT, DetallarControllerImpl.SEPARADOR_PALABRAS).replaceAll(DetallarControllerImpl.DOUBLE_DOT, DetallarControllerImpl.SEPARADOR_PALABRAS).replaceAll(DetallarControllerImpl.OPEN_QUESTIONMARK, DetallarControllerImpl.SEPARADOR_PALABRAS).replaceAll(DetallarControllerImpl.CLOSE_QUESTIONMARK, DetallarControllerImpl.SEPARADOR_PALABRAS).replaceAll(DetallarControllerImpl.OPEN_PARENTHESES, DetallarControllerImpl.SEPARADOR_PALABRAS).replaceAll(DetallarControllerImpl.CLOSE_PARENTHESES, DetallarControllerImpl.SEPARADOR_PALABRAS).replaceAll(DetallarControllerImpl.OPEN_EXCLAMATIONMARK, DetallarControllerImpl.SEPARADOR_PALABRAS).replaceAll(DetallarControllerImpl.CLOSE_EXCLAMATIONMARK, DetallarControllerImpl.SEPARADOR_PALABRAS).replaceAll(DetallarControllerImpl.COMA, DetallarControllerImpl.SEPARADOR_PALABRAS).replaceAll(DetallarControllerImpl.SLASH, DetallarControllerImpl.SEPARADOR_PALABRAS);
			return texto;//si es agregaadmin o agregatodos
		}
		return "";
	}
	

	//SOLO SI LOS ELIMINAR TAXONOMIAS DE SON UN BOTON, BORRAR SI SON LINK
	private String obtenerPulsacion(HttpServletRequest request,String inicioPulsacion) 
	throws Exception
	{
		String pulsacion="";
//		String[] partes;
		for (Enumeration names = request.getParameterNames(); pulsacion.equals("") && names.hasMoreElements();)
	       {
	      	 String name = String.valueOf(names.nextElement());
	           if(name.startsWith(inicioPulsacion))
	           {
	        	   pulsacion = name;        	 
	        	}
		
	        }
		return pulsacion;
	}
	
	private String convertirStringRutaTax(String ultTaxon, String idTaxonomia, String idioma) throws Exception{
		List rutaPadrevo = Arrays.asList(this.getSrvTaxonomiaService().obtenerTaxonPath(ultTaxon,idTaxonomia, idioma));
		Collections.reverse(rutaPadrevo);
		TaxonVO[] taxVORutaPadre = (TaxonVO[])rutaPadrevo.toArray();
		String rutaPadre[] = new String[taxVORutaPadre.length];
		for (int i = 0; i < taxVORutaPadre.length; i++) rutaPadre[i]= taxVORutaPadre[i].getValorTax();
		return array2String(rutaPadre);
	}
	
	private String[] obtenerTaxYTes(String enlaceTaxSelec,String idTesauro,String idTesauroSugerencia) throws Exception{
		String[] taxTesSelec=null;
		//si tesauro sugerencia tiene valor entonces taxonomias e idTesauro no tiene valor
		if(idTesauroSugerencia!=null && !idTesauroSugerencia.trim().equals("")){
			EstructuraVdexVO tesauroVigente = this.getSrvTesaurosServices().obtenerTesauroVigente();
			String vocabIdentifierTesauro = tesauroVigente.getVocabIdentifier();
			taxTesSelec = new String[1];
			taxTesSelec[0] = vocabIdentifierTesauro + "/" + idTesauroSugerencia;	
		}
		else{
			
			if(enlaceTaxSelec!=null && !enlaceTaxSelec.trim().equals("")){
				String[] taxSelec = enlaceTaxSelec.split(BuscarAvanzadoControllerImpl.TAX_ASTERISCO);
				int tamaño = taxSelec.length;
				if(idTesauro !=null && !idTesauro.trim().equals("")){
					EstructuraVdexVO tesauroVigente = this.getSrvTesaurosServices().obtenerTesauroVigente();
					String vocabIdentifierTesauro = tesauroVigente.getVocabIdentifier();
					tamaño = tamaño +1;
					taxTesSelec=new String[tamaño];
					taxTesSelec[tamaño-1]= vocabIdentifierTesauro + "/" + idTesauro;
				}else{
					taxTesSelec=new String[tamaño];
				}
			
				for (int i=0;i<taxSelec.length;i++){
					taxTesSelec[i] = taxSelec[i].replace("$","/");
				}
			}else if(idTesauro !=null && !idTesauro.trim().equals("")){
				EstructuraVdexVO tesauroVigente = this.getSrvTesaurosServices().obtenerTesauroVigente();
				String vocabIdentifierTesauro = tesauroVigente.getVocabIdentifier();
				taxTesSelec = new String[1];
				taxTesSelec[0] = vocabIdentifierTesauro + "/" + idTesauro;	
			}
			
		}
		return taxTesSelec;
	}
	 
	private void setCookieIdioma(HttpServletResponse response, String valorCookie)
    {
    	if (logger.isDebugEnabled()) logger.debug("cambiarIdioma-- Se pone la cookie con nombre: ["+NOMBRECOOKIE+"]");
		Cookie cookie = new Cookie(NOMBRECOOKIE, valorCookie);
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24*30*600);
		response.addCookie(cookie);

		if (logger.isDebugEnabled()) logger.debug("cambiarIdioma-- Despues de añadir la cookie " + NOMBRECOOKIE + ": " + cookie + " con valor: " + valorCookie + " al objeto response");
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
//	MÉTODOS PARA PINTAR LOS MENSAJES INTERNACIONALIZADOS
	public static String getResource(String key, String baseName, Locale locale)
	{
       String recurso = "";
       ResourceBundle theResourceBundle = getResource(baseName,locale);
       try{
       	if (theResourceBundle!=null){
              recurso = theResourceBundle.getString(key);
          }
       }catch (MissingResourceException mre){
       	recurso = key;
       }
       return recurso;
   }

	public static ResourceBundle getResource(String baseName, Locale locale) {
		try {
			return ResourceBundle.getBundle(baseName, locale);

		} catch (MissingResourceException mre) {
			locale = new Locale("", "");
			return ResourceBundle.getBundle(baseName, locale);
		}
	}
	

	private SrvPropiedadService ObtieneSrvPropiedad() throws Exception {
		if (this.prop==null)
		{
			prop = this.getSrvPropiedadService();
		}
		return prop;

	}
}