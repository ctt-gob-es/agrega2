// license-header java merge-point
package es.pode.buscador.presentacion.avanzado.busquedaSQI;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.buscador.presentacion.avanzado.buscarAvanzado.BuscarAvanzadoControllerImpl;
import es.pode.buscador.presentacion.basico.detallar.DetallarControllerImpl;
import es.pode.buscar.negocio.administrar.servicio.NodoVO;
import es.pode.buscar.negocio.buscar.servicios.LomEsVO;
import es.pode.buscar.negocio.buscar.servicios.ParamBusquedaSQIVO;
import es.pode.buscar.negocio.buscar.servicios.ResultadoBusquedaSQIVO;
import es.pode.buscar.negocio.buscar.servicios.SrvBuscarService;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.i18n.LocalizacionIdiomaVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.buscador.presentacion.avanzado.busquedaSQI.BusquedaSQIController
 */
public class BusquedaSQIControllerImpl extends BusquedaSQIController
{
	private static Logger logger = Logger.getLogger(BusquedaSQIControllerImpl.class);
	private java.util.Properties pSpringProperties = null;
	public final static String APPLICATION_PROPERTIES = "application-resources";

	
	public static final String HTML_PLUS = "%2B";
	public static final String JAVA_PLUS = "\\+";
	

	private final static String ACCION_FORMULARIO_ERROR = "FORMULARIO_ERROR";
	private final static String ACCION_FORMULARIO_VALIDO = "FORMULARIO_VALIDO";

	public final static String MENSAJE_NINGUNO_LLENO = "configurar.avanzado.controller.exception.NingunCampoLleno.SQI";
	public final static String MENSAJE_GENERICO_BUSQUEDA = "listar.odecu.mostrar.resultados.consulta.cabecera.errorBusqueda";
	public final static String MENSAJE_WILDCARD_SOLA = "configurar.avanzado.controller.exception.noAsterisco";
	public final static String MENSAJE_SUMATORIO_INICIO = "configurar.avanzado.controller.exception.sumatorioInicio";
	
	public final static String MENSAJE_ERROR_AUTENTIFICACION="configurar.avanzado.controller.exception.errorAutentificacion";
	
	
	
	

    /**
     * @see es.pode.buscador.presentacion.avanzado.busquedaSQI.BusquedaSQIController#analizarFormulario(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.avanzado.busquedaSQI.AnalizarFormularioForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String analizarFormulario(ActionMapping mapping, es.pode.buscador.presentacion.avanzado.busquedaSQI.AnalizarFormularioForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
    	if(logger.isDebugEnabled())logger.debug("BusquedaSQIControllerImpl - analizarFormulario");
    	ArrayList<String> errorMessages = new ArrayList<String>();
    	boolean formError =false;
    	if(form.getBuscadorContenido()==null) form.setBuscadorContenido(form.getBuscContenido());
    	if(form.getTipoLayoutBuscador()==null){
			try
			{
				String neutro = (AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NEUTRO));	    	
		    	if("true".equals(neutro)) form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_NEUTRO);
		    	else form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_BUSCADOR);
		    	
		    	form.setBuscadorContenido(devolverTextoBusqueda(form.getBuscadorContenido(), form.getBuscadorContenidoSolo()));
//				Valido el formulario para ver si es correcto
				if(esVacioCajaTexto(form)){
					errorMessages.add(MENSAJE_NINGUNO_LLENO);
					formError=true;
				}
				else{
					formError=false;
					if(!validarAsterisco(form.getBuscadorContenido())){
						errorMessages.add(MENSAJE_WILDCARD_SOLA);
						formError=true;
					}
					if(!validarAndQuery(form.getBuscadorContenido())){
						errorMessages.add(MENSAJE_SUMATORIO_INICIO);
						formError=true;
					}
				}
			}catch (Exception e)
			{
				logger.error("BusquedaSQIControllerImpl - analizarFormulario ERROR: formulario erroneo", e);
				form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_BUSCADOR);
			}
		}
    	if (formError){
    		return ACCION_FORMULARIO_ERROR;
    	}
		return ACCION_FORMULARIO_VALIDO;		
    }


    /**
     * @see es.pode.buscador.presentacion.avanzado.busquedaSQI.BusquedaSQIController#ejecutarBusquedaSQI(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.avanzado.busquedaSQI.EjecutarBusquedaSQIForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void ejecutarBusquedaSQI(ActionMapping mapping, es.pode.buscador.presentacion.avanzado.busquedaSQI.EjecutarBusquedaSQIForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	ParamBusquedaSQIVO param = null;
    	ResultadoBusquedaSQIVO resultados = new ResultadoBusquedaSQIVO();
    	String textoBusqueda = null;
    	List errores = new ArrayList();
    	try{
    		if(form.getTipoLayoutBuscador()==null) form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_BUSCADOR);    		
			if(form.getTipoLayoutBuscador().equals(DetallarControllerImpl.LAYOUT_BUSCADOR) || form.getTipoLayoutBuscador().equals(DetallarControllerImpl.LAYOUT_NEUTRO)){
				try{
					Boolean administrador=false;
					if(form.getKeyword()==null || form.getKeyword().trim().equals("")) administrador = LdapUserDetailsUtils.esAdministrador();
					form.setUsuarioAdministrador(administrador);
					if(logger.isDebugEnabled())logger.debug("BusquedaSQIControllerImpl - ejecutarBusquedaSQI:Administrador=="+administrador.toString());
				}catch(Exception e){
					form.setUsuarioAdministrador(false);
					errores.add(MENSAJE_ERROR_AUTENTIFICACION);					
					logger.error("Error al llamar a LdapUserDetailsUtils.esAdministrador() de Soporte");
				}
			}else form.setUsuarioAdministrador(false);			
			
    		String idiomaNavegacion = ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
//        	Elimino los espacios en blanco del formulario de los campos de wild-card
    		
			form.setIdiomaBuscador((form.getIdioma()==null)?devolverIdiomaBuscador(form.getIdiomaBuscador()):form.getIdioma());
			
//		    	Los idiomas que deben aparecer en el desplegable de buscar dependen del idioma de navegacion
			LocalizacionIdiomaVO[] localizacionesIdioma = I18n.getInstance().obtenerIdiomasBuscablesLocalizados(idiomaNavegacion);
			form.setIdiomaBuscadorBackingList(Arrays.asList(localizacionesIdioma), "idLocalizacion", "nombre");
			try{
				form.setNumeroResultadosLabelList(cargarNumeroResultadosLabel(new Locale(idiomaNavegacion)));
				form.setNumeroResultadosValueList(cargarNumeroResultadosValue());
			}catch(Exception ex){
				form.setNumeroResultadosLabelList(new String[0]); 
				form.setNumeroResultadosValueList(new String[0]);
				saveErrorMessage(request,MENSAJE_GENERICO_BUSQUEDA);
			}
	    	if (form.getPagina() == null || form.getPagina().equals("")) form.setPagina("1");

			textoBusqueda = devolverTextoBusqueda(form.getBuscadorContenido(), form.getBuscadorContenidoSolo());
			form.setBuscadorContenidoEnlace(textoBusqueda.replaceAll(JAVA_PLUS, HTML_PLUS));
			if(logger.isDebugEnabled())logger.debug("BusquedaSQIControllerImpl - ejecutarBusquedaSQI con textoBusqueda["+textoBusqueda+"], idiomaBuscador["+form.getIdiomaBuscador()+"] y paginaRequerida["+form.getPagina()+"]");
//	        	PREPARAMOS LA CONSULTA DE BUSQUEDA
//	        	Por ahora utilizamos el locale. Cuando tengamos usuarios sera el idioma de navegacion por la aplicacion
    		param = new ParamBusquedaSQIVO();
    		param.setOrigenPagina(Integer.valueOf(form.getPagina()));
    		param.setPalabrasClave(eliminarEspaciosBlanco(textoBusqueda).replaceAll("&quot;", DetallarControllerImpl.JAVA_QUOTES).replaceAll(HTML_PLUS, JAVA_PLUS));
    		param.setNumeroResultados(Integer.valueOf(request.getSession().getServletContext().getInitParameter("max_res_totales")));
			Integer maxResPagina = 0;
			if(form.getNumeroResultados()!=null && !form.getNumeroResultados().equals(""))maxResPagina=Integer.valueOf(form.getNumeroResultados());
			else maxResPagina = Integer.valueOf(request.getSession().getServletContext().getInitParameter("max_res_pagina"));
			param.setResultadoPorPagina(maxResPagina);
//		    	Invocamos el servicio de busqueda
			try{
				SrvBuscarService buscarService = this.getSrvBuscarService();
				resultados = buscarService.buscarSQI(param);
				if(logger.isDebugEnabled())logger.debug("BusquedaSQIControllerImpl - ejecutarBusquedaSQI: Resultados de busqueda avanzada ["+(resultados.getResultadoBusqueda()!= null?resultados.getResultadoBusqueda().length:0)+"]");
			}catch (Exception e){
				logger.error("BusquedaSQIControllerImpl - ejecutarBusquedaSQI: Error al recuperar los resultados de busqueda",e);
				resultados.setNumeroResultados(0);
				resultados.setResultadoBusqueda(new LomEsVO[0]);
			}

			String[] comuni=obtenerComunidades();
//			Comprobacion del nodo neutro
			if(comuni == null || comuni.length == 0) form.setMostrarAmbito(false);
			else form.setMostrarAmbito(true);
			
			form.setLomEsVO(Arrays.asList(resultados.getResultadoBusqueda()));
			form.setBuscadorContenido(textoBusqueda);
			int resultadosDesde = (Integer.parseInt(form.getPagina()) -1 ) * maxResPagina + 1;
			form.setResultadosDesde(resultadosDesde);

			int resultadosHasta = resultadosDesde + maxResPagina - 1;
			resultadosHasta = (resultadosHasta > resultados.getNumeroResultados().intValue()? resultados.getNumeroResultados().intValue(): resultadosHasta);
			form.setResultadosHasta(String.valueOf(resultadosHasta));
			form.setNumMaxRes(resultados.getNumeroResultados().toString());
			form.setResultadosDesde(resultadosDesde);
			form.setResultadosHasta(String.valueOf(resultadosHasta));

//		    Este metodo resuelve toda la problematica del banner de paginacion sobre el formulario.
			generarPaginas(	form,
					form.getPagina(),
    						resultados.getNumeroResultados(),
    						maxResPagina,
    						maxResPagina);
		}catch (Exception ex){
			resultados=null;
			logger.error("BusquedaSQIControllerImpl - ejecutarBusquedaSQI ERROR: Error en la invocacion a la Busqueda SQI ");
			logger.error("BuscquedaSQIControllerImpl - ejecutarBusquedaSQI ERROR:", ex);
			errores.add(MENSAJE_GENERICO_BUSQUEDA);			
		}
		for (int i=0;i<errores.size();i++){
			if(errores!=null && errores.size()>0) saveErrorMessage(request,(String)errores.get(i));
		}
    }

    /**
     * @see es.pode.buscador.presentacion.avanzado.busquedaSQI.BusquedaSQIController#analizarTipoBusqueda(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.avanzado.busquedaSQI.AnalizarTipoBusquedaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String analizarTipoBusqueda(ActionMapping mapping, es.pode.buscador.presentacion.avanzado.busquedaSQI.AnalizarTipoBusquedaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	if(form.getTipoBusqueda()!=null && form.getTipoBusqueda().equals("04")) return BuscarAvanzadoControllerImpl.ACCION_SQI;
    	return "AGREGA";
    }

    
    private boolean esVacioCajaTexto(AnalizarFormularioForm form){
		if(!atributoConValor(form.getBuscadorContenido()) &&
		!atributoConValor(form.getBuscadorContenidoSolo()))return true;
		return false;
	}
    
    
    private boolean atributoConValor(String variable){
		//Compruebo que el existe el campo analizado
		return(variable!=null && !variable.equals(""));
	}
    
    
//  Este método nos devuelve el texto de búsqueda; que puede venir de tres sitios diferentes:del buscador general, de la página de resultados el buscador de arriba y de la página de resultados del buscador de abajo
    private String devolverTextoBusqueda( String buscadorContenido, String buscadorContenidoSolo){
    	if(logger.isDebugEnabled())logger.debug("BusquedaSQIControllerImpl - devuelveTextoBusqueda");
//    	Determinamos cuales han sido los parametros de busqueda con los que se nos ha invocado.
//    	Se pulsa sobre el buscador general
    	if (buscadorContenido!= null && !buscadorContenido.trim().equals("")) return buscadorContenido.replaceAll(DetallarControllerImpl.JAVA_QUOTES, "&quot;");    	 	
//    	Se pulsa sobre el buscador inferior
    	else if (buscadorContenidoSolo!= null && !buscadorContenidoSolo.trim().equals("")) return buscadorContenidoSolo.replaceAll(DetallarControllerImpl.JAVA_QUOTES, "&quot;");

    	else return "";
    }
    
    
    private boolean validarAsterisco(String campo){
		if(logger.isDebugEnabled())logger.debug("BuscarAvanzadoControllerImpl - validaAsterisco: Campo=["+campo+"]");
		if(campo!=null){
			String[] palabras= campo.trim().split(" ");
			if(palabras.length>0)for(int i=0; i < palabras.length;i++) if(palabras[i].startsWith("*")||palabras[i].startsWith("?")||palabras[i].startsWith("&quot;"+"*"+"&quot;")||palabras[i].startsWith("'*'")) return false;
		}
		if(logger.isDebugEnabled())logger.debug("BuscarAvanzadoControllerImpl - validaAsterisco: Validado");
		return true;
	}
	
    
	private boolean validarAndQuery(String campo){
		if(logger.isDebugEnabled())logger.debug("BusquedaSQIControllerImpl - validarAndQuery +: Campo=["+campo+"]");
		if(campo!=null && !campo.trim().equals("") && (campo.trim().startsWith("+ ") || campo.trim().endsWith(" +")))return false;
		if(logger.isDebugEnabled())logger.debug("BusquedaSQIControllerImpl - validarAndQuery +: Validado");
		return true;
	}
    
	
	private String[] cargarNumeroResultadosLabel(Locale locale){
		  ResourceBundle datosResources = I18n.getInstance().getResource(APPLICATION_PROPERTIES, locale);
		  if(logger.isDebugEnabled())logger.debug("BusquedaSQIControllerImpl - cargarNumeroResultadosLabel: cargando labels de Numero resultados localizados en["+locale.getDisplayLanguage()+"]");
		  String[] formatoLabel = {datosResources.getString("listar.odecu.mostrar.resultados.consulta.cabecera.numeroResultados.diez"),
		  datosResources.getString("listar.odecu.mostrar.resultados.consulta.cabecera.numeroResultados.veinte"),datosResources.getString("listar.odecu.mostrar.resultados.consulta.cabecera.numeroResultados.treinta"),
		  datosResources.getString("listar.odecu.mostrar.resultados.consulta.cabecera.numeroResultados.cuarenta"),datosResources.getString("listar.odecu.mostrar.resultados.consulta.cabecera.numeroResultados.cincuenta")};
		  return formatoLabel;
	  }
	
	  private String[] cargarNumeroResultadosValue() throws IOException{
		  if(logger.isDebugEnabled())logger.debug("BusquedaSQIControllerImpl - cargarNumeroResultadosValue: cargando valores de Numero resultados");
		  String[] formatoArray = getPropertyValue("resultados.valor").split(",");
		  String[] formatoValue = {formatoArray[0], formatoArray[1], formatoArray[2], formatoArray[3], formatoArray[4]};
		  return formatoValue;
	  }
	  
	  
	  private String getPropertyValue(String sKey) throws IOException {
			InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/spring_buscador.properties");
			if (this.pSpringProperties == null) {
				pSpringProperties = new java.util.Properties();
				pSpringProperties.load(fIsSpringProperties);
			}
			fIsSpringProperties.close();
			if(logger.isDebugEnabled())logger.debug("BuscquedaSQIControllerImpl - getPropertyValue: Propiedad recuperada: " + sKey + " : "+ pSpringProperties.getProperty(sKey));
//			 devolvemos la propiedad
			return pSpringProperties.getProperty(sKey);
		}
	  
	  private String devolverIdiomaBuscador(String idioma) throws Exception{
			String idiomaNavegacion = "";
			if ((idioma != null) && !idioma.equals("") && !idioma.equals("#")) return idioma;
			else if(LdapUserDetailsUtils.estaAutenticado()){
					try{
						idiomaNavegacion=LdapUserDetailsUtils.getIdiomaBusqueda();
						if (idiomaNavegacion == null) idiomaNavegacion = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
					} catch (Exception e) {
						logger.error("BusquedaSQIControllerImpl - devolverIdiomaBuscador: Error en al obtener idioma búsqueda usuario LDAP, se recoge idioma de fichero propiedades.",e);
						idiomaNavegacion = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
					}
		   	}else idiomaNavegacion = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
			return idiomaNavegacion;
		}
	  
	  
	  private boolean contains (String word, String pattern){
			return (word.indexOf(pattern) != -1);
		}
	  
	  
//	  Metodo que elimina los espacios en blanco de la variable
	    private String eliminarEspaciosBlanco(String campo){
	    	if(logger.isDebugEnabled())logger.debug("BuscarAvanzadoControllerImpl - eliminarEspaciosBlanco: Campo antes=["+campo+"]");
	    	if(campo!=null){
	    		String retorno = "";
	    		while(!campo.trim().equals("")){
	    			if(contains(campo, "&quot;") && contains(campo.substring(campo.indexOf("&quot;")+1), "&quot;")){
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
	    				StringBuilder retornoBuilder = new StringBuilder();
	    				String[] palabras = campo.trim().split(DetallarControllerImpl.SEPARADOR_PALABRAS);
				    	for(int i=0; i < palabras.length ;i++) if(!palabras[i].equals("")) retornoBuilder.append(DetallarControllerImpl.SEPARADOR_PALABRAS + palabras[i]);
				    	retorno= retornoBuilder.toString();
				    	campo="";
	    			}
	    			if(logger.isDebugEnabled())logger.debug("BusquedaSQIControllerImpl - eliminarEspaciosBlanco: Campo depues=["+campo+"]");
	    		}
		    	return retorno.trim();
	    	}
	    	if(logger.isDebugEnabled())logger.debug("BusquedaSQUIControllerImpl - eliminarEspaciosBlanco FIN");
	    	return campo;
	    }
	  
	    
	    private void generarPaginas(EjecutarBusquedaSQIForm form, String pagina, Integer numRes, Integer numMaxResPagina, Integer numMaxPaginas){
	    	try{
//	    		Calculo el numero de paginas que salen del numero de resultados que se otienen por la busqueda.
	    		if(logger.isDebugEnabled())logger.debug("BusquedaSQIControllerImpl - generaPaginas");
	    		int numeroTotalPaginas = numRes.intValue() / numMaxResPagina.intValue();
	    		int paginaInt = Integer.parseInt(pagina);
	    		if ((numRes.intValue() % numMaxResPagina.intValue()) > 0) numeroTotalPaginas++;
//	    		Si el número de resultados por página es 1 no se pinta nada.
	    		if(numeroTotalPaginas==1){
	    			form.setPaginasAsArray(new String[1]);
	    			form.setAnterior(null);
	    			form.setSiguiente(null);   
	    		}else{
//	        		Calculo el siguiente y el anterior.
	    			if ((paginaInt -1) <= 0) form.setAnterior(null);
	    			else form.setAnterior(""+ (paginaInt - 1));
	    			
	    			if ((paginaInt) >= numeroTotalPaginas) form.setSiguiente(null);
	    				else form.setSiguiente(""+ (paginaInt + 1));
	    			
//	        		Calculo la horquilla de paginas por delante y por detras de la actual.
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
	    			if(logger.isDebugEnabled())logger.debug("BuscquedaSQIControllerImpl - generaPaginas FIN: listaDesde=["+listaDesde+"] listaHasta=["+listaHasta+"]");
	    		}
	    	}catch (Exception ex){
	    		logger.error("BusquedaSQIControllerImpl - generaPaginas ERROR: Error al generar las páginas. ",ex);
	    	}
	    }
	    
	    private String[] obtenerComunidades() throws RemoteException, Exception{		
			NodoVO[] nodoLista = this.getSrvNodoService().listarNodos();
	   	 	String[]comuni = new String[nodoLista.length];
	   	 	for(int j=0; j<nodoLista.length;j++) comuni[j]=nodoLista[j].getId().toString();
			return comuni;
		}
}